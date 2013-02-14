package org.iitk.brihaspati.modules.screens;

/*
 * @(#)BrihaspatiLogin.java	
 *
 *  Copyright (c) 2004-2005,2013 ETRG,IIT Kanpur. 
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

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.util.parser.ParameterParser;       
import org.apache.turbine.modules.screens.VelocityScreen;
import org.iitk.brihaspati.modules.utils.ListManagement;
import org.iitk.brihaspati.om.NewsPeer;
import org.iitk.brihaspati.om.News;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.RemoteCourseUtilClient;
import org.iitk.brihaspati.modules.utils.NewsHeadlinesUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.NewsDetail;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.apache.turbine.services.servlet.TurbineServlet;
import java.util.List;
import java.util.Date;
import java.util.Vector;
import org.apache.torque.util.Criteria;
import org.apache.xmlrpc.XmlRpc;
import java.text.SimpleDateFormat;
import java.text.Format;
import org.iitk.brihaspati.om.Lecture;
import org.iitk.brihaspati.om.LecturePeer;
import java.text.DateFormat;
import java .util.GregorianCalendar;
import java .util.Calendar;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

//import in.ac.dei.edrp.api.DEIRemoteAccessAPI;
import org.iitk.brihaspati.modules.utils.AdminProperties;

/**
 * @author <a href="mailto:awadhesh_trivedi@yahoo.co.in">Awadhesh Kumar Trivedi</a>
 * @author <a href="mailto:sweetshaista00@yahoo.com">Shaista Bano</a>
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla</a>
 */
public class BrihaspatiLogin extends VelocityScreen
{
    /**
     * Place all the data object in the context
     * for use in the template.
     */
    public void doBuildTemplate( RunData data, Context context )
    {
		boolean flag = false;
		System.gc();
		try{
			 /**
			  *This block of code compare current date ant time to Lecture Date and Time
			  *if Lecture date and time is greater than current date and time then pics 
		          *LECTURE ID of that Lecture and sends to VM for further use.
			  */

			 ArrayList list = new ArrayList();
                         Map map = new HashMap();
					
			 int lectureId=0;			
			
			 // Get Current date month and year.

			 Calendar cal = new GregorianCalendar();
			 int day = cal.get(Calendar.DAY_OF_MONTH);
			 int month=cal.get(Calendar.MONTH)+1;
			 int year=cal.get(Calendar.YEAR);
			 
			 Date date2 = new Date();
                         java.sql.Date currentDate = new java.sql.Date(date2.getTime());
                         Criteria crte = new Criteria();
                         crte.addGroupByColumn(LecturePeer.LECTUREID);
                         List lec=LecturePeer.doSelect(crte);

			 //Get Current time using SimpleDateFormat.

                         SimpleDateFormat stf = new SimpleDateFormat("HH:mm");
                         String time = stf.format(new Date());
                         Date currenttime=stf.parse(time);
			 
			 //Loop for getting data from Lecture table.
                         for(int i=0;i<lec.size();i++){
                                Lecture element=(Lecture)lec.get(i);
                                String lectime=element.getSessiontime();
                                String str1[]=lectime.split(":");
                                int itime = Integer.parseInt(str1[0]);
                                String duration=element.getDuration();
                                String str[]=duration.split(":");
                                int iduration=Integer.parseInt(str[0]);
                                int finaltime=itime+iduration;
                                String sf=new Integer(finaltime).toString();
                                String ct=sf+":"+str1[1];
                                Date sessiondate1=element.getSessiondate();
				java.sql.Date sessiondate= new java.sql.Date(sessiondate1.getTime());
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                		String strDate = dateFormat.format(sessiondate);
                                String strDate_split[]=strDate.split("-");
				int sday=Integer.parseInt(strDate_split[2]);
				int smonth=Integer.parseInt(strDate_split[1]);
				int syear=Integer.parseInt(strDate_split[0]);
                                SimpleDateFormat stf1 = new SimpleDateFormat("HH:mm");
                                Date date=stf1.parse(ct);

				//Condition to check if current date is before session date.And if current date is equal to session date then time will also 				     be cmpared.
 
				if((currentDate.compareTo(sessiondate)<0 )|| ((day==sday && month==smonth && year==syear) && currenttime.compareTo(date) < 0)){
					map = new HashMap();
					String lecname = element.getLecturename();
					map.put("lecname",lecname);
                                	lectureId=element.getLectureid();
					map.put("lid",lectureId);
					list.add(map);

                                }

                        }
			context.put("lec_details",list);
		}catch(Exception e){}
		//
/*
		String message=DEIRemoteAccessAPI.getStudentInfo("nksinghiitk@gmail.com", "iitk_brihaspati");
                ErrorDumpUtil.ErrorLog("Message comes from dei server  =="+message);

String hdir=System.getProperty("user.home");
		String osnme=System.getProperty("os.name");
		
                String path1234="";
                if (osnme.startsWith("Win")){
                        path1234=hdir+"\\remote_auth\\brihaspati3-remote-access.properties";
                }
                else{
                        path1234=hdir+"/remote_auth/brihaspati3-remote-access.properties";
                }
                System.out.println("The Value of path on the basis of OS is   "+osnme +"=="+path1234);
 ErrorDumpUtil.ErrorLog("The Value of path on the basis of OS is   "+osnme +"=="+path1234);
*/

                try{
                        ParameterParser pp=data.getParameters();
                        String lang=pp.getString("lang","");
                        if(lang.equals(""))
			{
				flag = true;
				lang= "english";
			}

				context.put("flag",flag);
				context.put("lang",lang);
				lang= "";
			
			String lpath=data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/"+"Admin.properties";
			String ldapcat = AdminProperties.getValue(lpath,"brihaspati.admin.ldapcate.value");
		//	boolean ldps=StringUtil.isBlank(ldapcat);
			context.put("ldapcat",ldapcat);
			//context.put("ldps",ldps);
			
			Vector newsd=NewsHeadlinesUtil.getNews(1);
			int t_size=newsd.size();
	        	if(t_size!=0)
                	{	
                                Vector split_news=ListManagement.listDivide(newsd,0,10);
                        	context.put("detail",split_news);
                        	context.put("status","Notempty");
                	}
			else
			{
                        	context.put("status","empty");
			}
			context.put("sample",newsd);

			/**
			* Keep xmlrpc port Alive
			*/

                        boolean bool= XmlRpc.getKeepAlive();
                        if(!bool)
			{
                        	XmlRpc.setKeepAlive(true);
			}//if

			// for notofication
			String path=data.getServletContext().getRealPath("/WEB-INF")+"/conf"+"/"+"Notification.properties";
			String fhead = AdminProperties.getValue(path,"brihaspati.admin.flashHeading.value");
	                context.put("fNoti",fhead);
	                context.put("msg",pp.getString("msg",""));


		}
                catch(Exception e)
		{
			data.setMessage("The Error in Login Page !!"+e);
		}

    }
}
