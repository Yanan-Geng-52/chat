package com.jc.dao;

import java.util.List;

import com.jc.entity.QQ;

/**
 * QQ方法名字
 * @author 尤少辉
 * @date: 2018年4月12日 下午3:51:58
 */
public interface QQDao {
	public int save(QQ qq);
	public String check(String qname,String qpassword);
	public boolean noExits(String qname);
	public List<String> findAllName();
	public QQ findByName(String qname);
}
