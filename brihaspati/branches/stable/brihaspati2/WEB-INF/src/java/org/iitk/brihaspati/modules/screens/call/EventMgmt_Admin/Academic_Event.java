package org.iitk.brihaspati.modules.screens.call.EventMgmt_Admin;
/*
 * @(#)Academic_Event.java
 *
 *  Copyright (c) 2007,2009,2013 ETRG,IIT Kanpur.
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
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEiORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *  Contributors: Members of ETRG, I.I.T. Kanpur
 *
 */

import java.io.File;
import java.util.Vector;
import java.io.FileReader;
import java.io.BufferedReader;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.YearListUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.apache.commons.lang.StringUtils;
import java.util.StringTokenizer;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.CalendarUtil;
/**
 * This class contains code for writing
 * Academic events and holidays in properties file.
 * @author <a href="mailto:singh_jaivir@rediffmail.com ">Jaivir Singh</a>
 */
public class Academic_Event extends SecureScreen
{
	/**
	* @param data RunData
	* @param context Context
	* @return nothing
	*/		
  	public void doBuildTemplate(RunData data,Context context)
	{
		try
		{
		/**
		*Retrieves the current date of the System
		*@see ExpiryUtil in utils
		*/

			String LangFile=data.getUser().getTemp("LangFile").toString();
			String instituteId=(data.getUser().getTemp("Institute_id")).toString();
			ParameterParser pp=data.getParameters();
			String type=pp.getString("type"," ");	
			//String mode=pp.getString("mode"," ");
				
			context.put("type",type);
			Vector year_list=YearListUtil.getYearList();
                        context.put("year_list",year_list);
			String msg="";
			/*Get current month & year.*/
			String cdate=ExpiryUtil.getCurrentDate("");
                        String cyear=cdate.substring(0,4);
                        int cyear1=Integer.parseInt(cyear);
                        String cmonth=cdate.substring(4,6);
			/*Get selected month & year from template*/
			String vmonth=pp.getString("vmonth"," ");
			String vyear=pp.getString("vyear","");
                        //context.put("maxValue",maxValue);
			/*
			*First time selected month and year is null
			*put current month and year.
			*/
			if(StringUtils.isBlank(type)){
				if(StringUtils.isBlank(vmonth))
					vmonth=cmonth;
					context.put("vmonth",vmonth);
				if(StringUtils.isBlank(vyear))
					vyear=cyear;
					context.put("vyear",vyear);
			String maxValue1=pp.getString("maxValue"," ");
			int maxValue=0;
			if(StringUtils.isBlank(maxValue1))
			{
			 	 maxValue=CalendarUtil.correctEntry(vmonth,vyear);
			}
			else{
				 maxValue=Integer.parseInt(maxValue1);
			}
                        context.put("maxValue",maxValue);

				String etype=pp.getString("etype"," ");
			/*First time when no event type selected.*/
				if(StringUtils.isBlank(etype)){
					etype="Academic";
				}
				context.put("etype",etype);
			/*When event in sert by Main Admin*/
				if(instituteId.equals("")){
                                instituteId="Admin";
                        	}
				String date=instituteId+"."+vmonth+"."+vyear;
				context.put("date",date);
			/*Path to properties file */
				String path=data.getServletContext().getRealPath("/WEB-INF") +"/conf";
                        	String acdPath=path+"/"+"AcademicCalendar.properties";
                        	String hldPath=path+"/"+"CalendarHolidays.properties";
                        	String fpath="";
                        	if(etype.equals("Academic"))
                                	fpath=acdPath;
                        	else
                                	fpath=hldPath;
                        	File f=new File(fpath);
			/*If file exist than read value from file.*/
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
                        	context.put("detail",detail);
                        }
                        else{
                                msg=MultilingualUtil.ConvertedString("ac_msg3",LangFile);
                                data.setMessage(msg);
                        }

                }

			

	}		
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("The exception in Academic Event file::"+e);
			data.setMessage("See ExceptionLog");
		}
	}
}
