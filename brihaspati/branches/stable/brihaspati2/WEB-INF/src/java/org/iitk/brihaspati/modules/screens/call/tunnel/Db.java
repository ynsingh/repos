package org.iitk.brihaspati.modules.screens.call.tunnel;

/*
 * @(#)Db.java
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

/**This class contain the code for Database connection 
*@author: <a href="mailto:seema_020504@yahoo.com">Seemapal</a>
*@author: <a href="mailto:kshuklak@rediffmail.com">Kishore Kumar shukla</a>
*/                                         

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

public class Db
{

    protected Connection conn;
    protected Statement stmt;
    protected String sql;
    private String resource;
    
    /**
     * 
     * @throws DbException
     */
    
    public Db() throws DbException 
    {
		this("");   
    }    
    
    /**
     * A constructor to create a singleton ConnectonSource object.  This will
     * result a connection object with same value.
     * @param resource
     * @throws DbException
     */

    public Db(String resource) throws DbException
    {
        try
        {
            ConnectionProperties connectionproperties = ConnectionProperties.getInstance(resource);
            if(connectionproperties != null)
            {
                connectionproperties.read();
				if ( connectionproperties.isUseLookup() ) {
					String lookupName = connectionproperties.getLookup();
					conn = ConnectionSource.getInstance(lookupName).getConnection();
				}
				else
				{                
	                String s = connectionproperties.getDriver();
	                String s1 = connectionproperties.getUrl();
	                String s2 = connectionproperties.getUser();
	                String s3 = connectionproperties.getPassword();
	               ServerLog.getController().Log("Dbclass--->"+s+"s1==="+s1+"s2===="+s2+"s3====="+s3); 
	                conn = ConnectionSource.getInstance(s, s1, s2, s3).getConnection();
	               ServerLog.getController().Log("Dbclass--->"+conn); 
                }
               
                stmt = conn.createStatement();
	               ServerLog.getController().Log("Dbclass--->"+stmt); 
            } else
            {
                throw new DbException("Db : dbconnection.properties file error");
            }
        }
        catch(SQLException sqlexception)
        {
            throw new DbException("Db - SQLException : " + sqlexception.getMessage());
        }
    }
    
    /**
     * A constructor to create a new instance of ConnectionSource, thus shall
     * create a different connection values each time this constructor
     * is called.
     * The Hashtable argument consistes of driver, url, user, and password.
     * @param prop
     * @throws DbException
     */
    
    public Db(Hashtable prop) throws DbException
    {
        try
        {
	        if ( prop != null ) 
	        {
	            conn = ConnectionSource.getNewInstance((String) prop.get("driver"), 
					    (String) prop.get("url"), 
					    (String) prop.get("user"), 
					    (String) prop.get("password")).getConnection();
                stmt = conn.createStatement();
		ServerLog.getController().Log("Db(Hashtable prop)--->"+conn+"stmt===="+stmt);
            } else
            {
                throw new DbException("Db : properties table is null");
            }
        }
        catch(SQLException sqlexception)
        {
            throw new DbException("Db - SQLException : " + sqlexception.getMessage());
        }
    }    
    


    public Connection getConnection()
    {
        return conn;
    }

    public String getSQL()
    {
        return sql;
    }

    public Statement getStatement()
    {
        return stmt;
    }

    public void close()
    {
        if(conn != null)
        {
            try
            {
                stmt.close();
            }
            catch(SQLException sqlexception) { }
            ConnectionSource.close(conn);
        }
    }

    protected String dblch(String s, char c)
    {
        StringBuffer stringbuffer = new StringBuffer("");
        if(s != null)
        {
            for(int i = 0; i < s.length(); i++)
            {
                char c1 = s.charAt(i);
                if(c1 == c)
                    stringbuffer.append(c).append(c);
                else
                    stringbuffer.append(String.valueOf(c1));
            }

        } else
        {
            stringbuffer.append("");
        }
        return stringbuffer.toString();
    }

    protected String replace(String s)
    {
        return dblch(s, '\'');
    }
    
    public static String getString(ResultSet rs, String name) throws Exception {
		//String data = rs.getString(name);
		//if ( data != null ) return data;
		//else return "";
        try {
        	String data = rs.getString(name);
    		if ( data != null ) return data;
        } catch (SQLException e ) {
            System.out.println("Date column throw Exception: " + e.getMessage());
        }
        return "";		
    }
    
    public static java.util.Date getDate(ResultSet rs, String name) throws Exception {
        try {
            return rs.getDate(name);
        } catch (SQLException e ) {
            //System.out.println("Date column throw Exception: " + e.getMessage());
        }
        return null;
    }    
}
