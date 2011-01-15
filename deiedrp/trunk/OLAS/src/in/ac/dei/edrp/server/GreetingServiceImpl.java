package in.ac.dei.edrp.server;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.sql.*;
import in.ac.dei.edrp.client.ReportInfoGetter;
import in.ac.dei.edrp.client.DataBean;
import in.ac.dei.edrp.client.RPCFiles.*;
import in.ac.dei.edrp.client.Shared.*;
import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;
import in.ac.dei.edrp.shared.FieldVerifier;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.ibatis.sqlmap.client.SqlMapClient;



/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	private static final boolean CM_entityInfoGetter = false;


	Log4JInitServlet logObj = new Log4JInitServlet();
		
	ReportingFunction reportfunction=new ReportingFunction();
	SqlMapClient client = SqlMapManager.getSqlMapClient();
	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");
		return "Hello, " + input + "!<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
	}
	
	@Override
	public List<DataBean> updateMarks(String university_id,String entity_type,String entity_id,String program_id,String branch_id) {
		// TODO Auto-generated method stub
		//System.out.println("------------update marks running----------");
		boolean compute=false;
		
		List<DataBean> list1=new ArrayList<DataBean>();
		String comp=null;
		String[] branch_code=null;
		String eligibility="Eligible";
		String errRegistrationNumber="";
		try{
			
			//String entity_id=reportfunction.getEntity_Id(entity_name, university_id);
			
			//String program_id=reportfunction.getProgram_Id(entity_id, program_name);
			//System.out.println("branch_name: "+branch_name);
			//CM_entityInfoGetter branch=new CM_entityInfoGetter();
			//System.out.println("***************************************");
			//System.out.println("branch_name "+branch_name);
			branch_code=getBranch_code(entity_id,program_id, branch_id);
			
			String[] udate=reportfunction.getSessionDate(university_id);
			
			//System.out.println("***************************************");
			
			//System.out.println("Entity_id in update: "+reportfunction.getEntity_Id(entity_name, university_id));
			//System.out.println("program id in update: "+reportfunction.getProgram_Id(entity_id, program_name));
			//System.out.println("branch_code in update: "+getBranch_Id(branch_name, con));
			for(int len=0;len<branch_code.length;len++){
				List<ReportInfoGetter> list=new ArrayList<ReportInfoGetter>();
			comp=reportfunction.isUpdated(entity_id, program_id, branch_code[len],udate);
			System.out.println("Coming here!!"+branch_code[len]);
			if(comp.equalsIgnoreCase("R") || comp.equalsIgnoreCase("N")||comp.equalsIgnoreCase("C")||comp.equalsIgnoreCase("I")){
			
				System.out.println("Coming here just after if");
				ReportInfoGetter entityInfo = new ReportInfoGetter();
				entityInfo.setProgram_id(program_id);
				//entityInfo.setUniversity_id(university_id);
				entityInfo.setEntity_id(entity_id);
				entityInfo.setBranch_code(branch_code[len]);
				entityInfo.setType("S");
				entityInfo.setOffered_by(entity_id);
				
				entityInfo.setStart_date(udate[0]);
				entityInfo.setEnd_date(udate[1]);
				
					System.out.println("Entity_id inside update"+entity_id+": "+program_id+" : "+branch_code[len]+": "+udate[0]+": "+udate[1]);
					List<ReportInfoGetter> li = client.queryForList("updateMarksList", entityInfo);
					System.out.println("List: "+li.size());
				String registrationNumber=null;
				String temp="abc";
				String temp1="";
				String eligibility1="";
				String message1="";
			//while(rs.next()){
				String cos=null;
				String message="You are eligible";
				int k=0;
				int count=0;
				int val=0;
				for(ReportInfoGetter entity: li){
					//System.out.println("Registration Number in process: "+entity.getRegistration_number());
					registrationNumber=entity.getRegistration_number();
					
					errRegistrationNumber=registrationNumber;
					
					logObj.logger.info("Computed Marks for registration number"+registrationNumber); 
					
					if((!registrationNumber.equalsIgnoreCase(temp1))&&(!temp1.equalsIgnoreCase(""))){
						System.out.println("Adding:"+temp1);
						list.add(new ReportInfoGetter(program_id,entity_id,branch_code[len],temp1,cos,comp,eligibility1,message1));
						logObj.logger.info("Completed successfully started"+registrationNumber);
					}
					
					if(!registrationNumber.equalsIgnoreCase(temp)){
//						temp1=registrationNumber;
						count=0;
						k=0;
						val=0;
						message="you are eligible";
						eligibility="Eligible";
						temp=entity.getRegistration_number();
						//eligibility=getAgeEligibility(entity_id,program_id,branch_code[len],registrationNumber,udate);
						eligibility=getAgeEligibility(entityInfo,registrationNumber);
						if(eligibility.equalsIgnoreCase("InEligible")){
							message="Overage";
							k=1;
						}
					//System.out.println("According to age eligibility is "+eligibility+" for registration number "+temp);
					}
					
				
				//System.out.println("------------update marks running starts ----------"+entity.getRegistration_number());
				double boardPercentage=0.0;
				if(checkBoardFlag(entityInfo,entity.getComponent_id())){
					
					//boardPercentage=boardPercentage(program_id,entity_id,branch_code[len],entity.getComponent_id(),entity.getMarks_percentage(),entity.getBoard_id());
					System.out.println("Equivalenet percentage "+boardPercentage +" and component : "+entity.getComponent_id());
					boardPercentage=boardPercentage(entityInfo,entity.getComponent_id(),entity.getMarks_percentage(),entity.getBoard_id());
				}
				else{
					boardPercentage=entity.getMarks_percentage();
					System.out.println("Normal Percentage "+boardPercentage +" and component : "+entity.getComponent_id());
				}
				//updateComputedMarks(program_id,entity_id,branch_code,rs.getString(1),rs.getString(2),rs.getInt(3),con);
				//updateComputedMarks(program_id,entity_id,branch_code[len],registrationNumber,entity.getComponent_id(),boardPercentage,entity.getBoard_id(),udate);
				updateComputedMarks(entityInfo,registrationNumber,entity.getComponent_id(),boardPercentage,entity.getBoard_id(),udate);
				//before updating sum and actual sum ,first calculate his marks
				//updateSumComputedMarks(program_id,entity_id,branch_code,rs.getString(1),con);
				
				ReportInfoGetter getcategory = new ReportInfoGetter();
				
				getcategory.setRegistration_number(registrationNumber);
				//setting session start_date and end_date
				getcategory.setStart_date(udate[0]);
				getcategory.setEnd_date(udate[1]);
				
				List<ReportInfoGetter> getLi = client.queryForList("getCategoryList", getcategory);
				
				for(ReportInfoGetter getE:getLi){
					 cos=getE.getCos_value();
					 //System.out.println("value of category "+cos);
				
				}
				if(eligibility.equalsIgnoreCase("Eligible")){
					//System.out.println("Eligibility"+eligibility);
					//eligibility=getEligibility(program_id,entity_id,branch_code[len],registrationNumber,entity.getComponent_id(),boardPercentage,udate);
					eligibility=getEligibility(entityInfo,registrationNumber,entity.getComponent_id(),entity.getMarks_percentage(),udate);
					
					if(eligibility.equalsIgnoreCase("InEligible")){
					message=reportfunction.getComponentDescription(entity.getComponent_id(), registrationNumber, entityInfo)+" < "+reportfunction.getComponetEligibleMarks(entity.getComponent_id(), registrationNumber,entityInfo);
					//System.out.println("message"+message+" and "+eligibility);
					k=1;
					}
				}
				
				
				temp1=registrationNumber;
				eligibility1=eligibility;
				message1=message;
				
				//System.out.println("Eligibility= "+eligibility);
				
			}
				System.out.println("Adding: "+temp1);
				list.add(new ReportInfoGetter(program_id,entity_id,branch_code[len],temp1,cos,comp,eligibility,message));	
				Iterator i=list.iterator();
				int i1=1;
				while(i.hasNext()){
					
					//System.out.println("Running "+i1++);
					ReportInfoGetter ceig=(ReportInfoGetter)i.next();
					//System.out.println("Program_id "+ceig.getProgram_id()+"|"+ceig.getEntity_id()+"|"+ceig.getBranch_code()+"|"+ceig.getRegistration_number()+"|"+ceig.getCos_value()+"|"+ceig.getComp()+"|"+ceig.getMessage()+"|"+ceig.getReason_code()+"|");
					updateSumComputedMarks(ceig.getProgram_id(),ceig.getEntity_id(),ceig.getBranch_code(),ceig.getRegistration_number(),ceig.getCos_value(),ceig.getComp(),ceig.getReason_code(),ceig.getMessage(),udate);
				}

			//reportfunction.updateControlReport(entity_id,program_id,branch_code[len],"C",comp,udate);
				reportfunction.updateControlReport(entityInfo,"C",comp);
			compute=true;
			}
			else{
				System.out.println("Already Computed !!");
			}//if already computed i.e flag is T,E,F
			
			}//for loop ends for branch
			list1=getmeritStudent(university_id, entity_type, entity_id, program_id, branch_id);
		}catch(Exception e){
			logObj.logger.info("Registration number not successfully computed: "+errRegistrationNumber);
		}
		
		return list1;
}//updateMarks ends here
	
	public String getAgeEligibility(ReportInfoGetter entityInfo,String registrationNumber) {
		// TODO Auto-generated method stub
		String eligibility="Eligible";
		System.out.println("Coming inside age");
		try{
			//s=getCategoryId(registrationNumber);
			//System.out.println("I am trying to access getAgeEligible function");
			ReportInfoGetter getEligibleList = entityInfo;
//			getEligibleList.setProgram_id(program_id);
//			getEligibleList.setEntity_id(entity_id);
//			getEligibleList.setBranch_code(branch_code);
//			getEligibleList.setOffered_by(entity_id);
			
			getEligibleList.setRegistration_number(registrationNumber);
			getEligibleList.setCategory(reportfunction.getCategoryId(registrationNumber,entityInfo));
			//settin session start_date and end_date
//			getEligibleList.setStart_date(udate[0]);
//			getEligibleList.setEnd_date(udate[1]);
			
			Calendar cal=Calendar.getInstance();
				List<ReportInfoGetter> age = client.queryForList("getEligibleAge", getEligibleList);
				int num=0;
				for(ReportInfoGetter getAge:age){
				num=getAge.getAge_eligibility();
				System.out.println("Coming inside age "+num);
			}
				
			int year = cal.get(Calendar.YEAR);
			num=year-num;
			String compare_date=num+"-07-01";
			
			//System.out.println("Compare Age"+compare_date);
			
			//System.out.println("Comparison Date"+compare_date);      
			
			getEligibleList.setCompare_date(compare_date);
			
			List<ReportInfoGetter> agediff=client.queryForList("getEligibleDate", getEligibleList);
			System.out.println("Size : "+agediff.size());
			for(ReportInfoGetter ageDiff:agediff)
			{
				System.out.println("inside age "+ageDiff.getDiff());
			if(ageDiff.getDiff()>0){
			//System.out.println("InEligible Student"+registrationNumber);
			eligibility="InEligible";
			}
			else{
			//System.out.println("Eligible Student"+ageDiff.getDiff());
			eligibility="Eligible";
			}
			
			}
			
			}catch(Exception e){
				logObj.logger.info("Error in age Eligibilty for registration: "+registrationNumber);
			}
		return eligibility;
	}

	/*
	 * For getting String eligible abd ineligible
	 */
	public String getEligibility(ReportInfoGetter entityInfo,String registrationNumber,String component,double percentage,String[] udate){
		String eligibility="Eligible";
		//String s=null;
		try{
			//s=getCategoryId(registrationNumber);
			
			if(isEligibleFlag(entityInfo,component)){
			
			System.out.println("I am trying to access getEligible function");
				
			ReportInfoGetter getEligibleList = entityInfo;
			
//			getEligibleList.setProgram_id(program_id);
//			getEligibleList.setEntity_id(entity_id);
//			getEligibleList.setBranch_code(branch_code);
			getEligibleList.setComponent_id(component);
			getEligibleList.setCategory(reportfunction.getCategoryId(registrationNumber,entityInfo));
			
			List<ReportInfoGetter> li = client.queryForList("getEligible", getEligibleList);
			
			for(ReportInfoGetter getE:li){
				System.out.println("Yes he is eligible because his percentage is :"+percentage+" and required percentage is :"+getE.getComponent_eligiblity());
				
				if(percentage<getE.getComponent_eligiblity()){
				//System.out.println("Yes he is not eigible because his percentage is :"+percentage+" and required percentage is  :"+getE.getComponent_eligiblity());
					eligibility="InEligible";
					
				}//if percent closed
			}//for loop
			}//if closed	 
			
		}catch(Exception e){
			logObj.logger.info("Error in Component Eligibilty for registration: "+registrationNumber);
		}
		
		return eligibility;
	}
	
	public boolean isEligibleFlag(ReportInfoGetter entityInfo, String component) {
		// TODO Auto-generated method stub
		boolean flag=false;
		try{
			//System.out.println("Trying to access isEligible flag!");
			ReportInfoGetter cmeig_Eligibility=entityInfo;
//			cmeig_Eligibility.setOffered_by(entity_id);
//			cmeig_Eligibility.setProgram_id(program_id);
//			cmeig_Eligibility.setBranch_code(branch_code);
			cmeig_Eligibility.setComponent_id(component);
			
			List<ReportInfoGetter> li = client.queryForList("getEligibleFlag", cmeig_Eligibility);
			
			for(ReportInfoGetter getEFlag:li){
				//System.out.println("Yes inside category flag "+getEFlag.getComponent_criteria_flag());
				if(getEFlag.getComponent_criteria_flag().equalsIgnoreCase("y")){
					flag=true;
				}
			}
			
		}catch(Exception e){
			logObj.logger.info("Flag is not set for component crieteria eligibility.");
			System.out.println("Exception in component crieteria eligibility "+e.getMessage());
		}
		return flag;
	}

	
	
	



	public void updateComputedMarks(ReportInfoGetter entityInfo,String registration, String component,double percentage,String board_id,String[] udate) {
// TODO Auto-generated method stub
//System.out.println("------------update computed marks running----------");
double[] marks=new double[2];
double boardPercentage=percentage;
try{
	//System.out.println("cominng inside updatecomputed ");
		marks=getComputedMarks(entityInfo,registration,component,boardPercentage,udate);
	
		System.out.println("marks: "+marks[0]+" and "+marks[1]);
	ReportInfoGetter updateEntity=entityInfo;
	
	updateEntity.setComponent_id(component);
//	updateEntity.setProgram_id(program_id);
//	updateEntity.setBranch_code(branch_code);
//	updateEntity.setEntity_id(entity_id);
	updateEntity.setRegistration_number(registration);
	updateEntity.setComputed_Marks(marks[0]);
	updateEntity.setActual_computed_Marks(marks[1]);
	
	updateEntity.setStart_date(udate[0]);
	updateEntity.setEnd_date(udate[1]);
	
	client.insert("updateCallList", updateEntity);
	//System.out.println("Updated result for "+registration+" and comp "+component);

}catch(Exception e){
	logObj.logger.info("Error while updating computing marks for registation number=: "+registration);
	logObj.logger.info("Exception is :"+e.getMessage());
	System.out.println("Exception in update computed marks "+e.getMessage());
}
}

public boolean checkComputeFlag(ReportInfoGetter entityInfo, String component) {
		// TODO Auto-generated method stub
	boolean compute=false;
	try{
		ReportInfoGetter checkCompute=entityInfo;
		
		checkCompute.setComponent_id(component);
//		checkCompute.setProgram_id(program_id);
//		checkCompute.setBranch_code(branch_code);
//		checkCompute.setOffered_by(entity_id);
	
		
		//write query for compute flag and special weightage flag.
		List<ReportInfoGetter> li = client.queryForList("getCheckCompute", checkCompute);
		for(ReportInfoGetter check: li){
			if(check.getWeightage_flag().equalsIgnoreCase("y")){
				compute=true;
			}
		}
		
	}catch(Exception e){
		logObj.logger.info("Flag Value is not set for compute flag or weightage flag"+e.getMessage());
		System.out.println("Exception in checkComputeFlag "+e.getMessage());
	}
		return compute;
}

public boolean checkSpecialComputeFlag(ReportInfoGetter entityInfo, String component) {
	// TODO Auto-generated method stub
boolean compute=false;
try{
	ReportInfoGetter checkCompute=entityInfo;
	
	checkCompute.setComponent_id(component);
//	checkCompute.setProgram_id(program_id);
//	checkCompute.setBranch_code(branch_code);
//	checkCompute.setOffered_by(entity_id);
	
	//write query for compute flag and special weightage flag.
	List<ReportInfoGetter> li = client.queryForList("getCheckCompute", checkCompute);
	for(ReportInfoGetter check: li){
		if(check.getSpecial_weightage_flag().equalsIgnoreCase("y")){
			compute=true;
		}
	}
	
}catch(Exception e){
	logObj.logger.info("Flag Value is not set for special compute flag."+e.getMessage());
	System.out.println("Exception in checkSpecialComputeFlag "+e.getMessage());
}
	return compute;
}

public boolean checkBoardFlag(ReportInfoGetter entityInfo, String component) {
		// TODO Auto-generated method stub
	boolean board=false;
	//System.out.println("Trying to access check Board flag");
	try{
		ReportInfoGetter checkBoard=entityInfo;
		
		checkBoard.setComponent_id(component);
//		checkBoard.setProgram_id(program_id);
//		checkBoard.setBranch_code(branch_code);
//		checkBoard.setOffered_by(entity_id);
		
		//write query for board flag
		List<ReportInfoGetter> li = client.queryForList("getcheckBoard", checkBoard);
		//System.out.println("List is "+li);
		for(ReportInfoGetter check: li){
			//System.out.println("Coming inside if: "+check.getBoard_flag());
			if(check.getBoard_flag().equalsIgnoreCase("y")){
				board=true;
				//System.out.println("Yes this component needs to be normalize "+component);
			}
		}
		
	}catch(Exception e){
		logObj.logger.info("Flag Value is not set for board flag."+e.getMessage());
		System.out.println("Exception in checkBoardFlag "+e.getMessage());
	}
		return board;
	}

public double boardPercentage(ReportInfoGetter entityInfo, String component, double percentage,String board_id) {
		// TODO Auto-generated method stub
	double boardPercentage=0.0;
	try{
	ReportInfoGetter boardEntity=entityInfo;
	boardEntity.setComponent_id(component);
//	boardEntity.setProgram_id(program_id);
//	boardEntity.setBranch_code(branch_code);
//	boardEntity.setEntity_id(entity_id);
	boardEntity.setBoard_id(board_id);
	//write query for normalized board percentage
	List<ReportInfoGetter> li = client.queryForList("getBoardPercentage", boardEntity);
	for(ReportInfoGetter board: li){
		//System.out.println("Normalized factor"+board.getNormalization_factor()+"percentage"+percentage+" boad_id: "+board_id);		
		//System.out.println("Normalized value"+percentage*board.getNormalization_factor());
		boardPercentage=percentage*board.getNormalization_factor();
		//System.out.println("Yes this component needs to be normalize "+component+" and "+boardPercentage);
	}
	}catch(Exception e){
		logObj.logger.info("Error while normalizing board percentage"+e.getMessage());
		System.out.println("Exception in boardPercentage "+e.getMessage());
		
	}
	return boardPercentage;
	}

public double[] getComputedMarks(ReportInfoGetter entityInfo,String regnum,String component,double percentage,String[] udate) {
		// TODO Auto-generated method stub
		//System.out.println("------------update get computed marks running----------");
	
	double[] umarks=new double[2];
	double x=0;
	double ax=0;
	
	try{
		
		ReportInfoGetter getM=entityInfo;
		
		//getM.setProgram_id(program_id);
		getM.setComponent_id(component);
		getM.setType("S");
		
		//getPerCentageMarks returns Component_weightage
		List<ReportInfoGetter> getMarks=client.queryForList("getPercentageMarks",getM);
		for(ReportInfoGetter getCM: getMarks){
			x=(double)(percentage*getCM.getComponent_Weightage())/100;
			//System.out.println("X: "+x+" and percentage"+percentage);
		}
		
		if(checkComputeFlag(entityInfo,component)){
		ax=x;
		String group=null;
		if(checkSpecialComputeFlag(entityInfo,component)){
		//System.out.println("For "+component+" Computeflag "+checkComputeFlag(program_id,branch_code,entity_id,component)
				//+" and special "+checkSpecialComputeFlag(program_id,branch_code,entity_id,component));
		x=getSpecialWeightage(regnum,entityInfo,component,x,udate);
		//System.out.println("If needs to be compute and special weightage both then x: "+x+" and ax: "+x);
		}//check Special compute
		else{
			//System.out.println("For "+component+" Computeflag "+checkComputeFlag(program_id,branch_code,entity_id,component)
					//+" and special "+checkSpecialComputeFlag(program_id,branch_code,entity_id,component));
			x=x;
			//System.out.println("If needs to be compute and not special weightage both then x: "+x+" and ax: "+x);
		}
		}//if check compute flag
		
		else{
			if(checkSpecialComputeFlag(entityInfo,component)){
				//System.out.println("For "+component+" Computeflag "+checkComputeFlag(program_id,branch_code,entity_id,component)
					//	+" and special "+checkSpecialComputeFlag(program_id,branch_code,entity_id,component));
				double y=getSpecialWeightage(regnum,entityInfo,component,x,udate);
				x=y-x;
				ax=0.0;
				//System.out.println("No need to compute but special weightage needed Special Weightage: "+x+" actual "+ax);
				}//check Special compute
				else{
					//System.out.println("For "+component+" Computeflag "+checkComputeFlag(program_id,branch_code,entity_id,component)
							//+" and special "+checkSpecialComputeFlag(program_id,branch_code,entity_id,component));
					x=0.0;
					ax=0.0;
					//System.out.println("No need to compute and special compute ");
				}
		}//else for compute flag ends
		}catch(Exception e){
			logObj.logger.info("Error while giving weightage and special weightage."+e.getMessage());
			System.out.println("Exception in getComputedMarks "+e.getMessage());
			
	}
		//System.out.println("Finally values are x: "+x+" and ax: "+ax);
		umarks=new double[]{x,ax};
		//System.out.println("inside funccomputed "+umarks[0]+" and actual "+umarks[1]);
	return umarks;
}
	
public double getSpecialWeightage(String regnum,ReportInfoGetter entityInfo,String component,double x,String[] udate){
	boolean b=checkStaff(regnum,udate);
	
	try{
	ReportInfoGetter getM=entityInfo;
	
	//getM.setProgram_id(program_id);
	//setting start_date and end_date
	getM.setStart_date(udate[0]);
	getM.setEnd_date(udate[1]);
	
	if(reportfunction.isUGorPGComponent(component)){
		if(component.substring(0,1).equalsIgnoreCase("U")){
			//getM.setComponent_id("UG");
			getM.setWeightage_id("UG");
		}
		else{
			//getM.setComponent_id("PG");
			getM.setWeightage_id("PG");
		}
	}
	else{
	//getM.setComponent_id(component);
		getM.setWeightage_id(component);
	}
	getM.setFlag("S");
	if(b==true){
		boolean csw=checkStaffWard(regnum,udate);
		double sum=x;
		double sum1=x;
		if(csw==true){
			//System.out.println("Yes he is staffward");
			getM.setWeightage_id("SW");
			List<ReportInfoGetter> getStaff=client.queryForList("getStaffPercentageMarks",getM);
			for(ReportInfoGetter getCM: getStaff){
				sum=sum+((x*getCM.getWeightage_percentage())/100);
				//System.out.println("Extra Weightage for staff"+((x*getCM.getWeightage_percentage())/100));
			}
		
		}
		else{
			//System.out.println("Yes he is only student");
			getM.setRegistration_number(regnum);
			getM.setWeight_id(component);
			List<ReportInfoGetter> getStaff=client.queryForList("getExtraPercentageMarks",getM);
			
			for(ReportInfoGetter getCM: getStaff){
				//System.out.println("Extra Weightage "+((x*getCM.getWeightage_percentage())/100));
				sum1=sum1+((x*getCM.getWeightage_percentage())/100);
			}		
	
		}
			if(sum>sum1)
			x=sum;
			else
			x=sum1;
			
		//System.out.println("sum x: "+x);
		
					
	}//if checkstaff closed
	}catch(Exception e){
		logObj.logger.info("Error while calculating special weightage for "+regnum);
		logObj.logger.info("Exception is :"+e.getMessage());
	}
	return x;
}
public boolean checkStaffWard(String regnum,String[] udate) {
		// TODO Auto-generated method stub
		boolean csw=false;
		try{
			ReportInfoGetter check=new ReportInfoGetter();
			check.setRegistration_number(regnum);
			//setting date
			check.setStart_date(udate[0]);
			check.setEnd_date(udate[1]);
			
			List<ReportInfoGetter> checkStaffWard=client.queryForList("getCheckStaffWard",check);
			
			for(ReportInfoGetter getCM: checkStaffWard){
				if(getCM.getWeightage_id().equalsIgnoreCase("SW")){
					csw=true;
					//System.out.println("Yes he is staff Ward");
					break;
				}
			}
			
			
		}catch(Exception e){
			logObj.logger.info("Error while checking staff ward for "+regnum);
			logObj.logger.info("Exception is :"+e.getMessage());
		}
		return csw;
	}

	public boolean checkStaff(String regnum,String[] udate) {
		// TODO Auto-generated method stub
		boolean b=false;
		try{
			ReportInfoGetter check=new ReportInfoGetter();
			check.setRegistration_number(regnum);
			
			check.setStart_date(udate[0]);
			check.setEnd_date(udate[1]);
			
			List<ReportInfoGetter> checkStaff=client.queryForList("getCheckStaff",check);
			
			for(ReportInfoGetter getStaff: checkStaff){
				
				b=true;
			}
		
		}catch(Exception e){
			logObj.logger.info("Error while checking DEI Staff for "+regnum);
			logObj.logger.info("Exception is :"+e.getMessage());
		}
		return b;
	}

	/*
	 * for updating sum of computedmarks and sum of actual computed marks
	 */
	public void updateSumComputedMarks(String program_id, String entity_id,
			String branch_code, String regnum,String cos,String flag,String message,String reason_code,String[] udate) {
		// TODO Auto-generated method stub
		try{
			System.out.println("Coming here inside updateSumComputed");
			ReportInfoGetter updateSum=new ReportInfoGetter();
			
			updateSum.setProgram_id(program_id);
			updateSum.setBranch_code(branch_code);
			updateSum.setEntity_id(entity_id);
			updateSum.setRegistration_number(regnum);
			updateSum.setCos_value(cos);
			updateSum.setReason_code(reason_code);
			updateSum.setMessage(message);
			//setting start_date and end_date
			updateSum.setStart_date(udate[0]);
			updateSum.setEnd_date(udate[1]);
			
			double sum_computed=0.0;
			double sum_actual_computed=0.0;
			List<ReportInfoGetter> getMarks=client.queryForList("getComputeMarks",updateSum);
			System.out.println("Sum_Computed "+getMarks.size());
			for(ReportInfoGetter marks:getMarks){
				System.out.println("Sum_Computed "+sum_computed);
				sum_computed=marks.getSum_computed_marks();
				sum_actual_computed=marks.getSum_actual_computed_marks();
				if(message.equalsIgnoreCase("InEligible")){
					updateSum.setCalled("n");
				}
				else{
					updateSum.setCalled("-");
				}
				System.out.println("Coming inside update sum for registration number : "+regnum+" and sum_com "+marks.getSum_computed_marks());
				updateSum.setSum_computed_marks(sum_computed);
				updateSum.setSum_actual_computed_marks(sum_actual_computed);
			}
			//System.out.println("Coming here and flag is: "+flag);
			if(flag.equalsIgnoreCase("R")||flag.equalsIgnoreCase("C")||flag.equalsIgnoreCase("I")){
				//System.out.println("update query needs to run here");
				//write update query
				client.insert("updateTestNumber", updateSum);
			}
			else{
//				if(message.equalsIgnoreCase("InEligible")){
//					updateSum.setCalled("n");
//				}
//				else{
//					updateSum.setCalled("-");
//				}
				System.out.println("Insert query write here!!");
				//write insert query here
				client.insert("insertTestNumber", updateSum);
				System.out.println("values inserting");
			}
			//System.out.println(".......Started sum and actual computed marks......");
	
		}catch(Exception e){
			logObj.logger.info("Error while inserting or updating data into STUDENT_TEST_NUMBER for "+regnum);
			logObj.logger.info("Exception is :"+e.getMessage());
			System.out.println("Exception is "+e.getMessage());
		}
	}

	
	@Override
	public List<DataBean> getmeritStudent(String university_id,String entity_type,String entity_id,String program_id,String branch_id) {
		// TODO Auto-generated method stub
		List<DataBean> list=new ArrayList<DataBean>();
		String[] branch_code;
		try{
			System.out.println("Coming here");
			branch_code=getBranch_code(entity_id,program_id, branch_id);
			String[] udate=reportfunction.getSessionDate(university_id);
			for(int i=0;i<branch_code.length;i++){
			
			ReportInfoGetter meritStudent=new ReportInfoGetter();
			meritStudent.setUniversity_id(university_id);
			meritStudent.setEntity_id(entity_id);
			meritStudent.setOffered_by(entity_id);
			meritStudent.setProgram_id(program_id);
			meritStudent.setStart_date(udate[0]);
			meritStudent.setEnd_date(udate[1]);
			//System.out.println(branch_code[i]);
			meritStudent.setBranch_code(branch_code[i]);

			
			List<ReportInfoGetter> getStudents=client.queryForList("getInEligibleStudentMarks",meritStudent);
			
			for(ReportInfoGetter student: getStudents){
				//System.out.println(student.getRegistration_number());
				String registrationNumber=student.getRegistration_number();
				String[] udetail=reportfunction.getUserDetail(registrationNumber, meritStudent);
				String[][] studentmarks=reportfunction.getFullyComputedmarks(registrationNumber, meritStudent, udate);
//				for(int x=0;x<studentmarks.length;x++){
//					for(int y=0;y<studentmarks[x].length;y++){
//						System.out.println("values ate server "+studentmarks[x][y]);
//					}
//				}
				//System.out.println("Name: "+studentmarks[0][0]+" and "+udetail[0]+" and "+studentmarks[1][0]);
				double sum_cm=student.getSum_computed_marks();
				String status=student.getStatus();
				String reason=student.getReason_code();
				list.add(new DataBean(registrationNumber,udetail,studentmarks,sum_cm,status,reason));
				
			}
			
			}//i=0,branch_code ends
//			Iterator i=list.iterator();
//			while(i.hasNext()){
//				DataBean db=(DataBean)i.next();
//				System.out.println(db.getRegistrationNumber());
//			}
			
	}catch(Exception e){

		logObj.logger.info("Error while getting student's list");
		logObj.logger.info("Exception is :"+e.getMessage());
		System.out.println("Exception in getMeriList :"+e.getMessage());
		}
		return list;
	}

	@Override
	public boolean generateTestNumber(String university_id,String entity_type,String entity_id,String program_id,String branch_id)
	{
		// TODO Auto-generated method stub
		List<String[]> list=new ArrayList<String[]>();
		String[] category=null;
		String comp=null;
		boolean compute=false;
		String[] branch_code=null;
		try{
			
			//String entity_id=reportfunction.getEntity_Id(entity_name, university_id);
			
			//String program_id=reportfunction.getProgram_Id(entity_id, program_name);
			
			branch_code=getBranch_code(entity_id,program_id,branch_id);
			
			String[] udate=reportfunction.getSessionDate(university_id);
			
			//System.out.println("Entity_id in update: "+reportfunction.getEntity_Id(entity_name, university_id));
			//System.out.println("program id in update: "+reportfunction.getProgram_Id(entity_id, program_name));
			////System.out.println("branch_code in update: "+getBranch_Id(branch_name));
			for(int len=0;len<branch_code.length;len++){
		comp=reportfunction.isUpdated(entity_id, program_id, branch_code[len],udate);
		if(comp.equalsIgnoreCase("I")){
		ReportInfoGetter test_number=new ReportInfoGetter();
		test_number.setEntity_id(entity_id);
		test_number.setOffered_by(entity_id);
		test_number.setProgram_id(program_id);
		//System.out.println("Branch_code "+branch_code[len]);
		test_number.setBranch_code(branch_code[len]);
		//setting session start date and end date
		test_number.setStart_date(udate[0]);
		test_number.setEnd_date(udate[1]);
		
		
		List<ReportInfoGetter> li=client.queryForList("countCategory", test_number);
		for(ReportInfoGetter test:li){
			category=new String[test.getCount()];
		}
		List<ReportInfoGetter> li1=client.queryForList("getCategory", test_number);
		int catlen=0;
		for(ReportInfoGetter test:li1){
			//System.out.println("categry:"+test.getCategory());
			   category[catlen]=test.getCategory();
			    catlen++;
			
		}
for(int i=0;i<category.length;i++){
//System.out.println("categorylength :"+category.length);
int count=0;
String x="";
list=new ArrayList();
		List<ReportInfoGetter> li2=client.queryForList("getMaxNumber", test_number);
		for(ReportInfoGetter max:li2){
			x=max.getMax_test_number();
		}

if(x==null || x.equals("")){
//System.out.println("Coming inside if: ");
x="100";
}
else{
//System.out.println("Coming inside else: ");
int y=Integer.parseInt(x)+1;
x=String.valueOf(y);

}
//System.out.println("Category="+category[i]);
test_number.setCategory(category[i]);
test_number.setCalled("y");
String suf="00001";

List<ReportInfoGetter> li3=client.queryForList("getRegistrationTestNumber", test_number);
//System.out.println("No value ");
for(ReportInfoGetter reg_test_number:li3){

boolean b=lookupcity(university_id,reg_test_number.getCity());
//System.out.println("city "+reg_test_number.getCity()+" flag: "+b+" regnumber "+reg_test_number.getRegistration_number());
if(b==true){
System.out.print(b+" | ");
System.out.print(String.valueOf(gettingCode(x,Long.valueOf(suf),5))+" | ");
//updateTest(String.valueOf(gettingCode(x,Long.valueOf(suf),5)),program_id,entity_id,branch_code[len],rs.getString(1),con);
updateTest(String.valueOf(gettingCode(x,Long.valueOf(suf),5)),program_id,entity_id,branch_code[len],reg_test_number.getRegistration_number(),udate);
suf=String.valueOf(Integer.parseInt(suf)+1);
count++;
}
else{
//String y=String.valueOf(Integer.parseInt(x)+1);
System.out.print(String.valueOf(gettingCode(x,Long.valueOf(suf),5))+" | ");
//list.add(new String[]{x,program_id,entity_id,branch_code[len],rs.getString(1)});
list.add(new String[]{x,program_id,entity_id,branch_code[len],reg_test_number.getRegistration_number()});
}//else close

//System.out.println();
}//while for registration

Iterator itr=list.iterator();
String[] data=null;
String pre="100";
String su="00001";
while(itr.hasNext()){
data=(String[])itr.next();
pre=data[0];
//System.out.println("value for previous: "+pre);
if(count>0){
pre=String.valueOf(Integer.parseInt(pre)+1);
}//if count ends
}//while ends for itr

Iterator itr1=list.iterator();
while(itr1.hasNext()){
data=(String[])itr1.next();
//updateTest(String.valueOf(gettingCode(pre,Long.valueOf(su),5)),program_id,entity_id,branch_code[len],data[4],con);
updateTest(String.valueOf(gettingCode(pre,Long.valueOf(su),5)),program_id,entity_id,branch_code[len],data[4],udate);
su=String.valueOf(Integer.parseInt(su)+1);
}



}//For loop ends for category
reportfunction.updateControlReport(test_number,"T",comp);	
}//if ends
		else{
			compute=true;
		}
}//for loop completes for branches
}catch(Exception e){
	logObj.logger.info("Error while generating test number");
	logObj.logger.info("Exception is :"+e.getMessage());
}

return compute;
}//generateTestNumber ends here

//********************************************//



public void updateTest(String num,String program_id,String entity_id,String branch_code,String regnum,String[] udate){
		
			try{
				ReportInfoGetter updatetest=new ReportInfoGetter();
				updatetest.setTest_number(num);
				updatetest.setProgram_id(program_id);
				updatetest.setEntity_id(entity_id);
				updatetest.setBranch_code(branch_code);
				updatetest.setRegistration_number(regnum);
				
				//setting session start date and end date
				updatetest.setStart_date(udate[0]);
				updatetest.setEnd_date(udate[1]);
				
				client.insert("updateRegTestNumber", updatetest);
				
			
			}catch(Exception e){
				logObj.logger.info("Error while inserting test number for "+regnum);
				logObj.logger.info("Exception is :"+e.getMessage());
			}
		
}//updateTest ends	
/*
generate valid number
*/
public static String gettingCode(String str,long maxvalue,int int_value_length)
{

int y=Integer.parseInt(str);
String no_zero="0",valid_code="";

if(String.valueOf(maxvalue).length()<=int_value_length)
{			
while(no_zero.length()<=(int_value_length-1-String.valueOf(maxvalue).length()))
	no_zero=no_zero+"0";
if(String.valueOf(maxvalue).length()<int_value_length)
{
	////System.out.println("code="+String.valueOf(y)+org_id+" "+s+String.valueOf(maxvalue));
	valid_code=String.valueOf(y)+no_zero+String.valueOf(maxvalue);
}
else
{
	////System.out.println("code="+String.valueOf(y)+org_id+" "+String.valueOf(maxvalue));	
	valid_code=String.valueOf(y)+String.valueOf(maxvalue);
}			
}
else
{
	////System.out.println("code="+String.valueOf(y)+org_id+" "+String.valueOf(maxvalue));	
	valid_code=String.valueOf(y)+String.valueOf(maxvalue);
}	
//System.out.println("Valid_code "+valid_code);
return valid_code;
}//getting_code ends
/*
*check
*/
public boolean lookupcity(String university_id,String city){

	boolean b=false;
	String maincityName="Agra";
	try{
		ReportInfoGetter ceigLookup=new ReportInfoGetter();
		ceigLookup.setUniversity_id(university_id);
		ceigLookup.setCode("MAINCT");
		List<ReportInfoGetter> mainCity=client.queryForList("getMainCity",ceigLookup);
		for(ReportInfoGetter maincitylist:mainCity){
		maincityName=maincitylist.getValue();
		}
		ceigLookup.setMaincity(maincityName);
		List<ReportInfoGetter> li=client.queryForList("getCity",ceigLookup);
		
//	PreparedStatement ps=con.prepareStatement("select near_city from CITYLOOKUP");
//	ResultSet rs=ps.executeQuery();

	for(ReportInfoGetter citylist:li){

	if(citylist.getCity().equalsIgnoreCase(city) || city.equalsIgnoreCase("AGRA")){
	b=true;
	System.out.println("City Matches");
	break;
	}

	}

	}catch(Exception e){
		logObj.logger.info("Error while checking near city"+e.getMessage());
	}
	return b;
}//look up city ends

/*
 * Method for ComboBox starts here!!
 */
@Override
public ReportInfoGetter[] methodEntityList(String university_id) {
	// TODO Auto-generated method stub
	//System.out.println("Coming here!!");
	ReportInfoGetter[] entityInfo = null;
	try {
		ReportInfoGetter reportInfoGetter = new ReportInfoGetter();
		reportInfoGetter.setUniversity_id(university_id);
		//System.out.println(university_id);
		List<?> li = client.queryForList("entityListReport", reportInfoGetter);
		System.out.println(li.size());
		entityInfo = (ReportInfoGetter[]) li
				.toArray(new ReportInfoGetter[li.size()]);
		for (int i = 0; i < entityInfo.length; i++) {
			System.out.println(entityInfo[i].getEntity_description());
		}
	} catch (Exception e) {
		logObj.logger.info("Error while getting EntityList"+e.getMessage());
	}

	return entityInfo;
}//method entity_list ends

@Override
/*
 * For entityname like DayalBaghEngineering college
 */
public ReportInfoGetter[] methodEntityNameList(String university_id,
		String entity_type) {
	// TODO Auto-generated method stub
	ReportInfoGetter[] entityInfo = null;
	try {

		ReportInfoGetter cm = new ReportInfoGetter();
		System.out.println("Selected type is "+entity_type);
	//	cm.setEntity_description(entity_description);
		
		/*List<ReportInfoGetter> li1=client.queryForList("getentityType", cm);
		for(ReportInfoGetter type:li1){
			cm.setEntity_type(type.getEntity_type());	
		}*/
		cm.setUniversity_id(university_id);
		cm.setEntity_type(entity_type);
		
		List<?> li = client.queryForList("entityNameListReport", cm);

		entityInfo = (ReportInfoGetter[]) li
				.toArray(new ReportInfoGetter[li.size()]);
		for (int i = 0; i < entityInfo.length; i++) {
			//System.out.println(entityInfo[i].getEntity_name());
			//System.out.println(entityInfo[i].getEntity_id());
		}
	} catch (Exception e) {
		logObj.logger.info("Error while getting entity name"+e.getMessage());
		
	}

	return entityInfo;
}//method entity_name ends

@Override
/*
 * Program name like Bachelor of science
 */
public ReportInfoGetter[] methodProgramNameList(String university_id,
		String entity_id) {
	// TODO Auto-generated method stub
	ReportInfoGetter[] entityInfo = null;
	try {

		ReportInfoGetter cm = new ReportInfoGetter();
		
		System.out.println("Selected entity id is "+entity_id);
		//System.out.println("entity "+entity_name+" university "+university_id);
		//String entity_id=reportfunction.getEntity_Id(entity_name, university_id);
		//System.out.println("Entity_id: "+reportfunction.getEntity_Id(entity_name, university_id));
		cm.setUniversity_id(university_id);
		cm.setOffered_by(entity_id);
		//cm.setOffered_by(reportfunction.getEntity_Id(entity_name, university_id));
		List<?> li = client.queryForList("programNameListReport", cm);

		entityInfo = (ReportInfoGetter[]) li
				.toArray(new ReportInfoGetter[li.size()]);
		for (int i = 0; i < entityInfo.length; i++) {
			//System.out.println(entityInfo[i].getProgram_name());
		}
	} catch (Exception e) {
		logObj.logger.info("Error while getting program_name"+e.getMessage());
		
	}

	return entityInfo;
}//methodProgram_name ends




@Override
public ReportInfoGetter[] methodBranchNameList(String university_id,
		String program_id, String entity_id) {
	// TODO Auto-generated method stub
	ReportInfoGetter[] entityInfo = null;
	try {
		//System.out.println("Coming inside branch "+entity_name+" and "+program_name);
		ReportInfoGetter cm = new ReportInfoGetter();
		//Connection con = createconnection();
		//System.out.println("entity "+entity_name+" university "+university_id);
		//String entity_id=reportfunction.getEntity_Id(entity_name, university_id);
		//System.out.println("Entity_id in branch: "+reportfunction.getEntity_Id(entity_name, university_id));
		//System.out.println("program id in branch: "+reportfunction.getProgram_Id(entity_id, program_name));
		//cm.setUniversity_id(university_id);
		System.out.println("Selected type is: "+entity_id+"Selected program_id is: "+program_id);
		cm.setOffered_by(entity_id);
		cm.setProgram_id(program_id);
		//cm.setProgram_id(reportfunction.getProgram_Id(entity_id, program_name));
		List<?> li = client.queryForList("branchNameListReport", cm);

		entityInfo = (ReportInfoGetter[]) li
				.toArray(new ReportInfoGetter[li.size()]);
		for (int i = 0; i < entityInfo.length; i++) {
			//System.out.println("in branch : "+entityInfo[i].getBranch_name());
		}
	} catch (Exception e) {
		logObj.logger.info("Error while getting for branch name"+e.getMessage());
		
	}

	return entityInfo;
}//methodBranchNameList ends



@Override
public String validateGenerate(String university_id, String entity_type,
		String entity_id, String program_id, String branch_id) {
	// TODO Auto-generated method stub
	String result=null;
	try{
	//	con=createconnection();
	//String entity_id=reportfunction.getEntity_Id(entity_name, university_id);
	
	//String program_id=reportfunction.getProgram_Id(entity_id, program_name);
	
	//String branch_code=reportfunction.getBranch_Id(branch_id);
	
	String[] udate=reportfunction.getSessionDate(university_id);
	
	result=reportfunction.isUpdated(entity_id, program_id, branch_id,udate);
	}catch(Exception e){
		logObj.logger.info("Error while validating flag for "+entity_id+", "+program_id+", "+branch_id);
		logObj.logger.info("Exception is :"+e.getMessage());
	}
	//System.out.println("Result at server is "+result);
	return result;
}//validateGenerate ends here

@Override
public String getResetFlag(String university_id, String entity_type,
		String entity_id, String program_id, String branch_id) {
	// TODO Auto-generated method stub
	String[] branch_code=null;
	String comp=null;
	try{
		
		//String entity_id=reportfunction.getEntity_Id(entity_name, university_id);
		
		//String program_id=reportfunction.getProgram_Id(entity_id, program_name);
		
		branch_code=getBranch_code(entity_id, program_id, branch_id);
		
		String[] udate=reportfunction.getSessionDate(university_id);
		
		ReportInfoGetter reset=new ReportInfoGetter();
		reset.setProgram_id(program_id);
		reset.setOffered_by(entity_id);
		reset.setEntity_id(entity_id);
		//setting session start date and end date
		reset.setStart_date(udate[0]);
		reset.setEnd_date(udate[1]);
		
		for(int len=0;len<branch_code.length;len++){
			reset.setBranch_code(branch_code[len]);
			List<ReportInfoGetter> li=client.queryForList("getResetFlag",reset);

			for(ReportInfoGetter resetflag:li){
				if(!resetflag.getFlag_status().equalsIgnoreCase(null)){
					//System.out.println("Successfully reset "+branch_code[len]);
					if(resetflag.getFlag_status().equalsIgnoreCase("T")||resetflag.getFlag_status().equalsIgnoreCase("E")||resetflag.getFlag_status().equalsIgnoreCase("F")){
					reportfunction.updateControlReport(reset,"E",resetflag.getFlag_status());
					comp="E";
					}
					else{
					reportfunction.updateControlReport(reset,"R",resetflag.getFlag_status());
					comp="R";	
					}
				}
			}
		}
	}catch(Exception e){
		logObj.logger.info("Error while resetting flag value for "+entity_id+", "+program_id+", "+branch_id);
		logObj.logger.info("Exception is :"+e.getMessage());
	}
	return comp;
}//get reset flag ends


private String[] getBranch_code(String entity_id, String program_id,
			String branch_id) {
		// TODO Auto-generated method stub
	String[] branch_code=null;
	try{
		ReportInfoGetter cbranch=new ReportInfoGetter();
		//System.out.println("Program_id "+program_id+" entity_id "+entity_id+" inside branch_code");
		cbranch.setProgram_id(program_id);
		cbranch.setOffered_by(entity_id);
	if(branch_id.equalsIgnoreCase("All")){
		//System.out.println("If all is selected ");
		List<ReportInfoGetter> branchcount=client.queryForList("getBranchCount",cbranch);
		for(ReportInfoGetter countbranch:branchcount){
			branch_code=new String[countbranch.getCount()];	
		}

		List<ReportInfoGetter> branchlist=client.queryForList("getBranchList",cbranch);
		int len=0;
		for(ReportInfoGetter listbranch:branchlist){
			System.out.println("Inside server "+listbranch.getBranch_code());
			branch_code[len]=listbranch.getBranch_code();
			len++;
		}

		
	}
	else{
		System.out.println("Inside else of branch_code ");
	//branch_code=new String[]{reportfunction.getBranch_Id(branch_name)};
		branch_code=new String[]{branch_id};
	}
	}catch(Exception e){
		logObj.logger.info("Error while resetting flag value for "+e.getMessage());
	}
	//System.out.println("branches are "+branch_code[0]+" and "+branch_code.length);
	return branch_code;
}//getbranch_code ends here

@Override
public List<DataBean> getUpdateMarks(String university_id, String entity_type,
		String entity_name, String program_name, String branch_name) {
	// TODO Auto-generated method stub
	return null;
}
public List<DataBean> getAllComputedStudentList(String university_id,String entity_type,String entity_id,String program_id,String branch_id){
	List<DataBean> list=new ArrayList<DataBean>();
	String[] branch_code=null;
	try{
		branch_code=getBranch_code(entity_id,program_id, branch_id);
		String[] udate=reportfunction.getSessionDate(university_id);
		
		ReportInfoGetter meritStudent=new ReportInfoGetter();
		meritStudent.setUniversity_id(university_id);
		meritStudent.setEntity_id(entity_id);
		meritStudent.setOffered_by(entity_id);
		meritStudent.setProgram_id(program_id);
		meritStudent.setUniversity_start_date(udate[0]);
		meritStudent.setUniversity_end_date(udate[1]);
		
		for(int i=0;i<branch_code.length;i++){
		meritStudent.setBranch_code(branch_code[i]);
		List<ReportInfoGetter> getStudents=client.queryForList("getInEligibleStudentMarks",meritStudent);
		
		for(ReportInfoGetter student: getStudents){
			String registrationNumber=student.getRegistration_number();
			String[] udetail=reportfunction.getUserDetail(registrationNumber, meritStudent);
			String[][] studentmarks=reportfunction.getFullyComputedmarks(registrationNumber, meritStudent, udate);
			double sum_cm=student.getSum_computed_marks();
			String status=student.getStatus();
			String reason=student.getReason_code();
			list.add(new DataBean(registrationNumber,udetail,studentmarks,sum_cm,status,reason));
		}
		
		}//i=0,branch_code ends
		
		
}catch(Exception e){

	logObj.logger.info("Error while getting student's list");
	logObj.logger.info("Exception is :"+e.getMessage());
	}
	return list;
}

@Override
public String uploadFile(String file) {
	// TODO Auto-generated method stub
	ReadXLSheet xlReader = new ReadXLSheet();
	xlReader.init(file);

	return "";
}
}//class ends