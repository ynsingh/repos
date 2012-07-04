
class GrantReceiptService{
	
	/**
	 * Function to get grant receipt by params
	 */
	public List getGrantReceiptByParams(def grantReceiptParams){
		def grantReceiptInstanceList = GrantReceipt.list( grantReceiptParams )
		return grantReceiptInstanceList
	}
	
	/**
	 * Function to get grant receipt by id.
	 */
	public GrantReceipt getGrantReceiptById(Integer grantReceiptId){
		def grantReceiptInstance = GrantReceipt.get( grantReceiptId )
		return grantReceiptInstance
	}
	
	/**
	 * Function to get grant receipt by grant allocation
	 */
	public List getGrantReceiptByGrantAllocation(def grantAllocationId){
		def grantReceiptList=GrantReceipt.findAll("from GrantReceipt  GA where  GA.grantAllocation="+grantAllocationId);
		return grantReceiptList
	}
	
	
	/**
	 * Function to get grant receipt by Projects
	 */
	public List getGrantReceiptByProjects(def projectId,String subQuery){
		def grantReceiptList=GrantReceipt.findAll("from GrantReceipt  GA where  GA.projects="+projectId+""+subQuery);
		return grantReceiptList
	}
	
	
	/**
	 * Function to delete grant receipt.
	 */
	public Integer deleteGrantReceipt(Integer grantReceiptId){
		Integer projectId = null
		def grantReceiptInstance = getGrantReceiptById(grantReceiptId )
		if(grantReceiptInstance) {
			 projectId = grantReceiptInstance.projects.id
            grantReceiptInstance.delete()
           
		}
		else
			projectId = -1
			
		return projectId
	}
	
	/**
	 * Function to update grant receipt.
	 */
	public GrantReceipt updateGrantReceipt(def grantReceiptParams){
		def grantReceiptInstance = getGrantReceiptById( new Integer(grantReceiptParams.id ))
		def fundTransferInstance = new FundTransfer()
		if(grantReceiptInstance) {
			grantReceiptInstance.properties = grantReceiptParams
			
			if(grantReceiptInstance.fundTransfer==null)
			{
				println "fundTransfer in service" + grantReceiptInstance.fundTransfer
				if(grantReceiptInstance.grantAllocation)
				{
					fundTransferInstance.grantAllocation = grantReceiptInstance.grantAllocation
					fundTransferInstance.amount = grantReceiptInstance.amount
					fundTransferInstance.dateOfTransfer = grantReceiptInstance.dateOfReceipt
					fundTransferInstance.createdBy = "admin"
					fundTransferInstance.modifiedBy = "admin"
					fundTransferInstance.save()		
					grantReceiptInstance.fundTransfer = fundTransferInstance
				}
			}
			
			
            if(!grantReceiptInstance.hasErrors() && grantReceiptInstance.save()) {
            	grantReceiptInstance.isSaved = true
            }
            else
            	grantReceiptInstance.isSaved = false
		}
		return grantReceiptInstance
	}
	
	/**
	 * Function to save grant receipt.
	 */
	public GrantReceipt saveGrantReceipt(def grantReceiptInstance,Integer projectId){
		def grantAllocationService = new GrantAllocationService()
		def projects=Projects.get(projectId);
		def fundTransferInstance = new FundTransfer()
		
		grantReceiptInstance.projects=projects
		println "grantReceiptInstance in service" + grantReceiptInstance.grantAllocation
		if(grantReceiptInstance.fundTransfer.id==null)
		{
			println "fundTransfer in service" + grantReceiptInstance.fundTransfer
			if(grantReceiptInstance.grantAllocation)
			{
				fundTransferInstance.grantAllocation = grantReceiptInstance.grantAllocation
				fundTransferInstance.amount = grantReceiptInstance.amount
				fundTransferInstance.dateOfTransfer = grantReceiptInstance.dateOfReceipt
				fundTransferInstance.createdBy = "admin"
				fundTransferInstance.modifiedBy = "admin"
				fundTransferInstance.save()		
				grantReceiptInstance.fundTransfer = fundTransferInstance
			}
		}
		
		if(grantReceiptInstance.save())
		{
			grantReceiptInstance.isSaved = true		
		}
		else
			grantReceiptInstance.isSaved = false
		
		println "grantReceiptInstance in service" + grantReceiptInstance.id
		def grantReceipt=GrantReceipt.get(grantReceiptInstance.id)
		return grantReceipt
	}
	
	/**
	 * Function to get summary of grant receipt
	 */
	public List getGrantReceiptSummary(def projectId){
		def summaryList = GrantAllocationSplit.executeQuery("select id, accountHead.code,sum(G.amount) as Total,"+
				"sum(G.amount) as expense from GrantAllocationSplit  G"+
                " where G.projects="+projectId+" group by  accountHead")
        def totamt
                        
        for(int i=0;i<summaryList.size();i++){
        	def childlist= summaryList[i]
        	totamt=GrantReceipt.executeQuery("select sum(GP.amount) as Total from GrantReceipt  GP"+
        			" where GP.grantAllocationSplit="+childlist[0]+" and GP.projects="+projectId+"  group by  GP.grantAllocationSplit")

			summaryList[i][3]=totamt[0]
    	}
		return summaryList
	}
	
	/**
	 * Function to get total amount recieved for projects.
	 */
	public double getSumOfGrantReceviedByProjects(def projectId){
		def totalAmt = 0.0
		def grantReceiptList = GrantReceipt.executeQuery("select sum(GA.amount) from GrantReceipt  GA "+ 
				" where  GA.projects="+projectId +" group by GA.projects");
		if(grantReceiptList.size() > 0)
			totalAmt = grantReceiptList[0]
		return totalAmt
	}
	/*public List getGrantReceiptBySort(def projectId,String subQuery ){
	def grantReceiptInstanceList=GrantReceipt.findAll("from GrantReceipt  GA where  GA.projects="+projectId+""+subQuery);
	return grantReceiptInstanceList
    }
	*/
	
	/**
	 * Function to check whether the funnd received against the headwise allocation.
	 */
	public GrantReceipt[] getGrantReceiptForGrantAllocationSplit(def grantAllocationSplitInstance){
		
		def grantReceiptInstance=GrantReceipt.find("from GrantReceipt GR where GR.grantAllocationSplit="+grantAllocationSplitInstance.id)
		println"*********grantAllocationSplitInstance***********"+grantReceiptInstance
		
	    return grantReceiptInstance    
		
	}
	
	public GrantReceipt[] chkReceiveFundTransfer(def fundTransfer)
	{
		def chkReceiveFund=GrantReceipt.findAll("from GrantReceipt GP where GP.fundTransfer="+fundTransfer.id)
		return chkReceiveFund
	}
	
	public double getSumOfAmountReceivedForProject(def projectId){
		 double totalAmt = 0.0
		 def sumAmt = GrantReceipt.executeQuery("select sum(GR.amount) as total from GrantReceipt  GR where   GR.projects= "+projectId+" group by GR.projects");
		 if(sumAmt[0]!=null)
			 totalAmt = new Double(sumAmt[0]).doubleValue()
			 
		 return totalAmt
	}
	
	public List chkgrantReceiptInstanceForParent(def fundTransferInstance)
	{
		def chkgrantReceiptInstance=GrantReceipt.findAll("from GrantReceipt GR where GR.projects="+fundTransferInstance.grantAllocation.projects.parent.id)
		return chkgrantReceiptInstance
	}
	
	/**
	 * Function to get total amount recieved for a Grant Allocation.
	 */
	public double getSumOfAmountReceivedForGrantAllocation(def grantAllocationId){
		 double totalAmt = 0.0
		 def sumAmt = GrantReceipt.executeQuery("select sum(GR.amount) as total from GrantReceipt  GR where   GR.grantAllocation= "+grantAllocationId+" group by GR.grantAllocation");
		 if(sumAmt[0]!=null)
			 totalAmt = new Double(sumAmt[0]).doubleValue()
			 
		 return totalAmt
	}
	
	public getGrantReceiptByFundTransfrId(def FundTransfrId)
	{
		def receivedInstance = GrantReceipt.find("from GrantReceipt GR where GR.fundTransfer.id="+FundTransfrId)
		return receivedInstance
	}
}