package in.ac.dei.edrp.pms.adminConfig;

import java.util.Locale;
import java.util.ResourceBundle;

import in.ac.dei.edrp.pms.myMail.SendingMail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
/**
 * This class is used for configuring mail server for sending mails.
 * This class is only used by super admin.
 * @author <a href="http://aniltiwaripms.blogspot.com" target="_blank">Anil Kumar Tiwari</a>
 *
 */

public class MailConfigAction extends Action{
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	static final Logger logger = Logger.getLogger(MailConfigAction.class);
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		MailConfigForm mailconfigform = (MailConfigForm) form;
		Locale locale = new Locale("en", "US");
		ResourceBundle message = ResourceBundle.getBundle("in//ac//dei//edrp//pms//propertiesFile//ApplicationResources",locale);

		boolean bool=SendingMail.checkMailValidation(mailconfigform.getSmtpServerName(),
				mailconfigform.getSmtpServerPort(),mailconfigform.getMailFrom(),
				mailconfigform.getPassword(),message.getString("mail.subject.mailserver.confirmation"),
				message.getString("body.mail.confirmation"));
		ActionErrors errors = new ActionErrors();
		ActionMessage error=null;
		if(!bool)
		{
		WritePropertiesFile.mailConfig(getServlet().getServletContext().getRealPath("/")+"WEB-INF/",
				mailconfigform.getSmtpServerName(),mailconfigform.getSmtpServerPort(),
				mailconfigform.getMailFrom(),mailconfigform.getPassword());
		error = new ActionMessage("msg.mailConfigMsgSuccess");
		errors.add("mailConfigMsg",error);
		saveErrors(request,errors);
		}
		else
		{
			logger.info("mail server fail Sample info message");
	        logger.error("mail configuration failed, because your account authentication failed or" +
	        		" problem in your internet connection.");
			error = new ActionMessage("msg.mailConfigMsgFail");
			errors.add("mailConfigMsg",error);
			saveErrors(request,errors);
		}
		return mapping.findForward("mailconfigsuccess");
	}
}
