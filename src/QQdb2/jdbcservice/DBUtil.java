package QQdb2.jdbcservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtil {
	private static Connection conn = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;

	/**
	 * ��ɾ�Ĳ���
	 * 
	 * @param sql
	 * @param paramsValue
	 * @return
	 */
	public static int execute(String sql, Object[] paramsValue) {
		int result = 0;
		try {
			// 1. ���ݿ�����
			conn = JDBCUtil.getConnection();
			// 2. ��ȡPreparedStatement
			pstmt = conn.prepareStatement(sql);
			// 3. ���ò���Ԫ���ݸ�SQL����ռλ����Ҫ�Ĳ�����ֵ
			if (paramsValue != null && paramsValue.length > 0) {
				for (int i = 0; i < paramsValue.length; i++) {
					// ѭ�����������Ը�SQL���������ֵ
					pstmt.setObject(i + 1, paramsValue[i]);
				}
			}
			// 4. ִ��
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
		return result;
	}

	/**
	 * ��ѯ����
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public static List<Map<String, Object>> executeQuery(String sql, Object[] params) {
		List<Map<String, Object>> list = new ArrayList<>();
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			// ���ò���
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					pstmt.setObject(i + 1, params[i]);//ռλ��
				}
			}
			rs = pstmt.executeQuery();
			// �õ�ResultSetMetaData���������������е���Ϣ(�е����ƣ��ֶΣ������͵ȵ�)
			ResultSetMetaData metaData = rs.getMetaData();
			// �õ���������еĸ���
			int columnCount = metaData.getColumnCount();
			while (rs.next()) { // 
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					// �õ�ָ���������ı���
					String name = metaData.getColumnLabel(i);  // �е����� �� password
					Object value = rs.getObject(i);			   // �ж��ڵ�ֵ 123456
					map.put(name, value);                      // name:����;age:25
				}
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return list;
	}

}
