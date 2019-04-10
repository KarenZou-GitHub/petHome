package com.shopping.controller;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
 
public class UpLoadController {
 
    public Map<String, Object> upload(@RequestParam MultipartFile productImgUpload, String filePath, String name, HttpServletRequest request) {
        Configuration cfg = new Configuration(Zone.zone1());                //zong1() 代表华北地区
        UploadManager uploadManager = new UploadManager(cfg);
 
        String accessKey = "CsC7y4_xADl-iJJtx-IBaAJj766XgnKiyjuBwkau";      //AccessKey的值
        String secretKey = "cCRsQ3hQFn02ejeU2bw0F5WEDnVUD8SBUTU0LekX";      //SecretKey的值
        String bucket = "pethome";                                          //存储空间名
        String localFilePath = filePath;     //上传图片路径
 
        String key = name;                                               //在七牛云中图片的命名
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("code", 200);
        resultMap.put("msg", "success");
        return resultMap;
    }
}
