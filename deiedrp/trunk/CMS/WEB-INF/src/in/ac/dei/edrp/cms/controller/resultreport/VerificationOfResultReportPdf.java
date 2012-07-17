/*
 * @(#) VerificationOfResultReportPdf.java
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * This file generates the reports (result verifications) 
 * for different programs in the required format
 * 
 * @author Ashish
 * @date 17 Nov 2011
 * @version 1.0
 */
public class VerificationOfResultReportPdf extends AbstractPdfView{

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
	 * @param model, Object of Map
	 * @param document, The output PDF document
	 * @param pdfWriter, Object of PdfWriter
	 * @param request, HttpServletRequest
	 * @param response, HttpServletResponse
	 */
	@SuppressWarnings("unchecked")
	protected void buildPdfDocument(Map model, Document document, PdfWriter pdfWriter,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DegreeListInfoGetter infoGetter  = new DegreeListInfoGetter();		

		List<DegreeListInfoGetter> resultList = new ArrayList<DegreeListInfoGetter>();
		
		HttpSession session = request.getSession(true);
		
		infoGetter.setUniversityCode(session.getAttribute("universityId")
				.toString());
		infoGetter.setUniversityName(session.getAttribute("universityName").toString());
		
		/*
		 * path of the directory
		 */
		String directory = getServletContext().getRealPath("/")+resourceBundle.getString("directory") +sep+infoGetter.getUniversityName()+ 
		sep+resourceBundle.getString("verificationresultreports");

		File file = new File(directory);

		file.mkdirs();
		
		document = new Document(PageSize.A4.rotate());
		/*
		 * temporary list of roll numbers
		 */
		List rollList = new ArrayList();	
		
		rollList.add("114021");
		rollList.add("114022");
		rollList.add("114023");
		rollList.add("114024");
		rollList.add("114025");
		rollList.add("114026");
		
		Iterator iterator = rollList.iterator();
		
		pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(
				directory + sep + "CompanyName" + ".pdf"));
		
		HeaderFooter footerIN = new HeaderFooter(
				new Phrase(
						resourceBundle.getString("verificationfooter")
								+"\n\n"+ "                                                                          " +
										"                                                                         " +
										"                "
								+ resourceBundle
										.getString("registrardetails")),
				false);

		footerIN.setBorder(0);
		
		document.setFooter(footerIN);
		
		document.open();

		document.add(Chunk.NEWLINE);
		Paragraph emptyParagraph= new Paragraph(new Phrase("                                                              " +
				"                            "+resourceBundle.getString("confidential")
														+"\n\n\n\n\n\n\n\n"));
		
		Paragraph otherDetailsParagraph = new Paragraph(new Phrase("                      "+resourceBundle.getString("referencedetails")+"" +
				"  reference details with date"+"\n"
						+"                      "+resourceBundle.getString("mailsubject")+"  "+resourceBundle.getString("defaultsubject")+"\n\n"));
		
		Paragraph newParagraph = new Paragraph(new Phrase(resourceBundle.getString
									("greetHeader")+"\n"+"          "+resourceBundle.getString("defaultText")));
		
		Phrase phrase = new Phrase("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -" +
				" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -" +
				" - - - - - - - - - - - - - - - - - - - - -");
		
		document.add(emptyParagraph);
		document.add(otherDetailsParagraph);
		document.add(newParagraph);
		document.add(phrase);
		
		
		
		PdfPTable pTable  = null;
		PdfPCell cel =null;
		
		Phrase cgpaPhrase =null;
		
		int i=0;
		int j = 0;
		
		while (iterator.hasNext()) {
			
			Object object = (Object) iterator.next();
			
			infoGetter.setRollNumber(object.toString());
			
			resultList = resultReportConnect.getStudents4Verification(infoGetter);
			Phrase studentDataPhrase = new Phrase();			
			for(@SuppressWarnings("unused") DegreeListInfoGetter getter:resultList){				
				
				pTable = new PdfPTable(new float[] { 7, 3 });				
				
				cel =new PdfPCell(new Phrase(i+1+". "+resourceBundle.getString("studentName")
						+resultList.get(0).getStudentName()));
				
				cel.setBorderWidth(0);				
				pTable.addCell(cel);
				
				cel =new PdfPCell(new Phrase("           "+resourceBundle.getString("rollNumber")+resultList.get(0).getStudentRollNumber()));
				
				cel.setBorderWidth(0);
				pTable.addCell(cel);
				
				cel =new PdfPCell(new Phrase("    "+resourceBundle.getString("passedExam")
						+resultList.get(0).getProgramName()));
				
				cel.setBorderWidth(0);
				pTable.addCell(cel);
				
				cel =new PdfPCell(new Phrase("           "+resourceBundle.getString("branchName")+resultList.get(0).getBranchName()));
				
				cel.setBorderWidth(0);
				pTable.addCell(cel);
				
				cel =new PdfPCell(new Phrase("    "+resourceBundle.getString("specialization")+resultList.get(0).getSpecializationName()));
				
				cel.setBorderWidth(0);
				pTable.addCell(cel);
				
				cel =new PdfPCell(new Phrase("           "+resourceBundle.getString("session")+resultList.get(0).getPassedFromSession()));
				
				cel.setBorderWidth(0);
				pTable.addCell(cel);
				
				if(resultList.get(0).getProgramPrintType().equalsIgnoreCase("SAG")){					
					cgpaPhrase =new Phrase();					
					cgpaPhrase.add(new Phrase("                            "+resourceBundle.getString("cgpa")
								+resultList.get(0).getCgpa()+"                        "
								+resourceBundle.getString("division")+": "+resultList.get(0).getDivision()));				
					
				}else if(resultList.get(0).getProgramPrintType().equalsIgnoreCase("TAP")){					
					cgpaPhrase =new Phrase();					
					cgpaPhrase.add(new Phrase("                            "+resourceBundle.getString("cgpa")
								+"                                 "+resourceBundle.getString("cgpatheory")+": "+resultList.get(0).getTheoryCGPA()+"                        "
								+resourceBundle.getString("cgpapractical")+": "+resultList.get(0).getPracticalCGPA()+"\n"+
								"                             "+resourceBundle.getString("cgpacombined")+
								"                              "+resultList.get(0).getCgpa()));
					
				}				
				cgpaPhrase.add("\n");
				
				j = j+1;
				
				if(j==1){
					
					Iterator<DegreeListInfoGetter> iterator2 = resultList.iterator();
					
					studentDataPhrase.add(new Phrase("                            "+resourceBundle.getString("semestersgpa")));
					
					while (iterator2.hasNext()) {
						DegreeListInfoGetter degreeListInfoGetter = (DegreeListInfoGetter) iterator2
								.next();						
						
						if(resultList.get(0).getProgramPrintType().equalsIgnoreCase("SAG")){
							
							studentDataPhrase.add(new Phrase("                 "+
											degreeListInfoGetter.getSemesterCode()+"-"+degreeListInfoGetter.getSGPA()));
							
						}else if(resultList.get(0).getProgramPrintType().equalsIgnoreCase("TAP")){
							
							studentDataPhrase.add(new Phrase("                 "+resourceBundle.getString("cgpatheory")+":   "+
									degreeListInfoGetter.getSemesterCode()+"-"+degreeListInfoGetter.getTheorySGPA()+"         "
									+resourceBundle.getString("cgpapractical")+
									":   "+degreeListInfoGetter.getSemesterCode()+"-"+degreeListInfoGetter.getPracticalSGPA()+"\n"
									+"                                                        "));							
						}
					}					
					studentDataPhrase.add(new Phrase("\n"));
				}
			}			
			i=i+1;
			j=0;			
			document.add(pTable);
			document.add(studentDataPhrase);
			document.add(cgpaPhrase);
			document.add(phrase);
		}		
		document.close();		
	}
}
