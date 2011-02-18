/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.db.CommonDB;

/**
 *
 * @author Algox
 */
public class DBConfig {

    private   String hostName;
    private  String port;
    private   String username;
    private   String password;
    private  String dbName;

    private boolean connected;

    public boolean isConnected() {
        connected =new CommonDB().testConnection(this);
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
    
    public  String getDbName() {
        return dbName;
    }


    public String buildConnection()
    {
        new CommonDB().testConnection(this);
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Database Connected", "Database Connected Successfully. Plz Click on Login to Proceed"));
        return "fail";
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
    
    public  String getHost() {
        return hostName;
    }

    public  void setHost(String host) {
        this.hostName = host;
    }

    public   String getPassword() {
        return password;
    }

    public  void setPassword(String password) {
        this.password = password;
    }

    public  String getPort() {
        return port;
    }

    public  void setPort(String port) {
        this.port = port;
    }

    public  String getUsername() {
        return username;
    }

    public  void setUsername(String username) {
        this.username = username;
    }

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    

    public boolean testConnection()
    {
        
       return new CommonDB().testConnection(this);

    }


}
