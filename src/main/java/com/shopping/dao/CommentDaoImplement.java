package com.shopping.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.shopping.entity.Comment;
import com.shopping.entity.Post;

@Repository
public class CommentDaoImplement implements CommentDao {
	@Resource
    private SessionFactory sessionFactory;
	
	@Override
	public List<Comment> getAllComment() {
        String hql = "from Comment";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
	}

	@Override
	public void addComment(Comment comment) {
		sessionFactory.getCurrentSession().save(comment); 
	}

	@Override
	public boolean deleteComment(int id) {
        String hql = "delete Comment where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, id);
        return query.executeUpdate() > 0;
	}

	@Override
	public Comment getComment(int id) {
        String hql = "from Comment where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, id);
        return (Comment) query.uniqueResult();
	}

	@Override
	public List<Comment> getCommentsByPostId(String id) {
        String hql = "from Comment where post_id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, Integer.valueOf(id));
        return query.list();
	}

}
