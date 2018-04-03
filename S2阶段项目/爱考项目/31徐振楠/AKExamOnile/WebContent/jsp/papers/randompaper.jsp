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
<title>随机组卷</title>
<script src="js/jquery-3.2.1.js" type="text/javascript"></script>
<script src="jquery-3.2.1.min.js" type="text/javascript"></script>
</head>
<script type="text/javascript">
var qtotal,maxdan1,maxdan2,maxdan3,maxduo1,maxduo2,maxduo3;
	//必须优先选择科目
	function mustChoose() {
		var csId=$("#csId").val();
		if(csId=="请选择"){
			alert("优先设置科目选项后才可以设置分数和题数哦~");
			$("#ptotalScore").val("");
			$("#dan1").val("");
			$("#dan2").val("");
			$("#dan3").val("");
			$("#duo1").val("");
			$("#duo2").val("");
			$("#duo3").val("");
			return;
		}
	}
	function GetJsondata() {
		var url="paper/paper_getCourseByInfo";
		var major=$("#majorSelect").val();
		var stage=$("#stageSelect").val();
		if(major != "请选择"){
			if(stage != "请选择"){
				$.getJSON(url,
				{
					'course.major':major,
					'course.stage':stage
				},
				function(data) {
					$("#csId").empty();
					$("#csId").append("<option value='请选择'>请选择</option>");
					$.each(data,function(i,objs){
						$("#csId").append("<option value='"+objs[0]+"'>"+objs[1]+"</option>");
					});
					}
				);
			}
		}
	}
	//根据科目算出各种题目类型的个数
	function GetJsondata2() {
		var csId=$("#csId").val();
		$("#dan1").val("");
		$("#dan2").val("");
		$("#dan3").val("");
		$("#duo1").val("");
		$("#duo2").val("");
		$("#duo3").val("");
		getCount();
		var url="paper/paper_getQuestionByCsId";
		if(csId != "请选择"){
		$.getJSON(
				url,
				{
					'course.csId':csId
				},
				function(data) {
					maxdan1 = data.maxdan1;
					maxdan2 = data.maxdan2;
					maxdan3 = data.maxdan3;
					maxduo1 = data.maxduo1;
					maxduo2 = data.maxduo2;
					maxduo3 = data.maxduo3;
				}
		);
	}
	}
	//算出总分和每一题的分数
	function getCount() {
		var dan1=0;
		var dan2=0;
		var dan3=0;
		var duo1=0;
		var duo2=0;
		var duo3=0;
		var ptotalScore=$("#ptotalScore").val();
		if(!$("#dan1").val()==""){
			if($("#dan1").val()<=maxdan1){
			dan1=parseInt($("#dan1").val());
			}else{
				alert("目前只有"+maxdan1+"道 简单 单选题");
				$("#dan1").val("");
				return;
			}
		}
		if(!$("#dan2").val()==""){
			if($("#dan2").val()<=maxdan2){
				dan2=parseInt($("#dan2").val());
				}else{
					alert("目前只有"+maxdan2+"道 一般 单选题");
					$("#dan2").val("");
					return;
				}
		}
		if(!$("#dan3").val()==""){
			if($("#dan3").val()<=maxdan3){
				dan3=parseInt($("#dan3").val());
				}else{
					alert("目前只有"+maxdan3+"道 困难 单选题");
					$("#dan3").val("");
					return;
				}
		}
		if(!$("#duo1").val()==""){
			if($("#duo1").val()<=maxduo1){
				duo1=parseInt($("#duo1").val());
				}else{
					alert("目前只有"+maxduo1+"道 简单 多选题");
					$("#duo1").val("");
					return;
				}
		}
		if(!$("#duo2").val()==""){
			if($("#duo2").val()<=maxduo2){
				duo2=parseInt($("#duo2").val());
				}else{
					alert("目前只有"+maxduo2+"道 一般 多选题");
					$("#duo2").val("");
					return;
				}
		}
		if(!$("#duo3").val()==""){
			if($("#duo3").val()<=maxduo3){
				duo3=parseInt($("#duo3").val());
				}else{
					alert("目前只有"+maxduo3+"道 困难 多选题");
					$("#duo3").val("");
					return;
				}
		}
		//总题目数
		qtotal=dan1+dan2+dan3+duo1+duo2+duo3;
		if(qtotal==0){
			$("#qtotal").val("");
			$("#qscore").val("");
			return;
		}
		if(qtotal>100){
			alert("题目限制100道");
			return;
		}
		//总题数
		$("#qtotal").val(qtotal);
		var qscore=Math.round(
				(ptotalScore/qtotal)*10
				)/10;
		//每一题分数(保留一位小数)
		$("#qscore").val(qscore);
	}
	
	function changeScore(element) {
		var ptotalScore=element.value;
		var qtotal=$("#qtotal").val();
		if(qtotal<=0){
			$("#qscore").val("0");
			return;
		}
		var score=(Math.round((ptotalScore/qtotal) * 10)) / 10;
		$("#qscore").val(score);
	}
	</script>
<body>
	<form action="paper/paper_createByRandom" id="randomform"
		 method="post">
		<input type="hidden" name="paper.ptype" value="笔试">
		<input type="hidden" name="paper.pstate" value="未开考">
		<table align="center" width="80%" height="80%">
			<tr>
				<td style="text-align: right;">方向：</td>
				<td>
					<s:select id="majorSelect" list="{'请选择','SCME','SCCE'}"
						name="course.major" onchange="GetJsondata()" theme="simple"/>
						 阶段:
					<s:select id="stageSelect" list="{'请选择','G1','G2','G3'}"
						name="course.stage" onchange="GetJsondata()" theme="simple" />
						 科目:
					<s:select list="{}" name="course.csId" id="csId" theme="simple"
						headerKey="请选择" headerValue="请选择" onchange="GetJsondata2()" /> 
						<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">标题：</td>
				<td>
					<input type="text" name="paper.pname" size="45" autocomplete="off"> 
					<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">总分：</td>
				<td>
					<input type="text" name="paper.ptotalScore"
					id="ptotalScore" autocomplete="off" onclick="mustChoose()" oninput="changeScore(this)" >分
					<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">考试时长：</td>
				<td>
					<input type="text" name="paper.ptime" autocomplete="off">分钟
					<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">单选题：</td>
				<td>
					简单<input type="text" name="maxdan1" id="dan1" size="2" autocomplete="off" onclick="mustChoose()"  oninput="getCount()">
					 一般<input type="text" name="maxdan2" id="dan2" size="2" autocomplete="off" onclick="mustChoose()" oninput="getCount()">
					困难<input type="text" name="maxdan3" id="dan3" size="2" autocomplete="off" onclick="mustChoose()" oninput="getCount()">
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">多选题：</td>
				<td>
					简单<input type="text" name="maxduo1" id="duo1" size="2" autocomplete="off" onclick="mustChoose()" oninput="getCount()">
				          一般<input type="text" name="maxduo2" id="duo2" size="2" autocomplete="off" onclick="mustChoose()" oninput="getCount()">
				          困难<input type="text" name="maxduo3" id="duo3" size="2" autocomplete="off" onclick="mustChoose()" oninput="getCount()">
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">总题数：</td>
				<td>
					<input type="text" name="paper.qtotal" id="qtotal" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">每题分数：</td>
				<td>
					<input type="text" name="paper.qscore" id="qscore" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td style="text-align: center;" colspan="2">
				<input type="submit" value="创建试卷"> 
				<input type="reset" value="取消">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>