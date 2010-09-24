class InvestigatorService {

	public List getAllInvestigators(String subQuery)
	{  
		def investigatorInstanceList=Investigator.findAll("from Investigator I where I.activeYesNo='Y'"+subQuery)
		return investigatorInstanceList
	}
	   
	public List getInvestigatorsWithParty(def partyId,String subQuery)
	{	   
		def investigatorInstanceList=Investigator.findAll("from Investigator I where I.party.id="+partyId+"and I.activeYesNo='Y'"+subQuery)
		return investigatorInstanceList
	}
	
	public List getInvestigatorsWithParty(def partyId)
	{	   
		def investigatorList=Investigator.findAll("from Investigator I where I.party.id="+partyId+"and I.activeYesNo='Y'")
		return investigatorList
	}
   
   /**
	 * Function to get investigator by id.
	 */
   public getInvestigatorById(def investigatorId)
   {
	   def investigatorInstance = Investigator.get(investigatorId)
	   return investigatorInstance
   }
}
