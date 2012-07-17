/**
 * @(#) BuildSemesterEndProcessImpl.java
 *Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
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
package in.ac.dei.edrp.cms.daoimpl.buildsemesterendprocess;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import in.ac.dei.edrp.cms.dao.buildsemesterenprocess.BuildSemesterEndProcessDao;
import in.ac.dei.edrp.cms.domain.buildsemesterendprocess.BuildSemesterEndProcess;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 *This file consist of the methods used for Build Semester End Process
 * @author Devendra Singhal
 * @date Dec 02 2011
 * @version 1.0
 */
public class BuildSemesterEndProcessImpl extends SqlMapClientDaoSupport implements BuildSemesterEndProcessDao{
	/** Creating object of Logger for log Maintenance */
	private Logger loggerObject = Logger.getLogger(BuildSemesterEndProcessImpl.class);

	/** Creating object of TransactionTemplate for transaction Management */
	TransactionTemplate transactionTemplate = null;

	/** defining setter method for object of TransactionTemplate */
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	/**
     * Method to Clear Temporary Tables
     * @param object of BuildSemesterEndProcess
     * @return String message
     */
	@SuppressWarnings("unchecked")
	public String clearTempTables(
			BuildSemesterEndProcess buildSemesterEndProcess) {
		String message="";
		try{
			buildSemesterEndProcess.setCode("REGDAY");
			List<BuildSemesterEndProcess>systemValuDay=getSqlMapClientTemplate().queryForList("buildSemesterEndProcess.getSystemValueDays",buildSemesterEndProcess);
			String day=systemValuDay.get(0).getPeriodday();
			buildSemesterEndProcess.setCode("REGEXD");
			List<BuildSemesterEndProcess>systemValuExcedDay=getSqlMapClientTemplate().queryForList("buildSemesterEndProcess.getSystemValueDays",buildSemesterEndProcess);
			String exceedDay=systemValuExcedDay.get(0).getPeriodday();
			//Create object of SimpleDateFormat
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			//Get System current date
			Date sysDate=(Date)dateFormat.parse(dateFormat.format(Calendar.getInstance().getTime()));
			//subtract days from semester start date for getting period start date
			Date periodStartDate=calculatePeriodDate(buildSemesterEndProcess.getNextSemStartDate(),day,"sub");
			//add exceed days in semester start date for getting period end date
			Date periodEndDate=calculatePeriodDate(buildSemesterEndProcess.getNextSemStartDate(),exceedDay,"add");
			
			buildSemesterEndProcess.setDummyFlag("1");
			List<BuildSemesterEndProcess>processCodeList=getSqlMapClientTemplate().queryForList("buildSemesterEndProcess.getProcessCode",buildSemesterEndProcess);
			if(processCodeList.size()>0){
				buildSemesterEndProcess.setProcessCode(processCodeList.get(0).getProcessCode());
				if(sysDate.before(periodStartDate)){
					message="lessSysDate"+"/"+dateFormat.format(periodStartDate);
				}
				else{
					if(sysDate.after(periodEndDate)){
						message="greaterSysDate"+"/"+dateFormat.format(periodEndDate);
					}
					else{
						List<BuildSemesterEndProcess> programRegList=getSqlMapClientTemplate().queryForList("buildSemesterEndProcess.getProgramRegistraionList",buildSemesterEndProcess);
						if(programRegList.size()==0){
							message="noProgram";
						}
						else{
							message=checkStatus(buildSemesterEndProcess);
							if(message.equals("run")){
								final BuildSemesterEndProcess buildSemester=buildSemesterEndProcess;
								String code="S"+buildSemester.getUniversityId()+"%";
								buildSemester.setCode(code);
								message=(String)transactionTemplate.execute(new TransactionCallback(){
									public Object doInTransaction(
											TransactionStatus tStatus) {
										Object savepoint=null;
										String msg="";
										try{
											savepoint=tStatus.createSavepoint();
											//Copy records in temp_student_master_history table from temp_student_master table
											getSqlMapClientTemplate().insert("buildSemesterEndProcess.insertTempMasterHistory",buildSemester);
											//Copy records in temp_student_program_history table from temp_student_program table
											getSqlMapClientTemplate().insert("buildSemesterEndProcess.insertTempProgramHistory",buildSemester);
											//Copy records in temp_student_program_history table from temp_student_program table
											getSqlMapClientTemplate().insert("buildSemesterEndProcess.insertTempCourseHistory",buildSemester);
											//Clear temp_student_master table
											getSqlMapClientTemplate().delete("buildSemesterEndProcess.clearTempStudentMaster",buildSemester);
											//Clear temp_student_program table
											getSqlMapClientTemplate().delete("buildSemesterEndProcess.clearTempStudentProgram",buildSemester);
											//Clear temp_student_courses table
											getSqlMapClientTemplate().delete("buildSemesterEndProcess.clearTempStudentCourses",buildSemester);
											//Copy records in applicant_info_history table from applicant_info table
											getSqlMapClientTemplate().insert("buildSemesterEndProcess.insertApplicantInfoHistory",buildSemester);
											//Clear applicant_info table whose processed_flag is not 'N' in staging table
											getSqlMapClientTemplate().delete("buildSemesterEndProcess.clearApplicantInfo",buildSemester);
											//Set Status in semesterend_control table
											setControlStatus(buildSemester,"success");
											msg="success";
										}
										catch(Exception e){
											msg="error";
											setControlStatus(buildSemester,"error");
											loggerObject.error("error in BuildSemesterEndProcessImpl inside method clearTempTables :"+e);
											tStatus.rollbackToSavepoint(savepoint);
										}
										return msg;
									}
								});
							}
						}
					}
				}
			}
			else{
				message="NOProcessCode";
			}
		}
		catch(ParseException pe){
			message="parseError";
			loggerObject.error("error in BuildSemesterEndProcessImpl inside method clearTempTables :"+pe);
		}
		catch(Exception ex){
			message="error";
			loggerObject.error("error in BuildSemesterEndProcessImpl inside method clearTempTables :"+ex);
		}
		return message;
	}

	/**
     * Method to Build REG in semester_processing_control table for next semester
     * @param object of BuildSemesterEndProcess
     * @return String message
     */
	@SuppressWarnings({ "unchecked" })
	public String readyForRegistration(
			BuildSemesterEndProcess buildSemesterEndProcess) {
		String message="";
		try{
			buildSemesterEndProcess.setCode("REGDAY");
			List<BuildSemesterEndProcess>systemValuDay=getSqlMapClientTemplate().queryForList("buildSemesterEndProcess.getSystemValueDays",buildSemesterEndProcess);
			String day=systemValuDay.get(0).getPeriodday();
			buildSemesterEndProcess.setCode("REGEXD");
			List<BuildSemesterEndProcess>systemValuExcedDay=getSqlMapClientTemplate().queryForList("buildSemesterEndProcess.getSystemValueDays",buildSemesterEndProcess);
			String exceedDay=systemValuExcedDay.get(0).getPeriodday();
			//Create object of SimpleDateFormat
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			//Get System current date
			Date sysDate=(Date)dateFormat.parse(dateFormat.format(Calendar.getInstance().getTime()));
			//subtract days from semester start date for getting period start date
			Date periodStartDate=calculatePeriodDate(buildSemesterEndProcess.getNextSemStartDate(),day,"sub");
			//add exceed days in semester start date for getting period end date
			Date periodEndDate=calculatePeriodDate(buildSemesterEndProcess.getNextSemStartDate(),exceedDay,"add");
			buildSemesterEndProcess.setDummyFlag("2");
			List<BuildSemesterEndProcess>processCodeList=getSqlMapClientTemplate().queryForList("buildSemesterEndProcess.getProcessCode",buildSemesterEndProcess);
			if(processCodeList.size()>0){
				final String processCD=processCodeList.get(0).getProcessCode();
				buildSemesterEndProcess.setProcessCode(processCD);
				if(sysDate.before(periodStartDate)){
					message="lessSysDate"+"/"+dateFormat.format(periodStartDate);
				}
				else{
					if(sysDate.after(periodEndDate)){
						message="greaterSysDate"+"/"+dateFormat.format(periodEndDate);
					}
					else{
						final List<BuildSemesterEndProcess> programRegList=getSqlMapClientTemplate().queryForList("buildSemesterEndProcess.getProgramRegistraionList",buildSemesterEndProcess);
						if(programRegList.size()==0){
							message="noProgram";
						}
						else{
							message=checkStatus(buildSemesterEndProcess);
							if(message.equals("run")){
								final BuildSemesterEndProcess buildSemester=buildSemesterEndProcess;
								message=(String)getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
									public Object doInSqlMapClient(
											SqlMapExecutor executor) {
										String msg="";
										try{
											int logRecords=0;
											int totalRecordInserted=0;
											int recordInserted=0;
											executor.startBatch();
											for(int i=0;i<programRegList.size();i++){
												buildSemester.setProgramCourse(programRegList.get(i).getProgramCourse());
												buildSemester.setEntityId(programRegList.get(i).getEntityId());
												buildSemester.setSemesterStartDate(programRegList.get(i).getSemesterStartDate());
												buildSemester.setSemesterEndDate(programRegList.get(i).getSemesterEndDate());
												buildSemester.setProcessCode(processCD);
												buildSemester.setStatus("RDY");
												List<BuildSemesterEndProcess>semesterProcessingList=getSqlMapClientTemplate().queryForList("buildSemesterEndProcess.checkSemesterProcessing",buildSemester);
												List<BuildSemesterEndProcess>activityList=getSqlMapClientTemplate().queryForList("buildSemesterEndProcess.checkActivity",buildSemester);
												//Check for Already Exist in semester_processing_control
												if(semesterProcessingList.size()>0){
													for(int j=0;j<semesterProcessingList.size();j++){
													logger.info("Record Already Exists in semster_processing_control table of 'REG' process : for program course key :"+semesterProcessingList.get(j).getProgramCourse()+" entity id :"+semesterProcessingList.get(j).getEntityId()
														+" semester start date :"+semesterProcessingList.get(j).getSemesterStartDate()+" semester end date "+semesterProcessingList.get(j).getSemesterEndDate()
														+" university id : "+buildSemester.getUniversityId());
													logRecords++;
													}
												}
												else{
													recordInserted++;
													executor.insert("buildSemesterEndProcess.insertSemesterProcessing",buildSemester);
												}
												if(activityList.size()>0){
													executor.update("buildSemesterEndProcess.updateActivity",buildSemester);
												}
												else{
													logger.info("Ther is no Record in activity_master table of 'REG' process :  for activity sequence '1' : for program course key :"+programRegList.get(i).getProgramCourse()+" entity id :"+programRegList.get(i).getEntityId()
															+" semester start date :"+programRegList.get(i).getSemesterStartDate()+" semester end date "+programRegList.get(i).getSemesterEndDate()
															+" university id : "+buildSemester.getUniversityId());
												}
											}
											if(recordInserted>0){
												 totalRecordInserted=executor.executeBatch();
											}
											setControlStatus(buildSemester,"success");
											msg="success"+"/"+recordInserted+"/"+logRecords+"/"+programRegList.size();
										}
										catch(Exception e){
											msg="error";
											setControlStatus(buildSemester,msg);
											logger.error("Error inside BuildSemesterEndProcessImpl method readyForRegistration: "+e);
										}
										return msg;
									}
	
								});
							}
						}
					}
				}
			}
			else{
				message="NOProcessCode";
			}
		
		}
		catch(ParseException pe){
			message="parseError";
			loggerObject.error("error in BuildSemesterEndProcessImpl inside method clearTempTables :"+pe);
		}
		catch(Exception e){
			message="error";
			logger.error("Error inside BuildSemesterEndProcessImpl method readyForRegistration: "+e);
		}
		return message;
	}

	/**
     * Method to Build SEP in semester_processing_control table for current semester
     * @param object of BuildSemesterEndProcess
     * @return String message
     */
	@SuppressWarnings("unchecked")
	public String readyForSemesterEnd(
			BuildSemesterEndProcess buildSemesterEndProcess) {
		String message="";
		try{
			buildSemesterEndProcess.setCode("SEPDAY");
			List<BuildSemesterEndProcess>systemValuDay=getSqlMapClientTemplate().queryForList("buildSemesterEndProcess.getSystemValueDays",buildSemesterEndProcess);
			String day=systemValuDay.get(0).getPeriodday();
			buildSemesterEndProcess.setCode("SEPEXD");
			List<BuildSemesterEndProcess>systemValuExcedDay=getSqlMapClientTemplate().queryForList("buildSemesterEndProcess.getSystemValueDays",buildSemesterEndProcess);
			String exceedDay=systemValuExcedDay.get(0).getPeriodday();
			//Create object of SimpleDateFormat
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			//Get System current date
			Date sysDate=(Date)dateFormat.parse(dateFormat.format(Calendar.getInstance().getTime()));
			//subtract days from semester end date for getting period start date
			Date periodStartDate=calculatePeriodDate(buildSemesterEndProcess.getNextSemEndDate(),day,"sub");
			//add exceed days in semester end date for getting period end date
			Date periodEndDate=calculatePeriodDate(buildSemesterEndProcess.getNextSemEndDate(),exceedDay,"add");
			buildSemesterEndProcess.setDummyFlag("3");
			List<BuildSemesterEndProcess>processCodeList=getSqlMapClientTemplate().queryForList("buildSemesterEndProcess.getProcessCode",buildSemesterEndProcess);
			if(processCodeList.size()>0){
				final String processCD=processCodeList.get(0).getProcessCode();
				buildSemesterEndProcess.setProcessCode(processCD);				
				if(sysDate.before(periodStartDate)){
					message="lessSysDate"+"/"+dateFormat.format(periodStartDate);
				}
				else{
					if(sysDate.after(periodEndDate)){
						message="greaterSysDate"+"/"+dateFormat.format(periodEndDate);
					}
					else{
						final List<BuildSemesterEndProcess> programRegList=getSqlMapClientTemplate().queryForList("buildSemesterEndProcess.getProgramRegistraionList",buildSemesterEndProcess);
						if(programRegList.size()==0){
							message="noProgram";
						}
						else{
							message=checkStatus(buildSemesterEndProcess);
							if(message.equals("run")){
								final BuildSemesterEndProcess buildSemester=buildSemesterEndProcess;
								message=(String)getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
									public Object doInSqlMapClient(
											SqlMapExecutor executor) {
										String msg="";
										try{
											int logRecords=0;
											int totalRecordInserted=0;
											int recordInserted=0;
											executor.startBatch();
											for(int i=0;i<programRegList.size();i++){
												buildSemester.setProgramCourse(programRegList.get(i).getProgramCourse());
												buildSemester.setEntityId(programRegList.get(i).getEntityId());
												buildSemester.setSemesterStartDate(programRegList.get(i).getSemesterStartDate());
												buildSemester.setSemesterEndDate(programRegList.get(i).getSemesterEndDate());
												buildSemester.setProcessCode(processCD);
												buildSemester.setStatus("RDY");
												List<BuildSemesterEndProcess>semesterProcessingList=getSqlMapClientTemplate().queryForList("buildSemesterEndProcess.checkSemesterProcessing",buildSemester);
												List<BuildSemesterEndProcess>activityList=getSqlMapClientTemplate().queryForList("buildSemesterEndProcess.checkActivity",buildSemester);
												//Check for Already Exist in semester_processing_control
												if(semesterProcessingList.size()>0){
													for(int j=0;j<semesterProcessingList.size();j++){
													logger.info("Record Already Exists in semster_processing_control table of 'SEP' process : for program course key :"+semesterProcessingList.get(j).getProgramCourse()+" entity id :"+semesterProcessingList.get(j).getEntityId()
														+" semester start date :"+semesterProcessingList.get(j).getSemesterStartDate()+" semester end date "+semesterProcessingList.get(j).getSemesterEndDate()
														+" university id : "+buildSemester.getUniversityId());
													logRecords++;
													}
												}
												else{
													recordInserted++;
													executor.insert("buildSemesterEndProcess.insertSemesterProcessing",buildSemester);
												}
												if(activityList.size()>0){
													executor.update("buildSemesterEndProcess.updateActivity",buildSemester);
												}
												else{
													logger.info("Ther is no Record in activity_master table of 'SEP' process :  for activity sequence '1' : for program course key :"+programRegList.get(i).getProgramCourse()+" entity id :"+programRegList.get(i).getEntityId()
															+" semester start date :"+programRegList.get(i).getSemesterStartDate()+" semester end date "+programRegList.get(i).getSemesterEndDate()
															+" university id : "+buildSemester.getUniversityId());
												}
											}
											if(recordInserted>0){
												 totalRecordInserted=executor.executeBatch();
											}
											setControlStatus(buildSemester,"success");
											msg="success"+"/"+recordInserted+"/"+logRecords+"/"+programRegList.size();
										}
										catch(Exception e){
											msg="error";
											setControlStatus(buildSemester,msg);
											logger.error("Error inside BuildSemesterEndProcessImpl method readyForSemesterEnd: "+e);
										}
										return msg;
									}
	
								});
							}
						}
					}
				}
			}
			else{
				message="NOProcessCode";
			}
		}
		catch(ParseException pe){
			message="parseError";
			loggerObject.error("Error in BuildSemesterEndProcessImpl inside method readyForSemesterEnd :"+pe);
		}
		catch(Exception e){
			message="error";
			logger.error("Error in BuildSemesterEndProcessImpl inside method readyForSemesterEnd: "+e);
		}
		return message;
	}

	/**
	 * Method to check status of build semester end process
	 * @param BuildSemesterEndProcess
	 * @return String for status
	 */
	@SuppressWarnings("unchecked")
	public String checkStatus(BuildSemesterEndProcess buildSemesterEndProcess) {
		String message="";
		try{
			BuildSemesterEndProcess buildSemester=new BuildSemesterEndProcess();
			buildSemester.setUniversityId(buildSemesterEndProcess.getUniversityId());
			buildSemester.setNextSemStartDate(buildSemesterEndProcess.getNextSemStartDate());
			buildSemester.setNextSemEndDate(buildSemesterEndProcess.getNextSemEndDate());
			buildSemester.setProcessCode(buildSemesterEndProcess.getProcessCode());

			List<BuildSemesterEndProcess> previousProcessDetail = getSqlMapClientTemplate().queryForList("buildSemesterEndProcess.getPreviousProcessDetail",buildSemester);
			if(previousProcessDetail.size()>0){
				buildSemester.setProcessCode(previousProcessDetail.get(0).getProcessCode());
				List<BuildSemesterEndProcess> controlList=getSqlMapClientTemplate().queryForList("buildSemesterEndProcess.getControlDetailStatus",buildSemester);
				if(controlList.size()==0){
					message="buildPrevious";
				}
				else if(controlList.get(0).getStatus().equals("E")){
					message="buildPrevious";
				}
				else if(controlList.get(0).getStatus().equals("P")){
					List<BuildSemesterEndProcess>controllist=getSqlMapClientTemplate().queryForList("buildSemesterEndProcess.getControlDetailStatus",buildSemesterEndProcess);
					if(controllist.size()==0){
						message="run";
					}
					else if(controllist.get(0).getStatus().equals("E")){
						message="run";
					}
					else if(controllist.get(0).getStatus().equals("P")){
						message="allreadyBuild";
					}
				}
			}
			else{
				List<BuildSemesterEndProcess>controllist=getSqlMapClientTemplate().queryForList("buildSemesterEndProcess.getControlDetailStatus",buildSemesterEndProcess);
				if(controllist.size()==0){
					message="run";
				}
				else if(controllist.get(0).getStatus().equals("E")){
					message="run";
				}
				else if(controllist.get(0).getStatus().equals("P")){
					message="allreadyBuild";
				}
			}
		}
		catch(Exception ex){
			message="error";
			logger.error("Error in BuildSemesterEndProcessImpl inside method checkStatus "+ex);
		}
		return message;

	}

	/**
	 * Method to set status in semesterend_control table
	 * @param String semesterStartDate
	 * @param String semesterEndDate
	 * @param String universityId
	 * @param String status
	 */
	@SuppressWarnings("unchecked")
	public void setControlStatus(BuildSemesterEndProcess buildSemesterEndProcess,String status){
		List<BuildSemesterEndProcess>controllist=getSqlMapClientTemplate().queryForList("buildSemesterEndProcess.getControlDetailStatus",buildSemesterEndProcess);
		if(controllist.size()==0){
			if(status.equals("error")){
				logger.info("Some Error Occured so set status 'E' in semesterend_control table for Process Code"+buildSemesterEndProcess.getProcessCode());
				buildSemesterEndProcess.setStatus("E");
				getSqlMapClientTemplate().insert("buildSemesterEndProcess.setControlDetailStatus",buildSemesterEndProcess);
			}
			else{
				buildSemesterEndProcess.setStatus("P");
				getSqlMapClientTemplate().insert("buildSemesterEndProcess.setControlDetailStatus",buildSemesterEndProcess);
			}
		}
		else if(controllist.get(0).getStatus().equals("E")){
			if(status.equals("error")){
				logger.info("Some Error Occured so semesterend_control table is not updated");
			}
			else{
				buildSemesterEndProcess.setStatus("P");
				getSqlMapClientTemplate().update("buildSemesterEndProcess.updateControlDetailStatus",buildSemesterEndProcess);
			}
		}
	}

	/**
	 * Method to calculate period date
	 * @param String date
	 * @param String days
	 * @return String period date
	 * @throws ParseException
	 */

	public Date calculatePeriodDate(String date,String day,String operator) throws ParseException{
		//Create object of SimpleDateFormat
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		//Get instance of Calendar class
		Calendar cal=Calendar.getInstance();
		cal.setTime((Date)dateFormat.parse(date));
		if(operator.equals("add")){
			cal.add(Calendar.DATE,Integer.parseInt(day));
			logger.info("Inside BuildSemesterEndProcessImpl: Period End Date is: "+dateFormat.format(cal.getTime()));
		}
		else if(operator.equals("sub")){
			cal.add(Calendar.DATE,-Integer.parseInt(day));
			logger.info("Inside BuildSemesterEndProcessImpl: Period Start Date is: "+dateFormat.format(cal.getTime()));
		}

		Date periodDate=dateFormat.parse(dateFormat.format(cal.getTime()));
		return periodDate;
	}
}
