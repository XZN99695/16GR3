<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>成绩详情</title>
<script src="js/jquery-3.2.1.min.js" type="text/javascript"></script>
<style type="text/css">
body {
color:#003333 ;
}
a {
	color: #FF0033;
	text-decoration: none;
	font-size: 16px;
}
.sele{
width: 85px;
height: 25px;
font-size:16px;
margin-right: 116px
}
.txt{
height: 20px;
font-size:16px;
margin-right: 80px
}
</style>
</head>
<script type="text/javascript">
var clazzCcodes=new Array();
var clazzCnames=new Array();
<c:forEach items="${paper.pacls}" var="pacs" varStatus="status">
clazzCcodes.push("${pacs.classInfo.ccode}");
clazzCnames.push("${pacs.classInfo.cname}");
</c:forEach>
//加载班级数据
function loadData() {
	$.each(clazzCcodes,
		function(i,cla) {
			$("#clazzSel").append('<option value="'+clazzCcodes[i]+'">'+clazzCnames[i]+'</option>');
		}	
	);
}
//通过选择班级选择数据
function checkClazz() {
	var pid=$("#pid").val();
	var ccode=$("#clazzSel > :selected").val();
	var url="scores/scores_getClassByPid?paper.pid="+pid+"&clazz.ccode="+ccode;
	window.location.href=url;
}
function examScore(name,pid) {
	var url="scores/scores_getExamScoreTwo?us.name="+name+"&us.pid="+pid;
	window.location.href=url;
}
</script>
<body onload="loadData()">
<div>
<h3 align="center">${paper.pname }成绩列表</h3>
</div>
<div id="infodiv">
<form action="scores/scores_getClassByPid" method="post">
班级:<s:select list="{'请选择'}" name="clazz.ccode" id="clazzSel" class="sele" theme="simple"/>
 <input type="hidden" id="pid" name="paper.pid" value="${paper.pid }" /> 学生姓名: <input type="text" name="userName" value="${userName }" id="" class="txt" autocomplete="off"/><input type="submit" value="查询"/>
</form>
查询结果:总人数${countbean.paper_num }&emsp;&ensp;&emsp;及格人数${countbean.pass_num }&emsp;&ensp;&emsp;及格率${countbean.pass_rate }
</div>
<div>
<table align="center" style="text-align: center; width: 100%;">
	<tr>
		<th>序 号</th>
		<th>姓 名</th>
		<th>班 级</th>
		<th>开 考 时 间</th>
		<th>结 束 时 间</th>
		<th>分 数</th>
		<th>操 作</th>
	</tr>
	<c:forEach items="${uScores }" var="uscore" varStatus="status">
	<tr>
		<td>${status.count }</td>
		<td>${uscore.user.student.sname }</td>
		<td>${uscore.user.student.classInfo.cname }</td>
		<td>${pcs[status.index].examDate }</td>
		<td>${pcs[status.index].endDate }</td>
		<td>${uscore.score }</td>
		<td><a href="javascript:examScore('${uscore.name }',${uscore.pid })">查看详情</a></td>
	</tr>
	</c:forEach>
</table>
</div>
</body>
</html>