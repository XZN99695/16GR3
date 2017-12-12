package com.qhit.lh.gr3b.xzn.t2.bean;

public class Emp {
  private int eid;
  private String ename;
  private int age;
  private String sex;
  //一对一映射
  private User user;
public Emp() {
	super();
	// TODO Auto-generated constructor stub
}
public Emp(int eid, String ename, int age, String sex, User user) {
	super();
	this.eid = eid;
	this.ename = ename;
	this.age = age;
	this.sex = sex;
	this.user = user;
}
public Emp(int eid, String ename, int age, String sex) {
	super();
	this.eid = eid;
	this.ename = ename;
	this.age = age;
	this.sex = sex;
}
public int getEid() {
	return eid;
}
public void setEid(int eid) {
	this.eid = eid;
}
public String getEname() {
	return ename;
}
public void setEname(String ename) {
	this.ename = ename;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
  
}
