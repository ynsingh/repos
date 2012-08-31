package in.ac.dei.edrp.server.addmarks;

import in.ac.dei.edrp.client.ReportInfoGetter;
import in.ac.dei.edrp.client.addmarks.AddMarksExcelService;
import in.ac.dei.edrp.server.ReportingFunction;
import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ibatis.sqlmap.client.SqlMapClient;

@SuppressWarnings("serial")
public class AddMarksExcelServiceImpl extends RemoteServiceServlet implements
		AddMarksExcelService {
	
	 Log4JInitServlet logObj = new Log4JInitServlet();
	 SqlMapClient client = SqlMapManager.getSqlMapClient();
	/**
	 * method updated by Devendra May 5th
	 */
	public List<Integer> uploadFile(String filename, String universityId,
		String entityType, String entityId, String programId) {
		List<Integer> list = new ArrayList<Integer>();
		ReadXLSheet xlReader = new ReadXLSheet();
		ReportInfoGetter addMarksReport = new ReportInfoGetter();
		ReportingFunction reportingFunction = new ReportingFunction();

		String[] sessionDate = reportingFunction.getSessionDate(universityId);		
		addMarksReport.setUniversity_id(universityId);
		addMarksReport.setEntity_id(entityId);
		addMarksReport.setProgram_id(programId);
		addMarksReport.setOffered_by(entityId);
		addMarksReport.setUniversity_start_date(sessionDate[0]);
		addMarksReport.setUniversity_end_date(sessionDate[1]);		
		list = xlReader.init(filename, addMarksReport);
		return list;
	}

	public String uploadFile(String filePath) {
		return null;
	}
	
	/**
	 * method added by Devendra May 7th
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> uploadFileForStudentComponentMarks(String filename,
			String userId, String entityType, String entityId,
			String programId, String ComponentId,String ComponentDescription) {				
		 ReportInfoGetter addMarksinDB=new ReportInfoGetter();
		 addMarksinDB.setEntity_type(entityType);
		 addMarksinDB.setEntity_id(entityId);
		 addMarksinDB.setProgram_id(programId);
		 addMarksinDB.setUser_id(userId);
		 addMarksinDB.setComponent_id(ComponentId);
		 addMarksinDB.setComponent_description(ComponentDescription);
		 ReportingFunction reportingFunction = new ReportingFunction();
		 String[] sessionDate = reportingFunction.getSessionDate(userId);
		 addMarksinDB.setStart_date(sessionDate[0]);
		 addMarksinDB.setEnd_date(sessionDate[1]);	
		 addMarksinDB.setUniversity_start_date(sessionDate[0]);
		 addMarksinDB.setUniversity_end_date(sessionDate[1]);
		 addMarksinDB.setUniversity_id(userId);
		 filename=this.getServletContext().getRealPath("/")+"Excel"+File.separator+"1.xls";
        WorkbookSettings ws = null;
        Workbook workbook = null;
        Sheet s = null;
        Cell[] rowData = null;
        int rowCount ='0';     
        int columnCount = '0';
        int totalSheet = 0;
        int count = 0;
        List<Integer> list = new ArrayList<Integer>();
        int totalrecord = 0;

        try {
        	if(!ComponentId.equalsIgnoreCase("AS")){        		
        		System.out.println("file name is "+filename);
	            ws = new WorkbookSettings();
	            ws.setLocale(new Locale("en", "EN"));	           
	            FileInputStream fileInputStream = new FileInputStream(new File(filename));            
	            workbook = Workbook.getWorkbook(fileInputStream, ws);
	            totalSheet = workbook.getNumberOfSheets();
	            s = workbook.getSheet(0);         
	            rowCount = s.getRows();           
	            columnCount = s.getColumns();
	            totalrecord=rowCount-1; 
	            List<ReportInfoGetter>studentList=client.queryForList("getStudents", addMarksinDB);            
				if(columnCount==3){
					for(int i=1;i<rowCount;i++){
						boolean flag=false;
						 rowData = s.getRow(i);
						 String registrationNumber = rowData[0].getContents();
						 String marks = rowData[1].getContents();
						 String attendence=rowData[2].getContents();
						 addMarksinDB.setRegistration_number(registrationNumber);
						 addMarksinDB.setMarks_percentage(Double.parseDouble(marks));
						 addMarksinDB.setAttendance(attendence);
						 for(int j=0;j<studentList.size();j++){
							 if(studentList.get(j).getRegistration_number().equals(registrationNumber)){
								 flag=true;
							 }
						 }
						if(flag){
						 if (validateExcelRecords(Double.parseDouble(marks),attendence, addMarksinDB)) {						 
							 if (!checkDuplicacyForRegistrationNumber(
	                                 addMarksinDB)) {								 
								 if(attendence.equalsIgnoreCase("P")){
									 addMarksinDB.setMarks_percentage(Double.parseDouble(marks));
								 }
								 else{
									 addMarksinDB.setMarks_percentage(Double.parseDouble("0"));
								 }
	                         /**
	                          * query updated
	                          */         
								 
	                         client.update("insertStudentFinalMarks",
	                             addMarksinDB);                                   
	                         count++;
	                     } else {
	                    	 
	                    	 if(attendence.equalsIgnoreCase("P")){
								 addMarksinDB.setMarks_percentage(Double.parseDouble(marks));
							 }
							 else{
								 addMarksinDB.setMarks_percentage(Double.parseDouble("0"));
							 }
	                         client.update("updateStudentFinalMarks",addMarksinDB);
	                         count++;
	                     	} 
						 }
						 else {
	                         logObj.logger.info("Improper value for: " +addMarksinDB.getRegistration_number());                                                      
	                     } 
					}
					else{
						 logObj.logger.info("Student whose registration number is "+registrationNumber+" is not found in student_test_number"); 
					}
					}
				}   
				else{
					 logObj.logger.info("value not inserted into student final marks because in uploaded excel columns " +
					 		"are not proper there shoul be 3 columns named roll Number, marks,present/Absent flag");                         
				}
				fileInputStream.close();
	            workbook.close();	            
	            new File(filename).delete();
        	}
        	else{
        		try{
        			//Get Student's Computed Marks of Academic Score
	        		List<ReportInfoGetter>studentList=client.queryForList("getStudents", addMarksinDB); 
	        		for(int i=0;i<studentList.size();i++){
	        			 addMarksinDB.setRegistration_number(studentList.get(i).getRegistration_number());
						 addMarksinDB.setMarks_percentage(Double.parseDouble(studentList.get(i).getMarks()));
						 addMarksinDB.setAttendance("P");
						//Check Record Exists or not
						 if (!checkDuplicacyForRegistrationNumber(addMarksinDB)) {     							 
							 client.update("insertStudentFinalMarks",addMarksinDB);                                                       
						 } else {
	                     	client.update("updateStudentFinalMarks",addMarksinDB);
	                 	}
	        		}
	        		//Get Student's Academic Component's Marks
	        		List<ReportInfoGetter>componentList=client.queryForList("getStudentAcademicComponent", addMarksinDB);
	        		ArrayList<String>tempList=new ArrayList<String>();
	        		for(int i=0;i<componentList.size();i++){
	        			if(tempList.indexOf(componentList.get(i).getRegistration_number())<0){
	        				tempList.add(componentList.get(i).getRegistration_number());
	        				addMarksinDB.setRegistration_number(componentList.get(i).getRegistration_number());
	        				for(int j=0;j<componentList.size();j++){
	        					if(componentList.get(i).getRegistration_number().equals(componentList.get(j).getRegistration_number())){
	        						addMarksinDB.setComponent_id(componentList.get(j).getComponent_id());
	        						addMarksinDB.setMarks_percentage(Double.parseDouble(componentList.get(j).getMarks()));
	        						addMarksinDB.setAttendance("P");
	        						//Check Record Exists or not
	        						 if (!checkDuplicacyForRegistrationNumber(addMarksinDB)) {
	        							 //Acadmic component score is inserted 
	        							 client.update("insertStudentFinalMarks",addMarksinDB);
	        						 }
	        						 else{
	        							//Acadmic component score is updated 
	        							 client.update("updateStudentFinalMarks",addMarksinDB);
	        						 }
	        					}
	        				}
	        			}
	        		}
	        		list.add(1);	        		
        		}
        		catch(Exception e){ 
        			list.add(0);
        			logObj.logger.info("Problem while inserting in data base : "+e);
        		}
        		return list;
        	}
        } catch (IOException e) {
        	logObj.logger.info("Problem while inserting in data base : "+e);
        } catch (BiffException e) {
        	logObj.logger.info("Problem while inserting in data base : "+e);
        } catch (Exception e) {
        	logObj.logger.info("Problem while inserting in data base : "+e);
        } // end catch

        list.add(totalrecord);
        list.add(count);

        return list;
	}

	 @SuppressWarnings("unchecked")
	    public boolean validateExcelRecords(double marks,
	        String attended, ReportInfoGetter validateMarks) {
	        boolean validateExcel = true;

	        try {
	            if ((attended.equalsIgnoreCase("P") ||
	                    attended.equalsIgnoreCase("A"))) {
	                /**
	                 * query updated
	                 */
	                List<ReportInfoGetter> validateRecord = client.queryForList("getFinalMeritEvaluationId",
	                        validateMarks);

	                for (ReportInfoGetter validate : validateRecord) {
	                    if (marks > validate.getTotal_marks()) {
	                        validateExcel = false;
	                    }
	                }
	            } else {
	                validateExcel = false;
	            }
	        } catch (Exception e) {
	            logObj.logger.info("Problem while inserting in data base" +
	                validateMarks.getRegistration_number());
	        }

	        return validateExcel;
	    }
	 
	 	/**
	     * method updated
	     */
	    @SuppressWarnings("unchecked")
	    public boolean checkDuplicacyForRegistrationNumber(
	        ReportInfoGetter addMarksinDB) {
	        boolean existRegistration = false;

	        try {
	            /**
	             * query updated
	             */
	            List<ReportInfoGetter> existReg = client.queryForList("checkDuplicacyOfRegInStduentFinal",
	                    addMarksinDB);

	            for (ReportInfoGetter exist : existReg) {

	                if (exist.getCount() >= 1) {
	                    existRegistration = true;
	                }
	            }
	        } catch (Exception e) {
	            logObj.logger.info("Problem while inserting in data base" +
	                addMarksinDB.getRegistration_number());
	        }

	        return existRegistration;
	    }
	    
}
