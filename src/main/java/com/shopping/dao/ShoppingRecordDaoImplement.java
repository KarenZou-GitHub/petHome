package com.shopping.dao;

import com.shopping.entity.ShoppingCar;
import com.shopping.entity.ShoppingRecord;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 14437 on 2017/3/3.
 */
@Repository
public class ShoppingRecordDaoImplement implements ShoppingRecordDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public ShoppingRecord getShoppingRecord(Integer id) {
        String hql = "from ShoppingRecord where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, id);
        return (ShoppingRecord) query.uniqueResult();
    }

    @Override
    public void addShoppingRecord(ShoppingRecord shoppingRecord) {
        sessionFactory.getCurrentSession().save(shoppingRecord);
    }

    @Override
    public boolean deleteShoppingRecord(Integer id) {
        String hql = "delete ShoppingRecord where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, id);
        return query.executeUpdate() > 0;
    }

    @Override
    public List<ShoppingRecord> getShoppingRecords(int userId) {
        String hql = "from ShoppingRecord where user_id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,userId);
        return query.list();
    }

    @Override
    public List<ShoppingRecord> getAllShoppingRecords() {
        String hql = "from ShoppingRecord";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

}
