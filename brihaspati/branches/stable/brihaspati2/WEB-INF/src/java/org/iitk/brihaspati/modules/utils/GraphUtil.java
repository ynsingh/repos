package org.iitk.brihaspati.modules.utils;

/*
 * @(#)GraphUtil.java
 *
 *  Copyright (c) 2012 ETRG,IIT Kanpur.
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
*/
//import java.io.*;
import java.util.List;
//import java.util*.;
import java.io.File;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.torque.util.Criteria;
import javax.xml.parsers.DocumentBuilder;
import java.io.FileOutputStream;
import org.apache.xerces.dom.DocumentImpl;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.om.CourseTimedayPeer;
import org.iitk.brihaspati.om.CourseTimeday;
import org.iitk.brihaspati.om.ModuleTimePeer;
import org.iitk.brihaspati.om.ModuleTime;
import org.apache.turbine.services.servlet.TurbineServlet;


public class GraphUtil{

	public static void  midnightcalculation()
		{
		try{
			//String filepath=TurbineServlet.getRealPath("/Courses/Graph");
			String filepath=TurbineServlet.getRealPath("/Graph");			
			File f=new File(filepath);
			if(!f.exists())
			f.mkdirs();
			File f1=new File(filepath+"/GraphCalculation.xml");
			if(f1.exists())
			f1.delete();
			filepath=filepath+"/GraphCalculation.xml";
			int num[]={7,15,30};
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Calendar cal = Calendar.getInstance();
                        Date todaydat=cal.getTime();
                        String todate=sdf.format(todaydat);
			int time;
			Criteria crit=new Criteria();
                	crit.setDistinct();
                	crit.addGroupByColumn(CourseTimedayPeer.USER_ID);
                	crit.addGroupByColumn(CourseTimedayPeer.COURSE_ID);
			List v=CourseTimedayPeer.doSelect(crit);
			if(v.size()!=0){
				for(int a=0;a<v.size();a++){
					CourseTimeday el=(CourseTimeday)v.get(a);
                			int uid=el.getUserId();
                			String cid=el.getCourseId();
					for(int i=0;i<num.length;i++)
					{
						int count=0;
						//int count1=0;
						String writeinxml=null;
						String fullvalue="";
						for(int j=1;j<=num[i];j++){
							cal.setTime(sdf.parse(todate));
                                        		cal.add(Calendar.DATE,-j);
                                        		String priviousdate = sdf.format(cal.getTime());		
							crit=new Criteria();
                        				crit.add(CourseTimedayPeer.USER_ID,uid);
                        				crit.add(CourseTimedayPeer.COURSE_ID,cid);
							crit.add(CourseTimedayPeer.PRIVIOUS_DATE,priviousdate);
                        				List v1=CourseTimedayPeer.doSelect(crit);
							if(v1.size()!=0){
                                                	        CourseTimeday elm=(CourseTimeday)v1.get(0);
								int time1=elm.getCourseTimeday();
							        time=time1/(60*1000);
								if(time==0)
									count=count+1;
							}else{
								time=0;
								count=count+1;
							}
						fullvalue=fullvalue+priviousdate+","+time+"\n";
					}
					if(count<num[i])
					writeinxml=XMLWriter_GraphCalculation.GraphCalculationXml(filepath,Integer.toString(uid),cid,Integer.toString(num[i]),fullvalue);  
				}
			}
		}
		}catch(Exception ex){ ErrorDumpUtil.ErrorLog("error in graphUtil midnightgraphcalculation------"+ex);}
		//return fullvalue;
	}
	public static void midnightModuleCalculation()
	{
		
		try{
			//String filepath=TurbineServlet.getRealPath("/Courses/Graph");
			String filepath=TurbineServlet.getRealPath("/Graph");
                        File f=new File(filepath);
                        if(!f.exists())
                        f.mkdirs();
                        File f1=new File(filepath+"/ModuleGraphCalculation.xml");
                        if(f1.exists())
                        f1.delete();
                        filepath=filepath+"/ModuleGraphCalculation.xml";
			int num[]={7,15,30};
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Calendar cal = Calendar.getInstance();
                        Date todaydat=cal.getTime();
                        String todate=sdf.format(todaydat);
                        int time;
                        Criteria crit=new Criteria();
                        crit.setDistinct();
                        crit.addGroupByColumn(ModuleTimePeer.USER_ID);
                        crit.addGroupByColumn(ModuleTimePeer.COURSE_ID);
			crit.addGroupByColumn(ModuleTimePeer.MNAME);
                        List v=ModuleTimePeer.doSelect(crit);
			if(v.size()!=0){
                                for(int a=0;a<v.size();a++){
                                        ModuleTime el=(ModuleTime)v.get(a);
                                        int uid=el.getUserId();
                                        String cid=el.getCourseId();
					String mname=el.getMname();
                                        for(int i=0;i<num.length;i++)
                                        {
						int count=0;
                                                String writeinxml=null;
                                                String fullvalue="";
                                                for(int j=1;j<=num[i];j++){
                                                        cal.setTime(sdf.parse(todate));
                                                        cal.add(Calendar.DATE,-j);
                                                        String priviousdate = sdf.format(cal.getTime());                      
                                                        crit=new Criteria();
                                                        crit.add(ModuleTimePeer.USER_ID,uid);
                                                        crit.add(ModuleTimePeer.COURSE_ID,cid);
							crit.add(ModuleTimePeer.MNAME,mname);
							crit.add("MODULE_TIME","MLOGIN_DATETIME",(Object) (priviousdate+"%"),crit.LIKE);
                                                        List v1=ModuleTimePeer.doSelect(crit);
							if(v1.size()!=0){
                                                                ModuleTime el1=(ModuleTime)v1.get(0);
                                                                int time1=el1.getMtime();
                                                              	time=time1/(60*1000);
								if(time==0)
                                                                        count=count+1;

                                                        }else
							{
								time=0;		
								count=count+1;
							}
							fullvalue=fullvalue+priviousdate+","+time+"\n";	
                                                }
					if(count<num[i])
                                        writeinxml=XMLWriter_GraphCalculation.ModuleGraphCalculationXml(filepath,Integer.toString(uid),cid,mname,Integer.toString(num[i]),fullvalue);
                                }
                        }
                }


		}catch(Exception ex){ErrorDumpUtil.ErrorLog("error in graphUtil midnightmodulecalculation------"+ex);}
		
	}

}

