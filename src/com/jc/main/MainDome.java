package com.jc.main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jc.dao.QQDao;
import com.jc.dao.impl.QQDaoImpl;
import com.jc.regist.QQRegist;

/**
 * 登录界面
 * @author 尤少辉
 * @date: 2018年4月12日 上午11:43:47
 */
public class MainDome extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new MainDome();
	}

	/**
	 * Create the frame.
	 */
	public MainDome() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblQq = new JLabel("QQ\u8D26\u53F7");
		lblQq.setFont(new Font("宋体", Font.BOLD, 20));
		lblQq.setBounds(87, 39, 75, 24);
		contentPane.add(lblQq);
		
		JLabel lblQq_1 = new JLabel("QQ\u5BC6\u7801");
		lblQq_1.setFont(new Font("宋体", Font.BOLD, 20));
		lblQq_1.setBounds(87, 98, 75, 24);
		contentPane.add(lblQq_1);
		
		textField = new JTextField();
		textField.setBounds(204, 36, 136, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		/**
		 * 登录界面
		 */
		JButton button = new JButton("\u767B\u5F55");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String qname=textField.getText();
				String qpassword=new String(passwordField.getPassword());
				if(qname.equals("")||qpassword.equals("")){
				     JOptionPane.showMessageDialog(null, "每个输入项的内容不能为空");
				}else{
					QQDao qqDao=new QQDaoImpl();
					String username=qqDao.check(qname, qpassword);
					if(username!=null){
//						JOptionPane.showMessageDialog(null, "登录成功");
						new AllTalkFrame(username).setVisible(true);
						dispose();
					}else{
						JOptionPane.showMessageDialog(null, "账号，密码不匹配，请重新登录");
					}
				}
			}
		});
		button.setBounds(68, 169, 113, 27);
		contentPane.add(button);
		
		/**
		 * 注册/找回密码
		 */
		JButton button_1 = new JButton("\u6CE8\u518C/\u627E\u56DE\u5BC6\u7801");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new QQRegist();
				dispose();
			}
		});
		button_1.setBounds(227, 169, 136, 27);
		contentPane.add(button_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(204, 93, 136, 38);
		contentPane.add(passwordField);
		this.setVisible(true);
	}
}
