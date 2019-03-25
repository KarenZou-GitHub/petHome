package com.shopping.entity;

import java.io.Serializable;

/**
 * Created by 14437 on 2017/3/3.
 */
public class ShoppingCarPriKey implements Serializable {
    private int user_id;
    private int product_id;

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
