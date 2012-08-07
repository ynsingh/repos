/*
 * @(#) GroupSection.java
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

import in.ac.dei.mhrd.omr.dbConnection.Connect;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

/**
 * This class defines the method that inserts the groupCodes for particular section 
 * into the database and deletes the groupCodes of particular section from database
 * Creation date: 18 Aug, 2011
 * @Author Dheeraj Singh
 * @version 1.0
 */

public class GroupSection {
	private Locale obj = new Locale("en", "US");
	private ResourceBundle message = ResourceBundle.getBundle("in//ac//dei//mhrd//omr//ApplicationResources", obj);

	/**
	* Creating object of logger class for writing logs in log file
	*/
	private static Logger log = Logger.getLogger(GroupSection.class);
	SelectTestId selectTestId = new SelectTestId();
	

	/**
	* This method retrieves the group codes from group_table
	* @param testName, 
	* @param sectionNo
	* @return list containing group codes
	*/
	public List<String> getGroups(String testName, String sectionNo) {
		String strSection = sectionNo;
		List<String> list=new ArrayList<String>();
		Connection con = null;
		try {
			// establish database connection
			con = Connect.prepareConnection();
			con.setAutoCommit(false);

			// count number of records
			
			if(strSection.equals("--Select--")){
				list.add("Select");
				return list;
			}else{
				PreparedStatement ps = con
						.prepareStatement("select group_code, group_name from group_table where TestId=? and group_code not in (Select group_code from testsectiongroups where TestId=? and section_no=?) order by group_code;");
				ps.setString(1, selectTestId.getTestId(testName) + "");
				ps.setString(2, selectTestId.getTestId(testName) + "");
				ps.setString(3, strSection);
				
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					list.add(rs.getString(1));
					list.add(rs.getString(2));
				}
				con.commit();
			}
				
		} catch (Exception e) {
			log.error("Error in retrieving data from group_table "+ e);
		} finally {
			Connect.freeConnection(con);

		}
		
		return list;
	}

	/**
	* This method deletes the group codes from testSectionGroups table
	* @param testName, 
	* @param sectionNo, 
	* @param groupCode
	* @return q containing count of deleted rows
	*/
	public int deleteGroup(String testName, String sectionNo, String groupCode) {
		int q = 0;
		Connection con = null;
		try {
			// establish database connection
			con = Connect.prepareConnection();
			con.setAutoCommit(false);

			StringTokenizer groups = new StringTokenizer(groupCode, "|");
			StringTokenizer sections = new StringTokenizer(sectionNo, "|");
			
			PreparedStatement ps = con
					.prepareStatement("delete from testsectiongroups where TestId=? and Group_Code=? and Section_No=? ");
			ps.setString(1, selectTestId.getTestId(testName) + "");
			
			while (groups.hasMoreTokens()) {
				ps.setString(2, groups.nextToken());
				ps.setString(3, sections.nextToken());
				q = q + ps.executeUpdate();
			}

			log.info("delete in group_table: " + q);

			con.commit();
		} catch (Exception e) {
			log.error("Error in deleting data from testsectiongroups table "+ e);
		} finally {
			Connect.freeConnection(con);

		}
		return q;
	}
	
	/**
	* This method insert the group codes in testSectionGroups table
	* @param testName, 
	* @param sectionNo, 
	* @param groupCode, 
	* @param groupName
	* @return q containing count of added rows
	*/
	public int addGroup(String testName, String sectionNo, String groupCode, String groupName) {
		int q = 0;
		Connection con = null;
		try {
			// establish database connection
			con = Connect.prepareConnection();
			con.setAutoCommit(false);

			StringTokenizer groupCodes = new StringTokenizer(groupCode, "|");
			StringTokenizer groupNames = new StringTokenizer(groupName, "|");

			PreparedStatement ps = con
					.prepareStatement("insert into testsectiongroups values (?,?,?,?)");
			ps.setString(1, selectTestId.getTestId(testName) + "");
			ps.setString(2, sectionNo);			
			while (groupCodes.hasMoreTokens()) {
				ps.setString(3, groupCodes.nextToken());
				ps.setString(4, groupNames.nextToken());
				q = q + ps.executeUpdate();
			}

			log.info("insert into testsectiongroups: " + q);

			con.commit();
		} catch (Exception e) {
			log.error("Error in inserting data into testsectiongroups table "+ e);
		} finally {
			Connect.freeConnection(con);

		}

		return q;
	}
	
	/**
	* This method retrieves the group codes from testSectionGroups table
	* @param testName
	* @param SectionNo
	* @return list containing list of group codes
	*/
	public List<String> getAddedGroups(String testName, String sectionNo) {
		List<String> list=new ArrayList<String>();
		Connection con = null;
		try {
			// establish database connection
			con = Connect.prepareConnection();
			con.setAutoCommit(false);


				PreparedStatement ps = con
						.prepareStatement("SELECT section_no, group_code, group_name FROM testsectiongroups WHERE TestId=? ORDER BY section_no,group_code");
				ps.setString(1, selectTestId.getTestId(testName) + "");
				
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					list.add(rs.getString(1));
					list.add(rs.getString(2));
					list.add(rs.getString(3));
				}
				con.commit();
				
		} catch (Exception e) {
			log.error("Error in retrieving data from testsectiongroups table "+ e);
		} finally {
			Connect.freeConnection(con);

		}
		
		return list;
	}

}
