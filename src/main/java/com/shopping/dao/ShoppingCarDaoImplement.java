package com.shopping.dao;

import com.shopping.entity.ShoppingCar;
import com.shopping.entity.User;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

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
        String hql = "delete ShoppingCar where user_id=? and product_id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(1, productId);
        return query.executeUpdate() > 0;
    }

    @Override
    public List<ShoppingCar> getShoppingCars(int userId) {
        String hql = "from ShoppingCar where user_id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,userId);
        return query.list();
    }

	@Override
	public ShoppingCar getShoppingCar(int userId, int productId) {
        String hql = "from ShoppingCar where user_id=? and product_id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, userId);
        query.setParameter(1, productId);
        return (ShoppingCar)query.uniqueResult();
	}
	
	public boolean updateShoppingCar(ShoppingCar shoppingcar){
        String hql = "update ShoppingCar set counts=? where user_id=? and product_id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,shoppingcar.getCounts());
        query.setParameter(1,shoppingcar.getUser_id());
        query.setParameter(2,shoppingcar.getProduct_id());
        return query.executeUpdate() > 0;
	}
}
