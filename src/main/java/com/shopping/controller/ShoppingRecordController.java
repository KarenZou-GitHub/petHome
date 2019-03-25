package com.shopping.controller;

import com.alibaba.fastjson.JSONArray;
import com.shopping.entity.Product;
import com.shopping.entity.ShoppingRecord;
import com.shopping.service.ProductService;
import com.shopping.service.ShoppingRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 方法
 * 1、增加一条购物记录（int int int）
 * 2.修改购物记录（int）
 * 3.获取自己的购物记录（int）
 * 4.根据购物记录状态获取购物记录（int）
 * 5.获取所有的购物记录
 * 6.获取用户的商品记录
 */
@Controller
public class ShoppingRecordController {
    @Resource
    private ProductService productService;
    @Resource
    private ShoppingRecordService shoppingRecordService;

    @RequestMapping(value = "/addShoppingRecord",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addShoppingRecord(int type,int userId,int productId,int counts){
        String result = "false";
        Product product = productService.getProduct(productId);
        if(counts<=product.getCounts()) {
            ShoppingRecord shoppingRecord = new ShoppingRecord();
            shoppingRecord.setType(type);
            shoppingRecord.setUser_id(userId);
            shoppingRecord.setProduct_id(productId);
            shoppingRecord.setProduct_price(product.getPrice());
            shoppingRecord.setProduct_name(product.getName());
            shoppingRecord.setCounts(counts);
            Date date = new Date();
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            shoppingRecord.setTime(sf.format(date));
            product.setCounts(product.getCounts()-counts);
            productService.updateProduct(product);
            shoppingRecordService.addShoppingRecord(shoppingRecord);
            result = "success";
        }
        else{
            result = "unEnough";
        }
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }

    @RequestMapping(value = "/getShoppingRecords",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getShoppingRecords(int userId){
        List<ShoppingRecord> shoppingRecordList = shoppingRecordService.getShoppingRecords(userId);
        String shoppingRecords = JSONArray.toJSONString(shoppingRecordList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",shoppingRecords);
        return resultMap;
    }

    @RequestMapping(value = "/getAllShoppingRecords",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getAllShoppingRecords(){
        List<ShoppingRecord> shoppingRecordList = shoppingRecordService.getAllShoppingRecords();
        String shoppingRecords = JSONArray.toJSONString(shoppingRecordList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",shoppingRecords);
        return resultMap;
    }
}
