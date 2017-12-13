/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.onlineshopping.service;

import com.onlineshopping.DAO.RoleDAO;
import com.onlineshopping.db.DataSource;
import com.onlineshopping.model.Role;
import java.sql.ResultSet;

public class RoleDAOImpl implements RoleDAO{
    
    DataSource ds=new DataSource();
    
    @Override
    public void createRole(Role role) {
        try{
        ds.setCon();
        ds.setSt("insert into Roles(RoleName,userId) values (?,?)");
        ds.getSt().setString(1,role.getRoleName());
        ds.getSt().setInt(2,role.getUserId());
        ds.getSt().executeUpdate();
        ds.getCon().commit();
        ds.getCon().close();
        }catch(Exception e){
        }
    }

    @Override
    public Role[] readRole(Role role) {
        Role[] roles=new Role[3];
        int i=0;
        try{
            ds.setCon();
            ds.setSt("select * from Roles where userId= ?");
            ds.getSt().setInt(1, role.getUserId());
            ResultSet rs= ds.getSt().executeQuery();
            while(rs.next()){
                    role=new Role();
                    role.setRoleName(rs.getString("roleName"));
                    role.setId(rs.getInt("Id"));
                    roles[i]=role;
                    i++;
            }
        }catch(Exception e){}
        return roles;
    }

    @Override
    public void updateRole(Role role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteRole(Role role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String[] arg){
        Role r=new Role("Customer", 23);
                    RoleDAO rdo=new RoleDAOImpl();
                    rdo.createRole(r);
    }
}
