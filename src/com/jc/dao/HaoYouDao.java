package com.jc.dao;

import java.util.List;

public interface HaoYouDao {
	public List<String> findAllNameByUserName(String username);
	public int save(String name,int pid);
}
