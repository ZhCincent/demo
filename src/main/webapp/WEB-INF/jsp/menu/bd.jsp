<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>圆形搜索</title>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.3"></script>
</head>
<body>
<div style="width:520px;height:340px;border:1px solid gray" id="container"></div>
</body>
</html>
<script type="text/javascript">
var map = new BMap.Map("container");
var mPoint = new BMap.Point(116.404, 39.915); 
map.enableScrollWheelZoom();
map.centerAndZoom(mPoint,14); var circle = new BMap.Circle(mPoint,1000,{fillColor:"blue", strokeWeight: 1 ,fillOpacity: 0.3, strokeOpacity: 0.3});
map.addOverlay(circle);
var local = new BMap.LocalSearch(map, {renderOptions: {map: map, autoViewport: false}}); 
var bounds = getSquareBounds(circle.getCenter(),circle.getRadius());
local.searchInBounds("餐馆",bounds); 

/**
* 得到圆的内接正方形bounds
* @param {Point} centerPoi 圆形范围的圆心
* @param {Number} r 圆形范围的半径
* @return 无返回值 
*/ 
// function getSquareBounds(centerPoi,r){
// var a = Math.sqrt(2) * r; //正方形边长

// mPoi = getMecator(centerPoi);
// var x0 = mPoi.x, y0 = mPoi.y;

// var x1 = x0 + a / 2 , y1 = y0 + a / 2;//东北点
// var x2 = x0 - a / 2 , y2 = y0 - a / 2;//西南点

// var ne = getPoi(new BMap.Pixel(x1, y1)), sw = getPoi(new BMap.Pixel(x2, y2));
// return new BMap.Bounds(sw, ne); 

// }
// //根据球面坐标获得平面坐标。
// function getMecator(poi){
// return map.getMapType().getProjection().lngLatToPoint(poi);
// }
// //根据平面坐标获得球面坐标。
// function getPoi(mecator){
// return map.getMapType().getProjection().pointToLngLat(mecator);
// }
</script>
 <BODY>
 </BODY>
</HTML>