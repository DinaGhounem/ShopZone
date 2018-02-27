/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtech.shopzone.model.entity;

/**
 *
 * @author Dina PC
 */
public class UserInterestsEntity {

    private int userId;
    private String interestName;

    public UserInterestsEntity() {
    }

    public UserInterestsEntity(int userId, String interestName) {
        this.userId = userId;
        this.interestName = interestName;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the interestName
     */
    public String getInterestName() {
        return interestName;
    }

    /**
     * @param interestName the interestName to set
     */
    public void setInterestName(String interestName) {
        this.interestName = interestName;
    }
}
