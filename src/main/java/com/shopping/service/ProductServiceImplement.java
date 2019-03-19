package com.shopping.service;

import com.shopping.dao.ProductDao;
import com.shopping.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ���ܣ�
 * 1.��ȡ��Ʒ��Ϣ��id��
 * 2.��ȡ��Ʒ��Ϣ��string��
 * 3��������Ʒ��product��
 * 4.ɾ����Ʒ��product��
 * 5.������Ʒ��Ϣ��product��
 * 6.ѡ��ĳ�ؼ�����Ʒ��string��
 * 7.ѡ��ĳ���Ͳ�Ʒ��typeint��
 */

@Service
public class ProductServiceImplement implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProduct(int id) {
        return productDao.getProduct(id);
    }

    @Override
    public Product getProduct(String name) {
        return productDao.getProduct(name);
    }

    @Override
    public void addProduct(Product product) {
        productDao.addProduct(product);
    }

    @Override
    public boolean deleteProduct(int id) {
        return productDao.deleteProduct(id);
    }

    @Override
    public boolean updateProduct(Product product) {
        return productDao.updateProduct(product);
    }

    @Override
    public List<Product> getProductsByKeyWord(String searchKeyWord) {
        return productDao.getProductsByKeyWord(searchKeyWord);
    }

    @Override
    public List<Product> getProductsByType(int type) {
        return productDao.getProductsByType(type);
    }

    @Override
    public List<Product> getAllProduct() {
        return productDao.getAllProduct();
    }
}
