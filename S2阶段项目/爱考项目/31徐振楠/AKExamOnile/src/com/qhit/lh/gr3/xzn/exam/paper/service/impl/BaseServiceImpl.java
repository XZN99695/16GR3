package com.qhit.lh.gr3.cyh.exam.paper.service.impl;

import java.util.List;

import com.qhit.lh.gr3.cyh.exam.classinfo.bean.TClassInfo;
import com.qhit.lh.gr3.cyh.exam.paper.bean.TPaper;
import com.qhit.lh.gr3.cyh.exam.paper.dao.BaseDao;
import com.qhit.lh.gr3.cyh.exam.paper.dao.impl.BaseDaoImpl;
import com.qhit.lh.gr3.cyh.exam.paper.service.BaseService;
import com.qhit.lh.gr3.cyh.exam.question.bean.TCourse;
import com.qhit.lh.gr3.cyh.exam.question.bean.TWrittenTest;

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
	public List<TWrittenTest> geTWrittenTestsByInfo(int csId, String qtype, String degree) {
		// TODO Auto-generated method stub
		return bd.geTWrittenTestsByInfo(csId, qtype, degree);
	}

	// 外部传入每一项的最大值
	// @Override
	public List<Integer> getRandomNum(int max, int maxdan1, int maxdan2, int maxdan3, int maxduo1, int maxduo2,
			int maxduo3) {
		// TODO Auto-generated method stub
		// 用每一项属性数据集合的最大值作为随机数的最大值,1位最小值,max查询数量

		// Set<Integer> dan1data=bd.getRandomNum(max,maxdan1);
		return null;
	}

	@Override
	public int save(TPaper paper) {
		// TODO Auto-generated method stub
		return bd.save(paper);
	}

	@Override
	public Object getObjectByInfo(String hql) {
		// TODO Auto-generated method stub
		return bd.getObjectByInfo(hql);
	}

	@Override
	public List<Object[]> getObjectsData(String hql) {
		// TODO Auto-generated method stub
		return bd.getObjectsData(hql);
	}
}
