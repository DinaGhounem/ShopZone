/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtech.shopzone.model.dal.dao;

import jtech.shopzone.model.dal.Status;

import java.util.ArrayList;

import jtech.shopzone.model.entity.UserInfoEntity;

/**
 * @author Hanaa
 */
public interface UserDao {

    Status checkEmail(String email);

    Status login(String email, String password);

    Status register(UserInfoEntity user);

    Status updateUser(UserInfoEntity user);

    int getUserId(String email);

    UserInfoEntity getUserInfo(int userId);

    ArrayList<UserInfoEntity> getUsers();

    Status updateCreditLimit(int userId, Double value);
    
    Status isAdmin(String email);

}
