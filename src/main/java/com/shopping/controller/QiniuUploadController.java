package com.shopping.controller;

import com.shopping.service.QiniuUploadServiceImplement;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
public class QiniuUploadController {

    @Resource
    private QiniuUploadServiceImplement qiniuUploadService;

    @GetMapping("getUpToken")
    @ResponseBody
    public Map getUpToken() {
        String token = qiniuUploadService.getUpToken();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("data", token);
        resultMap.put("msg", "success");
        resultMap.put("code", 200);
        return resultMap;
    }
}
