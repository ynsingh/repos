
package org.iitk.brihaspati.modules.screens.call.Task_Mgmt;

/*
 * @(#)TaskDIDU.java
 *
 *  Copyright (c) 2005-2010 ETRG,IIT Kanpur.
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
import java.util.Calendar;
import java.util.Collections;

import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;

import org.iitk.brihaspati.om.Task;
import org.iitk.brihaspati.om.TaskPeer;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.TaskDetail;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.YearListUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import java.util.LinkedList;

/**
 * @author <a href="mailto:nksngh_p@yahoo.co.in">Nagendra Kumar Singh</a>
 * @author <a href="mailto:singh_jaivir@rediffmail.com">Jaivir Singh</a>
 * @author <a href="mailto:kalpanagtm@gmail.com">Kalpana Gautam</a>
 */


public class TaskDIUD extends SecureScreen
{
    public void doBuildTemplate( RunData data, Context context )
    {
                User user=data.getUser();
                String username=user.getName();
                int uid=UserUtil.getUID(username);
                context.put("ylist",YearListUtil.getYearList());
                Criteria crit = new Criteria();
                String mode=data.getParameters().getString("mode","");
                String all=data.getParameters().getString("all","");
                context.put("mode",mode);
        	try
        	{
               		/**
                	 * Getting the current date
                	 */
	                String cdate=ExpiryUtil.getCurrentDate("");
        	        int currdate=Integer.parseInt(cdate);
               		// Integer cdate=new Integer(cdate1);
                	context.put("cdate",Integer.valueOf(cdate));

			LinkedList li1=new LinkedList(); //save ms_id
                        LinkedList Reli2=new LinkedList(); //save Re_id
                        LinkedList temp=new LinkedList();
                        LinkedList li=new LinkedList();
                        LinkedList li3=new LinkedList(); //rearenge ms_id
                        Vector spacevector=new Vector();
			{
				crit.add(TaskPeer.USER_ID,uid);
	                        crit.addAscendingOrderByColumn(TaskPeer.SEQ_NO);
        	                List v1 = TaskPeer.doSelect(crit);
				for(int i=0;i<v1.size();i++) {
                                	li1.add(((Task)v1.get(i)).getTaskId());
                                	Reli2.add(((Task)v1.get(i)).getParentTaskId());
				}
				int space=0;
                        	Object tem=new Object();
                        	int id=0;	
				while(Reli2.size()!=0) {
                                	int i=0;
                                	tem=li1.get(i);
                                	li.add(tem);
                               		temp.add(tem);
                                	Reli2.remove(i);
                                	li1.remove(i);
                                	spacevector.add(space);
                                	while(temp.size()!=0) {
						if(Reli2.contains(tem)) {
							id=Reli2.indexOf(tem);
							tem=li1.get(id);
							li.add(tem);
							temp.add(tem);
                                                	Reli2.remove(id);
                                                	li1.remove(id);
                                                	space=space+1;
                                                	spacevector.add(space);
                                        	} else {
                                                	if(space!=0)
                                                		space=space-1;
                                                	id=temp.indexOf(tem);
                                                	temp.remove(id);
                                                	if(temp.size()!=0) {	
								id=id-1;
								tem=temp.get(id);
                                                	}
                                        	}
                                	}  
                        	}
			}
               		/**
                	 * retrive the task from database and display on the screen.
                	 */
			Vector index=new Vector();
			Vector plist=new Vector();
                        Vector tlist=new Vector();
                        int duedate=0;
                        Vector vct=new Vector();
			Vector v2=new Vector();
                        crit.add(TaskPeer.USER_ID,uid);
			if(!all.equals("")) {
				context.put("spacevector",spacevector);
				for(int kk=0;kk<li.size();kk++)	{
					if(!all.equals("")) {
                        			crit.add(TaskPeer.TASK_ID,li.get(kk));
						List v = TaskPeer.doSelect(crit);
                                        	for(int i=0;i<v.size();i++)
                                        	{
                                        		String Title=((Task)v.get(i)).getTitle();
	                                	        Integer sdate=Integer.valueOf(ExpiryUtil.getDate((((Task)v.get(i)).getStartDate()).toString()));
        	                	                Integer edate=Integer.valueOf(ExpiryUtil.getDate((((Task)v.get(i)).getEndDate()).toString()));
                	                        	duedate=Integer.valueOf(ExpiryUtil.getDate((((Task)v.get(i)).getDueDate()).toString()));
	                        	                int status=((Task)v.get(i)).getStatus();
	        	                                int tid=((Task)v.get(i)).getTaskId();
							int pid=((Task)v.get(i)).getParentTaskId();
							int pseq=((Task)v.get(i)).getPseqNo();
							int depth=((Task)v.get(i)).getDepth();


							v2.add(tid);
        	        	                        TaskDetail tDetail=new TaskDetail();
                	        	                tDetail.setUser_Id(uid);
                        	        	        tDetail.setTask_Id(tid);
							tDetail.setParentTask_Id(pid);
                                	        	tDetail.setTitle(Title);
	                                	        tDetail.setStartDate(sdate);
        	                                	tDetail.setEndDate(edate);
	                	                        tDetail.setStatus(status);
        	                	                tDetail.setSeqNumber(((Task)v.get(i)).getSeqNo());
        	                	                tDetail.setPSeq_No(pseq);
        	                	                tDetail.setDepth(depth);
		
        		                                tlist.add(tDetail);
                		                        String str=Integer.toString(duedate);
                        		                vct.add(str);
							Object obm = Collections.max(v2);
							context.put("maxtid",obm);
							context.put("vindex",index);	
							context.put("splist",plist);
														
                                        	}
					}
				}		
			}else {
				crit.addAscendingOrderByColumn(TaskPeer.SEQ_NO);
                        	List v = TaskPeer.doSelect(crit);
                        	for(int i=0;i<v.size();i++)
                        	{
        	        		String Title=((Task)v.get(i)).getTitle();
	                                Integer sdate=Integer.valueOf(ExpiryUtil.getDate((((Task)v.get(i)).getStartDate()).toString()));
                                	Integer edate=Integer.valueOf(ExpiryUtil.getDate((((Task)v.get(i)).getEndDate()).toString()));
                        	        duedate=Integer.valueOf(ExpiryUtil.getDate((((Task)v.get(i)).getDueDate()).toString()));
                	                int status=((Task)v.get(i)).getStatus();
        	                        int tid=((Task)v.get(i)).getTaskId();
        	                        int pid=((Task)v.get(i)).getParentTaskId();
					int pseq=((Task)v.get(i)).getPseqNo();
					int depth=((Task)v.get(i)).getDepth();

	                                TaskDetail tDetail=new TaskDetail();
                                	tDetail.setUser_Id(uid);
                        	        tDetail.setTask_Id(tid);
                        	        tDetail.setParentTask_Id(pid);
                	                tDetail.setTitle(Title);
        	                        tDetail.setStartDate(sdate);
	                                tDetail.setEndDate(edate);
                                	tDetail.setStatus(status);
                        	        tDetail.setSeqNumber(((Task)v.get(i)).getSeqNo());
        	                	tDetail.setPSeq_No(pseq);
        	                	tDetail.setDepth(depth);

                	                tlist.add(tDetail);
        	                        String str=Integer.toString(duedate);
	                                vct.add(str);
                        	}
			}	
                        for(int n=0;n<vct.size();n++)
                        {
                                String dd=vct.get(n).toString();
                                int cd=Integer.parseInt(dd);
                                if(currdate > cd)
                                {
                                        String information="UPDATE TASK SET STATUS=null WHERE DUE_DATE<"+currdate;
                                        TaskPeer.executeStatement(information);
                                }
                        }

                        if(tlist.size()!=0)
                        {
                                context.put("tlist",tlist);
                                context.put("tlistsize",tlist.size()-1);
                        }
        	} catch(Exception e) {data.setMessage("The error in select task "+e);}
		
		if((mode.equals("insert"))||(mode.equals("subtask"))){
			/**
                 	 *Getting the current and  next day, month, year
                 	 */
				
			String tid=data.getParameters().getString("id");
			context.put("id",tid);
			String idname=data.getParameters().getString("idname");
			context.put("idname",idname);


			String id=data.getParameters().getString("id");
		        context.put("id",id);


			String seqno=data.getParameters().getString("seqno"); //////////////////
                        context.put("seqno",seqno); ////////////////////////
			//String idname=data.getParameters().getString("idname"); //////////////////
                        //context.put("idname",idname); ////////////////////////
			

			Calendar calendar=Calendar.getInstance();

			int cyear=calendar.get(Calendar.YEAR);
			String year=Integer.toString(cyear);	
			context.put("cyear",year);
			int cmonth=calendar.get(Calendar.MONTH);
			cmonth=cmonth + 1;
                	String month= Integer.toString(cmonth);
                	if(cmonth<10)
                        	month=0 + month;
                		int cday=calendar.get(Calendar.DATE);
           			//   String nday=Integer.toString(cday + 1);
                		String day= Integer.toString(cday);
                		if(cday<10)
                        		day=0+day;
                			int nyear,nmonth,nday;
                      			if (cmonth==12 && cday==31)
                      			{
                                		nyear=cyear +1;
                              			nmonth= 1;
                              			nday= 1;
                      			}
                      			else
                      			{	
                              			nyear=cyear;
			                        if(((cmonth==1|| cmonth==3|| cmonth==5||cmonth==7||cmonth==8||cmonth==10||cmonth==12) && (cday==31)) ||((cmonth==4||cmonth==6||cmonth==9||cmonth==11) && (cday==30))||((cmonth==2)&&(cday==28||cday==29))) {
							nmonth=cmonth + 1;
						        nday= 1;
						}
                              			else
                              			{
                                      			nyear=cyear;
                                      			nmonth=cmonth;
                                      			nday=cday + 1;
                              			}
                      			}
                				String nday1=Integer.toString(nday);
                				if(nday<10)
	                       				nday1=0+nday1;
               					String nmonth1=Integer.toString(nmonth);
				                if(nmonth<10)
        				                nmonth1=0+nmonth1;
				                String nyear1=Integer.toString(nyear );
				                context.put("cmonth",month);
				                context.put("cday",day);
			                context.put("nday",nday1);
		                context.put("nyear",nyear1);
		                context.put("nmonth",nmonth1);
                			//context.put("mode","insert");
		} //if((mode.equals("insert"))||(mode.equals("subtask")))
		
        	if(mode.equals("update") ||(mode.equals("move"))){
                	try {
                        String tid=data.getParameters().getString("id");
                        context.put("id",tid);
                        crit = new Criteria();
                        crit.addAscendingOrderByColumn(TaskPeer.SEQ_NO);
                        crit.add(TaskPeer.USER_ID,uid);
                        crit.add(TaskPeer.TASK_ID,tid);
                        List w = TaskPeer.doSelect(crit);
                        Vector sdate=(ExpiryUtil.getPostDate((((Task)w.get(0)).getStartDate()).toString()));
                        Vector edate=(ExpiryUtil.getPostDate((((Task)w.get(0)).getEndDate()).toString()));
                        context.put("usdate",sdate);
                        context.put("uedate",edate);
                        context.put("ulist",w);
                	} catch(Exception e){data.setMessage("The error in update"+e); }
		}
    	} //method
}

