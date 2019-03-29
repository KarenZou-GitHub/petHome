package com.shopping.entity;

import java.sql.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="shopping_record")
@IdClass(value=ShoppingRecordPriKey.class)
public class ShoppingRecord {
	private int id;
    private int user_id;
    private int product_id;
    private String time;
    private int type;
    private String product_name;
    private int product_price;
    private int counts;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment") //设置主键自增
    @GeneratedValue(generator = "generator")
    @Column(name="id")
    public int getId(){
    	return id;
    }
    public void setId(int id){
    	this.id = id;
    }
    
    @Column(name="user_id")
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int userId) {
        this.user_id = userId;
    }

    
    @Column(name="product_id")
    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int productId) {
        this.product_id = productId;
    }
    
    
    @Column(name="time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
    @Column(name="type")
    public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

    @Column(name="product_name")
    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String productName) {
        this.product_name = productName;
    }

    @Column(name="product_price")
    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int productPrice) {
        this.product_price = productPrice;
    }

    @Column(name="counts")
    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }


}
