package QQdb2.jdbcservice;

//1.�������� url
//2.connection
//3.statment  - sql
//4.rs
//5.�����������
//6.�ر�

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ���ݿ����ӹ�����
 * 
 * @author Wx
 *
 */
public class JDBCUtil {
	private static Properties ps = new Properties();
	static {
// ���������ļ�
		InputStream is = JDBCUtil.class.getResourceAsStream("jdbc.properties");// class
		try {
			ps.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �������ӷ���
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		Connection conn = null;
		if (conn == null) {
//��������

			conn = DriverManager.getConnection(ps.getProperty("url"), ps.getProperty("username"),
					ps.getProperty("password"));
			return conn;
		} else {
			return conn;
		}
	}

	/**
	 * �ر�����
	 * 
	 * @param conn
	 * @param pre
	 * @param rs
	 */
	public static void close(Connection conn, PreparedStatement pre, ResultSet rs) {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		if (pre != null)
			try {
				pre.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	/**
	 * �ر����ӷ���
	 * 
	 * @param conn
	 * @param pre
	 */
	public static void close(Connection conn, PreparedStatement pre) {
		if (pre != null)
			try {
				pre.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

}