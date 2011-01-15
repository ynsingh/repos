package in.ac.dei.edrp.server;

import in.ac.dei.edrp.client.CMaddMarksInfoGetter;
import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;

import java.sql.*;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class Idgetter {
	
	SqlMapClient client = SqlMapManager.getSqlMapClient();
	
//	Connection con=null;
	Log4JInitServlet logObj=new Log4JInitServlet();
//	/*
//	 * For database connection
//	 */
//	public Connection createConnection(){
//		try{
//		Class.forName("com.mysql.jdbc.Driver"); // Loading driver
//
//		String username = "root";
//		String password = "";
//		String mysqlurl = "jdbc:mysql://localhost:3306/mhrd1";
//		con = DriverManager.getConnection(mysqlurl, username, password); // Creating
//		// connection
//		
//		// database
//	} catch (Exception ex) {
//		System.out.println(ex);
//	}
//	return con;
//}//createConnection ends here!!
	
	

		public boolean validateRegistrationNumber(String entity_id,
				String program_id, String branch_code, String regnumber) {
			
			boolean b=true;
			try {
				
			
				CMaddMarksInfoGetter getDataObject[] = null;
				CMaddMarksInfoGetter addMarksObject = new CMaddMarksInfoGetter();
				addMarksObject.setProgram_id(program_id);
				addMarksObject.setBranch_id(branch_code);
				addMarksObject.setEntity_id(entity_id);
				addMarksObject.setRegistration_number(regnumber);
				
				List<?> regNoCount = client.queryForList("getRegCount",addMarksObject);			
				getDataObject=regNoCount.toArray(new CMaddMarksInfoGetter[regNoCount.size()]);
				
				for(int i=0;i<getDataObject.length;i++){
					if(getDataObject[i].getTotal_marks()>=1){
						
						b=false;
					}
				}

			} catch (Exception e) {
				System.out.println("Error message in getValidation " + e.getMessage());
				logObj.logger.error("Error message in getValidation " + e.getMessage());
			}
			return b;
		}

		public String[] getTestCosForReg(String entity_id, String program_id,String branch_code,String regnumber,Connection con){
		
			String[] val=new String[2];
			try {
								
				CMaddMarksInfoGetter getDataObject[] = null;
				CMaddMarksInfoGetter addMarksObject = new CMaddMarksInfoGetter();
				addMarksObject.setProgram_id(program_id);
				addMarksObject.setBranch_id(branch_code);
				addMarksObject.setEntity_id(entity_id);
				addMarksObject.setRegistration_number(regnumber);
				
				
				
				List<?> getCos = client.queryForList("getCosForTestNo",addMarksObject);			
				getDataObject=getCos.toArray(new CMaddMarksInfoGetter[getCos.size()]);
				val[0]=getDataObject[0].getTest_number();
				val[1]=getDataObject[0].getCos_value();
				
			} catch (Exception e) {
				System.out.println("Error message in getTestCosForReg " + e.getMessage());
				logObj.logger.error("Error message in getTestCosForReg " + e.getMessage());
			}
			return val;
		}

	

}
