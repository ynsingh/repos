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
		def proposalService = new ProposalService()
		def proposalId = params.proposalId
    	def notificationId = params.notificationId
    	def evalItemService = new EvalItemService()
		def proposalValInstance = proposalService.getProposalById(new Integer(params.proposalId))
		def evalScoreInstance = evalItemService.getEvalScore(proposalValInstance)
		if(evalScoreInstance && proposalValInstance.proposalStatus == "Reviewed")
 	   	{
 	   		flash.message = "Already submitted"
 	   		redirect(controller:"proposal",action:"proposalApplicationList",id:proposalApplicationInstance.id)     
 	   	}
		else
		{
		evalSaved = evalItemService.updateEvalAnswer(params,evalSaved)
    	def proposalApplicationInstance = ProposalApplication.find("from ProposalApplication PA where PA.proposal.id ="+proposalId) 
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
    	
    	GrailsHttpSession gh=getSession()
    	def userInstance = Person.get(gh.getValue("UserId"));
    	def evalAnswerInstanceList = evalItemService.getEvalAnswerList(userInstance,proposalId)
    	def evalItemInstanceList = evalItemService.getevalItemList(proposalInstance.notification.id)
    	def evalScaleOptionsList = []
    	for(int i=0;i<evalItemInstanceList.size();i++)
    	{
    		def evalScaleOptionsOfEvalScale = evalItemService.getEvalOptionsListbyEvalScale(evalItemInstanceList[i])
    		evalScaleOptionsList.add(evalScaleOptionsOfEvalScale) 
    	}
    	return [evalItemInstanceList:evalItemInstanceList,evalScaleOptionsList:evalScaleOptionsList,
    	        proposalId:proposalId,notificationId:notificationId,
    	        evalAnswerInstanceList:evalAnswerInstanceList,
    	        proposalApplicationExtInstance:proposalApplicationExtInstance,
    	        proposalInstance:proposalInstance,proposalApplicationInstance:proposalApplicationInstance,attachmentsInstanceGetCV:attachmentsInstanceGetCV,attachmentsInstanceGetDPR:attachmentsInstanceGetDPR]
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
    	
    	/*Update the evaluation result*/
 	   	if(evalAnswerList && evalScoreInstance && proposalValInstance.proposalStatus == "Reviewed")
 	   	{
 	   		flash.message = "Already submitted"
 	   		redirect(controller:"proposal",action:"proposalApplicationList",id:proposalApplicationInstance.id)     
 	   	}
 	   	else 
 	   	{
 	   	    if(evalAnswerList)
 	   	    	{
 	   	    	evalSaved = evalItemService.updateEvalAnswer(params,evalSaved)
 	   	    	}
        	else
 	   			{
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
	    	if(isSaved)
	    	{
	    	
	    		proposalInstance.proposalStatus = "Reviewed"
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
