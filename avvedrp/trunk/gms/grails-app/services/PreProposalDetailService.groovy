
class PreProposalDetailService {

    boolean transactional = true

    def serviceMethod() {

    }
    public savePreProposalDetail(params)
    {
    	def preProposalDetailInstance = new PreProposalDetail(params)
    	preProposalDetailInstance.save(flush: true)
    	return preProposalDetailInstance
    }
    /*
     * Get the details of the PreProposal Detail
     */
     public PreProposalDetail getPreProposalDetailById(Integer preProposalDetailId)
  	{
  		def preProposalDetailInstance = PreProposalDetail.get(preProposalDetailId)
  		return preProposalDetailInstance
  	}
     /*
      * Update Approval Authority
      */
      public updatePreProposalDetail(def params, def preProposalDetailInstance)
     {
     	if(preProposalDetailInstance)
     	{
     		preProposalDetailInstance.properties = params
     	
     	    if (!preProposalDetailInstance.hasErrors() && preProposalDetailInstance.save(flush: true)) 
     	    {
     	       preProposalDetailInstance.saveMode = "Updated"
     	    }
     	 return  preProposalDetailInstance
         }
      return  preProposalDetailInstance
     }
     
      /*
       * Get the details of the PreProposal Detail by PreProposalId
       */
       public List getAllPreProposalDetailByPreProposalId(def preProposalId)
    	{
    		def preProposalDetailInstance = PreProposalDetail.findAll("from PreProposalDetail PD where PD.activeYesNo='Y' and PD.preProposal="+preProposalId)
    		return preProposalDetailInstance
    	}
}
