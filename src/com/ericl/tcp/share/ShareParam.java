package com.ericl.tcp.share;

import java.util.concurrent.atomic.AtomicInteger;

public class ShareParam {
	private static AtomicInteger success = new AtomicInteger(0);  
	private static AtomicInteger fail = new AtomicInteger(0);  
	
	public static void refreshSuccess(){
		success.incrementAndGet();
	}
	public static void refreshFailed(){
		fail.incrementAndGet();
	}
	
	public static int getSuccessed(){
		return success.get();
	}
	
	public static int getFailed(){
		return fail.get();
	}
	
	public static void resetShareParam(){
		success = new AtomicInteger(0);
		fail = new AtomicInteger(0);
	}
}
