/*
 * @(#) Response.java
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

import java.io.File;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import com.mysql.jdbc.exceptions.*;
import org.apache.log4j.Logger;


/**
 * This class inserts the ques no, option, section into the database
 * @creation date:09-27-2010 
 * @author Anshul Agarwal
 * @version 1.0
 */
public class Response {
	
	/**
	 * This method accepts CandidateID alias RollNo and an array of responses into the database
	 * @param attemptAns
	 * @param TestNo
	 * @param str
	 * @param sec
	 */
	private static Logger log = Logger.getLogger(Response.class);

    public static void insert_response(byte[] attemptAns, int testid,
        int rno, int noOfQues, String filepath) {
    	Locale obj = new Locale("en", "US");
		ResourceBundle message = ResourceBundle.getBundle("in//ac//dei//mhrd//omr//ApplicationResources", obj);
		int[] responseCount=null;
		System.out.println("insert_response");
    	File fileName = new File(filepath);
    	Connection con=null;
    	int ques=0, sec=0;
    	int qno=0;
    	ArrayList<Integer> tempSec=new ArrayList<Integer>();
        try {
             con = Connect.prepareConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(
            "SELECT Section_number, No_of_question FROM testsectiondetail where TestId = ?");
            ps.setInt(1, testid);
            ResultSet rs = ps.executeQuery();
            
            /*
             * Check for duplicate Roll No.
             */
            ps = con.prepareStatement(
            "SELECT distinct FileName FROM response where TestId=? AND RollNo=?");
             ps.setInt(1, testid);
             ps.setInt(2, rno);
             ResultSet rsDuplicateRno = ps.executeQuery();
             while(rsDuplicateRno.next()){
            	 if(rno!=-1){
            	 LogEntry.insert_Log(testid, fileName.getName(), message.getString("code.E102"), message.getString("msg.E102")+"in file "+ fileName.getName());
             	log.error(message.getString("msg.E102"));
            	 }
             }
             ps = con.prepareStatement(
             "insert into response(TestId, RollNo, ques_no, ans, SectionNumber, FileName) values (?,?,?,?,?,?)");

            while(rs.next()){
            	sec = rs.getInt(1);
            	tempSec.add(sec);
            	ques=rs.getInt(2);
            	

                    for (int i = 1; i <= ques; i++) {
                    	
                    	                     	qno++;
                     	    if (attemptAns[qno] != 0) {
                    ps.setInt(1, testid);
                    ps.setInt(2, rno);
                    ps.setInt(3, qno);
                    ps.setString(4, "" + attemptAns[qno]);
                    ps.setInt(5, sec);
                    ps.setString(6, fileName.getName());
                    ps.addBatch();     

                }
            }//end for

            }
            responseCount = ps.executeBatch();
                       if(responseCount.length==attemptAns.length-1){
            	log.info("All responses inserted ");
            }
            
            /*PreparedStatement ps1  = con.prepareStatement(
                       "insert into student_result_info(TestId, RollNo, SectionNumber, FileName,group_code,section_marks,total_marks) values (?,?,?,?,?,?,?)");
            
            for(int l=0;l<tempSec.size();l++){
            	ps1.setInt(1, testid);
                ps1.setInt(2, rno);
                ps1.setInt(3, tempSec.get(l));
                ps1.setString(4, fileName.getName());
                ps1.setString(5, "00");
                ps1.setFloat(6, 0.0f);
                ps1.setFloat(7, 0.0f);
                
                ps1.executeUpdate();
            }*/
            
            
                       
            con.commit();
           // con.close();

        } catch(MySQLIntegrityConstraintViolationException e){
        	//insert into log in case or duplicate rollno or CandidateId
        	LogEntry.insert_Log(testid, fileName.getName(), message.getString("code.E105"), message.getString("msg.E105"));
        	log.error(message.getString("msg.E105"));
        }
        
        catch (Exception e) {
             log.error("Error while inserting responses");
        	LogEntry.insert_Log(testid, fileName.getName(),
            message.getString("code.E101"), message.getString("msg.E101")+e);
        }
        finally{
        	Connect.freeConnection(con);
        }
    }
}
