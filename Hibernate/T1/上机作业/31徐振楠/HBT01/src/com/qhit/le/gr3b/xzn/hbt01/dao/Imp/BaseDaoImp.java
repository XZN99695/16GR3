package com.qhit.le.gr3b.xzn.hbt01.dao.Imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.qhit.le.gr3b.xzn.hbt01.bean.User;
import com.qhit.le.gr3b.xzn.hbt01.dao.BaseDao;
import com.qhit.le.gr3b.xzn.hbt01.utils.HibernateSessionFactory;

/**
 * @author �����
 *TODO
 *2017��12��11��t����9:23:37
 */
public class BaseDaoImp implements BaseDao {

	@Override
	public void add(Object obj) {
				//1����ȡsession����
				Session session = HibernateSessionFactory.getSession();
				//2����������
				Transaction ts =session.beginTransaction();
				//3��ִ��
				session.save(obj);
				//4���ύ����
				ts.commit();
				//5���ͷ���Դ
				HibernateSessionFactory.closeSession();
	}

	@Override
	public void del(Object obj) {
		 // 1,��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		//��������
		Transaction ts = session.beginTransaction();
		//3,ִ��
		session.delete(obj);
		//4,�ύ����
		ts.commit();
		//5,�ͷ�
		HibernateSessionFactory.closeSession();
		
	}

	@Override
	public void upd(Object obj) {
		 // 1,��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		//��������
		Transaction ts = session.beginTransaction();
		//3,ִ��
		session.update(obj);
		//4,�ύ����
		ts.commit();
		//5,�ͷ�
		HibernateSessionFactory.closeSession();
		
	}

	@Override
	public List<User> all(String fromObject) {
		 // 1,��ȡsession����
		Session session = HibernateSessionFactory.getSession();
		//��������
		Transaction ts = session.beginTransaction();
		//3,��ѯ��
		 Query qu = session.createQuery(fromObject);
		  List<User> list = qu.list();
		//4,�ύ����
		ts.commit();
		//5,�ͷ�
		HibernateSessionFactory.closeSession();
		return list;
	}

}
