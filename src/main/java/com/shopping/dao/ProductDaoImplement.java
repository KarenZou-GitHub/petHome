package com.shopping.dao;

import com.shopping.entity.Product;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;


@Repository
public class ProductDaoImplement implements ProductDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public Product getProduct(int id) {
        String hql = "from Product where id=? and counts>0";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, id);
        return (Product) query.uniqueResult();
    }

    @Override
    public Product getProduct(String name) {
        String hql = "from Product where name=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,name);
        return (Product) query.uniqueResult();
    }

    @Override
    public void addProduct(Product product) {
        sessionFactory.getCurrentSession().save(product);
    }

    @Override
    public boolean deleteProduct(int id) {
        String hql = "delete Product where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, id);
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean updateProduct(Product product) {
        String hql = "update Product set description=?,keyWord=?,price=?,counts=?,type=? where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,product.getDescription());
        query.setParameter(1,product.getKeyWord());
        query.setParameter(2,product.getPrice());
        query.setParameter(3,product.getCounts());
        query.setParameter(4,product.getType());
        query.setParameter(5,product.getId());
        return query.executeUpdate() > 0;
    }

    //这个是dao层中查询方法，主要就是拼sql语句，然后hibernate执行
    @Override
    public List<Product> getProductsByKeyWord(String searchKeyWord) {
        String queryKeyWord = "%";
        for(int i=0;i<searchKeyWord.length();i++){
            queryKeyWord += String.valueOf(searchKeyWord.charAt(i)) +"%";
        }
        System.out.println("我搜索了"+queryKeyWord);
        //这句是拼接可执行的hibernate语句，比较好的地方时没有必要看数据库里表项的名字，直接使用实体类的变量名就行
        String hql = "from Product where name like ? or description like ?";
        //实例化一个查询接口
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,queryKeyWord);
        query.setParameter(1,queryKeyWord);
        //执行，获取数据
        return query.list();
    }

    @Override
    public List<Product> getProductsByType(int type) {
        String hql = "from Product where type=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,type);
        return query.list();
    }


    @Override
    public List<Product> getAllProduct() {
        String hql = "from Product";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

	@Override
	public boolean setCountdZero(int id) {
		String hql = "update Product set counts = 0 where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,id);	
        return query.executeUpdate() > 0;
	}
}
