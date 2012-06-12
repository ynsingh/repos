package org.iitk.brihaspati.modules.actions;
/*
 * @(#)Task_Action.java
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
 *  Contributors : members of ETRG, IIT Kanpur
 *
 */
import java.io.*;
import java.lang.*;
import java.util.List;
import java.sql.Date;
import java.util.Vector;
import java.util.Collections;

import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;

import org.apache.velocity.context.Context;
import org.apache.torque.util.Criteria;

import org.iitk.brihaspati.om.UserConfiguration;
import org.iitk.brihaspati.om.UserConfigurationPeer;
import org.iitk.brihaspati.om.Task;
import org.iitk.brihaspati.om.TaskPeer;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.modules.utils.ExpiryUtil;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.StringUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import java.util.LinkedList;

/**
*
*@author  <a href="nagendrakumarpal@yahoo.co.in">Nagendra kumar singh</a>
*@author  <a href="singh_jaivir@rediffmail.com">Jaivir Singh</a>
*@author  <a href="kalpanagtm@gmail.com">Kalpana Gautam</a>
*@author  <a href="gaurav.soni992@gmail.com">Gaurav Verma</a>
*
*/
public class Task_Action extends SecureAction
{
		

	
    /**
     * This method used for delete the task from database	
     * @param data Rundata
     * @param context Context
     * @exception genric exception
     */

	 public void doDelete(RunData data, Context context) throws Exception
	 {
		/**
                * Code for getting the  task id, sequence number, psequence and depth form the Form/Database
                * Getting user_id from the userUtil an the behalf of userName.
                */

                User user = data.getUser();
		String uname=user.getName();
                int uid=UserUtil.getUID(uname);
                String LangFile=(String)user.getTemp("LangFile");
		ParameterParser pp=data.getParameters();
                String d=pp.getString("id");
		String status=pp.getString("mode","");
                int tid=Integer.parseInt(d);
                int seqno=Integer.parseInt(data.getParameters().getString("seqno"));
                int pseqno=Integer.parseInt(data.getParameters().getString("pseqno"));
                int depth=Integer.parseInt(data.getParameters().getString("depth"));
		String d1=pp.getString("ptid");
                int ptid1=Integer.parseInt(d1);
		Criteria crit = new Criteria();

		/**
		* Code for getting the Task list which Parient_id is same
		* before deleting the Task
		*/ 

		crit = new Criteria();
                crit.add(TaskPeer.PARENT_TASK_ID,ptid1);
                crit.addAscendingOrderByColumn(TaskPeer.SEQ_NO);
                List w4 = TaskPeer.doSelect(crit);

		/**
		* Code for delete task with their child
		* 1 -Select the rows which Parent_id is equal to task_id
		* 2 -Select the task_id from  value (1)
		* 3 -Repeate (1) for each task_id collected by (2)
		* and get all the connected Tasks
		*/

		if(status.equals("deleteall"))
		{
       		String dtid=data.getParameters().getString("id");
                LinkedList li1=new LinkedList(); 
                LinkedList Reli2=new LinkedList(); 
                LinkedList temp=new LinkedList();
                LinkedList li=new LinkedList();
                Vector spacevector=new Vector();
                crit = new Criteria();
                crit.add(TaskPeer.USER_ID,uid);
                crit.addAscendingOrderByColumn(TaskPeer.TASK_ID);
                List v1 = TaskPeer.doSelect(crit);
                for(int i=0;i<v1.size();i++)
                {
                        li1.add(((Task)v1.get(i)).getTaskId());//TaskId
                        Reli2.add(((Task)v1.get(i)).getParentTaskId());//ParentTaskId
                }//for1
                Object tem=new Object();
                int id=0;
                boolean flag=false;
                Vector all=new Vector();
                while(Reli2.size()!=0)
                {
                        int i=0;
                        tem=li1.get(i);
                        li.add(tem);
                        temp.add(tem);
                        if(flag)
                        break;
                        if(tem.toString().equals(dtid))
                        {
                               all.add(tem);
                               flag=true;
                        }
                        Reli2.remove(i);
                        li1.remove(i);
                        while(temp.size()!=0)
                        {
                              if(Reli2.contains(tem))
                              {
                                      id=Reli2.indexOf(tem);
                                      tem=li1.get(id);
                                      li.add(tem);
                                      temp.add(tem);
                                      if(tem.toString().equals(dtid))
                                      flag=true;
                                      if(flag)
                                      all.add(tem);
                                      Reli2.remove(id);
                                      li1.remove(id);
                              }
                              else
                              {
                                      id=temp.indexOf(tem);
                                      Object arv=temp.get(id);
                                      if(arv.toString().equals(dtid))
                                      break;
                                      temp.remove(id);
                                      if(temp.size()!=0)
                                      {
                                               id=id-1;
                                               tem=temp.get(id);
                                      }
                               }
                          }
                     }
                     for(int i=0;i<all.size();i++)
                     {
                          Object ttask=all.get(i);
                          int ii = Integer.valueOf(String.valueOf(ttask));
                          crit = new Criteria();
                          crit.add(TaskPeer.TASK_ID,ii);
                          TaskPeer.doDelete(crit);
                     }

          }

	/**
	* Code for delete one Task, and Update subTask's Parent_id if subTask Exist.
	*/
		
	else if(status.equals("deleteone"))
	{
	    crit = new Criteria();
	    crit.add(TaskPeer.TASK_ID,tid);
	    crit.add(TaskPeer.SEQ_NO,seqno);
            List w = TaskPeer.doSelect(crit);
	    for(int i=0;i<w.size();i++)
            {
                try
		 {
                        int parentid =((Task)w.get(i)).getParentTaskId();
                        String information="UPDATE TASK SET PARENT_TASK_ID='"+parentid +"' WHERE PARENT_TASK_ID="+tid;
                        TaskPeer.executeStatement(information);
                 }
		catch(Exception e){}
           }
          w.clear();
	  crit=null;
          crit = new Criteria();
          crit.add(TaskPeer.TASK_ID,tid);
	  crit.add(TaskPeer.SEQ_NO,seqno);
          w = TaskPeer.doSelect(crit);
          TaskPeer.doDelete(crit);
	  w.clear();
	}

	/**
	* List the Tasks which Parent_Task_id is equal to This Task_id after delete this one Task.
       	*/

	crit = new Criteria();
	crit.add(TaskPeer.PARENT_TASK_ID,ptid1);
	crit.addAscendingOrderByColumn(TaskPeer.SEQ_NO);
	List w1 = TaskPeer.doSelect(crit);

	/**
        *Code for update the task, Sequence Number, Depth, and Psequence Nunber.
        */

	int firstseqno=((Task)w4.get(0)).getSeqNo();
	int fpsqno = ((Task)w4.get(0)).getPseqNo();
	for(int k =0;k<w1.size();k++)
	{
		int dep = ((Task)w1.get(k)).getTaskId();
		String information3 ="UPDATE TASK SET DEPTH='"+w1.size()+"' WHERE TASK_ID="+dep;
        	TaskPeer.executeStatement(information3);
	}
	for(int i =0; i<w1.size();i++)
	{
		try
		{
		int taskid =((Task)w1.get(i)).getTaskId();
		String information="UPDATE TASK SET SEQ_NO='"+firstseqno+"' WHERE TASK_ID="+taskid;
		TaskPeer.executeStatement(information);
                firstseqno++;
		String information1="UPDATE TASK SET PSEQ_NO='"+fpsqno+"' WHERE TASK_ID="+taskid;
                TaskPeer.executeStatement(information1);
                fpsqno++;
		}
		catch(Exception e){}
	}
               String msg1=MultilingualUtil.ConvertedString("Task_msg6",LangFile);
               data.setMessage(msg1);
 }

   	/**
     	 * This method used for insert the task into database	
     	 * @param data Rundata
     	 * @param context Context
     	 * @exception genric exception
	 */







public void doInsert(RunData data, Context context) throws Exception
	{
		try
		{
			/**
			* Get all the following value from the form- Title, Start_Date, End_date, Parent_Task_id and  mode
         		* Get current Date from the system,
		        * Get user_id from the userUtil an the behalf of userName.
			*/

			Vector v3=new Vector();
		        User user=data.getUser();
			String LangFile=(String)user.getTemp("LangFile");
	        	String UserName=user.getName();
        		ParameterParser pp=data.getParameters();
			String status=pp.getString("mode","");
			String exp=pp.getString("et","");
	       		String detailinfo1=pp.getString("title");
       			String subid=pp.getString("Subid","");
	        	String detailinfo=StringUtil.replaceXmlSpecialCharacters(detailinfo1);
        		String sd=pp.getString("Start_day");
	        	String sm=pp.getString("Start_mon");
	        	String sy=pp.getString("Start_year");
		        String sti="";
        		sti= sti + sy + sm + sd;
		        int sti1=Integer.parseInt(sti);
        		String sdate= sy + "-" + sm + "-" + sd;
	        	String ed=pp.getString("End_day");
	        	String em=pp.getString("End_mon");
		        String ey=pp.getString("End_year");
        		String edate= ey + "-"  + em + "-" + ed;
			context.put("edate",edate);
        		String et="";
	        	et= et + ey + em + ed;
	        	int et1=Integer.parseInt(et);
		        if (et1 <= sti1)
        		 {
				String msg1=MultilingualUtil.ConvertedString("Task_msg5",LangFile);
			        data.setMessage(msg1);
        		        setTemplate(data,"call,Task_Mgmt,TaskDIUD.vm");
                		return;
		         }
		       int userid=UserUtil.getUID(UserName);
		       String stdate=ExpiryUtil.getCurrentDate("-");
		       String currd=stdate.replace("-","");
		       int currentdate=Integer.parseInt(currd);
			
			/**
			* Store the information according userid
			*/

	               Criteria crit = new Criteria();
		       String msg1="";

			/**
			* This code is for Update (Edit) the Task.
			*/ 

		       if(status.equals("update"))
			{
		       		if(sti1 >= currentdate)
				{
				       crit = new Criteria();
			               int key1=pp.getInt("id");
				       context.put("key1",key1);
				       crit = new Criteria();
        			       crit.add(TaskPeer.TASK_ID,key1);
			               List l=TaskPeer.doSelect(crit);
        			       int STATUS=0;
			               for(int i=0;i<l.size();i++)
               				 {
        	        			STATUS=((Task)l.get(i)).getStatus();
					 }
		                        String information="";
                			if(STATUS==1)
                        		{
		                		String dday=pp.getString("dday","");
	                		    	information="UPDATE TASK SET TITLE='"+detailinfo+"',START_DATE='"+Date.valueOf(sdate)+"',END_DATE='"+Date.valueOf(edate)+"',EXPIRY='"+exp+"',EXPIRY_DATE='"+Date.valueOf(ExpiryUtil.getExpired(edate,Integer.parseInt(exp)))+"',DUE_DAYS='"+dday+"',DUE_DATE='"+Date.valueOf(ExpiryUtil.getExpired(stdate,Integer.parseInt(dday)))+"' WHERE TASK_ID="+key1;
		       			}
                			else
	                		{
        	            			information="UPDATE TASK SET TITLE='"+detailinfo+"',START_DATE='"+Date.valueOf(sdate)+"',END_DATE='"+Date.valueOf(edate)+"',EXPIRY='"+exp+"',EXPIRY_DATE='"+Date.valueOf(ExpiryUtil.getExpired(edate,Integer.parseInt(exp)))+"' WHERE TASK_ID="+key1;
                       			}
					TaskPeer.executeStatement(information);
					msg1=MultilingualUtil.ConvertedString("Task_msg4",LangFile);
        				data.setMessage(msg1);
	                        }
               		      else
	                        msg1=MultilingualUtil.ConvertedString("Task_msg7",LangFile);
        	                data.setMessage(msg1);

             		}
	  		else
	    		{
			if(sti1 >= currentdate)
				{
					List w=null;
					List y=null;
					List zz=null;
					int sub_id=Integer.parseInt(subid);	
					String psubid=Integer.toString(sub_id);
					crit.add(TaskPeer.USER_ID,userid);
					ParameterParser aa=data.getParameters();
                                        String ww=aa.getString("seqno");

					/**
					* Code For getting the Task list which Parent_Task_id is same according to this task.
					*/

					crit = new Criteria();
        		                crit.add(TaskPeer.PARENT_TASK_ID,sub_id);
                		        y = TaskPeer.doSelect(crit);

					/**
					* Code For adding Sequence Numger for Task or SubTask.
					*/

					crit = new Criteria();
					for(int i=0;i<y.size();i++)
                                        {
                                               int maxsqno=((Task)y.get(i)).getSeqNo();
                                               v3.add(maxsqno);
                                        }

					/**
					* Sequence Number for Root Task.
					*/

					if(y.size()!=0)
					{
						 Object mseqno = Collections.max(v3);
		                                 int ii = Integer.valueOf(String.valueOf(mseqno));
		                                 crit.add(TaskPeer.SEQ_NO,ii+1);
					}

					/**
					* Sequence Numger for subTask.
					*/

					else
					{
					if (status.equals("subtask"))
					{
						int subsize = y.size() + 1;
						int subseq = Integer.parseInt(ww+""+subsize);
						crit.add(TaskPeer.SEQ_NO,subseq);
					}
					else
					{		
						crit.add(TaskPeer.SEQ_NO,y.size()+1);
					}
				}
				crit.add(TaskPeer.PARENT_TASK_ID,psubid);
				crit.addAscendingOrderByColumn(TaskPeer.PARENT_TASK_ID);//,psubid);
				crit.add(TaskPeer.TITLE,detailinfo);
				crit.add(TaskPeer.START_DATE,Date.valueOf(sdate));
				crit.add(TaskPeer.END_DATE,Date.valueOf(edate));
				crit.add(TaskPeer.EXPIRY,exp);
				crit.add(TaskPeer.EXPIRY_DATE,Date.valueOf(ExpiryUtil.getExpired(edate,Integer.parseInt(exp))));
				crit.add(TaskPeer.USER_ID,userid);
				crit.add(TaskPeer.STATUS,null);
				crit.add(TaskPeer.DUE_DATE,Date.valueOf(sdate));
					
				/**
				* Code for adding Psequence Number and Depth.
				*/

				if (status.equals("subtask"))
                                ErrorDumpUtil.ErrorLog("SEQNOseqno===subtask mode============++++++++++"+ww);
                                                {

                                                        crit.add(TaskPeer.PSEQ_NO,y.size()+1);
                                                        crit.add(TaskPeer.DEPTH,y.size()+1);
                                                        for(int i= 0;i<y.size();i++)
                                                        {
                                                        int jj = y.size()+1;
                                                        int stid =((Task)y.get(i)).getTaskId();
                                                        String information ="UPDATE TASK SET DEPTH='"+jj+"' WHERE TASK_ID="+stid;
                                                        TaskPeer.executeStatement(information);
                                                        }
                                                }
                                        TaskPeer.doInsert(crit);
                                        msg1=MultilingualUtil.ConvertedString("Task_msg3",LangFile);
                                        data.setMessage(msg1);
                             }
                  else
               	   msg1=MultilingualUtil.ConvertedString("Task_msg7",LangFile);
	           data.setMessage(msg1);
	}
	}
	catch(Exception e)
	{	
		data.addMessage("The error is do Insert "+e);

	}
}

/**
* This method used for Up and down the task into database       
* @param data Rundata
* @param context Context
* @exception genric exception
*/

public void doUp(RunData data, Context context,String up_down) throws Exception
{
	/**
	* Getting the following detail from the Form/Database-
	* Task_id, Parent_Task_id,User_id, Sequence Number and Psequence Number.
	*/

        int seqno=Integer.parseInt(data.getParameters().getString("seqno"));
        int pseqno=Integer.parseInt(data.getParameters().getString("pseqno"));
        Criteria crit = new Criteria();

	/**
	* Getting the list of all the tasks.
	*/

        crit.addAscendingOrderByColumn(TaskPeer.SEQ_NO);
        crit.addAscendingOrderByColumn(TaskPeer.PSEQ_NO);
        crit.add(TaskPeer.USER_ID,UserUtil.getUID(data.getUser().getName()));
        List w = TaskPeer.doSelect(crit);
	ParameterParser pp=data.getParameters();
        String d=pp.getString("id");
        int tid=Integer.parseInt(d);
        String d1=pp.getString("ptid");
        int ptid1=Integer.parseInt(d1);

	/**
        * Getting the list of all the tasks which Parent_Task_id is equal to This Parent_Task_Id
	* for the same user_id.
        */

        crit = new Criteria();
        crit.add(TaskPeer.PARENT_TASK_ID,ptid1);
        List w1 = TaskPeer.doSelect(crit);
        int j=1;

        /**
        * Code for up the task according to seqno for same user_id
	* and update the Sequence Number and psequence Number  
        */

        if(up_down.equals("up"))
	{
		for(int i=0;i<w.size();i++)
		{
			int iseq2 =((Task)w.get(i)).getSeqNo();
			if((seqno-1) == iseq2)
			{
				int tid4 = ((Task)w.get(i)).getTaskId();
				int seq2 = ((Task)w.get(i)).getSeqNo();
				String information="UPDATE TASK SET SEQ_NO='"+seqno+"' WHERE TASK_ID="+tid4;
                                TaskPeer.executeStatement(information);
                                String information2="UPDATE TASK SET SEQ_NO='"+seq2+"' WHERE TASK_ID="+tid;
                                TaskPeer.executeStatement(information2);
			}
		}
		for(int k =0;k<w1.size();k++)
		{
			int ipseq =((Task)w1.get(k)).getPseqNo();
			if((pseqno-1) == ipseq)
			{
				int tid5 = ((Task)w1.get(k)).getTaskId();
				int pseq2 =((Task)w1.get(k)).getPseqNo();
				String information3="UPDATE TASK SET PSEQ_NO='"+pseqno+"' WHERE TASK_ID="+tid5;
       	                        TaskPeer.executeStatement(information3);
				String information4="UPDATE TASK SET PSEQ_NO='"+pseq2+"' WHERE TASK_ID="+tid;
                                TaskPeer.executeStatement(information4);
			}
                }
        }
	else
	{
		/**
         	* Code for down the task according to seqno for same user_id
	        * and update the Sequence Number and psequence Number
                */
		for(int i=0;i<w.size();i++)
		{
		     int iseq4 =((Task)w.get(i)).getSeqNo();
                     if((seqno+1) == iseq4)
		     {
			         int tid5 = ((Task)w.get(i)).getTaskId();
                                 int seq4 = ((Task)w.get(i)).getSeqNo();
				 String information="UPDATE TASK SET SEQ_NO='"+seqno+"' WHERE TASK_ID="+tid5;
                                 TaskPeer.executeStatement(information);
                                 String information2="UPDATE TASK SET SEQ_NO='"+seq4+"' WHERE TASK_ID="+tid;
                                 TaskPeer.executeStatement(information2);
	            }
	      }
             for(int k =0;k<w1.size();k++)
             {
                    int ipseq =((Task)w1.get(k)).getPseqNo();
                    if((pseqno+1) == ipseq)
                    {
                                int tid5 = ((Task)w1.get(k)).getTaskId();
                                int pseq2 =((Task)w1.get(k)).getPseqNo();
                                String information3="UPDATE TASK SET PSEQ_NO='"+pseqno+"' WHERE TASK_ID="+tid5;
                                TaskPeer.executeStatement(information3);
                                String information4="UPDATE TASK SET PSEQ_NO='"+pseq2+"' WHERE TASK_ID="+tid;
                                TaskPeer.executeStatement(information4);
                    }
              }
        }
 }
	
/**
* This method move the task from list to current list
* @param data Rundata
* @param context Context
* @exception genric exception
*/

public void doMove(RunData data, Context context) throws Exception
{
	User user = data.getUser();
	String uname=user.getName();
       	int uid=UserUtil.getUID(uname);
        String LangFile=(String)user.getTemp("LangFile");
       	String tid=data.getParameters().getString("id");
	LinkedList li1=new LinkedList(); 
      	LinkedList Reli2=new LinkedList(); 
	LinkedList temp=new LinkedList();
	LinkedList li=new LinkedList();

	/**
	* Getting the list of all the task for same user_id
	*/

	Vector spacevector=new Vector();	
	Criteria crit = new Criteria();		
	crit.add(TaskPeer.USER_ID,uid);
       	crit.addAscendingOrderByColumn(TaskPeer.TASK_ID);
       	List v1 = TaskPeer.doSelect(crit);

	/**
	* 1- Select the rows which Parent_id is equal to task_id for same user_id
        * 2- Select the task_id from  value (1)
        * 3- Repeate (1) for each task_id collected by (2)
        * 4- and get all the connected Tasks
	* and Update Their Status 1
        */

	for(int i=0;i<v1.size();i++)
	{
		li1.add(((Task)v1.get(i)).getTaskId());
	        Reli2.add(((Task)v1.get(i)).getParentTaskId());
        }
	Object tem=new Object();
	int id=0;
	boolean flag=false;
	Vector all=new Vector();
	while(Reli2.size()!=0)
	{
        	int i=0;
               	tem=li1.get(i);
	       	li.add(tem);
       	        temp.add(tem);
		if(flag)
		break;
		if(tem.toString().equals(tid))
		{
			all.add(tem);
			flag=true;
		}
                        Reli2.remove(i);
	                li1.remove(i);
        	       	while(temp.size()!=0)
		{
			if(Reli2.contains(tem))
			{
	                       id=Reli2.indexOf(tem);
        	               tem=li1.get(id);
                	       li.add(tem);
	                       temp.add(tem);
			       if(tem.toString().equals(tid))
                               flag=true;
		               if(flag)
		               all.add(tem);
        	               Reli2.remove(id);
                	       li1.remove(id);
	        	}
			else
			{
			       id=temp.indexOf(tem);
			       Object arv=temp.get(id);
			       if(arv.toString().equals(tid))
			       break;
			       temp.remove(id);
		               if(temp.size()!=0)
				{
        			       id=id-1;
	                       	       tem=temp.get(id);
        	       		}
              		}
		}
	}
		crit = new Criteria();
                crit.add(UserConfigurationPeer.USER_ID,uid);
               	List w = UserConfigurationPeer.doSelect(crit);
	        int tcd=0;
                for(int k=0;k< w.size();k++)
       {
                UserConfiguration uc=(UserConfiguration)w.get(k);
                tcd=uc.getTaskConfiguration();
       }
       	        String tcdays=Integer.toString(tcd);
               	String cdate=ExpiryUtil.getCurrentDate("-");
       	        String mvd=Date.valueOf(ExpiryUtil.getExpired(cdate,Integer.parseInt(tcdays))).toString();
                String rmvd=mvd.replaceAll("-","");
               	int seqno=Integer.parseInt(data.getParameters().getString("seqno"));
                int mvdate=Integer.parseInt(rmvd);
		crit=new Criteria();
                crit.add(TaskPeer.USER_ID,uid);
		List lst=TaskPeer.doSelect(crit);
		String exd="";
               if(all.size() > 0 )
		{
                        for(int j=0;j<all.size();j++)
                        {
                                String eee=all.get(j).toString();
                                for(int l=0;l<lst.size();l++)
                                {
                                       String aas=Integer.toString(((Task)(lst.get(l))).getTaskId());
                                       if(eee.equals(aas))
                                       {
                                              exd=((Task)(lst.get(0))).getExpiryDate().toString();
                                              String sbst=exd.substring(0,10).replaceAll("-","");
                                              int expdt=Integer.parseInt(sbst);
                                              int diffr=mvdate-expdt;
				              crit = new Criteria();
                			      crit.add(TaskPeer.TASK_ID,eee);
		                	      crit.add(TaskPeer.STATUS,"1");
                			      List v=TaskPeer.doSelect(crit);
                		              int info1=0;
					      String msg1="";
					      if(v.size() > 0 )
					      {
						     	msg1=MultilingualUtil.ConvertedString("Task_msg2",LangFile);
			                        	data.setMessage(msg1);
							setTemplate(data,"call,Task_Mgmt,TaskDIUD.vm");
					                return;
					      }
					     else
			                      {
                        		     if(diffr<1)
							{
					  			String information="UPDATE TASK SET STATUS=1,DUE_DAYS='"+tcdays+"',DUE_DATE='"+Date.valueOf(ExpiryUtil.getExpired(cdate,Integer.parseInt(tcdays)))+"' WHERE TASK_ID="+eee;
                                                		TaskPeer.executeStatement(information);
		                                                msg1=MultilingualUtil.ConvertedString("Task_msg1",LangFile);
                		                                data.setMessage(msg1);
							}
					 		else
							{
	                                		msg1=MultilingualUtil.ConvertedString("Task_msg",LangFile);
        	                        		data.setMessage(msg1);
                	        			}
						}
					}
				}
   	            }
	    }

    }

/**
* Default action to perform if the specified action cannot be executed.
* @param data RunData
* @param context Context
*/

public void doPerform(RunData data,Context context) throws Exception 
    {
	User user=data.getUser();
       	String LangFile=(String)user.getTemp("LangFile");
        String action=data.getParameters().getString("actionName","");
	context.put("action",action);
        if(action.equals("eventSubmit_doInsert"))
             doInsert(data,context);
        else if(action.equals("eventSubmit_doDelete"))
             doDelete(data,context);
        else if(action.equals("eventSubmit_doUpdate"))
             doInsert(data,context);
        else if(action.equals("eventSubmit_doMove"))
             doMove(data,context);
	else if(action.equals("eventSubmit_doUp"))
             doUp(data,context,"up");
	else if(action.equals("eventSubmit_doDown"))
	     doUp(data,context,"down");
        else{
                 String msg1=MultilingualUtil.ConvertedString("action_msg",LangFile);
	         data.setMessage(msg1);
	    }
     }
}//class




