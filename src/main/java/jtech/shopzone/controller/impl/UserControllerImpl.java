package jtech.shopzone.controller.impl;

import jtech.shopzone.controller.UserController;
import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.dal.dao.UserDao;
import jtech.shopzone.model.entity.UserInfoEntity;

import java.util.ArrayList;
import jtech.shopzone.model.dal.dao.impl.UserDaoImpl;

public class UserControllerImpl implements UserController {
    private UserDao userDao ;
    private UserControllerImpl(){

        userDao = new UserDaoImpl();

    }

    /**
     * Represents factory method for user controller, this is the only
     * method responsible for creating an instance from UserControllerImpl
     * @return
     */
    public static UserControllerImpl newInstance(){
        return new UserControllerImpl();
    }

    @Override
    public Status checkEmail(String email) {
        return userDao.checkEmail(email);
    }

    @Override
    public Status login(String email, String password) {
        return userDao.login(email,password);
    }

    @Override
    public Status register(UserInfoEntity user) {
        return userDao.register(user);
    }

    @Override
    public Status updateUser(UserInfoEntity user) {
        return userDao.updateUser(user);
    }

    @Override
    public int getUserId(String email) {
        return userDao.getUserId(email);
    }

    @Override
    public UserInfoEntity getUserInfo(int userId) {
        return userDao.getUserInfo(userId);
    }

    @Override
    public ArrayList<UserInfoEntity> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public Status updateCreditLimit(int userId, Double value) {
        return userDao.updateCreditLimit(userId,value);
    }

    @Override
    public Status isAdmin(String email, String password) {
        return userDao.isAdmin(email, password);
    }
}
