package in.ac.dei.edrp.cms.controller.reportgeneration;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;
import in.ac.dei.edrp.cms.dao.report.ReportDao;
import in.ac.dei.edrp.cms.dao.reportgeneration.UnsatisfactoryPerformanceDao;
import in.ac.dei.edrp.cms.domain.report.ReportLogBean;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentInfoGetter;
import in.ac.dei.edrp.cms.domain.university.UniversityMasterInfoGetter;
import in.ac.dei.edrp.cms.utility.ReportPath;
import in.ac.dei.edrp.cms.utility.ReportPathBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;






/**
 * This controller is designed for setting & retrieving
 * the unsatisfatory performance of student in selected program
 * @author Ashish Mohan
 * @date 13 Dec 2011
 * @version 1.0
 */
public class UnsatisfactoryPerformanceController extends MultiActionController{
	
	/** Creating object of Logger for log Maintenance */
	private static Logger logObj = Logger
			.getLogger(UnsatisfactoryPerformanceController.class);
	
private UnsatisfactoryPerformanceDao unsatisfactoryPerformanceDao;
	
public void setUnsatisfactoryPerformanceDao(
		UnsatisfactoryPerformanceDao unsatisfactoryPerformanceDao) {
	this.unsatisfactoryPerformanceDao = unsatisfactoryPerformanceDao;
}
private MeritListCPPdf meritListCPPdf;
public void setMeritListCPPdf(MeritListCPPdf meritListCPPdf) {
	this.meritListCPPdf = meritListCPPdf;
}

	/** creating object of ReportDao interface */
	private ReportDao reportDao;
	/** defining setter method for object of interface ReportDao */
	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
}

/**
 * Method to get the general info and remedial course list of student
 * @param request
 * @param response
 * @return ModelAndView
 * @throws Exception
 */
public ModelAndView unsatisfactoryPerformance(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession(true);
		String universityId = (String) session.getAttribute("universityId");
		String userId = (String)session.getAttribute("userId");
		String entityId = request.getParameter("entityId");
		String programId = request.getParameter("programId");
		String branchId =  request.getParameter("branchId");
		String specializationId = request.getParameter("specializationId");
		String semesterStartDate = request.getParameter("semesterStartDate");
		String semesterEndDate = request.getParameter("semesterEndDate");
//		String semesterId = request.getParameter("semesterId");
		String semesterId = request.getParameter("semesterCode");
		String sequenceNumber = request.getParameter("semesterSequence");
		StudentInfoGetter studentInfoGetter = new StudentInfoGetter();
		studentInfoGetter.setUniversityId(universityId);
		studentInfoGetter.setUserId(userId);
		studentInfoGetter.setEntityId(entityId);
		studentInfoGetter.setProgramId(programId);
		studentInfoGetter.setBranchId(branchId);
		studentInfoGetter.setSemesterCode(semesterId);
		studentInfoGetter.setSemesterStartDate(semesterStartDate);
		studentInfoGetter.setSemesterEndDate(semesterEndDate);
		studentInfoGetter.setSpecializationId(specializationId);
		studentInfoGetter.setSequenceNumber(sequenceNumber);
	
		List<StudentInfoGetter> checkList = new ArrayList<StudentInfoGetter>();				
		checkList = unsatisfactoryPerformanceDao.getInfoForStudent(studentInfoGetter);
		int count=checkList.size();		
		//to check that student are found or not
		if(count==0){
			String message = "true-No Student with Unsatisfactory performance";
			return new ModelAndView("activitymaster/SubmitSuccesful", "message", message);
//			return new ModelAndView("systemtabletwo/countInfo", "count", count);
		}
		else{
			try{
				return new ModelAndView("unsatisfactoryPerformance","checkList",checkList);
			}catch(Exception e){
				System.out.println("inside the exception "+e);
				return new ModelAndView("activitymaster/SubmitSuccesful", "message", "true");
			}
		}
	}
/**
 * Method to get the general info and remedial course list of student
 * @param request
 * @param response
 * @return ModelAndView
 * @throws Exception
 */
public ModelAndView meritListBasedOnCP(HttpServletRequest request,HttpServletResponse response){
	String message="";
	try{
//		MeritListCPPdf meritListCPPdf = new MeritListCPPdf();
		HttpSession session = request.getSession(true);
		String universityId = (String) session.getAttribute("universityId");
		String userId = (String)session.getAttribute("userId");
		String entityId = request.getParameter("entityId");
		String programId = request.getParameter("programId");
		String branchId =  request.getParameter("branchId");
		String specializationId = request.getParameter("specializationId");
//		String sessionEndDate=request.getParameter("session");
		String sessionEndDate=request.getParameter("toSession");
		
		StudentInfoGetter studentInfoGetter = new StudentInfoGetter();
		studentInfoGetter.setUniversityId(universityId);
		studentInfoGetter.setUserId(userId);
		studentInfoGetter.setEntityId(entityId);
		studentInfoGetter.setProgramId(programId);
		studentInfoGetter.setBranchId(branchId);		
		studentInfoGetter.setSpecializationId(specializationId);
		studentInfoGetter.setSessionEndDate(sessionEndDate);
		
		String sep = System.getProperty("file.separator");
		//*********nupur code *******************
		ReportPathBean reportPathBean = new ReportPathBean(session.getAttribute("universityId").toString(),request.getParameter("entityId"),
				request.getParameter("programId"),request.getParameter("branchId"),request.getParameter("specializationId"),
				request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
				request.getParameter("enrollmentNumber"),request.getParameter("rollNumber"),request.getParameter("reportCode"),
				request.getParameter("reportType"),request.getParameter("fromSession"),request.getParameter("toSession"),request.getParameter("semesterType"));
		
		String reportPath = ReportPath.getPath(reportPathBean);				
		System.out.println("here in controller of consolidated chart after path generation "+reportPath);	
		String path = this.getServletContext().getRealPath(File.separator);
		System.out.println("path "+path);
		path=path+reportPath;		
		File file = new File(path);
		file.mkdirs();
		System.out.println("after file making ");
		request.setAttribute("path", path);
		//***************************************	

		List<StudentInfoGetter> checkList = new ArrayList<StudentInfoGetter>();				
		checkList = unsatisfactoryPerformanceDao.getInfoOfStudentForMeritListCP(studentInfoGetter);
		int count=checkList.size();		
		//to check that student are found or not
		if(count==0){
			message = "false-No Records to generate the Report";			
		}
		else{
			System.out.println("chceck list size "+checkList.size());
			String  processStatus = meritListCPPdf.generateMeritList(request, checkList);
			 if(processStatus=="true"){
				message = "true";
				}
				else{
					message = "false-There is some error in PDF Generation kindly view the error log for more information";
				}			
//			return new ModelAndView("MeritListCP","checkList",checkList);
		}
	}catch(Exception e){
		e.printStackTrace();
		message = "false-There is some error in PDF Generation kindly view the error log for more information";
	}
		 return new ModelAndView("activitymaster/SubmitSuccesful", "message", message);
	}
	
	

/**
 * Method to get the general info and total marks of student according to selected group
 * @param request
 * @param response
 * @return ModelAndView
 * @throws Exception
 */
public ModelAndView meritListBasedOnGroup(HttpServletRequest request,HttpServletResponse response){
	HttpSession session = request.getSession(true);
	String universityId = (String) session.getAttribute("universityId");
	String userId = (String)session.getAttribute("userId");
	String entityId = request.getParameter("entityId");
	String programId = request.getParameter("programId");
	String branchId =  request.getParameter("branchId");
	String specializationId = request.getParameter("specializationId");
	String semesterStartDate = request.getParameter("semesterStartDate");
	String semesterEndDate = request.getParameter("semesterEndDate");
	String semesterId = request.getParameter("semesterId");
	String sequenceNumber = request.getParameter("semesterSequence");
	StudentInfoGetter studentInfoGetter = new StudentInfoGetter();
	studentInfoGetter.setUniversityId(universityId);
	studentInfoGetter.setUserId(userId);
	studentInfoGetter.setEntityId(entityId);
	studentInfoGetter.setProgramId(programId);
	studentInfoGetter.setBranchId(branchId);
	studentInfoGetter.setSemesterCode(semesterId);
	studentInfoGetter.setSemesterStartDate(semesterStartDate);
	studentInfoGetter.setSemesterEndDate(semesterEndDate);
	studentInfoGetter.setSpecializationId(specializationId);
	studentInfoGetter.setSequenceNumber(sequenceNumber);
	List<String> courseCodeList= new ArrayList<String>();
	StringTokenizer st=new StringTokenizer(request.getParameter("combination"),"+");
	
	while(st.hasMoreTokens()){
		
		courseCodeList.add(st.nextToken());
	}
	
		studentInfoGetter.setCourseCodeList(courseCodeList);
		List<StudentInfoGetter> checkList = new ArrayList<StudentInfoGetter>();		
		
		checkList = unsatisfactoryPerformanceDao.getInfoOfStudentForMeritListGroup(studentInfoGetter);
		int count=checkList.size();
		
		//to check that student are found or not
		if(count==0){
			return new ModelAndView("systemtabletwo/countInfo", "count", count);
		}
		else
		{
			return new ModelAndView("MeritListGroup","checkList",checkList);
		}
	}
	

/**
 * Method to get the course groups  of selected combination of pck
 * @param request
 * @param response
 * @return ModelAndView
 * @throws Exception
 */
public ModelAndView getCourseGroupList(HttpServletRequest request,HttpServletResponse response){
	HttpSession session = request.getSession(true);
	String universityId = (String) session.getAttribute("universityId");
	String userId = (String)session.getAttribute("userId");
	String entityId = request.getParameter("entityId");
	String programId = request.getParameter("programId");
	String branchId =  request.getParameter("branchId");
	String specializationId = request.getParameter("specializationId");
	String semesterStartDate = request.getParameter("semesterStartDate");
	String semesterEndDate = request.getParameter("semesterEndDate");
	String semesterId = request.getParameter("semesterId");
	String sequenceNumber = request.getParameter("semesterSequence");
	StudentInfoGetter studentInfoGetter = new StudentInfoGetter();
	studentInfoGetter.setUniversityId(universityId);
	studentInfoGetter.setUserId(userId);
	studentInfoGetter.setEntityId(entityId);
	studentInfoGetter.setProgramId(programId);
	studentInfoGetter.setBranchId(branchId);
	studentInfoGetter.setSemesterCode(semesterId);
	studentInfoGetter.setSemesterStartDate(semesterStartDate);
	studentInfoGetter.setSemesterEndDate(semesterEndDate);
	studentInfoGetter.setSpecializationId(specializationId);
	studentInfoGetter.setSequenceNumber(sequenceNumber);
	
	List<UniversityMasterInfoGetter> resultObject = unsatisfactoryPerformanceDao.getCourseGroup(studentInfoGetter);

	return new ModelAndView("UniversityRolesSetup/UniversityRoles", "resultObject",
			resultObject);
		
	}
	
	

/**
 * Method to set the list of unapproved award blank for university
 * @param request
 * @param response
 * @return ModelAndView
 * @throws Exception
 * @author Ashish Mohan
 */
public ModelAndView setUnapprovedCourseList(HttpServletRequest request,HttpServletResponse response){
	HttpSession session = request.getSession(true);
	String universityId = (String) session.getAttribute("universityId");
	String userId = (String)session.getAttribute("userId");
	String sessionStartDate = request.getParameter("fromSession");
	String sessionEndDate = request.getParameter("toSession");
	StudentInfoGetter studentInfoGetter = new StudentInfoGetter();
	studentInfoGetter.setUniversityCode(universityId);
	studentInfoGetter.setUserId(userId);
	studentInfoGetter.setSessionStartDate(sessionStartDate);
	studentInfoGetter.setSessionEndDate(sessionEndDate);
	studentInfoGetter.setFalg("data");
	
	List<StudentInfoGetter> resultObject = unsatisfactoryPerformanceDao.getListOfUnapprovedCourses(studentInfoGetter);
	
	//for insert in a  mysql table
	StudentInfoGetter input = new StudentInfoGetter();
	input.setFalg("delete");
	input.setUniversityCode(universityId);
	input.setUserId(userId);
	input.setSessionStartDate(sessionStartDate);
	input.setSessionEndDate(sessionEndDate);
	
	unsatisfactoryPerformanceDao.getListOfUnapprovedCourses(input);
		
	input.setFalg("insert");	
	for (int i=0;i<resultObject.size();i++){
		input.setEntityId(resultObject.get(i).getEntityId());
		input.setProgramCourseKey(resultObject.get(i).getProgramCourseKey());
		input.setCourseCode(resultObject.get(i).getCourseCode());
		input.setVerified(resultObject.get(i).getVerified());//for display type
		input.setComponentCode(resultObject.get(i).getComponentCode()); //for employee
		input.setComponentDescription(resultObject.get(i).getComponentDescription());//for delay days
		input.setSemesterStartDate(resultObject.get(i).getSemesterStartDate());
		input.setSemesterEndDate(resultObject.get(i).getSemesterEndDate());
		unsatisfactoryPerformanceDao.getListOfUnapprovedCourses(input);
	}
	
	// calling method to generate report
	String result=generateUnapprovedList(request,session,studentInfoGetter);
	
	return new ModelAndView("UniversityRolesSetup/UniversityRoles", "resultObject",
			result);
		
	}
	
	

/**
 * Method to generate list of Courses whose marks is not released by dean Report
 * @param studentInfoGetter 
 * @param HttpServletRequest request
 * @param HttpSession session
 * @return String status 
*/
private String generateUnapprovedList(HttpServletRequest request, HttpSession session, StudentInfoGetter studentInfoGetter) {
	File file;
	String logResult="";
	int reportGenerated=0;
	String status = "false";
	String reportSession = studentInfoGetter.getSessionStartDate().substring(0, 4) + "-" + studentInfoGetter.getSessionEndDate().substring(0, 4);
	String reportPath=null;
	String sep=System.getProperty("file.separator");
	String filePath = this.getServletContext().getRealPath("/");
	Document document=new Document(PageSize.A4.rotate());
	try{
		
		//calling function to get unique Entity for report
		studentInfoGetter.setFalg("entity");
		List<StudentInfoGetter> uniqueEntityList = unsatisfactoryPerformanceDao.getListOfUnapprovedCourses(studentInfoGetter);

		//check size of data
		if(uniqueEntityList.size()==0){
			//return status of report
			return "false-No Unapproved Award Blank Found for Selected Session";
		}
		
		//**********************Making directory for storing the file**************************	
		ReportPathBean reportPathBean =new ReportPathBean();
			reportPathBean = new ReportPathBean(session.getAttribute("universityId").toString(),request.getParameter("entityId"),
			request.getParameter("programId"),request.getParameter("branchId"),request.getParameter("specializationId"),
			request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
			request.getParameter("enrollmentNumber"),request.getParameter("rollNumber"),request.getParameter("reportCode"),
			request.getParameter("reportType"),request.getParameter("fromSession"),request.getParameter("toSession"),request.getParameter("semesterType"));
			reportPath=ReportPath.getPath(reportPathBean);
		//*************************************************************************************	
		System.out.println("here in controller after path generation "+reportPath);

		filePath=filePath+reportPath;
		file = new File(filePath);
		file.mkdirs();
		PdfWriter.getInstance(document,new FileOutputStream(filePath+sep+request.getParameter("reportDescription")+".pdf"));
	
		Phrase headerPhrase = new Phrase("LIST OF COURSES NOT RELEASED BY DEAN -- ( "+ reportSession+" )"+"                                   RUN DATE - "+Calendar.getInstance().getTime(),
			FontFactory.getFont(FontFactory.HELVETICA, 13, Font.BOLD,new Color(0, 0, 0)));


	
		//set font of header line in table
		Font headerFont=FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD,new Color(0, 0, 0));
		
		//setting header
		HeaderFooter header = new HeaderFooter(headerPhrase, false);
		header.setBorder(0);
		header.setAlignment(Element.ALIGN_LEFT);
		
		//setting footer
		HeaderFooter footer = new HeaderFooter(new Phrase("Page "),true);
		footer.setBorder(0);
		footer.setAlignment(Element.ALIGN_RIGHT);
	
		//setting document and opening it
		document.setHeader(header);
		document.setFooter(footer);
		document.open();
	
		//loop to iterate each entity
		for (int e=0;e<uniqueEntityList.size();e++){
			
			PdfPTable contentTable = new PdfPTable(new float[] {3,8});
			contentTable.setWidthPercentage(100);
			contentTable.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			
			int i=0;
			
			//adding entity
			PdfPCell cell = new PdfPCell(new Phrase("FAC "+(e+1)+" -  "+uniqueEntityList.get(e).getEntityName().toUpperCase(),headerFont));
			cell.setBorderWidthTop(0);
			cell.setBorderWidthLeft(0);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthBottom(1);
			cell.setColspan(2);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			contentTable.addCell(cell);
		
			//calling function to get unique Program for report
			studentInfoGetter.setFalg("program");
			studentInfoGetter.setEntityId(uniqueEntityList.get(e).getEntityId());
			List<StudentInfoGetter> uniqueProgramList = unsatisfactoryPerformanceDao.getListOfUnapprovedCourses(studentInfoGetter);

			//adding header line of table when entity change
			if(i==0){
				
				cell = new PdfPCell(new Phrase("Prg-Br-Spl-Sem",headerFont));
				cell.setBorderWidthTop(0);
				cell.setBorderWidthLeft(0);
				cell.setBorderWidthRight(0);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				contentTable.addCell(cell);
	
				cell = new PdfPCell(new Phrase("           Course Code                  Approval Last Date                 Employee Code                       Delay Days",headerFont));
				cell.setBorderWidthTop(0);
				cell.setBorderWidthLeft(0);
				cell.setBorderWidthRight(0);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				contentTable.addCell(cell);
	
			}

			//for adding space line in table for each program
			addSpace(contentTable,2,1);
		
			//setting no. of row of table as header
			contentTable.setHeaderRows(4);
	

			studentInfoGetter.setFalg("course");
			for(;i<uniqueProgramList.size();i++){
				
				//setting bean property
				studentInfoGetter.setEntityName(uniqueEntityList.get(e).getEntityName());
				studentInfoGetter.setProgramName(uniqueProgramList.get(i).getProgramName());
		
				//adding program in a table
				cell = new PdfPCell(new Phrase((i+1)+". "+uniqueProgramList.get(i).getProgramName()));
				cell.setBorderWidth(0);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				contentTable.addCell(cell);
				
				//adding two empty cell to content table
				for (int w=0;w<2;w++){
				PdfPCell cell1 = new PdfPCell(new Phrase(" "));
				cell1.setBorderWidth(0);
				contentTable.addCell(cell1);
				}
		
				//getting courses for each entity-program
				List<StudentInfoGetter> courseList=null;
				courseList = unsatisfactoryPerformanceDao.getListOfUnapprovedCourses(studentInfoGetter);
		
				PdfPTable courseTable = new PdfPTable(new float[] {2,2,2,2});
				courseTable.setWidthPercentage(100);
				courseTable.setHorizontalAlignment(Element.ALIGN_CENTER);
		
				for(int j=0;j<courseList.size();j++){
			
					PdfPCell cell2 = new PdfPCell(new Phrase(courseList.get(j).getCourseCode()));
					cell2.setBorderWidth(0);
					cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
					courseTable.addCell(cell2);
			
					cell2 = new PdfPCell(new Phrase(courseList.get(j).getVerified()));
					cell2.setBorderWidth(0);
					cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
					courseTable.addCell(cell2);
			
					cell2 = new PdfPCell(new Phrase(courseList.get(j).getComponentCode()));
					cell2.setBorderWidth(0);
					cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
					courseTable.addCell(cell2);
			
					cell2 = new PdfPCell(new Phrase(courseList.get(j).getComponentDescription()));
					cell2.setBorderWidth(0);
					cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
					courseTable.addCell(cell2);
				}
		
				//adding course table to content table cell
				PdfPCell cell1 = new PdfPCell(courseTable);
				cell1.setBorderWidth(0);
				contentTable.addCell(cell1);
				
				//adding two space line before each entity
				addSpace(contentTable, 2,2);
				
				//adding content table in document
				document.add(contentTable);
		
			}
			
			
			
		}
	
		//closing the document
		document.close();
		
		//setting status true i.e.report generated
		status = "true";
	
	} catch (Exception e) {
		status = "false";			
		ReportLogBean reportErrorBean = new ReportLogBean(session.getAttribute("universityId").toString(),request.getParameter("programId"),
			request.getParameter("branchId"),request.getParameter("specializationId"),request.getParameter("entityId"),
			request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
			request.getParameter("reportCode"),"",request.getParameter("rollNumber"),
			request.getParameter("fromSession").substring(0, 4)+"-"+request.getParameter("toSession").substring(2,4));
		logResult = reportDao.insertReportErrorLog(reportErrorBean);						
		System.out.println("nupur : some exception in report generation");
		System.out.println("exception :"+e);			
		logObj.error(e.getMessage());
	}
	try{
		if(status.equalsIgnoreCase("false")){
		reportGenerated = reportGenerated+1;
		ReportLogBean reportControlBean = new ReportLogBean(session.getAttribute("universityId").toString(),request.getParameter("entityId"),
				request.getParameter("programId"),request.getParameter("branchId"),request.getParameter("specializationId"),
				request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
				request.getParameter("reportCode"),String.valueOf(reportGenerated),"No",
				request.getParameter("fromSession"),request.getParameter("toSession"),request.getParameter("semesterType"),
				session.getAttribute("userName").toString());			
		System.out.println("before control log insert");		
		logResult = reportDao.insertReportLog(reportControlBean);
		System.out.println("report control log result "+logResult);
		}
	}catch(Exception e){
		System.out.println("some exception in log entry "+e);
	}
	
	//return status of report
	return status;
}



/**
 * Method for adding j blank line in i column table
 * @param PdfPTable table
 * @param Number of column in table i
 * @param Number of blank lines j  
 * @return void
 * @author Ashish Mohan 
*/
private void addSpace(PdfPTable table,int i,int j){
	for(int loop=0;loop<j;loop++){
		PdfPCell spaceCell=new PdfPCell(new Phrase(" "));
		spaceCell.setColspan(i);
		spaceCell.setBorderWidth(0);
		table.addCell(spaceCell);
	}
}


/**
 * Method to be called from client side which then calls generating report method
 * 
 * @author Ashish Mohan
 * @param request
 * @param response
 * @return ModelAndView containing info(String message)
 * @date 16 May 2012
 */
 public ModelAndView collationDifferences(HttpServletRequest request,
	HttpServletResponse response) throws Exception {		
	HttpSession session = request.getSession(true);
	String universityId=(String) session.getAttribute("universityId");
	if (universityId == null) {
		return new ModelAndView("general/SessionInactive",
				"sessionInactive", true);
	}
	StudentInfoGetter input=new StudentInfoGetter();	
	input.setUniversityId(universityId);
	input.setSessionStartDate(request.getParameter("fromSession"));
	input.setSessionEndDate(request.getParameter("toSession"));
	input.setEntityId(request.getParameter("entityId"));
	input.setProgramId(request.getParameter("programId"));
	input.setBranchId(request.getParameter("branchId"));
	input.setSpecializationId(request.getParameter("specializationId"));
	input.setSemesterCode(request.getParameter("semesterCode"));
	input.setSemesterStartDate(request.getParameter("semesterStartDate"));
	input.setSemesterEndDate(request.getParameter("semesterEndDate"));

	
	String message=generateCollationDifferenceReport(request,session,input);
	return new ModelAndView("activitymaster/SubmitSuccesful", "message", message);
 }




/**
 * Method to generate award blank collation difference report
 * @param HttpServletRequest request
 * @param HttpSession session
 * @param Object of Student Info Getter input
 * @return String message
 * @author Ashish Mohan 
*/

public String generateCollationDifferenceReport(HttpServletRequest request,HttpSession session,StudentInfoGetter input){
	String status = "false";
	String logResult="";		
	int reportGenerated = 0;
	
	String reportSession = input.getSessionStartDate().substring(0, 4) + "-" + input.getSessionEndDate().substring(0, 4);
	
	try {
		
		//calling method to get data for the pdf
		List<StudentInfoGetter> dataList=unsatisfactoryPerformanceDao.getCollationDifferences(input);
		
		//check size of data
		if(dataList.size()==0){
			return "false-No Record Found For This Combination";
		}
		
		

		String sep = System.getProperty("file.separator");
		
		//********* generating report path ashish code *******************
		ReportPathBean reportPathBean = new ReportPathBean(session.getAttribute("universityId").toString(),request.getParameter("entityId"),
				request.getParameter("programId"),request.getParameter("branchId"),request.getParameter("specializationId"),
				request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
				request.getParameter("enrollmentNumber"),request.getParameter("rollNumber"),request.getParameter("reportCode"),
				request.getParameter("reportType"),request.getParameter("fromSession"),request.getParameter("toSession"),request.getParameter("semesterType"));
		
		String reportPath = ReportPath.getPath(reportPathBean);				
		System.out.println("here in controller of component chart after path generation "+reportPath);
		String filePath = this.getServletContext().getRealPath("/");
		filePath=filePath+reportPath;
		File file = new File(filePath);
		file.mkdirs();
		//***************************************
		
		Document document = new Document(PageSize.A4.rotate());

		

		PdfWriter.getInstance(document, new FileOutputStream(filePath+sep+request.getParameter("reportDescription")+".pdf"));

		String branch;
		String spec;
		if(!request.getParameter("branchName").equalsIgnoreCase("NONE")){branch="-"+request.getParameter("branchName").toUpperCase();}else{branch="";}
		if(!request.getParameter("specializationName").equalsIgnoreCase("NONE")){spec="-"+request.getParameter("specializationName").toUpperCase();}else{spec="";}
		
		Phrase headerPhrase = new Phrase(
				"LIST OF DIFFERENCES AFTER COLLATION OF AWARD BLANK"+"\n"+request.getParameter("entityName").toUpperCase()+
				" ( "+request.getParameter("programName").toUpperCase()+branch+spec+" )"+" - "
						+ reportSession+"\n"+input.getSemesterCode()+"("+input.getSemesterStartDate()+"-"+input.getSemesterEndDate()+")",
				FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD,
						new Color(0, 0, 0)));

		PdfPTable contentTable = new PdfPTable(new float[] {2,2,2,2,2,2,2});
		contentTable.setWidthPercentage(100);
	contentTable.setHorizontalAlignment(Element.ALIGN_CENTER);
		
		//set font of header line in table
		Font headerFont=new Font(Font.BOLD);
		
		//adding header line of table
		
		PdfPCell cell = new PdfPCell(new Phrase("Course Code",headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		contentTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Roll Number",headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		contentTable.addCell(cell);

		cell = new PdfPCell(new Phrase("Marks Type",headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		contentTable.addCell(cell);

		cell = new PdfPCell(new Phrase("Award Blank Marks",headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		contentTable.addCell(cell);

		cell = new PdfPCell(new Phrase("User",headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		contentTable.addCell(cell);

		cell = new PdfPCell(new Phrase("Collation Marks",headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		contentTable.addCell(cell);

		cell = new PdfPCell(new Phrase("User",headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		contentTable.addCell(cell);
		
		//setting no. of row of table as header
		contentTable.setHeaderRows(1);
			
		//adding all data in pdf
		for (int j = 0; j <dataList.size(); j++) {
			
				cell = new PdfPCell(new Phrase(dataList.get(j).getCourseCode()));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				contentTable.addCell(cell);
				
				cell = new PdfPCell(new Phrase(dataList.get(j).getRollNumber()));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				contentTable.addCell(cell);
				
				cell = new PdfPCell(new Phrase(dataList.get(j).getComponentDescription()));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				contentTable.addCell(cell);
				
				cell = new PdfPCell(new Phrase(dataList.get(j).getFalg())); //for main marks
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				contentTable.addCell(cell);
				
				cell = new PdfPCell(new Phrase(dataList.get(j).getCreatorId())); //for award blank user
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				contentTable.addCell(cell);
				
				cell = new PdfPCell(new Phrase(dataList.get(j).getFalg1())); //for collation marks
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				contentTable.addCell(cell);
				
				cell = new PdfPCell(new Phrase(dataList.get(j).getModifierId()));//for collation award blank user
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				contentTable.addCell(cell);

		}
		
		
		HeaderFooter header = new HeaderFooter(headerPhrase, false);
		header.setBorder(0);
		header.setAlignment(Element.ALIGN_CENTER);
		
		HeaderFooter footer = new HeaderFooter(new Phrase("Page "),true);
		footer.setBorder(0);
		footer.setAlignment(Element.ALIGN_RIGHT);
		
		document.setHeader(header);
		document.setFooter(footer);
		document.open();
		document.add(contentTable);
		document.close();
		status = "true";
		
	} catch (Exception e) {
		status = "false";			
		ReportLogBean reportErrorBean = new ReportLogBean(session.getAttribute("universityId").toString(),request.getParameter("programId"),
				request.getParameter("branchId"),request.getParameter("specializationId"),request.getParameter("entityId"),
				request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
				request.getParameter("reportCode"),"",request.getParameter("rollNumber"),
				request.getParameter("fromSession").substring(0, 4)+"-"+request.getParameter("toSession").substring(2,4));
		logResult = reportDao.insertReportErrorLog(reportErrorBean);						
		System.out.println("nupur : some exception in report generation");
		System.out.println("exception :"+e);			
		logObj.error(e.getMessage());
	}
	try{
		if(status.equalsIgnoreCase("false")){
			reportGenerated = reportGenerated+1;
			ReportLogBean reportControlBean = new ReportLogBean(session.getAttribute("universityId").toString(),request.getParameter("entityId"),
					request.getParameter("programId"),request.getParameter("branchId"),request.getParameter("specializationId"),
					request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
					request.getParameter("reportCode"),String.valueOf(reportGenerated),"No",
					request.getParameter("fromSession"),request.getParameter("toSession"),request.getParameter("semesterType"),
					session.getAttribute("userName").toString());			
			System.out.println("before control log insert");		
			logResult = reportDao.insertReportLog(reportControlBean);
			System.out.println("report control log result "+logResult);
	}
	}catch(Exception e){
		System.out.println("some exception in log entry "+e);
	}
	return status;
}



}




