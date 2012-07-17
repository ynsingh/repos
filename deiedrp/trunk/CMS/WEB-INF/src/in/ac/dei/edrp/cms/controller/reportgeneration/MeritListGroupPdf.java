package in.ac.dei.edrp.cms.controller.reportgeneration;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentInfoGetter;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

public class MeritListGroupPdf extends AbstractPdfView{
	@Override
	protected void buildPdfDocument(Map map, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<StudentInfoGetter> checkList = (List<StudentInfoGetter>) map.get("checkList");
		
		String sep = System.getProperty("file.separator");
		String path = this.getServletContext().getRealPath("/")+ "MeritListOnGroupReport";
		File file = new File(path);
		file.mkdir();
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
		String combination=request.getParameter("combination");
		String entityCode=request.getParameter("entityId").substring(6);
		
		if((Integer.parseInt(semester)%2)==0){
		 report_session=(Integer.parseInt((sem_st_date.substring(0, 4)))-1) + "-"+ headerData.getSemesterEndDate().substring(2, 4);
		}
		else
			{ report_session=  headerData.getSemesterEndDate().substring(0, 4)+ "-"+(Integer.parseInt((sem_st_date.substring(2, 4)))+1);	
			}
			System.out.println(sem_st_date+sem_end_date+report_session);
		
		Font cellFont = new Font(Font.HELVETICA,8);
		Font cellFont1 = new Font(Font.HELVETICA,8,Font.BOLD);
		
		writer = PdfWriter.getInstance(document, new FileOutputStream(path+sep+ program + "-" + branch + "-"
				+ specialization+"-"+semester+"_"+sem_st_date+"_"+report_session+"_"+combination+".pdf"));
		
	
	
		float[] colsWidth = {1.0f,0.5f,1.0f,3.0f,0.5f};
		
		PdfPTable table = new PdfPTable(colsWidth);	
		table.setWidthPercentage(100);
		table.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cell = new PdfPCell(); 
		
		Phrase headerText = new Phrase("EXAMINATION DEPARTMENT \n DAYALBAGH EDUCATIONAL INSTITUTE \n DAYALBAGH,AGRA-282005",new Font(Font.HELVETICA,10,Font.BOLD));
		Paragraph paragraph = new Paragraph(9);
		paragraph.add(headerText);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		
		Phrase heading = new Phrase("LIST OF PASSED CANDIDATES IN ORDER OF MERIT "+combination+" EXAM OF "+report_session,cellFont1);      
		
	
		
		document.setPageSize(PageSize.A4.rotate());
		document.open();		
		document.add(paragraph);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		document.add(heading);
		document.add(Chunk.NEWLINE);
		
		cell = new PdfPCell(new Phrase("Class",cellFont1));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("#",cellFont1));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Roll#",cellFont1));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Name",cellFont1));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Total",cellFont1));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		int lp=5;
		while(lp>0)
		{
		cell = new PdfPCell(new Phrase(" ",cellFont));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		lp--;
		}
		
		
		for(StudentInfoGetter student:checkList)
		{	
			int j=(checkList.indexOf(student))+1;
			cell = new PdfPCell(new Phrase(entityCode+student.getProgramCode()+student.getBranchId()+semester,cellFont));			
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(""+j,cellFont));
			table.addCell(cell);			
			System.out.println("roll "+student.getRollNumber());
			cell = new PdfPCell(new Phrase(student.getRollNumber(),cellFont));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(student.getStudentName().toUpperCase(),cellFont));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(student.getSwitchNumber(),cellFont));			
			table.addCell(cell);
			
		}
		
	//	table.setLockedWidth(false);
		document.add(table);
		document.close();		
	}

}
