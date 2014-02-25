package com.ericl.tcp.thread;

import java.io.BufferedOutputStream;
import java.io.IOException;

import com.ericl.tcp.share.ShareParam;

public class Handler implements Runnable{
	
	private final String ip;
	private final int port;
	private final String message;
	
	private java.net.Socket socket = null;
	
	public Handler(final String ip, final int port, final String message){
		this.ip = ip;
		this.port = port;
		this.message = message;
	}
	
	@Override
	public void run() {
		try {
			// 发起一个tcp连接请求, 如果连接成功则累计成功次数, 否则反之
			socket = new java.net.Socket(ip, port);
			// 2014-2-25 新增报文输出
			BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
			byte[] ms = message.getBytes();
			bos.write(ms);
			bos.flush();
			ShareParam.refreshSuccess();
		} catch (Exception e) {
			ShareParam.refreshFailed();
		} finally{
			try {
				if(socket==null)
					return;
				socket.close();
			} catch (IOException e) {
			}
		}
	}

}
