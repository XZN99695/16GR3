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
	margin-right: 315px
}
.buttonstyle{
height: 28px;
width: 50px;
margin-left:20px;
}
</style>
</head>
<script type="text/javascript">
	var dialog;
	//Ajax Json异步加载科目数据
	function GetJsondata() {
		var url = "paper/paper_getCourseByInfo";
		var major = $("#majorSelect").val();
		var stage = $("#stageSelect").val();
		if (major != "请选择") {
			if (stage != "请选择") {
				$.getJSON(url,

				{
					'course.major' : major,
					'course.stage' : stage
				},

				function(data) {

					$("#csId").empty();
					$("#csId").append("<option value='请选择'>请选择</option>");

					$.each(

					data,

					function(i, objs) {//把list里的数据数组取出来,每一个数组都是一个option
						$("#csId").append(
								"<option value='"+objs[0]+"'>" +objs[1]+ "</option>");
					});
				});
			}
		}
	}
	//提交表单
	function getPaper() {
		var csId = $("#csId").val();
		var pType = $("#pType").val();
		var pState = $("#pState").val();
		if (csId != "请选择") {
			if (pType != "请选择") {
				if (pState != "请选择") {
					$("#paperform").submit();
				}
			}
		}
	}
	//随机组卷
	function randomWindow() {
		var width = (screen.width - 450) / 2;
		var height = (screen.height - 450) / 2;

		dialog = window
				.open(
						"jsp/papers/randompaper.jsp",
						"window",
						"width=538px,height=303px,top="
								+ height
								+ ", left="
								+ width
								+ ", toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
		//检查是否是否关闭了窗口
		setInterval(function() {
			if (dialog != null && dialog.closed) {
				window.location.reload(true);
			}
		}, 600);
	}
	//选题组卷
	function selectPaper() {
		var width = (screen.width - 450) / 2;
		var height = (screen.height - 450) / 2;
		var url = "paper/paper_getTWBycsId?course.csId=1&up=1&paper.qtotal=0";
		dialog = window
				.open(
						url,
						"window",
						"width=720px,height=530px,top="
								+ height
								+ ", left="
								+ width
								+ ", toolbar=no, menubar=no, scrollbars=no, resizable=no, location=yes, status=no");
		//检查是否是否关闭了窗口
		setInterval(function() {
			if (dialog != null && dialog.closed) {
				window.location.reload(true);
			}
		}, 600);
	}
	//准备考试
	function startExam(ma,id) {
		var width = (screen.width - 450) / 2;
		var height = (screen.height - 450) / 2;
// 		var url = "paper/paper_getTWBycsId?course.csId=1&up=1&paper.qtotal=0";
		var url = "classinfo/class_getClassbyInfo?course.major="+ma+"&paper.pid="+id;
		dialog = window
				.open(
						url,
						"window",
						"width=720px,height=530px,top="
								+ height
								+ ", left="
								+ width
								+ ", toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
		//检查是否是否关闭了窗口
		setInterval(function() {
			if (dialog != null && dialog.closed) {
				window.location.reload(true);
			}
		}, 600);
	}
	function lookPcScores(pid) {
		var url="scores/scores_getClassByPid?paper.pid="+pid;
		var width = (screen.width - 450) / 3;
		var height = (screen.height - 450) / 3;
		dialog = window
				.open(
						url,
						"window",
						"width=870px,height=530px,top="
								+ height
								+ ", left="
								+ width
								+ ", toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
		//检查是否是否关闭了窗口
		setInterval(function() {
			if (dialog != null && dialog.closed) {
				window.location.reload(true);
			}
		}, 600);
		}
	//关闭窗口
	function closedialog() {
		window.close();
	}
</script>
<body onload="closedialog()">
	<div>
		<h3 align="center">试卷列表</h3>
		<form action="paper/paper_getPaperByInfo?up=1" id="paperform"
			method="post">
			方向:
			<s:select id="majorSelect" name="course.major"
				list="{'请选择','SCME','SCCE'}" onchange="GetJsondata();" class="sele" />
			阶段:
			<s:select id="stageSelect" name="course.stage"
				list="{'请选择','G1','G2','G3'}" onchange="GetJsondata();" class="sele" />
			考试科目:
			<%-- 			<s:select list="{}" name="course.csId" listValue="course.csName" id="csId"  --%>
			<%-- 			 headerKey="" headerValue="请选择" class="sele2"/> --%>
			<select name="course.csId" id="csId" class="sele2">
				<option value="${course.csName == null ? '' : course.csId }">${course.csName == null ? '请选择' : course.csName }</option>
			</select> 考试类型:
			<s:select list="{'请选择','笔试','机试'}" name="paper.ptype" id="pType"
				class="sele" />
			状态:
			<s:select list="{'请选择','未开考','考试中','考试结束'}" name="paper.pstate"
				id="pState" class="sele" />&nbsp;
				<input type="button" value="查 询" onclick="getPaper()" class="buttonstyle" />
		</form>
		<div style="margin-top: 20px;">
			<button onclick="randomWindow()" class="margRight">随机组卷</button>
			<button onclick="selectPaper()" class="margRight">选题组卷</button>
			<button onclick="" class="margRight">上机考试</button>
		</div>
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
					<th><font color="white">考试时长(分钟)</font></th>
					<th><font color="white">状态</font></th>
					<th><font color="white">操作</font></th>
				</tr>
				<s:iterator value="pb.data" var="prs" status="status">
					<tr>
						<td><s:property value="#status.count" /></td>
						<td><s:property value="#prs.ptype" /></td>
						<td><s:property value="#prs.course.csName" /></td>
						<td><s:property value="#prs.pName" /></td>
						<td><s:iterator value="#prs.pacls" var="pacls">
					[<s:property value="#pacls.classInfo.cname" />]
					</s:iterator></td>
						<td><s:property value="#prs.pTime" /></td>
						<td><s:if test="#prs.pState == '未开考'">
								<font color="red"> <s:property value="#prs.pState" />
								</font>
							</s:if> <s:elseif test="#prs.pState == '考试中'">
								<font color="green"> <s:property value="#prs.pState" />
								</font>
							</s:elseif> <s:elseif test="#prs.pState == '考试结束'">
								<s:property value="#prs.pState" />
							</s:elseif></td>
						<td><s:if test="#prs.pState == '未开考'">
								<a href="paper/paper_delPaperById?paper.pid=${prs.pid }&up=${pb.p}">${prs.pid }删除</a>&nbsp;<a
									href="javascript:startExam('${prs.course.major }',${prs.pid })">开始考试</a>&nbsp;<a
									href="paper/paper_getQuestionById?paper.pid=${prs.pid }"
									target="_blank">查看试卷</a>
							</s:if> <s:elseif test="#prs.pState == '考试中'">
							<a href="paper/paper_delPaperById?paper.pid=${prs.pid }&up=${pb.p}">${prs.pid }删除</a>&nbsp;
								<a href="paper/paper_stopExam?paper.pid=${prs.pid }&up=${pb.p}">结束考试</a>&nbsp;<a
									href="paper/paper_getQuestionById?paper.pid=${prs.pid }"
									target="_blank">查看试卷</a>
							</s:elseif> <s:elseif test="#prs.pState == '考试结束'">
								<a href="javascript:lookPcScores(${prs.pid })">查看成绩</a>&nbsp;<a
									href="paper/paper_getQuestionById?paper.pid=${prs.pid }"
									target="_blank">查看试卷</a>
							</s:elseif></td>
					</tr>
				</s:iterator>
			</table>
			<s:if test="%{pb.data.size()>0}">
				<div align="right" id="marginTop">
					<font style="color: #FF0033;">第${pb.p }页， 共${pb.pagetotal }页</font>&nbsp;&nbsp;
					<a
						href="paper/paper_getPaperByInfo?up=1&course.csId=${course.csId }&paper.ptype=${paper.ptype }&paper.pstate=${paper.pstate}">首页</a>&nbsp;
					<a
						href="paper/paper_getPaperByInfo?up=${pb.upperpage }&course.csId=${course.csId }&paper.ptype=${paper.ptype }&paper.pstate=${paper.pstate}">上一页</a>&nbsp;
					<a
						href="paper/paper_getPaperByInfo?up=${pb.nextpage }&course.csId=${course.csId }&paper.ptype=${paper.ptype }&paper.pstate=${paper.pstate}">下一页</a>&nbsp;
					<a
						href="paper/paper_getPaperByInfo?up=${pb.pagetotal }&course.csId=${course.csId }&paper.ptype=${paper.ptype }&paper.pstate=${paper.pstate}">末页</a>
				</div>
			</s:if>
		</fieldset>
	</div>
</body>
</html>