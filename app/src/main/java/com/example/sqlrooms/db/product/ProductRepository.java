package com.example.sqlrooms.db.product;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.sqlrooms.db.AppDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProductRepository {

    private ProductDao productDao;
    private ExecutorService executorService;

    public ProductRepository(Application application){
        AppDatabase db = AppDatabase.getInstance(application);
        productDao = db.productDao();

        executorService = Executors.newSingleThreadExecutor();
    }
    public void insert(Product product){
        executorService.execute(() -> productDao.insert(product));
    }
    public void update(Product product){
        executorService.execute(() -> productDao.update(product));
    }
    public void delete(Product product){
        executorService.execute(() -> productDao.delete(product));
    }
    public LiveData<List<Product>> getAllProducts(int userId){
        return productDao.getallProducts(userId);
    }
}
