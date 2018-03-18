/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtech.shopzone.model.dal.dao.impl;

import java.util.ArrayList;
import jtech.shopzone.model.dal.MySessionFactory;
import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.dal.dao.UserDao;
import jtech.shopzone.model.entity.UserInfoEntity;
import jtech.shopzone.model.entity.UserInterestsEntity;
import org.hibernate.Session;

/**
 *
 * @author Dina PC
 */
public class UserDaoImpl implements UserDao {

    MySessionFactory sessionFactory = MySessionFactory.getMySessionFactory();
    Session session = sessionFactory.getSession();
    @Override
    public Status checkEmail(String email) {
        session.beginTransaction();
        //session.load()
        return Status.NOTOK;
    }

    @Override
    public Status login(String email, String password) {
        return Status.NOTOK;
    }

    @Override
    public Status register(UserInfoEntity user) {
        return Status.NOTOK;
    }

    @Override
    public Status updateUser(UserInfoEntity user) {
        return Status.NOTOK;
    }

    @Override
    public int getUserId(String email) {
        return 0;
    }

    @Override
    public UserInfoEntity getUserInfo(int userId) {
        UserInfoEntity user = null;
        return user;
    }

    @Override
    public ArrayList<UserInfoEntity> getUsers() {

        ArrayList<UserInterestsEntity> interests = new ArrayList<>();
        ArrayList<UserInfoEntity> users = new ArrayList<>();
        ;

        return users;
    }

    @Override
    public Status updateCreditLimit(int userId, Double value) {
        return Status.NOTOK;
    }


    @Override
    public Status isAdmin(String email, String password) {
        return Status.NOTOK;
    }

    @Override
    public int getAdminId(String email) {
        return 0;
    }
}
