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
            ps = con.prepareStatement("select user_id,email from userinfo where email=?");
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
                DbConnection.closeStatementAndResultSet(ps, rs);
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
            ps = con.prepareStatement("select user_id,email,password from userinfo where email=? and password = ?");
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
                DbConnection.closeStatementAndResultSet(ps, rs);
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
        ResultSet rs1 = null;
        int user_id = 0;
        int effectedRows;
        try {
            con = DbConnection.getConnection();
            /**
             * select the max user_id and add 1 to use it for the new user*
             */
            ps1 = con.prepareStatement("select nvl(max(user_id),0) from userinfo");
            rs1 = ps1.executeQuery();
            if (rs1.next()) {
                user_id = rs1.getInt(1) + 1;
            }
            ps = con.prepareStatement("insert into userinfo"
                    + "(user_id,first_name,Last_name,email,address,birthdate,password,job,credit_limit,user_img) "
                    + "values(?,?,?,?,?,?,?,?,?,?)");
            /**
             * set values to insert statement
             */
            ps.setInt(1, user_id);
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getAddress());
            ps.setDate(6, new java.sql.Date(user.getBirthdate().getTime()));
            ps.setString(7, user.getPassword());
            ps.setString(8, user.getJob());
            ps.setDouble(9, user.getCreditLimit());
            ps.setString(10, user.getUserImg());

            effectedRows = ps.executeUpdate();

            if (effectedRows > 0) {
                return Status.OK;
            } else {
                return Status.NOTOK;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Status.ERROR;
        } finally {
            try {
                DbConnection.closeStatementAndResultSet(ps1, rs1);
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Status updateUser(UserInfoEntity user) {
        Connection con = null;
        PreparedStatement ps = null;
        int effectedRows;
        try {
            con = DbConnection.getConnection();

            ps = con.prepareStatement("update userinfo\n"
                    + "set first_name=?,last_name=?,"
                    + "email=?,address=?,birthdate=?,"
                    + "password=?,job=?,credit_limit=?,user_img=?"
                    + "where user_id=?");
            /**
             * set values to update statement
             */

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getAddress());
            ps.setDate(5, new java.sql.Date(user.getBirthdate().getTime()));
            ps.setString(6, user.getPassword());
            ps.setString(7, user.getJob());
            ps.setDouble(8, user.getCreditLimit());
            ps.setString(9, user.getUserImg());
            ps.setInt(10, user.getUserId());

            effectedRows = ps.executeUpdate();

            if (effectedRows > 0) {
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

            } catch (SQLException ex) {
                Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public int getUserId(String email) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DbConnection.getConnection();
            ps = con.prepareStatement("select user_id,email from userinfo where email='?'");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                DbConnection.closeStatementAndResultSet(ps, rs);
            } catch (SQLException ex) {
                Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public UserInfoEntity getUserInfo(int userId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        UserInfoEntity user = null;
        try {
            con = DbConnection.getConnection();
            ps = con.prepareStatement("select user_id,first_name,last_name,email,address,"
                    + "birthdate,password,job,credit_limit,user_img "
                    + "from userinfo where user_id = ?");
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new UserInfoEntity();
                user.setUserId(rs.getInt(1));
                user.setFirstName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setAddress(rs.getString(5));
                user.setBirthdate(rs.getDate(6));
                user.setPassword(rs.getString(7));
                user.setJob(rs.getString(8));
                user.setCreditLimit(rs.getDouble(9));
                user.setUserImg(rs.getString(10));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DbConnection.closeStatementAndResultSet(ps, rs);
            } catch (SQLException ex) {
                Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return user;
    }

    @Override
    public ArrayList<UserInfoEntity> getUsers() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<UserInfoEntity> users = null;
        try {
            con = DbConnection.getConnection();
            ps = con.prepareStatement("select user_id,first_name,last_name,email,address,"
                    + "birthdate,password,job,credit_limit,user_img "
                    + "from userinfo ");
            rs = ps.executeQuery();
            while (rs.next()) {
                UserInfoEntity user = new UserInfoEntity();
                user.setUserId(rs.getInt(1));
                user.setFirstName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setAddress(rs.getString(5));
                user.setBirthdate(rs.getDate(6));
                user.setPassword(rs.getString(7));
                user.setJob(rs.getString(8));
                user.setCreditLimit(rs.getDouble(9));
                user.setUserImg(rs.getString(10));

                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DbConnection.closeStatementAndResultSet(ps, rs);
            } catch (SQLException ex) {
                Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return users;
    }

    @Override
    public Status updateCreditLimit(int userId, Double value) {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        double oldCredit = 0, newCredit;
        int effectedRows;
        try {
            con = DbConnection.getConnection();

            ps1 = con.prepareStatement("select credit_limit from userinfo where user_id=?");
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                oldCredit=rs.getDouble(1);
            }
            newCredit = oldCredit - value;
            ps = con.prepareStatement("update userinfo "
                    + "set credit_limit=?"
                    + "where user_id=?");
            /**
             * set values to update statement
             */
            ps.setDouble(1, newCredit);
            ps.setInt(2, userId);
            effectedRows = ps.executeUpdate();

            if (effectedRows > 0) {
                return Status.OK;
            } else {
                return Status.NOTOK;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Status.ERROR;
        } finally {
            try {
                DbConnection.closeStatementAndResultSet(ps1, rs);
                ps.close();

            } catch (SQLException ex) {
                Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
