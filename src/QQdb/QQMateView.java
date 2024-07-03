package QQdb;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class QQMateView extends JFrame{

	private static final String BASE_PATH = "D:\\tupian\\";

	private static final long serialVersionUID = -6788045638380819221L;//了保持版本的兼容性
	//用户名
	private JTextField ulName;
	//密码
	private JPasswordField ulPasswd;
	//小容器
	private JLabel j1;
	private JLabel j2;
	private JLabel j3;
	private JLabel j4;
	//小按钮
	private JButton b1;
	//复选框
	private JCheckBox c1;

	private BuzService buzService = new BuzService();

	private String loginAccount ;

	// -------- 登陆后 ----------------------------
	JTextField mateQuery;
	JTextArea jta;
	JButton searchButton;

	JTextField mateInput;
	JButton addButton;



	/**
	 * 初始化QQ登录页面
	 * */
	public QQMateView(){
		//设置登录窗口标题
		this.setTitle("系统登录");
		//去掉窗口的装饰(边框)
//		this.setUndecorated(true);
		//采用指定的窗口装饰风格
		this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		//窗体组件初始化
		init();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//JFrame的方法，设置默认的关闭操作，参数在关闭动作时退出
		//设置布局为绝对定位
		this.setLayout(null);//默认的布局样式；
		this.setBounds(0, 0, 355, 265);
		//设置窗体的图标
		Image img0 = new ImageIcon(BASE_PATH +  "icon.jpg").getImage();
		this.setIconImage(img0);
		//窗体大小不能改变
		this.setResizable(false);
		//居中显示
		this.setLocationRelativeTo(null);
		//窗体显示
		this.setVisible(true);
	}
	/**
	 * 窗体组件初始化
	 * */
	public void init(){
		//创建一个容器,其中的图片大小和setBounds第三、四个参数要基本一致(需要自己计算裁剪)
		Container container = this.getContentPane();
		j1 = new JLabel();
		//设置背景色
		Image img1 = new ImageIcon(BASE_PATH +  "bg.jpg").getImage();
		j1.setIcon(new ImageIcon(img1));
		j1.setBounds(0, 0, 355, 265);
		//qq头像设定
		j2 = new JLabel();
		Image img2 = new ImageIcon(BASE_PATH +  "tx.jpg").getImage();
		img2 = img2.getScaledInstance(50, 53, Image.SCALE_DEFAULT);//调整图像大小
		j2.setIcon(new ImageIcon(img2));
		j2.setBounds(40, 95, 150, 53);//public void setBounds(int x,int y,int width,int height)移动组件并调整其大小。
		//由 x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
		//用户名输入框
		ulName = new JTextField();
		ulName.setBounds(100, 100, 150,20);
		//注册账号
		j3 = new JLabel("注册账号");
		j3.setBounds(260, 100, 70, 20);
//		j3.setSize(50,50);
		//密码输入框
		ulPasswd = new JPasswordField();
		ulPasswd.setBounds(100, 130, 150, 20);
		//找回密码
		j4= new JLabel("找回密码");
		j4.setBounds(260, 130, 70, 20);
		//记住密码
		c1 = new JCheckBox("记住密码");
		c1.setOpaque(false);//此方法是设置控件是否透明的。true表示不透明，false表示透明。
		c1.setBounds(105, 155, 80, 15);


		//登陆按钮
		b1 = new JButton("登录");
		//设置字体和颜色和手形指针
		b1.setFont(new Font("宋体", Font.PLAIN, 12));
		b1.setForeground(Color.RED);
		b1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		b1.setBounds(280, 200, 65, 20);
		//给按钮添加
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();//
				if("登录".equals(cmd)){
					String account = ulName.getText();
					char[] password = ulPasswd.getPassword();
					String userpassword = String.valueOf(password);

					boolean loginSuccess = buzService.isLoginSuccess(account, userpassword);

					if(loginSuccess){
						loginAccount = account;
						JOptionPane.showMessageDialog(null, "登录成功!");
						initAfterLogin();
					}else {
						JOptionPane.showMessageDialog(null, "登录失败");
					}
				}
			}
		});
		//所有组件用容器装载
		j1.add(j2);
		j1.add(j3);
		j1.add(j4);
		j1.add(c1);
		j1.add(b1);
		container.add(ulName);
		container.add(ulPasswd);
		container.add(j1);

	}


	public void initAfterLogin() {
		Container container = this.getContentPane();
		container.removeAll();//
		mateQuery = new JTextField();
		mateQuery.setBounds(20, 10, 200, 20);
		container.setVisible(false);

		String userName = buzService.getUserName(loginAccount);
		this.setTitle("用户: " + userName);


		container.add(mateQuery);


		jta=new JTextArea();
		jta.setLineWrap(true);    //设置文本域中的文本为自动换行
		jta.setForeground(Color.BLACK);    //设置组件的背景色
		jta.setFont(new Font("楷体",Font.BOLD,16));    //修改字体样式
		jta.setBackground(Color.YELLOW);    //设置背景色
		jta.setBounds(20,35, 300, 150);
		jta.setDisabledTextColor(Color.BLACK);
		jta.setEnabled(false);


		JScrollPane scroll = new JScrollPane(jta);
		scroll.setBounds(jta.getBounds());
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setViewportView(jta);
		container.add(scroll);
//		container.add(jta);


		//查询按钮
		searchButton = new JButton("查询");
		//设置字体和颜色和手形指针
		searchButton.setFont(new Font("宋体", Font.PLAIN, 12));
		searchButton.setForeground(Color.RED);
		searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		searchButton.setBounds(270, 10, 65, 20);
		//给按钮添加
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();

				String theInput = mateQuery.getText();
				String s = buzService.mateList(theInput, loginAccount);
				jta.setText(s);
				System.out.println("5");
				System.out.println("4444");
				System.out.println("555");
				System.out.println("4");
			}
		});

		container.add(searchButton);

		mateInput = new JTextField();
		mateInput.setBounds(20, 200, 150, 20);
		container.add(mateInput);


		addButton = new JButton("加入");
		//设置字体和颜色和手形指针
		addButton.setFont(new Font("宋体", Font.PLAIN, 12));
		addButton.setForeground(Color.RED);
		addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addButton.setBounds(270, 200, 65, 20);
		//给按钮添加
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = buzService.addMate(loginAccount, mateInput.getText());
				if(i > 0) {
					JOptionPane.showMessageDialog(null, "好友添加成功");
					mateQuery.setText("");
					searchButton.doClick();
				}else{
					JOptionPane.showMessageDialog(null, "好友添加失败!");
				}

			}
		});

		container.add(addButton);
		container.setVisible(false);
		container.setVisible(true);

	}

	public static void main(String[] args) {
		System.out.println("123");
		new QQMateView();
	}
}