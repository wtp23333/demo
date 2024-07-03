package QQdb2.templateservice;

public class User {

	private String id;
	private String account;
	private String password;
	private String userName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public User(String id, String account, String password, String userName) {
		super();
		this.id = id;
		this.account = account;
		this.password = password;
		this.userName = userName;
	}
	public User() {
		super();
	}
	
	
}
