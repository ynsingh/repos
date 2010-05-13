class ProjectDepartmentMapService {

    boolean transactional = true

    public PartyDepartment[] getPartyDepartmentForUser(String partyId)
    {
    	def partyDepartmentList
    	if(partyId)
    		partyDepartmentList = PartyDepartment.findAll("from PartyDepartment PD where PD.party.id="+partyId)
    	return partyDepartmentList;
    }
    public ProjectDepartmentMap[] getProjectDepartmentMapList(String partyId)
    {
    	def projectDepartmentMapList = ProjectDepartmentMap.findAll("from ProjectDepartmentMap PM where PM.projects.id in (select GA.projects.id from GrantAllocation GA where GA.party.id='"+partyId+"')")

    	return projectDepartmentMapList
    }
}
