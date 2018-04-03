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
<title>查看试卷</title>
<script src="js/jquery-3.2.1.js" type="text/javascript"></script>
<script src="jquery-3.2.1.min.js" type="text/javascript"></script>
</head>
<script type="text/javascript">
var dialog;
function reviseQJsp(csId,qid,pid) {
	var url="question/question_getTWBycsId?course.csId="+csId+"&writtenTest.qid="+qid+"&paper.pid="+pid;
	var width = (screen.width - 450) / 2;
	var height = (screen.height - 450) / 2;
	dialog=window.open(url,"w",
		"width=600px,height=450px,top="
		+ height
		+ ", left="
		+ width
		+ ", toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
	setInterval(function(){
		if(dialog!=null&&dialog.closed){
			window.location.reload(true);
		 }
	  },500);
}
function closeDialog(){
	if(window.name=="w"){
		window.close();
	}
}
</script>
<body onload="closeDialog()">
<div style="text-align: center;background-color: #F9F9F9">
科目:<font color="red"><s:property value="paper.course.csName"/></font>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;考试时长:<font color="red">${paper.ptime }分钟</font>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
总分:<font color="red">${paper.ptotalScore }分</font>
</div>
<br /><br />
<div style="background-color: #F9F9F9">
<s:iterator value="paper.writtenTests" var="writtens" status="status">
<table style="background-color: #F9F9F9">
	<tr>
		<td><input type="button" value="替换" onclick="reviseQJsp(${writtens.csId},${writtens.qid},${paper.pid })"/>&nbsp;<s:property value="#status.count"/>.&nbsp;<s:property value="#writtens.qtitle"/></td>
	</tr>
	<tr>
		<td><s:property value="#writtens.optionA"/></td>
	</tr>
	<tr>
		<td><s:property value="#writtens.optionB"/></td>
	</tr>
	<tr>
		<td><s:property value="#writtens.optionC"/></td>
	</tr>
	<tr>
		<td><s:property value="#writtens.optionD"/></td>
	</tr>
</table>
</s:iterator>
</div>
</body>
</html>