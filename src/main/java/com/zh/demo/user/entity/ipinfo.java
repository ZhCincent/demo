package com.zh.demo.user.entity;

public class ipinfo {
	private Long id;
	private String ip;
	private String reponseid;
	private String lat;
	private String lng;
	private String reponseadd;
	private String creattime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getCreattime() {
		return creattime;
	}
	public void setCreattime(String creattime) {
		this.creattime = creattime;
	}
	public String getReponseid() {
		return reponseid;
	}
	public void setReponseid(String reponseid) {
		this.reponseid = reponseid;
	}
	public String getReponseadd() {
		return reponseadd;
	}
	public void setReponseadd(String reponseadd) {
		this.reponseadd = reponseadd;
	}
	
}
