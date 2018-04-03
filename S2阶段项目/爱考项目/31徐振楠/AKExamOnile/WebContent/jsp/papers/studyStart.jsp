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
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-3.2.1.min.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="<%=basePath%>js/WdatePicker.js"></script>
<title>准备考试</title>
<style type="text/css">
.buttonstyle{
height: 22px;
width: 56px;
}
.sele{
width: 200px;
height: 23px;
}
.calendar{
width: 200px;
}
td{
height: 30px;
}
th{
height: 34px;
}
</style>
</head>
<script type="text/javascript">
//创建行
function CreateTr() {
	var tr2=document.getElementById("t2").cloneNode(true);
	var num=getUpLengthNum();
	var trcells=tr2.cells;
	document.getElementById("tab").appendChild(tr2);
	var td3=trcells[trcells.length-1];
	td3.innerHTML='<input type="button" value="删除" class="buttonstyle" onclick="delTr(this)"/>';
	var td2=trcells[trcells.length-2];
	td2.firstChild.value="";
	td2.firstChild.name="paperClasses["+num+"].examDate";
	var td1=trcells[trcells.length-3];
	td1.firstChild.name="paperClasses["+num+"].ccode";
}
//删除行
function delTr(element) {
	var tr=element.parentNode.parentNode;//删除按钮的父节点是td,td的父节点是tr
	tr.parentNode.removeChild(tr);//tr的父节点是table,table删除指定子节点tr
}
//返回最后一行的索引减一的值
function getUpLengthNum() {
	var tab=document.getElementById("tab");
	var lastTr=tab.rows[tab.rows.length-1];
	return lastTr.rowIndex;
}
//Json加载班级列表
function getClassInfo(element) {
	var major=$("#hi").val();
	var url="classinfo/class_getClassbyInfo";
	$.getJSON(
			url,
			{
				"course.major":major
			},
			function(data) {
				element.innerHTML="";
				element.options.add(new Option("请选择班级","")); //这个兼容IE与firefox 
				$.each(
						data,
						function(i, clazz) {
							element.options.add(new Option(clazz.cname,clazz.ccode)); //Dom元素 select添加Option选项方法:element.options.add(new Option("text","value")); 
						}
				);
			}
	);	
}
function startExam() {
	var pid=$("#hello").val();
	var url="paper/paper_createExam?paper.pid="+pid;
	$("#studyform").attr("action",url);
	$("#studyform").submit();
}
function reset() {
	$("#studyform")[0].reset();//通过下标拿到DOM元素,通过DOM元素的reset()来重置表单
}
</script>
<body>
<div style="margin-left: 70px; margin-top: 10px">
<input type="button" onclick="startExam()" value="开始考试"/>&ensp;<input type="button" onclick="reset()" value="取消"/><input id="hi" type="text" value="${course.major }" /><input id="hello" type="text" value="${paper.pid }" />
</div>
<div align="center">
<br />
<font color="red">请选择参加考试的班级及开考时间</font>
<br />
<br />
<br />
<form action="" method="post" id="studyform">
<table id="tab" width="600px" border=1 bordercolor=#000000 style="border-collapse:collapse; text-align: center;" >
	<tr id="t1">
		<th>班 级</th>
		<th>开 考 时 间</th>
		<th width="110px;"><input type="button" value="添 加" onclick="CreateTr()" class="buttonstyle" /></th>
	</tr>
	<tr id="t2">
		<td><select onchange="" id="s1" name="paperClasses[0].ccode" class="sele">
		<option value="">请选择班级</option>
		<s:iterator value="clists" var="clazz" >
		<option value="${clazz.ccode }">${clazz.cname }</option>
		</s:iterator>
		</select>
		</td>
		<td><input id="" name="paperClasses[0].examDate" class="Wdate" onfocus="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss' })" /></td>
		<td></td>
	</tr>
</table>
</form>
</div>
</body>
</html>