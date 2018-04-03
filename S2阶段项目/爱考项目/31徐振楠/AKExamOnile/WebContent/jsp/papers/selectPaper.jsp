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
<script src="js/jquery-3.2.1.js" type="text/javascript"></script>
<script src="jquery-3.2.1.min.js" type="text/javascript"></script>
<title>选题组卷</title>
<style type="text/css">
.sele {
	width: 80px;
	height: 25px;
	font-size: 15px;
}

.sele2 {
	height: 25px;
	font-size: 15px;
}

.w {
	width: 405px;
}

.w2 {
	width: 100px;
}

.w3 {
	width: 51.2px;
}
</style>
</head>
<script type="text/javascript">
	//通过Ajax Json获取科目内容
	function getCourseJson() {
		var url = "paper/paper_getCourseByInfo";
		var major = $("#majorSelect").val();
		var stage = $("#stageSelect").val();
		if (major != "请选择") {
			if (stage != "请选择") {
				$.getJSON(url, {
					"course.major" : major,
					"course.stage" : stage
				}, function(Data) {
					$("#csId").empty();
					$("#csId").append("<option value='请选择'>请选择</option>");
			$.each(Data,
				function(i,objs) {//取出list里每一个数组
				$("#csId").append("<option value='"+objs[0]+"'>"+objs[1]+"</option>");//取出数组的值拼接
				});
			});
		}
	}
}
	
	function getQuestionData(up,doubt) {
		var csId = $("#csId").val();
		var url;
		if(doubt=="yes"){
			$("#qtotal").val("0");
			$("#qscore").val("0");
		}
		if (csId != "请选择") {
			url="paper/paper_getTWBycsId?course.csId=" + csId + "&up=" + up;
			$("#questionform").attr("action", url);
			$("#questionform").submit();
		}else{
			alert("请选择科目");
		}
	}
	//获取选择的个数
	function getSelectNum2(a) {
		var qtotal;//题数
		var score;//总分
		var qscore;//每题得分
		qtotal = Number($("#qtotal").val());
		score = Number($("#ptotalScore").val());
		if (a.checked) {
			qtotal += 1;
			$("#qtotal").val(qtotal);
			if (score != 0) {
				qscore = (Math.round((score / qtotal) * 10)) / 10;
				$("#qscore").val(qscore);
			}
		} else if (!a.checked) {
			qtotal -= 1;
			$("#qtotal").val(qtotal);
			if (score != 0) {
				if (qtotal != 0) {
					qscore = (Math.round((score / qtotal) * 10)) / 10;
					$("#qscore").val(qscore);
				} else {
					$("#qscore").val(0);
				}
			}
		}
	}
	//刷新多选框
	function refreshChecks() {
		var obj = document.getElementsByName("writtenTests");
		var num = Number($("#qtotal").val());
		if (num <= 0) {
			return;
		} else {
			var w = "${writtenTests}";
			w = w.replace("[", "");
			w = w.replace("]", "");
			var a = w.split(",");
			for ( var i in a) {
				var wid = a[i].replace(" ", "");
				$("#cbutton")
						.before(
								"<input type='hidden' name='writtenTests' value='"+wid+"'></input>");
				for ( var j in obj) {
					if (wid == obj[j].value) {
						obj[j].checked = true;
					}
				}
			}
		}
	}
	//创建选择试卷
	function creatBySelect() {
		var csId=$("#csId").val();
		var url;
		if(csId!="请选择"){
			url="paper/paper_createBySelect?course.csId="+csId;
			$("#questionform").attr("action",url);
			$("#questionform").submit();
		}else{
			alert("请选择科目");
		}
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
<body onload="refreshChecks()">
	<div align="center">
		&nbsp;&nbsp;
		<s:select label="方向" list="{'请选择','SCME','SCCE'}" id="majorSelect"
			name="course.major" class="sele" onchange="getCourseJson()" />
		<s:select label="阶段" list="{'请选择','G1','G2','G3'}" id="stageSelect"
			name="course.stage" class="sele" onchange="getCourseJson()" />
		科目:&nbsp;<select id="csId" name="course.csId" class="sele2"
			onchange="getQuestionData(1,'yes')">
			<option value="${course.csName == null ? '' : course.csId  }">${course.csName == null ? '请选择' : course.csName }
			</option>
		</select>&emsp;&emsp;&ensp;&ensp;&nbsp;&nbsp;&nbsp;&nbsp; <br />
		<form id="questionform" action="" method="post">
			<s:textfield label="标题" id="pname" name="paper.pname" class="w" autocomplete="off" />
			<br />
			<s:textfield label="总分" id="ptotalScore" name="paper.ptotalScore"
				oninput="changeScore(this)" class="w2" autocomplete="off" />
			&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&ensp;
			<s:textfield label="考试时长" id="ptime" name="paper.ptime" class="w2" autocomplete="off" />
			<br /> &nbsp;
			<s:textfield label="已选题数" id="qtotal" name="paper.qtotal"
				readonly="true" class="w3" />
			&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;
			<s:textfield label="每题分数" id="qscore" name="paper.qscore"
				readonly="true" class="w3" />
			&emsp;&emsp;&nbsp;&nbsp;
			<div>
				<div style="width: 700px; height: 80%; overflow: auto;">
					<table width="100%" height="125%" cellpadding="0" cellspacing="0"
						id="datatable" style="text-align: center;">
						<!-- 把科目下的笔试题封装成PageBean,每页 次显示8条,在table里分页显示 -->
						<s:iterator value="pb.data" var="written" status="status">
							<tr height="30px">
								<td>${written.qid }:<input type="checkbox" id="checks"
									value="${written.qid }" name="writtenTests"
									onchange="getSelectNum2(this)" />
								</td>
								<td>${status.count }</td>
								<td>${written.qtype }</td>
								<td>${written.degree }</td>
								<td>${written.chapter }</td>
								<td>${written.qtitle }</td>
							</tr>
						</s:iterator>
						<tr height="30px">
							<td colspan="6" style="text-align: center;">第${pb.p }页,共${pb.pagetotal }页&emsp;
								<a href="javascript:getQuestionData(1,'no')">第一页</a> <a
								href="javascript:getQuestionData(${pb.upperpage },'no')">上一页</a>
								<a href="javascript:getQuestionData(${pb.nextpage },'no')">下一页</a>
								<a href="javascript:getQuestionData(${pb.pagetotal },'no')">末页</a>
							</td>
						</tr>
					</table>
				</div>
				<input type="hidden" name="paper.ptype" value="笔试" /> <input
					type="hidden" name="paper.pstate" value="未开考" /> <input
					type="button" id="cbutton" onclick="creatBySelect()" value="创建试卷"></input>&ensp;
				<input type="reset" value="取消"></input>
				&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
			</div>
		</form>
	</div>
</body>
</html>