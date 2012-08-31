/** $URL:
* Licensed under the Educational Community License, Version 1.0 (the "License");
* you may not use this file except in compliance with the License.
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
**********************************************************************************/
package in.ac.dei.edrp.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Locale;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

/**
 * @author On Demand Examination Team
*/
public class ReadXLSheet {
 
	public void init(String filePath) {
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(new File(filePath));
			contentReading(fs);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
 
	//Returns the Headings used inside the excel sheet
	public void getHeadingFromXlsFile(Sheet sheet) {
		int columnCount = sheet.getColumns();
		for (int i = 0; i < columnCount; i++) {
			System.out.println("contents of cell of excel sheet of ReadXLSheet"+sheet.getCell(i, 0).getContents());
		}
	}
 
	public void contentReading(InputStream fileInputStream) {
		WorkbookSettings ws = null;
		Workbook workbook = null;
		Sheet s = null;
		Cell rowData[] = null;
		int rowCount = '0';
		@SuppressWarnings("unused")
		int columnCount = '0';
		int totalSheet = 0;
		@SuppressWarnings("unused")
		String query = "";
		@SuppressWarnings("unused")
		String query1 = "";
		DatabaseConnection DBConnection = new DatabaseConnection();
		try {
			Connection con=DBConnection.createconnection();
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
 
			//Getting Default Sheet i.e. 0
			s = workbook.getSheet(0);
 
			//Reading Individual Cell
			getHeadingFromXlsFile(s);
 
			//Total Total No Of Rows in Sheet, will return you no of rows that are occupied with some data
			System.out.println("Total Rows inside Sheet:" + s.getRows());
			rowCount = s.getRows();
 
			//Total Total No Of Columns in Sheet
			System.out.println("Total Column inside Sheet:" + s.getColumns());
			columnCount = s.getColumns();
			String university_id="0001";
			String entity_id="00010001";
			String program_id="0001001";
			String branch_code="017";
			
			PreparedStatement pss=con.prepareStatement("delete from STUDENT_CALL_LIST");
			pss.executeUpdate();
			
			PreparedStatement pss1=con.prepareStatement("delete from ENTITY_STUDENT");
			pss1.executeUpdate();
			
			PreparedStatement pss2=con.prepareStatement("delete from STUDENT_REGISTRATION");
			pss2.executeUpdate();
			
			PreparedStatement pss3=con.prepareStatement("delete from CALL_CUT_OFF");
			pss3.executeUpdate();
			
			PreparedStatement pss4=con.prepareStatement("delete from PROGRAM_COMPONENTS");
			pss4.executeUpdate();
			
			PreparedStatement pss5=con.prepareStatement("delete from ADDRESSES_MASTER");
			pss5.executeUpdate();
			
			PreparedStatement pss6=con.prepareStatement("delete from STUDENT_SPECIAL_WEIGHTAGE");
			pss6.executeUpdate();
			//Reading Individual Row Content
			
			
			for (int i = 1; i < rowCount; i++) {
				//Get Individual Row
				rowData = s.getRow(i);
				
					
					String category=rowData[0].getContents().substring(0, 2);
					String cos_value=rowData[0].getContents().substring(0, 4);
					String registration_number=rowData[1].getContents();
					String name=rowData[2].getContents();
//					String dob=rowData[3].getContents();
					String father_name=rowData[4].getContents();
					String city=rowData[5].getContents();
					String[] marks=new String[4];
					String[] total=new String[4];
					double[] per=new double[4];
					
					marks[0]=rowData[6].getContents();
					total[0]=rowData[7].getContents();
					per[0]=(Double.parseDouble(marks[0])/Double.parseDouble(total[0]))*100;
					
					marks[1]=rowData[8].getContents();
					total[1]=rowData[9].getContents();
					per[1]=(Double.parseDouble(marks[1])/Double.parseDouble(total[1]))*100;
					
					marks[2]=rowData[10].getContents();
					total[2]=rowData[11].getContents();
					per[2]=(Double.parseDouble(marks[2])/Double.parseDouble(total[2]))*100;
					String[] comp=new String[]{"HS","IN","MT","PM"};
					marks[3]=String.valueOf(Integer.parseInt(marks[0])+Integer.parseInt(marks[1])+Integer.parseInt(marks[2]));
					total[3]=String.valueOf(Integer.parseInt(total[0])+Integer.parseInt(total[1])+Integer.parseInt(total[2]));
					per[3]=(Double.parseDouble(marks[3])/Double.parseDouble(total[3]))*100;
					String upb=rowData[13].getContents();
					String rei=rowData[14].getContents();
					String staff=rowData[15].getContents();
					@SuppressWarnings("unused")
					String sat=rowData[16].getContents();
					
					String student_id=rowData[22].getContents();
					
					for(int len=0;len<marks.length;len++){
						PreparedStatement ps=con.prepareStatement("insert into STUDENT_CALL_LIST " +
								"(entity_id,program_id,branch_code,registration_number,component_id,marks_percentage,marks_obtained,total_marks,board_id" +
						") values (?,?,?,?,?,?,?,?,?)");
						ps.setString(1,entity_id);
						ps.setString(2, program_id);
						ps.setString(3, branch_code);
						ps.setString(4,registration_number);
						
						ps.setString(5,comp[len]);
						ps.setDouble(6,per[len]);
							ps.setString(7,marks[len]);
							ps.setString(8, total[len]);
						
						if(upb.equalsIgnoreCase("y")){
							ps.setString(9, "01");
						}
						else{
							ps.setString(9, "02");
						}
						int x=ps.executeUpdate();
						if(x>0){
							System.out.println("updated "+registration_number);
						}
					}//for loop ends here!
					
					System.out.println("******************************************");
//					System.out.println("dob is "+dob);
					System.out.println("******************************************");
					PreparedStatement ps1=con.prepareStatement("insert into ENTITY_STUDENT (university_id," +
							"student_id,first_name,date_of_birth,category_code,gender,father_first_name) values " +
							"(?,?,?,?,?,?,?)");
					ps1.setString(1,university_id);
					ps1.setString(2,student_id);
					ps1.setString(3,name);
					ps1.setString(4,"1989-05-07");
					ps1.setString(5,category);
					ps1.setString(6,"Male");
					ps1.setString(7,father_name);
					@SuppressWarnings("unused")
					int x=ps1.executeUpdate();
										
					PreparedStatement ps2=con.prepareStatement("insert into ADDRESSES_MASTER (user_type,user_id,city) values " +
							"(?,?,?)");
					ps2.setString(1,"STD");
					ps2.setString(2,student_id);
					ps2.setString(3,city);
					ps2.executeUpdate();
					
					PreparedStatement ps3=con.prepareStatement("insert into STUDENT_REGISTRATION (student_id,registration_number,cos_value) values " +
					"(?,?,?)");
			ps3.setString(1,student_id);
			ps3.setString(2,registration_number);
			ps3.setString(3,cos_value);
					ps3.executeUpdate();
					if(rei.equalsIgnoreCase("y")){
						PreparedStatement ps4=con.prepareStatement("insert into STUDENT_SPECIAL_WEIGHTAGE (university_id,program_id,registration_number,weightage_id) values " +
						"(?,?,?,?)");
				ps4.setString(1,university_id);
				ps4.setString(2,program_id);
				ps4.setString(3,registration_number);
				ps4.setString(4,"PM");
						ps4.executeUpdate();
						ps4.close();
					}
					if(staff.equalsIgnoreCase("y")){
						PreparedStatement ps4=con.prepareStatement("insert into STUDENT_SPECIAL_WEIGHTAGE (university_id,program_id,registration_number,weightage_id) values " +
						"(?,?,?,?)");
				ps4.setString(1,university_id);
				ps4.setString(2,program_id);
				ps4.setString(3,registration_number);
				ps4.setString(4,"SW");
						ps4.executeUpdate();
						ps4.close();
					}
				
					
				
				
				
			
			
		}
			workbook.close();
			String[] comp=new String[]{"HS","IN","MT","PM"};
			for(int l=0;l<comp.length;l++){
				PreparedStatement ps5=con.prepareStatement("insert into PROGRAM_COMPONENTS " +
						"(program_id,component_id,type,component_weightage,ug_pg,weightage_flag," +
						"component_criteria_flag," +
						"special_weightage_flag,board_flag,branch_code,offered_by) values " +
				"(?,?,?,?,?,?,?,?,?,?,?)");
		ps5.setString(1,program_id);
		ps5.setString(2,comp[l]);
		ps5.setString(3,"P");
		ps5.setString(4,"100");
		ps5.setString(5,"XX");
		if(l==0){
		ps5.setString(6,"n");
		ps5.setString(7,"n");
		ps5.setString(8,"n");
		ps5.setString(9,"n");
		}
		if(l==1){
			ps5.setString(6,"n");
			ps5.setString(7,"n");
			ps5.setString(8,"n");
			ps5.setString(9,"n");
		}
		if(l==2){
			ps5.setString(6,"n");
			ps5.setString(7,"y");
			ps5.setString(8,"n");
			ps5.setString(9,"y");
			}
		if(l==3){
			ps5.setString(6,"y");
			ps5.setString(7,"y");
			ps5.setString(8,"y");
			ps5.setString(9,"y");
			}
		ps5.setString(10,branch_code);
		ps5.setString(11,entity_id);
				ps5.executeUpdate();
				ps5.close();
			}
			String[] cos=new String[]{"GNMX","BCXX","SCXX","STXX"};
			double[] d=new double[]{65.00,60.00,55.00,55.00};
			for(int i=0;i<cos.length;i++){
			PreparedStatement ps6=con.prepareStatement("insert into CALL_CUT_OFF " +
					"(program_id,branch_code,offered_by,cos_value,cut_off_number) values " +
			"(?,?,?,?,?)");
			ps6.setString(1,program_id);
			ps6.setString(2,branch_code);
			ps6.setString(3,entity_id);
			ps6.setString(4,cos[i]);
			ps6.setDouble(5,d[0]);
			ps6.executeUpdate();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}