import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import java.util.*;
class ProposalService {

   def userService
   def notificationsEmailsService
   def partyService
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
   
    /*
     * method to get proposalApplication by controllerId
     */
    public def getProposalApplicationByControllerId(def controllerId)
    {
 	   def proposalApplicationInstance = ProposalApplication.find("from ProposalApplication PA where PA.controllerId='"+controllerId+"'")
 	   return proposalApplicationInstance
    }
    /*
     * method to save proposalApplication
     */
    public def saveProposal(def params,def grantor)
    {
 	   def proposalInstance = new Proposal()
 	   def dateValue = new Date()
 	   def grantorInstance=partyService.getPartyById(grantor)
 	  String uniqueId = UUID.randomUUID().toString();
 	  Random randomNumber = new Random();
 	  proposalInstance.code="PR-"+dateValue.getYear()+dateValue.getMonth()+1+dateValue.getDate()+dateValue.getSeconds()+dateValue.getMinutes()+dateValue.getHours()   
 	  proposalInstance.grantor=grantorInstance
 	  proposalInstance.proposalSubmitteddate=dateValue
 	  proposalInstance.lockedYN='Y'
 	  if(proposalInstance.save())
 	  {
 		 def proposalApplicationInstance = new ProposalApplication()
 		proposalApplicationInstance.proposal=proposalInstance
 		proposalApplicationInstance.applicationSubmitDate=dateValue
 		proposalApplicationInstance.controllerId=proposalInstance.code+randomNumber.nextInt()
 		
 		if(proposalApplicationInstance.save())
 		{
 			
 		}
 	  }
 	   return proposalInstance
    }
    public def saveProposalAppliction()
    {
    	
    }
    public def updateProposal(def proposalInstance)
    {
    	def proposalStatus = false
    	proposalInstance.proposalSubmitteddate=new Date()
    	if(proposalInstance.save())
    	{
    		proposalStatus = true
    	}
    	return proposalStatus
    }
    public def updateProposalApplication(def proposalApplicationInstance)
    {
    	def proposalStatus = false
    	proposalApplicationInstance.applicationSubmitDate=new Date()
    	if(proposalApplicationInstance.save())
    	{
    		proposalStatus = true
    	}
    	return proposalApplicationInstance
    }
    public def getProposalApplicationListForReviewer(def user,def party)
    {
    	def proposalApplicationInstanceList =ProposalApplication.findAll("from ProposalApplication P where P.proposal.grantor.id="+party) 
    	return proposalApplicationInstanceList
    }
    /*
     * method to get Last saved page number
     */
    def getMaxPageOfProposalApplicationExt(def proposalApplicationId)
    {
    	def proposalApplicationExtInstance = ProposalApplicationExt.executeQuery("select MAX(PE.page) from ProposalApplicationExt PE where PE.proposalApplication.id="+proposalApplicationId)
    	return proposalApplicationExtInstance
    }
    /*
     * method to get proposal application by id
     */
    def getProposalApplicationById(def proposalApplicationId)
    {
    	def proposalApplicationInstance = ProposalApplication.get(proposalApplicationId)
    	return proposalApplicationInstance
    }
   
   
    
    
}
