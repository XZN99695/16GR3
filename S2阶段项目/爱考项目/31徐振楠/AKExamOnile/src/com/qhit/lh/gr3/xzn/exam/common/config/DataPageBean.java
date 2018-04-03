package com.qhit.lh.gr3.cyh.exam.common.config;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.qhit.lh.gr3.cyh.exam.common.bean.PageBean;
import com.qhit.lh.gr3.cyh.exam.common.utils.HibernateSessionFactory;

public class DataPageBean {
	public static PageBean getPageBean(String hql, PageBean pb, int up) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		Transaction tr = session.beginTransaction();
		System.out.println(hql);
		Query query = session.createQuery(hql);
		int count = query.list().size();
		// 设置pagebean的总条数
		pb.setCount(count);
		// 当前页已经有值
		System.out.println("getPageBeanP:" + up);
		pb.setP(up);
//		 是指要查询的开始位置
		 query.setFirstResult((pb.getP() - 1) * pb.getPagesize());
		 // 设置每次查询条数
		 query.setMaxResults(pb.getPagesize());
		 
		List<Object> objs = new ArrayList<Object>();
		// 根据条件检索相应数据
//		query.setFirstResult((pb.getP() - 1) * pb.getPagesize());
//		query.setMaxResults(pb.getPagesize());
		objs = query.list();
		tr.commit();
		session.close();
		// 设置数据集合
		pb.setData(objs);
		return pb;
	}
}
