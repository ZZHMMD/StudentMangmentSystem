package com.zzh.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

//通用的连接数据库的类	
public class JdbcUtils {
	
	//这里采用的是 xml 来连接数据库
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	public static DataSource getDataSource() {
		return dataSource;
	}
	
	
	
	/*private static String driverClass="";
	private static String url="";
	private static String user="";
	private static String password="";
	
	static{
		ResourceBundle rb = ResourceBundle.getBundle("dbcfg");
		driverClass = rb.getString("driverClass");
		url= rb.getString("url")+"?characterEncoding=utf-8";
		user = rb.getString("user");
		password = rb.getString("password");
		
		try {
			
			Class.forName(driverClass);  
			System.out.println(driverClass);
			//Class.forName("com.jdbc.mysql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
/*	public static void main(String[] args){
		System.out.println(driverClass);
	}*/
	
	public static Connection getConnection() throws SQLException{
		/*try {
			Connection conn =DriverManager.getConnection(url,user,password);
			//Connection conn = DriverManager.getConnection("jdba:mysql://localhost:3306/mydb","root","root");
			return conn;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;*/
		return dataSource.getConnection();
	}
	public static void relase(ResultSet rs,Statement stmt,Connection conn){
		if(rs !=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(stmt !=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(conn !=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	

}
