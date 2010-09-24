class ProposalService {

   /*
    * Method to list all proposals
    */
   public List getProposalList(def proposalInstance,def partyId)
   {
	   def proposalInstanceList = Proposal
	   									.findAll("from Proposal P where P.notification.id = " 
	   											+ proposalInstance.notification.id+" and P.party.id="+partyId );
	   println "Proposal Id="+proposalInstanceList.id
	   return proposalInstanceList;
   }
    
    
}
