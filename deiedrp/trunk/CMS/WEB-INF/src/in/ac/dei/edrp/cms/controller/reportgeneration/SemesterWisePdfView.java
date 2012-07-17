package in.ac.dei.edrp.cms.controller.reportgeneration;

import in.ac.dei.edrp.cms.dao.report.ReportDao;
import in.ac.dei.edrp.cms.domain.report.ReportLogBean;
import in.ac.dei.edrp.cms.domain.reportgeneration.SemesterInfoForAllStudents;

import java.awt.Color;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class SemesterWisePdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map arg0, Document arg1, PdfWriter arg2,
			HttpServletRequest arg3, HttpServletResponse arg4) throws Exception {					
		
	}	
	public String generatePdfForSemesterWiseMarks(HttpServletRequest request,ReportDao reportDao, Document document,PdfWriter writer,List<SemesterInfoForAllStudents> studentsInfo,String directoryName) throws Exception
	{	
		String result = "false";		
		String logResult="";		
		int reportGenerated = 0;
		HttpSession session=request.getSession(true);
		
		System.out.println("in semester generation");
		String rollNo = null;
		String entityId = null;
		String programCode = null;
		String name = null;
		String branchId = null;
		String semesterSequence = null;
		String specialization=null;
		String genAttempt=null;
		String sessionStartDate=studentsInfo.get(0).getProgressCardInfo().getSessionStartDate();
		String sessionEndDate=studentsInfo.get(0).getProgressCardInfo().getSessionEndDate();
		String sessionDate  = sessionStartDate+"-"+sessionEndDate.substring(2,4);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		
		Calendar calendar = Calendar.getInstance();
		java.sql.Date date = new java.sql.Date(calendar.getTime().getTime());
		String currentDate = sdf.format(date);
			
		Phrase headerText = new Phrase("EXAMINATION DEPARTMENT \n DAYALBAGH EDUCATIONAL INSTITUTE \n DAYALBAGH,AGRA-282005",new Font(Font.HELVETICA,10,Font.BOLD));
		Paragraph paragraph = new Paragraph(9);
		paragraph.add(headerText);
		paragraph.setAlignment(Element.ALIGN_CENTER);
         Phrase heading=new Phrase("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tConsolidated Result\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+sessionDate);

		try{
		float[] colsWidth = {1.4f,1.3f,2f,0.8f,0.8f,1f,1f,1f,1f,1f,1f,1f,1f,1f}; // Code 1
		PdfPTable headerTable = new PdfPTable(colsWidth);		
		Font cellFont = new Font(Font.HELVETICA,8);
		

		
		headerTable.setWidthPercentage(100); // Code 2
		headerTable.setHorizontalAlignment(Element.ALIGN_LEFT);//Code 3		
		headerTable.addCell(new Phrase("FCLBRS",cellFont));
		headerTable.addCell(new Phrase("RollNO.",cellFont));
		headerTable.addCell(new Phrase("Name Of Student",cellFont));
		headerTable.addCell(new Phrase("SA",cellFont));
		headerTable.addCell(new Phrase("Sp",cellFont));
		String fileName;
		fileName = studentsInfo.get(0).getProgressCardInfo().getProgramCode()+"SS"+studentsInfo.get(0).getProgressCardInfo().getSemesterSequence()+"Y"+
					currentDate.substring(2,4);
		for(int i=1;i<=8;i++)
		{
			headerTable.addCell(new Phrase("GPA"+i,cellFont));			
		}
		headerTable.addCell(new Phrase("CGPA",cellFont));
		
		writer = PdfWriter.getInstance(document, new FileOutputStream(directoryName+System.getProperty("file.separator")+request.getParameter("reportDescription")+".pdf"));
		System.out.println("dir name "+directoryName);
		document.open();
		document.add(paragraph);
		document.add(Chunk.NEWLINE);
		document.add(heading);
		document.add(Chunk.NEWLINE);
		for(SemesterInfoForAllStudents sifas : studentsInfo)
		{
			rollNo = sifas.getProgressCardInfo().getRollNumber();
			entityId = sifas.getProgressCardInfo().getEntityId();
			programCode = sifas.getProgressCardInfo().getProgramCode();
			name = sifas.getProgressCardInfo().getName();
			branchId =sifas.getProgressCardInfo().getBranchId();
			specialization=sifas.getProgressCardInfo().getSpecializationId();
			//semesterId=sifas.getProgressCardInfo().getSemesterId();
			genAttempt=sifas.getProgressCardInfo().getGenAttempt();
			semesterSequence = sifas.getProgressCardInfo().getSemesterSequence();			
	
		headerTable.addCell(new Phrase(Integer.parseInt(entityId.substring(4,8))+programCode+branchId+semesterSequence,cellFont));
		headerTable.addCell(new Phrase(rollNo,cellFont));
		headerTable.addCell(new Phrase(name,cellFont));
		headerTable.addCell(new Phrase(genAttempt,cellFont));
		headerTable.addCell(new Phrase(specialization,cellFont));
		for(int i=1;i<=8;i++)
		{						
			if(i<=sifas.getMarksList().size())
			{
				headerTable.addCell(new Phrase(sifas.getMarksList().get(i-1).getSgpa(),cellFont));
				
			}
//			else if(i<=sifas.getMarksList().size() && sifas.getMarksList().get(i-1).getStatus().equalsIgnoreCase("FAL")){
//				headerTable.addCell(new Phrase(sifas.getMarksList().get(i-1).getSgpa(),cellFont));
//			}
//			else if(i<=sifas.getMarksList().size() && sifas.getMarksList().get(i-1).getStatus().equalsIgnoreCase("REM")){
//				headerTable.addCell(new Phrase(sifas.getMarksList().get(i-1).getSgpa(),cellFont));
//			}
			else {
				headerTable.addCell(new Phrase(" ",cellFont));	
			}
		}

			headerTable.addCell(new Phrase(sifas.getMarksList().get((sifas.getMarksList().size())-1).getCgpa(),cellFont));
				
		}
		
		document.add(headerTable);	
		document.close();
		result = "true";
		}catch(Exception e){		
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
			System.out.println("some exception in log entry "+e);
		}
		return result;
	}

	
}
