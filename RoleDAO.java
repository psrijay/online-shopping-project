/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.onlineshopping.DAO;
import com.onlineshopping.model.Role;
/**
 *
 * @author iamsu
 */
public interface RoleDAO {
    public void createRole(Role role);
    public Role[] readRole(Role role);
    public void updateRole(Role role);
    public void deleteRole(Role role);
}
