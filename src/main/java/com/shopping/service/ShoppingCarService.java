package com.shopping.service;

import com.shopping.entity.ShoppingCar;

import java.util.List;

/**
 * Created by 14437 on 2017/3/3.
 */
public interface ShoppingCarService {

	public void addShoppingCar(ShoppingCar shoppingCar);

    public boolean deleteShoppingCar(int userId, int productId);

    public List<ShoppingCar> getShoppingCars(int userId);

	public ShoppingCar getShoppingCar(int userId, int productId);

	public boolean updateShoppingCar(ShoppingCar shoppingcar2);
}
