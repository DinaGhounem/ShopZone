/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtech.shopzone.model.dal.dao;

import jtech.shopzone.model.dal.DataBaseStatusEnum;
import java.util.ArrayList;

/**
 *
 * @author Hanaa
 */
public interface UserDao {
     

    DataBaseStatusEnum checkMail(String email);

    DataBaseStatusEnum login(String email, String password);

    DataBaseStatusEnum register(User user);

    DataBaseStatusEnum updateUser(User user);

    int getUserId(String mail);

    User getUserInfo(int userId);

    ArrayList<User> getUsers();

    DataBaseStatusEnum updateCreditLimit(int userId, Double value);

}
