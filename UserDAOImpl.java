

package com.onlineshopping.service;

import com.onlineshopping.DAO.UserDAO;
import com.onlineshopping.db.DataSource;
import com.onlineshopping.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAOImpl implements UserDAO{

    DataSource ds=new DataSource();
    
    @Override
    public int create(User u) {
        int i=0;
        try{
            if(isNewUser(u)){
        ds.setCon();
        ds.setSt("insert into Users(firstName,lastName,mailId,phoneNumber,password,status) values(?,?,?,?,?,?)");
        ds.getSt().setString(1,u.getFirstName());
        ds.getSt().setString(2,u.getLastName());
        ds.getSt().setString(3,u.getMailId());
        ds.getSt().setString(4,u.getPhoneNumber());
        ds.getSt().setString(5,u.getPassword());
        ds.getSt().setString(6,u.getStatus());
        i=ds.getSt().executeUpdate();
        ds.getCon().commit();
        ds.getCon().close();
            }
        }catch(Exception e){
            System.err.println(e);
        }
        return i; 
    }
    
    public boolean validateUser(User u,String OTP) {
        try{
        if(u.getStatus().equals("InValid") && OTP==null){
            return false;
        }
        else if(u.getOTP().equals(OTP)){
            ds.getCon();
            ds.setSt("Update Users set status = 'Valid' where Id=?");
            ds.getSt().setInt(1, u.getId());
            ds.getSt().executeUpdate();
            ds.getCon().commit();
            ds.getCon().close();
            return true;
        }
        else if(u.getStatus().equals("Valid")){
            return true;
        }
        else{
            MailService ms=new MailService();
            ms.verifyMail(u.getMailId(), u.getFirstName()+" "+u.getLastName());
        }
        }catch(SQLException e){
               System.out.println(e);
        }
        return false;
    }

    @Override
    public User read(User u) {
        try{
            ds.setCon();
            ds.setSt("select * from Users where mailId=?");
            ds.getSt().setString(1, u.getMailId());
            ResultSet rs = ds.getSt().executeQuery();
            if(rs.next()){
                if(rs.getString("password").equals(u.getPassword())){
                    u.setFirstName(rs.getString("firstName"));
                    u.setLastName(rs.getString("lastName"));
                    u.setPhoneNumber(rs.getString("phoneNumber"));
                    u.setId(rs.getInt("Id"));
                    u.setStatus(rs.getString("status"));
                    u.setOTP(rs.getString("OTP"));
                }
            }
        }catch(Exception e){
            System.err.println(e);
        }
        return u;
    }
    

    @Override
    public int update(User u) {
        return 0;
    }

    @Override
    public int delete(User u) {
        return 0;
    }
    
    public boolean isNewUser(User u){
        try{
            ds.setCon();
            ds.setSt("select * from Users where mailId=?");
            ds.getSt().setString(1, u.getMailId());
            ResultSet rs = ds.getSt().executeQuery();
            if(rs.next()){
                return false;
            }
        }catch(Exception e){
            System.err.println(e);
        }
        return true;
    }
    
    public static void main(String[] arg){
        UserDAO ud=new UserDAOImpl();
        User u=new User("iamsukeshk@gmail.com", "123");
        System.out.println(ud.read(u).getFirstName());
        System.out.println(ud.validateUser(ud.read(u),"329832"));
    }
}
