package pojo.hibernate;
// Generated May 30, 2013 2:31:14 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import utils.ExceptionLogUtil;

/**
 *
 *@author <a href="mailto:jaivirpal@gmail.com">Jaivir Singh</a>2016
 */ 

public class Edrpuserlaststatus  implements java.io.Serializable {


	private Integer edrpuId;
	private int edrpUserid;
	private Erpmusers erpmusers;
	private int edrpuName;
	private String edrpufName;
	private String edrpuPassword;
	private String edrpuEmail;
	private String componentreg;
	private Date mobile;
	private String status;
	private Date lang;

	public Edrpuserlaststatus() {
	}

	public Edrpuserlaststatus(int erpmuName, String erpmuPassword, String edrpuEmail) {
        	this.edrpuName = edrpuName;
        	this.edrpuPassword = edrpuPassword;
        	this.edrpuEmail = edrpuEmail;
    	}
	public Edrpuserlaststatus(int edrpuName,String edrpufName, String edrpuPassword,String edrpuEmail, String componentreg, Date mobile, Date lang, String status) {
       		this.edrpuName = edrpuName;
       		this.edrpufName = edrpufName;
		this.edrpuPassword = edrpuPassword;
		this.edrpuEmail = edrpuEmail;
		this.componentreg = componentreg;
		this.mobile = mobile;
		this.lang = lang;
		this.status = status;
    }
   
    public Integer getEdrpuId() {
        return this.edrpuId;
    }
    public void setEdrpuId(Integer edrpuId) {
        this.edrpuId = edrpuId;
    }
    public int getEdrpUserId() {
        return this.edrpUserid;
    }
    public void setEdrpUserId(int edrpUserid) {
        this.edrpUserid = edrpUserid;
    }
    public int getEdrpuName() {
        return this.edrpuName;
    }
    
    public void setEdrpuName(int edrpuName) {
        this.edrpuName = edrpuName;
    }
    public String getEdrpuPassword() {
        return this.edrpuPassword;
    }
    
    public void setEdrpuPassword(String edrpuPassword) {
        this.edrpuPassword = edrpuPassword;
    }
    public String getEdrpuEmail() {
        return this.edrpuEmail;
    }
    
    public void setEdrpuEmail(String edrpuEmail) {
        this.edrpuEmail = edrpuEmail;
    }

    public String getComponentreg() {
        return this.componentreg;
    }
    
    public void setComponentreg(String componentreg) {
        this.componentreg = componentreg;
    }

    public Date getMobile() {
        return this.mobile;
    }
    
    public void setMobile(Date mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Date getLang() {
        return this.lang;
    }
    public void setLang(Date lang) {
        this.lang = lang;
    }
    public String getEdrpufName() {
        return this.edrpufName;
    }
    public void setEdrpufName(String edrpufName) {
        this.edrpufName = edrpufName;
    }
}


