package org.iitk.brihaspati.modules.screens;
/*
 * @(#) ViewCourseList.java	
 *
 *  Copyright (c) 2005, 2009,2010,2012 ETRG,IIT Kanpur. 
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

import java.util.Vector;
import java.util.ArrayList;
import java.util.List;
import org.apache.turbine.modules.screens.VelocityScreen;
import org.apache.turbine.util.RunData;
import org.iitk.brihaspati.om.Courses;
import org.iitk.brihaspati.om.CoursesPeer;
import org.apache.velocity.context.Context;
import org.apache.commons.lang.StringUtils;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.CourseUserDetail;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.ListManagement;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.modules.utils.CourseManagement;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.torque.util.Criteria;

/**
 * This class contains code for display details of all registered courses 
 * in System 
 * 
 * @author <a href="awadhesh_trivedi@yahoo.co.in">Awadhesh Kumar Trivedi</a>
 * @author <a href="nksngh_p@yahoo.co.in">Nagendra Kumar Singh</a>
 * @author <a href="mailto:shaistashekh@hotmail.com">Shaista</a>
 * 
 * @author <a href="singh_jaivir@rediffmail.com">Jaivir Singh</a>11102010
 * @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
 * @modified date:16-March-2012(Richa)
 */
public class ViewCourseList extends VelocityScreen
{
    	/**
     	* @param data Rundata
     	* @param context Context
     	* @see CourseUserDetail in Utils
     	*/
	public void doBuildTemplate( RunData data, Context context ) 
        {
		try
               	{
			ParameterParser pp=data.getParameters();
                        String lang=pp.getString("lang","english");
                        context.put("lang",lang);
			String LangFile=new String();
			Vector Details= new Vector();
			LangFile = MultilingualUtil.LanguageSelectionForScreenMessage(lang);
                        context.put("LangFile",LangFile);
                        int ListConf=pp.getInt("SearchList",10);
                        context.put("ListConf",new Integer(ListConf));
                        context.put("ListConf_str",Integer.toString(ListConf));
			String searchString=pp.getString("SearchKey","");
			List instdetail=InstituteIdUtil.getInstList();
			Criteria crit=new Criteria();
			List q=new ArrayList();
			String query="";
                        context.put("instdetail",instdetail);
			String Instid=pp.getString("InstId","");
			query=data.getParameters().getString("queryList","");
                        if(query.equals("")){
	                        query=data.getParameters().getString("query");
                        }
			context.put("query",query);
			/**
			 * check special character
			 * @see StringUtil in Utils
			 *
			 */
			searchString =StringUtil.replaceXmlSpecialCharacters(searchString);
                        context.put("SearchKey",searchString);
			if((org.apache.commons.lang.StringUtils.isBlank(searchString))&&(org.apache.commons.lang.StringUtils.isNotBlank(Instid))){
				context.put("InstName",InstituteIdUtil.getIstName(Integer.parseInt(Instid)));
				Details=CourseManagement.getInstituteCourseNUserDetails("All",Instid);
			}
			if((org.apache.commons.lang.StringUtils.isNotBlank(searchString))&&(org.apache.commons.lang.StringUtils.isBlank(Instid))){
				String str="GROUP_NAME";
				if(query.equals("CourseId"))
                                        str="GROUP_NAME";
                                else if(query.equals("Course Name"))
                                        str="CNAME";
				String table="COURSES";
                                crit.add(table,str,(Object)("%"+searchString+"%"),crit.LIKE);
                                q=CoursesPeer.doSelect(crit);
                                for(int i=0;i<q.size();i++)
                                {
                                        byte b= ((Courses)(q.get(i))).getActive();
                                        String act=Byte.toString(b);
                                        String courseName=((Courses)(q.get(i))).getCname().toString();
                                        String galias=((Courses)(q.get(i))).getGroupAlias().toString();
                                        String groupname=((Courses)(q.get(i))).getGroupName().toString();
                                        String iid=StringUtils.substringAfterLast(groupname,"_");
                                        String nmeml=StringUtils.substringBeforeLast(groupname,"_");
                                        String iname=InstituteIdUtil.getIstName(Integer.parseInt(iid));
                                        String pieml=StringUtils.substringAfter(nmeml,galias);
                                        String insname=UserUtil.getFullName(UserUtil.getUID(pieml));
                                        CourseUserDetail cuDetail=new CourseUserDetail();
                                        cuDetail.setInstName(iname);
                                        cuDetail.setEmail(pieml);
                                        cuDetail.setActive(act);
                                        cuDetail.setInstructorName(insname);
                                        cuDetail.setCourseName(courseName);
                                        cuDetail.setCAlias(galias);
                                        Details.add(cuDetail);
                                }
                                context.put("InstName"," ");
			}
                        if((org.apache.commons.lang.StringUtils.isNotBlank(searchString))&&(org.apache.commons.lang.StringUtils.isNotBlank(Instid))){
   	                	Details=ListManagement.getInstituteListBySearchString("CourseWise",query,searchString,Instid);
                                context.put("InstName",InstituteIdUtil.getIstName(Integer.parseInt(Instid)));
                        }
                        context.put("Instid",Instid);
			if(Details.size()==0)
			{
				if(!Instid.equals("")&&(searchString.equals("")))
				{
					data.setMessage(MultilingualUtil.ConvertedString("regCourseMsg",LangFile));
				}
				else if(!searchString.equals("")){
					String m1=MultilingualUtil.ConvertedString("listCourseReg",LangFile);
					String m2=MultilingualUtil.ConvertedString("notExist",LangFile);
					if(LangFile.endsWith("en.properties"))
						data.setMessage(m1+ " "+query +" '"+searchString+"' "+m2);
					else if(LangFile.endsWith("hi.properties"))
						data.setMessage("'"+searchString+"'"+" " + query +" "+m2);
					else if(LangFile.endsWith("urd.properties"))
						data.setMessage( m2+" "+ "'"+ searchString+"'"+ query);
				}
			}
                       	int startIndex=pp.getInt("startIndex",0);
                       	String status=new String();
                       	int t_size=Details.size();
			
                       	if(Details.size()!=0)
			{
                       		status="notempty";
                               	int value[]=new int[7];
                               	value=ListManagement.linkVisibility(startIndex,t_size,ListConf);

                               	int k=value[6];
                               	context.put("k",String.valueOf(k));

                               	Integer total_size=new Integer(t_size);
                               	context.put("total_size",total_size);

                               	int eI=value[1];
                               	Integer endIndex=new Integer(eI);
                               	context.put("endIndex",endIndex);

                               	int check_first=value[2];
                               	context.put("check_first",String.valueOf(check_first));

                               	int check_pre=value[3];
                               	context.put("check_pre",String.valueOf(check_pre));

                               	int check_last1=value[4];
                               	context.put("check_last1",String.valueOf(check_last1));

                       	 	int check_last=value[5];
                               	context.put("check_last",String.valueOf(check_last));

                               	context.put("startIndex",String.valueOf(eI));
                               	Vector splitlist=ListManagement.listDivide(Details,startIndex,ListConf);
	   			context.put("allcourse",splitlist);
                       	}
			else
			{
				status="empty";
			}
	   		context.put("status",status);
		}//end try
	       	catch(Exception e)
		{
			data.setMessage("The error in View Registered Course List !!"+e);
		}  
	}          
}
