var yhqxglGrid;
var gwlx;
require([ "app/util","dgrid/Editor","dijit/form/Button" ,"dijit/form/DateTextBox","dijit/form/FilteringSelect","dojo/store/Memory","dijit/form/Select"
    ,"dijit/Dialog", "dgrid/OnDemandGrid","dijit/form/TextBox","app/Pagination"
    ,"dgrid/Selection", "dgrid/Keyboard","dgrid/extensions/ColumnResizer"
    ,"app/createAsyncStore","dstore/Memory","dgrid/extensions/DijitRegistry"
    ,"dijit/registry", "dojo/dom-style","dojo/_base/declare",
    "dojo/dom-construct","app/createSyncStore", "dojo/domReady!"], function(util,Editor, Button,DateTextBox,FilteringSelect,_Memory,Select,Dialog,Grid,TextBox
    , Pagination,Selection,Keyboard,ColumnResizer,createAsyncStore,Memory,DijitRegistry
    ,registry, domStyle,declare, dc,createSyncStore ) {
    var  store = null ,count = 0;
    dc.place(dc.create("span",{innerHTML:"角色",style:{"margin-right":"5px"}}),'yhqxgldiv');
    var js_name= new TextBox({id:"js_name",style:{"width":"8em","margin-right":"15px"}}).placeAt('yhqxgldiv');
    var queryCondition = {"name":js_name};

    var yhqxglFrameDialog = new Dialog({id:'yhqxglFrameDialog', title:'添加角色', style:'height:190px;width:300px;'},'yhqxglFrameDialogDiv');
    var yhqxglbutton=new Button({
        label: "查询",
        onClick: function(){
        	this.showWait();
			var postData = {"page":1,"pageSize":30};
			for(var o in queryCondition){
				postData[o] = queryCondition[o].value;
			}
			dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
        }
    }).placeAt('yhqxgldiv');

    new Button({
        label: "删除",
        onClick: function(){
        	if(window.confirm("确定删除该角色吗？")){
				dojo.forEach(yhqxglGrid.collection.data, function(item,index) {
					if (yhqxglGrid.isSelected(item)) {
						dojo.xhrPost({
							url:"http://localhost:8080/zhpt/qxgl/delJs",
							postData : "id="+item.ID,
							handleAs : "json",
							load : function(data) {
								alert(data.info);
								deferred = dojo.xhrPost(xhrArgs);
							}
						});
					}
				});
			}
        }
    }).placeAt('yhqxgldiv');
    
    

    var btnFlag = 0;
    //添加、修改弹框
    dc.place(dc.create("span",{innerHTML:"角色姓名:",style:{'width': '60px', "margin-bottom":"10px", "margin-right":"10px", 'text-align': 'right', 'display': 'inline-block','position':'absolute','top':'40px'}}),'yhqxglFrameDialog');
    var name= new TextBox({id:"name",name:"name",placeholder:"请输入姓名",trim:"true",style:{"width":"18em","margin-left":"60px","margin-bottom":"5px"}}).placeAt('yhqxglFrameDialog');
    dc.place(dc.create("br"),'yhqxglFrameDialog');
    dc.place(dc.create("span",{innerHTML:"用户名:",style:{'width': '60px', "margin-bottom":"10px", "margin-right":"10px", 'text-align': 'right', 'display': 'inline-block','position':'absolute','top':'65px'}}),'yhqxglFrameDialog');
    var username= new TextBox({id:"username",name:"username",placeholder:"请输入用户名",trim:"true",style:{"width":"18em","margin-left":"60px","margin-bottom":"5px"}}).placeAt('yhqxglFrameDialog');
    dc.place(dc.create("br"),'yhqxglFrameDialog');
    dc.place(dc.create("span",{innerHTML:"密码:",style:{'width': '60px', "margin-bottom":"10px", "margin-right":"10px", 'text-align': 'right', 'display': 'inline-block','position':'absolute','top':'90px'}}),'yhqxglFrameDialog');
    var password= new TextBox({id:"password",name:"password",trim:"true",placeholder:"请输入密码",style:{"width":"18em","margin-left":"60px","margin-bottom":"5px"}}).placeAt('yhqxglFrameDialog');
    dc.place(dc.create("br"),'yhqxglFrameDialog');
    var jobStore = new _Memory({data: [{name:"管理员", id:"0"}, {name:"用户", id:"1"}]});
    dc.place(dc.create("span",{innerHTML:"岗位:",style:{'width': '60px', "margin-bottom":"10px", "margin-right":"10px", 'text-align': 'right', 'display':'inline-block','position':'absolute','top':'117px'}}),'yhqxglFrameDialog');
    var job= new FilteringSelect({id:"job",trim:"true",name: 'job', placeholder:"请选择岗位",store: jobStore,searchAttr: 'name',style:{"width":"18em","margin-left":"60px","margin-bottom":"5px"}}).placeAt('yhqxglFrameDialog');
    dc.place(dc.create("br"),'yhqxglFrameDialog');
    
    var updateId = new TextBox({id:"yhqxglId",name:"id",style:{"display": "none"}}).placeAt('yhqxglFrameDialog');
    var btnFlag = 0;
    var xhrArgsgw = {
          url : basePath + "qxgl/gwxl",
//      		url:"http://localhost:8080/zhpt/qxgl/gwxl",
      		postData : '',
      		 handleAs : "json",
      	        load : function(data) {
      	        	$("#job").empty();
      	        	dijit.byId("job").set('store',new dojo.store.Memory(data));
      	        }
    }
    dojo.xhrPost(xhrArgsgw);

    var yhqxglButton = new Button({
        label: "保存",
        onClick: function(){
        	var oo = yhqxglFrameDialog.getValues();
        	oo.job=dojo.byId('job').value;
        	console.log(oo)
        	if (btnFlag == 1) {
        		dojo.xhrPost({
    				url : basePath + 'qxgl/addJs',
//    				url:"http://localhost:8080/zhpt/qxgl/addJs",
    				postData : 'postData=' +  dojo.toJson(oo),
    				handleAs : "json",
    				load : function(data) {
    					alert(data.info);
    					yhqxglFrameDialog.hide();
    					deferred = dojo.xhrPost(xhrArgs);
    				},
    				error : function(error) {
    					targetNode.innerHTML = "An unexpected error occurred: " + error;
    				}
    			});
        	}else{
        		dojo.xhrPost({
    				url : basePath + 'qxgl/editJs',
//    				url:"http://localhost:8080/zhpt/qxgl/editJs",
    				postData : 'postData=' +  dojo.toJson(oo),
    				handleAs : "json",
    				load : function(data) {
    					alert(data.info);
    					yhqxglFrameDialog.hide();
    					deferred = dojo.xhrPost(xhrArgs);
    				},
    				error : function(error) {
    					targetNode.innerHTML = "An unexpected error occurred: " + error;
    				}
    			});
        	}
        }
    }).placeAt('yhqxglFrameDialog');
    new Button({
        label: "取消",
        onClick: function(){
            yhqxglFrameDialog.hide();
        }
    }).placeAt('yhqxglFrameDialog');

    new Button({
        label: "添加",
        onClick: function(){
        	yhqxglFrameDialog.show();
            document.getElementById('yhqxglFrameDialog_title').innerText = '添加';
            document.getElementById('dijit_form_Button_2_label').innerText = '添加';
            yhqxglFrameDialog.reset();
            btnFlag=1;
        }
    }).placeAt('yhqxgldiv');

    new Button({
        label: "修改",
        onClick: function(){
        	var hs=0;
			dojo.forEach(yhqxglGrid.collection.data, function(item,index) { if (yhqxglGrid.isSelected(item)) hs++; });
			if(hs==0){
				alert("请选择一行进行修改");
			}else if(hs>1){
				alert("只能选择一行进行修改");
			}else{
				yhqxglFrameDialog.show().then(function() {
					dojo.forEach(yhqxglGrid.collection.data, function(item, index) {
						if (yhqxglGrid.isSelected(item)) {
									document.getElementById('yhqxglFrameDialog_title').innerText = '修改';
									document.getElementById('dijit_form_Button_2_label').innerText = '修改';
									console.log(item)
									dojo.byId('name').value = item.name;
									dojo.byId('username').value = item.username;
									dojo.byId('job').value = item.job;
									dojo.byId('password').value = '';
									dijit.byId('yhqxglId').setValue(item.QXID);
									btnFlag = 2;
						}
					})
				});
			}
        }
    }).placeAt('yhqxgldiv');
   
    var columns = [
        {field:'dojoId',label : "序号"}, // give column a custom name
        {field:'name',
            label : "角色姓名"
        },{field:'username',
            label : "用户名"
        },{field:'job',
            label : "岗位"
        },{field:'competenceid',
            label : "权限"
        }
    ];


//    var js_name=dojo.byId("js_name").value;
    var xhrArgs = {
        url : basePath + "qxgl/qxglcheckjs",
//    		url:"http://localhost:8080/zhpt/qxgl/qxglcheckjs",
    		postData : 'postData={"page":1,"pageSize":30}',
    		 handleAs : "json",
    	        load : function(data) {
    	            yhqxglbutton.hideWait();
    	            console.log(data.count)
    	            yhqxglGrid.totalCount = data.count;
    	            for(var i=0; i<data.datas.length;  i++){
    	                data.datas[i] = dojo.mixin({ dojoId: i+1 }, data.datas[i]);
    	            }
    	            store = new Memory({ data: {
    	                identifier: 'dojoId',
    	                label: 'dojoId',
    	                items: data.datas
    	            } });
    	            yhqxglGrid.set('collection',store);
    	            yhqxglGrid.pagination.refresh(data.datas.length);
    	        },
    	    };
    	    var CustomGrid = declare([Grid, Keyboard ,ColumnResizer,Selection]);
    	    yhqxglGrid= new CustomGrid({
    	        totalCount : 0,
    	        columns: columns,
    	        pagination:null
    	    },"yhqxgltable");
    	    yhqxglGrid.refresh2 = function(){
    	        dojo.xhrPost(xhrArgs);
    	    }
//    				dojo.xhrPost(xhrArgs);

    	    var pageii = new Pagination(yhqxglGrid,xhrArgs,queryCondition,pageSize);

    	    dc.place(pageii.first(),'yhqxgltable','after');
});