package QQdb;
import java.util.List;
import java.util.Map;

import QQdb.util.DBUtil;

public class BuzService {


    public boolean isLoginSuccess(String account, String password) {

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

    public String getUserName(String account) {
        String loginQuerySql = "SELECT * FROM `user` WHERE `account` = ?";
        String[] param = {account};
        List<Map<String, Object>> maps = DBUtil.executeQuery(loginQuerySql, param);
        Map<String, Object> mapUser = maps.get(0);
        String userName = (String)mapUser.get("user_name");
        return userName;
    }

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

    public int addMate(String account, String mateName) {
        String mateAddSql = "INSERT INTO `mate` (`account`, `mate_name`) VALUES (?, ?)";
        String[] param = {account, mateName};
        int execute = DBUtil.execute(mateAddSql, param);
        return execute;


    }

}
