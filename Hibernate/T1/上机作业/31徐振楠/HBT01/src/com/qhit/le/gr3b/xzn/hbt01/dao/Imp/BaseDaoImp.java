package com.qhit.le.gr3b.xzn.hbt01.dao.Imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.qhit.le.gr3b.xzn.hbt01.bean.User;
import com.qhit.le.gr3b.xzn.hbt01.dao.BaseDao;
import com.qhit.le.gr3b.xzn.hbt01.utils.HibernateSessionFactory;

/**
 * @author 徐振楠
 *TODO
 *2017年12月11日t下午9:23:37
 */
public class BaseDaoImp implements BaseDao {

	@Override
	public void add(Object obj) {
				//1，获取session对象
				Session session = HibernateSessionFactory.getSession();
				//2，开启事务
				Transaction ts =session.beginTransaction();
				//3，执行
				session.save(obj);
				//4，提交事务
				ts.commit();
				//5，释放资源
				HibernateSessionFactory.closeSession();
	}

	@Override
	public void del(Object obj) {
		 // 1,获取session对象
		Session session = HibernateSessionFactory.getSession();
		//开启事物
		Transaction ts = session.beginTransaction();
		//3,执行
		session.delete(obj);
		//4,提交事务
		ts.commit();
		//5,释放
		HibernateSessionFactory.closeSession();
		
	}

	@Override
	public void upd(Object obj) {
		 // 1,获取session对象
		Session session = HibernateSessionFactory.getSession();
		//开启事物
		Transaction ts = session.beginTransaction();
		//3,执行
		session.update(obj);
		//4,提交事务
		ts.commit();
		//5,释放
		HibernateSessionFactory.closeSession();
		
	}

	@Override
	public List<User> all(String fromObject) {
		 // 1,获取session对象
		Session session = HibernateSessionFactory.getSession();
		//开启事物
		Transaction ts = session.beginTransaction();
		//3,查询器
		 Query qu = session.createQuery(fromObject);
		  List<User> list = qu.list();
		//4,提交事务
		ts.commit();
		//5,释放
		HibernateSessionFactory.closeSession();
		return list;
	}

}
