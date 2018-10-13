package com.yjh.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
/**
 * 涉及多个事务的操作，需要在业务层管理Connection连接，只有所以事务都执行完毕后，才关闭连接
 * @author Administrator
 *
 */
public class DBUtil2 {
	static String driver;
	static String url;
	static String user;
	static String password;
	
	//使用ThreadLocal可以存储某个变量的副本，让同一个线程中不同方法共用
	//将Connection放入TheadLocal可以实现不同层次、不同DML操作使用同一个Connection
	private static ThreadLocal threadLocal = new ThreadLocal();
	
	//读取属性文件properties并获取内容
	static{
		//准备一个空的map，没有key-value
		Properties prop = new Properties();
		
		//读取文件，并将文件键值对存入Properties对象
		//InputStream is = new FileInputStream(new File("C:\Users\Administrator\workspace\java_empmgr2\src\conn.properties"));
		InputStream is = DBUtil2.class.getResourceAsStream("/jdbc.properties"); //classpath
		try {
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//从prop中根据key获取四个参数的值
		driver = prop.getProperty("driver");
		//driver = prop.get("driver");
		url = prop.getProperty("url");
		user = prop.getProperty("username");
		password = prop.getProperty("password");
		
		//加载驱动
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		}
	}
	
	/**
	 * 获取数据库连接
	 * @return
	 */
	public static  Connection getConnection(){
		Connection conn = null;
		//首先从threadLocal获取connection对象
		conn = (Connection) threadLocal.get();
		if(conn == null){//说明是第一次访问，创建数据库连接
			try{			
				//建立数据库连接			
				conn = DriverManager.getConnection(url, user, password);
				//存进threadLocal里面
				threadLocal.set(conn);
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return conn;
		
	}
	
	/**
	 * 关闭数据库资源
	 * @param rs
	 * @param stmt
	 * @param conn
	 */
	public static void closeAll(ResultSet rs ,Statement stmt,Connection conn){
		//关闭数据库资源
		try {
			if(rs!=null){
				rs.close();
			}				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(stmt != null){
				stmt.close();
			}				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(conn != null){
				threadLocal.set(null);//从threadLocal中移除Connection
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * DML:insert  update  delete
	 */
	public static int executeUpdate(String  sql,Object ... params) {
		PreparedStatement pstmt = null;
		int n = 0;
		try{
			//获取数据库连接,由于不需要再dao层关闭，所以不需要向上声明
			Connection conn = DBUtil2.getConnection();
			
			//使用手枪发送SQL命令并得到结果			
			pstmt = conn.prepareStatement(sql);
			
			for(int i=0;i<params.length;i++){
				pstmt.setObject(i+1, params[i]);
			}		
			n = pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			//由于控制层servlet要采用捕捉异常方式判断有误错误，所以这里不能把异常捕捉了
			//抛出异常
			throw new MyException(e.toString());//这样servlet就能接收到异常
		}finally{
			//这里由于要进行多个事务操作，所以不能关闭连接
			DBUtil2.closeAll(null, pstmt, null);
		}
				
		//返回数据
		return n;
	}
	public static void main(String[] args) {
		Connection conn = getConnection();
		System.out.println(conn);
	}
}
