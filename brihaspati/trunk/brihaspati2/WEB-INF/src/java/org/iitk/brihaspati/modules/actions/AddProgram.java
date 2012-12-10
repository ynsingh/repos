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
import java.util.Vector;
import java.util.StringTokenizer;
import org.apache.torque.util.Criteria;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.om.ProgramPeer;
import org.iitk.brihaspati.om.Program;
import org.iitk.brihaspati.om.InstituteProgramPeer;
import org.iitk.brihaspati.om.InstituteProgram;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
*@author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a> 
*@modified date:11-01-2011
*/
public class AddProgram extends SecureAction_Institute_Admin
{
 /** 
  * @param data RunData instance
  * @param context Context instance
  */
        private String LangFile=null;
	private Log log = LogFactory.getLog(this.getClass());

        public void doInsert(RunData data, Context context) 
        {
                try{
			/**
                          *Getting Language value from temporary variable
                          *getting parameters by using ParameterParser
                          */

			Criteria crit=new Criteria();
			String LangFile = data.getUser().getTemp("LangFile").toString();
			int InstId =Integer.parseInt((String)data.getUser().getTemp("Institute_id"));
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
				crit = new Criteria();
				crit.add(InstituteProgramPeer.PROGRAM_CODE,prgcode);
                                crit.add(InstituteProgramPeer.INSTITUTE_ID,InstId);
                                InstituteProgramPeer.doInsert(crit);
				String Add = MultilingualUtil.ConvertedString("brih_Added",LangFile);
				String Program = MultilingualUtil.ConvertedString("brih_program",LangFile);
				String success = MultilingualUtil.ConvertedString("brih_successfully",LangFile);
				data.setMessage(Add+" "+Program+" "+success);
				log.info("Program added successfully with name "+prgname+" By "+data.getUser().getName()+" | IP Address : "+data.getRemoteAddr());
				
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
         * This method performs the action for inserting the program for instutute
         * @param data RunData
         * @param context Context
         * Here ProgramList has ProgramCode from Selected CheckBoxes for Inserting.
         * Using StringTokenizer to break  string after "^".
         */
	
	public void doSelect(RunData data, Context context)
        {
                try{
			LangFile=(String)data.getUser().getTemp("LangFile");
			int InstId =Integer.parseInt((String)data.getUser().getTemp("Institute_id"));
                        String ProgramList=data.getParameters().getString("selectFileNames","");
                        context.put("selectFile",ProgramList);
			String programCode="";
			/**
	                 * Use StringTokenizer to break string after "^".
                         */
			StringTokenizer st=new StringTokenizer(ProgramList,"^");
                        Vector v=new Vector();

                        for(int i=0;st.hasMoreTokens();i++)
                        {
  		        	v.addElement(st.nextToken());
                        }
			/**
	                 * All the programcode obtained from the list 
                         * then insert into table one by one
                         */
			for(int i=0;i<v.size();i++)
                        {
	                        programCode=(v.elementAt(i).toString()).toUpperCase();
				Criteria crit=new Criteria();
				crit.add(InstituteProgramPeer.PROGRAM_CODE,programCode);
	                        crit.add(InstituteProgramPeer.INSTITUTE_ID,InstId);
	                        InstituteProgramPeer.doInsert(crit);					
				String Instmsg = MultilingualUtil.ConvertedString("brih_instprg",LangFile);
				String Slmsg= MultilingualUtil.ConvertedString("brih_successfully",LangFile);
				data.setMessage(Instmsg+" "+Slmsg);
				log.info("Program maped successfully with name "+ProgramList+" By "+data.getUser().getName()+" | IP Address : "+data.getRemoteAddr());
			}
			
		}
		catch(Exception ex)
		{
			ErrorDumpUtil.ErrorLog("Error in SelectProgram for institute action !!"+ex);
		}
	}
	
	/**
 	 * This method perform action for deleting program
 	 * @param data RunData
 	 * @param context Context
 	 */
	  	
	public void doDelete(RunData data, Context context)
        {
                try{
			LangFile=(String)data.getUser().getTemp("LangFile");
			ParameterParser pp = data.getParameters();
                        String prgcode = (pp.getString("pcode")).toUpperCase();
			int InstId =Integer.parseInt((String)data.getUser().getTemp("Institute_id"));
			int[] instId ={InstId};
			Criteria crit = new Criteria();
			crit.add(InstituteProgramPeer.PROGRAM_CODE,prgcode);
			crit.andNotIn(InstituteProgramPeer.INSTITUTE_ID,instId);
			List v=InstituteProgramPeer.doSelect(crit);
			if(v.size()!=0)
			{
				crit=new Criteria();
				crit.add(InstituteProgramPeer.PROGRAM_CODE,prgcode);
				crit.and(InstituteProgramPeer.INSTITUTE_ID,InstId);
				InstituteProgramPeer.doDelete(crit);
				String delmsg = MultilingualUtil.ConvertedString("brih_instdel",LangFile);
				data.setMessage(delmsg);
				setTemplate(data,"call,Program,ProgramList.vm");
				log.info("Program deleted successfully with code "+prgcode+" By "+data.getUser().getName()+" | IP Address : "+data.getRemoteAddr());
			}
			else
			{
				crit=new Criteria();
				crit.add(InstituteProgramPeer.PROGRAM_CODE,prgcode);
				crit.and(InstituteProgramPeer.INSTITUTE_ID,InstId);
				InstituteProgramPeer.doDelete(crit);
				crit= new Criteria();
				crit.add(ProgramPeer.PROGRAM_CODE,prgcode);
				ProgramPeer.doDelete(crit);		
				String Dlmsg = MultilingualUtil.ConvertedString("brih_instdel",LangFile);
				String msg = MultilingualUtil.ConvertedString("brih_instremove",LangFile);
				data.setMessage(Dlmsg+" "+msg);
				log.info("Program deleted successfully with code "+prgcode+" By "+data.getUser().getName()+" | IP Address : "+data.getRemoteAddr());
				setTemplate(data,"call,Program,ProgramList.vm");
			}
		}
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("Error in Deleting Program action !!"+e);
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
		else if(action.equals("eventSubmit_doSelect"))
                	doSelect(data,context);
		else if(action.equals("eventSubmit_doDelete"))
                	doDelete(data,context);
                else
                	data.setMessage("Cannot find the button");
				
       }
}
	
