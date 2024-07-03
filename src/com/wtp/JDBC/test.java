package com.wtp.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;

public class test {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/test";
        CRUD crud1 = new CRUD(url,"root","123456");
        String sql = "select * from test";
        ResultSet search = crud1.search(sql);
        while(search.next()){
            int wf = search.getInt("wf");
            int fdsf = search.getInt("fdsf");
            int ww = search.getInt("ww");
            System.out.println(wf + " " + fdsf + " " + ww);
        }
        crud1.close();
    }
}
