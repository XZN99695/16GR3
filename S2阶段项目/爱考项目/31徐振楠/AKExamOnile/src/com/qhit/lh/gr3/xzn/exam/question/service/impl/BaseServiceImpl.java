package com.qhit.lh.gr3.cyh.exam.question.service.impl;

import java.util.List;

import com.qhit.lh.gr3.cyh.exam.common.bean.PageBean;
import com.qhit.lh.gr3.cyh.exam.paper.bean.PaperWritten;
import com.qhit.lh.gr3.cyh.exam.question.bean.TCourse;
import com.qhit.lh.gr3.cyh.exam.question.bean.TMachineTest;
import com.qhit.lh.gr3.cyh.exam.question.bean.TWrittenTest;
import com.qhit.lh.gr3.cyh.exam.question.dao.BaseDao;
import com.qhit.lh.gr3.cyh.exam.question.dao.impl.BaseDaoImpl;
import com.qhit.lh.gr3.cyh.exam.question.service.BaseService;

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
	public List<TCourse> getCourses(String hql) {
		// TODO Auto-generated method stub
		return bd.getCourses(hql);
	}

	@Override
	public void Mubanfile(List<TWrittenTest> writs) {
		// TODO Auto-generated method stub
		bd.Mubanfile(writs);
	}

	@Override
	public List<Object> getTWBycsId(String hql) {
		// TODO Auto-generated method stub
		return bd.getTWBycsId(hql);
	}

	@Override
	public PaperWritten getPWritten(int pid, int qid) {
		// TODO Auto-generated method stub
		return bd.getPWritten(pid, qid);
	}

	@Override
	public List<Object> getObjectByNum(String hql, int num) {
		// TODO Auto-generated method stub
		return bd.getObjectByNum(hql, num);
	}

	@Override
	public List<Object[]> getObjectsData(String hql) {
		// TODO Auto-generated method stub
		return bd.getObjectsData(hql);
	}
}
