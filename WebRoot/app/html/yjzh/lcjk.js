var grid = null,getCxmx;
require(
			imports_1,
			function(TabContainer, ContentPane, Dialog, Editor, Button,
					DateTextBox, CheckBox, TimeTextBox, SimpleTextarea, Grid,
					TextBox, Pagination, Selection, Keyboard, ColumnResizer,
					createAsyncStore, Memory, DijitRegistry, registry,
					domStyle, declare, Tree, ForestStoreModel,
					ItemFileWriteStore, dc, on, util) {
				
				var gemo = dojo.position('svgWraper');
				var w = gemo.w-100;
				var h = 600;
				var text_margin_left=2,text_font_size = 12,text_height = 18, count = 76,
					dataset=[],n_h=80,n_w=150,count_x,count_y,n_l1=50,n_l2=50;
				var count_y_h =n_h+n_l2//层高
				;
				Node = function(x, y, rect_x,rect_y,d) {
					this.x = x;
					this.y = y;
					this.l1 = n_l1;
					this.l2 = n_l2;
					this.w = n_w;
					this.h = n_h;
					this.d = d;
					this.rect_x=rect_x;
					this.rect_y=rect_y;
					this.vehic_count=20;
					this.datetime='13:03:22';
					this.address ='青山服务区';
					this.tc = '';
					this.id = ''
				}
				//Data
				
	dojo.xhrPost({
		url : basePath + "yjzh/lcjkCx",
		handleAs : "json",
		load : function(data) {
			console.log(data)
			if(data == null || data.length == 0){
				return;
			}
			count = data.length;
			count_x = Math.floor(w/(n_l1+n_w))
			count_y=count/count_x
			if(parseInt(count_y) != count_y){
				count_y=parseInt(count_y) +1;
			}
			h = count_y * count_y_h;
			var l2r = 1, t2b =2, r2l = 3;//左右，上下，右左
			for(var i=0;i<count_y;i++){ //层，纵
				for(var j=0;j<count_x;j++){ // 横
					if((i)*count_x+(j+1)>count){break;}
					if(i==0){
						var line_x = j*(n_l1+n_w),line_y = (i+1)*count_y_h-count_y_h/2;
						console.log(i,j,line_y)
						var rect_x = line_x+n_l1, rect_y = line_y-n_h/2
						dataset.push(new Node(line_x, line_y, rect_x, rect_y, l2r))
					}
					if(i>0 && i%2==1){//右到左
						if(j==0){//从第二层开始，每层的第一个节点是自上往下的画法
							var lastNode = dataset[dataset.length-1];
							var rect_x = lastNode.rect_x, rect_y = lastNode.rect_y + lastNode.h +lastNode.l2;
							var line_x = rect_x + n_w/2;
							var line_y = rect_y - n_l2;
							dataset.push(new Node(line_x, line_y, rect_x, rect_y, t2b))
						}else{
							var lastNode = dataset[dataset.length-1];
							var rect_x = lastNode.rect_x - lastNode.l1 - lastNode.w , rect_y = lastNode.rect_y;
							var line_x = rect_x  + n_w;
							var line_y = rect_y + n_h/2;
							dataset.push(new Node(line_x, line_y, rect_x, rect_y, r2l))
						}
					}
					if(i>0 && i%2==0){//左到右
						if(j==0){ 
							var line_x =  n_l1 + n_w/2;
							var line_y = (i)*count_y_h-count_y_h/2 + n_h/2;
							var rect_x = line_x - n_w/2 , rect_y = line_y + n_l2;
							dataset.push(new Node(line_x, line_y, rect_x, rect_y, t2b))
						}else{
							var line_x = j*(n_l1+n_w),line_y = (i+1)*count_y_h-count_y_h/2;
							var rect_x = line_x+n_l1, rect_y = line_y-n_h/2
							dataset.push(new Node(line_x, line_y, rect_x, rect_y, l2r))
						}
					}
				}
			}
			for(var i=0;i < dataset.length ; i++){
				dataset[i].address = data[i].ADDRESS;
				dataset[i].vehic_count = data[i].VEHI_NUMBER;
				dataset[i].datetime = data[i].CREATE_TIME;
				dataset[i].tc = data[i].CAR_NO;
				dataset[i].id = data[i].LC_ID;
			}
			//Create SVG element
			var svg = d3.select("svg").attr("width", w).attr("height", h);
//svg.append("defs").append('marker')
//						.attr("id","arrow")
//						.attr("markerUnits","strokeWidth")
//					    .attr("markerWidth","12")
//                        .attr("markerHeight","12")
//                        .attr("viewBox","0 0 12 12") 
//                        .attr("refX","10")
//                        .attr("refY","6")
//                        .attr("orient","auto")
//                  .append('path').attr({
//	'd':"M2,2 L10,6 L2,10 L6,6 L2,2",
//	'fill':'#f00'
//})
			var rect = svg.selectAll("rect1").data(dataset).enter().append("rect");

			rect.attr({
				"x" : function(d, i) {
					return d.rect_x;
				},
				"y" : function(d, i) {
					return d.rect_y;
				},
				"width":function(d,i){
					return d.w
				},
				"height":function(d,i){
					return d.h
				},
//				"fill" : "yellow",
				"stroke" : "orange",
				"stroke-width" : function(d) {
					return 1;
				}
			}).on('mouseover',function(d){
				   		d3.select(this).attr('stroke','red')
			}).on('mouseout',function(d){
		   		d3.select(this).attr('stroke','orange')
		   }).on('click',function(d){
			   getMx(d)
		   });
			var line = svg.selectAll("rect1").data(dataset).enter().append("line");

			line.attr({
				"x1" : function(d, i) {
					if(d.d == r2l){
						return d.x + d.l1
					}
					return d.x;
				},
				"y1" : function(d, i) {
					return d.y;
				},
				"x2":function(d,i){
					if(d.d == t2b){
						return d.x
					}
					if(d.d == r2l){
						return d.x
					}
					return d.x+d.l1
				},
				"y2":function(d,i){
					if(d.d == t2b){
						return d.y + d.l2
					}
					return d.y
				},
				'marker-end':"url(#arrow)",
				"stroke" : "#000",
				"stroke-width" : function(d) {
					return 2;
				}
			});
			
			var text1 = svg.selectAll("text1").data(dataset).enter().append("text");
			
			text1.text(function(d,i){
				return '数量:'+d.vehic_count 
			}).attr({
				'font-size':text_font_size,
				"x" : function(d, i) {
					return d.rect_x+text_margin_left;
				},
				"y" : function(d, i) {
					return d.rect_y+text_height;
				}
			});
			
			var text2 = svg.selectAll("text2").data(dataset).enter().append("text");
			
			text2.text(function(d,i){
				return '时间:'+util.formatYYYYMMDDHHMISS(d.datetime)
			}).attr({
				'font-size':text_font_size,
				"x" : function(d, i) {
					return d.rect_x+text_margin_left;
				},
				"y" : function(d, i) {
					return d.rect_y+2*text_height;
				}
			});
			
			var text3 = svg.selectAll("text3").data(dataset).enter().append("text");
			
			text3.text(function(d,i){
				return '地址:'+ d.address
			}).attr({
				'font-size':text_font_size,
				"x" : function(d, i) {
					return d.rect_x+text_margin_left;
				},
				"y" : function(d, i) {
					return d.rect_y+3*text_height;
				}
			});	
			
			var text4 = svg.selectAll("text4").data(dataset).enter().append("text");
			
			text4.text(function(d,i){
				return '头车:'+ d.tc
			}).attr({
				'font-size':text_font_size,
				"x" : function(d, i) {
					return d.rect_x+text_margin_left;
				},
				"y" : function(d, i) {
					return d.rect_y+4*text_height;
				}
			});	
		}
	});
		
	function getMx(node){
		dojo.xhrPost({

			url : basePath + "yjzh/lcjkCxmx?id="+node.id,
			handleAs : "json",
			load : function(data) {
				console.log(data)
//				
				var vehicles_str = '';
				for(var i=0;i<data.length;i++){
					vehicles_str += data[i].VEHI_NO
					if(i < data.length -1){
						vehicles_str += "、";
					}
				}
				dojo.byId('gridDiv').innerHTML =  "<span id='aaa' style='color:red;font-weight:bold'>"+ util.formatYYYYMMDDHHMISS(node.datetime) + "</span>在<span  style='color:red;font-weight:bold'>" + node.address + "</span>,聚集了<span  style='color:red;font-weight:bold'>" +node.vehic_count+"</span>辆车,疑似头车为:<span  style='font-weight:bold;font-size:20px;'>"+node.tc 
				+"</span><br>车辆列表：<br><span  style=''>"+vehicles_str+"</span>"
			},
			error : function(error) {
				targetNode.innerHTML = "An unexpected error occurred: "
						+ error;
			}
		})
	};
});
