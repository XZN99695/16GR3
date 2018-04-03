package com.qhit.lh.gr3.cyh.exam.question.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.qhit.lh.gr3.cyh.exam.common.utils.HibernateSessionFactory;
import com.qhit.lh.gr3.cyh.exam.paper.bean.PaperWritten;
import com.qhit.lh.gr3.cyh.exam.question.bean.TCourse;
import com.qhit.lh.gr3.cyh.exam.question.bean.TWrittenTest;
import com.qhit.lh.gr3.cyh.exam.question.dao.BaseDao;

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
		System.out.println("dao输出："+hql);
		List<TCourse> lists = new ArrayList<TCourse>();
		Query query = session.createQuery(hql);
		lists = (List<TCourse>) query.list();
		transaction.commit();
		session.close();
		return lists;
	}

	@Override
	public void Mubanfile(List<TWrittenTest> writs) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		Transaction tr = session.beginTransaction();
			for (TWrittenTest test: writs) {
				session.save(test);
			}
			tr.commit();
			session.close();
	}
	
	@Override
	public List<TWrittenTest> geWrittenById(int csId,Set<Integer> data) {
		// TODO Auto-generated method stub
		List<TWrittenTest> list=new ArrayList<TWrittenTest>();
		for(int i:data) {
			String hql="select w from TWrittenTest w where w.csId = "+csId+" and w.qtype = '单选' and w.degree = '简单' and w.qId = 100"+i;
//			TWrittenTest written=
		}
		return null;
	}

	@Override
	public List<Object> getTWBycsId(String hql) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getSession();
		Transaction transaction=session.beginTransaction();
		Query query=session.createQuery(hql);
		List<Object> list=new ArrayList<Object>();
		list=query.list();
		transaction.commit();
		session.close();
		return list;
	}

	@Override
	public PaperWritten getPWritten(int pid, int qid) {
		// TODO Auto-generated method stub
		String hql="select pw from PaperWritten pw where pw.pid="+pid+" and pw.qid="+qid;
		Session session=HibernateSessionFactory.getSession();
		Transaction transaction=session.beginTransaction();
		Query query=session.createQuery(hql);
		PaperWritten pw=new PaperWritten();
		pw=(PaperWritten) query.uniqueResult();
		transaction.commit();
		session.close();
		return pw;
	}

	@Override
	public List<Object> getObjectByNum(String hql,int num) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getSession();
		Transaction transaction=session.beginTransaction();
		Query query=session.createQuery(hql);
		List<Object> objs=new ArrayList<Object>();
		if (num>query.list().size()) {
			transaction.commit();
			session.close();
			return objs;
		}
		query.setFirstResult(num-1);//开始查询地方
		query.setMaxResults(1);//查询条数
		objs=query.list();
		transaction.commit();
		session.close();
		return objs;
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
