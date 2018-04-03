package com.qhit.lh.gr3.cyh.exam.examScores.service.impl;

import java.util.List;

import com.qhit.lh.gr3.cyh.exam.classinfo.bean.TClassInfo;
import com.qhit.lh.gr3.cyh.exam.examScores.bean.TUserScores;
import com.qhit.lh.gr3.cyh.exam.examScores.dao.BaseDao;
import com.qhit.lh.gr3.cyh.exam.examScores.dao.impl.BaseDaoImpl;
import com.qhit.lh.gr3.cyh.exam.examScores.service.BaseService;
import com.qhit.lh.gr3.cyh.exam.paper.bean.PaperClass;
import com.qhit.lh.gr3.cyh.exam.paper.bean.TPaper;
import com.qhit.lh.gr3.cyh.exam.question.bean.TCourse;

public class BaseServiceImpl implements BaseService {
	private BaseDao bd = new BaseDaoImpl();

	@Override
	public Object getObject(Class clazz, Integer id) {
		// TODO Auto-generated method stub
		return bd.getObject(clazz, id);
	}

	@Override
	public void add(Object object) {
		// TODO Auto-generated method stub
		bd.add(object);
	}

	@Override
	public void delete(Object object) {
		// TODO Auto-generated method stub
		bd.delete(object);
	}

	@Override
	public void update(Object object) {
		// TODO Auto-generated method stub
		bd.update(object);
	}

	@Override
	public List<Object> getAll(String hql) {
		// TODO Auto-generated method stub
		return bd.getAll(hql);
	}

	@Override
	public Object getObjectByInfo(String hql) {
		// TODO Auto-generated method stub
		return bd.getObjectByInfo(hql);
	}

	@Override
	public List<Object[]> getObjectData(String hql) {
		// TODO Auto-generated method stub
		return bd.getObjectData(hql);
	}

	@Override
	public List<TUserScores> getList(String hql) {
		// TODO Auto-generated method stub
		return bd.getList(hql);
	}

	@Override
	public List<TClassInfo> getClazzs(String hql) {
		// TODO Auto-generated method stub
		return bd.getClazzs(hql);
	}

	@Override
	public List<TCourse> getCourses(String hql) {
		// TODO Auto-generated method stub
		return bd.getCourses(hql);
	}

	@Override
	public List<TPaper> getPapers(String hql) {
		// TODO Auto-generated method stub
		return bd.getPapers(hql);
	}

	@Override
	public PaperClass getPcs(String hql) {
		// TODO Auto-generated method stub
		return bd.getPcs(hql);
	}
}
