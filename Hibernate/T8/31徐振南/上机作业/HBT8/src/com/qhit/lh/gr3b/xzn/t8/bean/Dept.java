package com.qhit.lh.gr3b.xzn.t8.bean;

import java.util.Set;

/**
 * Dept entity. @author MyEclipse Persistence Tools
 */

public class Dept implements java.io.Serializable {

	// Fields

	private Integer did;
	private String dname;
	private String address;
    
	//多对1
	private Set<Emp> emps;
	
	// Constructors
     //1对多
	public Set<Emp> getEmps() {
		return emps;
	}

	public void setEmps(Set<Emp> emps) {
		this.emps = emps;
	}

	/** default constructor */
	public Dept() {
	}

	/** minimal constructor */
	public Dept(String dname) {
		this.dname = dname;
	}

	/** full constructor */
	public Dept(String dname, String address) {
		this.dname = dname;
		this.address = address;
	}

	// Property accessors

	public Integer getDid() {
		return this.did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

	public String getDname() {
		return this.dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}