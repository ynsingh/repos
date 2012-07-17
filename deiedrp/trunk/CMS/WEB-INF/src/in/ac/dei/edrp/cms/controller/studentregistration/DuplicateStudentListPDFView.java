/**
 * @(#) DuplicateStudentListPDFView.java
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
package in.ac.dei.edrp.cms.controller.studentregistration;

import in.ac.dei.edrp.cms.domain.studentregistration.StudentInfoGetter;

import java.awt.Color;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.text.DateFormat;
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
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPrintPage;

public class DuplicateStudentListPDFView extends AbstractPdfView {
	
	@SuppressWarnings("unchecked")
	protected void buildPdfDocument(Map model,Document document,PdfWriter writer, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		List <StudentInfoGetter> duplicateStudentList;
		String directoryName = "";
		File file;
		String sep = System.getProperty("file.separator");
		HttpSession session=request.getSession(true);
		
		directoryName = getServletContext().getRealPath("/")+"PDF";		
		
		directoryName=directoryName +sep+session.getAttribute("universityName").toString()+sep+"DuplicateStudent"+
		sep+"Session-"+session.getAttribute("startDate").toString().substring(0, 4)+"-"+
		session.getAttribute("endDate").toString().substring(0, 4);
		
		file = new File(directoryName);
		file.mkdirs();
		
		writer = PdfWriter.getInstance(document,new FileOutputStream(directoryName+sep+"Duplicate_Student_List"+".pdf"));
		document.setPageSize(PageSize.A4.rotate());
		
		
		Phrase header1= new Phrase(session.getAttribute("universityName").toString().toUpperCase(),FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD,
				new Color(0, 0, 0)));
		Phrase header2= new Phrase("\nDUPLICATE STUDENTS OF SESSION : "+ session.getAttribute("startDate").toString().substring(0, 4)+"-"+
				session.getAttribute("endDate").toString().substring(0, 4),FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD,new Color(0, 0, 0)));
		
		Phrase headers=new Phrase();
        headers.add(header1);
        headers.add(header2);       
		HeaderFooter header = new HeaderFooter(headers,false);
        header.setBorderWidth(0);
        document.setHeader(header);
        
        Date printingDate = new Date();
        String toDay = DateFormat.getDateTimeInstance().format(printingDate);
       
        HeaderFooter footer = new HeaderFooter(new Phrase("Date : "+toDay),false); 
        footer.setBorderWidth(0);
        document.setFooter(footer);
        
        document.open();
		
		duplicateStudentList = (List <StudentInfoGetter>) model.get("duplicateStudentList");
		
		StudentInfoGetter finalSemesterResultStatistics=new StudentInfoGetter();
		finalSemesterResultStatistics.setProgramCourseKey(duplicateStudentList.get(0).getProgramCourseKey());
		finalSemesterResultStatistics.setProgramId(duplicateStudentList.get(0).getProgramId());
		finalSemesterResultStatistics.setBranchId(duplicateStudentList.get(0).getBranchId());
		finalSemesterResultStatistics.setSpecializationId(duplicateStudentList.get(0).getSpecializationId());
		finalSemesterResultStatistics.setSemesterCode(duplicateStudentList.get(0).getSemesterCode());
		
		Font cellFont = new Font(Font.HELVETICA, 8);
		PdfPTable studentTable = new PdfPTable(new float[] {4,5,5,5,3,3,2,4,5,4,3});
		studentTable.setWidthPercentage(100f);		
		PdfPCell c1 = new PdfPCell(new Phrase("", cellFont));
			addCell(c1, cellFont, studentTable,"Registration Number");
			addCell(c1, cellFont, studentTable,"Student Name");
			addCell(c1, cellFont, studentTable,"Father's Name");
			addCell(c1, cellFont, studentTable,"Mother's Name");
			addCell(c1, cellFont, studentTable,"Date of Birth");
			addCell(c1, cellFont, studentTable,"Category");
			addCell(c1, cellFont, studentTable,"Gender");
			addCell(c1, cellFont, studentTable,"Program");
			addCell(c1, cellFont, studentTable,"Branch");
			addCell(c1, cellFont, studentTable,"Specialization");
			addCell(c1, cellFont, studentTable,"Semester");
						
			for(int i=0;i<duplicateStudentList.size();i++){
				addCell(c1, cellFont, studentTable,duplicateStudentList.get(i).getRegistrationNumber());
				addCell(c1, cellFont, studentTable,duplicateStudentList.get(i).getStudentName());
				addCell(c1, cellFont, studentTable,duplicateStudentList.get(i).getFatherName());
				addCell(c1, cellFont, studentTable,duplicateStudentList.get(i).getMotherName());
				addCell(c1, cellFont, studentTable,duplicateStudentList.get(i).getDateOfBirth());
				addCell(c1, cellFont, studentTable,duplicateStudentList.get(i).getCategory());
				addCell(c1, cellFont, studentTable,duplicateStudentList.get(i).getGender());
				addCell(c1, cellFont, studentTable,duplicateStudentList.get(i).getProgramName());
				addCell(c1, cellFont, studentTable,duplicateStudentList.get(i).getBranchName());
				addCell(c1, cellFont, studentTable, duplicateStudentList.get(i).getNewSpecialization());
				addCell(c1, cellFont, studentTable, duplicateStudentList.get(i).getSemester());
			}
		
			document.add(studentTable);
			document.close();
			
			// Create a PDFFile from a File reference
			File f = new File(directoryName+sep+"Duplicate_Student_List"+".pdf");
		
			FileInputStream fis =	new FileInputStream(f);
			byte[] pdfContent = new byte[fis.available()];
			fis.read(pdfContent,0, fis.available());
			ByteBuffer bb = ByteBuffer.wrap(pdfContent);
			PDFFile pdfFile =
			new PDFFile(bb); // Create PDF Print Page

			PDFPrintPage pages =new PDFPrintPage(pdfFile);
			PrinterJob pjob = PrinterJob.getPrinterJob();
			PageFormat pf = PrinterJob.getPrinterJob().defaultPage();
			pjob.setJobName(f.getName());
			Book book =	new Book();
			book.append(pages, pf, pdfFile.getNumPages());
			pjob.setPageable(book);
			pjob.print();
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