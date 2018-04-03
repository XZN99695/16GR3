package com.qhit.lh.gr3.cyh.exam.startExam.service;

import java.util.List;

import com.qhit.lh.gr3.cyh.exam.paper.bean.TPaper;
import com.qhit.lh.gr3.cyh.exam.question.bean.TWrittenTest;

public interface BaseService {
	public Object getObject(Class clazz, Integer id);

	public void add(Object object);

	public void delete(Object object);

	public void update(Object object);

	public List<Object> getAll(String hql);

	public List<TWrittenTest> getList(String hql);

	public Object getObjectByInfo(String hql);

	public List<Object[]> getObjectData(String hql);
}
