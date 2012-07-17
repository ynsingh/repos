/**
 * @(#) ConsolidatedMarkSheetGeneration.java
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
package in.ac.dei.edrp.cms.controller.consolidatedchart;

import in.ac.dei.edrp.cms.dao.report.ReportDao;
import in.ac.dei.edrp.cms.daoimpl.consolidatedchart.ConsolidatedChartImpl;
import in.ac.dei.edrp.cms.domain.consolidatedchart.ConsolidatedMarkSheetInfo;
import in.ac.dei.edrp.cms.domain.report.ReportLogBean;
import in.ac.dei.edrp.cms.utility.ReportPath;
import in.ac.dei.edrp.cms.utility.ReportPathBean;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Calendar;
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
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import common.Logger;

/**
 * The server side PDF Generator for Consolidated Marksheet
 *
 * @version 1.0 07 SEP 2011
 * @author ROHIT SAXENA
 */
public class ConsolidatedMarkSheetGeneration extends AbstractPdfView {
	private ReportDao reportDao;
	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}
	/* Make object of logger class */
	private static Logger logObj = Logger.getLogger(ConsolidatedChartImpl.class);
	
	/**
	* This is default method of AbstractPdfView used for generating PDF document
	*
	* @param map, Object of Map
	* @param document, The output PDF document
	* @param pdfWriter, Object of PdfWriter
	* @param request, HttpServletRequest
	* @param response, HttpServletResponse
	*/
	@Override
	protected void buildPdfDocument(Map map, Document document,PdfWriter pdfWriter, HttpServletRequest request,
			HttpServletResponse response) {        
	}

	
	protected String generateGradeReport(List<List<ConsolidatedMarkSheetInfo>> markSheetDataList,HttpServletRequest request) {           
            String result = "false";		
    		String logResult="";		
    		int reportGenerated = 0;
    		File file;
    		HttpSession session=request.getSession(true);
//		List<List<ConsolidatedMarkSheetInfo>> markSheetDataList = (List<List<ConsolidatedMarkSheetInfo>>) map.get("outputDataList");
		try{
        Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String dated = formatter.format(currentDate.getTime());	
		String sep = System.getProperty("file.separator");		
		//*********nupur code *******************
		ReportPathBean reportPathBean = new ReportPathBean(session.getAttribute("universityId").toString(),request.getParameter("entityId"),
				request.getParameter("programId"),request.getParameter("branchId"),request.getParameter("specializationId"),
				request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
				request.getParameter("enrollmentNumber"),request.getParameter("rollNumber"),request.getParameter("reportCode"),
				request.getParameter("reportType"),request.getParameter("fromSession"),request.getParameter("toSession"),request.getParameter("semesterType"));
		
		String reportPath = ReportPath.getPath(reportPathBean);				
		System.out.println("here in controller of consolidated chart after path generation yes here "+reportPath);
		String filePath = this.getServletContext().getRealPath("/");
		filePath=filePath+reportPath;		
		file = new File(filePath);
		file.mkdirs();		
		//***************************************						
		/*String path = this.getServletContext().getRealPath("/") + "ConsolidatedMarkSheets";
		File file = new File(path);
		file.mkdir();*/
		for (int i = 0; i < markSheetDataList.size(); i++) {
			String rollNo = "";
			try{
				System.out.println("inside first try "+i);
			List<ConsolidatedMarkSheetInfo> markSheetData = markSheetDataList.get(i);
			ConsolidatedMarkSheetInfo headerData = markSheetData.get(markSheetData.size() - 1);
			rollNo = headerData.getRollNo();
			System.out.println("roll number is  "+rollNo);
			String universityName = headerData.getMarksheetHeader();
			String universitySession = headerData.getPassFromDate().substring(0, 4)
					+ "-" + headerData.getPassToDate().substring(0, 4);
			Document document = new Document(PageSize.A4);
			PdfWriter.getInstance(document, new FileOutputStream(filePath+sep+request.getParameter("reportDescription")+"_"+rollNo+".pdf"));			
//			PdfWriter.getInstance(document, new FileOutputStream(path + sep + rollNo + ".pdf"));
             System.out.println("rohit till pdf name");
			HeaderFooter header = new HeaderFooter(new Phrase(universityName.toUpperCase()+ headerData.getProgramName().toUpperCase()
					+ " EXAMINATION \n GRADE REPORT(CONSOLIDATED)", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(0,
							0, 0))), false);
			HeaderFooter footer = new HeaderFooter(
					new Phrase(
							"DATED: "
									+ dated
									+ "                                                  CHECKED BY"
									+ "                                                  ASST. REGISTRAR(ACAD.)\nFOR REGISTRAR\n\n\n"
									+ "NOTE: THE GRADE POINT AVERAGE CAN BE CONVERTED INTO PERCENTAGE MARKS BY MULTIPLYING WITH 10.      "
									+ "\n(As an example for a GPA of 7.79, the percentage will be 77.9%)                                                                                                   ",
							FontFactory.getFont(FontFactory.HELVETICA, 9,Font.NORMAL, new Color(0, 0, 0))), false);
			header.setBorderWidth(0);
			header.setAlignment(Element.ALIGN_CENTER);
			footer.setBorderWidth(0);
			footer.setAlignment(Element.ALIGN_RIGHT);
			document.setHeader(header);
			document.setFooter(footer);
			document.open();

			Paragraph studentRollInfo = new Paragraph("ENROLMENT No.: "
					+ headerData.getEnrollNo() + "\n  ROLL No.             : "
					+ headerData.getRollNo(), FontFactory.getFont(
					FontFactory.HELVETICA, 10, Font.NORMAL, new Color(0, 0, 0)));

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dob = sdf.parse(headerData.getDob());

			SimpleDateFormat modifiedsdf = new SimpleDateFormat("dd-MM-yyyy");
			Paragraph studentInfo = new Paragraph(" NAME: "	+ headerData.getStudentName().toUpperCase()	
					+ "\n  DATE OF BIRTH: " + modifiedsdf.format(dob)
					+ "\n  FATHER'S NAME: "	+ headerData.getFatherName().toUpperCase()
					+ "\n  MOTHER'S NAME: "	+ headerData.getMotherName().toUpperCase() 
					+ "\n  SCHOOL: "		+ headerData.getEntityName().toUpperCase() 
					+ "\n                   "	+ (headerData.getAddress()==null?"":headerData.getAddress()+", ").toUpperCase()  
						+ (headerData.getCity()==null?"":headerData.getCity()+", ").toUpperCase()
					+ (headerData.getState()==null?"":headerData.getState()).toUpperCase(),
					FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, new Color(0, 0, 0)));

			PdfPTable clastable = new PdfPTable(new float[] { 1, 7 });
			clastable.setHorizontalAlignment(Element.ALIGN_LEFT);
			PdfPCell cells = new PdfPCell();

			Font cellFont1 = FontFactory.getFont(FontFactory.HELVETICA, 10,Font.NORMAL, new Color(0, 0, 0));
			Font cellFont2 = FontFactory.getFont(FontFactory.HELVETICA, 10,Font.BOLD, new Color(0, 0, 0));
			addCell(cells, cellFont1, clastable, "CLASS:");
			addCell(cells, cellFont2, clastable, headerData.getProgramName().toUpperCase());
			addCell(cells, cellFont1, clastable, "BRANCH:");
			addCell(cells, cellFont2, clastable, headerData.getBranchName().toUpperCase());

			try {				
				Image logoImage = Image.getInstance(java.awt.Toolkit.getDefaultToolkit().createImage(request.getSession().getServletContext().getRealPath("/")
										+ "images/deiLogo.jpg"), null);
				logoImage.scaleAbsolute(50, 50);
				logoImage.setAbsolutePosition(38f, 765f);
				document.add(logoImage);
				String photoPath = request.getSession().getServletContext().getRealPath("/")+ headerData.getPhotoPath();
                System.out.println(photoPath+"rohit photo path");
				Image studentPhoto = Image.getInstance(java.awt.Toolkit.getDefaultToolkit().createImage(photoPath), null);
				studentPhoto.scaleAbsolute(100, 100);
				studentPhoto.setAbsolutePosition(450f, 597f);
				document.add(studentPhoto);
			} catch (Exception e) {
				logObj.error(e.getMessage()+"exception in photo path");
			}
			Paragraph sessionInfo = new Paragraph("SESSION: "+ universitySession, FontFactory.getFont(
					FontFactory.HELVETICA, 10, Font.BOLD, new Color(0, 0, 0)));
			Chunk dottedLine = new Chunk(
					"-------------------------------------------------------------------------------------------------------------------------------------------------------------",
					FontFactory.getFont(FontFactory.HELVETICA, 10, Font.ITALIC,new Color(0, 0, 0)));

			Font cellFont = new Font(Font.HELVETICA, 8);
			PdfPCell cel = new PdfPCell(new Phrase("", cellFont));
			PdfPTable headerTable = new PdfPTable(new float[] { 2, 7, 3, 1 });
			headerTable.setWidthPercentage(100f);
			headerTable.setHorizontalAlignment(Element.ALIGN_CENTER);

			addCell(cel, cellFont, headerTable, "COURSE \nNUMBER");
			addCell(cel, cellFont, headerTable, "COURSE TITLE");
			addCell(cel, cellFont, headerTable,"GRADE POINT \n AVERAGE\n(Out of 10)");
			addCell(cel, cellFont, headerTable, "CREDITS");

			PdfPTable dataTable = new PdfPTable(new float[] { 2, 7, 3, 1 });
			dataTable.setWidthPercentage(100f);
			dataTable.setHorizontalAlignment(Element.ALIGN_CENTER);
			List<String> tempGroupList = new ArrayList<String>();
			Double fgp = 0.0;
			Double cr = 0.0;
			for (int g = 0; g < markSheetData.size() - 1; g++) {
				int flag1 = 0;
				int flag2 = 0;
				String cgroup = markSheetData.get(g).getCourseGroup();
				String cname = markSheetData.get(g).getCourseName();
				if (cgroup.endsWith("#")) {
					continue;
				}
				for (int h = 0; h < markSheetData.size() - 1; h++) {
					String cgroup1 = markSheetData.get(h).getCourseGroup();
					String cname1 = markSheetData.get(h).getCourseName();
					if (cgroup.equals(cgroup1)) {
						if (flag1 == 0) {
							flag1 = 1;
						} else {
							if (cname.equals(cname1)) {
								flag2 = 0;
							} else {
								flag2 = 1;
								String oldString = markSheetData.get(g).getCourseGroup();
								for (int ss = 0; ss < markSheetData.size() - 1; ss++) {
									if (markSheetData.get(ss).getCourseGroup().equals(oldString)) {
										String newString = markSheetData.get(ss).getCourseGroup().concat("#");
										markSheetData.get(ss).setCourseGroup(newString);
									}
								}
								break;
							}
						}
					}
				}
			}
			for (int c = 0; c < markSheetData.size(); c++) {
				String courseGroup = markSheetData.get(c).getCourseGroup();
				if (tempGroupList.indexOf(courseGroup) < 0) {
					if (c > 0) {						
						Double gpa = fgp / cr;
						String ssd = String.valueOf(String.format("%.2f", gpa));						
						addCell(cel, cellFont, dataTable, ssd);												
						addCell(cel, cellFont, dataTable, String.valueOf(String.format("%.2f",cr)));						
						fgp = 0.0;
						cr = 0.0;
					}
					if (c < markSheetData.size() - 1) {
						tempGroupList.add(courseGroup);
						addCell(cel, cellFont, dataTable, markSheetData.get(c).getCourseCode().substring(0, 3));
						Boolean groupFlag = markSheetData.get(c).getCourseGroup().endsWith("#");
						if (groupFlag) {							
							addCell(cel, cellFont, dataTable,markSheetData.get(c).getCourseGroupName());
						} else {
							addCell(cel, cellFont, dataTable, markSheetData.get(c).getCourseName());
						}
						fgp = fgp+ Double.parseDouble(markSheetData.get(c).getGradePoint())* Double.parseDouble(markSheetData.get(c).getCredits());
						cr = cr+ Double.parseDouble(markSheetData.get(c).getCredits());
					}
				} else {
					fgp = fgp+ Double.parseDouble(markSheetData.get(c).getGradePoint())
							* Double.parseDouble(markSheetData.get(c).getCredits());
					cr = cr+ Double.parseDouble(markSheetData.get(c).getCredits());
				}
			}
			Paragraph cgpaInfo = new Paragraph("\n CUMULATIVE GRADE POINT AVERAGE: "
								+ new Phrase(headerData.getCgpa().substring(0,(headerData.getCgpa().indexOf('.')) + 3),
									FontFactory.getFont(FontFactory.HELVETICA,8, Font.BOLD, new Color(0, 0, 0)))
									.getContent(), FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, new Color(0,0, 0)));
			Paragraph divisionInfo = new Paragraph("\n DIVISION: "+ headerData.getDivision().toUpperCase(), FontFactory
								.getFont(FontFactory.HELVETICA, 8, Font.BOLD, new Color(0, 0, 0)));
			Paragraph remarksInfo = new Paragraph("\nREMARKS: NIL", FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, new Color(0, 0, 0)));
			studentRollInfo.setAlignment(Element.ALIGN_RIGHT);
			studentInfo.setAlignment(Element.ALIGN_LEFT);
			sessionInfo.setAlignment(Element.ALIGN_CENTER);
			cgpaInfo.setAlignment(Element.ALIGN_CENTER);
			divisionInfo.setAlignment(Element.ALIGN_CENTER);
			remarksInfo.setAlignment(Element.ALIGN_LEFT);			
			document.add(studentRollInfo);
			document.add(studentInfo);
			document.add(clastable);
			document.add(sessionInfo);
			document.add(dottedLine);
			document.add(headerTable);
			document.add(dottedLine);
			document.add(dataTable);
			document.add(dottedLine);
			document.add(cgpaInfo);
			document.add(divisionInfo);
			document.add(remarksInfo);
			document.close();
			reportGenerated = reportGenerated +1;
			result = "true";
			}catch(Exception e){
				e.printStackTrace();
				result = "false";
				ReportLogBean reportErrorBean = new ReportLogBean(session.getAttribute("universityId").toString(),request.getParameter("programId"),
						request.getParameter("branchId"),request.getParameter("specializationId"),request.getParameter("entityId"),
						request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
						request.getParameter("reportCode"),"",rollNo,
						request.getParameter("fromSession").substring(0, 4)+"-"+request.getParameter("toSession").substring(2,4));
				logResult = reportDao.insertReportErrorLog(reportErrorBean);						
				System.out.println("nupur : some exception in report generation");
				System.out.println("exception :"+e);
			}
		}
		}catch(Exception e){
			e.printStackTrace();
			result = "false";
			ReportLogBean reportErrorBean = new ReportLogBean(session.getAttribute("universityId").toString(),request.getParameter("programId"),
					request.getParameter("branchId"),request.getParameter("specializationId"),request.getParameter("entityId"),
					request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
					request.getParameter("reportCode"),"","",
					request.getParameter("fromSession").substring(0, 4)+"-"+request.getParameter("toSession").substring(2,4));
			logResult = reportDao.insertReportErrorLog(reportErrorBean);						
			System.out.println("nupur : some exception in report generation");
			System.out.println("exception :"+e);
		}
		if(reportGenerated>0){
			try{
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
			}catch(Exception e){
				System.out.println("inside inner exception ");
				e.printStackTrace();
			}
			result="true";
		}
		return result;
	}
/**
* This method add a cell to Table
* 
* @param c1, table cell
* @param cellFont, font used
* @param Table chartTable, table in which cell is added
* @param s, value in cell
*/
public static final void addCell(PdfPCell c1, Font cellFont,
			PdfPTable chartTable, String s) {
		try {
			c1 = new PdfPCell(new Phrase(s, cellFont));
		} catch (Exception e) {
			e.printStackTrace();
		}
		c1.setBorderWidth(0);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setVerticalAlignment(Element.ALIGN_TOP);
		chartTable.addCell(c1);
	}
}