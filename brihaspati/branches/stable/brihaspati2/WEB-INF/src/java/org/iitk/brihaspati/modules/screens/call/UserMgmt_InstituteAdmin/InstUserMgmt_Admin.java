package org.iitk.brihaspati.modules.screens.call.UserMgmt_InstituteAdmin;

/*
 * @(#)InstUserMgmt_Admin.java	
 *
 *  Copyright (c) 2010-11,2012 ETRG,IIT Kanpur. 
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
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
 * @author <a href="mailto:sharad23nov@yahoo.com">Sharad Singh</a>
 * @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
 * @author <a href="mailto:tejdgurung20@gmail.com">Tej Bahadur</a>
 * @author  <a href="prajeev@iitk.ac.in">Rajeev Parashari</a>
 * @modified date:23-12-2010, 11-01-2011, 31-01-2012
 * @modified date:18-07-2012(Rajeev),30-10-2012(Richa)
 */
import java.util.List;
import java.util.Vector;
import org.apache.turbine.util.RunData;
import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;
import org.apache.turbine.services.security.torque.om.TurbineUserGroupRolePeer;
import org.apache.turbine.services.security.torque.om.TurbineUser;
import org.apache.turbine.services.security.torque.om.TurbineUserPeer;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.CourseUserDetail;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.ListManagement;
import org.iitk.brihaspati.modules.utils.CourseManagement;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.modules.utils.CourseProgramUtil;
import org.iitk.brihaspati.modules.utils.InstituteDetailsManagement;
import org.iitk.brihaspati.om.InstituteProgramPeer;
import org.iitk.brihaspati.om.InstituteProgram;
import org.iitk.brihaspati.om.StudentRollno;
import org.iitk.brihaspati.om.StudentRollnoPeer;
import org.iitk.brihaspati.om.ProgramPeer;
import org.iitk.brihaspati.modules.screens.call.SecureScreen_Institute_Admin;
import org.iitk.brihaspati.modules.utils.UserGroupRoleUtil;

/* This screen class is called when Institute admin add a user as Secondary Instructor,student and author.
*View Student course list,delete Instructor/student,add multiple user's(Instructor/student)and upload photo. 

*/

public class InstUserMgmt_Admin extends SecureScreen_Institute_Admin
{
    public void doBuildTemplate( RunData data, Context context )
    {
	/* Get the mode for which part executed of the screen.
	 * get the counter for colour the tag in browser and 
	 * set mode and counter in context.
	 */
	String mode=data.getParameters().getString("mode","");
	String counter=data.getParameters().getString("count"," ");
	//String stats=data.getParameters().getString("stats"," ");
	String uname=data.getParameters().getString("username"," ");
	context.put("tdcolor",counter);
	context.put("username",uname);
	context.put("mode",mode);
	//context.put("stats",stats);
	//ParameterParser pp=data.getParameters();
	//String role1=data.getParameters().getString("Role");
	//context.put("role1",role1);
	//ErrorDumpUtil.ErrorLog("Role====>>"+role1);
	String instituteId=(data.getUser().getTemp("Institute_id")).toString();
        String file=(String)data.getUser().getTemp("LangFile");
	/**
 	* Get institute wise user rollno list.
 	* *@see CourseProgramUtil util in utils.   
 	*/ 
	List rollnoprglist=CourseProgramUtil.getUserInstituteRollnoList(uname,instituteId);
        Vector Rollnolist = new Vector();
        for(int j=0;j<rollnoprglist.size();j++)
 	{
        	StudentRollno st = (StudentRollno)rollnoprglist.get(j);
                String PrgCode = st.getProgram();
                String Pgname = InstituteIdUtil.getPrgName(PrgCode);
                String rollno = st.getRollNo();
                CourseUserDetail Crsdetail=new CourseUserDetail();
                Crsdetail.setPrgName(Pgname);
                Crsdetail.setRollNo(rollno);
                Rollnolist.add(Crsdetail);
        }
        context.put("rlnolist",Rollnolist);
	if(mode.equals("rollnomgmt")){
		if(rollnoprglist.size()==0){
			context.put("type","NoRollno");
			String msg=MultilingualUtil.ConvertedString("prgm_msg6",file);
			data.setMessage(msg);
		}	
		else
			context.put("type","notEmpty");
	}

	/**
	  *get InstituteId and used in getting Institute Course List.
	  *@see InstituteDetailsManagement util in utils. 	
	  */
	try{
		if((mode.equals(""))||(mode.equals("AddMUser"))||(mode.equals("userdelete"))||(mode.equals("USzip"))){	
			Vector CourseList=InstituteDetailsManagement.getInstituteCourseDetails(instituteId);
        		context.put("courseList",CourseList);
		}
		if(mode.equals("userdelete")){
			String role=data.getParameters().getString("role");
			context.put("role",role);
		}
	}
	catch(Exception ex)
	{
		ErrorDumpUtil.ErrorLog("Error in screen[InstUserMgmt_Admin.java] "+ex);
	}
	/**
 	 * Getting list of program from database according to institute
 	 */ 	
	try
	{
		int InstId=Integer.parseInt(instituteId);
		Criteria crit=new Criteria();
                crit.add(InstituteProgramPeer.INSTITUTE_ID,InstId);
                List Instplist= InstituteProgramPeer.doSelect(crit);
                Vector PrgDetail = new Vector();
                for(int i=0;i<Instplist.size();i++)
                {       
                        InstituteProgram element = (InstituteProgram)Instplist.get(i);
                        String PrgCode = element.getProgramCode();
                       // String PrgCode = Integer.toString(prgcode);
                        String prgName = InstituteIdUtil.getPrgName(PrgCode);
                        CourseUserDetail cDetails=new CourseUserDetail();
                        cDetails.setPrgName(prgName);
                        cDetails.setPrgCode(PrgCode);
                        PrgDetail.add(cDetails);
                }   
               	context.put("PrgDetail",PrgDetail);
	}
	catch(Exception e)
	{
		ErrorDumpUtil.ErrorLog("Error in screen[InstUserMgmt_Admin.java] "+e);
	}
	
	
	/**
	 * Institute admin view the student course list by search string selecting query(First name ,last name username and email )  
	 */
	if((mode.equals("sclist"))||(mode.equals("instlist"))){
		String stat=data.getParameters().getString("status");
                context.put("stat",stat);
		String mode1=data.getParameters().getString("mode1","");
		context.put("mode1",mode1);
		Vector userList=new Vector();
		List v=null;
		if(mode1.equals("list")){
		try{
                        MultilingualUtil m_u=new MultilingualUtil();

                        /**
                         * Get the search criteria and the search string
                         * from the screen
                         */


                        ParameterParser pp=data.getParameters();
                        String query=pp.getString("queryList");
                        if(query.equals(""))
                              query=pp.getString("query");

                        /**
                         * check for special characters using StringUtil.
			 * @see StringUtil in utils	
                         */

                         //String  valueString =StringUtil.replaceXmlSpecialCharacters(data.getParameters().getString("value"));
                         String  valueString =StringUtil.replaceXmlSpecialCharacters(data.getParameters().getString("valueString"));

                        context.put("query",query);
                        context.put("value",valueString);
                        String str=null;
			List rusrlist=CourseProgramUtil.getInstituteUserRollnoList(instituteId);
                        context.put("rollnolist",rusrlist);
			//ErrorDumpUtil.ErrorLog("rollnolist in instUserMagmt_Admin screen---"+rusrlist);

			/*set the feild as in TURBINE_USER table 
			 *according to search string set by user.
			 *select the list from database
			 */
                        if(query.equals("First Name"))
                                str="FIRST_NAME";
                        else if(query.equals("Last Name"))
                                str="LAST_NAME";
                        else if(query.equals("User Name"))
                                str="LOGIN_NAME";
                        else if(query.equals("Email"))
                                str="EMAIL";
                        else if(query.equals("RollNo"))
                                str="ROLL_NO";
			if(query.equals("RollNo"))
			{
				Criteria crit = new Criteria();
                                crit.add("STUDENT_ROLLNO",str,(Object)("%"+valueString+"%"),crit.LIKE);
                                crit.addAscendingOrderByColumn(StudentRollnoPeer.ROLL_NO);
                                v=StudentRollnoPeer.doSelect(crit);
				//ErrorDumpUtil.ErrorLog("List return from screen after search======"+v);
				//rusrlist=CourseProgramUtil.getInstituteUserRollnoList(instituteId);
	                        //context.put("rollnolist",rusrlist);
			}
			else
			{
				Criteria crit=new Criteria();
	                        crit.addJoin(TurbineUserPeer.USER_ID,TurbineUserGroupRolePeer.USER_ID);
	                        crit.add("TURBINE_USER",str,(Object)(valueString+"%"),crit.LIKE);
				if((mode.equals("sclist"))){
	                        	crit.add(TurbineUserGroupRolePeer.ROLE_ID,3);
				}
				if((mode.equals("instlist"))){
	                        	crit.add(TurbineUserGroupRolePeer.ROLE_ID,2);
				}
	                        crit.setDistinct();
	                        //List v=null;
	                        v=TurbineUserPeer.doSelect(crit);
				//ErrorDumpUtil.ErrorLog("List return from database===\n"+v);
			}

                        /**
                         * if list not empty then get the User details
			 *@see ListManagement util in utils. 
                         * use listDivide method for pagination of ListManagement
                         * set in context admin configuration value and the vector
                         */
                        if(v.size()!=0)
                                {
                                //Vector userList=ListManagement.getDetails(v,"User");
                                if(query.equals("RollNo")){
					userList=ListManagement.getInstituteUDetails(v,"RollNo",instituteId);
				}
				else{
					if((mode.equals("sclist"))){
	                             	   	userList=ListManagement.getInstituteUDetails(v,"User",instituteId);
					}
	                                if((mode.equals("instlist"))){
	                             	   	userList=ListManagement.getInstituteUDetails(v,"UserIns",instituteId);
					}
				}
				
				/**
 				 * if vector size is zero this shows no user exist with match string in this institute
				 * then show message. 
 				 */ 
				if(userList.size()==0){
				String usrWith=m_u.ConvertedString("usrWith",file);
                                String notExist=m_u.ConvertedString("notExist",file);
                                if(((String)data.getUser().getTemp("lang")).equals("hindi"))
                                        data.setMessage(usrWith+" "+query+" "+"'"+ valueString+"'"+" "+notExist );
                                else
                                        data.setMessage(usrWith+" "+query+" "+"'"+ valueString+"'"+" "+notExist );}
                                String path=TurbineServlet.getRealPath("/WEB-INF")+"/conf"+"/InstituteProfileDir/"+instituteId+"Admin.properties";
                                int AdminConf=10;
                                AdminConf = Integer.parseInt(AdminProperties.getValue(path,"brihaspati.admin.listconfiguration.value"));
                                context.put("AdminConf",new Integer(AdminConf));
                                context.put("AdminConf_str",Integer.toString(AdminConf));
                                int startIndex=pp.getInt("startIndex",0);
                                int endlastIndex=pp.getInt("end");
                                context.put("endlastIndex",String.valueOf(endlastIndex));
                                String status=new String();
                                int t_size=userList.size();

                                int value[]=new int[7];
                                value=ListManagement.linkVisibility(startIndex,t_size,AdminConf);
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
                                Vector splitlist=ListManagement.listDivide(userList,startIndex,AdminConf);
                                context.put("ListUser",splitlist);
                        }
                        else
                        {
                                String usrWith=m_u.ConvertedString("usrWith",file);
                                String notExist=m_u.ConvertedString("notExist",file);
                                if(((String)data.getUser().getTemp("lang")).equals("urdu"))
                                        data.setMessage(usrWith+" "+notExist+" " +query+" "+"'"+ valueString+"'" );
                                        //data.setMessage(usrWith+" "+query+" "+"'"+ valueString+"'"+" "+notExist );
                                else
                                        data.setMessage(usrWith+" "+query+" "+"'"+ valueString+"'"+" "+notExist );

                                context.put("stat","ForallUser");
                                //data.setScreenTemplate("call,UserMgmt_Admin,SelectUser.vm");
                                data.setScreenTemplate("call,UserMgmt_InstituteAdmin,InstUserMgmt_Admin.vm");

                        }
		}
		catch(Exception ex){data.setMessage("error in select course list---->"+ex);}
		}//if
	}

    }
}

