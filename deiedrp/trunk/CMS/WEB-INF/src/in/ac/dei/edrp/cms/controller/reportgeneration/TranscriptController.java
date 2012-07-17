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
import in.ac.dei.edrp.cms.dao.reportgeneration.TranscriptDao;
import in.ac.dei.edrp.cms.domain.report.ReportLogBean;
import in.ac.dei.edrp.cms.domain.reportgeneration.FinalSemesterResultStatisticsCategoryWise;
import in.ac.dei.edrp.cms.domain.reportgeneration.Transcript;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.SplitCharacter;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.MultiColumnText;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfContentByte;
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
public class TranscriptController extends MultiActionController{

	private TranscriptDao transcriptDao;
	private ReportDao reportDao;
	
	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}
	public void setTranscriptDao(
			TranscriptDao transcriptDao) {
		this.transcriptDao = transcriptDao;
	}	
	/**
     * this method will get the current session
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView getEnrollment(HttpServletRequest request,HttpServletResponse response)throws Exception {		
		HttpSession session=request.getSession(true);
//		subjectWiseMeritList.setUniversityId(request.getParameter("selectedEntityId").substring(0, 4));
		List<Transcript> enrollmentDetail = transcriptDao.getEnrollment((String)session.getAttribute("universityId"));
		return new ModelAndView("transcript/Transcript","enrollmentDetail", enrollmentDetail);			
	}
	
	
	/**
     * Method to generate the PDF.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView generatePDF(HttpServletRequest request,HttpServletResponse response)throws Exception {
		System.out.println("inside trnscript controller");		
		HttpSession session=request.getSession(true);
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive","sessionInactive", true);
		}
		String universityId = (String)session.getAttribute("universityId");
		String result = "false";
		Transcript  transcript = new Transcript();
		transcript.setEnrollmentNumber(request.getParameter("enrollmentNumber"));
		transcript.setUniversityId(universityId);
		List <Transcript> getAllProgramCourseKey = transcriptDao.getAllProgramCourseKey(transcript);
		System.out.println("siiiiize is:"+ getAllProgramCourseKey.size());
		if(getAllProgramCourseKey.size()!=0)
			result = generatePDF(request,getAllProgramCourseKey,transcript);
		else
			return new ModelAndView("ManualProcess/MyException","exception","For this enrollment number no record is available in the Database");	
		String message ="";
		String logResult="";
		if(result=="true"){
			logResult = reportDao.insertReportLog((ReportLogBean)request.getAttribute("reportLogBean"));
			message = "true";
		}
		else{
			message = "There is some error in PDF Generation kindly view the error log for more information";
		}
		return new ModelAndView("ManualProcess/MyException","exception",message);	
	}
	
	public String generatePDF(HttpServletRequest request,List<Transcript> getAllProgramCourseKey, Transcript beanPassed){
		String result = "false";
		String filePath = "";
		File file;		
		try{
			Date date = new Date();		
			
			Transcript personalDetail=transcriptDao.getPersonalDetail(beanPassed);
			String issuedTo = request.getParameter("issuedTo");
			System.out.println("issued to "+issuedTo);
			StringTokenizer tokenizer = null ;
			if(issuedTo.length()==0){
				tokenizer = new StringTokenizer(personalDetail.getStudentName(),"\n\t\r");
				//issuedTo = personalDetail.getStudentName();
			}
			else{
				 tokenizer = new StringTokenizer(issuedTo,"\n\t\r");
//				 while(tokenizer.hasMoreTokens()){
//						System.out.println("token :"+tokenizer.nextToken());
//					}
			}
			HttpSession session=request.getSession(true);
				
			String sep = System.getProperty("file.separator");		
			filePath = getServletContext().getRealPath("/")+"PDF";
			filePath=filePath +sep+session.getAttribute("universityName").toString()+sep+"Transcripts";			
			file = new File(filePath);
//			filePath = getServletContext().getRealPath("/")+request.getAttribute("reportPath");
//			file = new File(filePath);
			file.mkdirs();
			Document document = new Document(PageSize.A4);		
			Chunk line = new Chunk(
			"_____________________________________________________________________________");
			PdfPTable headerTable = new PdfPTable(new float[] {2,1});
			headerTable.setWidthPercentage(100f);	
			headerTable.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell fCell = new PdfPCell(new Phrase(session.getAttribute("universityName").toString().toUpperCase(),FontFactory.getFont(
					FontFactory.HELVETICA, 10, Font.BOLD,new Color(0, 0, 0))));
			fCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			fCell.setVerticalAlignment(Element.ALIGN_TOP);
			fCell.setBorder(0);
			headerTable.addCell(fCell);
			PdfPCell sCell = new PdfPCell(new Phrase("Transcript of Consolidated Academic Record",FontFactory.getFont(
				FontFactory.HELVETICA, 9, Font.BOLD,new Color(0, 0, 0))));
			sCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sCell.setVerticalAlignment(Element.ALIGN_CENTER);
			sCell.setBorderWidth(1);
			headerTable.addCell(sCell);
			Font cellFont = new Font(Font.HELVETICA, 8);
			PdfPCell c1 = new PdfPCell(new Phrase("", cellFont));
			c1.setVerticalAlignment(Element.ALIGN_TOP);
			addCellHeader(c1, cellFont, headerTable,"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tDayalbagh, Agra, India",Element.ALIGN_LEFT);
			addCellHeader(c1, cellFont, headerTable," ",Element.ALIGN_RIGHT);
			addCellHeader(c1, cellFont, headerTable," ",Element.ALIGN_RIGHT);
			addCellHeader(c1, cellFont, headerTable," ",Element.ALIGN_RIGHT);
			addCellHeader(c1, cellFont, headerTable," ",Element.ALIGN_RIGHT);
			addCellHeader(c1, cellFont, headerTable," ",Element.ALIGN_RIGHT);
			addCellHeader(c1, cellFont, headerTable,"NAME:            "+personalDetail.getStudentName(),Element.ALIGN_LEFT);
			addCellHeader(c1, cellFont, headerTable,"RECORD AS OF:\t\t\t\t\t\t"+String.format("%td-%<tb-%<tY", date),Element.ALIGN_LEFT);
			//addCellHeader(c1, cellFont, headerTable," ",Element.ALIGN_LEFT);
			addCellHeader(c1, cellFont, headerTable,"ENROLL. NUMBER:\t\t\t\t\t\t"+request.getParameter("enrollmentNumber"),Element.ALIGN_LEFT);
			
			addCellHeader(c1, cellFont, headerTable,"DOB:\t\t\t\t\t\t"+personalDetail.getDob(),Element.ALIGN_LEFT);
			addCellHeader(c1, cellFont, headerTable," ",Element.ALIGN_LEFT);
			addCellHeader(c1, cellFont, headerTable,"FATHER NAME:"+personalDetail.getFatherName(),Element.ALIGN_LEFT);			
			addCellHeader(c1, cellFont, headerTable,"ISSUED TO: ",Element.ALIGN_LEFT);
			addCellHeader(c1, cellFont, headerTable," ",Element.ALIGN_RIGHT);
			while(tokenizer.hasMoreTokens()){
				addCellHeader(c1, cellFont, headerTable,"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+tokenizer.nextToken(),Element.ALIGN_LEFT);
				addCellHeader(c1, cellFont, headerTable," ",Element.ALIGN_RIGHT);
				//System.out.println("token :"+tokenizer.nextToken());
			}
			
			
//			addCellHeader(c1, cellFont, headerTable,"ISSUED TO:     University of Toronto",Element.ALIGN_LEFT);
//			addCellHeader(c1, cellFont, headerTable,"                         Toronto",Element.ALIGN_LEFT);
//			
//			addCellHeader(c1, cellFont, headerTable,"                         Ontario",Element.ALIGN_LEFT);
//			addCellHeader(c1, cellFont, headerTable," ",Element.ALIGN_RIGHT);
//			addCellHeader(c1, cellFont, headerTable,"                         Canada",Element.ALIGN_LEFT);
			addCellHeader(c1, cellFont, headerTable," ",Element.ALIGN_RIGHT);		
			Phrase headers=new Phrase();
			headers.add(headerTable);			
			headers.add(line);

//			Font cellFont = new Font(Font.HELVETICA, 8);
		
			PdfPTable mainTable = new PdfPTable(new float[] {1,1});
			mainTable.setWidthPercentage(100f);	
			mainTable.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell mainFCell = new PdfPCell(new Phrase(session.getAttribute("universityName").toString().toUpperCase(),FontFactory.getFont(
					FontFactory.HELVETICA, 8, Font.BOLD,new Color(0, 0, 0))));
			mainFCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			mainFCell.setVerticalAlignment(Element.ALIGN_CENTER);			
			mainTable.addCell(mainFCell);

			HeaderFooter header = new HeaderFooter(headers,false);
			header.setBorderWidth(0);
			Phrase footerData = new Phrase("This transcript is official only if bearing the registrar's signature and printed" +
					"on blue security paper",FontFactory.getFont(
							FontFactory.HELVETICA, 8,new Color(0, 0, 0)));
			HeaderFooter footer = new HeaderFooter(footerData, false);		
			footer.setBorderWidth(1);
			footer.setAlignment(Element.ALIGN_CENTER);
			            
			PdfWriter.getInstance(document,new FileOutputStream(filePath+sep+"Transcript_"+request.getParameter("enrollmentNumber")+".pdf"));
			document.setHeader(header);
			document.setFooter(footer);		
			document.open();
			Image logoImage = Image.getInstance(java.awt.Toolkit.getDefaultToolkit().createImage(this.getServletContext().getRealPath("/")
							+ "images/deiLogo.jpg"), null);
			logoImage.scaleAbsolute(50, 50);
			logoImage.setAbsolutePosition(2f, 795f);
			document.add(logoImage);
			
			//******************** Define here multi column text to split in two columns***********************************
		
			MultiColumnText mct = new MultiColumnText();
			mct.addRegularColumns(document.left(),document.right(), 10f, 2);
			// Create the table here with 1 columns
            PdfPTable table = new PdfPTable(1); 
            table.setWidthPercentage(100f);
            // Create fonts for the table
            Font headerFont = new Font(Font.HELVETICA, 5.8f, Font.BOLD);
            Font rowFont = new Font(Font.HELVETICA, 5.8f, Font.NORMAL); 
            PdfPCell headCell = new PdfPCell(new Phrase(session.getAttribute("universityName").toString().toUpperCase(),FontFactory.getFont(
					FontFactory.HELVETICA, 8,Font.BOLD,new Color(0, 0, 0))));
            headCell.setBorderWidth(0.3f);
           table.addCell(headCell);
           PdfPCell headCell2 = new PdfPCell(new Phrase(" "));
           headCell2.setBorderWidth(0);
           table.addCell(headCell2);
          String regStart = "";
          String regEnd = "";
			for(Transcript prgCourseKey:getAllProgramCourseKey){
				prgCourseKey.setEnrollmentNumber(request.getParameter("enrollmentNumber"));
				prgCourseKey.setUniversityId((String)session.getAttribute("universityId"));
				Transcript getProgramName = transcriptDao.getProgramName(prgCourseKey);
				Transcript getSgpaCgpa = transcriptDao.getSgpaCgpa(prgCourseKey);
				if(getSgpaCgpa.getSgpa()==99) {
					continue;
				}
				System.out.println("after if :"+getSgpaCgpa.getSgpa());
				if(regStart.length()==0){
					regStart = getSgpaCgpa.getSemesterStartDate();
				}
				regEnd = getSgpaCgpa.getSemesterEndDate();
				String programName = getProgramName.getProgramName().concat(getProgramName.getBranchName().equalsIgnoreCase("none")?"":"-"+getProgramName.getBranchName())
				.concat(getProgramName.getSpecialization().equalsIgnoreCase("none")?"":"-"+getProgramName.getSpecialization());
				List<Transcript> getMarksDetail = transcriptDao.getMarksDetail(prgCourseKey);
				System.out.println("marks size is :"+getMarksDetail.size());				
				// Create the table here with 7 columns
				// Fill up the table with header data
            	PdfPTable innerTable = new PdfPTable(new float[] {1.5f, 4f, 1f, 1f, 1f, 1f, 1f});
            	innerTable.setWidthPercentage(100f); 
            	innerTable.setSplitRows(false);
            	PdfPCell headerCell = new PdfPCell(new Phrase("", headerFont));
            	addCell(headerCell, headerFont, innerTable,getSgpaCgpa.getSemesterStartDate(),1);
            	addCell(headerCell, headerFont, innerTable," : "+getSgpaCgpa.getSemesterEndDate()+" ("+prgCourseKey.getSemesterName()+")",1);
            	addCell(headerCell, headerFont, innerTable,programName,5);
            	addCell(headerCell, rowFont, innerTable,"SGPA:",1);
            	addCell(headerCell, headerFont, innerTable,String.valueOf(getSgpaCgpa.getSgpa()),1);
            	addCell(headerCell, rowFont, innerTable,"CGPA:",1);
            	addCell(headerCell, headerFont, innerTable,String.valueOf(getSgpaCgpa.getCgpa()),4);
            	addCell(headerCell, rowFont, innerTable,"STATUS:",1);
            	addCell(headerCell, rowFont, innerTable,getSgpaCgpa.getStatusName(),1);
            	addCell(headerCell, rowFont, innerTable,"Prac. CGPA:",1);
            	addCell(headerCell, headerFont, innerTable,String.valueOf(getSgpaCgpa.getPracticalCgpa()),1);
            	addCell(headerCell, rowFont, innerTable,"Theory CGPA:",1);
            	addCell(headerCell, headerFont, innerTable,String.valueOf(getSgpaCgpa.getTheoryCgpa()),2);
            	if(prgCourseKey.getFinalSemesterCode().equalsIgnoreCase("f")){
            		addCell(headerCell, rowFont, innerTable,"DIVISION:",1);
            		addCell(headerCell, rowFont, innerTable,prgCourseKey.getDivision(),6);
            	}
            	addCell(headerCell, rowFont, innerTable,"CRS CODE",1);
            	addCell(headerCell, rowFont, innerTable,"TITLE",1);
            	addCell(headerCell, rowFont, innerTable,"WGT",1);
            	addCell(headerCell, rowFont, innerTable,"MRK",1);
            	addCell(headerCell, rowFont, innerTable,"X-GRD",1);
            	addCell(headerCell, rowFont, innerTable,"I-GRD",1);
            	addCell(headerCell, rowFont, innerTable,"FGP",1);
            	double totalCredit = 0;
            	// Fill up the table with marks data
            	 for(Transcript marks:getMarksDetail) {  	            	 
                    // Add alternating color
//		            innerTable.getDefaultCell().setGrayFill((marks. % 2 == 0) ? 1f : 0.75f);
            		 PdfPCell innerCell = new PdfPCell(new Phrase("", rowFont));            		
            		 addCellInner(innerCell, headerFont, innerTable,marks.getCourseCode(),Element.ALIGN_LEFT);
            		 addCellInner(innerCell, rowFont, innerTable,marks.getCourseName(),Element.ALIGN_LEFT);
            		 addCellInner(innerCell, rowFont, innerTable,String.valueOf(marks.getCredit()),Element.ALIGN_LEFT);
            		 addCellInner(innerCell, rowFont, innerTable,"",Element.ALIGN_LEFT);
            		 addCellInner(innerCell, rowFont, innerTable,marks.getExternalGrade(),Element.ALIGN_LEFT);
            		 addCellInner(innerCell, rowFont, innerTable,marks.getInternalGrade(),Element.ALIGN_LEFT);
            		 addCellInner(innerCell, rowFont, innerTable,String.valueOf(marks.getFinalGradePoint()),Element.ALIGN_LEFT);            		 
	            	if(marks.getFinalGradePoint()>=3){
	            		totalCredit = totalCredit+marks.getCredit();
	            	}
	            }
	        	 addCell(headerCell, rowFont, innerTable,"Credits earned:",1);
	        	 addCell(headerCell, rowFont, innerTable,String.valueOf(totalCredit),6);
	        	 addCell(headerCell, rowFont, innerTable,"",7);
	        	 PdfPCell cell = new PdfPCell(innerTable);
	        	 cell.setBorderWidth(0);
	            table.addCell(cell);
//	            table.addCell(" ");	           
            }
            mct.addElement(table);
            Phrase ph = new Phrase("REGISTRATION HISTORY\n", cellFont);
			ph.add(new Phrase(regStart+" - "+regEnd+" : "+session.getAttribute("universityName")+"\n"));
			Chunk line2 = new Chunk(
			"_________________________________________________________________________________________________________________");
//			line2.setHorizontalScaling(100f);
			ph.add(line2);
			document.add(ph);
    		document.add(mct);
			document.close();
			result = "true";
		}catch(Exception e){
			result = "false";
			e.printStackTrace();
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
	
	public static final void addCellHeader(PdfPCell c1, Font cellFont, PdfPTable headerTable, String s, int alignment) {
		try {
			c1 = new PdfPCell(new Phrase(s, cellFont));
		} catch (Exception e) {
			e.printStackTrace();
		}
		c1.setBorderWidth(0);
		c1.setHorizontalAlignment(alignment);
		headerTable.addCell(c1);
	}
	public static final void addCellInner(PdfPCell c1, Font cellFont, PdfPTable headerTable, String s, int alignment) {
		try {
			c1 = new PdfPCell(new Phrase(s, cellFont));
		} catch (Exception e) {
			e.printStackTrace();
		}
		c1.setBorderWidth(0);
		
		 c1.setBorderWidthBottom(0.5f);
		 c1.setBorderWidthTop(0.5f);
		c1.setHorizontalAlignment(alignment);
		headerTable.addCell(c1);
	}
}