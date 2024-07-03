package QQdb2;

public interface BuzServiceInterface {

	boolean isLoginSuccess(String account, String password);

	String getUserName(String account);

	String mateList(String mateInput, String account);

	int addMate(String account, String mateName);
	

}