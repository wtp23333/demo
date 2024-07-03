package com.wtp.JDBC;

import java.sql.*;
import java.util.Locale;

public class jdbcConnection {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/test";
        Connection conn = DriverManager.getConnection(url,"root","123456");
        Statement stmt = conn.createStatement();//语句对象
        //(stmt.executeUpdate("insert into test(wf,fdsf,ww)  values (5,5,5)");
//        stmt.executeUpdate("update test set wf=6 where ww=1");
//        PreparedStatement p = conn.prepareStatement("select ? from test");
//        p.setString(1,"*");
//        ResultSet res = p.executeQuery();
        ResultSet res = stmt.executeQuery("select * from test");

        while(res.next()){
            System.out.print(res.getInt("wf") + " ");
            System.out.print(res.getInt("fdsf") + " ");
            System.out.println(res.getInt("ww"));
        }
    }
}
