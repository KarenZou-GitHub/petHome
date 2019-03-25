package com.shopping.controller;

import com.alibaba.fastjson.JSONArray;
import com.shopping.entity.ShoppingCar;
import com.shopping.service.PetService;
import com.shopping.service.ProductService;
import com.shopping.service.ShoppingCarService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 方法
 * 1.添加购物车(id,id)
 * 2.获取购物车(id)
 * 3.删除购物车内选项（id, id）
 */
@Controller
public class ShoppingCarController {
    @Resource
    private ProductService productService;
    @Resource
    private PetService petService;
    @Resource
    private ShoppingCarService shoppingCarService;

    @RequestMapping(value = "/addSupToShoppingCar",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addSupToShoppingCar(int type,int userId,int productId,int counts,int product_price,String product_name){
        
        ShoppingCar shoppingCar1 = new ShoppingCar();
        shoppingCar1.setUser_id(userId);
        shoppingCar1.setProduct_id(productId);
        shoppingCar1.setCounts(counts);
        shoppingCar1.setType(type);
        shoppingCar1.setProduct_price(product_price);
        shoppingCar1.setProduct_name(product_name);
        shoppingCarService.addShoppingCar(shoppingCar1);

        Map<String, Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result","success");
        return resultMap;
    }

    @RequestMapping(value = "/getShoppingCars",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getShoppingCars(int userId){
        List<ShoppingCar> shoppingCarList = shoppingCarService.getShoppingCars(userId);
        String shoppingCars = JSONArray.toJSONString(shoppingCarList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",shoppingCars);
        return resultMap;
    }

    @RequestMapping(value = "/deleteShoppingCar",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteShoppingCar(int userId,int productId){
        shoppingCarService.deleteShoppingCar(userId,productId);
        Map<String, Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result","success");
        return resultMap;
    }
}
