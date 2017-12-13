/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.onlineshopping.service;

import com.onlineshopping.db.DataSource;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailService {
    
    public boolean verifyMail(String toMail,String userName) throws SQLException{
        String OTP="";
        int x;
        for(int i=0;i<6;i++){
            Random rand=new Random();
            x=rand.nextInt(9)+1;
            OTP+=""+x;
        }
        DataSource ds=new DataSource();
        ds.setCon();
        ds.setSt("Update Users set OTP =? where MailId=?");
        ds.getSt().setString(1, OTP);
        ds.getSt().setString(2, toMail );
        ds.getSt().executeUpdate();
        sendMail(toMail, userName, OTP);
        return false;
    }
    
    public static void main(String[] arg) throws SQLException{
        MailService m=new MailService();
        m.verifyMail("iamsukeshk@gmail.com","Sukesh");
    }
    
    public boolean sendMail(String toMail,String userName,String OTP){
        String fromMail="sukesh.niithabsiguda@gmail.com";
        String password="iluVirat#IND";
        String sub="OnlineShopping - Verification";
        String msg="";
            
                            try{
                //Creating a result for getting status that messsage is delivered or not!
                String result;
                // Get recipient's email-ID, message & subject-line from index.html page
                final String to = toMail;
	
                final String subject = sub;
                
                String messg = " Dear "+userName+", <br/><br/> <i>Greetings from Online Shoping Portal!!</i> ";
                msg+="Thanks for Registering please verify your mail by using OTP <b>"+OTP+"</b>";
                msg+="<br/><br/>Warm Regards<br/>  Online Shopping Portal Team. ";
                messg=messg.concat(msg);
                
                // Sender's email ID and password needs to be mentioned
                final String from = fromMail;
                final String pass = password;
 
                // Defining the gmail host
                String host = "smtp.gmail.com";
 
                // Creating Properties object
                Properties props = new Properties();
 
                    // Defining properties
                    props.put("mail.smtp.host", host);
                    props.put("mail.transport.protocol", "smtp");
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.starttls.enable", "true");
                    props.put("mail.user", from);
                    props.put("mail.password", pass);
                    props.put("mail.port", "465");
 
                // Authorized the Session object.
                Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, pass);
                }
            });
 
                    try {
                        // Create a default MimeMessage object.
                        MimeMessage message = new MimeMessage(mailSession);
                        // Set From: header field of the header.
                        message.setFrom(new InternetAddress(from));
                        // Set To: header field of the header.
                        message.addRecipient(Message.RecipientType.TO,
                                new InternetAddress(to));
                        // Set Subject: header field
                        message.setSubject(subject);
                        // Now set the actual message
                        message.setContent(messg, "text/html; charset=utf-8");
                        // Send message
                        Transport.send(message);
                        return true;
                        } catch (MessagingException mex) {
                            mex.printStackTrace();
                            result = "Error: unable to send mail....";
                        }
                    }
                    catch(Exception e)
                    {
                        System.err.println(e);
                    }
          return false;
    }
}
