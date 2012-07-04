import java.util.*;
import java.text.*;
import grails.converters.*
import java.lang.*

class NotificationService 
{
public List getAllNotifications(String subQuery,def PartyId)
{
		
				
		def notificationInstanceList = Notification.findAll("from Notification N where N.party.id ="+PartyId+" "+subQuery)
		
		return notificationInstanceList
	}

public List getNotificationCode(def notificationInstance)
 {
	def chkUniNotCodeInstance = Notification.findAll("from Notification N where N.notificationCode = '"+notificationInstance.notificationCode+"'")
	println"chkUniNotCodeInstance[0]"+chkUniNotCodeInstance
    return chkUniNotCodeInstance
 }
/*
 * method to get notification by id
 */
public Notification getNotificationById(def id)
{
	def notificationInstance = Notification.get(id)
	return notificationInstance
}
/*
 * method to update notification 
 */
public Notification updateNotification(def notificationInstance)
{
	if(notificationInstance.save())
	{
		
	}
	else
	{
		notificationInstance=null
	}
	return notificationInstance
}
/*
 * method to get all published notification 
 */
public List getAllPublishedNotification(def partyId,def currentDate)
{println"########################currentDate################"+currentDate
	def notificationInstanceList = Notification.findAll("from Notification N where N.publishYesNo='Y' and DATE_FORMAT(N.proposalSubmissionLastDate, '%Y-%m-%d')>='"+currentDate+"'")
	return notificationInstanceList
}
/*
 * method to get all public notification 
 */
public List getAllPublicAndPublishedNotification(def currentDate)
{
	println"+++++++++++++++++++currentDate+++++++++++++++++++++"+currentDate
	
	def notificationInstanceList = Notification.findAll("from Notification N where N.publishYesNo='Y' and N.publicYesNo='Y' and DATE_FORMAT(N.proposalSubmissionLastDate, '%Y-%m-%d')>='"+currentDate+"' ORDER BY N.notificationDate DESC") 
	return notificationInstanceList
}

/*
 * method to get all notification based on party 
 */

 public List getAllNotificationUnderAParty(def partyId)
 {
 	def notificationInstanceList = Notification.findAll("from Notification N where N.party.id= "+partyId)
 	return notificationInstanceList
 }

 /*
  * method to get  all public notification  based on party 
  */

  public List getAllPublicNotificationUnderAParty(def partyId)
  {
  	def notificationInstanceList = Notification.findAll("from Notification N where N.publicYesNo='Y'and N.party.id= "+partyId)
  	return notificationInstanceList
  }
  
  
  
public int isnew(def notificationDate, def currentDate)
	{
	
	
	Date ndate = new Date().parse("yyyy-MM-dd", notificationDate)
	Date cdate = new Date().parse("yyyy-MM-dd", currentDate)
	def difference =cdate-ndate ;
	def gmsSettingInstance= GmsSettings.find("from GmsSettings GS where GS.name='"+"limit_days"+"'")
	def limit=Integer.parseInt(gmsSettingInstance.value);
	
	if(difference < limit){return 1;}
	else {return 0 ;}
	}
	/*
	* method to search notification
	*/
   def serachNotificationList(def query,def params){
	   def criteria = Notification.createCriteria()
	   def notificationInstanceList = criteria {
				   if(query.title){
					   like('notificationTitle', '%' + query.title + '%')
				   }
				   if(query.minDate1)
				   {
						ge("notificationDate",query.minDate1)
				   }
				   if(query.maxDate1)
				   {
					   le("notificationDate",query.maxDate1)
				   }
				   if(query.minDate)
				   {
						ge("proposalSubmissionLastDate",query.minDate)
				   }
				   if(query.maxDate)
				   {
					   le("proposalSubmissionLastDate",query.maxDate)
				   }
				  
				   eq("publicYesNo","Y")
				   eq("publishYesNo","Y")
				   ge("proposalSubmissionLastDate",new Date())
				   if(params?.sort){
					   order(params?.sort,params?.order)
				   }
				   else{
					   order("notificationDate","desc")
				   }
	   }
	   return notificationInstanceList
   }
  

 
}

