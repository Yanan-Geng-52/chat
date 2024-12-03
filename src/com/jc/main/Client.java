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
 * ���ӷ�����
 * @author ���ٻ�
 * @date: 2018��4��12�� ����11:56:25
 */
public class Client {
	PrintStream ps;
	BufferedReader br;
	Socket clientSocket;

	public Client(String clientName) {
		try {
			// �����ͻ���sSocket
			clientSocket = new Socket("127.0.0.1", 9999);
//			System.out.println(clientName + "���ӷ������ɹ�");
			ZaiXianDao zaiXianDao=new ZaiXianDaoImpl();
			boolean flat=true;
			for(int i=0;i<clientName.length();i++){
				if((clientName.charAt(i)+"").equals("��")){
					flat=false;
					break;
				}
			}
			if(flat){
				if(zaiXianDao.check(clientName)){
					zaiXianDao.save(clientName);
				}
			}
			// ���������
			OutputStream os = clientSocket.getOutputStream();
			ps = new PrintStream(os);

			// ����������
			InputStream is = clientSocket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			br = new BufferedReader(isr);

		} catch (UnknownHostException e) {
			System.out.println("��δ������������");
		} catch (IOException e) {
			System.out.println("��δ������������");
		} finally {
			// clientSocket.close();
		}

	}
}
