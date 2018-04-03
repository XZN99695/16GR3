package com.qhit.lh.gr3.cyh.exam.common.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.qhit.lh.gr3.cyh.exam.common.bean.TUser;
import com.qhit.lh.gr3.cyh.exam.common.dao.BaseDao;
import com.qhit.lh.gr3.cyh.exam.common.utils.HibernateSessionFactory;


public class BaseDaoImpl implements BaseDao {

	@Override
	public Object getObject(Class clazz, Integer id) {
		// TODO Auto-generated method stub
		Object object = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction ts = session.beginTransaction();
		object = session.get(clazz, id);
		ts.commit();
		session.close();
		return object;
	}

	@Override
	public void add(Object object) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		Transaction ts = session.beginTransaction();
		session.save(object);
		ts.commit();

		session.close();
	}

	@Override
	public void delete(Object object) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		Transaction ts = session.beginTransaction();
		session.delete(object);
		ts.commit();
		session.close();
	}

	@Override
	public void update(Object object) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		Transaction ts = session.beginTransaction();
		session.update(object);
		ts.commit();
		session.close();
	}

	@Override
	public List<Object> getAll(String hql) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		Transaction ts = session.beginTransaction();

		List<Object> list = new ArrayList<Object>();
		list = session.createQuery(hql).list();
		ts.commit();
		session.close();
		return list;
	}

	@Override
	public List<TUser> getObjectByInfo(String hql) {
		// TODO Auto-generated method stub
		//获得session
		Session session = HibernateSessionFactory.getSession();
		//开启事物
		Transaction ts = session.beginTransaction();
		//创建TUser类型List
		List<TUser> list = new ArrayList<TUser>();
		//通过session创建Query查询实例
		Query query=session.createQuery(hql);
		ts.commit();
		session.close();
		//通过hql语句查询数据库数据
		list=(List<TUser>)query.list();
		//如果数据不为空,并且只有一条,返回List,否则返回null
		if(list!=null&&list.size()==1) {
			return list;
		}else {
			return null;
		}
	}

	@Override
	public Object getObject(String hql) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getSession();
		Transaction transaction=session.beginTransaction();
		Query query=session.createQuery(hql);
		Object object=query.uniqueResult();
		transaction.commit();
		session.close();
		return object;
	}

}
