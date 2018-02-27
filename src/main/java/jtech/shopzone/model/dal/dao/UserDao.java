/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtech.shopzone.model.dal.dao;

import jtech.shopzone.model.dal.DataBaseStatusEnum;
import java.util.ArrayList;
import jtech.shopzone.model.entity.UserInfoEntity;

/**
 *
 * @author Hanaa
 */
public interface UserDao {

    public DataBaseStatusEnum checkEmail(String email);

    public DataBaseStatusEnum login(String email, String password);

    public DataBaseStatusEnum register(UserInfoEntity user);

    public DataBaseStatusEnum updateUser(UserInfoEntity user);

    public int getUserId(String email);

    public UserInfoEntity getUserInfo(int userId);

    public ArrayList<UserInfoEntity> getUsers();

    public DataBaseStatusEnum updateCreditLimit(int userId, Double value);

}
