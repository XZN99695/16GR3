package com.qhit.lh.gr3b.xzn.t4;

import static org.junit.Assert.*;

import com.qhit.lh.gr3b.xzn.t4.bean.Dept;
import com.qhit.lh.gr3b.xzn.t4.bean.Emp;
import com.qhit.lh.gr3b.xzn.t4.bean.User;
import com.qhit.lh.gr3b.xzn.t4.servcie.BaseService;
import com.qhit.lh.gr3b.xzn.t4.servcie.impl.BaseServiceImpl;

public class Test {
  private BaseService bs = new BaseServiceImpl();
	@org.junit.Test
	public void add() {
		//添加员工
		Emp emp = new Emp();
		emp.setEname("徐振南");
		emp.setSex("男");
		//账户
		User user = new User();
		user.setUname("xzn");
		user.setUpwd("123456");
		//所属部门
       Dept dept = (Dept) bs.getObjectById(Dept.class, 1);
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
		dept.setDname("拆弹部");

		Emp emp = new Emp();
		emp.setEname("xxx");
		//交给自己维护外检
		dept.getEmps().add(emp);
		//交给关联表维护外检
		//emp.setDept(dept);
		bs.add(dept);
	}
	

}
