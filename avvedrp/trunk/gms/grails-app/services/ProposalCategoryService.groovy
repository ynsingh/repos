
class ProposalCategoryService {

    boolean transactional = true

    def serviceMethod() {

    }
    
    /*
     * List the details of all active Proposal Category
     */
    
    
    public List getProposalCategoryList()
	{                                                                  
		def proposalCategoryInstanceList=ProposalCategory.findAll("from ProposalCategory PC where PC.activeYesNo='Y'")			
		return proposalCategoryInstanceList
	}
    public saveProposalCategory(params)
    {
    	def proposalCategoryInstance = new ProposalCategory(params)
    	proposalCategoryInstance.activeYesNo="Y" 
    	proposalCategoryInstance.save(flush: true)
    	return proposalCategoryInstance
    }
    /*
     * Get the details of the Proposal Category
     */
     public ProposalCategory getProposalCategoryById(Integer proposalCategoryId)
  	{
  		def proposalCategoryInstance = ProposalCategory.get( proposalCategoryId)
  		return proposalCategoryInstance
  	}
     /*
      * Update  Proposal Category
      */
      public updateProposalCategory(def params, def proposalCategoryInstance)
     {
     	if(proposalCategoryInstance)
     	{
     		proposalCategoryInstance.properties = params
     	
     	    if (!proposalCategoryInstance.hasErrors() && proposalCategoryInstance.save(flush: true)) 
     	    {
     	    	proposalCategoryInstance.saveMode = "Updated"
     	    }
     	 return  proposalCategoryInstance
         }
      return proposalCategoryInstance
     }
     
      /*
  	 * Check Proposal Category 
  	 */
  	 public checkDuplicateCategory(def params)
  	{
  		 def chkProposalCategoryInstance = ProposalCategory.find("from ProposalCategory PC where PC.name= '"+params.name+"' and PC.activeYesNo='Y'")
  		 return chkProposalCategoryInstance
  	}
}
