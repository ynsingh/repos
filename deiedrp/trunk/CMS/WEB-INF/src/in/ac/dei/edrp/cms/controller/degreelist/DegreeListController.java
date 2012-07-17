/*
 * @(#) DegreeListController.java
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

package in.ac.dei.edrp.cms.controller.degreelist;

import in.ac.dei.edrp.cms.dao.degreelist.DegreeListConnect;
import in.ac.dei.edrp.cms.dao.report.ReportDao;
import in.ac.dei.edrp.cms.domain.degreelist.DegreeListInfoGetter;
import in.ac.dei.edrp.cms.domain.report.ReportLogBean;
import in.ac.dei.edrp.cms.utility.ReportPath;
import in.ac.dei.edrp.cms.utility.ReportPathBean;

import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.format.Orientation;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Header;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.rtf.RtfWriter2;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPrintPage;

/**
 * This controller is designed for setting & retrieving information about the
 * students passing in any particular session of a university
 * 
 * @author Ashish
 * @date 21 Aug 2011
 * @version 1.0
 */
@SuppressWarnings("unused")
public class DegreeListController extends MultiActionController {

	private DegreeListConnect degreeListConnect;

	/**
	 * The setter method of the interface associated with this controller
	 * 
	 * @param degreeListConnect
	 */
	public void setDegreeListConnect(DegreeListConnect degreeListConnect) {
		this.degreeListConnect = degreeListConnect;
	}
	
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
	
/*
 * method to be used to print reports automatically
 */
public ModelAndView printReports(HttpServletRequest request,
		HttpServletResponse response)throws Exception{
	
	HttpSession session  = request.getSession(true);	
	DegreeListInfoGetter infoGetter = new DegreeListInfoGetter();
	
//	infoGetter.setUniversityName(session.getAttribute("universityName").toString());
	infoGetter.setUniversityName("Dayalbagh educational institute");
	infoGetter.setPassedFromSession("2011");
	infoGetter.setPassedToSession("12");
	infoGetter.setProgramName("BA");
	infoGetter.setReportType("DegreeListReports");
	
	/*
	 * path of the directory
	 */
	String directory = getServletContext().getRealPath("/")+resourceBundle.getString("directory") +sep+infoGetter.getUniversityName()+ sep
	+infoGetter.getPassedFromSession()+"-"+infoGetter.getPassedToSession()+sep+infoGetter.getReportType()+sep+ infoGetter.getProgramName();
	
	File file = new File(directory);
	
	File[] files = file.listFiles();
	
	for(int i=0;i<files.length;i++){		
		if(files[i].isFile()){			
			System.out.println("file name "+files[i].getName());
			FileInputStream fis =	new FileInputStream(files[i]);
			byte[] pdfContent = new byte[fis.available()];
			fis.read(pdfContent,0, fis.available());
			ByteBuffer bb = ByteBuffer.wrap(pdfContent);
			PDFFile  pdfFile =
			new PDFFile(bb); // Create PDF Print Page

			PDFPrintPage pages =new PDFPrintPage(pdfFile);
			
			Paper paper = new Paper();
			paper.setImageableArea(0,0,paper.getWidth(),paper.getHeight());
			
			PageFormat pf = PrinterJob.getPrinterJob().defaultPage();
			pf.setPaper(paper);
//			pf.setOrientation(PageFormat.LANDSCAPE);
		
			PrinterJob pjob = PrinterJob.getPrinterJob();
			pjob.setJobName(files[i].getName());
			
			Book book =	new Book();
			book.append(pages, pf, pdfFile.getNumPages());
			if(pjob.printDialog()){
				pjob.setPageable(book);
				pjob.print();
			}
			
		}		
	}		
	
	return new ModelAndView();	
	
}

	/**
	 * Generate degree list report for different programs for different sessions
	 * of the university
	 * 
	 * @param request
	 * @param response
	 * @return model & view object
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked" })
	public ModelAndView generateDegreeList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DegreeListInfoGetter infoGetter = new DegreeListInfoGetter();
		HttpSession session = request.getSession(true);

		String userId = (String) session.getAttribute("userId");

		if (userId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}
		String logResult="";
		String reportResult="";
		String message="";
try{
		infoGetter.setEntityId(request.getParameter("entityId"));
		infoGetter.setProgramId(request.getParameter("programId"));
		infoGetter.setPassedFromSession(request.getParameter("fromSession"));
		infoGetter.setPassedToSession(request.getParameter("toSession"));
		infoGetter.setUniversityCode(session.getAttribute("universityId").toString());
		infoGetter.setProgramCode(request.getParameter("programCode"));
		infoGetter.setProgramPrintType(request.getParameter("printType"));
		infoGetter.setUniversityName(session.getAttribute("universityName").toString());
		infoGetter.setProgramName(request.getParameter("programName"));

		List<DegreeListInfoGetter> resultList = null;
		
		List<DegreeListInfoGetter> list = new ArrayList<DegreeListInfoGetter>();

		list = degreeListConnect.getProgramBranches(infoGetter);
		
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
		System.out.println("here in controller, after path generation "+reportPath);
		String path = this.getServletContext().getRealPath("/");
		path=path+reportPath;
		File file = new File(path);
		file.mkdirs();
		//*****************************************************
		
//		String directory = getServletContext().getRealPath("/")+resourceBundle.getString("directory") +sep+infoGetter.getUniversityCode()+ sep
//		+infoGetter.getPassedFromSession().substring(0,4)+
//		"-"+infoGetter.getPassedToSession().substring(2,4)+sep+resourceBundle.getString("degreereports")+sep+ infoGetter.getProgramId();
		message = reportPath;
//		String message = resourceBundle.getString("directory") +sep+infoGetter.getUniversityName()+ sep
//		+infoGetter.getPassedFromSession().substring(0,4)+
//		"-"+infoGetter.getPassedToSession().substring(2,4)+sep+resourceBundle.getString("degreereports")+sep+ infoGetter.getProgramName();

//		File file = new File(directory);
//		file.mkdirs();
		
		Document document = null;
		
		Table pdfPTable = null ;
		
		Font cellFont = new Font(Font.HELVETICA, 8);
		
		/*
		 * The method retrieves the type of result system used for the
		 * program(input)
		 */
		String resultSystem = degreeListConnect.getResultSystem(infoGetter);
		
		/*
		 * If the programCode is HS ie. the report to be generated is for HIgh
		 * School program.
		 */
		if (infoGetter.getProgramCode().equalsIgnoreCase("HS")) {

			Iterator<DegreeListInfoGetter> branchIterator = list.iterator();

			while (branchIterator.hasNext()) {
				DegreeListInfoGetter branchListInfoGetter = (DegreeListInfoGetter) branchIterator
						.next();

				infoGetter.setBranchId(branchListInfoGetter.getBranchId());
				infoGetter.setBranchName(branchListInfoGetter.getBranchName());

				resultList = degreeListConnect.printDegreeListforHS(infoGetter);

				document = new Document(PageSize.A4.rotate());
				
				RtfWriter2.getInstance(document, new FileOutputStream(path+sep+request.getParameter("reportDescription")+".doc"));				
				message = message +  sep + "DegreeList.doc";
//				RtfWriter2.getInstance(document, new FileOutputStream(
//						directory + sep + infoGetter.getProgramName() + "("
//								+ infoGetter.getBranchName() + ")" + ".doc"));
				
//				message = message +  sep + infoGetter.getProgramName() + "("
//				+ infoGetter.getBranchName() + ")" + ".doc";

				HeaderFooter headerIN = new HeaderFooter(
						new Phrase(
								"                               "
										+ resourceBundle
												.getString("headerPart3")
										+ "\n"
										+ "                                                   "
										+ resourceBundle
												.getString("headerPart4")+" "
										+ infoGetter.getPassedFromSession()
												.substring(0, 4)
										+ "-"
										+ infoGetter.getPassedToSession()
												.substring(2, 4) + "-"
										+ infoGetter.getProgramName() + "("
										+ infoGetter.getBranchName() + ")"),
						false);

				HeaderFooter footerIN = new HeaderFooter(
						new Phrase(
								resourceBundle.getString("footerPart3")
										+ "                                                   "
										+ resourceBundle
												.getString("footerPart4")),
						false);

				headerIN.setBorder(0);
				footerIN.setBorder(0);
				
				headerIN.setAlignment(Element.ALIGN_CENTER);

				document.setHeader(headerIN);
				document.setFooter(footerIN);

//				document.setPageSize(PageSize.A4.rotate());

				document.open();

				document.add(Chunk.NEWLINE);

				/*
				 * Format the report if theory & practicals are to be considered
				 * collectively
				 */
				if (infoGetter.getProgramPrintType().equalsIgnoreCase("SAG")) {

					float[] colsWidth = { 0.4f, 0.8f, 2f, 2f, 1.5f, 0.8f, 0.8f,
							6f };
					Table headerTable = new Table(8);

					headerTable.addCell(new Phrase("S.N.", cellFont));
					headerTable.addCell(new Phrase("Roll Number", cellFont));
					headerTable.addCell(new Phrase("Name", cellFont));
					headerTable.addCell(new Phrase("Father Name", cellFont));
					headerTable.addCell(new Phrase("Date of Birth", cellFont));
					headerTable.addCell(new Phrase("Division", cellFont));

					if (resultSystem == "MK") {
						headerTable.addCell(new Phrase("CP", cellFont));
					} else {
						headerTable.addCell(new Phrase("CGPA", cellFont));
					}

					headerTable.addCell(new Phrase("Subjects", cellFont));

					String i = "1";

					Iterator iterator = resultList.iterator();

					while (iterator.hasNext()) {
						DegreeListInfoGetter object = (DegreeListInfoGetter) iterator
								.next();

						headerTable.addCell(new Phrase(i, cellFont));
						headerTable.addCell(new Phrase(object.getRollNumber(),
								cellFont));
						headerTable.addCell(new Phrase(object.getStudentName()+"\n"+
								URLDecoder.decode(object.getStudentNameInHindi(), "utf-8"),
								cellFont));
						headerTable.addCell(new Phrase(object.getFatherName()+"\n"+
								URLDecoder.decode(object.getFatherNameInHindi(),"utf-8"),
								cellFont));
						headerTable.addCell(new Phrase(object.getDob(),
								cellFont));
						headerTable.addCell(new Phrase(object
								.getDivisionInTheory(), cellFont));
						if (resultSystem == "MK") {
							headerTable.addCell(new Phrase(object
									.getCumPercentage(), cellFont));
						} else {
							headerTable.addCell(new Phrase(object.getCgpa(),
									cellFont));
						}

						headerTable.addCell(new Phrase(object.getCourseName(),
								cellFont));

						i = Integer.parseInt(i) + 1 + "";
					}
					document.add(headerTable);
					document.close();

				}

			}

		}

		/*
		 * If the programCode is IN ie the report to be generated is for
		 * Intermediate.
		 */
		else if (infoGetter.getProgramCode().equalsIgnoreCase("IC")) {

			Iterator<DegreeListInfoGetter> branchIterator = list.iterator();

			while (branchIterator.hasNext()) {
				DegreeListInfoGetter branchListInfoGetter = (DegreeListInfoGetter) branchIterator
						.next();

				infoGetter.setBranchId(branchListInfoGetter.getBranchId());
				infoGetter.setBranchName(branchListInfoGetter.getBranchName());

				resultList = degreeListConnect.printDegreeListforIN(infoGetter);

				document = new Document(PageSize.A4.rotate());
				RtfWriter2.getInstance(document, new FileOutputStream(path+sep+request.getParameter("reportDescription")+".doc"));				
				message = message +  sep + "DegreeList.doc";
								
//				RtfWriter2.getInstance(document, new FileOutputStream(
//						directory + sep + infoGetter.getProgramName() + "("
//								+ infoGetter.getBranchName() + ")" + ".doc"));
//				
//				message = message +  sep + infoGetter.getProgramName() + "("
//				+ infoGetter.getBranchName() + ")" + ".doc";

				HeaderFooter headerIN = new HeaderFooter(
						new Phrase(
								"                               "
										+ resourceBundle
												.getString("headerPart3")
										+ "\n"
										+ "                                                   "
										+ resourceBundle
												.getString("headerPart4")+" "
										+ infoGetter.getPassedFromSession()
												.substring(0, 4)
										+ "-"
										+ infoGetter.getPassedToSession()
												.substring(2, 4) + "-"
										+ infoGetter.getProgramName() + "("
										+ infoGetter.getBranchName() + ")"),
						false);

				HeaderFooter footerIN = new HeaderFooter(
						new Phrase(
								resourceBundle.getString("footerPart3")
										+ "                                                   "
										+ resourceBundle
												.getString("footerPart4")),
						false);
				
				headerIN.setAlignment(Element.ALIGN_CENTER);

				headerIN.setBorder(0);
				footerIN.setBorder(0);

				document.setHeader(headerIN);
				document.setFooter(footerIN);

				document.setPageSize(PageSize.A4.rotate());

				document.open();

				document.add(Chunk.NEWLINE);

				/*
				 * Format the report if theory & practicals are to be considered
				 * collectively
				 */
				if (infoGetter.getProgramPrintType().equalsIgnoreCase("SAG")) {

					float[] colsWidth = { 0.5f, 0.8f, 2f, 2f, 1f, 0.8f, 7.5f };
					Table headerTable = new Table(7);

					headerTable.addCell(new Phrase("S.N.", cellFont));
					headerTable.addCell(new Phrase("Roll Number", cellFont));
					headerTable.addCell(new Phrase("Name", cellFont));
					headerTable.addCell(new Phrase("Father Name", cellFont));
					headerTable.addCell(new Phrase("Division", cellFont));
					if (resultSystem == "MK") {
						headerTable.addCell(new Phrase("CP", cellFont));
					} else {
						headerTable.addCell(new Phrase("CGPA", cellFont));
					}
					headerTable.addCell(new Phrase("Subjects", cellFont));

					String i = "1";

					Iterator iterator = resultList.iterator();

					while (iterator.hasNext()) {
						DegreeListInfoGetter object = (DegreeListInfoGetter) iterator
								.next();

						headerTable.addCell(new Phrase(i, cellFont));
						headerTable.addCell(new Phrase(object.getRollNumber(),
								cellFont));
						headerTable.addCell(new Phrase(object.getStudentName()+"\n"+
								URLDecoder.decode(object.getStudentNameInHindi(), "utf-8"),
								cellFont));
						headerTable.addCell(new Phrase(object.getFatherName()+"\n"+
								URLDecoder.decode(object.getFatherNameInHindi(),"utf-8"),
								cellFont));
						headerTable.addCell(new Phrase(object
								.getDivisionInTheory(), cellFont));
						if (resultSystem == "MK") {
							headerTable.addCell(new Phrase(object
									.getCumPercentage(), cellFont));
						} else {
							headerTable.addCell(new Phrase(object.getCgpa(),
									cellFont));
						}
						headerTable.addCell(new Phrase(object.getCourseName(),
								cellFont));

						i = Integer.parseInt(i) + 1 + "";
					}
					document.add(headerTable);
					document.close();

				}

			}
		}

		/*
		 * For all other programs except above
		 */
		else {	
			
			Font cellFonts = new Font(Font.HELVETICA, 8);
			
			Iterator<DegreeListInfoGetter> branchIterator = list.iterator();

			document = new Document(PageSize.A4);		
			
			RtfWriter2.getInstance(document, new FileOutputStream(path+sep+request.getParameter("reportDescription")+".doc"));				
			message = message +  sep + "DegreeList.doc";
			
//			RtfWriter2.getInstance(document, new FileOutputStream(
//					directory+sep+infoGetter.getProgramName()+".doc"));
//			
//			message = message +sep+infoGetter.getProgramName()+".doc";

			while (branchIterator.hasNext()) {
				DegreeListInfoGetter branchListInfoGetter = (DegreeListInfoGetter) branchIterator
						.next();

				infoGetter.setBranchId(branchListInfoGetter.getBranchId());
				infoGetter.setBranchName(branchListInfoGetter.getBranchName());

				resultList = degreeListConnect
						.printDegreeListforOhterPrograms(infoGetter);

				HeaderFooter headerIN = new HeaderFooter(
						new Phrase(
								
										resourceBundle
												.getString("headerPart5")
										+ "                                                   "
										+ resourceBundle
												.getString("headerPart4")+" "
										+ infoGetter.getPassedFromSession()
												.substring(0, 4)
										+ "-"
										+ infoGetter.getPassedToSession()
												.substring(2, 4) + "-"
										+ infoGetter.getProgramName()), false);

				HeaderFooter footerIN = new HeaderFooter(new Phrase(
						resourceBundle.getString("footerPart1")
								+ "                            "
								+ resourceBundle.getString("footerPart2")),
						false);
				
				headerIN.setAlignment(Element.ALIGN_CENTER);

				headerIN.setBorder(0);
				footerIN.setBorder(0);

				document.setHeader(headerIN);
				document.setFooter(footerIN);				

				document.open();

				document.add(Chunk.NEWLINE);				

				if (infoGetter.getProgramPrintType().equalsIgnoreCase("SAG")) {
					
					float[] colsWidth = { 0.4f, 0.8f, 2f, 2f, 0.8f, 0.8f };
					pdfPTable = new Table(6);

					pdfPTable.addCell(new Phrase("S.N.", cellFonts));
					pdfPTable.addCell(new Phrase("Roll Number", cellFonts));
					pdfPTable.addCell(new Phrase("Name", cellFonts));
					pdfPTable.addCell(new Phrase("Name In Hindi", cellFonts));

					pdfPTable.addCell(new Phrase("Division", cellFonts));
					if (resultSystem == "MK") {
						pdfPTable.addCell(new Phrase("CP", cellFonts));
					} else {
						pdfPTable.addCell(new Phrase("CGPA", cellFonts));
					}

				} else if (infoGetter.getProgramPrintType().equalsIgnoreCase(
						"TAP")) {
					
					float[] colsWidth = { 0.4f, 0.8f, 2f, 1.8f, 0.8f, 0.8f, 0.6f,
							 0.6f };
					pdfPTable = new Table(8);

					pdfPTable.addCell(new Phrase("S.N.", cellFonts));
					pdfPTable.addCell(new Phrase("Roll Number", cellFonts));
					pdfPTable.addCell(new Phrase("Name", cellFonts));
					pdfPTable.addCell(new Phrase("Name In Hindi", cellFonts));

					pdfPTable.addCell(new Phrase("Div. Th.", cellFonts));
					pdfPTable.addCell(new Phrase("Div. Pr.", cellFonts));
					if (resultSystem == "MK") {
						pdfPTable.addCell(new Phrase("CP Th.", cellFonts));
						pdfPTable.addCell(new Phrase("CP Pr.", cellFonts));
					} else {
						pdfPTable.addCell(new Phrase("CGPA Th.", cellFonts));
						pdfPTable.addCell(new Phrase("CGPA Pr.", cellFonts));
					}

				}

				String i = "1";

				Iterator iterator = resultList.iterator();
				List<String> tempBranchCodeList = new ArrayList<String>();
				List<String> tempSpecializationCodeList = new ArrayList<String>();
				List<String> tempBranchNameList = new ArrayList<String>();
				List<String> tempSpecializationNameList = new ArrayList<String>();

				while (iterator.hasNext()) {

					DegreeListInfoGetter object = (DegreeListInfoGetter) iterator
							.next();
					String branchCode = object.getBranchId();
					String branchName = object.getBranchName();				
					
					String specializationCode = object.getSpecializationId();
					String specializationName = object.getSpecializationName();
					Cell cell = null;

					if (tempBranchCodeList.indexOf(branchCode) < 0) {
						if (tempSpecializationCodeList.indexOf(specializationCode) < 0) {
							tempBranchCodeList.add(branchCode);
							tempBranchNameList.add(branchName);
							tempSpecializationCodeList.add(specializationCode);
							tempSpecializationNameList.add(specializationName);
							
							boolean branchFlag = returnFlagforBranch(branchCode);
							boolean specializationFlag = returnFlagforSpeciailization(specializationCode);
							
							if(infoGetter.getProgramPrintType().equalsIgnoreCase("SAG")){
								
								if ((branchFlag==false)
										&& (specializationFlag==false)) {

									/*
									 * code temporaryily not in use
									 * cell = new PdfPCell(new Phrase(""));
									cell.setColspan(6);
									pdfPTable.addCell(cell);*/

								} else if ((branchFlag==false)
										&& (specializationFlag==true)) {

									cell = new Cell(new Phrase(
											specializationName));
									cell.setColspan(6);
									pdfPTable.addCell(cell);

								} else if ((branchFlag==true)
										&&(specializationFlag==true)){									
									
									cell = new Cell(new Phrase(branchName + " "
											+ specializationName));
									cell.setColspan(6);
									pdfPTable.addCell(cell);

								} else if ((branchFlag==true)
										&& (specializationFlag==false)) {									

									cell = new Cell(new Phrase(branchName));
									cell.setColspan(6);
									pdfPTable.addCell(cell);

								}
								
							}else if(infoGetter.getProgramPrintType().equalsIgnoreCase("TAP")){
								
								if ((branchFlag==false)
										&& (specializationFlag==false)) {

									/*
									 * code temporaryily not in use
									 * cell = new PdfPCell(new Phrase(""));
									cell.setColspan(8);
									pdfPTable.addCell(cell);*/

								} else if ((branchFlag==false)
										&& (specializationFlag==true)) {

									cell = new Cell(new Phrase(
											specializationName));
									cell.setColspan(8);
									pdfPTable.addCell(cell);

								} else if ((branchFlag==true)
										&& (specializationFlag==true)) {

									cell = new Cell(new Phrase(branchName + " "
											+ specializationName));
									cell.setColspan(8);
									pdfPTable.addCell(cell);

								} else if ((branchFlag==true)
										&& (specializationFlag==false)) {

									cell = new Cell(new Phrase(branchName));
									cell.setColspan(8);
									pdfPTable.addCell(cell);

								}
								
							}
							
						}
					}
					
					

					if (infoGetter.getProgramPrintType()
							.equalsIgnoreCase("SAG")) {
						pdfPTable.addCell(new Phrase(i, cellFonts));
						pdfPTable.addCell(new Phrase(object.getRollNumber(),
								cellFonts));
						pdfPTable.addCell(new Phrase(object.getStudentName(),
								cellFonts));
						/*
						 * student name in hindi+father's name in hindi
						 */
						
						if(object.getGender().equalsIgnoreCase("m")){
							
							pdfPTable.addCell(new Phrase(URLDecoder.decode(object.getStudentNameInHindi()+" "
									+"%E0%A4%86%E0%A4%A4%E0%A5%8D%E0%A4%AE%E0%A4%9C"+" "+object.getFatherNameInHindi(), "utf-8"),cellFonts));
						}else{
							
							pdfPTable.addCell(new Phrase(URLDecoder.decode(object.getStudentNameInHindi()+" "
									+"%E0%A4%86%E0%A4%A4%E0%A5%8D%E0%A4%AE%E0%A4%9C%E0%A4%BE"+" "+object.getFatherNameInHindi(), "utf-8"),cellFonts));
							
						}
						pdfPTable.addCell(new Phrase(object
								.getDivisionInTheory(), cellFonts));

						if (resultSystem == "MK") {
							pdfPTable.addCell(new Phrase(object
									.getCumPercentage(), cellFonts));
						} else {
							pdfPTable.addCell(new Phrase(object.getCgpa(),
									cellFonts));
						}

					} else if (infoGetter.getProgramPrintType()
							.equalsIgnoreCase("TAP")) {

						pdfPTable.addCell(new Phrase(i, cellFonts));
						pdfPTable.addCell(new Phrase(object.getRollNumber(),
								cellFonts));
						pdfPTable.addCell(new Phrase(object.getStudentName(),
								cellFonts));
						/*
						 * student name in hindi+father's name in hindi
						 */
						
						if(object.getGender().equalsIgnoreCase("m")){
							
							pdfPTable.addCell(new Phrase(URLDecoder.decode(object.getStudentNameInHindi()+" "
									+"%E0%A4%86%E0%A4%A4%E0%A5%8D%E0%A4%AE%E0%A4%9C"+" "+object.getFatherNameInHindi(), "utf-8"),cellFonts));
						}else{
							
							pdfPTable.addCell(new Phrase(URLDecoder.decode(object.getStudentNameInHindi()+" "
									+"%E0%A4%86%E0%A4%A4%E0%A5%8D%E0%A4%AE%E0%A4%9C%E0%A4%BE"+" "+object.getFatherNameInHindi(), "utf-8"),cellFonts));
							
						}
						
						pdfPTable.addCell(new Phrase(object
								.getDivisionInTheory(), cellFonts));
						pdfPTable.addCell(new Phrase(object
								.getDivisionInPractical(), cellFonts));
						if (resultSystem == "MK") {
							pdfPTable.addCell(new Phrase(object
									.getTheoryCWP(), cellFonts));
							pdfPTable.addCell(new Phrase(object
									.getPractialCWP(), cellFonts));
						} else {
							pdfPTable.addCell(new Phrase(object
									.getTheoryCGPA(), cellFonts));
							pdfPTable.addCell(new Phrase(object
									.getPracticalCGPA(), cellFonts));
						}

					}

					i = Integer.parseInt(i) + 1 + "";
				}
				document.add(pdfPTable);

			}

			document.close();
		}
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
	return new ModelAndView("activitymaster/SubmitSuccesful", "message", reportResult);
		//return new ModelAndView("activitymaster/SubmitSuccesful", "message", message);
	}
	
	/**
	 * The method returns boolean value for
	 * the branch code
	 * @param branchCode code of the branch
	 * @return boolean
	 */
	protected Boolean returnFlagforBranch(String branchCode){
		
		boolean flag = true;
		
		if((branchCode.equalsIgnoreCase("XX"))
				||(branchCode.equalsIgnoreCase("00"))){
			
			flag = false;
			
		}
		
		return flag;
		
	}
	
	/**
	 * The method returns booleam value for
	 * the specailization code
	 * @param specializationCode
	 * @return boolean
	 */
	protected Boolean returnFlagforSpeciailization(String specializationCode){
		
		boolean flag = true;
		
		if((specializationCode.equalsIgnoreCase("XX"))
				||(specializationCode.equalsIgnoreCase("00"))){
			
			flag = false;
			
		}
		
		return flag;
		
	}

	/**
	 * The method retrieves the list of entities which the students are persuing
	 * there programs in a university
	 * 
	 * @param request
	 * @param response
	 * @return model & view object
	 * @throws Exception
	 */
	public ModelAndView getEntities(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession(true);

		String userId = (String) session.getAttribute("userId");

		if (userId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		DegreeListInfoGetter input = new DegreeListInfoGetter();

		input.setUniversityCode(userId.substring(1, 5) + "%");

		List<DegreeListInfoGetter> resultObject = degreeListConnect
				.getEntities(input);

		return new ModelAndView("UniversityRolesSetup/UniversityRoles",
				"resultObject", resultObject);

	}

	/**
	 * The method retrieves the programs offered by the selected entity in the
	 * university
	 * 
	 * @param request
	 * @param response
	 * @return model & view object
	 * @throws Exception
	 */
	public ModelAndView getEntityProgram(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession(true);

		String userId = (String) session.getAttribute("userId");

		if (userId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		DegreeListInfoGetter input = new DegreeListInfoGetter();

		input.setEntityId(request.getParameter("entityId"));

		List<DegreeListInfoGetter> resultObject = degreeListConnect
				.getProgramForEntity(input);

		return new ModelAndView("AdvanceParentProgram/AdvanceParentProgram",
				"resultObject", resultObject);

	}

	/**
	 * The method retrieves the list of sessions for the university
	 * 
	 * @param request
	 * @param response
	 * @return model & view object
	 * @throws Exception
	 */
	public ModelAndView getUniversitySession(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession(true);

		String userId = (String) session.getAttribute("userId");

		if (userId == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		DegreeListInfoGetter input = new DegreeListInfoGetter();

		input.setEntityId(request.getParameter("entityId"));
		input.setProgramId(request.getParameter("programId"));
		input
				.setUniversityCode(session.getAttribute("universityId")
						.toString());

		List<DegreeListInfoGetter> resultObject = degreeListConnect
				.getUniversitySession(input);

		return new ModelAndView("UniversityRolesSetup/UniversityRoles",
				"resultObject", resultObject);
	}

}
