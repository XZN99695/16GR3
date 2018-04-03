package com.qhit.lh.gr3.cyh.exam.paper.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.qhit.lh.gr3.cyh.exam.common.bean.TUser;
import com.qhit.lh.gr3.cyh.exam.paper.bean.TPaper;
import com.qhit.lh.gr3.cyh.exam.startExam.service.BaseService;
import com.qhit.lh.gr3.cyh.exam.startExam.service.impl.BaseServiceImpl;

public class endPaper {
public static void endPaperByPid(int pid) throws ParseException {
	BaseService bs=new BaseServiceImpl();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String hql="select pc.endDate from PaperClass pc where pc.pid = "+ pid;
	List<Object>endDate=bs.getAll(hql);
	long maxEndDate=0;//所有试题-班级对象中,该试卷最大的结束时间,只有到了这个时间才能够将试卷的状态改为考试结束
	Date date=null;
	for(Object obj:endDate) {
		date=sdf.parse((String)obj);
		long time=date.getTime();
		maxEndDate=maxEndDate<time ? time : maxEndDate;//三目运算符,取最大的结束时间
	}
	System.out.println(maxEndDate);
	if(maxEndDate<=new Date().getTime()) {
		TPaper paper=(TPaper) bs.getObject(TPaper.class, pid);
		paper.setPstate("考试结束");
		bs.update(paper);
	}
}
}
