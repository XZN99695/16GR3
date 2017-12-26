/**
 * 
 */
package com.qhit.lh.gr3b.xzn.t8.dao.impl;

import java.util.List;



import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.qhit.lh.gr3b.xzn.t8.dao.BaseDao;
import com.qhit.lh.gr3b.xzn.t8.utils.HibernateSessionFactory;



/**
 * @author admin
 * 2017骞�2鏈�1鏃�
 */
public class BaseDaoImpl implements BaseDao {

	@Override
	public void add(Object obj) {
		//1,鑾峰彇session瀵硅薄
		Session session = HibernateSessionFactory.getSession();
		//2锛屽紑鍚簨鍔�
		Transaction ts = session.beginTransaction();
		//3锛屾墽琛�
		session.save(obj);
		//4,鎻愪氦浜嬪姟
		ts.commit();
		//5锛岄噴鏀捐祫婧�
		HibernateSessionFactory.closeSession();
	}

	@Override
	public void delete(Object obj) {
		//1,鑾峰彇session瀵硅薄
		Session session = HibernateSessionFactory.getSession();
		//2锛屽紑鍚簨鍔�
		Transaction ts = session.beginTransaction();
		//3锛屾墽琛�
		session.delete(obj);
		//4,鎻愪氦浜嬪姟
		ts.commit();
		//5锛岄噴鏀捐祫婧�
		HibernateSessionFactory.closeSession();

	}

	@Override
	public void update(Object obj) {
		//1,鑾峰彇session瀵硅薄
		Session session = HibernateSessionFactory.getSession();
		//2锛屽紑鍚簨鍔�
		Transaction ts = session.beginTransaction();
		//3锛屾墽琛�
		session.update(obj);
		//4,鎻愪氦浜嬪姟
		ts.commit();
		//5锛岄噴鏀捐祫婧�
		HibernateSessionFactory.closeSession();

	}

	@Override
	public List<Object> getAll(String fromObject) {
		//1,鑾峰彇session瀵硅薄
		Session session = HibernateSessionFactory.getSession();
		//2锛屽紑鍚簨鍔�
		Transaction ts = session.beginTransaction();
		//3锛岃幏鍙栨煡璇㈠櫒瀵硅薄
		Query query = session.createQuery(fromObject);
		List<Object> list = query.list();
		//4,鎻愪氦浜嬪姟
		ts.commit();
		//5锛岄噴鏀捐祫婧�
		HibernateSessionFactory.closeSession();
		return list;
	}

	@Override
	public Object getObjectById(Class clazz, int id) {
		//1,鑾峰彇session瀵硅薄
		Session session = HibernateSessionFactory.getSession();
		//2锛屽紑鍚簨鍔�
		Transaction ts = session.beginTransaction();
		//3锛岃幏鍙栨煡璇㈠櫒瀵硅薄
		Object obj = session.get(clazz, id);
		//4,鎻愪氦浜嬪姟
		ts.commit();
		//5锛岄噴鏀捐祫婧�
		HibernateSessionFactory.closeSession();
		return obj;
	}

}
