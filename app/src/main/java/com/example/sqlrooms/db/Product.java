package com.example.sqlrooms.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "products")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "product_name")
    public String productName;
    @ColumnInfo(name = "product_price")
    public int productPrice;

    public Product(int id, String productName, int productPrice) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
}
