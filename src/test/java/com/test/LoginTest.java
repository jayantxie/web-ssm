package com.test;




import com.jayantxie.model.User;

import com.jayantxie.utils.HttpRequest;
import com.jayantxie.utils.JsonUtil;
import com.jayantxie.utils.MapToUrl;
import net.sf.json.JSONObject;
import org.junit.Test;


import java.util.HashMap;
import java.util.*;

/**
 * Created by 天亮就出发 on 2017/3/10.
 */
public class LoginTest extends BaseTest{


    @Test
    public void registerTest(){
        String url = "http://localhost:8080/web-ssm/user/register";
        User user = new User();
        user.setName("1076408511");
        user.setPassword("mytmylb");
        user.setNickname("哈哈摇");
        //user对象转json对象
        JSONObject json = JSONObject.fromObject(user);
        //通过map对象将参数与json对象关联，这样req获得的参数可以通过getParameter逐一获得
        Map<String,String> params = new HashMap<String, String>();
        params.put("user",json.toString());
        //将map对象转成发送http请求对应的String格式
        String str = MapToUrl.getUrlParamsByMapDs(params);
        System.out.println(str);
        String response = HttpRequest.sendPost(url,str);
        //response获得的是服务端发回的jsonString，通过下列语句转成json对象
        JSONObject jsonObject = JSONObject.fromObject(response);
        String msg = jsonObject.getString("msg");
        System.out.println(msg);
    }

    @Test
    public void loginTest(){
        String baseUrl = "http://localhost:8080/web-ssm/";
        String url = baseUrl+"user/login";
        //user对象转json对象
        //Map<String,Object> map = new HashMap<>();
        //map.put("name",myUser.getName());
        //map.put("nickName",myUser.getNickname());
        //map.put("password",myUser.getPassword());
        User user = new User();
        user.setName("TTL2020");
        user.setPassword("13291883598");
        String userString = JsonUtil.objectToString(user);
        //JSONObject json = JSONObject.fromObject(map);
        //通过map对象将参数与json对象关联，这样req获得的参数可以通过getParameter逐一获得
        Map<String,String> params = new HashMap<String,String>();
        params.put("user",userString);
        //将map对象转成发送http请求对应的String格式
        String str = MapToUrl.getUrlParamsByMapDs(params);
        System.out.println(str);
        String response = HttpRequest.sendPost(url,str);
        System.out.println(response);
        JSONObject jsonObject = JSONObject.fromObject(response);
        String msg = jsonObject.getString("msg");
        System.out.println(msg);
        System.out.println(jsonObject.getString("token"));
    }

}
