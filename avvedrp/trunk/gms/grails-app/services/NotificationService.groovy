class NotificationService {
public List getAllNotifications(String subQuery,def PartyId){
		
				
		def notificationInstanceList = Notification.findAll("from Notification N where N.project.id in (select GA.projects.id from GrantAllocation GA where GA.party='"+PartyId+"')"+subQuery)
		
		return notificationInstanceList
	}
}
