package com.qhit.le.gr3b.xzn.hbt01.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.qhit.le.gr3b.xzn.hbt01.bean.User;
import com.qhit.le.gr3b.xzn.hbt01.service.BaseService;
import com.qhit.le.gr3b.xzn.hbt01.service.Imp.BaseServiceImp;

public class UserAction extends ActionSupport {
	private BaseService bas = new BaseServiceImp();
	private List<User> users = new ArrayList<>();
	private User user = new User();
     public String addUser(){
    	 bas.add(user);
		return "addsuccess";
		
     }
     public String allUser(){
    	 users = bas.all("from User");
		return "allsuccess";
    	 
     }
     public String delUser(){
    	 bas.del(user);
		return "delsuccess";
     }
	public BaseService getBas() {
		return bas;
	}
	public void setBas(BaseService bas) {
		this.bas = bas;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
     
}
