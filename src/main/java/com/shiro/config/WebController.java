package com.shiro.config;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class WebController {
    @RequestMapping("/index")
    public String index(Model map) {
        //集合演示
        List<String> list = new ArrayList<String>();
        for(int i=0;i<10;i++){
            list.add("优夺"+i);
        }
        map.addAttribute("lists", list);

        //map集合
        Map<String, String> ma =new HashMap<String,String>();
        ma.put("a", "砖石");
        ma.put("b","黄金");
        ma.put("c","白金");
        map.addAttribute("TestMap", ma);

        //时间
        map.addAttribute("nowTime", new Date().toString());

        //普通方法
        map.addAttribute("name", "World");
        return "index";
    }


    @RequestMapping(value = "/onLogin",method = RequestMethod.GET)
    public String login() {
        System.out.printf("调用一次");
        return "onLogin";
    }

    @RequestMapping(value = "/onLogin", method = RequestMethod.POST)
    @ResponseBody
    public String loginCheck(String username, String password, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        ResponseJson responseJson=new ResponseJson();
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            redirectAttributes.addFlashAttribute("message", "账户不存在");
        } catch (IncorrectCredentialsException ice) {
            redirectAttributes.addFlashAttribute("message", "密码错误");
        }
        if (subject.isAuthenticated()) {
            SavedRequest request1 = WebUtils.getAndClearSavedRequest(request);
            String url = ((request1 == null || request1.getRequestURI() == null) ? request.getContextPath()+"/index" : request1.getRequestUrl());
            responseJson.setMsg("success");
            responseJson.setUrl(url);
            return JSONObject.toJSONString(responseJson);
        } else {
            token.clear();
            Map<String,String > map=(Map) redirectAttributes.getFlashAttributes();
            responseJson.setMsg("false");
            responseJson.setReturnStr(map.get("message"));
            return JSONObject.toJSONString(responseJson);

        }
    }
}

