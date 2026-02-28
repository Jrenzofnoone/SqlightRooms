package com.example.sqlrooms.db.user;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    public UserRepository userRepository;
    public LiveData<List<User>> getAllUsers;
    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        getAllUsers = userRepository.getAllUsers();
    }
    public void insert(User user){
        userRepository.insert(user);
    }
    public void update(User user){
        userRepository.update(user);
    }
    public void delete(User user){
        userRepository.delete(user);
    }
    public void login(String email, String password, UserRepository.LoginCallBack callBack){
        userRepository.login(email, password, callBack);
    }
    public LiveData<List<User>> getAllUsers() {return getAllUsers;}
}
