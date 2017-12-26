package com.qhit.lh.gr3b.xzn.t7;

import static org.junit.Assert.*;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.qhit.lh.gr3b.xzn.t7.bean.Dept;
import com.qhit.lh.gr3b.xzn.t7.bean.Emp;
import com.qhit.lh.gr3b.xzn.t7.bean.User;
import com.qhit.lh.gr3b.xzn.t7.servcie.BaseService;
import com.qhit.lh.gr3b.xzn.t7.servcie.impl.BaseServiceImpl;
import com.qhit.lh.gr3b.xzn.t7.utils.HibernateSessionFactory;

public class Test {
  private BaseService bs = new BaseServiceImpl();
	@org.junit.Test
	public void addEmp() {
		//添加员工
		Emp emp = new Emp();
		emp.setEname("麻子");
		emp.setSex("男");
		//账户
		User user = new User();
		user.setUname("Mz");
		user.setUpwd("123456");
		//所属部门
       Dept dept = (Dept) bs.getObjectById(Dept.class, 4);
	   //1对1
       emp.setUser(user);
       user.setEmp(emp);
       //1对多
       emp.setDept(dept);
       bs.add(emp);
	}
	
	@org.junit.Test
	public void addDept(){
		Dept dept = new Dept();
		dept.setDname("宣传部");
        dept.setAddress("203");
		bs.add(dept);
	}
	@org.junit.Test
	//模糊查询
	public void getAll(){
	    String hql = "select  e from Emp e where e.ename like ? ";
	    //1,获取Session对象
	    Session session = HibernateSessionFactory.getSession();
	    //2,查询器
	    Query query = session.createQuery(hql);
	    query.setString(0, "张%");
	    
	    List<Emp> list = query.list();
	    for (Emp emp : list) {
	    	System.out.println(emp.getDept().getDname()+":"+emp.getEname()+"\t"+emp.getSex()+"\t"+"员工编号:"+emp.getEid());
			
		}
	}
}
