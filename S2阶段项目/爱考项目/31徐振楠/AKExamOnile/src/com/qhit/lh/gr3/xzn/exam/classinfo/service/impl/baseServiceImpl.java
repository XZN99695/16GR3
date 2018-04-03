package com.qhit.lh.gr3.cyh.exam.classinfo.service.impl;

import java.util.List;

import com.qhit.lh.gr3.cyh.exam.classinfo.bean.TClassInfo;
import com.qhit.lh.gr3.cyh.exam.classinfo.dao.baseDao;
import com.qhit.lh.gr3.cyh.exam.classinfo.dao.impl.baseDaoImpl;
import com.qhit.lh.gr3.cyh.exam.classinfo.service.baseService;

public class baseServiceImpl implements baseService {
	private baseDao bd = new baseDaoImpl();

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
	public List<TClassInfo> getClassByInfo(String hql) {
		// TODO Auto-generated method stub
		return bd.getClassByInfo(hql);
	}

}
