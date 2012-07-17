/*
 * @(#) AwardBlankCorrectionDaoImpl.java
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

package in.ac.dei.edrp.cms.daoimpl.correctionInAwardBlank;

import java.util.List;
import java.util.StringTokenizer;
import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.dao.correctionInAwardBlank.AwardBlankCorrectionDao;
import in.ac.dei.edrp.cms.domain.awardsheet.AwardSheetInfoGetter;

/**
 * Implementation class of AwardBlankCorrectionDao interface
 * @author Dheeraj Singh
 * @date 09-APR-2012
 * @version 1.0
 */
public class AwardBlankCorrectionDaoImpl extends SqlMapClientDaoSupport
	implements AwardBlankCorrectionDao{

	private static Logger logObj = Logger.getLogger(AwardBlankCorrectionDaoImpl.class);

	/**
     * Method for getting details list of given student
     * @param input
     * @return detailslist
     */
	public List<AwardSheetInfoGetter> getCurrentStatus(AwardSheetInfoGetter input){
		List<AwardSheetInfoGetter> detailslist=null;
		try{
			logObj.info("checking status of award blank");

			detailslist=getSqlMapClientTemplate().queryForList("AwardBlankCorrection.getCurrentDetails", input);

		}catch(Exception ex){
			System.out.println(ex);
		}
		return detailslist;
	}

	/**
     * Method for getting marks of given student
     * @param input
     * @return markslist
     */
	public List<AwardSheetInfoGetter> getMarks(AwardSheetInfoGetter input){
		List<AwardSheetInfoGetter> markslist=null;
		try{
			logObj.info("checking status of award blank");

			markslist=getSqlMapClientTemplate().queryForList("AwardBlankCorrection.getStudentMarks", input);

		}catch(Exception ex){
			System.out.println(ex);
		}
		return markslist;
	}

	/**
     * Method for updating marks of given student
     * @param input
     * @param data
     * @return null
     */
	public String saveStudentMarks(AwardSheetInfoGetter input,
			StringTokenizer data) {
		String total = "0";
		String grade = "";
		String status="";
		AwardSheetInfoGetter inputObj = new AwardSheetInfoGetter();
		try{
		System.out.println(input.getEmployeeId()+" "
				+input.getUniversityId()+" "
				+input.getCourseCode()+" "
				+input.getDisplayType()+" "
				+input.getProgramCourseKey()+" "
				+input.getSemesterStartDate()+" "
				+input.getSemesterEndDate());
		inputObj = (AwardSheetInfoGetter) getSqlMapClientTemplate().queryForObject("AwardBlankCorrection.employeeDetails", input);
		input.setEntityId(inputObj.getEntityId());
		input.setModifierId(inputObj.getEmployeeCode());
		System.out.println(input.getEntityId()+" "+input.getCreatorId());
		while(data.hasMoreTokens()){
			StringTokenizer rowData = new StringTokenizer(data.nextToken(),"|");
			input.setRollNumber(rowData.nextToken());
            input.setEvaluationId(rowData.nextToken());
            inputObj = (AwardSheetInfoGetter) getSqlMapClientTemplate().queryForObject("AwardBlankCorrection.getComponentMarks", input);
            input.setOldmarks(inputObj.getOldmarks());
            String oldAttandence = inputObj.getAttendence();
            String idType = rowData.nextToken();
            String updatedMarks = rowData.nextToken();
            if(isInteger(updatedMarks)){
            	input.setAttendence("P");
            }else{
            	updatedMarks = "0" ;
            	input.setAttendence("ABS");
            }

            if(rowData.hasMoreTokens()){
              	total=rowData.nextToken();
            }

            if(rowData.hasMoreTokens()){
            	grade=rowData.nextToken();
            }

            System.out.println(input.getRollNumber()+" "
            		+input.getEvaluationId()+" "
            		+idType+" "
            		+updatedMarks+" "
            		+total+" "
            		+grade);
            if(idType.equalsIgnoreCase("MK")){
            	if(Integer.parseInt(input.getOldmarks())==Integer.parseInt(updatedMarks) &&
            			oldAttandence.equalsIgnoreCase(input.getAttendence())){
            		status = "No Change";
            		System.out.println("Status : " + status);
            	}else{
            		input.setMarks(updatedMarks);
            		if(input.getDisplayType().equalsIgnoreCase("R")){
            			input.setGrades(grade);
            		}else{
            			input.setGrades(null);
            		}
            		int updateCountStudentMarks = getSqlMapClientTemplate().update("AwardBlankCorrection.updateStudentMarks", input);
            	}
            }
		}
		if(input.getDisplayType().equalsIgnoreCase("I") || input.getDisplayType().equalsIgnoreCase("R")){
			input.setTotalInternal(total);
			input.setInternalGrade(grade);
			int updateCountStudentMarksSummary = getSqlMapClientTemplate().update("AwardBlankCorrection.updateStudentMarksSummaryInternal", input);
		}
		if(input.getDisplayType().equalsIgnoreCase("E")){
			input.setTotalExternal(total);
			input.setExternalGrade(grade);
			int updateCountStudentMarksSummary = getSqlMapClientTemplate().update("AwardBlankCorrection.updateStudentMarksSummaryExternal", input);
		}

		}catch(Exception ex){
			System.out.println(ex);
			logObj.error("saveStudentMarks: "+ex);
            throw new MyException("E");
		}

		return null;
	}

	/**
     * Method for checking datatype of a value
     * @param s1
     * @return boolean value
     */
	public boolean isInteger(String s1){
	   try{
	      Double.parseDouble(s1);
	      return true;
	   }
	   catch(Exception ex){
	      return false;
	   }
	}

	/**
	     * Method for getting courses of a given program
	     * @param input
	     * @return courseList
	     */
		public List<AwardSheetInfoGetter> getCourses(AwardSheetInfoGetter input){
			List<AwardSheetInfoGetter> courseList=null;
			try{
				logObj.info("checking status of award blank");
				courseList=getSqlMapClientTemplate().queryForList("AwardBlankCorrection.getCourses", input);

			}catch(Exception ex){
				System.out.println(ex);
			}
			return courseList;
		}

		/**
	     * Method for getting semester dates of a given program
	     * @param input
	     * @return dateList
	     */
		public List<AwardSheetInfoGetter> getSemesterDates(AwardSheetInfoGetter input){
			List<AwardSheetInfoGetter> dateList=null;
			try{
				logObj.info("checking status of award blank");
				System.out.println(input.getSpecializationId() + input.getSemesterStartDate() + input.getSemesterEndDate());
				dateList=getSqlMapClientTemplate().queryForList("AwardBlankCorrection.getSemesterDates", input);

			}catch(Exception ex){
				System.out.println(ex);
			}
			return dateList;
		}

		/**
	     * Method for getting employee details
	     * @param input
	     * @return employeeList
	     */
		public List<AwardSheetInfoGetter> getEmployeeDetail(AwardSheetInfoGetter input){
			List<AwardSheetInfoGetter> employeeList=null;
			try{
				logObj.info("checking status of award blank");
				System.out.println(input.getSpecializationId() + input.getSemesterStartDate() + input.getSemesterEndDate());
				employeeList=getSqlMapClientTemplate().queryForList("AwardBlankCorrection.getEmployeeDetail", input);

			}catch(Exception ex){
				System.out.println(ex);
			}
			return employeeList;
		}

		/**
	     * Method for getting components
	     * @param input
	     * @return componentList
	     */
		public List<AwardSheetInfoGetter> getComponents(AwardSheetInfoGetter input){
			List<AwardSheetInfoGetter> componentList=null;
			try{
				logObj.info("checking status of award blank");
				componentList=getSqlMapClientTemplate().queryForList("AwardSheet.getEvaluationComponent", input);

			}catch(Exception ex){
				System.out.println(ex);
			}
			return componentList;
		}

		/**
	     * Method for getting marks of all students
	     * @param input
	     * @return marksList
	     */
		public List<AwardSheetInfoGetter> getStudentMarks(AwardSheetInfoGetter input){
			List<AwardSheetInfoGetter> marksList=null;
			try{
				logObj.info("checking status of award blank");
				marksList=getSqlMapClientTemplate().queryForList("AwardBlankCorrection.getStudentMarks1", input);

			}catch(Exception ex){
				System.out.println(ex);
			}
			return marksList;
	}


}
