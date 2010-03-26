class ProjectDepartmentMapService {

    boolean transactional = true

    public PartyDepartment[] getPartyDepartmentForUser(String partyId)
    {
    	def partyDepartmentList
    	if(partyId)
    		partyDepartmentList = PartyDepartment.findAll("from PartyDepartment PD where PD.party.id="+partyId)
    	return partyDepartmentList;
    }
}
