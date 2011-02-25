/*
 * @(#) SelectTestId.java
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


package in.ac.dei.mhrd.omr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import in.ac.dei.mhrd.omr.dbConnection.Connect;
import in.ac.dei.mhrd.omr.testSetUp.UpdateTestSetupAction;

import org.apache.log4j.Logger;

/**
 * This class retrieves the testid from the database according to the given testname
 * 
 * Creation date: 10-27-2010
 * @author Anshul Agarwal
 *  
 * 
 */
public class SelectTestId {
	
	/**
	 * This method accepts the testname and returns its corresponding system generated testid from the testheader table 
	 * @param TestName
	 * @return
	 */
	private static Logger log = Logger.getLogger(UpdateTestSetupAction.class);

	public static int getTestId(String TestName){
		int testid=0;
		Connection con=null;
		 try {
			 //establish database connection
              con = Connect.prepareConnection();
             ResultSet rsTestid=null;          
             PreparedStatement psTestid = con.prepareStatement(
                     "select TestId from testheader where Test_name=?");
             psTestid.setString(1, TestName);
            rsTestid= psTestid.executeQuery();
            rsTestid.next();
            //store the testid retrieved from the database
            testid=rsTestid.getInt(1);
            if(testid!=0){
            	log.info("Test Id successfully retrieved");
            }
            }
             catch(Exception e){
                log.error("Error while retrieving testid");             
            }
             finally{
            	 //free database connection
             	Connect.freeConnection(con);
             
             }
             //return the testid retrieved from the database
		return testid;

	}
	
}
