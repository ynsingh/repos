package org.iitk.brihaspati.modules.actions;
/*
 * @(#)AcademicAction.java
 *
 *  Copyright (c) 2005-2008,2011,2013 ETRG,IIT Kanpur. 
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



import java.io.File;
import java.sql.Date;
import java.util.Vector;
import java.util.List;
import java.io.FileWriter;
import org.apache.turbine.util.RunData;
import org.apache.torque.util.Criteria;
import org.apache.turbine.om.security.User;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.CalendarUtil;
import org.iitk.brihaspati.om.InstituteAdminRegistrationPeer;
import org.iitk.brihaspati.om.InstituteAdminRegistration;
import org.apache.commons.lang.StringUtils;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/*
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
 * @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kumar Singh</a>
 * @author <a href="mailto:smita@iitk.ac.in">SmitaPal</a>
 */

public class AcademicAction extends SecureAction
{     
	/**
	 * This method insert the academic event and holidays  
	 * in the property file
	 * @param data RunData
	 * @param context Context
	 * @return nothing
	 */
	private Log log = LogFactory.getLog(this.getClass());

	/*public void doInsert(RunData data, Context context)
	{
		try
		{
			String LangFile=data.getUser().getTemp("LangFile").toString();  
			String msg="";
			ParameterParser pp=data.getParameters();
			User user=data.getUser();
			String instituteId=(data.getUser().getTemp("Institute_id")).toString();
			if(instituteId.equals("")){
				instituteId="Admin";
			}	
			String detail=pp.getString("detail");
			StringTokenizer st=new StringTokenizer(detail,";"); 
			Vector v=new Vector();
			for(int i=0;i<detail.length();i++){
				while(st.hasMoreTokens())
                                {
                                        detail=st.nextToken();
					v.addElement(detail);	
                                }
			}
			String month=pp.getString("Start_mon");
			int im=Integer.parseInt(month);		
			String year=pp.getString("Start_year");
			int iy=Integer.parseInt(year);		
			String hd=pp.getString("etype");
			String prpdate=instituteId+"."+month+"."+year;
			String path=data.getServletContext().getRealPath("/WEB-INF") +"/conf";
			String acdPath=path+"/"+"AcademicCalendar.properties";
			String hldPath=path+"/"+"CalendarHolidays.properties";
			String fpath="";	
			if(hd.equals("Academic"))
				fpath=acdPath;
			else
				fpath=hldPath;
			File file=new File(fpath);
			FileWriter fw=new FileWriter(fpath,true);
			boolean flag=false;
			BufferedReader br=new BufferedReader(new FileReader (fpath));
                        String str[]=new String[1000];
			int i=0;
                        while((str[i]=br.readLine())!=null)
                        {
				if (str[i].startsWith(prpdate)){
					flag=true;
					break;
				}
			}
			br.close();
			if(flag)
			{
                                msg=MultilingualUtil.ConvertedString("ac_msg1",LangFile);
                                data.setMessage(msg);
			}
			else
			{
				if(detail.contains("#")){
				String ph="";
                                fw.write("\r\n");
                                fw.write(prpdate);
                                fw.write("=");
				for(int j=0;j<v.size();j++)
				{
					ph=v.get(j).toString();
                                	fw.write(ph);
                                	fw.write(";");
				}
                                fw.close();
                                msg=MultilingualUtil.ConvertedString("cal_ins",LangFile);
                                data.setMessage(msg);
				}
				else
                                msg=MultilingualUtil.ConvertedString("ac_msg2",LangFile);
                                data.setMessage(msg);
				// Maintain Log
                                log.info("User Name --> Admin | Operation --> Add Event | IP Address --> "+data.getRemoteAddr());
			}
		}
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("The error in insert method !!"+e);	
			data.setMessage("See ExceptionLog !!");
		}
	}*/
	/*public void doGet(RunData data, Context context)
	{
		try
		{
			String instituteId=(data.getUser().getTemp("Institute_id")).toString();
			String LangFile=data.getUser().getTemp("LangFile").toString();
			String msg="";  
			ParameterParser pp=data.getParameters();
			String mode=pp.getString("mode");
			String month=pp.getString("Start_mon");
			String year=pp.getString("Start_year");
			String etype=pp.getString("etype");
			context.put("etype",etype);
			if(instituteId.equals("")){
				instituteId="Admin";
			}	
			String date=instituteId+"."+month+"."+year;
			context.put("keydate",date);
			String path=data.getServletContext().getRealPath("/WEB-INF") +"/conf";
			String acdPath=path+"/"+"AcademicCalendar.properties";
			String hldPath=path+"/"+"CalendarHolidays.properties";
			String fpath="";	
			if(etype.equals("Academic"))
				fpath=acdPath;
			else
				fpath=hldPath;
			File f=new File(fpath);
			if(f.exists()){ 
			BufferedReader br=new BufferedReader(new FileReader (fpath));
                        String str[]=new String[1000];
			int i=0;
			int start=0;
			int stop=0;
			String detail="";
                        while((str[i]=br.readLine())!=null)
                        {
				if (str[i].startsWith(date)){
					detail=str[i].toString();
				}
			}
			br.close();
			StringTokenizer st=new StringTokenizer(detail,"=");
                        for(int j=0;j<detail.length();j++){
                                while(st.hasMoreTokens())
                                {
                                        detail=st.nextToken();
                                }
                        }
                	context.put("element",detail);
			}//ifexist
			else{
				msg=MultilingualUtil.ConvertedString("ac_msg3",LangFile);
				data.setMessage(msg);
			}
			//return;
		}
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("The error in get method !!"+e);	
			data.setMessage("See ExceptionLog !!");
		}
	}*/	
	public void doUpdate(RunData data, Context context)
	{
		try
		{
			String LangFile=data.getUser().getTemp("LangFile").toString();  
			ParameterParser pp=data.getParameters();
			/*Get values from vm and contextput for maintain 
                         *the values after form reload.
                         */
			String type=pp.getString("type"," ");
			String vmonth=pp.getString("Start_mon"," ");
			context.put("vmonth",vmonth);
			String vyear=pp.getString("Start_year"," ");
			context.put("vyear",vyear);
			String etype=pp.getString("etype");
			context.put("etype",etype);
			String maxValue=pp.getString("maxValue"," ");
			context.put("maxValue",maxValue);
			String detail=pp.getString("detail");
			context.put("detail",detail);
			StringTokenizer st=new StringTokenizer(detail,";"); 
			
			Vector v=new Vector();
			/*Split the event value and get in a vector*/
			for(int i=0;i<detail.length();i++){
				while(st.hasMoreTokens())
                                {
                                        detail=st.nextToken();
					v.addElement(detail);
	
                                }
			}
			String date=pp.getString("date"," ");
			context.put("date",date);
			/*Get Path for AcademicCalendar.properties
                         *and CalendarHolidays.properties";
                         */
			String path=data.getServletContext().getRealPath("/WEB-INF") +"/conf";
			String acdPath=path+"/"+"AcademicCalendar.properties";
			String hldPath=path+"/"+"CalendarHolidays.properties";
			String fpath="";
			if(etype.equals("Academic")){
				fpath=acdPath;
			}
			else if(etype.equals("Holidays")){
				fpath=hldPath;
			}
			String msg="";
			FileWriter fw=new FileWriter(fpath,true);

			/*If details contains(#).
                         *events save in propertiesfile.
                         *in given formate.
                         *InstituteId.Month.Year=todayDate#event;NextDate#event.
			 *Else If details isNotBlank .
			 *show the error message to give event datails in proper formate.
                         *else delete the selected events and show the message.
                         */

			if(detail.contains("#")){ 
				CalendarUtil.DeleteLine(fpath,date);
				String ph="";
				fw.write(date);
                        	fw.write("=");
				for(int j=0;j<v.size();j++)
				{
					ph=v.get(j).toString();
                               		fw.write(ph);
                               		fw.write(";");
		
				}
				msg=MultilingualUtil.ConvertedString("brih_event",LangFile)+MultilingualUtil.ConvertedString("update_msg",LangFile);
				data.setMessage(msg);
                        fw.close();
			}
			else if((!detail.contains("#")) && (StringUtils.isNotBlank(detail)))
			{	
				msg=MultilingualUtil.ConvertedString("ac_msg2",LangFile);
                                data.setMessage(msg);

			}
                        else{
				CalendarUtil.DeleteLine(fpath,date);
				msg=MultilingualUtil.ConvertedString("cal_del",LangFile);
                          	data.setMessage(msg);
			}
				// Maintain Log
                                log.info("User Name --> Admin | Operation --> Update event | IP Address --> "+data.getRemoteAddr());
		

	}
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("The error in Update method !!"+e);	
			data.setMessage("See ExceptionLog !!");
		}
	}

	public void doSave(RunData data, Context context)

        {
		try{
			String LangFile=data.getUser().getTemp("LangFile").toString();
			String msg="";
		        ParameterParser pp=data.getParameters();
			/*Get values from vm and contextput for maintain 
			 *the values after form reload.
			 */
                        String type=pp.getString("type"," ");
                         String vmonth=pp.getString("Start_mon"," ");
                        context.put("vmonth",vmonth);
                        String vyear=pp.getString("Start_year"," ");
                        context.put("vyear",vyear);
                        String etype=pp.getString("etype");
                        context.put("etype",etype);
                        String detail=pp.getString("detail");
                        context.put("detail",detail);
                        StringTokenizer st=new StringTokenizer(detail,";");
			Vector v=new Vector();
			/*Split the event value and get in a vector*/
                        for(int i=0;i<detail.length();i++){
                                while(st.hasMoreTokens())
                               {
                                        detail=st.nextToken();
                                        v.addElement(detail);
                                }
                        }
			String date=pp.getString("date"," ");
			context.put("date",date);
			String maxValue=pp.getString("maxValue"," ");
                        context.put("maxValue",maxValue);

			/*Get Path for AcademicCalendar.properties
                         *and CalendarHolidays.properties";
                         */
			String path=data.getServletContext().getRealPath("/WEB-INF") +"/conf";
                        String acdPath=path+"/"+"AcademicCalendar.properties";
	                String hldPath=path+"/"+"CalendarHolidays.properties";
                        String fpath="";
			
			if(etype.equals("Academic"))
                                fpath=acdPath;
                        else
                                fpath=hldPath;
			File file=new File(fpath);
                        FileWriter fw=new FileWriter(fpath,true);
			/*If details contains(#)
			 *events save in propertiesfile
			 *in given formate
			 *InstituteId.Month.Year=todayDate#event;NextDate#event
			 *else shows an error message.
			 */
                        if(detail.contains("#"))
			{
	                	String ph="";
                                fw.write("\r\n");
                                fw.write(date);
                                fw.write("=");
                                for(int j=0;j<v.size();j++)
                                {
                                        ph=v.get(j).toString();
                                        fw.write(ph);
                                        fw.write(";");
                                }
                                fw.close();
                                msg=MultilingualUtil.ConvertedString("cal_ins",LangFile);
                                data.setMessage(msg);
                    }
                    else{
         	   	msg=MultilingualUtil.ConvertedString("ac_msg2",LangFile);
                        data.setMessage(msg);
		}
		//}
	}
	catch(Exception e){
			ErrorDumpUtil.ErrorLog("The error in save method !!"+e);
                        data.setMessage("See ExceptionLog !!");
		}
	}


	public void doPerform(RunData data,Context context) throws Exception
	{
		String LangFile=data.getUser().getTemp("LangFile").toString();  
		String msg=MultilingualUtil.ConvertedString("action_msg",LangFile);
		String action=data.getParameters().getString("actionName","");
		context.put("actionName",action);
		/*if(action.equals("eventSubmit_doInsert"))
		{
			doInsert(data,context);
		}
		else if(action.equals("eventSubmit_doGet"))
		{
			doGet(data,context);
		}*/
		if(action.equals("eventSubmit_doUpdate"))
		{
			doUpdate(data,context);
		}
		else if(action.equals("eventSubmit_doSave"))
		{
			doSave(data,context);
		}
	
		else
		{
			data.setMessage(msg);
		}
	}
}
