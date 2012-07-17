/**
 * @(#) AssociateMOUdaoImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.associateMOU;

import in.ac.dei.edrp.cms.dao.associateMOU.AssociateMOUDAO;
import in.ac.dei.edrp.cms.domain.associateMOU.AssociateMOU;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * this is Server side implementation class for Associate MOU
 * 
 * @version 1.0 7 MAY 2011
 * @author MOHD AMIR
 */
public class AssociateMOUdaoimpl extends SqlMapClientDaoSupport implements
		AssociateMOUDAO {

	/** Creating object of Logger for log Maintenance */
	private Logger loggerObject = Logger.getLogger(AssociateMOUdaoimpl.class);

	/**
	 * This method retrieves list of university with their code
	 * 
	 * @param associateMOU
	 *            , object of the AssociateMOU
	 * @return univ, List of universities
	 */
	@SuppressWarnings("unchecked")
	public List<AssociateMOU> getUniversityList(AssociateMOU associateMOU) {

		List<AssociateMOU> univ = new ArrayList<AssociateMOU>();
		try {

			univ = getSqlMapClientTemplate().queryForList(
					"AssociateMOU.getUniversityList", associateMOU);
			loggerObject.info("in get university List");
		} catch (Exception e) {

			loggerObject.info("in get university List" + e);
		}
		return univ;
	}

	/**
	 * This method insert the Mou details from database
	 * 
	 * @param associateMOU
	 *            , Object of bean class AssociateMOU
	 * @return bool, true/false(record inserted or not)
	 */
	public boolean setMouDetails(AssociateMOU associateMOU) {
		try {

			getSqlMapClientTemplate().insert("AssociateMOU.setMouDetails",
					associateMOU);
			loggerObject.info("in writemouqry");

		} catch (Exception e) {

			loggerObject.error("in writemouqry" + e);
			return false;
		}
		return true;
	}

	/**
	 * This method retrieves mou university details
	 * 
	 * @param associateMOU
	 *            , object of the AssociateMOU
	 * @return mouList, List of type AssociateMOU
	 */
	@SuppressWarnings("unchecked")
	public List<AssociateMOU> getMouDetails(AssociateMOU associateMOU) {

		List<AssociateMOU> mouList = null;
		try {

			mouList = getSqlMapClientTemplate().queryForList(
					"AssociateMOU.getMouDetails", associateMOU);
		} catch (Exception e) {
			// loggerObject.error("in getProgramCourseHeaderList" + e);
			loggerObject.info("in getmouList" + e);
		}
		return mouList;
	}

	/**
	 * This method delete the Mou details from database
	 * 
	 * @param s1
	 *            , mou university ids
	 * @return bool, true/false(record deleted or not)
	 */
	public boolean deletMouDetails(String s1) {
		boolean bool = false;

		StringTokenizer s2 = new StringTokenizer(s1, ",");

		AssociateMOU amu = new AssociateMOU();

		try {
			while (s2.hasMoreTokens()) {

				amu.setUniversityId(s2.nextToken());
				amu.setMouId(s2.nextToken());

				getSqlMapClientTemplate().delete(
						"AssociateMOU.deletMouDetails", amu);

			}
			bool = true;
		}

		catch (Exception e) {
			loggerObject.info("in deletmou" + e);
		}

		return bool;
	}

}
