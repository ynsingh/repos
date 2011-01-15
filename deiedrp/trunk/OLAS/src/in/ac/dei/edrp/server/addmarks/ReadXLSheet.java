/** $URL:
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **********************************************************************************/
package in.ac.dei.edrp.server.addmarks;

import in.ac.dei.edrp.client.ReportInfoGetter;
import in.ac.dei.edrp.client.addmarks.ReadXLBean;
import in.ac.dei.edrp.server.ExportService2;
import in.ac.dei.edrp.server.ReportingFunction;
import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import com.ibatis.sqlmap.client.SqlMapClient;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

/**
 * @author On Demand Examination Team
 */
public class ReadXLSheet {
	SqlMapClient client = SqlMapManager.getSqlMapClient();
	ReadXLBean insertion = new ReadXLBean();
	Log4JInitServlet logObj = new Log4JInitServlet();
	
	
	public List<Integer> init(String filePath, ReportInfoGetter addMarksReport) {
		FileInputStream fs = null;
		
		List<Integer> list=new ArrayList<Integer>();
		try {
			fs = new FileInputStream(new File(filePath));
			list=contentReading(fs,addMarksReport);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				fs.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	//Returns the Headings used inside the excel sheet
	public void getHeadingFromXlsFile(Sheet sheet) {
		int columnCount = sheet.getColumns();
		for (int i = 0; i < columnCount; i++) {
			System.out.println("contents of cell of excel sheet of ReadXLSheet"+sheet.getCell(i, 0).getContents());
		}
	}

	public List<Integer> contentReading(InputStream fileInputStream, ReportInfoGetter addMarksReport) {
		WorkbookSettings ws = null;
		Workbook workbook = null;
		Sheet s = null;
		Cell rowData[] = null;
		int rowCount = '0';
		@SuppressWarnings("unused")
		int columnCount = '0';
		int totalSheet = 0;
		
		int count=0;
		List<Integer> list=new ArrayList<Integer>();
		int totalrecord=0;
		try {
			ws = new WorkbookSettings();
			ws.setLocale(new Locale("en", "EN"));
			workbook = Workbook.getWorkbook(fileInputStream, ws);
			totalSheet = workbook.getNumberOfSheets();
			if(totalSheet > 0) {
				System.out.println("Total Sheets Found=" + totalSheet);
				for(int j=0;j<totalSheet ;j++) {
					System.out.println("Sheet Name:" + workbook.getSheet(j).getName());
				}
			}
			//Gets Default Sheet i.e. 0
			s = workbook.getSheet(0);
			//Reads Individual Cell
			getHeadingFromXlsFile(s);
			//Total no of Rows in Sheet, will return you no of rows that are occupied with some data
			System.out.println("Total Rows inside Sheet:" + s.getRows());
			rowCount = s.getRows();
			//Total no of Columns in Sheet
			System.out.println("Total Column inside Sheet:" + s.getColumns());
			columnCount = s.getColumns();
			
					
			ExportService2 exportService2=new ExportService2();
			
			
			String[] headings=exportService2.getHeadLines(addMarksReport);
			int totalColumn=(headings.length*2)+3;
			
			totalrecord=(rowCount-8)*(headings.length);
			System.out.println(totalColumn+" , and "+s.getRow(7).length);
			if(totalColumn==s.getRow(7).length){
				
			//Reads Individual Row Content
			ReportInfoGetter addMarksinDB=addMarksReport;
						
			for (int i = 8; i < rowCount; i++) {
				int noOfComponent=0;
				//Gets Individual Row
				rowData = s.getRow(i);
				String registrationNumber=rowData[1].getContents();
				String testNumber=rowData[2].getContents();
				addMarksinDB.setRegistration_number(registrationNumber);
				addMarksinDB.setTest_number(testNumber);
//				boolean b=true;
				boolean b=false;
				for(ReportInfoGetter sessionDate:validateTestNumber(addMarksinDB)){
					addMarksinDB.setStart_date(sessionDate.getStart_date());
					addMarksinDB.setEnd_date(sessionDate.getEnd_date());
					addMarksinDB.setSum_actual_computed_marks(sessionDate.getSum_actual_computed_marks());
					addMarksinDB.setCos_value(sessionDate.getCos_value());
					b=true;
				}
//				addMarksinDB.setStart_date("2010/09/22");
//				addMarksinDB.setEnd_date("2011/05/22");
				
				int temp=3;
				double sum=addMarksinDB.getSum_actual_computed_marks();
				for(int length=0;length<headings.length;length++){
				addMarksinDB.setComponent_id(getEvaluationId(headings[length],addMarksReport));
				if(checkIfDouble(rowData[temp].getContents()) && (Double.parseDouble(rowData[temp].getContents())>=0)){
					
				double marks=Double.parseDouble((rowData[temp].getContents()));
				
				String attended=rowData[++temp].getContents();
				System.out.println("Marks: "+marks);
				addMarksinDB.setMarks_percentage(marks);
				addMarksinDB.setAttendance(attended);
				temp++;
				if(validateExcelRecords(headings[length],marks,attended,addMarksinDB)&& (b)){
				if(!checkDuplicacyForRegistrationNumber(addMarksinDB)){
						client.update("insertStudentFinalMarks", addMarksinDB);
						System.out.println("coming inside a if insertStudentFinalMarks ");
						count++;
						sum=sum+marks;
						noOfComponent++;
					}
					else{
						client.update("updateStudentFinalMarks", addMarksinDB);
						count++;
						sum=sum+marks;
						noOfComponent++;
						System.out.println("coming inside a if insertStudentFinalMarks ");
					}//check duplicacy else ends
				}//check validate if ends
				}//checkIfDouble
				else{
					logObj.logger.info("Improper value for: "+addMarksinDB.getRegistration_number());
					System.out.println("value is: "+rowData[temp].getContents());
					temp=temp+2;
					System.out.println("coming inside a if insertStudentFinalMarks ");
				}//checkIf Double ends
			}//first if closed
				if(b){
				if(checkDuplicateTestNumberInStudentFinalList(addMarksinDB)){
					if(noOfComponent==headings.length){
					addMarksinDB.setTotal_marks(sum);
					System.out.println("Running inside update");
					client.insert("updateStudentFinalMeritList", addMarksinDB);
					}//if no of components ends
					
				}//if check duplicate ends
				else{
					addMarksinDB.setTotal_marks(sum);
					System.out.println("Running inside insert:"+addMarksinDB.getTest_number());
					client.update("insertStudentFinalMeritList", addMarksinDB);
				}//if check duplicate ends
				}//if b ends
				
		}//for loop ends for row
			}//if total sheet length closed
			workbook.close();			
		} catch (IOException e) {                                                                                                                                          
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		}catch(Exception e){
			System.out.println("Exception is "+e.getMessage());
			logObj.logger.info("Problem while inserting in data base");
		}//end catch
		list.add(totalrecord);
		list.add(count);
		return list;
	}//end contentReading

	public String getEvaluationId(String evaluationComponent,
			ReportInfoGetter addMarksReport) {
		// TODO Auto-generated method stub
		String evaluationId="";
		try{
			
			addMarksReport.setComponent_description(evaluationComponent);
			List<ReportInfoGetter> existReg=client.queryForList("getFinalMeritEvaluationId", addMarksReport);
			for(ReportInfoGetter exist: existReg){
				evaluationId=exist.getComponent_id();
				System.out.println(evaluationId);
			}
		}catch(Exception e){
			logObj.logger.info("Problem while getting evaluation id in data base");
		System.out.println("Coming inside check duplicacy "+e.getMessage());	
		}
		return evaluationId;
	}
	
	public boolean checkDuplicacyForRegistrationNumber(
			ReportInfoGetter addMarksinDB) {
		// TODO Auto-generated method stub
		boolean existRegistration=false;
		try{
			List<ReportInfoGetter> existReg=client.queryForList("checkDuplicacyOfRegInStduentFinal", addMarksinDB);
			for(ReportInfoGetter exist: existReg){
				System.out.println("Coming inside");
				if(exist.getCount()>=1){
				existRegistration=true;
				}
			}
			
		}catch(Exception e){
			logObj.logger.info("Problem while inserting in data base"+addMarksinDB.getRegistration_number());
		System.out.println("Coming inside check duplicacy "+e.getMessage());	
		}
		
		return existRegistration;
	}
	
	public boolean validateExcelRecords(String evaluationId,double marks,String attended,ReportInfoGetter validateMarks){
		boolean validateExcel=true;
		try{
		if(attended.length()==1 && (attended.equalsIgnoreCase("P")||attended.equalsIgnoreCase("A"))){
		List<ReportInfoGetter> validateRecord=client.queryForList("getFinalMeritEvaluationId", validateMarks);
			for(ReportInfoGetter validate: validateRecord){
				System.out.println(validate.getTotal_marks());
				if(marks>validate.getTotal_marks()){
					validateExcel=false;
				}
			}
		}
		else{
			validateExcel=false;
		}
		}catch(Exception e){
			logObj.logger.info("Problem while inserting in data base"+validateMarks.getRegistration_number());
			System.out.println("Coming inside Exceptione "+e.getMessage());
		}
		return validateExcel;
	}
	
	public List<ReportInfoGetter> validateTestNumber(ReportInfoGetter validateTest){
		
		List<ReportInfoGetter> list=new ArrayList<ReportInfoGetter>();
		System.out.println(validateTest.getRegistration_number());
		validateTest.setCalled("y");
		try{
		list=client.queryForList("getStudentSessionDate",validateTest);
		
		}catch(Exception e){
			logObj.logger.info("Problem while inserting in data base");
			System.out.println("Coming inside Exceptione "+e.getMessage());
		}
		return list;
	}
	
	public boolean checkIfDouble(String in) {
         try {

            Double.parseDouble(in);
                  
        } catch (NumberFormatException ex) {
        	logObj.logger.info("Enter number values");
            return false;
            
        }
        return true;
   	}
	
	public boolean checkDuplicateTestNumberInStudentFinalList(ReportInfoGetter validateFinalList){
		boolean b=false;
		System.out.println(validateFinalList.getTest_number());
		try{
			List<ReportInfoGetter> studentList=client.queryForList("getStudentinStudentFinal",validateFinalList);
			for(ReportInfoGetter studentExist:studentList){
				if(studentExist.getCount()>=1){
					b=true;
				}
			}
			
			}catch(Exception e){
				logObj.logger.info("Problem while executing in checkDulicate in ReadXL");
				System.out.println("Coming inside Exceptione "+e.getMessage());
			}
			
		return b;
	}
}//end class