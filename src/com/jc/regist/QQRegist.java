package com.jc.regist;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jc.dao.QQDao;
import com.jc.dao.impl.QQDaoImpl;
import com.jc.entity.QQ;
import com.jc.main.MainDome;

/**
 * QQע��
 * @author ���ٻ�
 * @date: 2018��4��12�� ����11:44:25
 */
public class QQRegist extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new QQRegist();
	}

	/**
	 * Create the frame.
	 */
	public QQRegist() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 469);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblQq = new JLabel("QQ\u8D26\u53F7");
		lblQq.setFont(new Font("����", Font.BOLD, 20));
		lblQq.setBounds(94, 51, 76, 31);
		contentPane.add(lblQq);
		
		JLabel lblQq_1 = new JLabel("QQ\u5BC6\u7801");
		lblQq_1.setFont(new Font("����", Font.BOLD, 20));
		lblQq_1.setBounds(94, 112, 76, 31);
		contentPane.add(lblQq_1);
		
		JLabel label = new JLabel("\u91CD\u590D\u5BC6\u7801");
		label.setFont(new Font("����", Font.BOLD, 20));
		label.setBounds(94, 178, 84, 31);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(214, 47, 138, 42);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(214, 108, 138, 42);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(214, 174, 138, 42);
		contentPane.add(textField_2);
		
		/**
		 * QQע��
		 */
		JButton button = new JButton("\u6CE8\u518C");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String qname= textField.getText();
				String qpassword=textField_1.getText();
				String congfu=textField_2.getText();
				String username=textField_3.getText();
				QQDao qqDao=new QQDaoImpl();
				if(qname.equals("")||qpassword.equals("")||congfu.equals("")||username.equals("")){
					JOptionPane.showMessageDialog(null, "ÿ����������ݲ���Ϊ��");
				}else if(!qpassword.equals(congfu)){
					JOptionPane.showMessageDialog(null, "������ظ����벻һ��");
				}else{
					boolean flat=qqDao.noExits(qname);
					if(flat){
						QQ qq=new QQ(qname, qpassword,username);
						int i= qqDao.save(qq);
						if(i==1){
							JOptionPane.showMessageDialog(null, "ע��ɹ�");
							new MainDome();
							dispose();
						}else{
							JOptionPane.showMessageDialog(null, "ע��ʧ�ܣ�������ע��");
						}
					}else{
						JOptionPane.showMessageDialog(null, "���û����Ѿ���ע����ˣ�������ע��");
					}
				}
			}
		});
		button.setBounds(94, 319, 113, 27);
		contentPane.add(button);
		
		/**
		 * ����ı���
		 */
		JButton button_1 = new JButton("\u6E05\u7A7A");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
			}
		});
		button_1.setBounds(238, 319, 113, 27);
		contentPane.add(button_1);
		
		JLabel lblQq_2 = new JLabel("qq\u8D26\u53F7");
		lblQq_2.setFont(new Font("����", Font.BOLD, 20));
		lblQq_2.setBounds(94, 251, 84, 31);
		contentPane.add(lblQq_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(214, 240, 138, 42);
		contentPane.add(textField_3);
		this.setVisible(true);
	}
}
