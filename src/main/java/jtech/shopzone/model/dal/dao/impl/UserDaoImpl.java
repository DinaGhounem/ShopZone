/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtech.shopzone.model.dal.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import jtech.shopzone.model.dal.Connection;
import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.dal.dao.UserDao;
import jtech.shopzone.model.entity.UserInfoEntity;

/**
 *
 * @author Dina PC
 */
public class UserDaoImpl implements UserDao {

    @Override
    public Status checkEmail(String email) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            //ps = c.prepareStatement("select FRIENDSREQUESTS.EMAIL from FRIENDSREQUESTS where FRIENDSREQUESTS.MYFRIENDEMAIL = ?");
//            ps.setString(1, verEmail);
            ResultSet rs = ps.executeQuery();
            
        } catch (Exception e) {
            e.printStackTrace();
            return Status.ERROR;
        } /*finally {
            if (ps != null) {
                ps.close();
            }
            if (c != null) {
                c.close();
            }
        }*/
        return Status.NOTOK;
    }

    @Override
    public Status login(String email, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Status register(UserInfoEntity user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Status updateUser(UserInfoEntity user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getUserId(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserInfoEntity getUserInfo(int userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<UserInfoEntity> getUsers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Status updateCreditLimit(int userId, Double value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
