package jtech.shopzone.model.dal.bean;
// Generated Mar 16, 2018 2:19:44 PM by Hibernate Tools 4.3.1


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Userinfo generated by hbm2java
 */
public class Userinfo  implements java.io.Serializable {


     private Integer userId;
     private String firstName;
     private String lastName;
     private String email;
     private String address;
     private Date birthdate;
     private String password;
     private String job;
     private Long creditLimit;
     private String userImg;
     private Set userProductses = new HashSet(0);
     private Set shoppingCarts = new HashSet(0);
     private Set userInterestses = new HashSet(0);

    public Userinfo() {
    }

	
    public Userinfo(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
    public Userinfo(String firstName, String lastName, String email, String address, Date birthdate, String password, String job, Long creditLimit, String userImg, Set userProductses, Set shoppingCarts, Set userInterestses) {
       this.firstName = firstName;
       this.lastName = lastName;
       this.email = email;
       this.address = address;
       this.birthdate = birthdate;
       this.password = password;
       this.job = job;
       this.creditLimit = creditLimit;
       this.userImg = userImg;
       this.userProductses = userProductses;
       this.shoppingCarts = shoppingCarts;
       this.userInterestses = userInterestses;
    }
   
    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    public Date getBirthdate() {
        return this.birthdate;
    }
    
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public String getJob() {
        return this.job;
    }
    
    public void setJob(String job) {
        this.job = job;
    }
    public Long getCreditLimit() {
        return this.creditLimit;
    }
    
    public void setCreditLimit(Long creditLimit) {
        this.creditLimit = creditLimit;
    }
    public String getUserImg() {
        return this.userImg;
    }
    
    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }
    public Set getUserProductses() {
        return this.userProductses;
    }
    
    public void setUserProductses(Set userProductses) {
        this.userProductses = userProductses;
    }
    public Set getShoppingCarts() {
        return this.shoppingCarts;
    }
    
    public void setShoppingCarts(Set shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }
    public Set getUserInterestses() {
        return this.userInterestses;
    }
    
    public void setUserInterestses(Set userInterestses) {
        this.userInterestses = userInterestses;
    }




}


