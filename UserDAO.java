/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.onlineshopping.DAO;
import com.onlineshopping.model.User;
import java.sql.SQLException;
/**
 *
 * @author iamsu
 */
public interface UserDAO {
    public int create(User u);
    public User read(User u);
    public int update(User u);
    public int delete(User u);
    public boolean validateUser(User u,String OTP);
    public boolean isNewUser(User u);
}
