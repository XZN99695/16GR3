package com.qhit.lh.gr3b.xzn.t2;

import static org.junit.Assert.*;


import java.util.List;


import com.qhit.lh.gr3b.xzn.t2.Service.BaseService;
import com.qhit.lh.gr3b.xzn.t2.Service.Impl.BaseServiceImp;
import com.qhit.lh.gr3b.xzn.t2.bean.Emp;
import com.qhit.lh.gr3b.xzn.t2.bean.User;

public class Test {
	private BaseService bs=new BaseServiceImp();
	@org.junit.Test
	public void add() {
		Emp emp = new Emp();
		emp.setEname("帅比");
		emp.setSex("男");
		emp.setAge(20);
		User user=new User();
	    user.setUname("admin");
		user.setUpwd("123456");	
		emp.setUser(user);
		user.setEmp(emp);
	    bs.add(emp);
	}
	//删除
	@org.junit.Test
	public void del() {
		Emp emp = new Emp();
		emp = (Emp) bs.getObjectById(emp, 4);
		bs.del(emp);
	}
	//改
	@org.junit.Test
	public void upd() {
		Emp emp = new Emp();
		emp.setEname("帅比sd");
		emp.setSex("男");
		emp.setAge(20);
		  User user=new User();
			user.setUname("admins");
			user.setUpwd("123456s");
			emp.setUser(user);
			user.setEmp(emp);
			//级联操作
			bs.upd(emp);
	}
	//查
	@org.junit.Test
	public void getAll() {
		List<Object> list = bs.all("from Emp");
		
		for (Object object : list) {
			Emp emp = (Emp) object;
			System.out.println(emp.toString());
		}
	}

}
