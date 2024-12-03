package com.jc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 封装数据库的操作
 * @author 尤少辉
 * @date: 2018年4月12日 上午10:44:29
 */
public class DButil {
	/**
	 * 数据库连接
	 * @return
	 */
	public static Connection getConnetion(){
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql:///qq?useUnicode=true&characterEncoding=UTF-8", "root", "ysh09-04");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	/**
	 * 封装查询操作
	 */
	public static ResultSet doQuery(String sql,Object...params){
		ResultSet rs=null;
		Connection con=getConnetion();
		try {
			PreparedStatement ps= con.prepareStatement(sql);
			if(params!=null){
				for(int i=0;i<params.length;i++){
					ps.setObject(i+1, params[i]);
				}
			}
			rs=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * 封装增删改操作
	 */
	public static int doUpdate(String sql,Object...params){
		int j=0;
		Connection con=getConnetion();
		try {
			PreparedStatement ps= con.prepareStatement(sql);
			if(params!=null){
				for(int i=0;i<params.length;i++){
					ps.setObject(i+1, params[i]);
				}
			}
			j=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return j;
	}
}
