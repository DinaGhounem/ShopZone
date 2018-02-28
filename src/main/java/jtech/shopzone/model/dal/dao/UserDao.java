/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtech.shopzone.model.dal.dao;

import jtech.shopzone.model.dal.DBStatus;
import java.util.ArrayList;
import jtech.shopzone.model.entity.UserInfoEntity;

/**
 *
 * @author Hanaa
 */
public interface UserDao {

    public DBStatus checkEmail(String email);

    public DBStatus login(String email, String password);

    public DBStatus register(UserInfoEntity user);

    public DBStatus updateUser(UserInfoEntity user);

    public int getUserId(String email);

    public UserInfoEntity getUserInfo(int userId);

    public ArrayList<UserInfoEntity> getUsers();

    public DBStatus updateCreditLimit(int userId, Double value);

}
