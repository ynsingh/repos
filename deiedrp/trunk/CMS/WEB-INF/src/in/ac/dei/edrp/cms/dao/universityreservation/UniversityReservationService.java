/**
 * @(#) UniversityReservationService.java
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
package in.ac.dei.edrp.cms.dao.universityreservation;

import in.ac.dei.edrp.cms.domain.universityreservation.UniversityReservation;

import java.util.List;


/**
 * The client interface for University Reservation.
 *
 * @version 1.0 19 JAN 2011
 * @author MOHD AMIR
 */
public interface UniversityReservationService {
	/** adding reservation details to database */
	public void addReservationDetails(UniversityReservation universityReservation);
	
	/** deleting  reservation details from database */
	public int deleteReservationDetails(UniversityReservation universityReservation);
	
	/** Updating reservation details from database */
	public void updateReservationDetails(UniversityReservation universityReservation);
	
	/** getting List of Category from Database */
	public List<UniversityReservation> getAllCategories(String universityId);
	
	/** Getting Reservation Details from database */
	public List<UniversityReservation> getAllReservationDetails(String universityId);
	
	/** Checking for duplicate record in database */
	public Boolean checkForDuplicateCategory(UniversityReservation universityReservation); 
}
