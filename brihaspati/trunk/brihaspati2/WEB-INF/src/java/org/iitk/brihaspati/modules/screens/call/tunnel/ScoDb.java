package org.iitk.brihaspati.modules.screens.call.tunnel;
/*
 * @(#)ScoDb.java
 *
 *  Copyright (c) 2011-12 ETRG,IIT Kanpur.
 *  All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or
 *  without modification, are permitted provided that the following
 *  conditions are met:
 *
 *  Redistributions of source code must retain the above copyright
 *  notice, this  list of conditions and the following disclaimer.
 *
 *  Redistribution in binary form must reproducuce the above copyright
 *  notice, this list of conditions and the following disclaimer in
 *  the documentation and/or other materials provided with the
 *  distribution.
 *
 *
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *  Contributors: Members of ETRG, I.I.T. Kanpur
 *
 */

/**This class contain the code for  Set activity
*@author: <a href="mailto:seema_020504@yahoo.com">Seemapal</a>
*@author: <a href="mailto:kshuklak@rediffmail.com">Kishore Kumar shukla</a>
*/


import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Hashtable;


public class ScoDb implements ScoData {
	
	public boolean update(String userid, String courseid, Hashtable data) throws Exception {
		boolean result = true;
		if ( userid == null || "".equals(userid) ) return false;
		String scoId = (String) data.get("scoId");
		if ( scoId != null || !scoId.equals("null") ) {
			
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				Enumeration keys = data.keys();
				while ( keys.hasMoreElements() ) {
					String key = (String) keys.nextElement();
					if ( key.indexOf("cmi.") == 0 && course_items.indexOf(key) < 0 ) {
						String value = (String) data.get(key);
						r.clear();
						
						r.add("member_id");
						r.add("member_id", userid);
						r.add("course_id", courseid);
						r.add("sco_id", scoId);
						r.add("cmi_name", key);
						
						boolean isExist = false;
						{
							sql = r.getSQLSelect("Learner_sco");
							ResultSet rs = stmt.executeQuery(sql);
							if ( rs.next() ) isExist = true;
						}
						if ( isExist ) {
							r.clear();
							r.update("member_id", userid);
							r.update("course_id", courseid);
							r.update("sco_id", scoId);
							r.update("cmi_name", key);
							r.add(( "cmi.core._children".equals(key) || "cmi.comments".equals(key) ) ? 
							        "cmi_value_text" : "cmi_value", value); 
							sql = r.getSQLUpdate("Learner_sco");
							stmt.executeUpdate(sql);
						} 
						else {
							r.clear();
							r.add("member_id", userid);
							r.add("course_id", courseid);
							r.add("sco_id", scoId);
							r.add("cmi_name", key);
							r.add(( "cmi.core._children".equals(key) || "cmi.comments".equals(key) ) ? 
							        "cmi_value_text" : "cmi_value", value); 
							sql = r.getSQLInsert("Learner_sco");
							
							//System.out.println(sql);
							
							stmt.executeUpdate(sql);
						}
					}
				}
			} finally {
				if ( db != null ) db.close();
			}
		}
		return result;
	}	
	
	public boolean add(String userid, String courseid, Hashtable data) throws Exception {
		return update(userid, courseid, data);
	}
	
	public boolean delete(String userid, String courseid) throws Exception {
		boolean result = true;
		
		return result;	
	}
	
	/**
	Retrieve learner scos tracking
	*/
	public Hashtable get(String userid, String courseid, String scoId) throws Exception {
		Hashtable data = new Hashtable();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("cmi_name");
			r.add("cmi_value");
			r.add("cmi_value_text");
			r.add("member_id", userid);
			r.add("course_id", courseid);
			r.add("sco_id", scoId);
			sql = r.getSQLSelect("Learner_sco");
			//System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while ( rs.next() ) {
				String key = rs.getString("cmi_name");
				String value = ( "cmi.core._children".equals(key) || "cmi.comments".equals(key) ) ? 
				        rs.getString("cmi_value_text") : rs.getString("cmi_value") ;
				data.put(key, value);	
			}
		} finally {
			if ( db != null ) db.close();
		}
		// ServerLog.getController().Log("ConnectionProperties---last==================seema123>"+data);
		return data;
	}
	
	public Hashtable get(String userid, String courseid) throws Exception {
		Hashtable data = new Hashtable();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("sco_id");
			r.add("cmi_name");
			r.add("cmi_value");
			r.add("cmi_value_text");
			r.add("member_id", userid);
			r.add("course_id", courseid);
			sql = r.getSQLSelect("Learner_sco", "sco_id");
			//System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			String sco_id = "";
			Hashtable h = null;
			while ( rs.next() ) {
				String sco = rs.getString("sco_id");
				if ( !sco_id.equals(sco) ) {
					if ( h != null ) data.put(sco, h);
					sco_id = sco;
					h = new Hashtable();
					h.put("id", sco);
				}
				String key = rs.getString("cmi_name");
				String value = ( "cmi.core._children".equals(key) || "cmi.comments".equals(key) ) ? 
				        rs.getString("cmi_value_text") : rs.getString("cmi_value") ;
				h.put(key, value);	
			}
		} finally {
			if ( db != null ) db.close();
		}
		return data;
	}	
	

}
