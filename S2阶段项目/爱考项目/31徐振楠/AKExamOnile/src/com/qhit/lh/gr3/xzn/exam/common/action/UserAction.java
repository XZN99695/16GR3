package com.qhit.lh.gr3.cyh.exam.common.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.qhit.lh.gr3.cyh.exam.common.bean.TUser;
import com.qhit.lh.gr3.cyh.exam.common.config.Constant;
import com.qhit.lh.gr3.cyh.exam.common.service.BaseService;
import com.qhit.lh.gr3.cyh.exam.common.service.impl.BaseServiceImpl;

public class UserAction extends ActionSupport {
	private TUser user;
	private BaseService bs = new BaseServiceImpl();

	public String login() {
		String hql = "select u from TUser u where 1 = 1";
		if (user.getName() != null && user.getPwd() != null && user.getRole() != 0) {
			if (!user.getName().equals("") && !user.getPwd().equals("") && user.getRole() >= 1) {
				hql += " and u.name = '" + user.getName() + "' and u.pwd = '" + user.getPwd() + "' and u.role = "
						+ user.getRole();
			} else {
				super.addActionError("登录失败,请重新输入~");
				return Constant.returnindex;
			}
		} else {
			super.addActionError("登录失败,请重新输入~");
			return Constant.returnindex;
		}
		System.out.println(hql);
		user=(TUser) bs.getObject(hql);
		if(user!=null) {
			ServletActionContext.getRequest().getSession().setAttribute("user", user);
			return Constant.gohome;
		}else {
			super.addActionError("该账户未注册,无法登陆!");
			return Constant.returnindex;
		}
	}
	//注销账户,返回主页面
	public String logout() {
		ServletActionContext.getRequest().getSession().setAttribute("user", null);
		ServletActionContext.getRequest().getSession().removeAttribute("user");
		ServletActionContext.getRequest().getSession().invalidate();
		return Constant.returnindex;
		
	}
	public TUser getUser() {
		return user;
	}

	public void setUser(TUser user) {
		this.user = user;
	}

}
