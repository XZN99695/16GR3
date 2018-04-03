package com.qhit.lh.gr3.cyh.exam.question.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.qhit.lh.gr3.cyh.exam.common.bean.PageBean;
import com.qhit.lh.gr3.cyh.exam.common.config.Constant;
import com.qhit.lh.gr3.cyh.exam.common.config.DataPageBean;
import com.qhit.lh.gr3.cyh.exam.common.config.PoIinportfile;
import com.qhit.lh.gr3.cyh.exam.paper.bean.PaperWritten;
import com.qhit.lh.gr3.cyh.exam.paper.bean.TPaper;
import com.qhit.lh.gr3.cyh.exam.question.bean.TCourse;
import com.qhit.lh.gr3.cyh.exam.question.bean.TMachineTest;
import com.qhit.lh.gr3.cyh.exam.question.bean.TWrittenTest;
import com.qhit.lh.gr3.cyh.exam.question.service.BaseService;
import com.qhit.lh.gr3.cyh.exam.question.service.impl.BaseServiceImpl;
import com.sun.xml.internal.bind.v2.TODO;

public class QuestionAction extends ActionSupport {
	// 科目表
	private TCourse course;
	// 所有科目集合
	private List<TCourse> courses;
	// 笔试题
	private TWrittenTest writtenTest;
	private List<TWrittenTest> writs;
	// 机试题
	private TMachineTest machineTest;
	private List<TMachineTest> machies;
	// 业务类
	private BaseService bs = new BaseServiceImpl();
	private int up;
	// 分页Bean
	private PageBean pb = new PageBean();
	// 模板文件
	private File importfile;
	//机试题笔试题
	private List<Object> qiList=new ArrayList<Object>();
	private TPaper paper;
	//要替换的新qid
	private int qid;
	private String queryStr;
	// 获得所有科目
	public String getQuestionByInfo() {
		String hql = "select course from TCourse course where 1=1";
		if (course != null) {
			if (course.getMajor() != null && !course.getMajor().equals("请选择")) {
				hql += " and course.major = '" + course.getMajor() + "'";
			}

			if (course.getStage() != null && !course.getStage().equals("请选择")) {
				hql += " and course.stage = '" + course.getStage() + "'";
			}
		}
		System.out.println("输出：" + hql);
		courses = bs.getCourses(hql);
		if (courses.size() > 0) {
			course = courses.get(0);
			System.out.println(course.toString());
			return Constant.goquestion;
		} else {
			super.addActionError("该方向阶段下未添加科目~");
			return Constant.goquestion;
		}
	}

	// 获得某科目下所有笔试题
	public String getTWrittens() {
		if (course.getCsId() > 0) {
			System.out.println(course.getCsId() + "/" + up);
			String hql = "select t from TWrittenTest t where t.csId = " + course.getCsId();
			pb = DataPageBean.getPageBean(hql, pb, up);
		}else {
			System.out.println("course.getCsId() == 0");
		}
		return Constant.wquestions;
	}

	// 根据笔试题ID删除该笔试题
	public String delTWrittens() {
		TWrittenTest writtenTest1 = (TWrittenTest) bs.getObject(TWrittenTest.class, writtenTest.getQid());
		bs.delete(writtenTest1);
		System.out.println(Constant.redWlist);
		return Constant.redWlist;
	}

	// 添加笔试题
	public String addTWrittens() {
//		TODO
		TCourse course1 = (TCourse) bs.getObject(TCourse.class, course.getCsId());
		writtenTest.setCourse(course1);
		bs.add(writtenTest);
		return Constant.redWlist;
	}

	// 获得某科目下所有机试题
	public String getTMachines() {
		if (course.getCsId() > 0) {
			String hql = "select m from TMachineTest m where m.csId = " + course.getCsId();
			pb = DataPageBean.getPageBean(hql, pb, up);
		}
		return Constant.mquestions;
	}

	// 根据机试题ID删除该机试题
	public String delTMachines() {
		TMachineTest machineTest1 = (TMachineTest) bs.getObject(TMachineTest.class, machineTest.getQid());
		bs.delete(machineTest1);
		return Constant.redMlist;
	}

	// 添加笔试题
	public String addTMachines() {
		TCourse course1 = (TCourse) bs.getObject(TCourse.class, course.getCsId());
		machineTest.setCourse(course1);
		bs.add(machineTest);
		return Constant.redMlist;
	}

	public String importfile() throws FileNotFoundException {
		// 如果引入文件不为空
		if (importfile != null) {
			System.out.println("importfile不为空");
			// 创建文件输入流
			FileInputStream is = new FileInputStream(importfile);	
			// 通过读取Excel文件的方法读取该文件
			PoIinportfile ppf = new PoIinportfile();
			course=(TCourse) bs.getObject(TCourse.class, course.getCsId());
			writs = (List<TWrittenTest>) ppf.InportExcel(is, course);
			System.out.println(writs.size());
			// 通过for循环保存进笔试题表
			bs.Mubanfile(writs);
			System.out.println("结束");
		} else {
			System.out.println("importfile为空");         
		}
		return Constant.redWlist;
	}

	public String getTWBycsId() {
		System.out.println("getTWBycsId:"+course.getCsId());
		String hql="select w from TWrittenTest w where w.csId="+course.getCsId();
		if(queryStr!=null && queryStr!=""){
			if(queryStr.length()<=3) {//如果模糊查询字符串小于3检测第一个是不是数字
					char c=queryStr.charAt(0);
				if(Character.isDigit(c)) {//是数字的就查询第几条
					System.out.println("是数字");
					qiList=bs.getObjectByNum(hql, Integer.valueOf(queryStr));
					return Constant.goreviseQuestion;
				}else {//不是数字的也进行模糊查询
					hql+=" and w.qtype like '%"+queryStr+"%' or w.degree like'%"+queryStr+"%' or w.chapter like '%"
							+queryStr+"%' or w.qtitle like '%"+queryStr+"%'";
				}
			}else {//大于3的直接进行模糊查询
				hql+=" and w.qtype like '%"+queryStr+"%' or w.degree like'%"+queryStr+"%' or w.chapter like '%"
						+queryStr+"%' or w.qtitle like '%"+queryStr+"%'";
			}
		}else {
			if(writtenTest.getQtype()!= null&&!writtenTest.getQtype().equals("请选择")) {
				System.out.println("getQtype不是请选择");
				hql+=" and w.qtype='"+writtenTest.getQtype()+"'";
			}
			if(writtenTest.getDegree()!= null&&!writtenTest.getDegree().equals("请选择")) {
				System.out.println("getDegree不是请选择");
				hql+=" and w.degree='"+writtenTest.getDegree()+"'";
			}
		}
		
		System.out.println(hql);
		qiList=bs.getTWBycsId(hql);
		return Constant.goreviseQuestion;
	}
	//替换试题
	public String reviseQuestion() {
		System.out.println("reviseQuestion:");
		System.out.println(writtenTest.getQid()+"/"+qid);
		System.out.println(paper.getPid());
		paper=(TPaper) bs.getObject(TPaper.class, paper.getPid());
		for(TWrittenTest w:paper.getWrittenTests()) {
			if(qid==w.getQid()) {
				System.out.println("相同返回");
				return Constant.redpaper_getQuestionById;
			}
		}
		PaperWritten pw=bs.getPWritten(paper.getPid(), writtenTest.getQid());
		System.out.println(pw.toString());
		pw.setQid(qid);
		bs.update(pw);
		return Constant.redpaper_getQuestionById;
	}
	public String getWrittenById() {
		if(writtenTest.getQid()>0) {
			System.out.println("getWrittenById:courseID:"+course.getCsId());
			course=(TCourse) bs.getObject(TCourse.class, course.getCsId());
			writtenTest=(TWrittenTest) bs.getObject(TWrittenTest.class, writtenTest.getQid());
		}
		return Constant.toupdateWritten;
	}
	public String updateWritten() {
		if(writtenTest.getQid()>0) {
			System.out.println("updateWritten:");
			course=(TCourse) bs.getObject(TCourse.class, writtenTest.getCsId());
			writtenTest.setCourse(course);
			bs.update(writtenTest);
		}
		return Constant.redWlist;
	}
	public List<TCourse> getCourses() {
		return courses;
	}

	public void setCourses(List<TCourse> courses) {
		this.courses = courses;
	}

	public TCourse getCourse() {
		return course;
	}

	public void setCourse(TCourse course) {
		this.course = course;
	}

	public List<TWrittenTest> getWrits() {
		return writs;
	}

	public void setWrits(List<TWrittenTest> writs) {
		this.writs = writs;
	}

	public List<TMachineTest> getMachies() {
		return machies;
	}

	public void setMachies(List<TMachineTest> machies) {
		this.machies = machies;
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

	public TWrittenTest getWrittenTest() {
		return writtenTest;
	}

	public void setWrittenTest(TWrittenTest writtenTest) {
		this.writtenTest = writtenTest;
	}

	public TMachineTest getMachineTest() {
		return machineTest;
	}

	public void setMachineTest(TMachineTest machineTest) {
		this.machineTest = machineTest;
	}

	public File getImportfile() {
		return importfile;
	}

	public void setImportfile(File importfile) {
		this.importfile = importfile;
	}

	public List<Object> getQiList() {
		return qiList;
	}

	public void setQiList(List<Object> qiList) {
		this.qiList = qiList;
	}

	public TPaper getPaper() {
		return paper;
	}

	public void setPaper(TPaper paper) {
		this.paper = paper;
	}

	public int getQid() {
		return qid;
	}

	public void setQid(int qid) {
		this.qid = qid;
	}

	public String getQueryStr() {
		return queryStr;
	}

	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}
}
