<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑</title>
    <link href="${base}/js/ligerui/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="${base}/js/ligerui/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" />
    <script src="${base}/js/ligerui/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
    <script src="${base}/js/ligerui/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>  

    <script src="${base}/js/ligerui/lib/jquery-validation/jquery.validate.min.js"></script>
    <script src="${base}/js/ligerui/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="${base}/js/ligerui/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
<script type="text/javascript">
 function save(dialog,grid,isSaveOther){
        var form =liger.get('form');
        var data = form.getData();
        var arraySuccess=data.success.split(";");
        if(!checkSize(arraySuccess,"胜利阵容")){
        	return;
        };
         var arrayError=data.error.split(";");
        if(!checkSize(arrayError,"失败阵容")){
        	return;
        };
        var percentage=parseInt(data.percentage);
        if(percentage<0||percentage>100){
        	$.ligerDialog.warn('胜率只能在0-100之间');
        }else{
        	var parms={
        	 <#if heroResult??>"tbResult.id":${heroResult.id},</#if>
        		"tbResult.percentage":percentage,
        		"tbResult.success":arraySuccess+"",
        		"tbResult.error":arrayError+""
        	}
        	if(isSaveOther){
        		delete parms["tbResult.id"];
        	}
        	$.post("${base}/result/save.do",parms,function(data){
        		if(data.success){
        			parent.$.ligerDialog.success(data.success);
        			if(grid){
        				grid.reload();
        			}
        			if(dialog){
        				dialog.close();
        			}
        		}else if(data.error){
        			$.ligerDialog.error(data.error);
        		}
        		
        	},"json");
        }
        
 }
 function checkSize(array,type){
 	array.sort(
   	  function(a,b){
   	  	if(parseInt(a)>parseInt(b)){
   	  		return 1;
   	  	}else{
   	  		return -1;
   	  	}
    });
 	var length=array.length;
 	if(length>5){
 		$.ligerDialog.warn(type+'最多只能选择5个英雄,当前已选择'+length+'个');
 		return false;
 	}else if(length==1&&array[0]==""){
 		$.ligerDialog.warn('请选择'+type+'的英雄');
 		return false;
 	}
 	
 	for(var i=0;i<length;i++){
 		var value=array[i];
 		array[i]="["+value+"]";
 	}
 	return true;
 }
 function conditionSearchClick(e){
	 //清空操作
	 var conditionPanel=e.conditionPanel;
	 var $input=$("input",conditionPanel);
	 $input.val("");
	 e.rules=[];
	 e.grid.set("newPage",1);
	 e.grid.loadData($.ligerFilter.getFilterFunction(e.rules));
	 $input.focus();
 }
 function getGridOptions(checkbox)
        {
        	var data={};
        	data.Rows=[];
        	<#list heroList as item>
        	data.Rows.push({name:"${item.name}",id:${item.id}});
        	</#list>
           var options = {
                columns: [ 
                {display: '名字', name: 'name',  minWidth: 180} 
                ], switchPageSizeApplyComboBox: true,
                data: $.extend({}, data),
                //url: '${base}/index/listAllHeor.do', 
                pageSize: 5, 
                pageSizeOptions :[5,10,20,50,100],
                usePager :true,
                dataAction:'local',
                checkbox: checkbox
            };
            return options;
        }
        $(function ()
        { 	
            //创建表单结构 
            form=$("#form").ligerForm({
                inputWidth: 250, labelWidth: 60, space: 10,
                validate : true,
                unSetValidateAttr :true,
                fields: [
                {
                    display: "胜利阵容", name: "success",
                    newline: true, type: "combobox", editor: {
                         selectBoxWidth: 250,
               			 selectBoxHeight: 240,
                        valueField: 'id',
                		textField: 'name',
                		Search:"清空",
                        condition: { fields: [{ name: 'name', label: '名称',width:30,type:'text' }] },
                        conditionSearchClick:conditionSearchClick,
                        grid: getGridOptions(true)
                    }},
             	 {
                    display: "失败阵容", name: "error", 
                    newline: true, type: "combobox", editor: {
                        selectBoxWidth: 250,
               			 selectBoxHeight: 240,
                        valueField: 'id',
               			 textField: 'name',
               			 Search:"清空",
                       condition: { fields: [{ name: 'name', label: '名称',width:30,type:'text' }] },
                       conditionSearchClick:conditionSearchClick,
                         grid: getGridOptions(true)
                    }},
                 { display: "胜率(%)", name: "percentage", newline: true, type: "int" }
                ]
            }); 
            <#if heroResult??>
              form.setData({success:"${heroResult.success}",error:"${heroResult.error}",percentage:${heroResult.percentage}});
            <#else>
               form.setData({percentage:100});
            </#if>
          
        }); 
        
 
 </script>
</head>
<body>
<div>
<div id="form" style="width: 350px; margin: auto;"></div> 
</div>
</body>
</html>