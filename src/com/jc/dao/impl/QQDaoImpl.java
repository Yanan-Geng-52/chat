package com.jc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jc.dao.QQDao;
import com.jc.entity.QQ;
import com.jc.util.DButil;

/**
 * QQ��ʵ�ַ���
 * @author ���ٻ�
 * @date: 2018��4��12�� ����10:56:14
 */
public class QQDaoImpl implements QQDao {

	/**
	 * ���
	 */
	@Override
	public int save(QQ qq) {
		String sql="insert into qq(qname,qpassword,username) values(?,?,?)";
		int i= DButil.doUpdate(sql, qq.getQname(),qq.getQpassword(),qq.getUsername());
		return i;
	}

	/**
	 * ��֤�˺ţ����빦��
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
	 * �鿴�˺���û��
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
	 * �������е�����
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
	 * ͨ�����ֲ�ѯ��Ϣ
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
