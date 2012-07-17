/**
 * @(#) FinalSemesterResultStatisticsCategoryWiseController.java
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
package in.ac.dei.edrp.cms.controller.reportgeneration;
import in.ac.dei.edrp.cms.dao.report.ReportDao;
import in.ac.dei.edrp.cms.dao.reportgeneration.FinalSemesterResultStatisticsCategoryWiseDAO;
import in.ac.dei.edrp.cms.domain.report.ReportLogBean;
import in.ac.dei.edrp.cms.domain.reportgeneration.FinalSemesterResultStatisticsCategoryWise;
import in.ac.dei.edrp.cms.domain.reportgeneration.SubjectWiseMeritList;
import in.ac.dei.edrp.cms.utility.ReportPath;
import in.ac.dei.edrp.cms.utility.ReportPathBean;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.lowagie.text.Chunk;
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
 * the final semester result statistics (category wise)
 * @author Nupur Dixit
 * @date 12 Nov 2011
 * @version 1.0
 */
public class FinalSemesterResultStatisticsCategoryWiseController extends MultiActionController{

	private FinalSemesterResultStatisticsCategoryWiseDAO finalSemesterResultStatisticsCategoryWiseDAO;
	
	public void setFinalSemesterResultStatisticsCategoryWiseDAO(
			FinalSemesterResultStatisticsCategoryWiseDAO finalSemesterResultStatisticsCategoryWiseDAO) {
		this.finalSemesterResultStatisticsCategoryWiseDAO = finalSemesterResultStatisticsCategoryWiseDAO;
	}
	private ReportDao reportDao;
	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}
	/**
     * Method to get the session list
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView getSessionList(HttpServletRequest request,HttpServletResponse response)throws Exception {
		FinalSemesterResultStatisticsCategoryWise finalSemesterResultStatisticsCategoryWise=new FinalSemesterResultStatisticsCategoryWise();
		
		finalSemesterResultStatisticsCategoryWise.setEntityId(request.getParameter("selectedEntityId"));
		finalSemesterResultStatisticsCategoryWise.setUniversityId(request.getParameter("selectedEntityId").substring(0, 4));
		
		System.out.println(finalSemesterResultStatisticsCategoryWise.getUniversityId());
		
		List<FinalSemesterResultStatisticsCategoryWise> sessionDatesList = finalSemesterResultStatisticsCategoryWiseDAO.getSessionList(finalSemesterResultStatisticsCategoryWise);
		return new ModelAndView("SubjectCategoryWiseStudentList/SessionDates","sessionDates", sessionDatesList);
			
	}
	
	/**
     * this method will get the current session
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView getCurrentSession(HttpServletRequest request,HttpServletResponse response)throws Exception {
		SubjectWiseMeritList subjectWiseMeritList=new SubjectWiseMeritList();
		subjectWiseMeritList.setUniversityId(request.getParameter("selectedEntityId").substring(0, 4));
		List<SubjectWiseMeritList> sessionDates = finalSemesterResultStatisticsCategoryWiseDAO.getCurrentSession(subjectWiseMeritList);
		return new ModelAndView("SubjectCategoryWiseStudentList/SessionDates","sessionDates", sessionDates);			
	}
	
	
	/**
     * Method for generate the PDF.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView generatePDF(HttpServletRequest request,HttpServletResponse response)throws Exception {	
		HttpSession session=request.getSession(true);
		FinalSemesterResultStatisticsCategoryWise finalSemesterResultStatisticsCategoryWise=new FinalSemesterResultStatisticsCategoryWise();
		finalSemesterResultStatisticsCategoryWise.setEntityId(request.getParameter("entityId"));
		finalSemesterResultStatisticsCategoryWise.setSessionStartDate(request.getParameter("fromSession"));
		finalSemesterResultStatisticsCategoryWise.setSessionEndDate(request.getParameter("toSession"));
		finalSemesterResultStatisticsCategoryWise.setUniversityId(session.getAttribute("universityId").toString());
		String sem = request.getParameter("semesterType");
		System.out.println("semester type "+sem);
		String semAll = "2";		
		/*if(sem.equalsIgnoreCase("odd")){
			sem="1";
			semAll = "2";
		}
		else if(sem.equalsIgnoreCase("even")){
			sem="0";
			semAll = "2";
		}
		else{
			sem="0";
			semAll = "1";
		}*/
		if(sem.contains("Odd")){
			sem="1";
			semAll = "2";
		}
		else if(sem.contains("Even")){
			sem="0";
			semAll = "2";
		}
		else{
			sem="0";
			semAll = "1";
		}
		finalSemesterResultStatisticsCategoryWise.setSem(sem);
		finalSemesterResultStatisticsCategoryWise.setSemAll(semAll);		
		List <FinalSemesterResultStatisticsCategoryWise> getProgramDetail = finalSemesterResultStatisticsCategoryWiseDAO.getProgramDetails(finalSemesterResultStatisticsCategoryWise);
		System.out.println("siiiiize is:"+ getProgramDetail.size());
		String result = generatePDF(request,getProgramDetail,finalSemesterResultStatisticsCategoryWise);
		String message ="";			
		if(result=="true"){
			message = "true";
		}
		else{
			message = "false-There is some error in PDF Generation kindly view the error log for more information";
		}
		return new ModelAndView("activitymaster/SubmitSuccesful", "message", message);
//		return new ModelAndView("ManualProcess/MyException","exception",message);	
	}
	
	public String generatePDF(HttpServletRequest request,List<FinalSemesterResultStatisticsCategoryWise> programDetails, FinalSemesterResultStatisticsCategoryWise beanPassed){				
		String result = "false";		
		String logResult="";		
		int reportGenerated = 0;
		File file;
		HttpSession session=request.getSession(true);
		try{
			List<FinalSemesterResultStatisticsCategoryWise> completeDetails=new ArrayList<FinalSemesterResultStatisticsCategoryWise>();
			String sessionStartDate=beanPassed.getSessionStartDate();
			String sessionEndDate=beanPassed.getSessionEndDate();
//			System.out.println("session dates:"+sessionStartDate+" : "+sessionEndDate);			
			String sep = System.getProperty("file.separator");
			//*********nupur code *******************
			ReportPathBean reportPathBean = new ReportPathBean(session.getAttribute("universityId").toString(),request.getParameter("entityId"),
					request.getParameter("programId"),request.getParameter("branchId"),request.getParameter("specializationId"),
					request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
					request.getParameter("enrollmentNumber"),request.getParameter("rollNumber"),request.getParameter("reportCode"),
					request.getParameter("reportType"),request.getParameter("fromSession"),request.getParameter("toSession"),request.getParameter("semesterType"));
			
			String reportPath = ReportPath.getPath(reportPathBean);				
			System.out.println("here in controller of consolidated chart after path generation "+reportPath);
			String filePath = this.getServletContext().getRealPath("/");
			filePath=filePath+reportPath;
			file = new File(filePath);
			file.mkdirs();
			//***************************************
			/*filePath = getServletContext().getRealPath("/")+"PDF";
			filePath=filePath +sep+session.getAttribute("universityName").toString()+sep+"FinalSemesterResultStatisticsCategoryWise"+
			sep+"Session-"+sessionStartDate.substring(0, 4)+"-"+sessionEndDate.substring(0, 4);			
			file = new File(filePath);
			file.mkdirs();*/
			Document document = new Document(PageSize.A4.rotate());		
//			PdfWriter.getInstance(document,new FileOutputStream(filePath+sep+"Final Semester Result Statistics Category Wise_"+
//					sessionStartDate.substring(0, 4)+"-"+sessionEndDate.substring(0, 4)+".pdf"));
			Chunk line = new Chunk(
			"___________________________________________________________________________________________________________________");
			Phrase header1= new Phrase(session.getAttribute("universityName").toString().toUpperCase(),FontFactory.getFont(
					FontFactory.HELVETICA, 12, Font.BOLD,new Color(0, 0, 0)));

			Phrase header2= new Phrase("\nFINAL SEMESTER RESULT STATISTICS (CATEGORY WISE) : "+ sessionStartDate.substring(0, 4)+"-"+
					sessionEndDate.substring(0, 4)+" ("+request.getParameter("semesterType")+") ",FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD,new Color(0, 0, 0)));		
			Phrase headers=new Phrase();
			headers.add(header1);
			headers.add(header2); 

			Font cellFont = new Font(Font.HELVETICA, 8);
		
			PdfPTable headerTable = new PdfPTable(new float[] {5,3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2});
			headerTable.setWidthPercentage(100f);	
			headerTable.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell c1 = new PdfPCell(new Phrase("", cellFont));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_TOP);
			addCell(c1, cellFont, headerTable,"F-CL-BR-S",1);
			addCell(c1, cellFont, headerTable,"",1);
			addCell(c1, cellFont, headerTable,"<--------------------------------MALES-------------------------->",5);
			c1.setColspan(5);
			addCell(c1, cellFont, headerTable,"<-------------------------------FEMALES------------------------->",5);
			c1.setColspan(5);
			addCell(c1, cellFont, headerTable,"<--------------------------------TOTAL-------------------------->",5);

			addCell(c1, cellFont, headerTable,"",1);
			addCell(c1, cellFont, headerTable,"",1);
			addCell(c1, cellFont, headerTable,"GN",1);
			addCell(c1, cellFont, headerTable,"BC",1);
			addCell(c1, cellFont, headerTable,"SC",1);
			addCell(c1, cellFont, headerTable,"ST",1);
			addCell(c1, cellFont, headerTable,"TOTAL",1);
			addCell(c1, cellFont, headerTable,"GN",1);
			addCell(c1, cellFont, headerTable,"BC",1);
			addCell(c1, cellFont, headerTable,"SC",1);
			addCell(c1, cellFont, headerTable,"ST",1);
			addCell(c1, cellFont, headerTable,"TOTAL",1);
			addCell(c1, cellFont, headerTable,"GN",1);
			addCell(c1, cellFont, headerTable,"BC",1);
			addCell(c1, cellFont, headerTable,"SC",1);
			addCell(c1, cellFont, headerTable,"ST",1);
			addCell(c1, cellFont, headerTable,"TOTAL",1);
			
			headers.add(headerTable);
			headers.add(line);
			HeaderFooter header = new HeaderFooter(headers,false);
			header.setBorderWidth(0);
			header.setAlignment(Element.ALIGN_CENTER);

			String toDay = new SimpleDateFormat("dd MMM yyyy").format(new Date());
			Chunk space = new Chunk(
			"                                                                                                                                                                      ");
			Phrase footerData = new Phrase("Date : "+toDay+space+"Page  :-   ");
			HeaderFooter footer = new HeaderFooter(footerData, true);		
			footer.setBorderWidth(0);
			footer.setAlignment(Element.ALIGN_CENTER);
			
			PdfPTable studentTable = new PdfPTable(new float[] {5,3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2});
			studentTable.setWidthPercentage(100f);	
			studentTable.setHorizontalAlignment(Element.ALIGN_CENTER);
			FinalSemesterResultStatisticsCategoryWise entityObject=new FinalSemesterResultStatisticsCategoryWise();
			FinalSemesterResultStatisticsCategoryWise finalSemesterResultStatistics=new FinalSemesterResultStatisticsCategoryWise();
			finalSemesterResultStatistics.setEntityId(request.getParameter("entityId"));
			finalSemesterResultStatistics.setUniversityId(session.getAttribute("universityId").toString());
			entityObject=finalSemesterResultStatisticsCategoryWiseDAO.getEntityInfo(finalSemesterResultStatistics);
//			System.out.println("size of program details :"+programDetails.size());
			for(FinalSemesterResultStatisticsCategoryWise singleProgram:programDetails){										
				finalSemesterResultStatistics.setProgramCourseKey(singleProgram.getProgramCourseKey());
				finalSemesterResultStatistics.setProgramId(singleProgram.getProgramId());
				finalSemesterResultStatistics.setProgramCode(singleProgram.getProgramCode());
				finalSemesterResultStatistics.setBranchId(singleProgram.getBranchId());
				finalSemesterResultStatistics.setSpecializationId(singleProgram.getSpecializationId());
				finalSemesterResultStatistics.setSemesterCode(singleProgram.getSemesterCode());				
				finalSemesterResultStatistics.setProgramCourseKey2(singleProgram.getProgramCourseKey().substring(0, 11)+"%");
				//***********************nupur - 3/11/2011******************************
				finalSemesterResultStatistics.setSemesterStartDate(singleProgram.getSemesterStartDate());
				finalSemesterResultStatistics.setSemesterEndDate(singleProgram.getSemesterEndDate());
				finalSemesterResultStatistics.setSessionStartDate(sessionStartDate);
				finalSemesterResultStatistics.setSessionEndDate(sessionEndDate);
				finalSemesterResultStatistics.setUniversityId(session.getAttribute("universityId").toString());
				//**********************nupur end ***************************************
				completeDetails=finalSemesterResultStatisticsCategoryWiseDAO.getCompleteDetails(finalSemesterResultStatistics);
				System.out.println("size of complete details :"+completeDetails.size());
				
				String[] value={"ENROLLED","APPEARED","UFM","PASSED","I-DISTINCTION","I-DIV","II-DIV","PASS",
						"ELIG. FOR REMEDIAL","FAILED 1ST ATTEMPT","FAILED 2ND ATTEMPT","PASSED%"};
				char gender;
				String category = "";
				HashMap<String,Double> hmPassed = new HashMap<String,Double>();
				HashMap<String,Double> hmAppeared = new HashMap<String,Double>();

				for(int j=0;j<value.length;j++){
					int mgn = 0,msc = 0,mst = 0,mbc = 0,mtotal=0;
					int fgn = 0,fsc = 0,fst = 0,fbc = 0,ftotal=0;
					int tgn,tsc,tst,tbc,ttotal;
					tgn=tsc=tst=tbc=ttotal=0;
					if(j==0){
						addCell(c1, cellFont, studentTable,entityObject.getEntityCode()+"-"+singleProgram.getProgramCode()+"-"+
								singleProgram.getBranchId()+"-"+singleProgram.getSemesterCode(),1);
					}
					else{
						addCell(c1, cellFont, studentTable,"",1);
					}
					if(value[j].equalsIgnoreCase("passed%")){
						DecimalFormat df = new DecimalFormat("0.00");					
						addCell(c1, cellFont, studentTable,value[j],1);
						addCell(c1, cellFont, studentTable,String.valueOf(df.format((hmPassed.get("MGN")/hmAppeared.get("MGN"))*100)),1);
						addCell(c1, cellFont, studentTable,String.valueOf(df.format((hmPassed.get("MBC")/hmAppeared.get("MBC"))*100)),1);
						addCell(c1, cellFont, studentTable,String.valueOf(df.format((hmPassed.get("MSC")/hmAppeared.get("MSC"))*100)),1);
						addCell(c1, cellFont, studentTable,String.valueOf(df.format((hmPassed.get("MST")/hmAppeared.get("MST"))*100)),1);
						addCell(c1, cellFont, studentTable,String.valueOf(df.format((hmPassed.get("MTOTAL")/hmAppeared.get("MTOTAL"))*100)),1);
						addCell(c1, cellFont, studentTable,String.valueOf(df.format((hmPassed.get("FGN")/hmAppeared.get("FGN"))*100)),1);
						addCell(c1, cellFont, studentTable,String.valueOf(df.format((hmPassed.get("FBC")/hmAppeared.get("FBC"))*100)),1);
						addCell(c1, cellFont, studentTable,String.valueOf(df.format((hmPassed.get("FSC")/hmAppeared.get("FSC"))*100)),1);
						addCell(c1, cellFont, studentTable,String.valueOf(df.format((hmPassed.get("FST")/hmAppeared.get("FST"))*100)),1);
						addCell(c1, cellFont, studentTable,String.valueOf(df.format((hmPassed.get("FTOTAL")/hmAppeared.get("FTOTAL"))*100)),1);
						addCell(c1, cellFont, studentTable,String.valueOf(df.format((hmPassed.get("TGN")/hmAppeared.get("TGN"))*100)),1);
						addCell(c1, cellFont, studentTable,String.valueOf(df.format((hmPassed.get("TBC")/hmAppeared.get("TBC"))*100)),1);
						addCell(c1, cellFont, studentTable,String.valueOf(df.format((hmPassed.get("TSC")/hmAppeared.get("TSC"))*100)),1);
						addCell(c1, cellFont, studentTable,String.valueOf(df.format((hmPassed.get("TST")/hmAppeared.get("TST"))*100)),1);
						addCell(c1, cellFont, studentTable,String.valueOf(df.format((hmPassed.get("TTOTAL")/hmAppeared.get("TTOTAL"))*100)),1);
						addCell(c1, cellFont, studentTable, "  ", 17);		
					}
					else{
						int i=0;
						while(i<completeDetails.size()){
							System.out.println("complete detailsize "+completeDetails.size());
							System.out.println("value :"+value[j]+" value from list "+completeDetails.get(i).getValue());
							char gender1[] = completeDetails.get(i).getGender().toCharArray();
							gender = gender1[0];
							System.out.println("gender "+gender);
							category = completeDetails.get(i).getCategory();
							System.out.println("category :"+category);
							if(completeDetails.get(i).getValue().equalsIgnoreCase(value[j])){
								switch(gender){
								case 'F': if(category.equalsIgnoreCase("GN")){
									fgn = Integer.valueOf(completeDetails.get(i).getTotal());
									break;
								}
								if(category.equalsIgnoreCase("sc")){
									fsc = Integer.valueOf(completeDetails.get(i).getTotal());
									break;
								}
								if(category.equalsIgnoreCase("st")){
									fst = Integer.valueOf(completeDetails.get(i).getTotal());
									break;
								}
								if(category.equalsIgnoreCase("bc")){
									System.out.println("inside bc "+completeDetails.get(i).getTotal());
									fbc = Integer.valueOf(completeDetails.get(i).getTotal());
									break;
								}

								case 'M': if(category.equalsIgnoreCase("GN")){
									mgn = Integer.valueOf(completeDetails.get(i).getTotal());
									break;
								}
								if(category.equalsIgnoreCase("sc")){
									msc = Integer.valueOf(completeDetails.get(i).getTotal());
									break;
								}
								if(category.equalsIgnoreCase("st")){
									mst = Integer.valueOf(completeDetails.get(i).getTotal());
									break;
								}
								if(category.equalsIgnoreCase("bc")){
									mbc = Integer.valueOf(completeDetails.get(i).getTotal());
									break;
								}																	
								}
								completeDetails.remove(i);
								i=0;
							}
							else{
								i++;
							}
//							System.out.println(mgn+" : "+mbc+" : "+msc+" : "+mst+" : "+mtotal);
//							System.out.println(fgn+" : "+fbc+" : "+fsc+" : "+fst+" : "+ftotal);
//							System.out.println(tgn+" : "+tbc+" : "+tsc+" : "+tst+" : "+ttotal);
						}// end complete details for
						mtotal = mgn+mbc+msc+mst;
						ftotal = fgn+fbc+fsc+fst;
						tgn = mgn+fgn;
						tsc = msc+fsc;
						tst = mst+fst;
						tbc = mbc+fbc;
						ttotal = mtotal+ftotal;
						if(value[j].equalsIgnoreCase("appeared")){
							hmAppeared.put("MGN", new Double(mgn));
							hmAppeared.put("MBC", new Double(mbc));
							hmAppeared.put("MSC", new Double(msc));
							hmAppeared.put("MST", new Double(mst));
							hmAppeared.put("MTOTAL", new Double(mtotal));
							hmAppeared.put("FGN", new Double(fgn));
							hmAppeared.put("FBC", new Double(fbc));
							hmAppeared.put("FSC", new Double(fsc));
							hmAppeared.put("FST", new Double(fst));
							hmAppeared.put("FTOTAL", new Double(ftotal));
							hmAppeared.put("TGN", new Double(tgn));
							hmAppeared.put("TBC", new Double(tbc));
							hmAppeared.put("TSC", new Double(tsc));
							hmAppeared.put("TST", new Double(tst));
							hmAppeared.put("TTOTAL", new Double(ttotal));										
						}
						if(value[j].equalsIgnoreCase("passed")){
							hmPassed.put("MGN", new Double(mgn));
							hmPassed.put("MBC", new Double(mbc));
							hmPassed.put("MSC", new Double(msc));
							hmPassed.put("MST",  new Double(mst));
							hmPassed.put("MTOTAL", new Double(mtotal));
							hmPassed.put("FGN", new Double(fgn));
							hmPassed.put("FBC", new Double(fbc));
							hmPassed.put("FSC", new Double(fsc));
							hmPassed.put("FST", new Double(fst));
							hmPassed.put("FTOTAL", new Double(ftotal));
							hmPassed.put("TGN", new Double(tgn));
							hmPassed.put("TBC", new Double(tbc));
							hmPassed.put("TSC", new Double(tsc));
							hmPassed.put("TST",  new Double(tst));
							hmPassed.put("TTOTAL", new Double(ttotal));						
						}

						addCell(c1, cellFont, studentTable,value[j],1);
						addCell(c1, cellFont, studentTable,String.valueOf(mgn),1);
						addCell(c1, cellFont, studentTable,String.valueOf(mbc),1);
						addCell(c1, cellFont, studentTable,String.valueOf(msc),1);
						addCell(c1, cellFont, studentTable,String.valueOf(mst),1);
						addCell(c1, cellFont, studentTable,String.valueOf(mtotal),1);
						addCell(c1, cellFont, studentTable,String.valueOf(fgn),1);
						addCell(c1, cellFont, studentTable,String.valueOf(fbc),1);
						addCell(c1, cellFont, studentTable,String.valueOf(fsc),1);
						addCell(c1, cellFont, studentTable,String.valueOf(fst),1);
						addCell(c1, cellFont, studentTable,String.valueOf(ftotal),1);
						addCell(c1, cellFont, studentTable,String.valueOf(tgn),1);
						addCell(c1, cellFont, studentTable,String.valueOf(tbc),1);
						addCell(c1, cellFont, studentTable,String.valueOf(tsc),1);
						addCell(c1, cellFont, studentTable,String.valueOf(tst),1);
						addCell(c1, cellFont, studentTable,String.valueOf(ttotal),1);
					}//end else	
				}// end value for
			}//end for program list
			PdfWriter.getInstance(document, new FileOutputStream(filePath+sep+request.getParameter("reportDescription")+".pdf"));
			/*PdfWriter.getInstance(document,new FileOutputStream(filePath+sep+"Final Semester Result Statistics Category Wise_"+
					entityObject.getEntityName()+"_"+request.getParameter("semester")+"-semester_"+sessionStartDate.substring(0, 4)+"-"+sessionEndDate.substring(0, 4)+".pdf"));*/
			document.setHeader(header);
			document.setFooter(footer);		
			document.open();
			document.add(studentTable);
			document.close();
			result = "true";
		}catch(Exception e){		
			result = "false";
			ReportLogBean reportErrorBean = new ReportLogBean(session.getAttribute("universityId").toString(),request.getParameter("programId"),
					request.getParameter("branchId"),request.getParameter("specializationId"),request.getParameter("entityId"),
					request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
					request.getParameter("reportCode"),"",request.getParameter("rollNumber"),
					request.getParameter("fromSession").substring(0, 4)+"-"+request.getParameter("toSession").substring(2,4));
			logResult = reportDao.insertReportErrorLog(reportErrorBean);						
			System.out.println("nupur : some exception in report generation");
			System.out.println("exception :"+e);
		}
		try{
			if(result.equalsIgnoreCase("true")){
				reportGenerated = reportGenerated+1;
				ReportLogBean reportControlBean = new ReportLogBean(session.getAttribute("universityId").toString(),request.getParameter("entityId"),
						request.getParameter("programId"),request.getParameter("branchId"),request.getParameter("specializationId"),
						request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
						request.getParameter("reportCode"),String.valueOf(reportGenerated),"No",
						request.getParameter("fromSession"),request.getParameter("toSession"),request.getParameter("semesterType"),
						session.getAttribute("userName").toString());
				reportControlBean.setIsGenerated(request.getParameter("isGenerated")==null?"no":"yes");			
				System.out.println("before control log insert");		
				logResult = reportDao.insertReportLog(reportControlBean);
				System.out.println("report control log result "+logResult);
		}
		}catch(Exception e){
			System.out.println("some exception in log entry "+e);
		}
		return result;
	}

	public static final void addCell(PdfPCell c1, Font cellFont, PdfPTable chartTable, String s, int colSpan) {
		try {
			c1 = new PdfPCell(new Phrase(s, cellFont));
		} catch (Exception e) {
			e.printStackTrace();
		}
		c1.setBorderWidth(0);
		c1.setColspan(colSpan);

		chartTable.addCell(c1);
	}
}