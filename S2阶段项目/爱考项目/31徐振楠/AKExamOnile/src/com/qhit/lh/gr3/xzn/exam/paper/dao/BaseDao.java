package com.qhit.lh.gr3.cyh.exam.paper.dao;

import java.util.List;

import com.qhit.lh.gr3.cyh.exam.classinfo.bean.TClassInfo;
import com.qhit.lh.gr3.cyh.exam.paper.bean.TPaper;
import com.qhit.lh.gr3.cyh.exam.question.bean.TCourse;
import com.qhit.lh.gr3.cyh.exam.question.bean.TWrittenTest;

public interface BaseDao {
	public Object getObject(Class clazz, Integer id);

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

	public Object getObjectByInfo(String hql);
	public List<Object[]> getObjectsData(String hql);
}
