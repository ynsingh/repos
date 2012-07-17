/**
 * @(#) SystemTableTwoImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.systemtabletwo;

import in.ac.dei.edrp.cms.dao.systemtabletwo.SystemTableTwoService;
import in.ac.dei.edrp.cms.daoimpl.universityreservation.UniversityReservationServiceImpl;
import in.ac.dei.edrp.cms.domain.systemtabletwo.SystemTableTwoBean;
import in.ac.dei.edrp.cms.domain.utility.SystemValue;

import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * this is Server side Implementation class for SystemTableTwo
 *
 * @version 1.0 14 FEB 2011
 * @author MOHD AMIR
 */
public class SystemTableTwoImpl extends SqlMapClientDaoSupport implements
		SystemTableTwoService {

	/** Creating object of Logger for log Maintenance */
	private static Logger logObj = Logger
			.getLogger(UniversityReservationServiceImpl.class);

	/**
	 * This method check for duplicate record in database
	 *
	 * @param systemTableTwoBean
	 *            , object of bean class SystemTableTwoBean
	 * @return countList,Number of record found for given key values
	 */
	@SuppressWarnings("unchecked")
	public int getDuplicateComponentCount(SystemTableTwoBean systemTableTwoBean) {
		int countList = 1;
		try {
			List<SystemTableTwoBean> countBean = getSqlMapClientTemplate()
					.queryForList("systemTableTwo.getCount", systemTableTwoBean);
			countList = countBean.get(0).getCount();
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return countList;
	}

	/**
	 * This method retrieves all group component details from database
	 *
	 * @param systemTableTwoBean
	 *            , object of the SystemTableTwoBean
	 * @return groupDetails containing group component details
	 */
	@SuppressWarnings("unchecked")
	public List<SystemTableTwoBean> getGroupDetails(
			SystemTableTwoBean systemTableTwoBean) {
		List<SystemTableTwoBean> groupDetails = null;
		try {
			groupDetails = getSqlMapClientTemplate().queryForList(
					"systemTableTwo.getGroupInfo", systemTableTwoBean);
			return groupDetails;
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return groupDetails;
	}

	/**
	 * This method insert the component details into database
	 *
	 * @param systemTableTwoBean
	 *            , object of bean class SystemTableTwoBean
	 */
	public void setComponentDetails(SystemTableTwoBean systemTableTwoBean) {
		try {
			getSqlMapClientTemplate().insert("systemTableTwo.setComponentInfo",
					systemTableTwoBean);
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
	}

	/**
	 * This method delete the component details from database
	 *
	 * @param systemTableTwoBean
	 *            , object of bean class SystemTableTwoBean
	 * @return deleteCount,Number of record deleted
	 */
	public int deleteComponent(SystemTableTwoBean systemTableTwoBean) {
		int deleteCount = 0;
		try {
			deleteCount = getSqlMapClientTemplate().delete(
					"systemTableTwo.deleteComponent", systemTableTwoBean);
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return deleteCount;
	}

	/**
	 * This method update the component details from database
	 *
	 * @param systemTableTwoBean
	 *            , object of bean class SystemTableTwoBean
	 */
	public int updateComponent(SystemTableTwoBean systemTableTwoBean) {
		int i = 0;
		try {
			i = getSqlMapClientTemplate().update(
					"systemTableTwo.updateComponent", systemTableTwoBean);
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return i;
	}

		/**
		 * The method builds system values table for the university
		 * @param systemValueBean
		 * @return success once build is completed
		 */
		public String buildSystemValues(SystemValue systemValueBean,
				StringTokenizer codesInput) {

			String flag = "";

			try {

				if(systemValueBean.getProcess().equalsIgnoreCase("two")){

					SystemValue value = new SystemValue();

					value = (SystemValue) getSqlMapClient().queryForObject("systemValue.getUniversity",
							systemValueBean.getUniveristyCode());

					if(value!=null){

						flag="failure";
					}



				}else if(systemValueBean.getProcess().equalsIgnoreCase("one")){

				while (codesInput.hasMoreTokens()) {

					systemValueBean.setCode(codesInput.nextToken());
					systemValueBean.setValue(codesInput.nextToken());

					try {

						getSqlMapClient().insert("systemValue.insertSystemValue",
								systemValueBean);

					} catch (Exception e) {
						continue;
					}
				}

				flag="success";

				}

			} catch (Exception e) {
				logObj.error(e.getMessage());
			}

			return flag;
	}

}
