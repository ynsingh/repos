/*
 * @(#) LogEntry.java
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


package in.ac.dei.mhrd.omr.img;

import in.ac.dei.mhrd.omr.dbConnection.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import org.apache.log4j.Logger;


/**
 * This class inserts the name of the sheet and cause of error into the database
 * @author Anshul Agarwal
 * @Creation date:09-27-2010
 * @version 1.0 
 */

public class LogEntry {
	private static Logger log = Logger.getLogger(LogEntry.class);


	/**
	 * This  method  inserts the information in the log table if some error is encountered
	 * while processing the sheet
	 * @param testid
	 * @param filename
	 * @param errCode
	 * @param description 
	 */
	public static void insert_Log(int testid, String filename, String errCode, String description ){
		System.out.println("insert_Log");
		Connection con = null;
		try{
			
			// establish connection with the database
			System.out.println("file name in log"+ filename);
			con = Connect.prepareConnection();
			con.setAutoCommit(false);
			 
	     PreparedStatement ps= con.prepareStatement("insert into `log`(TestId, FileName, ErrCode, description) values (?,?,?,?)");
	     
	     ps.setInt(1, testid);
	     ps.setString(2, filename);
	     ps.setString(3, errCode);
	     ps.setString(4, description); 
	    
	     ps.executeUpdate();
	     con.commit();
	  // close the connection
	//	con.close();
		
		}
		catch(Exception e){
			log.error("error while insert in log:" + e);
		}
           finally{
        	   Connect.freeConnection(con);
           }
	
		
		
	}
	
	
}
