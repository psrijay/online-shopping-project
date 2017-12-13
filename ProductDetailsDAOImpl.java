/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.onlineshopping.service;

import com.onlineshopping.DAO.ProductDetailsDAO;
import com.onlineshopping.db.DataSource;
import com.onlineshopping.model.Product;
import com.onlineshopping.model.ProductDetails;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ProductDetailsDAOImpl implements ProductDetailsDAO{

    DataSource ds=new DataSource();
    
    @Override
    public void create(ProductDetails p){
        try{
        ds.setCon();
        ds.setSt("insert into ProductDetails(serialNumber,productId,manufactureDate,expieryDate,size,color,sellerId) values (?,?,?,?,?,?,?)");
        ds.getSt().setString(1,String.valueOf(p.getSerialNumber()));
        ds.getSt().setInt(2,p.getProductId());
        ds.getSt().setDate(3,p.getManufactureDate());
        ds.getSt().setDate(4,p.getExpieryDate());
        ds.getSt().setString(5,p.getSize());
        ds.getSt().setString(6,p.getColor());
        ds.getSt().setInt(7,p.getSellerId());
        ds.getSt().executeUpdate();
        ds.getCon().commit();
        ds.getCon().close();
        }catch(Exception e){}
        }

    public static void main(String[] arg) throws SQLException{
        ProductDetailsDAOImpl pd=new ProductDetailsDAOImpl();
        for(ProductDetails p:pd.read()){
            System.out.println(p.getSerialNumber());
        }
        
    }
    
    @Override
    public ProductDetails read(ProductDetails pd) {
         try{
            ds.setCon();
            ds.setSt("select * from ProductDetails where SerialNumber="+pd.getSerialNumber());
             ResultSet rs= ds.getSt().executeQuery();
            while(rs.next()){
                   ProductDetails p=new ProductDetails(rs.getInt("SerialNumber"));
                   p.setSerialNumber(rs.getInt("sno"));
                   p.setProductId(rs.getInt("ProductId"));
                   p.setManufactureDate(rs.getDate("manufactureDate"));
                   p.setExpieryDate(rs.getDate("expieryDate"));
                   p.setSize(rs.getString("size"));
                   p.setColor(rs.getString("color"));
                   p.setSellerId(rs.getInt("sellerId"));
            }
            ds.getCon().close();
        }catch(Exception e){}
        return pd;
    }

    @Override
    public List<ProductDetails> read(Product pid) {
        List<ProductDetails> products=new ArrayList<>();
         try{
            ds.setCon();
            ds.setSt("select * from ProductDetails where productId="+pid.getId());
             ResultSet rs= ds.getSt().executeQuery();
            while(rs.next()){
                   ProductDetails p=new ProductDetails(rs.getInt("Id"));
                   p.setSerialNumber(rs.getInt("sno"));
                   p.setProductId(rs.getInt("ProductId"));
                   p.setManufactureDate(rs.getDate("manufactureDate"));
                   p.setExpieryDate(rs.getDate("expieryDate"));
                   p.setSize(rs.getString("size"));
                   p.setColor(rs.getString("color"));
                   p.setSellerId(rs.getInt("sellerId"));
                   products.add(p);
            }
            ds.getCon().close();
        }catch(Exception e){}
        return products;
    }

    @Override
    public List<ProductDetails> read() {
        List<ProductDetails> products=new ArrayList<>();
         try{
            ds.setCon();
            ds.setSt("select * from ProductDetails");
             ResultSet rs= ds.getSt().executeQuery();
            while(rs.next()){
                   ProductDetails p=new ProductDetails(rs.getInt("serialNumber"));
                   p.setProductId(rs.getInt("ProductId"));
                   p.setManufactureDate(rs.getDate("manufactureDate"));
                   p.setExpieryDate(rs.getDate("expieryDate"));
                   p.setSize(rs.getString("size"));
                   p.setColor(rs.getString("color"));
                   p.setSellerId(rs.getInt("sellerId"));
                   products.add(p);
            }
            ds.getCon().close();
        }catch(Exception e){}
        return products;
    }

    @Override
    public void update(ProductDetails p) {
        try{
        ds.setCon();
        ds.setSt("update ProductDetails set productId=?,manufactureDate=?,expieryDate=?,size=?,color=?,sellerId=? where sno=?");
        
        ds.getSt().setInt(1,p.getProductId());
        ds.getSt().setDate(2,p.getManufactureDate());
        ds.getSt().setDate(3,p.getExpieryDate());
        ds.getSt().setString(4,p.getSize());
        ds.getSt().setString(5,p.getColor());
        ds.getSt().setInt(6,p.getSellerId());
        ds.getSt().setLong(7,p.getSerialNumber());
        ds.getSt().executeUpdate();
        ds.getCon().commit();
        ds.getCon().close();
        }catch(Exception e){
        }
    }

    @Override
    public void delete(ProductDetails p) {
        try{
        ds.setCon();
        ds.setSt("Delete from ProductDetails where sno=?");
        ds.getSt().setLong(1,p.getSerialNumber());
        ds.getSt().executeUpdate();
        ds.getCon().commit();
        ds.getCon().close();
        }catch(Exception e){
        }
    }
    
}
