class InvestigatorService {

   public List getAllInvestigators(String subQuery)
   {
   
   def investigatorInstanceList=Investigator.findAll("from Investigator I "+subQuery)
   return investigatorInstanceList
    }
   
   public List getInvestigatorsWithParty(def partyId,String subQuery)
   {
   
   def investigatorInstanceList=Investigator.findAll("from Investigator I where I.party.id="+partyId+subQuery)
   return investigatorInstanceList
   }
}
