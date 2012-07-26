/*
 * ACTION FORM
 */

package com.myapp.struts.admin;
public class MailSettingActionForm extends org.apache.struts.action.ActionForm {
    
     private String faddress;
     private String password;
     private String host;
     private String port;
     private String button;
     private String sname;

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }
    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getFaddress() {
        return faddress;
    }

    public void setFaddress(String faddress) {
        this.faddress = faddress;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public MailSettingActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    
}
