package com.zh.demo.user.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zh.demo.user.entity.ipinfo;
import com.zh.demo.user.entity.user;
import com.zh.demo.user.service.ipInfoService;
import com.zh.demo.user.service.userService;
import com.zh.demo.utils.Md5Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
public class userController {
	private final static Logger logger=LoggerFactory.getLogger(userController.class);
	@Autowired
	private userService userService;
	@Autowired
	private ipInfoService ipInfoService;
	
	
	@RequestMapping("/intoLogin")
	public String getAllUser(Map<String, Object> map){
//		List<user> list=userService.getAllUser();
//		map.put("hello", list.get(0).getName());
		return "/menu/login";
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public String login(String username,String password){
		Map<String, Object> map=new HashMap<String, Object>();
		user user=userService.getByMobile(username);
		if (user==null) {
			map.put("success", false);
            map.put("msg", "未发现此用户相关信息");			
			return JSONObject.toJSONString(map);
		}
		if (user.getPassword().equals(Md5Utils.getMD5(password))) {
			map.put("success", true);
            map.put("msg", "登陆成功");			
			
		}else {
			map.put("success", false);
            map.put("msg", "账户名密码错误");			
		}
		return JSONObject.toJSONString(map);
	}
	
	@RequestMapping("/getAddress")
	public String addUser(HttpServletRequest request,String ip,Map<String, Object> map){
		 String ipAddress = request.getHeader("x-forwarded-for");  
         if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
             ipAddress = request.getHeader("Proxy-Client-IP");  
         }  
         if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
             ipAddress = request.getHeader("WL-Proxy-Client-IP");  
         }  
         if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
             ipAddress = request.getRemoteAddr();  
             if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){  
                 //根据网卡取本机配置的IP  
                 InetAddress inet=null;  
                 try {  
                     inet = InetAddress.getLocalHost();  
                 } catch (UnknownHostException e) {  
                     e.printStackTrace();  
                 }  
                 ipAddress= inet.getHostAddress();  
             }  
         }  
         //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
         if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15  
             if(ipAddress.indexOf(",")>0){  
                 ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));  
             }  
         } 
         String loc_time="";
         String error="";
         String address="";
         String lat="";
         String lng="";
         String detail="";
         String radi="";
         int status=0;
         String[] listAk=new String[]{"4dmcZusUBCLzw3Aqj7qs1Xn5RfscUG1f", "oTq3kbSY0uwjt1zm0AepBr7FqYTx4Lcb", "9w0bGND7tWoDQlbC2IGT3VGxe73rlEUG"};
         for (int i = 0; i < listAk.length; i++) {
			String responseString=userService.getHighIpLoc(ipAddress, listAk[i]);
//			String responseString=userService.getHighIpLoc("113.57.174.18", listAk[i]);
			net.sf.json.JSONObject jsonObject =net.sf.json.JSONObject.fromObject(responseString);
	        //获取定位代码  161 位定位成功
	         error = net.sf.json.JSONObject.fromObject(jsonObject.getString("result")).getString("error");
	         //定位时间
	         loc_time = net.sf.json.JSONObject.fromObject(jsonObject.getString("result")).getString("loc_time");
	        if ("161".equals(error)) {
	        	//获取结构化地址
		         address = net.sf.json.JSONObject.fromObject(jsonObject.getString("content")).getString("formatted_address");
		        //纬度坐标 
		         lat = net.sf.json.JSONObject.fromObject(net.sf.json.JSONObject.fromObject(jsonObject.getString("content")).getString("location")).getString("lat");
		        //经度坐标 
		         lng = net.sf.json.JSONObject.fromObject(net.sf.json.JSONObject.fromObject(jsonObject.getString("content")).getString("location")).getString("lng");
		         net.sf.json.JSONObject json=net.sf.json.JSONObject.fromObject(jsonObject.getString("content"));
		         //定位半径
		         radi= net.sf.json.JSONObject.fromObject(jsonObject.getString("content")).getString("radius");
		         if (json.containsKey("location_description")) {
					detail=json.getString("location_description");
				 }
		         ipinfo info=new ipinfo();
	        	 info.setIp(ipAddress);
	        	 info.setLat(lat);
	        	 info.setLng(lng);
	        	 info.setReponseadd(address+detail);
	        	 info.setReponseid(error);
	        	 info.setCreattime(loc_time);
	        	 ipInfoService.addIpInfo(info);
		         break;
			}else if("167".equals(error)){
				status=-1;
				break;
			}else{
				if (i==listAk.length-1) {
					status=-1;
				}
				continue;
			}
		}
         
         if (status==-1) {
        	 map.put("ip", ipAddress);
        	 map.put("time",loc_time);
        	 return "/menu/errorAddress"; 
		}
         map.put("ip", ipAddress);
         map.put("lat", lat);
 		 map.put("lng", lng);
 		 map.put("address", address+detail);
 		 map.put("time", loc_time);
 		 map.put("radi", radi);
         return "/helloJsp";
	}
	@RequestMapping("/intoBdMap")
	public String intoBdMap(Map<String, Object> map){
		 map.put("ip", "117.136.74.146");
    	 map.put("time","2017-07-04 21:49:26");
//		map.put("lat", "30.551863");
//		map.put("lng", "114.203354");
		return "/menu/errorAddress";
	}
}
