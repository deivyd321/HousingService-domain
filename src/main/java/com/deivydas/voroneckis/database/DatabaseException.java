/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deivydas.voroneckis.database;

/**
 *
 * @author vdeiv
 */
public class DatabaseException extends RuntimeException {


    DatabaseException(String message, Exception e) {
        super(message);
        System.out.print(e.getStackTrace());
    }
    
}
