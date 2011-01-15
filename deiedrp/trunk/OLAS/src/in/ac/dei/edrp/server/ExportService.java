package in.ac.dei.edrp.server;

/*
 * Copyright 2010 Deepak Pandey
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

import in.ac.dei.edrp.client.CM_entityInfoGetter;
import in.ac.dei.edrp.client.DataBean;
import in.ac.dei.edrp.client.ReportInfoGetter;
import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * 
 * @author <a href="mailto:deepak2rok@gmail.com>Deepak Pandey</a>
 * @version 1.0
 */
public class ExportService {

	Log4JInitServlet logObj = new Log4JInitServlet();
	
	private static List<DataBean> list = new ArrayList<DataBean>();
	static  String[] category=null;
	//GreetingServiceImpl gsimpl=new GreetingServiceImpl();
	SqlMapClient client = SqlMapManager.getSqlMapClient();
	ReportingFunction reportfunction=new ReportingFunction();
	public  List<DataBean> getD(String university_id,String entity_type,String entity_id,String program_id,String branch_id){
//		public List<DataBean> getUpdateMarks(String entity_id, String program_id) {
		// TODO Auto-generated method stub
		
		
		System.out.println("------------At server side----------");
		List<DataBean> list=new ArrayList<DataBean>();
		double cm=0.0;
		String s[][]=null;
		String[] detail=null;
		
		try{
			//String entity_id=reportfunction.getEntity_Id(entity_name, university_id);
			
			//String program_id=reportfunction.getProgram_Id(entity_id, program_name);
			
			//System.out.println(branch_name+" of this branch");
			//String branch_code=reportfunction.getBranch_Id(branch_name);
			
			String[] udate=reportfunction.getSessionDate(university_id);
			
			//System.out.println("Entity_id in service: "+reportfunction.getEntity_Id(entity_name, university_id));
			//System.out.println("program id in service: "+reportfunction.getProgram_Id(entity_id, program_name));
			//System.out.println("branch_code in service: "+reportfunction.getBranch_Id(branch_name));
			
			ReportInfoGetter export=new ReportInfoGetter();
			export.setProgram_id(program_id);
			export.setEntity_id(entity_id);
			export.setBranch_code(branch_id);
			
			export.setOffered_by(entity_id);
			//setting session start date and end date
			export.setStart_date(udate[0]);
			export.setEnd_date(udate[1]);
			System.out.println(udate[0]+udate[1]);
			//System.out.println("coming here "+entity_id+program_id+branch_id);
			category=reportfunction.getCategoryCos(export);
			
			System.out.println("Coming here 1"+category.length);
			
	for(int catlen=0;catlen<category.length;catlen++){
		System.out.println("Coming here 2"+category[catlen]);
		int	slnum=0;
		export.setCos_value(category[catlen]);
		export.setStatus("Eligible");
		
	List<ReportInfoGetter> li2=client.queryForList("getExcelRegistrationNumber",export);
	System.out.println("Coming here 3"+li2.size());
	
	double call=0.0;
	export.setOffered_by(entity_id);
	List<ReportInfoGetter> li3=client.queryForList("getCallMerit",export);
	//System.out.println("Coming here 4"+li3.size());
	for(ReportInfoGetter callmarks:li3){
		call=callmarks.getCut_off_number();
	}
	
	for(ReportInfoGetter reg_excel:li2){
		//System.out.println("Coming here 4");
	String name = reg_excel.getRegistration_number();

		
	cm=reg_excel.getSum_computed_marks();
	//System.out.println(name+" and marks:"+cm+" and merit: "+call);
	if(cm>=call){
	String[][] data=reportfunction.getFullyComputedmarks(name,export,udate);
	
	String[] detailData=reportfunction.getUserDetail(name,export);
	s=data;
	detail=detailData;
	System.out.println("Adding ");
	slnum++;
	//System.out.println(cm);
	if(category[catlen].substring(0, 2).equalsIgnoreCase("XX")){
		reportfunction.updateCalledStudentTest(name,export,"n",udate);
	}
	else{
		reportfunction.updateCalledStudentTest(name,export,"y",udate);
	}
//	list.add(new DataBean(slnum,name,detail,s, cm,test));
	list.add(new DataBean(slnum,name,detail,s, cm));
	if(!(list.size()==0)){
		reportfunction.updateControlReport(export, "I", "C");
	}//if list.size() ends
	}//if cm>call ends
	else{
		reportfunction.updateCalledStudentTest(name,export,"n",udate);	
	}
	//System.out.println("coming till last");
//	}//if validate ends

}// second loop registration number ends

	}//category loop ends
	
		
//	gsimpl.updateControlReport(entity_id, program_id, branch_code, "I","C");
} catch (Exception e) {
	logObj.logger.info("Exception in getting list for internal call list: "+e.getMessage()); 
}// catch ends
Iterator i=list.iterator();
while(i.hasNext()){
	DataBean db=(DataBean)i.next();
	//String name=db.getRegistrationNumber();
	//System.out.print(name+" | ");
	String[][] data=db.getCompData();
	String[] det=db.getDetail();
	for(int l=0;l<det.length;l++){
		//System.out.print(det[l]+" | ");
	}
	for(int i1=0;i1<data.length;i1++){
		for(int j=0;j<3;j++){
			//System.out.print(data[i1][j]+" | ");
		}
	}
	double comp=db.getTotalMarks();
	
	System.out.print(comp+" | ");
	//System.out.print(db.getTestNumber()+" | ");
	System.out.println(" | ");
}

		return list;
}
	

	


	public ByteArrayOutputStream generateExcelReport(String university_id, String entity_type, String entity_id, String program_id, String branch_id) throws IOException, WriteException {
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

			
		Iterator i1=list.iterator();
		int damaxLen=0;
		while(i1.hasNext()){
			DataBean db=(DataBean)i1.next();
			String[][] datalen=db.getCompData();
			if(damaxLen<datalen.length){
				damaxLen=datalen.length;
				//System.out.println("Maximum Length : "+damaxLen);
			}
		}
		
		sheet.addCell(new Label(5, 1, university_id+"DayalBag Education Institute- Agra(282005)", headerCellFormat));
		sheet.addCell(new Label(5, 2, "List of Students for generating call list", headerCellFormat));
		sheet.addCell(new Label(5, 3, "Institute Name", headerCellFormat));
		sheet.addCell(new Label(6, 3, entity_name, headerCellFormat));
		sheet.addCell(new Label(5, 4, "Program Name", headerCellFormat));
		sheet.addCell(new Label(6, 4, program_name, headerCellFormat));
		sheet.addCell(new Label(5, 5, "Branch Name", headerCellFormat));
		sheet.addCell(new Label(6, 5, branch_name, headerCellFormat));
		
		
		i1=list.iterator();
		i1.hasNext();
			DataBean db=(DataBean)i1.next();
			String name=db.getRegistrationNumber();
			sheet.addCell(new Label(1, 7, "Serial Number", headerCellFormat));
			sheet.addCell(new Label(2, 7, "Registration Number", headerCellFormat));
			//sheet.addCell(new Label(3, 7, "Test Number", headerCellFormat));
			//System.out.println("Coming before heading");
			String[][] data=db.getCompData();
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
			int len=dlen;
			for(int i2=0;i2<damaxLen;i2++){
				for(int j=0;j<4;j++){
					switch(j){
					case 0:{
					sheet.addCell(new Label(len, 7, "Component", headerCellFormat));
					break;
					}
					case 1:{
						sheet.addCell(new Label(len, 7, "Board", headerCellFormat));
						break;
						}
					case 2:{
						sheet.addCell(new Label(len, 7, "Percentage", headerCellFormat));
						break;
						}
					case 3:{
						sheet.addCell(new Label(len, 7, "Weightage", headerCellFormat));
						break;
						}
					}
					len++;
				}
			}
			double comp=db.getTotalMarks();
			sheet.addCell(new Label(len, 7, "TotalMarks", headerCellFormat));
			
			//System.out.println("Coming after heading");
			
		//}

		/* Generates Data Cells */
		WritableFont dataFont = new WritableFont(WritableFont.TAHOMA, 8);
		WritableCellFormat dataCellFormat = new WritableCellFormat(dataFont);
		int currentRow =8;
		/*for (DataBean user : getUsers()) {
			sheet.addCell(new Label(1, currentRow, user.getRegistrationNumber(),dataCellFormat));
			sheet.addCell(new Label(2, currentRow, user.getFirstName(),dataCellFormat));
			currentRow++;
		}*/
		
		Iterator i=list.iterator();
		String[][] data1=new String[damaxLen][];
		String[] det1=null;
		int catval=0;
		while(i.hasNext()){
			DataBean db1=(DataBean)i.next();
			if(db1.getSlnum()==1){
				sheet.addCell(new Label(1, currentRow,"List according to cos: "+category[catval],dataCellFormat));
				currentRow++;
				catval++;
			}
			sheet.addCell(new Label(1, currentRow,String.valueOf(db1.getSlnum()),dataCellFormat));
			sheet.addCell(new Label(2, currentRow, db1.getRegistrationNumber(),dataCellFormat));
			//sheet.addCell(new Label(3, currentRow, db1.getTestNumber(),dataCellFormat));
			
			
			data1=db1.getCompData();
			
			det1=db1.getDetail();
			int dlen1=3;
			for(int l1=0;l1<det.length;l1++){
				
				sheet.addCell(new Label(dlen1, currentRow, det1[l1],dataCellFormat));
				dlen1++;
			}
			int len1=dlen1;
			for(int i3=0;i3<data1.length;i3++){
				for(int j=0;j<4;j++){
					
					sheet.addCell(new Label(len1, currentRow, data1[i3][j],dataCellFormat));
					len1++;
				}
			}
			if(data1.length<damaxLen){
				for(int i3=data1.length;i3<damaxLen;i3++){
					for(int j=0;j<4;j++){
						sheet.addCell(new Label(len1, currentRow,"",dataCellFormat));
						len1++;
					}
				}
			}
			double comp1=db1.getTotalMarks();
			sheet.addCell(new Label(len1, currentRow,String.valueOf(comp1),dataCellFormat));
			
			currentRow++;
						
		}
		
		/* Write & Close Excel WorkBook */
		workBook.write();
		workBook.close();

		return outputStream;
	}
	
	public List<DataBean> getUsers(String university_id,String entity_type,String entity_name,String program_name,String branch_name) {
		return getD(university_id,entity_type,entity_name,program_name,branch_name);
	}

	
//	private void updateControlReport(String entity_id, String program_id,
//			String branch_code, String flag) {
//		// TODO Auto-generated method stub
//		System.out.println("Coming here!!");
//		PreparedStatement ps=null;
//		try{
//		ReportInfoGetter updatecontrol=new ReportInfoGetter();
//		updatecontrol.setEntity_id(entity_id);
//		updatecontrol.setProgram_id(program_id);
//		updatecontrol.setBranch_code(branch_code);
//		updatecontrol.setFlag_status(flag);
//		client.update("updateControlReport");
//		
//			
//		}catch(Exception e){
//			System.out.println("Exception in update control report: "+e);
//		}
//	}
}