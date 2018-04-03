package com.qhit.lh.gr3.cyh.exam.classinfo.dao;

import java.util.List;

import com.qhit.lh.gr3.cyh.exam.classinfo.bean.TClassInfo;

public interface baseDao {
	public Object getObject(Class clazz,Integer id);
	public void add(Object object);
	public void delete(Object object);
	public void update(Object object);
//根据方向获取班级
	public List<TClassInfo> getClassByInfo(String hql);

}
