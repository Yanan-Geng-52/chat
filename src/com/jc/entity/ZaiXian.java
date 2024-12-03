package com.jc.entity;

/**
 * 在线的人
 * @author 尤少辉
 * @date: 2018年4月13日 上午10:06:26
 */
public class ZaiXian {
	private int zid;
	private String name;
	public int getZid() {
		return zid;
	}
	public void setZid(int zid) {
		this.zid = zid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ZaiXian(int zid, String name) {
		super();
		this.zid = zid;
		this.name = name;
	}
	public ZaiXian(String name) {
		super();
		this.name = name;
	}
	public ZaiXian() {
		super();
	}
	
}
