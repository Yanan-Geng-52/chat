package com.jc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jc.dao.HaoYouDao;
import com.jc.util.DButil;

/**
 * ���ѷ�����ʵ����
 * @author ���ٻ�
 * @date: 2018��4��12�� ����3:53:19
 */
public class HaoYouDaoImpl implements HaoYouDao {

	/**
	 * ��ȡ���еĺ��ѵ�����
	 */
	@Override
	public List<String> findAllNameByUserName(String username) {
		List<String> names=new ArrayList<String>();
		String sql="select haoyou from haoyou h,qq q where h.pid=q.qid and q.username = ?";
		ResultSet rs= DButil.doQuery(sql, username);
		try {
			while(rs.next()){
				String name=rs.getString("haoyou");
				names.add(name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return names;
	}

	/**
	 * ��Ӻ���
	 */
	@Override
	public int save(String name,int pid) {
		String sql="insert into haoyou(haoyou,pid) values(?,?)";
		int i= DButil.doUpdate(sql, name,pid);
		return i;
	}

}
