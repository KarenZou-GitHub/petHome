package com.shopping.dao;

import com.shopping.entity.ShoppingCar;

import java.util.List;

/**
 * Created by 14437 on 2017/3/3.
 */
public interface ShoppingCarDao {

    public void addShoppingCar(ShoppingCar shoppingCar);

    public boolean deleteShoppingCar(int userId,int productId);

    public List<ShoppingCar> getShoppingCars(int userId);
}
