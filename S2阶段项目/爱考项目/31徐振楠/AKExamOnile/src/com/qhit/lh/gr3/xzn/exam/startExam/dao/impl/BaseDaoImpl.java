package com.qhit.lh.gr3.cyh.exam.startExam.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.qhit.lh.gr3.cyh.exam.common.utils.HibernateSessionFactory;
import com.qhit.lh.gr3.cyh.exam.paper.bean.TPaper;
import com.qhit.lh.gr3.cyh.exam.question.bean.TWrittenTest;
import com.qhit.lh.gr3.cyh.exam.startExam.dao.BaseDao;

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
	public Object getObjectByInfo(String hql) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getSession();
		Transaction transaction =session.beginTransaction();
		Query query=session.createQuery(hql);
		Object obj=query.uniqueResult();
		transaction.commit();
		session.close();
		System.out.println("查询完毕");
		return obj;
	}

	@Override
	public List<Object[]> getObjectData(String hql) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getSession();
		Transaction transaction=session.beginTransaction();
		Query query=session.createQuery(hql);
		List<Object[]> lists=query.list();
		transaction.commit();
		session.close();
		return lists;
	}

	@Override
	public List<TWrittenTest> getList(String hql) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		Transaction ts = session.beginTransaction();

		List<TWrittenTest> list = new ArrayList<TWrittenTest>();
		list = session.createQuery(hql).list();
		ts.commit();
		session.close();
		return list;
	}

}
