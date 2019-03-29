package com.shopping.service;

import com.shopping.entity.ShoppingRecord;

import java.util.List;

/**
 * Created by 14437 on 2017/3/3.
 */
public interface ShoppingRecordService {
    public ShoppingRecord getShoppingRecord(int id);

    public void addShoppingRecord(ShoppingRecord shoppingRecord);

    public boolean deleteShoppingRecord(Integer id);

    public List<ShoppingRecord> getShoppingRecords(int userId);

    public List<ShoppingRecord> getAllShoppingRecords();
}
