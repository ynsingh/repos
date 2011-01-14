

class PreProposalCoPIService {

    boolean transactional = true

    def serviceMethod() {

    }
    
    public savePreProposalCoPI(params)
    {
    	def preProposalCoPIInstance = new PreProposalCoPI(params)
    	preProposalCoPIInstance.save(flush: true)
    	return preProposalCoPIInstance
    }
    /*
     * Get the details of the PreProposal CoPI
     */
     public PreProposalCoPI getPreProposalCoPIById(Integer preProposalCoPIId)
  	{
  		def preProposalCoPIInstance = PreProposalCoPI.get( preProposalCoPIId)
  		return preProposalCoPIInstance
  	}
     /*
      * Update Approval Authority
      */
      public updatePreProposalCoPI(def params, def preProposalCoPIInstance)
     {
     	if(preProposalCoPIInstance)
     	{
     		preProposalCoPIInstance.properties = params
     	
     	    if (!preProposalCoPIInstance.hasErrors() && preProposalCoPIInstance.save(flush: true)) 
     	    {
     	    	preProposalCoPIInstance.saveMode = "Updated"
     	    }
     	 return  preProposalCoPIInstance
         }
      return  preProposalCoPIInstance
     }
}
