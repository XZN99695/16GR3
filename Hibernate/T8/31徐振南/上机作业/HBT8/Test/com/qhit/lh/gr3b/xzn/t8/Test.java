package com.qhit.lh.gr3b.xzn.t8;

import static org.junit.Assert.*;


import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.qhit.lh.gr3b.xzn.t8.bean.Dept;
import com.qhit.lh.gr3b.xzn.t8.bean.Emp;
import com.qhit.lh.gr3b.xzn.t8.bean.User;
import com.qhit.lh.gr3b.xzn.t8.servcie.BaseService;
import com.qhit.lh.gr3b.xzn.t8.servcie.impl.BaseServiceImpl;
import com.qhit.lh.gr3b.xzn.t8.utils.HibernateSessionFactory;

public class Test {
  private BaseService bs = new BaseServiceImpl();
	@org.junit.Test
	public void add() {
		//娣诲姞鍛樺伐
		Emp emp = new Emp();
		emp.setEname("寮犱笁");
		emp.setSex("鐢�");
		//璐︽埛
		User user = new User();
		user.setUname("xzn");
		user.setUpwd("123456");
		//鎵�睘閮ㄩ棬
       Dept dept = (Dept) bs.getObjectById(Dept.class, 1);
	   //1瀵�
       emp.setUser(user);
       user.setEmp(emp);
       //1瀵瑰
       emp.setDept(dept);
       bs.add(emp);
	}
	
	@org.junit.Test
	public void addDept(){
		Dept dept = new Dept();
		dept.setDname("澶栦氦閮�");
        dept.setAddress("206");
		Emp emp = new Emp();
		emp.setEname("寮犱笁");
		//浜ょ粰鑷繁缁存姢澶栨
		//dept.getEmps().add(emp);
		//浜ょ粰鍏宠仈琛ㄧ淮鎶ゅ妫�
		emp.setDept(dept);
		bs.add(dept);
	}
	@org.junit.Test
	//妯＄硦鏌ヨ
	public void getByName(){
		//鑾峰彇Session瀵硅薄
		Session session = HibernateSessionFactory.getSession();
		//閫氳繃session瀵硅薄鍒涘缓criteria鏉′欢鏌ヨ鍣�
		 Criteria criteria = session.createCriteria(Emp.class)
				 .add(Restrictions.like("ename", "寰�"));
		 //閫氳繃criteria鏉′欢鏌ヨ鍣ㄦ煡璇�
		 List<Emp> list = criteria.list();
		 //杈撳嚭
		 for (Emp emp : list) {
             System.out.println(emp.getEid()+":"+emp.getEname());			
		} 
	}
	@org.junit.Test
	//杩炴帴鏌ヨ
	public void getQu(){
		//鑾峰彇Session瀵硅薄
	Session session = HibernateSessionFactory.getSession();
	//閫氳繃session瀵硅薄鍒涘缓criteria鏉′欢鏌ヨ鍣�
	 Criteria criteria = session.createCriteria(Emp.class)
			 .setFetchMode("dept", FetchMode.JOIN)
			 .createAlias("dept", "d")
			 .add(Restrictions.eq("d.dname", "鏀挎不"));
	 //閫氳繃criteria鏉′欢鏌ヨ鍣ㄦ煡璇�
	 List<Emp> list = criteria.list(); 
	 //杈撳嚭
	 for (Emp emp : list) {
		System.out.println(emp.getDept().getDname()+":"+emp.getEname()+""+emp.getEid());
	}
	}
	public void getEmp(){
		//鑾峰彇Session瀵硅薄
		Session session = HibernateSessionFactory.getSession();
		Criteria criteria = session.createCriteria(Emp.class)
				.add(Restrictions.like("ename", "寮�"));
		//鑾峰彇灞炴�鍒楄〃
		Projection projection = Projections.projectionList()
				.add(Projections.property("ename"))
				.add(Projections.property("sex"))
				.add(Projections.property("did"));
		
		List<Object[]> list = criteria.list();
		
		criteria.setProjection(projection);
		
		 
	for (Object[] oj : list) {
		System.out.println(""+oj[0]+oj[1]+oj[2]);
	}
	}
}
