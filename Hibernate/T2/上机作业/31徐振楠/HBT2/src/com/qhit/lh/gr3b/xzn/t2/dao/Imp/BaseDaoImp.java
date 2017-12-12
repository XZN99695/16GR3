package com.qhit.lh.gr3b.xzn.t2.dao.Imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.qhit.lh.gr3b.xzn.t2.dao.BaseDao;
import com.qhit.lh.gr3b.xzn.t2.utils.HibernateSessionFactory;

public class BaseDaoImp implements BaseDao {

	@Override
	public void add(Object obj) {
		// 1,鑾峰彇Session瀵硅薄
		Session session = HibernateSessionFactory.getSession();
		//寮�惎浜嬬墿
		Transaction ts = session.beginTransaction();
		//3,鎵ц
		session.save(obj);
		//4,鎻愪氦浜嬬墿
		ts.commit();
		//5,閲婃斁
		HibernateSessionFactory.closeSession();
	}

	@Override
	public void del(Object obj) {
		//1,鑾峰彇session瀵硅薄
		Session session = HibernateSessionFactory.getSession();
		//2,寮�惎浜嬬墿
		Transaction ts = session.beginTransaction();
		//3,鎵ц
		session.delete(obj);
		//4,鎻愪氦浜嬬墿
		ts.commit();
		//5,閲婃斁
		HibernateSessionFactory.closeSession();
	}

	@Override
	public void upd(Object obj) {
		//1,鑾峰彇session瀵硅薄
		Session session = HibernateSessionFactory.getSession();
		//2,寮�惎浜嬬墿
		Transaction ts = session.beginTransaction();
		//3,鎵ц
	    session.update(obj);
	    //4,鎻愪氦浜嬬墿
	    ts.commit();
	    //閲婃斁
		HibernateSessionFactory.closeSession();
	}

	@Override
	public List<Object> all(String fromObject) {
		//1,鑾峰彇session瀵硅薄
		Session session = HibernateSessionFactory.getSession();
		//2,寮�惎浜嬬墿
		Transaction ts = session.beginTransaction();
		//3,鑾峰彇鏌ヨ
	    Query qu = session.createQuery(fromObject);
	    List<Object> list = qu.list();
	    //4,鎻愪氦浜嬬墿
	    ts.commit();
	    //閲婃斁
		HibernateSessionFactory.closeSession();
		
		return list;
		// 
		
	}
	/*
	 * 根据ID查询
	 */
	public Object getObjectById(Object obj, int id) {
		//1,获取session对象
		Session session = HibernateSessionFactory.getSession();
		//2，开启事务
		Transaction ts = session.beginTransaction();
		//3，获取查询器对象
		obj = session.get(obj.getClass(), id);
		//4,提交事务
		ts.commit();
		//5，释放资源
		HibernateSessionFactory.closeSession();
		return obj;
	}
}
