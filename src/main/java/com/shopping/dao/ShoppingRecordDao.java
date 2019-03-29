package com.shopping.dao;

import com.shopping.entity.ShoppingRecord;

import java.util.List;

/**
 * Created by 14437 on 2017/3/3.
 */
public interface ShoppingRecordDao {
    public ShoppingRecord getShoppingRecord(Integer id);

    public void addShoppingRecord(ShoppingRecord shoppingRecord);

    public boolean deleteShoppingRecord(Integer id);

    public List<ShoppingRecord> getShoppingRecords(int userId);

    public List<ShoppingRecord> getAllShoppingRecords();

}
