package com.jc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jc.dao.QQDao;
import com.jc.entity.QQ;
import com.jc.util.DButil;

/**
 * QQ的实现方法
 * @author 尤少辉
 * @date: 2018年4月12日 上午10:56:14
 */
public class QQDaoImpl implements QQDao {

	/**
	 * 添加
	 */
	@Override
	public int save(QQ qq) {
		String sql="insert into qq(qname,qpassword,username) values(?,?,?)";
		int i= DButil.doUpdate(sql, qq.getQname(),qq.getQpassword(),qq.getUsername());
		return i;
	}

	/**
	 * 验证账号，密码功能
	 */
	@Override
	public String check(String qname, String qpassword) {
		String username = null;
		String sql="select username from qq where qname = ? and qpassword = ?";
		ResultSet rs= DButil.doQuery(sql, qname,qpassword);
		try {
			while(rs.next()){
				username=rs.getString("username");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return username;
	}

	/**
	 * 查看账号有没有
	 */
	@Override
	public boolean noExits(String qname) {
		String sql="select qid from qq where qname = ?";
		ResultSet rs= DButil.doQuery(sql, qname);
		try {
			while(rs.next()){
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 查找所有的名字
	 */
	@Override
	public List<String> findAllName() {
		List<String> names=new ArrayList<String>();
		String sql="select username from qq";
		ResultSet rs= DButil.doQuery(sql, null);
		try {
			while(rs.next()){
				String name=rs.getString("username");
				names.add(name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return names;
	}

	/**
	 * 通过名字查询信息
	 */
	@Override
	public QQ findByName(String qname) {
		QQ qq=new QQ();
		String sql="select * from qq where username = ?";
		ResultSet rs= DButil.doQuery(sql, qname);
		try {
			while(rs.next()){
				qq.setUsername(rs.getString("username"));
				qq.setQid(rs.getInt("qid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qq;
	}

}
