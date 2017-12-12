package com.qhit.le.gr3b.xzn.hbt01.service;

import java.util.List;

import com.qhit.le.gr3b.xzn.hbt01.bean.User;

/**
 * @author 徐振楠
 *TODO
 *2017年12月11日t下午9:18:59
 */
public interface BaseService {
	//添加
  public void add(Object obj);
	//删除
  public void del(Object obj);
	//修改
  public void upd(Object obj);
	//查询
  public List<User> all(String fromObject);
}
