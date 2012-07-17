/**
 * @(#) ResetCodes.java
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
 * Redistribution in binary form must reproducuce the above copyright
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

package in.ac.dei.edrp.cms.daoimpl.utility;


import in.ac.dei.edrp.cms.daoimpl.studentregistration.StudentMasterImpl;
import in.ac.dei.edrp.cms.domain.studentregistration.StudentNumbersInfoGetter;
import in.ac.dei.edrp.cms.domain.buildnextsession.BuildNextSession;
import in.ac.dei.edrp.cms.domain.utility.SystemValue;
import in.ac.dei.edrp.cms.dao.utility.ResetSystemValueService;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import com.ibatis.sqlmap.client.SqlMapClient;

public class ResetCodes implements ResetSystemValueService{

	/** Creating object of Logger for log Maintenance */
	private Logger loggerObject = Logger.getLogger(StudentMasterImpl.class);

	/** Creating object of SqlMapClient and  TransactionTemplate*/
	SqlMapClient sqlMapClient = null;
	TransactionTemplate transactionTemplate = null;

	/** Creating default constructor of ResetCodes class */
	public ResetCodes(){
	}

	/** Creating parameterize  constructor of ResetCodes class
	 * @param object of SqlMapClient
	 * @param object of TransactionTemplate
	 **/
	public ResetCodes(SqlMapClient sqlMapClient2,
			TransactionTemplate transactionTemplate) {
		this.sqlMapClient = sqlMapClient2;
		this.transactionTemplate = transactionTemplate;
	}
	/** overload SqlMapClient method setSqlMapClient*/
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	/** defining setter method for object of TransactionTemplate */
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	/**
	 * the method reinitializes the values for the selected codes which are to
	 * be initialized as default every year
	 * @param getter object of the referenced bean
	 * @return String value
	 */
	@SuppressWarnings("unchecked")
	public String resetSystemValues(SystemValue getter) {

		/*
		 * code to be set when the method will be drived from the common
		 * controller
		 */
		List<SystemValue> codeList=null;
		String message="";
		try {
			/*
			 * call checkStatus message for check previous/current process
			 * has been built or not this message return a String message
			 */
			message=checkStatus(getter,"RSV");
			if(message.equals("run")){
				codeList = sqlMapClient.queryForList("systemValue.getValuesForCodes", getter);
				Iterator iterator = codeList.iterator();
				int listSize=codeList.size();
				while (iterator.hasNext()) {
					SystemValue object = (SystemValue) iterator.next();
					String codeLength = "%0" + object.getValue() + "d";
					String code = object.getCode();
					String codeValue = String.format(codeLength, 0);
					getter.setValue(codeValue);
					getter.setCode(code);
					sqlMapClient.update("systemValue.resetSystemValues", getter);
					loggerObject.info("inside Reset Code method resetSystemValues Code "+getter.getCode()+" is Reset");
					listSize--;
				}
				if(listSize==0){
					String msg=setControlStatus(getter,"success","RSV");
					message=msg;
				}
			}
		} catch (SQLException e) {
			String msg=setControlStatus(getter,"error","RSV");
			if(msg.equals("success")){
				message="sqlError";
			}
			else{
				message=msg;
			}
			loggerObject.error("Error inside ResetCodes method resetSystemValues : " + e+msg);
		}

		return message;

	}

	/**
	 * Method clears the data from staging and pre staging  tables as and when required
	 * @author Devendra Singhal
	 * @param getter object of the referenced bean
	 * @return boolean value
	 */
	@SuppressWarnings("unchecked")
	public String clearTempTables(final SystemValue getter) {
				String msg="";
					msg=checkStatus(getter,"CTT");
					if(msg.equals("run")){
					try{
						List<SystemValue>list=sqlMapClient.queryForList("systemValue.getSessionStartEndDate",getter);
						getter.setPreviousSessionStartDate(list.get(0).getPreviousSessionStartDate());
						getter.setPreviousSessionEndDate(list.get(0).getPreviousSessionEndDate());
						final List<SystemValue>prestagingList=sqlMapClient.queryForList("systemValue.getprestagingList",getter);
						if(prestagingList.size()>0){
							//create int type final variable prestagingListSize used in inner class inside for loop
							final int prestagingListSize=prestagingList.size();

							String str=(String)transactionTemplate.execute(new TransactionCallback(){
								 public String doInTransaction(TransactionStatus transaction) {
									String msg="";
									Object savepoint=null;
									try{
										savepoint=transaction.createSavepoint();
										for(int i=0;i<prestagingListSize;i++){
											getter.setStudentId(prestagingList.get(i).getStudentId());
											getter.setRegRollNo(prestagingList.get(i).getRegRollNo());
											getter.setEntityId(prestagingList.get(i).getEntityId());
											getter.setProgram(prestagingList.get(i).getProgram());
											getter.setBranch(prestagingList.get(i).getBranch());
											getter.setSemester(prestagingList.get(i).getSemester());
											getter.setSpecialization(prestagingList.get(i).getSpecialization());
											getter.setAttemptNo(prestagingList.get(i).getAttemptNo());
											getter.setAdmissionMode(prestagingList.get(i).getAdmissionMode());
											getter.setSemesterStartDate(prestagingList.get(i).getSemesterStartDate());
											getter.setSemesterEndDate(prestagingList.get(i).getSemesterEndDate());
											List<SystemValue>checStaging=sqlMapClient.queryForList("systemValue.getStagingList",getter);
											if(checStaging.size()>0){
												//insert into prestaging_table_history table from prestaging_table
												sqlMapClient.insert("systemValue.setPrestagingHistory",getter);
												//clear prestaging_table
												sqlMapClient.delete("systemValue.clearPrestaging",getter);
												//clear staging_table
												sqlMapClient.delete("systemValue.clearStaging",getter);

											}
											else{
												loggerObject.info("Inside Clear Temporary Tables This Record not in staging_table so not deleted from prestaging_table : studentId="+getter.getStudentId()
													+" registrationRollNumner is "+getter.getRegRollNo()+" semesterStartDate is "+getter.getSemesterStartDate()
													+" semesterEndDate is "+getter.getSemesterEndDate()+" Program is "+getter.getProgram()+" Branch is "+getter.getBranch()
													+"specialization is "+getter.getSpecialization());
											}
											if(i==prestagingListSize-1){
												msg=setControlStatus(getter,"success","CTT");
											}
										}
									}
									catch(SQLException se){
										String str=setControlStatus(getter,"error","CTT");
										if(str.equals("success")){msg="sqlError";}
										else{msg=str;}
										loggerObject.error("Error inside ResetCodes method clearTempTables"+se);
										transaction.rollbackToSavepoint(savepoint);
									}
									catch(Exception e){
										String str=setControlStatus(getter,"error","CTT");
										if(str.equals("success")){msg=e.toString()+str;}
										else{msg=str;}
										loggerObject.error("Error inside ResetCodes method clearTempTables"+e+str);
										transaction.rollbackToSavepoint(savepoint);
									}
									return msg;
								}
							});

							msg=str;
						}
						else{
							msg="emptyPrestaging";
						}
					}
					catch(SQLException se){
						msg="sqlError";
						loggerObject.error("Error inside ResetCodes method clearTempTables"+se);
					}
					catch(NullPointerException ne){
						msg="noPreviousSession";
						loggerObject.error("Error inside ResetCodes method clearTempTables"+ne);
					}
					catch(Exception ex){
						msg="error";
						loggerObject.error("Error inside ResetCodes method clearTempTables"+ex);
					}
				}
					return msg;
	}

		/**
	 * Method to check status of build
	 * @author Devendra Singhal
	 * @param programDetails
	 * @return String, message for status
	 */
	@SuppressWarnings("unchecked")
	public String checkStatus(SystemValue programDetails, String processCD) {
		String message="";
		BuildNextSession buildNextSessionObject=new BuildNextSession();
		buildNextSessionObject.setUniversityId(programDetails.getUniveristyCode());
		if(processCD.equals("RSV")){
			buildNextSessionObject.setDummyFlag("5");
		}
		else if(processCD.equals("CTT")){
			buildNextSessionObject.setDummyFlag("6");
		}
		try{
			List<BuildNextSession> processList=sqlMapClient.queryForList("buildNextSession.getProcessCode", buildNextSessionObject);
			if(processList.size()>0){
				String processCode=processList.get(0).getProcessCode();
				programDetails.setProcessCode(processCode);
				List<SystemValue> sessionDetail = sqlMapClient.queryForList("systemValue.getSessionStartEndDate",programDetails);
				programDetails.setSessionStartDate(sessionDetail.get(0).getSessionStartDate());
				programDetails.setSessionEndDate(sessionDetail.get(0).getSessionEndDate());
				List<SystemValue> previousProcessDetail = sqlMapClient.queryForList("systemValue.getPreviousProcessDetail",programDetails);
				if(previousProcessDetail.size()>0){
					programDetails.setProcessCode(previousProcessDetail.get(0).getProcessCode());
					List<SystemValue> controlList=sqlMapClient.queryForList("systemValue.getControlDetailStatus",programDetails);
					if(controlList.size()==0){
						message="buildPrevious";
					}
					else if(controlList.get(0).getStatus().equals("E")){
						message="buildPrevious";
					}
					else if(controlList.get(0).getStatus().equals("P")){
						programDetails.setProcessCode(processCode);
						List<SystemValue>controllist=sqlMapClient.queryForList("systemValue.getControlDetailStatus",programDetails);
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
					message="noSequence";
				}
			}else{
				message="NOProcessCode";
			}
		}
		catch(Exception ex){
			message="error";
			loggerObject.error("Error inside ResetCodes method checkStatus "+ex);
		}
		return message;

	}

	/**
	 * Method to set status in yearend Process Control table
	 * @author Devendra Singhal
	 * @param object of SystemValue
	 * @param String status
	 */
	@SuppressWarnings("unchecked")
	public String setControlStatus(SystemValue sysValue,String status,String processCD){
		String msg="";
		BuildNextSession buildNextSessionObject=new BuildNextSession();
		buildNextSessionObject.setUniversityId(sysValue.getUniveristyCode());
		if(processCD.equals("RSV")){
			buildNextSessionObject.setDummyFlag("5");
		}
		else if(processCD.equals("CTT")){
			buildNextSessionObject.setDummyFlag("6");
		}
		try{
			List<BuildNextSession> processList=sqlMapClient.queryForList("buildNextSession.getProcessCode", buildNextSessionObject);
			sysValue.setProcessCode(processList.get(0).getProcessCode());
			
			List<SystemValue>controllist=sqlMapClient.queryForList("systemValue.getControlDetailStatus",sysValue);
			List<SystemValue> sessionList=sqlMapClient.queryForList("systemValue.getSessionStartEndDate",sysValue);
			sysValue.setSessionStartDate(sessionList.get(0).getSessionStartDate());
			sysValue.setSessionEndDate(sessionList.get(0).getSessionEndDate());
			if(controllist.size()==0){
				if(status.equals("error")){
					loggerObject.info("Some Error Occured so set status 'E' in yearend_process_control table for Process Code 'BPR' ");
					sysValue.setStatus("E");
					sqlMapClient.insert("systemValue.setControlDetailStatus",sysValue);
					msg="success";
				}
				else{
					sysValue.setStatus("P");
					sqlMapClient.insert("systemValue.setControlDetailStatus",sysValue);
					msg="success";
				}
			}
			else if(controllist.get(0).getStatus().equals("E")){
				if(status.equals("error")){
					loggerObject.info("Some Error Occured so yearend_process_control table is not updated");
				}
				else{
					sysValue.setStatus("P");
					sqlMapClient.update("systemValue.updateControlDetailStatus",sysValue);
					msg="success";
				}
			}
		}
		catch(Exception e){
			msg=e.toString();
			loggerObject.info("Error in ResetCode Class method setControlStatus "+e);
		}
		return msg;
	}
	
	/**
	 * The method inserts a record into semester processing control
	 * based upon the required condition
	 * @param systemValue
	 * @return
	 */
	@SuppressWarnings( { "unchecked" })
	public Boolean semesterProcessingControl(SystemValue systemValue) {

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

			StudentNumbersInfoGetter numbersInfoGetter = new StudentNumbersInfoGetter();

			Date date = new Date();

			List programKeyList;

			StudentNumbersInfoGetter sysObject;

			numbersInfoGetter.setUniversityId("0001");
			numbersInfoGetter.setCode("REDAYS");

			/*
			 * code to be set when the method will be drived from the common
			 * controller
			 */
			systemValue.setUniveristyCode("0001%");

			programKeyList = sqlMapClient.queryForList(
					"systemValue.getProgramCourseKeysDetails", systemValue);

			systemValue.setProcessCode("SEP");

			/*
			 * to get the value for the code for number
			 * of reserve days
			 */
			sysObject = (StudentNumbersInfoGetter) sqlMapClient
					.queryForObject(
							"studentenrollment.sysvalue",
							numbersInfoGetter);

			Iterator iterator = programKeyList.iterator();

			while (iterator.hasNext()) {
				SystemValue object = (SystemValue) iterator.next();

				/*
				 * check semester_start_date with todays' date processCode will
				 * be given as input
				 */
				if (systemValue.getProcessCode().equalsIgnoreCase("REG")) {

					Date semesterStartDate = sdf.parse(object
							.getSemesterStartDate());

					long diffDate = calculateDays(date, semesterStartDate);

					if (diffDate <= Integer.parseInt(sysObject.getSystemValue())) {

						object.setProcess("REG");

						/*
						 * insert record in semester_processing_control table
						 */
						sqlMapClient.insert(
								"systemValue.insertSemesterCotrolRecord",
								object);

					}
				}
				/*
				 * check semester_end_date with todays' date processCode will be
				 * given as input
				 */
				else if (systemValue.getProcessCode().equalsIgnoreCase("SEP")) {

					Date semesterEndDate = sdf.parse(object
							.getSemesterEndDate());

					long diffDate = calculateDays(date, semesterEndDate);

					if (diffDate <= Integer.parseInt(sysObject.getSystemValue())) {

						object.setProcess("SEP");

						/*
						 * insert record in semester_processing_control table
						 */
						sqlMapClient.insert(
								"systemValue.insertSemesterCotrolRecord",
								object);
					}
				}
			}

		} catch (Exception e) {
			loggerObject.error("Exception" + e);
		}

		return true;
	}

	/**
	 * method to calculate the difference between two dates
	 * @param startDate todays' date
	 * @param endDate semester start/end date as per the requirement
	 * @return number of days
	 **/
	public long calculateDays(Date startDate, Date endDate) {

		long MS_PER_DAY = 1000 * 60 * 60 * 24;
		long tempDate = (endDate).getTime() - (startDate).getTime();

		return Math.round((tempDate / MS_PER_DAY) + 1);

	}


}
