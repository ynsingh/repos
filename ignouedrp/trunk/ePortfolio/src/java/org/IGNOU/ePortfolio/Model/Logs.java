package org.IGNOU.ePortfolio.Model;
// Generated 24 Sep, 2013 4:17:53 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Logs generated by hbm2java
 */
public class Logs  implements java.io.Serializable {


     private Long logId;
     private String userId;
     private Date loginTime;
     private Date logoutTime;
     private String ip;

    public Logs() {
    }

    public Logs(String userId, Date loginTime, Date logoutTime, String ip) {
       this.userId = userId;
       this.loginTime = loginTime;
       this.logoutTime = logoutTime;
       this.ip = ip;
    }
   
    public Long getLogId() {
        return this.logId;
    }
    
    public void setLogId(Long logId) {
        this.logId = logId;
    }
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public Date getLoginTime() {
        return this.loginTime;
    }
    
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
    public Date getLogoutTime() {
        return this.logoutTime;
    }
    
    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }
    public String getIp() {
        return this.ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }




}

