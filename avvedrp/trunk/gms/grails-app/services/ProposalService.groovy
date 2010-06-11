class ProposalService {

   /*
    * Method to list all proposals
    */
   public List getProposalList(def proposalInstance)
   {
	   def proposalInstanceList = Proposal
	   									.findAll("from Proposal P where P.notification.id = " 
	   											+ proposalInstance.notification.id );
	   return proposalInstanceList;
   }
    
    
}
