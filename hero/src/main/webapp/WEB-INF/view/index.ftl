<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>阵容克制查询</title>
	<style>
	img {
	  float: left;
	}
	</style>
    <link href="${base}/js/ligerui/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="${base}/js/ligerui/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link href="${base}/js/ligerui/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <script src="${base}/js/ligerui/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
    <script src="${base}/js/ligerui/lib/ligerUI/js/core/base.js" type="text/javascript"></script> 
    <script src="${base}/js/ligerui/lib/ligerUI/js/ligerui.all.js"></script>
    <script type="text/javascript">
    	function initButton(){
    		 button = $("#button").ligerButton(
            {
                click: function ()
                {
                   var value=comboBox.getValue();
                   if(value.length==0){
                   	  grid.set('parms', { ids: null});
	                  grid.reload();
                   }else{
	                   	var array=value.split(";");
	                   if(array.length>5){
	                   	  $.ligerDialog.warn('最多只能选择五个英雄');
	                   }else{
	                   	  array.sort(
	                   	  function(a,b){
	                   	  	if(parseInt(a)>parseInt(b)){
	                   	  		return 1;
	                   	  	}else{
	                   	  		return -1;
	                   	  	}
	                   	  });
	                   	  grid.set('parms', { ids: array+""});
	                   	  grid.reload();
	                   }
                   }
                  
                }
            }
            );
    		
    	}
        function initComboBox(){
             comboBox= $("#txt").ligerComboBox({
                width: width-10,
                slide: false,
               selectBoxWidth: 290,
               selectBoxHeight: 390,
                valueField: 'id',
                textField: 'name',
                Search:"清空",
                grid: getGridOptions(true),
                condition: { fields: [{ name: 'name', label: '名称',width:90,type:'text' }] },
                conditionSearchClick:function(e){
                 var conditionPanel=e.conditionPanel;
                 var $input=$("input",conditionPanel);
				 $input.val("");
				 e.rules=[];
				 e.grid.set("newPage",1);
				 e.grid.set("parms",{});
				 e.grid.reload();
				  $input.focus();
                }
                 
            });
        }
        function initGrid(){
        	 grid = $("#maingrid").ligerGrid({
                columns: [ 
                {display: '胜利阵容', name: 'success', align: 'left', minWidth: 10,
                    render:function(item){
                    	var html="";
                    	$(item.success).each(function(){
                    		html+="<img  style=\"width: "+imgWidth+"px;\" src=\""+this.img+"\"  alt=\""+this.name+"\" title=\""+this.name+"\"  />";
                    	});
                		return html;
                	}
                } ,
                { display: '失败阵容', name: 'error', minWidth: 10 ,
                	 render:function(item){
                    	var html="";
                    	$(item.error).each(function(){
                    		html+="<img  style=\"width: "+imgWidth+"px;\" src=\""+this.img+"\"  alt=\""+this.name+"\" title=\""+this.name+"\"  />";
                    	});
                		return html;
                	}
                
                },
                { display: '胜率', name: 'percentage',width: 30,editor:{type:"int",onChanged:function(){alert(1)},onChanged:function(){alert(2)} } }
                ], url: '${base}/index/listResult.do', pageSize: 20,
                pageParmName :'page.pageNumber' ,pagesizeParmName:'page.pageSize',sortnameParmName :'page.sortName',sortorderParmName :'page.sortType',
                sortName:"percentage",sortOrder:"desc",
                enabledEdit: true,clickToEdit:false, 
                width: '98%', height:'99%', checkbox: false,rownumbers:true,
                 toolbar: {
                    items: [
                    <#if flag="admin">
                    { text: '增加', click: itemAdd, icon: 'add' },
                    { line: true },
                    { text: '修改', click: itemModify, icon: 'modify' },
                    { line: true },
                    { text: '删除', click: itemDelete, icon:'delete' },
                    { text: '站位', click: itemAddress, icon:'discuss' },
                   <#else>
                      { text: '站位', click: itemAddress2, icon:'discuss' },
                   </#if>
                    { text: '关于', click: about, icon:'help' }
                    ]
                },
                fixedCellHeight:false
            });
            
        }
        function itemAdd(item){
        	 var add=$.ligerDialog.open({
	                    url: '${base}/result/resultEdit.do', width: 350,height:390, showMax: true, showToggle: true, showMin: false, isResize: false, modal: true, title: "增加", buttons: [
	                    { text: '确定', onclick: function (item, dialog) {  dialog.frame.save(dialog,grid);} },
	                    { text: '取消', onclick: function (item, dialog) { dialog.close(); } }
	                ]
             });
             //if(height-20>390){
             	//add.max();
            // }
        }
        function itemModify(item){
        		var data=grid.getSelected();
        		if(data){
        			var add=$.ligerDialog.open({
	                    url: '${base}/result/resultEdit.do?id='+data.id, width: 350,height:390, showMax: true, showToggle: true, showMin: false, isResize: false, modal: true, title: "修改", buttons: [
	                    { text: '保存', onclick: function (item, dialog) {  dialog.frame.save(dialog,grid);} },
	                    { text: '另存为', onclick: function (item, dialog) {  dialog.frame.save(dialog,grid,true);} },
	                    { text: '取消', onclick: function (item, dialog) { dialog.close(); } }
	                ]
            	 });		
        		}else{
        			$.ligerDialog.warn("请选择要修改的数据");
        		
        		}
        		
				 	
           
        }
        function itemAddress(item){
        	 var address=$.ligerDialog.open({
	                    url: '${base}/address/address.do', width: 500,height:450, showMax: true, showToggle: true, showMin: false, isResize: false, modal: true, title: "站位", buttons: [
	                    { text: '确定', onclick: function (item, dialog) {  dialog.frame.save(dialog,grid);} },
	                    { text: '取消', onclick: function (item, dialog) { dialog.close(); } }
	                ]
             });
             address.max();
        }
        function itemAddress2(item){
        	 var address=$.ligerDialog.open({
	                    url: '${base}/address/address.do', width: 500,height:450, showMax: true, showToggle: true, showMin: false, isResize: false, modal: true, title: "站位", buttons: [
	                    { text: '关闭', onclick: function (item, dialog) { dialog.close(); } }
	                ]
             });
             address.max();
        }
        function itemDelete(item){
        	var data=grid.getSelected();
        		if(data){
        			$.ligerDialog.confirm('是否确认删除', function (yes) { 
        				if(yes){
								$.post("${base}/result/delete.do",{"tbResult.id":data.id},function(data){
					        		if(data.success){
					        			$.ligerDialog.success(data.success);
					        			if(grid){
					        				grid.reload();
					        			}
					        		}else if(data.error){
					        			$.ligerDialog.error(data.error);
					        		}
			        			},"json");        				
        				}
        			});
        		}else{
        			$.ligerDialog.warn("请选择要删除的数据");
        		}
        }
         function getGridOptions(checkbox)
        {
            var options = {
                columns: [ 
                {display: '名字', name: 'name',  minWidth: 230} 
                ], switchPageSizeApplyComboBox: false,
                url: '${base}/index/listHero.do', 
                pageParmName :'page.pageNumber' ,pagesizeParmName:'page.pageSize',sortnameParmName :'page.sortName',sortorderParmName :'page.sortType',
                pageSize: 10, 
                usePager :true,
                checkbox: checkbox
            };
            return options;
        }
	     function about(item){
	     	var about=$.ligerDialog.open({
	                    url: '${base}/index/about.do', width: null,height:'auto', showMax: true, showToggle: true, showMin: false, isResize: false, modal: true, title: "更新日志", buttons: [
	                    { text: '关闭', onclick: function (item, dialog) { dialog.close(); } }
	                ]
            });
            about.max();
	     }
        $(function ()
        {
           modifyObj={};
           height=$(document).height();
           width=$(document).width();
           imgWidth=(width-120)/10;
           imgWidth=Math.ceil(imgWidth);
           initGrid();
           initComboBox();
           initButton();
        });
    
    </script>
</head>
<body style="">
	<div style="float:left"><input type="text" id="txt" /></div><div> <input type="button" id="button" value="查询"  /></div>
    <br />
	<div id="maingrid" style="margin: 0; padding: 0"></div>
</body>
</html>