
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
		if(grantReceiptInstance) {
			grantReceiptInstance.properties = grantReceiptParams
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
		
		grantReceiptInstance.projects=projects
		if(grantReceiptInstance.save())
			grantReceiptInstance.isSaved = true
		else
			grantReceiptInstance.isSaved = false
			
		return grantReceiptInstance
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
}