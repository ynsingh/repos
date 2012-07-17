/*
 * @(#) ManageProgramCourseSetupDAOImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.programcoursesetup;

import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.dao.programcoursesetup.ManageProgramCourseSetupDAO;
import in.ac.dei.edrp.cms.domain.programcoursesetup.CourseDetails;
import in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster;

import java.sql.SQLException;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;
/**
 * Creation date: 12-Jan-2011
 * This class implements those methods that is used for managing program course set up.
 * @author <a href="http://aniltiwaripms.blogspot.com" target="_blank">Anil Kumar Tiwari</a>
 * @version 1.0
 */
public class ManageProgramCourseSetupDAOImpl extends SqlMapClientDaoSupport implements ManageProgramCourseSetupDAO{

	static final Logger logger = Logger.getLogger(ManageProgramCourseSetupDAOImpl.class);
	/** 
	 * Method getProgramCourseHeaderList is used for getting the program course header list.
	 * @return List of the ProgramMaster.
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster
	 */
	@SuppressWarnings("unchecked")
	
	public List<ProgramMaster> getProgramCourseHeaderList(String universityId) throws DataAccessException {
		List<ProgramMaster> programCourseHeaderList = null;
		try{
			programCourseHeaderList = getSqlMapClientTemplate().queryForList("ManageProgramCourseSetup.getProgramCourseHeaderList", universityId);
		}catch(DataAccessException dae){
			throw new MyException("Program course header list does not found.");
		}
		return programCourseHeaderList;
	}

	/** 
	 * Method getProgramCourseDetails is used for getting the program course list according program header.
	 * @param programCourseKey
	 * @return List of the CourseDetails.
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.CourseDetails
	 */
	@SuppressWarnings("unchecked")
	
	public List<CourseDetails> getProgramCourseDetails(String programCourseKey) throws DataAccessException{
		List<CourseDetails> programCourseDetails = null;
		try{
			programCourseDetails = getSqlMapClientTemplate().queryForList("ManageProgramCourseSetup.getProgramCourseDetails",programCourseKey);
		}catch(DataAccessException dae){
			throw new MyException("Program course detail list does not found.");
		}
		return programCourseDetails;
	}

	/** 
	 * Method getProgramCourseCtgOptGrp is used for getting the category, group and option 
	 * of program course.
	 * @return List of the CourseDetails.
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.CourseDetails
	 */
	@SuppressWarnings("unchecked")
	
	public List<CourseDetails> getProgramCourseCtgOptGrp(String universityId) throws DataAccessException{
		List<CourseDetails> programCourseCtgOptGrp = null;
		try{
			programCourseCtgOptGrp = getSqlMapClientTemplate().queryForList("ProgramCourseSetup.getCourseCtgGrpOpt", universityId);
		}catch(DataAccessException dae){
			throw new MyException("List of course category,group,option does not found.");
		}
		return programCourseCtgOptGrp;
	}

	/** 
	 * Method updateProgramCourseCtgOptGrp is used for updating the category, group, option 
	 * and availability of program course.
	 * @param courseCtgGrpOpt
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.CourseDetails
	 */
	
	public String updateProgramCourseCtgOptGrp(CourseDetails courseCtgGrpOpt)throws DataAccessException {
		try{
			int value=getSqlMapClientTemplate().update("ManageProgramCourseSetup.updateCourseCtgGrpOpt",courseCtgGrpOpt);
			logger.info("after updating the course category, group and option.");
			String count=Integer.toString(value);
			return count;
		}catch(DataAccessException dae){
			throw new MyException("Exception in updateProgramCourseCtgOptGrp");
		}
		catch(Exception e){
			logger.error("error in deleteDesiredProgramCourse:"+e);
			return "updateError"+e.getMessage();
		}
	}

	/** 
	 * Method deleteDesiredProgramCourse is used for deleting the selected program course.
	 * @param programCourseKey
	 * @param courseCodes
	 */
	
	public String deleteDesiredProgramCourse(final String programCourseKey, final String courseCodes) throws DataAccessException{
		String result1=null;
		try{
		result1=(String)getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
	         public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
	        		String result=null;
	        	 try{
	        	 StringTokenizer courseCode = new StringTokenizer(courseCodes, ",");
		        		executor.startBatch();
		        		CourseDetails courseDetails = new CourseDetails();
		        		int value=0;
		        			while(courseCode.hasMoreTokens()){
		        				courseDetails.setProgramCourseKey(programCourseKey);
		        				courseDetails.setCourseCode(courseCode.nextToken());
		        			    value=executor.delete("ManageProgramCourseSetup.deleteProgramCourse",courseDetails);
		        			    
		        			}
		        			
		        			 String count=Integer.toString(value+1);
		        			 result=count;
		        			 executor.executeBatch();
		        			 return result;
			        	}catch(Exception e){
			        		System.out.println("deleteImpl"+e.getMessage());
		        		logger.error("error in deleteDesiredProgramCourse:"+e);
		        		result="deleteError"+e.getMessage();
		        		return result;
						} 
		         }
			});
		}catch(DataAccessException dae){
		throw new MyException("Exception in deleteDesiredProgramCourse");
		}
		catch (Exception e) {
    		logger.error("error in deleteDesiredProgramCourse:"+e);
    		return "deleteError"+e.getMessage();
		}
		return result1;
	}
	/** 
	 * Method changeStatusDesiredProgramCourseHeader is used for changing the status of program course.
	 * @param programCourseKeys
	 */
	
	public void changeStatusDesiredProgramCourseHeader(final String programCourseKeys, final String modifierId) throws DataAccessException{
		try{
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
	         public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
		        	try{
	        	 StringTokenizer programCourseKey = new StringTokenizer(programCourseKeys, ",");
		        		executor.startBatch();
		        		ProgramMaster programHeader = new ProgramMaster();
		        			while(programCourseKey.hasMoreTokens()){
		        				programHeader.setProgramCourseKey(programCourseKey.nextToken());
		        				programHeader.setModifierId(modifierId);
		        				executor.update("ManageProgramCourseSetup.changeStatusProgramCourseHeader",programHeader);
		        			}
		        			 executor.executeBatch();
			        	}catch(Exception e){
			        		logger.error("error in changeStatusDesiredProgramCourseHeader:"+e);
						} 
		        	return null;
		         }
			});
		}catch(DataAccessException dae){
		throw new MyException("Exception in changeStatusDesiredProgramCourseHeader");
		}
	}

}
