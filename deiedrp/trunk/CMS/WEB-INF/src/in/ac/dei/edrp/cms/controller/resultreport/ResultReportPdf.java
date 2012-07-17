/*
 * @(#) ResultReportPdf.java
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

import in.ac.dei.edrp.cms.dao.report.ReportDao;
import in.ac.dei.edrp.cms.dao.resultreport.ResultReportConnect;
import in.ac.dei.edrp.cms.domain.cgpadivision.CgpaDivisionInfoGetter;
import in.ac.dei.edrp.cms.domain.degreelist.DegreeListInfoGetter;
import in.ac.dei.edrp.cms.domain.report.ReportLogBean;
import in.ac.dei.edrp.cms.utility.ReportPath;
import in.ac.dei.edrp.cms.utility.ReportPathBean;

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
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;


/**
 * This file generates the reports (results) 
 * for different programs in the default format
 * 
 * @author Ashish
 * @date 17 Nov 2011
 * @version 1.0
 */
public class ResultReportPdf extends AbstractPdfView{
	
	private ResultReportConnect resultReportConnect;

	String sep = System.getProperty("file.separator");
	ResourceBundle resourceBundle = ResourceBundle.getBundle("in" + sep + "ac"
			+ sep + "dei" + sep + "edrp" + sep + "cms" + sep
			+ "databasesetting" + sep + "MessageProperties", new Locale("en",
			"US"));
	//*****************nupur ************
	int reportGenerated = 0;
	private ReportDao reportDao;
	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}
	//********************************
	
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
		
		String logResult="";
		String reportResult="";
		DegreeListInfoGetter infoGetter  = new DegreeListInfoGetter();		

		List<DegreeListInfoGetter> resultList = null;
		
		HttpSession session = request.getSession(true);
try{
		infoGetter.setEntityId(request.getParameter("entityId"));
		infoGetter.setEntityName(request.getParameter("entityName"));
		infoGetter.setProgramId(request.getParameter("programId"));
		infoGetter.setPassedFromSession(request.getParameter("fromSession"));
		infoGetter.setPassedToSession(request.getParameter("toSession"));
		infoGetter.setUniversityCode(session.getAttribute("universityId")
				.toString());
		infoGetter.setUniversityName(session.getAttribute("universityName").toString());
		infoGetter.setProgramName(request.getParameter("programName"));
		infoGetter.setProgramCode(request.getParameter("programCode"));
		infoGetter.setProgramPrintType(request.getParameter("semesterType"));
		
		List<DegreeListInfoGetter> list = new ArrayList<DegreeListInfoGetter>();

		list = (List<DegreeListInfoGetter>) model.get("resultObject");
		
		/*
		 * path of the directory
		 */
		//*********nupur code *******************
		ReportPathBean reportPathBean = new ReportPathBean(session.getAttribute("universityId").toString(),request.getParameter("entityId"),
				request.getParameter("programId"),request.getParameter("branchId"),request.getParameter("specializationId"),
				request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
				request.getParameter("enrollmentNumber"),request.getParameter("rollNumber"),request.getParameter("reportCode"),
				request.getParameter("reportType"),request.getParameter("fromSession"),request.getParameter("toSession"),request.getParameter("semesterType"));
		
		String reportPath = ReportPath.getPath(reportPathBean);				
		System.out.println("here in controller after path generation "+reportPath);
		String path = this.getServletContext().getRealPath("/");
		path=path+reportPath;
		File file = new File(path);
		file.mkdirs();
		//***************************************
//		String directory = getServletContext().getRealPath("/")+resourceBundle.getString("directory") +sep+infoGetter.getUniversityName()+ sep
//		+infoGetter.getPassedFromSession().substring(0,4)+
//		"-"+infoGetter.getPassedToSession().substring(2,4)+sep+infoGetter.getEntityName()+sep+
//			infoGetter.getProgramName()+sep+resourceBundle.getString("resultreports");

//		File file = new File(directory);
		
		Font cellFont = new Font(Font.HELVETICA, 7);
		
		Font cellFonts = new Font(Font.HELVETICA, 10);

//		file.mkdirs();
		
		document = new Document(PageSize.A4.rotate());		
		
		CgpaDivisionInfoGetter cgpaDivisionInfoGetter = new CgpaDivisionInfoGetter();
		
		cgpaDivisionInfoGetter.setUserId(session.getAttribute("userId").toString());
		
		List<CgpaDivisionInfoGetter>  result = resultReportConnect.getSetDivisionRecords(cgpaDivisionInfoGetter);
		
		Iterator<CgpaDivisionInfoGetter> iteratorDivision = result.iterator();
		
		String divisionDetails = "";
		
		while (iteratorDivision.hasNext()) {
			CgpaDivisionInfoGetter divisionInfoGetter = (CgpaDivisionInfoGetter) iteratorDivision
					.next();
			
			divisionDetails = divisionDetails + " " +divisionInfoGetter.getDivisionId()+" - "+divisionInfoGetter.getDivisionDescription() ;
			
		}
		
		String name = "",type = "";
		
		HeaderFooter headerIN = null;
		
		if(infoGetter.getUniversityName().contains("(")){
			
			name = infoGetter.getUniversityName().substring(0,infoGetter.getUniversityName().indexOf("("));
			type = infoGetter.getUniversityName().substring(infoGetter.getUniversityName().indexOf("("));
			
			headerIN = new HeaderFooter(
					new Phrase(
									  "                                                                                                          "
									+ name+"\n                                                                                                                   "+type
									+ "\n"
									+ "                                                                                                                      "
									+ session.getAttribute("address").toString()+","+session.getAttribute("city").toString()
									+ "\n"+"                                                                                                                      "+
									"Results "+infoGetter.getPassedFromSession()
											.substring(0, 4)
									+ "-"
									+ infoGetter.getPassedToSession()
											.substring(2, 4)+"\n\n"+resourceBundle
											.getString("division").toUpperCase()+":"+"\n"+divisionDetails+"\n"+resourceBundle
											.getString("result")+"\n"+resourceBundle
											.getString("resultdetails"),cellFonts),
					false);
			
		}else{
			
			headerIN = new HeaderFooter(
					new Phrase(
									  "                                                                                                          "
									+infoGetter.getUniversityName()+ "\n"
									+ "                                                                                                                      "
									+ session.getAttribute("address").toString()+","+session.getAttribute("city").toString()
									+ "\n"+"                                                                                                                      "+
									"Results "+infoGetter.getPassedFromSession()
											.substring(0, 4)
									+ "-"
									+ infoGetter.getPassedToSession()
											.substring(2, 4)+"\n\n"+resourceBundle
											.getString("division").toUpperCase()+":"+"\n"+divisionDetails+"\n"+resourceBundle
											.getString("result")+"\n"+resourceBundle
											.getString("resultdetails"),cellFonts),
					false);
			
		}
		
		HeaderFooter footer = new HeaderFooter(new Phrase("",cellFont), true);
		
			
		
		pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(path+sep+request.getParameter("reportDescription")+".pdf"));
//		pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(
//				directory + sep + infoGetter.getProgramName()+"("+infoGetter.getProgramPrintType()+" Semesters"+")" + ".pdf"));
		
		footer.setAlignment(Element.ALIGN_RIGHT);
		
		headerIN.setBorder(0);
		footer.setBorder(0);
		
		
		document.setHeader(headerIN);
		document.setFooter(footer);
		
		document.open();

		document.add(Chunk.NEWLINE);
		Paragraph emptyParagraph= new Paragraph(new Phrase("\n",cellFont));	
		
		Iterator<DegreeListInfoGetter> iterator = list.iterator();
		
		List<String> semesterList = new ArrayList<String>();
		
		
		
		PdfPCell pcel;
		
		int i=0,counter = 0;
		List<String> strings = new ArrayList<String>();	
		while (iterator.hasNext()) {
			DegreeListInfoGetter semesterListInfoGetter = (DegreeListInfoGetter) iterator
					.next();
			
			i=i+1;				
			
			if(semesterList.indexOf(semesterListInfoGetter.getSemesterCode())==-1){
				semesterList.add(semesterListInfoGetter.getSemesterCode());	
				
				PdfPTable pTable=new PdfPTable(new float[]{1,1});
				
				document.add(emptyParagraph);				
				
				pTable.setWidthPercentage(100);
				
				pcel=new PdfPCell(new Phrase(infoGetter.getProgramName(),cellFont));
				pcel.setHorizontalAlignment(Element.ALIGN_LEFT);
				pcel.setBorderWidth(0);
				
				pTable.addCell(pcel);
				
				pcel=new PdfPCell(new Phrase(semesterListInfoGetter.getSemesterName(),cellFont));
				pcel.setHorizontalAlignment(Element.ALIGN_RIGHT);
				pcel.setBorderWidth(0);
				
				pTable.addCell(pcel);
				
				
				
//				Paragraph ddd=new Paragraph(new Phrase(""+infoGetter.getProgramName()+"														                                                                  " +
//						"                                                                                                                " +
//						"                                                                                                                " +
//						"                             " +
//						"                     "+semesterListInfoGetter.getSemesterName()+"    ",cellFont));
				document.add(pTable);
				document.add(emptyParagraph);	
			}
			semesterListInfoGetter.setEntityId(infoGetter.getEntityId());
			
			resultList = resultReportConnect.getStudentsForCombination(semesterListInfoGetter);
			String modifiedRollNo="";
			int ii=0;
				
			
			Iterator<DegreeListInfoGetter> resultIterator = resultList.iterator();
			int rollcount=resultList.size();
			rollcount=9-rollcount%9;
			PdfPTable roltable=new PdfPTable(new float[]{1,1,1,1,1,1,1,1,1});
			roltable.setWidthPercentage(100);
			
			
			while (resultIterator.hasNext()) {
				DegreeListInfoGetter studentListGetter = (DegreeListInfoGetter) resultIterator
						.next();
				ii=ii+1;				
				
				String rolToSet="";
				modifiedRollNo=studentListGetter.getRollNumber().substring(0,studentListGetter.getRollNumber().length()-2);
				
				logger.info("roll "+modifiedRollNo+" ii "+ii +" count "+counter);
				
				if((ii==1)&&(counter==0)){					
					
					strings.add(modifiedRollNo);
					rolToSet=studentListGetter.getRollNumber();
					
					counter = counter+1;
				}else{
				
				if(strings.indexOf(modifiedRollNo)==-1){
					
					rolToSet=studentListGetter.getRollNumber();
					strings.add(modifiedRollNo);
					pcel=new PdfPCell(new Phrase("",cellFont));
					pcel.setBorderWidth(0);
					pcel.setColspan(9);
					roltable.addCell(pcel);
					
				}else{
					rolToSet=studentListGetter.getRollNumber().substring(studentListGetter.getRollNumber().length()-2,
							studentListGetter.getRollNumber().length());					
					}
				}			
				
				if("F".equalsIgnoreCase(semesterListInfoGetter.getFinalSemesterCode())){
					pcel=new PdfPCell(new Phrase(rolToSet+"      ("+studentListGetter.getDivision()+")   ",cellFont));
					pcel.setBorderWidth(0);
					pcel.setHorizontalAlignment(Element.ALIGN_RIGHT);
					roltable.addCell(pcel);
					
				}else{				
					
					pcel=new PdfPCell(new Phrase(rolToSet+"      ("+studentListGetter.getStudentStatus()+")   ",cellFont));
					pcel.setBorderWidth(0);
					pcel.setHorizontalAlignment(Element.ALIGN_RIGHT);
					
					roltable.addCell(pcel);
				}			
			}
			pcel=new PdfPCell(new Phrase("",cellFont));
			pcel.setBorderWidth(0);
			for(int c=0;c<rollcount;c++){
				roltable.addCell(pcel);
			}
			
			document.add(roltable);
		}		
		document.close();	
		//****************************NUpur*****************************		
		System.out.println("nupur : report created successfully");
		reportResult="true";		
			
}catch(Exception e){
	reportResult="false";	
	ReportLogBean reportErrorBean = new ReportLogBean(session.getAttribute("universityId").toString(),request.getParameter("programId"),
			request.getParameter("branchId"),request.getParameter("specializationId"),request.getParameter("entityId"),
			request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
			request.getParameter("reportCode"),"",request.getParameter("rollNumber"),
			request.getParameter("fromSession").substring(0, 4)+"-"+request.getParameter("toSession").substring(2,4));
	logResult = reportDao.insertReportErrorLog(reportErrorBean);
	logger.error("inside exception of result report generation "+e);
	System.out.println("inside the exception "+e);
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
		System.out.println("report control log result "+logResult);
}
}catch(Exception e){
	logger.error("some exception in log entry "+e);
}
//***************************************************************

	}

}
