<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>考勤查询</title>
<link rel="stylesheet" href="${base}/js/artDialog/css/ui-dialog.css">
<script src="${base}/js/artDialog/lib/jquery-1.10.2.js" type="text/javascript"></script> 
<script src="${base}/js/artDialog/dist/dialog-min.js" type="text/javascript"></script> 
<script src="${base}/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script> 
</head>
<style>
#list{
    list-style:none;
}
</style>
<body>
<ul id="list">
	<li>
		<h3>v6(2016-01-28)</h3>
		<ol>
			<li>考勤日历显示风格修改，用绿色显示正常打卡，用红色显示异常打卡</li>
		</ol>
	</li>
	<li>
		<h3>v5(2016-01-27)</h3>
		<ol>
			<li>修改为从原始纪录表里读数据，避免有时数据不准确的问题</li>
			<li>修改人员列表显示bug</li>
		</ol>
	</li>
	<li>
		<h3>v4(2015-05-27)</h3>
		<ol>
			<li>解决管理员修改考勤密码导致不能查询使用的问题</li>
			<li>将帐号密码及访问地址修改为配置文件变量</li>
		</ol>
	</li>
	<li>
		<h3>v3(2014-10-22)</h3>
		<ol>
			<li>修改日历月份显示的问题</li>
			<li>修改结果弹出窗的高度适应问题</li>
		</ol>
	</li>
	<li>
		<h3>v2(2014-09-01)</h3>
		<ol>
			<li>增加回车查询功能</li>
			<li>增加查询数量为1个时，默认进行考勤数据获取</li>
			<li>修改月份选择、显示功能</li>
			<li>增加更新日志查询功能</li>
			<li>修改考勤数据的弹窗显示</li>
			<li>修改显示风格</li>
		</ol>
	</li>
	<li>
		<h3>v1</h3>
		<ol>
			<li>系统建立</li>
		</ol>
	</li>
</ul>
</body>
</html>