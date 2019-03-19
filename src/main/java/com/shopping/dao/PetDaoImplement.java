package com.shopping.dao;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.shopping.entity.Pet;
import com.shopping.entity.Product;

@Repository
public class PetDaoImplement implements PetDao {
    @Resource
    private SessionFactory sessionFactory;
	
	
	@Override
	public Pet getPet(String pname) {
        String hql = "from Pet where name=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,pname);
        return (Pet) query.uniqueResult();
	}

	@Override
	public Pet getPet(int id) {
		String hql = "from Pet where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, id);
        return (Pet) query.uniqueResult();
	}

	@Override
	public List<Pet> getAllPet() {
        String hql = "from Pet";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
	}

	@Override
	public void addpet(Pet pet) {
		sessionFactory.getCurrentSession().save(pet);
	}

	@Override
	public boolean deletePet(int id) {
        String hql = "delete Pet where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, id);
        return query.executeUpdate() > 0;
	}

	@Override
	public boolean updatePet(Pet pet) {
		// type=?,name=?,birthday=?,breed=?,color=?,nature=?  这几个不能改，这是固有属性
        String hql = "update Pet set description=?,img=?,price=?,relateproduct_id=? where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, pet.getDescription());
        query.setParameter(1, pet.getImg());
        query.setParameter(2, pet.getPrice());
        query.setParameter(3, pet.getRelateproduct_id());
        query.setParameter(4, pet.getId());
        return query.executeUpdate() > 0;
	}

	@Override
	public List<Pet> getPetsByKeyWord(String searchKeyWord) {
        String queryKeyWord = "%";
        for(int i=0;i<searchKeyWord.length();i++){
            queryKeyWord += String.valueOf(searchKeyWord.charAt(i)) +"%";
        }
        System.out.println("我搜索了:"+queryKeyWord);
        //这里的type是个数字，之后可以改成字符串，就好认一些
        String hql = "from Pet where name like ? or nature like ? or breed like ? or type like ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,queryKeyWord);
        query.setParameter(1,queryKeyWord);
        query.setParameter(2,queryKeyWord);
        query.setParameter(3,queryKeyWord);
        return query.list();
	}
	
    @Override
    public List<Pet> getPetsByType(int type) {
        String hql = "from Pet where type=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,type);
        return query.list();
    }

}
