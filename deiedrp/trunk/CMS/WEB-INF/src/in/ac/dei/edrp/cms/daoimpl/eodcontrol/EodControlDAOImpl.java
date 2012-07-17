/**
 * @(#) EodControlDAOImpl.java
 * Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 */
package in.ac.dei.edrp.cms.daoimpl.eodcontrol;

import in.ac.dei.edrp.cms.dao.eodcontrol.EodControlDAO;
import in.ac.dei.edrp.cms.daoimpl.utility.ResetCodes;
import in.ac.dei.edrp.cms.domain.eodcontrol.EodControl;

import org.apache.log4j.Logger;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * This file consist of the methods used at the
 * Activity Master process.
 * @author Ankit Jain
 * @date 25 Jan 2011
 * @version 1.0
 */
public class EodControlDAOImpl extends SqlMapClientDaoSupport implements EodControlDAO{
	
	private Logger loggerObject = Logger.getLogger(EodControlDAOImpl.class);
    
	/**
     * This method will fetch the ProgramCourse Information
     * @return List
     */
	@SuppressWarnings("unchecked")
	public List<EodControl> getProgramCourseHeaderList(EodControl activityMaster) {
		List<EodControl> programCourse=null;
		
		try{
			programCourse=getSqlMapClientTemplate().queryForList("eodControl.getProgramCourseHeaderList", activityMaster);
			loggerObject.info("in getProgramCourseHeaderList");
		}
		catch (Exception e) {
			loggerObject.error("in getProgramCourseHeaderList" + e);			
		}
		return programCourse;
	}
	
	/**
     * This method will fetch the Process Information
     * @return List
     */
	@SuppressWarnings("unchecked")
	
	public List<EodControl> getProcessList(EodControl activityMasterObj) {
		List<EodControl> processList=null;
		try{
			processList = getSqlMapClientTemplate().queryForList("eodControl.getProcessList", activityMasterObj);
			loggerObject.info("in Process List");
		}
		catch (Exception e) {
			loggerObject.error("in getProcessList" + e);
		}
		return processList;
	}
	
	/**
     * This method will fetch the Activity Information
     * @return List
     */
	@SuppressWarnings("unchecked")
	
	public List<EodControl> getActivityList(EodControl activityMasterObj) {
		List<EodControl> activityMaster=null;
		try{
			activityMaster = getSqlMapClientTemplate().queryForList("eodControl.getActivityList", activityMasterObj);
			loggerObject.info("in Activity List");
		}
		catch (Exception e) {
			loggerObject.error("in getActivityList" + e);
		}
		return activityMaster; 
	}
	
	/**
     * This method will return the semester start & end date Information
     * @return List
     */
	@SuppressWarnings("unchecked")
	
	public List<EodControl> getSemesterStartEndDate(EodControl actiMasterobj) {
		List<EodControl> semesterStartEndDate=null;
		try{
			semesterStartEndDate = getSqlMapClientTemplate().queryForList("eodControl.getSemesterStartEndDate", actiMasterobj);
			loggerObject.info("in getSemesterStartEndDate");
		}
		catch (Exception e) {
			loggerObject.error("in getSemesterStartEndDate" + e);
		}
		return semesterStartEndDate;
	}
	
	
	/**
     * This method will get the session start and date.
     * @return List
     */
	@SuppressWarnings("unchecked")
	
	public List<EodControl> getSessionDetails(
			EodControl activityMasterObject) {
		List<EodControl> sessionStartEndList=null;
		try{
			sessionStartEndList = getSqlMapClientTemplate().queryForList("eodControl.getSessionStartEndDate", activityMasterObject);
			loggerObject.info("in getSessionDetails"); 
		}
		catch (Exception e) {
			loggerObject.error("in getSessionDetails"+ e);
		}
		return sessionStartEndList;
	}
	
	/**
     * This method will return the Activity Master Details
     * @return List
     */
	@SuppressWarnings("unchecked")
	public List<EodControl> getEodControlDetails(EodControl eodControl) {
		List<EodControl> eodControlDetailList=null;
		try{
			eodControlDetailList=getSqlMapClientTemplate().queryForList("eodControl.getEodControlDetails", eodControl);
			loggerObject.info("in getEodControlDetails");
		}
		catch (Exception e) {
			loggerObject.error("in getEodControlDetails" + e);
		}
		return eodControlDetailList;
	}
	
	/**
     * This method will return the Activity Master Details
     * @return List
     */
	@SuppressWarnings("unchecked")	
	public List<EodControl> getMethodsToRun(EodControl eodControl) {
		List<EodControl> eodControlDetailList=null;
		try{
			eodControlDetailList=getSqlMapClientTemplate().queryForList("eodControl.getMethodsToRun", eodControl);
			loggerObject.info("in getMethodsToRun");
		}
		catch (Exception e) {
			loggerObject.error("in getMethodsToRun" + e);
		}
		return eodControlDetailList;
	}
	
	
	/**
     * This method will return the Activity Master Details
     * @return List
     */
	@SuppressWarnings("unchecked")
	
	public List<EodControl> getStepFrequency(EodControl eodControl) {
		List<EodControl> eodControlDetailList=null;
		try{
			eodControlDetailList=getSqlMapClientTemplate().queryForList("eodControl.getStepFrequency", eodControl);
			loggerObject.info("in getStepFrequency");
		}
		catch (Exception e) {
			loggerObject.error("in getStepFrequency" + e);
		}
		return eodControlDetailList;
	}
	
	/**
     * This method will Save the ActivityMaster Details.
     * @return void
     */
	
	@SuppressWarnings("unchecked")
	public String saveEodControlDetails(EodControl eodControl) {
		try{
			List<EodControl> eodControlDetailList=getSqlMapClientTemplate().queryForList("eodControl.getExistMethodDetail", eodControl);
			
			
			System.out.println(eodControlDetailList.isEmpty());
			loggerObject.info("in saveEodControlDetails");
			
			if(!eodControlDetailList.isEmpty()){
				System.out.println(eodControlDetailList.get(0).getStatus()+"     "+eodControl.getStatus());			
				
				if(eodControlDetailList.get(0).getStatus().equals(eodControl.getStatus())){
					System.out.println("Inside If");
					return "updateStatus";
				}
			}
			else{
				getSqlMapClientTemplate().insert("eodControl.saveEodControlDetails",eodControl);
				return "success";
			}			
			
			
					
		}catch (DataIntegrityViolationException e) {
			loggerObject.error("in saveEodControlDetails" + e);
			return "duplicateData";
		}
		catch (Exception e) {			
			loggerObject.error("in saveEodControlDetails" + e);
			return "error";
		}
		return null;
	}
	
	/**
     * This method will update the ActivityMaster Details.
     * @return String
     */
	
	public String updateEodControl(EodControl eodControl) {
		
		try{
			int recordUpdateCount= getSqlMapClientTemplate().update("eodControl.updateEodControl",eodControl);
			loggerObject.info("in updateEodControl");
			if(recordUpdateCount>0){
				return "success";
			}
			else{
				return "nothingUpdated";
			}
		}
		catch (Exception e) {
			loggerObject.error("in updateEodControl" + e);
			return "failure";
		}		
	}

	/**
     * This method will delete the ActivityMaster Details.
     * @return void
     */
	public String deleteEodControlDetails(EodControl eodControl, String eodControlDataTokens) {
		
	   	StringTokenizer tokens = new StringTokenizer(eodControlDataTokens, ",");
	    try{   	
		   	 while(tokens.hasMoreTokens()){
		   		eodControl.setPhase(tokens.nextToken()); 
		   		eodControl.setStep(tokens.nextToken());
		   		eodControl.setMethodToRunCode(tokens.nextToken());
		   		eodControl.setStatus(tokens.nextToken());
		   		getSqlMapClientTemplate().delete("eodControl.deleteEodControl",eodControl);
		   	 }
		   	loggerObject.info("in deleteEodControlDetails");
		   	return "success";
	    }
	    catch (Exception e) {
	    	loggerObject.error("in deleteEodControlDetails" + e);
	    	return "failure";
		}
	}

	/**
     * This method will change the activity status.
     * @return List 
     */
	@SuppressWarnings("unchecked")
	public String changeEodControlStatus(EodControl eodControl, String eodControlDataTokens) {
		/**
		 * Method to make record inactive and active
		 * inputs: method_code , method_status
		 * case 1: when a record exist with method_code=MH1 and method_status==ACT, 
		 * to make this record inactive
		 * 		step 1: check if any record exist with method_code=MH1 and method_status=ACT
		 * 					then print "A Active record already exist.";
		 * 				otherwise
		 * 					update the status to INA
		 * 
		 * case 2: when a record exist with method_code==MH1 and method_status==INA
		 * to make this record active
		 * step 1: check if any record exist with method_code=MH1 and method_status=INA
		 * 				then Print "a Inactive record already exist."
		 * 			otherwise
		 * 				update the status to ACT
		 */
		
		   	StringTokenizer tokens = new StringTokenizer(eodControlDataTokens, ",");
		   	try{   	
			   	 while(tokens.hasMoreTokens()){
			   		eodControl.setPhase(tokens.nextToken()); 
			   		eodControl.setStep(tokens.nextToken());
			   		eodControl.setMethodToRunCode(tokens.nextToken());
			   		eodControl.setStatus(tokens.nextToken());

			   		if(eodControl.getStatus().equalsIgnoreCase("ACT")){
			   			eodControl.setStatus("INA");
			   		}
			   		else{
			   			eodControl.setStatus("ACT");
			   		}

			   		List<EodControl> eodStatus=getSqlMapClientTemplate().queryForList("eodControl.getMethodToRunStatus", eodControl);
				   	System.out.println("size: "+ eodStatus.size()+"   empty?: "+eodStatus.isEmpty()+"         "+eodControl.getStatus());
			   		if(eodStatus.equals(null)){
			   			System.out.println("Sita Ram");
				   		getSqlMapClientTemplate().update("eodControl.updateEodControlStatus",eodControl);
			   		}
				   	else{
				   		if(eodStatus.size()>0){
			   				return "makeInactive";
			   			}
			   			else{
							getSqlMapClientTemplate().update("eodControl.updateEodControlStatus",eodControl);
			   			}
				   	}
			   	 }
			   	loggerObject.info("in changeEodControlStatus");
			   	return "success";
		    }
		    catch (Exception e) {
		    	loggerObject.error("in changeEodControlStatus" + e);
		    	return "failure";
			}
		}
	
			
	/**
     * This method will return the list of activity
     * @return List
     */
	@SuppressWarnings("unchecked")
	public List<EodControl> getEntity(EodControl actiMasterobj) {
		List<EodControl> entityList=null;
		try{
			entityList=getSqlMapClientTemplate().queryForList("eodControl.getEntity", actiMasterobj);
			loggerObject.info("in getEntity");
			
		}
		catch (Exception e) {
			loggerObject.error("in getEntity" + e);
		}
		return entityList;
	}
	
	/**
     * This method will build the EOD Master
     * @return List
     */
	@SuppressWarnings({ "unchecked" })
	public String buildEODMaster(EodControl eodControl) {
		String flowStatus="";
		try{
			System.out.println("AAAAAAAAA");
			// check if the build is first time.
			List<EodControl> eodMasterDetails = getSqlMapClientTemplate().queryForList("eodControl.getEodMasterDetail", eodControl);
			if(eodMasterDetails.isEmpty()){
				// i.e. first time build
				System.out.println("AA....a");
				flowStatus=buildFunction(eodControl);
			}
			else{
				// check if any record of last date having the record status not equal to complete
				System.out.println("Get phase 99th record status");
				List<EodControl> eodDateStatus = getSqlMapClientTemplate().queryForList("eodControl.getLastDate99thRecordsStatus", eodControl);
				System.out.println("Check Empty: "+eodDateStatus.isEmpty());
				if(eodDateStatus.get(0).getStatus()!="COM"){
					System.out.println("Returning to Home");
					return "incompleteStatus";
				}
				else {
					System.out.println("Building");
					flowStatus=buildFunction(eodControl);
				}
			}
			loggerObject.info("in buildEODMaster");
		}
		catch (Exception e) {
			loggerObject.error("in buildEODMaster" + e);			
		}
		return flowStatus;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	private String buildFunction(EodControl eodControl){
		int counter=0;
		// else goto step 2
		List<EodControl> eodRecords =null;
		String eodDate=eodControl.getEodDate();
		try{
			System.out.println("BBBBBBBB");
			eodRecords = getSqlMapClientTemplate().queryForList("eodControl.getTheRecordsOfSelectedDate", eodControl);
			if(eodRecords.isEmpty()){
				// step 3 No Record Exist In this Date
				return "noRecordsFoundInSelectedDate";
			}
			else{
				System.out.println("CCCCCCcc");
				for (int i=0;i<eodRecords.size();i++){
					if(eodRecords.get(i).getStepFrequencyCode().equalsIgnoreCase("DLY")){
						System.out.println("Daily: "+i);
						counter++;
						insertRecords(eodRecords,eodControl, i);
					}
					else if(eodRecords.get(i).getStepFrequencyCode().equalsIgnoreCase("WKY")){
						System.out.println("Inside Week : "+i + "   eodDate is: "+ eodDate);
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						Date date = (Date) df.parse(eodDate);							
						String day = getIndexDay(date.getDay());
						System.out.println(day+"  BBBBBBB   "+eodRecords.get(i).getDay());
						if(eodRecords.get(i).getDay().equalsIgnoreCase("any")){
							//check difference between eod-date and start date ==7
							System.out.println("Inside any if");
							eodControl.setMethodToRunCode(eodRecords.get(i).getMethodToRunCode());
							EodControl startDateObject = (EodControl) getSqlMapClientTemplate().queryForObject("eodControl.getEodStartDate", eodControl);
							System.out.println("after query  "+startDateObject);
							if(startDateObject!=null){
								System.out.println("inside any if");
								Date eodStartdate = (Date) df.parse(startDateObject.getEodStartTime());
								long noOfDays= new ResetCodes().calculateDays(eodStartdate,date);
								System.out.println("No Of Days: "+noOfDays);
								if(noOfDays==7){
									System.out.println("No of days is 7");
									counter++;
									insertRecords(eodRecords,eodControl, i);// build the selected records.
								}
								else{
									System.out.println("No of days is not 7");
								}
							}
							else{
								System.out.println("Hiiiiiiiiii");
								// i.e. No record exist in eod_master table. first time build
								counter++;
								insertRecords(eodRecords,eodControl, i);// build the selected records.
							}
						}
						else if(eodRecords.get(i).getDay().equalsIgnoreCase(day)){ 
							counter++;
							insertRecords(eodRecords,eodControl, i);// build the selected records.
						}
					}
					else if(eodRecords.get(i).getStepFrequencyCode().equalsIgnoreCase("FNL")){
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						Date date = (Date) df.parse(eodDate);		
						EodControl startDateObject = (EodControl) getSqlMapClientTemplate().queryForObject("eodControl.getEodStartDate", eodControl);
						if(startDateObject!=null){
							Date eodStartdate = (Date) df.parse(startDateObject.getEodStartTime());
							long noOfDays= new ResetCodes().calculateDays(eodStartdate,date);
							System.out.println("No Of Days: "+noOfDays);
							if(noOfDays==15){
								System.out.println("No of days is 15");
								getSqlMapClientTemplate().insert("eodControl.buildEodMaster", eodControl);
							}
							else{
								System.out.println("No of days is not 15");
							}
						}
					}
					else if(eodRecords.get(i).getStepFrequencyCode().equalsIgnoreCase("MTH")){
						String buildDate="";
						if(eodRecords.get(i).getFlag().equalsIgnoreCase("S")){
							buildDate=eodDate.substring(0, 8)+"01";
						}
						else{
							buildDate = eodDate.substring(0, 8)+getEndDateOfMonth(eodDate);
						}
						
						if(eodDate.equalsIgnoreCase(buildDate)){
							counter++;
							insertRecords(eodRecords,eodControl, i);//build record.
						}
					}
					else if(eodRecords.get(i).getStepFrequencyCode().equalsIgnoreCase("YRL")){
						String eodDateMMDD = eodDate.substring(5,10);
						System.out.println("Yearly: "+i+"   "+eodDateMMDD+"   "+eodRecords.get(i).getMmdd());
						if(eodDateMMDD.equalsIgnoreCase(eodRecords.get(i).getMmdd())){
							counter++;
							insertRecords(eodRecords,eodControl, i);//build record.
						}
					}
				}// end of for loop	
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		if(counter>0){
			return "success";
		}
		else{
			return "noRecordBuild";
		}
		
	}
	
	private int getEndDateOfMonth(String eodDate){
		int endDate=0;
		String year = eodDate.substring(0,4);
		String month = eodDate.substring(5,7);
		int year2=Integer.parseInt(year);
		int months= Integer.parseInt(month);
		
		if((months==4)||(months==6)||(months==9)||(months==11))	{
				endDate=30;
		}
		else if(months==2){
			if(year2%4==0){
				endDate=29;
			}
			else{
				endDate=28;
			}
		}
		else{
			endDate=31;
		}
		System.out.println("end Date: "+ endDate);
		
		return endDate;		
	}
	
	private void insertRecords(List<EodControl> eodRecords, EodControl eodControl, int i){
		eodControl.setPhase(eodRecords.get(i).getPhase());
		eodControl.setDependentPhase(eodRecords.get(i).getDependentPhase());
		eodControl.setStep(eodRecords.get(i).getStep());
		eodControl.setStepFrequencyCode(eodRecords.get(i).getStepFrequencyCode());
		eodControl.setMethodToRunCode(eodRecords.get(i).getMethodToRunCode());
		System.out.println("Before Insert ");
		getSqlMapClientTemplate().insert("eodControl.buildEodMaster", eodControl);
	}
	
	private String getIndexDay(int dayIndex){
		String day="";
		switch(dayIndex)
		{
			case 0:
				day="SUN";
				break;
			case 1:
				day="MON";
				break;
			case 2:
				day="TUE";
				break;
			case 3:
				day="WED";
				break;
			case 4:
				day="THU";
				break;
			case 5:
				day="FRI";
				break;
			case 6:
				day="SAT";
				break;
			default:
				break;
		}
		
		return day;
	}

}
