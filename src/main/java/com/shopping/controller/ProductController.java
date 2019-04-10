package com.shopping.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shopping.entity.Product;
import com.shopping.entity.ShoppingCar;
import com.shopping.entity.ShoppingRecord;
import com.shopping.entity.User;
import com.shopping.service.ProductService;
import com.shopping.service.ShoppingCarService;
import com.shopping.service.ShoppingRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 方法是：
 * 1、获取所有产品
 * 2.删除商品（id）
 * 3.增加商品（表项）
 * 4.商品详细信息（id, session）
 * 5.搜索商品（string）
 * 6.上传图片呢
 */

@Controller
public class ProductController {
    private static String productName = "";

    @Resource
    private ShoppingCarService shoppingCarService;
    @Resource
    private ShoppingRecordService shoppingRecordService;
    @Resource
    private ProductService productService;


    @RequestMapping(value = "/getAllProducts")
    @ResponseBody
    public Map<String, Object> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        productList = productService.getAllProduct();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("data", productList);
        resultMap.put("code", 200);
        resultMap.put("msg", "success");
        return resultMap;
    }

   
    @RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteProduct(Integer id) {
        String result = "badRequest";
        int code = 500;
        if (id == null) {
            result = "unExistProduct";
            code = 1002;
        } else if (productService.deleteProduct(id)) {
            result = "success";
            code = 200;
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("msg", result);
        resultMap.put("code", code);
        return resultMap;
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addProduct(String name, String description, String keyWord, Integer price, Integer counts, Integer type, String img, Integer relateproduct_id) {
        
    	String result = "badRequest";
        int code = 500;
        Product product1 = productService.getProduct(name);

        if (product1 != null) {
            result = "nameExsit";
            code = 2000;
        } else {
        	productName = name;
            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setKeyWord(keyWord);
            product.setPrice((int) price);
            product.setCounts((int) counts);
            product.setType((int) type);
            product.setImg(img);
            product.setRelateproduct_id((int) relateproduct_id);
            productService.addProduct(product);
            result = "success";
            code = 200;
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("msg", result);
        resultMap.put("code", code);
        return resultMap;
    }


    @RequestMapping(value = "/getProductById")
    @ResponseBody
    public Map<String, Object> getProductById(Integer id) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Product product = productService.getProduct(id);
        String result = "badRequest";
        int code = 500;
        if (product == null) {
            result = "unExistProduct";
            code = 2002;
        } else {
            String productstr = JSON.toJSONString(product);
            resultMap.put("product", productstr);
            result = "success";
            code = 200;
        }

        resultMap.put("msg", result);
        resultMap.put("code", code);
        return resultMap;
    }


    @RequestMapping(value = "/uploadProductImg", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadProductFile(@RequestParam MultipartFile productImgUploadInput, HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String result = "badRequest";
        int code = 500;
        String fileName = productName +".jpg";
        try {
            if (productImgUploadInput != null && !productImgUploadInput.isEmpty()) {
                String fileRealPath = "/usr/local/apache-tomcat-8.5.39/webapps/Shopping/static/img/supply";
                File fileFolder = new File(fileRealPath);
                if (!fileFolder.exists()) {
                    fileFolder.mkdirs();
                }
                File file = new File(fileFolder, fileName);
                productImgUploadInput.transferTo(file);
                result = "success";
                code = 200;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultMap.put("msg", result);
        resultMap.put("code", code);
        return resultMap;
    }
    

    @RequestMapping(value = "/product_detail")
    public String product_detail() {
        return "product_detail";
    }

}

