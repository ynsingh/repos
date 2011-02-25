/*
 * @(#) CorrectAns.java
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

import ij.*;
import in.ac.dei.mhrd.omr.dbConnection.Connect;
import in.ac.dei.mhrd.omr.processSheet.ProcessSheetAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * This class defines the method that inserts correct answers into database
 *
 * Creation date:09-29-2010
 * @author Anshul Agarwal
 * @version 1.0
 */
public class CorrectAns {
	
	private static Logger log = Logger.getLogger(CorrectAns.class);

    /**
     * Inserts correct answers into the database
     * @param correctAns : array contains correct answers
     * @param sec " section number
     */
	
    public static int insertCorrectAns(byte[] correctAns, int testid) {
        int j=0;
        int sec=0;
        int qno=0;
        int totalSec=0;
        int totalQues=0;
        int ques=0;
        int[] noOfCorrectAns=null;
        Connection con=null;
    	try {
            //establish connection with the database
            con = Connect.prepareConnection();

            con.setAutoCommit(false);
            System.out.println("inside correct");
                      PreparedStatement ps = con.prepareStatement(
            "SELECT Section_number, No_of_question from testsectiondetail where TestId = ?");
            ps.setInt(1, testid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            	sec = rs.getInt(1);
            	ques=rs.getInt(2);
            
            
            	//System.out.println("corerct ans : Q "+i+ " " + correctAns[i]);
            	ps = con.prepareStatement(
                "insert into correctans(TestId, Ques_no, Answer, SectionNumber) values (?,?,?,?)");
            	for (int i = 1; i <= ques; i++) {
                	qno++;
        ps.setInt(1, testid);
        ps.setInt(2, qno);
        ps.setInt(3, correctAns[qno]);
        ps.setInt(4, sec);                  
        ps.addBatch();
             
        }//end for
            	noOfCorrectAns= ps.executeBatch();
                log.info("total Rows inserted in correct : "+ noOfCorrectAns.length);
           }    
            
           con.commit();
      
        log.info("rows inserted : "+ noOfCorrectAns.length);
        } catch (Exception e) {
            log.error("error while insert in correct Ans: " + e);
        }
        finally{
        	Connect.freeConnection(con);
        	}
        
        return noOfCorrectAns.length;
    }
}
