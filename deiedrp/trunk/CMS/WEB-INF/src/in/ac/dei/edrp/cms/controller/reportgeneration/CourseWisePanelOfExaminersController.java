/**
 * @(#) CourseWisePanelOfExaminersController.java
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

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ac.dei.edrp.cms.dao.reportgeneration.CourseWisePanelOfExaminersDao;
import in.ac.dei.edrp.cms.domain.externalexaminercourse.ExternalExaminerCourseInfoGetter;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;


/**
 * This controller is designed for setting & retrieving
 * the Course wise Panel of examiner detail
 * @author Mandeep Sodhi
 * @date 8 Jan 2011
 * @version 1.0
 */

public class CourseWisePanelOfExaminersController extends MultiActionController {
  private CourseWisePanelOfExaminersDao courseWisePanelOfExaminersDao;

/**
 * @param courseWisePanelOfExaminersDao the courseWisePanelOfExaminersDao to set
 */
public void setCourseWisePanelOfExaminersDao(
		CourseWisePanelOfExaminersDao courseWisePanelOfExaminersDao) {
	this.courseWisePanelOfExaminersDao = courseWisePanelOfExaminersDao;
}

/**
 * Method to get the Course Name list
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
  public ModelAndView courseWisePanelOfExaminerPdf(HttpServletRequest request,HttpServletResponse response)throws Exception{
	  ExternalExaminerCourseInfoGetter input = new ExternalExaminerCourseInfoGetter();
	  input.setCourseCode(request.getParameter("courseCode"));
	  input.setSessionStartDate(request.getParameter("sessionStartDate"));
	  input.setSessionEndDate(request.getParameter("sessionEndDate"));
	  System.out.println(request.getParameter("sessionStartDate")+request.getParameter("sessionEndDate"));
	  File file=new File(this.getServletContext().getRealPath("/")+"//"+"Reports");
	  if(!file.exists()){
		  new File((this.getServletContext().getRealPath("/")+"//"+"Reports")).mkdir();
	  }
	  List<ExternalExaminerCourseInfoGetter>result=courseWisePanelOfExaminersDao.getCourseName(input);
	  String courseName=result.get(0).getCourseName();
	  List<ExternalExaminerCourseInfoGetter>examinerList=courseWisePanelOfExaminersDao.getExaminerList(input);
	  if(examinerList.size()==0){
		    return new ModelAndView("ExternalExaminerCourse/insert",
		    		"info", examinerList.size());
	  }
	  else{
	  String resultPdf=generatePdf(input,this.getServletContext().getRealPath("/")+"//"+"Reports"+"//",courseName);
	    return new ModelAndView("ExternalExaminerCourse/insert",
	    		"info", resultPdf);
	  }

  }
  
	/**
   * Method for generate the PDF.
   * @return String
   */ 
private String generatePdf(ExternalExaminerCourseInfoGetter input, String filePath, String courseName) {
	
	Document document=new Document(PageSize.A4.rotate());
	try{
		String sessionDate  =input.getSessionStartDate().substring(0,4)+"-"+input.getSessionEndDate().substring(2,4);
		filePath = filePath + "Examiners"+" "+"for"+" "+courseName+".pdf";
		System.out.println("file path is" + filePath);
		List<ExternalExaminerCourseInfoGetter>examinerList=courseWisePanelOfExaminersDao.getExaminerList(input);
		
		int  noOfExaminer=examinerList.size();
		
		Date date=new Date();
		String currentDate=DateFormat.getDateInstance().format(date);		
		PdfWriter.getInstance(document, new FileOutputStream(filePath));
		
		Phrase header1 = new Phrase(
				"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+"Course Wise Panel Of Examiners-"+currentDate+"\n\n"+"Course No."+input.getCourseCode()+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+"Title"+" "+courseName+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+sessionDate,
				FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD,
						new Color(0, 0, 0)));
		Phrase headers = new Phrase();
		headers.add(header1);

		HeaderFooter headerFooter = new HeaderFooter(headers, false);
		headerFooter.setBorderWidth(0);
		

		document.setHeader(headerFooter);
		document.open();
		PdfPTable pTable=new PdfPTable(new float[]{1,2,4,4});
		pTable.setWidthPercentage(100f);
		pTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		  for(int i=0;i<noOfExaminer;i++){
			String num=Integer.toString(i+1);
			PdfPCell cell1=new PdfPCell(new Phrase(num));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell1.setVerticalAlignment(Element.ALIGN_TOP);
			cell1.setBorder(Rectangle.NO_BORDER);
			pTable.addCell(cell1);
			
			PdfPCell cell2=new PdfPCell(new Phrase(examinerList.get(i).getExaminarId()));
			cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell2.setVerticalAlignment(Element.ALIGN_TOP);
			cell2.setBorder(Rectangle.NO_BORDER);
			pTable.addCell(cell2);

			
			PdfPCell cell3=new PdfPCell(new Phrase(examinerList.get(i).getFullName()));
			cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell3.setVerticalAlignment(Element.ALIGN_TOP);
			cell3.setBorder(Rectangle.NO_BORDER);
			pTable.addCell(cell3);
			
			PdfPCell cell4=new PdfPCell(new Phrase(examinerList.get(i).getDesgDept()));
			cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell4.setVerticalAlignment(Element.ALIGN_TOP);
			cell4.setBorder(Rectangle.NO_BORDER);
			pTable.addCell(cell4);			

			
			pTable.addCell("");
			pTable.addCell("");
			
			PdfPCell cell21=new PdfPCell(new Phrase(examinerList.get(i).getAddress()));
			cell21.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell21.setVerticalAlignment(Element.ALIGN_TOP);
			cell21.setBorder(Rectangle.NO_BORDER);
			pTable.addCell(cell21);
			
			PdfPCell cell22=new PdfPCell(new Phrase(examinerList.get(i).getCollegeName()));
			cell22.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell22.setVerticalAlignment(Element.ALIGN_TOP);
			cell22.setBorder(Rectangle.NO_BORDER);
			pTable.addCell(cell22);
			
			pTable.addCell("");
			pTable.addCell("");
			
			PdfPCell cell31=new PdfPCell(new Phrase(examinerList.get(i).getCityPin()));
			cell31.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell31.setVerticalAlignment(Element.ALIGN_TOP);
			cell31.setBorder(Rectangle.NO_BORDER);
			pTable.addCell(cell31);
			
			PdfPCell cell32=new PdfPCell(new Phrase(examinerList.get(i).getPhone()));
			cell32.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell32.setVerticalAlignment(Element.ALIGN_TOP);
			cell32.setBorder(Rectangle.NO_BORDER);
			pTable.addCell(cell32);
			for(int k=0;k<4;k++){
				pTable.addCell(" ");
			}
		  }
		document.add(pTable);
		
		document.close();
		return "success";
	}
	catch (Exception e) {
		e.printStackTrace();
		return "failure";
		
	}
	
	
}
}
