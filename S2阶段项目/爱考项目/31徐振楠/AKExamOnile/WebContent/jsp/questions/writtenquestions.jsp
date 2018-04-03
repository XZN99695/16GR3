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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<title>Insert title here</title>
<style type="text/css">
th {
	background-color: gray;
	height: 30px;
}
a {
	color:#FF0033;
	text-decoration: none;
	font-size: 18px;
}
table{ 
	border-collapse: collapse;
	border: 1px;
	bordercolor: #CCCCCC;
    width: 1220px;
    align: center;
    text-align: center;
}
td{
	border: solid #CCCCCC 1px;
}
</style>
</head>
<body onload="load()">
	<div style="margin: 20px 20px 5px 52px;">
		<div align="center" ><font style="align-content color: gray; font-size: 20px;">${course.csName }&nbsp;笔试试题列表</font></div>
		<br />
		<input type="button" value="添加试题" onclick="addTest('jsp/questions/addwritten.jsp?csId=${course.csId}&csName=${course.csName}')" style="float: left;margin-right: 20px;" />&emsp;
		<form
			action="question/question_importfile?course.csId=${course.csId }&course.csName=${course.csName }"
			method="post" enctype="multipart/form-data"
			style="float: left;margin-right: 20px;">
			<input type="file" name="importfile">
			<input type="submit" value="导入模板">
		</form>
		<input type="button" value="下载考试试题模板" onclick="downFile()" />
		<input type="hidden" id="csId" value="${course.csId}">
		<input type="hidden" id="csName" value="${course.csName }">
	</div>
	<div style="margin: 5px 20px 5px 5px; border:1px solid #CCCCCC; padding-top:20px;padding-right:28px;padding-bottom:0px;padding-left:30px; background-color: white;">
		<table>
			<tr>
				<th>序号</th>
				<th>类型</th>
				<th>题目内容</th>
				<th>选项A</th>
				<th>选项B</th>
				<th>选项C</th>
				<th>选项D</th>
				<th>答案</th>
				<th>难度</th>
				<th>对应章节</th>
				<th>操作</th>
			</tr>
			<s:iterator value="pb.data" var="written" status="status">
				<tr>
					<td><s:property value="#status.count" /></td>
					<td><s:property value="#written.qType" /></td>
					<td><s:property value="#written.qTitle" /></td>
					<td><s:property value="#written.optionA" /></td>
					<td><s:property value="#written.optionB" /></td>
					<td><s:property value="#written.optionC" /></td>
					<td><s:property value="#written.optionD" /></td>
					<td><s:property value="#written.answer" /></td>
					<td><s:property value="#written.degree" /></td>
					<td><s:property value="#written.chapter" /></td>
					<td><a href="javaScript:addTest('question/question_getWrittenById?course.csId=${course.csId}&writtenTest.qid=${written.qid }')">编辑</a>&emsp;<a href="question/question_delTWrittens?course.csId=${course.csId}&course.csName=${course.csName }&writtenTest.qid=${written.qid }&up=${pb.p}">删除</a></td>
				</tr>
			</s:iterator>
		</table>
		<div align="right" style="margin:margin-top: 15px;margin-right: 10px;">
		<font style="color: #FF0033;">
		第${pb.p }页,共${pb.pagetotal }页
		&nbsp;<a href="javascript:checkP(1)">首页</a>
		&nbsp;<a href="javascript:checkP(${pb.upperpage })">上一页</a>
		&nbsp;<a href="javascript:checkP(${pb.nextpage })">下一页</a>
		&nbsp;<a href="javascript:checkP(${pb.pagetotal })">末页</a>
		</font>
		</div>
	</div>
	<script type="text/javascript">
		//添加试题
		function addTest(url) {
			var width = (screen.width - 450) / 2;
			var height = (screen.height - 450) / 2;
			var dialog= window.open(url,
					"window",
					"width=510px,height=350px,top="
							+ height
							+ ", left="
							+ width
							+ ", toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
	
		setInterval(function(){
			if(dialog != null && dialog.closed){
				window.location.reload(true);
			}
		}, 800);
		}
		//选页
		function checkP(up) {
			var csId=document.getElementById("csId").value;
			var csName=document.getElementById("csName").value;
			var url="question/question_getTWrittens?course.csId="+csId+"&course.csName="+csName+"&up="+up;
			window.location.href=url;
		}
		//下载试题模板
		function downFile() {
			window.location.href = "file/试题模板.xls";
		}
		function load() {
			window.close();
		}
	</script>
</body>
</html>