package com.wtp.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCTest4 {


    public static void main(String[] args) throws Exception {
//  delete();
//  update();

        delete(6);

        update(55, 5);
        // update(28, "peter");
    }



    public static void delete(int id) throws Exception {

        String SQL = "delete from test where wf = \"" + id + "\"";

        Connection conn = CRUD.getConnection();
        Statement cs = conn.createStatement();
        int result = cs.executeUpdate(SQL);
        System.out.printf("删除了 %d 条 \n", result);

        cs.close();
        conn.close();
    }

    public static void update(int age, int n) throws Exception {

        Connection conn = CRUD.getConnection();
        PreparedStatement ps = conn.prepareStatement("update test set ww = ? where wf = ?");
        ps.setInt(1, age);
        ps.setInt(2, n);
        int result = ps.executeUpdate();

        System.out.printf("更新了 %d 条 \n", result);

        ps.close();
        conn.close();
    }


}
