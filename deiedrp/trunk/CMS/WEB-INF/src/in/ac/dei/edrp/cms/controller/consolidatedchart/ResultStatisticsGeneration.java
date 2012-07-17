/*
 * @(#) ResultStatisticsGenerator.java
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
import in.ac.dei.edrp.cms.domain.consolidatedchart.ResultStatisticsInfo;
import in.ac.dei.edrp.cms.domain.report.ReportLogBean;
import in.ac.dei.edrp.cms.utility.ReportPath;
import in.ac.dei.edrp.cms.utility.ReportPathBean;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.view.document.AbstractPdfView;

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
 * this is Server side PDF generator class for Result Statistics report
 * 
 * @version 1.0 8 September 2011
 * @author MOHD AMIR
 */
public class ResultStatisticsGeneration extends AbstractPdfView {
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void buildPdfDocument(Map map, Document document,PdfWriter pdfWriter, HttpServletRequest request,
									HttpServletResponse response) throws Exception {		
		HttpSession session = request.getSession(true);
		String logResult="";
		String reportResult="";
	try{	
		List<ResultStatisticsInfo> outputList = (List<ResultStatisticsInfo>) map.get("outputList");
		ResultStatisticsInfo headerData = outputList.get(outputList.size() - 1);
		outputList.remove(outputList.size() - 1);		
		String universityName = session.getAttribute("universityName") + "";
		String universitySession = session.getAttribute("startDate").toString().substring(0, 4)+ "-"+ session.getAttribute("endDate").toString().substring(0, 4);
		String reportSession = headerData.getSessionStartDate().substring(0, 4)+ "-" + headerData.getSessionEndDate().substring(2, 4);
		String sep = System.getProperty("file.separator");
		//making the directory for storing the file
		//*********nupur code *******************
		ReportPathBean reportPathBean = new ReportPathBean(session.getAttribute("universityId").toString(),request.getParameter("entityId"),
				request.getParameter("programId"),request.getParameter("branchId"),request.getParameter("specializationId"),
				request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
				request.getParameter("enrollmentNumber"),request.getParameter("rollNumber"),request.getParameter("reportCode"),
				request.getParameter("reportType"),request.getParameter("fromSession"),request.getParameter("toSession"),request.getParameter("semesterType"));
				
		String reportPath = ReportPath.getPath(reportPathBean);				
		System.out.println("here in controller, after path generation "+reportPath);
		String path = this.getServletContext().getRealPath("/");
		path=path+reportPath;
		File file = new File(path);
		file.mkdirs();
		//*****************************************************
		
		/*String path = this.getServletContext().getRealPath("/")
				+ "ResultStatisticsReports";*/

		String semesterWise = "";
		if (headerData.getSemesterWise() == 0) {
			semesterWise = "(EVEN SEMESTER)";
		} else {
			semesterWise = "(ODD SEMESTER)";
		}

		/*File file = new File(path);
		file.mkdir();*/
		document = new Document(PageSize.A4.rotate());
		pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(path+sep+ request.getParameter("reportDescription")+".pdf"));
		/*pdfWriter = PdfWriter.getInstance(document,new FileOutputStream(path + sep + headerData.getEntityId()
						+ "-" + headerData.getSessionStartDate() + "-"+ headerData.getSessionEndDate() + "-" + semesterWise
						+ "-ResultStatistics.pdf"));*/
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		String space = "                                                            ";
		Chunk line = new Chunk(
				"___________________________________________________________________________________________________________________");

		Phrase universityInfo = new Phrase(universityName.toUpperCase() + "\n"
				+ universitySession + "\n\n", FontFactory.getFont(
				FontFactory.HELVETICA, 10, Font.BOLD, new Color(0, 0, 0)));

		Phrase reportInfo = new Phrase("RESULT STATISTICS" + space + reportSession + semesterWise + space + sdf.format(new Date()),
						FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, new Color(0, 0,0)));

		Font headerCellFont = FontFactory.getFont(FontFactory.HELVETICA, 8,Font.BOLD, new Color(0, 0, 0));

		PdfPTable headerTable = new PdfPTable(new float[] { 4.2f, 2.5f, 2.4f,
											2.4f, 1.1f, 2.6f, 1.9f, 1.3f, 1, 1, 1.2f, 3, 3, 3, 2 });
		headerTable.setWidthPercentage(100f);
		headerTable.setHorizontalAlignment(Element.ALIGN_CENTER);

		addCell(headerCellFont, headerTable, "F -CL-BR-S");
		addCell(headerCellFont, headerTable, "SEX");
		addCell(headerCellFont, headerTable, "ENROLLED");
		addCell(headerCellFont, headerTable, "APPEARED");
		addCell(headerCellFont, headerTable, "UFM");
		addCell(headerCellFont, headerTable, "INCOMPLETE");
		addCell(headerCellFont, headerTable, "PASSED");
		addCell(headerCellFont, headerTable, "I-DIST");
		addCell(headerCellFont, headerTable, "I");
		addCell(headerCellFont, headerTable, "II");
		addCell(headerCellFont, headerTable, "PASS");
		addCell(headerCellFont, headerTable, "ELIGIBLE\nFOR REMEDIAL");
		addCell(headerCellFont, headerTable, "FAILED\n1ST ATTEMPT");
		addCell(headerCellFont, headerTable, "FAILED\n2ND ATTEMPT");
		addCell(headerCellFont, headerTable, "PASSED\n%");

		Phrase headerPhrase = new Phrase();
		headerPhrase.add(universityInfo);
		headerPhrase.add(reportInfo);
		headerPhrase.add(headerTable);
		headerPhrase.add(line);

		HeaderFooter header = new HeaderFooter(headerPhrase, false);

		HeaderFooter footer = new HeaderFooter(new Phrase("Page:- "), true);

		header.setAlignment(Element.ALIGN_CENTER);
		header.setBorderWidth(0);
		footer.setAlignment(Element.ALIGN_RIGHT);
		footer.setBorderWidth(0);

		Font dataCellFont = FontFactory.getFont("UTF-8", 8, Font.NORMAL,new Color(0, 0, 0));

		PdfPTable dataTable = new PdfPTable(new float[] { 4.2f, 2.5f, 2.4f,2.4f, 1.1f, 2.6f, 1.9f, 1.3f, 1, 1, 1.2f, 3, 3, 3, 2 });
		dataTable.setWidthPercentage(100f);
		dataTable.setHorizontalAlignment(Element.ALIGN_CENTER);

		for (int i = 0; i < outputList.size(); i++) {
			ResultStatisticsInfo rowData = outputList.get(i);
			if (i % 3 == 0) {
				addCell(dataCellFont,dataTable,rowData.getEntityName() + "-"
								+ rowData.getProgramName() + "-"
								+ rowData.getBranchId() + "-"
								+ rowData.getSemesterId());
			} else {
				addCell(dataCellFont, dataTable, " ");
			}

			addCell(dataCellFont, dataTable, rowData.getGender());
			addCell(dataCellFont, dataTable, rowData.getEnrolled());
			addCell(dataCellFont, dataTable, rowData.getAppeared());
			addCell(dataCellFont, dataTable, rowData.getUfm());
			addCell(dataCellFont, dataTable, rowData.getIncomplete());
			addCell(dataCellFont, dataTable, rowData.getPassed());
			addCell(dataCellFont, dataTable, rowData.getFstDic());
			addCell(dataCellFont, dataTable, rowData.getFstDiv());
			addCell(dataCellFont, dataTable, rowData.getScndDiv());
			addCell(dataCellFont, dataTable, " ");
			addCell(dataCellFont, dataTable, rowData.getRemedial());
			addCell(dataCellFont, dataTable, rowData.getFailedAt1());
			addCell(dataCellFont, dataTable, rowData.getFailedAt2());
			addCell(dataCellFont,dataTable,String.valueOf(String.format("%.2f",
							Double.parseDouble(rowData.getPassPercentage()))));
		}
		document.setHeader(header);
		document.setFooter(footer);
		document.open();
		document.add(dataTable);
		document.close();
		//****************************NUpur*****************************		
		System.out.println("nupur : report created successfully");
		reportResult="true";
	}catch(Exception e){
		e.printStackTrace();
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
			System.out.println("before control log insert");		
			logResult = reportDao.insertReportLog(reportControlBean);
			System.out.println("report control log result "+logResult);
	}
	}catch(Exception e){
		System.out.println("some exception in log entry "+e);
	}

	}

	/**
	 * This method add a cell to Table
	 * 
	 * @param cellFont
	 *            , Font used
	 * @param table
	 *            , PdfPTable in which cell is added
	 * @param value
	 *            , Text string in cell
	 */
	public static final void addCell(Font cellFont, PdfPTable table,
			String value) {
		PdfPCell c1 = new PdfPCell(new Phrase(value, cellFont));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setVerticalAlignment(Element.ALIGN_TOP);
		table.addCell(c1);
	}
}