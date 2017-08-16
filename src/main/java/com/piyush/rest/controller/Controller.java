package com.piyush.rest.controller;

import java.lang.Thread.State;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piyush.rest.domain.MyThread;
import com.piyush.rest.domain.Params;
import com.piyush.rest.domain.Status;

/**
 * 
 * @author Piyush Chugh
 *
 */
@RestController
public class Controller {
	
	static Map<Integer,String> paramsMap = new HashMap<Integer,String>();
	static Map<Integer,Integer> returnMap = new HashMap<Integer,Integer>();
	static Map<Integer,Thread> threadMap = new HashMap<Integer,Thread>();
	static boolean isRequestKilled = false;

    @GET
    @RequestMapping("/api/request")
	public Object message(@QueryParam("connId") String connId,@QueryParam("timeout") String timeout) {
    	isRequestKilled = false;
    	MyThread t1 = new MyThread(connId+"a"+timeout);
    	String [] arr = t1.getName().split("a");
    	Params msg = new Params(arr[0],arr[1]);
    	paramsMap.put(Integer.parseInt(msg.getConnId()),msg.getTimeout()+"a"+System.currentTimeMillis());
    	t1.start();
    	threadMap.put(Integer.parseInt(msg.getConnId()),t1);
    	try {
			Thread.sleep(Integer.parseInt(msg.getTimeout())*1000);
		} catch (InterruptedException | NumberFormatException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}
    	if(isRequestKilled) {
    		return new Status("killed");
    	} else {
    		return new Status("ok");
    	}
    }
    
    @RequestMapping("/api/serverStatus")
    public Map<Integer,Integer> message() {
    	for(Entry<Integer, String> set : paramsMap.entrySet()) {
    		String [] arr = set.getValue().split("a");
    		Integer timeout = Integer.parseInt(arr[0]);
    		long threadStartTime = Long.parseLong(arr[1]);
    		long difference = System.currentTimeMillis() - threadStartTime;
    		int diff = (int)Math.ceil((difference/1000));
    		if(timeout-diff > 0) {
    			returnMap.put(set.getKey(),timeout - Integer.valueOf(diff));
    		} else {
    			returnMap.remove(set.getKey());
    		}
    	}	    	
    	
    	String [] s = {"Hello"};
    	int i = s.length;
    	
    	String s324 = "asd";
    	s324.length();
    	
    	int [] aa = {1,2,3,4};
    	
    	
    	Map<Integer,Integer> falana = new HashMap<Integer,Integer>();
    	return returnMap;
    }
    
    @PutMapping("/api/kill")
    public Object message(@RequestBody String connId) {
    	isRequestKilled = true;
    	Integer connIdInt = Integer.parseInt(connId.substring(connId.length()-1, connId.length()));
    	Thread t = threadMap.get(connIdInt);
    	while(true) {
    		if(t == null) {
    			return new Status("invalid connection Id : "+connIdInt);
    		}
    		else {
    			t.interrupt();
    			if(t.getState().equals(State.TERMINATED)) {
    				return new Status("ok");
    			}
    		}	    			
    	}	    	  	
    }
}
