package in.ac.dei.edrp.server.InternalSummarySheet;

/*
 * @(#) SummarySheetImpl.java
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

import in.ac.dei.edrp.client.InternalSummarySheet.InternalSummaryService;
import in.ac.dei.edrp.client.RPCFiles.SummarySheetService;
import in.ac.dei.edrp.client.applicantAccount.ApplicantAccountBean;
import in.ac.dei.edrp.client.summarysheet.SummarySheetBean;
import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;
import in.ac.dei.edrp.server.summarysheet.SendEmail;

import java.awt.Color;

import java.io.File;
import java.io.FileOutputStream;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @version 1.0 8 MAY 2012
 * @author UPASANA KULSHRESTHA
 */

@SuppressWarnings("serial")
public class InternalSummaryImpl extends RemoteServiceServlet
    implements InternalSummaryService {
    SqlMapClient sqlMapClient = SqlMapManager.getSqlMapClient();
    Log4JInitServlet logObj = new Log4JInitServlet();

    @SuppressWarnings("unchecked")
    public List<SummarySheetBean> getProgramComponent(
        SummarySheetBean inputBean) {
        List<SummarySheetBean> componentList = new ArrayList<SummarySheetBean>();

        try {
            componentList = sqlMapClient.queryForList("getProgramComponent",inputBean);
        } catch (Exception ex) {
            logObj.logger.error(ex.getMessage());
        }

        return componentList;
    }

    @SuppressWarnings("unchecked")
    public List<SummarySheetBean> getGroupWisePaperCode(
        SummarySheetBean inputBean) {
        List<SummarySheetBean> paperList = new ArrayList<SummarySheetBean>();

        try {
            paperList = sqlMapClient.queryForList("getGroupWisePaperCode",inputBean);
        } catch (Exception ex) {
            logObj.logger.error(ex.getMessage());
        }

        return paperList;
    }

    @SuppressWarnings("unchecked")
    public List<SummarySheetBean> getProgramDocumentList(
        SummarySheetBean inputBean) {
        List<SummarySheetBean> docList = new ArrayList<SummarySheetBean>();

        try {
            docList = sqlMapClient.queryForList("getProgramDocumentList",inputBean);
        } catch (Exception ex) {
            logObj.logger.error(ex.getMessage());
        }

        return docList;
    }

    @SuppressWarnings("unchecked")
    public List<SummarySheetBean> getFormProgramList(SummarySheetBean inputBean) {
        System.out.println();

        List<SummarySheetBean> programList = new ArrayList<SummarySheetBean>();

        try {
            programList = sqlMapClient.queryForList("getFormProgramListInternal",
                    inputBean);
        } catch (Exception ex) {
            logObj.logger.error(ex.getMessage());
        }

        return programList;
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<SummarySheetBean> getProgramFirstDegreeList(SummarySheetBean inputBean) {
		List<SummarySheetBean> degreeList = new ArrayList<SummarySheetBean>();

        try {
        	degreeList = sqlMapClient.queryForList("getProgramFirstDegreeList",
        			inputBean);
        } catch (Exception ex) {
            logObj.logger.error(ex.getMessage());
        }

        return degreeList;
	}
	
	@SuppressWarnings("unchecked")
	public List<SummarySheetBean> getCosCodeDescription(SummarySheetBean inputBean) {
		List<SummarySheetBean> cosDescriptionList = new ArrayList<SummarySheetBean>();

        try {
        	cosDescriptionList = sqlMapClient.queryForList("getCosCodeDescription",inputBean);
        } catch (Exception ex) {
            logObj.logger.error(ex.getMessage());
        }

        return cosDescriptionList;
	}

    @SuppressWarnings({"unchecked",
        "deprecation"
    })
    @Override
    public SummarySheetBean insertSummarySheetDetails(
        SummarySheetBean inputBean) throws Exception {
    	
    	//System.out.println("hello im in insert method");
        String studentId = "";
        List<String> appNumber = new ArrayList<String>();
        inputBean.setDob(new SimpleDateFormat("yyyy-MM-dd").format(
                new Date(inputBean.getDob())));
        if(inputBean.getDdDate().length()>0){
        	inputBean.setDdDate(new SimpleDateFormat("yyyy-MM-dd").format(
                new Date(inputBean.getDdDate())));
        }

        try {
            sqlMapClient.startTransaction();

            System.out.println("transaction start");
            inputBean.setUgPg("APPNUM");

            SummarySheetBean sysValueObj = (SummarySheetBean) sqlMapClient.queryForObject("getSystemValues",
                    inputBean);

            Integer seqNum = 1;

            if (sysValueObj != null) {
            	seqNum=Integer.parseInt(sysValueObj.getSequenceNumber());
	            for(int i=0;i<inputBean.getProgramList().size();i++){
	                seqNum = seqNum + 1;
	                inputBean.setSequenceNumber(String.valueOf(seqNum));
	                sqlMapClient.update("updateSystemValues", inputBean);
	                appNumber.add(inputBean.getUniversityNickName() + padZero(seqNum, 6));
	                //inputBean.setRegistrationNumber(appNumber);
	                inputBean.setRegistrationNumList(appNumber);
	                
	            }
            }
            
            //registration number is university nick name+6 digits sequence number
            /*appNumber =inputBean.getUniversityNickName() + padZero(seqNum, 6);
            System.out.println("1");
            inputBean.setRegistrationNumber(appNumber);*/

            SummarySheetBean studentIdObj = (SummarySheetBean) sqlMapClient.queryForObject("getStudentId",
                    inputBean);

            if (studentIdObj != null) {
                studentId = studentIdObj.getStudentId();
                inputBean.setStudentId(studentId);
                sqlMapClient.update("updateEntityStudent",inputBean);		//only update guardian name and other phone,minority,maritalStatus
                sqlMapClient.update("updateStudentAddress",inputBean);
            } else {
                inputBean.setUgPg("STUDID");
                sysValueObj = (SummarySheetBean) sqlMapClient.queryForObject("getSystemValues",
                        inputBean);
                seqNum = 1;

                if (sysValueObj != null) {
                    seqNum = Integer.parseInt(sysValueObj.getSequenceNumber()) +
                        1;
                    inputBean.setSequenceNumber(String.valueOf(seqNum));
                    sqlMapClient.update("updateSystemValues", inputBean);
                }

                System.out.println("date is "+new SimpleDateFormat("yyyy").format(new Date().getTime()));
                String year=new SimpleDateFormat("yyyy").format(new Date().getTime());
                studentId = "S" + inputBean.getEntityId()+ year+ padZero(seqNum, 5);
                inputBean.setStudentId(studentId);
                sqlMapClient.insert("setEntityStudent", inputBean);
                sqlMapClient.insert("setStudentAddress", inputBean);
            }
            System.out.println("2");
            
            if((inputBean.getDdAmount().length()>0)&&(inputBean.getDdDate().length()>0)&&(inputBean.getDdNo().length()>0)&&(inputBean.getBankName().length()>0)){
				for(int i=0;i<inputBean.getRegistrationNumList().size();i++){
        		inputBean.setRegistrationNumber(inputBean.getRegistrationNumList().get(i));
        		sqlMapClient.insert("setStudentFee", inputBean);
        		}
            }
            for(int d=0;d<inputBean.getDegreeList().size();d++){
            	SummarySheetBean degree=inputBean.getDegreeList().get(d);
            	degree.setRegistrationNumber(inputBean.getRegistrationNumList().get(d));
            	degree.setUserId(inputBean.getUserId());
            	degree.setSessionStartDate(inputBean.getSessionStartDate());
            	degree.setSessionEndDate(inputBean.getSessionEndDate());
            	//System.out.println("prgm "+degree.getProgramId()+" component id "+degree.getComponentId());
            	sqlMapClient.insert("setStudentFirstDegree", degree);
            	System.out.println("4");
            }
            
            for (int i = 0; i < inputBean.getPaperGroupList().size(); i++) {
            	SummarySheetBean paperBean = inputBean.getPaperGroupList().get(i);
                //paperBean.setRegistrationNumber(appNumber);
                paperBean.setSessionStartDate(inputBean.getSessionStartDate());
                paperBean.setSessionEndDate(inputBean.getSessionEndDate());
                paperBean.setUserId(inputBean.getUserId());
                for(int j=0;j<inputBean.getProgramList().size();j++){
                	if(paperBean.getProgramId().equalsIgnoreCase(inputBean.getProgramList().get(j))){
                		paperBean.setRegistrationNumber(inputBean.getRegistrationNumList().get(j));
                		sqlMapClient.insert("setStudentPaper", paperBean);
                	}
                }
                //sqlMapClient.insert("setStudentPaper", paperBean);
            }

		for(int i=0;i<inputBean.getRegistrationNumList().size();i++){
            for (int d = 0; d < inputBean.getAttachmentList().size(); d++) {
                SummarySheetBean documentBean = inputBean.getAttachmentList().get(d);
                documentBean.setRegistrationNumber(inputBean.getRegistrationNumList().get(i));
                documentBean.setUserId(inputBean.getUserId());

                String fileType = documentBean.getDocName().substring(documentBean.getDocName().lastIndexOf(".") +1);
                documentBean.setDocPath(documentBean.getDocPath() +
                    File.separator + appNumber + File.separator +
                    documentBean.getDocId() + "." + fileType);

                if(fileType.length()>0){
                	sqlMapClient.insert("setStudentDocs", documentBean);
                	System.out.println("6");
                }
            }
		}

            for (int c = 0; c < inputBean.getAcademicList().size(); c++) {
                SummarySheetBean academicBean = inputBean.getAcademicList().get(c);
                //academicBean.setRegistrationNumber(appNumber);
                academicBean.setUniversityId(inputBean.getUniversityId());

                academicBean.setSessionStartDate(inputBean.getSessionStartDate());
                academicBean.setSessionEndDate(inputBean.getSessionEndDate());
                System.out.println(inputBean.getEntityIdList());

                academicBean.setUserId(inputBean.getUserId());
                
                for(int i=0;i<inputBean.getProgramList().size();i++){
                	
                	academicBean.setProgramId(inputBean.getProgramList().get(i));
                	academicBean.setEntityId(inputBean.getEntityIdList().get(i));
                	academicBean.setRegistrationNumber(inputBean.getRegistrationNumList().get(i));
                    sqlMapClient.insert("setStudentCallList", academicBean);
                    System.out.println("7");

                    if((academicBean.getStaffWeightage()!=null)&&(academicBean.getStaffWeightage())){
                    	sqlMapClient.insert("setStudentSpecialWeightage",academicBean);
                    	System.out.println("8");
                    }
                    else if ((academicBean.getWeightage() != null) && (academicBean.getWeightage())) {
                        sqlMapClient.insert("setStudentSpecialWeightage",academicBean);
                        System.out.println("9");
                    }
                }
            }

            for (int p = 0; p < inputBean.getCosCodeList().size(); p++) {

            	SummarySheetBean subjectCodeBean=inputBean.getCosCodeList().get(p);
            	inputBean.setProgramId(subjectCodeBean.getProgramId());
            	inputBean.setCosCode(subjectCodeBean.getCosCode());
            	inputBean.setEntityId(inputBean.getEntityIdList().get(p));
            	inputBean.setRegistrationNumber(inputBean.getRegistrationNumList().get(p));
                List<SummarySheetBean> cosValues = sqlMapClient.queryForList("getCosCode",inputBean);
                
                if (cosValues.size() > 0) {
                    if (cosValues.size() == 1) {
                        inputBean.setFormNumber(cosValues.get(0).getFormNumber());
                        System.out.println("cos value "+cosValues.get(0).getFormNumber());
                        System.out.println("10");
                    } else {
                        for (int c = 0; c < cosValues.size(); c++) {
                            if (cosValues.get(c).getFormNumber().charAt(2) == inputBean.getGender().charAt(0)) {
                                inputBean.setFormNumber(cosValues.get(c).getFormNumber());
                            }
                        }
                    }
                }
                else {
                	inputBean.setFormNumber(inputBean.getCategory()+"XX");
                }
                	sqlMapClient.insert("setInternalStudentRegistration", inputBean);
                	
            }
            
            for(int l=0;l<inputBean.getCenterCodeList().size();l++){
            	SummarySheetBean centerCodeBean=inputBean.getCenterCodeList().get(l);
            	inputBean.setProgramId(centerCodeBean.getProgramId());
            	inputBean.setCenterCode(centerCodeBean.getCenterCode());
            	inputBean.setRegistrationNumber(inputBean.getRegistrationNumList().get(l));
            	sqlMapClient.update("updateStudentRegistrationforCenter", inputBean);
            	
            }
            
            
            /*if(!(inputBean.getUserEmailId()==null)){
            	try{
	            	SummarySheetBean registrationDetail=new SummarySheetBean();
	            	
	            	for(int i=0;i<inputBean.getProgramList().size();i++){
	            		registrationDetail.setProgramId(inputBean.getProgramList().get(i));
	            		registrationDetail.setUserEmailId(inputBean.getUserEmailId());
	            		registrationDetail.setRegistrationNumber(inputBean.getRegistrationNumber());
	            		
	            		sqlMapClient.insert("setApplicantProgramRegistration",registrationDetail);
	            	}
	            }
            	catch(Exception ex){
            		System.out.println(" in setApplicantProgramRegistration "+ex.getMessage());
            	}
            }*/
            
            
            //generateApplicationPdf(inputBean, docPath);
            sqlMapClient.commitTransaction();

            sqlMapClient.endTransaction();

            /*//try {
                String message = "<h2 align='center'>Registered Successfully</h2><br>" +
                    "Dear Candidate,<br>" +
                    "You have successfully registered. Your registration details are as follows...<br>" +
                    "<b>Registration No.</b>: " + appNumber + "<br>" +
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
                System.out.println("Mail exception=" + mailexc.getStackTrace());
                
            }*/

            //inputBean.setRegistrationNumber(File.separator + appNumber);
            inputBean.setFileSeparator(File.separator);
            return inputBean;
        } catch (Exception e) {
            sqlMapClient.endTransaction();
            logObj.logger.error(e.getMessage());
            throw e;
        }
    }

    private String padZero(Integer number, int length) {
        String output = String.valueOf(number);

        while (output.length() < length) {
            output = "0" + output;
        }

        return output;
    }

    @Override
    public SummarySheetBean generatePDF(SummarySheetBean inputBean,
        String docPath) {
        
        try {
        	for(int q=0;q<inputBean.getRegistrationNumList().size();q++){
        		Document document = new Document(PageSize.A4);
                System.out.println(docPath);
        		
	            new File(this.getServletContext().getRealPath(File.separator) +
	                docPath + File.separator + inputBean.getStudentId() +
	                File.separator).mkdirs();
	            
	            inputBean.setDocPath(docPath + File.separator +
	                inputBean.getStudentId() + File.separator +
	                inputBean.getRegistrationNumList().get(q) + ".pdf");
	            PdfWriter.getInstance(document,
	                new FileOutputStream(this.getServletContext()
	                                         .getRealPath(File.separator) +
	                    inputBean.getDocPath()));
	
	            Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 8,
	                    Font.NORMAL, new Color(0, 0, 0));
	            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 8,
	                    Font.BOLD, new Color(0, 0, 0));
	
	            HeaderFooter header = new HeaderFooter(new Phrase(
	                        inputBean.getEntityName(),
	                        FontFactory.getFont(FontFactory.HELVETICA, 10,
	                            Font.BOLD, new Color(0, 0, 0))), false);
	            header.setAlignment(Element.ALIGN_CENTER);
	            header.setBorderWidth(0);
	            document.setHeader(header);
	
	            PdfPTable programTable = new PdfPTable(new float[] { 1 });
	            programTable.setWidthPercentage(100);
	            addCell(dataFont, programTable,
	                "Dear Candidate,\n                Your have successfully applied for the following programs:\n");
	
	            for (int i = 0; i < inputBean.getProgramNameList().size(); i++) {
	                addCell(dataFont, programTable,
	                    (i + 1) + ". " + inputBean.getProgramNameList().get(i));
	            }
	
	            PdfPTable studentTable = new PdfPTable(new float[] { 1, 1 });
	            studentTable.setWidthPercentage(100);
	            addCell(headerFont, studentTable, "Personal Details");
	            addCell(dataFont, studentTable, "");
	            /*addCell(dataFont, studentTable, "Registration Number");
	            System.out.println(inputBean.getRegistrationNumber());
	            addCell(dataFont, studentTable,
	                inputBean.getRegistrationNumber());*/
	            addCell(dataFont, studentTable, "Name");
	            addCell(dataFont, studentTable,
	                inputBean.getFirstName() + " " + inputBean.getMiddleName() +
	                " " + inputBean.getLastName());
	            addCell(dataFont, studentTable, "Father's Name");
	            addCell(dataFont, studentTable,
	                inputBean.getFatherFirstName() + " " +
	                inputBean.getFatherMiddleName() + " " +
	                inputBean.getFatherLastName());
	            addCell(dataFont, studentTable, "Mother's Name");
	            addCell(dataFont, studentTable,
	                inputBean.getMotherFirstName() + " " +
	                inputBean.getMotherMiddleName() + " " +
	                inputBean.getMotherLastName());
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
	
	            PdfPTable academicTable = new PdfPTable(new float[] { 1, 1, 2, 1, 1 });
	            academicTable.setWidthPercentage(100);
	            addCell(headerFont, academicTable, "Academic Details");
	            addCell(dataFont, academicTable, "");
	            addCell(dataFont, academicTable, "");
	            addCell(dataFont, academicTable, "");
	            addCell(dataFont, academicTable, "");
	
	            for (int c = 0; c < inputBean.getAcademicList().size(); c++) {
	                SummarySheetBean academicBean = inputBean.getAcademicList()
	                                                         .get(c);
	
	                addCell(dataFont, academicTable,
	                    academicBean.getComponentDescription());
	
	                if (academicBean.getComponentType().equalsIgnoreCase("M")) {
	                    addCell(dataFont, academicTable, "Marks");
	                    addCell(dataFont, academicTable,
	                        academicBean.getMarksObtained() + " Out Of " +
	                        academicBean.getTotalMarks());
	                } else if (academicBean.getComponentType().equalsIgnoreCase("P")) {
	                    addCell(dataFont, academicTable, "Percentage");
	                    addCell(dataFont, academicTable,
	                        academicBean.getPercentage() + "%");
	                } else {
	                    addCell(dataFont, academicTable, "Score");
	                    addCell(dataFont, academicTable, academicBean.getScore());
	                }
	
	                if (academicBean.getBoardFlag().equalsIgnoreCase("y")) {
	                    addCell(dataFont, academicTable,
	                        "From " + academicBean.getBankName());
	                } else {
	                    addCell(dataFont, academicTable, "");
	                }
	
	                if (academicBean.getSpecialWeightageFlag().equalsIgnoreCase("y") &&
	                        academicBean.getWeightage()) {
	                    addCell(dataFont, academicTable, "Internal Candidate");
	                } else {
	                    addCell(dataFont, academicTable, "");
	                }
	            }
	
	            PdfPTable entranceTable = new PdfPTable(new float[] {1, 1, 1 });
	            entranceTable.setWidthPercentage(100);
	            addCell(headerFont, entranceTable, "Entrance Test Option");
	            addCell(dataFont, entranceTable, "");
	            addCell(dataFont, entranceTable, "");
	
	            for (int p = 0; p < inputBean.getPaperGroupList().size(); p++) {
	            	addCell(dataFont, entranceTable,"For "   +inputBean.getPaperGroupList().get(p).getProgramName());
	                addCell(dataFont, entranceTable,"Group " +inputBean.getPaperGroupList().get(p).getGrouping());
	                addCell(dataFont, entranceTable,inputBean.getPaperGroupList().get(p).getPaperDescription());
	            }
	            
	            PdfPTable subjectCodeTable=new PdfPTable(new float[]{1,1});
	            subjectCodeTable.setWidthPercentage(100);
	            addCell(headerFont, subjectCodeTable, "Subject Code");
	            addCell(dataFont,subjectCodeTable,"");
	            for (int p = 0; p < inputBean.getCosCodeList().size(); p++) {
	                addCell(dataFont, subjectCodeTable,inputBean.getProgramNameList().get(p));
	                addCell(dataFont, subjectCodeTable,inputBean.getCosCodeList().get(p).getCosDescription());
	            }
	            
	            PdfPTable firstDegreeTable=new PdfPTable(new float[]{1,1});
	            firstDegreeTable.setWidthPercentage(100);
	            addCell(headerFont, firstDegreeTable, "Qualification");
	            addCell(dataFont, firstDegreeTable, "");
	            for(int i=0;i<inputBean.getDegreeList().size();i++){
	            	addCell(dataFont, firstDegreeTable,inputBean.getDegreeList().get(i).getProgramName());
	                addCell(dataFont, firstDegreeTable,inputBean.getDegreeList().get(i).getComponentDescription());
	            }
	            
	            PdfPTable examCenterTable=new PdfPTable(new float[]{1,1});
	            examCenterTable.setWidthPercentage(100);
	            addCell(headerFont, examCenterTable, "Examination Center");
	            addCell(dataFont,examCenterTable,"");
	            for (int p = 0; p < inputBean.getCosCodeList().size(); p++) {
	                addCell(dataFont, examCenterTable,inputBean.getProgramNameList().get(p));
	                addCell(dataFont, examCenterTable,inputBean.getCenterCodeList().get(p).getCenterDescription());
	            }
	            
	            
	            PdfPTable RegistrationNumberTable=new PdfPTable(new float[]{1,1});
	            RegistrationNumberTable.setWidthPercentage(100);
	            addCell(headerFont, RegistrationNumberTable, "Registration Number");
	            addCell(dataFont,RegistrationNumberTable,"");
	            for (int p = 0; p < inputBean.getCosCodeList().size(); p++) {
	                addCell(dataFont, RegistrationNumberTable,"For "+inputBean.getProgramNameList().get(p));
	                addCell(dataFont, RegistrationNumberTable,inputBean.getRegistrationNumList().get(p));
	            }
	
	            PdfPTable feeTable = new PdfPTable(new float[] { 2, 2, 2, 3 });
	            feeTable.setWidthPercentage(100);
	            addCell(headerFont, feeTable, "Fee Details Details");
	            addCell(dataFont, feeTable, "");
	            addCell(dataFont, feeTable, "");
	            addCell(dataFont, feeTable, "");
		        if((inputBean.getDdAmount().length()>0)&&(inputBean.getDdDate().length()>0)&&(inputBean.getDdNo().length()>0)&&(inputBean.getBankName().length()>0)){     
		            addCell(dataFont, feeTable, "DD Number");
		            addCell(dataFont, feeTable, inputBean.getDdNo());
		            addCell(dataFont, feeTable, "DD Date");
		            addCell(dataFont, feeTable, inputBean.getDdDate());
		            addCell(dataFont, feeTable, "DD Amount");
		            addCell(dataFont, feeTable, inputBean.getDdAmount());
		            addCell(dataFont, feeTable, "Bank Name");
		            addCell(dataFont, feeTable, inputBean.getBankName());
	            }
	            
	            PdfPTable docTable = new PdfPTable(new float[] { 1 });
	            docTable.setWidthPercentage(100);
	            addCell(headerFont, docTable, "Document Uploaded");
	
	            //System.out.println(inputBean.getAttachmentList().get(0).getDocName()+" document name");
	            
	            for (int a = 0; a < (inputBean.getAttachmentList().size() - 1);a++) {
	                addCell(dataFont, docTable,(a + 1) + ". " +inputBean.getAttachmentList().get(a).getComponentDescription());
	                
	            }
	
	            document.open();
	
	            try {
	                Image studentPhoto = Image.getInstance(java.awt.Toolkit.getDefaultToolkit().createImage(this.getServletContext().getRealPath(File.separator) +
	                            docPath +File.separator +inputBean.getStudentId() +File.separator + "1111.jpg"), null);
	                studentPhoto.scaleAbsolute(100, 125);
	                studentPhoto.setAbsolutePosition(450f, 597f);
	
	                document.add(studentPhoto);
	            } catch (Exception ee) {
	                System.out.println("image exception" + ee.getStackTrace());
	                System.out.println("image exception"+ee);
	            }
	
	            document.add(programTable);
	            document.add(new Phrase("\n"));
	            document.add(RegistrationNumberTable);
	            document.add(new Phrase("\n"));
	            document.add(studentTable);
	            document.add(new Phrase("\n"));
	            document.add(subjectCodeTable);
	            document.add(new Phrase("\n"));
	            document.add(academicTable);
	            document.add(new Phrase("\n"));
	            document.add(firstDegreeTable);
	            document.add(new Phrase("\n"));
	            document.add(examCenterTable);
	            document.add(new Phrase("\n"));
	            document.add(entranceTable);
	            document.add(new Phrase("\n"));
	            document.add(feeTable);
	            document.add(new Phrase("\n"));
	            document.add(docTable);
	
	            document.close();
        	}
        	 
        } catch (Exception e) {
            e.printStackTrace();
        }

        return inputBean;
    }

    /**
     * This method add a cell to Table
     *
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

	
	@SuppressWarnings("unchecked")
	public List<ApplicantAccountBean> getApplicantsPrograms(String userEmail) {
		List<ApplicantAccountBean> progDetail=null;
		try{
			progDetail=sqlMapClient.queryForList("getApplicantsPrograms",userEmail);
		}
		catch(Exception ex){
			System.out.println("getApplicantsPrograms "+ex.getStackTrace()+ex);
			
		}
		return progDetail;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SummarySheetBean> getApplicantDetails(String userEmail) {
		List<SummarySheetBean> summaryList=null;
		SummarySheetBean summaryBean = new SummarySheetBean();
		try{
			summaryBean.setPrimaryEmail(userEmail);
			summaryList=sqlMapClient.queryForList("getApplicantDetails",summaryBean);
		}
		catch(Exception ex){
			System.out.println("getApplicantDetails"+ex.getStackTrace()+" "+ex);
		}
		return summaryList;
	}

	@SuppressWarnings("unchecked")
	public List<SummarySheetBean> getExaminationCenter(SummarySheetBean inputBean) {
		
		List<SummarySheetBean> centerDescriptionList = new ArrayList<SummarySheetBean>();

        try {
        	centerDescriptionList = sqlMapClient.queryForList("getCenterCodeDescription",inputBean);
        } catch (Exception ex) {
            logObj.logger.error(ex.getMessage());
        }

        return centerDescriptionList;
	}

	@SuppressWarnings("static-access")
	public SummarySheetBean sendMailConfirmation(SummarySheetBean inputBean)
			throws Exception {
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
            System.out.println("Mail exception=" + mailexc.getStackTrace());
            throw mailexc;
        }
		System.out.println("impl");
		return inputBean;
	}
	
}
