package com.ask.food.dao;

import com.ask.food.vo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by xxxx on 2018/8/27.
 */
public class UserDao {

    public User findByName(String userName){
        try(Connection conn = JdbcUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM USERS WHERE USERNAME = ?")
        ) {
            ps.setObject(1,userName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return new User(rs.getString("userid"),
                        rs.getString("userName"),
                        rs.getString("password"),
                        rs.getString("realName"),
                        rs.getString("sex"));
            }else {
                System.out.println("该用户无记录");
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User login(String name,String password){
        User user = findByName(name);
        if (user != null){
            if (user.getPassword().equals(password)){
                return user;
            }else {
                return null;
            }
        }else {
            return null;
        }
    }
}
