package com.shopping.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.shopping.entity.Product;
import com.shopping.entity.User;
import com.shopping.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value = "/doLogin", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> doLogin(String name, String password, HttpSession httpSession) {
        System.out.println("登陆信息：" + name + " " + password);
        String result = "badRequest";
        String code="500";
        User user = userService.getUser(name);
        if (user == null){
            result = "unExistUser";
        	code = "1002";
        }else {
            if (user.getPassword().equals(password)) {
                result = "success";
                code = "200";
                httpSession.setAttribute("currentUser", user);
            }else{
            	result = "wrongPassword";
            	code="1003";
            }
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("msg", result);
        resultMap.put("code", code);
        return resultMap;
    }

    @RequestMapping(value = "/doRegister", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doRegister(String name, String password, String head,String phoneNumber,String email , String address) {

        String result = "badRequest";
        String code="500";
        
        User user11 = userService.getUser(name);
        User user22 = userService.getUser(phoneNumber);
        if(user11 != null || user22!=null){
        	 result = "nameOrPhoneExist";
        	 code = "1000";
        }else{
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
            code="200";
        }
       
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("msg", result);
        resultMap.put("code", code);
        return resultMap;
    }

    @RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doUpdate(int id, String name, String email, String head, String password, String phoneNumber, String address) {
    	String result = "badRequest";
        String code="500";
        
        User user = userService.getUser(id);
        User user1=userService.getUser(phoneNumber);
        if(user1.getId() != user.getId()){
        	result="phoneExist";
        	code="1004";
        }else{
	        user.setEmail(email);
	        user.setHead(head);
	        user.setAddress(address);
	        user.setPassword(password);
	        user.setPhoneNumber(phoneNumber);
	        userService.updateUser(user);
	        result = "success";
	        code="200";
	    }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("msg", result);
        resultMap.put("code",code);
        return resultMap;
    }

    @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAllUser() {
        List<User> userList = new ArrayList<>();
        userList = userService.getAllUser();
        String allUsers = JSONArray.toJSONString(userList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("allUsers",allUsers);
        resultMap.put("msg", "success");
        resultMap.put("code", "200");
        return resultMap;
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> deleteUser(Integer id) {
    	String result = "badRequest";
        String code="500";
    	if(id == null){
    		result="unExistUser";
    		code="1002";
    	}else if(userService.deleteUser(id)){
           result="success";
           code="200";
        }
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("msg",result);
        resultMap.put("code", code);
        return resultMap;
    }

    @RequestMapping(value = "/getUserAddressAndPhoneNumber", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getUserAddressAndPhoneNumber(Integer id) {
    	Map<String,Object> resultMap = new HashMap<String,Object>();
    	
    	if(id == null){
    		resultMap.put("msg","unExistUser");
    		resultMap.put("code", "1002");
    		return resultMap;
    	}else{
	        String address = userService.getUser(id).getAddress();
	        String phoneNumber = userService.getUser(id).getPhoneNumber();
	        resultMap.put("address",address);
	        resultMap.put("phoneNumber",phoneNumber);
	        resultMap.put("msg","success");
    		resultMap.put("code", "200");
	        return resultMap;
    	}
    }

    @RequestMapping(value = "/doLogout")
    public Map<String, Object> doLogout(HttpSession httpSession){
    	Map<String,Object> resultMap = new HashMap<String,Object>();
        httpSession.setAttribute("currentUser","");
        resultMap.put("msg","success");
		resultMap.put("code", "200");
        return resultMap;
    }

    @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getUserById( Integer id) {
    	Map<String,Object> resultMap = new HashMap<String,Object>();
    	if(id == null){
    		resultMap.put("msg","unExsitUser");
    		resultMap.put("code", "1002");
    	}else{
	        User user = userService.getUser(id);
	        String userstr = JSON.toJSONString(user);
	        resultMap.put("user",userstr);
	        resultMap.put("msg","success");
    		resultMap.put("code", "200");
        }
    	return resultMap;
    }

}
