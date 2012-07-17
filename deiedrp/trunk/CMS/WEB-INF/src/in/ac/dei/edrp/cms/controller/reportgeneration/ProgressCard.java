package in.ac.dei.edrp.cms.controller.reportgeneration;


import in.ac.dei.edrp.cms.dao.report.ReportDao;
import in.ac.dei.edrp.cms.dao.reportgeneration.ProgressCardDao;
import in.ac.dei.edrp.cms.domain.report.ReportLogBean;
import in.ac.dei.edrp.cms.domain.reportgeneration.ProgressCardInfo;
import in.ac.dei.edrp.cms.domain.reportgeneration.SemesterInfoForAllStudents;
import in.ac.dei.edrp.cms.utility.ReportPath;
import in.ac.dei.edrp.cms.utility.ReportPathBean;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;

public class ProgressCard extends AbstractController{
	private ProgressCardInfo trackingPdfGenration = new ProgressCardInfo();
	private ProgressCardDao progressCardDao;		
	
	public void setProgressCardDao(ProgressCardDao progressCardDao) {
		this.progressCardDao = progressCardDao;
	}
	private ReportDao reportDao;
	
	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}
	
	Locale obj = new Locale("en","Us");
	String sep=System.getProperty("file.separator");
	ResourceBundle message = ResourceBundle.getBundle("in"+sep+"ac"+sep+"dei"+sep+"edrp"+sep+"cms"+sep+"dao"+sep+"reportgeneration"+sep+"ApplicationResources", obj); 

	Calendar calendar = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");		
	java.sql.Date date = new java.sql.Date(calendar.getTime().getTime());

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception { 		
		
		HttpSession session = request.getSession(true);
		String universityId =(String) session.getAttribute("universityId");
		if(universityId == null){
			return new ModelAndView("general/SessionInactive","sessionInactive",true);
		}
		
		String entityId = request.getParameter("entityId");
		String entityName=request.getParameter("entityName");
		String programId = request.getParameter("programId");		
		String progressCardType = request.getParameter("pssReportName");
//		String progressCardType = request.getParameter("reportType");
		String semesterId = request.getParameter("semesterCode");
//		String semesterId = request.getParameter("semesterId");		
		String semesterName = request.getParameter("semesterName");
		String semesterStartDate = request.getParameter("semesterStartDate");
		String semesterEndDate = request.getParameter("semesterEndDate");
		String semesterSequence = request.getParameter("semesterSequence"); 
		String report = request.getParameter("report");	
		System.out.println("report "+report+ " progress card type "+progressCardType);
		System.out.println(entityId + " "+programId +" "+semesterEndDate+" "+semesterStartDate + " "+semesterId +" "+semesterSequence);
		
		ProgressCardInfo progressCardInfo = new ProgressCardInfo();
		progressCardInfo.setEntityId(entityId);
		progressCardInfo.setEntityName(entityName);
		progressCardInfo.setProgramId(programId);
		progressCardInfo.setSemesterEndDate(semesterEndDate);
		progressCardInfo.setSemesterStartDate(semesterStartDate);
		progressCardInfo.setUniversityCode(universityId);	
		progressCardInfo.setSemesterId(semesterId);
		progressCardInfo.setProgressCardType(progressCardType);
		progressCardInfo.setSemesterSequence(semesterSequence);
		progressCardInfo.setWhatToPrint(report);
		progressCardInfo.setSemesterName(semesterName);
//		progressCardInfo.setSessionStartDate(request.getParameter("sessionStartYear"));
//		progressCardInfo.setSessionEndDate(request.getParameter("sessionEndYear"));
		progressCardInfo.setSessionStartDate(request.getParameter("fromSession"));
		progressCardInfo.setSessionEndDate(request.getParameter("toSession"));
		String processStatus = buildPdfDocument(progressCardInfo,request,response);
		System.out.println("status is : "+ processStatus);				
		String message ="";			
		if(processStatus.contains("true")){
			message = processStatus;
		}
		else{
			message = "false-There is some error in PDF Generation kindly view the error log for more information";
		}
		return new ModelAndView("activitymaster/SubmitSuccesful", "message", message);				
	}
	
	protected String buildPdfDocument(ProgressCardInfo progressCardInfo,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Document document = new Document();
		PdfWriter writer=null;
		SemesterWisePdfView semesterWiseMarksPdfView = new SemesterWisePdfView();
		//******************* Nupur *************		
		String filePath = "";
		File file;	
		HttpSession httpSession = request.getSession(true);
		ReportLogBean reportErrorBean = new ReportLogBean(httpSession.getAttribute("universityId").toString(),
				request.getParameter("programId"),
				request.getParameter("branchId"),request.getParameter("specializationId"),request.getParameter("entityId"),
				request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),
				request.getParameter("semesterEndDate"),
				request.getParameter("reportCode"),"","",
				request.getParameter("fromSession").substring(0, 4)+"-"+request.getParameter("toSession").substring(2,4));
//		try{
		//***************************************
		List<SemesterInfoForAllStudents> semesterInfoForStudents = new ArrayList<SemesterInfoForAllStudents>();
		ProgressCardInfo semesterInfoData = new ProgressCardInfo();		
		String programId =null;
		String programCode =null;
		String branchId = null;
		String branchName = null;
		String semesterId = null;
		String semesterName = progressCardInfo.getSemesterName();
		String semesterStartDate = null;
		String semesterEndDate  = null;		
		String modeOfEntry = null;
		String name = null;
		String switchNumber = null;
		String rollNo = null;
		String programName = null;		
		String sessionStartDate =progressCardInfo.getSessionStartDate();
		String sessionEndDate =progressCardInfo.getSessionEndDate();
		String enrolmentNumber = null;		
		String printType = null;
		String entityId = null;									
		String sequenceNumber = null;	
		String inSemester = null;
		String inputSemester = null;
		String progressCardType = null;
		String semesterSequence = null;
		String mainBranchId = null;
		String whatToPrint = null;
		String reportType = null;
		String session = null;
		String programCourseKey = null;
		int switchSequence=0;
		int noOfStudents = 0;
		int noOfPdfGenerated = 0;
		String pdfGenerated = "N";
		String errorCode = null;
		String genAttempt=null;
		entityId = progressCardInfo.getEntityId();
		semesterSequence = progressCardInfo.getSemesterSequence();
		whatToPrint = progressCardInfo.getWhatToPrint();
		//Mandeep
		semesterId=progressCardInfo.getSemesterId();
		String specializationId=null;
		
		//specializationId=progressCardInfo.getSpecializationId();

		
		
		
		String universityName =(String) httpSession.getAttribute("universityName");
		String universityAddress=(String) httpSession.getAttribute("address");
		
		progressCardType = progressCardInfo.getProgressCardType();
		List<ProgressCardInfo> courseMarksDetails=null;
		//getting the list of program course key's
		List<ProgressCardInfo> programCourseKeyList = progressCardDao.getProgramCourseKeys(progressCardInfo);
		//getting the TAP/SAG of a program
		List<ProgressCardInfo> programTAPSAGList = progressCardDao.progressCardClass(progressCardInfo);
		// it is student list on program_course_key.
		List<ProgressCardInfo> studentSatisfyingPCK = new ArrayList<ProgressCardInfo>();
		
		//totalMarksDetailsForPreviousSemesters variable will hold the previous semesters marks details of the student.
		List<ProgressCardInfo> totalMarksDetailsForPreviousSemesters = null;
		//totalMarksDetailsForCurrentSemester variable will hold the current semester marks details of the student.
		List<ProgressCardInfo> totalMarksDetailsForCurrentSemester = null;
		
		
		ProgressCardInfo cummulativeForFC = new ProgressCardInfo();
		
		String directoryName = null;
		String directoryNameForSemester = null;
//		File file;
		if(programTAPSAGList.size()>0){
			 if(programTAPSAGList.get(0).getPrintType()==null){
				 logger.error("TAP/SAG is not defined in program_master");
				 errorCode = "E110";
				reportErrorBean.setErrorCode(errorCode);
				String logResult = reportDao.insertReportErrorLog(reportErrorBean);
				return "false";
			 }
			 printType = programTAPSAGList.get(0).getPrintType(); 
		 }
		else{
			logger.error("entry for this program is missing in program_master");
			 errorCode = "E111";
			reportErrorBean.setErrorCode(errorCode);
			String logResult = reportDao.insertReportErrorLog(reportErrorBean);
			return "false";
		}
		if(programCourseKeyList.size()>0){	
			//making the directory for storing the file
			//*********nupur code *******************
			ReportPathBean reportPathBean = new ReportPathBean(httpSession.getAttribute("universityId").toString(),request.getParameter("entityId"),
					request.getParameter("programId"),request.getParameter("branchId"),request.getParameter("specializationId"),
					request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
					request.getParameter("enrollmentNumber"),request.getParameter("rollNumber"),request.getParameter("reportCode"),
					request.getParameter("reportType"),request.getParameter("fromSession"),request.getParameter("toSession"),request.getParameter("semesterType"));
					
			String reportPath = ReportPath.getPath(reportPathBean);				
			System.out.println("here in controller, after path generation "+reportPath);
			String path = this.getServletContext().getRealPath("/");
			path=path+reportPath;
			file = new File(path);
			file.mkdirs();
			//*****************************************************			
			directoryName = path;
			directoryNameForSemester = path;
		/*	System.out.println("in directory creation");
			directoryName = getServletContext().getRealPath("/")+"PDF";
			System.out.println(directoryName);
			file = new File(directoryName);
			file.mkdir();
			directoryName = directoryName +sep+universityName; 
			file = new File(directoryName);
			file.mkdir();
			directoryName = directoryName +sep+"Session- "+httpSession.getAttribute("startDate").toString().substring(0, 4)+"-"+
			httpSession.getAttribute("endDate").toString().substring(2, 4); 
			file = new File(directoryName);
			file.mkdir();
			directoryName = directoryName +sep+progressCardInfo.getEntityName(); 
			file = new File(directoryName);
			file.mkdir();
			directoryName = directoryName +sep+"ProgressCard"; 
			file = new File(directoryName);
			file.mkdir();			
			
			directoryName = directoryName+sep+programCourseKeyList.get(0).getProgramName();
			file = new File(directoryName);		
			file.mkdir();*/		
			directoryNameForSemester = directoryName;
		}
		
		//iterate over the all programCourseKey
		for(ProgressCardInfo pci:programCourseKeyList){					
			programName = pci.getProgramName();			
//			System.out.println("AA"+"program code "+pci.getProgramCode()+" and the program course key "+progressCardInfo.getProgramCourseKey());			
			programCode = pci.getProgramCode();
			branchName = pci.getBranchName();
			semesterStartDate = progressCardInfo.getSemesterStartDate();
			semesterEndDate = progressCardInfo.getSemesterEndDate();
			specializationId=pci.getSpecializationId();
			
			/*
			 * making a folder with the name of semester start and end date
			 */
			/*directoryName = directoryName +"\\"+semesterSequence+"_"+semesterStartDate+"_"+semesterEndDate;
			file = new File(directoryName);
			file.mkdir();*/										
			
			mainBranchId = pci.getBranchId();
			pci.setSemesterStartDate(semesterStartDate);
			pci.setSemesterEndDate(semesterEndDate);
			studentSatisfyingPCK = new ArrayList<ProgressCardInfo>();	
			
			// get the list of student on the program_course_key
			studentSatisfyingPCK = progressCardDao.studentSatisfyingPCK(pci);			
			noOfStudents = studentSatisfyingPCK.size();			
			
			if(whatToPrint.equalsIgnoreCase("progress")){
				reportType = progressCardType.equalsIgnoreCase("FINAL RESULT CARD") ? "FC" : "PC";					
			}
			
			//iterate for each student on the program.
			if(noOfStudents!=0){
			for(ProgressCardInfo student: studentSatisfyingPCK){
				sessionEndDate= student.getSessionEndDate().substring(0,4);				
				sessionStartDate = student.getSessionStartDate().substring(0,4);	
				programCourseKey = pci.getProgramCourseKey();
				session  = sessionStartDate+"-"+sessionEndDate.substring(2,4);
				errorCode = null;
				
				if(whatToPrint.equalsIgnoreCase("progress")){
					reportType = progressCardType.equalsIgnoreCase("FINAL RESULT CARD") ? "FC" : "PC";					
				}
				
				name = student.getName();
				enrolmentNumber = student.getEnrollmentNumber();
				rollNo = student.getRollNumber();
				reportErrorBean.setStudentRollNumber(rollNo);
				//mandeep
				genAttempt=student.getGenAttempt();
				inSemester=student.getInSemester();
				sessionEndDate= student.getSessionEndDate().substring(0,4);	
				sessionStartDate = student.getSessionStartDate().substring(0,4);								
				switchNumber =student.getSwitchNumber();
				modeOfEntry = student.getModeOfEntry();
				sequenceNumber = student.getSequenceNumber();
				inSemester = student.getInSemester();
//				programStatus = student.getProgramStatus();
//				outSemester = student.getOutSemester();				
				pci.setRollNoForDetail(rollNo);		
				pci.setEnrollmentNumber(enrolmentNumber);
				pci.setEntityId(entityId);
				//check activity_status of the award sheet verification activity of current programCoursekey is com or not.
				// if activity status is COM else skip report of this student
				ProgressCardInfo activityStatus=progressCardDao.checkStatusOfAwradSheet(pci);
				if(activityStatus==null){
					errorCode = "E100";
					reportErrorBean.setErrorCode(errorCode);
					String logResult = reportDao.insertReportErrorLog(reportErrorBean);
//					progressCardDao.insertIntoErrorLog(rollNo, pci.getProgramCourseKey(), reportType, errorCode, semesterStartDate, semesterEndDate, session);
					continue;
				}
				
				courseMarksDetails = new ArrayList<ProgressCardInfo>();
				// get student current program semester courses and marks.
				courseMarksDetails = progressCardDao.progressCardMarksDetails(pci);
				
				// if courses not found then skip this student's progress card  
				// insert error into error_log table.
				if(courseMarksDetails.size()==0){
					System.out.println("course marks details of size 0 ");
					errorCode = "E101";
					reportErrorBean.setErrorCode(errorCode);
					String logResult = reportDao.insertReportErrorLog(reportErrorBean);
//					progressCardDao.insertIntoErrorLog(rollNo, pci.getProgramCourseKey(), reportType, errorCode, semesterStartDate, semesterEndDate, session);
					continue;
				}
				else{
					///total marks
					totalMarksDetailsForCurrentSemester = new ArrayList<ProgressCardInfo>();
					totalMarksDetailsForPreviousSemesters = new ArrayList<ProgressCardInfo>();
						
					// get marks on the current program semester.
					totalMarksDetailsForCurrentSemester.addAll(progressCardDao.totalMarksDetails(pci));
					if(totalMarksDetailsForCurrentSemester.size() == 0){						
						errorCode =  "E102";
						reportErrorBean.setErrorCode(errorCode);
						String logResult = reportDao.insertReportErrorLog(reportErrorBean);
//						progressCardDao.insertIntoErrorLog(rollNo, pci.getProgramCourseKey(), reportType, errorCode, semesterStartDate, semesterEndDate, session);
						break;
					} 
						
					//Ankit section start
					pci.setRollNumber(rollNo);
					pci.setSemesterStartDate(semesterStartDate);
					//get student previous semester marks
					totalMarksDetailsForPreviousSemesters.addAll(getPreviousPCKMarks(pci));
					//add current marks details at the last index
					totalMarksDetailsForPreviousSemesters.addAll(totalMarksDetailsForCurrentSemester);
					//Ankit section End
				
				
					System.out.println("what to print "+whatToPrint+" error code "+errorCode);
					if(whatToPrint.equalsIgnoreCase("progress") && errorCode == null){					
						System.out.println("before acks");
						if(progressCardType.equalsIgnoreCase("FINAL RESULT CARD")){
							trackingPdfGenration.setReportType("FC");
							progressCardInfo.setRollNoForDetail(rollNo);
							progressCardInfo.setEnrollmentNumber(enrolmentNumber);
							progressCardInfo.setBranchId(pci.getBranchId());
							progressCardInfo.setSpecializationId(pci.getSpecializationId());
							cummulativeForFC = progressCardDao.cummulativeForFinalResultCard(progressCardInfo);								
						}
						else{
							trackingPdfGenration.setReportType("PC");		
						}
						
						System.out.println("print type : "+printType);
						boolean acknwoledge	= generatePdf(document, writer, request, response, name, rollNo, programName,branchName,
								inputSemester, sessionStartDate, sessionEndDate,enrolmentNumber,courseMarksDetails,
								totalMarksDetailsForCurrentSemester,totalMarksDetailsForPreviousSemesters,printType,progressCardType,
								directoryName,cummulativeForFC,semesterName);
					
						System.out.println("Return Value: "+ acknwoledge);
					
						if(acknwoledge == true){
							noOfPdfGenerated = noOfPdfGenerated + 1;
							System.out.println("generated pdf "+noOfPdfGenerated);
							pdfGenerated = "true";									
						}
						else {
							pdfGenerated = "false";
							errorCode="E105";
							//*********************** nupur ********************************
							reportErrorBean.setErrorCode(errorCode);
							String logResult = reportDao.insertReportErrorLog(reportErrorBean);							
							//**************************************************************
							//progressCardDao.insertIntoErrorLog(rollNo, pci.getProgramCourseKey(), reportType, errorCode, semesterStartDate, semesterEndDate, session);
							break;								
						}
					}						
					else{
						semesterInfoData = new ProgressCardInfo();
						semesterInfoData.setRollNumber(rollNo);
						semesterInfoData.setEntityId(entityId);
						semesterInfoData.setProgramCode(programCode);
						semesterInfoData.setBranchId(mainBranchId);
						semesterInfoData.setSemesterSequence(semesterSequence);
						semesterInfoData.setName(name);
						semesterInfoData.setSemesterId(semesterId);
						semesterInfoData.setGenAttempt(genAttempt);
						semesterInfoData.setSpecializationId(specializationId);
					    semesterInfoData.setSessionStartDate(sessionStartDate);
					    semesterInfoData.setSessionEndDate(sessionEndDate);
						System.out.println(semesterId+"semesterIdSEMWISE");
						semesterInfoForStudents.add(new SemesterInfoForAllStudents(semesterInfoData,totalMarksDetailsForPreviousSemesters));
					}
			}	
		}//end of student for loop		
		}	
		trackingPdfGenration.setProgramCourseKey(pci.getProgramCourseKey());
		trackingPdfGenration.setSemesterStartDate(semesterStartDate);
		trackingPdfGenration.setSemesterEndDate(semesterEndDate);
		trackingPdfGenration.setNoOfStudents(noOfStudents);
		System.out.println("no of pdf generated"+noOfPdfGenerated);
		trackingPdfGenration.setReportType(reportType);
		trackingPdfGenration.setNoOfPdfGenerated(noOfPdfGenerated);
		trackingPdfGenration.setPdfGenerated(pdfGenerated);
		trackingPdfGenration.setSession(sessionStartDate+"-"+sessionEndDate.substring(2,4));
			
//		if(whatToPrint.equalsIgnoreCase("progress")){
//			if(progressCardType.equalsIgnoreCase("FINAL RESULT CARD")){
//				trackingPdfGenration.setReportType("FC");
//				progressCardInfo.setRollNoForDetail(rollNo);
//				progressCardInfo.setEnrollmentNumber(enrolmentNumber);
//				progressCardInfo.setBranchId(pci.getBranchId());
//				progressCardInfo.setSpecializationId(pci.getSpecializationId());
//				cummulativeForFC = progressCardDao.cummulativeForFinalResultCard(progressCardInfo);								
//			}
//			else{
//				trackingPdfGenration.setReportType("PC");		
//			}
//		}	
		//***********************nupur************************
		if(noOfPdfGenerated!=0){
			ReportLogBean reportControlBean = new ReportLogBean(httpSession.getAttribute("universityId").toString(),request.getParameter("entityId"),
					request.getParameter("programId"),request.getParameter("branchId"),request.getParameter("specializationId"),
					request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
					request.getParameter("reportCode"),String.valueOf(noOfPdfGenerated),"No",
					request.getParameter("fromSession"),request.getParameter("toSession"),request.getParameter("semesterType"),
					httpSession.getAttribute("userName").toString());
			reportControlBean.setIsGenerated(request.getParameter("isGenerated")==null?"no":"yes");					
			String logResult = reportDao.insertReportLog(reportControlBean);
			System.out.println("report control log result "+logResult);
		}
		
		//****************************************************
//		System.out.println("b4 inserting");
//		int insertCompleted = progressCardDao.insertIntoReportControlLog(trackingPdfGenration);			
//		System.out.println("after inserting "+insertCompleted);
		if(noOfStudents==0){
			pdfGenerated="true-No student found";
			//pdfGenerated="NRF";
		}
		if(errorCode!=null){
			pdfGenerated="false";
			//pdfGenerated=errorCode;			
		}
		
		noOfPdfGenerated = 0;
		noOfStudents = 0;
	}//end of programCourrseKey Loop
		
	if(whatToPrint.equalsIgnoreCase("semesterwise")){
//		directoryNameForSemester = directoryNameForSemester +"\\SemesterWiseMarks";
		file = new File(directoryNameForSemester);
		file.mkdir();							
		pdfGenerated = semesterWiseMarksPdfView.generatePdfForSemesterWiseMarks(request,reportDao,document, writer, semesterInfoForStudents,directoryNameForSemester);		
	}
		
	return pdfGenerated;		
}
	
	public boolean generatePdf(Document document,PdfWriter writer,
			HttpServletRequest request,HttpServletResponse response,
			String name,String rollNo,String programName,String branchName,String semester,
			String sessionStartDate,String sessionEndDate,String enrolmentNumber,List<ProgressCardInfo> marksDetails,
			List<ProgressCardInfo> totalMarksDetailsForCurrentSem,List<ProgressCardInfo> totalMarksDetailsForPreviousSem,String printType,
			String progressCardType,String directoryName,ProgressCardInfo cummulativeForFC, String semesterName) throws Exception
	{	
		try{
			HttpSession httpSession=request.getSession(true);
			System.out.println("inside generatePDF print type " +printType);
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			sdf.format(calendar.getTime());
			java.sql.Date date = new java.sql.Date(calendar.getTime().getTime());
			String currentDate = sdf.format(date);
			String totalTheoryCredits = null;
			String totalPracticalCredits = null;
			String totalCredit = null;
			String weightTheory = null;
			String weightPractical = null;
			String sgpa = null;
			String remarks = null;
			String roman=null;
			float cumulativeTheory = 0;
			float cumulativePractical = 0; 
			float credits = 0;
			String marks;
			boolean flagPass = true;		

			document = new Document(PageSize.A4,25, 50, 50, 50);
			String reportDesc = request.getParameter("reportDescription").toString();
			reportDesc = reportDesc.replace("/", "-");
			reportDesc = reportDesc.replace("\\","-");
			System.out.println("report description "+reportDesc);
			System.out.println(directoryName+sep+reportDesc+"-"+rollNo+".pdf");
//			document.open();
//			writer = PdfWriter.getInstance(document,new FileOutputStream(directoryName+""+sep+""+"ProgressCard_"+rollNo+".pdf"));
			writer = PdfWriter.getInstance(document,new FileOutputStream(directoryName+reportDesc+"-"+rollNo+".pdf"));
				totalTheoryCredits = totalMarksDetailsForCurrentSem.get(0).getTotalTheoryCredits();
				totalPracticalCredits = totalMarksDetailsForCurrentSem.get(0).getTotalPracticalCredits();
				totalCredit = totalMarksDetailsForCurrentSem.get(0).getTotalCredit();
				weightTheory = totalMarksDetailsForCurrentSem.get(0).getWeightTheory();
				weightPractical = totalMarksDetailsForCurrentSem.get(0).getWeightPractical();
				sgpa = totalMarksDetailsForCurrentSem.get(0).getSgpa();
				remarks = totalMarksDetailsForCurrentSem.get(0).getRemark();							
				document.open();
				
				String[] universitytokens = httpSession.getAttribute("universityName").toString().split("\\(");
				String universityType="";
				if(universitytokens.length==2){
					universityType="("+universitytokens[1]+"\n";
				}
				
				String headerText1 = universitytokens[0]+"\n"+universityType+httpSession.getAttribute("address").toString()+", "+httpSession.getAttribute("city").toString()+
				"-"+httpSession.getAttribute("pin").toString()+", ("+httpSession.getAttribute("country").toString()+")\n\n "+progressCardType;
				Paragraph header1 = new Paragraph(headerText1,FontFactory.getFont(FontFactory.HELVETICA,10, Font.NORMAL, new Color(0, 0, 255)));		
				
				header1.setAlignment(Element.ALIGN_CENTER);				
				document.add(header1);
				
				
				Font cellFont = new Font(Font.HELVETICA, 8);				
				Table nameHeader = new Table(3,4);
			//	nameHeader.setBorderColor(new Color(255,255,255));		
				Cell c1 = new Cell(new Phrase("NAME : "+ name,cellFont));		
				c1.setHeader(true);
				c1.setColspan(2);
				c1.setBorder(Rectangle.NO_BORDER);
				nameHeader.addCell(c1);		
				 c1 = new Cell(new Phrase("ROLL NO : "+ rollNo,cellFont));
				 c1.setHorizontalAlignment( Element.ALIGN_RIGHT);
				 c1.setBorder(Rectangle.NO_BORDER);
				nameHeader.addCell(c1);
				c1 = new Cell(new Phrase("CLASS : "+programName,cellFont));
				c1.setColspan(3);				
				c1.setBorder(Rectangle.NO_BORDER);
				nameHeader.addCell(c1);
				
				String branchText;
				if(branchName.equalsIgnoreCase("NONE"))
				{
					branchText = " ";
				}				
				else
				branchText = "BRANCH : "+branchName;
				c1 = new Cell(new Phrase(branchText,cellFont));
				c1.setColspan(3);
				c1.setBorder(Rectangle.NO_BORDER);
				nameHeader.addCell(c1);
				
				c1 = new Cell(new Phrase("SEMESTER : "+ semesterName,cellFont));
				c1.setBorder(Rectangle.NO_BORDER);
				nameHeader.addCell(c1);
				
				c1 = new Cell(new Phrase("\t\t\t\t\t\t\t\t\tSESSION : "+ sessionStartDate+"-"+sessionEndDate,cellFont));
				c1.setBorder(Rectangle.NO_BORDER);
				nameHeader.addCell(c1);
				c1= new Cell(new Phrase("ENROL. NO. : "+enrolmentNumber,cellFont));		
				c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
				c1.setBorder(Rectangle.NO_BORDER);
				nameHeader.addCell(c1);
				nameHeader.setBorderWidth(0);		
				nameHeader.setBorder(Rectangle.NO_BORDER);
				nameHeader.endHeaders();		
				document.add(nameHeader);
				
				// dashed line 
				
//				PdfContentByte pcb = writer.getDirectContent();		
//				pcb.moveTo(70,writer.getVerticalPosition(true)-8);
//				pcb.lineTo(PageSize.A4.getWidth()-80,writer.getVerticalPosition(true)-8);
//				pcb.setLineDash(3f,3f);
//				pcb.stroke();		
//				
				Paragraph pp=new Paragraph(new Phrase("                -------------------------------------------------------------------------------------------------------"));
				document.add(pp);
				
				//Titles between lines
				
				PdfPTable marksTable = new PdfPTable(new float[] {2.3f,6.3f,1.3f,1.5f,1.5f,1.5f});
			//	marksTable.setWidthPercentage(100);
				//marksTable.setBorderColor(new Color(255,255,255));
				System.out.println("Size: "+ marksDetails.size());
				PdfPCell c2 = new PdfPCell(new Phrase("Course Number",cellFont));
				//c2.setHeader(true);
				//c2.setBorder(Rectangle.NO_BORDER);
				c2.setBorderWidth(0);
				marksTable.addCell(c2);
				
				c2 = new PdfPCell(new Phrase("COURSE TITLE",cellFont));
				//c2.setBorder(Rectangle.NO_BORDER);
				//c2.setWidth(50f);
//				c2.setColspan(2);
				c2.setBorderWidth(0);
				marksTable.addCell(c2);
				
				c2 = new PdfPCell(new Phrase("GRADES OBTAINED",cellFont));
//				c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			//	c2.setBorder(Rectangle.NO_BORDER);	
				c2.setBorderWidth(0);
				c2.setColspan(4);
				marksTable.addCell(c2);

				//blank cell to occupy the space.
				c2 = new PdfPCell();
				//c2.setBorder(Rectangle.NO_BORDER);
				c2.setBorderWidth(0);
				c2.setColspan(2);
				marksTable.addCell(c2);
				
				c2 = new PdfPCell(new Phrase("CONT. EVAL. \n",cellFont));
				//c2.setBorder(Rectangle.NO_BORDER);
				c2.setBorderWidth(0);
				marksTable.addCell(c2);
				
				c2 = new PdfPCell(new Phrase("END SEM.",cellFont));
				//c2.setBorder(Rectangle.NO_BORDER);
				c2.setBorderWidth(0);
				marksTable.addCell(c2);
				
				c2 = new PdfPCell(new Phrase("GRADE POINT \n ",cellFont));
				//c2.setBorder(Rectangle.NO_BORDER);
				c2.setBorderWidth(0);
				marksTable.addCell(c2);
				
				c2 = new PdfPCell(new Phrase("CREDITS",cellFont));
				//c2.setBorder(Rectangle.NO_BORDER);
				c2.setBorderWidth(0);
				marksTable.addCell(c2);		
				
	
				PdfPCell cr = new PdfPCell(new Phrase("-------------------------------------------------------------------------------------------------------"));
				cr.setBorderWidth(0);
				cr.setColspan(6);
				marksTable.addCell(cr);
				
				PdfPCell c3 = new PdfPCell(); 
				for(ProgressCardInfo pci : marksDetails)
				{
					c3 = new PdfPCell(new Phrase(pci.getCourseCode(),cellFont));
					c3.setBorderWidth(0);
					marksTable.addCell(c3);
					
					c3 = new PdfPCell(new Phrase(pci.getCourseName(),cellFont));
					c3.setBorderWidth(0);		
//					c3.setColspan(2);
					marksTable.addCell(c3);	
					
					c3 = new PdfPCell(new Phrase((pci.getStudentInternal()==null?"-":pci.getStudentInternal()).toString(),cellFont));
					c3.setBorderWidth(0);
					marksTable.addCell(c3);	
					
					//added by ankit 
					String externalGrade="";
					if(pci.getStudentExternal()==null){
						externalGrade="-";
					}					
					else{					
						externalGrade=pci.getStudentExternal();
					}
					
					c3 = new PdfPCell(new Phrase(externalGrade,cellFont));
					c3.setBorderWidth(0);
					marksTable.addCell(c3);
					
					c3 = new PdfPCell(new Phrase((pci.getStudentTotalMarks()==null?"":pci.getStudentTotalMarks()).toString(),cellFont));
					c3.setBorderWidth(0);
					marksTable.addCell(c3);			
					if( pci.getStudentStatus().equalsIgnoreCase("PAS"))
					{
						c3 = new PdfPCell(new Phrase(pci.getCredits().toString(),cellFont)) ;
						c3.setBorderWidth(0);
					}
					else
					{
						c3 = new PdfPCell(new Phrase(pci.getCredits().toString()+"*",cellFont));
						c3.setBorderWidth(0);
						flagPass = false;
					}
					c3.setBorderWidth(0);
					marksTable.addCell(c3);						
				}
				
				document.add(marksTable);	
				document.add(pp);

				Table totalMarksTable = new Table(4,2);			
				totalMarksTable.setBorderColor(new Color(255,255,255));
				Cell c4 = new Cell(new Phrase("TOTAL CREDITS : ",cellFont));
				c4.setHeader(true);
				c4.setBorder(Rectangle.NO_BORDER);
				totalMarksTable.addCell(c4);
				
				if(printType.equalsIgnoreCase("tap")){
					c4 = new Cell(new Phrase("THEORY : "+totalTheoryCredits,cellFont));
					c4.setBorder(Rectangle.NO_BORDER);
					totalMarksTable.addCell(c4);
				}
				else{
					c4 = new Cell(new Phrase(""+totalCredit,cellFont));
					c4.setBorder(Rectangle.NO_BORDER);
					totalMarksTable.addCell(c4);
				}
				
				
				c4 = new Cell(new Phrase("SEM. GRADE POINT AVG. : ",cellFont));
				c4.setBorder(Rectangle.NO_BORDER);
				totalMarksTable.addCell(c4);
				
				if(printType.equalsIgnoreCase("tap")){
					c4 = new Cell(new Phrase("THEORY : "+weightTheory,cellFont));
					c4.setBorder(Rectangle.NO_BORDER);
					totalMarksTable.addCell(c4);
				}
				else{
					c4 = new Cell(new Phrase(""+sgpa,cellFont));
					c4.setBorder(Rectangle.NO_BORDER);
					totalMarksTable.addCell(c4);
				}
				
				if(printType.equalsIgnoreCase("tap")){
					c4 = new Cell(new Phrase("",cellFont));
					c4.setBorder(Rectangle.NO_BORDER);				
					totalMarksTable.addCell(c4);
				
					c4 = new Cell(new Phrase("PRACTICAL "+totalPracticalCredits,cellFont));
					c4.setBorder(Rectangle.NO_BORDER);				
					totalMarksTable.addCell(c4);
				
					c4 = new Cell(new Phrase("",cellFont));
					c4.setBorder(Rectangle.NO_BORDER);				
					totalMarksTable.addCell(c4);
				
					c4 = new Cell(new Phrase("PRACTICAL "+weightPractical,cellFont));
					c4.setBorder(Rectangle.NO_BORDER);				
					totalMarksTable.addCell(c4);
				}
				document.add(totalMarksTable);	
				
				// this code section will add Grade Point Average in Semesters Section into PDF.
				Table semesterWiseMarks = new Table(9,4);
				semesterWiseMarks.setBorderColor(new Color(255,255,255));
				
				Cell c5 = new Cell();
				c5.setHeader(true);
				c5.setBorder(Rectangle.NO_BORDER);
				semesterWiseMarks.addCell(c5);
				
				c5 = new Cell(new Phrase("GRADE POINT AVERAGE IN SEMESTERS",cellFont));
				c5.setColspan(8);
				c5.setHorizontalAlignment(Element.ALIGN_CENTER);
				c5.setBorder(Rectangle.NO_BORDER);
				semesterWiseMarks.addCell(c5);
				
				c5 = new Cell(new Phrase("SEMESTER ",cellFont));
				c5.setBorder(Rectangle.NO_BORDER);
				semesterWiseMarks.addCell(c5);
				
				// loop will create Roman Number.
				for(int i=1;i<=8;i++)
				{
					roman = binaryToRoman(i);
					c5 = new Cell(new Phrase(roman,cellFont));
					c5.setBorder(Rectangle.NO_BORDER);
					c5.setHorizontalAlignment(Element.ALIGN_RIGHT);			
					semesterWiseMarks.addCell(c5);		
				}
				
				if(printType.equalsIgnoreCase("tap")){
					c5 = new Cell(new Phrase("THEORY",cellFont));
					c5.setBorder(Rectangle.NO_BORDER);
					semesterWiseMarks.addCell(c5);
				}
				else{
					c5 = new Cell(new Phrase("SGPA",cellFont));
					c5.setBorder(Rectangle.NO_BORDER);
					semesterWiseMarks.addCell(c5);
				}
				
				System.out.println("goin to print marks");
				
				// this loop will add student semester theory marks into GRADE POINT AVERAGE IN SEMESTERS section.
				
					for(int i=1;i<=8;i++){		
						System.out.println("previous sem size "+totalMarksDetailsForPreviousSem.size());
						if(i<=totalMarksDetailsForPreviousSem.size()){
							if(printType.equalsIgnoreCase("tap")){
								marks = totalMarksDetailsForPreviousSem.get(i-1).getWeightTheory();
								System.out.println("marks "+marks);
								c5 =  new Cell(new Phrase(marks,cellFont));
								cumulativeTheory = cumulativeTheory+Float.parseFloat(marks)*Float.parseFloat(totalMarksDetailsForPreviousSem.get(i-1).getTotalTheoryCredits());
								System.out.println("cumulative theory  "+cumulativeTheory);
								credits = credits + Float.parseFloat(totalMarksDetailsForPreviousSem.get(i-1).getTotalTheoryCredits());
								System.out.println("credits "+credits);
							}
							else{
								marks = totalMarksDetailsForPreviousSem.get(i-1).getSgpa();
								c5 =  new Cell(new Phrase(marks,cellFont));
								cumulativeTheory = cumulativeTheory+Float.parseFloat(marks)*Float.parseFloat(totalMarksDetailsForPreviousSem.get(i-1).getTotalCredit());
								credits = credits + Float.parseFloat(totalMarksDetailsForPreviousSem.get(i-1).getTotalCredit());
							}
							
						}
						else{
							c5 = new Cell(new Phrase("-",cellFont)); 
						}
						
						c5.setBorder(Rectangle.NO_BORDER);
						c5.setHorizontalAlignment(Element.ALIGN_RIGHT);
						semesterWiseMarks.addCell(c5);					
					}
				
				
				
				cumulativeTheory = Math.round((cumulativeTheory / credits) * (float) Math.pow(10,2))/(float) (Math.pow(10,2));				
				System.out.println("ct"+cumulativeTheory);
				
				if(printType.equalsIgnoreCase("tap")){
					c5 = new Cell(new Phrase("PRACTICAL ",cellFont));
					c5.setBorder(Rectangle.NO_BORDER);
					semesterWiseMarks.addCell(c5);
					
					credits = 0;
					// this loop will add student semester practical marks into GRADE POINT AVERAGE IN SEMESTERS section.
					for(int i=1;i<=8;i++)
					{
						if(i<=totalMarksDetailsForPreviousSem.size())
						{
							marks = totalMarksDetailsForPreviousSem.get(i-1).getWeightPractical();
							c5 =  new Cell(new Phrase(marks,cellFont));
							cumulativePractical = cumulativePractical+Float.parseFloat(marks)*Float.parseFloat(totalMarksDetailsForPreviousSem.get(i-1).getTotalPracticalCredits());
							credits = credits + Float.parseFloat(totalMarksDetailsForPreviousSem.get(i-1).getTotalPracticalCredits());
						}
						else c5 = new Cell(new Phrase("-",cellFont)); 
							c5.setBorder(Rectangle.NO_BORDER);
							c5.setHorizontalAlignment(Element.ALIGN_RIGHT);
							semesterWiseMarks.addCell(c5);	
					}
					
					cumulativePractical = Math.round((cumulativePractical / credits) * (float) Math.pow(10,2))/(float) (Math.pow(10,2));
					System.out.println(cumulativePractical);
				}
										
				document.add(semesterWiseMarks);
				
				// this section add Cumulative Grade point Average section if progressCardType is Final Result Card.
				if(progressCardType.equalsIgnoreCase("FINAL RESULT CARD"))
				{
					Table cumulativeTable = new Table(4,6);
					cumulativeTable.setBorderColor(new Color(255,255,255));
					cumulativeTable.setBorder(1);
					
					Cell c6= new Cell();
					c6.setHeader(true);
					c6.setBorder(Rectangle.NO_BORDER);
					cumulativeTable.addCell(c6);
					
					c6 = new Cell(new Phrase("CUMULATIVE GRADE POINT AVERAGE :",cellFont));
					c6.setHorizontalAlignment(Element.ALIGN_CENTER);
					c6.setBorder(Rectangle.NO_BORDER);
					c6.setColspan(2);
					cumulativeTable.addCell(c6);
					
					c6 = new Cell(new Phrase("DIVISION :",cellFont));
					c6.setHorizontalAlignment(Element.ALIGN_CENTER);
					c6.setBorder(Rectangle.NO_BORDER);
					cumulativeTable.addCell(c6);
					
					if(printType.equalsIgnoreCase("tap")){
						c6 = new Cell(new Phrase("THEORY ",cellFont));
					}
					else{
						c6 = new Cell(new Phrase("",cellFont));
					}
					
					c6.setHorizontalAlignment(Element.ALIGN_LEFT);
					c6.setBorder(Rectangle.NO_BORDER);
					cumulativeTable.addCell(c6);
					System.out.println("\n nupuuuuuuuuuuuuur "+cummulativeForFC);
					if(printType.equalsIgnoreCase("tap")){
						c6 = new Cell(new Phrase(cummulativeForFC!=null?cummulativeForFC.getCummulativeTheoryCgpa():"--",cellFont));
					}
					else{
						c6 = new Cell(new Phrase(cummulativeForFC!=null?cummulativeForFC.getCgpa():"--",cellFont));
					}					
					c6.setHorizontalAlignment(Element.ALIGN_CENTER);
					c6.setBorder(Rectangle.NO_BORDER);
					c6.setColspan(2);
					cumulativeTable.addCell(c6);
					
					if(printType.equalsIgnoreCase("tap")){
						c6 = new Cell(new Phrase(cummulativeForFC!=null?cummulativeForFC.getTheoryDivision():"--",cellFont));
					}
					else{
						c6 = new Cell(new Phrase(cummulativeForFC!=null?cummulativeForFC.getDivision():"--",cellFont));
					}					
					c6.setHorizontalAlignment(Element.ALIGN_CENTER);
					c6.setBorder(Rectangle.NO_BORDER);
					cumulativeTable.addCell(c6);
					
//					c6 = new Cell(new Phrase(calcaulateGrade(cumulativeTheory),cellFont));
//					c6.setHorizontalAlignment(Element.ALIGN_CENTER);
//					c6.setBorder(Rectangle.NO_BORDER);
//					cumulativeTable.addCell(c6);
					if(printType.equalsIgnoreCase("tap")){
						c6 = new Cell(new Phrase("PRACTICAL",cellFont));
						c6.setHorizontalAlignment(Element.ALIGN_LEFT);
						c6.setBorder(Rectangle.NO_BORDER);
						cumulativeTable.addCell(c6);
						
						c6 = new Cell(new Phrase(cummulativeForFC!=null?cummulativeForFC.getCummulativePracticalCgpa():"--",cellFont));
						c6.setHorizontalAlignment(Element.ALIGN_CENTER);
						c6.setBorder(Rectangle.NO_BORDER);
						c6.setColspan(2);
						cumulativeTable.addCell(c6);
//						c6 = new Cell(new Phrase(cummulativeForFC.getCummulativePracticalCgpa(),cellFont));
//						c6.setHorizontalAlignment(Element.ALIGN_CENTER);
//						c6.setBorder(Rectangle.NO_BORDER);
//						cumulativeTable.addCell(c6);

						c6 = new Cell(new Phrase(cummulativeForFC!=null?cummulativeForFC.getPracticalDivision():"--",cellFont));
						c6.setHorizontalAlignment(Element.ALIGN_CENTER);
						c6.setBorder(Rectangle.NO_BORDER);
						cumulativeTable.addCell(c6);
						
						c6 = new Cell(new Phrase("COMBINED GRADE POINT AVERAGE (THOERY + PRACTICAL) : ",cellFont));
						c6.setHorizontalAlignment(Element.ALIGN_LEFT);
						c6.setColspan(3);
						c6.setBorder(Rectangle.NO_BORDER);
						cumulativeTable.addCell(c6);
						
						c6 = new Cell(new Phrase(cummulativeForFC!=null?cummulativeForFC.getCgpa():"--",cellFont));
						c6.setHorizontalAlignment(Element.ALIGN_LEFT);
						c6.setColspan(1);
						c6.setBorder(Rectangle.NO_BORDER);
						cumulativeTable.addCell(c6);
					}
									
					
										
					
					c6 = new Cell(new Phrase(message.getString("message.combinedPercentage"),cellFont));
					c6.setHorizontalAlignment(Element.ALIGN_LEFT);
					c6.setColspan(4);
					c6.setBorder(Rectangle.NO_BORDER);
					cumulativeTable.addCell(c6);					
					
					document.add(cumulativeTable);	
					
				}				
	            document.add(Chunk.NEWLINE);
	            document.add(Chunk.NEWLINE);	            
				String remarksText = " REMARKS - "+remarks+"";
				Paragraph remarkPara = new Paragraph(remarksText,cellFont);
				remarkPara.setIndentationLeft(48);				
				document.add(remarkPara);
				document.add(Chunk.NEWLINE);				
				document.add(Chunk.NEWLINE);        
				
				Paragraph remarkFooter = new Paragraph("\n\n\n\n",FontFactory.getFont(FontFactory.HELVETICA,8, Font.NORMAL, new Color(0, 0, 255)));				
				document.add(remarkFooter);
				
				//footer
				Table footer = new Table(3,1);
				footer.setBorderColor(new Color(255,255,255));				
				Cell c6 = new Cell(new Phrase("DATED "+currentDate,cellFont));
				c6.setBorder(Rectangle.NO_BORDER);
				footer.addCell(c6);
				
				c6 = new Cell(new Phrase("CHECKED BY",cellFont));
				c6.setBorder(Rectangle.NO_BORDER);
				footer.addCell(c6);
				
				c6 = new Cell(new Phrase("ASST. REGISTRAR (ACAD.) \n FOR REGISTRAR",cellFont));
				c6.setBorder(Rectangle.NO_BORDER);
				footer.addCell(c6);				
												
				document.add(footer);		
//				 String imageUrl = "file:///D://dei_logo.jpg";
//				 Image image = Image.getInstance(new URL(imageUrl));
//				 	image.scaleAbsolute(150f, 150f);
//				 		document.add(image);
				Phrase phrase = new Phrase();
				phrase.add(footer);
				HeaderFooter foot = new HeaderFooter(phrase, false);
				foot.setAlignment(Element.ALIGN_CENTER);				
				document.setFooter(foot);
				document.close();	
		}catch(IOException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			logger.error("inside generatePdf"+ e);
			return false;			
		}
		catch(BadElementException be){
			be.printStackTrace();
			System.out.println(be.getMessage());
			logger.error("inside generatePdf "+be);
			return false;
		}
		catch(DocumentException de){
			de.printStackTrace();
			System.out.println(de.getMessage());
			logger.error("inside generatePdf "+de);
			return false;
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("inside generatePdf "+e);
			return false;
		}
		
		return true;
	}
	
	protected List<ProgressCardInfo> getPreviousPCKMarks(ProgressCardInfo pci){
		List<ProgressCardInfo> preProgramCourseKeyList;
		List<ProgressCardInfo> totalMarks=new ArrayList<ProgressCardInfo>();
		try{
		preProgramCourseKeyList = progressCardDao.getPreviousProgramCourseKey(pci);
		
			for(ProgressCardInfo ppck : preProgramCourseKeyList){
				totalMarks.addAll(progressCardDao.totalMarksDetails(ppck));
			}
		}
		catch (Exception e) {
			System.out.println("getPreviousPCKMarks "+e);
		}
		return totalMarks;
	}
	
	public void waterMark(String rollNo)
	{
		try 
	    {
	      PdfReader reader = new PdfReader("E:"+sep+"ProgressCard_"+rollNo+".pdf");
	      int n = reader.getNumberOfPages();
	      System.out.println("in water"+n);
	      // Create a stamper that will copy the document to a new file
	      PdfStamper stamp = new PdfStamper(reader, 
	        new FileOutputStream("D:"+sep+"ProgressCard1_"+rollNo+".pdf"));
	      int i = 1;
	      PdfContentByte under;
	      PdfContentByte over;

	      String imageUrl = "file:///D://dei_logo.jpg";
			 Image image = Image.getInstance(new URL(imageUrl));
			
	      BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, 
	        BaseFont.WINANSI, BaseFont.EMBEDDED);

	      image.setAbsolutePosition(200, 700);

	      while (i < n) 
	      {
	        // Watermark under the existing page
	        under = stamp.getUnderContent(i);
	        under.addImage(image);
	      
	        // Text over the existing page
	        over = stamp.getOverContent(i);
	        over.beginText();
	        over.setFontAndSize(bf, 18);
	        over.showText("page " + i);
	        over.endText();
	    
	        i++;
	      }	    
	      stamp.close();
	    }
	    catch (Exception de) 
	    {}
	}
	
	 // Parallel arrays used in the conversion process.
    private static final String[] RCODE = {"M", "CM", "D", "CD", "C", "XC", "L",
                                           "XL", "X", "IX", "V", "IV", "I"};
    private static final int[]    BVAL  = {1000, 900, 500, 400,  100,   90,  50,
                                           40,   10,    9,   5,   4,    1};
    
    //=========================================================== binaryToRoman
    public String binaryToRoman(int binary) {
        if (binary <= 0 || binary >= 4000) {
            throw new NumberFormatException("Value outside roman numeral range.");
        }
        String roman = "";         // Roman notation will be accumualated here.
        
        // Loop from biggest value to smallest, successively subtracting,
        // from the binary value while adding to the roman representation.
        for (int i = 0; i < RCODE.length; i++) {
            while (binary >= BVAL[i]) {
                binary -= BVAL[i];
                roman  += RCODE[i];
            }
        }
        return roman;
    }      
    public List<ProgressCardInfo> sortedListBySemesterSequence(List<ProgressCardInfo> listToSort)
    {
    	List<ProgressCardInfo> tempList = new ArrayList<ProgressCardInfo>();	
    	for(int i=0;i<listToSort.size();i++)
    	{    		
    		for(int j=i;j<listToSort.size();j++)
    		{
    			if(Integer.parseInt(listToSort.get(i).getSemesterSequence()) > Integer.parseInt(listToSort.get(j).getSemesterSequence()))
    			{
    				System.out.println("greater for "+listToSort.get(i).getSemesterSequence()+" > "+listToSort.get(j).getSemesterSequence());
    				tempList.add(listToSort.get(i));
    				listToSort.set(i,listToSort.get(j));
    				listToSort.set(j,tempList.get(0));
    				tempList = new ArrayList<ProgressCardInfo>();
    			}
    		}
    	}
    	System.out.println("sorted");
    	for(int i=0;i<listToSort.size();i++)
    	{
    		System.out.println(listToSort.get(i).getSemesterSequence());
    	}
    	return listToSort;
    }    
    public static String calcaulateGrade(float marks)
    {
    	if(marks >= 70)
    	{
    		return "A";
    	}
    	else if(marks >= 60 && marks <= 70)
    	{
    		return "B";
    	}
    	else if(marks >= 50 && marks < 60)
    	{
    		return "C";
    	}
    	else return "D";
    }
    
    public static String calculateDivision(float marks)
    {
    	if(marks >= 60)
    	{
    		return "FIRST";    	
    	}
    	else if(marks>=50 && marks < 60)
    	{
    		return "SECOND";
    	}
    	else if(marks >=45 && marks<50)
    	{
    		return "THIRD";
    	}
    	else return "fail";    		
    }
	
	
}
