/**
 * @(#) UniversityReservationServiceImpl.java
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
package in.ac.dei.edrp.cms.daoimpl.universityreservation;

import in.ac.dei.edrp.cms.dao.universityreservation.UniversityReservationService;
import in.ac.dei.edrp.cms.domain.universityreservation.UniversityReservation;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * this is Server side Implementation class for University Reservation
 *
 * @version 1.0 18 JAN 2011
 * @author MOHD AMIR
 */
public class UniversityReservationServiceImpl extends SqlMapClientDaoSupport implements UniversityReservationService {

	/** Creating object of Logger for log Maintenance */
	private static Logger logObj = Logger.getLogger(UniversityReservationServiceImpl.class);

   /**
    * This method insert the reservation details into database
    *
    * @param universityReservation, object of bean class UniversityReservation
    */
    public void addReservationDetails(UniversityReservation universityReservation){
		
		try {
			getSqlMapClientTemplate().insert("reservation.addReservationDetails",universityReservation);
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
	}

   /**
    * This method retrieves list of categories with thier code
    *
    * @param universityId, Id of the University
    * @return categoryList containing category details
    */
    @SuppressWarnings("unchecked")
    public List<UniversityReservation> getAllCategories(String universityId) {
		try {
			
			List<UniversityReservation> categoryList=getSqlMapClientTemplate().queryForList("reservation.getCategories",universityId);
			return categoryList;
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return null;
	}

   /**
    * This method retrieves all reservation details from database
    *
    * @param universityId, Id of the University
    * @return reservationDetailsList containing reservation details
    */
    @SuppressWarnings("unchecked")
    public List<UniversityReservation> getAllReservationDetails(String universityId) {
		try {
			List<UniversityReservation> reservationDetailsList=getSqlMapClientTemplate().queryForList("reservation.getReservationDetails",universityId);
			return reservationDetailsList;
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return null;
	}

   /**
    * This method check for duplicate record for same category in database 
    *
    * @param universityReservation, object of bean class UniversityReservation
    * @return countList,Number of record found for given key values
    */
    @SuppressWarnings("unchecked")
    public Boolean checkForDuplicateCategory(UniversityReservation universityReservation) {
		try {
			List<UniversityReservation> countList=getSqlMapClientTemplate().queryForList("reservation.getCategoryCount",universityReservation);
			int count=countList.get(0).getCount();
			if(count==0)
			{
				return false;
			}
			else
			{
				return true;
			}
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return true;
	}

   /**
    * This method update the reservation details from database
    *
    * @param universityReservation, object of bean class UniversityReservation
    */
    public void updateReservationDetails(UniversityReservation universityReservation) {
		try {
			getSqlMapClientTemplate().update("reservation.updateReservationDetails",universityReservation);
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
	}

   /**
    * This method delete the reservation details from database
    *
    * @param universityReservation, object of bean class UniversityReservation
    * @return deleteCount,Number of record deleted
    */
    public int deleteReservationDetails(UniversityReservation universityReservation) {
		try {
			int deleteCount=getSqlMapClientTemplate().delete("reservation.deleteReservationDetails",universityReservation);
		    return deleteCount;
		} catch (Exception e) {
			logObj.error(e.getMessage());
		}
		return 0;
	}
}