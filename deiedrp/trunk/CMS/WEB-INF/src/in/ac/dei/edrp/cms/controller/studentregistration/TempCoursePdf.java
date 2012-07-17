/*
 * @(#) MouCoursesImpl.java
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


package in.ac.dei.edrp.cms.controller.studentregistration;

import in.ac.dei.edrp.cms.dao.studentregistration.TempCoursePdfDao;
import in.ac.dei.edrp.cms.domain.activitymaster.StartActivityBean;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentInfoGetter;

import java.io.FileOutputStream;
import java.util.ArrayList;
/*
 * @(#) TempCoursePdf.java
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

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;


public class TempCoursePdf extends AbstractPdfView{
	private TempCoursePdfDao tempCoursePdfDao;
	
	/**
	 * @param tempCoursePdfDao the tempCoursePdfDao to set
	 */
	public void setTempCoursePdfDao(TempCoursePdfDao tempCoursePdfDao) {
		this.tempCoursePdfDao = tempCoursePdfDao;		
	}

	@SuppressWarnings("unchecked")	
	/**
	 * Method buildPdfDocument is an abstract method which is overridden
	 * It takes the student info and generate pdf of temporary courses opted by student
	 * @param model: for getting the info in the form of map
	 * @param document for generating the document
	 * @param writer for getting the instance of document to be written.
	 * 
	 */
	protected void buildPdfDocument(Map model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		StartActivityBean startActivityBean = (StartActivityBean) model.get("temporaryInfo");		
		List<StudentInfoGetter> studentCourses = new ArrayList<StudentInfoGetter>();				
		StudentInfoGetter studentInfoGetter = new StudentInfoGetter();
		List<StudentInfoGetter> studentList = tempCoursePdfDao.getStudentInfo(startActivityBean);
		studentInfoGetter.setSemesterStartDate(startActivityBean.getSemesterStartDate());
		studentInfoGetter.setSemesterEndDate(startActivityBean.getSemesterEndDate());
				
		writer = PdfWriter.getInstance(document, new FileOutputStream("D:\\StudentTemporaryCourse.pdf"));
		document.open();
		Font cellFont = new Font(Font.HELVETICA, 8);	
		String headerText = "Courses Opted By Students";
		Paragraph header = new Paragraph(headerText,cellFont);
		header.setAlignment(Element.ALIGN_CENTER);
		document.add(header);
		
		document.add(Chunk.NEWLINE);
		
		float[] colsWidth = {1f,1f,2f,4f}; // Code 1
		PdfPTable headerTable = new PdfPTable(colsWidth);		
				
		String courses ="";
		
		headerTable.addCell(new Phrase("Registration Number",cellFont));
		headerTable.addCell(new Phrase("Name : ",cellFont));		
		headerTable.addCell(new Phrase("Father Name",cellFont));
		headerTable.addCell(new Phrase("Courses ",cellFont));
				
		for(StudentInfoGetter studentInfo : studentList){			
			studentInfoGetter.setStudentId(studentInfo.getStudentId());
			
			headerTable.addCell(new Phrase(studentInfo.getRegistrationNumber(),cellFont));
			headerTable.addCell(new Phrase(studentInfo.getStudentName(),cellFont));
			headerTable.addCell(new Phrase(studentInfo.getFatherName(),cellFont));
			
			
			studentCourses = tempCoursePdfDao.getStudentCourses(studentInfoGetter);
			
			for(StudentInfoGetter courseInfo : studentCourses)
			{
				courses = courses+courseInfo.getCourseCode()+",";
			}
			headerTable.addCell(new Phrase(courses,cellFont));
			courses = "";		
		}
		document.add(headerTable);
		document.close();
	}
}
