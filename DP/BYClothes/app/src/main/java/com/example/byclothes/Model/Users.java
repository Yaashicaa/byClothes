package com.example.byclothes.Model;

public class Users
{
    String username,mail,password, phone;

    public Users()
    {

    }
    public Users(String username, String mail, String phone, String password)
    {
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
