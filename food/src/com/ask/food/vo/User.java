package com.ask.food.vo;

/**
 * Created by xxxx on 2018/8/27.
 */
public class User {
    private String userid;
    private String userName;
    private String password;
    private String realName;
    private String sex;

    public User() {
    }

    public User(String userid, String userName, String password, String realName, String sex) {
        this.userid = userid;
        this.userName = userName;
        this.password = password;
        this.realName = realName;
        this.sex = sex;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid='" + userid + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
