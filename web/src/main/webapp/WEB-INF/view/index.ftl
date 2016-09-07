<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>考勤查询</title>
<link rel="stylesheet" href="${base}/js/artDialog/css/ui-dialog.css">
<script src="${base}/js/artDialog/lib/jquery-1.10.2.js" type="text/javascript"></script> 
<script src="${base}/js/artDialog/dist/dialog-plus-min.js" type="text/javascript"></script>  
<script src="${base}/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script> 
</head>
<script type="text/javascript">
$(function(){
	initMonth();
	initEnter();
	$("#search").click(function(){
		var $list=$("#list");
		var username=$.trim($("#username").val());
		$list.html("");
		var d = dialog({
		    title: '获取数据',
		    width:200
		});
		d.show();
		 $.post("${base}/index/getAllUser.do",{username:username},function(data){
		 		$list.html("");
		 		d.close().remove();
		 		if(data.error){
		 			dialog({
		 				okValue: '确定',
					    content: data.error,
					    ok: function () {}
					}).show();
		 		}
		 		var i=0;
				$(data.userList).each(function(){
				     i++;
					 var title="title='"+this.department+"'";
					 $list.append("<li><span "+title+" onclick='show(\""+this.userid+"\",\""+this.username+"\")'>"+i+"、"+this.username+"</span></li>");
				})
				if(data.userList.length==1){
				     var one=data.userList[0];
					show(one.userid,one.username);
				}
			},"json");
	});
		
})
//初始化月份选择框
function initMonth(){
    var date=new Date();
	var month=date.getMonth();
	month=month+1;//原先的month是从0开始，即0代表1月
	if(month>=0&&month<=9){
		month="0"+month;
	}
	var year=date.getFullYear();
	$("#month").val(month+1);
	var timeValue=year+"年"+month+"月";
	$("#time").val(timeValue);
	
}
function initEnter(){
	$(document).keydown(function(event){
			  if(event.keyCode==13) {
			     $("#search").click();
			  }
	});
}
function show(userid,username){
	var time=$("#time").val();
	var url="${base}/index/showDetailSource.do";
	var title=username+"---"+time;
	//console.log($(document).width()-60);
	var height=$(document).height()-120;
	if(height<580){
		height=580;
	}
	var iframeDialog=dialog({
			title: title+"---获取数据中",
		    url:url+"?time="+time+"&userid="+userid,
		    width:$(document).width()-60,
		    height:height,
		    oniframeload:oniframeload
		}).showModal();
	iframeDialog.temp=title;
	/*var $skip=$("#skip");
	$skip.attr("action",url);
	$("#userid").val(userid);
	$("#timeid").val(time);
	$skip.submit();*/
}
function oniframeload(){
	this.title(this.temp+"---数据获取成功");
}
function about(){
	var iframeDailog=dialog({
			title: "更新日志",
		    width:$(document).width()/2,
		    height:$(document).height()-150,
		    okValue: "关闭",
		    ok: function () {
		       
		    }
		});
	 var $iframe = $("<iframe />");
	 $iframe.attr({
            src: "${base}/about/about.do",
            width: '100%',
            height: '100%',
            allowtransparency: 'yes',
            frameborder: 'no',
            scrolling: 'yes'
        });
      iframeDailog.content($iframe[0]);
      iframeDailog.showModal();
        
	
}
</script> 
<style>
li{
	float: left;
    margin-right: 30px;
    margin-top: 30px;
    width: 100px;
    list-style:none;
    height:20px;
}
li span{
    cursor: pointer;
  
}
li span:hover{
	color:red;
}
#mydiv {
	float:left;
}
</style>
<body>
<div id="mydiv">
姓名：<input id="username" type="text" value="" />
月份：
<input id="time" type="text" onClick="WdatePicker({dateFmt:'yyyy年MM月'})" class="Wdate" readonly />
<input id="search" type="button" value="查询"/>
</div>
<a href="javascript:about();"  style="float: right;">更新日志</a>
<div style="clear:both;">
<ul style="float:left" id="list">
</ul>
<form action="#" id="skip" target="_blank" style="display: none;" method="GET">
	<input type="hidden" id="userid" name="userid" value="" />
	<input type="hidden" id="timeid" name="time" value="" />
</form>
</div>
</body>
</html>