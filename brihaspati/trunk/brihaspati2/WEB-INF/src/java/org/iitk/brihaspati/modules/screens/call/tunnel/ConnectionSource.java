package org.iitk.brihaspati.modules.screens.call.tunnel;
/*
 * @(#)ConnectionSource.java
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


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectionSource
{ 

    private static ConnectionSource instance;
    private String driver;
    private String url;
    private String userid;
    private String password;
    private String lookupName;

    private ConnectionSource(String s, String s1, String s2, String s3)
    { 
        driver = s;
        url = s1;
        userid = s2;
        password = s3;
        lookupName = "";
    }

    private ConnectionSource(String s, String s1)
    {
        this(s, s1, "", "");
    }
    
	private ConnectionSource(String lookupName) {
		this.lookupName = lookupName; 
	}    

    private Connection newConnection()
        throws DbException
    {
        Connection connection = null;
        try
        {
			if ( lookupName.equals("") ) {
            	Class.forName(driver).newInstance();
            	if ( (userid.equals("")) && (password.equals("")) )
            		connection = DriverManager.getConnection(url);
            	else
            		connection = DriverManager.getConnection(url, userid, password);
			} else {
				
				InitialContext initCtx = new InitialContext();
				Context envCtx = (Context) initCtx.lookup("java:comp/env");
				DataSource datasource = (DataSource) envCtx.lookup(lookupName);
				connection = datasource.getConnection();				
			}
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            throw new DbException("ConnectionSource - ClassNotFoundException : " + classnotfoundexception.getMessage());
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            throw new DbException("ConnectionSource - IllegalAccessException:  - " + illegalaccessexception.getMessage());
        }
        catch(InstantiationException instantiationexception)
        {
            throw new DbException("ConnectionSource - InstantiationException:  - " + instantiationexception.getMessage());
		} 
		catch(javax.naming.NamingException nex) 
		{
			throw new DbException ("ConnectionSource - NamingException :" + lookupName + " - " + nex.getMessage());
		}
        catch(SQLException sqlexception)
        {
            throw new DbException("ConnectionSource -SQLException :  - " + sqlexception.getMessage());
        }
        return connection;
    }

    public static synchronized ConnectionSource getInstance(String s, String s1, String s2, String s3)
    {
       	if ( instance == null )
	    		instance = new ConnectionSource(s, s1, s2, s3);  
        return instance;
    }
    
    public static synchronized ConnectionSource getNewInstance(String s, String s1, String s2, String s3)
    {
        return new ConnectionSource(s, s1, s2, s3);  
    }    
    
    public static synchronized ConnectionSource getInstance(String lookupName)
    {
       	if ( instance == null )
	    		instance = new ConnectionSource(lookupName);  
        return instance;
    }    

    public synchronized Connection getConnection()
        throws DbException
    {
        return newConnection();
    }

    public synchronized static void close(Connection connection)
    {
        try
        {
            connection.close();
            //Log.print("Connection close :: " + connection.toString());
        }
        catch(SQLException sqlexception)
        {
            //Log.print(sqlexception.getMessage());
        }
    }
}
