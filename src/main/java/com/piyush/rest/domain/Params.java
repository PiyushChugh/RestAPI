package com.piyush.rest.domain;

/**
 * 
 * @author Piyush Chugh
 *
 */
public class Params {		 
    String connId;
    String timeout;
 
    public Params(String connId, String timeout) {
        this.connId = connId;
        this.timeout = timeout;
    }
 
    public String getConnId() {
        return connId;
    }
 
    public String getTimeout() {
        return timeout;
    }
}
