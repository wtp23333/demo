package QQdb2.templateservice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.CollectionUtils;

import QQdb2.BuzServiceInterface;



public class BuzJDBCTemplateService implements BuzServiceInterface {

	private JdbcTemplate jdbcTemplate;

	
	public BuzJDBCTemplateService() {
		super();
		ApplicationContext context= new ClassPathXmlApplicationContext("QQdb2/templateservice/application.xml");
		jdbcTemplate = context.getBean(JdbcTemplate.class);//通过JdbcTemplate.class这种类信息来从容器中获取java对象（bean对象）。
		
	}
	
	public boolean isLoginSuccess(String account, String password) {

		System.out.println("use JDBCTemplate !!!!");
        String loginQuerySql = "SELECT * FROM `user` WHERE `account` = ?";
        String[] param = {account};
        
		List<User> users = jdbcTemplate.query(loginQuerySql, param, new RowMapper() //RowMapper()是orm的一个事例   传的是对象数据里的函数
		{
			@Override
			public Object mapRow(ResultSet rs, int num) throws SQLException {
				User user = new User();
				user.setId(rs.getString("account"));
				user.setPassword(rs.getString("password"));
				user.setUserName(rs.getString("user_Name"));
				return user;
			}});
        
        
        if(CollectionUtils.isEmpty(users)) {
        	return false;
        }
        
       User user = users.get(0);
       if(password.equals(user.getPassword())) {
    	   return true;
       }
       return false;
        
    }


    public String getUserName(String account) {
    	
        String loginQuerySql = "SELECT * FROM `user` WHERE `account` = ?";
        String[] param = {account};
        
		List<User> users = jdbcTemplate.query(loginQuerySql, param, new RowMapper()
				{
			@Override
			public Object mapRow(ResultSet rs, int num) throws SQLException {
				User user = new User();
				user.setPassword(rs.getString("password"));
				user.setAccount(rs.getString("account"));
				user.setUserName(rs.getString("user_Name"));
				return user;
			}});
    	 
        if(CollectionUtils.isEmpty(users)) {
        	return null;
        }
    	
        User user = users.get(0);  // get第一个user
        return user.getUserName();
    }

    public String mateList(String mateInput, String account) {

        String resultMsg = "";

        String mateQuerySql = "SELECT * FROM `mate` WHERE `mate_name` LIKE ? AND account = ?";
        String[] param = {"%" + mateInput + "%", account};
        
		List<Mate> mates = jdbcTemplate.query(mateQuerySql, param, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int num) throws SQLException {

				Mate mate = new Mate(rs.getString("account"), rs.getString("mate_name"));
				return mate;
			}});
        if(CollectionUtils.isEmpty(mates)) {
        	return resultMsg;
        }
        
        for (Mate theMate : mates) {
            String mate = theMate.getMateName();
            resultMsg += mate + "\r\n";
        }

        return resultMsg;

    }

    public int addMate(String account, String mateName) {
        String mateAddSql = "INSERT INTO `mate` (`account`, `mate_name`) VALUES (?, ?)";
        String[] param = {account, mateName};
        int update = jdbcTemplate.update(mateAddSql, param);
      //没有取数据所以不需要映射
        return update;
    }
}
