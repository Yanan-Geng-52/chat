package com.jc.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.jc.dao.ZaiXianDao;
import com.jc.dao.impl.ZaiXianDaoImpl;

/**
 * 连接服务器
 * @author 尤少辉
 * @date: 2018年4月12日 上午11:56:25
 */
public class Client {
	PrintStream ps;
	BufferedReader br;
	Socket clientSocket;

	public Client(String clientName) {
		try {
			// 创建客户端sSocket
			clientSocket = new Socket("127.0.0.1", 9999);
//			System.out.println(clientName + "连接服务器成功");
			ZaiXianDao zaiXianDao=new ZaiXianDaoImpl();
			boolean flat=true;
			for(int i=0;i<clientName.length();i++){
				if((clientName.charAt(i)+"").equals("和")){
					flat=false;
					break;
				}
			}
			if(flat){
				if(zaiXianDao.check(clientName)){
					zaiXianDao.save(clientName);
				}
			}
			// 启用输出流
			OutputStream os = clientSocket.getOutputStream();
			ps = new PrintStream(os);

			// 启用输入流
			InputStream is = clientSocket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			br = new BufferedReader(isr);

		} catch (UnknownHostException e) {
			System.out.println("您未能连接上主机");
		} catch (IOException e) {
			System.out.println("您未能连接上主机");
		} finally {
			// clientSocket.close();
		}

	}
}
