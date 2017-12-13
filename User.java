

package com.onlineshopping.model;

public class User {
    private int Id;
    private String firstName;
    private String lastName;
    private String mailId;
    private String phoneNumber;
    private char gender;
    private String password;
    private String status;
    private String OTP;

    public User(int Id) {
        this.Id = Id;
    }
    
    
    
    public User(String firstName, String lastName, String mailId, String phoneNumber, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mailId = mailId;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.status="InValid";
    }

    public User(String mailId, String password) {
        this.mailId = mailId;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }
    
}
