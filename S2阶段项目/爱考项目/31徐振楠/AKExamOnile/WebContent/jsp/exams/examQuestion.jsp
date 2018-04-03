<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>examing...</title>
<script src="js/jquery-3.2.1.min.js" type="text/javascript"></script>
<style type="text/css">
.em{
margin-left: 95px;
margin-right: 95px
}
</style>
</head>
<script type="text/javascript">
var timeEr;
//页面加载完成时,创建一map集合
//试题类型Map,通过试题索引+1,取值
var qtypeMap =new Map();
var qtitleMap =new Map();
var optionAMap =new Map();
var optionBMap =new Map();
var optionCMap =new Map();
var optionDMap =new Map();
//通过c:foreach 拿到后台的的list 赋值给var,使用c:foreach主要是因为方便使用EL表达式而更方便
<c:forEach items="${writtenTests}" var="written" varStatus="status" > //varStatus: 排序属性
//这里map的赋值方式是使用.set方式
    qtypeMap.set(${status.index},"${written.qtype}");//这里注意,使用EL表达式取值时,int类型不带"",字符串要带""
    qtitleMap.set(${status.index},"${written.qtitle}");
    optionAMap.set(${status.index},"${written.optionA}");
    optionBMap.set(${status.index},"${written.optionB}");
    optionCMap.set(${status.index},"${written.optionC}");
    optionDMap.set(${status.index},"${written.optionD}");
    //数据完全放入Map后便可以取值了啊
</c:forEach>  
    
function checkDate() {
	var examDate=Date.parse($("#examDate").text());//解析开始时间
	var whenTime=$("#time").text();//解析考试用时
	whenTime=whenTime.replace("考试时长:","");
	whenTime=whenTime.replace("分钟","");
	var endtime=Number(whenTime)*60*1000+examDate;//开始时间加上考试用时等于结束时间
	var time=endtime-new Date().getTime();//结束时间减去现在时间等于间隔时间
	var hours=parseInt((time/1000)/60/60%24);
	var minutes=parseInt((time/1000)/60%60);
	var seconds=parseInt((time/1000)%60);
	hours=checkTime(hours);
		minutes=checkTime(minutes);
			seconds=checkTime(seconds);
	$("#downDate").text("倒计时:"+hours+":"+minutes+":"+seconds);
	//每一秒调用一次倒计时函数
	timeEr=setInterval(function(){ downtime(endtime); }, 1000);//timeEr为此循环的ID值,可以使用clearInterval("ID")传参,结束循环调用!!!
}
	//倒计时方法
	function downtime(endtime) {
		nowtime=new Date().getTime();//当前用时
		var time=endtime-nowtime;//倒计时时间
		var hours=parseInt((time/1000)/60/60%24);
		var minutes=parseInt((time/1000)/60%60);
		var seconds=parseInt((time/1000)%60);
		hours=checkTime(hours);
			minutes=checkTime(minutes);
				seconds=checkTime(seconds);
		timerStr="倒计时:"+hours+":"+minutes+":"+seconds;
		if(time<=0){//如果倒计时结束,则必须要交卷了!
			window.clearInterval(timeEr);
			finishExam();
			return;
		}
		$("#downDate").text(timerStr);
	}
	
	function checkTime(T) {
		if(T>0){
			if(T<10){
				T="0"+T;
			}
		}else{
			T="00";
		}
		return T;
	}

	//获得第一个td
	function checkOne() {
		var examDataTable=document.getElementById("examDataTable");
		var td1=examDataTable.rows[0].cells[0];
		getWritten(td1);
	}
	
	//获得选择的值
	function getCheckedValue() {
		var answer="";
		$("#question_Content > :checked").each(// :checked获得一组单选或多选框中被选中的项
				function() {
					answer+=this.value;
				}
		);
		return answer;
	}
	
	//获得所有单元格中被选中的索引
	function getSel() {
		var trs=document.getElementById("examDataTable").rows;
		var index;
		$.each(trs,function(i,tr) {
			var tds=tr.cells;
			$.each(tds,
					function(i,td) {
						if(td.style.background!=""){
							index=tr.rowIndex*10+td.cellIndex;
							return false;
						}
					});
		});
		return index;
	}
	//CheckBox回显
	function echor(valstr) {
		if(valstr!='*'){//是*的是还没有选择过得
			for(var str of valstr){//如果是多选,将会有两个值,所以要for循环
				//每一个字符都循环一次答案选择,一样的就让选中
				$("#question_Content >:input").each(//这里使用> 使用方法是父节点A下>的指定B名称子节点,只会查询这一级
						function() {
							if(str==this.value){
								this.checked=true;
							}
					}	
				);
			}
		}else{
			return;
		}
	}
	
	//上一题或者下一题
	function selNum(order) {
		var index=getSel();//获得选中单元格的索引
		var tds=document.getElementById("examDataTable").getElementsByTagName("td");//table的单元格数组
		//获得所有单元格中被选中的td里的所有子节点的length,以便根据length取最后一个子节点
		tdl=tds[index].children.length;
		//获得子节点最后一个子元素span标签
		//把被选中td的最后一个子节点的value改为用户答题的答案
		if(getCheckedValue()!=''){
			tds[index].children[tdl-1].innerText=getCheckedValue();//此处声明span标签的value获取和设置的方法是innerText,Text的首字母要大写
			tds[index].children[2].value=getCheckedValue();
		}
		var td;
		if(order=="up"){
			if(index-1<0){
				td=tds[index];
			}else{
				td=tds[index-1];
			}
		}else if(order=="down"){
			if(index+1>tds.length-1){
				td=tds[index];
			}else{
				td=tds[index+1];
			}
		}
		getWritten(td,tds);
	}
	
	
	//用户点击td选择 
	function clicksel(element) {
		var index=getSel();//当前被选中的单元格索引
		var tds=document.getElementById("examDataTable").getElementsByTagName("td");//经鉴定table.cells不能直接取到值,故用ByTagname标签名称来取值
		//获得所有单元格中被选中的td里的所有子节点的length,以便根据length取最后一个子节点
		tdl=tds[index].children.length;
		//把被选中td的最后一个子节点的value改为用户答题的答案
		//getCheckedValue()获得用户选择的值
		if(getCheckedValue()!=''){
			tds[index].children[tdl-1].innerText=getCheckedValue();//此处声明span标签的value获取和设置的方法是innerText,Text的首字母要大写
			tds[index].children[2].value=getCheckedValue();
		}
		getWritten(element,tds);
	}
	
	//根据传进来的td元素获取试题
	function getWritten(element,tds) {
		//首先循环td,把所有的td的背景色设置为默认
		$.each(tds,
			function() {
				this.style.background="";
			}
		);
		//然后拿到传进来的td的首个子元素的value也就是试题qid
		var num=Number(element.children[0].value);
		var htmlStr="<span>"+qtitleMap.get(num)+"</span><br/>";
		if(qtypeMap.get(num)=='单选'){
			htmlStr+='<div id="question_Content"><input type="radio" name="answers" value="'+optionAMap.get(num)+'" />'+optionAMap.get(num)+'<br/>'+
			'<input type="radio" name="answers" value="'+optionBMap.get(num)+'" />'+optionBMap.get(num)+'<br/>'+
			'<input type="radio" name="answers" value="'+optionCMap.get(num)+'" />'+optionCMap.get(num)+'<br/>'+
			'<input type="radio" name="answers" value="'+optionDMap.get(num)+'" />'+optionDMap.get(num)+'</div>';
		}else{
			htmlStr+='<div id="question_Content"><input type="checkbox" name="answers" value="'+optionAMap.get(num)+'" />'+optionAMap.get(num)+'<br/>'+
			'<input type="checkbox" name="answers" value="'+optionBMap.get(num)+'" />'+optionBMap.get(num)+'<br/>'+
			'<input type="checkbox" name="answers" value="'+optionCMap.get(num)+'" />'+optionCMap.get(num)+'<br/>'+
			'<input type="checkbox" name="answers" value="'+optionDMap.get(num)+'" />'+optionDMap.get(num)+'</div>';
		}
		//然后根据试题qid,通过JSON获得该道试题数据
		//然后把该道试题数据显示在div内容栏里
		//然后让给td被选中
		document.getElementById("content").innerHTML=htmlStr;//先写好Html
		var tdl=element.children.length;//拿到这个td所有的孩子的length
		var valstr=element.children[tdl-1].innerText;//因为span标签的设置和取值方式是innerText
		//判断下一个td是否已经是选择过,有答案的,如果有,让其回显
		echor(valstr);
		//让这个特定被选中
		element.style.background="#FFCC00";
		ajaxformSubmit()
	}
	
	//ajax异步提交表单
	function ajaxformSubmit() {
		$.ajax({ 
			url: "exam/exam_questionSaveSession",
			type: "post",
			data: $("#examForm").serialize(),
			datatype: "json",
			cache:true
		});
	}
	
	//完成考试
	function finishExam() {
		var examDate=Date.parse($("#examDate").text());//解析开始时间
		var whenTime=$("#time").text();//解析考试用时
		whenTime=whenTime.replace("考试时长:","");
		whenTime=whenTime.replace("分钟","");
		var whenTimeSecond=Number(whenTime)*60*1000;
		var endtime=whenTimeSecond+examDate;//开始时间加上考试用时等于结束时间
		var nowTime=new Date().getTime();
		var url="exam/exam_examEnd?examEndDate="+endtime;
		$("#examForm").attr("action",url);
		$("#examForm").attr("method","post");
		alert(document.getElementById("examForm").method);
		//设置考试
		if(endtime-nowTime>(whenTimeSecond/3)){//如果考试结束时间减去现在时间得出的离考试结束的差距时间还要大于考试规定用时的三分之一
			//不能交卷
			var time=(whenTimeSecond/3*2)/1000/60;
			alert("开考"+(Math.round(time*10))/10+"分钟后才能交卷");
			return;
		}else if(endtime-nowTime<whenTimeSecond/3&&endtime-nowTime>0){//如果离考试结束时间大于考试用时的三分之一,并且该时间大于0
			//允许交卷
			var boo=confirm("现在可以开始交卷,确定交卷吗?");
				if(boo==true){
					$("#examForm").submit();
				}else{
					return;
				}
		}else{				//考试时间到
			//必须交卷
			timerStr=$("#downDate").text();
			timerStr=timerStr.replace("倒计时:","");
			alert("考试结束!  ["+timerStr+"]");
			$("#examForm").submit();
		}
	}
</script>
<body onload="checkDate(),checkOne()">
<div>
<span id="" class="em">科目:${paper.course.csName }</span><span id="examDate" class="em">开考时间:${examDate[0] }</span><span id="time"  class="em">考试时长:${paper.ptime }分钟</span><br><br>
	<span id="" class="em">总分:${paper.ptotalScore }</span><span id="" class="em">总题数:${up }</span> <span id="downDate" class="em">倒计时:</span><input type="button" value="完成考试" onclick="finishExam()">
	</div>
	<hr width="100%px">
	<div style="width: 100%px;height: 250px;">
	<form id="examForm">
	<input type="hidden" name="paper.pid" id="pid" value="${paper.pid }">
	<input type="hidden" name="paper.qscore" value="${paper.qscore }">
	<table id="examDataTable" style="text-align: center;border-collapse:collapse;" width="100%px" border="1" bordercolor=#000000>
	<tr height="50px">
	<s:iterator value="writtenTests" var="wr" status="status">
	
	<td width="10%px" onclick="clicksel(this)">
	<input type="hidden" value="${status.count-1 }">
	<input type="hidden" name="qids" value="${wr.qid }">
	<input type="hidden" name="answers" value=${answers[0]!=null ? answers[status.index] : "*" }>
	${status.count }<br/>
	<span><font color="red">${answers[0]!=null ? answers[status.index] : "*" }</font></span>
	</td>
	<s:if test="#status.count%10==0">
	</tr>
	<tr height="50px">
	</s:if>
	</s:iterator>
	</tr>
	</table>
	</form>
	</div>
	<hr width="100%px">
	<div id="content">
	
	</div>
	<input type="button" value="上一题" onclick="selNum('up')" /><input type="button" value="下一题" onclick="selNum('down')" />
	
</body>
</html>