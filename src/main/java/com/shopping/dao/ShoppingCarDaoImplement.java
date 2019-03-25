package com.shopping.dao;

import com.shopping.entity.ShoppingCar;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 14437 on 2017/3/3.
 */

@Repository
public class ShoppingCarDaoImplement implements ShoppingCarDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public void addShoppingCar(ShoppingCar shoppingCar) {
        sessionFactory.getCurrentSession().save(shoppingCar);
    }

    @Override
    public boolean deleteShoppingCar(int userId, int productId) {
        String hql = "delete ShoppingCar where userId=? and productId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(1, productId);
        return query.executeUpdate() > 0;
    }

    @Override
    public List<ShoppingCar> getShoppingCars(int userId) {
        String hql = "from ShoppingCar where userId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,userId);
        return query.list();
    }
}
