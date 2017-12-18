package com.qhit.lh.gr3b.xzn.t4.bean;

/**
 * Emp entity. @author MyEclipse Persistence Tools
 */

public class Emp implements java.io.Serializable {

	// Fields

	private Integer eid;
	private String ename;
	private String sex;
	private Integer did;
    
	//1对1
	private User user;
	//多对1
	private Dept dept;
	
	// Constructors

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	/** default constructor */
	public Emp() {
	}

	/** minimal constructor */
	public Emp(String ename, String sex) {
		this.ename = ename;
		this.sex = sex;
	}

	/** full constructor */
	public Emp(String ename, String sex, Integer did) {
		this.ename = ename;
		this.sex = sex;
		this.did = did;
	}

	// Property accessors

	public Integer getEid() {
		return this.eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getEname() {
		return this.ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getDid() {
		return this.did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

}