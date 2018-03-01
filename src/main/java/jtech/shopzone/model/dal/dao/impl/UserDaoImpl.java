/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtech.shopzone.model.dal.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jtech.shopzone.model.dal.DbConnection;
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
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DbConnection.getConnection();
            ps = con.prepareStatement("select user_id,email from userinfo where email='?'");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                return Status.OK;
            } else {
                return Status.NOTOK;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Status.ERROR;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Status login(String email, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DbConnection.getConnection();
            ps = con.prepareStatement("select user_id,email,password from userinfo where email='?' and password = ?");
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                return Status.OK;
            } else {
                return Status.NOTOK;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Status.ERROR;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Status register(UserInfoEntity user) {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        int user_id = 0;
        try {
            con = DbConnection.getConnection();
            /**
             * select the max user_id and add 1 to use it for the new user*
             */
            ps1 = con.prepareStatement("select nvl(max(user_id),0) from userinfo");
            rs1 = ps1.executeQuery();
            if (rs1.next()) {
                user_id = rs.getInt(1) + 1;
            }
            rs1.close();
            ps1.close();
            
            ps = con.prepareStatement("insert into userinfo"
                    + "(user_id,first_name,Last_name,email,address,birthdate,password,job,credit_limit,user_img) "
                    + "values(?,?,?,?,?,?,?,?,?,?)");
            /**
             * set values to update statement
             */
            ps.setInt(1,user_id);
            ps.setString(2,user.getFirstName());
            ps.setString(3,user.getLastName());
            ps.setString(4,user.getEmail());
            rs = ps.executeQuery();
            if (rs.next()) {
                return Status.OK;
            } else {
                return Status.NOTOK;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Status.ERROR;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
