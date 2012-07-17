/**
 * @(#) CourseEvaluationCompnentDAOImpl.java
 * Author :Arush Kumar
 * Date :21/3/2011
 * Version 1.0
 * 
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
package in.ac.dei.edrp.cms.daoimpl.courseEvaluationComponent;

import in.ac.dei.edrp.cms.dao.courseEvaluationComponent.CecDAO;
import in.ac.dei.edrp.cms.domain.courseEvaluationComponent.Cec;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * this is Server side Implementation class for Course Evaluation Component
 *
 * @version 1.0 7 MAY 2011
 * @author MOHD AMIR
 */
public class CecDAOImpl extends SqlMapClientDaoSupport implements CecDAO {

	/** Creating object of Logger for log Maintenance */
	private Logger loggerObject = Logger.getLogger(CecDAOImpl.class);

	/**
	 * This method retrieves program course details
	 *
	 * @param cec, object of the Cec
	 * @return pclist, List of type Cec
	 */
	@SuppressWarnings("unchecked")
	public List<Cec> getProgramCourse(Cec cec) {
		List<Cec> pclist = null;
		try {
			pclist = getSqlMapClientTemplate().queryForList(
					"CourseEvaluationComponent.getProgramCourse", cec);
		} catch (Exception e) {
			loggerObject.error(e.getMessage());
		}
		return pclist;
	}

	/**
	 * This method insert the course evaluation details into database
	 *
	 * @param cecList, object of bean class Cec
	 * @return bool,whether record is inserted or not
 	 */
	public boolean insertCecDetail(Cec cec) {
		boolean bool = false;
		try {
			getSqlMapClientTemplate().insert(
					"CourseEvaluationComponent.insertCecDetail", cec);
			bool = true;
		} catch (Exception e) {
			loggerObject.error(e.getMessage());
		}
		return bool;
	}

	/**
	 * This method retrieves list of evaluation ids
	 *
	 * @param cec, object of the Cec
	 * @return evalidlist, List of type Cec
	 */
	@SuppressWarnings("unchecked")
	public List<Cec> getEvaluationIds(Cec cec) {
		List<Cec> evalidlist = new ArrayList<Cec>();
		try {
			evalidlist = getSqlMapClientTemplate().queryForList(
					"CourseEvaluationComponent.getEvaluationIds", cec);
		} catch (Exception e) {
			loggerObject.error(e.getMessage());
		}
		return evalidlist;
	}

	/**
	 * This method retrieves course evaluation component details
	 *
	 * @param cec, object of the Cec
	 * @return ceclist, List of type Cec
	 */
	@SuppressWarnings("unchecked")
	public List<Cec> getCecList(Cec cec) {
		List<Cec> ceclist = new ArrayList<Cec>();
		try {
			ceclist = getSqlMapClientTemplate().queryForList(
					"CourseEvaluationComponent.getCecList", cec);

		} catch (Exception e) {
			loggerObject.error(e.getMessage());
		}
		return ceclist;
	}

	/**
	 * This method retrieves no. of duplicate record
	 *
	 * @param cec, object of the Cec
	 * @return ceclist, List of type Cec
	 */
	@SuppressWarnings("unchecked")
	public List<Cec> getDuplicateCount(Cec cec) {
		List<Cec> ceclist = new ArrayList<Cec>();
		try {
			ceclist = getSqlMapClientTemplate().queryForList(
					"CourseEvaluationComponent.getDuplicateCount", cec);
		} catch (Exception e) {
			loggerObject.error(e.getMessage());
		}
		return ceclist;
	}

	/**
	 * This method update the course evaluation details into database
	 *
	 * @param cecList, Object of bean class Cec
	 * @return bool,whether record is updated or not
 	 */
	public boolean updateCecDetails(Cec cec) {
		boolean bool = false;
		try {
			int count = getSqlMapClientTemplate().update(
					"CourseEvaluationComponent.updateCecDetails", cec);

			if (count > 0) {
				bool = true;
			}
		} catch (Exception e) {
			loggerObject.error(e.getMessage());
		}
		return bool;
	}

	/**
	 * This method delete the course evaluation details from database
	 *
	 * @param cecList, List of type Cec
	 * @return bool,whether records are deleted or not
 	 */
	public boolean deleteCecDetails(List<Cec> cecList) {
		boolean bool = false;
		try {
			int count = 0;
			for (int i = 0; i < cecList.size(); i++) {
				Cec cec = cecList.get(i);

				count = count
						+ getSqlMapClientTemplate().update(
								"CourseEvaluationComponent.deleteCecDetails",
								cec);
			}

			if (count > 0) {
				bool = true;
			}
		} catch (Exception e) {
			loggerObject.error(e.getMessage());
		}
		return bool;
	}
}