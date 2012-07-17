/**
 * @(#) CourseWisePanelOfExaminersImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.reportgeneration;

import java.util.ArrayList;
import java.util.List;

import in.ac.dei.edrp.cms.dao.reportgeneration.CourseWisePanelOfExaminersDao;
import in.ac.dei.edrp.cms.domain.externalexaminercourse.ExternalExaminerCourseInfoGetter;
import in.ac.dei.edrp.cms.domain.reportgeneration.FinalSemesterResultStatistics;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * This file consist of the methods used at the Activity Master process.
 * @author Mandeep Sodhi
 * @date 8 Jan 2012
 * @version 1.0
 */
public class CourseWisePanelOfExaminersImpl extends
SqlMapClientDaoSupport implements CourseWisePanelOfExaminersDao {
	private Logger loggerObject = Logger
	.getLogger(CourseWisePanelOfExaminersImpl.class);
	
	/**Method to Get Course Name 
	 *@param object of ExternalExaminerCourseInfoGetter Bean
	 *@return List<getCourseName> 
	 **/
	public List<ExternalExaminerCourseInfoGetter> getCourseName(ExternalExaminerCourseInfoGetter input) {
        List<ExternalExaminerCourseInfoGetter>getCourseName=new ArrayList<ExternalExaminerCourseInfoGetter>();

          try{
        	   getCourseName=getSqlMapClientTemplate().queryForList("examinerPdf.getCourseName",input);
        	  return getCourseName;
          }
          catch(Exception e){
        	  loggerObject.error("in getCourseName" + e);
          }
          return getCourseName;
	}
	
	/**Method to Get Examiner List
	 *@param object of ExternalExaminerCourseInfoGetter Bean
	 *@return List<result> 
	 **/
	public List<ExternalExaminerCourseInfoGetter> getExaminerList(
			ExternalExaminerCourseInfoGetter input) {
		List<ExternalExaminerCourseInfoGetter>result=new ArrayList<ExternalExaminerCourseInfoGetter>();
		try{
			result=getSqlMapClientTemplate().queryForList("examinerPdf.getExaminerId",input);
			return result;
		}
		catch (Exception e) {
			loggerObject.error("in getCourseName" + e);
		}
		return result;
	}

	/**Method to Get Course Code List
	 *@param object of ExternalExaminerCourseInfoGetter Bean
	 *@return List<getCourseList> 
	 **/
	public List<ExternalExaminerCourseInfoGetter> getcourseList(
			ExternalExaminerCourseInfoGetter input) {
        List<ExternalExaminerCourseInfoGetter>getCourseList=new ArrayList<ExternalExaminerCourseInfoGetter>();

        try{
        	getCourseList=getSqlMapClientTemplate().queryForList("examinerPdf.getCourseList",input);
      	  return getCourseList;
        }
        catch(Exception e){
      	  loggerObject.error("in getCourseList" + e);
        }
        return getCourseList;
	}

	/**Method to Get Program Id List
	 *@param object of ExternalExaminerCourseInfoGetter Bean
	 *@return List<getProgramList> 
	 **/
	public List<ExternalExaminerCourseInfoGetter> getProgramId(
			ExternalExaminerCourseInfoGetter input) {
        List<ExternalExaminerCourseInfoGetter>getProgramList=new ArrayList<ExternalExaminerCourseInfoGetter>();

        try{
        	getProgramList=getSqlMapClientTemplate().queryForList("examinerPdf.getProgram",input);

      	  return getProgramList;
        }
        catch(Exception e){
      	  loggerObject.error("in getProgramList" + e);
        }
        return getProgramList;
	}

	/**Method to Get the class List
	 *@param object of ExternalExaminerCourseInfoGetter Bean
	 *@return List<getClassList> 
	 **/
	@SuppressWarnings("unchecked")
	public List<ExternalExaminerCourseInfoGetter> getClassList(
			ExternalExaminerCourseInfoGetter input) {
        List<ExternalExaminerCourseInfoGetter>getClassList=new ArrayList<ExternalExaminerCourseInfoGetter>();

        try{
        	getClassList=getSqlMapClientTemplate().queryForList("examinerPdf.getCourseClass",input);

      	  return getClassList;
        }
        catch(Exception e){
      	  loggerObject.error("in getClassList" + e);
        }
        return getClassList;
	}
	/**Method to Get the details For Generating Report
	 *@param object of ExternalExaminerCourseInfoGetter Bean
	 *@return List<getDelayDetailList> 
	 **/
	@SuppressWarnings("unchecked")
	public List<FinalSemesterResultStatistics> getDelayDetails(
			FinalSemesterResultStatistics input) {
		List<FinalSemesterResultStatistics>getDelayDetailList=new ArrayList<FinalSemesterResultStatistics>();
		try{
			getDelayDetailList=getSqlMapClientTemplate().queryForList("examinerPdf.getDelayDetailList",input);
		}
		catch(Exception e){
			loggerObject.error("in getClassList" + e);
		}
		return getDelayDetailList;
	}
	/**Method to Get the program Course Key List
	 *@param object of ExternalExaminerCourseInfoGetter Bean
	 *@return List<programCourseKeyList> 
	 **/
	@SuppressWarnings("unchecked")
	public List<FinalSemesterResultStatistics> getProgramCourseKey(
			FinalSemesterResultStatistics input) {
		List<FinalSemesterResultStatistics>programCourseKeyList=new ArrayList<FinalSemesterResultStatistics>();
		try{
			programCourseKeyList=getSqlMapClientTemplate().queryForList("examinerPdf.getProgramCourseKey",input);
		}
		catch (Exception e) {
			loggerObject.error("in getClassList" + e);
		}
		return programCourseKeyList;
	}
	
	/**Method to Get the details For Generating Report
	 *@param object of ExternalExaminerCourseInfoGetter Bean
	 *@return List<getDelayDetailList> 
	 **/
	@SuppressWarnings("unchecked")
	public List<FinalSemesterResultStatistics> getDelayDetailEntityWise(
			FinalSemesterResultStatistics input) {
		List<FinalSemesterResultStatistics>getDelayDetailList=new ArrayList<FinalSemesterResultStatistics>();
		try{
			getDelayDetailList=getSqlMapClientTemplate().queryForList("examinerPdf.getDelayDetailEntityWise",input);
		}
		catch(Exception e){
			loggerObject.error("in getClassList" + e);
		}
		return getDelayDetailList;
	}
	
	/**Method to Get the program Course Key List
	 *@param object of ExternalExaminerCourseInfoGetter Bean
	 *@return List<programCourseKeyList> 
	 **/
	@SuppressWarnings("unchecked")
	public List<FinalSemesterResultStatistics> getProgramCourseKeyEntityWise(
			FinalSemesterResultStatistics input) {
		List<FinalSemesterResultStatistics>programCourseKeyList=new ArrayList<FinalSemesterResultStatistics>();
		try{
			programCourseKeyList=getSqlMapClientTemplate().queryForList("examinerPdf.getProgramCourseKeyEntityWise",input);
		}
		catch (Exception e) {
			loggerObject.error("in getClassList" + e);
		}
		return programCourseKeyList;
	}
	
	/**Method to Get the details For Generating Report
	 *@param object of FinalSemesterResultStatistics Bean
	 *@return List<getCoursesList> 
	 **/	
	@SuppressWarnings("unchecked")
	public List<FinalSemesterResultStatistics> getcoursesMarksReleasedByDean(
			FinalSemesterResultStatistics input) {
		List<FinalSemesterResultStatistics>getCoursesList=new ArrayList<FinalSemesterResultStatistics>();
		try{
			getCoursesList=getSqlMapClientTemplate().queryForList("examinerPdf.getCoursesMarksReleasedByDean",input);
		}
		catch(Exception e){
			loggerObject.error("in getClassList" + e);
		}
		return getCoursesList;
	}

	public List<FinalSemesterResultStatistics> getEntityProgramList(
			FinalSemesterResultStatistics input) {
		List<FinalSemesterResultStatistics>getEntityProgram=new ArrayList<FinalSemesterResultStatistics>();
		try{
			getEntityProgram=getSqlMapClientTemplate().queryForList("examinerPdf.getEntityProgram",input);
		}
		catch(Exception e){
			loggerObject.error("in getClassList" + e);
		}
		return getEntityProgram;
	}

	public List<FinalSemesterResultStatistics> getReportProgramList(
			FinalSemesterResultStatistics input) {
		List<FinalSemesterResultStatistics>programList=new ArrayList<FinalSemesterResultStatistics>();
		try{
			programList=getSqlMapClientTemplate().queryForList("examinerPdf.getProgramForReport",input);
		}
		catch(Exception e){
			loggerObject.error("in getClassList" + e);
		}
		return programList; 
	}
}


