package in.ac.dei.edrp.server;

import in.ac.dei.edrp.client.DataBean;
import in.ac.dei.edrp.client.ReportInfoGetter;
import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
 * The server side implementation of the RPC service.
 * File updated by Devendra Singhal
 */
public class ExportService2 {
    Log4JInitServlet logObj = new Log4JInitServlet();
    List<DataBean> list = new ArrayList<DataBean>();
    List<DataBean> list1 = new ArrayList<DataBean>();
    List<ReportInfoGetter>headingList=null;
    String[] heading = null;   
    String[] category = null;
    String[] catstr = null;   
    SqlMapClient client = SqlMapManager.getSqlMapClient();
    ReportingFunction reportfunction = new ReportingFunction();

    @SuppressWarnings("unchecked")
    public List<DataBean> methodProgramList(String university_id,String entity_id, String program_id,String subject_code) {
        try {
            String[] udate = reportfunction.getSessionDate(university_id);
            ReportInfoGetter export2 = new ReportInfoGetter();
            export2.setProgram_id(program_id);
            export2.setEntity_id(entity_id);
            export2.setOffered_by(entity_id);
            export2.setStart_date(udate[0]);
            export2.setEnd_date(udate[1]);
            if(subject_code.equals("All")){            	
            	 category = reportfunction.getCategoryForAllSubject(export2);
            }
            else{
            	export2.setSubject_code(subject_code);
            	category = reportfunction.getCategoryCos(export2);
            }
           
            int count = 0;
            String gender="";//add by Devendra
            catstr = new String[2];           
            for (int len = 0; len < category.length; len++) {
                if (category[len].substring(0, 2).equalsIgnoreCase("GN")) {                    
                    catstr[count] = category[len];
                    count++;
                }
            }
            heading = getHeadLines(export2); 
            headingList=client.queryForList("getFinalComponentDetail", export2);
            if (count == 2) {
                export2.setOffered_by(entity_id);
                List<ReportInfoGetter> li1;                
                for (int i = 0; i < catstr.length; i++) {
                    String cm = "";
                    int slnum = 0;
                    int slnum1 = 0;
                    export2.setCos_value(catstr[i]);                   
                    li1 = client.queryForList("getCosSeats1", export2);                    
                    int seats = 0;
                    for (ReportInfoGetter cos : li1) {
                        seats = cos.getCos_seat();
                    }
                    export2.setCos_value("__" + catstr[i].substring(2));
                    List<ReportInfoGetter> li = client.queryForList("exportfinalmeritwithMF",export2);                  
                    for (ReportInfoGetter finallist : li) {
                        String[] qualify = updateQualifyStatus(export2,finallist.getRegistration_number()); 
                        
                        updateStatusinFinal(export2, finallist.getRegistration_number(), qualify);
                        
                        if (qualify[0].equalsIgnoreCase("Waiting")) {
                            String name = finallist.getRegistration_number();
                            String test_number = finallist.getTest_number();
                            String cos = finallist.getCategory();
                            cm = String.valueOf(finallist.getSum_actual_computed_marks());
                            double total_marks = finallist.getTotal_marks();
                            String[] udetail = reportfunction.getUserDetail(name,export2);
                            String sname = udetail[0];
                            gender=udetail[3];
                            List<ReportInfoGetter>sam = addMarks(name, export2,heading.length);
                            slnum++;
                            if (slnum <= seats) {                            	
                                qualify[0] = "selected";
                                qualify[1] = "Congratulations,you are selected ";
                                updateStatusinFinal(export2,finallist.getRegistration_number(), qualify);
                                list.add(new DataBean(slnum, name, test_number,cos, sname, cm, sam, total_marks,gender));
                            } else {
                                slnum1++;
                                list1.add(new DataBean(slnum1, name,test_number, cos, sname, cm, sam,total_marks,gender));
                            } // else ends for adding waiting
                        } // waiting if ends
                    } // registration_number finallist ends
                } // int i=0 ends loop
            } // if closed count==2
            else { 
                String cm = "";
                int slnum = 0;
                int slnum1 = 0;
                export2.setOffered_by(entity_id);
                export2.setCos_value("GN%");
                List<ReportInfoGetter> li1 = client.queryForList("getCosSeats1", export2);
                int seats = 0;
                for (ReportInfoGetter cos : li1) {
                    seats = cos.getCos_seat();
                }
				export2.setCos_value("__"+category[0].substring(2));//Add by Devendra
                List<ReportInfoGetter> li = client.queryForList("exportfinalmeritwithMF",export2);
                for (ReportInfoGetter finallist : li) {
                    String[] qualify = updateQualifyStatus(export2,finallist.getRegistration_number());
                    
                    updateStatusinFinal(export2,finallist.getRegistration_number(), qualify);
                    if (qualify[0].equalsIgnoreCase("Waiting")) {
                        String name = finallist.getRegistration_number();
                        String test_number = finallist.getTest_number();
                        String cos = finallist.getCategory();
                        cm = String.valueOf(finallist.getSum_actual_computed_marks());
                        double total_marks = finallist.getTotal_marks();
                        
                        String[] udetail = reportfunction.getUserDetail(name,export2);
                        String sname = udetail[0];
                        gender=udetail[3];
                        List<ReportInfoGetter>sam  = addMarks(name, export2, heading.length);
                        slnum++;
                        if (slnum <= seats) {
                            qualify[0] = "selected";
                            qualify[1] = "Congratulations,you are selected ";                            
                            updateStatusinFinal(export2,finallist.getRegistration_number(), qualify);                            
                            list.add(new DataBean(slnum, name, test_number,cos, sname, cm, sam, total_marks,gender));
                        } else {
                            slnum1++;
                            list1.add(new DataBean(slnum1, name, test_number,cos, sname, cm, sam, total_marks,gender));
                        } // else ends for adding waiting
                    } // waiting if ends
                } // registration_number finallist ends
            }
                        
            for (int catlen = 0; catlen < category.length; catlen++) {             	
                if (!(category[catlen].substring(0, 2).equalsIgnoreCase("GN"))) {                 
                    String cm = "";
                    int slnum = 0;
                    int slnum1 = 0;
                    export2.setOffered_by(entity_id);
                    export2.setCos_value(category[catlen]);
                    export2.setStatus("Waiting");
                    List<ReportInfoGetter> li = client.queryForList("getFinalRegistrationNumberWithStatus",export2);
                    List<ReportInfoGetter> li2 = client.queryForList("getCosSeats",export2);
                    int seats = 0;
                    for (ReportInfoGetter cos : li2) {
                        seats = cos.getCos_seat();
                    }
                    for (ReportInfoGetter finallist : li) {
                    	
                        String[] qualify = updateQualifyStatus(export2, finallist.getRegistration_number());
                        
                        updateStatusinFinal(export2,finallist.getRegistration_number(), qualify);

                        if (qualify[0].equalsIgnoreCase("Waiting")) {                        	
                            String name = finallist.getRegistration_number();
                            String test_number = finallist.getTest_number();
                            cm = String.valueOf(finallist.getSum_actual_computed_marks());
                            double total_marks = finallist.getTotal_marks();
                            
                            String[] udetail = reportfunction.getUserDetail(name,export2);
                            String sname = udetail[0];
                            gender=udetail[3];
                            
                            List<ReportInfoGetter>sam  = addMarks(name, export2,heading.length);
                            slnum++;
                            if (slnum <= seats) {
                                qualify[0] = "selected";
                                qualify[1] = "Congratulations,you are selected ";
                                
                                updateStatusinFinal(export2,finallist.getRegistration_number(), qualify);

                                list.add(new DataBean(slnum, name, test_number,category[catlen], sname, cm, sam,total_marks,gender));
                            } // if ends
                            else {
                                slnum1++;
                                list1.add(new DataBean(slnum1, name,test_number, category[catlen], sname,cm, sam, total_marks,gender));
                            } // else ends
                        } // if ends
                    } // finallist category ends
                } // if GN ends
            } // category loop ends
        } catch (Exception e) {
        	System.out.println("Exception "+e);
            logObj.logger.info("Exception in getting list for final merit list: " +e.getMessage());
        } // catch ends

        return list;
    }

    private void updateStatusinFinal(ReportInfoGetter export2,
        String registration_number, String[] qualify) {
        try {
            ReportInfoGetter update = new ReportInfoGetter();
            update = export2;
            update.setRegistration_number(registration_number);
            update.setStatus(qualify[0]);
            update.setReason_code(qualify[1]);
            client.update("updatestatusfinal", update);
        } catch (Exception e) {
        	  logObj.logger.info("Exception in updateStatusFinal in ExportService " +e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private String[] updateQualifyStatus(ReportInfoGetter export2, String regnum) {
        String[] qualify = new String[2];
        qualify[0] = "waiting";
        qualify[1] = "You are qualified";
        try {
            export2.setRegistration_number(regnum);
            List<ReportInfoGetter> li = client.queryForList("getfinalmarks", export2);
            for (ReportInfoGetter finaluser : li) {               
                export2.setComponent_id(finaluser.getComponent_id());
                List<ReportInfoGetter> li1 = client.queryForList("getattendanceflag",export2);
                for (ReportInfoGetter flag : li1) {                   
                    if (flag.getAttendance_flag().equalsIgnoreCase("Y") && finaluser.getAttendance().equalsIgnoreCase("A")) {
                        qualify[0] = "Disqualify";
                        qualify[1] = "You were absent in " +
                        flag.getComponent_description();
                        break;
                    }
                }
            }
        } catch (Exception e) {
        	logObj.logger.info("Exception in update qualify status " +e.getMessage());
        }
        return qualify;
    }

    @SuppressWarnings("unchecked")
    public String[] getHeadLines(ReportInfoGetter report) {
        String[] heading1 = null;
        try {
            ReportInfoGetter reportInfo = report;
            reportInfo.setOffered_by(report.getEntity_id());
            List<ReportInfoGetter> li = client.queryForList("getCountComponentDescription",reportInfo);
            for (ReportInfoGetter countvalue : li) {
                heading1 = new String[countvalue.getCount()];
            }
            List<ReportInfoGetter> li1 = client.queryForList("getComponentDescriptionValue",reportInfo);
            int i = 0;
            for (ReportInfoGetter countdesc : li1) {
                heading1[i] = countdesc.getComponent_description();
                i++;
            }
        } catch (Exception e) {
        	logObj.logger.info("Coming here in exception e" + e.getMessage());
        }
        return heading1;
    }

    /*
     * for getting total edit marks
     * method updated
     */
    @SuppressWarnings("unchecked")
    public  List<ReportInfoGetter> addMarks(String regnumber, ReportInfoGetter export2, int len) {
    	List<ReportInfoGetter> li=null;
        try {
            ReportInfoGetter finalmarks = export2;
            finalmarks.setRegistration_number(regnumber);
            li = client.queryForList("getfinalmarks",finalmarks);
            if (!(list.size() == 0)) {
                reportfunction.updateControlReport(export2, "F", "E");
            }
        } catch (Exception e) {
        	logObj.logger.error("Exception during getting student marks from student final marks "+e);
        }
        return li;
    }
    
    public ByteArrayOutputStream generateExcelReport(String university_id,String entity_id, String program_id,
        String subject_code, String gender, String subject_name)
        throws IOException, WriteException {
        /* Stream containing excel data */
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ReportingFunction reportFunction = new ReportingFunction();

        /* Create Excel WorkBook and Sheet */
        WritableWorkbook workBook = Workbook.createWorkbook(outputStream);
        WritableSheet sheet = workBook.createSheet("User List", 0);

        /* Generates Headers Cells */
        WritableFont headerFont = new WritableFont(WritableFont.TAHOMA, 8,WritableFont.BOLD);
        WritableCellFormat headerCellFormat = new WritableCellFormat(headerFont);
        headerCellFormat.setBackground(Colour.PALE_BLUE);

        String entity_name = reportFunction.getEntity_Name(entity_id,university_id);
        String program_name = reportFunction.getProgram_Name(entity_id,program_id);

        list = getUsers(university_id, entity_id, program_id,subject_code);
        
        System.out.println("inside final merrit list size is "+list.size());

        sheet.addCell(new Label(1, 0, "Selected Candidates", headerCellFormat));
        sheet.addCell(new Label(5, 1,
                "DayalBagh Education Institute- Agra(282005)", headerCellFormat));
        sheet.addCell(new Label(5, 2,
                "List of Students for generating call list", headerCellFormat));
        sheet.addCell(new Label(5, 3, "Institute Name", headerCellFormat));
        sheet.addCell(new Label(6, 3, entity_name, headerCellFormat));
        sheet.addCell(new Label(5, 4, "Program Name", headerCellFormat));
        sheet.addCell(new Label(6, 4, program_name, headerCellFormat));      
        if(!subject_code.equals("All")){        	
        	 sheet.addCell(new Label(5, 5, "Subject Name", headerCellFormat));
        	 sheet.addCell(new Label(6, 5, subject_name, headerCellFormat));
        }       
        if(list.size()>0){//Condition added by devendra
	        int dlen = 0;
	        int dlen1 = 0;
	        int dlen12 =0 ;
	        sheet.addCell(new Label(1, 7, "Rank", headerCellFormat));
	        sheet.addCell(new Label(2, 7, "Registration Number", headerCellFormat));
	        sheet.addCell(new Label(3, 7, "Test Number", headerCellFormat));
	        sheet.addCell(new Label(4, 7, "Stduent Name", headerCellFormat));
	        sheet.addCell(new Label(5, 7, "Category", headerCellFormat));
	        if(gender.equals("Y")){
	        	sheet.addCell(new Label(6, 7, "Gender", headerCellFormat));	        	
	        	 for (int l = 0; l < headingList.size(); l++) {		       
	        		 if(headingList.get(l).getComponent_id().equalsIgnoreCase("AS")){
	        			 sheet.addCell(new Label(7+l, 7, "CallMerit", headerCellFormat));
	        		 }
	        		 else{
	        			 sheet.addCell(new Label(7+l, 7,headingList.get(l).getComponent_description(), headerCellFormat));	
	        		 }	 	            	 	           
	 	        }
	        	dlen=7+heading.length;
	        }
	        else{
	        	for (int l = 0; l <  headingList.size(); l++) {	
		        	 if(headingList.get(l).getComponent_id().equalsIgnoreCase("AS")){
	        			 sheet.addCell(new Label(6+l, 7, "CallMerit", headerCellFormat));
	        		 }
	        		 else{
	        			 sheet.addCell(new Label(6+l, 7, headingList.get(l).getComponent_description(), headerCellFormat));	
	        		 }		        
	        	}
	        	dlen=6+heading.length;;	        	 
	        }
	
	        sheet.addCell(new Label(dlen, 7, "ComputedMarks", headerCellFormat));
	        
	        /* Generates Data Cells */
	        WritableFont dataFont = new WritableFont(WritableFont.TAHOMA, 8);
	        WritableCellFormat dataCellFormat = new WritableCellFormat(dataFont);
	
	        int currentRow = 8;
	        List<ReportInfoGetter> det1 = null;
	
	        Iterator<DataBean> i11 = list.iterator();
	
	        String categ = "";
	        String[] udate = reportfunction.getSessionDate(university_id);
	
	        ReportInfoGetter export2 = new ReportInfoGetter();
	        export2.setProgram_id(program_id);
	        export2.setEntity_id(entity_id);
	        export2.setSubject_code(subject_code);
	        export2.setStart_date(udate[0]);
	        export2.setEnd_date(udate[1]);
	
	        int count = 0;
	        String[] catstr = new String[2];
	
	        for (int len = 0; len < category.length; len++) {
	            if (category[len].substring(0, 2).equalsIgnoreCase("GN")) {
	                catstr[count] = category[len];
	                count++;
	            }
	        }
	
	        int l = 0;
	        int x1 = 0;
	        int temp = 0;
	
	        while (i11.hasNext()) {
	             System.out.println("---------------------------------------------");
	            DataBean db1 = (DataBean) i11.next();
	            System.out.println("inside while set data "+count+" "+x1+" "+temp+" "+db1.getSlnum()+" "+db1.getCategory()+" "+categ);
	            if (count == 2) {
	                if (x1 == 2) {
	                    if (temp == 0) {
	                        if ((db1.getSlnum() == 1) &&
	                                (!categ.equalsIgnoreCase(""))) {	                
	                            sheet.addCell(new Label(1, currentRow,
	                                    "Selected List for " + db1.getCategory(),
	                                    dataCellFormat));
	                            currentRow++;
	                            temp++;
	                        }
	                    } else {
	                        if ((db1.getSlnum() == 1) &&
	                                (!categ.equalsIgnoreCase(db1.getCategory())) &&
	                                (!categ.equalsIgnoreCase(""))) {	                        	
	                            sheet.addCell(new Label(1, currentRow,
	                                    "Selected List for " + db1.getCategory(),
	                                    dataCellFormat));
	                            currentRow++;
	                        }
	                    }
	                }
	            } else {
	                if (temp == 0) {
	                    if ((db1.getSlnum() == 1) && (!categ.equalsIgnoreCase(""))) {	                    	
	                        sheet.addCell(new Label(1, currentRow,
	                                "Selected List for " + db1.getCategory(),
	                                dataCellFormat));
	                        currentRow++;
	                        temp++;
	                    }
	                } else {
	                    if ((db1.getSlnum() == 1) &&
	                            (!categ.equalsIgnoreCase(db1.getCategory())) &&
	                            (!categ.equalsIgnoreCase(""))) {	                    	
	                        sheet.addCell(new Label(1, currentRow,
	                                "Selected List for " + db1.getCategory(),
	                                dataCellFormat));
	                        currentRow++;
	                    }
	                }
	            }
	
	            if ((count == 2) && (l < 2) && (db1.getSlnum() == 1)) {
	                sheet.addCell(new Label(1, currentRow,
	                        "Selected List for count " + catstr[l], dataCellFormat));
	                l++;
	                currentRow++;
	                x1++;
	            } else {
	                if (db1.getSlnum() == 1) {
	                    if (l == 0) {
	                        // System.out.println("Coming");
	                        String catg = "";
	
	                        for (int l1 = 0; l1 < category.length; l1++) {	                           
	
	                            if (category[l1].substring(0, 2)
	                                                .equalsIgnoreCase("GN")) {
	                                catg = category[l1];	                                
	
	                                break;
	                            }
	                        }
	                        if(catg.equals("")){
	                        	 catg = category[0];	
	                        }
	                        sheet.addCell(new Label(1, currentRow,
	                                "Selected List for count " +
	                                catg, dataCellFormat));
	                        l++;
	
	                        x1++;
	                        currentRow++;
	                    } // if l ends
	                } // if db1.getSlnum ends
	            }
	
	            sheet.addCell(new Label(1, currentRow,
	                    String.valueOf(db1.getSlnum()), dataCellFormat));
	            sheet.addCell(new Label(2, currentRow, db1.getRegistrationNumber(),
	                    dataCellFormat));
	            sheet.addCell(new Label(3, currentRow,
	                    String.valueOf(db1.getTestNumber()), dataCellFormat));
	            sheet.addCell(new Label(4, currentRow, db1.getStudent_name(),
	                    dataCellFormat));
	            sheet.addCell(new Label(5, currentRow, db1.getCategory(),
	                    dataCellFormat));
	            
	            det1 = db1.getComponentDetail();
	            
	            if(gender.equals("Y")){
	            	sheet.addCell(new Label(6, currentRow, db1.getGender(), dataCellFormat));	
	            	for(int k=0;k<headingList.size();k++){
	            		 for (int ll = 0; ll < det1.size(); ll++) {		            			 
	            			 if(headingList.get(k).getComponent_id().equals(det1.get(ll).getComponent_id())){
	            				 if(det1.get(ll).getComponent_id().equalsIgnoreCase("AS")){
				        			 sheet.addCell(new Label(7+k, currentRow,  String.valueOf(db1.getPercentage()), dataCellFormat));
				        		 }
				        		 else{				        			
				        			 double compnentMarks=Double.parseDouble(det1.get(ll).getMarks());
									 double weightagePercentage=det1.get(ll).getComponent_Weightage();
									 double computedMarks=(compnentMarks*weightagePercentage)/100;
				        			 sheet.addCell(new Label(7+k, currentRow, String.valueOf(computedMarks), dataCellFormat));	
				        		 }	
	            				 break;
	            			 }
			        		 	        		
			 	        }
	            	}
		        	dlen1=7+headingList.size();
 		        }
 		        else{
 		         	for(int k=0;k<headingList.size();k++){
	            		 for (int ll = 0; ll < det1.size(); ll++) {		            			 
	            			 if(headingList.get(k).getComponent_id().equals(det1.get(ll).getComponent_id())){
	            				 if(det1.get(ll).getComponent_id().equalsIgnoreCase("AS")){
				        			 sheet.addCell(new Label(6+k, currentRow,  String.valueOf(db1.getPercentage()), dataCellFormat));
				        		 }
				        		 else{				        			
				        			 double compnentMarks=Double.parseDouble(det1.get(ll).getMarks());
									 double weightagePercentage=det1.get(ll).getComponent_Weightage();
									 double computedMarks=(compnentMarks*weightagePercentage)/100;
				        			 sheet.addCell(new Label(6+k, currentRow, String.valueOf(computedMarks), dataCellFormat));	
				        		 }	
	            				 break;
	            			 }
			        		 	        		
			 	        }
	            	}
 		         	dlen1=6+headingList.size();
 		        }	        	
	            double comp1 = db1.getTotalMarks();
	            sheet.addCell(new Label(dlen1, currentRow, String.valueOf(comp1),dataCellFormat));
	            currentRow++;
	            categ = db1.getCategory();	       
	        }
	
	        // Another work sheet
	        currentRow = 0;	
	        WritableSheet sheet1 = workBook.createSheet("Waiting List", 0);
	
	        /* Generates Headers Cells */
	        sheet1.addCell(new Label(1, 0, "Waiting Candidates", headerCellFormat));
	        sheet1.addCell(new Label(5, 1,
	                "DayalBagh Education Institute- Agra(282005)", headerCellFormat));
	        sheet1.addCell(new Label(5, 2,
	                "List of Students for generating call list", headerCellFormat));
	        sheet1.addCell(new Label(5, 3, "Institute Name", headerCellFormat));
	        sheet1.addCell(new Label(6, 3, entity_name, headerCellFormat));
	        sheet1.addCell(new Label(5, 4, "Program Name", headerCellFormat));
	        sheet1.addCell(new Label(6, 4, program_name, headerCellFormat));
	        if(subject_code.equals("All")){	        	
	        	 sheet1.addCell(new Label(5, 5, "Subject Name", headerCellFormat));
	 	         sheet1.addCell(new Label(6, 5, subject_name, headerCellFormat));
	        }	      
	        currentRow = 8;
	        
	        sheet1.addCell(new Label(1, 7, "Rank", headerCellFormat));
	        sheet1.addCell(new Label(2, 7, "Registration Number", headerCellFormat));
	        sheet1.addCell(new Label(3, 7, "Test Number", headerCellFormat));
	        sheet1.addCell(new Label(4, 7, "Student Name", headerCellFormat));
	        sheet1.addCell(new Label(5, 7, "Category", headerCellFormat));
	        if(gender.equals("Y")){	        	
	        	sheet1.addCell(new Label(6, 7, "Gender", headerCellFormat));	        	
	        	 for (int ll = 0; ll < headingList.size(); ll++) {	
	        		 if(headingList.get(ll).getComponent_id().equalsIgnoreCase("AS")){
	        			 sheet1.addCell(new Label(7+ll, 7, "CallMerit", headerCellFormat));
	        		 }
	        		 else{
	        			 sheet1.addCell(new Label(7+ll, 7, headingList.get(ll).getComponent_description(), headerCellFormat));	
	        		 }	 	            	 	           
	 	        }
	        	 dlen=7+headingList.size();
	        }
	        else{	  
	        	for (int ll = 0; ll<headingList.size(); ll++) {	
		        	 if(headingList.get(ll).getComponent_id().equalsIgnoreCase("AS")){
	        			 sheet1.addCell(new Label(6+ll, 7, "CallMerit", headerCellFormat));
	        		 }
	        		 else{
	        			 sheet1.addCell(new Label(6+ll, 7, headingList.get(ll).getComponent_description(), headerCellFormat));	
	        		 }		        
	        	}
	        	dlen=6+headingList.size();
	        }
	        sheet1.addCell(new Label(dlen, 7, "ComputedMarks", headerCellFormat));

	        /* Generates Data Cells */
	        currentRow = 8;
	        currentRow = currentRow + 2;
	        sheet1.addCell(new Label(1, currentRow,
	                "***********Waiting List for All Categories******************* ",
	                dataCellFormat));
	        currentRow = currentRow + 2;
	
	        Iterator<DataBean> i12 = list1.iterator();
	        categ = "";
	        l = 0;
	        x1 = 0;
	        temp = 0;
	        int catIndex=0;
	        while (i12.hasNext()) {
	            DataBean db2 = (DataBean) i12.next();	
	            if (count == 2) {
	                if (x1 == 2) {
	                    if (temp == 0) {
	                        if ((db2.getSlnum() == 1) &&
	                                (!categ.equalsIgnoreCase(""))) {
	                            sheet1.addCell(new Label(1, currentRow,
	                                    "Waiting List for " + db2.getCategory(),
	                                    dataCellFormat));
	                            currentRow++;
	                            temp++;
	                        }
	                    } else {
	                        if ((db2.getSlnum() == 1) &&
	                                (!categ.equalsIgnoreCase(db2.getCategory())) &&
	                                (!categ.equalsIgnoreCase(""))) {
	                            sheet1.addCell(new Label(1, currentRow,
	                                    "Waiting List for " + db2.getCategory(),
	                                    dataCellFormat));
	                            currentRow++;
	                        }
	                    }
	                }
	            } else {
	                if (temp == 0) {
	                    if ((db2.getSlnum() == 1) && (!categ.equalsIgnoreCase(""))) {
	                        sheet1.addCell(new Label(1, currentRow,
	                                "Waiting List for " + db2.getCategory(),
	                                dataCellFormat));
	                        currentRow++;
	                        temp++;
	                    }
	                } else {
	                    if ((db2.getSlnum() == 1) &&
	                            (!categ.equalsIgnoreCase(db2.getCategory())) &&
	                            (!categ.equalsIgnoreCase(""))) {
	                        sheet1.addCell(new Label(1, currentRow,
	                                "Waiting List for " + db2.getCategory(),
	                                dataCellFormat));
	                        currentRow++;
	                    }
	                }
	            }
	            if ((count == 2) && (l < 2) && (db2.getSlnum() == 1)&& (db2.getCategory().substring(2, 4).equalsIgnoreCase( catstr[l].substring(2, 4))) && (catIndex<2)) {
	                sheet1.addCell(new Label(1, currentRow,
	                        "Waiting  List for " + catstr[l], dataCellFormat));
	                l++;
	                currentRow++;
	                x1++;
	                catIndex++;
	            } 
	            else if((count == 2) && (l < 2) && (db2.getSlnum() == 1) && !(db2.getCategory().substring(2, 4).equalsIgnoreCase( catstr[l].substring(2, 4))) && (catIndex<2)){
	            	 sheet1.addCell(new Label(1, currentRow,
		                        "Waiting  List for " + catstr[l+1], dataCellFormat));
		                l++;
		                currentRow++;
		                x1=x1+2;
		                catIndex=catIndex+2;
	            }
	            else {
	                if (db2.getSlnum() == 1) {
	                    if (l == 0) {
	                        String catg = "";
	
	                        for (int l1 = 0; l1 < category.length; l1++) {
	                            if (category[l1].substring(0, 2)
	                                                .equalsIgnoreCase("GN")) {
	                                catg = category[l1];
	                            }
	                        }
	                        if(catg.equals("")){
	                        	 catg = category[0];
	                        }
	                        sheet1.addCell(new Label(1, currentRow,
	                                "Waiting List for  " + catg, dataCellFormat));
	                        l++;
	
	                        x1++;
	                        currentRow++;
	                    } // if b ends
	                } // if db1.getSlnum ends
	            }
	
	            sheet1.addCell(new Label(1, currentRow,
	                    String.valueOf(db2.getSlnum()), dataCellFormat));
	            sheet1.addCell(new Label(2, currentRow,
	                    db2.getRegistrationNumber(), dataCellFormat));
	            sheet1.addCell(new Label(3, currentRow,
	                    String.valueOf(db2.getTestNumber()), dataCellFormat));
	            sheet1.addCell(new Label(4, currentRow, db2.getStudent_name(),
	                    dataCellFormat));
	            sheet1.addCell(new Label(5, currentRow, db2.getCategory(),
	                    dataCellFormat));
	            
	            det1=db2.getComponentDetail();
	            
	            if(gender.equals("Y")){
	            	sheet1.addCell(new Label(6, currentRow, db2.getGender(), dataCellFormat));	
	            	for(int k=0;k<headingList.size();k++){
	            		 for (int ll = 0; ll < det1.size(); ll++) {		            			 
	            			 if(headingList.get(k).getComponent_id().equals(det1.get(ll).getComponent_id())){
	            				 if(det1.get(ll).getComponent_id().equalsIgnoreCase("AS")){
				        			 sheet1.addCell(new Label(7+k, currentRow,  String.valueOf(db2.getPercentage()), dataCellFormat));
				        		 }
				        		 else{				        			
				        			 double compnentMarks=Double.parseDouble(det1.get(ll).getMarks());
									 double weightagePercentage=det1.get(ll).getComponent_Weightage();
									 double computedMarks=(compnentMarks*weightagePercentage)/100;
				        			 sheet1.addCell(new Label(7+k, currentRow, String.valueOf(computedMarks), dataCellFormat));	
				        		 }	
	            				 break;
	            			 }
			        		 	        		
			 	        }
	            	}
	            	dlen12=7+headingList.size();
 		        }
 		        else{
 		         	for(int k=0;k<headingList.size();k++){
	            		 for (int ll = 0; ll < det1.size(); ll++) {		            			 
	            			 if(headingList.get(k).getComponent_id().equals(det1.get(ll).getComponent_id())){
	            				 if(det1.get(ll).getComponent_id().equalsIgnoreCase("AS")){
				        			 sheet1.addCell(new Label(6+k, currentRow,  String.valueOf(db2.getPercentage()), dataCellFormat));
				        		 }
				        		 else{				        			
				        			 double compnentMarks=Double.parseDouble(det1.get(ll).getMarks());
									 double weightagePercentage=det1.get(ll).getComponent_Weightage();
									 double computedMarks=(compnentMarks*weightagePercentage)/100;
				        			 sheet1.addCell(new Label(6+k, currentRow, String.valueOf(computedMarks), dataCellFormat));	
				        		 }	
	            				 break;
	            			 }
			        		 	        		
			 	        }
	            	}
 		         	dlen12=6+headingList.size();
 		        }	
	            
	            double comp12 = db2.getTotalMarks();
	            sheet1.addCell(new Label(dlen12, currentRow,String.valueOf(comp12), dataCellFormat));	
	            currentRow++;
	            categ = db2.getCategory();
	        }
        }
        /* Write & Close Excel WorkBook */
        workBook.write();
        workBook.close();
        return outputStream;
    }

    public List<DataBean> getUsers(String university_id,
        String entity_name, String program_name, String subject_code) {
        return methodProgramList(university_id, entity_name,
            program_name, subject_code);
    }
}
