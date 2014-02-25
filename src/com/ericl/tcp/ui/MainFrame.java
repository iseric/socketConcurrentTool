package com.ericl.tcp.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.ericl.tcp.client.Client;

public class MainFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel comment = null;
	
	private JTextField ip = null;
	private JTextField port = null;
	private JTextField count = null;
	private JTextArea message = null;
	
	private Client client = null;
	
	private int height = 550;
	private int width = 350;
	
	
	public MainFrame(Client client){
		this.client = client;
		client.setFrame(this);
		init();
	}
	private void init(){
		setTitle("TCP协议并发测试工具");
		setSize(height, width);
		setLocationRelativeTo(null);
		setContentPane(createMainUI());
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private JPanel createMainUI(){
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(BorderLayout.NORTH,createInput());
		panel.add(BorderLayout.CENTER,createTextArea());
		panel.add(BorderLayout.SOUTH,createButton());
		return panel;
	}
	
	private JPanel createInput(){
		JPanel p = new JPanel();
		
		p.add(new JLabel("　IP地址："));
		ip = new JTextField(14);
		p.add(ip);
		
		p.add(new JLabel("端口："));
		port = new JTextField(7);
		p.add(port);
		
		p.add(new JLabel("并发量："));
		count = new JTextField(7);
		p.add(count);
		
		return p;
	}
	
	private JPanel createTextArea(){
		JPanel panel = new JPanel(new FlowLayout());
		panel.add(new JLabel("报文内容："));
		message = new JTextArea(10,40);
		message.setLineWrap(true);
		JScrollPane scr = new JScrollPane(message);
		comment = new JLabel();
		panel.add(scr);
		panel.add(comment);
		return panel;
	}
	
	private JPanel createButton(){
		JPanel p = new JPanel();
		JButton b = new JButton("发送");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comment.setText("并发测试中...请稍后!");
				client.start();
			}
		});
		p.add(b);
		return p;
	}
	
	public void setComment(String str){
		comment.setText(str);
	}
	public String getIp() {
		return ip.getText().trim();
	}
	public String getPort() {
		return port.getText().trim();
	}
	public String getCount() {
		return count.getText().trim();
	}
	public String getMessage() {
		return message.getText().trim();
	}
}
