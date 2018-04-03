package com.qhit.lh.gr3.cyh.exam.question.service;

import java.util.List;

import com.qhit.lh.gr3.cyh.exam.paper.bean.PaperWritten;
import com.qhit.lh.gr3.cyh.exam.question.bean.TCourse;
import com.qhit.lh.gr3.cyh.exam.question.bean.TMachineTest;
import com.qhit.lh.gr3.cyh.exam.question.bean.TWrittenTest;

public interface BaseService {
	public Object getObject(Class clazz, Integer id);

	public void add(Object object);

	public void delete(Object object);

	public void update(Object object);

	public List<Object> getAll(String hql);

	// 根据条件查出科目
	public List<TCourse> getCourses(String hql);

	// 导入试题
	public void Mubanfile(List<TWrittenTest> writs);

	// 根据科目Id获得所有的笔试题
	public List<Object> getTWBycsId(String hql);

	// 先从数据库取出旧qid所属的中间表对象ID
	public PaperWritten getPWritten(int pid, int qid);
	public List<Object> getObjectByNum(String hql,int num);
	public List<Object[]> getObjectsData(String hql);
}
