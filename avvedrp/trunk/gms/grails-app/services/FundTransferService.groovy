class FundTransferService
{
	public getFundTransferDetailsByProject(def projectId)
	{
		def fundTransferInstanceList=FundTransfer.findAll("from FundTransfer FT where FT.grantAllocation.projects = " +projectId)
		return fundTransferInstanceList
	}
}