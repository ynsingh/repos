class EvalItemService {

    boolean transactional = true
 
/*
 * Function to Get Duplicate EvalItems
*/
	
	public toGetDuplicateEvalItem(def evalitem,def notificationId,def evalScaleId)
	{
		def evalItemDuplicateInstance = EvalItem.find("from EvalItem EI where EI.activeYesNo = 'Y' and EI.item ='"+evalitem+"' and EI.notification = '"+notificationId+"' and EI.evalScale = "+evalScaleId )
	    return evalItemDuplicateInstance
	}
	 
    
    
    public boolean saveEvalAnswer(def params,def isSaved,def userInstance) 
    {
    	def proposalId = params.proposalId
    	def notificationId = params.notificationId
    	
    	def evalItemInstanceList = getevalItemList(notificationId)
    
    	def proposalInstance = Proposal.get(proposalId);
    	def evalAnswerInstance
    	def evalAnswerInstanceList = []
    	def scaleOptionsList = []
    	def nullScaleList = []
    	def remarksList = []
    	def nullRemarksList = []
    	for(int i=0;i<evalItemInstanceList.size();i++)
    	{
    		def scaleOptions = params."scaleOptions${i}"
    		if(!scaleOptions)
    			nullScaleList.add(scaleOptions)
    		scaleOptionsList.add(scaleOptions)
    		def remarks = params."remarks${i}"
    		if(!remarks)
    			nullRemarksList.add(remarks)
    		remarksList.add(remarks)
    		println "scaleOptions" + scaleOptions
    	}
    	if(scaleOptionsList.size() != nullScaleList.size() || remarksList.size() != nullRemarksList.size())
    	{
	    	for(int i=0;i<evalItemInstanceList.size();i++)
	    	{
	    		evalAnswerInstance = new EvalAnswer(params)
	    		evalAnswerInstance.person = userInstance
	
	    		if(scaleOptionsList[i])
	    		{
		    		def evalScaleOptionsInstance = EvalScaleOptions.get(new Integer(scaleOptionsList[i]));
		    		evalAnswerInstance.evalScaleOptions = evalScaleOptionsInstance
	    		}
	    		evalAnswerInstance.evalItem = evalItemInstanceList[i]
	    		evalAnswerInstance.proposal = proposalInstance
	    		evalAnswerInstance.Remarks = remarksList[i]
	     		evalAnswerInstance.activeYesNo= "Y"
	    		evalAnswerInstance.createdBy = "admin"
	    		evalAnswerInstance.modifiedBy = "admin"
	        	evalAnswerInstance.save(flush: true)
	        	if( evalAnswerInstance.hasErrors())
	        	{
	        		evalAnswerInstance.errors.each 
	        		{
	                println it
	        		}
	        	}
	        	evalAnswerInstanceList.add(evalAnswerInstance)
	        	isSaved = true;
	    	}    
    	}else
    	{
    		isSaved = false;
    	}
    	println " evalAnswerInstanceList" +evalAnswerInstanceList
    	return isSaved;
    }
    public boolean updateEvalAnswer(def params,def evalSaved)
    {
    	def proposalId = params.proposalId
    	def notificationId = params.notificationId
    	def evalItemInstanceList = getevalItemList(notificationId)
    	for(int i=0;i<evalItemInstanceList.size();i++)
    	{
    		def evalAnswerId = params."id${i}"
    		println "evalAnswerId" + evalAnswerId
    		def evalAnswerInstance = EvalAnswer.get(evalAnswerId)
    		println "evalAnswerInstance" + evalAnswerInstance
    		if(evalAnswerInstance)
    			evalAnswerInstance.properties = params
       		def scaleOptions = params."scaleOptions${i}"
    		def remarks = params."remarks${i}"
    		if(scaleOptions)
    		{
	    		def evalScaleOptionsInstance = EvalScaleOptions.get(new Integer(scaleOptions));
	    		evalAnswerInstance.evalScaleOptions = evalScaleOptionsInstance
    		}
    		evalAnswerInstance.Remarks = remarks
            evalAnswerInstance.save(flush: true)
            evalSaved = true; 
    	}
    	return evalSaved;
    }
    /**
     * Get Eval Item List based on notification
     */
    public List getevalItemList(def notificationId)
    {
    	def evalItemInstanceList =  EvalItem.findAll("from EvalItem EI where EI.activeYesNo='Y' and EI.notification.id = " + notificationId)
    	return evalItemInstanceList
    }
    /**
     * Get Eval Answer List of reviewer based on the proposal
     */
    public List getEvalAnswerList(def userInstance,def proposalId)
    {
    	def evalAnswerInstanceList = EvalAnswer.findAll("from EvalAnswer EA where EA.activeYesNo='Y' and EA.person.id = "+ userInstance.id + " and EA.proposal.id = "+proposalId)
   
    }
    /**
     * Get Eval Answer List based on the proposal
     */
    public List getEvalAnswerListBasedOnProposal(def proposalId)
    {
    	def evalAnswerInstanceList = EvalAnswer.findAll("from EvalAnswer EA where EA.activeYesNo='Y' and EA.proposal.id = "+proposalId)
    }
    /**
     * Get Eval Answer List based on the proposal group by users
     */
    public List getEvalAnswerListGroupByUser(def proposalId)
    {
    	EvalAnswer.findAll("from EvalAnswer EA where EA.activeYesNo='Y' and EA.proposal.id = "+proposalId+" group by EA.person.id")
    }
    /**
     * Get Eval Scale Options list based on the eval scale
     */
    public List getEvalOptionsListbyEvalScale(def evalItemInstance)
    {
    	def evalScaleOptionsOfEvalScale = EvalScaleOptions.findAll("from EvalScaleOptions EO where EO.activeYesNo = 'Y' and EO.evalScale.id = "+evalItemInstance.evalScale.id);
    }
    /**
     * Get Eval Score based on the proposal
     */
    public EvalScore getEvalScoreByProposal(def proposalId)
    {
    	def evalScoreInstance = EvalScore.find("from EvalScore ES where ES.proposal.id = " + proposalId)
    	return evalScoreInstance 
    }
    public boolean saveEvalScore(def finalScore,def proposalInstance,def isSAaved,def nOfreviewers)
    {
    	def evalScoreInstance = getEvalScoreByProposal(proposalInstance.id)
    	    	
    	if(!evalScoreInstance)
    	{
   	    	evalScoreInstance = new EvalScore();
	    	evalScoreInstance.proposal = proposalInstance
	    	evalScoreInstance.activeYesNo= "Y"
	    	evalScoreInstance.createdBy = "admin"
	    	evalScoreInstance.modifiedBy = "admin"
    	}
		evalScoreInstance.totalScore = new Double(finalScore)
		evalScoreInstance.noOfReviewers = nOfreviewers
    	evalScoreInstance.save()
    	isSAaved = true;
		
    	return isSAaved
    }
    public getMaxScoreEvalScale(def evalScaleInstance)
    {
    	def maxScale=EvalScaleOptions.executeQuery("select max(ES.scaleOptionIndex) from EvalScaleOptions ES where ES.activeYesNo='Y' and ES.evalScale.id="+evalScaleInstance.id)
    	return maxScale;
    }    
    
    /**
     * Get Eval Item List based on EvalScale
     */
     
     public getEvalItemListByEvalScale(def evalScaleId)
      {
    	def evalItemForEvalScaleInstanceList = EvalItem.findAll("from EvalItem EI where EI.evalScale.id= "+evalScaleId)
    	return evalItemForEvalScaleInstanceList
      }
    
     /**
      * Get Eval Item List Instance on Id
      */
      
     public getEvalItemInstanceById(def evalItemId)
     {
    	 def evalItemAssignedInstance = EvalItem.find("from EvalItem EI where EI.activeYesNo='Y'and EI.id= "+evalItemId)
     }
     
     /*
 	 * Function to delete an Eval Item
 	 */
     public deleteEvalscale(def evalItemInstance)
 	{
    	evalItemInstance.activeYesNo = "N"
    	evalItemInstance.save()
 		return evalItemInstance
 	}
     
     /*
  	 * Function to get Proposal List for a notification
  	 */
     public getProposalsForANotification(def notificationId) 
    {
    	
    	def proposalsForANotificationList = Proposal.findAll("from Proposal PP where PP.notification.id="+notificationId)
        return proposalsForANotificationList
    }   
     
     
     /*
   	 * Function to get an evalAnswer for a notification 
   	 */
   	 public getEvalAnswerForProposalId(def proposalId)
   	 {
   		def evalAnswerProposalInstance = EvalAnswer.find("from EvalAnswer EAR where EAR.proposal.id="+proposalId)
   	    return evalAnswerProposalInstance
   	 }
     
   /*
    * Function to get an evalAnswer for a notification 
    */
     
     public getEvalAnswerByEvalItemIdAndProposalId(def evalItemId,def proposalId)
    {
    	def evalItemInEvalAnswerInstance = EvalAnswer.find("from EvalAnswer EAR where EAR.evalItem.id= "+evalItemId+" and EAR.proposal.id="+proposalId+" ")
    	return evalItemInEvalAnswerInstance
    }
     /**
      * Function to get Eval Answer List by user id
      */
     public List getEvalAnswerListByUser(def userId,def party)
     {
     	def evalAnswerInstanceList = EvalAnswer.findAll("from EvalAnswer EA where EA.activeYesNo='Y' and EA.person.id = "+
     			userId+" and EA.proposal.notification.party.id ="+party+" group by EA.proposal.id")
     	return evalAnswerInstanceList
     }
    def serviceMethod() {

    }
}
