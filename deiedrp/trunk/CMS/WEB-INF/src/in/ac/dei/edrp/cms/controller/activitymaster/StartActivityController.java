/**
 * @(#) StartActivityController.java
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

package in.ac.dei.edrp.cms.controller.activitymaster;


import in.ac.dei.edrp.cms.constants.CRConstant;
import in.ac.dei.edrp.cms.dao.activitymaster.StartActivityDao;
import in.ac.dei.edrp.cms.domain.activitymaster.CountProcessRecorList;
import in.ac.dei.edrp.cms.domain.activitymaster.StartActivityBean;
import in.ac.dei.edrp.cms.domain.resultprocessing.UnProcessedStduent;
import in.ac.dei.edrp.cms.utility.RegistrationFunction;
import in.ac.dei.edrp.cms.daoimpl.employee.sendmail;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class StartActivityController extends MultiActionController {

	public static final Logger logger = Logger
			.getLogger(StartActivityController.class);

	private StartActivityDao startActivityDao;

	public void setStartActivityDao(StartActivityDao startActivityDao) {
		this.startActivityDao = startActivityDao;
	}

	public ModelAndView getProcesses(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(true);

		String universityId = (String) session.getAttribute("universityId");
		String userId = (String) session.getAttribute("userId");
		// System.out.println("Coming in process");
		StartActivityBean startActivityBean = new StartActivityBean();
		startActivityBean.setUniversityId(universityId);
		startActivityBean.setGroupCode(CRConstant.PROCESS_MASTER);
		// Since code for process master is attached, so process id will be in
		// list
		List<StartActivityBean> processList = startActivityDao
				.getProcessList(startActivityBean);
		// for(StartActivityBean sta:processList){
		// System.out.println("Coming here"+sta.getComponentDescription());
		// }
		return new ModelAndView("activitymaster/ComponentList", "itemList",
				processList);

	}

	public ModelAndView getSemesterList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(true);

		String universityId = (String) session.getAttribute("universityId");
		String userId = (String) session.getAttribute("userId");

		StartActivityBean startActivityBean = new StartActivityBean();
		startActivityBean.setUniversityId(universityId);
		startActivityBean.setGroupCode(CRConstant.SEMESTER_GROUP_CODE);

		// Since code for process master is attached, so semester codes will be
		// in list
		List<StartActivityBean> semesterList = startActivityDao
				.getProcessList(startActivityBean);

		return new ModelAndView("activitymaster/ComponentList", "itemList",
				semesterList);

	}

	/**
	 * It returns data for process grid list of start activity screen
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView processList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RegistrationFunction registrationFunction = new RegistrationFunction();

		HttpSession session = request.getSession(true);

		String universityId = (String) session.getAttribute("universityId");
		String userId = (String) session.getAttribute("userId");

		String semesterStartDate = "";
		String semesterEndDate = "";

		// programProcessList holds processGridList values for client side
		List<StartActivityBean> programProcessList = new ArrayList<StartActivityBean>();
		// startActivityKey will be used to get program course key
		// if branch is null, then keys for all branches and specialization for
		// selected program will be in list
		// if only specialization is null, then keys for all specialization for
		// selected program and branches will be in list
		StartActivityBean startActivityKey = new StartActivityBean(
				(String) request.getParameter("programId"), (String) request
						.getParameter("branchId"), (String) request
						.getParameter("specializationId"), (String) request
						.getParameter("semesterId"));

		// setting university id
		startActivityKey.setUniversityId(universityId);

		// getting session start date and end date from university_master table
		// for current_status=1
		// setting values of session start date and session end date
		System.out.println((String) request.getParameter("specializationId")
				+ (String) request.getParameter("programId")
				+ (String) request.getParameter("branchId")
				+ (String) request.getParameter("semesterId")
				+ (String) request.getParameter("processId"));
		List<StartActivityBean> programCourseKey = startActivityDao
				.getProgramCourseKey(startActivityKey);
		for (StartActivityBean programCourseList : programCourseKey) {

			// Setting program course key in startActivityKey object
			startActivityKey.setProgramCourseKey(programCourseList
					.getProgramCourseKey());

			// setting session start and end date for program course key
			List<StartActivityBean> sessionList = startActivityDao
					.getSessionDate(universityId);
			for (StartActivityBean sessionListBean : sessionList) {
				startActivityKey.setSessionStartDate(sessionListBean
						.getSessionStartDate());
				startActivityKey.setSessionEndDate(sessionListBean
						.getSessionEndDate());
				System.out.println(sessionListBean.getSessionStartDate()
						+ sessionListBean.getSessionEndDate());
			}

			// Getting semester start and end date for program course key and
			// semester start date (using startActivityKey)
			List<StartActivityBean> semesterList = startActivityDao
					.getSemesterDate(startActivityKey);
			for (StartActivityBean semesterListBean : semesterList) {
				semesterStartDate = semesterListBean.getSemesterStartDate();
				semesterEndDate = semesterListBean.getSemesterEndDate();
				System.out.println(semesterStartDate + semesterEndDate);

			}

			// Object prepared to get list of program, branch and specialization
			// for given input
			StartActivityBean startActivityBean = new StartActivityBean(
					universityId, (String) request.getParameter("entityId"),
					programCourseList.getProgramCourseKey(), semesterStartDate,
					semesterEndDate, (String) request.getParameter("processId"));

			System.out.println(programCourseList.getProgramCourseKey());
			// Here list of branch and specialization for given program is now
			// set in startActivityBean2 object
			List<StartActivityBean> programCourseHeaderList = startActivityDao
					.getProcessGridList(startActivityBean);
			for (StartActivityBean processGridList : programCourseHeaderList) {
				// setting semesterStartDate and semesterEndDate and
				// programCourseKey in processGridList
				processGridList.setSemesterStartDate(semesterStartDate);
				processGridList.setSemesterEndDate(semesterEndDate);
				processGridList.setProgramCourseKey(programCourseList
						.getProgramCourseKey());
				// processGridList object is added into programProcessList
				programProcessList.add(processGridList);
			}

		}

		// System.out.println(programProcessList);
		return new ModelAndView("activitymaster/ProcessGrid", "prcessGridList",
				programProcessList);
	}

	/**
	 * It returns data for process grid list of start activity screen
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView activityList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("Coming in activity list");
		RegistrationFunction registrationFunction = new RegistrationFunction();
		HttpSession session = request.getSession(true);

		String universityId = (String) session.getAttribute("universityId");
		String userId = (String) session.getAttribute("userId");

		StartActivityBean startActivityBean = new StartActivityBean(
				universityId, (String) request.getParameter("entityId"),
				(String) request.getParameter("programCourseKey"),
				(String) request.getParameter("semesterStartDate"),
				(String) request.getParameter("semesterEndDate"),
				(String) request.getParameter("processId"));

		List<StartActivityBean> sessionList = startActivityDao
				.getSessionDate(universityId);
		for (StartActivityBean startBean : sessionList) {
			startActivityBean.setSessionStartDate(startBean
					.getSessionStartDate());
			startActivityBean.setSessionEndDate(startBean.getSessionEndDate());
		}

		// System.out.println((String)request.getParameter("entityId")+(String)request.getParameter("programCourseKey")+(String)request.getParameter("semesterStartDate")+(String)request.getParameter("semesterEndDate")+(String)request.getParameter("processId"));
		// List contains activities for given input and sent to clinet side
		List<StartActivityBean> processActivityList = startActivityDao
				.getProcessActivityGridList(startActivityBean);

		for (StartActivityBean star : processActivityList) {
			System.out.println(star.getActivityId());
		}
		return new ModelAndView("activitymaster/ProcessActivityList",
				"processActivityList", processActivityList);

	}

	public ModelAndView sessionDataList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(true);

		String universityId = (String) session.getAttribute("universityId");
		String ssd = (String) session.getAttribute("startDate");
		String sed = (String) session.getAttribute("endDate");

		StartActivityBean startActivityBean = new StartActivityBean();
		
		startActivityBean.setUniversityId(universityId);
		startActivityBean.setSessionStartDate(ssd);
		startActivityBean.setSessionEndDate(sed);

		// Since code for process master is attached, so semester codes will be
		// in list
		List<StartActivityBean> semesterList = new ArrayList<StartActivityBean>();
		
		for(int i=0;i<semesterList.size();i++){
			
			System.out.println(startActivityBean.getUniversityId());
			System.out.println(startActivityBean.getSessionStartDate());
			System.out.println(startActivityBean.getSessionEndDate());
		}

		return new ModelAndView("activitymaster/SessionDataList", "sessionDetails",
				startActivityBean);

	}
	
	
	
	/**
	 * This method sets required input values according to user request and
	 * calls startActivityMethod
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView startProcessActivity(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession(true);

		String universityId = (String) session.getAttribute("universityId");
		String universityName = (String) session.getAttribute("universityName");
		String ssd = (String) session.getAttribute("startDate");
		String sed = (String) session.getAttribute("endDate");
		String userId = (String) session.getAttribute("userId");
		
		CountProcessRecorList countList = new CountProcessRecorList();

		// String programId="";
		// String branchId="";
		// String specializationId="";
		// String semesterCode="";
		String filepath="";
		String prgkey="";
		String actvty="";
		String semName="";
		String sess="";

		try {

			// startActivityBean contains following properties:
			// 1-universityId, 2-entityId, 3- programId, 4-branchId,
			// 5-specializationId, 6-SemesterCode
			// 7-programCourseKey
			// 8-sessionStartDate,9-sessionEndDate
			// 10-semesterStartDate,11-semesterEndDate
			// 12-processId
			// 13-activityId,14-activitySequence,15-activityStatus
			// 16-programName, 17-branchName, 18-specializationName,
			// 19-semesterName, 18- entityName
			System.out.println(universityId
					+ request.getParameter("entityId")
					+ request.getParameter("entityName")
					+ request.getParameter("programId")
					+ request.getParameter("programName")
					+ request.getParameter("branchId")
					+ request.getParameter("branchName")
					+ request.getParameter("specializationId")
					+ request.getParameter("specializationName")
					+ request.getParameter("semesterId")
					+ request.getParameter("semesterName")
					+ request.getParameter("semesterStartDate")
					+ request.getParameter("semesterEndDate")
					+ request.getParameter("programCourseKey")
					+ request.getParameter("processId")
					+ request.getParameter("activityCode")
					+ Integer.parseInt((String) request
							.getParameter("activitySequence"))
					+ request.getParameter("activityStatus"));
			
			prgkey=request.getParameter("programCourseKey");
			actvty=request.getParameter("activityCode");
			semName=request.getParameter("semesterId");
			sess=ssd.substring(0,4)+"-"+sed.substring(0,4);

			StartActivityBean startActivityBean = new StartActivityBean(
					universityId, (String) request.getParameter("entityId"),
					(String) request.getParameter("entityName"),
					(String) request.getParameter("programId"),
					(String) request.getParameter("programName"),
					(String) request.getParameter("branchId"), (String) request
							.getParameter("branchName"), (String) request
							.getParameter("specializationId"), (String) request
							.getParameter("specializationName"),
					(String) request.getParameter("semesterId"),
					(String) request.getParameter("semesterName"),
					(String) request.getParameter("semesterStartDate"),
					(String) request.getParameter("semesterEndDate"),
					(String) request.getParameter("programCourseKey"),
					(String) request.getParameter("processId"),
					(String) request.getParameter("activityCode"), Integer
							.parseInt((String) request
									.getParameter("activitySequence")),
					(String) request.getParameter("activityStatus"), userId);

			// getting session start date and end date from university_master
			// table for current_status=1
			// setting values of session start date and session end date
			List<StartActivityBean> sessionList = startActivityDao
					.getSessionDate(universityId);
			for (StartActivityBean startBean : sessionList) {
				startActivityBean.setSessionStartDate(startBean
						.getSessionStartDate());
				startActivityBean.setSessionEndDate(startBean
						.getSessionEndDate());
			}

			// setting values for program id, branch id,specialization id,
			// semester code for program course key
			// List<StartActivityBean>
			// programvalueList=registrationFunction.getProgramForKey(startActivityBean);
			// for(StartActivityBean idList:programvalueList){
			// startActivityBean.setProgramId(idList.getProgramId());
			// startActivityBean.setBranchId(idList.getBranchId());
			// startActivityBean.setSpecializationId(idList.getSpecializationId());
			// startActivityBean.setSemesterCode(idList.getSemesterCode());
			// }

			// startActivityFunction triggers from this point. and before this
			// all values are set in startActivityBean object
			// added by pachauri without implementing count
			if (startActivityBean.getProcessId().equalsIgnoreCase("REG")
					&& startActivityBean.getActivityId()
							.equalsIgnoreCase("PDF")) {
				System.out.println("inside if");
				return new ModelAndView("studentCoursePdf", "temporaryInfo",
						startActivityBean);
			}
			// end
			
			//added by ankit
			String sep = System.getProperty("file.separator");
			String directoryName = getServletContext().getRealPath("/")+"PDF";		
			directoryName=directoryName +sep+session.getAttribute("universityName").toString()+sep+"DuplicateStudent"+
			sep+"Session-"+session.getAttribute("startDate").toString().substring(0, 4)+"-"+
			session.getAttribute("endDate").toString().substring(0, 4);
			
			File file = new File(directoryName);
			file.mkdirs();
			//end of ankit
			countList = startActivityDao.startActivity(startActivityBean,directoryName,universityName);

		} catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());
		}

		
		if(countList.getRejStudentList().size()>0){
			try{
				filepath=this.getServletContext().getRealPath("/")+"RejectedRecords"+File.separator+universityId+File.separator+sess+File.separator+semName+File.separator;
			   File file=new File(filepath);
		       file.mkdirs();
		  	
		       
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			generatePdf(countList.getRejStudentList(),filepath,prgkey,actvty,request.getParameter("entityName"),request.getParameter("programName"),
					request.getParameter("branchName"),request.getParameter("specializationName"),request.getParameter("semesterName"),universityName,ssd,sed,request.getParameter("activityName"));
			
			//added by ashish mohan
			//changed by Dheeraj
		try{
			List<StartActivityBean> getMailUsers = startActivityDao.getMailUsers();
			List toUsers = new ArrayList();
			for(int k = 0 ; k < getMailUsers.size() ; k++){
				toUsers.add(getMailUsers.get(k).getMailId());
			}
			String[] to = (String[]) toUsers.toArray(new String[0]);
			//DS
			sendmail.mainWithAttachment("Course Management System Information Mail:Please See The Attachment for Error Details",
										to,
										"Error Occured When Running "+actvty+" For "+request.getParameter("programName")+"_"+request.getParameter("branchName")+"_"+request.getParameter("specializationName")+"_"+request.getParameter("semesterName"),
										filepath+actvty+"_"+prgkey+".pdf",universityId);
			//ashish mohan addition ends 
		}catch(Exception ex){
				System.out.println("Exception In Sending Mail");
				return new ModelAndView("activitymaster/CountProcessedRecord",
						"countList", countList);
			}
		}
		
		return new ModelAndView("activitymaster/CountProcessedRecord",
				"countList", countList);
	}
	
	/**
	    * Function For Generation Of Pdf 
	    */
		public void generatePdf(
				List<UnProcessedStduent> GenerateList,
				String filePaths,String pck,String act,String ent,String prg,String brn,String spc,String sem,String univ,String ssds,String seds,String actName) {
			Document document = new Document(PageSize.A4.rotate());
			try {
				
				Date date=new Date();
				String currentDate=DateFormat.getDateInstance().format(date);
				filePaths = filePaths +act+"_"+pck+".pdf";
				System.out.println("File path is" + filePaths);


				PdfWriter.getInstance(document, new FileOutputStream(filePaths));

				 Phrase h1= new Phrase(
					         univ.toUpperCase()+"\n"
						,
						FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD,
								new Color(0, 0, 0)));
		
				 String activityname="";
				 if(actName.indexOf(":")>0){
					 activityname=actName.substring(0,actName.indexOf(":"));
				 }else{
					 activityname=actName;
				 }
				 
				 
				 Phrase h2= new Phrase(
							"Rejected Record List "+"for "+activityname.toUpperCase()+"\t\t("+ssds.substring(0,4)+"-"+seds.substring(0,4)+")"
							,
							FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD,
									new Color(0, 0, 0)));
				 h1.add(h2);
				

				HeaderFooter headerFooter = new HeaderFooter(h1, false);
				headerFooter.setBorderWidth(1);
				headerFooter.setAlignment(Element.ALIGN_CENTER);
				
				HeaderFooter foooter = new HeaderFooter(new Phrase("Page:"), true);
				foooter.setBorderWidth(0);
				foooter.setAlignment(Element.ALIGN_RIGHT);
				
				document.setHeader(headerFooter);
				document.setFooter(foooter);
				document.open();
				
				
				
			
				
				PdfPTable clastable = new PdfPTable(new float[] {1.5f,7});
				clastable.setWidthPercentage(100);
				clastable.setHorizontalAlignment(Element.ALIGN_LEFT);
				PdfPCell cells = new PdfPCell();

				Font cellFont1 = FontFactory.getFont(FontFactory.HELVETICA, 12,
						Font.NORMAL, new Color(0, 0, 0));
				Font cellFont2 = FontFactory.getFont(FontFactory.HELVETICA, 12,
						Font.BOLD, new Color(0, 0, 0));
				addCell(cells, cellFont1, clastable, "ENTITY :");
				addCell(cells, cellFont2, clastable, ent.toUpperCase());
				addCell(cells, cellFont1, clastable, "PROGRAM :");
				addCell(cells, cellFont2, clastable, prg.toUpperCase());
				addCell(cells, cellFont1, clastable, "BRANCH :");
				addCell(cells, cellFont2, clastable, brn.toUpperCase());
				addCell(cells, cellFont1, clastable, "SPECIALIZATION :");
				addCell(cells, cellFont2, clastable, spc.toUpperCase());
				addCell(cells, cellFont1, clastable, "SEMESTER :");
				addCell(cells, cellFont2, clastable, sem.toUpperCase());
				
				
				
				Paragraph pp=new Paragraph(new Phrase("\n"));
				document.add(pp);
				document.add(pp);
				
				document.add(clastable);
				
				Chunk dottedLine = new Chunk(
						"------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------",
						FontFactory.getFont(FontFactory.HELVETICA, 10, Font.ITALIC,
								new Color(0, 0, 0)));
				
				document.add(dottedLine);
				document.add(pp);
				document.add(pp);
				
				Font cellFont = new Font(Font.HELVETICA,12);
				Font cellFontt = new Font(Font.HELVETICA,14,Font.BOLD);
				PdfPCell cel = new PdfPCell(new Phrase("", cellFont));
				PdfPTable headerTable = new PdfPTable(new float[] {5,15});
				headerTable.setWidthPercentage(90);
				headerTable.setHorizontalAlignment(Element.ALIGN_CENTER);
				
				if((act.equalsIgnoreCase("ABV"))||(act.equalsIgnoreCase("ABR"))){
					addCell1(cel, cellFontt, headerTable, "Course Codes");
				}else{
				addCell1(cel, cellFontt, headerTable, "Registration/Roll Number");
				}
				addCell1(cel, cellFontt, headerTable, "Reason Of Rejection");
				

				PdfPTable dataTable = new PdfPTable(new float[] {5,15});

				dataTable.setWidthPercentage(90);
				dataTable.setHorizontalAlignment(Element.ALIGN_CENTER);
				
				for(int ii=0;ii<GenerateList.size();ii++){
					
					addCell1(cel, cellFont, dataTable,GenerateList.get(ii).getRollNumber());
					addCell1(cel, cellFont, dataTable,GenerateList.get(ii).getProcessed());
					
				}
				document.add(headerTable);
				document.add(dataTable);




		
				
				document.close();
				
			}

			catch (Exception e) {
				e.printStackTrace();
				
			} 
	
			
		}	
	
	
/**
* This method add a cell to Table
* @param c1, table cell
* @param cellFont, font used
* @param Table chartTable, table in which cell is added
* @param s, value in cell
*/
public static final void addCell(PdfPCell c1, Font cellFont,
			PdfPTable chartTable, String s) {
		try {
			c1 = new PdfPCell(new Phrase(s, cellFont));
		} catch (Exception e) {
			e.printStackTrace();
		}
		c1.setBorderWidth(0);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setVerticalAlignment(Element.ALIGN_TOP);
		chartTable.addCell(c1);
	}


/**
* This method add a cell to Table
* @param c1, table cell
* @param cellFont, font used
* @param Table chartTable, table in which cell is added
* @param s, value in cell
*/
public static final void addCell1(PdfPCell c1, Font cellFont,
			PdfPTable chartTable, String s) {
		try {
			c1 = new PdfPCell(new Phrase(s, cellFont));
		} catch (Exception e) {
			e.printStackTrace();
		}
		c1.setBorderWidth(1);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setVerticalAlignment(Element.ALIGN_TOP);
		chartTable.addCell(c1);
	}

}
