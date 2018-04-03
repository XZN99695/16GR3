package com.qhit.lh.gr3.cyh.exam.startExam.utils;

import com.qhit.lh.gr3.cyh.exam.common.bean.PageBean;
import com.qhit.lh.gr3.cyh.exam.common.config.DataPageBean;
import com.qhit.lh.gr3.cyh.exam.startExam.service.BaseService;
import com.qhit.lh.gr3.cyh.exam.startExam.service.impl.BaseServiceImpl;

public class startExamUtils {
	public static PageBean getDataByInfo(String ccode,int up,String nowDate) {
		PageBean pb=new PageBean();
		BaseService bs = new BaseServiceImpl();
		//根据当前user的班级id查询出没有参加过考试的和考试状态为未开考和考试中的试卷集合
		String hql = "select p from PaperClass pc join pc.pper p join pc.classInfo c where p.pstate <> '考试结束' and c.ccode = '"
				+ ccode+"' and pc.endDate > '"+nowDate+"'";
		pb = DataPageBean.getPageBean(hql, pb, up);
		System.out.println("startExamUtils:"+pb.getData().size());
		return pb;
	}

}
