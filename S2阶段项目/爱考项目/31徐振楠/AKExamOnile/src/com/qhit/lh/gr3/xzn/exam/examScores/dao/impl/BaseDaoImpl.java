package com.qhit.lh.gr3.cyh.exam.examScores.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.qhit.lh.gr3.cyh.exam.classinfo.bean.TClassInfo;
import com.qhit.lh.gr3.cyh.exam.common.utils.HibernateSessionFactory;
import com.qhit.lh.gr3.cyh.exam.examScores.bean.TUserScores;
import com.qhit.lh.gr3.cyh.exam.examScores.dao.BaseDao;
import com.qhit.lh.gr3.cyh.exam.paper.bean.PaperClass;
import com.qhit.lh.gr3.cyh.exam.paper.bean.TPaper;
import com.qhit.lh.gr3.cyh.exam.question.bean.TCourse;

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
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery(hql);
		Object obj = query.uniqueResult();
		transaction.commit();
		session.close();
		System.out.println("查询完毕");
		return obj;
	}

	@Override
	public List<Object[]> getObjectData(String hql) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery(hql);
		List<Object[]> lists = query.list();
		transaction.commit();
		session.close();
		return lists;
	}

	@Override
	public List<TUserScores> getList(String hql) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		Transaction ts = session.beginTransaction();

		List<TUserScores> list = new ArrayList<TUserScores>();
		list = session.createQuery(hql).list();
		ts.commit();
		session.close();
		return list;
	}

	@Override
	public List<TClassInfo> getClazzs(String hql) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getSession();
		Transaction transaction=session.beginTransaction();
		Query query=session.createQuery(hql);
		List<TClassInfo> clazzs=new ArrayList<TClassInfo>();
		clazzs=query.list();
		transaction.commit();
		session.close();
		return clazzs;
	}

	@Override
	public List<TCourse> getCourses(String hql) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getSession();
		Transaction transaction=session.beginTransaction();
		Query query=session.createQuery(hql);
		List<TCourse> courses=new ArrayList<TCourse>();
		courses=query.list();
		transaction.commit();
		session.close();
		return courses;
	}

	@Override
	public List<TPaper> getPapers(String hql) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getSession();
		Transaction transaction=session.beginTransaction();
		Query query=session.createQuery(hql);
		List<TPaper> papers=new ArrayList<TPaper>();
		papers=query.list();
		transaction.commit();
		session.close();
		return papers;
	}

	@Override
	public PaperClass getPcs(String hql) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getSession();
		Transaction transaction=session.beginTransaction();
		Query query=session.createQuery(hql);
		PaperClass pcs=new PaperClass();
		pcs=(PaperClass) query.uniqueResult();
		transaction.commit();
		session.close();
		return pcs;
	}

}
