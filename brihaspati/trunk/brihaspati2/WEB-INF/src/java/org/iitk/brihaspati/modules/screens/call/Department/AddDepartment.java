package org.iitk.brihaspati.modules.screens.call.Department;

/*
 * @(#)AddDepartment.java	
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

import java.util.List;
import java.util.Vector;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import org.iitk.brihaspati.om.School;
import org.apache.torque.util.Criteria;
import org.apache.turbine.util.RunData;
import org.iitk.brihaspati.om.Department;
import org.apache.velocity.context.Context;
import org.iitk.brihaspati.om.DeptSchoolUniv;
import org.iitk.brihaspati.om.DeptSchoolUnivPeer;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.ListManagement;
import org.iitk.brihaspati.modules.utils.InstituteIdUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;

/**
 * @author <a href="santoshkumarmiracle@gmail.com">Santosh Kumar</a>
 */

/* This screen class is called when User's selects a Department/School
 */
public class AddDepartment extends SecureScreen
{
	public void doBuildTemplate( RunData data, Context context )
	{
		try
		{
			/**
	         	*Getting file value from temporary variable according to selection
        	 	*Replacing the value from Property file
		 	* @param mode: Getting mode as a String from Parameter Parser 
	         	* @param count: Getting count as a String from Parameter Parser 
        	 	* @param instituteId: Getting instituteId as a String from Parameter Parser 
         	 	**/
		
			String mode=data.getParameters().getString("mode","");
			String counter=data.getParameters().getString("count","");
			String instituteId=data.getParameters().getString("instituteId","");
			if((instituteId.equals(""))||(instituteId.equals(null)))
			{
				instituteId=(data.getUser().getTemp("Institute_id")).toString();
			}
			String dptid=data.getParameters().getString("deptid","");
			String schid=data.getParameters().getString("schid","");
                	context.put("mode",mode);
                	context.put("instituteId",instituteId);
                	context.put("tdcolor",counter);
			String userrole= data.getUser().getTemp("role").toString();
			Criteria crit=new Criteria();
		
			/**
	 		* if mode is lstdept or deptmap or deptdel
	 		* get list of department
	 		* get mapped department list
	 		*@Utils:ListMangement
	 		*/		
			if (mode.equals("lstdept")||mode.equals("deptmap") || (mode.equals("deptunmap"))) 
			{
				dptid="";
				Vector unid=new Vector();
				// getting mapped department list
				List mapdeptlist = ListManagement.getMapDeptList(instituteId);
				/**
				* If role is Insttitute Admin then send department listto the template 
				* to showing mapped department list in template 
				*/
				if(userrole.equals("institute_admin") && ((mode.equals("lstdept"))||(mode.equals("deptunmap"))))
				{
					context.put("mapdeptlist",mapdeptlist);
				}
				if(userrole.equals("institute_admin") && (mode.equals("deptmap")))
				{
					// get all Departments List	
                			List lstdept=ListManagement.getDepartmentList(dptid);
					// get map Departments List	
					crit=new Criteria();
                			crit.add(DeptSchoolUnivPeer.SCHOOL_ID,null);
                			crit.add(DeptSchoolUnivPeer.UNIVERSITY_ID,instituteId);
                			List dsuulist=DeptSchoolUnivPeer.doSelect(crit);
					// set variable for displaying mapped Department List in Template
					context.put("deptmap",lstdept);
					context.put("lsdsu",dsuulist);
				}
			}
			// Getting deprtment list for deletion by super admin
   			if (!userrole.equals("institute_admin"))
			{
				if(mode.equals("lstdept"))
				{
					List newlstdept=ListManagement.getDepartmentList(dptid);
	 				context.put("mapdeptlist",newlstdept);
				}
				if(mode.equals("deptdel"))
				{
        				Vector unid=new Vector();
					//Get Department Name
					//Get Institute name correspondance Department  
					List lstdept=ListManagement.getDepartmentList(dptid);
					String deptname= ((Department)lstdept.get(0)).getName();
					crit=new Criteria();
        				crit.add(DeptSchoolUnivPeer.DEPT_ID,dptid);
        				List dsuulist=DeptSchoolUnivPeer.doSelect(crit);
        				for (int j=0; j<dsuulist.size();j++)
					{
                				DeptSchoolUniv newelement=(DeptSchoolUniv)dsuulist.get(j);
                				String univid=newelement.getUniversityId();
						int instid = Integer.parseInt(univid);
						String InstName=InstituteIdUtil.getIstName(instid);
                				unid.add(InstName);
               				}
                			unid = new Vector(new LinkedHashSet(unid));
					context.put("depid",dptid);
					context.put("depname",deptname);
					context.put("instname",unid);
        			}
			}
			//Getting school list for deletion by super admin
			if (mode.equals("schdel"))
			{
        			Vector unid=new Vector();
        			List lstschool = ListManagement.getSchoolList(schid);
				//Get School Name
        			String schname= ((School)lstschool.get(0)).getName();
        			crit=new Criteria();
        			crit.add(DeptSchoolUnivPeer.SCHOOL_ID,schid);
        			List dsuulist=DeptSchoolUnivPeer.doSelect(crit);
				if(dsuulist.size()!=0)
				{
                			for (int j=0; j<dsuulist.size();j++)
					{
                				DeptSchoolUniv newelement=(DeptSchoolUniv)dsuulist.get(j);
                        			String univid=newelement.getUniversityId();
                        			int instid = Integer.parseInt(univid);
                        			//Get Institute name correspondance School 
                        			String InstName=InstituteIdUtil.getIstName(instid);
                        			unid.add(InstName);
        	        		}
				}
                		unid = new Vector(new LinkedHashSet(unid));
               			context.put("schid",schid);
                		context.put("schname",schname);
                		context.put("instname",unid);
                		context.put("listsize",dsuulist);
        		}
		
                	// This code getting all school list and mapped school list to show in template 
			else if((mode.equals("lstschool")) || (mode.equals("schoolmap")) || (mode.equals("schunmap")))
			{
				schid="";
				// get all School list 
				List lstschool=ListManagement.getSchoolList(schid);
				//if(mode.equals("lstschool")){	
				Vector mapschlist = new Vector();
				// set variable for displaying School info in Template
				List lsdsu=ListManagement.getDeptScoolUnivList(instituteId);
        			ArrayList mapdeptlist = new ArrayList();
        			try 
				{
        				for(int i=0;i<=lsdsu.size();i++)
					{
        					DeptSchoolUniv element=(DeptSchoolUniv)lsdsu.get(i);
        					String schoolid=element.getSchoolId();
						if(schoolid != null)
						{
                					crit=new Criteria();
                        				List mapschool=ListManagement.getSchoolList(schoolid);
                        				mapschlist.addAll(mapschool);
						}
                			}
                		}	
                		catch(Exception e) 
				{
					ErrorDumpUtil.ErrorLog("Exception in getting mapped school list"+e);
				}
				if(((mode.equals("lstschool"))|| (mode.equals("schunmap"))) && (userrole.equals("institute_admin")))
				{	
					mapschlist = new Vector(new LinkedHashSet(mapschlist));
					context.put("lstschool",mapschlist);
                		}
                		else 
				{
					// get all department List to disable mapped department
                			List lstdept=ListManagement.getDepartmentList(dptid);
					// set variable for displaying mapped department with school in Template
					context.put("lstschool",lstschool);
					context.put("deptmap",lstdept);
                			context.put("lsdsu",lsdsu);
                			context.put("schoolmap",lstschool);
                		}
        		}
		}
		catch(Exception e) 
		{
			ErrorDumpUtil.ErrorLog("Exception in Add Department-----"+e.getMessage());
		}
	}
}
