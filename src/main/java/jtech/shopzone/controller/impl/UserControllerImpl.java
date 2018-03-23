package jtech.shopzone.controller.impl;

import jtech.shopzone.controller.UserController;
import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.dal.dao.UserDao;
import jtech.shopzone.model.entity.UserInfoEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import jtech.shopzone.model.dal.bean.Userinfo;
import jtech.shopzone.model.dal.dao.impl.UserDaoImpl;
import jtech.shopzone.model.entity.UserInterestsEntity;

public class UserControllerImpl implements UserController {

    private UserDao userDao;

    private UserControllerImpl() {

        userDao = new UserDaoImpl();

    }

    /**
     * Represents factory method for user controller, this is the only method
     * responsible for creating an instance from UserControllerImpl
     *
     * @return
     */
    public static UserControllerImpl newInstance() {
        return new UserControllerImpl();
    }

    @Override
    public Status checkEmail(String email) {
        return userDao.checkEmail(email);
    }

    @Override
    public Status login(String email, String password) {
        return userDao.login(email, password);
    }

    @Override
    public Status register(UserInfoEntity user) {
        Userinfo userInfo = new Userinfo();
        userInfo.setFirstName(user.getFirstName());
        userInfo.setLastName(user.getLastName());
        userInfo.setEmail(user.getEmail());
        userInfo.setPassword(user.getPassword());
        userInfo.setAddress(user.getAddress());
        userInfo.setBirthdate(user.getBirthdate());
        userInfo.setCreditLimit((long) user.getCreditLimit());
        userInfo.setJob(user.getJob());   
        
//        ArrayList<UserInterestsEntity> interests = new ArrayList<UserInterestsEntity>();
//        interests =  user.getInterests();
//        userInfo.setUserInterestses(interests);
        return userDao.register(userInfo);
    }

    @Override
    public Status updateUser(UserInfoEntity user) {
        Userinfo userInfo = new Userinfo();
        userInfo.setUserId(user.getUserId());
        userInfo.setFirstName(user.getFirstName());
        userInfo.setLastName(user.getLastName());
        userInfo.setEmail(user.getEmail());
        userInfo.setPassword(user.getPassword());
        userInfo.setAddress(user.getAddress());
        userInfo.setBirthdate(user.getBirthdate());
        userInfo.setCreditLimit((long) user.getCreditLimit());
        userInfo.setJob(user.getJob());
        return userDao.updateUser(userInfo);
    }

    @Override
    public int getUserId(String email) {
        return userDao.getUserId(email);
    }

    @Override
    public UserInfoEntity getUserInfo(int userId) {
        Userinfo user = userDao.getUserInfo(userId);
        UserInfoEntity userInfo = new UserInfoEntity();
        userInfo.setFirstName(user.getFirstName());
        userInfo.setLastName(user.getLastName());
        userInfo.setEmail(user.getEmail());
        userInfo.setPassword(user.getPassword());
        userInfo.setAddress(user.getAddress());
        userInfo.setBirthdate(user.getBirthdate());
        userInfo.setCreditLimit((long) user.getCreditLimit());
        userInfo.setJob(user.getJob());
        return userInfo;
    }

    @Override
    public ArrayList<UserInfoEntity> getUsers() {
        ArrayList<UserInfoEntity> usersInfo = new ArrayList<>();
        ArrayList<Userinfo> users = userDao.getUsers();
        for (int i = 0; i < users.size(); i++) {
            usersInfo.get(i).setFirstName(users.get(i).getFirstName());
            usersInfo.get(i).setLastName(users.get(i).getLastName());
            usersInfo.get(i).setEmail(users.get(i).getEmail());
            usersInfo.get(i).setPassword(users.get(i).getPassword());
            usersInfo.get(i).setAddress(users.get(i).getAddress());
            usersInfo.get(i).setBirthdate(users.get(i).getBirthdate());
            usersInfo.get(i).setCreditLimit((long) users.get(i).getCreditLimit());
            usersInfo.get(i).setJob(users.get(i).getJob());
        }
        return usersInfo;
    }

    @Override
    public Status updateCreditLimit(int userId, Double value) {
        return userDao.updateCreditLimit(userId, value);
    }

    @Override
    public Status isAdmin(String email, String password) {
        return userDao.isAdmin(email, password);
    }

    @Override
    public int getAdminId(String email) {
        return userDao.getAdminId(email);
    }
    public static void main(String[] args) {
        UserControllerImpl ucon = new UserControllerImpl();
        ArrayList<UserInterestsEntity> interests = new ArrayList<>();
        UserInterestsEntity intre = new UserInterestsEntity(1,"fashon");
        UserInterestsEntity intre1 = new UserInterestsEntity(1,"labs");
        interests.add(intre);
        interests.add(intre1);
       UserInfoEntity user = new UserInfoEntity(1,"dodo","dodo","dodo_didid","egypt",
               new Date(),"123","joooooo",2000,null);
       user.setInterests(interests);
        ucon.register(user);
    }
}
