package com.example.sqlrooms.db.product;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.sqlrooms.db.user.User;

import java.io.Serializable;

@Entity(tableName = "products",
foreignKeys = @ForeignKey(
        entity = User.class,
        parentColumns = "id",
        childColumns = "user_id",
        onDelete = ForeignKey.CASCADE
))
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "product_name")
    public String productName;
    @ColumnInfo(name = "product_price")
    public int productPrice;
    @ColumnInfo(name = "user_id")
    public int userId;

    public Product(int id, String productName, int productPrice, int userId) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
