package com.jc.main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jc.dao.HaoYouDao;
import com.jc.dao.QQDao;
import com.jc.dao.impl.HaoYouDaoImpl;
import com.jc.dao.impl.QQDaoImpl;
import com.jc.entity.QQ;

public class Findfriends extends JFrame {

	private JPanel contentPane;
	private JTextField textField;


	/**
	 * Create the frame.
	 */
	public Findfriends(String qname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u67E5\u8BE2\u7684\u540D\u5B57");
		label.setFont(new Font("宋体", Font.BOLD, 20));
		label.setBounds(60, 54, 105, 46);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(215, 54, 141, 46);
		contentPane.add(textField);
		textField.setColumns(10);
		
		/**
		 * 添加好友
		 */
		JButton button = new JButton("\u6DFB\u52A0\u597D\u53CB");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String haoyou=textField.getText();
				QQDao qqDao=new QQDaoImpl();
				QQ qq= qqDao.findByName(qname);
				QQ qq2=qqDao.findByName(haoyou);
				HaoYouDao haoYouDao=new HaoYouDaoImpl();
				haoYouDao.save(haoyou, qq.getQid());
				haoYouDao.save(qname, qq2.getQid());
				dispose();
			}
		});
		button.setBounds(52, 154, 113, 27);
		contentPane.add(button);
		
		/**
		 * 返回全聊的页面
		 */
		JButton button_1 = new JButton("\u8FD4\u56DE\u804A\u5929");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AllTalkFrame(qname).setVisible(true);
				dispose();
			}
		});
		button_1.setBounds(233, 154, 113, 27);
		contentPane.add(button_1);
	}
}
