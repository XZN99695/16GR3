package com.qhit.lh.gr3.cyh.exam.classinfo.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.qhit.lh.gr3.cyh.exam.classinfo.bean.TClassInfo;
import com.qhit.lh.gr3.cyh.exam.classinfo.service.baseService;
import com.qhit.lh.gr3.cyh.exam.classinfo.service.impl.baseServiceImpl;
import com.qhit.lh.gr3.cyh.exam.common.config.Constant;
import com.qhit.lh.gr3.cyh.exam.paper.bean.PaperClass;
import com.qhit.lh.gr3.cyh.exam.paper.bean.TPaper;
import com.qhit.lh.gr3.cyh.exam.question.bean.TCourse;

public class classinfoAction extends ActionSupport {
	private TCourse course;
	private TPaper paper;
	private TClassInfo classInfo;
	private List<TClassInfo> clists;
	private baseService bs = new baseServiceImpl();

	public String getClassbyInfo() {
		System.out.println("getClassbyInfo:");
		String hql = "select c from TClassInfo c where c.major = '" + course.getMajor() + "'";
		clists = bs.getClassByInfo(hql);
		return Constant.gostudyStart;
	}
	
	public TCourse getCourse() {
		return course;
	}

	public void setCourse(TCourse course) {
		this.course = course;
	}

	public TClassInfo getClassInfo() {
		return classInfo;
	}

	public void setClassInfo(TClassInfo classInfo) {
		this.classInfo = classInfo;
	}

	public List<TClassInfo> getClists() {
		return clists;
	}

	public void setClists(List<TClassInfo> clists) {
		this.clists = clists;
	}

	public TPaper getPaper() {
		return paper;
	}

	public void setPaper(TPaper paper) {
		this.paper = paper;
	}
}
