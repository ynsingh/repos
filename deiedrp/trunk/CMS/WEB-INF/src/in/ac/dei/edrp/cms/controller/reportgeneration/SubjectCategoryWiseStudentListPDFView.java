/**
 * @(#) SubjectCategoryWiseStudentListPDFView.java
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

import in.ac.dei.edrp.cms.dao.report.ReportDao;
import in.ac.dei.edrp.cms.dao.reportgeneration.SubjectCategoryWiseStudentListDAO;
import in.ac.dei.edrp.cms.domain.report.ReportLogBean;
import in.ac.dei.edrp.cms.domain.reportgeneration.SubjectWiseMeritList;
import in.ac.dei.edrp.cms.utility.ReportPath;
import in.ac.dei.edrp.cms.utility.ReportPathBean;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

public class SubjectCategoryWiseStudentListPDFView extends AbstractPdfView {
	int reportGenerated = 0;
	private ReportDao reportDao;
	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}
	String sep = System.getProperty("file.separator");
	ResourceBundle resourceBundle = ResourceBundle.getBundle("in" + sep + "ac"
			+ sep + "dei" + sep + "edrp" + sep + "cms" + sep
			+ "databasesetting" + sep + "MessageProperties", new Locale("en",
			"US"));
	
	private SubjectCategoryWiseStudentListDAO subjectCategoryWiseStudentListDao;	
		
	public void setSubjectCategoryWiseStudentListDAO(SubjectCategoryWiseStudentListDAO subjectCategoryWiseStudentListDao) {
		this.subjectCategoryWiseStudentListDao = subjectCategoryWiseStudentListDao;
	}

	@SuppressWarnings("unchecked")
	protected void buildPdfDocument(Map model,Document document,PdfWriter writer,HttpServletRequest request,HttpServletResponse response)
									throws Exception {
		HttpSession session = request.getSession(true);
		String logResult="";
		String reportResult="";
		String filePath = "";
		File file;
		try{		
			List <SubjectWiseMeritList> courseGroupList;
			List<SubjectWiseMeritList> studentList=new ArrayList<SubjectWiseMeritList>();
			List<SubjectWiseMeritList> categoryTotalList=new ArrayList<SubjectWiseMeritList>();
			List<SubjectWiseMeritList> completeStudentList=new ArrayList<SubjectWiseMeritList>();
			Table studentTable = null;
			Cell c1;
			//*********nupur code *******************
			ReportPathBean reportPathBean = new ReportPathBean(session.getAttribute("universityId").toString(),request.getParameter("entityId"),
					request.getParameter("programId"),request.getParameter("branchId"),request.getParameter("specializationId"),
					request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
					request.getParameter("enrollmentNumber"),request.getParameter("rollNumber"),request.getParameter("reportCode"),
					request.getParameter("reportType"),request.getParameter("fromSession"),request.getParameter("toSession"),request.getParameter("semesterType"));
				
			String reportPath = ReportPath.getPath(reportPathBean);				
			System.out.println("here in controller of consolidated chart after path generation "+reportPath);
			filePath = this.getServletContext().getRealPath("/");
			filePath=filePath+reportPath;
			file = new File(filePath);
			file.mkdirs();
			PdfWriter.getInstance(document, new FileOutputStream(filePath+sep+request.getParameter("reportDescription")+".pdf"));	
			/*filePath = getServletContext().getRealPath("/")+resourceBundle.getString("directory");
			
			filePath=filePath +sep+session.getAttribute("universityName").toString()+sep+"Session-"+session.getAttribute("startDate").toString().substring(0, 4)+"-"+
			session.getAttribute("endDate").toString().substring(0, 4)+	sep+request.getParameter("selectedEntityName")+sep+request.getParameter("programName")+sep+
			"SubjectCategoryWiseStudentList";
			
			file = new File(filePath);
			file.mkdirs();*/
			
			/*writer = PdfWriter.getInstance(document,new FileOutputStream(filePath+sep+"Subject_Category_Wise_Student_List"+" ("+request.getParameter("programName")+"-"+
					request.getParameter("branchName")+"-"+request.getParameter("specializationName")+"-"+request.getParameter("semesterCode")+").pdf"));*/
			document.setPageSize(PageSize.A4.rotate());	
			String report_session="";
			System.out.println(request.getParameter("semesterCode").substring(2)+"");
			if((Integer.parseInt(request.getParameter("semesterCode").substring(2))%2)==0){
				 report_session=(Integer.parseInt((session.getAttribute("semesterStartDate").toString().substring(0, 4)))-1) + "-"+ session.getAttribute("semesterEndDate").toString().substring(2, 4);
				}
				else
					{ report_session=  session.getAttribute("semesterEndDate").toString().substring(0, 4)+ "-"+(Integer.parseInt((session.getAttribute("semesterStartDate").toString().substring(2, 4)))+1);	
					}
			Phrase header1= new Phrase("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+
					"\t\t\t\t\t\t\t\t\t\t\t\t"+session.getAttribute("universityName").toString().toUpperCase(),FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD,
					new Color(0, 0, 0)));
			Phrase header2= new Phrase("\n \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+
					"NUMBER OF STUDENTS, REGISTRATION BASED (SUBJECT-CATEGORY WISE): "+ report_session,FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,new Color(0, 0, 0)));
		
			Phrase headers=new Phrase();
	        headers.add(header1);
	        headers.add(header2);       
			HeaderFooter header = new HeaderFooter(headers,false);
	        header.setBorderWidth(0);        
	        document.setHeader(header);	        
	        Date printingDate = new Date();
	        String toDay = DateFormat.getDateTimeInstance().format(printingDate);	       
	        HeaderFooter footer = new HeaderFooter(new Phrase("Date : "+toDay),false); 
	        footer.setBorderWidth(0);
	        document.setFooter(footer);        
	        document.open();			
			Font cellFont = new Font(Font.HELVETICA, 8);			
			courseGroupList = (List <SubjectWiseMeritList>) model.get("courseGroupList");			
			SubjectWiseMeritList subjectWiseMeritList=new SubjectWiseMeritList();			
			subjectWiseMeritList.setEntityId(request.getParameter("entityId"));
			subjectWiseMeritList.setUniversityId(session.getAttribute("universityId").toString());
			subjectWiseMeritList.setSemesterStartDate(session.getAttribute("semesterStartDate").toString());
			subjectWiseMeritList.setSemesterEndDate(session.getAttribute("semesterEndDate").toString());
			subjectWiseMeritList.setProgramId(request.getParameter("programId"));
			subjectWiseMeritList.setBranchId(request.getParameter("branchId"));
			subjectWiseMeritList.setSpecializationId(request.getParameter("specializationId"));
			subjectWiseMeritList.setSemesterCode(request.getParameter("semesterCode"));
		
			for(int i=0;i<courseGroupList.size();i++){
				subjectWiseMeritList.setCourseGroup(courseGroupList.get(i).getCourseGroup()+"%");
				studentList=subjectCategoryWiseStudentListDao.getStudentListCategoryGenderWise(subjectWiseMeritList);
				completeStudentList.addAll(studentList);				
				categoryTotalList=subjectCategoryWiseStudentListDao.getStudentListCategoryWise(subjectWiseMeritList);				
				SubjectWiseMeritList entityObject=subjectCategoryWiseStudentListDao.getEntityInfo(subjectWiseMeritList);					
				if(i==0){
					studentTable = new Table((studentList.size()+categoryTotalList.size()+4),3);
					studentTable.setBorderWidth(1);								
					c1 = new Cell(new Phrase(" F-CL-BR-S ",cellFont));		
					c1.setHeader(true);
					c1.setRowspan(2);
					c1.setColspan(3);
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					c1.setVerticalAlignment(Element.ALIGN_CENTER);
					studentTable.addCell(c1);
					
					c1 = new Cell(new Phrase(" Course Group ",cellFont));
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					c1.setVerticalAlignment(Element.ALIGN_CENTER);
					c1.setRowspan(2);
					studentTable.addCell(c1);
					
					c1 = new Cell(new Phrase(" Female \n ",cellFont));
					c1.setColspan((studentList.size()/2));
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					c1.setVerticalAlignment(Element.ALIGN_CENTER);
					studentTable.addCell(c1);
					
					c1 = new Cell(new Phrase(" Males ",cellFont));
					c1.setColspan((studentList.size()/2));
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					c1.setVerticalAlignment(Element.ALIGN_CENTER);
					studentTable.addCell(c1);		
					
					c1 = new Cell(new Phrase(" TOTAL ",cellFont));
					c1.setColspan(categoryTotalList.size());
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					c1.setVerticalAlignment(Element.ALIGN_CENTER);
					studentTable.addCell(c1);
					
					for(int j=0;j<studentList.size();j++){
						c1 = new Cell(new Phrase(studentList.get(j).getCategory(),cellFont));
						c1.setHorizontalAlignment(Element.ALIGN_CENTER);
						c1.setVerticalAlignment(Element.ALIGN_CENTER);
						c1.setUseBorderPadding(true);
						studentTable.addCell(c1); 
					}
				
					for(int j=0;j<categoryTotalList.size();j++){
						c1 = new Cell(new Phrase(categoryTotalList.get(j).getCategory(),cellFont));
						c1.setHorizontalAlignment(Element.ALIGN_CENTER);
						c1.setVerticalAlignment(Element.ALIGN_CENTER);
						studentTable.addCell(c1);
					}
								
					c1 = new Cell(new Phrase(entityObject.getEntityCode()+"-"+request.getParameter("programCode")+"-"+request.getParameter("branchId")+"-"+request.getParameter("specializationId"),cellFont));
					c1.setColspan(3);
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					c1.setVerticalAlignment(Element.ALIGN_CENTER);
					studentTable.addCell(c1);				
				
					c1 = new Cell(new Phrase(courseGroupList.get(i).getCourseGroup(),cellFont));
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					c1.setVerticalAlignment(Element.ALIGN_CENTER);
					studentTable.addCell(c1);								
					for(int j=0;j<studentList.size();j++){
						c1 = new Cell(new Phrase(studentList.get(j).getTotal(),cellFont));					
						c1.setHorizontalAlignment(Element.ALIGN_CENTER);
						c1.setVerticalAlignment(Element.ALIGN_CENTER);
						studentTable.addCell(c1); 
					}
				
					for(int j=0;j<categoryTotalList.size();j++){
						c1 = new Cell(new Phrase(categoryTotalList.get(j).getTotal(),cellFont));
						c1.setHorizontalAlignment(Element.ALIGN_CENTER);
						c1.setVerticalAlignment(Element.ALIGN_CENTER);
						studentTable.addCell(c1);
					}
				}
				else{				
					c1 = new Cell(new Phrase(entityObject.getEntityCode()+"-"+request.getParameter("programCode")+"-"+request.getParameter("branchId")+"-"+request.getParameter("specializationId"),cellFont));
					c1.setColspan(3);
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					c1.setVerticalAlignment(Element.ALIGN_CENTER);
					studentTable.addCell(c1);				
				
					c1 = new Cell(new Phrase(courseGroupList.get(i).getCourseGroup(),cellFont));
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					c1.setVerticalAlignment(Element.ALIGN_CENTER);
					studentTable.addCell(c1);
				
					for(int j=0;j<studentList.size();j++){
						c1 = new Cell(new Phrase(studentList.get(j).getTotal(),cellFont));
						c1.setHorizontalAlignment(Element.ALIGN_CENTER);
						c1.setVerticalAlignment(Element.ALIGN_CENTER);
						studentTable.addCell(c1); 
					}				
					for(int j=0;j<categoryTotalList.size();j++){
						c1 = new Cell(new Phrase(categoryTotalList.get(j).getTotal(),cellFont));
						c1.setHorizontalAlignment(Element.ALIGN_CENTER);
						c1.setVerticalAlignment(Element.ALIGN_CENTER);
						studentTable.addCell(c1);
					}
				}			
			}	    			
			studentTable.setBorderWidth(0);		
			studentTable.setBorder(Rectangle.NO_BORDER);
			studentTable.endHeaders();		
			document.add(studentTable);
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
				logResult = reportDao.insertReportLog(reportControlBean);
				System.out.println("report control log result "+logResult);
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("some exception in log entry "+e);
		}
	}	
}

