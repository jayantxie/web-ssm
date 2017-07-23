package com.test;

import com.google.gson.Gson;
import com.jayantxie.model.DistractionTime;
import com.jayantxie.model.EvaluationData;
import com.jayantxie.utils.HttpRequest;
import com.jayantxie.utils.JsonUtil;
import com.jayantxie.utils.MapToUrl;
import org.json.JSONObject;
import org.junit.Test;

import java.util.*;

/**
 * Created by 天亮就出发 on 2017/4/30.
 */
public class EvaluationDataTest extends BaseTest {
    @Test
    public void testRelease() {
        String url = "http://localhost:8080/web-ssm/evaluationData/release";

        EvaluationData evaluationData = new EvaluationData();
        //user对象转json对象
        Date date1 = new Date();
        date1.setDate(1);
        date1.setHours(8);
        Date date2 = new Date();
        date2.setDate(1);
        evaluationData.setBeginTime(date1);
        evaluationData.setEndTime(date2);
        evaluationData.setScores(85);
        evaluationData.setRelaxMinutes(1);
        evaluationData.setRelaxTimes(1);
        evaluationData.setDistractionTimes(3);
        evaluationData.setText("今天很开心！");
        evaluationData.setUserName("CC98");
        List<DistractionTime> list = new ArrayList<DistractionTime>();
        DistractionTime d1 = new DistractionTime();
        DistractionTime d2 = new DistractionTime();
        DistractionTime d3 = new DistractionTime();
        d1.setsTime(date1);
        d1.seteTime(date2);
        d2.setsTime(date1);
        d2.seteTime(date2);
        d3.setsTime(date1);
        d3.seteTime(date2);
        list.add(d1);
        list.add(d2);
        list.add(d3);
        String evaluationDataString = JsonUtil.objectToString(evaluationData);
        String listString = JsonUtil.objectToString(list);

        //通过map对象将参数与json对象关联，这样req获得的参数可以通过getParameter逐一获得
        Map<String, String> params = new HashMap<String, String>();
        params.put("evaluationData", evaluationDataString);
        params.put("distractionTimeList", listString);
        //将map对象转成发送http请求对应的String格式
        String str = MapToUrl.getUrlParamsByMapDs(params);
        System.out.println(str);

        String response = HttpRequest.sendPost(url, str);
        //response获得的是服务端发回的jsonString，通过下列语句转成json对象
        System.out.println(response);
    }

    @Test
    public void testShowTenTitles(){
        String url = "http://localhost:8080/web-ssm/evaluationData/showTenTitles";
        int number = 0;
        Map<String,String> params = new HashMap<String, String>();
        params.put("number",number+"");
        params.put("userName","CC98");
        //将map对象转成发送http请求对应的String格式
        String str = MapToUrl.getUrlParamsByMapDs(params);
        String response = HttpRequest.sendGet(url,str);
        System.out.println(response);
        //response获得的是服务端发回的jsonString，通过下列语句转成json对象
        JSONObject jsonObject = new JSONObject(response);
        if(jsonObject.getInt("status") == 1)
            System.out.print("错误");
        else {
            String returnList = jsonObject.getString("list");
            //把传递的字符串的第一个字符‘a’去除
            returnList = returnList.substring(1);
            System.out.println(returnList);
            List<EvaluationData> list = JsonUtil.stringToList(returnList,EvaluationData.class);
            System.out.print(list.get(1).getBeginTime());
        }
    }

    @Test
    public void test(){
        Map<String,String> map = new HashMap<String, String>();
        map.put("status",0+"");
        map.put("msg","查询列表成功！");
        String mapString = JsonUtil.objectToString(map);
        System.out.print(mapString);
    }

}