class FundTransferService
{
	public getFundTransferDetailsByProject(def projectId)
	{
		def fundTransferInstanceList=FundTransfer.findAll("from FundTransfer FT where FT.grantAllocation.projects = " +projectId)
		return fundTransferInstanceList
	}
	
	public List chkAmntValidation(def fundTransferInstance)
	{
		def chkFundTransferAmnt = GrantAllocation.findAll("from GrantAllocation GA where GA.id = "+fundTransferInstance.grantAllocationId)
		return chkFundTransferAmnt
	}

}