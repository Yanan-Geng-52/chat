package com.jc.entity;

/**
 * ���ߵ���
 * @author ���ٻ�
 * @date: 2018��4��13�� ����10:06:26
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
