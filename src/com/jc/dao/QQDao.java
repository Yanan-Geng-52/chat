package com.jc.dao;

import java.util.List;

import com.jc.entity.QQ;

/**
 * QQ��������
 * @author ���ٻ�
 * @date: 2018��4��12�� ����3:51:58
 */
public interface QQDao {
	public int save(QQ qq);
	public String check(String qname,String qpassword);
	public boolean noExits(String qname);
	public List<String> findAllName();
	public QQ findByName(String qname);
}
