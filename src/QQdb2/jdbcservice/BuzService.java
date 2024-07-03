package QQdb2.jdbcservice;
import java.util.List;
import java.util.Map;

import QQdb2.BuzServiceInterface;


public class BuzService implements BuzServiceInterface {


    @Override
	public boolean isLoginSuccess(String account, String password) {
    	
    	System.out.println("use JDBC !!!!");
    	
        String loginQuerySql = "SELECT * FROM `user` WHERE `account` = ?";
        String[] param = {account};
        List<Map<String, Object>> maps = DBUtil.executeQuery(loginQuerySql, param);
        for (Map<String, Object> map : maps) {
            String passwordRaw = (String)map.get("password");
            if(password.equals(passwordRaw)) {
                return true;
            }
        }

        return false;
    }

    @Override
	public String getUserName(String account) {
        String loginQuerySql = "SELECT * FROM `user` WHERE `account` = ?";
        String[] param = {account};
        List<Map<String, Object>> maps = DBUtil.executeQuery(loginQuerySql, param);
        Map<String, Object> mapUser = maps.get(0);
        String userName = (String)mapUser.get("user_name");
        return userName;
    }

    @Override
	public String mateList(String mateInput, String account) {

        String resultMsg = "";

        String mateQuerySql = "SELECT * FROM `mate` WHERE `mate_name` LIKE ? AND account = ?";
        String[] param = {"%" + mateInput + "%", account};
        List<Map<String, Object>> maps = DBUtil.executeQuery(mateQuerySql, param);
        for (Map<String, Object> map : maps) {
            String mate = (String)map.get("mate_name");
            resultMsg += mate + "\r\n";
        }

        return resultMsg;

    }

    @Override
	public int addMate(String account, String mateName) {
        String mateAddSql = "INSERT INTO `mate` (`account`, `mate_name`) VALUES (?, ?)";
        String[] param = {account, mateName};
        int execute = DBUtil.execute(mateAddSql, param);
        return execute;


    }

}
