<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>成绩详情</title>
<style type="text/css">
body {
color:#003333 ;
}
</style>
</head>
<script type="text/javascript">
function goback() {
	window.history.go(-1);
}
</script>
<body>
<div style="width: 100%px">
<span style="margin-right:135px">科目:${us.paper.course.csName }</span>
<span style="margin-right:108px">规定时长:${us.paper.ptime }(分钟)</span>
<span style="margin-right:145px">总分:${us.paper.ptotalScore }</span>
<span>总题数:${up }</span><br /><br />
<span style="margin-right:267px">开考时间:${pClass.examDate }</span>
<span>结束时间:${pClass.endDate }</span><br /><br />
<span style="margin-right:377px">考生姓名:${us.user.student.sname }</span>
<span style="margin-right:136px">总得分:<font color="#993300">${us.score }分</font></span>
<input type="button" value="返回" onclick="goback()" />
</div><br />
<hr width="89%" align="left"/>
<br />
<c:forEach items="${writtenTests }" var="w" varStatus="status">
<div>
${status.count }.${w.qtitle }&nbsp;［${answers[status.index] }］
<c:if test="${answers[status.index] != w.answer }"><font color="red">正确答案:［${w.answer }］</font></c:if>
<br />
A.${w.optionA }<br />
B.${w.optionB }<br />
C.${w.optionC }<br />
D.${w.optionD }<br /><br />
</div>
</c:forEach>
</body>
</html>