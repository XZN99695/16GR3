package com.qhit.xzn;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class Myinter extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
	System.out.println("我开始拦截...");
	String result =arg0.invoke();
	System.out.println("返回代码:"+result);
	return result;
	}

}
