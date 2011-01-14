
class ProposalCategoryService {

    boolean transactional = true

    def serviceMethod() {

    }
    public saveProposalCategory(params)
    {
    	def proposalCategoryInstance = new ProposalCategory(params)
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
}
