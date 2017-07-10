<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="百度地图,百度地图API，百度地图自定义工具，百度地图所见即所得工具" />
<meta name="description" content="百度地图API自定义地图，帮助用户在可视化操作下生成百度地图" />
<link rel="stylesheet" type="text/css" href="css/normalize.css" />
<link rel="stylesheet" type="text/css" href="css/buttons.css" />
<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.2.0/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="css/demo.css" />
<link rel="stylesheet" type="text/css" href="css/component.css" />
<title>IP定位</title>
<!--引用百度地图API-->
<style type="text/css">
    html,body{margin:0;padding:0;}
    .iw_poi_title {color:#CC5522;font-size:14px;font-weight:bold;overflow:hidden;padding-right:13px;white-space:nowrap}
    .iw_poi_content {font:12px arial,sans-serif;overflow:visible;padding-top:4px;white-space:-moz-pre-wrap;word-wrap:break-word}
</style>
<script type="text/javascript" src="http://api.map.baidu.com/api?key=&v=1.2&services=true"></script>
</head>
<body>
  <!--百度地图容器-->
  <div style="width:76%;height:100%;position:absolute;" id="dituContent"></div>
  <div style="widows:19%;height:100%;float:right" id="zz">
      <section class="content">
		<span class="input input--hoshi">
			<input class="input__field input__field--hoshi" type="text" id="input-4" />
			<label class="input__label input__label--hoshi input__label--hoshi-color-1" for="input-4">
				<span class="input__label-content input__label-content--hoshi">IP地址</span>
			</label>
		</span>
	  </section>
	  <div style="margin-top: -60px;margin-left: 130px;"><a href="#" class="button button-action button-pill">查询</a></div>
  </div>
</body>
<script src="js/jquery-3.2.1.js"></script>
<script type="text/javascript">
    //创建和初始化地图函数：
    var lat=${lat};
    var lng=${lng};
    function initMap(){
        createMap();//创建地图
        setMapEvent();//设置地图事件
        addMapControl();//向地图添加控件
    }
    
    //创建地图函数：
    function createMap(){
    	var radi=${radi};
    	var msg="您的IP: [${ip}]  定位地址为: [${address}] <br/>经纬度:[${lat},${lng}] 定位时间:[${time}]";
        var map = new BMap.Map("dituContent",{enableAutoResize:true});//在百度地图容器中创建一个地图
        var point = new BMap.Point(lng,lat);//定义一个中心点坐标
        map.centerAndZoom(point,18);//设定地图的中心点和坐标并将地图显示在地图容器中
        map.enableScrollWheelZoom(true); 
        var marker = new BMap.Marker(point);  // 创建标注
    	map.addOverlay(marker);               // 将标注添加到地图中
    	marker.setAnimation(BMAP_ANIMATION_BOUNCE);
        var pointCenter = map.getCenter();
        //创建圆对象
        circle = new BMap.Circle(pointCenter, radi, {
            strokeColor: "red",
            strokeWeight: 1.5,
            fillColor: "#E2E8F1",
            fillOpacity: 0.6
        });
        //画到地图上面
        map.addOverlay(circle);
        map.setViewport(circle.getBounds());
        var opts = {
        		  position : point,    // 指定文本标注所在的地理位置
        		  offset   : new BMap.Size(30, -30)    //设置文本偏移量
        		}
        var label = new BMap.Label(msg, opts);  // 创建文本标注对象
		label.setStyle({
			 color : "red",
			 fontSize : "12px",
			 height : "50px",
			 lineHeight : "20px",
			 fontFamily:"微软雅黑"
		 });
	    map.addOverlay(label);
        window.map = map;//将map变量存储在全局
    }
    
    //地图事件设置函数：
    function setMapEvent(){
        map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
        map.enableScrollWheelZoom();//启用地图滚轮放大缩小
        map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
        map.enableKeyboard();//启用键盘上下左右键移动地图
    }
    
    //地图控件添加函数：
    function addMapControl(){
        //向地图中添加缩放控件
	var ctrl_nav = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
	map.addControl(ctrl_nav);
        //向地图中添加缩略图控件
	var ctrl_ove = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:1});
	map.addControl(ctrl_ove);
        //向地图中添加比例尺控件
	var ctrl_sca = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
	map.addControl(ctrl_sca);
	    //地图添加多维视图
    var ctrl_type=new BMap.MapTypeControl({ mapTypes: [BMAP_NORMAL_MAP,BMAP_HYBRID_MAP,BMAP_SATELLITE_MAP] })
    map.addControl(ctrl_type);
    }
    
    
    initMap();//创建和初始化地图
</script>

<script src="js/classie.js"></script>
		<script>
			(function() {
				// trim polyfill : https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/Trim
				if (!String.prototype.trim) {
					(function() {
						// Make sure we trim BOM and NBSP
						var rtrim = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g;
						String.prototype.trim = function() {
							return this.replace(rtrim, '');
						};
					})();
				}

				[].slice.call( document.querySelectorAll( 'input.input__field' ) ).forEach( function( inputEl ) {
					// in case the input is already filled..
					if( inputEl.value.trim() !== '' ) {
						classie.add( inputEl.parentNode, 'input--filled' );
					}

					// events:
					inputEl.addEventListener( 'focus', onInputFocus );
					inputEl.addEventListener( 'blur', onInputBlur );
				} );

				function onInputFocus( ev ) {
					classie.add( ev.target.parentNode, 'input--filled' );
				}

				function onInputBlur( ev ) {
					if( ev.target.value.trim() === '' ) {
						classie.remove( ev.target.parentNode, 'input--filled' );
					}
				}
			})();
		</script>
                
</html>