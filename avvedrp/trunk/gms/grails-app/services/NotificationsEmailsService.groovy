import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class NotificationsEmailsService {

    boolean transactional = true
    //def mailService 
    def serviceMethod() {

    }
 //for change the status of party 
 //select the party id from party and compare with corresponding id in NotificationsEmails
 def savePartyStatus(def notificationId){
	 println "saveparty"
	 def notific = 2
	 def partyInstanceList =Party.findAll("from Party P  ")
	 println "party--"+partyInstanceList
	 for(e in partyInstanceList){
		def notificationInstance = NotificationsEmails.find("from NotificationsEmails NE where NE.notification="+notificationId+" and NE.party="+e.id)
 		def partyInstanceStatus = Party.get(e.id)
 		if (notificationInstance)
 		{
 			partyInstanceStatus.statusEmail = true
 			println "Notif--"+notificationInstance
 			
 		}
 		else{
 		partyInstanceStatus.statusEmail = false
 		}
 		partyInstanceStatus.save()
 	}
    	
    }
    //sending mail 
    public List contactUser(def message,def notificationId,def email,def fromEmailId)
    {
    	println "sendem--"+email  
    	println "from id"+fromEmailId
    	def emailList = []
    	 
    	email.each 
    	{  
    		def notificationsEmailsInstance = new NotificationsEmails()
    		def partyEmailId=it
    		def partyEmails = Party.find("from Party P where P.id="+partyEmailId)
    		println "partyemailsinner-"+partyEmails.email
    		if(partyEmails.email != null)
    		{
    			/*sendMail{
    				to partyEmails.email
    				from fromEmailId
    				subject "Hello"
    				body (view:"mail",model:[partyEmails:partyEmails,message:message],plugin:'email-confirmation')
    			}*/
    			println "mail send to "+partyEmails.email
    			println "notificationId="+notificationId
    			def p=Notification.find("from Notification N where id="+notificationId)
    			notificationsEmailsInstance.notification=p
    			notificationsEmailsInstance.party=partyEmails
    			notificationsEmailsInstance.save()
    			emailList.add(partyEmails.email)
    	  	}
    		else
    		{
    			println "emails null=="
    			//flash.message "Invalid emailaddress"
    			emailList.add(null)
    			//redirect(action:list)
    		}
    	}
    	return emailList
	}
    public List getAllParty()
    {
    	def partyInstanceList =Party.findAll("from Party P  ")
    	println "party from=="+partyInstanceList
    	
		return partyInstanceList
    }
    public List getNotificationsByParty(String PartyId)
    {	
    	println "Partyser="+PartyId
    	def notifictionsInstanceList = NotificationsEmails.findAll("from NotificationsEmails NE where NE.party="+PartyId)
    	println "Notificationservice=="+notifictionsInstanceList
    	return notifictionsInstanceList
    }
   
}
