package com.qhit.lh.gr3.cyh.exam.classinfo.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.qhit.lh.gr3.cyh.exam.classinfo.bean.TClassInfo;
import com.qhit.lh.gr3.cyh.exam.classinfo.dao.baseDao;
import com.qhit.lh.gr3.cyh.exam.common.utils.HibernateSessionFactory;

public class baseDaoImpl implements baseDao {

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

	public List<TClassInfo> getClassByInfo(String hql) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery(hql);
		List<TClassInfo> lists = query.list();
		transaction.commit();
		session.close();
		return lists;
	}

}
