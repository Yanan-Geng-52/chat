package com.jc.dao;

import java.util.List;

/**
 * ���ߵ��˵ķ�����
 * @author ���ٻ�
 * @date: 2018��4��13�� ����10:07:48
 */
public interface ZaiXianDao {
	public int save(String zname);
	public int delete(String zname);
	public List<String> findAll();
	public boolean check(String zname);
}
