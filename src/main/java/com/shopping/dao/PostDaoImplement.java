package com.shopping.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.shopping.entity.Post;
import com.shopping.entity.Product;

@Repository
public class PostDaoImplement implements PostDao {

    @Resource
    private SessionFactory sessionFactory;
    
	@Override
	public List<Post> getAllPosts() {
        String hql = "from Post";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
	}

	@Override
	public void addPost(Post post) {
		sessionFactory.getCurrentSession().save(post); 
	}

	@Override
	public boolean deletePost(int id) {
        String hql = "delete Post where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, id);
        return query.executeUpdate() > 0;
	}

	@Override
	public Post getPost(int id) {
        String hql = "from Post where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, id);
        return (Post) query.uniqueResult();
		
	}

	@Override
	public Post getPost(String name) {
        String hql = "from Post where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, name);
        return (Post) query.uniqueResult();
	}

	@Override
	public boolean updatePost(Post post) {
        String hql = "update Post set title=?,content=? where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,post.getTitle());
        query.setParameter(1,post.getContent());
        query.setParameter(2,post.getId());
        return query.executeUpdate() > 0;
	}

}
