package org.iitk.brihaspati.modules.screens.call.tunnel;

/*
 * @(#)ConnectionProperties.java
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

/**This class contain the code for ConnectionProperties 
*@author: <a href="mailto:seema_020504@yahoo.com">Seemapal</a>
*@author: <a href="mailto:kshuklak@rediffmail.com">Kishore Kumar shukla</a>
*/

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ConnectionProperties
{

    private static ConnectionProperties instance;
    private ResourceBundle rb;
    private String driver;
    private String url;
    private String user;
    private String password;
    private String lookupName;
    private String dbserver;
    private boolean useLookup;

    private ConnectionProperties(String resource)
        throws MissingResourceException
    {
        rb = ResourceBundle.getBundle(resource);
        read();
	ServerLog.getController().Log("ConnectionProperties--->"+rb);
    }    
    

    public static synchronized ConnectionProperties getInstance(String resource)
    {
	    //if ( "".equals(resource) ) resource = "dbconnection";
	    if ( "".equals(resource) ) resource = "dbconnection";
        if(instance == null)
            try
            {
                instance = new ConnectionProperties(resource);
		ServerLog.getController().Log("ConnectionProperties--->getInstance==="+instance);
            }
            catch(MissingResourceException missingresourceexception)
            {
                instance = null;
            }
           
        
        return instance;
    }
    
    public static ConnectionProperties getInstance() {
			return getInstance("");
    }

    public String getLookup()
    {
        return lookupName;
    }

    public String getDriver()
    {
        return driver;
    }

    public String getUrl()
    {
        return url;
    }

    public String getUser()
    {
        return user;
    }

    public String getPassword()
    {
        return password;
    }

    public String getDbserver()
    {
        return dbserver;
    }

    public boolean isUseLookup()
    {
        return useLookup;
    }

    
	public void read() {
		useLookup = true;
		readLookup();
		if ( (lookupName == null) || (lookupName.equals("")) ) {
			useLookup = false;
			readDriver();
			readUrl();
			readUser();
			readPassword();
		}
	} 
	
	private void readLookup() {
		try {
			lookupName = rb.getString("lookupname");
			ServerLog.getController().Log("ConnectionProperties--->lookupName"+lookupName);
		} 
		catch ( MissingResourceException ex ) 
		{ 
			//Log.print(ex.getMessage()); 
		}
	}	   

    private void readDriver()
    {
        try
        {
            driver = rb.getString("driver");
		ServerLog.getController().Log("ConnectionProperties--->driver"+driver);
        }
        catch(MissingResourceException missingresourceexception)
        {
            //Log.print(missingresourceexception.getMessage());
        }
    }

    private void readUrl()
    {
        try
        {
            url = rb.getString("url");
        }
        catch(MissingResourceException missingresourceexception)
        {
            //Log.print(missingresourceexception.getMessage());
        }
    }

    private void readUser()
    { 
        try
        {
            user = rb.getString("user");
        }
        catch(MissingResourceException missingresourceexception)
        {
            //Log.print(missingresourceexception.getMessage());
        }
    }

    private void readPassword()
    {
        try
        {
            password = rb.getString("password");
        }
        catch(MissingResourceException missingresourceexception)
        {
            //Log.print(missingresourceexception.getMessage());
        }
    }
}
