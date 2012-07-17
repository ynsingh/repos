package in.ac.dei.edrp.cms.controller.enrollment;

import in.ac.dei.edrp.cms.dao.enrollment.EnrollmentService;
import in.ac.dei.edrp.cms.domain.enrollment.EnrollmentInfo;

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
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPrintPage;

public class EnrollmentReportGeneration extends AbstractPdfView {
	/** creating object of transferEnrollmentToPrestagingService interface */
	private EnrollmentService enrollmentService;

	/** defining setter method for object of interface */
	public void setEnrollmentService(EnrollmentService enrollmentService) {
		this.enrollmentService = enrollmentService;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void buildPdfDocument(Map map, Document document,
			PdfWriter pdfWriter, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.setProperty("file.encoding", "utf-8");

		List<EnrollmentInfo> personalData = (List<EnrollmentInfo>) map
				.get("personalData");
		EnrollmentInfo headerData = personalData.get(personalData.size() - 1);
		personalData.remove(personalData.size() - 1);
		System.out.println("in pdf generator size=" + personalData.size());
		String seperator = System.getProperty("file.separator");
		String filePath = this.getServletContext().getRealPath("/")
				+ "EnrollmentReport";
		(new File(filePath)).mkdir();

		HttpSession session = request.getSession(true);
		String universityName = session.getAttribute("universityName") + "";
		String universitySession = session.getAttribute("startDate").toString()
				.substring(0, 4)
				+ "-"
				+ session.getAttribute("endDate").toString().substring(0, 4);

		String reportSession = headerData.getSessionStartDate().substring(0, 4)
				+ "-" + headerData.getSessionEndDate().substring(2, 4);
		document = new Document(PageSize.A4.rotate());
		pdfWriter = PdfWriter.getInstance(
				document,
				new FileOutputStream(filePath + seperator
						+ "Enrollemnt_Personal_And_Contact_Report_"
						+ headerData.getEntity() + "_"
						+ headerData.getProgram() + "_"
						+ headerData.getBranch() + "_"
						+ headerData.getSpecialization()
						+ headerData.getSessionStartDate() + "_"
						+ headerData.getSessionEndDate() + ".pdf"));

		Phrase headerPhrase = new Phrase(universityName.toUpperCase() + "\n"
				+ universitySession + "\n\nENROLLMENT FORM REPORT("
				+ reportSession + ")\n\nENTITY:- " + headerData.getEntity()
				+ "                              PROGRAM:- "
				+ headerData.getProgram()
				+ "                              BRANCH:- "
				+ headerData.getBranch()
				+ "                              SPECIALIZATION:- "
				+ headerData.getSpecialization() + "\n", FontFactory.getFont(
				FontFactory.HELVETICA, 10, Font.BOLD, new Color(0, 0, 0)));

		Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 8,
				Font.BOLD, new Color(0, 0, 0));
		Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 8,
				Font.NORMAL, new Color(0, 0, 0));
		Font hindiFont = new Font(BaseFont.createFont(this.getServletContext()
				.getRealPath("/") + seperator + "fonts/ARIALUNI.TTF",
				BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 8);

		PdfPTable headerTable = new PdfPTable(new float[] { 2, 2, 2, 2, 2, 2,
				2, 2, 2.3F, 2, 2, 4, 4 });
		headerTable.setWidthPercentage(100f);
		headerTable.setHorizontalAlignment(Element.ALIGN_CENTER);

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
			PdfPTable dataTable = new PdfPTable(new float[] { 2, 2, 2, 2, 2, 2,
					2, 2, 2.3F, 2, 2, 4, 4 });
			dataTable.setWidthPercentage(100f);
			dataTable.setHorizontalAlignment(Element.ALIGN_CENTER);

			addCell(dataFont, dataTable, outputRow.getRegistrationNo());

			addCell(hindiFont,
					dataTable,
					outputRow.getStudentFirstName()
							+ " "
							+ outputRow.getStudentMiddleName()
							+ " "
							+ outputRow.getStudentLastName()
							+ "\n"
							+ URLDecoder.decode(outputRow.getHindiName(),
									"utf-8"));
			addCell(hindiFont,
					dataTable,
					outputRow.getFatherFirstName()
							+ " "
							+ outputRow.getFatherMiddleName()
							+ " "
							+ outputRow.getFatherLastName()
							+ "\n"
							+ URLDecoder.decode(outputRow.getFatherHindiName(),
									"utf-8"));
			addCell(hindiFont,
					dataTable,
					outputRow.getMotherFirstName()
							+ " "
							+ outputRow.getMotherMiddleName()
							+ " "
							+ outputRow.getMotherLastName()
							+ "\n"
							+ URLDecoder.decode(outputRow.getMotherHindiName(),
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

		document = new Document(PageSize.A4.rotate());
		pdfWriter = PdfWriter.getInstance(
				document,
				new FileOutputStream(filePath + seperator
						+ "Enrollemnt_Academic_Report_"
						+ headerData.getEntity() + "_"
						+ headerData.getProgram() + "_"
						+ headerData.getBranch() + "_"
						+ headerData.getSpecialization()
						+ headerData.getSessionStartDate() + "_"
						+ headerData.getSessionEndDate() + ".pdf"));

		headerPhrase = new Phrase(universityName.toUpperCase() + "\n"
				+ universitySession + "\n\nENROLLMENT FORM REPORT("
				+ reportSession + ")\n\nENTITY:- " + headerData.getEntity()
				+ "                              PROGRAM:- "
				+ headerData.getProgram()
				+ "                              BRANCH:- "
				+ headerData.getBranch()
				+ "                              SPECIALIZATION:- "
				+ headerData.getSpecialization() + "\n", FontFactory.getFont(
				FontFactory.HELVETICA, 10, Font.BOLD, new Color(0, 0, 0)));

		headerTable = new PdfPTable(new float[] { 2, 5, 3, 2, 5, 5, 3, 3 });
		headerTable.setWidthPercentage(100f);
		headerTable.setHorizontalAlignment(Element.ALIGN_CENTER);

		addCell(headerFont, headerTable, "USERNAME");
		addCell(headerFont, headerTable, "STUDENT'S NAME");
		addCell(headerFont, headerTable, "EXAMINATION");
		addCell(headerFont, headerTable, "YEAR");
		addCell(headerFont, headerTable, "SCHOOL/COLLEGE");
		addCell(headerFont, headerTable, "BOARD/UNIVERSITY");
		addCell(headerFont, headerTable, "MARKS OBTAINED");
		addCell(headerFont, headerTable, "TOTAL MARKS");

		headerPhrase.add(headerTable);
		headerPhrase.add(line);

		header = new HeaderFooter(headerPhrase, false);

		header.setAlignment(Element.ALIGN_CENTER);
		header.setBorderWidth(0);
		footer.setAlignment(Element.ALIGN_RIGHT);
		footer.setBorderWidth(0);

		document.setHeader(header);
		document.setFooter(footer);
		document.open();
		for (int j = 0; j < personalData.size(); j++) {
			PdfPTable dataTable = new PdfPTable(new float[] { 2, 5, 3, 2, 5, 5,
					3, 3 });
			dataTable.setWidthPercentage(100f);
			dataTable.setHorizontalAlignment(Element.ALIGN_CENTER);

			List<EnrollmentInfo> academicData = enrollmentService
					.getAcademicDetails(personalData.get(j).getRegistrationNo());

			addCell(dataFont, dataTable, personalData.get(j)
					.getRegistrationNo());
			addCell(dataFont, dataTable, personalData.get(j)
					.getStudentFirstName()
					+ " "
					+ personalData.get(j).getStudentMiddleName()
					+ " "
					+ personalData.get(j).getStudentLastName());

			addCell(dataFont, dataTable, "");
			addCell(dataFont, dataTable, "");
			addCell(dataFont, dataTable, "");
			addCell(dataFont, dataTable, "");
			addCell(dataFont, dataTable, "");
			addCell(dataFont, dataTable, "");

			for (int k = 0; k < academicData.size(); k++) {
				addCell(dataFont, dataTable, "");
				addCell(dataFont, dataTable, "");
				addCell(dataFont, dataTable, academicData.get(k).getExam());
				addCell(dataFont, dataTable, academicData.get(k).getYear());
				addCell(dataFont, dataTable, academicData.get(k).getCollege());
				addCell(dataFont, dataTable, academicData.get(k).getBoard());
				addCell(dataFont, dataTable, academicData.get(k)
						.getMarksObtained() + "");
				addCell(dataFont, dataTable, academicData.get(k)
						.getTotalMarks() + "");
			}
			document.add(dataTable);
		}
		document.close();

//		 File f = new File(filePath + seperator
//		 + "Enrollemnt_Academic_Report_"
//		 + headerData.getEntity() + "_"
//		 + headerData.getProgram() + "_"
//		 + headerData.getBranch() + "_"
//		 + headerData.getSpecialization()
//		 + headerData.getSessionStartDate() + "_"
//		 + headerData.getSessionEndDate() + ".pdf");
//		
//		 FileInputStream fis = new FileInputStream(f);
//		 byte[] pdfContent = new byte[fis.available()];
//		 fis.read(pdfContent,0, fis.available());
//		 ByteBuffer bb = ByteBuffer.wrap(pdfContent);
//		 PDFFile pdfFile = new PDFFile(bb); // Create PDF Print Page
//		 PDFPrintPage pages = new PDFPrintPage(pdfFile);
//		 PrinterJob pjob = PrinterJob.getPrinterJob();
//		 PageFormat pf = PrinterJob.getPrinterJob().defaultPage();
//		 pjob.setJobName(f.getName());
//		 Book book = new Book();
//		 book.append(pages, pf, pdfFile.getNumPages());
//		 pjob.setPageable(book);
//		 pjob.print();
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