/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtech.shopzone.model.dal;

/**
 *
 * @author Hanaa
 */
public enum DBStatus {
    success, // action done against database is successfully done
    fail,    // action done against database is not completed meaning
    error   //  this represents that SQL Exception happened
}
