package com.shopping.service;

import com.shopping.dao.ShoppingCarDao;
import com.shopping.entity.ShoppingCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 方法
 * 1.添加购物车(shoppingcar)、添加购物车（int，int）
 * 2.获取购物车(id)
 * 3.更新购物车（shoppingcar）
 * 4.删除购物车内选项（id, id）
 */
@Service
public class ShoppingCarServiceImplement implements ShoppingCarService {
    @Autowired
    private ShoppingCarDao shoppingCarDao;
    @Override
    public ShoppingCar getShoppingCar(int userId, int productId) {
        return shoppingCarDao.getShoppingCar(userId,productId);
    }

    @Override
    public void addShoppingCar(ShoppingCar shoppingCar) {
        shoppingCarDao.addShoppingCar(shoppingCar);
    }

    @Override
    public boolean deleteShoppingCar(int userId, int productId) {
        return shoppingCarDao.deleteShoppingCar(userId,productId);
    }

    @Override
    public boolean updateShoppingCar(ShoppingCar shoppingCar) {
        return shoppingCarDao.updateShoppingCar(shoppingCar);
    }

    @Override
    public List<ShoppingCar> getShoppingCars(int userId) {
        return shoppingCarDao.getShoppingCars(userId);
    }
}
