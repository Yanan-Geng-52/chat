package com.jc.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.jc.dao.ZaiXianDao;
import com.jc.dao.impl.ZaiXianDaoImpl;
import com.jc.main.Client;

/**
 * 服务器开启，获取所有的登录人的信息
 * @author 尤少辉
 * @date: 2018年4月11日 下午3:24:42
 */
public class Server {
	ServerSocket server;
	static int clientNum = 0;
	String s;
	List<String> names=new ArrayList<>();
	// 存放与服务器连接上的对应的Socket，作用是保存服务器与客户端之间的流，便于服务器给每个客户端进行回发消息
	List<Socket> clientConnection = new ArrayList<Socket>();

	public Server() {
		try {
			server = new ServerSocket(9999);
			System.out.println("服务器已经启动");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("服务器启动失败");
		}
	}

	// 内部类，监听客户端是否有连接到服务器，并将此客户端的Socket传递给HandleSocket进行处理，同时将client存放到List中，即clientConnection中
	class SocketListener implements Runnable {
		public void run() {
			Socket client;
			try {
				while (true) {
					client = server.accept();
					// 连接上一个就压入List中，即clientConnection中
					clientConnection.add(client);
					HandleSocket hs = new HandleSocket(client);
					// 连接上就让HandleSocket去处理
					new Thread(hs).start();
				}
			} catch (IOException e) {
				//				System.out.println("客户连接服务器失败");
			}
		}
	}

	// 内部类 处理一个Socket,接收一个Client发送过来的消息，并且服务器原封不动的返回给所有客户端，客户端对消息进行过滤
	class HandleSocket implements Runnable {
		Socket client;
		HandleSocket(Socket client) {
			this.client = client;
		}
		public void run() {
			try {
				clientNum++;
				// 启用输入流
				InputStream is = client.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				//  System.out.println("第" + clientNum + "个客户端连接进入服务器");
				boolean flag = true;
				do {
					// 对用户发来的消息进行群发给客户端
					s = br.readLine();
					//					System.out.println("111"+s);
					System.out.println("接受到一个客户端消息：" + s);
					for (int i = 0; i < clientConnection.size(); i++) {
						Socket client = clientConnection.get(i);
						//						System.out.println(client.getInetAddress().getHostName());
						OutputStream os = client.getOutputStream();
						PrintStream ps = new PrintStream(os);
						ps.println(s);
					}
				} while (flag);
				client.close();
			} catch (IOException e) {
				if(s!=null){
					String name;
					String stou=s.substring(0, 2);
					if(stou.equals("私聊")){
						String[] s1=s.split("和");
						name=s1[0].substring(2, s1[0].length());
					}else{
						String[] s1=s.split("说");
						name=s1[0];
					}
					names.add(name);
					ZaiXianDao zaiXianDao=new ZaiXianDaoImpl();
					zaiXianDao.delete(name);
					System.out.println(name+"客户断开与服务器的连接");
					clientNum--;
				}
				//				System.out.println("客户端有人开挂");
			}
		}
	}

	public static void main(String[] args) {
		Server server = new Server();
		SocketListener listener = server.new SocketListener();
		new Thread(listener).start();
	}

}
