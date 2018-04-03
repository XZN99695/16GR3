package com.qhit.lh.gr3.cyh.exam.paper.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.qhit.lh.gr3.cyh.exam.classinfo.bean.TClassInfo;
import com.qhit.lh.gr3.cyh.exam.common.utils.HibernateSessionFactory;
import com.qhit.lh.gr3.cyh.exam.paper.bean.TPaper;
import com.qhit.lh.gr3.cyh.exam.paper.dao.BaseDao;
import com.qhit.lh.gr3.cyh.exam.question.bean.TCourse;
import com.qhit.lh.gr3.cyh.exam.question.bean.TWrittenTest;

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
	public List<TCourse> getCourses(String hql) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();

		Query query = session.createQuery(hql);
		List<TCourse> lists = (List<TCourse>) query.list();
		transaction.commit();
		session.close();
		return lists;
	}

	// 根据科目csId和题目类型和难易程度查询试题集合
	@Override
	public List<TWrittenTest> geTWrittenTestsByInfo(int csId, String qtype, String degree) {
		// TODO Auto-generated method stub
		String hql = "select w from TWrittenTest w where w.csId = " + csId + " and w.qtype = '" + qtype
				+ "' and w.degree = '" + degree + "'";
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();

		Query query = session.createQuery(hql);
		List<TWrittenTest> list = new ArrayList<TWrittenTest>();
		list = query.list();
		transaction.commit();
		session.close();
		return list;
	}

	@Override
	public int save(TPaper paper) {
		// TODO Auto-generated method stub
		String hql = "insert into TPaper (pName, pTime, pTotalScore, csId, qTotal, qScore, pType, pState) values (?, ?, ?, ?, ?, ?, ?, ?)";
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery(hql);
		query.setString(0, paper.getPname());
		query.setInteger(1, paper.getPtime());
		query.setInteger(2, paper.getPtotalScore());
		query.setInteger(3, paper.getCsId());
		query.setInteger(4, paper.getQtotal());
		query.setFloat(5, paper.getQscore());
		query.setString(6, paper.getPtype());
		query.setString(7, paper.getPstate());
		int i = query.executeUpdate();
		return i;
	}

	@Override
	public Object getObjectByInfo(String hql) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery(hql);
		Object obj = query.uniqueResult();
		transaction.commit();
		session.close();
		return obj;
	}
	@Override
	public List<Object[]> getObjectsData(String hql) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getSession();
		Transaction transaction=session.beginTransaction();
		Query query=session.createQuery(hql);
		List<Object[]> lists=query.list();
		transaction.commit();
		session.close();
		return lists;
	}
}
