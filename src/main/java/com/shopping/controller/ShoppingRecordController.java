package com.shopping.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shopping.entity.Pet;
import com.shopping.entity.Product;
import com.shopping.entity.ShoppingRecord;
import com.shopping.service.PetService;
import com.shopping.service.ProductService;
import com.shopping.service.ShoppingRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.json.JsonObject;

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
    private PetService petService;
    @Resource
    private ShoppingRecordService shoppingRecordService;

    @RequestMapping(value = "/addShoppingRecord", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addShoppingRecord(int type, int userId, int productId, int counts) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String result = "badRequest";
        Integer code = 500;
        if (type == 1) {
            Product product = productService.getProduct(productId);
            if (product == null) {
                result = "unExistProduct";
                code = 2002;
            } else if (counts <= product.getCounts()) {
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
                if (counts == product.getCounts()) {
                    productService.deleteProduct(productId);
                } else {
                    product.setCounts(product.getCounts() - counts);
                    productService.updateProduct(product);
                }
                shoppingRecordService.addShoppingRecord(shoppingRecord);
                result = "success";
                code = 200;
            } else {
                result = "notEnough";
                code = 3006;
            }
        } else if (type == 0) {
            Pet pet = petService.getPet(productId);
            if (counts == 1) {
                ShoppingRecord shoppingRecord = new ShoppingRecord();
                shoppingRecord.setType(type);
                shoppingRecord.setUser_id(userId);
                shoppingRecord.setProduct_id(productId);
                shoppingRecord.setProduct_price(pet.getPrice());
                shoppingRecord.setProduct_name(pet.getName());
                shoppingRecord.setCounts(counts);
                Date date = new Date();
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
                shoppingRecord.setTime(sf.format(date));
                petService.deletePet(productId);
                shoppingRecordService.addShoppingRecord(shoppingRecord);
                result = "success";
                code = 200;
            } else {
                result = "notEnough";
                code = 3006;
            }
        }
        resultMap.put("msg", result);
        resultMap.put("code", code);
        return resultMap;
    }
    
    ///////////////////////////////   大佬这是我新加的list     ////////////////////////////////////////////////////////
    @RequestMapping(value = "/addShoppingRecordList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addShoppingRecordList(String lists, Integer userId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        /*String shoppingRecords=(String)param.get("shoppingRecords");*/
        String result = "badRequest";
        Integer code = 500;
        
        JSONObject jsonlists=(JSONObject)JSON.parse(lists);
        JSONArray ja = jsonlists.getJSONArray("data");
        for(int i=0; i<ja.size(); i++){
        	JSONObject jsoni = ja.getJSONObject(i); 
        	resultMap = addShoppingRecord((int)jsoni.get("type"), userId, (int)jsoni.get("id"), (int)jsoni.get("count"));
        	if((String)resultMap.get("msg") != "success"){
                return resultMap;
        	}
        }
        return resultMap;
        
    }
    

    @GetMapping(value = "/getShoppingRecords")
    @ResponseBody
    public Map<String, Object> getShoppingRecords(int userId) {
        List<ShoppingRecord> shoppingRecordList = shoppingRecordService.getShoppingRecords(userId);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("data", shoppingRecordList);
        resultMap.put("msg", "success");
        resultMap.put("code", 200);
        return resultMap;
    }

    @RequestMapping(value = "/getAllShoppingRecords", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAllShoppingRecords() {
        List<ShoppingRecord> shoppingRecordList = shoppingRecordService.getAllShoppingRecords();
        String allShoppingRecords = JSONArray.toJSONString(shoppingRecordList);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("allShoppingRecords", allShoppingRecords);
        resultMap.put("msg", "success");
        resultMap.put("code", 200);
        return resultMap;
    }

    @RequestMapping(value = "/deleteShoppingRecord", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteShoppingRecord(Integer id) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String result = "badRequest";
        int code = 500;
        ShoppingRecord shoppingrecord = shoppingRecordService.getShoppingRecord(id);
        if (shoppingrecord == null) {
            result = "unExistShoppingRecord";
            code = 4002;
        } else if (shoppingRecordService.deleteShoppingRecord(id)) {
            result = "success";
            code = 200;
        }
        resultMap.put("msg", result);
        resultMap.put("code", code);
        return resultMap;
    }
}
