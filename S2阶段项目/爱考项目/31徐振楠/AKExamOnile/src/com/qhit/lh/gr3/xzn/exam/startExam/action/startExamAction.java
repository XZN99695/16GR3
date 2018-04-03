package com.qhit.lh.gr3.cyh.exam.startExam.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.catalina.User;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.qhit.lh.gr3.cyh.exam.common.bean.PageBean;
import com.qhit.lh.gr3.cyh.exam.common.bean.TUser;
import com.qhit.lh.gr3.cyh.exam.common.config.Constant;
import com.qhit.lh.gr3.cyh.exam.common.utils.RandomNumber;
import com.qhit.lh.gr3.cyh.exam.examScores.bean.TUserScores;
import com.qhit.lh.gr3.cyh.exam.paper.bean.TPaper;
import com.qhit.lh.gr3.cyh.exam.paper.utils.endPaper;
import com.qhit.lh.gr3.cyh.exam.question.bean.TWrittenTest;
import com.qhit.lh.gr3.cyh.exam.startExam.service.BaseService;
import com.qhit.lh.gr3.cyh.exam.startExam.service.impl.BaseServiceImpl;
import com.qhit.lh.gr3.cyh.exam.startExam.utils.RandomExam;
import com.qhit.lh.gr3.cyh.exam.startExam.utils.startExamUtils;
import com.sun.org.apache.bcel.internal.generic.NEW;

import javassist.compiler.ast.NewExpr;


public class startExamAction extends ActionSupport {
	private String hql;
	private String ccode;
	private TPaper paper;
	private TWrittenTest writtenTest;
	private TUserScores userScores=new TUserScores();
	private List<TPaper> papers;
	private PageBean pb=new PageBean();
	private int up;
	private List<Object> examDate=new ArrayList<Object>();
	private List<Object> endDate=new ArrayList<Object>();
	private List<TWrittenTest> writtenTests=new ArrayList<TWrittenTest>(); 
	private BaseService bs=new BaseServiceImpl();
	private List<Integer> qids=new ArrayList<Integer>();
	private List<String> answers=new ArrayList<String>();
	private String examEndDate;
	private List<Object> userExamQuestion;//创建试题与答案集合
	private TUser user=(TUser) ServletActionContext.getRequest().getSession().getAttribute("user");
	public String getPapersByInfo() throws ParseException {
		//获得当前在线的user
		//根据userId也就是stu学生的Id查询出该用户所在的班级
		hql="select stu.ccode from TStudent stu where stu.sid = "+Integer.valueOf(user.getName());
		ccode = (String) bs.getObjectByInfo(hql);System.out.println(ccode);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now =sdf.format(new Date());
		//根据当前user的班级id查询出考试状态为未开考和考试中的试卷集合
		pb=startExamUtils.getDataByInfo(ccode, up,now);
		System.out.println("1.pb.getData().size()"+pb.getData().size());
		//查询出当前班级和试卷对应的开考时间
		hql="select pc.examDate from PaperClass pc join pc.pper p join pc.classInfo c where p.pstate <> '考试结束' and c.ccode = '"+ccode+"' and pc.endDate > '"+now+"'";
		examDate=bs.getAll(hql);
		long startTime;long nowTime;
		//循环查看试卷开考时间对应现在时间
		for(int i=0;i<examDate.size();i++) {
			//拿到试卷开考时间
			startTime=sdf.parse((String)examDate.get(i)).getTime();
			//获得现在时间
			nowTime=new Date().getTime();
			//如果现在时间大于开考时间,证明已经开始考试
			if(nowTime>=startTime) {
				//然后获得对应试卷对象
				TPaper paper2=(TPaper)pb.getData().get(i);
				//查询该试卷对象的考试状态
				if(paper2.getPstate().equals("未开考")) {
					System.out.println("paper2.getPstate():"+"未开考");
					//时间已经到了的试卷的考试状态必须改变为:考试中
					//如果时间已经到了的试卷的考试状态还是未开考的话,就改为考试中!
						paper2.setPstate("考试中");
						bs.update(paper2);
						System.out.println("修改状态");
						
						//重新检索试卷PageBean
						pb=startExamUtils.getDataByInfo(ccode, 1 , now);
						//下标索引改变为0,重新开始循环查看,直到状态完全正确
						i=0;
						System.out.println("2.pb.getData().size()"+pb.getData().size());
				}
			}
		}
		return Constant.goexamlist;
	}
	
	//根据班级id和试卷id查询出试卷状态
	public String getExamInfo() {
		//根据班级和试卷id查询出试卷和对应的考试时间
		hql="select p, pc.examDate, pc.endDate from PaperClass pc join pc.pper p join pc.classInfo c where p.pid = "+paper.getPid()+" and c.ccode = '"+ccode+"'";
		System.out.println("getExamInfo:"+hql);			
		List<Object[]> lists=bs.getObjectData(hql);//多属性查询返回的是数组类型的list集合
		//数组中的属性便是要查询的一组属性
		//分别通过下标取出每一个试卷,对应的开考时间,结束时间
		Object[] objs=lists.get(0);
		paper=(TPaper)objs[0];
		
		examDate.add(lists.get(0)[1]);//开始时间
		endDate.add(lists.get(0)[2]);//结束时间
		up=paper.getWrittenTests().size();//试题数量
		//去查询数据库有没有该生和该试卷的考试信息,如果有使用这个信息
		hql="select us from TUserScores us where us.name = '"+user.getName()+"' and us.pid = "+paper.getPid();
		TUserScores us2 =(TUserScores) bs.getObjectByInfo(hql);
		if(us2!=null) {//如果为null则证明是正常结束考试
			System.out.println("us2不为空");
			String [] qids=us2.getQids().split(",");
			String [] ans=us2.getAnswers().split(",");
			for(int i=0;i<qids.length;i++) {
				String qid=qids[i];//把每一个qid拿出来
				//根据这些qid查询出对象
				TWrittenTest written=(TWrittenTest) bs.getObject(TWrittenTest.class, Integer.valueOf(qid));
				//放进试题writtenTests集合中
				writtenTests.add(written);
				//把用户选择的答案也分别筛选出来放进答案answers集合里
				String answer=ans[i];
				answers.add(answer);
			}
			return Constant.goexamQuestion;
		}
		System.out.println("us2为空");
		//获得该试卷对应的试题
		hql="select w from PaperWritten pw join pw.paper p join pw.writtenTest w where p.pid = "+paper.getPid();
		writtenTests=bs.getList(hql);
		System.out.println("第一次查询出试题");
		for (int i = 0; i < writtenTests.size(); i++) {
			System.out.println(writtenTests.get(i).getQtype());
		}
		//拿到对应试题数量的最小值为1,最大值为试题数量的随机数
		List<Integer> randNum=RandomNumber.getRandomNum(writtenTests.size(), 1, writtenTests.size());
		//通过随机数减1的下标从试卷对应试题集合中随机取出试题,由于Set集合会对元素进行排序,故使用List,将随机数放好,页面取出就好了
		writtenTests=RandomExam.getRandomExam(writtenTests, randNum);
		System.out.println("第二次查询出试题");
		for (int i = 0; i < writtenTests.size(); i++) {
			System.out.println(writtenTests.get(i).toString());
		}
		System.out.println("size:"+writtenTests.size());
		return Constant.goexamQuestion;
	}
	
	//试卷提交,开始改卷
	public String examEnd() throws ParseException {
		if(qids.size()>0) {
			System.out.println("examEnd:");
			endPaper.endPaperByPid(paper.getPid());//查询是否到了该试卷的最大结束时间,到了就设置为考试结束状态
			String wqids="";//所有的随机试题id
			String wanswers="";//用户的所有随机试题对应的选择答案
			int score=0;
			System.out.println("qids.size():"+qids.size());
			for(int i=0;i<qids.size();i++) {
				wqids+=qids.get(i)+",";//随机试题拼接
				wanswers+=answers.get(i)+",";//随机试题对应拼接
				if (!answers.get(i).equals("*")) {//答案为*的是没有做的题
					writtenTest=(TWrittenTest) bs.getObject(TWrittenTest.class, qids.get(i));//根据id获得笔试题对象
					String zqAnswer=writtenTest.getAnswer();//正确答案
					System.out.println("此时试题对象答案:"+writtenTest.toString());
					String yhAnswer=answers.get(i);//用户答案
					System.out.println("用户选择不是*,是否单选:"+zqAnswer.length());
					//这时要判断是不是多选,要比对分数
					if(zqAnswer.length()>1) {//答案的字符串length大于1的是多选
							System.out.println("证明多选:");
							String zq="";
							String yh="";
						for(int j=0;j<zqAnswer.length();j++) {//取出正确答案的一个值
							zq=zqAnswer.substring(j, j+1);
							System.out.println("zq:"+zq);
							for (int x = 0; x < yhAnswer.length(); x++) {//与用户答案的每一个值作对比,相同了就加分
								yh=yhAnswer.substring(x, x+1);
								System.out.println("yh:"+yh);
								if(yh.equals(zq)) {
									System.out.println("多选第"+(j+1)+"个比对相同:");
									score+=paper.getQscore()/zqAnswer.length();
									System.out.println("目前得分:"+score);
						//A.eq(abcd)肯定浪费时间,A.eq(a)当有相同值时直接break,用户答案不用全部检索,再往下也全都是不同的
									break;
								}
							}
						}
					}else {//否则是单选
						System.out.println("证明单选");
							if(yhAnswer.equals(zqAnswer)) {
								//用户的答案与试题正确答案作对比
								System.out.println("单选正确:"+yhAnswer+"/"+zqAnswer);
								score+=paper.getQscore();
								System.out.println("目前得分:"+score);
							}else {
								System.out.println("单选错误"+yhAnswer+"/"+zqAnswer);
								continue;
							}
					}
					
				}else {
					continue;
				}
			}
			System.out.println(wqids);
			System.out.println(wanswers);
			System.out.println(score);
			//获得当前用户名字name
			//根据当前用户名和试卷id查询考试答案表,如果答案表已经有该用户与该试卷的对应对象,则不能添加
			hql="select us from TUserScores us where us.name = '"+user.getName()+"' and us.pid = "+paper.getPid();
			TUserScores us2 =(TUserScores) bs.getObjectByInfo(hql);
			if(us2==null) {//如果为null则证明是正常结束考试
				userScores.setPid(paper.getPid());
				userScores.setName(user.getName());
				userScores.setQids(wqids);
				userScores.setAnswers(wanswers);
				userScores.setScore(score);
				bs.add(userScores);
				System.out.println("创建");
			}else {
				us2.setQids(wqids);
				us2.setAnswers(wanswers);
				us2.setScore(score);
				bs.update(us2);
				System.out.println("修改");
			}
		}
		System.out.println("qids.size()"+qids.size());
		return Constant.goExamPaperList;
	}
	public String questionSaveSession() {
//		TODO
		System.out.println("questionSaveSession:");
		String wqids="";//所有的随机试题id
		String wanswers="";//用户的所有随机试题对应的选择答案
		int score=0;
		for(int i=0;i<qids.size();i++) {
			wqids+=qids.get(i)+",";//随机试题拼接
			wanswers+=answers.get(i)+",";//随机试题对应拼接
			if (!answers.get(i).equals("*")) {//答案为*的是没有做的题
				writtenTest=(TWrittenTest) bs.getObject(TWrittenTest.class, qids.get(i));//根据id获得笔试题对象
				String zqAnswer=writtenTest.getAnswer();//正确答案
				String yhAnswer=answers.get(i);//用户答案
				//这时要判断是不是多选,要比对分数
				if(zqAnswer.length()>1) {//答案的字符串length大于1的是多选
						String zq="";
						String yh="";
					for(int j=0;j<zqAnswer.length();j++) {//取出正确答案的一个值
						zq=zqAnswer.substring(j, j+1);
						for (int x = 0; x < yhAnswer.length(); x++) {//与用户答案的每一个值作对比,相同了就加分
							yh=yhAnswer.substring(x, x+1);
							if(yh.equals(zq)) {
								score+=paper.getQscore()/zqAnswer.length();
					//A.eq(abcd)肯定浪费时间,A.eq(a)当有相同值时直接break,用户答案不用全部检索,再往下也全都是不同的
								break;
							}
						}
					}
				}else {//否则是单选
						if(yhAnswer.equals(zqAnswer)) {
							//用户的答案与试题正确答案作对比
							score+=paper.getQscore();
						}else {
							continue;
						}
				}
				
			}else {
				continue;
			}
		}
		//获得当前用户名字name
		//根据当前用户名和试卷id查询考试答案表,如果答案表已经有该用户与该试卷的对应对象,则不能添加
		hql="select us from TUserScores us where us.name = '"+user.getName()+"' and us.pid = "+paper.getPid();
		TUserScores us2 =(TUserScores) bs.getObjectByInfo(hql);
		if(us2==null) {//如果为null则证明是正常结束考试
			userScores.setPid(paper.getPid());
			userScores.setName(user.getName());
			userScores.setQids(wqids);
			userScores.setAnswers(wanswers);
			userScores.setScore(score);
			bs.add(userScores);
			System.out.println("创建");
		}else {
			us2.setQids(wqids);
			us2.setAnswers(wanswers);
			us2.setScore(score);
			bs.update(us2);
			System.out.println("修改");
		}
		return Constant.ajaxform;
	}
	public TPaper getPaper() {
		return paper;
	}

	public void setPaper(TPaper paper) {
		this.paper = paper;
	}

	public List<TPaper> getPapers() {
		return papers;
	}

	public void setPapers(List<TPaper> papers) {
		this.papers = papers;
	}

	public PageBean getPb() {
		return pb;
	}

	public void setPb(PageBean pb) {
		this.pb = pb;
	}

	public int getUp() {
		return up;
	}

	public void setUp(int up) {
		this.up = up;
	}

	public List<Object> getExamDate() {
		return examDate;
	}

	public void setExamDate(List<Object> examDate) {
		this.examDate = examDate;
	}

	public List<Object> getEndDate() {
		return endDate;
	}

	public void setEndDate(List<Object> endDate) {
		this.endDate = endDate;
	}

	public String getCcode() {
		return ccode;
	}

	public void setCcode(String ccode) {
		this.ccode = ccode;
	}

	
	public TUserScores getUserScores() {
		return userScores;
	}

	public void setUserScores(TUserScores userScores) {
		this.userScores = userScores;
	}

	public List<Integer> getQids() {
		return qids;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void setQids(List<Integer> qids) {
		this.qids = qids;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	public TWrittenTest getWrittenTest() {
		return writtenTest;
	}

	public void setWrittenTest(TWrittenTest writtenTest) {
		this.writtenTest = writtenTest;
	}

	public String getExamEndDate() {
		return examEndDate;
	}

	public void setExamEndDate(String examEndDate) {
		this.examEndDate = examEndDate;
	}

	public List<TWrittenTest> getWrittenTests() {
		return writtenTests;
	}

	public void setWrittenTests(List<TWrittenTest> writtenTests) {
		this.writtenTests = writtenTests;
	}

}
