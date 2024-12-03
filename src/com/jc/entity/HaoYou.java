package com.jc.entity;

/**
 * 好友的类
 * @author 尤少辉
 * @date: 2018年4月12日 下午3:52:20
 */
public class HaoYou {
	private int hid;
	private String haoyou;
	private int pid;
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public String getHaoyou() {
		return haoyou;
	}
	public void setHaoyou(String haoyou) {
		this.haoyou = haoyou;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public HaoYou(int hid, String haoyou, int pid) {
		super();
		this.hid = hid;
		this.haoyou = haoyou;
		this.pid = pid;
	}
	public HaoYou(String haoyou, int pid) {
		super();
		this.haoyou = haoyou;
		this.pid = pid;
	}
	public HaoYou() {
		super();
	}
	
}
