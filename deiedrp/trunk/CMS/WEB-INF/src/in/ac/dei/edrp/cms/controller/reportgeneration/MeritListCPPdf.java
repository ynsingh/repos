package in.ac.dei.edrp.cms.controller.reportgeneration;
import in.ac.dei.edrp.cms.dao.report.ReportDao;
import in.ac.dei.edrp.cms.daoimpl.report.ReportDaoImpl;
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

public class MeritListCPPdf{
	private ReportDao reportDao;
	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}
	/*@Override
	protected void buildPdfDocument(Map map, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	}*/
	public String generateMeritList(HttpServletRequest request,List<StudentInfoGetter> checkList){
//		ReportDao reportDao = new ReportDaoImpl();
		System.out.println("report dao is "+reportDao);
		System.out.println("chceck list size "+checkList.size());
		 String result = "false";		
 		String logResult="";		
 		int reportGenerated = 0;
 		File file;
 		HttpSession session=request.getSession(true);
 		String sep = System.getProperty("file.separator");
//		List<StudentInfoGetter> checkList = (List<StudentInfoGetter>) map.get("checkList");	
 		try{
		System.out.println("check list size is "+checkList.size());
		StudentInfoGetter  headerData = checkList.get(checkList.size() - 1);
	
		headerData.setProgramName(request.getParameter("programName"));
		headerData.setBranchName(request.getParameter("branchName"));
		headerData.setNewSpecializationDescription(request.getParameter("specializationName"));
		
		String program= headerData.getProgramName();
		String branch=headerData.getBranchName();
		String specialization=headerData.getNewSpecializationDescription();
		String passingDate=headerData.getStatus();
		String session1 =passingDate.substring(0, 4);
		
		Font cellFont = new Font(Font.HELVETICA,8);
		Font cellFont1 = new Font(Font.HELVETICA,8,Font.BOLD);
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, new FileOutputStream(request.getAttribute("path")+sep+request.getParameter("reportDescription")+".pdf"));
		/*Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(path+sep+ program + "-" + branch + "-"
				+ specialization+"-"+session+".pdf"));*/
	
		float[] colsWidth = {0.8f,0.8f,0.8f,0.8f,0.8f};
		
		PdfPTable table = new PdfPTable(colsWidth);	
		table.setWidthPercentage(100);
		table.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cell = new PdfPCell(); 
		
		Phrase headerText = new Phrase("EXAMINATION DEPARTMENT \n DAYALBAGH EDUCATIONAL INSTITUTE \n DAYALBAGH,AGRA-282005",new Font(Font.HELVETICA,10,Font.BOLD));
		Paragraph paragraph = new Paragraph(9);
		paragraph.add(headerText);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		
		Phrase heading = new Phrase("LIST OF PASSED CANDIDATES IN ORDER OF MERIT IN THE EXAMINATION OF "+session1+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPAGE   1",cellFont1);      
		
	
		Phrase head =new Phrase(program+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+branch+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+specialization,cellFont);
		
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
		cell = new PdfPCell(new Phrase("CGPA",cellFont1));
	
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("POSITION",cellFont1));
		
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
			cell = new PdfPCell(new Phrase(student.getStudentName().toUpperCase(),cellFont));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(student.getCosValue(),cellFont));
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(""+j,cellFont));	
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
		}	
			
		
	//	table.setLockedWidth(false);
		document.add(table);
		document.close();
		result = "true";
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("after prnt stack trace");
			result = "false";
			ReportLogBean reportErrorBean = new ReportLogBean(session.getAttribute("universityId").toString(),request.getParameter("programId"),
					request.getParameter("branchId"),request.getParameter("specializationId"),request.getParameter("entityId"),
					request.getParameter("semesterCode"),request.getParameter("semesterStartDate"),request.getParameter("semesterEndDate"),
					request.getParameter("reportCode"),"",request.getParameter("rollNumber"),
					request.getParameter("fromSession").substring(0, 4)+"-"+request.getParameter("toSession").substring(2,4));
			logResult = reportDao.insertReportErrorLog(reportErrorBean);						
			System.out.println("nupur : some exception in report generation");
			System.out.println("exception :"+e);
		}
		try{
			if(result.equalsIgnoreCase("true")){
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
			e.printStackTrace();
			System.out.println("some exception in log entry "+e);
		}
		return result;
	}

}
