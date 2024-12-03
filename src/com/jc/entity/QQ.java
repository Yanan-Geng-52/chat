package com.jc.entity;

/**
 * QQ实体类
 * @author 尤少辉
 * @date: 2018年4月12日 上午10:37:36
 */
public class QQ {
	private int qid;
	private String qname;
	private String qpassword;
	private String username;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public String getQname() {
		return qname;
	}
	public void setQname(String qname) {
		this.qname = qname;
	}
	public String getQpassword() {
		return qpassword;
	}
	public void setQpassword(String qpassword) {
		this.qpassword = qpassword;
	}
	public QQ(int qid, String qname, String qpassword,String username) {
		super();
		this.qid = qid;
		this.qname = qname;
		this.qpassword = qpassword;
		this.username=username;
	}
	public QQ(String qname, String qpassword,String username) {
		super();
		this.qname = qname;
		this.qpassword = qpassword;
		this.username=username;
	}
	public QQ() {
		super();
	}
	@Override
	public String toString() {
		return "QQ [qid=" + qid + ", qname=" + qname + ", qpassword=" + qpassword + ", username=" + username + "]";
	}
	
	
}
