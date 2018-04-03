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
<title>试卷管理</title>
<script src="js/jquery-3.2.1.min.js" type="text/javascript"></script>
<style type="text/css">
span {
	display: none;
}

th {
	background-color: #666;
}

td {
	text-align: center;
}

#marginTop {
	margin-top: 20px;
}

.margRight {
	margin-right: 10px;
}

a {
	color: #FF0033;
	text-decoration: none;
	font-size: 16px;
}

.sele {
	width: 80px;
	height: 25px;
	font-size: 15px;
}

.sele2 {
	height: 25px;
	font-size: 15px;
}
.buttonstyle{
height: 28px;
width: 50px;
}
</style>
</head>
<script type="text/javascript">
function eqTime(startDateStr,pid,ccode) {
	var examDate=Date.parse(startDateStr);//Date.parse(dateStr)把时间转换成Date类型;Number(Str)再转为数值
	var time = new Date().getTime();//getTime():获得当前时间的毫秒数
	if(time>=examDate){
		var url="exam/exam_getExamInfo?paper.pid="+pid+"&ccode="+ccode;
		StartExam(url);
	}else{
		downtime=examDate-time;
		var hours = parseInt(downtime/1000/60/60%24, 10); //计算剩余的小时
		hours=checkTime(hours);
	  	var minutes = parseInt(downtime/1000/60%60, 10);//计算剩余的分钟
	  	minutes=checkTime(minutes);
	  	var seconds = parseInt(downtime/1000%60, 10);//计算剩余的秒数
	  	seconds=checkTime(seconds);
		alert("现在不能参加考试,剩余:"+hours+"时"+minutes+"分"+seconds+"秒.");
	}
}
function checkTime(T) {
	if(T<10){
		T="0"+T;
	}else if(T<=0){
		T="00";
	}
	return T;
}
function StartExam(url) {
	var width = (screen.width - 450) / 2;
	var height = (screen.height - 450) / 2;

	dialog = window
			.open(
					url,
					"Exam",
					"width=1070px,height=620px,,top="
							+ height/2
							+ ", left="
							+ width/2
							+ ", toolbar=no, menubar=n  scrollbars=no, resizable=no, location=no, status=no");
	//检查是否是否关闭了窗口
	setInterval(function() {
		if (dialog != null && dialog.closed) {
			window.location.reload(true);
		}
	}, 600);
}
//选页
function checkP(up) {
	var url="exam/exam_getPapersByInfo?up="+up;
	window.location.href=url;
}

function closeDialog() {
	window.close();
	}
</script>
<body onload="closeDialog()">
	<div>
		<h3 align="center">您可以参加以下考试</h3>
	</div>
	<div>
		<fieldset id="marginTop">
			<table width="100%" align="center" id="marginTop" border="0.5"
				bordercolor="#eee" cellspacing="1">
				<tr>
					<th><font color="white">序号</font></th>
					<th><font color="white">类型</font></th>
					<th><font color="white">科目</font></th>
					<th><font color="white">标题</font></th>
					<th><font color="white">考试班级</font></th>
					<th><font color="white">开始时间</font></th>
					<th><font color="white">考试时长(分钟)</font></th>
					<th><font color="white">操作</font></th>
				</tr>
				<s:set var="i" value="-1" />
				<s:iterator value="pb.data" var="prs" status="status" step="1">
				<s:set var="i" value="#i+1" />
					<tr>
						<td><s:property value="#status.count" /></td>
						<td><s:property value="#prs.ptype" /></td>
						<td><s:property value="#prs.course.csName" /></td>
						<td><s:property value="#prs.pName" /></td>
						<td><s:iterator value="#prs.pacls" var="pacls">
					[<s:property value="#pacls.classInfo.cname" />]
					</s:iterator></td>
					<td>${examDate[i] }</td>
					<td><s:property value="#prs.ptime" /></td>
					<td>
					<s:if test="#prs.pstate=='未开考'">
					<a href="javascript:eqTime('${examDate[i] }','${prs.pid }','${ccode }');">开始考试</a>
					</s:if><s:elseif test="#prs.pstate=='考试中'">
					<a href="javascript:eqTime('${examDate[i] }','${prs.pid }','${ccode }');">考试中</a>
					</s:elseif>
					</td>
				</s:iterator>
			</table>
				<div align="right" id="marginTop">
					<font style="color: #FF0033">第${pb.p }页， 共${pb.pagetotal }页&nbsp;&nbsp;
					<a href="javascript:checkP(1)">首页</a>&nbsp;
					<a href="javascript:checkP(${pb.upperpage })">上一页</a>&nbsp;
					<a href="javascript:checkP(${pb.nextpage })">下一页</a>&nbsp;
					<a href="javascript:checkP(${pb.pagetotal })">末页</a>
					</font>
				</div>
		</fieldset>
	</div>
</body>
</html>