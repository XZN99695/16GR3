package com.qhit.lh.gr3.cyh.exam.examScores.action;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.fail;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.qhit.lh.gr3.cyh.exam.classinfo.bean.TClassInfo;
import com.qhit.lh.gr3.cyh.exam.common.bean.PageBean;
import com.qhit.lh.gr3.cyh.exam.common.bean.TUser;
import com.qhit.lh.gr3.cyh.exam.common.config.Constant;
import com.qhit.lh.gr3.cyh.exam.common.config.DataPageBean;
import com.qhit.lh.gr3.cyh.exam.examScores.bean.TUserScores;
import com.qhit.lh.gr3.cyh.exam.examScores.bean.countBean;
import com.qhit.lh.gr3.cyh.exam.examScores.service.BaseService;
import com.qhit.lh.gr3.cyh.exam.examScores.service.impl.BaseServiceImpl;
import com.qhit.lh.gr3.cyh.exam.paper.bean.PaperClass;
import com.qhit.lh.gr3.cyh.exam.paper.bean.TPaper;
import com.qhit.lh.gr3.cyh.exam.question.bean.TCourse;
import com.qhit.lh.gr3.cyh.exam.question.bean.TWrittenTest;

public class scoresAction extends ActionSupport {
	private BaseService bs = new BaseServiceImpl();
	private String hql;
	private TUserScores us;
	private PageBean pb = new PageBean();
	private PageBean scoresPb = new PageBean();
	private int up;
	private PaperClass pClass = new PaperClass();
	private List<TWrittenTest> writtenTests = new ArrayList<TWrittenTest>();
	private List<String> answers = new ArrayList<String>();
	private TUser user;
	private TClassInfo clazz;
	private TCourse course;
	private TPaper paper;
	private List<TClassInfo> classinfos = new ArrayList<TClassInfo>();
	private List<TCourse> courses = new ArrayList<TCourse>();
	private List<TPaper> papers = new ArrayList<TPaper>();
	private List<Object[]> objs = new ArrayList<Object[]>();
	private List<TUserScores> uScores = new ArrayList<TUserScores>();
	private List<PaperClass> pcs = new ArrayList<PaperClass>();
	private String userName;
	private countBean countbean = new countBean();

	// 获得完成试卷列表
	public String getScoresList() {
		user = (TUser) ServletActionContext.getRequest().getSession().getAttribute("user");
		hql = "select pc from PaperClass pc where pc.ccode = ( select stu.ccode from TStudent stu where stu.sid = '"
				+ user.getName() + "' ) and pc.pid in(select us.pid from TUserScores us where us.name = '"
				+ user.getName() + "') order by pc.examDate";
		pb = DataPageBean.getPageBean(hql, pb, up);
		hql = "select us from TUserScores us where us.name = '" + user.getName() + "'";
		scoresPb = DataPageBean.getPageBean(hql, scoresPb, up);
		for (Object obj : scoresPb.getData()) {
			us = (TUserScores) obj;
			uScores.add(us);
		}
		return Constant.goscoresList;
	}

	// 考试成绩
	public String getExamScore() {
		if (us.getPid() > 0) {
			user = (TUser) ServletActionContext.getRequest().getSession().getAttribute("user");
			hql = "select pc from PaperClass pc where pc.pid = " + us.getPid()
					+ " and pc.ccode = (select stu.ccode from TStudent stu where stu.sid = '" + user.getName() + "')";
			pClass = (PaperClass) bs.getObjectByInfo(hql);
			hql = "select us from TUserScores us where us.name = '" + user.getName() + "' and us.pid = " + us.getPid();
			us = (TUserScores) bs.getObjectByInfo(hql);
			// 查询出成绩表的随机试题id,根据,分割成每一个数组
			String[] qids = us.getQids().split(",");
			String[] ans = us.getAnswers().split(",");
			for (int i = 0; i < qids.length; i++) {
				String qid = qids[i];// 把每一个qid拿出来
				// 根据这些qid查询出对象
				TWrittenTest written = (TWrittenTest) bs.getObject(TWrittenTest.class, Integer.valueOf(qid));
				// 放进试题writtenTests集合中
				writtenTests.add(written);
				// 把用户选择的答案也分别筛选出来放进答案answers集合里
				String answer = ans[i];
				answers.add(answer);
			}
			up = writtenTests.size();
			System.out.println(writtenTests.size() + "/" + answers.size());
		}
		return Constant.goExamScore;
	}

	public String getData() {
		hql = "select c from TClassInfo c";
		classinfos = bs.getClazzs(hql);
		System.out.println(classinfos.size());
		hql = "select course from TCourse course";
		courses = bs.getCourses(hql);
		System.out.println(courses.size());
		return Constant.gopaperInfoList;
	}

	public String getPapersByInfo() {
		hql = "select distinct p from PaperClass pc join pc.pper p where p.pstate <> '未开考'";
		if (course != null && course.getCsId() > 0) {
			course = (TCourse) bs.getObject(TCourse.class, course.getCsId());
			hql += " and p.csId = " + course.getCsId();
		}
		if (paper != null && paper.getPtype() != null && paper.getPtype() != "" && paper.getPtype() != "请选择") {
			hql += " and p.ptype = '" + paper.getPtype() + "'";
		}
		System.out.println("getDateByInfo:" + hql);
		pb = DataPageBean.getPageBean(hql, pb, up);
		System.out.println(pb.getData().size());
		return Constant.gopaperInfoList;
	}

	// 查询试卷对应的班级
	public String getClassByPid() {
		// TODO
		if (paper.getPid() > 0) {
			paper = (TPaper) bs.getObjectByInfo("select p from TPaper p where p.pid = " + paper.getPid());
			hql = "select us from TUserScores us join TStudent stu on us.name = stu.sid where us.pid = "
					+ paper.getPid();// 查询出所有的考生成绩
			System.out.println("userName:");
			if (userName == "") {
				System.out.println("为空");
			} else if (userName == null) {
				System.out.println("为null");
			}
			if (clazz != null && clazz.getCcode() != "" && clazz.getCcode() != "请选择" && userName == "") {
				hql += " and stu.ccode = '" + clazz.getCcode() + "'";
			}
			if (userName != null && userName != "" && clazz == null) {
				hql += " and us.name in (select stu.sid from TStudent stu where stu.sname like '%" + userName + "%')";
			}
			if (userName != null && userName != "" && clazz != null && clazz.getCcode() != ""
					&& clazz.getCcode() != "请选择") {
				hql += " and us.name in (select stu.sid from TStudent stu where stu.sname like '%" + userName + "%')";
			}
			System.out.println("getClassByPid" + hql);
			uScores = bs.getList(hql);
			// 然后根据每一个考生成绩对象的考生name查询出该生对应的班级,在结合考生成绩对象的试卷pid,查询出每一个对应的PaperClass对象
			// 存进pcs集合
			int paper_num = uScores.size();// 总人数
			int pass_num = 0;// 及格人数
			for (TUserScores userScores : uScores) {// 由于参与每张试卷考试的用户肯定是不一样的,对应着的分数也是唯一的
				if (userScores.getScore() > 60) {// 所以如果分数大于60,即代表一个用户及格了
					pass_num++;// 及格人数加一
				}
				long pass_rate = (Math.round((pass_num / paper_num) * 100)) / 100;// 取两位小数
				// 设置countBean
				countbean.setPaper_num(paper_num);// 传参总人数
				countbean.setPass_num(pass_num);// 传参及格人数
				DecimalFormat decimalFormat = new DecimalFormat("0.00%");// Numberformat的子类,decimalformat小数点解析,返回String
				// 此处转换为百分数,相除的数字乘以100+.00%
				countbean.setPass_rate(decimalFormat.format(pass_rate));// 传参及格率
				hql = "select pc from PaperClass pc where pc.pid = " + userScores.getPid() + " and pc.ccode"
						+ " =(select stu.ccode from TStudent stu where stu.sid = '" + userScores.getName() + "')";
				pClass = bs.getPcs(hql);
				pcs.add(pClass);
			}
			System.out.println(uScores.size() + "/" + pcs.size());
		}
		return Constant.gopaper_Class;
	}

	public String getExamScoreTwo() {
		// 根据用户id查询出班级代码,再根据班级代码和试卷pid查询出PaperClass对象
		hql = "select pc from PaperClass pc where pc.ccode = (select stu.ccode from TStudent stu where stu.sid = '"
				+ us.getName() + "')" + " and pc.pid = " + us.getPid();
		pClass = bs.getPcs(hql);
		// us.name=user.uid=stu.sid
		// 再根据用户id和试卷pid查询出考试成绩对象
		hql = "select us from TUserScores us where us.name = '" + us.getName() + "' and us.pid = " + us.getPid();
		us = (TUserScores) bs.getObjectByInfo(hql);
		// 然后把考生成绩对象的随机试题和对应的随机答案检索出来
		String[] qids = us.getQids().split(",");
		String[] wanswers = us.getAnswers().split(",");
		up = qids.length;
		TWrittenTest written;
		String answer = "";
		for (int i = 0; i < qids.length; i++) {
			written = (TWrittenTest) bs.getObject(TWrittenTest.class, Integer.valueOf(qids[i]));
			writtenTests.add(written);
			answer = wanswers[i];
			answers.add(answer);
		}
		System.out.println(writtenTests.size() + "/" + answers.size());
		return Constant.goExamScore;
	}

	// 结束考试
	public String stopExam() {
		if (paper.getPid() > 0) {
			TPaper tPaper = (TPaper) bs.getObject(TPaper.class, paper.getPid());
			tPaper.setPstate("考试结束");
			bs.update(tPaper);
		}
		return Constant.gopaperscoresList;
	}

	public TUserScores getUs() {
		return us;
	}

	public int getUp() {
		return up;
	}

	public List<TUserScores> getuScores() {
		return uScores;
	}

	public void setUs(TUserScores us) {
		this.us = us;
	}

	public void setUp(int up) {
		this.up = up;
	}

	public void setuScores(List<TUserScores> uScores) {
		this.uScores = uScores;
	}

	public PageBean getPb() {
		return pb;
	}

	public void setPb(PageBean pb) {
		this.pb = pb;
	}

	public PageBean getScoresPb() {
		return scoresPb;
	}

	public void setScoresPb(PageBean scoresPb) {
		this.scoresPb = scoresPb;
	}

	public PaperClass getpClass() {
		return pClass;
	}

	public void setpClass(PaperClass pClass) {
		this.pClass = pClass;
	}

	public List<TWrittenTest> getWrittenTests() {
		return writtenTests;
	}

	public void setWrittenTests(List<TWrittenTest> writtenTests) {
		this.writtenTests = writtenTests;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	public TUser getUser() {
		return user;
	}

	public void setUser(TUser user) {
		this.user = user;
	}

	public List<TClassInfo> getClassinfos() {
		return classinfos;
	}

	public List<TCourse> getCourses() {
		return courses;
	}

	public List<TPaper> getPapers() {
		return papers;
	}

	public void setClassinfos(List<TClassInfo> classinfos) {
		this.classinfos = classinfos;
	}

	public void setCourses(List<TCourse> courses) {
		this.courses = courses;
	}

	public void setPapers(List<TPaper> papers) {
		this.papers = papers;
	}

	public TClassInfo getClazz() {
		return clazz;
	}

	public TCourse getCourse() {
		return course;
	}

	public TPaper getPaper() {
		return paper;
	}

	public void setClazz(TClassInfo clazz) {
		this.clazz = clazz;
	}

	public void setCourse(TCourse course) {
		this.course = course;
	}

	public void setPaper(TPaper paper) {
		this.paper = paper;
	}

	public List<Object[]> getObjs() {
		return objs;
	}

	public void setObjs(List<Object[]> objs) {
		this.objs = objs;
	}

	public List<PaperClass> getPcs() {
		return pcs;
	}

	public void setPcs(List<PaperClass> pcs) {
		this.pcs = pcs;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public countBean getCountbean() {
		return countbean;
	}

	public void setCountbean(countBean countbean) {
		this.countbean = countbean;
	}
}
