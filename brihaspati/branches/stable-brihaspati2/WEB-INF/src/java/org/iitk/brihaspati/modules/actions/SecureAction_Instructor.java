package org.iitk.brihaspati.modules.actions;
/*
 *  Copyright (c) 2005 ETRG,IIT Kanpur. 
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
 *  Contributors : members of ETRG, IIT Kanpur  
 */

import org.apache.velocity.context.Context;
import org.apache.turbine.util.RunData;
import org.apache.turbine.modules.actions.VelocitySecureAction;
import org.apache.turbine.util.security.AccessControlList;
import org.apache.turbine.Turbine;
import org.apache.turbine.om.security.User;
/**
 * Velocity Secure action
 *
 * Always performs a Security Check that you've defined before
 * executing the doBuildtemplate().
 * @author <a href="mailto:awadhk_t@yahoo.com">Awadhesh Kumar Trivedi</a>
 */
public class SecureAction_Instructor extends VelocitySecureAction
{
    	/**
     	* Implement this to add information to the context.
     	*
     	* @param data Turbine information.
     	* @param context Context for web pages.
     	* @exception Exception, a generic exception.
     	*/
    	public void doPerform( RunData data,Context context )throws Exception
    	{
    	}

    /**
     * This currently only checks to make sure that user is allowed to
     * view the storage area.  If you create an action that requires more
     * security then override this method.
     *
     * @param data Turbine information.
     * @return True if the user is authorized to access the screen.
     * @exception Exception, a generic exception.
     */
 	protected boolean isAuthorized( RunData data )  throws Exception
        {
                boolean isAuthorized = false;
                try{
                        AccessControlList acl = data.getACL();
                        User user=data.getUser();
                        String g=user.getTemp("course_id").toString();

                /**
                 * Checks if the user has logged in as an instructor. If so, then he is
                 * authorized for this action
                 */

                        if (acl==null || (! acl.hasRole("turbine_root") && ! acl.hasRole("instructor",g)))
                        {
                            data.setScreenTemplate(Turbine.getConfiguration().getString("template.login"));
                            isAuthorized = false;
                        }
                        else if((acl.hasRole("instructor",g)) || (acl.hasRole("turbine_root")))
                        {
                            isAuthorized = true;
                        }
                }
                catch(Exception e)
                {
                                data.setScreenTemplate(Turbine.getConfiguration().getString("template.login"));
                                return false;
                }
                return isAuthorized;

    }
}
