<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
 String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="css/common.css" type="text/css" />
    <title>在线考试管理系统</title>
</head>
<frameset rows="50,*" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="jsp/top.jsp" name="topFrame" frameborder="no" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
  <frameset name="myFrame" cols="199,7,*," frameborder="no" border="0" framespacing="0">
    <frame src="jsp/left.jsp" name="leftFrame" frameborder="no" scrolling="No" noresize="noresize" id="leftFrame" title="leftFrame" />
	<frame src="jsp/switchframe.jsp" name="midFrame" frameborder="no" scrolling="No" noresize="noresize" id="midFrame" title="midFrame" />
    <frameset rows="59,*" cols="*" frameborder="no" border="0" framespacing="0">
         <frame src="jsp/mainframe.jsp" name="mainFrame" frameborder="no" scrolling="No"  noresize="noresize" id="mainFrame" title="mainFrame" />
         <frame src="jsp/manframe.jsp" name="manFrame" frameborder="no" id="manFrame" title="manFrame" />
     </frameset>
  </frameset>
</frameset>
</html>
