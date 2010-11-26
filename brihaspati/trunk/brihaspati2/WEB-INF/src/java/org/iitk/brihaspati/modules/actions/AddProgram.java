package org.iitk.brihaspati.modules.actions;
/*
 *@(#) AddProgram.java    
 *
 *Copyright (c) 2010 ETRG,IIT Kanpur. 
 *All Rights Reserved.
 *
 *Redistribution and use in source and binary forms, with or 
 *without modification, are permitted provided that the following 
 *conditions are met:
 * 
 *Redistributions of source code must retain the above copyright  
 *notice, this  list of conditions and the following disclaimer.
 * 
 *Redistribution in binary form must reproducuce the above copyright 
 *notice, this list of conditions and the following disclaimer in 
 *the documentation and/or other materials provided with the 
 *distribution.
 * 
 * 
 *THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 *FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
 *CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
 *BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 *OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 *EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
import java.util.List;
import org.apache.torque.util.Criteria;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.om.ProgramPeer;
import org.iitk.brihaspati.om.Program;
/**
*@author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a> 
*/
public class AddProgram extends SecureAction_Institute_Admin
{
 /** 
  * @param data RunData instance
  * @param context Context instance
  */
        private String LangFile=null;

        public void doInsert(RunData data, Context context) 
        {
                try{
			/**
                          *Getting Language value from temporary variable
                          *getting parameters by using ParameterParser
                          */

			Criteria crit=new Criteria();
			String LangFile = data.getUser().getTemp("LangFile").toString();
			ParameterParser pp = data.getParameters();
			String prgcode = (pp.getString("pcode")).toUpperCase();
			String prgname = pp.getString("pname");
			String descrpt= pp.getString("descp","");
			String alsprgcode = (pp.getString("alspcode","")).toUpperCase();
			String alsprgname = pp.getString("alspname","");
			String status = pp.getString("status","");
			/**
 			 * First check status
 			 * if status is not update then insert values in database 	
 			 */ 			
			if(!status.equals("update"))
			{
				crit.add(ProgramPeer.PROGRAM_CODE,prgcode);
				crit.add(ProgramPeer.PROGRAM_NAME,prgname);
				crit.add(ProgramPeer.DESCRP,descrpt);
				crit.add(ProgramPeer.ALIAS_PCODE,alsprgcode);
				crit.add(ProgramPeer.ALIAS_PNAME,alsprgname);
				ProgramPeer.doInsert(crit);	
				String Add = MultilingualUtil.ConvertedString("brih_Added",LangFile);
				String Program = MultilingualUtil.ConvertedString("brih_program",LangFile);
				String success = MultilingualUtil.ConvertedString("QueBankUtil_msg5",LangFile);
				data.setMessage(Add+" "+Program+" "+success);
				
			}
			/**
 			 * If status is update then update values of table 	
 			 */ 
			else
			{
				crit=new Criteria();
				crit.add(ProgramPeer.PROGRAM_CODE,prgcode);
				List v=ProgramPeer.doSelect(crit);
				Program element = (Program)v.get(0);
                                int id = element.getId();
				crit=new Criteria();
				crit.add(ProgramPeer.ID,id);
				crit.add(ProgramPeer.PROGRAM_CODE,prgcode);
                                crit.add(ProgramPeer.PROGRAM_NAME,prgname);
                                crit.add(ProgramPeer.DESCRP,descrpt);
                                crit.add(ProgramPeer.ALIAS_PCODE,alsprgcode);
                                crit.add(ProgramPeer.ALIAS_PNAME,alsprgname);
                                ProgramPeer.doUpdate(crit);
				String msg = MultilingualUtil.ConvertedString("c_msg5",LangFile);
				data.setMessage(msg);
			}
					
		}
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("Error in addProgram action !!"+e);
		}
	}
	/**
         * Default action to perform if the specified action
         * cannot be executed.
         */

	public void doPerform(RunData data,Context context) throws Exception
	{
        	String action=data.getParameters().getString("actionName","");
		if(action.equals("eventSubmit_doSubmit"))
                	doInsert(data,context);
                else
                	data.setMessage("Cannot find the button");
				
       }
}
	
