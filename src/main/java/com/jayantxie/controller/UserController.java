package com.jayantxie.controller;

import com.jayantxie.utils.JsonUtil;
import net.sf.json.JSONObject;
import com.jayantxie.model.User;
import com.jayantxie.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    private void finalSend(HttpServletResponse rsp, JSONObject rspObject){
        try {
            PrintWriter writer = rsp.getWriter();
            writer.print(rspObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
    public void doRegister(HttpServletRequest req, HttpServletResponse rsp) {
        rsp.setCharacterEncoding("utf-8");
        JSONObject rspObject = new JSONObject();
        String jsonString = req.getParameter("user");
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        User user = (User) JSONObject.toBean(jsonObject, User.class);
        /*
        if(user.getName() == null || user.getNickname() == null ||
                user.getPassword() == null) {
            rspObject.put("status", 2);
            rspObject.put("msg","用户信息不完善！")
        }
        else {
        */
        System.out.println(user.getNickname());
        if (this.userService.register(user)) {
            rspObject.put("status", 0);
            rspObject.put("msg", "注册成功！");
        } else {
            rspObject.put("status", 1);
            rspObject.put("msg", "注册失败，用户名已存在！");
        }

        this.finalSend(rsp,rspObject);
        //}
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
    public void doLogin(HttpServletRequest req, HttpServletResponse rsp) {
        rsp.setCharacterEncoding("utf-8");
        JSONObject rspObject = new JSONObject();
        String jsonString = req.getParameter("user");
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        User user = (User) JSONObject.toBean(jsonObject, User.class);
        System.out.println(user);
        User newUser = this.userService.login(user);
        if (newUser != null) {
            String token = newUser.getToken();
            rspObject.put("token", token);
            rspObject.put("status", 0);
            rspObject.put("msg", "登录成功！");
        } else {
            rspObject.put("status", 1);
            rspObject.put("msg", "登录失败！");
        }
        this.finalSend(rsp,rspObject);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
    public void doUpdate(HttpServletRequest req, HttpServletResponse rsp){
        rsp.setCharacterEncoding("utf-8");
        JSONObject rspObject = new JSONObject();
        String jsonString = req.getParameter("user");
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        User user = (User) JSONObject.toBean(jsonObject, User.class);
        if(this.userService.update(user)){
            rspObject.put("status", 0);
            rspObject.put("msg", "修改成功！");
        }else{
            rspObject.put("status", 1);
            rspObject.put("msg", "修改失败！");
        }

        this.finalSend(rsp,rspObject);
    }


    @RequestMapping(value = "/showUser",method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public void toIndex(HttpServletRequest req, HttpServletResponse rsp) {
        rsp.setCharacterEncoding("utf-8");
        JSONObject rspObject = new JSONObject();
        String name = req.getParameter("name");
        User user = this.userService.query(name);
        if(user != null){
            rspObject.put("status", 0);
            rspObject.put("msg", "查询成功！");
            rspObject.put("user",JsonUtil.objectToString(user));
        }else{
            rspObject.put("status", 1);
            rspObject.put("msg","查询失败！");
        }

        this.finalSend(rsp,rspObject);
    }

}