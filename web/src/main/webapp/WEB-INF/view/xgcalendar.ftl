<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>考勤查询-${time}</title>
  <link href="${base}/js/xgcalendar/theme/Default/main.css"" rel="stylesheet" type="text/css" />
  <link href="${base}/js/xgcalendar/theme/Default/dailog.css" rel="stylesheet" type="text/css" />
  <link href="${base}/js/xgcalendar/theme/Default/calendar.css" rel="stylesheet" type="text/css" /> 
  <link href="${base}/js/xgcalendar/theme/Default/dp.css" rel="stylesheet" type="text/css" />   
  <link href="${base}/js/xgcalendar/theme/Default/alert.css" rel="stylesheet" type="text/css" />     
  <link href="${base}/js/xgcalendar/theme/Shared/blackbird.css" rel="stylesheet" type="text/css" />
  <script src="${base}/js/xgcalendar/javascripts/jquery.min.js" type="text/javascript"></script>  
  <script src="${base}/js/xgcalendar/javascripts/Common.js" type="text/javascript"></script>    
    <script src="${base}/js/xgcalendar/javascripts/lib/blackbird.js" type="text/javascript"></script> 
    <script src="${base}/js/xgcalendar/javascripts/Plugins/datepicker_lang_zh_CN.js" type="text/javascript"></script>     
    <script src="${base}/js/xgcalendar/javascripts/Plugins/jquery.datepicker.js" type="text/javascript"></script>
    <script src="${base}/js/xgcalendar/javascripts/Plugins/jquery.alert.js" type="text/javascript"></script>    
    <script src="${base}/js/xgcalendar/javascripts/Plugins/jquery.ifrmdailog.js" defer="defer" type="text/javascript"></script>
    <script src="${base}/js/xgcalendar/javascripts/Plugins/xgcalendar_lang_zh_CN.js" type="text/javascript"></script>  
    <script src="${base}/js/xgcalendar/javascripts/Plugins/xgcalendar.js?v=1.2.0.4" type="text/javascript"></script>  
</head>
<body>
 <div id="xgcalendar">这里是日历控件</div>
 
</body>
 <script type="text/javascript">
 var color=0;
 $(function(){
 	 var op = {
        view: "month", //默认视图，这里是周视图
        theme:1,//默认的主题风格
        autoload:false, //是否在页面加载完毕后自动获取当前视图时间的数据
        showday: new Date(${longList[0]}), //当前视图的显示时间
        ViewCmdhandler:function(){},    //查看的响应事件 
		readonly:true
    };
    var _MH = document.documentElement.clientHeight; //获取页面高度，不同的文档类型需要不同的计算方法，注意示例中使用的doctype 用这个就搞定了
    op.height = _MH-70; //container height;
    var array=[];
    <#--
    <#list longList as item>
    	array.push(["1","打卡",new Date(${item}),new Date(${item}),0,0,1,0,1,"",""]);
    </#list>
    -->
     <#list resultList as item>
    	array.push(["1","${item.title}",new Date(${item.time}),new Date(${item.time}),0,0,1,${item.color},1,"",""]);
    </#list>
    
    op.eventItems =array; //default event data;
    $("#xgcalendar").bcalendar(op);
	
 })

 function getColor(){
 	if(color>21){
 		color=0;
 	}
 	return color++;
 }
           
 </script>

</html>
