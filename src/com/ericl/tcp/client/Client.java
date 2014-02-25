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
		
		final String ip; //请求主机地址
		final int port; //端口
		final int count; //并发量
		final String message;
		try{
			ip = frame.getIp();
			port = Integer.parseInt(frame.getPort());
			count = Integer.parseInt(frame.getCount());
			message = frame.getMessage();
		} catch (Exception e){
			frame.setComment("格式输入有误,请重新输入!");
			return;
		}
		
		ExecutorService pools = Executors.newCachedThreadPool();
		// 创建POOL_SIZE个并发
		for (int i = 0; i < count; i++) {
			pools.execute(new Handler(ip,port,message));
		}
		// 发起线程池终止请求, 等待正在执行以及尚未执行的任务结束
		pools.shutdown();
		
		// 轮询线程池中的任务是否全部结束
		while(!pools.isTerminated()){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
		}
		String result = "<html>您好, 总并发量:"+count+"<br/>"+
						"成功并发数量:"+ShareParam.getSuccessed()+
						"失败并发数量:"+ShareParam.getFailed()+"</html>";
		frame.setComment(result);
		
		ShareParam.resetShareParam();
	}
}
