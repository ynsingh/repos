package in.ac.dei.edrp.cms.controller.reportgeneration;

import in.ac.dei.edrp.cms.dao.report.ReportDao;
import in.ac.dei.edrp.cms.dao.reportgeneration.ProgressCardDao;
import in.ac.dei.edrp.cms.daoimpl.report.ReportDaoImpl;
import in.ac.dei.edrp.cms.domain.reportgeneration.ProgressCardInfo;
import in.ac.dei.edrp.cms.domain.reportgeneration.SemesterInfoForAllStudents;

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
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.view.document.AbstractPdfView;

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

public class PdfProgresscardView extends AbstractPdfView {
	
	private ProgressCardInfo trackingPdfGenration = new ProgressCardInfo();
	private ProgressCardDao progressCardDao;	
		
	public void setProgressCardDao(ProgressCardDao progressCardDao) {
		this.progressCardDao = progressCardDao;
	}

	Locale obj = new Locale("en","Us");
	String sep=System.getProperty("file.separator");
	ResourceBundle message = ResourceBundle.getBundle("in"+sep+"ac"+sep+"dei"+sep+"edrp"+sep+"cms"+sep+"dao"+sep+"reportgeneration"+sep+"ApplicationResources", obj); 

	Calendar calendar = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");		
	java.sql.Date date = new java.sql.Date(calendar.getTime().getTime());
	
	@SuppressWarnings("unchecked")
	protected void buildPdfDocument(Map model,Document document,PdfWriter writer,
			HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		/**
		 * progressCardInfo Object have the current Program Branch information.
		 */
		ProgressCardInfo progressCardInfo  = new ProgressCardInfo();	
		//getting the content in the form model
		progressCardInfo = (ProgressCardInfo) model.get("progressCardData");
		
		SemesterWisePdfView semesterWiseMarksPdfView = new SemesterWisePdfView();
		
		List<SemesterInfoForAllStudents> semesterInfoForStudents = new ArrayList<SemesterInfoForAllStudents>();
		ProgressCardInfo semesterInfoData = new ProgressCardInfo();		
		String programId =null;
		String programCode =null;
		String branchId = null;
		String branchName = null;
		String semesterId = null;
		String semesterStartDate = null;
		String semesterEndDate  = null;		
		String modeOfEntry = null;
		String name = null;
		String switchNumber = null;
		String rollNo = null;
		String programName = null;		
		String sessionStartDate =null;
		String sessionEndDate =null;
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
		entityId = progressCardInfo.getEntityId();
		semesterSequence = progressCardInfo.getSemesterSequence();
		whatToPrint = progressCardInfo.getWhatToPrint();
		
		HttpSession httpSession = request.getSession(true);
		
		String universityName =(String) httpSession.getAttribute("universityName");
		String universityAddress=(String) httpSession.getAttribute("address");
		
		progressCardType = progressCardInfo.getProgressCardType();
		List<ProgressCardInfo> courseMarksDetails = new ArrayList<ProgressCardInfo>();
		//getting the list of program course key's
		List<ProgressCardInfo> programCourseKeyList = progressCardDao.getProgramCourseKeys(progressCardInfo);
		
		// it is student list on program_course_key.
		List<ProgressCardInfo> studentSatisfyingPCK = new ArrayList<ProgressCardInfo>();
		
		//totalMarksDetailsForPreviousSemesters variable will hold the previous semesters marks details of the student.
		List<ProgressCardInfo> totalMarksDetailsForPreviousSemesters = new ArrayList<ProgressCardInfo>();
		 
		//totalMarksDetailsForCurrentSemester variable will hold the current semester marks details of the student.
		List<ProgressCardInfo> totalMarksDetailsForCurrentSemester = new ArrayList<ProgressCardInfo>();
		List<ProgressCardInfo> semesterList = new ArrayList<ProgressCardInfo>();
		ProgressCardInfo cummulativeForFC = new ProgressCardInfo();
		ProgressCardInfo switchRules = new ProgressCardInfo();
		ProgressCardInfo combinationBeforeSwitch = new ProgressCardInfo();
		ProgressCardInfo getPCK = new ProgressCardInfo();
		ProgressCardInfo semesterDate = new ProgressCardInfo();
		ProgressCardInfo errorLog = new ProgressCardInfo();
		String directoryName = null;
		String directoryNameForSemester = null;
		File file;
		 
		if(programCourseKeyList.size()>0)
		{	
			//making the directory for storing the file
			System.out.println("in directory creation");
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
			file.mkdir();		
			directoryNameForSemester = directoryName;
		}
		
		for(ProgressCardInfo pci:programCourseKeyList){					
			programName = pci.getProgramName();			
//			System.out.println("AA"+"program code "+pci.getProgramCode()+" and the program course key "+progressCardInfo.getProgramCourseKey());			
			programCode = pci.getProgramCode();
			branchName = pci.getBranchName();
			semesterStartDate = progressCardInfo.getSemesterStartDate();
			semesterEndDate = progressCardInfo.getSemesterEndDate();
			
			/*
			 * making a folder with the name of semester start and end date
			 */
			directoryName = directoryName +"\\"+semesterSequence+"_"+semesterStartDate+"_"+semesterEndDate;
			file = new File(directoryName);
			file.mkdir();										
			
			mainBranchId = pci.getBranchId();
			pci.setSemesterStartDate(semesterStartDate);
			pci.setSemesterEndDate(semesterEndDate);
			studentSatisfyingPCK = new ArrayList<ProgressCardInfo>();	
			
			/*
			 * getting students satisfying the program course key combination
			 */
			// get the list of student on the program_course_key
			studentSatisfyingPCK = progressCardDao.studentSatisfyingPCK(pci);			
			System.out.println("b4 student "+studentSatisfyingPCK.size());
			noOfStudents = studentSatisfyingPCK.size();			
			
			for(ProgressCardInfo student: studentSatisfyingPCK){
				sessionEndDate= student.getSessionEndDate().substring(0,4);				
				sessionStartDate = student.getSessionStartDate().substring(0,4);	
				programCourseKey = pci.getProgramCourseKey();
				session  = sessionStartDate+"-"+sessionEndDate.substring(2,4);
				errorCode = null;
				
				if(whatToPrint.equalsIgnoreCase("progress")){
					reportType = progressCardType.equalsIgnoreCase("FINAL RESULT CARD") ? "FC" : "PC";					
				}
				
//				System.out.println("in student detail");
				name = student.getName();
//				System.out.println("name"+name);
				enrolmentNumber = student.getEnrollmentNumber();
				rollNo = student.getRollNumber();
				sessionEndDate= student.getSessionEndDate().substring(0,4);				
				sessionStartDate = student.getSessionStartDate().substring(0,4);								
//				System.out.println("rollno "+rollNo);
				switchNumber =student.getSwitchNumber();
				modeOfEntry = student.getModeOfEntry();
				sequenceNumber = student.getSequenceNumber();
			//	programStatus = student.getProgramStatus();
				inSemester = student.getInSemester();
			//	outSemester = student.getOutSemester();				
				pci.setRollNoForDetail(rollNo);		
				pci.setEnrollmentNumber(enrolmentNumber);
				
				// course marks
				courseMarksDetails = new ArrayList<ProgressCardInfo>();
				// get student current program semester courses and marks.
				courseMarksDetails = progressCardDao.progressCardMarksDetails(pci);
//				System.out.println("course"+courseMarksDetails.size());
				
				// if courses not found then break the process and 
				// insert error into error_log table.
				if(courseMarksDetails.size()==0){
					System.out.println("course marks details of size 0 ");
					errorCode = "E101";
					progressCardDao.insertIntoErrorLog(rollNo, pci.getProgramCourseKey(), reportType, errorCode, semesterStartDate, semesterEndDate, session);
					continue;
				}
				else{
					///total marks
					totalMarksDetailsForCurrentSemester = new ArrayList<ProgressCardInfo>();
					totalMarksDetailsForPreviousSemesters = new ArrayList<ProgressCardInfo>();
					inSemester = student.getInSemester();
					inputSemester = progressCardInfo.getSemesterId();
					System.out.println("check: " + switchNumber+" ........ "+modeOfEntry);
					
					// if switch number exist and mode of entry is SW then go into switch logic.
					if(switchNumber != null && modeOfEntry.equalsIgnoreCase("SW")){					
//						System.out.println("sn"+switchNumber);
						programId = pci.getProgramId();
//						System.out.println("pid "+programId);
						branchId = pci.getBranchId();	
//						System.out.println("bid "+branchId);
						//		specializationId = pci.getSpecializationId();					
						semesterList = new ArrayList<ProgressCardInfo>();
						
						// set inSemester variable. 
						pci.setInSemester(inSemester);
						
						//this object is unused.
						ProgressCardInfo greaterSemeter = progressCardDao.semesterIsGreater(pci);
//						System.out.println(greaterSemeter.getSemesterId());
//						if(greaterSemeter.getSemesterId() != null)
//						{	
//							System.out.println(greaterSemeter.getSemesterId());	
							System.out.println("jst b4 "+pci.getSemesterId());
							
							// get the marks details for the current program semesters.
							totalMarksDetailsForCurrentSemester.addAll(progressCardDao.totalMarksDetails(pci));
							
							// if current program semester marks not found then
							// break the loop and insert error into error_logs table.
							if(totalMarksDetailsForCurrentSemester.size() == 0){
								errorCode =  "E102";
								progressCardDao.insertIntoErrorLog(rollNo, pci.getProgramCourseKey(), reportType, errorCode, semesterStartDate, semesterEndDate, session);
								break;
							}
							
							pci.setFirstSemesterId(semesterId);//commented by ankit
							//pci.setFirstSemesterId(pci.getSemesterId());// addded by ankit.
							//pci.setFirstSemesterId(inputSemester);
							pci.setSecondSemesterId(inSemester);
							
							// this logic is called to get the  List of semesters between inSemester and semesterId
							semesterList = progressCardDao.semestersBetTwoSemesters(pci);	
							if(semesterList.size() == 0){
								System.out.println("semester list between semester fail to get");
								errorCode =  "E103";
								progressCardDao.insertIntoErrorLog(rollNo, pci.getProgramCourseKey(), reportType, errorCode, semesterStartDate, semesterEndDate, session);
								break;
							}
							
							// get programCourseKey according to semesters list
							for(ProgressCardInfo semesterInfo : semesterList){
								System.out.println("for "+semesterInfo.getSemesterId()+" pck "+pci.getProgramCourseKey());
								pci.setSemesterId(semesterInfo.getSemesterId());
								
								//get programCourseKey to get marks on this programCourseKey
								getPCK = progressCardDao.getSinglePCK(pci);
								System.out.println(getPCK.getProgramCourseKey());
								//get last semesters marks.
								pci.setProgramCourseKey(getPCK.getProgramCourseKey());
								
								// first time, get marks of last semester.
								totalMarksDetailsForPreviousSemesters.addAll(progressCardDao.totalMarksDetails(pci));						
							}	
							
							System.out.println("ttmd");										
							for(ProgressCardInfo p : totalMarksDetailsForCurrentSemester){
								System.out.println(p.getRollNumber()+" wt "+p.getWeightTheory()+" wp "+p.getWeightPractical()+" ta "+p.getTotalAggregate()+" tpc "+p.getTotalPracticalCredits()+" ttc "+p.getTotalTheoryCredits()+" rem "+p.getRemark());
							}
							
							// getting sequence number of switching
							switchSequence = sequenceNumber!= null? Integer.parseInt(sequenceNumber) : 0;
							System.out.println("switch sequence: "+ switchSequence+" - - - "+sequenceNumber);
							while(switchSequence > 1){
								System.out.println("ss"+switchSequence);
								switchSequence = switchSequence - 1;
								pci.setSequenceNumber(String.valueOf(switchSequence));
								pci.setSwitchNumber(switchNumber);
								combinationBeforeSwitch = new ProgressCardInfo();
								combinationBeforeSwitch = progressCardDao.getPreviousCombination(pci);
								if(combinationBeforeSwitch == null){
									errorCode ="E104";
									progressCardDao.insertIntoErrorLog(rollNo, pci.getProgramCourseKey(), reportType, errorCode, semesterStartDate, semesterEndDate, session);
									break;
								}
								
								System.out.println(combinationBeforeSwitch.getProgramId()+" "+combinationBeforeSwitch.getBranchId()+" "+combinationBeforeSwitch.getRollNoForDetail()+" "+combinationBeforeSwitch.getSpecializationId());
								System.out.println("branch id for "+switchSequence+" "+ pci.getBranchId());
								switchRules = progressCardDao.switchRules(pci);
								
								if(switchRules == null){
									System.out.println("no switch rule specified");
									errorCode =  "E105";
									progressCardDao.insertIntoErrorLog(rollNo, pci.getProgramCourseKey(), reportType, errorCode, semesterStartDate, semesterEndDate, session);
									break;
								}
								
								System.out.println(switchRules.getRuleCodeOne()+" "+switchRules.getRuleCodeTwo()+" "+switchRules.getRuleCodeThree());
								if(switchRules.getRuleCodeTwo() != null && switchRules.getRuleCodeThree() != null && switchRules.getRuleCodeTwo().equalsIgnoreCase("Y"))
								{	
									System.out.println("Inside switch Logic: "+ switchRules.getRuleCodeThree());
									switch(Integer.parseInt((switchRules.getRuleCodeThree())))
									{
										case 1:
											System.out.println("in case 1"+pci.getProgramId());							
											semesterList = progressCardDao.semestersBetTwoSemesters(combinationBeforeSwitch);
											System.out.println(combinationBeforeSwitch.getFirstSemesterId()+" "+combinationBeforeSwitch.getSecondSemesterId());
											for(ProgressCardInfo semesterInfo : semesterList)
											{
												System.out.println("for "+semesterInfo.getSemesterId());
												combinationBeforeSwitch.setSemesterId(semesterInfo.getSemesterId());
												getPCK = progressCardDao.getSinglePCK(combinationBeforeSwitch);
												System.out.println(getPCK.getProgramCourseKey()+entityId);
												combinationBeforeSwitch.setEntityId(entityId);												
												combinationBeforeSwitch.setProgramCourseKey(getPCK.getProgramCourseKey());
												semesterDate = progressCardDao.semesterStartEndDate(combinationBeforeSwitch);
												System.out.println(semesterDate.getSemesterStartDate());
												combinationBeforeSwitch.setSemesterStartDate(semesterDate.getSemesterStartDate());
												combinationBeforeSwitch.setSemesterEndDate(semesterDate.getSemesterEndDate());
												// second time, get marks of last semester.
												totalMarksDetailsForPreviousSemesters.addAll(progressCardDao.totalMarksDetails(combinationBeforeSwitch));
												System.out.println(totalMarksDetailsForPreviousSemesters.size());
											}	
											
											// third time, get marks of last semester.
											totalMarksDetailsForPreviousSemesters.addAll(totalMarksDetailsForCurrentSemester);
											System.out.println(totalMarksDetailsForPreviousSemesters.size());
											break;
										case 2:
											String [] equalSeperator = new String[2];
											int i=0;							
											ProgressCardInfo previousSemesterMarks = new ProgressCardInfo();
											System.out.println("in case 2");
											Float previousWeightTheory =0f,previousWeightPractical = 0f;
											StringTokenizer switchFormula =  new StringTokenizer(switchRules.getSwitchRuleFormula(),",");							
											StringTokenizer plusSeprator;
											List<String> plusList = new ArrayList<String>();
											Map<String,List<String>> semesterMap = new TreeMap<String, List<String>>();
											while(switchFormula.hasMoreTokens())
											{
												//equalSeprator = new StringTokenizer(switchFormula.nextToken(),"=");
												plusList = new ArrayList<String>();
												equalSeperator = switchFormula.nextToken().toString().split("=");									
												plusSeprator = new StringTokenizer(equalSeperator[1],"+");
												while(plusSeprator.hasMoreTokens())
												{
													plusList.add(plusSeprator.nextToken());
												}
												semesterMap.put(equalSeperator[0], plusList);								
											}
							
											for(Map.Entry<String,List<String>> entry : semesterMap.entrySet())								
											{
												System.out.println(entry.getKey()+entry.getValue());
												i=0;
												previousWeightTheory = 0f;
												previousWeightPractical = 0f;
												previousSemesterMarks = new ProgressCardInfo();
												for(String sem : entry.getValue())
												{
													combinationBeforeSwitch.setSemesterId(sem.trim());
													combinationBeforeSwitch.setInsertSemester(entry.getKey().trim());
													previousSemesterMarks = progressCardDao.previousSemesterMarks(combinationBeforeSwitch);									
													System.out.println("ss"+Integer.parseInt(previousSemesterMarks.getSemesterSequence()));	
													previousWeightTheory = previousWeightTheory + Float.parseFloat(previousSemesterMarks.getWeightTheory());
													previousWeightPractical = previousWeightPractical + Float.parseFloat(previousSemesterMarks.getWeightPractical());
													i++;
												}
												previousWeightTheory = previousWeightTheory/i;							
												previousWeightPractical = previousWeightPractical/i;								
												previousSemesterMarks.setWeightTheory(previousWeightTheory.toString());
												previousSemesterMarks.setWeightPractical(previousWeightPractical.toString());								
												totalMarksDetailsForPreviousSemesters.add(previousSemesterMarks);								
											}												
											totalMarksDetailsForPreviousSemesters = sortedListBySemesterSequence(totalMarksDetailsForPreviousSemesters);
											totalMarksDetailsForPreviousSemesters.addAll(totalMarksDetailsForCurrentSemester);
											break;
										case 3:							
											break;
										case 4:
											break;
										default:
											System.out.println("No rule specified");
									}	
								}
							}
	//				}
				}
				else 
				{
					System.out.println("in else for "+pci.getBranchId());
					totalMarksDetailsForCurrentSemester.addAll(progressCardDao.totalMarksDetails(pci));
					if(totalMarksDetailsForCurrentSemester.size() == 0){						
						errorCode =  "E102";
						progressCardDao.insertIntoErrorLog(rollNo, pci.getProgramCourseKey(), reportType, errorCode, semesterStartDate, semesterEndDate, session);
						break;
					} 
						
//					semesterId = pci.getSemesterId();
//					inSemester = pci.getInSemester();
					pci.setFirstSemesterId(inputSemester);
					pci.setSecondSemesterId(inSemester);
					semesterList = progressCardDao.semestersBetTwoSemesters(pci);
					
					if(semesterList.size() == 0){
						System.out.println("semester list between semester fail to get");
						errorCode =  "E103";
						progressCardDao.insertIntoErrorLog(rollNo, pci.getProgramCourseKey(), reportType, errorCode, semesterStartDate, semesterEndDate, session);
						break;
					}					
					
					System.out.println("semid "+inputSemester+" in sem "+inSemester+" size "+semesterList.size());
					for(ProgressCardInfo semesterInfo : semesterList)
					{
						System.out.println("in loop semester List");
					//	System.out.println("for "+semesterInfo.getSemesterId()+" pck "+pci.getProgramCourseKey());
						pci.setSemesterId(semesterInfo.getSemesterId());
						getPCK = progressCardDao.getSinglePCK(pci);						
						pci.setProgramCourseKey(getPCK.getProgramCourseKey());
						pci.setEntityId(entityId);
						semesterDate = progressCardDao.semesterStartEndDate(pci);
						System.out.println("ssd "+semesterDate.getSemesterStartDate() + " "+semesterDate.getSemesterEndDate());
						pci.setSemesterStartDate(semesterDate.getSemesterStartDate());
						pci.setSemesterEndDate(semesterDate.getSemesterEndDate());						
						totalMarksDetailsForPreviousSemesters.addAll(progressCardDao.totalMarksDetails(pci));						
					}										
				}
				
					System.out.println("what to print "+whatToPrint+" error code "+errorCode);
					if(whatToPrint.equalsIgnoreCase("progress") && errorCode == null)
					{					
						System.out.println("before acks");
						boolean acknwoledge	= generatePdf(document, writer, request, response, name, rollNo, programName,branchName,
						inputSemester, sessionStartDate, sessionEndDate,enrolmentNumber,courseMarksDetails,
						totalMarksDetailsForCurrentSemester,totalMarksDetailsForPreviousSemesters,printType,progressCardType,
						directoryName,cummulativeForFC);
						
						System.out.println("Return Value: "+ acknwoledge);
						
						if(acknwoledge == true)
						{
							System.out.println("in if ");
							noOfPdfGenerated = noOfPdfGenerated + 1;
							pdfGenerated = "Y";									
						}
						else {
							System.out.println("in s=else");
							pdfGenerated = "N";
							errorCode="E105";
							progressCardDao.insertIntoErrorLog(rollNo, pci.getProgramCourseKey(), reportType, errorCode, semesterStartDate, semesterEndDate, session);
							break;								
						}
					}						
					else{
						semesterInfoData = new ProgressCardInfo();
						semesterInfoData.setEntityId(entityId);
						semesterInfoData.setRollNumber(rollNo);
						semesterInfoData.setProgramCode(programCode);
						semesterInfoData.setBranchId(mainBranchId);
						semesterInfoData.setSemesterSequence(semesterSequence);
						semesterInfoData.setName(name);
						semesterInfoForStudents.add(new SemesterInfoForAllStudents(semesterInfoData,totalMarksDetailsForPreviousSemesters));
					}
				}
				System.out.println("error codes "+errorCode);	
			}		
			
			trackingPdfGenration.setProgramCourseKey(pci.getProgramCourseKey());
			trackingPdfGenration.setSemesterStartDate(semesterStartDate);
			trackingPdfGenration.setSemesterEndDate(semesterEndDate);
			trackingPdfGenration.setNoOfStudents(noOfStudents);
			System.out.println("no of pdf generated"+noOfPdfGenerated);
			trackingPdfGenration.setNoOfPdfGenerated(noOfPdfGenerated);
			trackingPdfGenration.setPdfGenerated(pdfGenerated);
//			System.out.println(sessionStartDate+"-"+sessionEndDate.substring(2,4));
			trackingPdfGenration.setSession(sessionStartDate+"-"+sessionEndDate.substring(2,4));
			
			if(whatToPrint.equalsIgnoreCase("progress"))
			{
				if(progressCardType.equalsIgnoreCase("FINAL RESULT CARD"))
				{
				trackingPdfGenration.setReportType("FC");
				progressCardInfo.setRollNoForDetail(rollNo);
				progressCardInfo.setEnrollmentNumber(enrolmentNumber);
				progressCardInfo.setBranchId(pci.getBranchId());
				progressCardInfo.setSpecializationId(pci.getSpecializationId());
				
				cummulativeForFC = progressCardDao.cummulativeForFinalResultCard(progressCardInfo);								
				}
				else trackingPdfGenration.setReportType("PC");		
			}	
			
			System.out.println("b4 inserting");
			int insertCompleted = progressCardDao.insertIntoReportControlLog(trackingPdfGenration);			
			System.out.println("after inserting "+insertCompleted);
			noOfPdfGenerated = 0;
			noOfStudents = 0;
		}
		
		if(whatToPrint.equalsIgnoreCase("semesterwise"))
		{
//			directoryNameForSemester = directoryNameForSemester +"\\SemesterWiseMarks";
			file = new File(directoryNameForSemester);
			file.mkdir();	
			ReportDao reportDao = new ReportDaoImpl();
			semesterWiseMarksPdfView.generatePdfForSemesterWiseMarks(request,reportDao,document, writer, semesterInfoForStudents,directoryNameForSemester);
		}
	}
		
	public boolean generatePdf(Document document,PdfWriter writer,
			HttpServletRequest request,HttpServletResponse response,
			String name,String rollNo,String programName,String branchName,String semester,
			String sessionStartDate,String sessionEndDate,String enrolmentNumber,List<ProgressCardInfo> marksDetails,
			List<ProgressCardInfo> totalMarksDetailsForCurrentSem,List<ProgressCardInfo> totalMarksDetailsForPreviousSem,String printType,
			String progressCardType,String directoryName,ProgressCardInfo cummulativeForFC) throws Exception
	{	
		try{
			HttpSession httpSession=request.getSession(true);
			System.out.println("inside generatePDF " +name);
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			sdf.format(calendar.getTime());
			java.sql.Date date = new java.sql.Date(calendar.getTime().getTime());
			String currentDate = sdf.format(date);
			String totalTheoryCredits = null;
			String totalPracticalCredits = null;
			String weightTheory = null;
			String weightPractical = null;
			String remarks = null;
			String roman=null;
			float cumulativeTheory = 0;
			float cumulativePractical = 0; 
			float credits = 0;
			String marks;
			boolean flagPass = true;		
			PdfContentByte cb = writer.getDirectContent();
			document = new Document(PageSize.A4,25, 50, 50, 50);
//			document.open();
			writer = PdfWriter.getInstance(document,new FileOutputStream(directoryName+""+sep+""+"ProgressCard_"+rollNo+".pdf"));							
				totalTheoryCredits = totalMarksDetailsForCurrentSem.get(0).getTotalTheoryCredits();
				totalPracticalCredits = totalMarksDetailsForCurrentSem.get(0).getTotalPracticalCredits();
				weightTheory = totalMarksDetailsForCurrentSem.get(0).getWeightTheory();
				weightPractical = totalMarksDetailsForCurrentSem.get(0).getWeightPractical();
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
				
				c1 = new Cell(new Phrase("SEMESTER : "+ semester,cellFont));
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
				
//				c2 = new PdfPCell(new Phrase(""));
////				c2.setColspan(3);
//				//c2.setBorder(Rectangle.NO_BORDER);
//				c2.setBorderWidth(0);
//				marksTable.addCell(c2);
//				
//				c2= new PdfPCell(new Phrase(" ",cellFont));
//				//c2.setBorder(Rectangle.NO_BORDER);
//				c2.setBorderWidth(0);
//				marksTable.addCell(c2);		
//				
//				c2 = new PdfPCell(new Phrase("",cellFont));
//				//c2.setBorder(Rectangle.NO_BORDER);
//				c2.setBorderWidth(0);
//				marksTable.addCell(c2);
//				
//				c2 = new PdfPCell(new Phrase("",cellFont));
//				//c2.setBorder(Rectangle.NO_BORDER);
//				c2.setBorderWidth(0);
////				c2.setColspan(2);
//				marksTable.addCell(c2);
							
				
//				pcb.moveTo(70,writer.getVerticalPosition(true)-50);
//				pcb.lineTo(PageSize.A4.getWidth()-80,writer.getVerticalPosition(true)-50);
//				pcb.setLineDash(3f,3f);
//				pcb.stroke();	
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
					c3 = new PdfPCell(new Phrase(pci.getStudentInternal().toString(),cellFont));
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
					
					c3 = new PdfPCell(new Phrase(pci.getStudentTotalMarks().toString(),cellFont));
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
//				pcb.moveTo(70,writer.getVerticalPosition(true)-10);
//				pcb.lineTo(PageSize.A4.getWidth()-80,writer.getVerticalPosition(true)-10);
//				pcb.setLineDash(3f,3f);
//				pcb.stroke();
				System.out.println(writer.getVerticalPosition(true));
				
				// footer
				//commented by ankit
				//this if else not needed now, because we are processing result 
				// on the basis of the grades.
//				if(flagPass == true)
//				{
					Table totalMarksTable = new Table(4,2);			
					totalMarksTable.setBorderColor(new Color(255,255,255));
					Cell c4 = new Cell(new Phrase("TOTAL CREDITS : ",cellFont));
					c4.setHeader(true);
					c4.setBorder(Rectangle.NO_BORDER);
					totalMarksTable.addCell(c4);
					
					c4 = new Cell(new Phrase("THEORY : "+totalTheoryCredits,cellFont));
					c4.setBorder(Rectangle.NO_BORDER);
					totalMarksTable.addCell(c4);
					
					c4 = new Cell(new Phrase("SEM. GRADE POINT AVG. : ",cellFont));
					c4.setBorder(Rectangle.NO_BORDER);
					totalMarksTable.addCell(c4);
					
					c4 = new Cell(new Phrase("THEORY : "+weightTheory,cellFont));
					c4.setBorder(Rectangle.NO_BORDER);
					totalMarksTable.addCell(c4);
					
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
					document.add(totalMarksTable);	
//				}
//				else 
//				{
//					 //cb.setTextMatrix(80, writer.getVerticalPosition(true)-10); 
//		                //BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, 
//		            	//        BaseFont.WINANSI, BaseFont.EMBEDDED);
//		               // cb.setFontAndSize(bf, 9f);
//		                //cb.showText("WEIGHTED % -");
//						System.out.println("in fail");
//		                Table weightFailTable = new Table(1,2);
//		                weightFailTable.setBorderColor(new Color(255,255,255));
//		                Cell c4 = new Cell(new Phrase("WEIGHTED % -",cellFont));
//		                c4.setHeader(true);
//		                c4.setRowspan(2);
//		                c4.setBorder(Rectangle.NO_BORDER);
//		                c4.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		                weightFailTable.addCell(c4);		                
//		                document.add(weightFailTable);
//		                document.add(Chunk.NEWLINE);
//		                document.add(Chunk.NEWLINE);
//		                document.add(Chunk.NEWLINE);
//				}
				
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
				
				c5 = new Cell(new Phrase("THEORY",cellFont));
				c5.setBorder(Rectangle.NO_BORDER);
				semesterWiseMarks.addCell(c5);
				
				System.out.println("goin to print marks");
				
				// this loop will add student semester theory marks into GRADE POINT AVERAGE IN SEMESTERS section.
				for(int i=1;i<=8;i++)
				{			
					if(i<=totalMarksDetailsForPreviousSem.size()){
						marks = totalMarksDetailsForPreviousSem.get(i-1).getWeightTheory();
						c5 =  new Cell(new Phrase(marks,cellFont));
						cumulativeTheory = cumulativeTheory+Float.parseFloat(marks)*Float.parseFloat(totalMarksDetailsForPreviousSem.get(i-1).getTotalTheoryCredits());
						credits = credits + Float.parseFloat(totalMarksDetailsForPreviousSem.get(i-1).getTotalTheoryCredits());
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
						
				document.add(semesterWiseMarks);
				
				// this section add Cumulative Grade point Average section if progressCardType is Final Result Card.
				if(progressCardType.equalsIgnoreCase("FINAL RESULT CARD"))
				{
					Table cumulativeTable = new Table(4,6);
					cumulativeTable.setBorderColor(new Color(255,255,255));
					
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
					
					
					c6 = new Cell(new Phrase("THEORY ",cellFont));
					c6.setHorizontalAlignment(Element.ALIGN_LEFT);
					c6.setBorder(Rectangle.NO_BORDER);
					cumulativeTable.addCell(c6);

					c6 = new Cell(new Phrase(cummulativeForFC!=null?cummulativeForFC.getCummulativeTheoryCgpa():"--",cellFont));
					c6.setHorizontalAlignment(Element.ALIGN_CENTER);
					c6.setBorder(Rectangle.NO_BORDER);
					c6.setColspan(2);
					cumulativeTable.addCell(c6);
					
					c6 = new Cell(new Phrase(cummulativeForFC!=null?cummulativeForFC.getTheoryDivision():"--",cellFont));
					c6.setHorizontalAlignment(Element.ALIGN_CENTER);
					c6.setBorder(Rectangle.NO_BORDER);
					cumulativeTable.addCell(c6);
					
//					c6 = new Cell(new Phrase(calcaulateGrade(cumulativeTheory),cellFont));
//					c6.setHorizontalAlignment(Element.ALIGN_CENTER);
//					c6.setBorder(Rectangle.NO_BORDER);
//					cumulativeTable.addCell(c6);
					
					c6 = new Cell(new Phrase("PRACTICAL",cellFont));
					c6.setHorizontalAlignment(Element.ALIGN_LEFT);
					c6.setBorder(Rectangle.NO_BORDER);
					cumulativeTable.addCell(c6);
					
					c6 = new Cell(new Phrase(cummulativeForFC!=null?cummulativeForFC.getCummulativePracticalCgpa():"--",cellFont));
					c6.setHorizontalAlignment(Element.ALIGN_CENTER);
					c6.setBorder(Rectangle.NO_BORDER);
					c6.setColspan(2);
					cumulativeTable.addCell(c6);
//					c6 = new Cell(new Phrase(cummulativeForFC.getCummulativePracticalCgpa(),cellFont));
//					c6.setHorizontalAlignment(Element.ALIGN_CENTER);
//					c6.setBorder(Rectangle.NO_BORDER);
//					cumulativeTable.addCell(c6);

					c6 = new Cell(new Phrase(cummulativeForFC!=null?cummulativeForFC.getPracticalDivision():"--",cellFont));
					c6.setHorizontalAlignment(Element.ALIGN_CENTER);
					c6.setBorder(Rectangle.NO_BORDER);
					cumulativeTable.addCell(c6);					
					
					c6 = new Cell(new Phrase("COMBINED GRADE POINT AVERAGE (THOERY + PRACTICAL) : "+cummulativeForFC!=null?"--":cummulativeForFC.getDivision(),cellFont));
					c6.setHorizontalAlignment(Element.ALIGN_LEFT);
					c6.setColspan(4);
					c6.setBorder(Rectangle.NO_BORDER);
					cumulativeTable.addCell(c6);					
					
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
		}catch(IOException e)
		{
			System.out.println(e.getMessage());
			return false;			
		}
		catch(BadElementException be)
		{
			System.out.println(be.getMessage());
			return false;
		}
		catch(DocumentException de)
		{
			System.out.println(de.getMessage());
			return false;
		}		
		return true;

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
