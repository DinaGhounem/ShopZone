/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtech.shopzone.model.dal.dao;

import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.entity.UserInfoEntity;

import java.util.ArrayList;
import jtech.shopzone.model.dal.bean.Userinfo;

/**
 * @author Dina
 */
public interface UserDao {

    Status checkEmail(String email);

    Status login(String email, String password);

    Status register(Userinfo user);

    Status updateUser(Userinfo user);

    int getUserId(String email);

    Userinfo getUserInfo(int userId);

    ArrayList<Userinfo> getUsers();

    Status updateCreditLimit(int userId, Double value);
    
    Status isAdmin(String email,String password);
    
    int getAdminId(String email);

}
