/**
 * @(#) EodControlDAO.java
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
 * Redistribution in binary form must reproduce the above copyright
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

package in.ac.dei.edrp.cms.dao.eodcontrol;

import in.ac.dei.edrp.cms.domain.eodcontrol.EodControl;
import java.util.List;

/**
 * This interface consist the list of methods used
 * in ActivityMasterDAOImpl file.
 * @author Ankit Jain
 * @date 25 Jan 2011
 * @version 1.0
 */
public interface EodControlDAO {
	
	/**
     * Method to get the activityMaster details
     * @return List of activity master details
     */
	List<EodControl> getEodControlDetails(EodControl eodControl);
	
	/**
     * Method to get the activityMaster details
     * @return List of activity master details
     */
	List<EodControl> getStepFrequency(EodControl eodControl);
	
	/**
     * Method to get the activityMaster details
     * @return List of activity master details
     */
	List<EodControl> getMethodsToRun(EodControl eodControl);
	
	/**
     * Method to save the activityMaster details
     * @return void
     */
	String saveEodControlDetails(EodControl eodControl);
	
	/**
     * Method to save the activityMaster details
     * @return void
     */
	String updateEodControl(EodControl eodControl);
	
	/**
     * Method to delete the activityMaster details
     * @return void
     */
	String deleteEodControlDetails(EodControl eodControl, String eodControlDataTokens);
	
	/**
     * Method to delete the activityMaster details
     * @return void
     */
	String changeEodControlStatus(EodControl eodControl, String eodControlDataTokens);
	
	/**
     * Method to get the session start and end date.
     * @return List it will return the session start and end date. 
     */
	List<EodControl> getSessionDetails(EodControl activityMasterObject);
	
	/**
     * Method to get the entity.
     * @return List it will return the session start and end date. 
     */
	List<EodControl> getEntity(EodControl activityMasterObject);
	
	/**
     * This method will be used to build the EOD Master
     * @return List it will return the build status. 
     */
	String buildEODMaster(EodControl eodControl);
	
}
