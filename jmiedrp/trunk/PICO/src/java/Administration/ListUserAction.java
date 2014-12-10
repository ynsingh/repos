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

public class ListUserAction extends DevelopmentSupport {

	private ErpmusersDAO erpmusersDao = new ErpmusersDAO();
	private String message;
	private List<Erpmusers> ruList = new ArrayList<Erpmusers>();
	
	public List<Erpmusers> getruList() {
        	return this.ruList;
    	}

	public void setruList(List<Erpmusers> ruList) {
        	this.ruList = ruList;
    	}
	
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
			//ruList = erpmusersDao.findAll();
			ruList = erpmusersDao.findGUser();
            		return SUCCESS;
		} catch (Exception e) {
			message = "Exception in ViewUserList method -> " + e.getMessage() + " Reported Cause is: " + e.getCause();
            		return ERROR;
        	}
    }
}
