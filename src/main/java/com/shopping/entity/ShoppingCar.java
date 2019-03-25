package com.shopping.entity;

import javax.persistence.*;


@Entity
@Table(name="shopping_car")
@IdClass(value=ShoppingCarPriKey.class)
public class ShoppingCar {
	private int user_id;
	private int product_id;
	private int type;
    private int counts;
    private String product_name;
    private int product_price;

  
    @Id
    @Column(name="user_id")
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    
    @Column(name="product_id")
    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
    
    
    @Column(name="type")
    public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Column(name="product_price")
    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    @Column(name="counts")
    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }
    
    @Column(name="product_name")
    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
}
