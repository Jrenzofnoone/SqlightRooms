package com.example.sqlrooms.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sqlrooms.db.product.Product;
import com.example.sqlrooms.db.product.ProductDao;
import com.example.sqlrooms.db.user.User;
import com.example.sqlrooms.db.user.UserDao;

@Database(entities = {Product.class, User.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public abstract ProductDao productDao();

    public abstract UserDao userDao();

    public static AppDatabase getInstance(Context context){
        if (INSTANCE == null){
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "app_database"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}
