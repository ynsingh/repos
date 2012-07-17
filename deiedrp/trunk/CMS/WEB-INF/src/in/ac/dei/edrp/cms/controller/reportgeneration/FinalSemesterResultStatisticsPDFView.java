/**
 * @(#) FinalSemesterResultStatisticsPDFView.java
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

import in.ac.dei.edrp.cms.dao.reportgeneration.FinalSemesterResultStatisticsDAO;
import in.ac.dei.edrp.cms.domain.reportgeneration.FinalSemesterResultStatistics;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.view.document.AbstractPdfView;

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

public class FinalSemesterResultStatisticsPDFView extends AbstractPdfView {

	private FinalSemesterResultStatisticsDAO finalSemesterResultStatisticsDao;	

	public void setFinalSemesterResultStatisticsDao(FinalSemesterResultStatisticsDAO finalSemesterResultStatisticsDao) {
		this.finalSemesterResultStatisticsDao = finalSemesterResultStatisticsDao;
	}

	@SuppressWarnings("unchecked")
	protected void buildPdfDocument(Map model,Document document,PdfWriter writer,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String filePath = "";
		File file;
		List <FinalSemesterResultStatistics> programDetails;
		List<FinalSemesterResultStatistics> completeDetails=new 

		ArrayList<FinalSemesterResultStatistics>();		
		String sessionStartDate=request.getParameter("sessionStartDate");
		String sessionEndDate=request.getParameter("sessionEndDate");

		HttpSession session=request.getSession(true);
		String sep = System.getProperty("file.separator");
		filePath = getServletContext().getRealPath("/")+"PDF";

		filePath=filePath +sep+session.getAttribute("universityName").toString()+sep+"FinalSemesterResultStatistics"+
				sep+"Session-"+sessionStartDate.substring(0, 4)+"-"+sessionEndDate.substring(0, 4);
		file = new File(filePath);
		file.mkdirs();
//		writer = PdfWriter.getInstance(document,new FileOutputStream(filePath+sep+"Final Semester Result Statistics_"+entityId+"_"+
//				sessionStartDate.substring(0, 4)+"-"+sessionEndDate.substring(0, 4)+".pdf"));
//		document.setPageSize(PageSize.A4.rotate());
		Phrase header1= new Phrase(session.getAttribute("universityName").toString().toUpperCase(),FontFactory.getFont(
						FontFactory.HELVETICA, 12, Font.BOLD,new Color(0, 0, 0)));
		Phrase header2= new Phrase("\nFINAL SEMESTER RESULT STATISTICS : "+ sessionStartDate.substring(0, 4)+"-"+
						sessionEndDate.substring(0, 4),FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD,new Color(0, 0, 0)));
		Phrase headers=new Phrase();
		headers.add(header1);
		headers.add(header2);       
		HeaderFooter header = new HeaderFooter(headers,false);
		header.setBorderWidth(0);
		header.setAlignment(Element.ALIGN_CENTER);
		Date printingDate = new Date();
		String toDay = DateFormat.getDateTimeInstance().format(printingDate);
		HeaderFooter footer = new HeaderFooter(new Phrase("Date : "+toDay),false); 
		footer.setBorderWidth(0);
		Font cellFont = new Font(Font.HELVETICA, 8);
		PdfPTable studentTable = new PdfPTable(new float[] {5,2,3,3,3,3,2,3,3,4,4,4,4,3});
		studentTable.setWidthPercentage(100f);		
		PdfPCell c1 = new PdfPCell(new Phrase("", cellFont));
		addCell(c1, cellFont, studentTable,"F-CL-BR-S");
		addCell(c1, cellFont, studentTable,"SEX");
		addCell(c1, cellFont, studentTable,"ENROLLED");
		addCell(c1, cellFont, studentTable,"APPEARED");
		addCell(c1, cellFont, studentTable,"UFM");
		addCell(c1, cellFont, studentTable,"PASSED");
		addCell(c1, cellFont, studentTable,"I-DIST");
		addCell(c1, cellFont, studentTable,"I-Div");
		addCell(c1, cellFont, studentTable,"II-Div");
		addCell(c1, cellFont, studentTable,"PASS");
		addCell(c1, cellFont, studentTable,"ELIGIBLE FOR REMEDIALS");
		addCell(c1, cellFont, studentTable,"FAILED 1ST ATTEMPT");			
		addCell(c1, cellFont, studentTable,"FAILED 2ND ATTEMPT");
		addCell(c1, cellFont, studentTable,"PASSED %");
		programDetails = (List <FinalSemesterResultStatistics>) model.get("getProgramDetail");
		System.out.println("size of program details :"+programDetails.size());
		FinalSemesterResultStatistics entityObject= new FinalSemesterResultStatistics();
		FinalSemesterResultStatistics finalSemesterResultStatistics=new FinalSemesterResultStatistics();
		finalSemesterResultStatistics.setEntityId(request.getParameter("selectedEntityId"));
		entityObject=finalSemesterResultStatisticsDao.getEntityInfo(finalSemesterResultStatistics);
		for(FinalSemesterResultStatistics singleProgram:programDetails){			
			finalSemesterResultStatistics.setProgramCourseKey(singleProgram.getProgramCourseKey());
			finalSemesterResultStatistics.setProgramId(singleProgram.getProgramId());
			finalSemesterResultStatistics.setProgramCode(singleProgram.getProgramCode());
			finalSemesterResultStatistics.setBranchId(singleProgram.getBranchId());
			finalSemesterResultStatistics.setSpecializationId(singleProgram.getSpecializationId());
			finalSemesterResultStatistics.setSemesterCode(singleProgram.getSemesterCode());			
			finalSemesterResultStatistics.setProgramCourseKey2(singleProgram.getProgramCourseKey().substring(0, 11)+"%");
			//***********************nupur - 3/11/2011******************************
			finalSemesterResultStatistics.setSessionStartDate(sessionStartDate);
			finalSemesterResultStatistics.setSessionEndDate(sessionEndDate);
			//**********************nupur end ***************************************
			completeDetails=finalSemesterResultStatisticsDao.getCompleteDetails(finalSemesterResultStatistics);
			System.out.println("size of complete details :"+completeDetails.size());			

			for(int i=0;i<completeDetails.size();i++){
				if(i==0){
					addCell(c1, cellFont, studentTable,entityObject.getEntityCode()+"-"+singleProgram.getProgramCode()+"-"+
							singleProgram.getBranchId()+"-"+singleProgram.getSemesterCode());
				}
				else{
					addCell(c1, cellFont, studentTable,"");
				}						
				addCell(c1, cellFont, studentTable,completeDetails.get(i).getSex());
				addCell(c1, cellFont, studentTable,completeDetails.get(i).getEnrolled());
				addCell(c1, cellFont, studentTable,completeDetails.get(i).getAppeared());
				addCell(c1, cellFont, studentTable,completeDetails.get(i).getUfm());
				addCell(c1, cellFont, studentTable,completeDetails.get(i).getPassed());
				addCell(c1, cellFont, studentTable,completeDetails.get(i).getIDistinction());
				addCell(c1, cellFont, studentTable,completeDetails.get(i).getIDiv());
				addCell(c1, cellFont, studentTable,completeDetails.get(i).getIIDiv());
				addCell(c1, cellFont, studentTable,completeDetails.get(i).getPass());
				addCell(c1, cellFont, studentTable,completeDetails.get(i).getRemedial());
				addCell(c1, cellFont, studentTable,completeDetails.get(i).getFailedFirstAttempt());
				addCell(c1, cellFont, studentTable,completeDetails.get(i).getFailedSecondAttempt());
				addCell(c1, cellFont, studentTable,completeDetails.get(i).getPercent());	
			}				
		}	
		writer = PdfWriter.getInstance(document,new FileOutputStream(filePath+sep+"Final Semester Result Statistics_"+entityObject.getEntityName()+"_"+
				sessionStartDate.substring(0, 4)+"-"+sessionEndDate.substring(0, 4)+".pdf"));
		document.setPageSize(PageSize.A4.rotate());
		document.setHeader(header);
		document.setFooter(footer);
		document.open();
		document.add(studentTable);
		document.close();		
	}

	public static final void addCell(PdfPCell c1, Font cellFont, PdfPTable chartTable, String s) {
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

