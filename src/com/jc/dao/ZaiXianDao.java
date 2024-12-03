package com.jc.dao;

import java.util.List;

/**
 * 在线的人的方法名
 * @author 尤少辉
 * @date: 2018年4月13日 上午10:07:48
 */
public interface ZaiXianDao {
	public int save(String zname);
	public int delete(String zname);
	public List<String> findAll();
	public boolean check(String zname);
}
