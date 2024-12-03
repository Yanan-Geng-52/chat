package com.jc.main;

import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import com.jc.dao.HaoYouDao;
import com.jc.dao.ZaiXianDao;
import com.jc.dao.impl.HaoYouDaoImpl;
import com.jc.dao.impl.ZaiXianDaoImpl;

public class AllTalkFrame extends JFrame {

	List<String> list=new ArrayList<String>();
	TextArea oldMessageTextArea;
	TextArea sendMessageTextArea;
	JList userList;
	JScrollPane userListPane;
	JButton btSend;
	JButton btClosed;
	JButton upLine;
	String doubleClickedName;
	Client client;
	String clientName;
	JLabel userlistTitle;
	Vector users;
	private JButton button;
	private JButton button_1;

	// ֻҪ������������Ϣ���ͽ���Ϣ��ʾ��oldMessageTextArea
	class showOldMessageThread implements Runnable {
		public void run() {
			boolean flag = true;
			while (flag) {
				try {
					// ����Ⱥ�ķ������˻ط���������Ϣ
					String serverOutput = client.br.readLine() + "\r\n";
					System.out.println(serverOutput+"111");
					if (!serverOutput.startsWith("˽��")
							&& !serverOutput.startsWith("*")
							&& !(serverOutput.substring(serverOutput
									.indexOf("��") + 1).equals("\r\n"))) {
						String s1 = serverOutput.replace('˵', ' ');
						String s = s1.replaceAll("", "\r\n     ");
						oldMessageTextArea.append(serverOutput);
					}

					// ��ӿͻ��˵��û������б�
					if (!serverOutput.startsWith("*")
							&& !serverOutput.startsWith("˽��")
							&& (serverOutput.indexOf("˵") != -1)) {
						String listName = serverOutput.substring(0,
								serverOutput.indexOf('˵'));
						// ���JList������ͬ���ֵ��û�������ӣ��������
						HaoYouDao haoYouDao=new HaoYouDaoImpl();
						List<String> haoyous= haoYouDao.findAllNameByUserName(clientName);
						ZaiXianDao zaiXianDao=new ZaiXianDaoImpl();
						List<String> names= zaiXianDao.findAll();
						for (String string:names) {
							if (!list.contains(string)&&!string.equals(clientName)&&haoyous.contains(string)) {
								//System.out.println("�û�" + listName + "������");
								list.add(string);
								users.add(string);
								userList.setListData(users);
							}
						}
					}

					// �жϷ������ط���������Ϣ�ǲ�����"˽��"��ͷ�ģ��ǵĻ�����ȡ���������û���
					if (serverOutput.startsWith("˽��")) {
						String siliaoName1 = serverOutput.substring(
								serverOutput.indexOf("*") + 1, serverOutput
								.indexOf("��"));
						String siliaoName2 = serverOutput.substring(
								serverOutput.indexOf("��") + 1, serverOutput
								.indexOf("\r"));
						String siliaoBenshen = "";
						String siliaoDuixiangName = "";
						if (siliaoName1.equals(clientName)) {
							siliaoBenshen = siliaoName1;
							siliaoDuixiangName = siliaoName2;
						} else {
							siliaoBenshen = siliaoName2;
							siliaoDuixiangName = siliaoName1;
						}
						System.out.println(siliaoName1+":"+siliaoName2);
						
						// �ж��������������Ƿ������Լ�ͬ���ģ��еĻ��͵�����˽�Ĵ�
							if (siliaoName1.equals(clientName)||siliaoName2.equals(clientName)) {
								new PointToPointTalkFrame(siliaoBenshen + "��"
										+ siliaoDuixiangName).setVisible(true);
							}
					}
				} catch (IOException e1) {
					System.out.println("��ȡ����������Ϣ����");
				}
			}
		}
	}

	AllTalkFrame(final String clientName) {
		this.clientName = clientName;
		client = new Client(clientName);
		this.setTitle("��ӭ����" + clientName);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int x = (int) screenSize.getWidth();
		int y = (int) screenSize.getHeight();
		this.setBounds((x - 600) / 2, (y - 600) / 2, 600, 600);
		this.setResizable(false);
		getContentPane().setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// �����Ѿ�����ȥ����Ϣ���ڵ�����
		oldMessageTextArea = new TextArea();
		oldMessageTextArea.setBounds(0, 0, 390, 360);

		// ����׼��������Ϣ���ڵ�����
		sendMessageTextArea = new TextArea(3, 3);
		sendMessageTextArea.setBounds(0, 380, 390, 140);
		sendMessageTextArea.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
				}
			}

		});

		// ����<����>��ť������
		upLine = new JButton("����");
		upLine.setBounds(0, 530, 70, 30);

		// ע��<����>��ť�ĵ���¼�
		upLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upLine.setEnabled(false);
				String s = sendMessageTextArea.getText();
				client.ps.println(clientName + "˵��" + s);
				sendMessageTextArea.setText("");
			}
		});

		// ����<����>��ť������
		btSend = new JButton("����");
		btSend.setBounds(240, 530, 70, 30);

		// ע��<����>��ť�ĵ���¼�
		btSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upLine.setEnabled(false);
				String s1 = sendMessageTextArea.getText();
				//String s = s1.replaceAll("\r\n", "");
				client.ps.println(clientName + "˵��" + s1);
				sendMessageTextArea.setText("");
			}
		});

		// ����<�ر�>��ť������
		btClosed = new JButton("�ر�");
		btClosed.setBounds(320, 530, 70, 30);

		// ע��<�ر�>��ť�ĵ���¼�
		btClosed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// AllTalkFrame.this.dispose();
				System.exit(0);
			}
		});

		// �����û��б�ı���
		userlistTitle = new JLabel("��ǰ�����û��б�,˫������˽��");
		userlistTitle.setBounds(400, 0, 200, 20);

		// �����û��б�JList����,������е����
		userList = new JList();
		userList.setBounds(400, 20, 200, 600);
		users = new Vector();


		// ע��JList�ĵ���¼�
		userList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (AllTalkFrame.this.userList.getSelectedValue()
							.toString().equals(clientName)) {
						JOptionPane.showMessageDialog(null, "���ܺ��Լ�����");
					} else {
						String PToPMemberName = "˽��"
								+ "*"
								+ clientName
								+ "��"
								+ AllTalkFrame.this.userList.getSelectedValue()
								.toString();
						System.out.println("�����Һ�������֪����"+PToPMemberName);
						client.ps.println(PToPMemberName);
					}
				}
			}
		});

		// �����û��б�JScrollPane������
		userListPane = new JScrollPane(userList,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		userListPane.setBounds(400, 0, 200, 600);

		// new һ��AllTalkFrameʱ�����߳���������ȡ�������˻ط�����Ϣ
		new Thread(new showOldMessageThread()).start();

		// �����������ӵ�������
		getContentPane().add(oldMessageTextArea);
		getContentPane().add(sendMessageTextArea);
		getContentPane().add(btSend);
		getContentPane().add(upLine);
		getContentPane().add(btClosed);
		getContentPane().add(userListPane);
		getContentPane().add(userlistTitle);

		/**
		 * ��Ӻ��ѵ�
		 */
		button_1 = new JButton("\u6DFB\u52A0\u597D\u53CB");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Findfriends(clientName).setVisible(true);
			}
		});
		button_1.setBounds(84, 526, 93, 34);
		getContentPane().add(button_1);

	}
}
