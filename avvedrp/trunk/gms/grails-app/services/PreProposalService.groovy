

class PreProposalService {
	
    boolean transactional = true

    def serviceMethod() {

    }
    public def getPrePropsalById(def preProposalId)
    {
    	def preProposalInstance = PreProposal.get(preProposalId) 
    	return preProposalInstance
    }
    public savePreProposal(def params,def userId,def partyId)
    {
    	
    	def userService = new UserService()
    	def userInstance=Person.get(userId)
    	//def userMapInstance=UserMap.find("from UserMap UM where UM.user="+userId)
    	def preProposalInstance = new PreProposal(params)
    	preProposalInstance.party=Party.get(partyId)
    	
    	//preProposalInstance.department=facultyInstance.department
    	preProposalInstance.person=userInstance
    	preProposalInstance.preProposalSavedDate=new Date()
    	preProposalInstance.preProposalStatus="Saved"
    	preProposalInstance.preProposalLevel=new Integer(0)
    	preProposalInstance.activeYesNo='Y'
    	if(preProposalInstance.save(flush: true))
    	{
    		println "hai"
    	}
    	return  preProposalInstance
    }
    /*
     * Get the details of the Pre Proposal
     */
     public PreProposal getPreProposalById(Integer preProposalId)
  	{
  		def  preProposalInstance = PreProposal.get( preProposalId)
  		return preProposalInstance
  	}
     /*
      * Update  Pre Proposal
      */
      public updatePreProposal(def params, def  preProposalInstance)
     {
     	if( preProposalInstance)
     	{
     		preProposalInstance.properties = params
     	
     	    if (! preProposalInstance.hasErrors() &&  preProposalInstance.save(flush: true)) 
     	    {
     	    	 preProposalInstance.saveMode = "Updated"
     	    }
     	 return   preProposalInstance
         }
      return  preProposalInstance
     }
     
    /*
     * method to get all preProposal
     */
 	
 	public List getPreProposal(def params)
 	{
 
 	def preProposalInstanceList = PreProposal.findAll("from PreProposal PP where PP.id = "+params.id)
 	return preProposalInstanceList
 	}
 	/*
 	 * method to save preProposalDetails
 	 */
     public def saveformDetails(def params,def proposalId)
     {
    	 def preProposalDetailSavedInstance
	  def preProposalInstance=getPrePropsalById(proposalId)
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
						def preProposalDetailInstance=new PreProposalDetail()
						preProposalDetailSavedInstance = PreProposalDetail.findAll("from PreProposalDetail PE where PE.preProposal.id="+proposalId+" and PE.field='"+obj+"'") 
			
						if(preProposalDetailSavedInstance)
						{
							println "preProposalDetailsSavedInstance "+preProposalDetailSavedInstance
							for(preProposalPreviousInstance in preProposalDetailSavedInstance)
							{
								preProposalPreviousInstance.activeYesNo="N"
								preProposalPreviousInstance.save()
							}
						}
						println "field - "+params.get(obj).toString()
						preProposalDetailInstance.preProposal=preProposalInstance
						preProposalDetailInstance.field=obj.toString()
						preProposalDetailInstance.value=params.get(obj).toString()
						preProposalDetailInstance.preProposalSubmittedDate=new Date()
						preProposalDetailInstance.activeYesNo='Y'
						preProposalDetailInstance.save()
						
						
					}
				}
			}
		
		
		
		return saveStatusList
    }
     public savePreProposalInstance(def preProposalInstance)
     {
    	 def status = false
    	 if(preProposalInstance.save())
    	 {
    		 status = true
    	 }
    	 return status
     }
     public List getSubmittedPreProposal(def party)
  	
  	{
  		def preProposalList = PreProposal.findAll("from PreProposal PP where PP.preProposalStatus = 'Submitted' and PP.activeYesNo = 'Y' and PP.party="+party)
  		return preProposalList

  	}
     /*
      * method to get submitted preproposal by id
      */
     public PreProposal getSubmittedPreProposalById(def proposalId)
     {
    	 def preProposalValue= PreProposal.find("from PreProposal P where P.preProposalStatus='Submitted' and P.id="+proposalId)
    	 return preProposalValue
     }
     /*
   	 * Check Proposal Category 
   	 */
   	 public checkDuplicatePreProposal(def params)
   	{
   		 def chkPreProposalInstance = PreProposal.findAll("from PreProposal P where P.projectTitle= '"+params.projectTitle+"' and P.activeYesNo='Y'")
   		 return chkPreProposalInstance
   	}
   	/*
	 * update PreProposal
	 */
	 public updatePreProposalInstance(def preProposalInstance)
	{
		 if(preProposalInstance.save())
		 {
			 
		 }
		 else
		 {
			 preProposalInstance=null
		 }
		 return preProposalInstance
	} 
	 /*
      * method to get preproposal by proposal status and user id
      */
     public List getPreProposalByStatus(def proposalStatus,def userId)
     {
    	 def preProposalInstance= PreProposal.findAll("from PreProposal P where P.preProposalStatus='"+proposalStatus+"' and P.person="+userId)
    	 return preProposalInstance
     }

}
