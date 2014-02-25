package com.ericl.tcp.client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ericl.tcp.share.ShareParam;
import com.ericl.tcp.thread.Handler;
import com.ericl.tcp.ui.MainFrame;

public class Client {
	private MainFrame frame = null;
	public static void main(String[] args) {
		new MainFrame(new Client());
	}
	
	
	public void setFrame(MainFrame frame) {
		this.frame = frame;
	}

	public void start(){
		
		final String ip; //����������ַ
		final int port; //�˿�
		final int count; //������
		final String message;
		try{
			ip = frame.getIp();
			port = Integer.parseInt(frame.getPort());
			count = Integer.parseInt(frame.getCount());
			message = frame.getMessage();
		} catch (Exception e){
			frame.setComment("��ʽ��������,����������!");
			return;
		}
		
		ExecutorService pools = Executors.newCachedThreadPool();
		// ����POOL_SIZE������
		for (int i = 0; i < count; i++) {
			pools.execute(new Handler(ip,port,message));
		}
		// �����̳߳���ֹ����, �ȴ�����ִ���Լ���δִ�е��������
		pools.shutdown();
		
		// ��ѯ�̳߳��е������Ƿ�ȫ������
		while(!pools.isTerminated()){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
		}
		String result = "<html>����, �ܲ�����:"+count+"<br/>"+
						"�ɹ���������:"+ShareParam.getSuccessed()+
						"ʧ�ܲ�������:"+ShareParam.getFailed()+"</html>";
		frame.setComment(result);
		
		ShareParam.resetShareParam();
	}
}
