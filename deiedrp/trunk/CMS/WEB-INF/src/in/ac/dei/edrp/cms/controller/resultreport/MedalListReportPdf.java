/*
 * @(#) MedalListReportPdf.java
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
package in.ac.dei.edrp.cms.controller.resultreport;

import in.ac.dei.edrp.cms.dao.resultreport.ResultReportConnect;
import in.ac.dei.edrp.cms.domain.degreelist.DegreeListInfoGetter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;



/**
 * This file generates the report (Medal List) 
 * for the university in a specific format
 * 
 * @author Ashish
 * @date 22 Nov 2011
 * @version 1.0
 */
public class MedalListReportPdf extends AbstractPdfView{

	private ResultReportConnect resultReportConnect;	

	String sep = System.getProperty("file.separator");
	ResourceBundle resourceBundle = ResourceBundle.getBundle("in" + sep + "ac"
			+ sep + "dei" + sep + "edrp" + sep + "cms" + sep
			+ "databasesetting" + sep + "MessageProperties", new Locale("en",
			"US"));		

	/**
	 * @param resultReportConnect the resultReportConnect to set
	 */
	public void setResultReportConnect(ResultReportConnect resultReportConnect) {
		this.resultReportConnect = resultReportConnect;
	}

	/**
	 * This is default method of AbstractPdfView used for generating PDF document
	 * 
	 * @param map, Object of Map
	 * @param document, The output PDF document
	 * @param pdfWriter, Object of PdfWriter
	 * @param request, HttpServletRequest
	 * @param response, HttpServletResponse
	 */
	@SuppressWarnings("unchecked")
	protected void buildPdfDocument(Map map, Document document, PdfWriter pdfWriter,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DegreeListInfoGetter infoGetter  = new DegreeListInfoGetter();		

		List<DegreeListInfoGetter> resultList = new ArrayList<DegreeListInfoGetter>();
		
		DegreeListInfoGetter degreeListInfoGetter = new DegreeListInfoGetter();
		
		PdfPTable pTable = null;		
		
		PdfPCell cell = null;
		
		Font cellFonts = new Font(Font.HELVETICA, 8);
		
		HttpSession session = request.getSession(true);
		
		infoGetter.setUniversityCode(session.getAttribute("universityId")
				.toString());
		infoGetter.setUniversityName(session.getAttribute("universityName").toString());
		infoGetter.setPassedFromSession(request.getParameter("fromSession"));
		infoGetter.setPassedToSession(request.getParameter("toSession"));
		
		
		/*
		 * path of the directory
		 */
		String directory = getServletContext().getRealPath("/")+resourceBundle.getString("directory")+sep+infoGetter.getUniversityName()+ sep
		+infoGetter.getPassedFromSession().substring(0,4)+"-"+infoGetter.getPassedToSession().substring(2,4)+sep+
			resourceBundle.getString("medallistreport");

		File file = new File(directory);

		file.mkdirs();
		
		document = new Document(PageSize.A4.rotate());
		
		pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(
				directory + sep + resourceBundle.getString("medallistreport")+ ".pdf"));
		
		HeaderFooter header = new HeaderFooter(new Phrase("                                                                "
				+resourceBundle.getString("medallistheader")+" "+
				whichConvocation(infoGetter.getPassedFromSession().substring(0,4))+" - Convocation"),false);
		
		HeaderFooter footerIN = new HeaderFooter(
				new Phrase(
						resourceBundle.getString("footerPart1")
								+"                                  "+resourceBundle
										.getString("footerPart4")+"                              "+resourceBundle.getString("registrarFooter")),
				false);
		
		header.setBorder(0);
		footerIN.setBorder(0);
		
		document.setHeader(header);
		document.setFooter(footerIN);
		
		document.open();
		
		document.add(Chunk.NEWLINE);
		
		float[] colsWidth = { 7, 5, 2 ,2 };
		
		pTable = new PdfPTable(colsWidth);
		
		pTable.addCell(new Phrase("Name of Examination", cellFonts));
		pTable.addCell(new Phrase("Student's Name", cellFonts));
		pTable.addCell(new Phrase("Roll #", cellFonts));
		pTable.addCell(new Phrase("CGPA", cellFonts));
		
		
		cell = new PdfPCell(new Phrase("                                                       " +
				"                       "+resourceBundle.getString("directormedal")));		
		cell.setColspan(4);
		
		pTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase(resourceBundle.getString("highestmarkscertificate")));		
		
		cell.setColspan(4);
		
		pTable.addCell(cell);
		
		/*
		 * for tencode of certificate examinations
		 */
		infoGetter.setProgramCourseKey("CE");
		
		resultList = resultReportConnect.getStudentsforCode(infoGetter);
		
		Iterator<DegreeListInfoGetter> iterator = resultList.iterator();
		
		while (iterator.hasNext()) {
			DegreeListInfoGetter listInfoGetter = (DegreeListInfoGetter) iterator
					.next();
			
			pTable.addCell(new Phrase(listInfoGetter.getProgramName()+" Examination"
						+", "+infoGetter.getPassedFromSession().substring(0,4),cellFonts));
			pTable.addCell(new Phrase(listInfoGetter.getStudentName(),cellFonts));
			pTable.addCell(new Phrase(listInfoGetter.getRollNumber(),cellFonts));
			pTable.addCell(new Phrase(listInfoGetter.getCgpa(),cellFonts));
		}
		
		cell = new PdfPCell(new Phrase(resourceBundle.getString("highestmarksdiploma")));		
		
		cell.setColspan(4);
		
		pTable.addCell(cell);
		
		/*
		 * tencode for diploma examinations
		 */
		infoGetter.setProgramCourseKey("DE");
		
		resultList = resultReportConnect.getStudentsforCode(infoGetter);
		
		Iterator<DegreeListInfoGetter> iteratorDiploma = resultList.iterator();
		
		while (iteratorDiploma.hasNext()) {
			DegreeListInfoGetter listInfoGetter = (DegreeListInfoGetter) iteratorDiploma
					.next();
			
			pTable.addCell(new Phrase(listInfoGetter.getProgramName()+" Examination"
					+", "+infoGetter.getPassedFromSession().substring(0,4),cellFonts));
			pTable.addCell(new Phrase(listInfoGetter.getStudentName(),cellFonts));
			pTable.addCell(new Phrase(listInfoGetter.getRollNumber(),cellFonts));
			pTable.addCell(new Phrase(listInfoGetter.getCgpa(),cellFonts));
		}	
		
		StringTokenizer coursesTokenizer = new StringTokenizer(resourceBundle.getString("coursesdetails"),",");
		
		String courseName = "";
		
		while (coursesTokenizer.hasMoreElements()) {
			Object object = (Object) coursesTokenizer.nextElement();
			
			infoGetter.setProgramCode(object.toString());		
			
			/*
			 * for courses
			 */		
			degreeListInfoGetter = resultReportConnect.getCourseName(infoGetter);
			
			courseName = courseName.trim()+" & "+degreeListInfoGetter.getCourseName().trim();
			
			String course = courseName.substring(1, courseName.length());
			
			cell = new PdfPCell(new Phrase("(C)"+resourceBundle.getString("highestmarksdefault")+course+"   "+resourceBundle.getString("gpa")));
			
		}	
		
		cell.setColspan(4);
		
		pTable.addCell(cell);
		
		/*
		 * for courses
		 */		
		resultList = resultReportConnect.getmaxoncourses(infoGetter);		
		
		Iterator<DegreeListInfoGetter> iteratorCRS = resultList.iterator();
		
		while (iteratorCRS.hasNext()) {
			DegreeListInfoGetter crsInfoGetter = (DegreeListInfoGetter) iteratorCRS
					.next();
			
			pTable.addCell(new Phrase(crsInfoGetter.getProgramName()+" Examination"+", "+infoGetter.getPassedFromSession().substring(0,4),cellFonts));
			pTable.addCell(new Phrase(crsInfoGetter.getStudentName(),cellFonts));
			pTable.addCell(new Phrase(crsInfoGetter.getRollNumber(),cellFonts));
			pTable.addCell(new Phrase(crsInfoGetter.getCgpa(),cellFonts));
		}
		
		cell = new PdfPCell(new Phrase(resourceBundle.getString("highestmarkssug")));		
		
		cell.setColspan(4);
		
		pTable.addCell(cell);
		
		/*
		 * tencode for UG examinations
		 */
		infoGetter.setProgramCourseKey("UG");
		
		resultList = resultReportConnect.getStudentsforCode(infoGetter);		
		
		Iterator<DegreeListInfoGetter> iteratorUG = resultList.iterator();
		
		while (iteratorUG.hasNext()) {
			DegreeListInfoGetter listInfoGetter = (DegreeListInfoGetter) iteratorUG
					.next();
			
			pTable.addCell(new Phrase(listInfoGetter.getProgramName()+" Examination"
					+", "+infoGetter.getPassedFromSession().substring(0,4),cellFonts));
			pTable.addCell(new Phrase(listInfoGetter.getStudentName(),cellFonts));
			pTable.addCell(new Phrase(listInfoGetter.getRollNumber(),cellFonts));
			pTable.addCell(new Phrase(listInfoGetter.getCgpa(),cellFonts));
		}
		
		cell = new PdfPCell(new Phrase(resourceBundle.getString("highestmarkspg")));		
		
		cell.setColspan(4);
		
		pTable.addCell(cell);
		
		/*
		 * tencode for PG examinations
		 */
		infoGetter.setProgramCourseKey("PG");
		
		resultList = resultReportConnect.getStudentsforCode(infoGetter);
		
		Iterator<DegreeListInfoGetter> iteratorPG = resultList.iterator();
		
		while (iteratorPG.hasNext()) {
			DegreeListInfoGetter listInfoGetter = (DegreeListInfoGetter) iteratorPG
					.next();
			
			pTable.addCell(new Phrase(listInfoGetter.getProgramName()+" Examination"
					+", "+infoGetter.getPassedFromSession().substring(0,4),cellFonts));
			pTable.addCell(new Phrase(listInfoGetter.getStudentName(),cellFonts));
			pTable.addCell(new Phrase(listInfoGetter.getRollNumber(),cellFonts));
			pTable.addCell(new Phrase(listInfoGetter.getCgpa(),cellFonts));
		}			
		
		cell = new PdfPCell(new Phrase("                                                       " +
				"                       "+resourceBundle.getString("presidentmedal")));		
		
		cell.setColspan(4);
		
		pTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("(F)"+resourceBundle.getString("highestmarksdefault")));		
		
		cell.setColspan(4);
		
		pTable.addCell(cell);
		
		StringTokenizer modesTokenizer = new StringTokenizer(resourceBundle.getString("modesdetails"),",");
		
		while (modesTokenizer.hasMoreElements()) {
			Object object = (Object) modesTokenizer.nextElement();
			
			infoGetter.setProgramCourseKey(object.toString());
			
			resultList = resultReportConnect.getStudentsforMAXUGPG(infoGetter);
			
			
			Iterator<DegreeListInfoGetter> iteratorMAX = resultList.iterator();
			
			while (iteratorMAX.hasNext()) {
				DegreeListInfoGetter listInfoGetter = (DegreeListInfoGetter) iteratorMAX
						.next();
				
				if(listInfoGetter.getProgramCode().equalsIgnoreCase("UG")){
					
					pTable.addCell(new Phrase(resourceBundle.getString("bestinug")
							+", "+infoGetter.getPassedFromSession().substring(0,4),cellFonts));
					
				}else if(listInfoGetter.getProgramCode().equalsIgnoreCase("PG")){
					
					pTable.addCell(new Phrase(resourceBundle.getString("bestinpg")
							+", "+infoGetter.getPassedFromSession().substring(0,4),cellFonts));
					
				}			
				pTable.addCell(new Phrase(listInfoGetter.getStudentName(),cellFonts));
				pTable.addCell(new Phrase(listInfoGetter.getRollNumber(),cellFonts));
				pTable.addCell(new Phrase(listInfoGetter.getCgpa(),cellFonts));
			}
			
		}		
		
		cell = new PdfPCell(new Phrase("                                                       " +
				"                       "+resourceBundle.getString("foundersmedal")));		
		
		cell.setColspan(4);
		
		pTable.addCell(cell);
		
		/*
		 * founder's medal details to be added 
		 */
		pTable.addCell(new Phrase("Yet to be decided",cellFonts));
		pTable.addCell(new Phrase("",cellFonts));
		pTable.addCell(new Phrase("",cellFonts));
		pTable.addCell(new Phrase("",cellFonts));
		
		document.add(pTable);
		
		document.close();
		
	}
	
	/**
	 * The method gives the details of the 
	 * number of convcation ie., present session 
	 * year subjected from the founding year of the
	 * concerned university(1981 in this case)
	 * @param sessionDate year if the session
	 * @return String
	 */
	private String whichConvocation(String sessionDate){
		
		int date = ((Integer.parseInt(sessionDate))-1981);
		
		/*
		 * conditions for binding correct text as per the
		 * number of convocation
		 */
		String dateTo = String.valueOf(date);
		
		if(dateTo.substring(dateTo.length()-1).contains("1")||
				(dateTo.length()==1 && dateTo.contains("1"))){
			
			dateTo = dateTo+"st";
		}else if(dateTo.substring(dateTo.length()-1).contains("2")||
				(dateTo.length()==1 && dateTo.contains("1"))){
			
			dateTo = dateTo+"nd";
		}else if(dateTo.substring(dateTo.length()-1).contains("3")||
				(dateTo.length()==1 && dateTo.contains("1"))){
			
			dateTo = dateTo+"rd";
			
		}else{
			
			dateTo = dateTo+"th";
			
		}
		
		return dateTo;
	}
}
