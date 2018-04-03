package com.qhit.lh.gr3.cyh.exam.common.bean;

import java.util.ArrayList;
import java.util.List;

public class PageBean {
	// 每页显示条数
	private int pagesize;
	// 总条数
	private int count;
	// 总页数
	private int pagetotal;
	// 当前页
	private int p;
	// 数据集合
	private List<Object> data;
	// 上一页
	private int upperpage;
	// 下一页
	private int nextpage;

	public PageBean() {
		this.pagesize = 8;
		this.data = new ArrayList<Object>();
	}

	public PageBean(int pagesize, int count, int pagetotal, int p, List<Object> data, int upperpage, int nextpage) {
		super();
		this.pagesize = pagesize;
		this.count = count;
		this.pagetotal = pagetotal;
		this.p = p;
		this.data = data;
		this.upperpage = upperpage;
		this.nextpage = nextpage;
	}

	public int getPagesize() {
		return pagesize;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
		// 总页数 Math.ceil()函数用来将浮点类型向上取整 Math.ceil(0.1)=1;
		pagetotal = (int) Math.ceil(count*1.0 / pagesize);
	}

	public void setPagetotal(int pagetotal) {
		this.pagetotal = pagetotal;
	}

	public int getPagetotal() {
		return pagetotal;
	}

	public int getP() {
		return p;
	}

	public void setP(int up) {
		this.p = up;
	}

	public List<Object> getData() {
		return data;
	}

	// 数据集合
	public void setData(List<Object> data) {
		this.data = data;
	}

	// 上一页
	public int getUpperpage() {
		// 如果当前页小于等于0的话,上一页的页数就是0;
		if (this.p <= 1) {
			this.upperpage = 1;
		} else {
			// 如果当前页的页数大于0的话,上一页就是当前页减1
			this.upperpage = this.p - 1;
		}
		return upperpage;
	}

	// 下一页
	public int getNextpage() {
		// 如果当前页数大于等于最大页了,那么下一页页数就是最大页的数量
		if (this.p >= this.pagetotal) {
			this.nextpage = this.pagetotal;
		} else {
			// 如果当前页数不是最大页数的话,下一页就等于是当前页数+1;
			this.nextpage = this.p + 1;
		}
		return nextpage;
	}

	@Override
	public String toString() {
		return "PageBean [pagesize=" + pagesize + ", count=" + count + ", pagetotal=" + pagetotal + ", p=" + p
				+ ", data=" + data + ", upperpage=" + upperpage + ", nextpage=" + nextpage + "]";
	}

}
