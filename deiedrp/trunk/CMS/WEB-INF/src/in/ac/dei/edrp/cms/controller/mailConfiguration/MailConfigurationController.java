package in.ac.dei.edrp.cms.controller.mailConfiguration;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.ac.dei.edrp.cms.dao.mailConfiguration.MailConfigurationDao;
import in.ac.dei.edrp.cms.domain.mailConfiguration.MailConfigurationDomain;


import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class MailConfigurationController extends MultiActionController {

	MailConfigurationDao mailConfigurationDao;
	
	private static Logger logObj = Logger.getLogger(MailConfigurationController.class);
	
	public void setMailConfigurationDao(MailConfigurationDao mailConfigurationDao){
		this.mailConfigurationDao = mailConfigurationDao;
	}
	
	public ModelAndView getExistingConfigurationDetails(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		
		MailConfigurationDomain mailConfigurationDomain = new MailConfigurationDomain();
		HttpSession session = request.getSession(true);
		String universityId = session.getAttribute("universityId").toString();
		mailConfigurationDomain.setUniversityId(universityId);
		List<MailConfigurationDomain> detailsList = mailConfigurationDao.getExistingConfigurationDetails(mailConfigurationDomain);
		return new ModelAndView("mailConfiguration/details", "detailsList", detailsList);
	}
	
	public ModelAndView updateExistingConfigurationDetails(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		
		MailConfigurationDomain mailConfigurationDomain = new MailConfigurationDomain();
		HttpSession session = request.getSession(true);
		String universityId = session.getAttribute("universityId").toString();
		mailConfigurationDomain.setUniversityId(universityId);
		mailConfigurationDomain.setSmtpAddress(request.getParameter("smtpAddress"));
		mailConfigurationDomain.setSmtpPort(request.getParameter("smtpPort"));
		mailConfigurationDomain.setUserName(request.getParameter("userName"));
		mailConfigurationDomain.setPassword(request.getParameter("password"));
		mailConfigurationDomain.setModifierId(session.getAttribute("userId").toString());
		String statusValue = mailConfigurationDao.updateExistingConfigurationDetails(mailConfigurationDomain);
		return new ModelAndView("RegistrationForm/RegisterStudent", "result", statusValue);
	}
	
	public ModelAndView submitConfigurationDetails(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		
		MailConfigurationDomain mailConfigurationDomain = new MailConfigurationDomain();
		HttpSession session = request.getSession(true);
		String universityId = session.getAttribute("universityId").toString();
		mailConfigurationDomain.setUniversityId(universityId);
		mailConfigurationDomain.setSmtpAddress(request.getParameter("smtpAddress"));
		mailConfigurationDomain.setSmtpPort(request.getParameter("smtpPort"));
		mailConfigurationDomain.setUserName(request.getParameter("userName"));
		mailConfigurationDomain.setPassword(request.getParameter("password"));
		mailConfigurationDomain.setCreatorId(session.getAttribute("userId").toString());
		String statusValue = mailConfigurationDao.insertConfigurationDetails(mailConfigurationDomain);
		return new ModelAndView("RegistrationForm/RegisterStudent", "result", statusValue);
	}
	
	public ModelAndView uploadUniversityLogo(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		
		MailConfigurationDomain mailConfigurationDomain = new MailConfigurationDomain();
		HttpSession session = request.getSession(true);
		String sep = System.getProperty("file.separator");
		String universityId = session.getAttribute("universityId").toString();
		mailConfigurationDomain.setUniversityId(universityId);
		mailConfigurationDomain.setFileName(request.getParameter("fileName"));
		String str=request.getParameter("fileName");
		String f1=str.substring(0, str.indexOf("."));
		String extension=str.substring(str.indexOf("."));
		System.out.println("Request File Name : " +f1+" : "+extension);
		
		String rootPath = this.getServletContext().getRealPath(sep)+"UniversityLogos";
		File file = new File(rootPath);
		file.mkdir();
		
		String sourceFilePath = this.getServletContext().getRealPath(sep)+"StudentPhotos"+sep+mailConfigurationDomain.getFileName();
		
		File sourceFile = new File(sourceFilePath);
		
		String statusValue = "";
		
		if(extension.equalsIgnoreCase(".png")){
			String destinationFilePath = this.getServletContext().getRealPath(sep)+"UniversityLogos"+sep+universityId+extension;
			if(sourceFile.renameTo(new File(destinationFilePath))){
				System.out.println("File Moved Successfully");
				statusValue = "YES";
			}else{
				System.out.println("File Not Moved");
				statusValue = "NO";
			}
		}else{
			statusValue = "WrongFile";
		}
		
		return new ModelAndView("RegistrationForm/RegisterStudent", "result", statusValue);
	}
}
