<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>站位</title>
<link rel="stylesheet" href="${base}/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${base}/js/ztree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${base}/js/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${base}/js/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="${base}/js/ztree/js/jquery.ztree.exedit-3.5.js"></script>
<script type="text/javascript" src="${base}/js/json2.js"></script>
<script type="text/javascript">
		var setting = {
			edit: {
				drag: {
					autoExpandTrigger: true,
					prev: dropPrev,
					inner: dropInner,
					next: dropNext
				},
				enable: true,
				showRemoveBtn: false,
				showRenameBtn: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeDrag: beforeDrag,
				beforeDrop: beforeDrop,
				beforeDragOpen: beforeDragOpen,
				onDrag: onDrag,
				onDrop: onDrop,
				onExpand: onExpand,
				onClick: onClick
			}
		};
		var address1={name:"前排", open:false, children: [],childOuter:false,drag:false,isParent:true};
		var address2={name:"中排", open:false, children: [],childOuter:false,drag:false,isParent:true};
		var address3={name:"后排", open:false, children: [],childOuter:false,drag:false,isParent:true};
		var zNodes =[
			address1,address2,address3
		];
		<#list heroList as item>
			<#if item.address == 1>
			  address1.children.push({name:"${item.name}",id:${item.id}});
			 <#elseif item.address == 2>
			  address2.children.push({name:"${item.name}",id:${item.id}});
			  <#elseif item.address == 3>
			  address3.children.push({name:"${item.name}",id:${item.id}});
			</#if>
		</#list>
		function onClick(event,treeId,treeNode,clickFlag){
			var level=treeNode.level;
			ztree.expandNode(treeNode, null, false, false, false);
		}
		function dropPrev(treeId, nodes, targetNode) {
			var pNode = targetNode.getParentNode();
			if (pNode && pNode.dropInner === false) {
				return false;
			} else {
				for (var i=0,l=curDragNodes.length; i<l; i++) {
					var curPNode = curDragNodes[i].getParentNode();
					if (curPNode && curPNode !== targetNode.getParentNode() && curPNode.childOuter === false) {
						return false;
					}
				}
			}
			return true;
		}
		function dropInner(treeId, nodes, targetNode) {
			if (targetNode && targetNode.dropInner === false) {
				return false;
			} else {
				for (var i=0,l=curDragNodes.length; i<l; i++) {
					if (!targetNode && curDragNodes[i].dropRoot === false) {
						return false;
					} else if (curDragNodes[i].parentTId && curDragNodes[i].getParentNode() !== targetNode && curDragNodes[i].getParentNode().childOuter === false) {
						return false;
					}
				}
			}
			return true;
		}
		function dropNext(treeId, nodes, targetNode) {
			var pNode = targetNode.getParentNode();
			if (pNode && pNode.dropInner === false) {
				return false;
			} else {
				for (var i=0,l=curDragNodes.length; i<l; i++) {
					var curPNode = curDragNodes[i].getParentNode();
					if (curPNode && curPNode !== targetNode.getParentNode() && curPNode.childOuter === false) {
						return false;
					}
				}
			}
			return true;
		}

		var log, className = "dark", curDragNodes, autoExpandNode;
		function beforeDrag(treeId, treeNodes) {
			className = (className === "dark" ? "":"dark");
			showLog("[ "+getTime()+" beforeDrag ]&nbsp;&nbsp;&nbsp;&nbsp; drag: " + treeNodes.length + " nodes." );
			for (var i=0,l=treeNodes.length; i<l; i++) {
				if (treeNodes[i].drag === false) {
					curDragNodes = null;
					return false;
				} else if (treeNodes[i].parentTId && treeNodes[i].getParentNode().childDrag === false) {
					curDragNodes = null;
					return false;
				}
			}
			curDragNodes = treeNodes;
			return true;
		}
		function beforeDragOpen(treeId, treeNode) {
			autoExpandNode = treeNode;
			return true;
		}
		function beforeDrop(treeId, treeNodes, targetNode, moveType, isCopy) {
			className = (className === "dark" ? "":"dark");
			showLog("[ "+getTime()+" beforeDrop ]&nbsp;&nbsp;&nbsp;&nbsp; moveType:" + moveType);
			showLog("target: " + (targetNode ? targetNode.name : "root") + "  -- is "+ (isCopy==null? "cancel" : isCopy ? "copy" : "move"));
			return true;
		}
		function onDrag(event, treeId, treeNodes) {
			className = (className === "dark" ? "":"dark");
			showLog("[ "+getTime()+" onDrag ]&nbsp;&nbsp;&nbsp;&nbsp; drag: " + treeNodes.length + " nodes." );
		}
		function onDrop(event, treeId, treeNodes, targetNode, moveType, isCopy) {
			className = (className === "dark" ? "":"dark");
			showLog("[ "+getTime()+" onDrop ]&nbsp;&nbsp;&nbsp;&nbsp; moveType:" + moveType);
			showLog("target: " + (targetNode ? targetNode.name : "root") + "  -- is "+ (isCopy==null? "cancel" : isCopy ? "copy" : "move"))
		}
		function onExpand(event, treeId, treeNode) {
			if (treeNode === autoExpandNode) {
				className = (className === "dark" ? "":"dark");
				showLog("[ "+getTime()+" onExpand ]&nbsp;&nbsp;&nbsp;&nbsp;" + treeNode.name);
			}
		}

		function showLog(str) {
			if (!log) log = $("#log");
			log.append("<li class='"+className+"'>"+str+"</li>");
			if(log.children("li").length > 8) {
				log.get(0).removeChild(log.children("li")[0]);
			}
		}
		function getTime() {
			var now= new Date(),
			h=now.getHours(),
			m=now.getMinutes(),
			s=now.getSeconds(),
			ms=now.getMilliseconds();
			return (h+":"+m+":"+s+ " " +ms);
		}
		
		function setTrigger() {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.setting.edit.drag.autoExpandTrigger = $("#callbackTrigger").attr("checked");
		}
		
		function save(dialog,grid){
			var nodes = ztree.getNodes();
				$.post("${base}/address/save.do",{jsonStr:JSON.stringify(nodes)},function(data){
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
		$(document).ready(function(){
			ztree=$.fn.zTree.init($("#tree"), setting, zNodes);
			$("#callbackTrigger").bind("change", {}, setTrigger);
		});
	</script>
</head>
<body>
<div class="content_wrap">
	<div class="zTreeDemoBackground left">
		<ul id="tree" class="ztree"></ul>
	</div>
</div>
</body>
</html>