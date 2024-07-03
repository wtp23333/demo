import QQdb.util.DBUtil;

public class test {
    public static void main(String[] args) {

        String sql = "insert into order_ (user_name,dish) values( 'fadsfd','sad')";
//        String sql = "insert into Login (user_name,password) values('444','5')";

        DBUtil.execute(sql,new Object[]{});
//        DBUtil.execute(sql, new Object[]{1234.5, "456666"});

        //啊手动阀手动阀
    }
}
