 import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class EvalAnswerController {
	def attachmentsService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "evalForm", params: params)
    }

    def save = {
    	def isSaved = false;
    	def proposalId = params.proposalId
    	def notificationId = params.notificationId
    	def evalItemService = new EvalItemService()
    	GrailsHttpSession gh=getSession()
    	def userInstance = Person.get(gh.getValue("UserId"));
    	isSaved = evalItemService.saveEvalAnswer(params,isSaved,userInstance)
    	def proposalApplicationInstance = ProposalApplication.find("from ProposalApplication PA where PA.proposal.id ="+proposalId) 
    	if (isSaved) 
        {
            flash.message = "${message(code: 'default.saved.label')}"
            redirect(action: "evalForm",id:proposalApplicationInstance.id)
        }
        else 
        {
        	flash.error = "${message(code: 'default.CannotSave.message')}"
        	redirect(action: "evalForm",id:proposalApplicationInstance.id)
        }
    }

    def update = {
		boolean evalSaved = false;
		GrailsHttpSession gh=getSession()
		def proposalService = new ProposalService()
		def proposalId = params.proposalId
    	def notificationId = params.notificationId
    	def evalItemService = new EvalItemService()
		def userInstance = Person.get(gh.getValue("UserId"));
		def evalAnswerList = evalItemService.getEvalAnswerList(userInstance,proposalId)
		def proposalValInstance = proposalService.getProposalById(new Integer(params.proposalId))
		def evalScoreInstance = evalItemService.getEvalScore(proposalValInstance)
		def proposalApplicationInstance = ProposalApplication.find("from ProposalApplication PA where PA.proposal.id ="+proposalId)
		if(evalAnswerList && evalScoreInstance && proposalValInstance.proposalStatus == "Reviewed" )
 	   	{
 	   		if(params.personStatus == 'N')
 	   		{
 	   		flash.message = "Already submitted"
 	   		redirect(controller:"proposal",action:"proposalApplicationList",id:proposalApplicationInstance.id)
 	   		}
 		   	else if(params.personStatus == 'N' && params.reviewalStatus == 'Y')
 	 	   		{
 	 	   		flash.message = "Already submitted"
 	 	 	   		redirect(controller:"proposal",action:"proposalApplicationList",id:proposalApplicationInstance.id)
 	 	   		}
 		   	else
 		   	{
 		   	evalSaved = evalItemService.updateEvalAnswer(params,evalSaved)
 	    	
 	    	if(evalSaved)
 	    		flash.message = "${message(code: 'default.updated.label')}"
 	    	else
 	    		flash.error = "${message(code: 'default.NotUpdated.message')}"
 	    	redirect(action: "evalForm",id:proposalApplicationInstance.id)
 		   	}
 	 	   	
 	   	}
 
		else
		{
		evalSaved = evalItemService.updateEvalAnswer(params,evalSaved)
    	
    	if(evalSaved)
    		flash.message = "${message(code: 'default.updated.label')}"
    	else
    		flash.error = "${message(code: 'default.NotUpdated.message')}"
    	redirect(action: "evalForm",id:proposalApplicationInstance.id)
    }
	}
    def evalForm = 
    {
    	def proposalService = new ProposalService()
    	def proposalApplicationInstance = ProposalApplication.find("from ProposalApplication PA where PA.id ="+params.id)
    	def proposalInstance = proposalService.getProposalById(proposalApplicationInstance.proposal.id)
    	def proposalApplicationExtInstance = proposalService.getProposalApplicationExtByProposalApplication(proposalApplicationInstance.id)
    	def attachmentsInstanceGetCV=attachmentsService.getAttachmentsByDomainAndType("Proposal","CV",proposalInstance.id)
        def attachmentsInstanceGetDPR=attachmentsService.getAttachmentsByDomainAndType("Proposal","DPR",proposalInstance.id)
        def evalItemService = new EvalItemService()  
    	def proposalId = proposalInstance.id
    	def notificationId = proposalInstance.notification.id
    	char personStatus
    	char reviewalStatus
    	GrailsHttpSession gh=getSession()
    	def userInstance = Person.get(gh.getValue("UserId"));
    	
    	def evalAnswerInstanceList = evalItemService.getEvalAnswerList(userInstance,proposalId)
    	def evalItemInstanceList = evalItemService.getevalItemList(proposalInstance.notification.id)
    	def evalScaleOptionsList = []
    	def evalScoreInstance = EvalScore.find("from EvalScore ES where ES.proposal.id="+proposalInstance.id)
    	def proposalAuthorityMapInstance = ProposalApprovalAuthorityMap.find("from ProposalApprovalAuthorityMap PAM where PAM.proposalId="+proposalInstance.id)
    	def approvalAuthorityDetailInstance = ApprovalAuthorityDetail.findAll("from ApprovalAuthorityDetail AD where AD.activeYesNo='Y' and AD.approvalAuthority.id="+proposalAuthorityMapInstance.approvalAuthority.id)
    	
    	for(int i=0;i<evalItemInstanceList.size();i++)
    	{
    		def evalScaleOptionsOfEvalScale = evalItemService.getEvalOptionsListbyEvalScale(evalItemInstanceList[i])
    		evalScaleOptionsList.add(evalScaleOptionsOfEvalScale) 
    	}
    	if(proposalInstance.person)
    	{
    	def propoNotInstance = Proposal.findAll("from Proposal P where P.notification.id="+proposalInstance.notification.id+"and P.id="+proposalInstance.id)
    	if(propoNotInstance[0].person.id != userInstance.id)
    	{
    		personStatus = 'Y'
    	}
    	else
    	{
    		personStatus = 'N'
    	}
    	if(approvalAuthorityDetailInstance.size() == evalScoreInstance.noOfReviewers)
    	{
    		reviewalStatus = 'Y'
    	}
    	else
    	{
    		reviewalStatus = 'N'
    	}
    	println"personStatus;;;;;;;;;;;;"+personStatus
    	return [evalItemInstanceList:evalItemInstanceList,evalScaleOptionsList:evalScaleOptionsList,
    	        proposalId:proposalId,notificationId:notificationId,
    	        evalAnswerInstanceList:evalAnswerInstanceList,
    	        proposalApplicationExtInstance:proposalApplicationExtInstance,
    	        propoNotInstance:propoNotInstance,userInstance:userInstance,
    	        personStatus:personStatus,reviewalStatus:reviewalStatus,
    	        proposalInstance:proposalInstance,proposalApplicationInstance:proposalApplicationInstance,attachmentsInstanceGetCV:attachmentsInstanceGetCV,attachmentsInstanceGetDPR:attachmentsInstanceGetDPR]
    	}
    	else
    	{
    		return [evalItemInstanceList:evalItemInstanceList,evalScaleOptionsList:evalScaleOptionsList,
        	        proposalId:proposalId,notificationId:notificationId,
        	        evalAnswerInstanceList:evalAnswerInstanceList,
        	        proposalApplicationExtInstance:proposalApplicationExtInstance,
        	        userInstance:userInstance,
        	        proposalInstance:proposalInstance,proposalApplicationInstance:proposalApplicationInstance,attachmentsInstanceGetCV:attachmentsInstanceGetCV,attachmentsInstanceGetDPR:attachmentsInstanceGetDPR]
    	}
    }
    def submitResult = 
    {
    	def paramId
    	boolean isSaved = false;
    	boolean evalSaved = false;
    	if(params.id)
    		paramId = params.id
    	else
    		paramId = params.proposalId	
    		
    	GrailsHttpSession gh=getSession()
    	def userInstance = Person.get(gh.getValue("UserId"));
    	def proposalService = new ProposalService()
    	def proposalValInstance = proposalService.getProposalById(new Integer(params.proposalId))
    	def evalItemService = new EvalItemService() 
    	def evalScaleOptionsService =new EvalScaleOptionsService()
    	def evalAnswerList = evalItemService.getEvalAnswerList(userInstance,paramId)
    	def evalScoreInstance = evalItemService.getEvalScore(proposalValInstance)
    	def proposalApplicationInstance = ProposalApplication.find("from ProposalApplication PA where PA.proposal.id ="+paramId)
    	println"params.personStatus-----------"+params.personStatus
    	println"params.reviewalStatus..........."+params.reviewalStatus
    	/*Update the evaluation result*/
 	   	if(evalAnswerList && evalScoreInstance && proposalValInstance.proposalStatus == "Reviewed" )
 	   	{
 	   		println"bbbbbbbbbbbb"
 	   		if(params.personStatus == 'N')
 	   		{
 	   		println"ffffffffffffff"
 	   		flash.message = "Already submitted"
 	   		redirect(controller:"proposal",action:"proposalApplicationList",id:proposalApplicationInstance.id)
 	   		}
 		   	else if(params.personStatus == 'N' && params.reviewalStatus == 'Y')
 	 	   		{
 	 	   		println"llllllllllllll"
 	 	   		flash.message = "Already submitted"
 	 	 	   		redirect(controller:"proposal",action:"proposalApplicationList",id:proposalApplicationInstance.id)
 	 	   		}
 			else 
 	 	   	{
 	 	   	    if(evalAnswerList)
 	 	   	    	{
 	 	   	    println"mmmmmmmmmmmm"
 	 	   	    	evalSaved = evalItemService.updateEvalAnswer(params,evalSaved)
 	 	   	    	}
 	        	else
 	 	   			{
 	        		println"jjjjjjjjjj"
 	 	   			evalSaved = evalItemService.saveEvalAnswer(params,evalSaved,userInstance)
 	 	   			}
 	    	if(evalSaved)
 	    	
 	    	{
 		 	   	/*GEt proposal by Id*/
 		    	def proposalInstance = proposalService.getProposalById(new Integer(paramId))
 		    	def proposalId = proposalInstance.id
 		    	
 		    	double totalScore = 0.0
 		    	def evalAnswerInstanceCount = evalItemService.getEvalAnswerListGroupByUser(proposalId)
 		    	double nOfreviewers = evalAnswerInstanceCount.size()
 		    	def evalAnswerInstanceList = evalItemService.getEvalAnswerListBasedOnProposal(proposalId)
 		    	def evalItemInstanceList = evalItemService.getevalItemList(proposalInstance.notification.id)
 		    	double nOfQuestions = evalItemInstanceList.size()
 		    	for(int i=0;i<evalItemInstanceList.size();i++)
 		    	{
 		    		def scaleOptionsList = evalScaleOptionsService.listEvalScaleOptionsByEvalScale(evalItemInstanceList[i].evalScale.id)
 		    		if(!scaleOptionsList)
 		    			nOfQuestions = nOfQuestions - (1).doubleValue()
 		    	}

 		    	for(int i=0;i<evalAnswerInstanceList.size();i++)
 		    	{
 		    		
 		    		if(evalAnswerInstanceList[i].evalScaleOptions)//def val = evalAnswerInstanceList[i].evalScaleOptions.scaleOptionIndex		
 		    			totalScore = totalScore + (evalAnswerInstanceList[i].evalScaleOptions.scaleOptionIndex).doubleValue()
 		    	}
 		    	double finalScore = totalScore/(nOfQuestions*nOfreviewers)
 		    	isSaved = evalItemService.saveEvalScore(finalScore,proposalInstance,isSaved,nOfreviewers)
 		    	def evalScoreNewInstance = EvalScore.find("from EvalScore ES where ES.proposal.id ="+proposalInstance.id+" and ES.activeYesNo ='N'")
 		    	
 		    	if(isSaved)
 		    	{
 		    		
 		    		println"kkkkkkkkkkkkkk"
 		    	    println"userInstance"+userInstance
 		    	    println"proposalInstance.person"+proposalInstance.person
 		    		proposalInstance.proposalStatus = "Reviewed"

 		    		if(evalScoreNewInstance)
 		    			evalScoreNewInstance[0].activeYesNo = 'Y'
 		    			proposalInstance.person = userInstance
 		    		flash.message = "${message(code:'default.submittedsuccessfully.message')}";
 		    		redirect(controller:"proposal",action:"proposalApplicationList",id:proposalApplicationInstance.id)   
 		    	}
 		    	else
 		    	{
 		    		redirect(action: "evalForm",id:proposalApplicationInstance.id)   
 		    	}
 	    	}
 	    	else
 	    	{
 	    		flash.error = "${message(code: 'default.CannotSubmit.message')}";
 	    		redirect(action: "evalForm",id:proposalApplicationInstance.id)   
 	    	}
 	 	   	}
 	    	
 	    	
 	    	
 	    }
 		   		
 	 	   	
 	   	
 
 	   	else 
 	   	{
 	   	    if(evalAnswerList)
 	   	    	{
 	   	    println"mmmmmmmmmmmm"
 	   	    	evalSaved = evalItemService.updateEvalAnswer(params,evalSaved)
 	   	    	}
        	else
 	   			{
        		println"jjjjjjjjjj"
 	   			evalSaved = evalItemService.saveEvalAnswer(params,evalSaved,userInstance)
 	   			}
    	if(evalSaved)
    	
    	{
	 	   	/*GEt proposal by Id*/
	    	def proposalInstance = proposalService.getProposalById(new Integer(paramId))
	    	def proposalId = proposalInstance.id
	    	
	    	double totalScore = 0.0
	    	def evalAnswerInstanceCount = evalItemService.getEvalAnswerListGroupByUser(proposalId)
	    	double nOfreviewers = evalAnswerInstanceCount.size()
	    	def evalAnswerInstanceList = evalItemService.getEvalAnswerListBasedOnProposal(proposalId)
	    	def evalItemInstanceList = evalItemService.getevalItemList(proposalInstance.notification.id)
	    	double nOfQuestions = evalItemInstanceList.size()
	    	for(int i=0;i<evalItemInstanceList.size();i++)
	    	{
	    		def scaleOptionsList = evalScaleOptionsService.listEvalScaleOptionsByEvalScale(evalItemInstanceList[i].evalScale.id)
	    		if(!scaleOptionsList)
	    			nOfQuestions = nOfQuestions - (1).doubleValue()
	    	}

	    	for(int i=0;i<evalAnswerInstanceList.size();i++)
	    	{
	    		
	    		if(evalAnswerInstanceList[i].evalScaleOptions)//def val = evalAnswerInstanceList[i].evalScaleOptions.scaleOptionIndex		
	    			totalScore = totalScore + (evalAnswerInstanceList[i].evalScaleOptions.scaleOptionIndex).doubleValue()
	    	}
	    	double finalScore = totalScore/(nOfQuestions*nOfreviewers)
	    	isSaved = evalItemService.saveEvalScore(finalScore,proposalInstance,isSaved,nOfreviewers)
	    	def evalScoreNewInstance = EvalScore.find("from EvalScore ES where ES.proposal.id ="+proposalInstance.id+" and ES.activeYesNo ='N'")
 		    	
	    	if(isSaved)
	    	{
	    		println"kkkkkkkkkkkkkk"
	    	    println"userInstance"+userInstance
	    	    println"proposalInstance.person"+proposalInstance.person
	    		proposalInstance.proposalStatus = "Reviewed"
	    			if(evalScoreNewInstance)
	    			evalScoreNewInstance.activeYesNo = 'Y'
	    			proposalInstance.person = userInstance
	    		flash.message = "${message(code:'default.submittedsuccessfully.message')}";
	    		redirect(controller:"proposal",action:"proposalApplicationList",id:proposalApplicationInstance.id)   
	    	}
	    	else
	    	{
	    		redirect(action: "evalForm",id:proposalApplicationInstance.id)   
	    	}
    	}
    	else
    	{
    		flash.error = "${message(code: 'default.CannotSubmit.message')}";
    		redirect(action: "evalForm",id:proposalApplicationInstance.id)   
    	}
 	   	}
    	
    	
    	
    }

}
