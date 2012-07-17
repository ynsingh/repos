/**
 * @(#) TransferOldStudentToPrestagingController.java
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
package in.ac.dei.edrp.cms.controller.prestaging;

import in.ac.dei.edrp.cms.dao.prestaging.TransferOldStudentToPrestagingService;
import in.ac.dei.edrp.cms.domain.consolidatedchart.ConsolidatedChartBean;
import in.ac.dei.edrp.cms.domain.enrollment.EnrollmentInfo;
import in.ac.dei.edrp.cms.domain.prestaging.PrestagingInfoGetter;
import in.ac.dei.edrp.cms.domain.studentmaster.StudentMasterInfoBean;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Sheet;
import jxl.Workbook;

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

/**
 * this is Server side controller class for transfer old students data to
 * prestaging
 * 
 * @version 1.0 12 AUGUST 2011
 * @author MOHD AMIR
 */
public class TransferOldStudentToPrestagingController extends
		MultiActionController {
	/** creating object of transferEnrollmentToPrestagingService interface */
	private TransferOldStudentToPrestagingService transferOldStudentToPrestagingService;

	/** defining setter method for object of interface */
	public void setTransferOldStudentToPrestagingService(
			TransferOldStudentToPrestagingService transferOldStudentToPrestagingService) {
		this.transferOldStudentToPrestagingService = transferOldStudentToPrestagingService;
	}

	/**
	 * This method transfer enrollment data to prestaging table
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView containing no of records transfered
	 */
	public ModelAndView transferEnrollmentToPrestaging(
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);

		if (session.getAttribute("universityId") == null) {
			return new ModelAndView("general/SessionInactive",
					"sessionInactive", true);
		}

		List<EnrollmentInfo> errorList = new ArrayList<EnrollmentInfo>();
		File inputWorkbook = new File(this.getServletContext().getRealPath("/")
				+ "PrestagingDataDocuments/Existing/"
				+ request.getParameter("fileName"));
		Boolean b = true;
		Workbook w;
		try {
			w = Workbook.getWorkbook(inputWorkbook);

			for (int s = 0; s < w.getNumberOfSheets(); s++) {
				Sheet sheet = w.getSheet(s);
				for (int i = 2; i < sheet.getRows(); i++) {
					EnrollmentInfo input = new EnrollmentInfo();
					input.setEnrollmentNo(sheet.getCell(0, i).getContents()
							.toString());
					input.setStudentFirstName("%"
							+ sheet.getCell(1, i).getContents().toString());
					input.setStudentMiddleName("%"
							+ sheet.getCell(2, i).getContents().toString());
					input.setStudentLastName("%"
							+ sheet.getCell(3, i).getContents().toString());
					input.setDob("%"
							+ sheet.getCell(4, i).getContents().toString());
					input.setGender("%"
							+ sheet.getCell(5, i).getContents().toString());

					input.setUniversityId(session
							.getAttribute("universityName").toString());
					input.setEntityCode(request.getParameter("entityId"));
					input.setProgramCode(request.getParameter("programId"));
					input.setBranchCode(request.getParameter("branchId"));
					input.setSpecializationCode(request
							.getParameter("specializationId"));
					input.setSessionStartDate(session.getAttribute("startDate")
							.toString());
					input.setSessionEndDate(session.getAttribute("endDate")
							.toString());

					List<EnrollmentInfo> personalData = transferOldStudentToPrestagingService
							.getStudentPersonalData(input);

					if (personalData.size() > 0) {
						EnrollmentInfo inputTemp = personalData.get(0);
						inputTemp.setUniversityId(session.getAttribute(
								"universityId").toString());
						inputTemp.setEntityCode(input.getEntityCode());
						inputTemp.setProgramCode(input.getProgramCode());
						inputTemp.setBranchCode(input.getBranchCode());
						inputTemp.setSpecializationCode(input
								.getSpecializationCode());
						inputTemp.setSessionStartDate(input
								.getSessionStartDate());
						inputTemp.setSessionEndDate(input.getSessionEndDate());
						inputTemp.setUserType(session.getAttribute("userId")
								.toString());// used for user id

						ConsolidatedChartBean inputForSem = new ConsolidatedChartBean();
						inputForSem.setSemSeqNo("1");
						inputForSem.setSemesterCode("%");
						inputForSem.setProgramCode(input.getProgramCode());
						
						List<ConsolidatedChartBean> semSeqList = transferOldStudentToPrestagingService
								.getSemesterAndSeqNo(inputForSem);
						if (semSeqList.size() > 0) {
							inputTemp.setSemesterCode(semSeqList.get(0)
									.getSemesterCode());
						}

						List<StudentMasterInfoBean> addressData = transferOldStudentToPrestagingService
								.getStudentAddressData(inputTemp.getStudentId());

						PrestagingInfoGetter dateData = transferOldStudentToPrestagingService
								.getRegistrationDueDate(inputTemp);

						PrestagingInfoGetter dataRow = new PrestagingInfoGetter();
						dataRow.setEnrollmentNo(inputTemp.getEnrollmentNo());
						dataRow.setStudentFirstName(inputTemp
								.getStudentFirstName());
						dataRow.setStudentMiddleName(inputTemp
								.getStudentMiddleName());
						dataRow.setStudentLastName(inputTemp
								.getStudentLastName());
						dataRow.setFatherFirstName(inputTemp
								.getFatherFirstName());
						dataRow.setFatherMiddleName(inputTemp
								.getFatherMiddleName());
						dataRow.setFatherLastName(inputTemp.getFatherLastName());
						dataRow.setMotherFirstName(inputTemp
								.getMotherFirstName());
						dataRow.setMotherMiddleName(inputTemp
								.getMotherMiddleName());
						dataRow.setMotherLastName(inputTemp.getMotherLastName());
						dataRow.setCategory(inputTemp.getCategoryCode());
						dataRow.setDob(inputTemp.getDob());
						dataRow.setAdmissionMode("NEW");
						dataRow.setAttemptNo("1");
						dataRow.setEntityId(inputTemp.getEntityCode());
						dataRow.setProgramId(inputTemp.getProgramCode());
						dataRow.setBranchId(inputTemp.getBranchCode());
						dataRow.setSpecializationId(inputTemp
								.getSpecializationCode());
						dataRow.setSemesterId(inputTemp.getSemesterCode());
						dataRow.setUniversityId(inputTemp.getUniversityId());
						dataRow.setGender(inputTemp.getGender());
						dataRow.setRegistrationDueDate(dateData
								.getRegistrationDueDate());
						dataRow.setSemesterStartDate(dateData
								.getSemesterStartDate());
						dataRow.setSemesterEndDate(dateData
								.getSemesterEndDate());
						dataRow.setPrimaryEmail(inputTemp.getPrimaryMail());
						dataRow.setUserId(inputTemp.getUserType());
						dataRow.setNickName(session.getAttribute("nickName")
								.toString());

						for (int j = 0; j < addressData.size(); j++) {
							if (addressData.get(j).getAddressKey()
									.equalsIgnoreCase("PER")) {
								dataRow.setPerAddress(addressData.get(j)
										.getAddressLineOne());
								dataRow.setPerCity(addressData.get(j).getCity());
								dataRow.setPerState(addressData.get(j)
										.getState());
								dataRow.setPerPinCode(addressData.get(j)
										.getPinCode());
								dataRow.setOfficePhone(addressData.get(j)
										.getOfficePhone());
								dataRow.setHomePhone(addressData.get(j)
										.getHomePhone());
								dataRow.setOtherPhone(addressData.get(j)
										.getOtherPhone());
								dataRow.setFax(addressData.get(j).getFax());
							} else if (addressData.get(j).getAddressKey()
									.equalsIgnoreCase("COR")) {
								dataRow.setCorAddress(addressData.get(j)
										.getAddressLineOne());
								dataRow.setCorCity(addressData.get(j).getCity());
								dataRow.setCorState(addressData.get(j)
										.getState());
								dataRow.setCorPinCode(addressData.get(j)
										.getPinCode());
							}
						}
						b = transferOldStudentToPrestagingService
								.setPrestagingData(dataRow) && b;
					} else {
						errorList.add(input);
						b = false;
					}
				}
			}

			if (errorList.size() == 0) {
				String s = this.getServletContext().getRealPath("/")
						+ "PrestagingDataDocuments/Existing/"
						+ request.getParameter("fileName");
				File source = new File(s);
				s = this.getServletContext().getRealPath("/")
						+ "UploadedPrestagingDataDocuments/Existing/";
				File destination = new File(s);
				destination.mkdirs();
				s = s + request.getParameter("fileName");
				destination = new File(s);
				source.renameTo(destination);
			} else {
				generateAndShowPDF(errorList);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new ModelAndView("systemtabletwo/countInfo", "count", b);
	}

	public void generateAndShowPDF(List<EnrollmentInfo> errorList) {
		String sep = System.getProperty("file.separator");
		String filePath = this.getServletContext().getRealPath("/")
				+ "PrestagingErrorList" + sep + "Existing";
		Chunk line = new Chunk(
				"______________________________________________________________________________");
		File file = new File(filePath);
		file.mkdirs();
		filePath = filePath + sep + errorList.get(0).getEntityCode() + "_"
				+ errorList.get(0).getProgramCode() + "_"
				+ errorList.get(0).getBranchCode() + "_"
				+ errorList.get(0).getSpecializationCode() + "_errorList.pdf";

		String universityName = errorList.get(0).getUniversityId();
		String universitySession = errorList.get(0).getSessionStartDate()
				.substring(0, 4)
				+ "-" + errorList.get(0).getSessionEndDate().substring(0, 4);

		try {
			Document document = new Document(PageSize.A4);
			PdfWriter.getInstance(document, new FileOutputStream(filePath));

			Phrase universityInfo = new Phrase(universityName.toUpperCase()
					+ "\n(" + universitySession + ")\n", FontFactory.getFont(
					FontFactory.HELVETICA, 10, Font.BOLD, new Color(0, 0, 0)));

			Phrase reportInfo = new Phrase(
					"STUDENT'S RECORDS REJECTED BY UPLOAD PROCESS",
					FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD,
							new Color(0, 0, 0)));

			Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 8,
					Font.BOLD, new Color(0, 0, 0));
			Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 8,
					Font.NORMAL, new Color(0, 0, 0));
			PdfPCell c1 = new PdfPCell(new Phrase("", headerFont));
			PdfPTable headerTable = new PdfPTable(new float[] { 1.5f, 5, 1.5f,
					1 });
			headerTable.setWidthPercentage(100);
			addCell(c1, headerFont, headerTable, "ENROLLMENT NO.");
			addCell(c1, headerFont, headerTable, "STUDENT'S NAME");
			addCell(c1, headerFont, headerTable, "DATE OF BIRTH");
			addCell(c1, headerFont, headerTable, "GENDER");

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

			PdfPTable dataTable = new PdfPTable(
					new float[] { 1.5f, 5, 1.5f, 1 });
			dataTable.setWidthPercentage(100);
			dataTable.setHorizontalAlignment(Element.ALIGN_CENTER);
			for (int i = 0; i < errorList.size(); i++) {
				addCell(c1, dataFont, dataTable, errorList.get(i)
						.getEnrollmentNo());
				addCell(c1, dataFont, dataTable, errorList.get(i)
						.getStudentFirstName().substring(1)
						+ " "
						+ errorList.get(i).getStudentMiddleName().substring(1)
						+ " "
						+ errorList.get(i).getStudentLastName().substring(1));
				addCell(c1, dataFont, dataTable, errorList.get(i).getDob()
						.substring(1));
				addCell(c1, dataFont, dataTable, errorList.get(i).getGender()
						.substring(1));
			}

			document.setHeader(header);
			document.setFooter(footer);

			document.open();
			document.add(dataTable);
			document.close();

			File pdfFile = new File(filePath);
			if (pdfFile.exists()) {

				if (Desktop.isDesktopSupported()) {
					Desktop.getDesktop().open(pdfFile);
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

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