package com.jc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jc.dao.ZaiXianDao;
import com.jc.util.DButil;

/**
 * 实现在线的方法
 * @author 尤少辉
 * @date: 2018年4月13日 上午10:09:39
 */
public class ZaiXianDaoImpl implements ZaiXianDao {

	/**
	 * 添加
	 */
	@Override
	public int save(String zname) {
		String sql="insert into zaixian(zname) values(?)";
		int i= DButil.doUpdate(sql, zname);
		return i;
	}

	/**
	 * 删除
	 */
	@Override
	public int delete(String zname) {
		String sql="delete from zaixian where zname=?";
		int i= DButil.doUpdate(sql, zname);
		return i;
	}

	/**
	 * 检验重复的
	 */
	public boolean check(String zname){
		String sql="select zname from zaixian";
		ResultSet rs= DButil.doQuery(sql, null);
		try {
			while(rs.next()){
				if(zname.equals(rs.getString("zname"))){
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}
	/**
	 * 查询全部
	 */
	@Override
	public List<String> findAll() {
		List<String> znames=new ArrayList<String>();
		String sql="select zname from zaixian";
		ResultSet rs= DButil.doQuery(sql, null);
		try {
			while(rs.next()){
				String name=rs.getString("zname");
				znames.add(name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return znames;
	}

}
