package com.shopping.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.shopping.entity.Product;
import com.shopping.entity.User;
import com.shopping.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 鍔熻兘
 * 1.鐧婚檰锛堣〃椤癸級
 * 2.娉ㄥ唽锛堣〃椤癸級
 * 3.鏇存柊涓汉淇℃伅锛堣〃椤癸級
 * 4.鑾峰彇鎵�鏈夌敤鎴蜂俊鎭�
 * 5.鍒犻櫎鐢ㄦ埛锛坕d锛�
 * 6.鑾峰彇鐢ㄦ埛鍦板潃鍜岀數璇濆彿鐮侊紙id锛�
 * 7.閫�鍑虹櫥褰�
 * 8.鑾峰彇鐢ㄦ埛绠�鍗曚俊鎭紙id锛�
 * 9.鑾峰彇鐢ㄦ埛璇︾粏淇℃伅锛坕d锛�
 **/
@Controller
public class UserController {

    @Resource
    UserService userService;

    @PostMapping("/doLogin")
    @ResponseBody
    public Map<String, Object> doLogin(String name, String password, HttpSession httpSession) {
        System.out.println("鐧婚檰淇℃伅锛�" + name + " " + password);
        String result = "badRequest";
        int code = 201;
        User user = userService.getUser(name);
        if (user == null) {
            result = "unExistUser";
            code = 1002;
        } else {
            if (user.getPassword().equals(password)) {
                result = "success";
                code = 200;
                httpSession.setAttribute("currentUser", user);
            } else {
                result = "wrongPassword";
                code = 1003;
            }
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("msg", result);
        resultMap.put("code", code);
        return resultMap;
    }

    @PostMapping(value = "/doRegister")
    @ResponseBody
    public Map<String, Object> doRegister(String name, String password, String head, String phoneNumber, String email, String address) {

        String result = "badRequest";
        Integer code = 201;

        User user11 = userService.getUser(name);
        User user22 = userService.getUser(phoneNumber);
        if (user11 != null || user22 != null) {
            result = "nameOrPhoneExist";
            code = 1000;
        } else {
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
            code = 200;
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
        Integer code = 202;

        User user = userService.getUser(id);
        User user1 = userService.getUser(phoneNumber);
        if (user1.getId() != user.getId()) {
            result = "phoneExist";
            code = 1004;
        } else {
            user.setEmail(email);
            user.setHead(head);
            user.setAddress(address);
            user.setPassword(password);
            user.setPhoneNumber(phoneNumber);
            userService.updateUser(user);
            result = "success";
            code = 200;
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("msg", result);
        resultMap.put("code", code);
        return resultMap;
    }

    @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAllUser() {
        List<User> userList = new ArrayList<>();
        userList = userService.getAllUser();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("data", userList);
        resultMap.put("msg", "success");
        resultMap.put("code", 200);
        return resultMap;
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteUser(Integer id) {
        String result = "badRequest";
        Integer code = 202;
        if (id == null) {
            result = "unExistUser";
            code = 1002;
        } else if (userService.deleteUser(id)) {
            result = "success";
            code = 200;
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("msg", result);
        resultMap.put("code", code);
        return resultMap;
    }

    @RequestMapping(value = "/getUserAddressAndPhoneNumber", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getUserAddressAndPhoneNumber(Integer id) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (id == null) {
            resultMap.put("msg", "unExistUser");
            resultMap.put("code", 1002);
            return resultMap;
        } else {
            String address = userService.getUser(id).getAddress();
            String phoneNumber = userService.getUser(id).getPhoneNumber();
            Map<String, Object> userInfo = new HashMap<String, Object>();
            userInfo.put("address", address);
            userInfo.put("phoneNumber", phoneNumber);
            resultMap.put("data", userInfo);
            resultMap.put("msg", "success");
            resultMap.put("code", 200);
            return resultMap;
        }
    }

    @RequestMapping(value = "/doLogout")
    @ResponseBody
    public void doLogout(HttpSession httpSession) {
    	if(httpSession.getAttribute("currentUser")!= null){
    		httpSession.setAttribute("currentUser", "");
    	}
    }

    @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getUserById(Integer id) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (id == null) {
            resultMap.put("msg", "unExsitUser");
            resultMap.put("code", 1002);
        } else {
            User user = userService.getUser(id);
            String userstr = JSON.toJSONString(user);
            resultMap.put("data", userstr);
            resultMap.put("msg", "success");
            resultMap.put("code", 200);
        }
        return resultMap;
    }

    @GetMapping(value = "/getUserInfo")
    @ResponseBody
    public Map<String, Object> getUserInfo(HttpSession httpSession) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Object currentUser = httpSession.getAttribute("currentUser");
        if (null == currentUser) {
            resultMap.put("msg", "user not login");
            resultMap.put("code", 204);
        } else {
            resultMap.put("msg", "success");
            resultMap.put("data", currentUser);
            resultMap.put("code", 200);
        }
        return resultMap;
    }
    
    //important, do not delete
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
}
