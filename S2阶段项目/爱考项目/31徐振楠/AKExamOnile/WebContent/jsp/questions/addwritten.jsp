<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
<title>添加试题</title>
<script src="js/jquery-3.2.1.min.js" type="text/javascript"></script>
<style type="text/css">
.red{
color: red;
}
.right{
text-align: right;
}
.left{
text-align: left;
}
</style>
</head>
<body onload="loadValue()">
<div align="center">

<form  id="addwfrom" action="question/question_addTWrittens" method="post" onsubmit="closeDialog()">
<input type="hidden" name="writtenTest.csId" value="${param.csId }">
<input type="hidden" name="course.csId" value="${param.csId }">
<input type="hidden" name="course.csName" value="${param.csName }">
<table width="490px;" height="300px;">
<tr>
	<td class="right">科目名称:</td>
	<td>${param.csName}<span class="red">*</span></td>
</tr>
<tr>
	<td class="right">类&emsp;&emsp;型:</td>
	<td>
	<select name="writtenTest.qtype" id="XZ" onchange="ChangeType()">
	<option value="单选" selected="selected">单选</option>
	<option value="多选">多选</option>
	</select><span class="red">*</span>
	</td>
</tr>
<tr>
	<td class="right">题目内容:</td>
	<td>
	<textarea rows="4" cols="30" style="width: 350px; height: 80px;" name="writtenTest.qtitle" id="qtitle"></textarea>
	<span class="red">*</span>
	</td>
</tr>
<tr>
	<td class="right">选&emsp;&emsp;项:</td>
	<td>
	<input type="radio" name="writtenTest.answer" value="A" checked="checked"/>
	<input type="text" name="writtenTest.optionA" id="optionA" /><span class="red">*</span>
	<br />
	<input type="radio" name="writtenTest.answer" value="B"/>
	<input type="text" name="writtenTest.optionB" id="optionB" /><span class="red">*</span>
	<br />
	<input type="radio" name="writtenTest.answer" value="C"/>
	<input type="text" name="writtenTest.optionC" id="optionC" /><span class="red">*</span>
	<br />
	<input type="radio" name="writtenTest.answer" value="D"/>
	<input type="text" name="writtenTest.optionD" id="optionD" /><span class="red">*</span>
	<br />
	</td>
</tr>
<tr>
	<td class="right">难易程度:</td>
	<td>
	<select name="writtenTest.degree" id="degree">
	<option value="简单" selected="selected">简单</option>
	<option value="普通" >普通</option>
	<option value="困难" >困难</option>
	</select><span class="red">*</span>
	</td>
</tr>
<tr>
	<td class="right">对应章节:</td>
	<td><input type="text" name="writtenTest.chapter" id="chapter"><span class="red">*</span></td>
</tr>
<tr>
	<td>
	</td>
	<td align="left">
	<input type="button" value="添加" onclick="GoAction()"/>
	<input type="reset" value="重置">
	</td>
</tr>
</table>
</form>
</div>
<script type="text/javascript">
function loadValue() {
	var qtype="${writtenTest.qtype}";
	var degree="${writtenTest.degree}";
	 $("#XZ>option").each(function(){  //遍历所有option
		 if(this.value==qtype){
			 this.selected=true;
		 }
    });
	 $("#degree option").each(
		function() {
			if(this.value==degree){
				this.selected=true;
			}
		} 
	 );
// 	var w=$("#degree").children("option");
// 	alert(w.length);
}
function ChangeType() {
	var XZ=document.getElementById("XZ").value;
	var options=document.getElementsByName("writtenTest.answer");
	if(XZ == '单选'){
		for (var i = 0; i < options.length; i++) {
			options[i].setAttribute("type", "radio");
		}
	}else{
		for (var i = 0; i < options.length; i++) {
		options[i].setAttribute("type", "checkbox");
		}
	}
}

function GoAction() {
	var v1=document.getElementById("optionA").value;
	var v2=document.getElementById("optionB").value;
	var v3=document.getElementById("optionC").value;
	var v4=document.getElementById("optionD").value;
	var v5=document.getElementById("qtitle").value;
	var v6=document.getElementById("chapter").value;
	if(v1!="" && v2!="" && v3!="" && v4!="" && v5!="" && v6!=""){
		document.getElementById("addwfrom").submit();
	}else{
		alert("信息必须填写完整!");
	}
	
function closeDialog() {
	window.close();
	return true;
	}
}
</script>
</body>
</html>