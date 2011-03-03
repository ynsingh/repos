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
public List getAllPublishedNotification(def partyId)
{
	def notificationInstanceList = Notification.findAll("from Notification N where N.publishYesNo='Y'")
	return notificationInstanceList
}
/*
 * method to get all public notification 
 */
public List getAllPublicAndPublishedNotification()
{
	def notificationInstanceList = Notification.findAll("from Notification N where N.publishYesNo='Y' and N.publicYesNo='Y'")
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

}

