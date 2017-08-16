package com.piyush.rest.domain;

/**
 * 
 * @author Piyush Chugh
 *
 */
public class MyThread extends Thread {	
    public MyThread(String name) {
    	super(name);
    }

    @Override
    public void run() {
        String [] arr = Thread.currentThread().getName().split("a");
    	Params msg = new Params(arr[0],arr[1]);
    	try {
			Thread.sleep(Integer.parseInt(msg.getTimeout())*1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}
    }
}
