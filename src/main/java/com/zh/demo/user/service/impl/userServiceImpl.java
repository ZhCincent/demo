package com.zh.demo.user.service.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zh.demo.user.service.userService;

import net.sf.json.JSONObject;

import com.zh.demo.user.dao.userDao;
import com.zh.demo.user.entity.BdParms;
import com.zh.demo.user.entity.user;

@Service
public class userServiceImpl implements userService {
	@Value("${bdak}")
	private String  bdak;
    
	@Autowired
	private userDao userDao;
	
	@Override
	public List<user> getAllUser() {
		// TODO Auto-generated method stub
		return userDao.getAllUser();
	}
	
	@Override
	public user getByMobile(String mobile) {
		// TODO Auto-generated method stub
		return userDao.getByMobile(mobile);
	}
	
	@Override
	public void addUser(user user) {
		// TODO Auto-generated method stub
		 userDao.addUser(user);
	}
	
	@Override
	public Map<String, Object> getHighIpLoc(String ip) {
		System.out.println(bdak);
		BdParms bd=new BdParms();
		bd.setAk(bdak);
		bd.setQcip(ip);
		bd.setQterm("pc");
		bd.setExtensions("3");
		bd.setCoord("bd09ll");
		bd.setCoding("utf-8");
		bd.setCallback("json");
		String url="api.map.baidu.com/highacciploc/v1?ak="+bdak+"&qcip="+ip+"&qterm=pc&extensions=3&coord=bd09ll&callback_type=json";
		String responseString = "";
		try {
            URL urls = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urls.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");

            out.write("");
            out.flush();
            out.close();
            
            String strLine = "";
            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while ((strLine = reader.readLine()) != null) {
                responseString += strLine + "\n";
            }
            in.close();
        
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		JSONObject jsonObject = JSONObject.fromObject(responseString);
        //定位时间
        String loc_time = JSONObject.fromObject(jsonObject.getString("result")).getString("loc_time");
        //获取定位代码  161 位定位成功
        String error = JSONObject.fromObject(jsonObject.getString("result")).getString("error");
        //获取结构化地址
        String address = JSONObject.fromObject(jsonObject.getString("content")).getString("formatted_address");
        //纬度坐标 
        String lat = JSONObject.fromObject(JSONObject.fromObject(jsonObject.getString("content")).getString("location")).getString("lat");
        //经度坐标 
        String lng = JSONObject.fromObject(JSONObject.fromObject(jsonObject.getString("content")).getString("location")).getString("lng");
		return null;
	}
}
