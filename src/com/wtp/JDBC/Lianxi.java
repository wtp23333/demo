package com.wtp.JDBC;

import java.sql.*;
public class Lianxi {

    private static final String driver="com.mysql.jdbc.Driver";
    private static final String url= "jdbc:mysql://localhost:3306/test" ;
    private static final String user="root";
    private static final String password="123456";
    public static void main(String[] args)
    {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String selectSql="SELECT * FROM Student WHERE room=?";
        String insertSql="INSERT INTO Student(id,age,name,room)"+
                "VALUES(?,?,?,?);";
        String updateSql="UPDATE Student SET room='203' WHERE id=?";
        String deleteSql="DELETE FROM Student WHERE id=?";
        String selectSql1="SELECT * FROM Student";
        try
        {
            Class.forName(driver);
            conn=DriverManager.getConnection(url,user,password);

            ps=conn.prepareStatement(selectSql);
            ps.setString(1, "101");//查找room=101的信息；
            rs=ps.executeQuery();
            while(rs.next())
            {
                int id=rs.getInt("id");
                int age=rs.getInt("age");
                String name=rs.getString("name");
                String room=rs.getString("room");
                System.out.println(id+"  "+age+"  "+name+"  "+room);
            }
//执行查找

            ps=conn.prepareStatement(insertSql);
            ps.setInt(1, 8);
            ps.setInt(2, 5665 );
            ps.setString(3,"泰罗");
            ps.setString(4, "303");
            //插入以上数据
            int count=ps.executeUpdate();
            System.out.println("添加"+ count+"条记录到Student表中");
            //添加數據

            ps=conn.prepareStatement(updateSql);
            ps.setInt(1, 12);  //修改id=12的room号
            count=ps.executeUpdate();
            System.out.println("修改了Student表的"+count+"条记录");
            //修改

            ps=conn.prepareStatement(deleteSql);
            ps.setInt(1, 10);//删除id=10的信息；
            count=ps.executeUpdate();
            System.out.println("删除了Student表的"+count+"条记录");

            ps=conn.prepareStatement(selectSql1);

            rs=ps.executeQuery();
            while(rs.next())
            {
                int id=rs.getInt("id");
                int age=rs.getInt("age");
                String name=rs.getString("name");
                String room=rs.getString("room");
                System.out.println(id+"  "+age+"  "+name+"  "+room);
            }
//执行查找
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(rs!=null) rs.close();
                if(ps!=null) ps.close();
                if(conn!=null) conn.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}

