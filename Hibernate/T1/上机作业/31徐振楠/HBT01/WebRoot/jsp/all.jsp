<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'all.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
  <a href="jsp/add.jsp">添加</a><br>
   <table border="1px">
    		<tr>
    			<td>编号</td>
    			<td>账号</td>
    			<td>密码</td>
    			<td>性别</td>
    			<td>年龄</td>
    			<td>学历</td>
    			<td>操作</td>
    		</tr>
    		<s:iterator value="users" var="user">
    			<tr>
    				<td><s:property value="#user.id"></s:property></td>
    				<td><s:property value="#user.name"></s:property></td>
    				<td><s:property value="#user.pwd"></s:property></td>
    				<td><s:property value="#user.sex"></s:property></td>
    				<td><s:property value="#user.age"></s:property></td>
    				<td><s:property value="#user.edu"></s:property></td>
    				<td>
	    				<a href="delUser?user.id=<s:property value="#user.id"></s:property>">删除</a>&nbsp;
    				</td>
    			</tr>
    		</s:iterator>
    	</table>
  </body>
</html>
