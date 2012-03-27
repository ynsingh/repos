package aell

import java.util.Date;


class LogService 
{
	boolean transactional = true

    def getUserDetails(params,session) 
	{
      if(params.subjectId!=null && params.exp!=null && params.cnt!=null)
	  {
		  def subid= params.subjectId 
		  def contentid=params.cnt
		  def expid=params.exp
		  def userid=session.user.id
		  def userLogInstanceDetails=AvlUserLoginDetails.find("from AvlUserLoginDetails where logoutStatus='E' and userId='"+userid+"'")
		  def sessioncount=userLogInstanceDetails.sessionCount
		  def userTopicInstanceDetails=AvlExperimentSubtopicRel.find("from AvlExperimentSubtopicRel where experiment_id='"+expid+"'")
     	  def topicid=userTopicInstanceDetails.sub_topic.id
		  def time= new Date()
		  
		  //to make the status of tabs to E, if any, in previous sessions of the user if he doesnot logout
		  def userpastlogInstanceList=AvlLogDetails.findAll("from AvlLogDetails where userId='"+userid+"' and  status='A'")
		  if(userpastlogInstanceList)
		  {
			  //if the entries exist then change the staus to 'E'
			  def flag=0;
			  for(int i=0;i<userpastlogInstanceList.size();i++)
			  {
				  if(userpastlogInstanceList[i].sessionCount!=sessioncount)
				  {
					  flag=1
					  userpastlogInstanceList[i].status='E'
					  def accesstime2=new Date();
					  Date date1 = time
					  Date date2 = accesstime2;
					  def duration =getTimeDuration(date1,date2)
					  userpastlogInstanceList[i].Duration=duration
				  }
			  }
		  }
		  
		  //to make the status to Exit(E) for the tabs already accessed in a particular session
		  def userstatusInstance=AvlLogDetails.find("from AvlLogDetails where userId='"+userid+"' and sessionCount='"+sessioncount+"' and  status='A'")
		  if(userstatusInstance)
		  {
			  userstatusInstance.status='E'
		  }
		  def AvlLogDetailsInstance=new AvlLogDetails()
		  AvlLogDetailsInstance.userId = userid
		  AvlLogDetailsInstance.sessionCount= sessioncount
		  AvlLogDetailsInstance.subjectId = subid
		  AvlLogDetailsInstance.topicId= topicid
		  AvlLogDetailsInstance.experimentId = expid
		  AvlLogDetailsInstance.contentTypeId= contentid
		  AvlLogDetailsInstance.accessTime=time
		  AvlLogDetailsInstance.Duration=0
		  AvlLogDetailsInstance.status='A'
		  //AvlLogDetailsInstance.endTime=null
		  AvlLogDetailsInstance.save()
		  
		  //For duration
		  //query to get the tab which is Active
		  def usercurrentlogInstance=AvlLogDetails.find("from AvlLogDetails where userId='"+userid+"' and sessionCount='"+ sessioncount+"' and  status='A'")
		  Date accesstime1=usercurrentlogInstance.accessTime
		  //Query to get the details of last accessed tab
		  def userlastlogInstance=AvlLogDetails.find("from AvlLogDetails where status='E' ORDER BY accessTime DESC")
		  if(userlastlogInstance)
		  {
			  Date accesstime2=userlastlogInstance.accessTime
			  //userlastlogInstance.endTime=accesstime1
			  def duration=getTimeDuration(accesstime1,accesstime2)
			  userlastlogInstance.Duration=duration
		  }
	  }
	    
	}

	def getTimeDuration(Date accesstime1,Date accesstime2)
	{
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(accesstime1);
		calendar2.setTime(accesstime2);
		long milliseconds1 = calendar1.getTimeInMillis();
		long milliseconds2 = calendar2.getTimeInMillis();
		long diff = milliseconds1 - milliseconds2;
		float diffSeconds = diff / 1000;
		int diffSecondsRound=Math.round(diffSeconds)
		def duration =diffSecondsRound;
		return duration
	}
}

//============================================================================================================================
		  //First code
		  //to check only one time the tab is stored in session
		  /*def checkfirsttabInstance=AvlLogDetails.find("from AvlLogDetails where experimentId='"+expid+"' and contentTypeId='"+contentid+"' and sessionCount='"+sessioncount+"'")
		  if(checkfirsttabInstance)
		  {
		  }
		  else
		  {
			  def AvlLogDetailsInstance=new AvlLogDetails()
			  AvlLogDetailsInstance.userId = userid
			  AvlLogDetailsInstance.sessionCount= sessioncount
			  AvlLogDetailsInstance.subjectId = subid
			  AvlLogDetailsInstance.topicId= topicid
			  AvlLogDetailsInstance.experimentId = expid
			  AvlLogDetailsInstance.contentTypeId= contentid
			  AvlLogDetailsInstance.accessTime=time
			  AvlLogDetailsInstance.Duration=0
			  AvlLogDetailsInstance.status='A'
			  AvlLogDetailsInstance.save()
		  }*/
//===========================================================================================================================

