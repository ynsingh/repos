/*
 * @(#) EDEISummaryImpl.java
 *Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproduce the above copyright
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

package in.ac.dei.edrp.server;

import in.ac.dei.edrp.client.EdeiAdmission.EDEIStudentBean;
import in.ac.dei.edrp.client.EdeiAdmission.EDeiSummaryService;
import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;
import in.ac.dei.edrp.server.summarysheet.SendEmail;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.w3c.dom.NodeList;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * 
 * @version 1.0 9 AUGUST 2012
 * @author UPASANA KULSHRESTHA
 */

@SuppressWarnings("serial")
public class EDeiSummaryImpl extends RemoteServiceServlet
implements EDeiSummaryService {
	SqlMapClient sqlMapClient = SqlMapManager.getSqlMapClient();
	Log4JInitServlet logObj = new Log4JInitServlet();

	private String padZero(Integer number, int length) {
		String output = String.valueOf(number);

		while (output.length() < length) {
			output = "0" + output;
		}

		return output;
	}

	@SuppressWarnings("unchecked")
	public List<EDEIStudentBean> getUniversityCourse(String universityId,String pgId) {
		List<EDEIStudentBean> studBean=new ArrayList<EDEIStudentBean>();
		EDEIStudentBean input=new EDEIStudentBean();
		try{
			input.setUniversityId(universityId);
			input.setProgramId(pgId);
			studBean=sqlMapClient.queryForList("getUniversityCoursesEDEI", input);
		}
		catch (Exception e) {
			System.out.println("Error inside getUniversityCourse:in EDeiSummaryImpl file ::"+e.getMessage());
			logObj.logger.error("Error inside getUniversityCourse:in EDeiSummaryImpl file ::"+e.getMessage());
		}
		return studBean;
	}

	@SuppressWarnings("unchecked")
	public List<EDEIStudentBean> getUniversityModules(String universityId,String pgId) {
		List<EDEIStudentBean> studBean=new ArrayList<EDEIStudentBean>();
		EDEIStudentBean input=new EDEIStudentBean();
		try{
			input.setUniversityId(universityId);
			input.setProgramId(pgId);
			studBean=sqlMapClient.queryForList("getUniversityModulesEDEI", input);
		}
		catch (Exception e) {
			logObj.logger.error("Error inside getUniversityModules:in EDeiSummaryImpl file ::"+e.getMessage());
			System.out.println("Error inside getUniversityModules:in EDeiSummaryImpl file ::"+e.getMessage());
		}
		return studBean;
	}

	@SuppressWarnings("unchecked")
	public List<EDEIStudentBean> Category(String uniId) {
		List<EDEIStudentBean> li=new ArrayList<EDEIStudentBean>();
		try {
			logObj.logger.info("in Category");
			EDEIStudentBean categoryBean = new EDEIStudentBean();
			categoryBean.setUniversityId(uniId);
			li = sqlMapClient.queryForList("categoryEDEI", categoryBean);
		} catch (Exception ex) {
			logObj.logger.error("Error inside Category:in EDeiSummaryImpl file ::"+ex.getMessage());
			System.out.println("Error inside Category:in EDeiSummaryImpl file ::"+ex.getMessage());
		}

		return li;
	}

	public String[][] getStateData(){
		String[][] data=null;
		String path= this.getServletContext().getRealPath("stateCity.xml");    	  
		File fXmlFile = new File(path);
		if(fXmlFile.exists()){
			javax.xml.parsers.DocumentBuilderFactory dbFactory = javax.xml.parsers.DocumentBuilderFactory.newInstance();          	
			javax.xml.parsers.DocumentBuilder dBuilder;
			try {  				
				dBuilder = dbFactory.newDocumentBuilder();  				
				org.w3c.dom.Document doc =dBuilder.parse(fXmlFile);  				
				doc.getDocumentElement().normalize();  				
				org.w3c.dom.NodeList stateList = doc.getElementsByTagName("state");  	    		
				data=new String[stateList.getLength()][2];
				for(int i=0;i<stateList.getLength();i++){
					org.w3c.dom.Node nNode = stateList.item(i);
					org.w3c.dom.Element element=(org.w3c.dom.Element) nNode;
					data[i][0]="st";
					data[i][1]=element.getAttribute("name");  	  	    			

				}
			} catch (Exception e) {
				logObj.logger.error("Error inside getStateData:in EDeiSummaryImpl file ::"+e.getMessage());
				System.out.println("Error inside getStateData:in EDeiSUmmaryImpl file ::"+e.getMessage());
			}
		}
		else{
			data[0][0]="FileNotFound";
			return data; 
		}
		return data;
	}

	public String[][]getCityData(String state){
		String[][] data=null;
		String path= this.getServletContext().getRealPath("stateCity.xml");     	 
		File fXmlFile = new File(path);
		if(fXmlFile.exists()){
			javax.xml.parsers.DocumentBuilderFactory dbFactory = javax.xml.parsers.DocumentBuilderFactory.newInstance();          	
			javax.xml.parsers.DocumentBuilder dBuilder;
			try {  				
				dBuilder = dbFactory.newDocumentBuilder();  				
				org.w3c.dom.Document doc =dBuilder.parse(fXmlFile);  				
				doc.getDocumentElement().normalize();  				
				org.w3c.dom.NodeList stateList = doc.getElementsByTagName("state");   	    		   	    			
				for(int i=0;i<stateList.getLength();i++){
					org.w3c.dom.Node nNode = stateList.item(i);
					org.w3c.dom.Element element=(org.w3c.dom.Element) nNode;
					if(state.equals(element.getAttribute("name"))){   	  	    				
						NodeList cityNodeList = element.getElementsByTagName("city");
						System.out.println("Total cities -----------------------"+cityNodeList.getLength());
						data=new String[cityNodeList.getLength()][2];
						for(int j=0;j<cityNodeList.getLength();j++){
							org.w3c.dom.Node childNode = cityNodeList.item(j);
							org.w3c.dom.Element childElement=(org.w3c.dom.Element) childNode;
							data[j][0]=childElement.getAttribute("code");
							data[j][1]=childElement.getTextContent();    	   	  	    			   	   	  	    				
						}
					}   	  	    			   	  	    			   	  	    			

				}
			} catch (Exception e) {
				logObj.logger.error("Error inside getCityData:in EDeiSummaryImpl file ::"+e.getMessage());
				System.out.println("Error inside getCityData:in EDeiSummaryImpl file ::"+e.getMessage());
			}
		}
		else{
			data[0][0]="FileNotFound";
			return data; 
		}
		return data;

	}

	@SuppressWarnings("unchecked")
	public List<EDEIStudentBean> getCourseModule(List<String> selectedCourseId, String universityId,String programId) {
		List<EDEIStudentBean> moduleList = new ArrayList<EDEIStudentBean>();
		EDEIStudentBean inputBean=new EDEIStudentBean();

		inputBean.setUniversityId(universityId);
		inputBean.setCourseCodeList(selectedCourseId);
		inputBean.setProgramId(programId);
		try {
			moduleList = sqlMapClient.queryForList("getCourseModuleList",inputBean);
		} catch (Exception ex) {
			logObj.logger.error("Error inside getCourseModule:in EDeiSummaryImpl file ::"+ex.getMessage());
			System.out.println("Error inside getCourseModule:in EDeiSummaryImpl file ::"+ex.getMessage());
		}		
		return moduleList;
	}

	@SuppressWarnings("unchecked")
	public List<EDEIStudentBean> getApplicantAccountDetails(EDEIStudentBean userEmail) {
		List<EDEIStudentBean> edeiList=new ArrayList<EDEIStudentBean>();
		try{
			edeiList=sqlMapClient.queryForList("getApplicantAccountDetailsEDEI",userEmail);
		}
		catch(Exception ex){
			logObj.logger.error("Error inside getApplicantAccountDetails:in EDeiSummaryImpl file ::"+ex.getMessage());
			System.out.println("Error inside getApplicantAccountDetails:in EDeiSummaryImpl file ::"+ex.getMessage());
		}
		return edeiList;
	}

	@SuppressWarnings("deprecation")
	public EDEIStudentBean insertEDEINEWSummarySheetDetails(EDEIStudentBean inputBean)  throws Exception {

		inputBean.setDob(new SimpleDateFormat("yyyy-MM-dd").format(new Date(inputBean.getDob())));
		try {
			sqlMapClient.startTransaction();
			inputBean.setUgPg("APPNUM");
			EDEIStudentBean sysValueObj = (EDEIStudentBean) sqlMapClient.queryForObject("getSystemValuesEDEI",inputBean);

			Integer seqNum = 1;
			String appNumber = null;

			if (sysValueObj != null) {
				seqNum=Integer.parseInt(sysValueObj.getSequence());
				seqNum = seqNum + 1;
				inputBean.setSequence(String.valueOf(seqNum));
				sqlMapClient.update("updateSystemValuesEDEI", inputBean);
				appNumber=inputBean.getUniversityNickName() + padZero(seqNum, 6);
				inputBean.setRegistrationNumber(appNumber);
			}

			EDEIStudentBean studentIdObj = (EDEIStudentBean) sqlMapClient.queryForObject("getStudentIdEDEI",inputBean);

			if (studentIdObj != null) {
				sqlMapClient.update("updateEntityStudentEDEI",inputBean);		//only update guardian name and other phone,minority,maritalStatus
				sqlMapClient.update("updateStudentAddressEDEI",inputBean);
			} else {
				sqlMapClient.insert("setEntityStudentEDEI", inputBean);
				sqlMapClient.insert("setStudentAddressEDEI", inputBean);
			}

			for (int d = 0; d < inputBean.getAttachmentList().size(); d++) {
				EDEIStudentBean documentBean = inputBean.getAttachmentList().get(d);
				documentBean.setRegistrationNumber(inputBean.getRegistrationNumber());
				documentBean.setUserId(inputBean.getUserId());
				String fileType = documentBean.getDocName().substring(documentBean.getDocName().lastIndexOf(".") +1);
				documentBean.setDocPath(documentBean.getDocPath() +File.separator + inputBean.getRegistrationNumber() + File.separator +inputBean.getStudentId()+File.separator+documentBean.getDocId() + "." + fileType);
				sqlMapClient.insert("setStudentDocsEDEI", documentBean);
			}

			for (int c = 0; c < inputBean.getAcademicDataList().size(); c++) {
				EDEIStudentBean academicBean = inputBean.getAcademicDataList().get(c);
				academicBean.setUniversityId(inputBean.getUniversityId());
				academicBean.setSessionStartDate(inputBean.getSessionStartDate());
				academicBean.setSessionEndDate(inputBean.getSessionEndDate());
				academicBean.setUserId(inputBean.getUserId());
				academicBean.setRegistrationNumber(inputBean.getRegistrationNumber());
				academicBean.setProgramId(inputBean.getProgramId());
				academicBean.setEntityId(inputBean.getEntityId());
				sqlMapClient.insert("setStudentCallListEDEI", academicBean);
			}

			for(int e=0;e<inputBean.getCourseModuleList().size();e++){
				EDEIStudentBean courseBean = inputBean.getCourseModuleList().get(e);
				courseBean.setRegistrationNumber(inputBean.getRegistrationNumber());
				courseBean.setSessionStartDate(inputBean.getSessionStartDate());
				courseBean.setSessionEndDate(inputBean.getSessionEndDate());
				courseBean.setUserId(inputBean.getUserId());
				courseBean.setProgramId(inputBean.getProgramId());
				courseBean.setStudentStatus("NEW");
				sqlMapClient.insert("setTempStudentModuleEDEI",courseBean);
			}

			inputBean.setCosCode(inputBean.getCategory()+"XX");
			sqlMapClient.insert("setStudentRegistrationEDEI", inputBean);

			sqlMapClient.commitTransaction();
			sqlMapClient.endTransaction();
			inputBean.setFileSeparator(File.separator);


		} catch (Exception e) {
			sqlMapClient.endTransaction();
			logObj.logger.error("Error inside insertEDEINEWSummarySheetDetails:in EDeiSummaryImpl file ::"+e.getMessage());
			System.out.println("Error inside insertEDEINEWSummarySheetDetails:in EDeiSummaryImpl file ::"+e.getMessage());
			throw e;
		}
		return inputBean;

	}

	public EDEIStudentBean generatePDF(EDEIStudentBean inputBean, String docPath) {
		try{
			Document document = new Document(PageSize.A4);
			System.out.println(docPath);

			new File(this.getServletContext().getRealPath(File.separator) +
					docPath + File.separator + inputBean.getStudentId() +File.separator+
					inputBean.getRegistrationNumber()+
					File.separator).mkdirs();

			inputBean.setDocPath(docPath + File.separator +
					inputBean.getStudentId() + File.separator +
					inputBean.getRegistrationNumber()+File.separator+
					inputBean.getRegistrationNumber() + ".pdf");
			PdfWriter.getInstance(document,
					new FileOutputStream(this.getServletContext()
							.getRealPath(File.separator) +
							inputBean.getDocPath()));

			Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 8,
					Font.NORMAL, new Color(0, 0, 0));
			Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 8,
					Font.BOLD, new Color(0, 0, 0));

			HeaderFooter header = new HeaderFooter(new Phrase(
					"Dayalbagh Educational Institute",
					FontFactory.getFont(FontFactory.HELVETICA, 10,
							Font.BOLD, new Color(0, 0, 0))), false);
			header.setAlignment(Element.ALIGN_CENTER);
			header.setBorderWidth(0);
			document.setHeader(header);

			PdfPTable programTable = new PdfPTable(new float[] { 1 });
			programTable.setWidthPercentage(100);
			addCell(dataFont, programTable,
					"Dear Candidate,\n                Your have successfully applied for the following Track and its Modules:\n");

			for (int i = 0; i < inputBean.getCourseModuleList().size(); i++) {
				addCell(dataFont, programTable,
						(i + 1) + ". " + inputBean.getCourseModuleList().get(i).getCoursesName()+ " - "+inputBean.getCourseModuleList().get(i).getModuleName());
			}

			PdfPTable studentTable = new PdfPTable(new float[] { 1, 1 });
			studentTable.setWidthPercentage(100);
			addCell(headerFont, studentTable, "Personal Details");
			addCell(dataFont, studentTable, "");
			addCell(dataFont, studentTable, "Registration Number");
			addCell(dataFont, studentTable,inputBean.getRegistrationNumber());
			addCell(dataFont, studentTable, "Name");
			addCell(dataFont, studentTable,inputBean.getFirstName() + " " + inputBean.getMiddleName() +" " + inputBean.getLastName());
			addCell(dataFont, studentTable, "Father's Name");
			addCell(dataFont, studentTable,inputBean.getFatherFirstName() + " " +
					inputBean.getFatherMiddleName() + " " +inputBean.getFatherLastName());
			addCell(dataFont, studentTable, "Mother's Name");
			addCell(dataFont, studentTable,inputBean.getMotherFirstName() + " " +
					inputBean.getMotherMiddleName() + " " +inputBean.getMotherLastName());
			addCell(dataFont, studentTable, "Primary Email Id");
			addCell(dataFont, studentTable, inputBean.getPrimaryEmail());
			addCell(dataFont, studentTable, "Secordary Email Id");
			addCell(dataFont, studentTable, inputBean.getSecondaryEmail());
			addCell(dataFont, studentTable, "Date Of Birth");
			addCell(dataFont, studentTable, inputBean.getDob());
			addCell(dataFont, studentTable, "Category");
			addCell(dataFont, studentTable, inputBean.getCategory());
			addCell(dataFont, studentTable, "Nationality");
			addCell(dataFont, studentTable, inputBean.getNationality());
			addCell(dataFont, studentTable, "Gender");
			if(inputBean.getGender().equalsIgnoreCase("M")){
				addCell(dataFont, studentTable, "Male");
			}
			else addCell(dataFont, studentTable, "Female");

			addCell(dataFont, studentTable, "Religion");
			addCell(dataFont, studentTable, inputBean.getReligion());
			addCell(dataFont, studentTable, "Guardian");
			addCell(dataFont, studentTable, inputBean.getGuardian());
			addCell(dataFont,studentTable,"Minority");
			addCell(dataFont,studentTable,inputBean.getMinorityDesc().length()>0?inputBean.getMinorityDesc():"Not Mentioned");
			addCell(dataFont,studentTable,"MaritalStatus");
			addCell(dataFont,studentTable,inputBean.getMaritalStatusDesc().length()>0?inputBean.getMaritalStatusDesc():"Not Mentioned");
			addCell(dataFont, studentTable, "Address For Correspondence");
			addCell(dataFont, studentTable,
					inputBean.getAddressLine1() + " " +
							inputBean.getAddressLine2() + " " + inputBean.getCity() + " " +
							inputBean.getState() + "-" + inputBean.getPincode());
			addCell(dataFont, studentTable, "Phone Numbers");
			addCell(dataFont, studentTable,
					inputBean.getPhoneNumber() + "  " + inputBean.getOtherPhone());

			PdfPTable academicTable = new PdfPTable(new float[] { 1, 1, 1, 1 });
			academicTable.setWidthPercentage(100);
			addCell(headerFont, academicTable, "Academic Details");
			addCell(dataFont, academicTable, "");
			addCell(dataFont, academicTable, "");
			addCell(dataFont, academicTable, "");


			for (int c = 0; c < inputBean.getAcademicDataList().size(); c++) {
				EDEIStudentBean academicBean = inputBean.getAcademicDataList().get(c);

				addCell(dataFont, academicTable,academicBean.getComponentDescription());
				addCell(dataFont, academicTable, academicBean.getMarkOrGrade().equalsIgnoreCase("Mk")?"Marks":"Grade");
				addCell(dataFont, academicTable,academicBean.getMarksObtained() + " Out Of " +academicBean.getTotalMarks());

				if((academicBean.getDegreeDescription()!=null)&&(academicBean.getDegreeDescription().length()>0)) {
					addCell(dataFont, academicTable, academicBean.getDegreeDescription());
				} else {
					addCell(dataFont, academicTable, "");
				}
			}

			PdfPTable docTable = new PdfPTable(new float[] { 1 });
			docTable.setWidthPercentage(100);
			addCell(headerFont, docTable, "Document Uploaded");

			for (int a = 0; a < (inputBean.getAttachmentList().size());a++) {
				if(inputBean.getAttachmentList().get(a).getDocId().equalsIgnoreCase("1111")){

				}
				else addCell(dataFont, docTable,(a + 1) + ". " +inputBean.getAttachmentList().get(a).getComponentDescription());
			}

			document.open();

			try {
				Image studentPhoto = Image.getInstance(java.awt.Toolkit.getDefaultToolkit().createImage(this.getServletContext().getRealPath(File.separator) +
						docPath +File.separator +inputBean.getStudentId() +File.separator +inputBean.getRegistrationNumber() +File.separator + "1111.jpg"), null);
				studentPhoto.scaleAbsolute(100, 125);
				studentPhoto.setAbsolutePosition(450f, 597f);

				document.add(studentPhoto);
			} catch (Exception ee) {
				System.out.println("image exception" + ee.getStackTrace());
				System.out.println("image exception"+ee);
			}

			document.add(programTable);
			document.add(new Phrase("\n"));
			document.add(studentTable);
			document.add(new Phrase("\n"));
			document.add(academicTable);
			document.add(new Phrase("\n"));
			document.add(docTable);

			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inputBean;
	}

	/**
	 * This method add a cell to Table
	 * @param cellFont, Font used
	 * @param table, PdfPTable in which cell is added
	 * @param value, Text string in cell
	 */
	public static final void addCell(Font cellFont, PdfPTable table,
			String value) {
		PdfPCell c1 = new PdfPCell(new Phrase(value, cellFont));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setVerticalAlignment(Element.ALIGN_TOP);
		table.addCell(c1);
	}

	/**
	 * To send Confirmation for the submittion of summary sheet through mail
	 */
	@SuppressWarnings("static-access")
	public EDEIStudentBean sendMailConfirmation(EDEIStudentBean inputBean) throws Exception {
		try {
			String message = "<h2 align='center'>Registered Successfully</h2><br>" +
					"Dear Candidate,<br>" +
					"You have successfully registered. Your registration details are as follows...<br>" +
					"<b>Registration No.</b>: " + inputBean.getRegistrationNumber() + "<br>" +
					"<b>Full Name</b>: " + inputBean.getFirstName() + " " +
					inputBean.getMiddleName() + " " + inputBean.getLastName() +
					"<br>" + "<b>Father's Name</b>: " +
					inputBean.getFatherFirstName() + " " +
					inputBean.getFatherMiddleName() + " " +
					inputBean.getFatherLastName() + "<br>" +
					"<b>Mother's Name</b>: " + inputBean.getMotherFirstName() +
					" " + inputBean.getMotherMiddleName() + " " +
					inputBean.getMotherLastName() + "<br>" +
					"<b>Date Of Birth</b>: " + inputBean.getDob() + "<br>" +
					"<b>Category</b>: " + inputBean.getCategory();
			System.out.println(message);
			new SendEmail().send(inputBean.getPrimaryEmail(),
					"Registered Successfully", message);
			System.out.println("after send mail");
		} catch (Exception mailexc) {
			logObj.logger.error(mailexc);
			System.out.println("Mail exception=" + mailexc.getStackTrace());
			throw mailexc;
		}

		return inputBean;
	}

	/**
	 * To get the module limit from the system_values table
	 * @return integer
	 */
	@SuppressWarnings("unchecked")
	public Integer getModuleCredit() {
		try{
			List<EDEIStudentBean> creditLimit=sqlMapClient.queryForList("getModuleMaxCreditLimit");
			return creditLimit.get(0).getCount();
		}
		catch (Exception e) {
			logObj.logger.error("Error inside getModuleCredit:in EDeiSummaryImpl file ::"+e.getMessage());
			System.out.println("Error inside getModuleCredit:in EDeiSummaryImpl file ::"+e.getMessage());
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	public List<EDEIStudentBean> checkstudentModules(String userEmail,String sessionStartDate, String sessionEndDate) {
		List<EDEIStudentBean> edeiList=new ArrayList<EDEIStudentBean>();
		EDEIStudentBean edeiBean=new EDEIStudentBean();
		try{
			edeiBean.setSessionStartDate(sessionStartDate);
			edeiBean.setSessionEndDate(sessionEndDate);
			edeiBean.setStudentId(userEmail);
			edeiList=sqlMapClient.queryForList("getStudentModuleEDEI",edeiBean);
			return edeiList;
		}
		catch (Exception e) {
			logObj.logger.error("Error inside checkstudentModules:in EDeiSummaryImpl file ::"+e.getMessage());
			System.out.println("Error inside checkstudentModules:in EDeiSummaryImpl file ::"+e.getMessage());
		}
		return edeiList;
	}

	@SuppressWarnings("unchecked")
	public List<EDEIStudentBean> getStudentCourses(String userEmail,
			String sessionStartDate, String sessionEndDate) {
		List<EDEIStudentBean> edeiList=new ArrayList<EDEIStudentBean>();
		EDEIStudentBean edeiBean=new EDEIStudentBean();
		try{
			edeiBean.setSessionStartDate(sessionStartDate);
			edeiBean.setSessionEndDate(sessionEndDate);
			edeiBean.setStudentId(userEmail);
			edeiList=sqlMapClient.queryForList("getStudentCourseEDEI",edeiBean);
			return edeiList;
		}
		catch (Exception e) {
			logObj.logger.error("Error inside getStudentCourses:in EDeiSummaryImpl file ::"+e.getMessage());
			System.out.println("Error inside getStudentCourses:in EDeiSummaryImpl file ::"+e.getMessage());
		}
		return edeiList;
	}

	@Override
	public String[][] getProgramNameUGData() {
		String[][] data=null;
		String path= this.getServletContext().getRealPath("progName.xml");    	  
		File fXmlFile = new File(path);
		if(fXmlFile.exists()){
			javax.xml.parsers.DocumentBuilderFactory dbFactory = javax.xml.parsers.DocumentBuilderFactory.newInstance();          	
			javax.xml.parsers.DocumentBuilder dBuilder;
			try {  				
				dBuilder = dbFactory.newDocumentBuilder();  				
				org.w3c.dom.Document doc =dBuilder.parse(fXmlFile);  				
				doc.getDocumentElement().normalize();  				
				org.w3c.dom.NodeList progList = doc.getElementsByTagName("programNameUG");  	    		
				data=new String[progList.getLength()][2];
				for(int i=0;i<progList.getLength();i++){
					org.w3c.dom.Node nNode = progList.item(i);
					org.w3c.dom.Element element=(org.w3c.dom.Element) nNode;
					data[i][0]=element.getAttribute("name");
					data[i][1]=element.getAttribute("name");  	  	    			

				}
			} catch (Exception e) {
				logObj.logger.error("Error inside getProgramNameUGData:in EDeiSummaryImpl file ::"+e.getMessage());
				System.out.println("Error inside getProgramNameUGData:in EDeiSummaryImpl file ::"+e.getMessage());
			}
		}
		else{
			data[0][0]="FileNotFound";
			return data; 
		}
		return data;
	}

	@Override
	public String[][] getProgramNamePGData() {
		String[][] data=null;
		String path= this.getServletContext().getRealPath("progName.xml");    	  
		File fXmlFile = new File(path);
		if(fXmlFile.exists()){
			javax.xml.parsers.DocumentBuilderFactory dbFactory = javax.xml.parsers.DocumentBuilderFactory.newInstance();          	
			javax.xml.parsers.DocumentBuilder dBuilder;
			try {  				
				dBuilder = dbFactory.newDocumentBuilder();  				
				org.w3c.dom.Document doc =dBuilder.parse(fXmlFile);  				
				doc.getDocumentElement().normalize();  				
				org.w3c.dom.NodeList progList = doc.getElementsByTagName("programNamePG");  	    		
				data=new String[progList.getLength()][2];
				for(int i=0;i<progList.getLength();i++){
					org.w3c.dom.Node nNode = progList.item(i);
					org.w3c.dom.Element element=(org.w3c.dom.Element) nNode;
					data[i][0]=element.getAttribute("name");
					data[i][1]=element.getAttribute("name");  	  	    			

				}
			} catch (Exception e) {
				logObj.logger.error("Error inside getProgramNamePGData:in EDeiSummaryImpl file ::"+e.getMessage());
				System.out.println("Error inside getProgramNamePGData:in EDeiSummaryImpl file ::"+e.getMessage());
			}
		}
		else{
			data[0][0]="FileNotFound";
			return data; 
		}
		return data;
	}

	@Override
	public String[][] getProgramNameOTData() {
		String[][] data=null;
		String path= this.getServletContext().getRealPath("progName.xml");    	  
		File fXmlFile = new File(path);
		if(fXmlFile.exists()){
			javax.xml.parsers.DocumentBuilderFactory dbFactory = javax.xml.parsers.DocumentBuilderFactory.newInstance();          	
			javax.xml.parsers.DocumentBuilder dBuilder;
			try {  				
				dBuilder = dbFactory.newDocumentBuilder();  				
				org.w3c.dom.Document doc =dBuilder.parse(fXmlFile);  				
				doc.getDocumentElement().normalize();  				
				org.w3c.dom.NodeList progList = doc.getElementsByTagName("programNameOT");  	    		
				data=new String[progList.getLength()][2];
				for(int i=0;i<progList.getLength();i++){
					org.w3c.dom.Node nNode = progList.item(i);
					org.w3c.dom.Element element=(org.w3c.dom.Element) nNode;
					data[i][0]=element.getAttribute("name");
					data[i][1]=element.getAttribute("name");  	  	    			
				}
			} catch (Exception e) {
				logObj.logger.error("Error inside getProgramNameOTData:in EDeiSummaryImpl file ::"+e.getMessage());
				System.out.println("Error inside getProgramNameOTData:in EDeiSummaryImpl file ::"+e.getMessage());
			}
		}
		else{
			data[0][0]="FileNotFound";
			return data; 
		}
		return data;
	}

	@SuppressWarnings("unchecked")
	public List<EDEIStudentBean> getUniversityOnlineProgram(EDEIStudentBean input) {
		List<EDEIStudentBean> list=new ArrayList<EDEIStudentBean>();

		try{

			list=sqlMapClient.queryForList("getUniversityOnlineProgramEDEI",input);

		}
		catch (Exception e) {
			logObj.logger.error("Error inside getUniversityOnlineProgram:in EDeiSummaryImpl file ::"+e.getMessage());
			System.out.println("Error inside getUniversityOnlineProgram:in EDeiSummaryImpl file ::"+e.getMessage());
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<EDEIStudentBean> getUniversityOnlineDomains(String uniNickName) {
		List<EDEIStudentBean> list=new ArrayList<EDEIStudentBean>();
		EDEIStudentBean obj=new EDEIStudentBean();
		try{
			obj.setUniversityNickName(uniNickName);
			list=sqlMapClient.queryForList("getUniversityOnlineDomainsEDEI",obj);

		}
		catch (Exception e) {
			logObj.logger.error("Error inside getUniversityOnlineDomains:in EDeiSummaryImpl file ::"+e.getMessage());
			System.out.println("Error inside getUniversityOnlineDomains:in EDeiSummaryImpl file ::"+e.getMessage());
		}
		return list;
	}


	@SuppressWarnings("unchecked")
	public List<EDEIStudentBean> getUniversityOnlineProgramModule(EDEIStudentBean input) {
		List<EDEIStudentBean> list=new ArrayList<EDEIStudentBean>();
		try{
			list=sqlMapClient.queryForList("getUniversityOnlineProgramModuleEDEI",input);
		}
		catch (Exception e) {
			logObj.logger.error("Error inside getUniversityOnlineProgramModule:in EDeiSummaryImpl file ::"+e.getMessage());
			System.out.println("Error inside getUniversityOnlineProgramModule:in EDeiSummaryImpl file ::"+e.getMessage());
		}
		return list;
	}

	@Override
	public List<EDEIStudentBean> getPersonalDetails(EDEIStudentBean inputBean) {
		List<EDEIStudentBean> list=new ArrayList<EDEIStudentBean>();
		try{
			list=sqlMapClient.queryForList("getPersonalDetailsForEDEISummarySheet",inputBean);
		}
		catch (Exception e) {
			logObj.logger.error("Error inside getPersonalDetails:in EDeiSummaryImpl file ::"+e.getMessage());
			System.out.println("Error inside getUniversityOnlineProgramModule:in EDeiSummaryImpl file ::"+e.getMessage());
		}
		return list;
	}

	@Override
	public EDEIStudentBean insertEDEINEWSummarySheetDetailsForExistingProgram(
			EDEIStudentBean inputBean) throws Exception {			
		try {
			inputBean.setDob(new SimpleDateFormat("yyyy-MM-dd").format(new Date(inputBean.getDob())));
			sqlMapClient.startTransaction();
			inputBean.setUgPg("APPNUM");
			EDEIStudentBean sysValueObj = (EDEIStudentBean) sqlMapClient.queryForObject("getSystemValuesEDEI",inputBean);
			Integer seqNum = 1;
			String appNumber = null;
			if (sysValueObj != null) {
				seqNum=Integer.parseInt(sysValueObj.getSequence());
				seqNum = seqNum + 1;
				inputBean.setSequence(String.valueOf(seqNum));
				sqlMapClient.update("updateSystemValuesEDEI", inputBean);
				appNumber=inputBean.getUniversityNickName() + padZero(seqNum, 6);
				inputBean.setRegistrationNumber(appNumber);
			}
			for(int e=0;e<inputBean.getCourseModuleList().size();e++){
				EDEIStudentBean courseBean = inputBean.getCourseModuleList().get(e);
				courseBean.setRegistrationNumber(inputBean.getRegistrationNumber());
				courseBean.setSessionStartDate(inputBean.getSessionStartDate());
				courseBean.setSessionEndDate(inputBean.getSessionEndDate());
				courseBean.setEnrollmentNumber(inputBean.getEnrollmentNumber());
				courseBean.setRollNumber(inputBean.getRollNumber());
				courseBean.setUserId(inputBean.getUserId());
				courseBean.setProgramId(inputBean.getProgramId());
				courseBean.setStudentStatus(inputBean.getStudentStatus());
				sqlMapClient.insert("setTempStudentModuleEDEIForExistingProgram",courseBean);
			}

			inputBean.setCosCode(inputBean.getCategoryCode()+"XX");
			sqlMapClient.insert("setStudentRegistrationEDEI", inputBean);

			sqlMapClient.commitTransaction();
			sqlMapClient.endTransaction();
			inputBean.setFileSeparator(File.separator);

		} catch (Exception e) {
			sqlMapClient.endTransaction();
			logObj.logger.error("Error inside insertEDEINEWSummarySheetDetailsForExistingProgram:in EDeiSummaryImpl file ::"+e.getMessage());
			System.out.println("Error inside insertEDEINEWSummarySheetDetailsForExistingProgram:in EDeiSummaryImpl file ::"+e.getMessage());
			throw e;
		}
		return inputBean;
	}
	
	public EDEIStudentBean generatePDFForExistingProgram(EDEIStudentBean inputBean, String docPath) {
		try{
			Document document = new Document(PageSize.A4);
			new File(this.getServletContext().getRealPath(File.separator) +
					docPath + File.separator + inputBean.getStudentId() +File.separator+
					inputBean.getRegistrationNumber()+
					File.separator).mkdirs();

			inputBean.setDocPath(docPath + File.separator +
					inputBean.getStudentId() + File.separator +
					inputBean.getRegistrationNumber()+File.separator+
					inputBean.getRegistrationNumber() + ".pdf");
			PdfWriter.getInstance(document,
					new FileOutputStream(this.getServletContext()
							.getRealPath(File.separator) +
							inputBean.getDocPath()));

			Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 8,
					Font.NORMAL, new Color(0, 0, 0));
			Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 8,
					Font.BOLD, new Color(0, 0, 0));

			HeaderFooter header = new HeaderFooter(new Phrase(
					"Dayalbagh Educational Institute",
					FontFactory.getFont(FontFactory.HELVETICA, 10,
							Font.BOLD, new Color(0, 0, 0))), false);
			header.setAlignment(Element.ALIGN_CENTER);
			header.setBorderWidth(0);
			document.setHeader(header);

			PdfPTable programTable = new PdfPTable(new float[] { 1 });
			programTable.setWidthPercentage(100);
			addCell(dataFont, programTable,
					"Dear Candidate,\n                Your have successfully applied for the following Track and its Modules:\n");

			for (int i = 0; i < inputBean.getCourseModuleList().size(); i++) {
				addCell(dataFont, programTable,
						(i + 1) + ". " + inputBean.getCourseModuleList().get(i).getCoursesName()+ " - "+inputBean.getCourseModuleList().get(i).getModuleName());
			}

			PdfPTable studentTable = new PdfPTable(new float[] { 1, 1 });
			studentTable.setWidthPercentage(100);
			addCell(headerFont, studentTable, "Personal Details");
			addCell(dataFont, studentTable, "");
			addCell(dataFont, studentTable, "Registration Number");
			addCell(dataFont, studentTable,inputBean.getRegistrationNumber());
			addCell(dataFont, studentTable, "Enrollment Number");
			addCell(dataFont, studentTable,inputBean.getEnrollmentNumber());
			if(inputBean.getStudentStatus().equals("OLDEXP")){
				addCell(dataFont, studentTable, "Roll Number");
				addCell(dataFont, studentTable,inputBean.getRollNumber());
			}
			addCell(dataFont, studentTable, "Name");
			addCell(dataFont, studentTable,inputBean.getFirstName() + " " + inputBean.getMiddleName() +" " + inputBean.getLastName());
			addCell(dataFont, studentTable, "Father's Name");
			addCell(dataFont, studentTable,inputBean.getFatherFirstName() + " " +
					inputBean.getFatherMiddleName() + " " +inputBean.getFatherLastName());
			addCell(dataFont, studentTable, "Mother's Name");
			addCell(dataFont, studentTable,inputBean.getMotherFirstName() + " " +
					inputBean.getMotherMiddleName() + " " +inputBean.getMotherLastName());
			addCell(dataFont, studentTable, "Primary Email Id");
			addCell(dataFont, studentTable, inputBean.getPrimaryEmail());
			addCell(dataFont, studentTable, "Secordary Email Id");
			addCell(dataFont, studentTable, inputBean.getSecondaryEmail());
			addCell(dataFont, studentTable, "Date Of Birth");
			addCell(dataFont, studentTable, inputBean.getDob());
			addCell(dataFont, studentTable, "Category");
			addCell(dataFont, studentTable, inputBean.getCategory());
			addCell(dataFont, studentTable, "Nationality");
			addCell(dataFont, studentTable, inputBean.getNationality());
			addCell(dataFont, studentTable, "Gender");
			if(inputBean.getGender().equalsIgnoreCase("M")){
				addCell(dataFont, studentTable, "Male");
			}
			else addCell(dataFont, studentTable, "Female");

			addCell(dataFont, studentTable, "Religion");
			addCell(dataFont, studentTable, inputBean.getReligion());
			addCell(dataFont, studentTable, "Guardian");
			addCell(dataFont, studentTable, inputBean.getGuardian());
			addCell(dataFont,studentTable,"Minority");
			addCell(dataFont,studentTable,inputBean.getMinorityDesc().length()>0?inputBean.getMinorityDesc():"Not Mentioned");
			addCell(dataFont,studentTable,"MaritalStatus");
			addCell(dataFont,studentTable,inputBean.getMaritalStatusDesc().length()>0?inputBean.getMaritalStatusDesc():"Not Mentioned");
			addCell(dataFont, studentTable, "Address For Correspondence");
			addCell(dataFont, studentTable,
					inputBean.getAddressLine1() + " " +
							inputBean.getAddressLine2() + " " + inputBean.getCity() + " " +
							inputBean.getState() + "-" + inputBean.getPincode());
			addCell(dataFont, studentTable, "Phone Numbers");
			addCell(dataFont, studentTable,
					inputBean.getPhoneNumber() + "  " + inputBean.getOtherPhone());
	
			PdfPTable academicTable = null;
			PdfPTable docTable = null;
			if(inputBean.getStudentStatus().equals("OLDNEW")){
				academicTable = new PdfPTable(new float[] { 1, 1, 1, 1 });
				academicTable.setWidthPercentage(100);
				addCell(headerFont, academicTable, "Academic Details");
				addCell(dataFont, academicTable, "");
				addCell(dataFont, academicTable, "");
				addCell(dataFont, academicTable, "");


				for (int c = 0; c < inputBean.getAcademicDataList().size(); c++) {
					EDEIStudentBean academicBean = inputBean.getAcademicDataList().get(c);

					addCell(dataFont, academicTable,academicBean.getComponentDescription());
					addCell(dataFont, academicTable, academicBean.getMarkOrGrade().equalsIgnoreCase("Mk")?"Marks":"Grade");
					addCell(dataFont, academicTable,academicBean.getMarksObtained() + " Out Of " +academicBean.getTotalMarks());

					if((academicBean.getDegreeDescription()!=null)&&(academicBean.getDegreeDescription().length()>0)) {
						addCell(dataFont, academicTable, academicBean.getDegreeDescription());
					} else {
						addCell(dataFont, academicTable, "");
					}
				}

				docTable = new PdfPTable(new float[] { 1 });
				docTable.setWidthPercentage(100);
				addCell(headerFont, docTable, "Document Uploaded");

				for (int a = 0; a < (inputBean.getAttachmentList().size());a++) {
					if(inputBean.getAttachmentList().get(a).getDocId().equalsIgnoreCase("1111")){

					}
					else addCell(dataFont, docTable,(a + 1) + ". " +inputBean.getAttachmentList().get(a).getComponentDescription());
				}

			}
			document.open();

			document.add(programTable);
			document.add(new Phrase("\n"));
			document.add(studentTable);			
			if(inputBean.getStudentStatus().equals("OLDNEW")){
				document.add(new Phrase("\n"));
				document.add(academicTable);
				document.add(new Phrase("\n"));
				document.add(docTable);
			}
			
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inputBean;
	}

	@Override
	public EDEIStudentBean insertEDEINEWSummarySheetDetailsForNEWProgram(
			EDEIStudentBean inputBean) throws Exception {		
		try {
			inputBean.setDob(new SimpleDateFormat("yyyy-MM-dd").format(new Date(inputBean.getDob())));
			sqlMapClient.startTransaction();
			inputBean.setUgPg("APPNUM");
			EDEIStudentBean sysValueObj = (EDEIStudentBean) sqlMapClient.queryForObject("getSystemValuesEDEI",inputBean);
			Integer seqNum = 1;
			String appNumber = null;
			if (sysValueObj != null) {
				seqNum=Integer.parseInt(sysValueObj.getSequence());
				seqNum = seqNum + 1;
				inputBean.setSequence(String.valueOf(seqNum));
				sqlMapClient.update("updateSystemValuesEDEI", inputBean);
				appNumber=inputBean.getUniversityNickName() + padZero(seqNum, 6);
				inputBean.setRegistrationNumber(appNumber);
			}
				
			for (int d = 0; d < inputBean.getAttachmentList().size(); d++) {
				EDEIStudentBean documentBean = inputBean.getAttachmentList().get(d);
				documentBean.setRegistrationNumber(inputBean.getRegistrationNumber());
				documentBean.setUserId(inputBean.getUserId());
				String fileType = documentBean.getDocName().substring(documentBean.getDocName().lastIndexOf(".") +1);
				documentBean.setDocPath(documentBean.getDocPath() +File.separator + inputBean.getRegistrationNumber() + File.separator +inputBean.getStudentId()+File.separator+documentBean.getDocId() + "." + fileType);
				sqlMapClient.insert("setStudentDocsEDEI", documentBean);
			}

			for (int c = 0; c < inputBean.getAcademicDataList().size(); c++) {
				EDEIStudentBean academicBean = inputBean.getAcademicDataList().get(c);
				academicBean.setUniversityId(inputBean.getUniversityId());
				academicBean.setSessionStartDate(inputBean.getSessionStartDate());
				academicBean.setSessionEndDate(inputBean.getSessionEndDate());
				academicBean.setUserId(inputBean.getUserId());
				academicBean.setRegistrationNumber(inputBean.getRegistrationNumber());
				academicBean.setProgramId(inputBean.getProgramId());
				academicBean.setEntityId(inputBean.getEntityId());
				sqlMapClient.insert("setStudentCallListEDEI", academicBean);
			}

			for(int e=0;e<inputBean.getCourseModuleList().size();e++){
				EDEIStudentBean courseBean = inputBean.getCourseModuleList().get(e);
				courseBean.setRegistrationNumber(inputBean.getRegistrationNumber());
				courseBean.setSessionStartDate(inputBean.getSessionStartDate());
				courseBean.setSessionEndDate(inputBean.getSessionEndDate());
				courseBean.setUserId(inputBean.getUserId());
				courseBean.setProgramId(inputBean.getProgramId());
				courseBean.setEnrollmentNumber(inputBean.getEnrollmentNumber());
				courseBean.setStudentStatus(inputBean.getStudentStatus());
				sqlMapClient.insert("setTempStudentModuleEDEIForExistingProgram",courseBean);
			}

			inputBean.setCosCode(inputBean.getCategoryCode()+"XX");
			sqlMapClient.insert("setStudentRegistrationEDEI", inputBean);

			sqlMapClient.commitTransaction();
			sqlMapClient.endTransaction();
			inputBean.setFileSeparator(File.separator);


		} catch (Exception e) {
			sqlMapClient.endTransaction();
			logObj.logger.error("Error inside insertEDEINEWSummarySheetDetails:in EDeiSummaryImpl file ::"+e.getMessage());
			System.out.println("Error inside insertEDEINEWSummarySheetDetails:in EDeiSummaryImpl file ::"+e.getMessage());
			throw e;
		}
		return inputBean;
	}
}
