package com.qhit.le.gr3b.xzn.hbt01.bean;

public class User {
  private int id;
  private String name;
  private String pwd;
  private String sex;
  private int age;
  private String edu;
public User() {
	super();
	// TODO Auto-generated constructor stub
}
public User(int id, String name, String pwd, String sex, int age, String edu) {
	super();
	this.id = id;
	this.name = name;
	this.pwd = pwd;
	this.sex = sex;
	this.age = age;
	this.edu = edu;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPwd() {
	return pwd;
}
public void setPwd(String pwd) {
	this.pwd = pwd;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public String getEdu() {
	return edu;
}
public void setEdu(String edu) {
	this.edu = edu;
}
@Override
public String toString() {
	return "User [id=" + id + ", name=" + name + ", pwd=" + pwd + ", sex="
			+ sex + ", age=" + age + ", edu=" + edu + "]";
}
  
}
