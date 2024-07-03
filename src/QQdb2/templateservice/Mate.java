package QQdb2.templateservice;

public class Mate {

	private String account;
	private String mateName;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getMateName() {
		return mateName;
	}
	public void setMateName(String mateName) {
		this.mateName = mateName;
	}
	
	
	public Mate(String account, String mateName) {
		super();
		this.account = account;
		this.mateName = mateName;
	}
}
