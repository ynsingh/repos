import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import java.util.*;
class ProposalService {

   def userService
   def notificationsEmailsService
   def partyService
   def notificationService
   def proposalApprovalAuthorityMapService
   def evalItemService
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
		    	mailStatus=notificationsEmailsService.sendMessage(userInstance.email,mailMessage,"text/plain")
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
    * method to get proposalApplicationExt Instance by proposalApplicationId and order by page and orderNo
    */
   public List getProposalApplicationExtByProposalApplication(def proposalApplicationId)
   {
	   def proposalApplicationExtResult = ProposalApplicationExt.findAll("from ProposalApplicationExt PE where PE.proposalApplication="+proposalApplicationId+" order by page,orderNo")
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
    public def saveProposal(def params,def notification,def partyId)
    {
 	   def proposalInstance = new Proposal()
 	   def dateValue = new Date()
 	   def notificationInstance=notificationService.getNotificationById(notification)
 	   def partyInstance
 	   if(partyId == "null")
 	   {
 		  partyInstance=null
 	   }
 	   else
 	   {
 	      partyInstance = partyService.getPartyById(partyId)
 	   }
 	  String uniqueId = UUID.randomUUID().toString();
 	  Random randomNumber = new Random();
 	  proposalInstance.party=partyInstance
 	  proposalInstance.code="PR-"+dateValue.getYear()+dateValue.getMonth()+1+dateValue.getDate()+dateValue.getSeconds()+dateValue.getMinutes()+dateValue.getHours()   
 	  proposalInstance.notification=notificationInstance
 	  proposalInstance.proposalSubmitteddate=dateValue
 	  proposalInstance.lockedYN='Y'
 	  proposalInstance.proposalVersion=new Integer(0);
 	  if(proposalInstance.save())
 	  {
 		
 	  }
 	   return proposalInstance
    }
    /*
     * method to save proposalApplication
     */
    public def saveProposalAppliction(def proposalInstance,def params)
    {
    	def proposalApplicationInstanceCheck = getProposalApplicationByProposal(proposalInstance.id)
    	def proposalApplicationInstance
    	if(proposalApplicationInstanceCheck)
    	{
    		proposalApplicationInstance=proposalApplicationInstanceCheck
    	}
    	else
    	{
    		proposalApplicationInstance = new ProposalApplication()
    		Random randomNumber = new Random();
    		proposalApplicationInstance.proposal=proposalInstance
    		proposalApplicationInstance.controllerId=proposalInstance.code+randomNumber.nextInt()
    	}
 		proposalApplicationInstance.applicationSubmitDate=new Date()
 		proposalApplicationInstance.name=params.FirstName_1+" "+params.LastName_2
 		proposalApplicationInstance.designation=params.Designation_3
 		proposalApplicationInstance.organisation=params.Organisation_4
 		proposalApplicationInstance.postalAddress=params.PostalAddress_5
 		proposalApplicationInstance.city=params.City_6
 		proposalApplicationInstance.state=params.State_7
 		proposalApplicationInstance.phone=params.STD_8+"-"+params.Phone_9
 		proposalApplicationInstance.fax=params.FaxSTD_10+"-"+params.FaxPhone_11
 		proposalApplicationInstance.email=params.Email_12
 		proposalApplicationInstance.mobile=params.Mobile_13
 		proposalApplicationInstance.proposalCategory=ProposalCategory.find("from ProposalCategory PC where PC.name='"+params.ProjectCategory_14+"'")
 		
 		if(proposalApplicationInstance.save())
 		{
 			
 		}
 		
    	return proposalApplicationInstance
    }
    public def updateProposalAppliction(def proposalInstance)
    {
    	def proposalApplicationInstance 
    	proposalApplicationInstance.proposal=proposalInstance
 		proposalApplicationInstance.applicationSubmitDate=new Date()
 		proposalApplicationInstance.controllerId=proposalInstance.code+randomNumber.nextInt()
 		if(proposalApplicationInstance.save())
 		{
 			
 		}
 		
    	return proposalApplicationInstance
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
    /*
     * method to get proposal application instance assigned to each reviewer of party
     */
    public def getProposalApplicationListForReviewer(def user,def party)
    {
    	/*method to get proposalApprovalAuthorityMapInstance of each user using approval authority of user*/
    	def proposalApprovalAuthorityMapInstance = proposalApprovalAuthorityMapService.getProposalApprovalAuthorityMapByReviewer(user,'Proposal')
    	def proposalApplicationInstanceList = []
    	if(proposalApprovalAuthorityMapInstance)
    	{
    		for(proposalId in proposalApprovalAuthorityMapInstance.proposalId)
    		{
    			def proposalApplicationInstance=getProposalApplicationByLockedNoProposal(proposalId)
    			if(proposalApplicationInstance)
    			{
    				proposalApplicationInstanceList.add(proposalApplicationInstance)
    			}
    		}
    	}
    	
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
    /*
     * method to get proposal by id
     */
    def getProposalById(def proposalId)
    {
    	def proposalInstance = Proposal.get(proposalId)
    	return proposalInstance
    }
   
    /*
     * method to get proposalApplication by controllerId and notification
     */
    public def getProposalApplicationByControllerIdAndNotification(def controllerId,def notificationId)
    {
 	   def proposalApplicationInstance = ProposalApplication.find("from ProposalApplication PA where PA.controllerId='"+controllerId+"' and PA.proposal.notification.id="+notificationId)
 	   return proposalApplicationInstance
    }
    /*
     * method to get proposalApplication Instance by proposalId and locked no
     */
    public ProposalApplication getProposalApplicationByLockedNoProposal(def proposalId)
    {
 	   def proposalApplicationInstance = ProposalApplication.find("from ProposalApplication PA where PA.proposal.lockedYN='N' and PA.proposal="+proposalId)
 	   return proposalApplicationInstance
    }
    /*
     * method to get proposalApplicationExt Instance by proposalApplication Id 
     */
    public List getProposalApplicationExtByProposalApplicationId(def proposalApplicationId)
    {
 	   def proposalApplicationExtResult = ProposalApplicationExt.findAll("from ProposalApplicationExt PE where PE.proposalApplication="+proposalApplicationId)
 	   return proposalApplicationExtResult
    }
    /*
     * method to get search result of proposal application instance assigned to each reviewer of party
     */
    public def getProposalApplicationSearchListForReviewer(def user,def party,def params)
    {
    	/*method to get proposalApprovalAuthorityMapInstance of each user using approval authority of user*/
    	def proposalApprovalAuthorityMapInstance = proposalApprovalAuthorityMapService.getProposalApprovalAuthorityMapByReviewer(user,'Proposal')
    	/*method to get evalAnswer of user*/
    	def evalAnwserInstanceList = evalItemService.getEvalAnswerListByUser(user,party)
    	def proposalInstanceList 
    	if(params.status=='Reviewed')
    	{
    		proposalInstanceList=evalAnwserInstanceList?.proposal?.id 
    	}
    	else if(params.status=='NotReviewed')
    	{
    		proposalInstanceList=(proposalApprovalAuthorityMapInstance?.proposalId)-(evalAnwserInstanceList?.proposal.id)
    	}
    	else
    	{
    		proposalInstanceList=proposalApprovalAuthorityMapInstance?.proposalId 		
    	}
    	def proposalApplicationInstanceList = []
    	if(proposalInstanceList)
    	{
    		for(proposalId in proposalInstanceList)
    		{
    			def proposalApplicationInstance=getProposalApplicationByLockedNoProposal(proposalId)
    			if(proposalApplicationInstance)
    			{
    				proposalApplicationInstanceList.add(proposalApplicationInstance)
    			}
    		}
    	}
    	
    	return proposalApplicationInstanceList
    }
    
    public List getEvalAnswerByProposal(def proposalId)
    {
    	def evalAnswerInstance = EvalAnswer.findAll("from EvalAnswer EA where EA.proposal.id ="+proposalId+"group by EA.person.id")
    	return evalAnswerInstance
    }
    
   
    
    public List getEvalItem(def notificationId)
    {
    	def evalItemInstance = EvalItem.findAll("from EvalItem EI where EI.notification.id ="+notificationId)
    	return evalItemInstance
    }
    
    public List getEvalScore(def proposalId,def personId)
    {
    def evalScoreInstance = EvalAnswer.findAll("from EvalAnswer EA where EA.proposal.id ="+proposalId+"and EA.person.id="+personId)
    return evalScoreInstance
    }
}
