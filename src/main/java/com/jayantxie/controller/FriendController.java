package com.jayantxie.controller;

import com.jayantxie.model.Friend;
import com.jayantxie.model.User;
import com.jayantxie.service.FriendService;
import com.jayantxie.utils.JsonUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by 天亮就出发 on 2017/3/12.
 */

@Controller
@RequestMapping("/friend")
public class FriendController {
    @Resource
    private FriendService friendService;

    private void finalSend(HttpServletResponse rsp, JSONObject rspObject){
        try {
            PrintWriter writer = rsp.getWriter();
            writer.print(rspObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/requestAdd",method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
    public void doAdd(HttpServletRequest req, HttpServletResponse rsp){
        rsp.setCharacterEncoding("utf-8");
        JSONObject rspObject = new JSONObject();
        String jsonString = req.getParameter("friend");
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        Friend friend = (Friend) JSONObject.toBean(jsonObject, Friend.class);

        if(friendService.requestAddFriend(friend)){
            rspObject.put("status",0);
            rspObject.put("msg","添加成功！");
        }
        else{
            rspObject.put("status",1);
            rspObject.put("msg","添加失败,已存在！");
        }

        this.finalSend(rsp,rspObject);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
    public void doDelete(HttpServletRequest req, HttpServletResponse rsp){
        rsp.setCharacterEncoding("utf-8");
        JSONObject rspObject = new JSONObject();
        String jsonString = req.getParameter("friend");
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        Friend friend = (Friend) JSONObject.toBean(jsonObject, Friend.class);
        if(friendService.deleteFriend(friend)){
            rspObject.put("status",0);
            rspObject.put("msg","删除成功！");
        } else{
            rspObject.put("status",1);
            rspObject.put("msg","删除失败,不存在！");
        }
        this.finalSend(rsp,rspObject);
    }

    @RequestMapping(value = "/friendState",method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
    public void doFriendState(HttpServletRequest req, HttpServletResponse rsp){
        rsp.setCharacterEncoding("utf-8");
        JSONObject rspObject = new JSONObject();
        String jsonString = req.getParameter("friend");
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        Friend friend = (Friend) JSONObject.toBean(jsonObject, Friend.class);
        rspObject.put("state",this.friendService.friendState(friend));
        this.finalSend(rsp,rspObject);
    }


    @RequestMapping(value = "/doneAdd",method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
    public void doDoneAddFriend(HttpServletRequest req, HttpServletResponse rsp){
        rsp.setCharacterEncoding("utf-8");
        JSONObject rspObject = new JSONObject();
        String jsonString = req.getParameter("friend");
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        Friend friend = (Friend) JSONObject.toBean(jsonObject, Friend.class);
        if(this.friendService.doneAddFriend(friend)){
            rspObject.put("status",0);
            rspObject.put("msg","添加好友成功！");
        }else{
            rspObject.put("status",1);
            rspObject.put("msg","添加好友失败！");
        }
        this.finalSend(rsp,rspObject);
    }

    @RequestMapping(value = "/queryAll",method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public void doQueryAll(HttpServletRequest req, HttpServletResponse rsp){
        rsp.setCharacterEncoding("utf-8");
        JSONObject rspObject = new JSONObject();
        String name = req.getParameter("name");
        Integer state = Integer.parseInt(req.getParameter("state"));
        List<User> list = this.friendService.queryAllFriend(name,state);
        if(!list.isEmpty()){
            rspObject.put("status",0);
            rspObject.put("msg","查询成功！");
            //注意，这里在Gson转换的字符串前加字符‘a’，是为了防止org.json对其进行解析，导致解析错误...
            //I make sure I'll never use the fucking net.sf.json！
            rspObject.put("userList", "a" + JsonUtil.objectToString(list));
        }else{
            rspObject.put("status",1);
            rspObject.put("msg","查询失败！");
        }
        this.finalSend(rsp,rspObject);
    }

}
