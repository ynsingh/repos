package in.ac.dei.edrp.cms.controller.studentregistration;

import in.ac.dei.edrp.cms.dao.report.ReportDao;
import in.ac.dei.edrp.cms.domain.report.ReportLogBean;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentInfoGetter;
import in.ac.dei.edrp.cms.utility.ReportPath;
import in.ac.dei.edrp.cms.utility.ReportPathBean;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.document.AbstractPdfView;



import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class FinalExtendedCheckListPdf extends AbstractPdfView {
	int reportGenerated = 0;
	private ReportDao reportDao;

	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}
	@Override
	protected void buildPdfDocument(Map map, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String logResult="";
		String reportResult="";
		try{
			List<StudentInfoGetter> checkList = (List<StudentInfoGetter>) map.get("checkList");
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
			/*String path = this.getServletContext().getRealPath("/")+ "FinalRegistrationCharts";
		File file = new File(path);
		file.mkdir();*/
			StudentInfoGetter  headerData = checkList.get(checkList.size() - 1);

			headerData.setProgramName(request.getParameter("programName"));
			headerData.setBranchName(request.getParameter("branchName"));
			headerData.setNewSpecializationDescription(request.getParameter("specializationName"));
			headerData.setSemesterStartDate(request.getParameter("semesterStartDate"));
			headerData.setSemesterEndDate(request.getParameter("semesterEndDate"));
			String program= headerData.getProgramName();
			String branch=headerData.getBranchName();
			String specialization=headerData.getNewSpecializationDescription();
			String semester = headerData.getSemesterSequence();
			String sem_st_date = headerData.getSemesterStartDate();
			String sem_end_date=headerData.getSemesterEndDate();
			String report_session;
			if((Integer.parseInt(semester)%2)==0){
				report_session=(Integer.parseInt((sem_st_date.substring(0, 4)))-1) + "-"+ headerData.getSemesterEndDate().substring(2, 4);
			}
			else{
				report_session=  headerData.getSemesterEndDate().substring(0, 4)+ "-"+(Integer.parseInt((sem_st_date.substring(2, 4)))+1);	
			}
			System.out.println(sem_st_date+sem_end_date+report_session);

			Font cellFont = new Font(Font.HELVETICA,8);
			Font cellFont1 = new Font(Font.HELVETICA,8,Font.BOLD);
			writer = PdfWriter.getInstance(document, new FileOutputStream(path+sep+ request.getParameter("reportDescription")+".pdf"));
			/*writer = PdfWriter.getInstance(document, new FileOutputStream(path+sep+ program + "-" + branch + "-"
				+ specialization+"-"+semester+"_"+sem_st_date+"_"+report_session+".pdf"));*/
			String courses;
			String remedial;
			float[] colsWidth = {1.2f,1.2f,3.3f,1f,4.0f,1.3f,1.3f};

			PdfPTable table = new PdfPTable(colsWidth);	
			PdfPCell cell = new PdfPCell(); 
			String headerText = "FINAL REGISTRATION CHART";
			Phrase phrase1 = new Phrase(headerText,new Font(Font.HELVETICA,10,Font.BOLD));
			Phrase head = new Phrase("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tCLASS-"+""+ program+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tBRANCH-"+""+branch+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tSPECIALIZATION-"+""+specialization+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tSEMESTER-"+""+semester+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tSESSION-"+""+report_session,cellFont1);      
			Paragraph paragraph = new Paragraph(20);
			paragraph.add(phrase1);
			paragraph.add(Chunk.NEWLINE);

			paragraph.setAlignment(Element.ALIGN_CENTER);

			document.setPageSize(PageSize.A4.rotate());
			document.open();		
			document.add(paragraph);
			document.add(head);
			document.add(Chunk.NEWLINE);

			cell = new PdfPCell(new Phrase("F CL BF S",cellFont1));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase("ROLL #",cellFont1));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase("--NAME/CATEGORY/ENROLLMENT#--",cellFont1));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase("S A SP",cellFont1));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase("- - - - - - - - - - - - -COURSES- - - - - - - - - - - - -",cellFont1));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase("    REMEDIAL",cellFont1));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase("       SGPA   ",cellFont1));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			for(StudentInfoGetter student:checkList){
				cell = new PdfPCell(new Phrase(Integer.parseInt(student.getEntityNumber())+" "+student.getProgramCode().toUpperCase()+" "+student.getBranchId().toUpperCase()+" "+student.getSemesterSequence(),cellFont));			
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);			
				System.out.println("roll "+student.getRollNumber());
				cell = new PdfPCell(new Phrase(student.getRollNumber(),cellFont));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);
				cell = new PdfPCell(new Phrase(student.getStudentName().toUpperCase()+"\n"+student.getCategory().toUpperCase()+"\n"+((student.getEnrollmentNumber()==null)?"":student.getEnrollmentNumber()),cellFont));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);
				cell = new PdfPCell(new Phrase(student.getGender().toUpperCase()+" "+student.getAttemptNumber()+" "+student.getSpecializationId(),cellFont));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);
				courses = new String();

				int	k=0;
				for(StudentInfoGetter course:student.getCourseList()){			
					k++;								
				}									
				float[] cWidth = {.8f,.8f,.8f,.8f};
				PdfPTable table2=new PdfPTable(cWidth);	

				if((k%4)==0){				
					for(StudentInfoGetter course:student.getCourseList()){	
						cell = new PdfPCell(new Phrase(course.getCourseCode(),cellFont));				
						cell.setBorder(Rectangle.ALIGN_MIDDLE);
						cell.setBorderWidth(0);
						table2.addCell(cell);
					}
				}

				if((k%4)!=0){				
					for(StudentInfoGetter course:student.getCourseList()){					
						cell = new PdfPCell(new Phrase(course.getCourseCode(),cellFont));					
						cell.setBorder(Rectangle.ALIGN_MIDDLE);
						cell.setBorderWidth(0);
						table2.addCell(cell);
					}

					k=4-(k%4);
					while(k>0){
						PdfPCell emptycell=new PdfPCell(new Phrase(" ",cellFont));
						emptycell.setBorder(Rectangle.ALIGN_MIDDLE);
						emptycell.setBorderWidth(0);
						table2.addCell(emptycell);
						k--;
					}
				}	

				PdfPCell cell1 = new PdfPCell(table2);
				cell1.setBorderWidth(0);
				table.addCell(cell1);			
				remedial = new String();			
				if(student.getRemedialList().size()!=0){				
					for(StudentInfoGetter studentRemedial : student.getRemedialList()){		
						remedial = studentRemedial.getCourseCode()+"  "+remedial;				
					}				
				}
				cell = new PdfPCell(new Phrase(remedial,cellFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);

				if(student.getAggregatePractical()!=null){
					cell = new PdfPCell(new Phrase(student.getAggregateTheory()+" "+student.getAggregatePractical(),cellFont));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBorder(Rectangle.NO_BORDER);
					table.addCell(cell);
				}
				else{
					cell = new PdfPCell(new Phrase(student.getAggregateTheory(),cellFont));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBorder(Rectangle.NO_BORDER);
					table.addCell(cell);
				}				

			}		
			table.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.setLockedWidth(false);
			document.add(table);
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
}	

