package in.ac.dei.edrp.cms.controller.enrollment;

import in.ac.dei.edrp.cms.dao.enrollment.EnrollmentService;
import in.ac.dei.edrp.cms.dao.report.ReportDao;
import in.ac.dei.edrp.cms.domain.enrollment.EnrollmentInfo;
import in.ac.dei.edrp.cms.domain.report.ReportLogBean;
import in.ac.dei.edrp.cms.utility.ReportPath;
import in.ac.dei.edrp.cms.utility.ReportPathBean;

import java.awt.Color;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.rtf.RtfWriter2;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPrintPage;

public class EnrollmentReportController extends MultiActionController {
	int reportGenerated = 0;
	/** creating object of transferEnrollmentToPrestagingService interface */
	private EnrollmentService enrollmentService;

	/** defining setter method for object of interface */
	public void setEnrollmentService(EnrollmentService enrollmentService) {
		this.enrollmentService = enrollmentService;
	}
	private ReportDao reportDao;
	
	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}
	public ModelAndView getEnrollmentDataForReportGeneration(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession(true);
		EnrollmentInfo input = new EnrollmentInfo();

		input.setUniversityId(session.getAttribute("universityId").toString());
		input.setRegistrationNo("%");
		input.setEntityCode(request.getParameter("entityId"));
//		input.setEntity(request.getParameter("entity"));
		input.setEntity(request.getParameter("entityName"));
		input.setProgramCode(request.getParameter("programId"));
//		input.setProgram(request.getParameter("program"));
		input.setProgram(request.getParameter("programName"));
		input.setBranchCode(request.getParameter("branchId"));
//		input.setBranch(request.getParameter("branch"));
		input.setBranch(request.getParameter("branchName"));
		input.setSpecializationCode(request.getParameter("specializationId"));
//		input.setSpecialization(request.getParameter("specialization"));
		input.setSpecialization(request.getParameter("specializationName"));
		input.setSessionStartDate(request.getParameter("startDate"));
		input.setSessionEndDate(request.getParameter("endDate"));
		input.setSessionStartDate(request.getParameter("fromSession"));
		input.setSessionEndDate(request.getParameter("toSession"));
		input.setStatus("SUB");
		List<EnrollmentInfo> personalData = enrollmentService.getPersonalDetails(input);

		for (int i = 0; i < personalData.size(); i++) {
			List<EnrollmentInfo> addressData = enrollmentService.getContactDetails(personalData.get(i).getStudentId());
			for (int j = 0; j < addressData.size(); j++) {
				String address = "";
				String s = "";
				s = addressData.get(j).getAddress();
				if ((s != "") && (s != null)) {
					address = address + s;
				}
				s = addressData.get(j).getCity();
				if ((s != "") && (s != null)) {
					address = address + ", " + s;
				}
				s = addressData.get(j).getState();
				if ((s != "") && (s != null)) {
					address = address + ", " + s;
				}
				s = addressData.get(j).getPinCode();
				if ((s != "") && (s != null)) {
					address = address + " " + s;
				}
				if (addressData.get(j).getAddressKey().equalsIgnoreCase("PER")) {
					personalData.get(i).setAddress(address);
				} else if (addressData.get(j).getAddressKey()
						.equalsIgnoreCase("COR")) {
					personalData.get(i).setCorAddress(address);
				}
			}
		}
		personalData.add(input);
		String filePaths = generateEnrollmentReport(personalData,request,request.getSession(true));
		System.out.println(filePaths);
		return new ModelAndView("activitymaster/SubmitSuccesful", "message", filePaths);
//		return new ModelAndView("enrollment/info", "info", filePaths);
	}

	public String generateEnrollmentReport(List<EnrollmentInfo> personalData,HttpServletRequest request,HttpSession session) throws Exception {
		String path = "";
		EnrollmentInfo headerData = personalData.get(personalData.size() - 1);
		personalData.remove(personalData.size() - 1);
		System.out.println("in pdf generator size=" + personalData.size());
		String seperator = System.getProperty("file.separator");
		String logResult="";
		String reportResult="";
		try{
		//making the directory for storing the file
		//*********nupur code *******************
		ReportPathBean reportPathBean = new ReportPathBean(session.getAttribute("universityId").toString(),request.getParameter("entityId"),
				request.getParameter("programId"),request.getParameter("branchId"),request.getParameter("specializationId"),
				request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
				request.getParameter("enrollmentNumber"),request.getParameter("rollNumber"),request.getParameter("reportCode"),
				request.getParameter("reportType"),request.getParameter("fromSession"),request.getParameter("toSession"),request.getParameter("semesterType"));
				
		String reportPath = ReportPath.getPath(reportPathBean);				
		System.out.println("here in controller, after path generation "+reportPath);
		String filePath = this.getServletContext().getRealPath("/");
		filePath=filePath+reportPath;
		File file = new File(filePath);
		file.mkdirs();
		//*****************************************************		
//		String filePath = this.getServletContext().getRealPath("/")
//				+ "EnrollmentReport";
//		(new File(filePath)).mkdir();

		String universityName = session.getAttribute("universityName") + "";
		String universitySession = session.getAttribute("startDate").toString()
				.substring(0, 4)
				+ "-"
				+ session.getAttribute("endDate").toString().substring(0, 4);

		String reportSession = headerData.getSessionStartDate().substring(0, 4)
				+ "-" + headerData.getSessionEndDate().substring(2, 4);
		Document document = new Document(PageSize.A4.rotate());
		/*path = seperator+"EnrollmentReport"+seperator + "Enrollemnt_Personal_And_Contact_Report_"
				+ headerData.getEntity() + "_" + headerData.getProgram() + "_"
				+ headerData.getBranch() + "_" + headerData.getSpecialization()
				+ headerData.getSessionStartDate() + "_"
				+ headerData.getSessionEndDate() + ".doc";*/
		/*RtfWriter2.getInstance(document, new FileOutputStream(filePath + seperator + "Enrollemnt_Personal_And_Contact_Report_"
				+ headerData.getEntity() + "_" + headerData.getProgram() + "_"
				+ headerData.getBranch() + "_" + headerData.getSpecialization()
				+ headerData.getSessionStartDate() + "_"
				+ headerData.getSessionEndDate() + ".doc"));*/
		RtfWriter2.getInstance(document, new FileOutputStream(filePath + seperator + request.getParameter("reportDescription")+".doc"));
System.out.println("after rtfwriter creation");
		Phrase headerPhrase = new Phrase(universityName.toUpperCase() + "\n"
				+ universitySession + "\n\nENROLLMENT FORM REPORT("
				+ reportSession + ")\n\nENTITY:- " + headerData.getEntity()
				+ "     PROGRAM:- " + headerData.getProgram()
				+ "     BRANCH:- " + headerData.getBranch()
				+ "     SPECIALIZATION:- " + headerData.getSpecialization()
				+ "\n", FontFactory.getFont(FontFactory.HELVETICA, 10,
				Font.BOLD, new Color(0, 0, 0)));

		Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 10,
				Font.BOLD, new Color(0, 0, 0));
		Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 8,
				Font.NORMAL, new Color(0, 0, 0));
		Font hindiFont = new Font(BaseFont.createFont(this.getServletContext()
				.getRealPath("/") + seperator + "fonts/ARIALUNI.TTF",
				BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 8);

		Table headerTable = new Table(13);
		headerTable.setWidth(100f);
		headerTable.setAlignment(Element.ALIGN_CENTER);

		addCell(headerFont, headerTable, "USERNAME");
		addCell(headerFont, headerTable, "STUDENT'S NAME");
		addCell(headerFont, headerTable, "FATHER'S NAME");
		addCell(headerFont, headerTable, "MOTHER'S NAME");
		addCell(headerFont, headerTable, "GUARDIAN NAME");
		addCell(headerFont, headerTable, "DATE OF BIRTH");
		addCell(headerFont, headerTable, "GENDER");
		addCell(headerFont, headerTable, "CATEGORY");
		addCell(headerFont, headerTable, "NATIONALITY");
		addCell(headerFont, headerTable, "RELIGION");
		addCell(headerFont, headerTable, "EMAIL ID");
		addCell(headerFont, headerTable, "PERMANENT ADDRESS");
		addCell(headerFont, headerTable, "CORRESPONDENCE ADDRESS");

		Chunk line = new Chunk(
				"__________________________________________________________________________________________________________________________________________");

		headerPhrase.add(new Phrase("\n"));
		headerPhrase.add(headerTable);
		headerPhrase.add(line);

		HeaderFooter header = new HeaderFooter(headerPhrase, false);
		HeaderFooter footer = new HeaderFooter(new Phrase("PAGE NUMBER:- "),
				true);

		header.setAlignment(Element.ALIGN_CENTER);
		header.setBorderWidth(0);
		footer.setAlignment(Element.ALIGN_RIGHT);
		footer.setBorderWidth(0);

		document.setHeader(header);
		document.setFooter(footer);
		document.open();
		for (int i = 0; i < personalData.size(); i++) {
			EnrollmentInfo outputRow = personalData.get(i);
			Table dataTable = new Table(13);
			dataTable.setCellsFitPage(true);
			dataTable.setTableFitsPage(true);
			dataTable.setWidth(100f);
			dataTable.setAlignment(Element.ALIGN_CENTER);

			addCell(dataFont, dataTable, outputRow.getRegistrationNo());

			addCell(hindiFont,
					dataTable,
					outputRow.getStudentFirstName()
							+ " "
							+ outputRow.getStudentMiddleName()
							+ " "
							+ outputRow.getStudentLastName()
							+ "\n"
							+ URLDecoder.decode(outputRow.getHindiName()==null?"":outputRow.getHindiName(),
									"utf-8"));
			addCell(hindiFont,
					dataTable,
					outputRow.getFatherFirstName()
							+ " "
							+ outputRow.getFatherMiddleName()
							+ " "
							+ outputRow.getFatherLastName()
							+ "\n"
							+ URLDecoder.decode(outputRow.getFatherHindiName()==null?"":outputRow.getFatherHindiName(),
									"utf-8"));
			addCell(hindiFont,
					dataTable,
					outputRow.getMotherFirstName()
							+ " "
							+ outputRow.getMotherMiddleName()
							+ " "
							+ outputRow.getMotherLastName()
							+ "\n"
							+ URLDecoder.decode(outputRow.getMotherHindiName()==null?"":outputRow.getMotherHindiName(),
									"utf-8"));

			addCell(dataFont, dataTable, outputRow.getGuardianName());
			addCell(dataFont, dataTable, outputRow.getDob());
			addCell(dataFont, dataTable, outputRow.getGender());
			addCell(dataFont, dataTable, outputRow.getCategoryCode());
			addCell(dataFont, dataTable, outputRow.getNationality());
			addCell(dataFont, dataTable, outputRow.getReligion());
			addCell(dataFont, dataTable, outputRow.getPrimaryMail());
			addCell(dataFont, dataTable, outputRow.getAddress());
			addCell(dataFont, dataTable, outputRow.getCorAddress());

			document.add(dataTable);
		}
		document.close();
		System.out.println("doc cerated successfully");
		/*path = path + "|" +seperator+"EnrollmentReport"+ seperator + "Enrollemnt_Academic_Report_"
				+ headerData.getEntity() + "_" + headerData.getProgram() + "_"
				+ headerData.getBranch() + "_" + headerData.getSpecialization()
				+ headerData.getSessionStartDate() + "_"
				+ headerData.getSessionEndDate() + ".pdf";*/
		Document doc = new Document(PageSize.A4.rotate());
		
		PdfWriter.getInstance(doc,new FileOutputStream(filePath + seperator + request.getParameter("reportDescription")+".pdf"));
		/*PdfWriter.getInstance(
				doc,
				new FileOutputStream(filePath + seperator
						+ "Enrollemnt_Academic_Report_"
						+ headerData.getEntity() + "_"
						+ headerData.getProgram() + "_"
						+ headerData.getBranch() + "_"
						+ headerData.getSpecialization()
						+ headerData.getSessionStartDate() + "_"
						+ headerData.getSessionEndDate() + ".pdf"));*/

		headerPhrase = new Phrase(universityName.toUpperCase() + "\n"
				+ universitySession + "\n\nENROLLMENT FORM REPORT("
				+ reportSession + ")\n\nENTITY:- " + headerData.getEntity()
				+ "     PROGRAM:- " + headerData.getProgram()
				+ "     BRANCH:- " + headerData.getBranch()
				+ "     SPECIALIZATION:- " + headerData.getSpecialization()
				+ "\n", FontFactory.getFont(FontFactory.HELVETICA, 10,
				Font.BOLD, new Color(0, 0, 0)));

		PdfPTable headTable = new PdfPTable(new float[] { 3, 4.5f, 3, 1.5f, 5,
				5, 3, 3 });
		headTable.setWidthPercentage(100f);
		headTable.setHorizontalAlignment(Element.ALIGN_CENTER);
		headTable.setSplitRows(false);
		addCell(headerFont, headTable, "USERNAME");
		addCell(headerFont, headTable, "STUDENT'S NAME");
		addCell(headerFont, headTable, "EXAMINATION");
		addCell(headerFont, headTable, "YEAR");
		addCell(headerFont, headTable, "SCHOOL/COLLEGE");
		addCell(headerFont, headTable, "BOARD/UNIVERSITY");
		addCell(headerFont, headTable, "MARKS OBTAINED");
		addCell(headerFont, headTable, "TOTAL MARKS");

		headerPhrase.add(headTable);
		headerPhrase.add(line);

		header = new HeaderFooter(headerPhrase, false);

		header.setAlignment(Element.ALIGN_CENTER);
		header.setBorderWidth(0);
		footer.setAlignment(Element.ALIGN_RIGHT);
		footer.setBorderWidth(0);

		doc.setHeader(header);
		doc.setFooter(footer);
		doc.open();
		System.out.println("size for pdf generation "+ personalData.size());
		for (int j = 0; j < personalData.size(); j++) {
			PdfPTable dataTable = new PdfPTable(new float[] { 3, 4.5f, 3, 1.5f,
					5, 5, 3, 3 });
			dataTable.setWidthPercentage(100f);
			dataTable.setHorizontalAlignment(Element.ALIGN_CENTER);

			List<EnrollmentInfo> academicData = enrollmentService
					.getAcademicDetails(personalData.get(j).getRegistrationNo());

			addCell(headerFont, dataTable, personalData.get(j)
					.getRegistrationNo());
			addCell(headerFont, dataTable, personalData.get(j)
					.getStudentFirstName()
					+ " "
					+ personalData.get(j).getStudentMiddleName()
					+ " "
					+ personalData.get(j).getStudentLastName());

			System.out.println("academic data size "+academicData.size());
			for (int k = 0; k < academicData.size(); k++) {
				if (k > 0) {
					addCell(dataFont, dataTable, "");
					addCell(dataFont, dataTable, "");
				}
				addCell(dataFont, dataTable, academicData.get(k).getExam());
				addCell(dataFont, dataTable, academicData.get(k).getYear());
				addCell(dataFont, dataTable, academicData.get(k).getCollege());
				addCell(dataFont, dataTable, academicData.get(k).getBoard());
				addCell(dataFont, dataTable, academicData.get(k)
						.getMarksObtained() + "");
				addCell(dataFont, dataTable, academicData.get(k)
						.getTotalMarks() + "");
			}
			doc.add(dataTable);
		}
		doc.close();
		//****************************NUpur*****************************		
		System.out.println("nupur : report created successfully");
		reportResult="true";		
		path = "true";	
}catch(Exception e){
	path = "false";
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
//***************************************************************

		return path;
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

	public static final void addCell(Font cellFont, Table table, String value) {
		try {
			Cell c1 = new Cell(new Phrase(value, cellFont));
			c1.setBorder(0);
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setVerticalAlignment(Element.ALIGN_TOP);
			table.addCell(c1);
		} catch (BadElementException e) {
			e.printStackTrace();
		}
	}
}