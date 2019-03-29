package com.shopping.service;

import com.shopping.dao.ShoppingRecordDao;
import com.shopping.entity.ShoppingRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ����
 * 1������һ�������¼
 * 2.�޸Ĺ����¼
 * 3.ɾ�������¼
 * 4.���ݹ����¼״̬��ȡ�����¼
 * 5.��ȡ���еĹ����¼
 * 6.��ȡ�����û��Ĺ����¼
 */
@Service
public class ShoppingRecordServiceImplement implements ShoppingRecordService {
    @Autowired
    private ShoppingRecordDao shoppingRecordDao;
    @Override
    public ShoppingRecord getShoppingRecord(int id) {
        return shoppingRecordDao.getShoppingRecord(id);
    }

    @Override
    public void addShoppingRecord(ShoppingRecord shoppingRecord) {
        shoppingRecordDao.addShoppingRecord(shoppingRecord);
    }

    @Override
    public boolean deleteShoppingRecord(Integer id) {
        return shoppingRecordDao.deleteShoppingRecord(id);
    }

    @Override
    public List<ShoppingRecord> getShoppingRecords(int userId) {
        return shoppingRecordDao.getShoppingRecords(userId);
    }

    @Override
    public List<ShoppingRecord> getAllShoppingRecords() {
        return shoppingRecordDao.getAllShoppingRecords();
    }

}
