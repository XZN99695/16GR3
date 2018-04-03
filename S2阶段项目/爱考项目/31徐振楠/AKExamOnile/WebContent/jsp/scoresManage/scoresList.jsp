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
<title>笔试成绩列表</title>
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
//查询考试成绩
function examScore(pid) {
	var url="scores/scores_getExamScore?us.pid="+pid;
	var width = (screen.width - 450) / 2;
	var height = (screen.height - 450) / 2;
	dialog = window
			.open(
					url,
					"window",
					"width=1000px,height=600px,top="
							+ height/2
							+ ", left="
							+ width/2+30
							+ ", toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
	//检查是否是否关闭了窗口
	setInterval(function() {
		if (dialog != null && dialog.closed) {
			window.location.reload(true);
		}
	}, 600);
}
//选页
function checkP(up) {
	var url="scores/scores_getScoresList?up="+up;
	window.location.href=url;
}
function closeDialog() {
	window.close();
	}
</script>
<body onload="closeDialog()">
	<div>
		<h3 align="center">笔试成绩列表</h3>
	</div>
	<div>
		<fieldset id="marginTop">
			<table width="100%" align="center" id="marginTop" border="0.5"
				bordercolor="#eee" cellspacing="1">
				<tr>
					<th><font color="white">序号</font></th>
					<th><font color="white">考试科目</font></th>
					<th><font color="white">开考时间</font></th>
					<th><font color="white">结束时间</font></th>
					<th><font color="white">分数</font></th>
					<th><font color="white">操作</font></th>
				</tr>
				<s:iterator value="pb.data" var="pc" status="status" step="1">
					<tr>
						<td><s:property value="#status.count" /></td>
						<td><s:property value="#pc.pper.course.csName" /></td>
						<td><s:property value="#pc.examDate" /></td>
						<td><s:property value="#pc.endDate" /></td>
						<td><s:property value="uScores[#status.index].score" /></td>
					<td><a href="javascript:examScore(${pc.pid })">查看详情</a></td>
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