/*
 * @(#) DegreeListPdf.java
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
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * This file generates the reports for different programs in the defined format
 * 
 * @author Ashish
 * @date 21 Aug 2011
 * @version 1.0
 */
public class DegreeListPdf extends AbstractPdfView {

	private DegreeListConnect degreeListConnect;

	String sep = System.getProperty("file.separator");
	ResourceBundle resourceBundle = ResourceBundle.getBundle("in" + sep + "ac"
			+ sep + "dei" + sep + "edrp" + sep + "cms" + sep
			+ "databasesetting" + sep + "MessageProperties", new Locale("en",
			"US"));

	/**
	 * @param degreeListConnect
	 *            the degreeListConnect to set
	 */
	public void setDegreeListConnect(DegreeListConnect degreeListConnect) {
		this.degreeListConnect = degreeListConnect;
	}

	@SuppressWarnings("unchecked")
	protected void buildPdfDocument(Map model, Document document,
			PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DegreeListInfoGetter infoGetter = new DegreeListInfoGetter();
		
		PdfPTable pdfPTable = null ;

		List<DegreeListInfoGetter> resultList = null;

		HttpSession session = request.getSession(true);

		infoGetter.setEntityId(request.getParameter("entityId"));
		infoGetter.setProgramId(request.getParameter("programId"));
		infoGetter.setPassedFromSession(request.getParameter("fromSession"));
		infoGetter.setPassedToSession(request.getParameter("toSession"));
		infoGetter.setUniversityCode(session.getAttribute("universityId")
				.toString());
		infoGetter.setUniversityName(session.getAttribute("universityName").toString());
		infoGetter.setProgramName(request.getParameter("programName"));
		infoGetter.setProgramCode(request.getParameter("programCode"));
		infoGetter.setProgramPrintType(request.getParameter("printType"));

		List<DegreeListInfoGetter> list = new ArrayList<DegreeListInfoGetter>();

		list = (List<DegreeListInfoGetter>) model.get("resultObject");

		/*
		 * path of the directory
		 */
		String directory = getServletContext().getRealPath("/")+resourceBundle.getString("directory") +sep+infoGetter.getUniversityName()+ sep
		+infoGetter.getPassedFromSession().substring(0,4)+
		"-"+infoGetter.getPassedToSession().substring(2,4)+sep+resourceBundle.getString("degreereports")+sep+ infoGetter.getProgramName();

		File file = new File(directory);

		file.mkdirs();

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
				
				writer = PdfWriter.getInstance(document, new FileOutputStream(
						directory + sep + infoGetter.getProgramName() + "("
								+ infoGetter.getBranchName() + ")" + ".pdf"));

				HeaderFooter headerIN = new HeaderFooter(
						new Phrase(
								"                                           "
										+ resourceBundle
												.getString("headerPart3")
										+ "\n"
										+ "                                                               "
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
										+ "                                                           "
										+ resourceBundle
												.getString("footerPart4")),
						false);

				headerIN.setBorder(0);
				footerIN.setBorder(0);

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
					PdfPTable headerTable = new PdfPTable(colsWidth);

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
						headerTable.addCell(new Phrase(object.getStudentName(),
								cellFont));
						headerTable.addCell(new Phrase(object.getFatherName(),
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
				writer = PdfWriter.getInstance(document, new FileOutputStream(
						directory + sep + infoGetter.getProgramName() + "("
								+ infoGetter.getBranchName() + ")" + ".pdf"));

				HeaderFooter headerIN = new HeaderFooter(
						new Phrase(
								"                                           "
										+ resourceBundle
												.getString("headerPart3")
										+ "\n"
										+ "                                                               "
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
										+ "                                                           "
										+ resourceBundle
												.getString("footerPart4")),
						false);

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
					PdfPTable headerTable = new PdfPTable(colsWidth);

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
						headerTable.addCell(new Phrase(object.getStudentName(),
								cellFont));
						headerTable.addCell(new Phrase(object.getFatherName(),
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
			
			writer = PdfWriter.getInstance(document, new FileOutputStream(
					directory+sep+infoGetter.getProgramName()+".pdf"));

			while (branchIterator.hasNext()) {
				DegreeListInfoGetter branchListInfoGetter = (DegreeListInfoGetter) branchIterator
						.next();

				infoGetter.setBranchId(branchListInfoGetter.getBranchId());
				infoGetter.setBranchName(branchListInfoGetter.getBranchName());

				resultList = degreeListConnect
						.printDegreeListforOhterPrograms(infoGetter);

				HeaderFooter headerIN = new HeaderFooter(
						new Phrase(
								"           "
										+ resourceBundle
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
								+ "                                    "
								+ resourceBundle.getString("footerPart2")),
						false);

				headerIN.setBorder(0);
				footerIN.setBorder(0);

				document.setHeader(headerIN);
				document.setFooter(footerIN);				

				document.open();

				document.add(Chunk.NEWLINE);				

				if (infoGetter.getProgramPrintType().equalsIgnoreCase("SAG")) {
					
					float[] colsWidth = { 0.4f, 0.8f, 2f, 2f, 0.8f, 0.8f };
					pdfPTable = new PdfPTable(colsWidth);

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
					pdfPTable = new PdfPTable(colsWidth);

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
					PdfPCell cell = null;

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

									cell = new PdfPCell(new Phrase(
											specializationName));
									cell.setColspan(6);
									pdfPTable.addCell(cell);

								} else if ((branchFlag==true)
										&&(specializationFlag==true)){									
									
									cell = new PdfPCell(new Phrase(branchName + " "
											+ specializationName));
									cell.setColspan(6);
									pdfPTable.addCell(cell);

								} else if ((branchFlag==true)
										&& (specializationFlag==false)) {									

									cell = new PdfPCell(new Phrase(branchName));
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

									cell = new PdfPCell(new Phrase(
											specializationName));
									cell.setColspan(8);
									pdfPTable.addCell(cell);

								} else if ((branchFlag==true)
										&& (specializationFlag==true)) {

									cell = new PdfPCell(new Phrase(branchName + " "
											+ specializationName));
									cell.setColspan(8);
									pdfPTable.addCell(cell);

								} else if ((branchFlag==true)
										&& (specializationFlag==false)) {

									cell = new PdfPCell(new Phrase(branchName));
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
						pdfPTable.addCell(new Phrase("", cellFonts));
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
						pdfPTable.addCell(new Phrase("", cellFont));
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

}
