package Administration;

/**
 * @author Jaivir Singh
 */
import utils.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import pojo.hibernate.ErpmTenderMasterDAO;
import com.opensymphony.xwork2.ActionContext;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import org.apache.struts2.ServletActionContext;
import pojo.hibernate.ErpmusersDAO;
import pojo.hibernate.Erpmusers;

import utils.DevelopmentSupport;
import java.util.Locale;
import utils.ExceptionLogUtil;
import utils.HibernateEncryptionUtil;
import utils.sendMail;
import java.util.ResourceBundle;

public class ListUserAction extends DevelopmentSupport {

	private ErpmusersDAO erpmusersDao = new ErpmusersDAO();
	private String message;
	private List<Erpmusers> ruList = new ArrayList<Erpmusers>();
	//private List<Erpmusers> uName = new ArrayList<Erpmusers>();
	//private String erpmuName;
	private String erpmuId;
	public Erpmusers erpmusers;
	public Erpmusers erpmusers2;
	//private Erpmusers erpmUsers = new Erpmusers();
	//public String erpmuPassword;	

	public void setMesssge(String message) {
            this.message = message;
    	}
	
	public Erpmusers geterpmusers() {
        	return erpmusers;
	}
	public void seterpmusers(Erpmusers erpmusers) {
        this.erpmusers = erpmusers;
    	}

     	public String getMessage() {
        	return message;
    	}

	public List<Erpmusers> getruList() {
        	return this.ruList;
    	}

	public void setruList(List<Erpmusers> ruList) {
        	this.ruList = ruList;
    	}
    /*	
	public String getErpmuName() {
        	return this.erpmuName;
    	}
*/	
	public void setErpmuId(String erpmuId) {
        	this.erpmuId = erpmuId;
    	}

    	public String getErpmuId() {
        return this.erpmuId;
    }
/*	public String getErpmuPassword() {
        return this.erpmuPassword;
    }

    public void setErpmuPassword(String erpmuPassword) {
        this.erpmuPassword = erpmuPassword;
    }
*/

	
	public String execute() throws Exception {
        	try {
            		return SUCCESS;
        	} catch (Exception e) {
            		message = "Exception in -> ListUserAction in execute method";
            		return ERROR;
        	}
    	}

	public String ViewUsersList() {
        	try {
			ruList = erpmusersDao.findGUser();
            		return SUCCESS;
		} catch (Exception e) {
			message = "Exception in ViewUserList method -> " + e.getMessage() + " Reported Cause is: " + e.getCause();
            		return ERROR;
        	}
    	}
	public String ChangePassword() {
        	try {
			erpmusers=erpmusersDao.findByUserId(Integer.parseInt(getErpmuId()));
            		return SUCCESS;
		} catch (Exception e) {
			message = "Exception in ViewUserList method -> " + e.getMessage() + " Reported Cause is: " + e.getCause();
            		return ERROR;
        	}
    	}
	public String UpdatePassword() {
        	try {
			erpmusers2 = erpmusersDao.findByUserId(erpmusers.getErpmuId());
			erpmusers2=erpmusers;
			String pswd=erpmusers2.getErpmuPassword();
			erpmusers.setErpmuVerifiedBy("NULL");
            		String encrptdpswd = HibernateEncryptionUtil.createDigest("MD5",erpmusers2.getErpmuPassword());
            		erpmusers.setErpmuPassword(encrptdpswd);
			erpmusersDao.update(erpmusers2);
			Locale locale = ActionContext.getContext().getLocale();
            		ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
            		if(!bundle.getString("emailFrom").equals("") && !bundle.getString("emailUser").equals("") && !bundle.getString("emailFromPasswd").equals("")) {
                		String toEmailAddress = erpmusers.getErpmuName();
                		String emailSubject = "Pico Password Changed";
                		String emailMessage = "<html><head><title>Dear " + erpmusers.getErpmuFullName()+"</title></head><br /><body><table width='500' border='0' align='center' cellpadding='15' cellspacing='0' style='font-family:Verdana, Arial, Helvetica, sans-serif; font-size:12pt; color:#5a5a5a;'><tr><td>Your LoginId is " + erpmusers.getErpmuName() + ",</td></tr><tr><td align='left'>Your password is:<br/> " + pswd + "<br /><br/><tr><td><p>Thank you for using this site.<br /></p><br/><br/><p>Regards,<br />Administrator, PICO Module<br /></p><p><br /><br />THIS IS AN AUTOMATED MESSAGE; PLEASE DO NOT REPLY. </p></td></tr></table></body></html>";
                		sendMail.sendMail(bundle.getString("emailFrom"), bundle.getString("emailUser"), bundle.getString("emailFromPasswd"), toEmailAddress, "", emailSubject, emailMessage);
           		}
            		message = "Password Changed Successfully.A mail has been send to your mailId containing password";
            		return SUCCESS;
		} catch (Exception e) {
			message = "Exception in ViewUserList method -> " + e.getMessage() + " Reported Cause is: " + e.getCause();
            		return ERROR;
        	}
    }
}
