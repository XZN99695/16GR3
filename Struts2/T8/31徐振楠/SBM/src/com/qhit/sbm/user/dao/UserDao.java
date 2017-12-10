/**
 * 
 */
package com.qhit.sbm.user.dao;

import com.qhit.sbm.user.bean.User;

/**
 * @author admin
 * 2017年11月13日
 */
public interface UserDao {

	/**
	 * @param userName
	 * @param passWord
	 * @return
	 * 登录的数据访问
	 */
	public User doLogin(String userName, String userPassword);
}
