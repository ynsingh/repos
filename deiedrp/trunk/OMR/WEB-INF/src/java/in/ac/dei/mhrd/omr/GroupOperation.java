/*
 * @(#) GroupOperation.java
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
* this class is for retrieving and deleting group details from the database.
*
*
@version 1.0 21-08-2011
*
@author UPASANA KULSHRESTHA
*/


public class GroupOperation {
	private Locale obj = new Locale("en", "US");
	private ResourceBundle message = ResourceBundle.getBundle("in//ac//dei//mhrd//omr//ApplicationResources", obj);

	/* Creating object for writing logs*/
	private static Logger log = Logger.getLogger(GroupOperation.class);
	SelectTestId selectTestId = new SelectTestId();


	/**
	* This method insert the group code , group name and test id into the database
	*
	*
	@param testName
	@param groupCode
	@param groupName
	@return boolean value.
	*/
	
	public boolean insertGroup(String testName, String groupCode,
			String groupName) {
		Boolean bool = false;
		Connection con = null;
		try {
			// establish database connection
			con = Connect.prepareConnection();
			
			con.setAutoCommit(false);

			
			if (!(groupCode.equalsIgnoreCase("") || groupName
					.equalsIgnoreCase(""))) {

				PreparedStatement ps = con.prepareStatement("insert into group_table (TestId,group_code,group_name) value (?,?,?)");
				ps.setString(1, selectTestId.getTestId(testName) + "");
				ps.setString(2, groupCode);
				ps.setString(3, groupName);

				int q = ps.executeUpdate();
				log.info("insert in group_table: " + q);
				
				if(q>0){
					
					PreparedStatement ps1=con.prepareStatement("update testheader set group_exists=? where TestId=?");
						
					ps1.setString(1, "Y");
					ps1.setInt(2, selectTestId.getTestId(testName));
					ps1.executeUpdate();
			
				}

				bool = true;
				con.commit();
			}
		} catch (Exception e) {
			log.error("Error in retrieving TestName for upload response sheet interface "+ e);
		} finally {
			Connect.freeConnection(con);

		}

		return bool;
	}

	/**
	* This method retrieves Group List from database
	*
	*
	@param testName
	* 
	*
	@return groupNameList containing Group code and Group Name list
	*/
	
	public List<String> getGroupList(String testName) {
		Connection con = null;
		List<String> groupNameList = new ArrayList<String>();

		try {
			// establish database connection
			con = Connect.prepareConnection();
			ResultSet rs = null;
			con.setAutoCommit(false);

			PreparedStatement ps = null;
			
			ps = con.prepareStatement("SELECT group_code, group_name FROM group_table where TestId=? ORDER BY group_code DESC");
			ps.setString(1, selectTestId.getTestId(testName) + "");

			rs = ps.executeQuery();

			while (rs.next()) {
				// populate the arraylist with groupnames retrieved from the
				// database
				groupNameList.add(rs.getString(1));
				groupNameList.add(rs.getString(2));
				}
			
			con.commit();

		} catch (Exception e) {
			log.error("Error in retrieving Group Code and Group Name " + e);
		} finally {
			Connect.freeConnection(con);

		}
		return groupNameList;
	}

	/**
	* This method deletes the group detail from database
	*
	*
	@param testName
	@param groupCode
	*
	@return integer value containing information of deleted rows.
	*/

	public int deleteGroup(String testName, String groupCode) {
		int q = 0;
		Connection con = null;
		try {
			// establish database connection
			con = Connect.prepareConnection();
			
			con.setAutoCommit(false);

			StringTokenizer groups = new StringTokenizer(groupCode, "|");

			PreparedStatement ps = con
					.prepareStatement("delete from group_table where TestId=? and group_code=? ");
			ps.setString(1, selectTestId.getTestId(testName) + "");
			
			
			while (groups.hasMoreTokens()) {
				ps.setString(2, groups.nextToken());
				q = q + ps.executeUpdate();
			}
			
			PreparedStatement ps1 = con.prepareStatement("select count(*) from group_table where TestId=?  ");
				ps1.setString(1, selectTestId.getTestId(testName) + "");
			ResultSet rs=ps1.executeQuery();
			rs.next();
			
			
			if(rs.getInt(1)==0){
				PreparedStatement ps2=con.prepareStatement("update testheader set group_exists=? where TestId=?");
				
				ps2.setString(1, null);
				ps2.setInt(2, selectTestId.getTestId(testName));
				ps2.executeUpdate();
				
				
			}

			log.info("delete in group_table: " + q);

			con.commit();
		} catch (Exception e) {
			log.error("Error in deleting TestName and group code for group code setup interface. " + e);
		} finally {
			Connect.freeConnection(con);

		}

		return q;
	}
	
	
	
	/**
	* This method retrieves the group detail from database
	*
	*
	@param groupCode
	@param testName
	*
	@return integer value containing information of deleted rows.
	*/

	public String validateGroupCodeJs(String groupCode, String testName)
	{
	    Connection  con = null;
	    String groupExist="false";
	    try{
			/*
			 * Check if the test name entered by the user already exist 
			 */
			con = (Connection) Connect.prepareConnection();
	            
           PreparedStatement ps = con.prepareStatement(
                   "select group_code from group_table where group_code=? and TestId=? ");
           ps.setString(1, groupCode);
           ps.setString(2, selectTestId.getTestId(testName) + "");
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
           groupExist=rs.getString(1);
           }
           
	    }catch(Exception e){
	    	log.error("Error in validation of unique code.");
	    }finally{
	    	Connect.freeConnection(con);
	    }    
           
	  
	    return groupExist;
	  
	}
	
	/**
	* This method retrieves Group List from correctAnswers
	*@param testName
	* 
	*
	@return groupNameList containing Group code and Group Name list
	*/
	@SuppressWarnings("static-access")
	public List<String> getExistingGroupList(String testName) {
		Connection con = null;
		List<String> groupList = new ArrayList<String>();
		try {
			// establish database connection
			con = Connect.prepareConnection();
			ResultSet rs = null;
			con.setAutoCommit(false);
			PreparedStatement ps = null;
			ps = con.prepareStatement("SELECT DISTINCT ca.group_code, gt.group_name FROM correctans ca,group_table gt WHERE ca.TestId= ? AND ca.group_code=gt.group_code AND ca.TestId=gt.TestId ORDER BY group_code DESC");
			ps.setString(1, selectTestId.getTestId(testName) + "");
			rs = ps.executeQuery();
			while (rs.next()) {
				// populate the arraylist with groupnames retrieved from the
				// database
				groupList.add(rs.getString(1));
				groupList.add(rs.getString(2));
				}
			con.commit();
			
		} catch (Exception e) {
			log.error("Error in retrieving Group Code and Group Name " + e);
		} finally {
			Connect.freeConnection(con);
		}
		return groupList;
	}

}
