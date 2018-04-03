package com.qhit.lh.gr3.cyh.exam.common.config;

import org.apache.struts2.ServletActionContext;
import org.eclipse.jdt.internal.compiler.ast.Invocation;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.qhit.lh.gr3.cyh.exam.common.bean.TUser;

public class LoginInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
		TUser user=(TUser) arg0.getInvocationContext().getSession().get("user");
		if(null==user) {
			return Constant.returnindex;
		}
		return arg0.invoke();
	}

}
