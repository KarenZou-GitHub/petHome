package com.shopping.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.shopping.entity.Product;
import com.shopping.entity.ShoppingCar;
import com.shopping.entity.ShoppingRecord;
import com.shopping.entity.User;
import com.shopping.service.ProductService;
import com.shopping.service.ShoppingCarService;
import com.shopping.service.ShoppingRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
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
    private static String pname="";

    @Resource
    private ShoppingCarService shoppingCarService;
    @Resource
    private ShoppingRecordService shoppingRecordService;
    @Resource
    private ProductService productService;
    
    @RequestMapping(value = "/getAllProducts")
    @ResponseBody
    public Map<String,Object> getAllProducts(){
        List<Product> productList = new ArrayList<>();
        productList = productService.getAllProduct();
        String allProducts = JSONArray.toJSONString(productList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("allProducts",allProducts);
        return resultMap;
    }


    @RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteProduct(int id) {
        String result ="fail";
        if(productService.deleteProduct(id)){
            result="success";
        }
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addProduct(String name,String description,String keyWord,int price,int counts,int type,String img,int relateproduct_id) {
        System.out.println("娣诲姞浜嗗晢鍝侊細"+name);
        pname=name;
        String result ="fail";
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setKeyWord(keyWord);
        product.setPrice(price);
        product.setCounts(counts);
        product.setType(type);
        product.setImg(img);
        product.setRelateproduct_id(relateproduct_id);
        productService.addProduct(product);
        result = "success";
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }

    @RequestMapping(value = "/productDetail", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> productDetail(int id, HttpSession httpSession) {
        System.out.println("I am here!"+id);
        Product product = productService.getProduct(id);
        httpSession.setAttribute("productDetail",product);
        System.out.print("I am here"+product.getName());
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result","success");
        return resultMap;
    }
    
    /*现在好像没见过用的地方*/
    @RequestMapping(value = "/getProductById", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getProductById(int id) {
        Product product = productService.getProduct(id);
        String result = JSON.toJSONString(product);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }

    @RequestMapping(value = "/product_detail")
    public String product_detail() {
        return "product_detail";
    }

    @RequestMapping(value = "/searchPre", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> searchPre(String searchKeyWord,HttpSession httpSession) {
        httpSession.setAttribute("searchKeyWord",searchKeyWord);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result","success");
        return resultMap;
    }

    @RequestMapping(value = "/search")
    public String search() {
        return "search";
    }

    @RequestMapping(value = "/searchProduct", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> searchProduct(String searchKeyWord){
        System.out.println("鎴戝埌浜哠earchProduct"+searchKeyWord);
        List<Product> productList = new ArrayList<Product>();
        productList = productService.getProductsByKeyWord(searchKeyWord);
        String searchResult = JSONArray.toJSONString(productList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",searchResult);
        System.out.println("鎴戣繑鍥炰簡"+searchResult);
        return resultMap;
    }


    @RequestMapping(value = "/uploadProductFile", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadProductFile(@RequestParam MultipartFile productImgUpload,String name, HttpServletRequest request) {
        String result = "fail";
        try{
            System.out.println(pname+"shagnp"+name);
            if(productImgUpload != null && !productImgUpload.isEmpty()) {
                String fileRealPath = "E:\\GraduateProject\\Shopping\\Shopping\\src\\main\\webapp\\static\\img\\";
                int id = productService.getProduct(pname).getId();
                String fileName = String.valueOf(id)+".jpg";
                File fileFolder = new File(fileRealPath);
                System.out.println("fileRealPath=" + fileRealPath+"\\"+fileName);
                if(!fileFolder.exists()){
                    fileFolder.mkdirs();
                }
                File file = new File(fileFolder,fileName);
                productImgUpload.transferTo(file);
                result = "success";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }
    
    
    

    //没用到。不过这么复杂可以留着参考
   @RequestMapping(value = "/getAllProductsandRecomand")
    @ResponseBody
    public Map<String,Object> getAllProductsandRecomand(int userId){
	   /*System.out.println("鐢ㄦ埛鐨刬d锛�"+userId);
        List<Product> productList = new ArrayList<>();
        List<Product> productTem = new ArrayList<>();
        List<Product> productRcommand = new ArrayList<>();
        productList = productService.getAllProduct();

        if (userId!=0) {
            List<ShoppingCar> shoppingCarList = shoppingCarService.getShoppingCars(userId);
            List<ShoppingRecord> shoppingRecordList = shoppingRecordService.getShoppingRecords(userId);

            if (shoppingCarList.size()>0){
                Product pp = productService.getProduct(shoppingCarList.get(0).getProductId());
                productRcommand.add(pp);
            }
            if (shoppingRecordList.size()>0&&shoppingCarList.size()<0){
                Product pp = productService.getProduct(shoppingRecordList.get(0).getProductId());
                productRcommand.add(pp);
            }else if (shoppingRecordList.size()>0&&shoppingCarList.size()>0){
                if (shoppingCarList.get(0).getProductId()!=shoppingRecordList.get(0).getProductId()){
                    Product pp = productService.getProduct(shoppingRecordList.get(0).getProductId());
                    productRcommand.add(pp);
                }else if (shoppingRecordList.size()>1){
                    Product pp = productService.getProduct(shoppingRecordList.get(1).getProductId());
                    productRcommand.add(pp);
                }
            }
            if (productRcommand.size()>0){
                for (int i=0;i<productRcommand.size();i++){
                    String namet=productRcommand.get(i).getName();
                    String namee=namet;
                    if (namet.length()>3){
                         namee=namet.substring(0,3);
                    }else {
                         namee=namet;
                    }

                    String descrip=productRcommand.get(i).getDescription().split(",")[0];
                    int typee=productRcommand.get(i).getType();
                    String words=productRcommand.get(i).getKeyWord().split(";")[0];

                    List<Product> ppt =productService.getProductsByType(typee);
                    if (ppt.size()>0){

                        Product ppv=ppt.get(0);
                        if (ppv!=null&&ppv.getName()!=null&&!ppv.getName().equals("")) {
                            ppv.setType(8);
                            productTem.add(ppv);
                        }
                    }
                    List<Product> ppw =productService.getProductsByKeyWord(""+words);
                    if (ppw.size()>0){
                        Product ppv=ppw.get(0);
                        if (ppv!=null&&ppv.getName()!=null&&!ppv.getName().equals("")) {
                            ppv.setType(8);
                            productTem.add(ppv);
                        }
                    }
                    Product ppn =productService.getProduct(""+namee);
                    if (ppn!=null&&ppn.getName()!=null&&!ppn.getName().equals("")){
                        Product ppv=ppn;
                        if (ppv!=null&&ppv.getName()!=null&&!ppv.getName().equals("")) {
                            ppv.setType(8);
                            productTem.add(ppv);
                        }
                    }
                    List<Product> ppd =productService.getProductsByKeyWord(""+descrip);
                    if (ppd.size()>0){
                        Product ppv=ppd.get(0);
                        if (ppv!=null&&ppv.getName()!=null&&!ppv.getName().equals("")) {
                            ppv.setType(8);
                            productTem.add(ppv);
                        }
                    }
                }
            }
            int popul[]=new int[9];
            for (int i=0;i<popul.length;i++){
                popul[i]=0;
            }
            for (int j=0;j<productList.size();j++){
                if (productList.get(j).getType()==1){
                    popul[0]+=1;
                }else if (productList.get(j).getType()==2){
                    popul[1]+=1;
                }else if (productList.get(j).getType()==3){
                    popul[2]+=1;
                }else if (productList.get(j).getType()==4){
                    popul[3]+=1;
                }else if (productList.get(j).getType()==5){
                    popul[4]+=1;
                }else if (productList.get(j).getType()==6){
                    popul[5]+=1;
                }else if (productList.get(j).getType()==7){
                    popul[6]+=1;
                }
            }
            int tag=0;
            for (int n=0;n<7;n++){
                if (popul[n]>=tag){
                    tag=n;
                }
            }
            Product popluar=null;
            for (int h=0;h<productList.size();h++){
                if (productList.get(h).getType()==tag){
                    Product pt=productList.get(h);
                    boolean tt=false;
                    for (int r=0;r<productTem.size();r++){
                        if (productTem.get(r).getId()!=pt.getId()){
                            tt=true;
                            popluar=pt;
                            break;
                        }
                    }
                }
            }
            for (int u=0;u<productTem.size();u++){
                productList.add(productTem.get(u));
            }
            if (popluar!=null&&popluar.getName()!=null){
                productList.add(popluar);
            }
        }
        String allProducts = JSONArray.toJSONString(productList);*/
        Map<String,Object> resultMap = new HashMap<String,Object>();
       /* resultMap.put("allProducts",allProducts);*/
        resultMap.put("allProducts","");
        return resultMap;
    }

}

