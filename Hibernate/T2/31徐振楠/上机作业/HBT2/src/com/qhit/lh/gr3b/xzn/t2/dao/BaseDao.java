package com.qhit.lh.gr3b.xzn.t2.dao;

import java.util.List;

public interface BaseDao {
	//添加
  public void add(Object obj);
  //删除
  public void del(Object obj);
  //更新
  public void upd(Object obj);
  //查询
  public List<Object> all(String fromObject);
  //根据ID查询
  public Object getObjectById(Object obj, int id);
}
