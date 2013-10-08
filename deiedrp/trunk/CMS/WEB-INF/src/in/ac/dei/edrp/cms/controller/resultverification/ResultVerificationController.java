/**
 * @(#) StagingTableReportController.java
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
package in.ac.dei.edrp.cms.controller.resultverification;
import in.ac.dei.edrp.cms.dao.resultverification.ResultVerificationDao;
import in.ac.dei.edrp.cms.domain.degreelist.DegreeListInfoGetter;
import in.ac.dei.edrp.cms.domain.resultverification.ResultVerificationBean;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.write.Alignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.sun.org.apache.commons.collections.ListUtils;

/**
 * This controller is designed for setting & retrieving
 * the final semester result statistics (category wise)
 * @author Nupur Dixit
 * @date 26 Sep 2012
 * @version 1.0
 */
@Controller
public class ResultVerificationController extends MultiActionController{
	
	@Autowired
	@Qualifier("ResultVerificationImpl")
	private ResultVerificationDao resultVerificationDao;
	
	@RequestMapping("/resultVerification/generateRequestId")
	public ModelAndView generateRequestId(HttpServletRequest request,HttpServletResponse response)throws Exception {
		System.out.println("inside controller");		
		HttpSession session=request.getSession(true);
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive","sessionInactive", true);
		}
		String universityId = (String)session.getAttribute("universityId");
		ResultVerificationBean resultVerificationBean = new ResultVerificationBean();
		resultVerificationBean.setUniversityId(universityId);
		ResultVerificationBean requestNo = resultVerificationDao.getRequestNo(resultVerificationBean);
		List<ResultVerificationBean> listBean = new ArrayList<ResultVerificationBean>();
		listBean.add(requestNo);
		return new ModelAndView("resultVerification/ResultVerification", "resultObject", listBean);
	}
	
	@RequestMapping("/resultVerification/getRollNo")
	public ModelAndView getRollNo(HttpServletRequest request,HttpServletResponse response)throws Exception {
		System.out.println("inside get roll no controller");		
		HttpSession session=request.getSession(true);
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive","sessionInactive", true);
		}
		String universityId = (String)session.getAttribute("universityId");
		ResultVerificationBean resultVerificationBean = new ResultVerificationBean();
		resultVerificationBean.setUniversityId(universityId);
		String[] selRolNo = request.getParameterValues("rolNoArray") ;
		
		for(String i:selRolNo) {
			  System.out.println("nupooooooooooo"+i);
		}
//		List<ResultVerificationBean> rollNoList = new ArrayList<ResultVerificationBean>();
		
		List<ResultVerificationBean> rollNoList = resultVerificationDao.getRollNo(resultVerificationBean);
		try{
		List<String> allRollNo = new ArrayList<String>();
		for(ResultVerificationBean beanId:rollNoList){
			allRollNo.add(beanId.getRollNumber());
		}
		List<String> diff = ListUtils.subtract(Arrays.asList(selRolNo), allRollNo);
		System.out.println("diff size :"+diff.size());
		if(diff.size()>0){
			rollNoList.clear();
			for(String a:diff){
				ResultVerificationBean aa = new ResultVerificationBean();
				aa.setRollNumber(a);
				rollNoList.add(aa);
				System.out.println("new size of roll no list :"+rollNoList.size());
				System.out.println("difference in array :"+a);
			}			
		}
		else{
			rollNoList.clear();
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("resultVerification/ResultVerification", "resultObject", rollNoList);
	}
	
	/**
	 * This method set values in result verification request header and  detail
	 * @param request
	 * @param response
	 * @return ModelAndView containing info (success/Failure)
	 */
	@RequestMapping("/resultVerification/setResultVerRequest")
	public ModelAndView setResultVerRequest(HttpServletRequest request,HttpServletResponse response)throws Exception {
		System.out.println("inside setResultVerRequest controller");		
		HttpSession session=request.getSession(true);
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive","sessionInactive", true);
		}
		String universityId = (String)session.getAttribute("universityId");
		ResultVerificationBean resultVerificationBean = new ResultVerificationBean();
		resultVerificationBean.setUniversityId(universityId);
		
		resultVerificationBean.setRequester(request.getParameter("requester"));
		resultVerificationBean.setReceiveDate((request.getParameter("receiveDate")==""?null:request.getParameter("receiveDate")));		
		resultVerificationBean.setCompName(request.getParameter("companyName"));
		resultVerificationBean.setCompAdd(request.getParameter("companyAdd"));
		resultVerificationBean.setRequestType(request.getParameter("requestType"));
		resultVerificationBean.setRefNo(request.getParameter("referenceNo"));
		resultVerificationBean.setRefDate((request.getParameter("refDate")==""?null:request.getParameter("refDate")));
		System.out.println("ref date :"+resultVerificationBean.getRefDate());
		resultVerificationBean.setCreatorId((String)session.getAttribute("userName"));
		resultVerificationBean.setModifierId((String)session.getAttribute("userName"));
		StringTokenizer rollNoToken=new StringTokenizer(request.getParameter("rollNo"),"|");
		List<String> rollList = new ArrayList<String>();
		while(rollNoToken.hasMoreTokens()){
			rollList.add(rollNoToken.nextToken());
		}
		resultVerificationBean.setRollNoList(rollList);
		String updateFlag = request.getParameter("updateFlag");
		String isInserted = null;
		if(updateFlag.contains("update")){
			String[] selRolNo = request.getParameterValues("rolNoArray") ;			
			for(String i:selRolNo) {
				  System.out.println("enterd roll no nupooooooooooo"+i);
			}
			String[] olderRolNo = request.getParameterValues("olderRollNo") ;
			
			for(String i:olderRolNo) {
				  System.out.println(" older roll no nupooooooooooo"+i);
			}			
			resultVerificationBean.setRollNoList(Arrays.asList(selRolNo));
			resultVerificationBean.setOlderRollNoList(Arrays.asList(olderRolNo));
			resultVerificationBean.setRequestNo(request.getParameter("requestNo"));
			isInserted = resultVerificationDao.updateResultVerValue(resultVerificationBean);
			isInserted = "update"+isInserted;
			System.out.println("yes update flag is true :"+isInserted);
//			return null;
		}
		else{
			isInserted = resultVerificationDao.insertResultVerValue(resultVerificationBean);
			System.out.println("no update flag is false");
		}
		System.out.println("is inserted "+isInserted);
		List<ResultVerificationBean> listBean = new ArrayList<ResultVerificationBean>();
		ResultVerificationBean answerBean =  new ResultVerificationBean();
		answerBean.setExtra(isInserted);
		listBean.add(answerBean);
		return new ModelAndView("resultVerification/ResultVerification", "resultObject", listBean);
	}
		
	/**
	 * This method set values in result verification request header and  detail
	 * @param request
	 * @param response
	 * @return ModelAndView containing info (success/Failure)
	 */               
	@RequestMapping("/resultVerification/getRequestSearchService")
	public ModelAndView getRequestSearchService(HttpServletRequest request,HttpServletResponse response)throws Exception {
		System.out.println("inside getRequestSearchService controller");		
		HttpSession session=request.getSession(true);
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive","sessionInactive", true);
		}
		String universityId = (String)session.getAttribute("universityId");
		ResultVerificationBean resultVerificationBean = new ResultVerificationBean();
		resultVerificationBean.setUniversityId(universityId);
		resultVerificationBean.setRequestType(request.getParameter("requestType"));
		resultVerificationBean.setCompName((request.getParameter("companyName")==""?"%":("%"+request.getParameter("companyName")+"%")));
		resultVerificationBean.setReceiveDate((request.getParameter("receiveDate")==""?null:request.getParameter("receiveDate")));		
		System.out.println("receive date comp name "+resultVerificationBean.getReceiveDate()+" : "+resultVerificationBean.getCompName());
		List<ResultVerificationBean> requestList = new ArrayList<ResultVerificationBean>();		
		requestList = resultVerificationDao.getRequestHeader(resultVerificationBean);
		System.out.println("request list size "+requestList.size());
		return new ModelAndView("resultVerification/ResultVerification", "resultObject", requestList);
	}
		
	/**
	 * This method set values in result verification request header and  detail
	 * @param request
	 * @param response
	 * @return ModelAndView containing info (success/Failure)
	 */               
	@RequestMapping("/resultVerification/getRequestDetail")
	public ModelAndView getRequestDetail(HttpServletRequest request,HttpServletResponse response)throws Exception {
		System.out.println("inside getRequestDetail controller");		
		HttpSession session=request.getSession(true);
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive","sessionInactive", true);
		}
		String universityId = (String)session.getAttribute("universityId");
		ResultVerificationBean resultVerificationBean = new ResultVerificationBean();
		resultVerificationBean.setUniversityId(universityId);
		resultVerificationBean.setRequestType(request.getParameter("requestType"));
		resultVerificationBean.setRequestNo(request.getParameter("requestNo"));		
		List<ResultVerificationBean> rollNoList = new ArrayList<ResultVerificationBean>();		
		rollNoList = resultVerificationDao.getRequestDetail(resultVerificationBean);
		System.out.println("request list size "+rollNoList.size());
		return new ModelAndView("resultVerification/ResultVerification", "resultObject", rollNoList);
	}
		
	/**
	 * This method delete values in result verification request header and  detail
	 * @param request
	 * @param response
	 * @return ModelAndView containing info (success/Failure)
	 */               
	@RequestMapping("/resultVerification/deleteRequestRecords")
	public ModelAndView deleteRequestRecords(HttpServletRequest request,HttpServletResponse response)throws Exception {
		System.out.println("inside deleteRequestRecords controller");	
		List<ResultVerificationBean> deletedRecords = new ArrayList<ResultVerificationBean>();
		HttpSession session=request.getSession(true);
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive","sessionInactive", true);
		}
		String universityId = (String)session.getAttribute("universityId");
		
		String[] selReqNo = request.getParameterValues("deleteRecords") ;
		for(String reqNo:selReqNo){
			ResultVerificationBean resultVerificationBean = new ResultVerificationBean();
			resultVerificationBean.setUniversityId(universityId);
			resultVerificationBean.setRequestNo(reqNo);
			deletedRecords.add(resultVerificationBean);
			System.out.println("sent request no:"+reqNo);
		}		
		String isDeleted = resultVerificationDao.deleteResultVerRecord(deletedRecords);			
		System.out.println("is deleted "+isDeleted);
		List<ResultVerificationBean> listBean = new ArrayList<ResultVerificationBean>();
		ResultVerificationBean answerBean =  new ResultVerificationBean();
		answerBean.setExtra(isDeleted);
		listBean.add(answerBean);
		return new ModelAndView("resultVerification/ResultVerification", "resultObject", listBean);
	}
	
	/**
	 * This method set values in result verification request header and  detail
	 * @param request
	 * @param response
	 * @return ModelAndView containing info (success/Failure)
	 */               
	@RequestMapping("/resultVerification/getRequestType")
	public ModelAndView getRequestType(HttpServletRequest request,HttpServletResponse response)throws Exception {
		System.out.println("inside getRequestType controller");		
		HttpSession session=request.getSession(true);
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive","sessionInactive", true);
		}
		String universityId = (String)session.getAttribute("universityId");
		ResultVerificationBean resultVerificationBean = new ResultVerificationBean();
		resultVerificationBean.setUniversityId(universityId);		
		List<ResultVerificationBean> requestTypeList = new ArrayList<ResultVerificationBean>();		
		requestTypeList = resultVerificationDao.getRequestType(resultVerificationBean);
		System.out.println("request list size "+requestTypeList.size());
		return new ModelAndView("resultVerification/ResultVerification", "resultObject", requestTypeList);
	}
	
	/**
	 * The method generates a report for the students
	 * whose results need to be verified
	 * @param request input passed
	 * @param response report generated in response
	 * @return report
	 * @throws Exception
	 */
	@RequestMapping("/resultVerification/VerificationOfResultReport")
	public ModelAndView VerificationOfResultReport(HttpServletRequest request,HttpServletResponse response)throws Exception{		
		System.out.println("inside VerificationOfResultReport controller");		
		HttpSession session=request.getSession(true);
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive","sessionInactive", true);
		}
		String universityId = (String)session.getAttribute("universityId");
		ResultVerificationBean resultVerificationBean = new ResultVerificationBean();
		resultVerificationBean.setUniversityId(universityId);
		resultVerificationBean.setRequestType(request.getParameter("requestType"));
		resultVerificationBean.setRequestNo(request.getParameter("requestNo"));
		resultVerificationBean.setRequester(request.getParameter("requester"));
		resultVerificationBean.setCompName(request.getParameter("compName"));
		resultVerificationBean.setCompAdd(request.getParameter("compAdd"));
		resultVerificationBean.setReceiveDate(request.getParameter("receiveDate"));
		resultVerificationBean.setRefNo(request.getParameter("refNo"));
		resultVerificationBean.setRefDate(request.getParameter("refDate"));
		List<ResultVerificationBean> rollNoList = new ArrayList<ResultVerificationBean>();		
		rollNoList = resultVerificationDao.getRequestDetail(resultVerificationBean);
		System.out.println("request list size "+rollNoList.size());	
		List<String> rollNo = new ArrayList<String>();
		for(ResultVerificationBean aa:rollNoList){
			rollNo.add(aa.getRollNumber());
		}
		System.out.println("roll no array size:"+rollNo.size());
		resultVerificationBean.setRollNoList(rollNo);
		String isInserted = buildPdfDocument(resultVerificationBean, request, response);
		System.out.println("is inserted "+isInserted);
		List<ResultVerificationBean> listBean = new ArrayList<ResultVerificationBean>();
		ResultVerificationBean answerBean =  new ResultVerificationBean();
		answerBean.setExtra(isInserted);
		listBean.add(answerBean);
		return new ModelAndView("resultVerification/ResultVerification", "resultObject", listBean);
	}
	
	/**
	 * This is method for generating PDF document report 
	 * @param model, Object of Map
	 * @param document, The output PDF document
	 * @param pdfWriter, Object of PdfWriter
	 * @param request, HttpServletRequest
	 * @param response, HttpServletResponse
	 */
	@SuppressWarnings("unchecked")
	protected String buildPdfDocument(ResultVerificationBean input,HttpServletRequest request, HttpServletResponse response){		
		DegreeListInfoGetter infoGetter  = new DegreeListInfoGetter();		
		List<DegreeListInfoGetter> resultList = new ArrayList<DegreeListInfoGetter>();	
		String result = "false";
		try{
		HttpSession session = request.getSession(true);		
		infoGetter.setUniversityCode(session.getAttribute("universityId").toString());
		infoGetter.setUniversityName(session.getAttribute("universityName").toString());		
		/*
		 * path of the directory
		 */
		String sep = System.getProperty("file.separator");
		ResourceBundle resourceBundle = ResourceBundle.getBundle("in" + sep + "ac"
				+ sep + "dei" + sep + "edrp" + sep + "cms" + sep
				+ "databasesetting" + sep + "MessageProperties", new Locale("en",
				"US"));
		String directory = getServletContext().getRealPath("/")+"REPORTS"+sep+input.getUniversityId()+sep+input.getRequestType() ;
		File file = new File(directory);
		file.mkdirs();		
		Document document = new Document(PageSize.A4);
		/*
		 * temporary list of roll numbers
		 */
		List rollList = input.getRollNoList();		
		Iterator iterator = rollList.iterator();		
		PdfWriter.getInstance(document, new FileOutputStream(directory + sep + input.getRequestNo()+ ".pdf"));		
		HeaderFooter footerIN = new HeaderFooter(new Phrase(resourceBundle.getString("verificationfooter")
								+"\n\n"+ "                                                                          " +
								resourceBundle.getString("registrardetails")),false);
		footerIN.setBorder(0);		
		document.setFooter(footerIN);		
		document.open();
		document.add(Chunk.NEWLINE);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Paragraph emptyParagraph= new Paragraph(new Phrase(resourceBundle.getString("confidential")));
		emptyParagraph.setAlignment(1);
		Paragraph requestNo = new Paragraph("Request No:"+input.getRequestNo()+"                                                               "+
				"                                          "+dateFormat.format(new Date())
		+"\nTo:\n The "+input.getRequester()+
				"\n"+input.getCompName()+"\n"+input.getCompAdd());
		Paragraph otherDetailsParagraph = new Paragraph(new Phrase("                      "+resourceBundle.getString("referencedetails")+"    " +
				input.getRefNo()+" , Dated "+input.getRefDate()+"\n"
						+"                                            (Received by this office on "+input.getReceiveDate()+" )" +
								"\n"+"                       "+
						resourceBundle.getString("mailsubject")+"      "+resourceBundle.getString("defaultsubject")+"\n\n"));
		
		Paragraph newParagraph = new Paragraph(new Phrase(resourceBundle.getString
									("greetHeader")+"\n"+"          "+resourceBundle.getString("defaultText")));
		
		Phrase phrase = new Phrase("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -" +
				" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -" );
		
		document.add(emptyParagraph);
		document.add(requestNo);
		document.add(otherDetailsParagraph);
		document.add(newParagraph);
		document.add(phrase);		
		PdfPTable pTable  = null;
		PdfPCell cel =null;		
		Phrase cgpaPhrase =null;		
		int i=0;
		int j = 0;		
		while (iterator.hasNext()) {			
			Object object = (Object) iterator.next();			
			infoGetter.setRollNumber(object.toString());			
			resultList = resultVerificationDao.getStudents4Verification(infoGetter);
			Phrase studentDataPhrase = new Phrase();			
			for(@SuppressWarnings("unused") DegreeListInfoGetter getter:resultList){								
				pTable = new PdfPTable(new float[] { 6, 4 });
				pTable.setWidthPercentage(100f);
				pTable.setHorizontalAlignment(Element.ALIGN_LEFT);
				cel =new PdfPCell(new Phrase(i+1+". "+resourceBundle.getString("studentName")+resultList.get(0).getStudentName()));				
				cel.setBorderWidth(0);				
				pTable.addCell(cel);				
				cel =new PdfPCell(new Phrase("           "+resourceBundle.getString("rollNumber")+resultList.get(0).getStudentRollNumber()));				
				cel.setBorderWidth(0);
				pTable.addCell(cel);				
				cel =new PdfPCell(new Phrase("    "+resourceBundle.getString("passedExam")+resultList.get(0).getProgramName()));				
				cel.setBorderWidth(0);
				pTable.addCell(cel);				
				cel =new PdfPCell(new Phrase("           "+resourceBundle.getString("branchName")+resultList.get(0).getBranchName()));				
				cel.setBorderWidth(0);
				pTable.addCell(cel);				
				cel =new PdfPCell(new Phrase("    "+resourceBundle.getString("specialization")+resultList.get(0).getSpecializationName()));				
				cel.setBorderWidth(0);
				pTable.addCell(cel);				
				cel =new PdfPCell(new Phrase("           "+resourceBundle.getString("session")+resultList.get(0).getPassedFromSession()));				
				cel.setBorderWidth(0);
				pTable.addCell(cel);				
				if(resultList.get(0).getProgramPrintType().equalsIgnoreCase("SAG")){					
					cgpaPhrase =new Phrase();					
					cgpaPhrase.add(new Phrase("    "+resourceBundle.getString("cgpa")
								+resultList.get(0).getCgpa()+"                        "
								+resourceBundle.getString("division")+": "+resultList.get(0).getDivision()));									
				}else if(resultList.get(0).getProgramPrintType().equalsIgnoreCase("TAP")){					
					cgpaPhrase =new Phrase();					
					cgpaPhrase.add(new Phrase("    "+resourceBundle.getString("cgpa")
								+"                                 "+resourceBundle.getString("cgpatheory")+": "+resultList.get(0).getTheoryCGPA()+"                        "
								+resourceBundle.getString("cgpapractical")+": "+resultList.get(0).getPracticalCGPA()+"\n"+
								"                             "+resourceBundle.getString("cgpacombined")+
								"                              "+resultList.get(0).getCgpa()));
					
				}				
				cgpaPhrase.add("\n");				
				j = j+1;				
				if(j==1){					
					Iterator<DegreeListInfoGetter> iterator2 = resultList.iterator();					
					studentDataPhrase.add(new Phrase("    "+resourceBundle.getString("semestersgpa")));					
					while (iterator2.hasNext()) {
						DegreeListInfoGetter degreeListInfoGetter = (DegreeListInfoGetter) iterator2.next();												
						if(resultList.get(0).getProgramPrintType().equalsIgnoreCase("SAG")){							
							studentDataPhrase.add(new Phrase("                 "+degreeListInfoGetter.getSemesterCode()+"-"+degreeListInfoGetter.getSGPA()));							
						}
						else if(resultList.get(0).getProgramPrintType().equalsIgnoreCase("TAP")){						
							studentDataPhrase.add(new Phrase("                 "+resourceBundle.getString("cgpatheory")+":   "+
									degreeListInfoGetter.getSemesterCode()+"-"+degreeListInfoGetter.getTheorySGPA()+"         "
									+resourceBundle.getString("cgpapractical")+
									":   "+degreeListInfoGetter.getSemesterCode()+"-"+degreeListInfoGetter.getPracticalSGPA()+"\n"
									+"                                                        "));							
						}
					}					
					studentDataPhrase.add(new Phrase("\n"));
				}
			}			
			i=i+1;
			j=0;			
			document.add(pTable);
			document.add(studentDataPhrase);
			document.add(cgpaPhrase);
			document.add(phrase);
		}		
		document.close();		
		result="true";
		}catch(Exception e){
			e.printStackTrace();
			result="false";
		}
		return result;
	}
}
