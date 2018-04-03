package com.qhit.lh.gr3.cyh.exam.common.dao;

import java.util.List;
import java.util.Set;

import com.qhit.lh.gr3.cyh.exam.common.bean.TUser;

public interface BaseDao {
	public Object getObject(Class clazz, Integer id);

	public void add(Object object);

	public void delete(Object object);

	public void update(Object object);

	public List<Object> getAll(String hql);

	public Object getObject(String hql);

	// 用户登录判断
	public List<TUser> getObjectByInfo(String hql);
}
