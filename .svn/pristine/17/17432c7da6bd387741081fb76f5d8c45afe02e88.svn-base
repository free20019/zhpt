var gwqxglGrid;
var gwqxglButton;
var gwqxglButton1;
var sendMessageConfig;
var key=[];
var array=[];
var ppp;
require([ "dijit/Dialog", "dgrid/Editor", "dijit/form/Button",
		"dijit/form/DateTextBox", "dijit/form/TimeTextBox",
		"dijit/form/SimpleTextarea", "dijit/form/Select",
		"dijit/form/FilteringSelect", "dgrid/OnDemandGrid",
		"dijit/form/TextBox", "app/Pagination", "dgrid/Selection",
		"dgrid/Keyboard", "dgrid/extensions/ColumnResizer",
		"dojo/store/Memory","cbtree/model/TreeStoreModel",
		"app/createAsyncStore", "dstore/Memory","dijit/form/NumberTextBox",
		"dgrid/extensions/DijitRegistry", "dijit/registry", "dojo/dom-style",
		"dojo/_base/declare", "dojo/dom-construct", "dojo/on","dijit/tree/ObjectStoreModel", "cbtree/Tree",
		"cbtree/models/ForestStoreModel", "dojo/data/ItemFileWriteStore", "app/util" ],
	function(Dialog, Editor, Button, DateTextBox, TimeTextBox,
			 SimpleTextarea, Select, FilteringSelect, Grid, TextBox,
			 Pagination, Selection, Keyboard, ColumnResizer,
			 Memory2,TreeStoreModel,
			 createAsyncStore, Memory,NumberTextBox, DijitRegistry, registry, domStyle,
			 declare, dc, on,ObjectStoreModel, Tree,
			 ForestStoreModel, ItemFileWriteStore, util)
 {
    var  store = null ,count = 0;
    dc.place(dc.create("span",{innerHTML:"岗位",style:{"margin-right":"5px"}}),'gwqxgldiv');
    var gw_job= new TextBox({id:"gw_job",style:{"width":"8em","margin-right":"15px"}}).placeAt('gwqxgldiv');
    var queryCondition = {"name":gw_job};
    var btnFlag = 0;
    var gwqxglFrameDialog = new Dialog({id:'gwqxglFrameDialog', title:'添加岗位', style:'height:600px;width:400px;'},'gwqxglFrameDialogDiv');
    dc.place(dc.create("span",{innerHTML:"岗位名:",style:{'width': '60px', "margin-bottom":"10px", "margin-right":"10px", 'text-align': 'right', 'display': 'inline-block','position':'absolute','top':'40px'}}),'gwqxglFrameDialog');
    var tj_job= new TextBox({id:"tj_job",name:"tj_job",placeholder:"请输入岗位",trim:"true",style:{"width":"25em","margin-left":"60px","margin-bottom":"5px"}}).placeAt('gwqxglFrameDialog');
    dc.place(dc.create("br"),'gwqxglFrameDialog');
    dc.place(dc.create("span",{innerHTML:"权限:",style:{'width': '60px', "margin-bottom":"10px", "margin-right":"10px", 'text-align': 'right', 'display': 'inline-block','position':'absolute','top':'65px'}}),'gwqxglFrameDialog');
    var tj_qx= new TextBox({id:"tj_qx",name:"tj_qx",placeholder:"请选择权限",trim:"true",style:{"width":"25em","margin-left":"60px","margin-bottom":"5px"}}).placeAt('gwqxglFrameDialog');
    dc.place(dc.create("br"),'gwqxglFrameDialog');
    dc.place(dc.create("div",{id:"qxgl_xllb",innerHTML:"",style:{'box-shadow':'0px 1px 1px #696969','width': '380px','height': '470px','background': ' #ffffff' ,'overflow': 'hidden' ,"margin-bottom":"10px","border-top":"1px solid rgba(105,105,105,0.25)", "margin":" 0px 10px", 'text-align': 'left', 'display': 'inline-block','position':'relative','top':'-65px'}}),'gwqxglFrameDialog');
    dc.place(dc.create("br"),'gwqxglFrameDialog');
    var updateId = new TextBox({id:"gwqxglId",name:"gwqxglId",style:{"display": "none"}}).placeAt('gwqxglFrameDialog');
    var updateIds = new TextBox({id:"gwqxglIds",name:"gwqxglIds",style:{"display": "none"}}).placeAt('gwqxglFrameDialog');
    
    gwqxglButton = new Button({
        label: "保存",
        style: {"position":"relative","top":"480px"},
        onClick: function(){  
        	var oo = gwqxglFrameDialog.getValues();
        	if (btnFlag == 1) {
        		dojo.xhrPost({
    				url : basePath + 'qxgl/addGw',
//    				url:"http://localhost:8080/zhpt/qxgl/addGw",
    				postData : 'postData=' +  dojo.toJson(oo),
    				handleAs : "json",
    				load : function(data) {
    					alert(data.info);
    					gwqxglFrameDialog.hide();
    					deferred = dojo.xhrPost(xhrArgs);
    				},
    				error : function(error) {
    					targetNode.innerHTML = "An unexpected error occurred: " + error;
    				}
    			});
        	}else{
        		dojo.xhrPost({
    				url : basePath + 'qxgl/editGw',
//    				url:"http://localhost:8080/zhpt/qxgl/editGw",
    				postData : 'postData=' +  dojo.toJson(oo),
    				handleAs : "json",
    				load : function(data) {
    					alert(data.info);
    					gwqxglFrameDialog.hide();
    					deferred = dojo.xhrPost(xhrArgs);
    				},
    				error : function(error) {
    					targetNode.innerHTML = "An unexpected error occurred: " + error;
    				}
    			});
        	}
        }
    }).placeAt('gwqxglFrameDialog');
    gwqxglButton1=new Button({
        label: "取消",
        style: {"position":"relative","top":"480px"},
        onClick: function(){
        	gwqxglFrameDialog.hide();
        }
    }).placeAt('gwqxglFrameDialog');
    
    var gwqxglbutton=new Button({
        label: "查询",
        onClick: function(){
        	this.showWait();
			var postData = {"page":1,"pageSize":30};
			for(var o in queryCondition){
				postData[o] = queryCondition[o].value;
			}
			dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
        }
    }).placeAt('gwqxgldiv');

    new Button({
        label: "删除",
        onClick: function(){
        	if(window.confirm("确定删除该岗位吗？")){
				dojo.forEach(gwqxglGrid.collection.data, function(item,index) {
					if (gwqxglGrid.isSelected(item)) {
						dojo.xhrPost({
							url : basePath + 'qxgl/delGw',
//							url:"http://localhost:8080/zhpt/qxgl/delGw",
							postData : "id="+item.ID,
							handleAs : "json",
							load : function(data) {
								console.log(data);
								alert(data.info);
								deferred = dojo.xhrPost(xhrArgs);
							}
						});
					}
				});
			}
        }
    }).placeAt('gwqxgldiv');   
    new Button({
        label: "添加",
        onClick: function(){
        	gwqxglFrameDialog.show();
            document.getElementById('gwqxglFrameDialog_title').innerText = '添加';
            document.getElementById('dijit_form_Button_6_label').innerText = '添加';
            gwqxglFrameDialog.reset();
            btnFlag=1;
            if(fstree){
				fstree.destroy();
			}
			gettree();
        }
    }).placeAt('gwqxgldiv');

    
    new Button({
        label: "修改",
        onClick: function(){
        	var hs=0;
			dojo.forEach(gwqxglGrid.collection.data, function(item,index) { if (gwqxglGrid.isSelected(item)) hs++; });
			if(hs==0){
				alert("请选择一行进行修改");
			}else if(hs>1){
				alert("只能选择一行进行修改");
			}else{
				gwqxglFrameDialog.show().then(function() {
					dojo.forEach(gwqxglGrid.collection.data, function(item, index) {
						if (gwqxglGrid.isSelected(item)) {
									document.getElementById('gwqxglFrameDialog_title').innerText = '修改';
									document.getElementById('dijit_form_Button_6_label').innerText = '修改';
									console.log(item)
									dojo.byId('tj_job').value = item.job;
									dojo.byId('tj_qx').value = item.competenceid;
									dijit.byId('gwqxglIds').setValue(item.QXID);
									console.log(item.QXID);
									btnFlag = 2;
									key=item.COMPETENCE;
									if(fstree){
										fstree.destroy();
									}
									gettree(key);
						}
					})
				});
			}
        }
    }).placeAt('gwqxgldiv');

    var columns = [
        {field:'dojoId',label : "序号"}, // give column a custom name
        {field:'job',
            label : "岗位"
        },{field:'competenceid',
            label : "岗位权限"
        }
    ];
    var a = [];
	var b;
	function gettree(obj){
    dojo.xhrPost({
    	url : basePath + 'qxgl/gwTree',
//		url:"http://localhost:8080/zhpt/qxgl/gwTree",
		postData : 'postData={"ids":"'+obj+'"}',
		handleAs : "json",
		load : function(data) {
			var storeTree = new Memory2( { data: dojo.clone(data) });
			var model = new TreeStoreModel({
				store : storeTree,
				query : {
					parent : ''
				}
			});
			fstree = new Tree({
				model : model,
				branchIcons : true,
				style : {
					"height" : "470px"
				},
				branchReadOnly : false,
				checkBoxes : true,
				nodeIcons : true
			}).placeAt('qxgl_xllb', 'last');

			fstree.expandAll(); // 是否刚打开页面展开所有标签
			dojo.connect(fstree, "onCheckBoxClick", model,
					checkBoxClicked);
		}
	});
	}
    function checkBoxClicked(item, nodeWidget, evt) {
    	a = [];
    	b = fstree;
    	getNode(b);
    	var checktrue = "";
    	var ids = "";
    	for(var i=0;i<a.length;i++){
    		   var no = a[i].item;
    		   if((no.checked==true||no.checked=="mixed")&&no.id!="d124"){
    			   checktrue += no.name+",";
    			   ids += no.id+",";
    		   }
    		}
    	dojo.byId('tj_qx').value=checktrue.substr(0,checktrue.length-1);
    	dojo.byId('gwqxglId').value=ids.substr(0,ids.length-1);
	}

    function getNode(b){
    	var ns = b.getChildren();
    	for(var i=0;i<ns.length;i++){
    		a.push(ns[i]);
    		getNode(ns[i]);
    	}
    }


    var xhrArgs = {
        url : basePath + "qxgl/qxglcheckgw",
//    		url:"http://localhost:8080/zhpt/qxgl/qxglcheckgw",
        postData : 'postData={"page":1,"pageSize":30}',
        handleAs : "json",
        load : function(data) {
            gwqxglbutton.hideWait();
            console.log(data.count)
            gwqxglGrid.totalCount = data.count;
            for(var i=0; i<data.datas.length;  i++){
                data.datas[i] = dojo.mixin({ dojoId: i+1 }, data.datas[i]);
            }
            store = new Memory({ data: {
                identifier: 'dojoId',
                label: 'dojoId',
                items: data.datas
            } });
            gwqxglGrid.set('collection',store);
            gwqxglGrid.pagination.refresh(data.datas.length);
        },
    };
    var CustomGrid = declare([Grid, Keyboard ,ColumnResizer,Selection]);
    gwqxglGrid= new CustomGrid({
        totalCount : 0,
        columns: columns,
        pagination:null
    },"gwqxgltable");
    gwqxglGrid.refresh2 = function(){
        dojo.xhrPost(xhrArgs);
    }
//			dojo.xhrPost(xhrArgs);

    var pageii = new Pagination(gwqxglGrid,xhrArgs,queryCondition,pageSize);

    dc.place(pageii.first(),'gwqxgltable','after');

});
