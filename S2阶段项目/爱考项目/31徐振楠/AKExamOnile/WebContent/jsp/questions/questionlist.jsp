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
<title>Insert title here</title>
<style type="text/css">
.group {
	text-align: center;
	width: 395px;
	height: 60px;
	margin: 10px 13px 0px 5px;
	float: left;
	border: solid 2px black;
	padding: 5px;
}

.sele {
	width: 80px;
	height: 25px;
	font-size: 15px;
}

a {
color:black;
text-decoration: none;
font-size: 17px;
}
</style>
</head>
<body>
	<br />
	<s:form action="/question/question_getQuestionByInfo" id="questionform"
		method="post" theme="simple">
		&emsp;&emsp;&nbsp;<s:select name="course.major" id="sele1"
			list="{'请选择','SCME','SCCE'}" class="sele" onchange="getQuestion()" />
		<s:select name="course.stage" id="sele2" list="{'请选择','G1','G2','G3'}"
			class="sele" onchange="getQuestion()" />
	</s:form>
	<div style="width: 1500px; margin: 15px 20px 0px 15px;" align="center">
		<!-- 开始取值 -->
		<s:iterator value="courses" var="courser">
			<div align="center" class="group">
				${courser.csName}&emsp;${courser.stage} <br /> &nbsp;
				<a href="question/question_getTMachines?course.csId=${courser.csId}&course.csName=${courser.csName}&up=1">
				机试题: <s:property value="#courser.machineTests.size()" /> </a>
				 <br /> 
				 <a href="question/question_getTWrittens?course.csId=${courser.csId}&course.csName=${courser.csName}&up=1">
				 笔试题: <s:property value="#courser.writtenTests.size()" /> </a>
			</div>
		</s:iterator>
	</div>
	<script type="text/javascript">
		function getQuestion() {
			// 		window.location.href="question/question_getQuestionByInfo";
			var questionform = document.getElementById("questionform");
			var sele1 = document.getElementById("sele1").value;
			var sele2 = document.getElementById("sele2").value;
			if (sele1 != "请选择") {
				if (sele2 != "请选择") {
					questionform.submit();
				}
			}
		}
	</script>
</body>
</html>