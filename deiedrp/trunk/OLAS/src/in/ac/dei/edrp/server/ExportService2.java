package in.ac.dei.edrp.server;


import in.ac.dei.edrp.client.CM_entityInfoGetter;
import in.ac.dei.edrp.client.DataBean;
import in.ac.dei.edrp.client.ReportInfoGetter;
import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
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
 */
@SuppressWarnings("serial")
public class ExportService2{

	Log4JInitServlet logObj = new Log4JInitServlet();

	
	List<DataBean> list = new ArrayList<DataBean>();
	
	List<DataBean> list1 = new ArrayList<DataBean>();
	
	String[] heading=null;
	String[] category=null;
	String[] catstr=null;
	GreetingServiceImpl gsimpl=new GreetingServiceImpl();
	SqlMapClient client = SqlMapManager.getSqlMapClient();
	ReportingFunction reportfunction=new ReportingFunction();
	
	/**
	 * Method to create connection with database
	 */
	
	
		
	public List<DataBean> methodProgramList(String university_id,String entity_type,String entity_id,String program_id,String branch_code) {
		// TODO Auto-generated method stub
		
		
		
		try {
			
			//Connection con = createconnection();
//			String entity_id=reportfunction.getEntity_Id(entity_name, university_id);
//			
//			String program_id=reportfunction.getProgram_Id(entity_id, program_name);
//			
//			//System.out.println(branch_name+" of this branch");
//			String branch_code=reportfunction.getBranch_Id(branch_name);
			
			String[] udate=reportfunction.getSessionDate(university_id);
			
			//System.out.println("Entity_id in service: "+reportfunction.getEntity_Id(entity_name, university_id));
			//System.out.println("program id in service: "+reportfunction.getProgram_Id(entity_id, program_name));
			//System.out.println("branch_code in service: "+reportfunction.getBranch_Id(branch_name));
			
			ReportInfoGetter export2=new ReportInfoGetter();
			export2.setProgram_id(program_id);
			export2.setEntity_id(entity_id);
			export2.setBranch_code(branch_code);
			export2.setOffered_by(entity_id);
			//setting session start_date and end_date
			export2.setStart_date(udate[0]);
			export2.setEnd_date(udate[1]);
			
			category=reportfunction.getCategoryCos(export2);
			
			int count=0;
			catstr=new String[2];
			for(int len=0;len<category.length;len++){
				if(category[len].substring(0, 2).equalsIgnoreCase("GN")){
					System.out.println(category[len]);
					catstr[count]=category[len];
					count++;
				}
			}
			heading=getHeadLines(export2);
			
			if(count==2){
				System.out.println("coming inside if:");
				export2.setOffered_by(entity_id);
				
				
				List<ReportInfoGetter> li1;
				for(int i=0;i<catstr.length;i++){
					System.out.println("Coming");
					String cm="";
					int slnum=0;
					int slnum1=0;
					export2.setCos_value(catstr[i]);
					li1=client.queryForList("getCosSeats1",export2);
				System.out.println("Li: "+li1.size());
				
				int seats=0;
				for(ReportInfoGetter cos:li1){
					System.out.println(cos.getCos_seat()+" and "+catstr[i]);
					seats=cos.getCos_seat();
				}
				export2.setCos_value("__"+catstr[i].substring(2)+"%");
				List<ReportInfoGetter> li=client.queryForList("exportfinalmeritwithMF",export2);
				System.out.println("Returning list and size is in MF"+li.size()+catstr[i].substring(2));
				
					for(ReportInfoGetter finallist:li){
					//System.out.println("Coming here inside in MF");
					String[] qualify=updateQualifyStatus(export2,finallist.getRegistration_number());
					updateStatusinFinal(export2,finallist.getRegistration_number(),qualify);
					if(qualify[0].equalsIgnoreCase("Waiting")){
						
						String name=finallist.getRegistration_number();
						String test_number=finallist.getTest_number();
						String cos=finallist.getCategory();
						cm=String.valueOf(finallist.getSum_actual_computed_marks());
						double total_marks=finallist.getTotal_marks();
						String[] udetail=reportfunction.getUserDetail(name,export2);
						String sname=udetail[0];
						//int sum = 0;
						
						//System.out.println("Coming here in MF");
						//System.out.println("error is "+heading.length);
						String[] sam=addMarks(name,export2,heading.length);
						//System.out.println("Coming here1");
						slnum++;
						//System.out.println("Coming here::"+slnum+" and "+rs4.getInt(1));
						if(slnum<=seats){
							//System.out.println("Adding for fresh list "+name);
							qualify[0]="selected";
							qualify[1]="Congratulations,you are selected ";
							updateStatusinFinal(export2,finallist.getRegistration_number(),qualify);
						list.add(new DataBean(slnum,name,test_number ,cos,sname,cm, sam, 
								total_marks));
						}
						else{
							//System.out.println("Adding for waiting "+name);
						slnum1++;
						list1.add(new DataBean(slnum1,name,test_number,cos,sname ,cm, sam, 
								total_marks));
						}//else ends for adding waiting
					}//waiting if ends


					}//registration_number finallist ends

			}//int i=0 ends loop
				
			}//if closed count==2
			else{
				System.out.println("Coming inside else");
				String cm="";
				int slnum=0;
				int slnum1=0;
				export2.setOffered_by(entity_id);
				export2.setCos_value("GN%");
				List<ReportInfoGetter> li1=client.queryForList("getCosSeats1",export2);
				
				System.out.println("l11 size"+li1.size());
				int seats=0;
				for(ReportInfoGetter cos:li1){
					System.out.println(cos.getCos_seat());
					seats=cos.getCos_seat();
				}
				export2.setCos_value("%");
				List<ReportInfoGetter> li=client.queryForList("exportfinalmeritwithMF",export2);
				System.out.println("Returning list and size is in else"+li.size());
				
					for(ReportInfoGetter finallist:li){
					//System.out.println("Coming here inside in else");
					String[] qualify=updateQualifyStatus(export2,finallist.getRegistration_number());
					updateStatusinFinal(export2,finallist.getRegistration_number(),qualify);
					if(qualify[0].equalsIgnoreCase("Waiting")){
						
						String name=finallist.getRegistration_number();
						String test_number=finallist.getTest_number();
						String cos=finallist.getCategory();
						cm=String.valueOf(finallist.getSum_actual_computed_marks());
						double total_marks=finallist.getTotal_marks();
						String[] udetail=reportfunction.getUserDetail(name,export2);
						String sname=udetail[0];
						int sum = 0;
						
						//System.out.println("Coming here in MF");
						//System.out.println("error is "+heading.length);
						String[] sam=addMarks(name,export2,heading.length);
						//System.out.println("Coming here1");
						slnum++;
						//System.out.println("Coming here::"+slnum+" and "+rs4.getInt(1));
						if(slnum<=seats){
							//System.out.println("Adding for fresh list "+name);
							qualify[0]="selected";
							qualify[1]="Congratulations,you are selected ";
							updateStatusinFinal(export2,finallist.getRegistration_number(),qualify);
						list.add(new DataBean(slnum,name,test_number ,cos,sname,cm, sam, 
								total_marks));
						}
						else{
							//System.out.println("Adding for waiting "+name);
						slnum1++;
						list1.add(new DataBean(slnum1,name,test_number,cos,sname ,cm, sam, 
								total_marks));
						}//else ends for adding waiting
					}//waiting if ends


					}//registration_number finallist ends
				
			}
			
			for(int catlen=0;catlen<category.length;catlen++){
				
				if(!(category[catlen].substring(0, 2).equalsIgnoreCase("GN"))){
				String cm="";
				int slnum=0;
				int slnum1=0;
				export2.setOffered_by(entity_id);
				export2.setCos_value(category[catlen]);
				export2.setStatus("Waiting");
				List<ReportInfoGetter> li=client.queryForList("getFinalRegistrationNumberWithStatus", export2);
				//System.out.println("Returning list and size is "+li.size());
				List<ReportInfoGetter> li2=client.queryForList("getCosSeats",export2);
				int seats=0;
				for(ReportInfoGetter cos:li2){
					//System.out.println(cos.getCos_seat());
					seats=cos.getCos_seat();
				}
				//String lens[] = new String[heading.length];
				for(ReportInfoGetter finallist:li){
					//System.out.println("Coming here inside category");
					String[] qualify=updateQualifyStatus(export2,finallist.getRegistration_number());
					updateStatusinFinal(export2,finallist.getRegistration_number(),qualify);
					if(qualify[0].equalsIgnoreCase("Waiting")){
						String name=finallist.getRegistration_number();
						String test_number=finallist.getTest_number();
						cm=String.valueOf(finallist.getSum_actual_computed_marks());
						double total_marks=finallist.getTotal_marks();
						String[] udetail=reportfunction.getUserDetail(name,export2);
						String sname=udetail[0];
						
						
						//System.out.println("Coming here in category");
						//System.out.println("error is "+heading.length);
						String[] sam=addMarks(name,export2,heading.length);
						//System.out.println("Coming here1");
						slnum++;
						//System.out.println("Coming here::"+slnum+" and "+rs4.getInt(1));
						if(slnum<=seats){
							//System.out.println("Adding for fresh list "+name);
							qualify[0]="selected";
							qualify[1]="Congratulations,you are selected ";
							updateStatusinFinal(export2,finallist.getRegistration_number(),qualify);
						list.add(new DataBean(slnum,name,test_number ,category[catlen],sname,cm, sam, 
								total_marks));
						}//if ends
						else{
							//System.out.println("Adding for waiting "+name);
						slnum1++;
//						list.add(new DataBean(slnum1,name,test_number,category[catlen] ,cm, sam, 
//								total_marks));
						list1.add(new DataBean(slnum1,name,test_number,category[catlen],sname ,cm, sam, 
								total_marks));
						}//else ends
					}//if ends
				}//finallist category ends
				}//if GN ends
			}//category loop ends
				
		} catch (Exception e) {
			logObj.logger.info("Exception in getting list for final merit list: "+e.getMessage()); 
		}// catch ends
		Iterator i=list.iterator();
		while(i.hasNext()){
			//System.out.println("---------------------------------------------");
		DataBean gd=(DataBean)i.next();
		//System.out.print(gd.getRegistrationNumber()+"	|");
		//System.out.print(gd.getTestNumber()+"	|");
		String[] s2=gd.getDetail();
			for(int k=0;k<s2.length;k++){
				//System.out.print(s2[k]+"	|");
			}
		//System.out.print(gd.getTotalMarks()+"	|");	
			//System.out.println();
		}
		
		return list;
	}
	
	private void updateStatusinFinal(ReportInfoGetter export2,
			String registration_number, String[] qualify) {
		// TODO Auto-generated method stub
		try{
			ReportInfoGetter update=new ReportInfoGetter();
			update=export2;
			update.setRegistration_number(registration_number);
			update.setStatus(qualify[0]);
			update.setReason_code(qualify[1]);
			client.update("updatestatusfinal", update);
			//System.out.println("update status final completed ");
		}catch(Exception e){
			System.out.println("Exception in updateStatusFinal in ExportService "+e.getMessage());
		}
		
	}


	private String[] updateQualifyStatus(ReportInfoGetter export2,String regnum) {
		// TODO Auto-generated method stub
		
		String[] qualify=new String[2];
		qualify[0]="waiting";
		qualify[1]="You are qualified";
		try{
			export2.setRegistration_number(regnum);
			List<ReportInfoGetter> li=client.queryForList("getfinalmarks", export2);
			for(ReportInfoGetter finaluser:li){
			//System.out.println("Component_id "+finaluser.getComponent_id());
			export2.setComponent_id(finaluser.getComponent_id());
			List<ReportInfoGetter> li1=client.queryForList("getattendanceflag", export2);
			for(ReportInfoGetter flag:li1){
				//System.out.println("Attendance Flag "+flag.getAttendance_flag()+" attended or not "+finaluser.getAttendance());
				if(flag.getAttendance_flag().equalsIgnoreCase("Y") && finaluser.getAttendance().equalsIgnoreCase("A")){
						qualify[0]="Disqualify";
						qualify[1]="You are absent in "+flag.getComponent_description();
						break;						
				}
			}
			}
		}catch(Exception e){
			System.out.println("Exception in update qualify status "+e.getMessage());
		}
		//System.out.println("Qualify status "+qualify[0]+" and message "+qualify[1]);
		return qualify;
	}


	public String[] getHeadLines(ReportInfoGetter report) {
		// TODO Auto-generated method stub
		String[] heading1=null;
		try{
			ReportInfoGetter reportInfo=report;
			reportInfo.setOffered_by(report.getEntity_id());
			List<ReportInfoGetter> li=client.queryForList("getCountComponentDescription",reportInfo);
			
			for(ReportInfoGetter countvalue:li){
				heading1 = new String[countvalue.getCount()];
			}
			
			List<ReportInfoGetter> li1=client.queryForList("getComponentDescriptionValue",reportInfo);
			int i=0;
			for(ReportInfoGetter countdesc:li1){
				heading1[i] =countdesc.getComponent_description();
				i++;
			}

		}catch(Exception e){
			System.out.println("Coming here in exception e"+e.getMessage());
		}
		//System.out.println("Heading length "+heading1.length+" and value "+heading1[0]);
		return heading1;
	}


	/*
	 * for getting total edit marks
	 */
	public  String[] addMarks(String regnumber,ReportInfoGetter export2,int len) {
		
		String[] call = new String[len];
		try {
			//System.out.println("************Add Marks Called***************");
			ReportInfoGetter finalmarks=export2;
//			finalmarks.setProgram_id(program_id);
//			finalmarks.setEntity_id(entity_id);
//			finalmarks.setBranch_code(branch_code);
			finalmarks.setRegistration_number(regnumber);
			//setting start_date and end_date
			//finalmarks.setStart_date(udate[0]);
			//finalmarks.setEnd_date(udate[1]);
			
			//get marks for components in asc order
			List<ReportInfoGetter> li=client.queryForList("getfinalmarks", finalmarks);
			//System.out.println("Add marks list size is "+li.size());
			
			int i=0;
			for(ReportInfoGetter cmarks:li){
				if(cmarks.getMarks()==null){
					call[i]="0";
					//System.out.println("in add program "+call[i]);
				}
				else{
					call[i]=cmarks.getMarks();
					//System.out.println("in add program "+call[i]);
				}
				i++;
			}
			if(i<len){
				while(i<len){
					call[i]="0";
					//System.out.println("in add program "+call[i]);
					i++;
				}

			}
			if(!(list.size()==0)){
				reportfunction.updateControlReport(export2,"F", "E");
			}
		} catch (Exception e) {
			System.out.println("Error message in add marks" + e.getMessage());
		}
		//System.out.println("component marks "+call[0]);
		return call;

	}

	
	public  ByteArrayOutputStream generateExcelReport(String university_id,String entity_type,String entity_id,String program_id,String branch_code) throws IOException, WriteException {
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
		String branch_name=reportFunction.getBranch_Name(university_id,branch_code);
		
		list=getUsers(university_id,entity_type,entity_id,program_id,branch_code);
		
		sheet.addCell(new Label(1, 0, "Selected Candidates", headerCellFormat));
		sheet.addCell(new Label(5, 1, "DayalBag Education Institute- Agra(282005)", headerCellFormat));
		sheet.addCell(new Label(5, 2, "List of Students for generating call list", headerCellFormat));
		sheet.addCell(new Label(5, 3, "Institute Name", headerCellFormat));
		sheet.addCell(new Label(6, 3, entity_name, headerCellFormat));
		sheet.addCell(new Label(5, 4, "Program Name", headerCellFormat));
		sheet.addCell(new Label(6, 4, program_name, headerCellFormat));
		sheet.addCell(new Label(5, 5, "Branch Name", headerCellFormat));
		sheet.addCell(new Label(6, 5, branch_name, headerCellFormat));
		
		Iterator i=list.iterator();
		i.hasNext();
			DataBean db=(DataBean)i.next();
			String name=db.getRegistrationNumber();
			sheet.addCell(new Label(1, 7, "Rank", headerCellFormat));
			sheet.addCell(new Label(2, 7, "Registration Number", headerCellFormat));
			sheet.addCell(new Label(3, 7, "Test Number", headerCellFormat));
			sheet.addCell(new Label(4, 7, "Stduent Name", headerCellFormat));
			sheet.addCell(new Label(5, 7, "Category", headerCellFormat));
			sheet.addCell(new Label(6, 7, "CallMerit", headerCellFormat));
			
			//System.out.print(name+" | ");
			
			//String[] det=db.getDetail();
			int dlen=7;
			for(int l=0,j=0;l<heading.length;l++){
				sheet.addCell(new Label(dlen, 7, heading[l], headerCellFormat));
				
				dlen++;
			}
			
			double comp=db.getTotalMarks();
			sheet.addCell(new Label(dlen, 7, "ComputedMarks", headerCellFormat));
			
			
		//}

		/* Generates Data Cells */
		WritableFont dataFont = new WritableFont(WritableFont.TAHOMA, 8);
		WritableCellFormat dataCellFormat = new WritableCellFormat(dataFont);
		
		int currentRow = 8;
		/*for (DataBean user : getUsers()) {
			sheet.addCell(new Label(1, currentRow, user.getRegistrationNumber(),dataCellFormat));
			sheet.addCell(new Label(2, currentRow, user.getFirstName(),dataCellFormat));
			currentRow++;
		}*/
		
		
		
		String[][] data1=null;
		String[] det1=null;

			Iterator<DataBean> i11=list.iterator();
			//int cate=0;
			//int count=0;
			//int cat=0;
			String categ="";
			
			//String entity_id=reportfunction.getEntity_Id(entity_name, university_id);
			
			//String program_id=reportfunction.getProgram_Id(entity_id, program_name);
			
			//System.out.println(branch_name+" of this branch");
			//String branch_code=reportfunction.getBranch_Id(branch_name);
			
			String[] udate=reportfunction.getSessionDate(university_id);
			
			ReportInfoGetter export2=new ReportInfoGetter();
			export2.setProgram_id(program_id);
			export2.setEntity_id(entity_id);
			export2.setBranch_code(branch_code);
			//setting start_date and end_date
			export2.setStart_date(udate[0]);
			export2.setEnd_date(udate[1]);
			
			category=reportfunction.getCategoryCos(export2);
			
			int count=0;
			String[] catstr=new String[2];
			for(int len=0;len<category.length;len++){
				if(category[len].substring(0, 2).equalsIgnoreCase("GN")){
					catstr[count]=category[len];
					count++;
				}
			}
			
			int l=0;
			int x1=0;
			int temp=0;
			while(i11.hasNext()){
				//System.out.println("---------------------------------------------");
			DataBean db1=(DataBean)i11.next();
			
			if(count==2){
				if(x1==2){
					if(temp==0){
						if(db1.getSlnum()==1 && (!categ.equalsIgnoreCase(""))){
							sheet.addCell(new Label(1, currentRow,"Selected List for " +db1.getCategory(),dataCellFormat));
							currentRow++;
							temp++;
						}
					}
					else{
				if(db1.getSlnum()==1 && (!categ.equalsIgnoreCase(db1.getCategory())) && (!categ.equalsIgnoreCase(""))){
	
					sheet.addCell(new Label(1, currentRow,"Selected List for " +db1.getCategory(),dataCellFormat));
					currentRow++;
				}
					}
				}
			}
			else{
				if(temp==0){
					if(db1.getSlnum()==1 && (!categ.equalsIgnoreCase(""))){
						sheet.addCell(new Label(1, currentRow,"Selected List for " +db1.getCategory(),dataCellFormat));
						currentRow++;
						temp++;
					}
				}
				else{
					if(db1.getSlnum()==1 && (!categ.equalsIgnoreCase(db1.getCategory())) && (!categ.equalsIgnoreCase(""))){

				sheet.addCell(new Label(1, currentRow,"Selected List for " +db1.getCategory(),dataCellFormat));
				currentRow++;
			}
				}
			
			}
			
			if(count==2 && l<2 && db1.getSlnum()==1){
				
				sheet.addCell(new Label(1, currentRow,"Selected List for count "+catstr[l],dataCellFormat));
				l++;
				currentRow++;
				x1++;
			}	
			else{
				//System.out.println("Coming1");
				if(db1.getSlnum()==1){
				if(l==0){
					//System.out.println("Coming");
					String catg="";
					for(int l1=0;l1<category.length;l1++){
						System.out.println("Coming here");
						if(category[l1].substring(0,2).equalsIgnoreCase("GN")){
							catg=category[l1];
							System.out.println("catg="+catg);
							break;
							//System.out.println("catg="+catg);
						}
					}
					sheet.addCell(new Label(1, currentRow,"Selected List for Selected List for count "+catg,dataCellFormat));
					l++;
				
				x1++;
				currentRow++;
				}//if l ends
				}//if db1.getSlnum ends
				
			}
			
			
			
			
			sheet.addCell(new Label(1, currentRow, String.valueOf(db1.getSlnum()),dataCellFormat));
			sheet.addCell(new Label(2, currentRow, db1.getRegistrationNumber(),dataCellFormat));
			sheet.addCell(new Label(3, currentRow,String.valueOf(db1.getTestNumber()),dataCellFormat));
			sheet.addCell(new Label(4, currentRow,db1.getStudent_name(),dataCellFormat));
			sheet.addCell(new Label(5, currentRow,db1.getCategory(),dataCellFormat));
			sheet.addCell(new Label(6, currentRow,String.valueOf(db1.getPercentage()),dataCellFormat));
			det1=db1.getDetail();
			int dlen1=7;
			for(int l1=0;l1<det1.length;l1++){
				
				sheet.addCell(new Label(dlen1, currentRow, det1[l1],dataCellFormat));
				dlen1++;
			}
			double comp1=db1.getTotalMarks();
			sheet.addCell(new Label(dlen1, currentRow,String.valueOf(comp1),dataCellFormat));
			
			currentRow++;
			categ=db1.getCategory();
			}
			//Another work sheet
			currentRow=0;
			WritableSheet sheet1 = workBook.createSheet("Waiting List", 0);

			/* Generates Headers Cells */
			
			
			sheet1.addCell(new Label(1, 0, "Waiting Candidates", headerCellFormat));
			sheet1.addCell(new Label(5, 1, "DayalBag Education Institute- Agra(282005)", headerCellFormat));
			sheet1.addCell(new Label(5, 2, "List of Students for generating call list", headerCellFormat));
			sheet1.addCell(new Label(5, 3, "Institute Name", headerCellFormat));
			sheet1.addCell(new Label(6, 3, entity_name, headerCellFormat));
			sheet1.addCell(new Label(5, 4, "Program Name", headerCellFormat));
			sheet1.addCell(new Label(6, 4, program_name, headerCellFormat));
			sheet1.addCell(new Label(5, 5, "Branch Name", headerCellFormat));
			sheet1.addCell(new Label(6, 5, branch_name, headerCellFormat));
			currentRow=8;
			i=list.iterator();
			i.hasNext();
				db=(DataBean)i.next();
				name=db.getRegistrationNumber();
				sheet1.addCell(new Label(1, 7, "Rank", headerCellFormat));
				sheet1.addCell(new Label(2, 7, "Registration Number", headerCellFormat));
				sheet1.addCell(new Label(3, 7, "Test Number", headerCellFormat));
				sheet1.addCell(new Label(4, 7, "Student Name", headerCellFormat));
				sheet1.addCell(new Label(5, 7, "Category", headerCellFormat));
				sheet1.addCell(new Label(6, 7, "CallMerit", headerCellFormat));
				
				//System.out.print(name+" | ");
				
				//String[] det=db.getDetail();
				dlen=7;
				for(int l1=0,j=0;l1<heading.length;l1++){
					sheet1.addCell(new Label(dlen, 7, heading[l1], headerCellFormat));
					
					dlen++;
				}
				
				comp=db.getTotalMarks();
				sheet1.addCell(new Label(dlen, 7, "ComputedMarks", headerCellFormat));
				
				
			//}

			/* Generates Data Cells */
			
			
			currentRow = 8;
			currentRow=currentRow+2;
			sheet1.addCell(new Label(1, currentRow,"***********Waiting List for All Categories******************* ",dataCellFormat));
			currentRow=currentRow+2;
			Iterator<DataBean> i12=list1.iterator();
			//int cate=0;
			//int count=0;
			//int cat=0;
			categ="";
			l=0;
			x1=0;
			temp=0;
			while(i12.hasNext()){
				//System.out.println("---------------------------------------------");
			DataBean db2=(DataBean)i12.next();
			
			if(count==2){
				if(x1==2){
					if(temp==0){
						if(db2.getSlnum()==1 && (!categ.equalsIgnoreCase(""))){
							sheet1.addCell(new Label(1, currentRow,"Waiting List for " +db2.getCategory(),dataCellFormat));
							currentRow++;
							temp++;
						}
					}
					else{
				if(db2.getSlnum()==1 && (!categ.equalsIgnoreCase(db2.getCategory())) && (!categ.equalsIgnoreCase(""))){
	
					sheet1.addCell(new Label(1, currentRow,"Waiting List for " +db2.getCategory(),dataCellFormat));
					currentRow++;
				}
					}
				}
			}
			else{
				if(temp==0){
					if(db2.getSlnum()==1 && (!categ.equalsIgnoreCase(""))){
						sheet1.addCell(new Label(1, currentRow,"Waiting List for " +db2.getCategory(),dataCellFormat));
						currentRow++;
						temp++;
					}
				}
				else{
					if(db2.getSlnum()==1 && (!categ.equalsIgnoreCase(db2.getCategory())) && (!categ.equalsIgnoreCase(""))){

				sheet1.addCell(new Label(1, currentRow,"Waiting List for " +db2.getCategory(),dataCellFormat));
				currentRow++;
			}
				}
			
			
			}
			//System.out.println("count=="+count);
			if(count==2 && l<2 && db2.getSlnum()==1){
				
				sheet1.addCell(new Label(1, currentRow,"Waiting  List for count "+catstr[l],dataCellFormat));
				l++;
				currentRow++;
				x1++;
			}	
			else{
				if(db2.getSlnum()==1){
				if(l==0){
					String catg="";
					for(int l1=0;l1<category.length;l1++){
						if(category[l1].substring(0,2).equalsIgnoreCase("GN")){
							catg=category[l1];
						}
					}
					sheet1.addCell(new Label(1, currentRow,"Waiting List for count "+catg,dataCellFormat));
					l++;
				
				x1++;
				currentRow++;
				}//if b ends
				}//if db1.getSlnum ends
				
			}
			
			
			
			
			sheet1.addCell(new Label(1, currentRow, String.valueOf(db2.getSlnum()),dataCellFormat));
			sheet1.addCell(new Label(2, currentRow, db2.getRegistrationNumber(),dataCellFormat));
			sheet1.addCell(new Label(3, currentRow,String.valueOf(db2.getTestNumber()),dataCellFormat));
			sheet1.addCell(new Label(4, currentRow,db2.getStudent_name(),dataCellFormat));
			sheet1.addCell(new Label(5, currentRow,db2.getCategory(),dataCellFormat));
			sheet1.addCell(new Label(6, currentRow,String.valueOf(db2.getPercentage()),dataCellFormat));
			det1=db2.getDetail();
			int dlen12=7;
			for(int l1=0;l1<det1.length;l1++){
				
				sheet1.addCell(new Label(dlen12, currentRow, det1[l1],dataCellFormat));
				dlen12++;
			}
			double comp12=db2.getTotalMarks();
			sheet1.addCell(new Label(dlen12, currentRow,String.valueOf(comp12),dataCellFormat));
			
			currentRow++;
			categ=db2.getCategory();
			}
			
		
			
		
		/* Write & Close Excel WorkBook */
		workBook.write();
		workBook.close();

		return outputStream;
	}
	
	public List<DataBean> getUsers(String university_id,String entity_type,String entity_name,String program_name,String branch_name) {
		return methodProgramList(university_id,entity_type,entity_name,program_name,branch_name);
	}

}