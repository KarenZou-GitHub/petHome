package com.shopping.controller;

import java.io.Console;
import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.shopping.entity.Pet;
import com.shopping.service.PetService;
import com.shopping.service.ShoppingCarService;
import com.shopping.service.ShoppingRecordService;

/**
 * 方法是：
 * 1、获取所有宠物
 * 2.删除宠物（id）
 * 3.增加宠物（表项）
 * 4.查看宠物详细信息（id, session）
 * 5.搜索宠物（string）
 * 6.上传图片
 * 7.修改宠物信息*************************************************************************8888**还没有呢，注意写
 */

@Controller
public class PetController {
    private static String petName = "";

    @Resource
    private ShoppingCarService shoppingCarService;
    @Resource
    private ShoppingRecordService shoppingRecordService;
    @Resource
    private PetService petService;

    @RequestMapping(value = "/getAllPets")
    @ResponseBody
    public Map<String, Object> getAllPets() {
        List<Pet> petList = new ArrayList<>();
        petList = petService.getAllPet();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("data", petList);
        resultMap.put("msg", "success");
        resultMap.put("code", 200);
        return resultMap;
    }

    @RequestMapping(value = "/deletePet", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deletePet(Integer id) {
        String result = "badRequest";
        int code = 500;
        if (petService.deletePet(id)) {
            result = "success";
            code = 200;
        } else {
            result = "unExistProduct";
            code = 2002;
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("msg", result);
        resultMap.put("code", code);
        return resultMap;
    }

    @RequestMapping(value = "/addPet", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addPet(int type, String name, Date birthday, String breed, String color, String nature, String description, String img, Integer price, Integer relateproduct_id) {
        System.out.println("petname:" + name);
        petName = name;
        Pet pet11 = petService.getPet(name);
        String result = "badRequest";
        int code = 500;
        if (pet11 != null) {
            result = "nameExist";
            code = 2000;
        } else {
            Pet pet = new Pet();
            pet.setType(type);
            pet.setName(name);
            pet.setBirthday(birthday);
            pet.setBreed(breed);
            pet.setColor(color);
            pet.setNature(nature);
            pet.setDescription(description);
            pet.setPrice(price);
            pet.setImg("/Shopping/img/pet/"+img+"。jpg");
            pet.setRelateproduct_id(relateproduct_id);
            petService.addpet(pet);
            result = "success";
            code = 200;
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("msg", result);
        resultMap.put("code", code);
        return resultMap;
    }


    @RequestMapping(value = "/getPetById")
    @ResponseBody
    public Map<String, Object> getPetById(Integer id) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String result = "badRequest";
        int code = 500;
        if (id == null) {
            result = "wrongParam";
            code = 2001;
        } else {
            Pet pet = petService.getPet(id);
            if (pet == null) {
                result = "unExistProduct";
                code = 2002;
            } else {
                String petstr = JSON.toJSONString(pet);
                resultMap.put("pet", petstr);
                result = "success";
                code = 200;
            }
        }
        resultMap.put("msg", result);
        resultMap.put("code", code);
        return resultMap;
    }


    @RequestMapping(value = "/getPetsByType")
    @ResponseBody
    public List<Pet> getPetsByType(int type) {
        // TODO Auto-generated method stub
        return petService.getPetsByType(type);
        //这个是随便写写的
    }

//功能是上传宠物图片，用了springmvc的multipartfile，主要是先找到路径，然后将二进制的数据流换成file，然后存起来就可以了
    @RequestMapping(value = "/uploadPetImg", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadFile(@RequestParam MultipartFile petImgUploadInput, HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String result = "badRequest";
        int code = 500;
        String fileName = petName+ ".jpg";
        try {
            if (petImgUploadInput != null && !petImgUploadInput.isEmpty()) {
            	//首先定义文件路径
            	String fileRealPath = "/usr/local/apache-tomcat-8.5.39/webapps/Shopping/static/img/pet";
            	//创建一个新的文件夹
                File fileFolder = new File(fileRealPath);
                if (!fileFolder.exists()) {
                    fileFolder.mkdirs();
                }
                //在这个文件夹下建一个新的文件
                File file = new File(fileFolder, fileName);
                //这句非常重要，这句是把二进制流转换成file
                petImgUploadInput.transferTo(file);
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
    
    
    @RequestMapping(value = "/searchPet")
    @ResponseBody
    public Map<String, Object> searchPet(String keyWord) {
        List<Pet> petList = new ArrayList<>();
        petList = petService.searchPet(keyWord);
        Map<String, Object> resultMap = new HashMap<String, Object>();        
        resultMap.put("data", petList);
        resultMap.put("msg", "success");
        resultMap.put("code", 200);
        return resultMap;
    }


}
