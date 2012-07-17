/*
 * @(#) BuildProgramRegistrationDAOImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.programregistration;

import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.common.utility.ProogramRegistrationDate;
import in.ac.dei.edrp.cms.dao.programregistration.BuildProgramRegistrationDAO;
import in.ac.dei.edrp.cms.domain.programregistration.ProgramRegistrationDetails;
import in.ac.dei.edrp.cms.domain.buildnextsession.BuildNextSession;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * Creation date: 10-Feb-2011
 * This class implements those methods that is used for program registration.
 * @author <a href="http://aniltiwaripms.blogspot.com" target="_blank">Anil Kumar Tiwari</a>
 * @version 1.0
 */

public class BuildProgramRegistrationDAOImpl extends SqlMapClientDaoSupport implements BuildProgramRegistrationDAO{

	static final Logger logger = Logger.getLogger(BuildProgramRegistrationDAOImpl.class);

	/**
	 * Method getSessionDate is used for getting the session date of an university.
	 * @param universityId
	 * @return List of the ProgramRegistrationDetails.
	 * @see in.ac.dei.edrp.cms.domain.programregistration.ProgramRegistrationDetails
	 */

	public ProgramRegistrationDetails getSessionDate(String universityId)throws DataAccessException {
		ProgramRegistrationDetails sessionDate = null;
		try{
			sessionDate = (ProgramRegistrationDetails) getSqlMapClientTemplate().queryForObject("ProgramRegistration.getSessionDate",universityId);
		}catch(DataAccessException dae){
			throw new MyException("Session date does not found");
		}
		return sessionDate;
	}

	/**
	 * Method insertProgramRegistration is used for inserting the data.
	 * @param registrationPeriod
	 * @param addDropPeriod
	 * @param sessionStartDate
	 * @param sessionEndDate
	 * @param universityId
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")

	public List<Integer> insertProgramRegistration(final int registrationPeriod, final int addDropPeriod,
			final String sessionStartDate,final String sessionEndDate,final String universityId,final String userId,final String flag) throws ParseException {
		List<Integer> currentSessionInsertInfo= (List<Integer>) getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
	         public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
	        	int numberOfRecordsInserted = 0;
	        	int recordsInLogs = 0;
	        	List<Integer> insertionInfo = new ArrayList<Integer>();
	        	try{
		    			String[] sessionYearMonth = sessionStartDate.split("-");
		    			int sessionYear = Integer.parseInt(sessionYearMonth[0]);
		    			int sessionMonth = Integer.parseInt(sessionYearMonth[1]);
		    			List<ProgramRegistrationDetails> programRegDetailList = getSqlMapClientTemplate().queryForList("ProgramRegistration.getSemesterDate",universityId);
		        		insertionInfo.add(programRegDetailList.size());
		        		Iterator<ProgramRegistrationDetails> programRegDetailIterator = programRegDetailList.iterator();
		        		executor.startBatch();
		        		// starting point
		           		while(programRegDetailIterator.hasNext()){
		           			ProgramRegistrationDetails programDetails = programRegDetailIterator.next();
		           			ProgramRegistrationDetails programRegistrationDetails =
		           				ProogramRegistrationDate.getProgramRegistrationObject(programDetails,
		           						sessionMonth,sessionYear,registrationPeriod,
		           						addDropPeriod,sessionStartDate,
		           						sessionEndDate,userId,universityId,"-");
		        			ProgramRegistrationDetails desiredProgramCourseKey =
		        				(ProgramRegistrationDetails) getSqlMapClientTemplate().queryForObject("ProgramRegistration.checkExistance",programRegistrationDetails);
		        			if(desiredProgramCourseKey==null){
		        					executor.insert("ProgramRegistration.insertProgramRegistrationDetail", programRegistrationDetails);
		        			}
		        			else{
		        				logger.info("this record already exist and key="+programRegistrationDetails.getProgramCourseKey()
		        						+" ,semStartDate="+programRegistrationDetails.getSemesterStartDate()+
		        						" ,semEndDate="+programRegistrationDetails.getSemesterEndDate()+
		        						", sessionStartDate="+programRegistrationDetails.getSessionStartDate()+
		        						", sessionEndDate="+programRegistrationDetails.getSessionEndDate()+
		        						", status="+programRegistrationDetails.getStatus()+
										", entityId="+programRegistrationDetails.getEntityId());
		        				recordsInLogs++;
		        			}
	        			}
		           		insertionInfo.add(recordsInLogs);
		           		numberOfRecordsInserted = executor.executeBatch();
		           		insertionInfo.add(numberOfRecordsInserted);
		           }catch(Exception e){
		        		//executor.executeBatch();
		        		logger.error("error in insertProgramRegistration="+e);
		        		throw new MyException("problem in insertion of program registration");
					}
		        	return insertionInfo;
		         }
				});

		List<Integer> nextSessionInsertInfo= (List<Integer>) getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
	         public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
	        	int numberOfRecordsInserted = 0;
	        	int recordsInLogs = 0;
	        	List<Integer> insertionInfo = new ArrayList<Integer>();
	        	try{
		    			String[] sessionYearMonth = sessionStartDate.split("-");
		    			int sessionYear = Integer.parseInt(sessionYearMonth[0]);
		    			int sessionMonth = Integer.parseInt(sessionYearMonth[1]);
		    			List<ProgramRegistrationDetails> programRegDetailList = getSqlMapClientTemplate().queryForList("ProgramRegistration.getSemesterDate",universityId);
		        		insertionInfo.add(programRegDetailList.size());
		        		Iterator<ProgramRegistrationDetails> programRegDetailIterator = programRegDetailList.iterator();
		        		executor.startBatch();
		        		while(programRegDetailIterator.hasNext()){
		           			ProgramRegistrationDetails programDetails = programRegDetailIterator.next();
		           			ProgramRegistrationDetails programRegistrationDetails =
		           				ProogramRegistrationDate.getProgramRegistrationObject(programDetails,
		           						sessionMonth,sessionYear,registrationPeriod,
		           						addDropPeriod,sessionStartDate,
		           						sessionEndDate,userId,universityId,"-");

		        			/* Ankit code for generation */
		        			ProgramRegistrationDetails programCourseKeyObject =
		        				(ProgramRegistrationDetails) getSqlMapClientTemplate().queryForObject("ProgramRegistration.checkExistanceOfNextSession",programRegistrationDetails);
		        			if(programCourseKeyObject==null){
		        				executor.insert("ProgramRegistration.insertNextSessionProgramRegistrationDetail", programRegistrationDetails);
		        			}
		        			else{
		        				logger.info("this record already exist and key="+programRegistrationDetails.getProgramCourseKey()
		        						+" ,semStartDate="+programRegistrationDetails.getSemesterStartDate()+
		        						" ,semEndDate="+programRegistrationDetails.getSemesterEndDate()+
		        						", sessionStartDate="+programRegistrationDetails.getSessionStartDate()+
		        						", sessionEndDate="+programRegistrationDetails.getSessionEndDate()+
		        						", status="+programRegistrationDetails.getStatus()+
										", entityId="+programRegistrationDetails.getEntityId());
		        				recordsInLogs++;
		        			}
	        			}
		           		insertionInfo.add(recordsInLogs);

		           		numberOfRecordsInserted = executor.executeBatch();

		           	 //For Set Status P in yearend_process_control table if this process run at year end
		           		if(flag!=null && flag.equals("BuildYearEnd")){
		           			insertControlStatus(sessionStartDate,sessionEndDate,universityId,userId,"success");
		           		}
		           		insertionInfo.add(numberOfRecordsInserted);
		           }catch(Exception e){
		        	  //For Set Status E in yearend_process_control table if Any Exception occurred and this process run at year end
		        	   if(flag!=null && flag.equals("BuildYearEnd")){
		        		   insertControlStatus(sessionStartDate,sessionEndDate,universityId,userId,"error");
		           		}
		        		e.printStackTrace();
		        		logger.error("error in insertProgramRegistration="+e);
		        		throw new MyException("problem in insertion of program registration");
					}
		        	return insertionInfo;
		         }
				});

		List<Integer> InsertInfoObject = new ArrayList<Integer>();
		InsertInfoObject.addAll(currentSessionInsertInfo);
		InsertInfoObject.addAll(nextSessionInsertInfo);

		return	InsertInfoObject;
	}

	/**
	 * Method getProgramRegistrationDetails is used for getting all program details which
	 * are not inserted.
	 * @see in.ac.dei.edrp.cms.domain.programregistration.ProgramRegistrationDetails
	 */
	@SuppressWarnings("unchecked")

	public List<ProgramRegistrationDetails> getProgramRegistrationDetails(ProgramRegistrationDetails sessionDate) throws DataAccessException {
		List<ProgramRegistrationDetails> programDetails = null;
		try{
			programDetails = getSqlMapClientTemplate().queryForList("ProgramRegistration.getProgramRegistrationDetail",sessionDate);

		}catch(DataAccessException dae){
			throw new MyException("program details does not found");
		}
		return programDetails;
	}

	/**
	 * Method insertSingleProgramRegistration is used for inserting single record
	 * @param programDetails
	 * @param registrationPeriod
	 * @param addDropPeriod
	 * @param sessionStartDate
	 * @param sessionEndDate
	 * @param universityId
	 * @param userId
	 * @throws ParseException
	 */
	public void insertSingleProgramRegistration(ProgramRegistrationDetails programDetails,
			int registrationPeriod,int addDropPeriod,String sessionStartDate,
			String sessionEndDate,String universityId,String userId) throws ParseException,DataAccessException{
		String[] sessionYearMonth = sessionStartDate.split("-");
		int sessionYear = Integer.parseInt(sessionYearMonth[0]);
		int sessionMonth = Integer.parseInt(sessionYearMonth[1]);
		try{
		ProgramRegistrationDetails programRegistrationDetails =
				ProogramRegistrationDate.getProgramRegistrationObject(programDetails,
						sessionMonth,sessionYear,registrationPeriod,
						addDropPeriod,sessionStartDate,
						sessionEndDate,userId,universityId,"-");
			getSqlMapClientTemplate().insert("ProgramRegistration.insertProgramRegistrationDetail", programRegistrationDetails);
		}catch(DataAccessException dae){
			throw new MyException("Single program could not be inserted.");
		}

	}

	@SuppressWarnings("unchecked")
	public String systemBuildRegistration(ProgramRegistrationDetails sessionDate) {
		try{
			List<ProgramRegistrationDetails> details = getSqlMapClientTemplate().queryForList("ProgramRegistration.checkProgramRegistration", sessionDate);
			if(details.size()==0)
			{
				getSqlMapClientTemplate().insert("ProgramRegistration.systemBuildProgramRegistration",sessionDate);
				getSqlMapClientTemplate().insert("ProgramRegistration.systemUpdatePreviousRegistration",sessionDate);
			}
			else return "alreadyExist";
		}
		catch(DataAccessException dae)
		{
			throw new MyException("Problem in registering for new session");
		}
		return "success";
	}

	/**
	 * Method to check status of build
	 * @param programDetails
	 * @return String, message for status
	 */
	@SuppressWarnings("unchecked")
	public String checkStatus(ProgramRegistrationDetails programDetails) {
		String message="";
		try{
			BuildNextSession buildNextSessionObject=new BuildNextSession();
			buildNextSessionObject.setUniversityId(programDetails.getUniversityId());
			buildNextSessionObject.setDummyFlag("2");
			List<BuildNextSession> processList=getSqlMapClientTemplate().queryForList("buildNextSession.getProcessCode", buildNextSessionObject);
			System.out.println("inside Build Program registrion IMPL process code is  "+processList.get(0).getProcessCode());
			if(processList.size()>0){
				String processCD=processList.get(0).getProcessCode();
				programDetails.setProcessCode(processCD);
				List<ProgramRegistrationDetails> sessionDetail = getSqlMapClientTemplate().queryForList("ProgramRegistration.getCurrentSession",programDetails);
				programDetails.setSessionStartDate(sessionDetail.get(0).getSessionStartDate());
				programDetails.setSessionEndDate(sessionDetail.get(0).getSessionEndDate());
	
				List<ProgramRegistrationDetails> previousProcessDetail = getSqlMapClientTemplate().queryForList("ProgramRegistration.getPreviousProcessDetail",programDetails);
				if(previousProcessDetail.size()>0){
					programDetails.setProcessCode(previousProcessDetail.get(0).getProcessCode());
					List<ProgramRegistrationDetails> controlList=getSqlMapClientTemplate().queryForList("ProgramRegistration.getControlDetailStatus",programDetails);
					if(controlList.size()==0){
						message="buildPrevious";
					}
					else if(controlList.get(0).getStatus().equals("E")){
						message="buildPrevious";
					}
					else if(controlList.get(0).getStatus().equals("P")){
						programDetails.setProcessCode(processCD);
						List<ProgramRegistrationDetails>controllist=getSqlMapClientTemplate().queryForList("ProgramRegistration.getControlDetailStatus",programDetails);
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
			}
			else{
				message="NOProcessCode";
			}
		}
		catch(Exception ex){
			message="error";
			logger.error("Error inside BuildProgramRegistrationDAOImpl method checkStatus "+ex);
		}
		return message;

	}

	/**
	 * Method to set status in yearend Process Control table
	 * @param String sessionStartDate
	 * @param String sessionEndDate
	 * @param String universityId
	 * @param String status
	 */
	@SuppressWarnings("unchecked")
	public void insertControlStatus(String sessionStartDate,String sessionEndDate,String universityId,String userId,String status){
		ProgramRegistrationDetails programDetails=new ProgramRegistrationDetails();
		programDetails.setUniversityId(universityId);
		programDetails.setSessionStartDate(sessionStartDate);
		programDetails.setSessionEndDate(sessionEndDate);
		programDetails.setCreatorId(userId);
		
		BuildNextSession buildNextSessionObject=new BuildNextSession();
		buildNextSessionObject.setUniversityId(programDetails.getUniversityId());
		buildNextSessionObject.setDummyFlag("2");
		List<BuildNextSession> processList=getSqlMapClientTemplate().queryForList("buildNextSession.getProcessCode", buildNextSessionObject);
		if(processList.size()>0){
			programDetails.setProcessCode(processList.get(0).getProcessCode());
			List<ProgramRegistrationDetails>controllist=getSqlMapClientTemplate().queryForList("ProgramRegistration.getControlDetailStatus",programDetails);
			if(controllist.size()==0){
				if(status.equals("error")){
					logger.info("Some Error Occured so set status 'E' in yearend_process_control table for Process Code 'BPR' ");
					programDetails.setStatus("E");
					getSqlMapClientTemplate().insert("ProgramRegistration.setControlDetailStatus",programDetails);
				}
				else{
					programDetails.setStatus("P");
					getSqlMapClientTemplate().insert("ProgramRegistration.setControlDetailStatus",programDetails);
				}
			}
			else if(controllist.get(0).getStatus().equals("E")){
				if(status.equals("error")){
					logger.info("Some Error Occured so yearend_process_control table is not updated");
				}
				else{
					programDetails.setStatus("P");
					getSqlMapClientTemplate().update("ProgramRegistration.updateControlDetailStatus",programDetails);
				}
			}
		}
		
	}

}
