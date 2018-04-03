package com.qhit.lh.gr3.cyh.exam.paper.service;

import java.util.List;
import java.util.Set;

import com.qhit.lh.gr3.cyh.exam.classinfo.bean.TClassInfo;
import com.qhit.lh.gr3.cyh.exam.common.bean.PageBean;
import com.qhit.lh.gr3.cyh.exam.paper.bean.TPaper;
import com.qhit.lh.gr3.cyh.exam.question.bean.TCourse;
import com.qhit.lh.gr3.cyh.exam.question.bean.TWrittenTest;

public interface BaseService {
	public Object getObject(Class clazz, Integer id);

	// 保存
	public void add(Object object);

	public void delete(Object object);

	public void update(Object object);

	public List<Object> getAll(String hql);

	// 根据条件获得所有科目
	public List<TCourse> getCourses(String hql);

	// 根据科目csId和题目类型和难易程度查询试题集合
	public List<TWrittenTest> geTWrittenTestsByInfo(int csId, String qtype, String degree);

	// 添加
	public int save(TPaper paper);

	// 根据那班级ccode查询出班级
	public Object getObjectByInfo(String hql);

	public List<Object[]> getObjectsData(String hql);

}
