package in.ac.dei.edrp.cms.controller.reportgeneration;
import in.ac.dei.edrp.cms.dao.report.ReportDao;
import in.ac.dei.edrp.cms.domain.report.ReportLogBean;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentInfoGetter;
import in.ac.dei.edrp.cms.utility.ReportPath;
import in.ac.dei.edrp.cms.utility.ReportPathBean;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.view.document.AbstractPdfView;


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

public class UnsatisfactoryPerformancePdf extends AbstractPdfView{
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
		/*String path = this.getServletContext().getRealPath("/")+ "UnsatisfactoryPerformanceReport";
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
		else
			{ report_session=  headerData.getSemesterEndDate().substring(0, 4)+ "-"+(Integer.parseInt((sem_st_date.substring(2, 4)))+1);	
			}
			System.out.println(sem_st_date+sem_end_date+report_session);
		
		Font cellFont = new Font(Font.HELVETICA,8);
		Font cellFont1 = new Font(Font.HELVETICA,8,Font.BOLD);

		writer = PdfWriter.getInstance(document, new FileOutputStream(path+sep+ request.getParameter("reportDescription")+".pdf"));
		
		Calendar currentDate=Calendar.getInstance() ;
		SimpleDateFormat formatter= new SimpleDateFormat("dd-MMM-yyyy");
		String dateNow = formatter.format(currentDate.getTime());
	
		float[] colsWidth = {0.8f,1.0f,2.8f,1.8f,3.2f};
		
		PdfPTable table = new PdfPTable(colsWidth);	
		table.setWidthPercentage(100);
		table.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cell = new PdfPCell(); 
		
		Phrase headerText = new Phrase("EXAMINATION DEPARTMENT \n DAYALBAGH EDUCATIONAL INSTITUTE \n DAYALBAGH,AGRA-282005",new Font(Font.HELVETICA,10,Font.BOLD));
		Paragraph paragraph = new Paragraph(9);
		paragraph.add(headerText);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		
		Phrase heading = new Phrase("LIST OF UNSATISFACTORY PERFORMANCES"+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tRUN DATE "+dateNow+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPAGE   1",cellFont1);      
		
	//	Phrase tabPhrase=new Phrase();
	//	for(int i=0;i<((40-(program.length())));i++){tabPhrase.add("\t");}
		Phrase head =new Phrase("\nCLASS-"+""+ program+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tBRANCH-"+""+branch+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tSPECIALIZATION-"+""+specialization+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tSEMESTER-"+""+semester,cellFont);
		
		Phrase dotline=new Phrase("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------",cellFont);

		
		document.setPageSize(PageSize.A4.rotate());
		document.open();		
		document.add(paragraph);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		document.add(heading);
		document.add(Chunk.NEWLINE);
		document.add(head);
		document.add(Chunk.NEWLINE);
		document.add(dotline);
		cell = new PdfPCell(new Phrase("SRL.\nNO.",cellFont1));
		
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("   ROLL \nNUMBER",cellFont1));
		
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("N A M E",cellFont1));
		
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("RESULT",cellFont1));
	
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("SUBJECTS",cellFont1));
		
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		int lp=5;
		while(lp>0)
		{
		cell = new PdfPCell(new Phrase(" ",cellFont));
		cell.setBorder(Rectangle.BOTTOM);
		table.addCell(cell);
		lp--;
		}
		
		
		for(StudentInfoGetter student:checkList)
		{	
			int j=(checkList.indexOf(student))+1;
			cell = new PdfPCell(new Phrase(""+j,cellFont));			
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);			
			System.out.println("roll "+student.getRollNumber());
			cell = new PdfPCell(new Phrase(student.getRollNumber(),cellFont));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(student.getStudentName(),cellFont));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			
			if(student.getStatus().equalsIgnoreCase("REM"))
			{
				cell = new PdfPCell(new Phrase("REMEDIAL",cellFont));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);
			}
			else
			{
				if(student.getAttemptNumber()==1){
				cell = new PdfPCell(new Phrase("FAILED  -  FIRST ATTEMPT",cellFont));
				}
				else if(student.getAttemptNumber()==2){
					cell = new PdfPCell(new Phrase("FAILED  -  SECOND ATTEMPT",cellFont));
				}
				else if(student.getAttemptNumber()==3){
					cell = new PdfPCell(new Phrase("FAILED  -  THIRD ATTEMPT",cellFont));
				}
				else{
					cell = new PdfPCell(new Phrase("FAILED  -  "+student.getAttemptNumber()+"th ATTEMPT",cellFont));
				}
				cell.setBorder(Rectangle.ALIGN_LEFT);
				table.addCell(cell);
			}
			
			int	k=0;
			for(StudentInfoGetter remedial:student.getRemedialList()){
			k++;
			}
			float[] cWidth = {.8f,.8f,.8f,.8f};
			PdfPTable table2=new PdfPTable(cWidth);	
			
			//courses come only for remedial
			if(student.getStatus().equalsIgnoreCase("REM"))
			{
			if((k%4)==0){
				for(StudentInfoGetter remedial:student.getRemedialList())
				{	
				cell = new PdfPCell(new Phrase(remedial.getCourseCode(),cellFont));
				cell.setBorder(Rectangle.ALIGN_MIDDLE);
				cell.setBorderWidth(0);
				table2.addCell(cell);
				}
			}
			
			if((k%4)!=0){
				
			for(StudentInfoGetter remedial:student.getRemedialList())
			{	
				
					cell = new PdfPCell(new Phrase(remedial.getCourseCode(),cellFont));
					
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
			}
			
			
			
			//courses for fail
			else{
				int loop=4;
				while(loop>0)
				{
				cell = new PdfPCell(new Phrase("",cellFont));
				cell.setBorder(Rectangle.ALIGN_MIDDLE);
				cell.setBorderWidth(0);
				table2.addCell(cell);
				loop--;
				}
			}
			PdfPCell cell1 = new PdfPCell(table2);
			cell1.setBorderWidth(0);
			table.addCell(cell1);
		
		}		
		
	//	table.setLockedWidth(false);
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
