var ten;
var qdname;
map_lxt = new AMap.Map('lxt-mapBox', {
	animateEnable:false,
	jogEnable:false,
	view: new AMap.View2D({
		center: new AMap.LngLat(120.153576,30.287459),
		zoom: 10
	})

});
//findodlxt();
			
$(".sc").click(function(){
	map_lxt.clearMap();
	if(pathSimplifierIns)
		pathSimplifierIns.setData(null)
	var lxt_ul = $("#lxt-mapShowList")[0].children;
	for(var i=0; i<lxt_ul.length; i++){
		lxt_ul[i].className='iconItem findonelxt fontcolor2';
	}
	$(this).addClass('action').siblings('.action').removeClass('action');
	var OrientID = $(this).attr("OrientID");
	console.log(OrientID)
	var obj = [];
	if(OrientID == '火车东站'){
		obj = [{'name':'',path:[[120.217241,30.288571],[120.114244,30.315841]]},
               {'name':'',path:[[120.217241,30.288571],[120.434221,30.225702]]},
               {'name':'',path:[[120.217241,30.288571],[120.073861,30.287681]]},
               {'name':'',path:[[120.217241,30.288571],[120.164026,30.25462]]},
               {'name':'',path:[[120.217241,30.288571],[120.185655,30.239945]]},
               {'name':'',path:[[120.217241,30.288571],[120.165571,30.279121]]}]
		od(obj)
	} else {
		obj = [{'name':'',path:[[120.434221,30.225702],[120.185655,30.239945]]},
               {'name':'',path:[[120.434221,30.225702],[120.217241,30.288571]]},
               {'name':'',path:[[120.434221,30.225702],[120.164026,30.25462]]},
               {'name':'',path:[[120.434221,30.225702],[120.165571,30.279121]]},
               {'name':'',path:[[120.434221,30.225702],[120.114244,30.315841]]},
               {'name':'',path:[[120.434221,30.225702],[120.163983,30.261441]]}]
		od(obj)
	}
});
$(".xc").click(function(){
	map_lxt.clearMap();
	if(pathSimplifierIns)
	pathSimplifierIns.setData(null)
	var lxt_ul = $("#lxt-mapShowList-right")[0].children;
	for(var i=0; i<lxt_ul.length; i++){
		lxt_ul[i].className='iconItem findonelxt fontcolor2';
	}
	$(this).addClass('action').siblings('.action').removeClass('action');
	var OrientID = $(this).attr("OrientID");
	console.log(OrientID)
	var obj = [];
	if(OrientID == '龙翔桥'){
		obj = [{'name':'',path:[[120.165571,30.279121],[120.114244,30.315841]]},
               {'name':'',path:[[120.165571,30.279121],[120.114244,30.315841]]},
               {'name':'',path:[[120.434221,30.225702],[120.114244,30.315841]]},
               {'name':'',path:[[120.217241,30.288571],[120.114244,30.315841]]},
               {'name':'',path:[[120.163983,30.261441],[120.114244,30.315841]]},
               {'name':'',path:[[120.185655,30.239945],[120.114244,30.315841]]}]
		od(obj)
	} else {
		obj = [{'name':'',path:[[120.114244,30.315841],[120.217241,30.288571]]},
               {'name':'',path:[[120.434221,30.225702],[120.217241,30.288571]]},
               {'name':'',path:[[120.185655,30.239945],[120.217241,30.288571]]},
               {'name':'',path:[[120.164026,30.25462],[120.217241,30.288571]]},
               {'name':'',path:[[120.073861,30.287681],[120.217241,30.288571]]},
               {'name':'',path:[[120.163983,30.261441],[120.217241,30.288571]]}]
		od(obj)
	}
});
var pathSimplifierIns;
function od(obj){
	 AMapUI.load(['ui/misc/PathSimplifier', 'lib/$'], function(PathSimplifier, $) {

	        if (!PathSimplifier.supportCanvas) {
	            alert('当前环境不支持 Canvas！');
	            return;
	        }

	        //just some colors
	        var colors = [
	            "#3366cc", "#dc3912", "#ff9900", "#109618", "#990099", "#0099c6", "#dd4477", "#66aa00",
	            "#b82e2e", "#316395", "#994499", "#22aa99", "#aaaa11", "#6633cc", "#e67300", "#8b0707",
	            "#651067", "#329262", "#5574a6", "#3b3eac"
	        ];

	        pathSimplifierIns = new PathSimplifier({
	            zIndex: 100,
	            //autoSetFitView:false,
	            map: map_lxt, //所属的地图实例

	            getPath: function(pathData, pathIndex) {

	                return pathData.path;
	            },
	            getHoverTitle: function(pathData, pathIndex, pointIndex) {

	                if (pointIndex >= 0) {
	                    //point 
	                    return pathData.name + '，点：' + pointIndex + '/' + pathData.path.length;
	                }

	                return pathData.name + '，点数量' + pathData.path.length;
	            },
	            renderOptions: {
	                pathLineStyle: {
	                    dirArrowStyle: true
	                },
	                getPathStyle: function(pathItem, zoom) {

	                    var color = colors[pathItem.pathIndex % colors.length],
	                        lineWidth = Math.round(4 * Math.pow(1.1, zoom - 3));

	                    return {
	                        pathLineStyle: {
	                            strokeStyle: color,
	                            lineWidth: lineWidth
	                        },
	                        pathLineSelectedStyle: {
	                            lineWidth: lineWidth + 2
	                        },
	                        pathNavigatorStyle: {
	                            fillStyle: color
	                        }
	                    };
	                }
	            }
	        });

	        window.pathSimplifierIns = pathSimplifierIns;

	        $('<div id="loadingTip">加载数据，请稍候...</div>').appendTo(document.body);

	        $.getJSON('http://a.amap.com/amap-ui/static/data/big-routes.json', function(d) {

	            $('#loadingTip').remove();
	            var flyRoutes = [];
	            d = obj;
	            for (var i = 0, len = d.length; i < len; i++) {

	                if (d[i].name.indexOf('乌鲁木齐') >= 0) {

	                    d.splice(i, 0, {
	                        name: '飞行 - ' + d[i].name,
	                        path: PathSimplifier.getGeodesicPath(
	                            d[i].path[0], d[i].path[d[i].path.length - 1], 100)
	                    });

	                    i++;
	                    len++;
	                }
	            }

	            d.push.apply(d, flyRoutes);

	            pathSimplifierIns.setData(d);

	            //initRoutesContainer(d);

	            function onload() {
	                pathSimplifierIns.renderLater();
	            }

	            function onerror(e) {
	                alert('图片加载失败！');
	            }

	            //创建一个巡航器
	            var navg0 = pathSimplifierIns.createPathNavigator(0, {
	                loop: true, //循环播放
	                speed: 5000
	            });

	            navg0.start();
	          
	   			var navg1 = pathSimplifierIns.createPathNavigator(1, {
	                loop: true, //循环播放
	                speed: 5000
	            });

	            navg1.start();
	            var navg2 = pathSimplifierIns.createPathNavigator(2, {
	                loop: true, //循环播放
	                speed: 5000
	            });

	            navg2.start();
	          var navg3 = pathSimplifierIns.createPathNavigator(3, {
	                loop: true, //循环播放
	                speed: 5000
	            });

	            navg3.start();
	            var navg4 = pathSimplifierIns.createPathNavigator(4, {
	                loop: true, //循环播放
	                speed: 5000
	            });

	            navg4.start();
	            var navg5 = pathSimplifierIns.createPathNavigator(5, {
	                loop: true, //循环播放
	                speed: 5000
	            });

	            navg5.start();
	           
	        });
	    });
	 map_lxt.setFitView(-1);
}