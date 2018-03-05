package jtech.shopzone.controller;

import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.entity.UserInfoEntity;

import java.util.ArrayList;

public interface UserController {

    Status checkEmail(String email);

    Status login(String email, String password);

    Status register(UserInfoEntity user);

    Status updateUser(UserInfoEntity user);

    int getUserId(String email);

    UserInfoEntity getUserInfo(int userId);

    ArrayList<UserInfoEntity> getUsers();

    Status updateCreditLimit(int userId, Double value);
    
    Status isAdmin(String email,String password);
}
