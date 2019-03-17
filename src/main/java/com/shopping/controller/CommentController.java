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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.shopping.entity.Comment;
import com.shopping.entity.Product;
import com.shopping.service.CommentService;

@Controller
public class CommentController {
	@Resource 
	private CommentService commentservice;
	
    @RequestMapping(value = "/getAllComments")
    @ResponseBody
    public Map<String,Object> getAllComments(){
        List<Comment> commentList = new ArrayList<>();
        commentList = commentservice.getAllComment();
        String allComments = JSONArray.toJSONString(commentList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("allComments",allComments);
        return resultMap;
    }
    
    @RequestMapping(value = "/deleteComment", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> deleteComment(int id) {
        String result ="fail";
        if(commentservice.deleteComment(id)){
            result="success";
        }
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }

    @RequestMapping(value = "/addComment", method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> addComment(Integer user_id,Integer post_id,String content) {
        String result ="fail";
        Comment comment = new Comment();
        comment.setUser_id(user_id);
        comment.setPost_id(post_id);
        comment.setContent(content);
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        comment.setTime(sf.format(date));

        commentservice.addComment(comment);
        result = "success";
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }
    
    @RequestMapping(value = "/getCommentById", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getCommentById(int id) {
        Comment comment = commentservice.getComment(id);
        String result = JSON.toJSONString(comment);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("comment",result);
        return resultMap;
    }
    
}
