import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class ProposalService {

   def userService
   def notificationsEmailsService
	/*
    * Method to list all proposals
    */
   public List getProposalList(def proposalInstance,def partyId)
   {
	   def proposalInstanceList = Proposal
	   									.findAll("from Proposal P where P.notification.id = " 
	   											+ proposalInstance.notification.id+" and P.party.id="+partyId );
	  return proposalInstanceList;
   }
   public List getProposalByNotification(def notificationId)
   {
	   def proposalInstanceList = Proposal.findAll("from Proposal P where P.notification.id="+notificationId+" and P.lockedYN='N'")
	   return proposalInstanceList
   }
   /*
    * method to get Proposal using notification and party id
    */
    public Proposal getProposalByNotificationAndParty(def notification,def party)
   {
    	def proposalInstance = Proposal.find("from Proposal P where P.notification="+notification+" and P.party="+party)
    	return proposalInstance
   }
   /*
    * method to save proposal application form details 
    */
   public def saveformDetails(def params,def userId,def proposalId,def urlPath)
   {
	   def proposalApplicationInstance=new ProposalApplication()
		def emailId
		def saveStatusList = [saveFormStatus:true,controllerIdMail:false]
		boolean mailStatus = true
		String mailMessage="";
		Set keyList=params.keySet()
		
		def userInstance = userService.getUserById((Integer)userId)
		Iterator itr=keyList.iterator()
		def proposalApplicationInstanceDb = ProposalApplication.find("from ProposalApplication PA where PA.proposal="+proposalId)
		if(proposalApplicationInstanceDb)
		{
			proposalApplicationInstance=proposalApplicationInstanceDb
		}
		else
		{
			proposalApplicationInstance.proposal=Proposal.get(proposalId)
			proposalApplicationInstance.controllerId=proposalApplicationInstance.proposal.code+proposalApplicationInstance.proposal.party.id
			mailMessage="Dear "+userInstance.userRealName+", \n \n ";
		    mailMessage+="\n \n Proposal Controll Id has generated \nProposal Controll Id    : "+proposalApplicationInstance.controllerId+"\n";
		    mailMessage+="\n \n Please use the controller Id to edit the proposal"
		    mailMessage+="\n \n \n To Login   \t "+urlPath;
		   	if(userInstance.email)
		    {
		    	mailStatus=notificationsEmailsService.sendMessage(userInstance.email,mailMessage)
		    	saveStatusList.controllerIdMail=true
		    }
			else
			{
				mailStatus=false
			}
		}
							
		proposalApplicationInstance.applicationSubmitDate=new Date()
				
       proposalApplicationInstance.save()
		
	
		if((mailStatus == true))
		{
			
		if(proposalApplicationInstance.save())
		{
			while(itr.hasNext())
			{
				Object obj=itr.next()
				if(obj.toString() !="action")
				{
					if(obj.toString()!="controller")
					{
						def proposalApplicationExtInstance=new ProposalApplicationExt()
						def proposalApplicationExtInstanceDb = ProposalApplicationExt.find("from ProposalApplicationExt PE where PE.proposalApplication="+proposalApplicationInstance.id+" and PE.field='"+obj+"'") 
			
						if(proposalApplicationExtInstanceDb)
						{
							proposalApplicationExtInstance=proposalApplicationExtInstanceDb
						}
						proposalApplicationExtInstance.proposalApplication=proposalApplicationInstance
						proposalApplicationExtInstance.field=obj.toString()
						proposalApplicationExtInstance.value=params.get(obj).toString()
						proposalApplicationExtInstance.save()
			
						
						saveStatusList.saveFormStatus=true
					}
				}
			}
		}
		else
		{
			saveStatusList.saveFormStatus=false
		}
		}
		else
		{
			saveStatusList.saveFormStatus=false
		}
		
		return saveStatusList
   }
   /*
    * method to get proposalApplication Instance by proposalId
    */
   public ProposalApplication getProposalApplicationByProposal(def proposalId)
   {
	   def proposalApplicationInstance = ProposalApplication.find("from ProposalApplication PA where PA.proposal="+proposalId)
	   return proposalApplicationInstance
   }
   
   /*
    * method to get proposalApplicationExt Instance by proposalApplicationId
    */
   public List getProposalApplicationExtByProposalApplication(def proposalApplicationId)
   {
	   def proposalApplicationExtResult = ProposalApplicationExt.findAll("from ProposalApplicationExt PE where PE.proposalApplication="+proposalApplicationId)
	   return proposalApplicationExtResult
   }
   
   /*
    * method to get eligibilityCriteriaInstance by eligibilitycriteriaId
    */ 
   
   public getEligibilityCriteriaById(def eligibilitycriteriaId)
  {
	  def eligibilityCriteriaInstance = EligibilityCriteria.get(eligibilitycriteriaId)
	  return eligibilityCriteriaInstance
  }
   
   /*
    * method to get duplicate eligibilityCriteria 
    */ 
   
    public chkEligibilityCriteria(def eligibilityCriteria)
  {
	  def chkEligibilityCriteriaInstance = EligibilityCriteria.find("from EligibilityCriteria EC where EC.eligibilityCriteria= '"+eligibilityCriteria+"'")    
      return chkEligibilityCriteriaInstance
  
  }
   
   
   
   
    
    
}
