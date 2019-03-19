package com.shopping.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.shopping.entity.Product;
import com.shopping.entity.User;
import com.shopping.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 功能
 * 1.登陆（表项）
 * 2.注册（表项）
 * 3.更新个人信息（表项）
 * 4.获取所有用户信息
 * 5.删除用户（id）
 * 6.获取用户地址和电话号码（id）
 * 7.退出登录
 * 8.获取用户简单信息（id）
 * 9.获取用户详细信息（id）
 **/
@Controller
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping(value = "/register")
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/amend_info")
    public String amend_info() {
        return "amend_info";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/main")
    public String main() {
        return "main";
    }

    @RequestMapping(value = "/control")
    public String control() {
        return "control";
    }

    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doLogin(String name, String password, HttpSession httpSession) {
        System.out.println("登陆信息：" + name + " " + password);
        String result = "fail";
        User user = userService.getUser(name);
        if (user == null)
            result = "unexist";
        else {
            if (user.getPassword().equals(password)) {
                result = "success";
                httpSession.setAttribute("currentUser", user);
            } else
                result = "wrong";
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", result);
        return resultMap;
    }

    @RequestMapping(value = "/doRegister", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doRegister(String name, String password, String head,String phoneNumber,String email , String address) {

        String result = "fail";
        try{
        	 User user = userService.getUser(name);
        	 user = userService.getUser(email);
        	 //上面这两句检验是否有重名或者是用email注册过，如果出错就被catch了，就不走下面的了。hhh其实就是不知道怎么进行如果有问题就catch，没问题就继续
        	 result = "nameOrEmailExist";
        }catch(Exception e){
        	
        	User user1 = new User();
            user1.setName(name);
            user1.setPassword(password);
            user1.setPhoneNumber(phoneNumber);
            user1.setHead(head);
            user1.setAddress(address);
            user1.setEmail(email);
            user1.setRole(0);
            userService.addUser(user1);
            result = "success";
        }
       
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", result);
        return resultMap;
    }

    @RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doUpdate(String name, String email, String head, String password, String phoneNumber, String address) {
        String result = "fail";
        User user = userService.getUser(name);
        user.setEmail(email);
        user.setHead(head);
        user.setAddress(address);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        userService.updateUser(user);
        result = "success";
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", result);
        return resultMap;
    }

    @RequestMapping(value = "/getAllUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getAllUser() {
//        System.out.println("鎴戞帴鏀跺埌浜嗚幏鍙栫敤鎴疯姹�");
        List<User> userList = new ArrayList<>();
        userList = userService.getAllUser();
        String allUsers = JSONArray.toJSONString(userList);
//        System.out.println("鎴戣繑鍥炵殑缁撴灉鏄�"+allUsers);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("allUsers",allUsers);
        return resultMap;
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteUser(int id) {
        String result ="fail";
        if(userService.deleteUser(id)){
           result="success";
        }
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }

    @RequestMapping(value = "/getUserAddressAndPhoneNumber", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getUserAddressAndPhoneNumber(int id) {
        String address = userService.getUser(id).getAddress();
        String phoneNumber = userService.getUser(id).getPhoneNumber();
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("address",address);
        resultMap.put("phoneNumber",phoneNumber);
        return resultMap;
    }

    @RequestMapping(value = "/doLogout")
    public String doLogout(HttpSession httpSession){
        httpSession.setAttribute("currentUser","");
        return "redirect:login";
    }

    @RequestMapping(value = "/getUserById", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getUserById(int id) {
        User user = userService.getUser(id);
        String result = JSON.toJSONString(user);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }

    @RequestMapping(value = "/getUserDetailById", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getUserDetailById(int id) {
        User user = userService.getUser(id);
        String result = JSON.toJSONString(user);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }

}
