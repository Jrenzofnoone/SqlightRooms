package com.example.sqlrooms.db.user;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import com.example.sqlrooms.db.AppDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserRepository {
    public UserDao userDao;
    public LiveData<List<User>> getAllUsers;
    public ExecutorService executorService;

    public UserRepository(Application application){
        AppDatabase db = AppDatabase.getInstance(application);
        userDao = db.userDao();
        getAllUsers = userDao.getAllUsers();
        executorService = Executors.newSingleThreadExecutor();
    }
    public void insert(User user){
        executorService.execute( () -> userDao.insert(user));
    }
    public void update(User user){
        executorService.execute( () -> userDao.update(user));
    }
    public void delete(User user){
        executorService.execute( () -> userDao.delete(user));
    }
    public LiveData<User> login(String email, String password){
        return userDao.login(email, password);
    }
    public LiveData<List<User>> getAllUsers() {return getAllUsers;}

}
