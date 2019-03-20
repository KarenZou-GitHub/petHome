package com.shopping.controller;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
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
    private static String pname = "";

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

    @RequestMapping(value = "/deletePet", method = RequestMethod.GET)
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
        pname = name;
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
            pet.setImg(img);
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


    @RequestMapping(value = "/uploadPetImg", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadFile(@RequestParam MultipartFile petImgUpload, String name, HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String result = "badRequest";
        int code = 500;
        try {
            System.out.println(pname + "shagnp" + name);
            if (petImgUpload != null && !petImgUpload.isEmpty()) {
                //TODO:路径是以后需要改的
                String fileRealPath = "E:\\GraduateProject\\Shopping\\Shopping\\src\\main\\webapp\\static\\img\\";
                int id = petService.getPet(pname).getId();
                String fileName = String.valueOf(id) + ".jpg";
                File fileFolder = new File(fileRealPath);
                System.out.println("fileRealPath=" + fileRealPath + "\\" + fileName);
                if (!fileFolder.exists()) {
                    fileFolder.mkdirs();
                }
                File file = new File(fileFolder, fileName);
                petImgUpload.transferTo(file);
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


}
