/**
 * @(#) StudentMasterAPIController.java
 * Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproducuce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 */
package in.ac.dei.edrp.apicontroller;

import in.ac.dei.edrp.api.StudentMasterBeanAPI;
import in.ac.dei.edrp.cms.dao.studentmaster.StudentMasterService;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.ResponseWrapper;

import org.apache.commons.lang.StringUtils;
import org.iitk.brihaspati.modules.utils.security.EncrptDecrpt;
import org.iitk.brihaspati.modules.utils.security.ReadNWriteInTxt;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.mysql.jdbc.exceptions.MySQLDataException;

/**
 * this is Server side controller class for Student API
 * 
 * @version 1.0 01 Aug 2012
 * @author Nupur Dixit
 */

public class StudentMasterAPIController extends MultiActionController {

	/** creating object of StudentMasterService interface */
	private StudentMasterService studentMasterService;

	/** defining setter method for object of StudentMasterService interface */
	public void setStudentMasterService(
			StudentMasterService studentMasterService) {
		this.studentMasterService = studentMasterService;
	}
	
	private static String hdir=System.getProperty("user.home");
    private static String path=hdir+"/remote_auth/dei-remote-access.properties";
	
	/**
	 * This method check and get student Info from database and map to a jsp
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing student Info
	 */
	@ResponseWrapper	
	public ModelAndView chkUserExist(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("inside controller");
		StudentMasterBeanAPI input = new StudentMasterBeanAPI();
		input.setUserType(request.getParameter("emailId"));
		String rand = request.getParameter("randomPassword");
		String hash = request.getParameter("hashCode");
		String answer = studentMasterService.checkExistanceOfEmailId(input);
		response.setContentType("text/xml; charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter pw = null;
		try {
			pw = response.getWriter();			
		} catch (IOException e) {
			 response.setHeader("Errored", "true");
	         response.setHeader("ErrorType", "system");
	         response.setHeader("ErrorMessage", e.getMessage());
	         answer="false";
			e.printStackTrace();
		}
		pw.write("<answer>");
		pw.write(answer);
		pw.write("</answer>");
		pw.write("<randomNumber>");
		pw.write(rand);
		pw.write("</randomNumber>");
		pw.write("<hash>");
		pw.write(hash);
		pw.write("</hash>");
		pw.close();
        return null;
	}

	
	/**
	 * This method check and get student Info from database and map to a jsp
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing student Info
	 */
	@ResponseWrapper	
	public ModelAndView getStudentInformation(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("inside controller");
		List<StudentMasterBeanAPI> studentRecord = new ArrayList<StudentMasterBeanAPI>();
		StudentMasterBeanAPI input = new StudentMasterBeanAPI();
		input.setUserEmailId(request.getParameter("emailId"));
		input.setUniversityId(request.getParameter("univCode"));
		String randomPassword = request.getParameter("randomPassword");
		String hashCode = request.getParameter("hashCode");		
		String srcId = request.getParameter("srcId");
		response.setContentType("text/xml; charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter pw = null;
        try {
        	pw = response.getWriter();

        } catch (IOException e) {
        	studentRecord.clear();
        	System.out.println("inisde exception in controller size after exception"+studentRecord.size());
        	e.printStackTrace();
        	response.setHeader("Errored", "true");
        	response.setHeader("ErrorType", "system");
        	response.setHeader("ErrorMessage", e.getMessage());	        
        	return null;	        
        }
        try{
        	if(!chkHashCode(srcId,hashCode,randomPassword)){
        		pw.write("Hash Code mismatched !!! Sorry you are not the authorized person to access the data"+"\n Please contact DEI admin ");	
        		pw.close();
        		return null;
        	}

        	studentRecord = studentMasterService.getStudentInfo(input);
        	if(studentRecord.size()==0){
        		pw.write("No user exist with this email Id");
        	}
        	else{
        		String stuName,fatherName,motherName;
        		for(StudentMasterBeanAPI stuRecord:studentRecord){		
        			stuName = stuRecord.getStudentFirstName()==null?"":stuRecord.getStudentFirstName();
        			stuName = stuName.concat(stuRecord.getStudentMiddleName()==null?"":" "+stuRecord.getStudentMiddleName());
        			stuName = stuName.concat(stuRecord.getStudentLastName()==null?"":" "+stuRecord.getStudentLastName());
        			fatherName = stuRecord.getFatherFirstName()==null?"":stuRecord.getFatherFirstName();
        			fatherName = fatherName.concat(stuRecord.getFatherMiddleName()==null?"":" "+stuRecord.getFatherMiddleName());
        			fatherName = fatherName.concat(stuRecord.getFatherLastName()==null?"":" "+stuRecord.getFatherLastName());
        			motherName = stuRecord.getMotherFirstName()==null?"":stuRecord.getMotherFirstName();
        			motherName = motherName.concat(stuRecord.getMotherMiddleName()==null?"":" "+stuRecord.getMotherMiddleName());
        			motherName = motherName.concat(stuRecord.getMotherLastName()==null?"":" "+stuRecord.getMotherLastName());
        			pw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+'\n');				
        			pw.write("<StudentInfo>"+'\n');
        			pw.write("\t <studentName>");
        			pw.write(stuName);
        			pw.write("</studentName>"+'\n');
        			pw.write("\t <EnrollmentNumber>");
        			pw.write(stuRecord.getEnrollmentNumber()==null?"Not Available":stuRecord.getEnrollmentNumber());
        			pw.write("</EnrollmentNumber>"+'\n');
        			pw.write("\t <Category>");
        			pw.write(stuRecord.getCategoryName()==null?"Not Available":stuRecord.getCategoryName());
        			pw.write("</Category>"+'\n');
        			pw.write("\t <UID>");
        			pw.write(stuRecord.getUID()==null?"Not Available":stuRecord.getUID());
        			pw.write("</UID>"+'\n');
        			pw.write("\t <panNo>");
        			pw.write(stuRecord.getPanNo()==null?"Not Available":stuRecord.getPanNo());
        			pw.write("</panNo>"+'\n');
        			pw.write("\t <passportNo>");
        			pw.write(stuRecord.getPassportNo()==null?"Not Available":stuRecord.getPassportNo());
        			pw.write("</passportNo>"+'\n');
        			pw.write(" \t <fatherName>");
        			pw.write(fatherName);
        			pw.write("</fatherName>"+'\n');
        			pw.write("\t <motherName>");
        			pw.write(motherName);
        			pw.write("</motherName>"+'\n');
        			pw.write("\t <primaryEmailId>");
        			pw.write(stuRecord.getPrimaryEmailId()==null?"Not Available":stuRecord.getPrimaryEmailId());
        			pw.write("</primaryEmailId>"+'\n');
        			pw.write("\t <secondaryEmailId>");
        			pw.write(stuRecord.getSecondaryEmailId()==null?"Not Available":stuRecord.getSecondaryEmailId());
        			pw.write("</secondaryEmailId>"+'\n');
        			pw.write("\t <dateOfBirth>");
        			pw.write((stuRecord.getDateOfBirth()+"").equalsIgnoreCase("2000-01-01")?"Not available":stuRecord.getDateOfBirth()+"");
        			pw.write("</dateOfBirth>"+'\n');
        			pw.write("\t <placeOfBirth>");
        			pw.write(stuRecord.getPlaceOfBirth()==null?"Not Available":stuRecord.getPlaceOfBirth());
        			pw.write("</placeOfBirth>"+'\n');
        			pw.write("\t <religion>");
        			pw.write(stuRecord.getReligion()==null?"Not Available":stuRecord.getReligion());
        			pw.write("</religion>"+'\n');
        			pw.write("\t <gender>");
        			pw.write(stuRecord.getGender()==null?"Not Available":stuRecord.getGender());
        			pw.write("</gender>"+'\n');
        			pw.write("\t <maritalStatus>");
        			pw.write(stuRecord.getMaritalStatus()==null?"Not Available":stuRecord.getMaritalStatus());
        			pw.write("</maritalStatus>"+'\n');
        			pw.write("\t <nationality>");
        			pw.write(stuRecord.getNationality()==null?"Not Available":stuRecord.getNationality());
        			pw.write("</nationality>"+'\n');				
        			pw.write("\t <randomPassword>");
        			pw.write(randomPassword);
        			pw.write("</randomPassword>"+'\n');
        			pw.write("\t <hashCode>");
        			pw.write(hashCode);
        			pw.write("</hashCode>"+'\n');
        			pw.write("</StudentInfo>");
        		}
        	}
        } catch (IOException e) {
        	System.out.println("inisde exception in get student info"+e.getMessage());
        	e.printStackTrace();
        	response.setHeader("Errored", "true");
        	response.setHeader("ErrorType", "system");
        	response.setHeader("ErrorMessage", e.getMessage());
        	response.setContentType("text/html");
        	e.printStackTrace();
        }catch (ClassNotFoundException e) {
        	System.out.println("inisde exception in get student info"+e.getMessage());
        	e.printStackTrace();
        	response.setHeader("Errored", "true");
        	response.setHeader("ErrorType", "system");
        	response.setHeader("ErrorMessage", e.getMessage());
        	response.setContentType("text/html");
        	e.printStackTrace();
        }catch (MySQLDataException e) {
        	System.out.println("inisde mysql exception in controller get student info"+e.getMessage());
        	e.printStackTrace();
        	response.setHeader("Errored", "true");
        	response.setHeader("ErrorType", "system");
        	response.setHeader("ErrorMessage", e.getMessage());
        	response.setContentType("text/html");
        	e.printStackTrace();
        } catch (Exception e) {
        	System.out.println("inisde exception in controller get student info"+e.getMessage());
        	e.printStackTrace();
        	response.setHeader("Errored", "true");
        	response.setHeader("ErrorType", "system");
        	response.setHeader("ErrorMessage", e.getCause().toString());
        	response.setContentType("text/html");
        	e.printStackTrace();
        }      

        pw.close();
        return null;
	}

	private boolean chkHashCode(String srcId, String hashCode,String randomPassword) {
		//code to check the hash code
		String kline=ReadNWriteInTxt.readLin(path,srcId);
		String skey=StringUtils.substringBetween(kline,";",";");
		String genHashCode=EncrptDecrpt.keyedHash(srcId,randomPassword,skey);
		if(!genHashCode.equalsIgnoreCase(hashCode)){
			return false;
		}
		return true;
	}

	public ModelAndView updateStudentInformation(HttpServletRequest request,HttpServletResponse response){                       
		String isInserted = "";
		ObjectOutputStream oos = null;
		HashMap<String,String> parmMap = new HashMap<String,String>();
		try {			
			response.setContentType("application/x-java-serialized-object");
			InputStream in;
			// read a String-object from applet
			// instead of a String-object, you can transmit any object, which
			// is known to the servlet and to the applet
			in = request.getInputStream();
			ObjectInputStream inputFromApplet = new ObjectInputStream(in);
			StudentMasterBeanAPI studentBean = (StudentMasterBeanAPI) inputFromApplet.readObject();
			System.out.println("student first name "+studentBean.getStudentFirstName()); 
			String params = (String)inputFromApplet.readObject();
			String param[] = params.split("&");
			for(String paramIndep:param){
				parmMap.put(((paramIndep.split("="))[0]), ((paramIndep.split("="))[1]));
			}

			OutputStream outstr = response.getOutputStream();
			oos = new ObjectOutputStream(outstr);
			if(!chkHashCode(parmMap.get("srcId"),parmMap.get("hashCode") ,parmMap.get("randomPassword") )){
				oos.writeObject("Hash Code mismatched !!! Sorry you are not the authorized person to update the data"+"\n Please contact DEI admin ");	
				oos.flush();
				oos.close();
				return null;
			}
			else{
				isInserted = studentMasterService.updateStudentPersonalInfo(studentBean);		        
				oos.writeObject(isInserted);
				oos.flush();
				oos.close();
			}	        
		} catch (IOException e) {
			System.out.println("inisde exception in update StudentInfo"+e.getMessage());
			e.printStackTrace();
			response.setHeader("Errored", "true");
			response.setHeader("ErrorType", "system");
			response.setHeader("ErrorMessage", e.getMessage());
			response.setContentType("text/html");
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			System.out.println("inisde exception in update StudentInfo"+e.getMessage());
			e.printStackTrace();
			response.setHeader("Errored", "true");
			response.setHeader("ErrorType", "system");
			response.setHeader("ErrorMessage", e.getMessage());
			response.setContentType("text/html");
			e.printStackTrace();
		}catch (MySQLDataException e) {
			System.out.println("inisde mysql exception in controller contactInfo"+e.getMessage());
			e.printStackTrace();
			response.setHeader("Errored", "true");
			response.setHeader("ErrorType", "system");
			response.setHeader("ErrorMessage", e.getMessage());
			response.setContentType("text/html");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("inisde exception in controller contactInfo"+e.getMessage());
			e.printStackTrace();
			response.setHeader("Errored", "true");
			response.setHeader("ErrorType", "system");
			response.setHeader("ErrorMessage", e.getCause().toString());
			response.setContentType("text/html");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This method check and get student contact Info from database and map to a jsp
	 * 
	 * @param request
	 * @param response
	 * @return null ModelAndView
	 */
	@ResponseWrapper	
	public ModelAndView getContactInfo(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("inside controller");
		List<StudentMasterBeanAPI> studentRecord = new ArrayList<StudentMasterBeanAPI>();
		StudentMasterBeanAPI input = new StudentMasterBeanAPI();
		input.setUserEmailId(request.getParameter("emailId"));
		input.setUniversityId(request.getParameter("univCode"));
		String randomPassword = request.getParameter("randomPassword");
		String hashCode = request.getParameter("hashCode");		
		String srcId = request.getParameter("srcId");
		response.setContentType("text/xml; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();

		} catch (IOException e) {
			studentRecord.clear();
			System.out.println("inisde exception in controller size after exception"+studentRecord.size());
			e.printStackTrace();
			response.setHeader("Errored", "true");
			response.setHeader("ErrorType", "system");
			response.setHeader("ErrorMessage", e.getMessage());	        
			return null;	        
		}
		try{
			if(!chkHashCode(srcId,hashCode,randomPassword)){
				pw.write("Hash Code mismatched !!! Sorry you are not the authorized person to access the data"+"\n Please contact DEI admin ");	
				pw.close();
				return null;
			}

			studentRecord = studentMasterService.getContactInfo(input);
			if(studentRecord.size()==0){
				pw.write("No user exist with this email Id");
			}
			else{
				String addressKey,corAdd = "",perAdd = "",corCity = "",perCity= "",corState= "",perState= "",perCountry="",corCountry="",corPin= "",perPin= "",
				corOffPhone = "",perOffPhone="",corOtherPhone="",
				perOtherPhone="",corHomePhone="",perHomePhone="",corFax="",perFax="";
				for(StudentMasterBeanAPI stuRecord:studentRecord){		
					addressKey = stuRecord.getAddressKey();
					if(addressKey.equalsIgnoreCase("cor")){
						corAdd = stuRecord.getAddressLineOne();
						corCity = stuRecord.getCity();
						corState = stuRecord.getState();
						corCountry = stuRecord.getCountry();
						corPin = stuRecord.getPinCode();
						corOffPhone = stuRecord.getOfficePhone();
						corOtherPhone = stuRecord.getOtherPhone();
						corHomePhone = stuRecord.getHomePhone();
						corFax = stuRecord .getFax();
					}
					else if(addressKey.equalsIgnoreCase("per")){
						perAdd = stuRecord.getAddressLineOne();
						perCity = stuRecord.getCity();
						perState = stuRecord.getState();
						perCountry = stuRecord.getCountry();
						perPin = stuRecord.getPinCode();
						perOffPhone = stuRecord.getOfficePhone();
						perOtherPhone = stuRecord.getOtherPhone();
						perHomePhone = stuRecord.getHomePhone();
						perFax = stuRecord .getFax();
					}				
				}//end for											
				pw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+'\n');				
				pw.write("<ContactInfo>"+'\n');
				pw.write('\t'+"<CorrespondenceAddress>"+'\n');
				pw.write("\t \t"+"<address>");
				pw.write(corAdd+"");
				pw.write("</address>"+'\n');
				pw.write("\t \t"+"<city>");
				pw.write(corCity+"");
				pw.write("</city>"+'\n');
				pw.write("\t \t"+"<state>");
				pw.write(corState+"");
				pw.write("</state>"+'\n');
				pw.write("\t \t"+"<country>");
				pw.write(corCountry+"");
				pw.write("</country>"+'\n');
				pw.write("\t \t"+"<pinCode>");
				pw.write(corPin+"");
				pw.write("</pinCode>"+'\n');
				pw.write("\t \t"+"<officePhone>");
				pw.write(corOffPhone+"");
				pw.write("</officePhone>"+'\n');
				pw.write("\t \t"+"<homePhone>");
				pw.write(corHomePhone+"");
				pw.write("</homePhone>"+'\n');
				pw.write("\t \t"+"<otherPhone>");
				pw.write(corOtherPhone+"");
				pw.write("</otherPhone>"+'\n');
				pw.write("\t \t"+"<fax>");
				pw.write(corFax+"");
				pw.write("</fax>"+'\n');
				pw.write('\t'+"</CorrespondenceAddress>"+'\n');

				pw.write("\t"+"<PermanentAddress>"+'\n');
				pw.write("\t \t"+"<address>");
				pw.write(perAdd+"");
				pw.write("</address>"+'\n');
				pw.write("\t \t"+"<city>");
				pw.write(perCity+"");
				pw.write("</city>"+'\n');
				pw.write("\t \t"+"<state>");
				pw.write(perState+"");
				pw.write("</state>"+'\n');
				pw.write("\t \t"+"<country>");
				pw.write(perCountry+"");
				pw.write("</country>"+'\n');
				pw.write("\t \t"+"<pinCode>");
				pw.write(perPin+"");
				pw.write("</pinCode>"+'\n');
				pw.write("\t \t"+"<officePhone>");
				pw.write(perOffPhone+"");
				pw.write("</officePhone>"+'\n');
				pw.write("\t \t"+"<homePhone>");
				pw.write(perHomePhone+"");
				pw.write("</homePhone>"+'\n');
				pw.write("\t \t"+"<otherPhone>");
				pw.write(perOtherPhone+"");
				pw.write("</otherPhone>"+'\n');
				pw.write("\t \t"+"<fax>");
				pw.write(perFax+"");
				pw.write("</fax>"+'\n');
				pw.write("\t"+"</PermanentAddress>"+'\n');

				pw.write("<randomPassword>");
				pw.write(randomPassword+"");
				pw.write("</randomPassword>"+'\n');
				pw.write("<hashCode>");
				pw.write(hashCode+"");
				pw.write("</hashCode>"+'\n');	
				pw.write("</ContactInfo>"+'\n');
			}
		} catch (IOException e) {
			System.out.println("inisde exception in get parent info"+e.getMessage());
			e.printStackTrace();
			response.setHeader("Errored", "true");
			response.setHeader("ErrorType", "system");
			response.setHeader("ErrorMessage", e.getMessage());
			response.setContentType("text/html");
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			System.out.println("inisde exception in get parent info"+e.getMessage());
			e.printStackTrace();
			response.setHeader("Errored", "true");
			response.setHeader("ErrorType", "system");
			response.setHeader("ErrorMessage", e.getMessage());
			response.setContentType("text/html");
			e.printStackTrace();
		}catch (MySQLDataException e) {
			System.out.println("inisde mysql exception in controller get parent info"+e.getMessage());
			e.printStackTrace();
			response.setHeader("Errored", "true");
			response.setHeader("ErrorType", "system");
			response.setHeader("ErrorMessage", e.getMessage());
			response.setContentType("text/html");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("inisde exception in controller get parent info"+e.getMessage());
			e.printStackTrace();
			response.setHeader("Errored", "true");
			response.setHeader("ErrorType", "system");
			response.setHeader("ErrorMessage", e.getCause().toString());
			response.setContentType("text/html");
			e.printStackTrace();
		}      

		pw.close();
		return null;
	}

	/**
	 * This method update contact Info into database 
	 * @param request
	 * @param response
	 * @return null ModelAndView
	 */	
	public ModelAndView updateContactInformation(HttpServletRequest request,HttpServletResponse response){                       
		String isInserted = "";
		ObjectOutputStream oos = null;
		HashMap<String,String> parmMap = new HashMap<String,String>();
		try {			
			response.setContentType("application/x-java-serialized-object");
			InputStream in;			 
			in = request.getInputStream();
			ObjectInputStream inputFromApplet = new ObjectInputStream(in);
			StudentMasterBeanAPI studentBean = (StudentMasterBeanAPI) inputFromApplet.readObject();
			System.out.println("student first name "+studentBean.getStudentFirstName()); 
			String params = (String)inputFromApplet.readObject();
			String param[] = params.split("&");
			for(String paramIndep:param){
				parmMap.put(((paramIndep.split("="))[0]), ((paramIndep.split("="))[1]));
			}

			OutputStream outstr = response.getOutputStream();
			oos = new ObjectOutputStream(outstr);
			if(!chkHashCode(parmMap.get("srcId"),parmMap.get("hashCode") ,parmMap.get("randomPassword") )){
				oos.writeObject("Hash Code mismatched !!! Sorry you are not the authorized person to update the data"+"\n Please contact DEI admin ");	
				oos.flush();
				oos.close();
				return null;
			}
			else{	        	
				isInserted = studentMasterService.updateContactInfo(studentBean);	
				System.out.println("after exception throw"+isInserted);
				oos.writeObject(isInserted);
				oos.flush();
				oos.close();
			}	        
		} catch (IOException e) {
			System.out.println("inisde exception in update contactInfo"+e.getMessage());
			e.printStackTrace();
			response.setHeader("Errored", "true");
			response.setHeader("ErrorType", "system");
			response.setHeader("ErrorMessage", e.getMessage());
			response.setContentType("text/html");
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			System.out.println("inisde exception in update contactInfo"+e.getMessage());
			e.printStackTrace();
			response.setHeader("Errored", "true");
			response.setHeader("ErrorType", "system");
			response.setHeader("ErrorMessage", e.getMessage());
			response.setContentType("text/html");
			e.printStackTrace();
		}catch (MySQLDataException e) {
			System.out.println("inisde mysql exception in controller contactInfo"+e.getMessage());
			e.printStackTrace();
			response.setHeader("Errored", "true");
			response.setHeader("ErrorType", "system");
			response.setHeader("ErrorMessage", e.getMessage());
			response.setContentType("text/html");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("inisde exception in controller contactInfo"+e.getMessage());
			e.printStackTrace();
			response.setHeader("Errored", "true");
			response.setHeader("ErrorType", "system");
			response.setHeader("ErrorMessage", e.getCause().toString());
			response.setContentType("text/html");
			e.printStackTrace();
		}      
		return null;
	}

	
	/**
	 * This method check and get student contact Info from database and map to a jsp
	 * 
	 * @param request
	 * @param response
	 * @return null ModelAndView
	 */
	@ResponseWrapper	
	public ModelAndView getParentInfo(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("inside controller");
		List<StudentMasterBeanAPI> studentRecord = new ArrayList<StudentMasterBeanAPI>();
		StudentMasterBeanAPI input = new StudentMasterBeanAPI();
		input.setUserEmailId(request.getParameter("emailId"));
		input.setUniversityId(request.getParameter("univCode"));
		String randomPassword = request.getParameter("randomPassword");
		String hashCode = request.getParameter("hashCode");		
		String srcId = request.getParameter("srcId");
		response.setContentType("text/xml; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();

		} catch (IOException e) {
			studentRecord.clear();
			System.out.println("inisde exception in controller size after exception"+studentRecord.size());
			e.printStackTrace();
			response.setHeader("Errored", "true");
			response.setHeader("ErrorType", "system");
			response.setHeader("ErrorMessage", e.getMessage());	        
			return null;	        
		}
		try{
			if(!chkHashCode(srcId,hashCode,randomPassword)){
				pw.write("Hash Code mismatched !!! Sorry you are not the authorized person to access the data"+"\n Please contact DEI admin ");	
				pw.close();
				return null;
			}

			studentRecord = studentMasterService.getParentInfo(input);
			if(studentRecord.size()==0){
				pw.write("No user exist with this email Id");
			}
			else{
				String fatherName="",motherName = "",fatherNameHindi = "",motherNameHindi = "",address1= "",address2= "",phone1= "",phone2= "",
				email1= "",email2 = "";
				for(StudentMasterBeanAPI stuRecord:studentRecord){		
					fatherName = stuRecord.getFatherFirstName()==null?"":stuRecord.getFatherFirstName();
					motherName = stuRecord.getMotherFirstName()==null?"":stuRecord.getMotherFirstName();
					fatherNameHindi = stuRecord.getFatherHindiName()==null?"":stuRecord.getFatherHindiName();
					motherNameHindi = stuRecord.getMotherHindiName()==null?"":stuRecord.getMotherHindiName();
					address1 = stuRecord.getParentAddress1()==null?"":stuRecord.getParentAddress1();
					address2 = stuRecord.getParentAddress2()==null?"":stuRecord.getParentAddress2();
					phone1 = stuRecord.getParentPhone1()==null?"":stuRecord.getParentPhone1();
					phone2 = stuRecord.getParentPhone2()==null?"":stuRecord.getParentPhone2();
					email1 = stuRecord.getParentEmail1()==null?"":stuRecord.getParentEmail1();
					email2 = stuRecord.getParentEmail2()==null?"":stuRecord.getParentEmail2();																
				}//end for											
				pw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+'\n');				
				pw.write("<ParentInfo>"+'\n');
				pw.write('\t'+"<fatherName>");
				pw.write(fatherName+"");
				pw.write("</fatherName>"+'\n');
				pw.write('\t'+"<motherName>");
				pw.write(motherName+"");
				pw.write("</motherName>"+'\n');
				pw.write('\t'+"<fatherNameInHindi>");
				pw.write(fatherNameHindi+"");
				pw.write("</fatherNameInHindi>"+'\n');
				pw.write('\t'+"<motherNameInHindi>");
				pw.write(motherNameHindi+"");
				pw.write("</motherNameInHindi>"+'\n');
				pw.write('\t'+"<AddressLine1>");
				pw.write(address1+"");
				pw.write("</AddressLine1>"+'\n');
				pw.write('\t'+"<AddressLine2>");
				pw.write(address2+"");
				pw.write("</AddressLine2>"+'\n');
				pw.write('\t'+"<PrimaryPhone>");
				pw.write(phone1+"");
				pw.write("</PrimaryPhone>"+'\n');
				pw.write('\t'+"<SecondaryPhone>");
				pw.write(phone2+"");
				pw.write("</SecondaryPhone>"+'\n');
				pw.write('\t'+"<PrimaryEmail>");
				pw.write(email1+"");
				pw.write("</PrimaryEmail>"+'\n');
				pw.write('\t'+"<SecondaryEmail>");
				pw.write(email2+"");
				pw.write("</SecondaryEmail>"+'\n');				

				pw.write("<randomPassword>");
				pw.write(randomPassword+"");
				pw.write("</randomPassword>"+'\n');
				pw.write("<hashCode>");
				pw.write(hashCode+"");
				pw.write("</hashCode>"+'\n');	
				pw.write("</ParentInfo>"+'\n');
			}		
		} catch (IOException e) {
			System.out.println("inisde exception in get parent info"+e.getMessage());
			e.printStackTrace();
			response.setHeader("Errored", "true");
			response.setHeader("ErrorType", "system");
			response.setHeader("ErrorMessage", e.getMessage());
			response.setContentType("text/html");
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			System.out.println("inisde exception in get parent info"+e.getMessage());
			e.printStackTrace();
			response.setHeader("Errored", "true");
			response.setHeader("ErrorType", "system");
			response.setHeader("ErrorMessage", e.getMessage());
			response.setContentType("text/html");
			e.printStackTrace();
		}catch (MySQLDataException e) {
			System.out.println("inisde mysql exception in controller get parent info"+e.getMessage());
			e.printStackTrace();
			response.setHeader("Errored", "true");
			response.setHeader("ErrorType", "system");
			response.setHeader("ErrorMessage", e.getMessage());
			response.setContentType("text/html");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("inisde exception in controller get parent info"+e.getMessage());
			e.printStackTrace();
			response.setHeader("Errored", "true");
			response.setHeader("ErrorType", "system");
			response.setHeader("ErrorMessage", e.getCause().toString());
			response.setContentType("text/html");
			e.printStackTrace();
		}      
		pw.close();
		return null;
	}

	/**
	 * This method check and get student academic Info from database and map to a jsp
	 * 
	 * @param request
	 * @param response
	 * @return null ModelAndView
	 */
	@ResponseWrapper	
	public ModelAndView getAcademicInfo(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("inside controller");
		List<StudentMasterBeanAPI> studentRecord = new ArrayList<StudentMasterBeanAPI>();
		StudentMasterBeanAPI input = new StudentMasterBeanAPI();
		input.setRollNumber(request.getParameter("rollNo"));
		input.setUniversityId(request.getParameter("univCode"));
		String randomPassword = request.getParameter("randomPassword");
		String hashCode = request.getParameter("hashCode");		
		String srcId = request.getParameter("srcId");
		response.setContentType("text/xml; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();			
		} catch (IOException e) {
			studentRecord.clear();
			System.out.println("inisde exception in controller size after exception"+studentRecord.size());
			e.printStackTrace();
			response.setHeader("Errored", "true");
			response.setHeader("ErrorType", "system");
			response.setHeader("ErrorMessage", e.getMessage());	        
			return null;	        
		}
		try{
			if(!chkHashCode(srcId,hashCode,randomPassword)){
				pw.write("Hash Code mismatched !!! Sorry you are not the authorized person to access the data"+"\n Please contact DEI admin ");	
				pw.close();
				return null;
			}		 
			studentRecord = studentMasterService.getAcademicInfo(input);
			StudentMasterBeanAPI aa = studentRecord.get(0);
			if(aa.getErrorMsg()!=null){
				pw.write(aa.getErrorMsg());
			}
			else{
				for(StudentMasterBeanAPI stuRecord:studentRecord){
					pw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+'\n');				
					pw.write("<AcademicInfo>"+'\n');
					pw.write('\t'+"<RollNumber>");pw.write(stuRecord.getRollNumber()+"");pw.write("</RollNumber>"+'\n');
					pw.write('\t'+"<EnrollmentNumber>");pw.write(stuRecord.getEnrollmentNumber()+"");pw.write("</EnrollmentNumber>"+'\n');
					pw.write('\t'+"<MainProgram> \n");
					pw.write("\t\t"+"<Entity> \n");
					pw.write("\t\t\t"+"<Code>");pw.write(stuRecord.getEntityId()+"");pw.write("</Code> \n");
					pw.write("\t\t\t"+"<Name>");pw.write(stuRecord.getEntityName()+"");pw.write("</Name> \n");
					pw.write("\t\t"+"</Entity> \n");
					pw.write("\t\t"+"<Program> \n");
					pw.write("\t\t\t"+"<Code>");pw.write(stuRecord.getProgramId()+"");pw.write("</Code> \n");
					pw.write("\t\t\t"+"<Name>");pw.write(stuRecord.getProgramName()+"");pw.write("</Name> \n");
					pw.write("\t\t"+"</Program> \n");
					pw.write("\t\t"+"<Branch> \n");
					pw.write("\t\t\t"+"<Code>");pw.write(stuRecord.getBranchId()+"");pw.write("</Code> \n");
					pw.write("\t\t\t"+"<Name>");pw.write(stuRecord.getBranchName()+"");pw.write("</Name> \n");
					pw.write("\t\t"+"</Branch> \n");
					pw.write("\t\t"+"<Specialization> \n");
					pw.write("\t\t\t"+"<Code>");pw.write(stuRecord.getSpecializationId()+"");pw.write("</Code> \n");
					pw.write("\t\t\t"+"<Name>");pw.write(stuRecord.getSpecialization()+"");pw.write("</Name> \n");
					pw.write("\t\t"+"</Specialization> \n");
					pw.write("\t\t"+"<CGPA>");pw.write(stuRecord.getCgpa()+"");pw.write("</CGPA> \n");
					pw.write("\t\t"+"<Division>");pw.write(stuRecord.getDivision()+"");pw.write("</Division> \n");
					List<StudentMasterBeanAPI> semesterList = stuRecord.getSemesterList();
					for(StudentMasterBeanAPI semBean:semesterList){
						pw.write("\t\t"+"<Semester> \n");
						pw.write("\t\t\t"+"<Code>");pw.write(semBean.getSemesterCode()+"");pw.write("</Code> \n");
						pw.write("\t\t\t"+"<Name>");pw.write(semBean.getSemesterName()+"".trim());pw.write("</Name> \n");
						pw.write("\t\t\t"+"<StartDate>");pw.write(semBean.getSemesterStartDate()+"");pw.write("</StartDate> \n");
						pw.write("\t\t\t"+"<EndDate>");pw.write(semBean.getSemesterEndDate()+"");pw.write("</EndDate> \n");
						pw.write("\t\t\t"+"<SGPA>");pw.write(semBean.getSgpa()+"");pw.write("</SGPA> \n");
						pw.write("\t\t\t"+"<CGPA>");pw.write(semBean.getCgpa()+"");pw.write("</CGPA> \n");
						List<StudentMasterBeanAPI> courseList = semBean.getCourseList();
						for(StudentMasterBeanAPI courseBean:courseList){
							pw.write("\t\t\t"+"<course> \n");
							pw.write("\t\t\t\t"+"<Code>");pw.write(courseBean.getCourseCode()+"");pw.write("</Code> \n");
							pw.write("\t\t\t\t"+"<Name>");pw.write(courseBean.getCourseName()+"");pw.write("</Name> \n");
							pw.write("\t\t\t\t"+"<CourseStatus>");pw.write(courseBean.getCourseStatus()+"");pw.write("</CourseStatus> \n");
							pw.write("\t\t\t\t"+"<StudentStatus>");pw.write(courseBean.getStudentStatus()+"");pw.write("</StudentStatus> \n");
							pw.write("\t\t\t\t"+"<Marks> \n");
							pw.write("\t\t\t\t\t"+"<InternalMarks>");pw.write(courseBean.getTotalInternal()+"");pw.write("</InternalMarks> \n");
							pw.write("\t\t\t\t\t"+"<ExternalMarks>");pw.write(courseBean.getTotalExternal()+"");pw.write("</ExternalMarks> \n");
							pw.write("\t\t\t\t\t"+"<TotalMarks>");pw.write(courseBean.getTotalMarks()+"");pw.write("</TotalMarks> \n");
							pw.write("\t\t\t\t"+"</Marks> \n");
							pw.write("\t\t\t\t"+"<Grade> \n");
							pw.write("\t\t\t\t\t"+"<InternalGrade>");pw.write(courseBean.getInternalGrade()+"");pw.write("</InternalGrade> \n");
							pw.write("\t\t\t\t\t"+"<ExternalGrade>");pw.write(courseBean.getExternalGrade()+"");pw.write("</ExternalGrade> \n");
							pw.write("\t\t\t\t\t"+"<FinalGradePoint>");pw.write(courseBean.getFinalGradePoint()+"");pw.write("</FinalGradePoint> \n");
							pw.write("\t\t\t\t"+"</Grade> \n");																											
							pw.write("\t\t\t"+"</course> \n");
						}					
						pw.write("\t\t"+"</Semester> \n");
					}
					pw.write('\t'+"</MainProgram> \n");
					pw.write("\t"+"<randomPassword>");pw.write(randomPassword+"");pw.write("</randomPassword>"+'\n');
					pw.write("\t"+"<hashCode>");pw.write(hashCode+"");pw.write("</hashCode>"+'\n');	
					pw.write("</AcademicInfo> \n");							
				}
			}		
		} catch (IOException e) {
			System.out.println("inisde exception in get parent info"+e.getMessage());
			e.printStackTrace();
			response.setHeader("Errored", "true");
			response.setHeader("ErrorType", "system");
			response.setHeader("ErrorMessage", e.getMessage());
			response.setContentType("text/html");
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			System.out.println("inisde exception in get parent info"+e.getMessage());
			e.printStackTrace();
			response.setHeader("Errored", "true");
			response.setHeader("ErrorType", "system");
			response.setHeader("ErrorMessage", e.getMessage());
			response.setContentType("text/html");
			e.printStackTrace();
		}catch (MySQLDataException e) {
			System.out.println("inisde mysql exception in controller get parent info"+e.getMessage());
			e.printStackTrace();
			response.setHeader("Errored", "true");
			response.setHeader("ErrorType", "system");
			response.setHeader("ErrorMessage", e.getMessage());
			response.setContentType("text/html");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("inisde exception in controller get parent info"+e.getMessage());
			e.printStackTrace();
			response.setHeader("Errored", "true");
			response.setHeader("ErrorType", "system");
			response.setHeader("ErrorMessage", e.getCause().toString());
			response.setContentType("text/html");
			e.printStackTrace();
		}      
		pw.close();
		return null;
	}

	/**
	 * This method check and get student contact Info from database and map to a jsp
	 * 
	 * @param request
	 * @param response
	 * @return null ModelAndView
	 */
	@ResponseWrapper	
	public ModelAndView getRegistrationInfo(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("inside Registration controller");
		List<StudentMasterBeanAPI> studentRecord = new ArrayList<StudentMasterBeanAPI>();
		StudentMasterBeanAPI input = new StudentMasterBeanAPI();
		input.setUserEmailId(request.getParameter("emailId"));
		input.setUniversityId(request.getParameter("univCode"));
		String randomPassword = request.getParameter("randomPassword");
		String hashCode = request.getParameter("hashCode");		
		String srcId = request.getParameter("srcId");
		response.setContentType("text/xml; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();

		} catch (IOException e) {
			studentRecord.clear();
			System.out.println("inisde exception in Registration controller size after exception"+studentRecord.size());
			e.printStackTrace();
			response.setHeader("Errored", "true");
			response.setHeader("ErrorType", "system");
			response.setHeader("ErrorMessage", e.getMessage());	 	        
			return null;	        
		}
		try{
			if(!chkHashCode(srcId,hashCode,randomPassword)){
				pw.write("Hash Code mismatched !!! Sorry you are not the authorized person to access the data"+"\n Please contact DEI admin ");	
				pw.close();
				return null;
			}

			studentRecord = studentMasterService.getRegistrationInfo(input);
			if(studentRecord.size()==0){
				pw.write("No Registration details available for this email Id");
			}
			else{												
				pw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+'\n');
				pw.write("<RegistrationDetail>"+'\n');
				for(StudentMasterBeanAPI stuRecord:studentRecord){	
					pw.write("\t <Registration>"+'\n');
					pw.write('\t'+"\t <UniversityId>");
					pw.write(stuRecord.getUniversityId()+"");
					pw.write("</UniversityId>"+'\n');
					pw.write('\t'+"\t <UniversityName>");
					pw.write(stuRecord.getUniversityName()+"");
					pw.write("</UniversityName>"+'\n');
					pw.write('\t'+"\t <EnrollmentNumber>");
					pw.write(stuRecord.getEnrollmentNumber()+"");
					pw.write("</EnrollmentNumber>"+'\n');
					pw.write('\t'+"\t <RollNumber>");
					pw.write(stuRecord.getRollNumber()+"");
					pw.write("</RollNumber>"+'\n');
					pw.write('\t'+"\t <ProgramId>");
					pw.write(stuRecord.getProgramId()+"");
					pw.write("</ProgramId>"+'\n');
					pw.write('\t'+"\t <ProgramName>");
					pw.write(stuRecord.getProgramName()+"");
					pw.write("</ProgramName>"+'\n');
					pw.write('\t'+"\t <RegistrationDate>");
					pw.write(stuRecord.getRegisteredInSession()+"");
					pw.write("</RegistrationDate>"+'\n');
					pw.write("\t </Registration>"+'\n');																															
				}//end for	
				pw.write("\t <randomPassword>");
				pw.write(randomPassword+"");
				pw.write("</randomPassword>"+'\n');
				pw.write("\t <hashCode>");
				pw.write(hashCode+"");
				pw.write("</hashCode>"+'\n');	
				pw.write("</RegistrationDetail>"+'\n');
			}		
		} catch (IOException e) {
			System.out.println("inisde exception in get registration info"+e.getMessage());
			e.printStackTrace();
			response.setHeader("Errored", "true");
			response.setHeader("ErrorType", "system");
			response.setHeader("ErrorMessage", e.getMessage());
			response.setContentType("text/html");
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			System.out.println("inisde exception in get registration info"+e.getMessage());
			e.printStackTrace();
			response.setHeader("Errored", "true");
			response.setHeader("ErrorType", "system");
			response.setHeader("ErrorMessage", e.getMessage());
			response.setContentType("text/html");
			e.printStackTrace();
		}catch (MySQLDataException e) {
			System.out.println("inisde mysql exception in controller get registration info"+e.getMessage());
			e.printStackTrace();
			response.setHeader("Errored", "true");
			response.setHeader("ErrorType", "system");
			response.setHeader("ErrorMessage", e.getMessage());
			response.setContentType("text/html");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("inisde exception in controller get registration info"+e.getMessage());
			e.printStackTrace();
			response.setHeader("Errored", "true");
			response.setHeader("ErrorType", "system");
			response.setHeader("ErrorMessage", e.getCause().toString());
			response.setContentType("text/html");
			e.printStackTrace();
		}      
		pw.close();
		return null;
	}
}
