class CheckListService
{
	public  getAllCheckListByParty(def partyId)
	{
		def checkListList = Checklist.findAll("from Checklist CL where CL.activeYesNo='Y' and CL.party.id="+partyId)
		return checkListList
	}
	
	public  getAllCheckListByPartyandProject(def partyId,def projectId)
	{
		def checkListList = Checklist.findAll("from Checklist CL where CL.activeYesNo='Y' and CL.party.id="+partyId+" and  CL.id NOT IN (SELECT CLM.checklist.id from ChecklistMap CLM where CLM.projects="+projectId+" and CLM.activeYesNo='Y')")
		return checkListList
	}
	public  getCheckListBySatifiedInstnce(def projectId)
	{
		def checkcompulsoryList = ChecklistMap.findAll("from ChecklistMap CLM where CLM.projects="+projectId+" and CLM.activeYesNo='Y' and CLM.compulsory='y'")
		return checkcompulsoryList
	}
	public  getAllCheckListByParamsId(def params)
	{
		def chkList = ChecklistMap.findAll("from ChecklistMap CLM where CLM.id="+params)
		return chkList
	}
}

