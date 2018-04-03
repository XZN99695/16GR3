package com.qhit.lh.gr3.cyh.exam.paper.action;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com.opensymphony.xwork2.ActionSupport;
import com.qhit.lh.gr3.cyh.exam.classinfo.bean.TClassInfo;
import com.qhit.lh.gr3.cyh.exam.common.bean.PageBean;
import com.qhit.lh.gr3.cyh.exam.common.config.Constant;
import com.qhit.lh.gr3.cyh.exam.common.config.DataPageBean;
import com.qhit.lh.gr3.cyh.exam.examScores.bean.TUserScores;
import com.qhit.lh.gr3.cyh.exam.paper.bean.PaperClass;
import com.qhit.lh.gr3.cyh.exam.paper.bean.TPaper;
import com.qhit.lh.gr3.cyh.exam.paper.service.BaseService;
import com.qhit.lh.gr3.cyh.exam.paper.service.impl.BaseServiceImpl;
import com.qhit.lh.gr3.cyh.exam.paper.utils.RandomPaperUtil;
import com.qhit.lh.gr3.cyh.exam.question.bean.TCourse;
import com.qhit.lh.gr3.cyh.exam.question.bean.TMachineTest;
import com.qhit.lh.gr3.cyh.exam.question.bean.TWrittenTest;


public class PaperAction extends ActionSupport {
	private BaseService bs = new BaseServiceImpl();
	private TPaper paper;
	private PageBean pb = new PageBean();
	private int up;
	private TCourse course;
	private TWrittenTest writtenTest;
	private TMachineTest machineTest;
	private List<TCourse> courses;
	private List<TWrittenTest> dan1list, dan2list, dan3list, duo1list, duo2list, duo3list;
	private int maxdan1, maxdan2, maxdan3, maxduo1, maxduo2, maxduo3;
	//已选题数
	private int qtotalnum;
	private Set<Integer> writtenTests=new HashSet<Integer>();
	private TClassInfo classInfo;
	private List<PaperClass> paperClasses;
	private List<Object[]> objects=new ArrayList<Object[]>();
	// 查询Paper数据集合
	public String getPaperByInfo() {
		// 根据条件添加hql语句
		String hql = "select p from TPaper p where 1=1";
		if (paper != null) {
			System.out.println("course:"+course.getCsId());
			if (paper.getPtype() != null && paper.getPtype() != "") {
				hql += " and p.ptype = '" + paper.getPtype() + "'";
			}
			if (paper.getPstate() != null && paper.getPstate() != "") {
				hql += " and p.pstate ='" + paper.getPstate() + "'";
			}
			if (course.getCsId() > 0) {
				course=(TCourse) bs.getObject(TCourse.class, course.getCsId());
				hql += " and p.csId = " + course.getCsId();
			}
		}
		System.out.println("getPaperByInfo:"+hql);
		// 加载数据PageBean
		pb = DataPageBean.getPageBean(hql, pb, up);
		return Constant.gopaper;
	}

	// 根据科目算出各种题目类型的个数
	public String getQuestionByCsId() {
		// 首先我需要可以重复利用的可根据科目csId和题目类型和难易程度查询试题集合的模块
		dan1list = bs.geTWrittenTestsByInfo(course.getCsId(), "单选", "简单");
		maxdan1 = dan1list.size();
		dan2list = bs.geTWrittenTestsByInfo(course.getCsId(), "单选", "一般");
		maxdan2 = dan2list.size();
		dan3list = bs.geTWrittenTestsByInfo(course.getCsId(), "单选", "困难");
		maxdan3 = dan3list.size();
		duo1list = bs.geTWrittenTestsByInfo(course.getCsId(), "多选", "简单");
		maxduo1 = duo1list.size();
		duo2list = bs.geTWrittenTestsByInfo(course.getCsId(), "多选", "一般");
		maxduo2 = duo2list.size();
		duo3list = bs.geTWrittenTestsByInfo(course.getCsId(), "多选", "困难");
		maxduo3 = duo3list.size();
		// 再用查询出来的试题集合大size作为试题数量
		return Constant.goJsonRandom;
	}

	// 根据json传来的条件检索数据
	public String getCourseByInfo() {
//		TODO
		String hql = "select c.csId,c.csName from TCourse c where 1=1";
		System.out.println("getCourseByInfo:"+course.getStage()+"/"+course.getMajor());
		if (course.getStage() != null && course.getMajor() != null) {
			hql += " and c.stage = '" + course.getStage() + "' and c.major = '" + course.getMajor() + "'";
		}
		System.out.println(hql);
		objects = bs.getObjectsData(hql);
		Object[] objs=objects.get(1);
		for(Object o:objs) {
			System.out.println(o);
		}
		return Constant.goJsonpaper;
	}

	// 获得随机区间(大减小)内随机数
	public String createByRandom() {
		System.out.println("createByRandom方法");
		// 首先查出来各种类型属性的list集合
		dan1list = bs.geTWrittenTestsByInfo(course.getCsId(), "单选", "简单");
		dan2list = bs.geTWrittenTestsByInfo(course.getCsId(), "单选", "一般");
		dan3list = bs.geTWrittenTestsByInfo(course.getCsId(), "单选", "困难");
		duo1list = bs.geTWrittenTestsByInfo(course.getCsId(), "多选", "简单");
		duo2list = bs.geTWrittenTestsByInfo(course.getCsId(), "多选", "一般");
		duo3list = bs.geTWrittenTestsByInfo(course.getCsId(), "多选", "困难");
		// 根据用户需求的随机数数量,使用最小值1,最大值某一数据集合的size,获得1~size区间内的用户需求数量的随机数//现在需要一个调度分配这些listsize的Service
		// 拿到了所有试题
		Set<TWrittenTest> randRompaper = RandomPaperUtil.getNum(dan1list, dan2list, dan3list, duo1list, duo2list, duo3list,
				maxdan1, maxdan2, maxdan3, maxduo1, maxduo2, maxduo3);
		// 设置试题对试卷关系
		paper.setWrittenTests(randRompaper);
		System.out.println("设置试题对试卷关系完成");
		//根据科目ID查出科目赋值给试卷科目关系
		course=(TCourse) bs.getObject(TCourse.class, course.getCsId());
		System.out.println("根据科目ID查出科目");
		paper.setCourse(course);
		System.out.println("赋值给试卷科目关系");
		System.out.println(paper.toString());
		//保存试卷
		bs.add(paper);
		return Constant.redPlist;
	}

	public String createBySelect() {
		if(course.getCsId()>0) {
			System.out.println("paper.getQscore():"+paper.getQscore());
			System.out.println("createBySelect:"+writtenTests.size());
			System.out.println(course.getCsId());
			course=(TCourse) bs.getObject(TCourse.class, course.getCsId());
			System.out.println("查询到的courseid:"+course.toString());
			//设置科目关系
			paper.setCourse(course);
			System.out.println("1:"+paper.toString());
			Set<TWrittenTest> ws=new HashSet<TWrittenTest>();
			for(int qid:writtenTests) {
				System.out.println(qid);
				writtenTest=(TWrittenTest) bs.getObject(TWrittenTest.class, qid);
				ws.add(writtenTest);
			}
			for(TWrittenTest t:ws) {
				System.out.println(t.toString());
			}
			//设置笔试题关系
			System.out.println("笔试题集合:"+ws.size());
			paper.setWrittenTests(ws);
			System.out.println(paper.toString());
			bs.add(paper);
		}
		return Constant.redPlist;
	}
	
	public String getQuestionById() {
		System.out.println("getQuestionById:");
//		course=(TCourse) bs.getObject(TCourse.class, paper.getCsId());
		paper=(TPaper) bs.getObject(TPaper.class, paper.getPid());
		return Constant.gopaperInfo;
	}
	//删除试卷
	public String delPaperById() {
		System.out.println("delPaperById:");
		System.out.println(paper.getPid());
		String hql="select pc from PaperClass pc where pc.pid = "+paper.getPid();
		List<Object> dels=bs.getAll(hql);
		if(dels!=null) {
			for(Object obj:dels) {
				bs.delete((PaperClass)obj);
			}
		}
		
		hql="select us from TUserScores us where us.pid = "+paper.getPid();
		List<Object> us=bs.getAll(hql);
		for(Object o:us) {
			bs.delete((TUserScores)o);
		}
		
		paper=(TPaper) bs.getObject(TPaper.class, paper.getPid());
		bs.delete(paper);
		return Constant.redPlist;
	}
	//根据csId查询笔试题
	public String getTWBycsId() {
		System.out.println("getTWBycsId:");
		if(course.getCsId()>0) {
			course=(TCourse) bs.getObject(TCourse.class, course.getCsId());
			String hql="select w from TWrittenTest w where w.csId="+course.getCsId();
			pb=DataPageBean.getPageBean(hql, pb, up);
		}else {
			System.out.println("course.getCsId()=0");
		}
		if (writtenTests!=null) {
			for(int wr:writtenTests) {
				System.out.print(wr);
			}
		}
		return Constant.goselectPapaer;
	}
	
		// 5.赋值给paper的多对多关系PaperClass Set集合
		// 6.保存paper
		public String createExam() throws ParseException {
			System.out.println("createExam:"+paperClasses.size());
			for(int i =0;i<paperClasses.size();i++) {
				if(paperClasses.get(i).getCcode()==""||paperClasses.get(i).getExamDate()=="") {
					continue;
				}
				String hql="select p from PaperClass p where p.pid = "+paper.getPid()+" and p.ccode = '"+paperClasses.get(i).getCcode()+"'";
				Object obj=bs.getObjectByInfo(hql);
				if (obj!=null) {
					continue;
				}
				PaperClass paperClass=new PaperClass();
				// 1.试卷pid,根据试卷pid查询出试卷
				paper=(TPaper) bs.getObject(TPaper.class, paper.getPid());
				paperClass.setPper(paper);
				// 2.班级ccode,根据班级ccode查询出班级
				hql = "select c from TClassInfo c where c.ccode = '" + paperClasses.get(i).getCcode()+"'";
				classInfo=(TClassInfo) bs.getObjectByInfo(hql);
				paperClass.setClassInfo(classInfo);
				// 3.考试开始时间examDate,加上查询出的试卷的需要考试时间得出endDate考试结束时间
					//开始时间
				String examDate=paperClasses.get(i).getExamDate();
				paperClass.setExamDate(examDate);
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date startTime=sdf.parse(examDate);
				Date overDate=new Date();
				overDate.setTime(startTime.getTime()+paper.getPtime()*60*1000);
				System.out.println(overDate);
				String endDate=sdf.format(overDate);
				paperClass.setEndDate(endDate);
//				// 4.将这一行数据封装成PaperClass中间表对象
				bs.add(paperClass);
			}
			return Constant.redPlist;
		}
//结束考试
		public String stopExam() {
			if(paper.getPid()>0) {
				TPaper tPaper=(TPaper) bs.getObject(TPaper.class, paper.getPid());
				tPaper.setPstate("考试结束");
				bs.update(tPaper);
			}
			return Constant.redPlist;
		}
	public void setPaper(TPaper paper) {
		this.paper = paper;
	}

	public TPaper getPaper() {
		return paper;
	}

	public int getUp() {
		return up;
	}

	public void setUp(int up) {
		this.up = up;
	}

	public PageBean getPb() {
		return pb;
	}

	public void setPb(PageBean pb) {
		this.pb = pb;
	}

	public TCourse getCourse() {
		return course;
	}

	public void setCourse(TCourse course) {
		this.course = course;
	}

	public List<TCourse> getCourses() {
		return courses;
	}

	public void setCourses(List<TCourse> courses) {
		this.courses = courses;
	}

	public List<TWrittenTest> getDan1list() {
		return dan1list;
	}

	public List<TWrittenTest> getDan2list() {
		return dan2list;
	}

	public List<TWrittenTest> getDan3list() {
		return dan3list;
	}

	public List<TWrittenTest> getDuo1list() {
		return duo1list;
	}

	public List<TWrittenTest> getDuo2list() {
		return duo2list;
	}

	public TWrittenTest getWrittenTest() {
		return writtenTest;
	}

	public TMachineTest getMachineTest() {
		return machineTest;
	}

	public void setWrittenTest(TWrittenTest writtenTest) {
		this.writtenTest = writtenTest;
	}

	public void setMachineTest(TMachineTest machineTest) {
		this.machineTest = machineTest;
	}

	public List<TWrittenTest> getDuo3list() {
		return duo3list;
	}

	public void setDan1list(List<TWrittenTest> dan1list) {
		this.dan1list = dan1list;
	}

	public void setDan2list(List<TWrittenTest> dan2list) {
		this.dan2list = dan2list;
	}

	public void setDan3list(List<TWrittenTest> dan3list) {
		this.dan3list = dan3list;
	}

	public void setDuo1list(List<TWrittenTest> duo1list) {
		this.duo1list = duo1list;
	}

	public void setDuo2list(List<TWrittenTest> duo2list) {
		this.duo2list = duo2list;
	}

	public void setDuo3list(List<TWrittenTest> duo3list) {
		this.duo3list = duo3list;
	}

	public int getMaxdan1() {
		return maxdan1;
	}

	public int getMaxdan2() {
		return maxdan2;
	}

	public int getMaxdan3() {
		return maxdan3;
	}

	public int getMaxduo1() {
		return maxduo1;
	}

	public int getMaxduo2() {
		return maxduo2;
	}

	public int getMaxduo3() {
		return maxduo3;
	}

	public void setMaxdan1(int maxdan1) {
		this.maxdan1 = maxdan1;
	}

	public void setMaxdan2(int maxdan2) {
		this.maxdan2 = maxdan2;
	}

	public void setMaxdan3(int maxdan3) {
		this.maxdan3 = maxdan3;
	}

	public void setMaxduo1(int maxduo1) {
		this.maxduo1 = maxduo1;
	}

	public void setMaxduo2(int maxduo2) {
		this.maxduo2 = maxduo2;
	}

	public void setMaxduo3(int maxduo3) {
		this.maxduo3 = maxduo3;
	}

	public int getQtotalnum() {
		return qtotalnum;
	}

	public void setQtotalnum(int qtotalnum) {
		this.qtotalnum = qtotalnum;
	}

	public Set<Integer> getWrittenTests() {
		return writtenTests;
	}

	public void setWrittenTests(Set<Integer> writtenTests) {
		this.writtenTests = writtenTests;
	}

	public List<PaperClass> getPaperClasses() {
		return paperClasses;
	}

	public void setPaperClasses(List<PaperClass> paperClasses) {
		this.paperClasses = paperClasses;
	}

	public TClassInfo getClassInfo() {
		return classInfo;
	}

	public void setClassInfo(TClassInfo classInfo) {
		this.classInfo = classInfo;
	}

	public List<Object[]> getObjects() {
		return objects;
	}

	public void setObjects(List<Object[]> objects) {
		this.objects = objects;
	}

}

