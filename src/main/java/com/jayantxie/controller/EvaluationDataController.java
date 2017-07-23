package com.jayantxie.controller;


import com.jayantxie.model.DistractionTime;
import com.jayantxie.model.EvaluationData;
import com.jayantxie.service.DistractionTimeService;
import com.jayantxie.service.EvaluationDataService;
import com.jayantxie.utils.JsonUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 天亮就出发 on 2017/3/13.
 */

@Controller
@RequestMapping("/evaluationData")
public class EvaluationDataController {
    @Resource
    private EvaluationDataService evaluationDataService;

    @Resource
    private DistractionTimeService distractionTimeService;

    //会产生竞态冲突吗？
    private void finalSend(HttpServletResponse rsp, JSONObject rspObject){
        try {
            PrintWriter writer = rsp.getWriter();
            writer.print(rspObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/release",method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
    public void doRelease(HttpServletRequest req, HttpServletResponse rsp){
        rsp.setCharacterEncoding("utf-8");
        JSONObject rspObject = new JSONObject();
        String str = req.getParameter("evaluationData");
        String str1 = req.getParameter("distractionTimeList");
        EvaluationData evaluationData = (EvaluationData)JsonUtil.stringToObject(str,EvaluationData.class);
        boolean succeed = false;
        if(this.evaluationDataService.addEvaluationData(evaluationData)){
            int id = this.evaluationDataService.queryId(evaluationData.getUserName());
            if(!str1.equals("")){
                List<DistractionTime> distractionTimeList = JsonUtil.stringToList(str1,DistractionTime.class);
                List<DistractionTime> newDistractionTimeList = new ArrayList<DistractionTime>();
                for(DistractionTime d:distractionTimeList){
                    d.setEvaId(id);
                    newDistractionTimeList.add(d);
                }
                if(distractionTimeService.addList(newDistractionTimeList))
                    succeed = true;
            }else
                succeed = true;
        }

        if(succeed){
            rspObject.put("status",0);
            rspObject.put("msg","发布成功！");
        }else{
            rspObject.put("status",1);
            rspObject.put("msg","发布失败！");
        }

        this.finalSend(rsp,rspObject);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public void doDelete(HttpServletRequest req, HttpServletResponse rsp){
        rsp.setCharacterEncoding("utf-8");
        JSONObject rspObject = new JSONObject();
        Integer id = Integer.parseInt(req.getParameter("id"));

        if(this.evaluationDataService.deleteEvaluationData(id)){
            rspObject.put("status",0);
            rspObject.put("msg","删除成功！");
        }else{
            rspObject.put("status",1);
            rspObject.put("msg","删除失败！");
        }

        this.finalSend(rsp,rspObject);
    }

    @RequestMapping(value = "/showPage",method = RequestMethod.GET)
    public String doShowPage(HttpServletRequest req, Model model){
        int id = Integer.parseInt(req.getParameter("id"));
        EvaluationData evaluationData = this.evaluationDataService.queryOne(id);
        List<DistractionTime> distractionTimeList = this.distractionTimeService.queryList(id);
        model.addAttribute("evaluationData",evaluationData);
        model.addAttribute("distractionTimeList",distractionTimeList);
        return "showEvaluationData";
    }

    @RequestMapping(value = "/showTenTitles",method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public void doShowTenPages(HttpServletRequest req,HttpServletResponse rsp){
        rsp.setCharacterEncoding("utf-8");
        JSONObject rspObject = new JSONObject();
        String userName = req.getParameter("userName");
        System.out.println(userName);
        Integer number = Integer.parseInt(req.getParameter("number"));
        List<EvaluationData> list = this.evaluationDataService.queryTen(userName,number);

        if(!list.isEmpty()){
            rspObject.put("status",0);
            rspObject.put("msg","查询列表成功！");
            //注意，这里在Gson转换的字符串前加字符‘a’，是为了防止org.json对其进行解析，导致解析错误...
            //I make sure I'll never use the fucking net.sf.json！
            rspObject.put("list", "a" + JsonUtil.objectToString(list));
        }else{
            rspObject.put("status",1);
            rspObject.put("msg","查询列表失败，不存在！");
        }

        this.finalSend(rsp,rspObject);
    }
}
