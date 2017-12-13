/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.onlineshopping.service;

import com.onlineshopping.DAO.CategoryDAO;
import com.onlineshopping.db.DataSource;
import com.onlineshopping.model.Category;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO{

    DataSource ds=new DataSource();
    
    @Override
    public void create(Category c) {
        try{
        ds.setCon();
        ds.setSt("insert into Categories(name,description) values (?,?)");
        ds.getSt().setString(1,c.getName());
        ds.getSt().setString(2,c.getDescription());
        ds.getSt().executeUpdate();
        ds.getCon().commit();
        ds.getCon().close();
        }catch(Exception e){
        }
    }

    @Override
    public Category read(Category c) {
        try{
            ds.setCon();
            ds.setSt("select * from Categories where Id= ?");
            ds.getSt().setInt(1, c.getId());
            ResultSet rs= ds.getSt().executeQuery();
            if(rs.next()){
                    c.setName(rs.getString("name"));
                    c.setDescription(rs.getString("description"));
            }
        }catch(Exception e){}
        return c;
    }

    @Override
    public void update(Category c) {
        try{
        ds.setCon();
        ds.setSt("update Categories set name=?,description=? where id=?");
        ds.getSt().setString(1,c.getName());
        ds.getSt().setString(2,c.getDescription());
        ds.getSt().setInt(3, c.getId());
        ds.getSt().executeUpdate();
        ds.getCon().commit();
        ds.getCon().close();
        }catch(Exception e){
        }
    }

    @Override
    public void delete(Category c) {
        try{
        ds.setCon();
        ds.setSt("Delete from Categories where id=?");
        ds.getSt().setInt(1,c.getId());
        ds.getSt().executeUpdate();
        ds.getCon().commit();
        ds.getCon().close();
        }catch(Exception e){
        }
    }

    @Override
    public List<Category> read() {
        List<Category> catgories=new ArrayList<>();
         try{
            ds.setCon();
            ds.setSt("select * from Categories");
             ResultSet rs= ds.getSt().executeQuery();
            while(rs.next()){
                   Category c=new Category(rs.getInt("Id"));
                   c.setName(rs.getString("name"));
                   c.setDescription(rs.getString("description"));
                   catgories.add(c);
            }
            ds.getCon().close();
        }catch(Exception e){}
        return catgories;
    }
    
}
