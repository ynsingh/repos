package org.iitk.brihaspati.modules.actions;

/*
 * @(#) Groupmanagement.java
 *
 *  Copyright (c) 2006-07 ETRG,IIT Kanpur.
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
 */

import java.io.File;
import java.util.Vector;
import java.util.List;
import java.util.Date;
import java.util.StringTokenizer;

import org.apache.velocity.context.Context;
import org.apache.turbine.util.RunData;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.om.security.User;
import org.apache.torque.util.Criteria;

import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.XmlWriter;
import org.iitk.brihaspati.modules.utils.XmlData;
import org.iitk.brihaspati.modules.utils.FileEntry;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlWriter;
import org.iitk.brihaspati.modules.utils.GroupUtil;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.ListManagement;
import org.iitk.brihaspati.modules.utils.NotInclude;

import org.iitk.brihaspati.om.News;
import org.iitk.brihaspati.om.NewsPeer;

import org.apache.turbine.services.security.torque.om.TurbineUserGroupRolePeer;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;
import org.apache.turbine.services.security.torque.om.TurbineUser;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.turbine.modules.screens.VelocityScreen;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;


/**This class contain the code Create, Delete, Update,Join,Set activity
* and Selection of student for the Group.
* @author: <a href="mailto:seema_020504@yahoo.com">seema pal</a>
* @author: <a href="mailto:kshuklak@rediffmail.com">kishore kumar shukla</a>
* @modified: <a href="mailto:shaistashekh@hotmail.com">Shaista</a>
* @modifie date: 15-02-2011
*/


public class Groupmanagement extends SecureAction
{
	/**
         * Place all the data object in the context for use in the template.
         * @param data RunData instance
         * @param context Context instance
         * @exception Exception, a generic exception
         */
	
	/** Get path for GroupManagment.*/

        String groupPath=TurbineServlet.getRealPath("/Courses");
	VelocityScreen vs=new VelocityScreen();
	String LangFile ="";
	/**This method is for the cancelling process.*/

	public void doCancel(RunData data,Context context)
        {
                data.setScreenTemplate("call,Group_Mgmt,Grpmgmt.vm");
        }

	/**This method is for the creating grouptype.*/

	public void doCreategrouptype(RunData data,Context context)
	{       
		try{
        		ParameterParser pp=data.getParameters();
			/**
                         * Get courseid for the user currently logged in
                         * @see UserUtil in Util.
                         */
                	User user = data.getUser();
			String courseid=(String)user.getTemp("course_id");
                        /**
			*Get  path for the group 
			*and creating groupmanagement dir.
			*/
                        File f=new File(groupPath+"/"+courseid+"/"+"GroupManagement");
			if(!f.exists())
                        f.mkdirs();
			/**
			*Here we creating the blank xml for maintaining the record
			*@see TopicMetaDataXmlWriter in Util.
			*/
			String grouplistpath=groupPath+"/"+courseid+"/"+"GroupManagement";
			File grouplistxml= new File(grouplistpath+"/GroupList__des.xml");
                	if(!grouplistxml.exists())
                        	TopicMetaDataXmlWriter.GroupxmlRootOnly(grouplistxml.getAbsolutePath());
		}//try
		catch(Exception e){
                                   ErrorDumpUtil.ErrorLog("Error in method:doCreategrouptype !!"+e);
                                   data.setMessage("See ExceptionLog !! " );
				}
	}//docreategrouptype

	/**
	*This method is for the creating the groups
	*and save the group informations.
	*/
	
	public void doSavegroup(RunData data,Context context)
        {	
                try{
                	ParameterParser pp=data.getParameters();
			LangFile=data.getUser().getTemp("LangFile").toString();

			/**
                        * Get courseid for the user currently logged in
                        * @see UserUtil in Util.
                        */
               		User user = data.getUser();
                	String courseid=(String)user.getTemp("course_id");
			int g_id=GroupUtil.getGID(courseid);
			/**
                        *Retrieve the parameters by using the ParameterParser and
                        *Put it in the context for Using in templates.
                        */
                	String grouptype=pp.getString("grouptype","");
                	String groupname=pp.getString("groupname","");
                	String groupmode=pp.getString("groupmode","");
                	String groupdesc =pp.getString("groupdesc","");
			/**
                         * Check for illegal character
                         * @see StringUtil in Util.
                         */
			groupdesc=StringUtil.replaceXmlSpecialCharacters(groupdesc);
			
			/** Get the current date of the system.*/
                	Date date=new Date();
                	String CreationDate=date.toString();
			/** Blank variables for this method.*/
			String studentname="",stdno="",gpname="",grptype="",stdname="";
			boolean found=false;
			int len=0,stuno=0,j=0;
			XmlWriter xmlwriter;
			TopicMetaDataXmlReader topicmetadata;
			if(!grouptype.equals("usagegroup"))
			{
                		String groupno =pp.getString("groupno","");
				len=Integer.parseInt(groupno);
			}
			/** 
			*Maintaining the record in the GroupListxml file 
			*@see TopicMetaDataXmlWriter in Util
			*/
			String groupnamepath=groupPath+"/"+courseid+"/"+"GroupManagement";
			String Grouplist="/GroupList__des.xml";
			if(grouptype.equals("usagegroup"))
				methodsave(groupnamepath,Grouplist,groupname,CreationDate,grouptype,studentname,stdno,groupdesc,data);
			/**Creating Multiple groups.*/
			else if((grouptype.equals("Multiplegroup")) && (groupmode.equals("Mgroup")))
			{
				for( j=0;j<len;j++)
				{
					gpname=groupname+j;
					methodsave(groupnamepath,Grouplist,gpname,CreationDate,grouptype,studentname,stdno,groupdesc,data);
				}//for
			}
			else if((groupmode.equals("MRgroup"))&& (grouptype.equals("Multiplegroup")))
			{//ifmr
				/** 
				*Getting parameter through parameter parser
				* Put in the context for the use in templates.
				*/
                		String studentno =pp.getString("studentno","");
				context.put("studentno",studentno);
				stuno=Integer.parseInt(studentno);
				//-------------------------------------------------------------------
                        	Vector freshlist=new Vector();
                       		Vector selectedList=new Vector();
				/**
				* Getting the groupdetail through xml file
				**@see TopicMetaDataXmlReader in Util.
				*/
                               	topicmetadata=new TopicMetaDataXmlReader(groupnamepath+"/GroupList__des.xml");
                               	Vector Read=topicmetadata.getGroupDetails();
                               	if(Read!=null)
                               	{//1if
                                       	for(int k=0;k<Read.size();k++)
                                       	{//2for
						/**
						*Getting the groupwise detail by xml file
						*and get the selected list of student groupwise.
						*/
                                               	gpname =((FileEntry) Read.elementAt(k)).getName();
                                               	topicmetadata=new TopicMetaDataXmlReader(groupnamepath+"/"+gpname+"__des.xml");
                                               	Vector sname =topicmetadata.getGroupDetails();
                                               	if(sname!=null)
                                               	{//2if
                                                       	for(int m=0;m <sname.size();m++)
                                                       	{//3for
                                                               	stdname=((FileEntry)sname.elementAt(m)).getUserName();
                                                               	selectedList.addElement(stdname);
                                                       	}//for3
                                               	}//2if
                                       	}//2for
                               	}//1if
				/**
				*getting the list of all students of the course
				*matching with selected list of student
				*for getting the freshlist of student for add in the group. 
				*/
				Criteria crit =new Criteria();
                        	crit.addJoin(TurbineUserPeer.USER_ID,TurbineUserGroupRolePeer.USER_ID);
                        	crit.add(TurbineUserGroupRolePeer.ROLE_ID,3);
                        	crit.and(TurbineUserGroupRolePeer.GROUP_ID,g_id);
                        	crit.setDistinct();
                        	List v=TurbineUserPeer.doSelect(crit);
                        	for(int l=0;l<v.size();l++)
                        	{
                               		TurbineUser element=(TurbineUser)v.get(l);
                               		studentname=element.getUserName();
                               		for(int k=0;k<selectedList.size();k++)
                               		{
                                       		stdname=(String)selectedList.get(k);
                                       		if(studentname.equals(stdname))
                                       		{
                                               		found=true;
                                       		}
                               		}
                               		if(found == false)
					{
                                               	freshlist.add(studentname);
                               		}
                        		found=false;
                        	} //for1
//-------------------------------------------------------------------				
				/**
                                * Getting the groupdetail through xml file
                                *@see TopicMetaDataXmlReader in Util.
                                */
				//int l=0;
                                //int ar=stuno;
				int check=stuno*len;
				ErrorDumpUtil.ErrorLog("\ncheck----"+check);
				if(freshlist.size()==0)
				{
					//data.setMessage("Sorry !! No students are remained to join the group.");
					data.setMessage(MultilingualUtil.ConvertedString("Grpmgmt_msg8",LangFile));
					
					//	break;
				}
				else if(freshlist.size()<check)
				{
					//data.setMessage("Sorry !! Number of student is not sufficient to join the group.");
					data.setMessage(MultilingualUtil.ConvertedString("Grpmgmt_msg10",LangFile));
				}
				else
				{
					int l=0;
                                	int ar=stuno;
					for( j=0;j<len;j++)
					{
						gpname=groupname+j;
						methodsave(groupnamepath,Grouplist,gpname,CreationDate,grouptype,studentname,stdno,groupdesc,data);
						/**
						*Randomly distribution of the student
						*in multiple group (freshlist).
						*/
						for(;l<ar;l++)
						{
							ErrorDumpUtil.ErrorLog("\n2partfreshlist----"+freshlist.size()+"\nar-----"+ar+"\ngpnme"+gpname);
                               				studentname=(String)freshlist.get(l);
							File addxmlpath=new File(groupnamepath+"/"+gpname+"__des.xml");
							if(!addxmlpath.exists())
               							xmlwriter=new XmlWriter(groupnamepath+"/"+gpname+"__des.xml");
							else
								xmlwriter=TopicMetaDataXmlWriter.Groupwriterxml(groupnamepath,gpname);
							TopicMetaDataXmlWriter.appendGroupElement(xmlwriter,gpname,grouptype,CreationDate,studentname,stdno);
                       					xmlwriter.writeXmlFile();
						}//for
						l=ar;
						ar=ar+stuno;
					}//for
				}//else
				vs.doRedirect(data,"call,Group_Mgmt,Grp_View_Delete.vm");
			}//ifmr
			else
			{
				/**
				*Getting the parameters from the template
				*and creating signupgroups.
				*/
                		String studentno =pp.getString("studentno","");
				for(j=0;j<len;j++)
				{
					gpname=groupname+j;
					methodsave(groupnamepath,Grouplist,gpname,CreationDate,grouptype,studentname,studentno,groupdesc,data);
				}//for
			}//else
		}//try
		catch(Exception e){
                                   ErrorDumpUtil.ErrorLog("Error in method:doSavegroup !!"+e);
                                   data.setMessage("See ExceptionLog !! " );
				}
	}//save method

	/**This method is for the Add memebers in the particular group
	* by using xml file.  
	*/
	public void doAddSelected(RunData data, Context context)
	{
		try
		{
                        ParameterParser pp=data.getParameters();
			LangFile=data.getUser().getTemp("LangFile").toString();
			/**
                        * Get  courseid for the user currently logged in
                        * @see UserUtil in Util.
                        */
                        User user = data.getUser();
                        String courseid=(String)user.getTemp("course_id");

			/**
			*Getting parameter through parameter parser
                        * Put in the context for the use in templates.
                        */
                	String grouptype=pp.getString("type","");
			context.put("type",grouptype);
			String grpoption=pp.getString("val");
			context.put("val",grpoption);

			/** Get the current date of the system.*/
                        Date date=new Date();
                        String CreationDate=date.toString();
                        String studentname="",studentno="",stuno="",gname="";
			boolean found=false;
			XmlWriter xmlwriter=null;
			TopicMetaDataXmlReader topicmetadata=null;

			/**
			*Getting the name of students
			*for joing the groups.
			*/
			String studentList=pp.getString("StudentNames","");
               		StringTokenizer st=new StringTokenizer(studentList,"^");
               		String stdnames[]=new String[st.countTokens()];
               		for(int i=0;st.hasMoreTokens();i++)
               		{
                       		stdnames[i]=st.nextToken();
               		}
                       	for(int i=0,j=1;i<stdnames.length;i++)
			{
                        	studentname=stdnames[i];
				Vector selectlist=new Vector();

				/** Getting the path for maintaing the records in the xml file.*/
				String addpath=groupPath+"/"+courseid+"/"+"GroupManagement";
				File addxmlpath=new File(addpath+"/"+grpoption+"__des.xml");
				if(!addxmlpath.exists())
                       			xmlwriter=new XmlWriter(addpath+"/"+grpoption+"__des.xml");
				else
				{
					/**
                                        * Getting the groupdetail through xml file
                                        * @see TopicMetaDataXmlReader in Util.
                                        */
                               		topicmetadata=new TopicMetaDataXmlReader(addpath+"/GroupList__des.xml");
                                	Vector grouplist=topicmetadata.getGroupDetails();
					if(grouplist!=null)
                                	{
                                		for(int m=0;m<grouplist.size();m++)
                                       		{//for
							gname =((FileEntry) grouplist.elementAt(m)).getName();
							stuno =((FileEntry) grouplist.elementAt(m)).getstudentno();
                               				topicmetadata=new TopicMetaDataXmlReader(addpath+"/"+gname+"__des.xml");
                                			selectlist=topicmetadata.getGroupDetails();
							if(selectlist!=null)
                                			for(int k=0;k<selectlist.size();k++)
							{
								/** Check if the student profile already exists in group.*/
                                               			String sname =((FileEntry) selectlist.elementAt(k)).getUserName();
                                               			if(sname.equals(studentname))
                                                       			found=true;
								data.setMessage(MultilingualUtil.ConvertedString("Grpmgmt_msg5",LangFile)+" "+MultilingualUtil.ConvertedString("Grpmgmt_msg7",LangFile));
                                       			}//for
						}//for
                                	}//if
					/**
					*Maintaing the records in the xml file
					* @see TopicMetaDataXmlWriter in Util.
					*/
					if(found==false)
					{
						xmlwriter=TopicMetaDataXmlWriter.Groupwriterxml(addpath,grpoption);
						TopicMetaDataXmlWriter.appendGroupElement(xmlwriter,grpoption,grouptype,CreationDate,studentname,studentno);
                       	 			xmlwriter.writeXmlFile();
						data.setMessage(MultilingualUtil.ConvertedString("brih_successfully",LangFile)+" "+MultilingualUtil.ConvertedString("Grpmgmt_msg5",LangFile)+" "+MultilingualUtil.ConvertedString("Grpmgmt_msg6",LangFile));
						
					}
				}//else
			}//for
		}//try
		catch(Exception e){
                                   ErrorDumpUtil.ErrorLog("Error in method:doAddSelected !!"+e);
                                   data.setMessage("See ExceptionLog !! " );
				}
	}

	/**This method is for the view memebers of the particular group
        * by using xml file.
        */
        public void doViewmember(RunData data, Context context)
        {
                try
                {
                        ParameterParser pp=data.getParameters();
                        LangFile=data.getUser().getTemp("LangFile").toString();
                        /**
                        * Get  courseid for the user currently logged in
                        * @see UserUtil in Util.
                        */
                        User user = data.getUser();
                        String courseid=(String)user.getTemp("course_id");

                        /**
                        *Getting parameter through parameter parser
                        * Put in the context for the use in templates.
                        */
                        String grpName=pp.getString("val");

			/**
                        *Reading the group xml for getting the details
                        *put in the context for the use in templates.
                        *@see TopicMetaDataXmlReader in Utils
                        */
			String addpath=groupPath+"/"+courseid+"/"+"GroupManagement";
                        TopicMetaDataXmlReader topicmetadata=null;
                        Vector selectedlist=new Vector();
                        File f=new File(groupPath+"/"+grpName+"__des.xml");
                        if(f.exists())
                        {
                        	topicmetadata=new TopicMetaDataXmlReader(addpath+"/"+grpName+"__des.xml");
                                selectedlist=topicmetadata.getGroupDetails();
                                if(selectedlist == null)
                                return;
                                if(selectedlist.size()!=0)
                                {
                                        context.put("selectedlist",selectedlist);
                                        context.put("mode","noempty");
                                }
                                else
                                        context.put("mode","empty");
			}
		}//try
		catch(Exception e){
                                   ErrorDumpUtil.ErrorLog("Error in method:doViewmember !!"+e);
                                   data.setMessage("See ExceptionLog !! " );
				}
	}

        /** This method is for the Group Deletion .*/  

        public void doDeleteGroup(RunData data,Context context)
        {
               	try
		{
                	ParameterParser pp=data.getParameters();
			LangFile=data.getUser().getTemp("LangFile").toString();
			
			/**
                        * Get userid and courseid for the user currently logged in
                        * @see UserUtil in Util.
                        */
			User user = data.getUser();
                	String courseid=(String)user.getTemp("course_id");

			/** Getting the groupnames for the deletion.*/
			String deleteList=pp.getString("deleteFileNames","");
               		StringTokenizer st=new StringTokenizer(deleteList,"^");
               		String fileToDelete[]=new String[st.countTokens()];
               		for(int i=0;st.hasMoreTokens();i++)
               		{
                       		fileToDelete[i]=st.nextToken();
               		}
               		XmlWriter xmlWriter;
			/** Get the path of the groups and xml file.*/
               		String grouppath=groupPath+"/"+courseid+"/"+"GroupManagement";

               		String xmlfile="GroupList";
			Vector str=new Vector();
			String deltype="grpdel";
                       	/**
                       	* Delete the Entry  and geting the groupname for deleting 
			*the file. 
                       	*/
                       	for(int i=0;i<fileToDelete.length;i++)
                       	{
                               	String groupName=fileToDelete[i];
				str=DeleteEntry(grouppath,xmlfile,groupName,deltype,data);
                               	File file=new File(grouppath+"/"+groupName+"__des.xml");
                                file.delete();
			}
			data.setMessage(MultilingualUtil.ConvertedString("GrpmgmtGroup",LangFile)+" "+MultilingualUtil.ConvertedString("c_msg9",LangFile));

                  }//try
		  catch(Exception e){
                                   	ErrorDumpUtil.ErrorLog("Error in method:doDeleteGroup !!"+e);
                                   	data.setMessage("See ExceptionLog !! " );
					}
         }//method

	/**This method is for the Remove members from the paerticular group.*/  

	public void doRemoveselectedstudent(RunData data,Context context)
	{	
		try
		{
                	ParameterParser pp=data.getParameters();
			LangFile=data.getUser().getTemp("LangFile").toString();
			
			/**
                        * Get userid and courseid for the user currently logged in
                        * @see UserUtil in Util.
                        */
			User user = data.getUser();
                	String courseid=(String)user.getTemp("course_id");
			
			/**
                        *Getting parameter through parameter parser
                        *and selected student names for the removal. 
                        */
			String Status=pp.getString("Status","");
			context.put("Status",Status);
                	String grpname=pp.getString("grpname","");
                	String leadername=pp.getString("grpleader","");
                	String grptype=pp.getString("grptype","");
                	String stuno=pp.getString("stuno","");
                	String date1=pp.getString("date1","");
                	String date2=pp.getString("date2","");
                	String sno=pp.getString("sno","");
                	String actmode=pp.getString("actmode","");
                	String actlead=pp.getString("leadval","");
			String studentList=pp.getString("StudentNames","");
                        StringTokenizer st=new StringTokenizer(studentList,"^");
                        String stdnames[]=new String[st.countTokens()];
                        for(int i=0;st.hasMoreTokens();i++)
                        {
                                stdnames[i]=st.nextToken();
                        }
               		XmlWriter xmlWriter;
			/**
			* Get the path of the groups and xml file
			* and sequence no of the student for the removal.
			*/
			Date date=new Date();
                        String CreationDate=date.toString();
			String deltype="studel",studentno="",studentname="";
			Vector str=new Vector(); 
               		String grouppath=groupPath+"/"+courseid+"/"+"GroupManagement";
//---------------------------------------------------------------------CODE FOR LEADER-----//
				/**
				*Getting the name of students
				*for the groupsleader.
				*/
				if(actmode.equals("lead"))
				{
					String xmlfile="GroupList",type="grplist",type1="grp";
					/**
                        		*Getting the path for set leader and writing in  xml file
                        		*@see TopicMetaDataXmlWriter in Util.
                        		*/
					MethodLeader(type,grouppath,xmlfile,grpname,actlead,data);
					MethodLeader(type1,grouppath,grpname,grpname,actlead,data);
				}
//-----------------------------------------------------------------------------------------------//
				else
				{
					for(int i=0;i<stdnames.length;i++)
                       			{
                               			String StudentName=stdnames[i];
						str=DeleteEntry(grouppath,grpname,StudentName,deltype,data);
						if(StudentName.equals(leadername)||(sno.equals("leader")))
						{
							xmlWriter=TopicMetaDataXmlWriter.Groupwriterxml(grouppath,grpname);
                                			TopicMetaDataXmlWriter.appendGroupElement(xmlWriter,grpname,grptype,date2,StudentName,studentno);
							xmlWriter.writeXmlFile();
							str=DeleteEntry(grouppath,"GroupList",grpname,"grpdel",data);
							xmlWriter=TopicMetaDataXmlWriter.Groupwriterxml(grouppath,"GroupList");
                                			TopicMetaDataXmlWriter.appendGroupElement(xmlWriter,grpname,grptype,date1,studentname,stuno);
							xmlWriter.writeXmlFile();
							ErrorDumpUtil.ErrorLog("leadername"+leadername+"grptype"+grptype);
						}
					}//for
					//data.setMessage("Members deleted successfully !!");
					data.setMessage(MultilingualUtil.ConvertedString("Grpmgmt_msg5",LangFile)+" "+MultilingualUtil.ConvertedString("c_msg9",LangFile));
				}//else

		}//
		catch(Exception e){
                                   ErrorDumpUtil.ErrorLog("Error in method:doRemoveselectedstudent !!"+e);
                                   data.setMessage("See ExceptionLog !! " );
				}
	}//method
	

	/**This method is for Updation.*/

        public void doupdategroup(RunData data,Context context)
        {
                try
                {
                        ParameterParser pp=data.getParameters();
			LangFile=data.getUser().getTemp("LangFile").toString();
			
			/**
                        * Get  courseid for the user currently logged in
                        * @see UserUtil in Util.
                        */
                        User user = data.getUser();
                        String courseid=(String)user.getTemp("course_id");

			/**
			*Getting parameter through parameter parser
			*Put the parameters context for use in templates.
			*/
                        String grptype=pp.getString("type","");
                        String grpname=pp.getString("grpname","");
                       	String grpdesc=pp.getString("groupdesc","");
			String groupname=pp.getString("groupname","");
			String studentno=pp.getString("studentno","");
                        XmlWriter xmlWriter=null;
			int seq=-1;
			grpdesc=StringUtil.replaceXmlSpecialCharacters(grpdesc);

			/** Get the current date of the system.*/
                	Date date=new Date();
                	String CreationDate=date.toString();
			String studentname="",gname="", actvity="";
			TopicMetaDataXmlReader topicmetadata=null;
			boolean found=false;

			/** Get the path of the groups and xml file.*/
                        String grouppath=groupPath+"/"+courseid+"/"+"GroupManagement";
			String xmlfile="/GroupList__des.xml";
			File grouplistxmls=new File(grouppath+xmlfile);

                       	/** Delete the Entry  and geting the groupname for deleting.*/ 
			if(!grpname.equals(groupname)) 
			{
                       		topicmetadata=new TopicMetaDataXmlReader(grouppath+xmlfile);
                        	Vector Read=topicmetadata.getGroupDetails();
				if(Read!=null)
				{
                       			for(int i=0;i<Read.size();i++)
                       			{//for
                       				gname =((FileEntry) Read.elementAt(i)).getName();
                       				if(grpname.equals(gname))
                       				{//if
							seq=i;
							break;
                       				}//if
					}//for
					xmlWriter=TopicMetaDataXmlWriter.Groupwriterxml(grouppath,xmlfile);
	                		xmlWriter.removeElement("Group",seq);
        	       			xmlWriter.writeXmlFile();
				}//if
			}//if equals
			/**
			*Appending the entry in the xml File
			*@see TopicMetaDataXmlWriter in Util.
			*/
                         topicmetadata=new TopicMetaDataXmlReader(grouppath+"/"+xmlfile);
                         Vector collect=topicmetadata.getGroupDetails();
                         if(collect!=null)
                         {
                         	for(int i=0;i<collect.size();i++)
                                {//for
                                	String name =((FileEntry) collect.elementAt(i)).getName();
                                	if(groupname.equals(name))
                                        {//if
                                        	found=true;
                                        }//if
                                }//for
                        }//if
                        if(found==false)
			{
                       		xmlWriter=TopicMetaDataXmlWriter.Groupwriterxml(grouppath,xmlfile);
                       		TopicMetaDataXmlWriter.appendGroupElement(xmlWriter,groupname,grptype,CreationDate,studentname,studentno);
                        	xmlWriter.writeXmlFile();
			}
			/**
                       	*Creating the blank xml for the maintaining the record
                       	*and also mainting the description record in this xmlfile
                       	*@see TopicMetaDataXmlWriter in Util.
                       	*/
                       	File groupnamexml=new File(grouppath+"/"+groupname+"__des.xml");
                       	if(!groupnamexml.exists())
                       	{
                               	TopicMetaDataXmlWriter.GroupxmlRootOnly(groupnamexml.getAbsolutePath());
                               	xmlWriter=new XmlWriter(grouppath+"/"+groupname+"__des.xml");
                       	}
                       	else
                               	xmlWriter=TopicMetaDataXmlWriter.Groupwriterxml(grouppath,groupname);
                       	xmlWriter.changeData("Desc",grpdesc,0);
                        xmlWriter.writeXmlFile();

			/**
			*Getting the path for maintaing the records of student
			*for this we reading the previous xml and writing in new xml.
			*/
			if(!groupname.equals(grpname))
			{
                      	topicmetadata=new TopicMetaDataXmlReader(grouppath+"/"+grpname+"__des.xml");
                       	Vector student=topicmetadata.getGroupDetails();
                        String activity=topicmetadata.getActivity();
			if(student!=null)
			{
                       		for(int i=0;i<student.size();i++)
                       		{//for
                       			studentname =((FileEntry) student.elementAt(i)).getUserName();
					File addxmlpath=new File(grouppath+"/"+grpname+"__des.xml");
                        		if(!addxmlpath.exists())
                               			xmlWriter=new XmlWriter(grouppath+"/"+groupname+"__des.xml");
                        		else
						xmlWriter=TopicMetaDataXmlWriter.Groupwriterxml(grouppath,groupname);
                                	TopicMetaDataXmlWriter.appendGroupElement(xmlWriter,groupname,grptype,CreationDate,studentname,studentno);
                        		xmlWriter.changeData("Desc",grpdesc,0);
                                        xmlWriter.changeData("activity",activity,0);
        	       			xmlWriter.writeXmlFile();
				}//for
			}//if
			}
			/** Get the path  deleting the previous xml.*/
			if(!grpname.equals(groupname))
			{
                        	File file=new File(grouppath+"/"+grpname+"__des.xml");
                               	file.delete();
			}
                        data.setMessage(MultilingualUtil.ConvertedString("GrpmgmtGroup",LangFile)+" "+MultilingualUtil.ConvertedString("c_msg5",LangFile));
                        /** Calling the next vm.*/
                        vs.doRedirect(data,"call,Group_Mgmt,Grpmgmt.vm");

                }//try
		catch(Exception e){
                                   ErrorDumpUtil.ErrorLog("Error in method:doupdategroup !!"+e);
                                   data.setMessage("See ExceptionLog !! " );
				}
             }//method

	/**This method is for JoinGroup.*/
	 
	public void doJoinGroup(RunData data,Context context)
	{
		try
		{	
                        ParameterParser pp=data.getParameters();
			LangFile=data.getUser().getTemp("LangFile").toString();
			
			/**
                        * Get username,courseid and userrole  for the user currently logged in
                        * @see UserUtil in Util.
                        */
                        User user = data.getUser();
			String userName=user.getName();
			String userRole=(String)user.getTemp("role");
                        String courseid=(String)user.getTemp("course_id");

			/**Getting parameter through parameter parser.*/
			String grouptype=pp.getString("type","");
			String grpName=pp.getString("val","");
			String studentno="";

			/** Get the current date of the system.*/
                	Date date=new Date();
                	String CreationDate=date.toString();
			
			/**
                        *Getting the path for maintaing the records of student
                        *and writing in  xml file
			*@see TopicMetaDataXmlWriter in Util.
                        */
               		String grouppath=groupPath+"/"+courseid+"/"+"GroupManagement";
			XmlWriter xmlwriter;
			xmlwriter=TopicMetaDataXmlWriter.Groupwriterxml(grouppath,grpName);
			TopicMetaDataXmlWriter.appendGroupElement(xmlwriter,grpName,grouptype,CreationDate,userName,studentno);
	               	xmlwriter.writeXmlFile();
			data.setMessage(MultilingualUtil.ConvertedString("Grpmgmt_msg9",LangFile)+" "+MultilingualUtil.ConvertedString("Grpmgmt_msg4",LangFile));
			/** Calling the next vm.*/
	               	vs.doRedirect(data,"call,Group_Mgmt,ViewActivity.vm");
		}//try
		catch(Exception e){
                                   ErrorDumpUtil.ErrorLog("Error in method:doJoinGroup !!"+e);
                                   data.setMessage("See ExceptionLog !! " );
				}
	}//method

	/** This method is for Setting the Activity For the particular group.*/

	public void doActivity(RunData data,Context context)
	{
		try
		{
			LangFile=data.getUser().getTemp("LangFile").toString();
			/**
                        * Get courseid  for the user currently logged in
                        * @see UserUtil in Util.
                        */
			User user = data.getUser();
			String courseid=(String)user.getTemp("course_id");
			
			/**
                        *Getting parameter through parameter parser
                        *Put the parameters context for use in templates.
			*/
			String seloption=data.getParameters().getString("val");
			context.put("val",seloption);
			String valgrp=data.getParameters().getString("val1");
			String valtype=data.getParameters().getString("actmode","");
			String pollval=data.getParameters().getString("pollno");
			String options=new String();
			String activity="",groupdesc="";
			StringTokenizer st;
			Vector grpDetail=new Vector();
			boolean match=false;
			XmlWriter xmlWriter=null;
			TopicMetaDataXmlReader topicmetadata=null;
			
			/**
                        *Getting the path for set Activity and writing in  xml file
			* and also set vm for the related activity
                        *@see TopicMetaDataXmlWriter in Util.
                        */
               		String filepath=groupPath+"/"+courseid+"/"+"GroupManagement";
			if(valtype.equals("setact"))
			{
				File xmlpath=new File(filepath+"/"+valgrp+"__des.xml");
                       		topicmetadata=new TopicMetaDataXmlReader(filepath+"/"+valgrp+"__des.xml");
				grpDetail=topicmetadata.getGroupDetails();
				if((match==true)||(match==false)&&(grpDetail!=null))
				{
					if(seloption.equals("Discussion Board"))
					{
	                			vs.doRedirect(data,"call,Dis_Board,DisBoard.vm");
						context.put("mode","compose");
						context.put("mode1","grpmgmt");
					}
					if(seloption.equals("Chat"))
	                		vs.doRedirect(data,"call,Chat,Chat.vm");
					if(seloption.equals("Local Mail"))
	                		vs.doRedirect(data,"call,Local_Mail,MailTestMessage.vm");
					if(seloption.equals("Assignment"))
	                		vs.doRedirect(data,"call,Assignment,Assignment.vm");
					if(seloption.equals("Notice"))
	                		vs.doRedirect(data,"call,Notice_User,Notice_Send.vm");
				}
				if(!xmlpath.exists())
					xmlWriter=new XmlWriter(filepath+"/"+valgrp+"__des.xml");
				else
				{
					/**
					*Reading the xml file Check if the activity already exists in group.
					*@see TopicMetaDataXmlReader in Util.
					*/
                       			topicmetadata=new TopicMetaDataXmlReader(filepath+"/"+valgrp+"__des.xml");
					groupdesc=topicmetadata.getTopicDescription();
					activity=topicmetadata.getActivity();
					options=seloption;
					if(activity!=null)
					{
						options=activity+","+seloption;
						st=new StringTokenizer(activity,",");
                                		for(int i=0;st.hasMoreTokens();i++)
                                		{
                                        		activity=st.nextToken();
                                        		if(activity.equals(seloption))
                                        		{
                                                		match=true;
                                        		}
                                        		data.setMessage(MultilingualUtil.ConvertedString("brih_This",LangFile)+" "+MultilingualUtil.ConvertedString("Grpmgmt_msg3",LangFile));
                                		}
					}
				}//else
				/**
				* Set the activity and writing in the xml file.
				*@see TopicMetaDataXmlWriter in Util.
				*/
				if(match==false)
				{
					if(grpDetail!=null)
					{
                        			xmlWriter=TopicMetaDataXmlWriter.Groupwriterxml(filepath,valgrp);
                        			xmlWriter.changeData("activity",options,0);
                        			xmlWriter.changeData("Desc",groupdesc,0);
                        			xmlWriter.writeXmlFile();
						data.setMessage(MultilingualUtil.ConvertedString("brih_successfully",LangFile)+"!!"+" "+MultilingualUtil.ConvertedString("Grpmgmt_msg2",LangFile));
					}
					else
					{
						data.setMessage(MultilingualUtil.ConvertedString("Grpmgmt_msg11",LangFile));
					}
				}
			}//valtype
			//code for the group leader-------------------------------------------------------//
			//code for the expiry time---------------------------------------------//
			if(valtype.equals("setpoll"))
			{
				String type3="",stuno="";
				int pollval1=Integer.parseInt(pollval);
				Date date=new Date();
                        	String CreationDate=date.toString();
				String c_date=ExpiryUtil.getCurrentDate("-");
				java.sql.Date Post_date=java.sql.Date.valueOf(c_date);
                        	String exDate1=ExpiryUtil.getExpired(c_date,pollval1);
				java.sql.Date Expiry_date=java.sql.Date.valueOf(exDate1);
				Vector str1=new Vector();
				File grouplistxml= new File(filepath+"/Pollexptime__des.xml");
                        	if(!grouplistxml.exists())
                        	{
                               		TopicMetaDataXmlWriter.GroupxmlRootOnly(grouplistxml.getAbsolutePath());
                               		xmlWriter=new XmlWriter(filepath+"/Pollexptime__des.xml");
                        	}
                        	topicmetadata=new TopicMetaDataXmlReader(filepath+"/Pollexptime__des.xml");
                        	Vector collect=topicmetadata.getGroupDetails();
                        	if(collect!=null)
                        	{
                        		for(int i=0;i<collect.size();i++)
                                	{//for
                                		String gname1 =((FileEntry) collect.elementAt(i)).getName();
                                        	if(gname1.equals(valgrp))
							str1=DeleteEntry(filepath,"Pollexptime",gname1,"grpdel",data);
                                	}//for
				}
                        	xmlWriter=TopicMetaDataXmlWriter.Groupwriterxml(filepath,"/Pollexptime__des.xml");
                        	TopicMetaDataXmlWriter.appendGroupElement(xmlWriter,valgrp,type3,c_date,pollval,exDate1);
                        	xmlWriter.writeXmlFile();
				//----------------News------------------------------//
				String uname=user.getName();
                        	String u_id=Integer.toString(UserUtil.getUID(uname));
                        	int gid=GroupUtil.getGID(courseid);
				String news_title="Groupmanagement-";
				String news="voting start for"+" " +valgrp+ " "+"Group"+ " "+"close on:" + Expiry_date ;
				Criteria crit=new Criteria();
                        	crit.add(NewsPeer.GROUP_ID,gid);
                        	crit.add(NewsPeer.USER_ID,u_id);
                        	crit.add(NewsPeer.NEWS_TITLE,news_title);
                        	crit.add(NewsPeer.NEWS_DESCRIPTION,news);
                        	crit.add(NewsPeer.PUBLISH_DATE,Post_date);
                        	crit.add(NewsPeer.EXPIRY,pollval1);
                        	crit.add(NewsPeer.EXPIRY_DATE,Expiry_date);
                        	NewsPeer.doInsert(crit);
			}
						data.setMessage(MultilingualUtil.ConvertedString("brih_successfully",LangFile)+"!!"+" "+MultilingualUtil.ConvertedString("Grpmgmt_msg2",LangFile));
			//-------------------------------------------------//
		}//try
		catch(Exception e){
                                   ErrorDumpUtil.ErrorLog("Error in method:doActivity !!"+e);
                                   data.setMessage("See ExceptionLog !! " );
					}
	}
	/** This method is for Reset the selected Activity.*/

	public void doResetActivity(RunData data,Context context)
	{
		try
		{
			LangFile=data.getUser().getTemp("LangFile").toString();
			/**
                        * Get courseid  for the user currently logged in
                        * @see UserUtil in Util.
                        */
			User user = data.getUser();
			String courseid=(String)user.getTemp("course_id");
			
			/** Getting parameter through parameter parser.*/
			String seloption=data.getParameters().getString("val");
			String valgrp=data.getParameters().getString("val1");
			String grpName="",activity="",options="",absoluteString="",groupdesc="";
			Vector freshlist=new Vector();
			StringTokenizer st;
                        StringTokenizer Str;
			XmlWriter xmlWriter=null;

			/**
                        *Getting the path reading xml file for  activity value
                        *@see TopicMetaDataXmlReader in Util.
                        */
               		String filepath=groupPath+"/"+courseid+"/"+"GroupManagement";
			TopicMetaDataXmlReader topicmetadata=null;
                       	topicmetadata=new TopicMetaDataXmlReader(filepath+"/"+valgrp+"__des.xml");
			groupdesc=topicmetadata.getTopicDescription();
			String activity1=topicmetadata.getActivity();

			boolean found=false;
			String str1[]=new String[1000];
			String str2[]=new String[1000];
			String str3[]=new String[1000];
			int k=0; int l=0; int m=0; 
			if(activity1!=null)
			{
				st=new StringTokenizer(activity1,",");
				for(int i=0;st.hasMoreTokens();i++)
                       		{
					/** Check for the existing activity.*/
                        		activity=st.nextToken();
					if(activity.equals(seloption))
					{
						found=true;
					}
					if(found==false)
					{
						freshlist.add(activity);
						str3[k]=activity;
						k++;
					}
					found=false;
					activity="";
				}
				context.put("freshlist",freshlist);
				String abstr="";
				for(int abstr1=0;abstr1<k;abstr1++)
				{
					String abstr2=str3[abstr1];
					if(abstr.equals(""))
						abstr=abstr2;
					else abstr=abstr+","+abstr2;
				}
				/**
				* Reset the activity nad updating the xml file
				* @see TopicMetaDataXmlWriter in Util.
				*/
				xmlWriter=TopicMetaDataXmlWriter.Groupwriterxml(filepath,valgrp+"__des.xml");
	                	xmlWriter.changeData("activity",abstr,0);
        	       		xmlWriter.writeXmlFile();
				if(LangFile.endsWith("en.properties"))
					data.setMessage(MultilingualUtil.ConvertedString("Grpmgmt_msg1",LangFile)+" "+MultilingualUtil.ConvertedString("brih_successfully",LangFile));
				else
					data.setMessage(MultilingualUtil.ConvertedString("brih_successfully",LangFile)+"!!"+" "+MultilingualUtil.ConvertedString("Grpmgmt_msg1",LangFile));
			}
			
		}//try
		catch(Exception e){
                                   ErrorDumpUtil.ErrorLog("Error in method:doResetActivity !!"+e);
                                   data.setMessage("See ExceptionLog !! " );
				}
	}

	/**this method is for the polling.*/
	public void doPolling(RunData data,Context context)
	{
		try
		{
			LangFile=data.getUser().getTemp("LangFile").toString();
                        /**
                        * Get username for the user currently logged in
                        * @see UserUtil in Util.
                        */
                        User user = data.getUser();
			String courseid=(String)user.getTemp("course_id");
			String username=user.getName();
                        context.put("username",username);
			String grpname=data.getParameters().getString("grpname","");
			String value=data.getParameters().getString("val2");
			String filepath=groupPath+"/"+courseid+"/"+"GroupManagement";
			String stuno="",deltype="studel",studentname="",type="",Cdate="";
			int sno=0;
			XmlWriter xmlWriter=null;
                        TopicMetaDataXmlReader topicmetadata=null;
                        File grouplistxml= new File(filepath+"/PollingList__des.xml");
                        if(!grouplistxml.exists())
			{
                                TopicMetaDataXmlWriter.GroupxmlRootOnly(grouplistxml.getAbsolutePath());
				xmlWriter=new XmlWriter(filepath+"/PollingList__des.xml");
			}
                       	xmlWriter=TopicMetaDataXmlWriter.Groupwriterxml(filepath,"/PollingList__des.xml");
                       	TopicMetaDataXmlWriter.appendGroupElement(xmlWriter,grpname,username,Cdate,value,stuno);
			xmlWriter.writeXmlFile();
                       	topicmetadata=new TopicMetaDataXmlReader(filepath+"/"+grpname+"__des.xml");
                       	String groupdesc=topicmetadata.getTopicDescription();
                       	String activity=topicmetadata.getActivity();
			Vector Detail=topicmetadata.getGroupDetails();
			if(Detail!=null)
                       	{
                       		for(int j=0;j<Detail.size();j++)
                       		{//for
                               		studentname=((FileEntry) Detail.elementAt(j)).getUserName();
                               		type=((FileEntry) Detail.elementAt(j)).gettype();
                               		Cdate=((FileEntry) Detail.elementAt(j)).getPDate();
                               		stuno=((FileEntry) Detail.elementAt(j)).getstudentno();
					if(studentname.equals(value))
					{
						xmlWriter=TopicMetaDataXmlWriter.Groupwriterxml(filepath,grpname);
                               			if(stuno.equals(""))
                               			sno =1;
						else
						sno=Integer.parseInt(stuno)+1;
                               			TopicMetaDataXmlWriter.appendGroupElement(xmlWriter,grpname,type,Cdate,studentname,Integer.toString(sno));
                               			xmlWriter.changeData("Desc",groupdesc,0);
                               			xmlWriter.changeData("activity",activity,0);
                               			xmlWriter.writeXmlFile();
						Vector str=DeleteEntry(filepath,grpname,studentname,deltype,data);
					}//if
				}//for2
			}//if2
					//	data.setMessage("Successfully voted");
					data.setMessage(MultilingualUtil.ConvertedString("brih_successfully",LangFile)+"!!"+" "+MultilingualUtil.ConvertedString("Grpmgmt_msg12",LangFile));
		}//try
		catch(Exception e){
                                   ErrorDumpUtil.ErrorLog("Error in method:doPolling !!"+e);
                                   data.setMessage("See ExceptionLog !! " );
                                }
	}

 	/**
	* Default action to perform if the specified action
	* cannot be executed.
	*/
	public void doPerform(RunData data,Context context)throws Exception
	{
		String LangFile=data.getUser().getTemp("LangFile").toString();
		ParameterParser pp=data.getParameters();
       		String action = pp.getString("actionName","");
		context.put("actionName",action);
       		if(action.equals("eventSubmit_doCreategrouptype"))
       			doCreategrouptype(data,context);
		
       		else if(action.equals("eventSubmit_doSavegroup"))
       			doSavegroup(data,context);

               else if(action.equals("eventSubmit_doDeleteGroup"))
                        doDeleteGroup(data,context);

               else if(action.equals("eventSubmit_doAddSelected"))
                        doAddSelected(data,context);

                else if(action.equals("eventSubmit_doupdategroup"))
                       doupdategroup(data,context);

               else if(action.equals("eventSubmit_doRemoveselectedstudent"))
                        doRemoveselectedstudent(data,context);

                else if(action.equals("eventSubmit_doActivity"))
                       doActivity(data,context);
                else if(action.equals("eventSubmit_doResetActivity"))
                       doResetActivity(data,context);
                else if(action.equals("eventSubmit_doJoinGroup"))
                       doJoinGroup(data,context);
                else if(action.equals("eventSubmit_doViewmember"))
                       doViewmember(data,context);
                else if(action.equals("eventSubmit_doPolling"))
                       doPolling(data,context);
       		else
       	 		data.setMessage(MultilingualUtil.ConvertedString("action_msg",LangFile));	
	}
	/** This method is for Creating group.*/

	public void methodsave(String filepath, String groupfile, String groupname, String CreationDate, String grouptype, String studentname, String studentno, String groupdesc, RunData data)
	{
		try
		{
			LangFile=data.getUser().getTemp("LangFile").toString();
			XmlWriter xmlWriter=null;
			boolean found=false;
			String gname="";
                        File grouplistxmls=new File(filepath+"/"+groupfile);
                        if(!grouplistxmls.exists())
                        {
                        	xmlWriter=new XmlWriter(filepath+"/"+groupfile);
                        }
                        /**
                        *Checking for  the existing group
                        *@see TopicMetaDataXmlReader in Util.
                        */
                        else
                        {
                        	TopicMetaDataXmlReader topicmetadata=null;
                                topicmetadata=new TopicMetaDataXmlReader(filepath+"/"+groupfile);
                                Vector collect=topicmetadata.getGroupDetails();
                                if(collect!=null)
                                {
                                	for(int i=0;i<collect.size();i++)
                                        {//for
                                        	 gname =((FileEntry) collect.elementAt(i)).getName();
                                                if(groupname.equals(gname))
                                                	found=true;
						data.setMessage(MultilingualUtil.ConvertedString("brih_This",LangFile) +" "+MultilingualUtil.ConvertedString("GrpmgmtGroup",LangFile)+" "+MultilingualUtil.ConvertedString("Wikiaction6",LangFile)+" " +"!!");
                                          }//for
                                }//if
                        }//else

                        /**
			*Appending the entry in the xml File
                        *@see TopicMetaDataXmlWriter in Util.
                        */
                        if(found==false)
			{ 
                               	xmlWriter=TopicMetaDataXmlWriter.Groupwriterxml(filepath,groupfile);
       	                	TopicMetaDataXmlWriter.appendGroupElement(xmlWriter,groupname,grouptype,CreationDate,studentname,studentno);    			 
                        	xmlWriter.writeXmlFile();
			if(LangFile.endsWith("en.properties"))
                        data.setMessage(MultilingualUtil.ConvertedString("GrpmgmtGroup",LangFile)+" "+MultilingualUtil.ConvertedString("Wikiaction5",LangFile)+" "+MultilingualUtil.ConvertedString("brih_successfully",LangFile));
			else
                        data.setMessage(MultilingualUtil.ConvertedString("GrpmgmtGroup",LangFile)+" "+MultilingualUtil.ConvertedString("brih_successfully",LangFile)+" "+MultilingualUtil.ConvertedString("Wikiaction5",LangFile) +"!!" );
			}
			vs.doRedirect(data,"call,Group_Mgmt,Grp_View_Delete.vm");

                        /**
                        *Creating the blank xml for the maintaining the record
                        *and also mainting the record in this xmlfile
                        *@see TopicMetaDataXmlWriter in Util.
                        */
                        File groupnamexml=new File(filepath+"/"+groupname+"__des.xml");
                        if(!groupnamexml.exists())
			{
                        	TopicMetaDataXmlWriter.GroupxmlRootOnly(groupnamexml.getAbsolutePath());
                                xmlWriter=new XmlWriter(filepath+"/"+groupname+"__des.xml");
			}//if
                        else
                        	xmlWriter=TopicMetaDataXmlWriter.Groupwriterxml(filepath,groupname);
                                xmlWriter.changeData("Desc",groupdesc,0);
				xmlWriter.writeXmlFile();
		}//try
		catch(Exception e){
                                   ErrorDumpUtil.ErrorLog("Error in method:methodsave !!"+e);
                                   data.setMessage("See ExceptionLog !! " );
				}

	}//method save
	public  Vector DeleteEntry(String filePath,String topic,String stuname,String str, RunData data)
        {
                Vector Read=null;
                try
                {
                        XmlWriter xmlWriter=null;
			String studentname="";
                        int seq=-1;
                        TopicMetaDataXmlReader tr =new TopicMetaDataXmlReader(filePath+"/"+topic+"__des.xml");
                        Read=tr.getGroupDetails();
                        if(Read != null)
                        {
                                for(int n=0;n<Read.size();n++)
                                {
					if(str.equals("studel"))
                                        	studentname =((FileEntry)Read.elementAt(n)).getUserName();
					if(str.equals("grpdel"))
                                        	studentname =((FileEntry)Read.elementAt(n)).getName();
                                        if(stuname.equals(studentname))
                                        {
                                                seq=n;
                                                break;
                                        }
                                }
                        }
                        /**
                        Here we deleting the particular entry from the "xml" file
                        */
			xmlWriter=TopicMetaDataXmlWriter.Groupwriterxml(filePath,topic);
                        xmlWriter.removeElement("Group",seq);
                        xmlWriter.writeXmlFile();
                }//try
		catch(Exception e){
                                   ErrorDumpUtil.ErrorLog("Error in method:DeleteEntry !!"+e);
                                   data.setMessage("See ExceptionLog !! " );
				}

        return Read;
        }//readmethod
	
	public void MethodLeader(String type,String filepath,String path,String valgrp,String valgrp2,RunData data) 
	{
		try
		{
			LangFile=data.getUser().getTemp("LangFile").toString();
			XmlWriter xmlWriter=null;
			boolean found=false;
			String studentname="",studentname1="",grptype="",studentno="",grpname="",date1="",grpname1="",deltype="grpdel",date2="",grptype2="",studentno2="";
			Vector str=new Vector();
			TopicMetaDataXmlReader tr=null;
			tr=new TopicMetaDataXmlReader(filepath+"/"+path+"__des.xml");
                        String groupdesc=tr.getTopicDescription();
                        String activity=tr.getActivity();
                        Vector Detail=tr.getGroupDetails();
			if(type.equals("grplist"))						
			{
                        	if(Detail!=null)
                        	{
                        		for(int i=0;i<Detail.size();i++)
                                	{//for
                                		grpname =((FileEntry) Detail.elementAt(i)).getName();
                                       		if(grpname.equals(valgrp))
                                       		{
							grpname1=grpname;
							break;
						}
					}//for
                        		for(int j=0;j<Detail.size();j++)
                                	{//for
                                        	studentname=((FileEntry) Detail.elementAt(j)).getUserName();
                                		grpname =((FileEntry) Detail.elementAt(j)).getName();
                                       		if(grpname.equals(valgrp))
                                       		{
							studentname1=studentname;
							break;
						}
					}//for
                        		for(int k=0;k<Detail.size();k++)
					{
                                        	if((grpname1.equals(valgrp)))
						{
                                        		if(studentname1.equals(valgrp2))
							{
								found=true;
                                               			//data.setMessage("Already assign role as a leader for this group !!");
                        					data.setMessage(MultilingualUtil.ConvertedString("Grpmgmt_msg13",LangFile));
							}
                                        		if(!studentname1.equals(""))
							{
								found=true;
                                               			//data.setMessage("Already assign role as a leader for this group !!");
                        					data.setMessage(MultilingualUtil.ConvertedString("Grpmgmt_msg13",LangFile));
							}
						}//if
					}//for
				}//if		
                                if((found==false)&&(grpname1.equals(valgrp)))
				{
					for(int a=0;a<Detail.size();a++)
                                        {//for
                                                grpname =((FileEntry) Detail.elementAt(a)).getName();
                                        	grptype=((FileEntry) Detail.elementAt(a)).gettype();
                                        	studentno =((FileEntry) Detail.elementAt(a)).getstudentno();
                                        	date1=((FileEntry) Detail.elementAt(a)).getPDate();
                                                if(grpname.equals(valgrp))
                                                {
							date2=date1;
							grptype2=grptype;
							studentno2=studentno;
                                                }
                                        }//for
                                	// Appending Entry(data) in the xml file
                                       	xmlWriter=TopicMetaDataXmlWriter.Groupwriterxml(filepath,path);
                                       	TopicMetaDataXmlWriter.appendGroupElement(xmlWriter,valgrp,grptype2,date2,valgrp2,studentno2);
                                       	xmlWriter.writeXmlFile();
					str=DeleteEntry(filepath,path,valgrp,deltype,data);
					//data.setMessage("Group Leader selected successfully");
                        		data.setMessage(MultilingualUtil.ConvertedString("Grpmgmt_msg14",LangFile)+" "+MultilingualUtil.ConvertedString("brih_successfully",LangFile));

				}//if
			}//if
			if(type.equals("grp"))
			{
                        	if(Detail!=null)
                        	for(int l=0;l<Detail.size();l++)
				{
                                       	studentname =((FileEntry) Detail.elementAt(l)).getUserName();
                                       	studentno =((FileEntry) Detail.elementAt(l)).getstudentno();
                                       	if((studentname.equals(valgrp2))&&(studentno.equals("leader")))
						found=true;
                                              	//data.setMessage("Already assign role as a leader for this group !!");
                        			data.setMessage(MultilingualUtil.ConvertedString("Grpmgmt_msg13",LangFile));
                                       	if((studentno.equals("leader")))
						found=true;
                                            	//data.setMessage("Already assign role as a leader for this group !!");
                        			data.setMessage(MultilingualUtil.ConvertedString("Grpmgmt_msg13",LangFile));
				}
				if(found==false)
                                {
					Date date=new Date();
                        		String CreationDate=date.toString();   
                                	// Appending Entry(data) in the xml file
                                       	xmlWriter=TopicMetaDataXmlWriter.Groupwriterxml(filepath,path);
					if(studentno.equals(""))
                                	studentno ="leader";
                                       	TopicMetaDataXmlWriter.appendGroupElement(xmlWriter,valgrp,grptype,CreationDate,valgrp2,studentno);
                                	xmlWriter.writeXmlFile();
					str=DeleteEntry(filepath,path,valgrp2,"studel",data);
					//data.setMessage("Group Leader selected successfully");
                        		data.setMessage(MultilingualUtil.ConvertedString("Grpmgmt_msg14",LangFile)+" "+MultilingualUtil.ConvertedString("brih_successfully",LangFile));
				}//if
			}//if
		}//try
		catch(Exception e){
                                   ErrorDumpUtil.ErrorLog("Error in method:MethodLeader !!"+e);
                                   data.setMessage("See ExceptionLog !! " );
                                }
	}

}//class
