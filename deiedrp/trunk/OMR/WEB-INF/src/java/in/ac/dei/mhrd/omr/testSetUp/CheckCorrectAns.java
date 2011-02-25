/*
 * @(#) CheckCorrectAns.java
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


package in.ac.dei.mhrd.omr.testSetUp;

import java.sql.*;

import org.apache.log4j.Logger;

import in.ac.dei.mhrd.omr.dbConnection.Connect;
import in.ac.dei.mhrd.omr.uploadSheet.UploadResponseAction;

/**
 * This class defines the method that checks whether the correct answers has
 * been uploaded for the particular test or not Creation Date: 10-11-2010
 * Creation Date: 06-11-2010
 * @version 1.0
 * @author Ashutosh
 * 
 * 
 */
public class CheckCorrectAns {
	private int testId;
	String result = "notExist";
	private static Logger log = Logger.getLogger(CheckCorrectAns.class);

	public CheckCorrectAns(int testId) {
		this.testId = testId;
	}
/**
 * This method checks whether the correct answers 
 * has been uploaded or not for the selected test
 * @return
 */
	public String isCorrectAnsUploaded() {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rsCheckCorrect = null;
		String qry="";
		try {
			con = Connect.prepareConnection();
			qry = "select distinct TestId from correctans where TestId=?";
			ps = con.prepareStatement(qry);
			ps.setInt(1, testId);
			rsCheckCorrect = ps.executeQuery();
			
			while(rsCheckCorrect.next()) {
				result = "Exist";
			} 
		} catch (Exception e) {
			log.error("checkCorrectAns Exception: " + e.getMessage());
		} finally {
			Connect.freeConnection(con);
		}
		return result;
	}
}
