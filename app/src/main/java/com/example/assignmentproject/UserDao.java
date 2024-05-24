package com.example.assignmentproject;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Insert
    void registerUser(UserEntity userEntity);

    @Query("SELECT * from users where userName=(:userName) and password=(:password)")
    UserEntity login(String userName,String password);


}
