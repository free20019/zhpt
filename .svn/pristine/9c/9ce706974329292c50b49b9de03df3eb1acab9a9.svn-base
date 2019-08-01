//TODO 为行加单击样式
//TODO 重构操作模块，如详情关联的事件
function json2Table(jsonList,config,flagNo){
	
	var table = document.createElement('table');
	table.className = 'gridTable';
	var cellIndex = 0;
	//1 title
	var row = table.insertRow(0)
	row.className = 'gridTableTitle'
	if(flagNo){
		var cell = row.insertCell(cellIndex++);
		cell.innerHTML ='序号'
		cell.style.textAlign = 'center'
		cell.style.width='5%'
	}
	var title = config['title']
	for(var i=0;i<title.length;i++){
		var cell = row.insertCell(cellIndex++);
		cell.innerHTML = title[i]['name'];
		if(title[i]['styleTitle'] !=null){
			cell.style.cssText = title[i]['styleTitle']
		}
	}
	if(flagNo){
		var cell = row.insertCell(cellIndex++);
		cell.innerHTML ='操作'
			cell.style.textAlign = 'center'
		if(config.name != 'btjs'){
			cell.style.width='7%'
		}else{
			cell.style.width='3%'
		}
	}
	//2 body
	for(var i=0;i<jsonList.length;i++){
		cellIndex = 0;
		row = table.insertRow(i+1)
		if(flagNo){
			var cell = row.insertCell(cellIndex++);
			cell.innerHTML = i+1
			cell.style.textAlign = 'center'
			cell.className = 'gridTableBodyTd'
		}
		if(i%2==0){
			row.className = 'gridTableBodyTr1'
		}else{
			row.className = 'gridTableBodyTr2'
		}
		for(var j=0;j<title.length;j++){
			var cell = row.insertCell(cellIndex++);
			cell.className = 'gridTableBodyTd'
			cell.innerHTML = jsonList[i][[title[j]['col']]];
			if (title[j]['type'] == 'date' ){
				var tt  = jsonList[i][title[j]['col']];
				if(tt){
					var fromat = title[j]['format']
					if(fromat){
						cell.innerHTML = new Date(tt).format(fromat); 
					}else{
						cell.innerHTML = new Date(tt).format("yyyy-MM-dd");
					}
				}
			}
			if(title[j]['style'] !=null){
				cell.style.cssText = title[j]['style']
			}
		}
		
		if(flagNo){
			var cell = row.insertCell(cellIndex++);
			var bb = document.createElement('a');
			bb.className = 'cds_btn'
			bb.innerHTML='<span class="cds_btn_text">详  情</span>';
			
			if(config.name == 'yj'
	        	|| config.name=='btjzj'){
				bb.innerHTML='<span class="cds_btn_text">删 除</span>';
			}
			bb.po = jsonList[i];//TODO guid 需要 可配置
			
			//bb.onclick = function(){console.log(jsonList[i]['guid'])} //这种方式取不到值，所以要放到value中
			bb.onclick = function(){
				if(config.name == 'yj'){
					delYj(this.po['id']);
				}else if(config.name == 'btjzj'){
					delBtjzj(this.po['id']);
				}
			}
			if(config.name == 'yj'
	        	|| config.name=='btjzj'){
				cell.appendChild(bb)
				cell.style.textAlign = 'center'
				cell.className = 'gridTableBodyTd'
			}
		}
	}
	
	//3 toolbar
	var wrapTable =$('<div>');
	var configToolbar = config['toolbar']
	if(configToolbar){
		if(configToolbar == 'add'){
			var toolbar = '<div class=gridtoolbar><span><a href="javascript:void(0)" class="cds_btn"><span class="cds_btn_text">添加</span></a></span></div>';
			wrapTable.append($(toolbar));
		}
	}
	var buttons = $(wrapTable).find('.gridtoolbar a');
	if(buttons.length >0 ){
		if(config.name == 'yj'){
			$(buttons[0]).bind('click',function(){
				var sTime = $("<input id=yj_sTime>").jqxInput({height: 25, width: 200, minLength: 1 });
				var eTime = $("<input id=yj_eTime>").jqxInput({height: 25, width: 200, minLength: 1 });
				var price = $("<input id=yj_price>").jqxInput({height: 25, width: 200, minLength: 1 });
				var digButtosDom =$('<div style="text-align: center;"><br><span><a href="javascript:void(0)" class="cds_btn"><span class="cds_btn_text">保存</span></a></span><span><a href="javascript:void(0)" class="cds_btn"><span class="cds_btn_text">重置</span></a></span></div>');
				var editBody = $('<div id="editYjBody">')
				.append('开始时间：').append(sTime).append('<br><br>')
				.append('结束时间：').append(eTime).append('<br><br>')
				.append('价&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格：').append(price).append(digButtosDom);
				;
				
				var digButtos  =  $(digButtosDom).find('a');
				$(digButtos[0]).bind('click',function(){
					saveYj($('#yj_sTime').val(),$('#yj_eTime').val(),$('#yj_price').val());
				})
				$(digButtos[1]).bind('click',function(){
					$('#yj_sTime').val('');
					$('#yj_eTime').val('');
					$('#yj_price').val('')
				})
				
				
				var editYj = $('<div id="editYj"><div>添加油价</div></div>').append(editBody);
				$(document.body).append(editYj)
				var jqxYjWindow = $('#editYj').jqxWindow({ height: 200, width: 300,isModal:true});
				$('#editYj').on('close',function(event){
					$(this).jqxWindow('destroy');
				})
			})
		}else if(config.name == 'btjzj'){
			$(buttons[0]).bind('click',function(){
				var sTime = $("<input id=btjzj_sTime>").jqxInput({height: 25, width: 200, minLength: 1 });
				var eTime = $("<input id=btjzj_eTime>").jqxInput({height: 25, width: 200, minLength: 1 });
				var price = $("<input id=btjzj_price>").jqxInput({height: 25, width: 200, minLength: 1 });
				var digButtosDom =$('<div style="text-align: center;"><br><span><a href="javascript:void(0)" class="cds_btn"><span class="cds_btn_text">保存</span></a></span><span><a href="javascript:void(0)" class="cds_btn"><span class="cds_btn_text">重置</span></a></span></div>');
				var editBody = $('<div id="editBtjzjBody">')
				.append('开始时间：').append(sTime).append('<br><br>')
				.append('结束时间：').append(eTime).append('<br><br>')
				.append('价&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格：').append(price).append(digButtosDom);
				;
				
				var digButtos  =  $(digButtosDom).find('a');
				$(digButtos[0]).bind('click',function(){
					saveBtjzj($('#btjzj_sTime').val(),$('#btjzj_eTime').val(),$('#btjzj_price').val());
				})
				$(digButtos[1]).bind('click',function(){
					$('#btjzj_sTime').val('');
					$('#btjzj_eTime').val('');
					$('#btjzj_price').val('')
				})
				
				
				var editBtjzj = $('<div id="editBtjzj"><div>添加基准价</div></div>').append(editBody);
				$(document.body).append(editBtjzj)
				var jqxBtjzjWindow = $('#editBtjzj').jqxWindow({ height: 200, width: 300,isModal:true});
				$('#editBtjzj').on('close',function(event){
					$(this).jqxWindow('destroy');
				})
			})
		}
	}
	wrapTable.append(table);
	return wrapTable;
}