package com.shopping.service;

import com.shopping.dao.ShoppingCarDao;
import com.shopping.entity.ShoppingCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ����
 * 1.��ӹ��ﳵ(shoppingcar)����ӹ��ﳵ��int��int��
 * 2.��ȡ���ﳵ(id)
 * 3.���¹��ﳵ��shoppingcar��
 * 4.ɾ�����ﳵ��ѡ�id, id��
 */
@Service
public class ShoppingCarServiceImplement implements ShoppingCarService {
    @Autowired
    private ShoppingCarDao shoppingCarDao;

    @Override
    public void addShoppingCar(ShoppingCar shoppingCar) {
        shoppingCarDao.addShoppingCar(shoppingCar);
    }

    @Override
    public boolean deleteShoppingCar(int userId, int productId) {
        return shoppingCarDao.deleteShoppingCar(userId,productId);
    }

    @Override
    public List<ShoppingCar> getShoppingCars(int userId) {
        return shoppingCarDao.getShoppingCars(userId);
    }

	@Override
	public ShoppingCar getShoppingCar(int userId, int productId) {
		// TODO Auto-generated method stub
		return shoppingCarDao.getShoppingCar(userId,productId);
	}

	@Override
	public boolean updateShoppingCar(ShoppingCar shoppingcar2) {
		// TODO Auto-generated method stub
		return shoppingCarDao.updateShoppingCar(shoppingcar2);
	}
}
