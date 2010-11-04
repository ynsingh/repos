class PartyDepartmentService
{
	public List getActiveDepartment(def partyId)
	{
		def departmentList=PartyDepartment.findAll("from PartyDepartment PD where PD.party.id="+partyId+"and PD.activeYesNo='Y'")
		return departmentList
	}
}