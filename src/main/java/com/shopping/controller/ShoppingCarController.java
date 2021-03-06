package com.shopping.controller;

import com.alibaba.fastjson.JSONArray;
import com.shopping.entity.ShoppingCar;
import com.shopping.entity.User;
import com.shopping.service.PetService;
import com.shopping.service.ProductService;
import com.shopping.service.ShoppingCarService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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

    @RequestMapping(value = "/addShoppingCar", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addShoppingCar(Integer type, Integer userId, Integer productId, Integer counts, Integer product_price, String product_name) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String result = "badRequest";
        int code = 500;

        ShoppingCar shoppingCar1 = new ShoppingCar();
        shoppingCar1.setUser_id(userId);
        shoppingCar1.setProduct_id(productId);
        shoppingCar1.setCounts(counts);
        shoppingCar1.setType(type);
        shoppingCar1.setProduct_price(product_price);
        shoppingCar1.setProduct_name(product_name);
        try {
            shoppingCarService.addShoppingCar(shoppingCar1);
            result = "success";
            code = 200;
        } catch (Exception e) {
            ShoppingCar shoppingcar2 = shoppingCarService.getShoppingCar(userId, productId);
            shoppingcar2.setCounts(shoppingcar2.getCounts() + 1);
            boolean tf = shoppingCarService.updateShoppingCar(shoppingcar2);
            if (tf) {
                result = "success";
                code = 200;
            } else {
                result = "failUpdate";
                code = 3005;
            }
        }

        resultMap.put("msg", result);
        resultMap.put("code", code);
        return resultMap;
    }

    @RequestMapping(value = "/getShoppingCars", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getShoppingCars(HttpSession httpSession) {
        User currentUser = (User) httpSession.getAttribute("currentUser");
        List<ShoppingCar> shoppingCarList = shoppingCarService.getShoppingCars(currentUser.getId());
        String shoppingCars = JSONArray.toJSONString(shoppingCarList);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("shoppingCars", shoppingCars);
        resultMap.put("msg", "success");
        resultMap.put("code", 200);
        return resultMap;
    }

    //这个是删除整个类别
    @RequestMapping(value = "/deleteShoppingCar", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteShoppingCar(int userId, int productId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String result = "badRequest";
        int code = 500;
        ShoppingCar shoppingcar = shoppingCarService.getShoppingCar(userId, productId);
        if (shoppingcar == null) {
            result = "unExistShoppingCar";
            code = 3002;
        } else {
            shoppingCarService.deleteShoppingCar(userId, productId);
            result = "success";
            code = 200;
        }
        resultMap.put("msg", result);
        resultMap.put("code", code);
        return resultMap;
    }

    //这个是删除一件
    @RequestMapping(value = "/minusOne", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> minuxOne(Integer userId, Integer productId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String result = "badRequest";
        int code = 500;
        ShoppingCar shoppingcar = shoppingCarService.getShoppingCar(userId, productId);
        if (shoppingcar == null) {
            result = "unExistShoppingCar";
            code = 3002;
        } else if (shoppingcar.getCounts() == 1) {
            deleteShoppingCar(userId, productId);
            result = "success";
            code = 200;
        } else {
            shoppingcar.setCounts(shoppingcar.getCounts() - 1);
            if (shoppingCarService.updateShoppingCar(shoppingcar)) {
                result = "success";
                code = 200;
            } else {
                result = "failUpdate";
                code = 3005;
            }
        }
        resultMap.put("msg", result);
        resultMap.put("code", code);
        return resultMap;
    }
}
