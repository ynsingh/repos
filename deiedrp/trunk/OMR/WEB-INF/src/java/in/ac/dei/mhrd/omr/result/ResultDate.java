/*
 * @(#) ResultDate.java
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


package in.ac.dei.mhrd.omr.result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

import in.ac.dei.mhrd.omr.dbConnection.Connect;
import in.ac.dei.mhrd.omr.img.*;

/**
 * This class is accessed by the ManageResult jsp via AJAX to determine the time period
 * for which result of the selected test can be viewed
 * Creation date: 12-21-2010
 * @author Anshul Agarwal 
 * @version 1.0
 */
public class ResultDate {
	
	private static Logger log = Logger.getLogger(ResultDate.class);
	
	/**
	 * This method accepts the testname and then retrieves the Time period for which result can be viewed
	 * @param testname
	 * @return
	 */
	
	public String[] getResultDate(String testname) {
		
		System.out.println("ca;lling res");
		int i=0; //index of the array
		String[] resultDate=new String[4];
	    Connection con = null;
	try {
		
        con = Connect.prepareConnection();
        ResultSet rs=null;
       
        /*
         * This section of code retrieves the date at which the processing of test completes and the timeperiod for which results can be viewed
         */
        PreparedStatement ps = con.prepareStatement(
        "select date(ProcessEndDate), date(ResultDisplayedFrom), date(ResultDisplayedTo) from testheader where Test_name=?");
        ps.setString(1, testname);
        rs = ps.executeQuery();
        rs.next();
        System.out.println("time stamp : " + rs.getTimestamp(1));
        System.out.println("count inside name list: " + rs.getString(2));

        resultDate[0]=rs.getTimestamp(1).toString(); //ProcessEndDate 
        resultDate[1]=rs.getString(2);              //ResultDisplayedFrom  
        resultDate[2]=rs.getString(3);             // ResultDisplayedTo
       
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		
       
		
		/*
		 * Result can be displayed maximum for one month after processing of the test
		 */
        ps = con.prepareStatement(
        "select date(Date_Add(?, interval 1 Month));");   // return the last date of displaying result
        ps.setTimestamp(1, rs.getTimestamp(1));
        ResultSet rsDate = ps.executeQuery();
        rsDate.next();
            resultDate[3]=rsDate.getString(1); // last date of displaying result
        }
        catch(Exception e){
        	log.error("error in try of result date " + e);
        }
         finally{
        	 Connect.freeConnection(con);
         }
      
        return resultDate;
    }


}
