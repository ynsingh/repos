import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import java.util.*;
import grails.util.GrailsUtil
import java.text.SimpleDateFormat
import org.apache.commons.validator.EmailValidator
import org.codehaus.groovy.grails.commons.ApplicationHolder as AH
class ProposalService {

   def userService
   def notificationsEmailsService
   def partyService
   def notificationService
   def proposalApprovalAuthorityMapService
   def evalItemService
   def projectsService
   def grantAllocationService
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
 	  proposalInstance.proposalType='Proposal'
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
 		proposalApplicationInstance.projectTitle=""
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
    			//check if proposal awarded or not
    			def proposalAwardList = getAwardListByProposal(proposalId)
    			if(proposalAwardList.size() == 0)
    			{
	    			def proposalApplicationInstance=getProposalApplicationByLockedNoProposal(proposalId)
	    			if(proposalApplicationInstance)
	    			{
	    				proposalApplicationInstanceList.add(proposalApplicationInstance)
	    			}
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
    			//check if proposal awarded or not
    			def proposalAwardList = getAwardListByProposal(proposalId)
    			if(proposalAwardList.size() == 0)
    			{
	    			def proposalApplicationInstance=getProposalApplicationByLockedNoProposal(proposalId)
	    			if(proposalApplicationInstance)
	    			{
	    				proposalApplicationInstanceList.add(proposalApplicationInstance)
	    			}
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
    
    /*
     * method to save award details
     */
     public Award saveAward(def proposalInstance,def projectsInstance)
    {
    	def awardInstance = new Award()
    	awardInstance.proposal=proposalInstance
		awardInstance.projects=projectsInstance
		awardInstance.createdBy="admin"
		awardInstance.createdDate=new Date()	
		awardInstance.modifiedBy="admin"
		awardInstance.modifiedDate=new Date()
		awardInstance.activeYesNo='Y'
		awardInstance.awardedDate=new Date()
		if(awardInstance.save())
		{
			
		}
		else
		{
			awardInstance=null
		}
    	return awardInstance
    }
   
    /*
     * method to get award list by proposal id
     */
    public def getAwardListByProposal(def proposalId)
    {
    	def proposalAwardList = Award.findAll("from Award A where A.proposal.id="+proposalId)
    	return proposalAwardList
    }
    /*
     * method to get award by proposal id
     */
    public def getAwardByProposal(def proposalId)
    {
    	def proposalAwardList = Award.find("from Award A where A.proposal.id="+proposalId)
    	return proposalAwardList
    }
    /*
     * method to get award list by notification id 
     */
    public def getAwardListByProposalNotificationId(def notificationId)
    {
    	def proposalAwardList = Award.findAll("from Award A where A.proposal.notification.id="+notificationId)
    	return proposalAwardList
    }
    /*
     * method to get ProposalApplicationExt by field name and proposal application id 
     */
    public def getProposalApplicationExtByFieldAndProposalAppId(def field,def proposalApplicationId)
    {
    	def proposalApplicationExtProjectInstance = ProposalApplicationExt.find("from ProposalApplicationExt PE where PE.field='"+field+"' and PE.proposalApplication.id="+proposalApplicationId)
    	return proposalApplicationExtProjectInstance
    }
    /*
     * method to generate random string
     */
    public def generateCode()  
    {  
      final int PASSWORD_LENGTH = 8;  
      StringBuffer sb = new StringBuffer();  
      for (int x = 0; x < PASSWORD_LENGTH; x++)  
      {  
        sb.append((char)((int)(Math.random()*26)+97));  
      }  
      System.out.println(sb.toString());  
      return sb.toString()
    }  
    public getParentProposalId(def proposal,def proposalStatus,def userId)
    {
   	 def proposalInstance = Proposal.findAll("from Proposal P where P.proposalType='"+proposal+"' and P.proposalStatus ='"+proposalStatus+"' and P.person="+userId);
   	 return proposalInstance
    }

   
    /*
     * method to get getfullProposalStatus
     */
    public getfullProposalStatus(def proposalId)
    {
   	 def fullProposalStatus = Proposal.find("from Proposal P where P.parent="+proposalId+" and P.proposalStatus in ('Submitted','Approved')")
   	 return fullProposalStatus
    }
    
    /*
     * method to get fullProposalSavedStatus
     */
    
    public getfullProposalSavedStatus(def proposalId)
    {
   	 def fullProposalSavedStatus = Proposal.find("from Proposal P where P.parent="+proposalId+" and P.proposalStatus in ('Saved','NeedMoreInfo')")
   	 return fullProposalSavedStatus
    }
    
    /*
     * method to get full proposal by proposalId
     */
    def getFullProposalByProposal(def proposalId)
    {
    	def fullProposalInstance= Proposal.find("from Proposal F where F.parent.id = "+proposalId)
    	return fullProposalInstance
    }
 /*
     * method to save PreProposal
     */
     public savePreProposal(def params,def userId,def partyId)
     {
    	 def dateValue = new Date()
    	def userService = new UserService()
     	def userInstance=Person.get(userId)
     	//def userMapInstance=UserMap.find("from UserMap UM where UM.user="+userId)
     	def proposalInstance = new Proposal(params)
     	proposalInstance.party=Party.get(partyId)
     	
     	//preProposalInstance.department=facultyInstance.department
     	proposalInstance.person=userInstance
     	proposalInstance.proposalSubmitteddate=new Date()
    	proposalInstance.proposalStatus="Saved"
    	proposalInstance.proposalLevel=new Integer(0)
	    proposalInstance.proposalType="PreProposal"
     	proposalInstance.code="PR-"+dateValue.getYear()+dateValue.getMonth()+1+dateValue.getDate()+dateValue.getSeconds()+dateValue.getMinutes()+dateValue.getHours()   
     	if(proposalInstance.save(flush: true))
     	{
     		def proposalApplicationInstance = new ProposalApplication(params)
         	proposalApplicationInstance.proposal=proposalInstance
    		proposalApplicationInstance.applicationSubmitDate=new Date()
     		proposalApplicationInstance.save()
      		
     	}
     	return  proposalInstance
        
     }
    /*
     * method to get preproposal by id
     */
     public def getPrePropsalById(def proposalId)
     {
     	def proposalInstance = Proposal.get(proposalId) 
     	return proposalInstance
     }
     
     /*
      * Get the details of the PreProposal Detail by PreProposalId
      */
      public List getAllPreProposalAppExtByPreProposalId(def proposalId)
   	{
    	  def proposalApplicationExtInstance = ProposalApplicationExt.findAll("from ProposalApplicationExt PE where PE.proposalApplication.proposal.id="+proposalId)
   	   return proposalApplicationExtInstance
      
   	}
     
     /*
  	 * method to save preProposalDetails
  	 */
      public def saveformDetailsPreProposal(def params,def proposalId)
      {
     	 def proposalApplicationExtSavedInstance
     	 def proposalInstance=getPrePropsalById(proposalId)
     	 def proposalApplicationInstance = ProposalApplication.find("from ProposalApplication PA where PA.proposal="+proposalId)
     	
	  	 Set keyList=params.keySet()
 		def saveStatusList = true
 		Iterator itr=keyList.iterator()
 					println "saved para"+params		
 			while(itr.hasNext())
 			{
 				Object obj=itr.next()
 				if(obj.toString() !="action")
 				{
 					if(obj.toString()!="controller")
 					{
 						def proposalApplicationExtInstance=new ProposalApplicationExt()
 						proposalApplicationExtSavedInstance = ProposalApplicationExt.findAll("from ProposalApplicationExt PAE where PAE.proposalApplication.proposal.id="+proposalInstance.id+" and PAE.field='"+obj+"'") 
 			
 						if(proposalApplicationExtSavedInstance)
 						{
 							for(proposalPreviousInstance in proposalApplicationExtSavedInstance)
 							{
 								println"-------------------proposalPreviousInstance----------------------"+proposalPreviousInstance
 								proposalPreviousInstance.activeYesNo="N"
 								proposalPreviousInstance.save()
 							}
 						}
 						println "field - "+params.get(obj).toString()
 						proposalApplicationExtInstance.proposalApplication=proposalApplicationInstance
						proposalApplicationExtInstance.field=obj.toString()
 						proposalApplicationExtInstance.value=params.get(obj).toString()
 						proposalApplicationExtInstance.activeYesNo='Y'
 						proposalApplicationExtInstance.save()
 						
 						
 					}
 				}
 			}
 		
 		
 		
 		return saveStatusList
     }
     
      public savePreProposalInstance(def proposalInstance)
      {
     	 def status = false
     	 if(proposalInstance.save())
     	 {
     		 status = true
     	 }
     	 return status
      }
      
      /*
       * Get the details of the Pre Proposal
       */
       public Proposal getPreProposalById(def proposalId)
    	{
    		def  proposalInstance = Proposal.get(proposalId)
    		return proposalInstance
    	}
      
       /*
        * Update  Pre Proposal
        */
        public updatePreProposal(def params, def  proposalInstance)
       {
       	if( proposalInstance)
       	{
       		proposalInstance.properties = params
       	
       	    if (! proposalInstance.hasErrors() &&  proposalInstance.save(flush: true)) 
       	    {
       	    	 proposalInstance.saveMode = "Updated"
       	    }
       	 return   proposalInstance
           }
        return  proposalInstance
       }
       
       
        /*
       	 * Check Duplicate PreProposal
       	 */
       	 public checkDuplicatePreProposal(def params)
       	{
       		 def chkPreProposalInstance = ProposalApplication.find("from ProposalApplication PA where PA.projectTitle= '"+params.projectTitle+"'")
       		 return chkPreProposalInstance
       	}
        /*
         * Method to get submitted PreProposal
         */
       	 public List getSubmittedPreProposal(def party)
       	
       	{
       		def preProposalList = Proposal.findAll("from Proposal P where P.proposalStatus = 'Submitted' and P.party="+party)
       		return preProposalList

       	}
        
         /*
          * method to get submitted preproposal by id
          */
         public Proposal getSubmittedPreProposalById(def proposalId)
         {
        	 def preProposalValue= Proposal.find("from Proposal P where P.proposalStatus='Submitted' and P.id="+proposalId)
        	 return preProposalValue
         }

       
	
 /*
  * update PreProposal
	 */
	 public updatePreProposalInstance(def proposalInstance)
	{
		 if(proposalInstance.save())
		 {
			 
		 }
		 else
		 {
			 proposalInstance=null
		 }
		 return proposalInstance
	} 

	 public getUserInstance(def UserId)
		{
		  def proposalInstanceList = Proposal.findAll("from Proposal P where P.proposalType='PreProposal' and P.person.id= "+UserId)
		  return proposalInstanceList
		}
	 /*
	  * Method to get Proposal Approval Authority By proposal Id
	  */
	 public getProposalApprovalAuthorityByProposalId(def proposalId)
		{
	      def proposalInstance = Proposal.find("from Proposal P where P.id ="+proposalId)
	      return proposalInstance
		}
	 /*
	  * Method to get Proposal Application By proposal Id
	  */
	 public getProposalApplicationByProposalId(def proposalId)
		{
			 def proposalApplicationInstance = ProposalApplication.find("from ProposalApplication PA where PA.proposal.id ="+proposalId)
			 return proposalApplicationInstance
		}
	   /*
	    * Method to get proposal Application Details by Proposal Application Id
	    */
	 public getProposalApplicationId(def proposalApplicationId)
	    {
		   
	    	def proposalApplicationInstance = ProposalApplication.find("from ProposalApplication PA where PA.id="+proposalApplicationId)
           return proposalApplicationInstance
	    }
    
	 /*
	     * method to update FullProposal
	     */
	    def updateFullProposal(def fullProposalInstance)
	    {
	    	if(fullProposalInstance.save())
	    	{
	    		
	    	}
	    	else
	    	{
	    		fullProposalInstance=null
	    	}
	    	return fullProposalInstance
	    }
	 
	    /*
         * method to get submitted preproposal by id and type
         */
        public Proposal getSubmittedPreProposalByIdAndType(def proposalId,def params)
        {
       	 def preProposalValue= Proposal.find("from Proposal P where P.proposalType='"+params.ProposalType+"'and P.proposalStatus='Submitted' and P.id="+proposalId)
       	 return preProposalValue
        }
	    
	    
        /*
         * Method to get submitted PreProposal By type
         */
       	 public List getSubmittedPreProposalByType(def party)
       	
       	{
       		def preProposalList = Proposal.findAll("from Proposal P where P.proposalType='PreProposal' and P.proposalStatus = 'Submitted' and P.party="+party)
       		return preProposalList

       	}
       	 /*
          * Method to get submitted FullProposal By type
          */
     	 public List getSubmittedFullProposalByType(def party)
         {
        	def preProposalList = Proposal.findAll("from Proposal P where P.proposalType='FullProposal' and P.proposalStatus = 'Submitted' and P.party="+party)
        	return preProposalList
         }

}
