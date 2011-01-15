package in.ac.dei.edrp.server;

import in.ac.dei.edrp.client.CM_entityInfoGetter;
import in.ac.dei.edrp.client.DataBean;
import in.ac.dei.edrp.client.ReportInfoGetter;
import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.benchmarks.client.Category;
import com.ibatis.sqlmap.client.SqlMapClient;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExportService1 {

//private static List<DataBean> list = new ArrayList<DataBean>();
	Log4JInitServlet logObj = new Log4JInitServlet();
	
	List<DataBean> list = new ArrayList<DataBean>();
	String[] category=null;
	GreetingServiceImpl gsimpl=new GreetingServiceImpl();
	ReportingFunction reportfunction=new ReportingFunction();
	SqlMapClient client = SqlMapManager.getSqlMapClient();
	public List<DataBean> getD(String university_id,String entity_type,String entity_id,String program_id,String branch_id){
//		public List<DataBean> getUpdateMarks(String entity_id, String program_id) {
		// TODO Auto-generated method stub
		
		
		System.out.println("------------At server side----------");
		
		try{
			String[] detail=null;
			//String entity_id=reportfunction.getEntity_Id(entity_name, university_id);
			
			//String program_id=reportfunction.getProgram_Id(entity_id, program_name);
			
			//System.out.println(branch_name+" of this branch");
			//String branch_code=reportfunction.getBranch_Id(branch_name);
			
			String udate[]=reportfunction.getSessionDate(university_id);
			
			//System.out.println("Entity_id in service: "+reportfunction.getEntity_Id(entity_name, university_id));
			//System.out.println("program id in service: "+reportfunction.getProgram_Id(entity_id, program_name));
			//System.out.println("branch_code in service: "+reportfunction.getBranch_Id(branch_name));
			

			ReportInfoGetter export1=new ReportInfoGetter();
			export1.setProgram_id(program_id);
			export1.setEntity_id(entity_id);
			export1.setBranch_code(branch_id);
			
			export1.setOffered_by(entity_id);
			//setting start_date and end_date
			export1.setStart_date(udate[0]);
			export1.setEnd_date(udate[1]);
			
			category=reportfunction.getCategoryCos(export1);
			
			for(int catlen=0;catlen<category.length;catlen++){
	
				export1.setCos_value(category[catlen]);
				export1.setCalled("y");
			List<ReportInfoGetter> li=client.queryForList("getStudentTestNumber",export1);	
	
			int slnum=0;
			for(ReportInfoGetter user:li){
		
		//System.out.println(user.getRegistration_number()+" | ");
		//System.out.print(user.getTest_number()+" | ");
		//System.out.print(user.getSum_computed_marks()+" | ");
		
		String[] detailData=reportfunction.getUserDetail(user.getRegistration_number(),export1);
		detail=detailData;
		
		
			slnum++;
			list.add(new DataBean(slnum,user.getRegistration_number(),user.getTest_number(),detailData,user.getSum_computed_marks()));

	}
			}//for loop
			if(!(list.size()==0)){
				reportfunction.updateControlReport(export1, "E", "T");
			}
			//reportfunction.updateControlReport(entity_id, program_id, branch_code, "F","T",udate);
			}catch(Exception e){
				logObj.logger.info("Exception in getting list for external call list: "+e.getMessage()); 
		}
			
			
		return list;
	}
	
	

		

	

	public ByteArrayOutputStream generateExcelReport(String university_id,String entity_type,String entity_id,String program_id,String branch_id) throws IOException, WriteException {
		/* Stream containing excel data */
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
		ReportingFunction reportFunction=new ReportingFunction();
		
		/* Create Excel WorkBook and Sheet */
		WritableWorkbook workBook = Workbook.createWorkbook(outputStream);
		WritableSheet sheet = workBook.createSheet("User List", 0);

		/* Generates Headers Cells */
		WritableFont headerFont = new WritableFont(WritableFont.TAHOMA, 8, WritableFont.BOLD);
		WritableCellFormat headerCellFormat = new WritableCellFormat(headerFont);
		headerCellFormat.setBackground(Colour.PALE_BLUE);
		
		String entity_description=reportFunction.getEntity_description(university_id, entity_type);
		String entity_name=reportFunction.getEntity_Name(entity_id, university_id);
		String program_name=reportFunction.getProgram_Name(entity_id, program_id);
		String branch_name=reportFunction.getBranch_Name(university_id,branch_id);
		
		list=getUsers(university_id,entity_type,entity_id,program_id,branch_id);

		sheet.addCell(new Label(5, 1, "DayalBag Education Institute- Agra(282005)", headerCellFormat));
		sheet.addCell(new Label(5, 2, "List of Students for generating call list", headerCellFormat));
		sheet.addCell(new Label(5, 3, "Institute Name", headerCellFormat));
		sheet.addCell(new Label(6, 3, entity_name, headerCellFormat));
		sheet.addCell(new Label(5, 4, "Program Name", headerCellFormat));
		sheet.addCell(new Label(6, 4, program_name, headerCellFormat));
		sheet.addCell(new Label(5, 5, "Branch Name", headerCellFormat));
		sheet.addCell(new Label(6, 5, branch_name, headerCellFormat));
		
		Iterator i1=list.iterator();
		i1.hasNext();
			DataBean db=(DataBean)i1.next();
			String name=db.getRegistrationNumber();
			sheet.addCell(new Label(1, 7, "Registration Number", headerCellFormat));
			sheet.addCell(new Label(2, 7, "Test Number", headerCellFormat));
			//System.out.print(name+" | ");
			
			String[] det=db.getDetail();
			int dlen=3;
			for(int l=0;l<det.length;l++){
				switch(l){
				case 0:{
				sheet.addCell(new Label(dlen, 7, "Name", headerCellFormat));
				break;
				}
				case 1:{
					sheet.addCell(new Label(dlen, 7, "Date of Birth", headerCellFormat));
					break;
					}
				case 2:{
					sheet.addCell(new Label(dlen, 7, "category", headerCellFormat));
					break;
					}
				case 3:{
					sheet.addCell(new Label(dlen, 7, "gender", headerCellFormat));
					break;
					}
				case 4:{
					sheet.addCell(new Label(dlen, 7, "city", headerCellFormat));
					break;
					}
				}//switch1 ends
				dlen++;
			}
			
			double comp=db.getTotalMarks();
			sheet.addCell(new Label(dlen, 7, "ComputedMarks", headerCellFormat));
			
			
		//}

		/* Generates Data Cells */
		WritableFont dataFont = new WritableFont(WritableFont.TAHOMA, 8);
		WritableCellFormat dataCellFormat = new WritableCellFormat(dataFont);
		int currentRow = 8;
		
		
		Iterator i=list.iterator();
		String[][] data1=null;
		String[] det1=null;
		int cate=0;
		while(i.hasNext()){
			DataBean db1=(DataBean)i.next();
			if(db1.getSlnum()==1){
				sheet.addCell(new Label(1, currentRow,""+category[cate],dataCellFormat));
				cate++;
				currentRow++;
			}
			sheet.addCell(new Label(1, currentRow, String.valueOf(db1.getSlnum()),dataCellFormat));
			sheet.addCell(new Label(1, currentRow, db1.getRegistrationNumber(),dataCellFormat));
			sheet.addCell(new Label(2, currentRow, db1.getTestNumber(),dataCellFormat));
			
			
			data1=db1.getCompData();
			
			det1=db1.getDetail();
			int dlen1=3;
			for(int l1=0;l1<det.length;l1++){
				
				sheet.addCell(new Label(dlen1, currentRow, det1[l1],dataCellFormat));
				dlen1++;
			}
			
			double comp1=db1.getTotalMarks();
			sheet.addCell(new Label(dlen1, currentRow,String.valueOf(comp1),dataCellFormat));
			
			currentRow++;
			
		}
		/* Write & Close Excel WorkBook */
		workBook.write();
		workBook.close();

		return outputStream;
	}
	
	public List<DataBean> getUsers(String university_id,String entity_type,String entity_id,String program_id,String branch_id) {
		return getD(university_id,entity_type,entity_id,program_id,branch_id);
	}
	

}
