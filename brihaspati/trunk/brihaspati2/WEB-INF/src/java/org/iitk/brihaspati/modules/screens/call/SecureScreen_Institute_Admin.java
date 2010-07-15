package org.iitk.brihaspati.modules.screens.call;


import java.util.List;

import org.apache.turbine.modules.screens.VelocitySecureScreen;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.security.*;
import org.apache.turbine.Turbine;
import org.apache.velocity.context.Context;



  /**
    * This class has code of authorized Screens for Institute Admin
    * @author <a href="mailto:sharad23nov@yahoo.com">Sharad Singh</a>
    */


public class SecureScreen_Institute_Admin extends VelocitySecureScreen
{
	public void doBuildTemplate(RunData data, Context context)
  	{
    	}

    	protected boolean isAuthorized( RunData data )  throws Exception
    	{
        	boolean isAuthorized = false;

        	AccessControlList acl = data.getACL();

		//check for user to assign a role in a group.

                if(acl == null || !acl.hasRole("institute_admin","institute_admin"))
                {

            		data.setScreenTemplate(Turbine.getConfiguration().getString("template.login"));

            		isAuthorized = false;
                }
                else if(acl.hasRole("institute_admin","institute_admin"))
                {
                        isAuthorized=true;
        	}
	return isAuthorized;
    	}
}

