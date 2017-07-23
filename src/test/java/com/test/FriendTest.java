package com.test;

import com.jayantxie.model.Friend;
import com.jayantxie.model.User;
import com.jayantxie.utils.HttpRequest;
import com.jayantxie.utils.JsonUtil;
import com.jayantxie.utils.MapToUrl;
import org.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 天亮就出发 on 2017/3/12.
 */
public class FriendTest extends BaseTest {

    @Test
    public void testQuery(){
        String url = "http://localhost:8080/web-ssm/friend/queryAll";
        Map<String,String> params = new HashMap<String, String>();
        params.put("name","TTL2020");
        params.put("state","2");
        String str = MapToUrl.getUrlParamsByMapDs(params);
        String response = HttpRequest.sendGet(url,str);
        //response获得的是服务端发回的jsonString，通过下列语句转成json对象
        JSONObject jsonObject = new JSONObject(response);
        String msg = jsonObject.getString("msg");
        System.out.println(msg);
        //null
        List<User> list = JsonUtil.stringToList(jsonObject.getString("userList").substring(1),User.class);
        for(User user:list){
            System.out.println(user.getName());
        }
    }

    @Test
    public void testRequestAdd(){
        String url = "http://localhost:8080/web-ssm/friend/requestAdd";
        Map<String,String> params = new HashMap<String, String>();
        Friend friend = new Friend();
        friend.setUserName("1076408511");
        friend.setFriendName("TTL2020");
        params.put("friend", JsonUtil.objectToString(friend));
        String str = MapToUrl.getUrlParamsByMapDs(params);
        String response = HttpRequest.sendPost(url,str);
        System.out.println(response);
        JSONObject jsonObject = new JSONObject(response);
        String msg = jsonObject.getString("msg");
        System.out.println(msg);
    }

    @Test
    public void testDoneAdd(){
        String url = "http://localhost:8080/web-ssm/friend/doneAdd";
        Map<String,String> params = new HashMap<String, String>();
        Friend friend = new Friend();
        friend.setUserName("TTL2020");
        friend.setFriendName("1076408511");
        params.put("friend", JsonUtil.objectToString(friend));
        String str = MapToUrl.getUrlParamsByMapDs(params);
        String response = HttpRequest.sendPost(url,str);
        System.out.println(response);
        JSONObject jsonObject = new JSONObject(response);
        String msg = jsonObject.getString("msg");
        System.out.println(msg);
    }
}
