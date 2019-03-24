package com.shopping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;


public class ShoppingRecordPriKey implements Serializable {
    private int user_id;
    private int product_id;
    private String time;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int userId) {
        this.user_id = userId;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int productId) {
        this.product_id = productId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShoppingRecordPriKey)) return false;

        ShoppingRecordPriKey that = (ShoppingRecordPriKey) o;

        if (getUser_id() != that.getUser_id()) return false;
        if (getProduct_id() != that.getProduct_id()) return false;
        return getTime().equals(that.getTime());

    }

    @Override
    public int hashCode() {
        int result = getUser_id();
        result = 31 * result + getProduct_id();
        result = 31 * result + getTime().hashCode();
        return result;
    }
}
