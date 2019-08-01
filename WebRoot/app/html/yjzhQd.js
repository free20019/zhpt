require(
		[ "dijit/Dialog", "dgrid/Editor", "dijit/form/Button",
				"dijit/form/DateTextBox", "dijit/form/CheckBox",
				"dijit/form/TimeTextBox", "dijit/form/SimpleTextarea",
				"dgrid/OnDemandGrid", "dijit/form/TextBox",
				"dgrid/extensions/Pagination", "dgrid/Selection",
				"dgrid/Keyboard", "dgrid/extensions/ColumnResizer",
				"app/createAsyncStore", "dstore/Memory",
				"dgrid/extensions/DijitRegistry", "dijit/registry",
				"dojo/dom-style", "dojo/_base/declare", "cbtree/Tree",
				"cbtree/models/ForestStoreModel",
				"dojo/data/ItemFileWriteStore", "dojo/dom-construct",
				"dojo/on", "app/util", "dojo/domReady!" ],
		function(Dialog, Editor, Button, DateTextBox, CheckBox, TimeTextBox,
				SimpleTextarea, Grid, TextBox, Pagination, Selection, Keyboard,
				ColumnResizer, createAsyncStore, Memory, DijitRegistry,
				registry, domStyle, declare, Tree, ForestStoreModel,
				ItemFileWriteStore, dc, on, util) {

			var xhrArgs = {
				url : basePath + "yjzh/sj/get",
				postData : 'postData={"page":1,"pageSize":'+pageSize+'}',
				handleAs : "json",
				load : function(data) {
					console.log(data)
					dojo.byId('qd_sjmiaoshu').innerHTML = data.datas[0].miaoshu == '' ? ''
							: data.datas[0].miaoshu

				},
				error : function(error) {
					targetNode.innerHTML = "An unexpected error occurred: "
							+ error;
				}
			};

			var deferred = dojo.xhrPost(xhrArgs);

			var qdYj = new Button({
				label : "启动预警",
				onClick : function() {
					alert("预警启动成功");
				}
			}, "qd_start_yj").startup();

			var saveYj = new Button({
				label : "保存方案",
				onClick : function() {
					alert("预警方案保存成功");
				}
			}, "qd_save_yj").startup();

			for (var ii = 1; ii < 4; ii++) {
				new CheckBox({
					name : "checkBox",
					value : "yj" + ii,
					checked : false,
					onChange : function(b) {
//						this.get('value')
					}
				}, "qd_checkBox" + ii).startup();
			}
			// end
		});
