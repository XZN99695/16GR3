/**
 * 
 */
package com.qhit.lh.gr3b.xzn.t8.dao;

import java.util.List;

/**
 * @author admin
 * 2017骞�2鏈�1鏃�
 */
public interface BaseDao {
	
	/**
	 * @param obj
	 * 澧�
	 */
	public void add(Object obj);
	
	/**
	 * @param obj
	 * 鍒�
	 */
	public void delete(Object obj);
	
	/**
	 * @param obj
	 * 鏀�
	 */
	public void update(Object obj);
	
	/**
	 * 鏌�
	 */
	public List<Object> getAll(String fromObject);
	
	/**
	 * @param obj
	 * @param id
	 * @return
	 * 鏍规嵁id鍘绘煡
	 */
	public Object getObjectById(Class clazz, int id);
}
