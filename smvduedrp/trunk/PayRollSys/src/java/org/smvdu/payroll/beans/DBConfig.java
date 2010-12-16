/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import org.smvdu.payroll.beans.db.CommonDB;

/**
 *
 * @author Algox
 */
public class DBConfig {

    private  String hostName;
    private  String port;
    private  String username;
    private  String password;



    private String root;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getRoot() {
        return System.getProperty("user.dir");
    }

    public void setRoot(String root) {
        this.root = root;
    }

    private String dbName;

    public String getDbName() {
        return dbName;
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

    public  String getPassword() {
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
    

    public String testConnection()
    {
        message = new CommonDB().testConnection(this);
        return "success";
    }


}
