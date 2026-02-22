package com.example.sqlrooms.db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProductRepository {

    private ProductDao productDao;
    private LiveData<List<Product>> allProducts;
    private ExecutorService executorService;

    public ProductRepository(Application application){
        ProductDatabase db = ProductDatabase.getInstance(application);
        productDao = db.productDao();

        allProducts = productDao.getallProducts();
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
    public LiveData<List<Product>> getAllProducts(){
        return allProducts;
    }
}
