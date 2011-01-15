package in.ac.dei.edrp.server;

import in.ac.dei.edrp.client.CM_entityInfoGetter;
import in.ac.dei.edrp.client.ReportInfoGetter;
import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ReportingFunction {

	
	SqlMapClient client = SqlMapManager.getSqlMapClient();
	
	Log4JInitServlet logObj = new Log4JInitServlet();
	
	/*
	 * get entity_id according to entity_name and university_id
	 */

		public String getEntity_description(String university_id,String entity_type){
		
		String result=null;
		try{
			CM_entityInfoGetter entitydesc=new CM_entityInfoGetter();
			entitydesc.setUniversity_id(university_id);
			entitydesc.setEntity_type(entity_type);
			
			List<CM_entityInfoGetter> li=client.queryForList("getentityDescription",entitydesc);
			for(CM_entityInfoGetter bid:li){
				result=bid.getEntity_description();
			}
		}catch(Exception e){
			logObj.logger.info("Error while getting Entity Descrition: "+e.getMessage()); 	
		}
		return result;
		}//getBranch
	public String getEntity_Name(String entity_id,String university_id){
		
		String result=null;
		try{
			CM_entityInfoGetter entity=new CM_entityInfoGetter();
			entity.setEntity_id(entity_id);
			//entity.setOffered_by(entity_id);
			entity.setUniversity_id(university_id);
			List<ReportInfoGetter> li=client.queryForList("getReportEntityName",entity);
			System.out.println("sgghega "+li.size());
			for(ReportInfoGetter entityid:li){
				result=entityid.getEntity_name();
				System.out.println("sgghega "+result);
			}


		}catch(Exception e){
			logObj.logger.info("Error while getting Entity Name: "+e.getMessage());
		}
		
		return result;
	}//Entity_id ends

	/*
	 * get program_id according to entity_id and program_name
	 */
	public String getProgram_Name(String entity_id,String program_id){
		
		String result=null;
		try{
			ReportInfoGetter program=new ReportInfoGetter();
			program.setOffered_by(entity_id);
			program.setProgram_id(program_id);
			List<ReportInfoGetter> li=client.queryForList("getProgramName",program);
			for(ReportInfoGetter programid:li){
				result=programid.getProgram_name();
			}

		}catch(Exception e){
			logObj.logger.info("Error while getting Program Name: "+e.getMessage());
		}
		return result;
	}//getProgram_id ends
	
	/*
	 * get Branch_Id according to branch_name
	 */
	public String getBranch_Name(String university_id,String branch_id){
		
		String result=null;
		try{
			ReportInfoGetter branchId=new ReportInfoGetter();
			branchId.setUniversity_id(university_id);
			branchId.setBranch_code(branch_id);
			List<ReportInfoGetter> li=client.queryForList("getBranchName",branchId);
			for(ReportInfoGetter bid:li){
				result=bid.getBranch_name();
			}
		}catch(Exception e){
			logObj.logger.info("Error while getting Branch Name: "+e.getMessage());
		}
		return result;
	}//getBranch
	
	public String[] getCategoryCos(ReportInfoGetter export) {
		// TODO Auto-generated method stub
		String[] category1=null;
		try{
		List<ReportInfoGetter> li=client.queryForList("getCosCount",export);
		System.out.println("cos="+li.size());
		for(ReportInfoGetter coscount:li){
			category1=new String[coscount.getCount()];
		}
		//System.out.println("Coming here");
		int catrslen=0;
		List<ReportInfoGetter> li1=client.queryForList("getCosValue",export);
		for(ReportInfoGetter cosvalue:li1){
			category1[catrslen]=cosvalue.getCos_value();
			System.out.println("cos="+cosvalue.getCos_value());
			catrslen++;
		}
		}catch(Exception e){
			logObj.logger.info("Error while getting COS Value: "+e.getMessage());
			System.out.println(e.getMessage());
		}
		return category1;
	}


	public void updateCalledStudentTest(String name, ReportInfoGetter entityInfo,String flag,String[] udate) {
		// TODO Auto-generated method stub
		try{
			//System.out.println("Coming dear in updatecalllist");
			ReportInfoGetter called=entityInfo;
//			called.setEntity_id(entity_id);
//			called.setProgram_id(program_id);
//			called.setBranch_code(branch_code);
			called.setRegistration_number(name);
			called.setCalled(flag);
			//setting session start date and session end date
			called.setStart_date(udate[0]);
			called.setEnd_date(udate[1]);
			
			client.update("updatecalledFlag", called);
			
		}catch(Exception e){
			logObj.logger.info("Error while updating CALLED flag in student_test_number: "+e.getMessage());
		}
	}


	public String[] getUserDetail(String name,ReportInfoGetter entityInfo) {
		// TODO Auto-generated method stub
		String[] userdetail=null;
		//System.out.println("Coming here in user detail");
		//Connection con=null;
		try {
			ReportInfoGetter exportuser=entityInfo;
			
			exportuser.setRegistration_number(name);
			//setting session start date and end date
//			exportuser.setStart_date(udate[0]);
//			exportuser.setEnd_date(udate[1]);
			
			List<ReportInfoGetter> li=client.queryForList("getRegistrationStudentId", exportuser);
			for(ReportInfoGetter sid:li){
			exportuser.setStudent_id(sid.getStudent_id());
			}
			
			List<ReportInfoGetter> li1=client.queryForList("getRegistrationDetails", exportuser);

			String fullname="";
			String date="";
			String cat="";
			String gen="";
			String city="";
			for(ReportInfoGetter user:li1){
				String middle_name=user.getMiddle_name();
				String last_name=user.getLast_name();
				if(user.getMiddle_name()==null){
					middle_name="";
				}
				if(user.getLast_name()==null){
					last_name="";
				}
			fullname=user.getFirst_name()+" "+middle_name+" "+last_name;
			date=user.getDate_of_birth();
			cat=getCategorydescription(user.getCategory());
			gen=user.getGender();
			city=user.getCity();
			}
			userdetail=new String[]{fullname,date,cat,gen,city};
			//System.out.println("Computed Marks "+userdetail);
		} catch (Exception e) {
			logObj.logger.info("Error while getting User detail: "+e.getMessage());
		}

		return userdetail;
	}


	
	public String getCategorydescription(String category) {
		// TODO Auto-generated method stub
		String description="";
		try{
			ReportInfoGetter desc=new ReportInfoGetter();
			desc.setCategory(category);
			List<ReportInfoGetter> li=client.queryForList("getCategoryDescription", desc);
			for(ReportInfoGetter catdesc:li){
				description=catdesc.getDescription();
			}
		}catch(Exception e){
			logObj.logger.info("Error while getting cetegory descrition for : "+category+", "+e.getMessage());
		}
		
		return description;
	}

	public String[][] getFullyComputedmarks(String name, ReportInfoGetter entityInfo,String[] udate) {
		// TODO Auto-generated method stub
		String s[][]=null;
		//System.out.println("Coming here in fully computed");
		try {
			ReportInfoGetter getFullCompute=entityInfo;
			getFullCompute.setRegistration_number(name);
//			getFullCompute.setProgram_id(program_id);
//			getFullCompute.setEntity_id(entity_id);
//			getFullCompute.setBranch_code(branch_code);
			getFullCompute.setFlag("S");
			
			//setting start date and end date
			getFullCompute.setStart_date(udate[0]);
			getFullCompute.setEnd_date(udate[1]);
			
			List<ReportInfoGetter> li=client.queryForList("getExcelComponentCount", getFullCompute);
			
			int length=0;
			
			for(ReportInfoGetter excelcomp:li){
				length=excelcomp.getCount();
			}
			
			List<ReportInfoGetter> li1=client.queryForList("getExcelComponentMarksValue", getFullCompute);
			
			s=new String[length][4];
			int i=0;
			for(ReportInfoGetter excelcomp:li1){
				
				//s[i][0]=String.valueOf(excelcomp.getComponent_id());	
				s[i][0]=getComponentDescription(excelcomp.getComponent_id(),name,entityInfo);
				s[i][1]=getBoardDescription(String.valueOf(excelcomp.getBoard_id()));
				if(String.valueOf(excelcomp.getMarks_percentage()).equalsIgnoreCase(null)){
					s[i][2]=String.valueOf(0.0);
				}
				else{
				s[i][2]=String.valueOf(excelcomp.getMarks_percentage());
				}
				s[i][3]=String.valueOf(excelcomp.getComputed_Marks());
				
			i++;
			}
			
		} catch (Exception e) {
			logObj.logger.info("Error while getting fully computed marks for : "+name+", "+e.getMessage());
			//System.out.println("Exception in getFully "+e.getMessage());
		}
		
		return s;
	}
		
	public String getBoardDescription(String board_id) {
		// TODO Auto-generated method stub
		String boardName="None";
		try{
			ReportInfoGetter ceigboard=new ReportInfoGetter();
			ceigboard.setBoard_id(board_id);
			List<ReportInfoGetter> board=client.queryForList("getBoardDescription",ceigboard);
			for(ReportInfoGetter boarddescription:board){
				boardName=boarddescription.getDescription();
			}
		}catch(Exception e){
			logObj.logger.info("Error while getting board description : "+e.getMessage());
			//System.out.println("Exception in geting board "+e.getMessage());
		}
		return boardName;
	}
	public String getComponentDescription(String component_id,String regnum,ReportInfoGetter entityInfo) {
		// TODO Auto-generated method stub
		String description=null;
		try{
			ReportInfoGetter desc=entityInfo;
			
			desc.setComponent_id(component_id);
			List<ReportInfoGetter> li;
			if(isUGorPGComponent(component_id)){
				desc.setRegistration_number(regnum);
				//setting start_date and end _date
//				desc.setStart_date(udate[0]);
//				desc.setEnd_date(udate[1]);
				li=client.queryForList("getUGComponentDescription",desc);
			
			}
			else{
				li=client.queryForList("getComponentDescription",desc);
			}
			
			
			for(ReportInfoGetter descr:li){
				description=descr.getComponent_description();
			}
			
		}catch(Exception e){
			logObj.logger.info("Error while getting Component Descrition for : "+component_id+", registration"+regnum);
			logObj.logger.info("Exception is:  "+e.getMessage());
		}
		return description;
	}
	
	public boolean isUGorPGComponent(String component_id){
		boolean b=false;
		try{
		ReportInfoGetter ug=new ReportInfoGetter();
		ug.setComponent_id(component_id);
		List<ReportInfoGetter> li=client.queryForList("getugorpg", ug);
		for(ReportInfoGetter ugorpg:li){
			if(ugorpg.getComponent_description().equalsIgnoreCase("UG")||ugorpg.getComponent_description().equalsIgnoreCase("PG")){
				b=true;
			}
		}
		}catch(Exception e){
			logObj.logger.info("Error while getting Component Descrition for : "+component_id);
		}
		return b;
	}
	
	public boolean isInteger(String value) {
		// TODO Auto-generated method stub
		boolean b=false;
		if(Integer.parseInt(value)>=0 && Integer.parseInt(value)<=9){
			b=true;
		}
		return b;
	}

	/*
	 * Validation for update marksbutton
	 */
	public String isUpdated(String entity_id,String program_id,String branch_code,String[] udate) {
		
		String comp="N";
			try{
				//System.out.println("Coming inside validation!!");
				
				ReportInfoGetter update=new ReportInfoGetter();
				update.setProgram_id(program_id);
				update.setEntity_id(entity_id);
				update.setBranch_code(branch_code);
				//setting session start date and end date
				update.setStart_date(udate[0]);
				update.setEnd_date(udate[1]);
				
				List<ReportInfoGetter> li=client.queryForList("getResetFlag",update);
				for(ReportInfoGetter updateflag:li){
					if(!updateflag.getFlag_status().equals(null)){
						comp=updateflag.getFlag_status();
						System.out.println("comp: +comp");
					}
				}	
						
		}catch(Exception e){
			logObj.logger.info("Error while getting flag status for  : "+program_id+", "+entity_id+", "+branch_code);
		}
		return comp;
	}//isUpdate function ends here
	
	/*
	 * For validation purpose Insert values into control_register table
	 */
	public void updateControlReport(ReportInfoGetter entityInfo, String flag,String comp) {
		// TODO Auto-generated method stub
		//System.out.println("Coming here inside update control report!!");
		
		ReportInfoGetter updateControl=entityInfo;
		System.out.println("Updating: "+updateControl.getBranch_code());
		updateControl.setFlag(flag);
//		updateControl.setEntity_id(entity_id);
//		updateControl.setProgram_id(program_id);
//		updateControl.setBranch_code(branch_code);
	//setting session start_date and end_date
//		updateControl.setStart_date(udate[0]);
//		updateControl.setEnd_date(udate[1]);
		
		try{
			if(!comp.equalsIgnoreCase("N")){

				updateControl.setFlag_status(flag);
				client.insert("updateControlReport", updateControl);
				System.out.println("update successfully inside if: ");
			}
			else{
				System.out.println("inserted successfully inside else: ");
			updateControl.setFlag_status(flag);
			updateControl.setUser_id("Deepak Pandey");	

				client.insert("insertControlReport", updateControl);
				//System.out.println("inserted successfully inside else: ");
			}
			
		}catch(Exception e){
			logObj.logger.info("Error while inserting or updating flag for selected values  : ");
		}
	}
	
	public String[] getSessionDate(String university_id){
	
		String[] date=new String[2];
		try{
			ReportInfoGetter cdate=new ReportInfoGetter();
			cdate.setUniversity_id(university_id);
			List<ReportInfoGetter> li=client.queryForList("getuniversitydate", cdate);
			for(ReportInfoGetter udate:li){
				date[0]=udate.getUniversity_start_date();
				date[1]=udate.getUniversity_end_date();
			}
		}catch(Exception e){
			logObj.logger.info("Error while getting sesion start date and end date for selected university : "+university_id);
		}
		return date;
	}

	public double getComponetEligibleMarks(String component_id,String registration_number,
			ReportInfoGetter entityInfo) {
		// TODO Auto-generated method stub
		double marks=0.0;
		try{
			String[] udate=new String[]{entityInfo.getStart_date(),entityInfo.getEnd_date()};
			ReportInfoGetter comp_marks=entityInfo;
			comp_marks.setComponent_id(component_id);
			comp_marks.setCategory(getCategoryId(registration_number,entityInfo));
			List<ReportInfoGetter> li=client.queryForList("getEligible", comp_marks);
			for(ReportInfoGetter el_marks:li){
				marks=el_marks.getComponent_eligiblity();
			}
			
		}catch(Exception e){
			logObj.logger.info("Error while getting eligible for component marks for "+registration_number);
		}
		return marks;
	}
	
	public String getCategoryId(String registrationNumber,ReportInfoGetter entityInfo) {
		// TODO Auto-generated method stub
		String s=null;
		try{
			//System.out.println("Yes getCategory called");
		ReportInfoGetter getcategory = entityInfo;
		
		getcategory.setRegistration_number(registrationNumber);
		//setting start_date and finish_date
		//getcategory.setStart_date(udate[0]);
		//getcategory.setEnd_date(udate[1]);
		
		List<ReportInfoGetter> getLi = client.queryForList("getCategoryList", getcategory);
		for(ReportInfoGetter getE:getLi){
			 s=getE.getCos_value().substring(0, 2);
			 //System.out.println("value of category "+s);
		
		}
		}catch(Exception e){
			logObj.logger.info("Error while getting category id: "+registrationNumber);
		}
		return s;
	}
	public int getTotalComponentLength(ReportInfoGetter meritStudent) {
		// TODO Auto-generated method stub
		int length=0;
		try{
			ReportInfoGetter ceigTotalComponent=meritStudent;
			ceigTotalComponent.setFlag("S");
			ceigTotalComponent.setWeightage_flag("Y");
			List<ReportInfoGetter> totalList=client.queryForList("getTotalCompLength",ceigTotalComponent);
		for(ReportInfoGetter total:totalList){
			length=total.getCount();
		}
		}catch(Exception e){
			logObj.logger.info("Error while getting category id: "+e.getMessage());
			//System.out.println("Exception is e: "+e.getMessage());
		}
		return length;
	}
}
