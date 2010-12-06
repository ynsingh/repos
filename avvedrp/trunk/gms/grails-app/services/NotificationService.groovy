class NotificationService {
public List getAllNotifications(String subQuery,def PartyId)
{
		
				
		def notificationInstanceList = Notification.findAll("from Notification N where N.project.id in (select GA.projects.id from GrantAllocation GA where GA.party='"+PartyId+"')"+subQuery)
		
		return notificationInstanceList
	}

public List getNotificationCode(def notificationInstance)
 {
	def chkUniNotCodeInstance = Notification.findAll("from Notification N where N.notificationCode = '"+notificationInstance.notificationCode+"'")
	println"chkUniNotCodeInstance[0]"+chkUniNotCodeInstance
    return chkUniNotCodeInstance
 }
}

