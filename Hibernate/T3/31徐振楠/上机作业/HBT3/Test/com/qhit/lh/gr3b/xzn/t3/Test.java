package com.qhit.lh.gr3b.xzn.t3;

import static org.junit.Assert.*;

import com.qhit.lh.gr3b.xzn.t3.bean.Dept;
import com.qhit.lh.gr3b.xzn.t3.bean.Emp;
import com.qhit.lh.gr3b.xzn.t3.servcie.BaseService;
import com.qhit.lh.gr3b.xzn.t3.servcie.impl.BaseServiceImpl;

public class Test {
  private BaseService bs = new BaseServiceImpl();
	@org.junit.Test
	public void add() {
		//添加用户
		Emp emp = new Emp();
		emp.setEname("徐振楠");
		emp.setEdu("大专");
		emp.setDid(1);
		//分配部门
		Dept dept= new Dept();
		dept = (Dept) bs.getObjectById(dept.getClass(), 1);
		//级联操作
		emp.setDept(dept);
		bs.add(emp);
	}

}
