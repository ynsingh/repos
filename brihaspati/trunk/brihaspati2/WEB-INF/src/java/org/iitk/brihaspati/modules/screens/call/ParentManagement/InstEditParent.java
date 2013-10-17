package org.iitk.brihaspati.modules.screens.call.ParentManagement;

/*
 * @(#)InstEditParent.java      
 *
 *  Copyright (c) 2013 ETRG,IIT Kanpur. 
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

/**
 *  @author: <a href="mailto:rpriyanka12@ymail.com">Priyanka Rawat</a> 
 *  @creation date: 22-04-2013,31-05-2013
 */
import java.util.List;
import java.util.Vector;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.iitk.brihaspati.modules.screens.call.SecureScreen_Institute_Admin;
import org.iitk.brihaspati.modules.utils.CourseManagement;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.ParentManagementUtil;

 /**
 * @author <a href="mailto:rpriyanka12@ymail.com">Priyanka Rawat</a> 
 * @createdate 14-10-2013
 */
public class InstEditParent extends SecureScreen_Institute_Admin{
        /**
         * Display all details of the parent
         * @param data RunData
         * @param context Context
         */
        public void doBuildTemplate( RunData data, Context context )
        {
                try{
                        /**
                         * Get the parent id from the parameter parser
                         * and retreive the all details of the parent 
                         * @see ParentManagementUtil from Utils
                         */
                        String LangFile=null;
                        LangFile=(String)data.getUser().getTemp("LangFile");
			//get parent_id from list of parents page
                        String parentId = data.getParameters().getString("parentId");
                        String studentId = data.getParameters().getString("studentId");
			//ErrorDumpUtil.ErrorLog("inside scrrens:InstEditParent "+parentId+" "+studentId);
                        String instituteId=(data.getUser().getTemp("Institute_id")).toString();
			//get existing parent details from db 
                        Vector pDetail=ParentManagementUtil.getListOfAllParents(parentId,studentId);
			//place the details on the edit page
                        context.put("ParentDetail",pDetail);
                        String counter = data.getParameters().getString("count","");
                        context.put("tdcolor",counter);
                }
                catch(Exception e)
                {
                        data.setMessage("Error in class: InstEditParent"+e);
                }
        }//method
}//class

