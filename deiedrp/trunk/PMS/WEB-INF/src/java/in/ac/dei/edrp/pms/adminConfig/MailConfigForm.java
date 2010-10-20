package in.ac.dei.edrp.pms.adminConfig;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

public class MailConfigForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String smtpServerPort;
	private String smtpServerName;
	private String mailFrom;
	private String password;
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
		super.reset(mapping,request);
		}
	
	public String getSmtpServerPort() {
		return smtpServerPort;
	}
	public void setSmtpServerPort(String smtpServerPort) {
		this.smtpServerPort = smtpServerPort;
	}
	public String getSmtpServerName() {
		return smtpServerName;
	}
	public void setSmtpServerName(String smtpServerName) {
		this.smtpServerName = smtpServerName;
	}
	public String getMailFrom() {
		return mailFrom;
	}
	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
