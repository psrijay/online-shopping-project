/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.onlineshopping.model;

/**
 *
 * @author iamsu
 */
public class Role {
    private int id;
    private String roleName;
    private int userId;

    public Role(String roleName, int userId) {
        this.roleName = roleName;
        this.userId = userId;
    }

    public Role(int userId) {
        this.userId = userId;
    }

    public Role() {
    }
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    
}
