/*
 * @(#) ConsolidatedChartGenerator.java
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
package in.ac.dei.edrp.cms.controller.consolidatedchart;

import in.ac.dei.edrp.cms.dao.report.ReportDao;
import in.ac.dei.edrp.cms.domain.consolidatedchart.ConsolidatedChartBean;
import in.ac.dei.edrp.cms.domain.report.ReportLogBean;
import in.ac.dei.edrp.cms.utility.ReportPath;
import in.ac.dei.edrp.cms.utility.ReportPathBean;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * this is Server side PDF generator class for Consolidated Chart report
 * 
 * @version 1.0 8 September 2011
 * @author MOHD AMIR
 */
public class ConsolidatedChartGeneration extends AbstractPdfView {
	int reportGenerated = 0;
	private ReportDao reportDao;
	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}
	/**
	 * This is default method of AbstractPdfView used for generating PDF
	 * document
	 * 
	 * @param map
	 *            , Object of Map
	 * @param document
	 *            , The output PDF document
	 * @param pdfWriter
	 *            , Object of PdfWriter
	 * @param request
	 *            , HttpServletRequest
	 * @param response
	 *            , HttpServletResponse
	 */
	@SuppressWarnings({"unchecked" })
	protected void buildPdfDocument(Map map, Document document,PdfWriter pdfWriter, HttpServletRequest request,HttpServletResponse response)throws Exception {
		
	}
	@SuppressWarnings("unchecked")
	public String buildPdf(List<ConsolidatedChartBean> chartDataList,HttpServletRequest request,HttpServletResponse response,String isFinalSem) throws Exception {
		HttpSession session = request.getSession(true);
		String logResult="";
		String reportResult="";
		try{
			String sep = System.getProperty("file.separator");
			//List<ConsolidatedChartBean> chartDataList = (List<ConsolidatedChartBean>) map.get("chartDataList");
			ConsolidatedChartBean headerData = chartDataList.get(chartDataList.size() - 1);
			//String entity = headerData.getEntity();
			String program = headerData.getProgram();
			String branch = headerData.getBranch();
			String specialization = headerData.getSpecialization();
			String semester = headerData.getSemester();
			String sessionStartDate = headerData.getSessionStartDate();
			String sessionEndDate = headerData.getSessionEndDate();
			String universityName = session.getAttribute("universityName") + "";
					
			String reportSession = sessionStartDate.substring(0, 4) + "-"+ sessionEndDate.substring(0, 4);
			Document document = new Document(PageSize.A4.rotate());
			//*********nupur code *******************
			ReportPathBean reportPathBean = new ReportPathBean(session.getAttribute("universityId").toString(),request.getParameter("entityId"),
				request.getParameter("programId"),request.getParameter("branchId"),request.getParameter("specializationId"),
				request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
				request.getParameter("enrollmentNumber"),request.getParameter("rollNumber"),request.getParameter("reportCode"),
				request.getParameter("reportType"),request.getParameter("fromSession"),request.getParameter("toSession"),request.getParameter("semesterType"));
			String reportPath = ReportPath.getPath(reportPathBean);				
			System.out.println("here in controller of consolidated chart after path generation "+reportPath);
			//***************************************
			
			String path = this.getServletContext().getRealPath("/");
			path=path+reportPath;
			File file = new File(path);
			file.mkdirs();

			PdfWriter.getInstance(document, new FileOutputStream(path+sep+request.getParameter("reportDescription")+".pdf"));
			String branchNew = branch.equalsIgnoreCase("none")?"":branch ;
			String speciNew = specialization.equalsIgnoreCase("none")?"":specialization;
			String toDay = new SimpleDateFormat("dd MMM yyyy").format(new Date());				
			//****************DEVENDRA CODE STARTS****************************
			Font headerFont = new Font(Font.HELVETICA,8,Font.BOLD,new Color(0, 0, 0));														
			Font cellFont = new Font(Font.HELVETICA, 8);
			PdfPTable headerTable = new PdfPTable(new float[] {1,1});
			headerTable.setWidthPercentage(100f);				
			headerTable.setHorizontalAlignment(Element.ALIGN_LEFT);
			addHeaderCell(headerFont, headerTable, universityName.toUpperCase(), 1,"left");			
			addHeaderCell(headerFont, headerTable,"CONSOLIDATED CHART", 1,"left");
			PdfPTable headerTable1 = new PdfPTable(new float[] {1});
			headerTable1.setWidthPercentage(100f);				
			headerTable1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			addHeaderCell(headerFont, headerTable1, "RUN DATE:- "+toDay, 1,"right");
			
			PdfPTable headerTable2=new PdfPTable(new float[] {1,1,1,1,1});;
			headerTable2.setWidthPercentage(100f);				
			headerTable2.setHorizontalAlignment(Element.ALIGN_LEFT);
			if(branchNew=="" && speciNew!=""){					
				addHeaderCell(headerFont, headerTable2,"CLASS: "+program, 1,"left");				
				addHeaderCell(headerFont, headerTable2,"SPECIALIZATION: "+speciNew, 2,"left");
				addHeaderCell(headerFont, headerTable2,semester.toUpperCase(), 1,"left");
				addHeaderCell(headerFont, headerTable2,"SESSION:- " + reportSession, 1,"left");
			}
			else if(speciNew=="" && branchNew!=""){
				addHeaderCell(headerFont, headerTable2,"CLASS: "+program, 1,"left");
				addHeaderCell(headerFont, headerTable2,"BRANCH: "+branchNew, 2,"left");			
				addHeaderCell(headerFont, headerTable2,semester.toUpperCase(), 1,"left");
				addHeaderCell(headerFont, headerTable2,"SESSION:- " + reportSession, 1,"left");
			}
				
			else if(speciNew=="" && branchNew==""){
				addHeaderCell(headerFont, headerTable2,"CLASS: "+program, 1,"left");
				addHeaderCell(headerFont, headerTable2,"", 1,"left");
				addHeaderCell(headerFont, headerTable2,semester.toUpperCase(), 2,"left");
				addHeaderCell(headerFont, headerTable2,"SESSION:- " + reportSession, 1,"left");														
			}
			else{
				addHeaderCell(headerFont, headerTable2,"CLASS: "+program, 1,"left");
				addHeaderCell(headerFont, headerTable2,"BRANCH: "+branchNew, 1,"left");
				addHeaderCell(headerFont, headerTable2,"SPECIALIZATION: "+speciNew, 1,"left");
				addHeaderCell(headerFont, headerTable2,semester.toUpperCase(), 1,"left");
				addHeaderCell(headerFont, headerTable2,"SESSION:- " + reportSession, 1,"left");		
			}
			Phrase headerPhrase=new Phrase();
			headerPhrase.add(headerTable);
			headerPhrase.add(headerTable1);
			headerPhrase.add(headerTable2);
			String headerText1="";
			if(isFinalSem.equalsIgnoreCase("F")){
				headerText1="\nNAME / ENROL# \t\t\t\t\t\t\t\t\t\t\t\t\t CT \t\t\t\t SX \t\t\t\t AT  \t\t\t\t RES.   \t\t\t\t CGPA\nID  \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tSGPA \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t DIV.";
			}
			else{
				headerText1="\nNAME / ENROL# \t\t\t\t\t\t\t\t\t\t\t\t\t CT \t\t\t\t SX \t\t\t\t AT  \t\t\t\t RES.\nID \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tSGPA";
			}
			
			
			Phrase headerPhrase1=new Phrase(headerText1,cellFont);
			Phrase mainHeaderPhrase=new Phrase();
			mainHeaderPhrase.add(headerPhrase);
			mainHeaderPhrase.add(headerPhrase1);
			HeaderFooter header=new HeaderFooter(mainHeaderPhrase, false);	
			header.setBorder(Rectangle.NO_BORDER);
			document.setHeader(header);			
			Phrase pagePhrase=new Phrase("Page: ",cellFont);
			HeaderFooter footer=new HeaderFooter(pagePhrase, true);
			footer.setBorder(Rectangle.NO_BORDER);
			footer.setAlignment(HeaderFooter.ALIGN_RIGHT);
			document.setFooter(footer);
			document.open();
			//**************************DEVENDRA ENDS**************************
			Map<String,List<ConsolidatedChartBean>> courseCount = new HashMap<String,List<ConsolidatedChartBean>>();
			List<ConsolidatedChartBean> courseList = null ;
			for(ConsolidatedChartBean mine:chartDataList){
				String rollNumber = mine.getRollNo();	
				if(courseCount.containsKey(rollNumber)){
					courseList.add(mine);
					courseCount.put(rollNumber, courseList);					
				}
				else{
					courseList = new ArrayList<ConsolidatedChartBean>();
					courseList.add(mine);
					courseCount.put(rollNumber, courseList);					
				}
			}
			
			//****************
			PdfPTable mainOuterTable = new PdfPTable(new float[] {1.4f,3});
			mainOuterTable.setWidthPercentage(100f);	
			mainOuterTable.setHorizontalAlignment(Element.ALIGN_CENTER);
						
			PdfPCell c11 = new PdfPCell(new Phrase("", cellFont));
			c11.setHorizontalAlignment(Element.ALIGN_CENTER);
			c11.setVerticalAlignment(Element.ALIGN_TOP);
			
			// Changes Done By Dheeraj on 2-4-2012 for generating sorted report based on roll numbers
			
			courseCount.remove(null);
			Map sorted = new TreeMap(courseCount);
			Iterator i = sorted.entrySet().iterator();

			List<String> tempRoll = new ArrayList<String>();
			int cellAdd = 0;		
			while (i.hasNext()){							
				Map.Entry<String, List<ConsolidatedChartBean>> pair = (Map.Entry<String, List<ConsolidatedChartBean>>)i.next();
				if(pair.getKey()!=null){
					PdfPTable leftTable = new PdfPTable(new float[] {7,2,2,2,3,3});
					leftTable.setWidthPercentage(100f);	
					leftTable.setHorizontalAlignment(Element.ALIGN_LEFT);					
					//**********************CODE UPDATED BY DEVENDRA************************
					String secRow = request.getParameter("entityCode")+"-"+request.getParameter("programCode")+
					"-"+request.getParameter("branchId")+"-"+request.getAttribute("currentSemesterNo")+"-"+pair.getValue().get(0).getRollNo();
					
					addCell(c11, cellFont, leftTable, pair.getValue().get(0).getName()+" / "+pair.getValue().get(0).getEnrollmentNo(), 6);
					addCell(c11, cellFont, leftTable, secRow, 1);
					addCell(c11, cellFont, leftTable, pair.getValue().get(0).getCategory(), 1);
					addCell(c11, cellFont, leftTable, pair.getValue().get(0).getGender(), 1);
					addCell(c11, cellFont, leftTable, pair.getValue().get(0).getAttemptNo(), 1);				
					String percentage = "";
					for (int l = 0; l < pair.getValue().get(0).getPercentages().length; l++) {
						String sp = pair.getValue().get(0).getPercentages()[l];
						if ((sp == null)
								|| ((l == 0) && (pair.getValue().get(0).getResult().equalsIgnoreCase(
										"REM") || pair.getValue().get(0).getResult().equalsIgnoreCase(
										"FAL")))) {
							sp = "";
						}
						percentage = sp + " " + percentage;
					}
					String result="";					
					if(pair.getValue().get(0).getResult().toString().equals("PAS")){
						result="PASS";
					}
					else if(pair.getValue().get(0).getResult().toString().equals("FAL")){
						result="FAIL";
					}
					else{
						result=pair.getValue().get(0).getResult().toString();
					}
					addCell(c11, cellFont, leftTable, result,1 );					
					if(isFinalSem.equalsIgnoreCase("F")){
						addCell(c11, cellFont, leftTable, pair.getValue().get(0).getCgpa(), 1);
					}
					else{
						addCell(c11, cellFont, leftTable, " ", 1);
					}
					addCell(c11, cellFont, leftTable, " ", 1);
					addCell(c11, cellFont, leftTable, percentage.trim(), 4);
					if(isFinalSem.equalsIgnoreCase("F")){
						addCell(c11, cellFont, leftTable,getDivision(pair.getValue().get(0).getDivision()), 1);
					}
					else{
						addCell(c11, cellFont, leftTable,"", 1);
					}
					PdfPTable rightTable = new PdfPTable(new float[] {4,2,2,2.4f,4,2,2,2.4f,4,2,2,2.4f,4,2,2,2.4f});
					rightTable.setWidthPercentage(100f);	
					rightTable.setHorizontalAlignment(Element.ALIGN_LEFT);
					for(ConsolidatedChartBean courses:pair.getValue()){
						String rollNo = courses.getRollNo();
						if(tempRoll.indexOf(rollNo)<0){
							//first time entry
							tempRoll.add(rollNo);
							cellAdd = 0;
						}
						addCell(c11, cellFont, rightTable, courses.getCourseCode(), 1);
						if (headerData.getResultSystem().equalsIgnoreCase("MK")) {
							addCell(c11, cellFont, rightTable, courses.getInternal(), 1);
							addCell(c11, cellFont, rightTable, courses.getExternal(), 1);
							String marks="";
							if(courses.getTotalMarks()==null || courses.getTotalMarks().equals(" ")){
								marks=courses.getTotalMarks();
							}
							else{
								marks=String.format("%.3g%n",Double.parseDouble(courses.getTotalMarks()));
							}	
							addCell(c11, cellFont, rightTable, marks, 1);
						} else {
							addCell(c11, cellFont, rightTable, courses.getInternalGr(), 1);
							addCell(c11, cellFont, rightTable, courses.getExternalGr(), 1);
							String marks="";
							if(courses.getFinalGr()==null || courses.getFinalGr().equals(" ")){
								marks=courses.getFinalGr();
							}
							else{
								marks=String.format("%.3g%n",Double.parseDouble(courses.getFinalGr()));
							}						
							addCell(c11, cellFont, rightTable, marks, 1);						
						}
						cellAdd++;
					}					
					if(cellAdd%4>0){
						for(int a=0;a<4-cellAdd%4;a++){
							addCell(c11, cellFont, rightTable, "", 4);
						}
					}					
					addCell(c11, cellFont, mainOuterTable, leftTable, 1);
					addCell(c11, cellFont, mainOuterTable, rightTable, 1);					
					
					//*******************DEVENDRA ENDS*************************					
				}				
			}
			document.add(mainOuterTable);
			document.close();
			//********************************
			//****************************NUpur*****************************		
			System.out.println("nupur : report created successfully");
			reportResult="true";					
	}catch(Exception e){
		System.out.println(e);
		reportResult="false";	
		ReportLogBean reportErrorBean = new ReportLogBean(session.getAttribute("universityId").toString(),request.getParameter("programId"),
		request.getParameter("branchId"),request.getParameter("specializationId"),request.getParameter("entityId"),
		request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
		request.getParameter("reportCode"),"",request.getParameter("rollNumber"),
		request.getParameter("fromSession").substring(0, 4)+"-"+request.getParameter("toSession").substring(2,4));
		logResult = reportDao.insertReportErrorLog(reportErrorBean);
		System.out.println("report error log result "+logResult);
		System.out.println("inside the exception "+e);
		System.out.println("nupur : some exception in report generation");
	}

	try{
		if(reportResult.equalsIgnoreCase("true")){
			reportGenerated = reportGenerated+1;
			ReportLogBean reportControlBean = new ReportLogBean(session.getAttribute("universityId").toString(),request.getParameter("entityId"),
					request.getParameter("programId"),request.getParameter("branchId"),request.getParameter("specializationId"),
					request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
					request.getParameter("reportCode"),String.valueOf(reportGenerated),"No",
					request.getParameter("fromSession"),request.getParameter("toSession"),request.getParameter("semesterType"),
					session.getAttribute("userName").toString());
			reportControlBean.setIsGenerated(request.getParameter("isGenerated")==null?"no":"yes");				
			logResult = reportDao.insertReportLog(reportControlBean);
	}
	}catch(Exception e){
		System.out.println("some exception in log entry "+e);
	}
	
	//***************************************************************
	System.out.println("repor result in generator "+reportResult);
	return reportResult;
}

	/**
	 * This method add a cell to Table
	 * 
	 * @param c1
	 *            , Table cell
	 * @param cellFont
	 *            , Font used
	 * @param chartTable
	 *            , Table in which cell is added
	 * @param s
	 *            , Text string in cell
	 * @param rbw
	 *            , Right side border width of cell
	 */
	public static final void addCell(Cell c1, Font cellFont, Table chartTable,String s, float rbw) {
		try {
			c1 = new Cell(new Phrase(s, cellFont));
		} catch (BadElementException e) {
			e.printStackTrace();
		}
		c1.setHeader(true);
		c1.setBorderWidth(0);
		c1.setBorderWidthRight(rbw);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_CENTER);
		chartTable.addCell(c1);
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
	public static final void addCell(PdfPCell c1, Font cellFont, PdfPTable chartTable, PdfPTable s, int colSpan) {
		try {
			c1 = new PdfPCell(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		c1.setBorderWidth(0);
		c1.setColspan(colSpan);
		c1.setPaddingTop(1);
		c1.setPaddingBottom(1);
		chartTable.addCell(c1);
	}
	public static final void addHeaderCell(Font cellFont, PdfPTable chartTable, String s, int colSpan,String align) {
		PdfPCell c1 = null;
		try {
			c1 = new PdfPCell(new Phrase(s, cellFont));
		} catch (Exception e) {
			e.printStackTrace();
		}
		c1.setBorder(Rectangle.NO_BORDER);
		if(align.equals("left")){
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		}
		else{
			c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		}
		c1.setColspan(colSpan);
		chartTable.addCell(c1);
	}
	
	public String getDivision(String div){
		String str="";
		if(div.equalsIgnoreCase("01")){
			str="I.D.";
		}
		else if(div.equalsIgnoreCase("02")){
			str="I";
		}
		else if(div.equalsIgnoreCase("03")){
			str="II";
		}	
		return str;
	}
	//==============================================
	
	
	
}
