/*
 * @(#) ProgramCourseSetupDAOImpl.java
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
import in.ac.dei.edrp.cms.dao.programcoursesetup.ProgramCourseSetupDAO;
import in.ac.dei.edrp.cms.domain.programcoursesetup.CourseDetails;
import in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;
/**
 * Creation date: 06-Jan-2011
 * This class implements those methods that is used for creating program course set up.
 * @author <a href="http://aniltiwaripms.blogspot.com" target="_blank">Anil Kumar Tiwari</a>
 * @version 1.0
 */
public class ProgramCourseSetupDAOImpl extends SqlMapClientDaoSupport implements ProgramCourseSetupDAO{

	static final Logger logger = Logger.getLogger(ProgramCourseSetupDAOImpl.class);
	/** 
	 * Method getProgramNameList is used for getting the program list.
	 * @return List of the ProgramMaster.
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster
	 */
	@SuppressWarnings("unchecked")
	
	public List<ProgramMaster> getProgramNameList(String universityId) throws DataAccessException{
		List<ProgramMaster> programNameList = null;
		String likeUniversityId = universityId+"%";
		try{
				programNameList = getSqlMapClientTemplate().queryForList("ProgramCourseSetup.getProgramNameList", likeUniversityId);
		}catch(DataAccessException dae){
			throw new MyException("Program list does not found.");
		}
		return programNameList;
	}

	/** 
	 * Method getBranchNameList is used for getting the branch list.
	 * @param programName object of ProgramMaster
	 * @return List of the ProgramMaster.
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster
	 */
	@SuppressWarnings("unchecked")
	
	public List<ProgramMaster> getBranchNameList(ProgramMaster programName) throws DataAccessException {
		List<ProgramMaster> branchNameList = null;
		try{
				branchNameList = getSqlMapClientTemplate().queryForList("ProgramCourseSetup.getBranchNameList",programName);
		}catch(DataAccessException dae){
			throw new MyException("Branch list does not found.");
		}
		return branchNameList;
	}

	/** 
	 * Method getSemesterList is used for getting the semester list.
	 * @param programName object of ProgramMaster
	 * @return List of the ProgramMaster.
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster
	 */
	@SuppressWarnings("unchecked")
	
	public List<ProgramMaster> getSemesterList(ProgramMaster programName) throws DataAccessException {
		
		List<ProgramMaster> semesterList = null;
		try{
			semesterList = getSqlMapClientTemplate().queryForList("ProgramCourseSetup.getSemesterList",programName);
		}catch(DataAccessException dae){
			throw new MyException("Semester list does not found.");
		}
		return semesterList;
	}

	/** 
	 * Method getSpecializationList is used for getting the specialization list.
	 * @param programMaster object of ProgramMaster
	 * @return HashSet of the ProgramMaster.
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster
	 */
	@SuppressWarnings("unchecked")
	
	public HashSet<ProgramMaster> getSpecializationList(ProgramMaster programMaster) throws DataAccessException {
		
		HashSet<ProgramMaster> specializationList = new HashSet<ProgramMaster>();
			try{
				List<ProgramMaster> semesterDetails = getSqlMapClientTemplate().queryForList("ProgramCourseSetup.getSpecializationList",programMaster);
				specializationList.addAll(semesterDetails);
			}catch(DataAccessException dae){
				throw new MyException("Specialization list does not found.");
			}
			return specializationList;
	}

	/** 
	 * Method getCourseList is used for getting the course list.
	 * @param programDetail object of ProgramMaster
	 * @return List list of the CourseDetails.
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.CourseDetails
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster
	 */
	@SuppressWarnings("unchecked")
	
	public List<List<CourseDetails>> getCourseList(ProgramMaster programDetail) throws DataAccessException {
		List<List<CourseDetails>> courseDetails = new ArrayList<List<CourseDetails>>();
		try{
			List<CourseDetails> courseCtgGrpOpt = getSqlMapClientTemplate().queryForList("ProgramCourseSetup.getCourseCtgGrpOpt", programDetail);
			
			List<CourseDetails> CourseDetailList = getSqlMapClientTemplate().queryForList("ProgramCourseSetup.getCourseDetailList",programDetail);
				
			courseDetails.add(courseCtgGrpOpt);
			courseDetails.add(CourseDetailList);

		}catch(DataAccessException dae){
			throw new MyException("Course list does not found.");
		}
		
		return courseDetails;
	}

	/** 
	 * Method insertProgramCourseHeader is used for inserting the program header details
	 * @param programHeader
	 */	
	
	public void insertProgramCourseHeader(ProgramMaster programHeader) throws DataAccessException{
		try{
			getSqlMapClientTemplate().insert("ProgramCourseSetup.insertProgramHeader", programHeader);
			logger.info("program header inserted successfully");
		}catch(DataAccessException dae){
			throw new MyException("Program course header does not insert");
		}
	}

	/** 
	 * Method checkProgramHeader is used for checking the program header.
	 * @param programHeader object of ProgramMaster
	 * @return String key of the program header.
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster
	 */	
	
	public String checkProgramHeader(ProgramMaster programHeader) throws DataAccessException {
		String programCourseKey = null;
		try{
			programCourseKey = (String)getSqlMapClientTemplate().queryForObject("ProgramCourseSetup.checkProgramHeader", programHeader);
		}catch(DataAccessException dae){
			throw new MyException("Exception comes inside checkProgramHeader method");
		}
		return programCourseKey;
	}

	/** 
	 * Method gettingProgramCourseKey is used for getting the max value of key.
	 * @return String max value of the program header key.
	 */
	
	public String gettingProgramCourseKey() throws DataAccessException {
		String maxKey = null;
		try{
			maxKey = (String)getSqlMapClientTemplate().queryForObject("ProgramCourseSetup.getMaxKey");
		}catch(DataAccessException dae){
			throw new MyException("Exception comes inside gettingProgramCourseKey method");
		}
		return maxKey;
	}

	/** 
	 * Method insertProgramCourseDetail is used for inserting the course details
	 * @param courseDetailList
	 * @param programCourseKey
	 */
	
	public void insertProgramCourseDetail(final String courseDetailList,final String programCourseKey,
			final String creatorId) throws DataAccessException {
		try{
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
         public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
	        	try{
	        		StringTokenizer courseInfo = new StringTokenizer(courseDetailList, ";"); 
	           		executor.startBatch();
	        		while(courseInfo.hasMoreTokens()){
	        			String courseTokens=courseInfo.nextToken();
	         			StringTokenizer courseSubTokens = new StringTokenizer(courseTokens, ",");
	        			CourseDetails courseDetails = new CourseDetails();
	        			while(courseSubTokens.hasMoreTokens()){
	        				courseDetails.setProgramCourseKey(programCourseKey);
	        				courseDetails.setCourseCode(courseSubTokens.nextToken());
	        				courseDetails.setCourseCategory(courseSubTokens.nextToken());
	        				courseDetails.setCourseGroup(courseSubTokens.nextToken());
	        				courseDetails.setCourseAvailability(Boolean.valueOf(courseSubTokens.nextToken())==true?"Y":"N");
	        				courseDetails.setCreatorId(creatorId);
	        				courseDetails.setModifierId(null);
	        				executor.insert("ProgramCourseSetup.insertProgramCourseDetail", courseDetails);
	        			}
	        		}
	           	 executor.executeBatch();
        
	        	}catch(SQLException e){
	        		logger.error("error in insert_ProgramCourseDetail:"+e);
					} 
	        	return null;
	         }
			});
		}catch(DataAccessException dae){
		throw new MyException("Exception in insertProgramCourseDetail");
		}

	}

	/** 
	 * Method getExistingProgramCourseDetails is used for getting the course list.
	 * @param programHeader object of ProgramMaster
	 * @return List list of the CourseDetails.
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.CourseDetails
	 * @see in.ac.dei.edrp.cms.domain.programcoursesetup.ProgramMaster
	 */
	@SuppressWarnings("unchecked")
	
	public List<CourseDetails> getExistingProgramCourseDetails(
			ProgramMaster programHeader) throws DataAccessException {
		List<CourseDetails> courseDetails = null;
		try{
			courseDetails = getSqlMapClientTemplate().queryForList("ProgramCourseSetup.getExistingProgramCourseDetails",programHeader);
		}catch(DataAccessException dae){
			throw new MyException("CourseDetail list does not found.");
		}
		return courseDetails;
	}

}
