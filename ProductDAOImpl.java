/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.onlineshopping.service;

import com.onlineshopping.DAO.ProductDAO;
import com.onlineshopping.db.DataSource;
import com.onlineshopping.model.Category;
import com.onlineshopping.model.Product;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ProductDAOImpl implements ProductDAO{
    
    DataSource ds=new DataSource();

    @Override
    public void create(Product p) {
        try{
        ds.setCon();
        ds.setSt("insert into Products(name,description,price,categoryId,manufacturename) values (?,?,?,?,?)");
        ds.getSt().setString(1,p.getName());
        ds.getSt().setString(2,p.getDescription());
        ds.getSt().setFloat(3,p.getPrice());
        ds.getSt().setInt(4,p.getCategoryId());
        ds.getSt().setString(5,p.getManufacturename());
        ds.getSt().executeUpdate();
        ds.getCon().commit();
        ds.getCon().close();
        }catch(Exception e){
        }
    }

    @Override
    public Product read(Product p) {
        try{
            ds.setCon();
            ds.setSt("select * from Products where Id= ?");
            ds.getSt().setInt(1, p.getId());
            ResultSet rs= ds.getSt().executeQuery();
            if(rs.next()){
                    p.setName(rs.getString("name"));
                    p.setDescription(rs.getString("description"));
                    p.setPrice(rs.getFloat("price"));
                    p.setCategoryId(rs.getInt("categoryId"));
                    p.setManufacturename(rs.getString("manufacturename"));
            }
        }catch(Exception e){}
        return p;
    }
    
    @Override
    public List<Product> read(Category c) {
        List<Product> products=new ArrayList<>();
         try{
            ds.setCon();
            ds.setSt("select * from Products where categoryId=?");
            ds.getSt().setInt(1, c.getId());
            ResultSet rs= ds.getSt().executeQuery();
            while(rs.next()){
                   Product p=new Product(rs.getInt("Id"));
                   p.setName(rs.getString("name"));
                   p.setDescription(rs.getString("description"));
                   p.setPrice(rs.getFloat("price"));
                   p.setCategoryId(rs.getInt("categoryId"));
                   p.setManufacturename(rs.getString("manufacturename"));
                   products.add(p);
            }
            ds.getCon().close();
        }catch(Exception e){}
        return products;
    }

    @Override
    public List<Product> read() {
        List<Product> products=new ArrayList<>();
         try{
            ds.setCon();
            ds.setSt("select * from Products");
             ResultSet rs= ds.getSt().executeQuery();
            while(rs.next()){
                   Product p=new Product(rs.getInt("Id"));
                   p.setName(rs.getString("name"));
                   p.setDescription(rs.getString("description"));
                   p.setPrice(rs.getFloat("price"));
                   p.setCategoryId(rs.getInt("categoryId"));
                   p.setManufacturename(rs.getString("manufacturename"));
                   products.add(p);
            }
            ds.getCon().close();
        }catch(Exception e){}
        return products;
    }

    @Override
    public void update(Product p) {
        try{
        ds.setCon();
        ds.setSt("update Products set name=?,description=?,price=?,categoryId=?,manufacturename=? where id=?");
        ds.getSt().setString(1,p.getName());
        ds.getSt().setString(2,p.getDescription());
        ds.getSt().setFloat(3, p.getPrice());
        ds.getSt().setInt(4, p.getCategoryId());
        ds.getSt().setString(5,p.getManufacturename());
        ds.getSt().setInt(6, p.getId());
        ds.getSt().executeUpdate();
        ds.getCon().commit();
        ds.getCon().close();
        }catch(Exception e){
        }
    }

    @Override
    public void delete(Product p) {
        try{
        ds.setCon();
        ds.setSt("Delete from Products where id=?");
        ds.getSt().setInt(1,p.getId());
        ds.getSt().executeUpdate();
        ds.getCon().commit();
        ds.getCon().close();
        }catch(Exception e){
        }
    }
    
}
