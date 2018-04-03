package com.qhit.lh.gr3.cyh.exam.examScores.dao;

import java.util.List;

import com.qhit.lh.gr3.cyh.exam.classinfo.bean.TClassInfo;
import com.qhit.lh.gr3.cyh.exam.examScores.bean.TUserScores;
import com.qhit.lh.gr3.cyh.exam.paper.bean.PaperClass;
import com.qhit.lh.gr3.cyh.exam.paper.bean.TPaper;
import com.qhit.lh.gr3.cyh.exam.question.bean.TCourse;

public interface BaseDao {
	public Object getObject(Class clazz, Integer id);

	public void add(Object object);

	public void delete(Object object);

	public void update(Object object);

	public List<Object> getAll(String hql);

	public List<TUserScores> getList(String hql);

	public Object getObjectByInfo(String hql);

	public List<Object[]> getObjectData(String hql);
	
	public List<TClassInfo> getClazzs(String hql);
	
	public List<TCourse> getCourses(String hql);
	
	public List<TPaper> getPapers(String hql);
	public PaperClass getPcs(String hql);
}