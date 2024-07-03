package com.wtp.JDBC;

import java.sql.*;

public class CRUD {

    private Connection conn;
    private Statement stmt ;
    private final String url;
    private final String user;
    private final String password;

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","123456");
    }

    public CRUD(String url,String user,String password)  {
        this.url = url;
        this.user = user;
        this.password = password;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean InsertOrDeleteOrUpdate(String sql){
        int res = 0;
        try {
            conn = DriverManager.getConnection(url,user,password);
            stmt = conn.createStatement();
            res = stmt.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res > 0;
    }

    public ResultSet search(String sql){
        ResultSet resultSet = null;
        try {
            conn = DriverManager.getConnection(url,user,password);
            stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    public void close(){
        try {
            stmt.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
