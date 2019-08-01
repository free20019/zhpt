define(["exports"],
		function(exports){
//	console.log('=====================================================');
	exports.show = function show(d){
		console.log('#',d);
	}
	exports.formatYYYYMMDD = function formatYYYYMMDD(d){
		if(d != ''){
			return new Date(d).format("yyyy-MM-dd");
		}else{
			return ''
		}
	}
	exports.formatYYYYMMDDHHMISS = function formatYYYYMMDDHHMISS(d){
		if(d != ''){
			return new Date(d).format("yyyy-MM-dd hh:mm:ss");
		}else{
			return '';
		}
	}
	exports.formatBJ_STATUS = function formatBJ_STATUS(d){
		if(d=='0'){
			return "未报警";
		}else if(d=='1'){
			return "未处理";
		}else if(d=='2'){
			return "待处理";
		}else if(d=='3'){
			return "已处理";
		}else if(d=='4'){
			return "不处理";
		}
	}
	exports.formatSH_STATUS = function formatSH_STATUS(d){
		if(d=='1'){
			return "已核实";
		}else {
			return "未核实";
		}
	}
//	exports.raodao = function raodao(d){
//		if(d=='1'){
//			return "绕道";
//		}else {
//			return "未绕道";
//		}
//	}
//	exports.jjqyc = function jjqyc(d){
//		if(d=='1'){
//			return "异常";
//		}else {
//			return "未异常";
//		}
//	}
	exports.formatRWGLSTATE = function formatRWGLSTATE(d){
		if(d=='0'){
			return "在工厂";
		}else if(d=='1'){
			return "从工厂到工地";
		}else if(d=='2'){
			return "在工地";
		}else if(d=='3'){
			return "从工地到工厂";
		}
	}
	exports.formatONLINE = function formatONLINE(d){
		if(d=='0'){
			return "不在线";
		}else{
			return "在线"
		}
			
		return new Date(d).format("yyyy-MM-dd hh:mm:ss");
	}
	exports.fxzh = function fxzh(obj){
		if(obj==0||obj==360){
			return "正北";
		}else if(obj==90){
			return "正东";
		}else if(obj==180){
			return "正南";
		}else if(obj==270){
			return "正西";
		}else if(obj>0&&obj<90){
			return "东北";
		}else if(obj>90&&obj<180){
			return "东南";
		}else if(obj>180&&obj<270){
			return "西南";
		}else if(obj>270&&obj<360){
			return "西北";
		}
	}
	Date.prototype.format = function(fmt)   
	{ //author: meizz   
	  var o = {   
	    "M+" : this.getMonth()+1,                 //月份   
	    "d+" : this.getDate(),                    //日   
	    "h+" : this.getHours(),                   //小时   
	    "m+" : this.getMinutes(),                 //分   
	    "s+" : this.getSeconds(),                 //秒   
	    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
	    "S"  : this.getMilliseconds()             //毫秒   
	  };   
	  if(/(y+)/.test(fmt))   
	    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
	  for(var k in o)   
	    if(new RegExp("("+ k +")").test(fmt))   
	  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
	  return fmt;   
	}
})