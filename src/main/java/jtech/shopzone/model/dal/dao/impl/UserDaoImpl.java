/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtech.shopzone.model.dal.dao.impl;

import java.util.ArrayList;
import java.util.List;

import jtech.shopzone.model.dal.MySessionFactory;
import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.dal.bean.AdminInfo;
import jtech.shopzone.model.dal.bean.Userinfo;
import jtech.shopzone.model.dal.dao.UserDao;
import jtech.shopzone.model.entity.UserInfoEntity;
import jtech.shopzone.model.entity.UserInterestsEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 * @author Dina PC
 */
public class UserDaoImpl implements UserDao {


    private static MySessionFactory sessionFactory;
    private static Session session;

    public UserDaoImpl() {
        if (sessionFactory == null)
            sessionFactory = MySessionFactory.getMySessionFactory();
        if (session == null)
            session = sessionFactory.getSession();
    }

    @Override
    public Status checkEmail(String email) {
        Query q1 = session.createQuery("from Userinfo a where a.email = '" + email + "'");
        List<Userinfo> users = q1.list();
        if (users.size() > 0)
            return Status.NOTOK;
        else
            return Status.OK;
    }

    @Override
    public Status login(String email, String password) {
        Query q1 = session.createQuery("from Userinfo a where a.email = '" + email + "' and a.password = '" + password + " '");
        List<Userinfo> user = q1.list();
        if (user.size() > 0)
            return Status.OK;
        else
            return Status.NOTOK;
    }

    @Override
    public Status register(Userinfo user) {
        session.beginTransaction();
        session.persist(user);
        session.getTransaction().commit();
        return Status.OK;
    }

    @Override
    public Status updateUser(Userinfo user) {
        session.beginTransaction();
        session.merge(user);
        session.getTransaction().commit();
        return Status.OK;
    }

    @Override
    public int getUserId(String email) {
        Query q1 = session.createQuery("from Userinfo a where a.email = '" + email + "'");
        Userinfo user = (Userinfo) q1.uniqueResult();
        int userId = 0;
        if (user != null)
            userId = user.getUserId();
        return userId;
    }

    @Override
    public Userinfo getUserInfo(int userId) {
        Userinfo user = session.load(Userinfo.class, userId);
        return user;
    }

    @Override
    public ArrayList<Userinfo> getUsers() {

        ArrayList<UserInterestsEntity> interests = new ArrayList<>();
        ArrayList<Userinfo> users = new ArrayList<>();
        Query q1 = session.createQuery("from Userinfo");
        users = (ArrayList<Userinfo>) q1.list();
        return users;
    }

    @Override
    public Status updateCreditLimit(int userId, Double value) {
        long oldCredit = 0, newCredit;
        session.beginTransaction();
        Userinfo user = session.load(Userinfo.class, userId);
        Query selectLimit = session.createQuery("select creditLimit from Userinfo where userId = " + userId);

        oldCredit = (long) selectLimit.uniqueResult();
        newCredit = (long) (oldCredit - value);

        user.setCreditLimit(newCredit);
        session.persist(user);
        session.getTransaction().commit();
        return Status.OK;
    }


    @Override
    public Status isAdmin(String email, String password) {
        Query q1 = session.createQuery("from AdminInfo a where a.email = '" + email + "' and a.password = '" + password + " '");
        List<AdminInfo> adminList = q1.list();
        if (adminList.size() > 0)
            return Status.OK;
        else
            return Status.NOTOK;
    }

    @Override
    public int getAdminId(String email) {
        Query q1 = session.createQuery("from AdminInfo a where a.email = '" + email + "'");
        AdminInfo admin = (AdminInfo) q1.uniqueResult();
        int adminId = 0;
        if (admin != null)
            adminId = admin.getAdminId();
        return adminId;
    }

    public static void main(String[] args) {
        UserDaoImpl udao = new UserDaoImpl();
        Userinfo user = session.load(Userinfo.class, 1);
        user.setJob("javaDeveloper");
        System.out.println(udao.updateUser(user));
//        ArrayList<Userinfo> users = udao.getUsers();
//        for(int i=0;i<users.size();i++)
//            System.out.println(users.get(i).getFirstName());
    }
}
