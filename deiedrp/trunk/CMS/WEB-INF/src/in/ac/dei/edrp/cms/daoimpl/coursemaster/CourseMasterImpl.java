/**
 * @(#) CourseMasterImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.coursemaster;

import in.ac.dei.edrp.cms.common.utility.MyException;
import in.ac.dei.edrp.cms.dao.coursemaster.CourseMasterService;
import in.ac.dei.edrp.cms.daoimpl.universityreservation.UniversityReservationServiceImpl;
import in.ac.dei.edrp.cms.domain.coursemaster.CourseMasterBean;
import in.ac.dei.edrp.cms.domain.university.UnivRoleInfoGetter;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

/**
 * this is Server side Implementation class for CourseMaster
 * 
 * @version 1.0 21 FEB 2011
 * @author MOHD AMIR
 */
public class CourseMasterImpl extends SqlMapClientDaoSupport implements
		CourseMasterService {

	/** Creating object of Logger for log Maintenance */
	private static Logger logObj = Logger
			.getLogger(UniversityReservationServiceImpl.class);

	/**
	 * This method delete the Course details from database
	 *
	 * @param courseMasterBean, object of bean class CourseMasterBean
	 * @return count,Number of record deleted
	 * @throws Exception 
	 */
	public int deleteCourseDetails(CourseMasterBean courseMasterBean) throws Exception {
		try {
			int count = getSqlMapClientTemplate().delete(
					"courseMaster.deleteCourseDetails", courseMasterBean);
			return count;
		} 
		catch(Exception e){
			logObj.error(e.getMessage());
			throw new MyException("ForiegnKeyConstraint"+e.getMessage());
		}
	}

	/**
	 * This method retrieves list of Branch with their code
	 *
	 * @param ownerProgramId, id of the program
	 * @return branchList, List of type CourseMasterBean
	 */
	@SuppressWarnings("unchecked")
	public List<CourseMasterBean> getBranch(CourseMasterBean courseMasterBean) {
		try {
			List<CourseMasterBean> branchList = getSqlMapClientTemplate()
					.queryForList("courseMaster.getBranchList", courseMasterBean);
			return branchList;
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return null;
	}

	/**
	 * This method retrieves list of Components with their code
	 *
	 * @param courseMasterBean, object of the CourseMasterBean
	 * @return componentInfo, List of type UnivRoleInfoGetter
	 */
	@SuppressWarnings("unchecked")
	public List<UnivRoleInfoGetter> getComponents(
			CourseMasterBean courseMasterBean) {
		try {
			List<UnivRoleInfoGetter> componentInfo = getSqlMapClientTemplate()
					.queryForList("unirolesetup.getComponentsInfo",
							courseMasterBean);
			return componentInfo;
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return null;
	}

	/**
	 * This method get duplicate record count from database 
	 *
	 * @param courseMasterBean, object of bean class CourseMasterBean
	 * @return count,Number of record found
	 */
	@SuppressWarnings("unchecked")
	public int getDuplicateCourseCount(CourseMasterBean courseMasterBean) {
		try {
			List<CourseMasterBean> countList = getSqlMapClientTemplate()
					.queryForList("courseMaster.getDuplicateCourseCount",
							courseMasterBean);
			return countList.get(0).getCount();
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return 0;
	}

	/**
	 * This method retrieves list of Branch with their code
	 *
	 * @param universityCode, id of the university
	 * @return entityList, List of type CourseMasterBean
	 */
	@SuppressWarnings("unchecked")
	public List<CourseMasterBean> getEntities(String universityCode) {
		try {
			List<CourseMasterBean> entityList = getSqlMapClientTemplate()
					.queryForList("courseMaster.getEntityList", universityCode);
			return entityList;
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return null;
	}

	/**
	 * This method retrieves list of Programs with their code
	 *
	 * @param universityCode, id of the university
	 * @return programList, List of type CourseMasterBean
	 */
	@SuppressWarnings("unchecked")
	public List<CourseMasterBean> getPrograms(String ownerEntityId) {
		try {
			List<CourseMasterBean> programList = getSqlMapClientTemplate()
					.queryForList("courseMaster.getProgramList",
							ownerEntityId);
			return programList;
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return null;
	}

	/**
	 * This method retrieves list of Programs with their code
	 *
	 * @param courseMasterBean,object of the CourseMasterBean
	 * @return specializationList, List of type CourseMasterBean
	 */
	@SuppressWarnings("unchecked")
	public List<CourseMasterBean> getSpecialization(
			CourseMasterBean courseMasterBean) {
		try {
			List<CourseMasterBean> specializationList = getSqlMapClientTemplate()
					.queryForList("courseMaster.getSpecializationList",
							courseMasterBean);
			return specializationList;
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return null;
	}

	/**
	 * This method insert the course group into database
	 *
	 * @param courseMasterBean, object of bean class CourseMasterBean
	 */
	public void setCourseDetails(CourseMasterBean courseMasterBean)throws Exception{
		try {
			getSqlMapClientTemplate().insert("courseMaster.setCourseDetails",
					courseMasterBean);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			logObj.error(e.getMessage());
			throw new MyException("InsertError"+e.getMessage());
		}
	}

	/**
	 * This method update the Course details from database
	 *
	 * @param courseMasterBean, object of bean class CourseMasterBean
	 * @return count,Number of record updated
	 */
	public int updateCourseDetails(CourseMasterBean courseMasterBean) throws Exception {
		int count=0;
		try {
			 count = getSqlMapClientTemplate().update(
					"courseMaster.updateCourseDetails", courseMasterBean);
			System.out.println("count update is"+count);
			return count;
		} catch (Exception e) {
			logObj.error(e.getMessage());
			throw new MyException("updateError"+e.getMessage());
		}
	}

	/**
	 * This method retrieves all course Details from database
	 *
	 * @param courseMasterBean, object of the CourseMasterBean
	 * @return detailsList containing Course Details
	 */
	@SuppressWarnings("unchecked")
	public List<CourseMasterBean> getCourseDetails(
			CourseMasterBean courseMasterBean) {
		try {
			List<CourseMasterBean> detailsList = getSqlMapClientTemplate()
					.queryForList("courseMaster.getCourseDetails",
							courseMasterBean);
			return detailsList;
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return null;
	}
}