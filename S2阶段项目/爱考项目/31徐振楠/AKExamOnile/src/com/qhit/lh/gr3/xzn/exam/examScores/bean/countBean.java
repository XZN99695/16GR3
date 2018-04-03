package com.qhit.lh.gr3.cyh.exam.examScores.bean;

public class countBean {
private int paper_num;//总人数
private int pass_num;//及格人数
private String pass_rate;//及格率

public countBean() {
	super();
}

public countBean(int paper_num, int pass_num, String pass_rate) {
	super();
	this.paper_num = paper_num;
	this.pass_num = pass_num;
	this.pass_rate = pass_rate;
}

public int getPaper_num() {
	return paper_num;
}

public int getPass_num() {
	return pass_num;
}

public String getPass_rate() {
	return pass_rate;
}

public void setPaper_num(int paper_num) {
	this.paper_num = paper_num;
}

public void setPass_num(int pass_num) {
	this.pass_num = pass_num;
}

public void setPass_rate(String pass_rate) {
	this.pass_rate = pass_rate;
}

@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("countBean [paper_num=");
	builder.append(paper_num);
	builder.append(", pass_num=");
	builder.append(pass_num);
	builder.append(", pass_rate=");
	builder.append(pass_rate);
	builder.append("]");
	return builder.toString();
}

}
