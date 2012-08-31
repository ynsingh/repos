package in.ac.dei.edrp.server;

import in.ac.dei.edrp.client.DataBean;
import in.ac.dei.edrp.client.ReportInfoGetter;
import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import com.ibatis.sqlmap.client.SqlMapClient;


/**
 * File updated
 * @author <a href="mailto:deepak2rok@gmail.com>Deepak Pandey</a>
 * @version 1.0
 */
public class ExportService {
    private static List<DataBean> list = new ArrayList<DataBean>();
    static String[] category = null;
    Log4JInitServlet logObj = new Log4JInitServlet();

    //GreetingServiceImpl gsimpl=new GreetingServiceImpl();
    SqlMapClient client = SqlMapManager.getSqlMapClient();
    ReportingFunction reportfunction = new ReportingFunction();

    
    /**
	 * method updated
	 */
    @SuppressWarnings("unchecked")
    public List<DataBean> getD(String university_id,
        String entity_id, String program_id, String subject_code) {
        System.out.println("------------At server side----------");

        List<DataBean> list = new ArrayList<DataBean>();
        double cm = 0.0;
        String[][] s = null;
        String[] detail = null;

        try {
        	
        	/**
        	 * method updated
        	 */
            String[] udate = reportfunction.getSessionDate(university_id);            
            ReportInfoGetter export = new ReportInfoGetter();
            export.setProgram_id(program_id);
            export.setEntity_id(entity_id);
            export.setSubject_code(subject_code);          
            export.setOffered_by(entity_id);
            //setting session start date and end date
            export.setStart_date(udate[0]);
            export.setEnd_date(udate[1]);          
            export.setUniversity_id(university_id);          
            
            /**
             * method updated
             */
            category = reportfunction.getCategoryCos(export);     
            for (int catlen = 0; catlen < category.length; catlen++) {
                int slnum = 0;
                export.setCos_value(category[catlen]);
                export.setStatus("Eligible");               
                /**
                 * query updated
                 */
                List<ReportInfoGetter> li2 = client.queryForList("getExcelRegistrationNumber",
                        export);    
                double call = 0.0;
                export.setOffered_by(entity_id);
                
                
                /**
                 * query updated
                 */
                List<ReportInfoGetter> li3 = client.queryForList("getCallMerit",
                        export);    
                for (ReportInfoGetter callmarks : li3) {
                    call = callmarks.getCut_off_number();
                }

                for (ReportInfoGetter reg_excel : li2) {                    
                    String name = reg_excel.getRegistration_number();
                    cm = reg_excel.getSum_computed_marks();
               
                    if (cm >= call) {
                    	
                    	/**
                    	 * method updated
                    	 */
                        String[][] data = reportfunction.getFullyExcelComputedmarks(name,
                                export, udate);

                        /**
                         * method updated
                         */
                        String[] detailData = reportfunction.getPersonalDetail(name,
                                export);
                        s = data;
                        
                        detail = detailData;
                        
                        System.out.println("Adding ");
                        
                        slnum++;

                        //System.out.println(cm);
                        if (category[catlen].substring(0, 2)
                                                .equalsIgnoreCase("XX")) {
                        	
                        	/**
                        	 * method updated
                        	 */
                            reportfunction.updateCalledStudentTest(name,
                                export, "n", udate);
                        } else {
                        	
                        	/**
                        	 * method updated
                        	 */
                            reportfunction.updateCalledStudentTest(name,
                                export, "y", udate);
                        }
                        
                        list.add(new DataBean(slnum, name, detail, s, cm));

                        if (!(list.size() == 0)) {
                        	
                        	/**
                        	 * method updated
                        	 */
                            reportfunction.updateControlReport(export, "I", "C");
                        } //if list.size() ends
                    } //if cm>call ends
                    else {
                    	
                    	/**
                    	 * method updated
                    	 */
                        reportfunction.updateCalledStudentTest(name, export,
                            "n", udate);
                    }
                                        
                } // second loop registration number ends
            } //category loop ends
            	
        } catch (Exception e) {
        	
            logObj.logger.info(
                "Exception in getting list for internal call list: " +
                e.getMessage());
            
        } // catch ends    

        return list;
    }

    /**
     * method updated
     */
    @SuppressWarnings("unchecked")
    public ByteArrayOutputStream generateExcelReport(String university_id,
        String entity_type, String entity_id, String program_id,
        String subject_code,String genderDep,String subject_name)
        throws IOException, WriteException {
        /* Stream containing excel data */
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ReportingFunction reportFunction = new ReportingFunction();

        /* Create Excel WorkBook and Sheet */
        WritableWorkbook workBook = Workbook.createWorkbook(outputStream);       
        WritableSheet sheet = workBook.createSheet("User List", 0);

        /* Generates Headers Cells */
        WritableFont headerFont = new WritableFont(WritableFont.TAHOMA, 8,
                WritableFont.BOLD);
        WritableCellFormat headerCellFormat = new WritableCellFormat(headerFont);
        headerCellFormat.setBackground(Colour.PALE_BLUE);

        /**
         * method updated
         */
        String entity_name = reportFunction.getEntity_Name(entity_id,
                university_id);
        /**
         * method updated
         */
        String program_name = reportFunction.getProgram_Name(entity_id,
                program_id);
      
        /**
         * method updated
         */
        list = getUsers(university_id, entity_id, program_id,
                subject_code);       
        Iterator i1 = list.iterator();
        int damaxLen = 0;

        while (i1.hasNext()) {
            DataBean db = (DataBean) i1.next();
            String[][] datalen = db.getCompData();          
            if (damaxLen < datalen.length) {
                damaxLen = datalen.length;           
            }
        }
        sheet.addCell(new Label(5, 1,
                "DayalBagh Education Institute- Agra(282110)",
                headerCellFormat));
        sheet.addCell(new Label(5, 2,
                "List of Students for generating call list", headerCellFormat));
        sheet.addCell(new Label(5, 3, "Institute Name", headerCellFormat));
        sheet.addCell(new Label(6, 3, entity_name, headerCellFormat));
        sheet.addCell(new Label(5, 4, "Program Name", headerCellFormat));
        sheet.addCell(new Label(6, 4, program_name, headerCellFormat));
        sheet.addCell(new Label(5, 5, "Subject Name", headerCellFormat));
        sheet.addCell(new Label(6, 5, subject_name, headerCellFormat));
		
		if(list.size()>0){//Condition add by devendra
	        i1 = list.iterator();
	        i1.hasNext();
	
	        DataBean db = (DataBean) i1.next();
	        sheet.addCell(new Label(1, 7, "Serial Number", headerCellFormat));
	        sheet.addCell(new Label(2, 7, "Registration Number", headerCellFormat));
	        sheet.addCell(new Label(3, 7,"Faculty Registration Number",headerCellFormat));
	        
	        String[] det = db.getDetail();
	        int dlen = 4;

	if(genderDep.equals("Y")){
		 for (int l = 0; l < det.length; l++) {
	            switch (l) {
	            case 0: {
	                sheet.addCell(new Label(dlen, 7, "Name", headerCellFormat));
	
	                break;
	            }
	
	            case 1: {
	                sheet.addCell(new Label(dlen, 7, "Father Name",
	                        headerCellFormat));
	
	                break;
	            }
	
	            
	            case 2: {
	                sheet.addCell(new Label(dlen, 7, "Date of Birth",
	                        headerCellFormat));
	
	                break;
	            }
	
	            case 3: {
	                sheet.addCell(new Label(dlen, 7, "Category", headerCellFormat));
	
	                break;
	            }
	
	            case 4: {
	            	
	            		 sheet.addCell(new Label(dlen, 7, "Gender", headerCellFormat));
	            	
	                break;
	            }
	
	            case 5: {
	                sheet.addCell(new Label(dlen, 7, "City", headerCellFormat));
	
	                break;
	            }
	            case 6: {
	                sheet.addCell(new Label(dlen, 7, "DEI Staff", headerCellFormat));
	
	                break;
	            }
	            } //switch1 ends
	
	            dlen++;
	        }
	}
	else{
		 for (int l = 0; l < det.length-1; l++) {
	            switch (l) {
	            case 0: {
	                sheet.addCell(new Label(dlen, 7, "Name", headerCellFormat));
	
	                break;
	            }
	
	            case 1: {
	                sheet.addCell(new Label(dlen, 7, "Father Name",
	                        headerCellFormat));
	
	                break;
	            }
	
	            
	            case 2: {
	                sheet.addCell(new Label(dlen, 7, "Date of Birth",
	                        headerCellFormat));
	
	                break;
	            }
	
	            case 3: {
	                sheet.addCell(new Label(dlen, 7, "Category", headerCellFormat));
	
	                break;
	            }
	
	            case 4: {
	            	sheet.addCell(new Label(dlen, 7, "City", headerCellFormat));
	            	
	                break;
	            }
	
	            case 5: {
	            	sheet.addCell(new Label(dlen, 7, "DEI Staff", headerCellFormat));
	
	                break;
	            }
	            } //switch1 ends
	
	            dlen++;
	        }
	}
	
	        int len = dlen-1;       
	
	        for (int i2 = 0; i2 < damaxLen; i2++) {
	            for (int j = 0; j < 9; j++) {
	                switch (j) {
	                case 0: {
	                    sheet.addCell(new Label(len, 7, "Component",
	                            headerCellFormat));
	
	                    break;
	                }
	
	                case 1: {
	                    sheet.addCell(new Label(len, 7, "Board", headerCellFormat));
	
	                    break;
	                }
	                
	                case 2: {
	                    sheet.addCell(new Label(len, 7, "Marks Obatained", headerCellFormat));
	
	                    break;
	                }
	                
	                case 3: {
	                    sheet.addCell(new Label(len, 7, "Total Marks", headerCellFormat));
	
	                    break;
	                }
	                
	                case 4: {
	                    sheet.addCell(new Label(len, 7, "Percentage",
	                            headerCellFormat));
	
	                    break;
	                }
	
	                case 5: {
	                    sheet.addCell(new Label(len, 7, "Computed Marks",
	                            headerCellFormat));
	
	                    break;
	                }
	                case 6: {
	                    sheet.addCell(new Label(len, 7, "DEI Student",
	                            headerCellFormat));
	
	                    break;
	                }
	                case 7: {
	                    sheet.addCell(new Label(len, 7, "Special Weightage",
	                            headerCellFormat));
	
	                    break;
	                }
	
	                case 8: {
	                    sheet.addCell(new Label(len, 7, "Computed Weightage",
	                            headerCellFormat));
	
	                    break;
	                }
	
	                }
	
	                len++;
	            }
	        }
	
	        sheet.addCell(new Label(len, 7, "Complete Weightage", headerCellFormat));	
	        len++;
	        ReportInfoGetter been=new ReportInfoGetter();
            been.setProgram_id(program_id);
            been.setUniversity_id(university_id);
            been.setEntity_id(entity_id);
            try {
				List<ReportInfoGetter> paperGroupList=client.queryForList("getProgramGroup", been);
				for(int i2=0;i2<paperGroupList.size();i2++){
					 sheet.addCell(new Label(len, 7, paperGroupList.get(i2).getDescription(), headerCellFormat));
					 len++;
				}
			} catch (SQLException e) {
				logObj.logger.error("Error during get program group inside ExportListForEducation class : "+e);
			}       
	        /* Generates Data Cells */
	        WritableFont dataFont = new WritableFont(WritableFont.TAHOMA, 8);
	        WritableCellFormat dataCellFormat = new WritableCellFormat(dataFont);
	        int currentRow = 8;		   
	        Iterator i = list.iterator();
	        String[][] data1 = new String[damaxLen][]; 
	        String[] det1 = null;
	        int catval = 0;
	
	        while (i.hasNext()) {
	            DataBean db1 = (DataBean) i.next();
	
	            if (db1.getSlnum() == 1) {
	                sheet.addCell(new Label(1, currentRow,
	                        "List according to cos: " + category[catval],
	                        dataCellFormat));
	                currentRow++;
	                catval++;
	            }
	
	            sheet.addCell(new Label(1, currentRow,
	                    String.valueOf(db1.getSlnum()), dataCellFormat));
	            sheet.addCell(new Label(2, currentRow, db1.getRegistrationNumber(),
	                    dataCellFormat));	            
	            data1 = db1.getCompData();
	
	            det1 = db1.getDetail();
	
	            int dlen1 = 3;
	            
	            // Add by Devendra Condition to check gender dependency
	            if(genderDep.equals("Y")){
	            	for (int l1 = 0; l1 < det.length; l1++) {		            		            	            	
		            			 sheet.addCell(new Label(dlen1, currentRow, det1[l1],
		     	                        dataCellFormat));	            			             
		                dlen1++;
		            }
	            }
	            else{
	            	for (int l1 = 0; l1 < det.length; l1++) {		            
		            	        if(l1==5){
		            	        	//Condition to skip gender detail
		            	        }
		            	        else{
		            	        	sheet.addCell(new Label(dlen1, currentRow, det1[l1],
			     	                        dataCellFormat));
		            	        	dlen1++;
		            	        }
		            	     
		            			 	            			             
		                
		            }
	            }

	            int len1 = dlen1;
	
	            for (int i3 = 0; i3 < data1.length; i3++) {
	                for (int j = 0; j < 9; j++) {
	                    sheet.addCell(new Label(len1, currentRow, data1[i3][j],
	                            dataCellFormat));
	                    len1++;
	                }
	            }
	
	            if (data1.length < damaxLen) {
	                for (int i3 = data1.length; i3 < damaxLen; i3++) {
	                    for (int j = 0; j < 9; j++) {
	                        sheet.addCell(new Label(len1, currentRow, "",
	                                dataCellFormat));
	                        len1++;
	                    }
	                }
	            }
	
	            double comp1 = db1.getTotalMarks();
	            sheet.addCell(new Label(len1, currentRow, String.valueOf(comp1),
	                    dataCellFormat));
	            len1++;
                been.setRegistration_number(db1.getRegistrationNumber());
                try {
					List<ReportInfoGetter> paperGroupList=client.queryForList("getProgramPaperDesc", been);
					for(int i2=0;i2<paperGroupList.size();i2++){
						sheet.addCell(new Label(len1, currentRow, paperGroupList.get(i2).getDescription(),dataCellFormat));
						len1++;
					}
				} catch (SQLException e) {
					logObj.logger.error("Error during get program paper code inside ExportListForEducation class : "+e);
				}
	            currentRow++;
	        }
    }
        /* Write & Close Excel WorkBook */
        workBook.write();
        workBook.close();

        return outputStream;
    }

    
    /**
     * method updated
     */
    public List<DataBean> getUsers(String university_id,
        String entity_name, String program_name, String subject_code) {
        return getD(university_id, entity_name, program_name,
        		subject_code);
    }
    
}
