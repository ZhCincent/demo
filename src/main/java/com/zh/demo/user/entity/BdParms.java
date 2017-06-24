package com.zh.demo.user.entity;

public class BdParms {
	private String qcip; //待定位IP  可选，如果为空，则针对定位服务的IP进行定位
	private String ak; //开发者密钥  必填  进行AK鉴权及配额控制
	private String qterm;  //	待定位终端类型  必填，mb：终端定位设备类型为移动设备  pc：终端定位设备类型为固定设备
	private String extensions; // 可选，0（默认）：只返回基础定位结果 1：返回基础定位结果+地址信息  2：返回基础定位结果+周边POI信息  3：返回基础定位结果+地址信息+POI信息
	private String coord;  	//返回坐标类型  可选，bd09（默认）：百度墨卡托坐标  bd09ll：百度经纬度坐标   gcj02：国测局经纬度坐标
	private String coding;  // 返回结果编码类型	可选，utf-8（默认）：返回UTF-8类型   gbk：返回GBK类型
	private String callback_type;  //回调方式选择  必选，json（默认）：json方式   jsonp：jsonp方式，需设置callback参数
	private String callback; //	jsonp回调函数   当callback_type=jsonp时，必填，取值为开发者所设置回调函数名
	public String getQcip() {
		return qcip;
	}
	public void setQcip(String qcip) {
		this.qcip = qcip;
	}
	public String getAk() {
		return ak;
	}
	public void setAk(String ak) {
		this.ak = ak;
	}
	public String getQterm() {
		return qterm;
	}
	public void setQterm(String qterm) {
		this.qterm = qterm;
	}
	public String getExtensions() {
		return extensions;
	}
	public void setExtensions(String extensions) {
		this.extensions = extensions;
	}
	public String getCoord() {
		return coord;
	}
	public void setCoord(String coord) {
		this.coord = coord;
	}
	public String getCoding() {
		return coding;
	}
	public void setCoding(String coding) {
		this.coding = coding;
	}
	public String getCallback_type() {
		return callback_type;
	}
	public void setCallback_type(String callback_type) {
		this.callback_type = callback_type;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	
	

}