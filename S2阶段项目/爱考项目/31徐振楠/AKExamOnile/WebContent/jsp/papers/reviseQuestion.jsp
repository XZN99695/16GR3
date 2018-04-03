<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>替换试题页面</title>
<style type="text/css">
.sele {
	width: 80px;
	height: 25px;
	font-size: 15px;
}
</style>
<script src="js/jquery-3.2.1.js" type="text/javascript"></script>
<script src="jquery-3.2.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
function reviseQJsp2() {
	var qtype=$("#qtypeSelect").val();
	var degree=$("#degreeSelect").val();
	var reviseform=$("#reviseform").val();
	if(qtype != "请选择"){
		if(degree != "请选择"){
			reviseform.submit();
		}
	}
}
function reviseSubmit() {
	var reviseform=document.getElementById("reviseform");
	reviseform.submit();
}
function closeDialog() {
	window.close();
	return true;
}
</script>
</head>
<body>
	<div>
		<s:form action="question/question_getTWBycsId" method="post" theme="simple">
		<s:hidden name="course.csId"/>
		<s:hidden name="writtenTest.qid"/>
		<s:hidden name="paper.pid"/>
		&emsp;类型:<s:select list="{'请选择','单选','多选'}" id="qtypeSelect" name="writtenTest.qtype" class="sele" />
		&emsp;难度:<s:select list="{'请选择','简单','一般','困难'}" id="degreeSelect" name="writtenTest.degree" class="sele" />
		&emsp;关键字:<s:textfield name="queryStr" />
		&nbsp;<s:submit value="查询" />
		</s:form>
	</div>
	<form id="reviseform" onsubmit="closeDialog()" action="question/question_reviseQuestion" method="post">
	<input type="hidden" name="course.csId" value="${course.csId }" >
	<input type="hidden" name="writtenTest.qid" value="${writtenTest.qid}">
	<input type="hidden" name="paper.pid" value="${paper.pid}">
	<table>
		<tr>
			<th></th>
			<th>序号</th>
			<th>类别</th>
			<th>难度</th>
			<th>章节</th>
			<th>标题</th>
		</tr>
		<s:iterator value="qiList" var="q" status="status">
			<tr>
				<td><s:radio name="qid" list="#{'%{#q.qid}':''}" listKey="#q.qid" theme="simple"/></td>
				<td><s:property value="#status.count"/></td>
				<td><s:property value="#q.qtype"/></td>
				<td><s:property value="#q.degree"/></td>
				<td><s:property value="#q.chapter"/></td>
				<td><s:property value="#q.qTitle"/></td>
			</tr>
		</s:iterator>
	</table>
	<input type="button" onclick="reviseSubmit()" value="替换"/>
	</form>
</body>
</html>