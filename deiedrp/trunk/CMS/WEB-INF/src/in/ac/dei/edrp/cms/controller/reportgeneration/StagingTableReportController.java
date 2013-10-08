/**
 * @(#) StagingTableReportController.java
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
import in.ac.dei.edrp.cms.dao.reportgeneration.StagingTableReportDao;
import in.ac.dei.edrp.cms.domain.reportgeneration.StagingTableReportBean;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

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

import org.apache.poi.hssf.util.HSSFColor.LAVENDER;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * This controller is designed for setting & retrieving
 * the final semester result statistics (category wise)
 * @author Nupur Dixit
 * @date 26 Sep 2012
 * @version 1.0
 */
@Controller
public class StagingTableReportController extends MultiActionController{
	
	@Autowired
	@Qualifier("StagingTableReportImpl")
	private StagingTableReportDao stagingTableReportDao;
	
	/**
     * this method will get the current session
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ModelAndView getCurrentSession(HttpServletRequest request,HttpServletResponse response)throws Exception {
		HttpSession session=request.getSession(true);
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive","sessionInactive", true);
		}
		String universityId = (String)session.getAttribute("universityId");
		StagingTableReportBean stagingTableReportBean=new StagingTableReportBean();
		stagingTableReportBean.setUniversityId(universityId);
		List<StagingTableReportBean> sessionDates = stagingTableReportDao.getCurrentSession(stagingTableReportBean);
		return new ModelAndView("SubjectCategoryWiseStudentList/SessionDates","sessionDates", sessionDates);			
	}
	
	
	/**
     * Method to generate the PDF.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	@RequestMapping("/stagingTableReport/generateReport")
	public ModelAndView generateReport(HttpServletRequest request,HttpServletResponse response)throws Exception {
		System.out.println("inside trnscript controller");		
		HttpSession session=request.getSession(true);
		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive","sessionInactive", true);
		}
		String result = "false";
		String universityId = (String)session.getAttribute("universityId");
		StagingTableReportBean stagingTableReportBean=new StagingTableReportBean();
		stagingTableReportBean.setUniversityId(universityId);
		List<StagingTableReportBean> sessionDates = stagingTableReportDao.getCurrentSession(stagingTableReportBean);
		if(sessionDates.size()!=0){
			stagingTableReportBean.setSessionStartDate( sessionDates.get(0).getSessionStartDate());
			stagingTableReportBean.setSessionEndDate(sessionDates.get(0).getSessionEndDate());
		}			
		else{
			return new ModelAndView("ManualProcess/MyException","exception","Current session dates are not set in the Database");	
		}
		String entityId = request.getParameter("entityId");
		String programId = request.getParameter("programId");
		String programCode = request.getParameter("programCode");
		String branchId = request.getParameter("branchId");
		String specializationId = request.getParameter("specializationId");
		String semesterCode = request.getParameter("semesterCode");
		String processFlag = request.getParameter("processFlag");
		
		stagingTableReportBean.setEntityId(entityId);
		stagingTableReportBean.setProgramId(programId);
		stagingTableReportBean.setProgramCode(programCode);
		stagingTableReportBean.setBranchId(branchId);
		stagingTableReportBean.setSpecializationId(specializationId);
		stagingTableReportBean.setSemesterCode(semesterCode);
		stagingTableReportBean.setProcessFlag(processFlag);
		
		List <StagingTableReportBean> getStudentDetail = stagingTableReportDao.getStudentDetail(stagingTableReportBean);
		System.out.println("siiiiize is:"+ getStudentDetail.size());
		if(getStudentDetail.size()!=0)
			result = generatePDF(request,getStudentDetail,stagingTableReportBean);
		else
			return new ModelAndView("ManualProcess/MyException","exception","For the selected program/branch/specialization/semester, no students" +
					" are available in staging table");	
		String message ="";
		if(result=="true"){
//			return new ModelAndView("ManualProcess/MyException","exception","Report Generated Successfully");
			message = "true";
		}
		else{
			if(result=="false"){
				message = "There is some error in PDF Generation kindly view the error log for more information";
			}
			else{
				message=result;
			}			
		}
		return new ModelAndView("ManualProcess/MyException","exception",message);	
	}
		
	  public String buildExcelDocument(String fileName, List<StagingTableReportBean> studentDetail) {
          FileOutputStream fileOutputStream = null;
          String result ="false";
          try {
                      fileOutputStream = new FileOutputStream(fileName);
                      // Create Sheet.
                      HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
                      HSSFSheet sheet = hssfWorkbook.createSheet("Staging Table Data");
                      // Font setting for sheet.
                      HSSFFont font = hssfWorkbook.createFont();
                      font.setBoldweight((short) 700);
                      sheet.setDefaultColumnWidth((short) 30);
                      // Create Styles for sheet.
                      HSSFCellStyle headerStyle = hssfWorkbook.createCellStyle();
                      headerStyle.setFillForegroundColor(LAVENDER.index);
                      headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                      headerStyle.setFont(font);
                      HSSFCellStyle dataStyle = hssfWorkbook.createCellStyle();
                      dataStyle.setWrapText(true);
                      // Create Header Row
                      HSSFRow headerRow = sheet.createRow(0);
                      // Write row for header
                      HSSFCell headerCell1 = headerRow.createCell((short) 0);
                      headerCell1.setCellStyle(headerStyle);
                      headerCell1.setCellValue("Enrollment No.");
                      HSSFCell headerCell2 = headerRow.createCell((short) 1);
                      headerCell2.setCellStyle(headerStyle);
                      headerCell2.setCellValue("Reg.Roll No");
                      HSSFCell headerCell3 = headerRow.createCell((short) 2);
                      headerCell3.setCellStyle(headerStyle);
                      headerCell3.setCellValue("StudentName");
                      HSSFCell headerCell4 = headerRow.createCell((short) 3);
                      headerCell4.setCellStyle(headerStyle);
                      headerCell4.setCellValue("FatherName");
                      HSSFCell headerCell5 = headerRow.createCell((short) 4);
                      headerCell5.setCellStyle(headerStyle);
                      headerCell5.setCellValue("OldClassName");
                      HSSFCell headerCell6 = headerRow.createCell((short) 5);
                      headerCell6.setCellStyle(headerStyle);
                      headerCell6.setCellValue("NewClassName");
                      HSSFCell headerCell7 = headerRow.createCell((short) 6);
                      headerCell7.setCellStyle(headerStyle);
                      headerCell7.setCellValue("OldSemester");
                      HSSFCell headerCell8 = headerRow.createCell((short) 7);
                      headerCell8.setCellStyle(headerStyle);
                      headerCell8.setCellValue("NewSemester");
                      HSSFCell headerCell9 = headerRow.createCell((short) 8);
                      headerCell9.setCellStyle(headerStyle);
                      headerCell9.setCellValue("ProcessFlag");
                      
                      //write data in rows
                      for (int i = 0; i < studentDetail.size(); i++) {
          				StagingTableReportBean rowData = studentDetail.get(i);
          			// Create First Data Row
                        HSSFRow dataRow = sheet.createRow(i+1);                      
                    	 // Write data in data row
                        HSSFCell cell1 = dataRow.createCell((short) 0);
                        cell1.setCellStyle(dataStyle);
                        cell1.setCellValue(new HSSFRichTextString(rowData.getEnrollmentNumber()));   
                        HSSFCell cell2 = dataRow.createCell((short) 1);
                        cell2.setCellStyle(dataStyle);
                        cell2.setCellValue(new HSSFRichTextString(rowData.getNewRollNumber()));
                        HSSFCell cell3 = dataRow.createCell((short) 2);
                        cell3.setCellStyle(dataStyle);
                        cell3.setCellValue(new HSSFRichTextString(rowData.getStudentName()));
                        HSSFCell cell4 = dataRow.createCell((short) 3);
                        cell4.setCellStyle(dataStyle);
                        cell4.setCellValue(new HSSFRichTextString(rowData.getFatherName()));
                        HSSFCell cell5 = dataRow.createCell((short) 4);
                        cell5.setCellStyle(dataStyle);
                        cell5.setCellValue(new HSSFRichTextString(rowData.getOldClassName()));
                        HSSFCell cell6 = dataRow.createCell((short) 5);
                        cell6.setCellStyle(dataStyle);
                        cell6.setCellValue(new HSSFRichTextString(rowData.getNewClassName()));
                        HSSFCell cell7 = dataRow.createCell((short) 6);
                        cell7.setCellStyle(dataStyle);
                        cell7.setCellValue(new HSSFRichTextString(rowData.getOldSemester()));
                        HSSFCell cell8 = dataRow.createCell((short) 7);
                        cell8.setCellStyle(dataStyle);
                        cell8.setCellValue(new HSSFRichTextString(rowData.getNewSemester()));
                        HSSFCell cell9 = dataRow.createCell((short) 8);
                        cell9.setCellStyle(dataStyle);
                        cell9.setCellValue(new HSSFRichTextString(rowData.getProcessFlag()));                                                                	
          			}                                        
                      // write in excel
                      hssfWorkbook.write(fileOutputStream);
                      result="true";
          } catch (Exception e) {
                      e.printStackTrace();
                      result="false";
          } finally {
                      /*
                       * Close File Output Stream
                       */
                      try {
                                  if (fileOutputStream != null) {
                                              fileOutputStream.close();
                                  }
                      } catch (IOException ex) {
                                  ex.printStackTrace();
                      }
          }
          return result;
	  }//method close  
          
	public String generatePDF(HttpServletRequest request,List<StagingTableReportBean> studentDetail, StagingTableReportBean beanPassed){
		String result = "false";
		try{
			String entityName = request.getParameter("entityName");
			String programName = request.getParameter("programName");
			String branchName = request.getParameter("branchName");
			String specialization = request.getParameter("specialization");	
			String semesterCode = request.getParameter("semesterCode");
			String processFlagName = request.getParameter("processFlagName");
			String reportName = entityName+"_"+programName+"_"+branchName+"_"+specialization+"_"+semesterCode+"_"+processFlagName;
			HttpSession session=request.getSession(true);
			String universityName = session.getAttribute("universityName") + "";
			String universitySession = beanPassed.getSessionStartDate().substring(0, 4)+"-"+beanPassed.getSessionEndDate().substring(0, 4);
//			String universitySession = session.getAttribute("startDate").toString().substring(0, 4)+ "-"+ session.getAttribute("endDate").toString().substring(0, 4);
			String sep = System.getProperty("file.separator");
			String path = this.getServletContext().getRealPath("/");
			path=path+"REPORTS"+sep+"StagingTable";
			File file = new File(path);
			file.mkdirs();
			Document document = new Document(PageSize.A4.rotate());
			String pathName = path+sep+ reportName+".xls";
			try{
				PdfWriter.getInstance(document, new FileOutputStream(path+sep+ reportName+".pdf"));	
			}
			catch(FileNotFoundException e){
				String message = e.getMessage();
				System.out.println("message "+message);
				if(message.contains("The process cannot access the file because it is being used by another process")){
					System.out.println("inside true");
					result = "The process cannot access the pdf file because it is being used by another process. First close the already open file" +
							" and then try to generate";
					return result;
				}				
				else{
					System.out.println("inside false");
				}
			}
			
			Chunk line = new Chunk(
			"___________________________________________________________________________________________________________");
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			Phrase universityInfo = new Phrase(universityName.toUpperCase() + "\n"
					+ universitySession + "\n", FontFactory.getFont(
							FontFactory.HELVETICA, 9, Font.BOLD, new Color(0, 0, 0)));

			Phrase reportInfo = new Phrase("STUDENT LIST (" +processFlagName+" )",
					FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL, new Color(0, 0,0)));

			Font headerCellFont = FontFactory.getFont(FontFactory.HELVETICA, 7,Font.BOLD, new Color(0, 0, 0));

			PdfPTable headerTable = new PdfPTable(new float[] { 2.0f, 2.0f,4.0f, 4.0f, 5.0f, 5.0f, 2.5f, 2.5f, 2.2f});
			headerTable.setWidthPercentage(100f);
			headerTable.setHorizontalAlignment(Element.ALIGN_CENTER);

			addCell(headerCellFont, headerTable, "Enroll.No.");
			addCell(headerCellFont, headerTable, "Reg.RollNo.");
			addCell(headerCellFont, headerTable, "StudentName");
			addCell(headerCellFont, headerTable, "FatherName");
			addCell(headerCellFont, headerTable, "OldClassName");
			addCell(headerCellFont, headerTable, "NewClassName");
			addCell(headerCellFont, headerTable, "OldSemester");
			addCell(headerCellFont, headerTable, "NewSemester");
			addCell(headerCellFont, headerTable, "ProcessFlag");
			Phrase headerPhrase = new Phrase();
			headerPhrase.add(universityInfo);
			headerPhrase.add(reportInfo);
			headerPhrase.add(headerTable);
			headerPhrase.add(line);
			HeaderFooter header = new HeaderFooter(headerPhrase, false);
			Font dataCellFont = FontFactory.getFont("UTF-8", 7, Font.NORMAL,new Color(0, 0, 0));
//			HeaderFooter footer = new HeaderFooter(new Phrase("Page:- "), true);
			HeaderFooter footer = new HeaderFooter(new Phrase("Page:-",dataCellFont), new Phrase("                                                              "+sdf.format(new Date()),dataCellFont));

			header.setAlignment(Element.ALIGN_CENTER);
			header.setBorderWidth(0);
			footer.setAlignment(Element.ALIGN_RIGHT);
			footer.setBorderWidth(0);
			
			PdfPTable dataTable = new PdfPTable(new float[] {2.0f, 2.0f,4.0f, 4.0f, 5.0f, 5.0f, 2.5f, 2.5f, 2.2f});
			dataTable.setWidthPercentage(100f);
			dataTable.setHorizontalAlignment(Element.ALIGN_CENTER);
			for (int i = 0; i < studentDetail.size(); i++) {
				StagingTableReportBean rowData = studentDetail.get(i);
				addCell(dataCellFont, dataTable, rowData.getEnrollmentNumber());
				addCell(dataCellFont, dataTable, rowData.getNewRollNumber());
				addCell(dataCellFont, dataTable, rowData.getStudentName());
				addCell(dataCellFont, dataTable, rowData.getFatherName());
				addCell(dataCellFont, dataTable, rowData.getOldClassName());
				addCell(dataCellFont, dataTable, rowData.getNewClassName());
				addCell(dataCellFont, dataTable, rowData.getOldSemester());
				addCell(dataCellFont, dataTable, rowData.getNewSemester());
				addCell(dataCellFont, dataTable, rowData.getProcessFlag());		
			}	
			document.setHeader(header);
			document.setFooter(footer);
			document.open();
			document.add(dataTable);
			document.close();
			System.out.println("first document closed success");			
			result = "true";
//			result = genDoc(pathName, studentDetail);
			String excelResult = buildExcelDocument(pathName,studentDetail);			
			if(result.equalsIgnoreCase("true")){
				if(excelResult.equalsIgnoreCase("true")){
					return "true";
				}
				else{
					return "PDF generated successfully but there is some error in Excel generation";
				}
			}
			else{
				if(excelResult.equalsIgnoreCase("true")){
					return "Excel file generated successfully but there is some error in PDF generation";
				}
				else{
					return "false";
				}
			}
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
	/**
	 * This method add a cell to Table
	 * @param cellFont, Font used
	 * @param table, PdfPTable in which cell is added
	 * @param value, Text string in cell
	 */
	public static final void addCell(Font cellFont, PdfPTable table,String value) {
		PdfPCell c1 = new PdfPCell(new Phrase(value, cellFont));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setVerticalAlignment(Element.ALIGN_TOP);
		table.addCell(c1);
	}
}
