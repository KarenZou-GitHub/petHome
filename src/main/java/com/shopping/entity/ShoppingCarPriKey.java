package com.shopping.entity;

import java.io.Serializable;

import javax.persistence.Column;

public class ShoppingCarPriKey implements Serializable {
    private int user_id;
    private int product_id;
    private int type;

    public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	
    public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShoppingCarPriKey)) return false;

        ShoppingCarPriKey that = (ShoppingCarPriKey) o;

        if (user_id != that.user_id) return false;
        return product_id == that.product_id;

    }

    @Override
    public int hashCode() {
        int result = user_id;
        result = 31 * result + product_id;
        return result;
    }
}
