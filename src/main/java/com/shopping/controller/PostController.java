package com.shopping.controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.shopping.entity.Post;
import com.shopping.entity.User;
import com.shopping.service.PostService;
import com.shopping.service.UserService;

@Controller
public class PostController {

    @Resource
    private PostService postService;
    @Resource
    private UserService userservice;

    
    @RequestMapping(value = "/getAllPosts")
    @ResponseBody
    public Map<String,Object> getAllProducts(){
        List<Post> postList = new ArrayList<>();
        postList = postService.getAllPosts();
        String allPosts = JSONArray.toJSONString(postList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("allPosts",allPosts);
        resultMap.put("msg", "success");
        resultMap.put("code", "200");
        return resultMap;
    }
    
    @RequestMapping(value = "/addPost", method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> addPost(int user_id,String title,String img,String content) {
    	Map<String,Object> resultMap = new HashMap<String,Object>();
    	String result = "badRequest";
        String code="500";
        User user = userservice.getUser(user_id);
        Post post = new Post();
        post.setUser_id(user_id);
        post.setImg(img);
        post.setUser_name(user.getName());
        post.setUser_head(user.getHead());
        post.setTitle(title);
        post.setContent(content);
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        post.setTime(sf.format(date));
        postService.addPost(post);
        
        result = "success";
        code="200";
        resultMap.put("msg",result);
        resultMap.put("code", code);
        return resultMap;
    }
    
    @RequestMapping(value = "/deletePost", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> deletePost(Integer id) {
    	Map<String,Object> resultMap = new HashMap<String,Object>();
    	String result = "badRequest";
        String code="500";
        if(postService.deletePost(id)){
            result="success";
            code="200";
        }
        resultMap.put("msg",result);
        resultMap.put("code", code);
        return resultMap;
    }
    
    @RequestMapping(value = "/getPostById", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getPostById(Integer id) {
        Post post = postService.getPost(id);
        String poststr = JSON.toJSONString(post);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("post",poststr);
        resultMap.put("msg","success");
        resultMap.put("code", "200");
        return resultMap;
    }
}
