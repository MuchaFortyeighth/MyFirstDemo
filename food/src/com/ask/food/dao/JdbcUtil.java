package com.ask.food.dao;

import java.sql.*;

/**
 * Created by xxxx on 2018/8/6.
 */
public class JdbcUtil {

    public static final String URL = "jdbc:oracle:thin:@localhost:1521:ORCL";
    public static final String USER = "mucha";
    public static final String PASSWORD = "ask00048";

    //加载数据库驱动
    static {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.err.println("加载数据库驱动失败" + e.getMessage());
        }
    }

    //获得数据库的连接
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            System.err.println("连接数据库失败" + e.getMessage());
        }
        return null;
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs){
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        System.out.println(getConnection());
    }
}
