
import java.text.DecimalFormat;
import java.text.NumberFormat;
class GrantAllocationSplitService{
	
	/**
	 * Function to get grant allocation split by params
	 */
	public List getGrantAllocationSplitByParams(def grantAllocationSplitParams){
		def grantAllocationSplitInstanceList = GrantAllocationSplit.list( grantAllocationSplitParams )
		return grantAllocationSplitInstanceList
	}
	
	/**
	 * Function to get grant allocation split by id.
	 */
	public GrantAllocationSplit getGrantAllocationSplitById(Integer grantAllocationSplitId){
		def grantAllocationSplitInstance = GrantAllocationSplit.get( grantAllocationSplitId )
		return grantAllocationSplitInstance
	}
	
	/**
	 * Function to get grant allocation split by grant allocation
	 */
	public List getGrantAllocationSplitByGrantAllocation(def grantAllocationId){
		def grantAllocationSplitList=GrantAllocationSplit.findAll("from GrantAllocationSplit  GA where  GA.grantAllocation="+grantAllocationId);
		return grantAllocationSplitList
	}
	
	
	/**
	 * Function to get grant allocation split by grant allocation
	 */
	public List getGrantAllocationSplitDetailsByProject(def projectId){
		def grantAllocationSplitList=GrantAllocationSplit.executeQuery("select  GA.grantPeriod, GA.grantAllocation,GA.accountHead, sum(GA.amount)  from GrantAllocationSplit GA where GA.projects ="+projectId+" group by GA.grantAllocation, GA.accountHead");
		return grantAllocationSplitList
		
		for(int i=0;i<grantAllocationSplitList.size();i++)
		 {
			
			
			 
			  def numformatter = new DecimalFormat("#0.00");
			  grantAllocationSplitList[i][3]= numformatter.format(grantAllocationSplitList[i][3])

			
		 }
	}
	
	
	/**
	 * Function to get grant allocation split by grant allocation
	 */
	public List getGrantAllocationSplitDetailsByGrantAllocation(def grantAllocationId){
		def grantAllocationSplitList=GrantAllocationSplit.executeQuery("select  GA.grantPeriod, GA.grantAllocation,GA.accountHead, sum(GA.amount) from GrantAllocationSplit GA where GA.grantAllocation ="+grantAllocationId+" group by GA.accountHead");
		for(int i=0;i<grantAllocationSplitList.size();i++)
		 {
			
			
			 
			  def numformatter = new DecimalFormat("#0.00");
			  grantAllocationSplitList[i][3]=numformatter.format(grantAllocationSplitList[i][3])

			
		 }
		return grantAllocationSplitList
	}
	
	
	/**
	 * Function to get grant allocation split by Projects
	 */
	public List getGrantAllocationSplitByProjects(def projectId)
	{
		
		def grantAllocationSplitList=GrantAllocationSplit.findAll("from GrantAllocationSplit  GA where  GA.projects="+projectId);
		
		return grantAllocationSplitList
	}
	
	
	
	/**
	 * Function to delete grant allocation split.
	 */
	public Integer deleteGrantAllocationSplit(Integer grantAllocationSplitId){
		Integer projectId = null
		def grantAllocationSplitInstance = getGrantAllocationSplitById(grantAllocationSplitId )
		if(grantAllocationSplitInstance) {
            grantAllocationSplitInstance.delete()
            projectId = grantAllocationSplitInstance.projects.id
		}
		else
			projectId = -1
			
		return projectId
	}
	
	/**
	 * Function to update grant allocation.
	 */
	public GrantAllocationSplit updateGrantAllocationSplit(def grantAllocationSplitParams){
		def grantAllocationSplitInstance = getGrantAllocationSplitById( new Integer(grantAllocationSplitParams.id ))
		if(grantAllocationSplitInstance) {
            grantAllocationSplitInstance.properties = grantAllocationSplitParams
            if(!grantAllocationSplitInstance.hasErrors() && grantAllocationSplitInstance.save()) {
            	grantAllocationSplitInstance.isSaved = true
            }
            else
            	grantAllocationSplitInstance.isSaved = false
		}
		return grantAllocationSplitInstance
	}
	
	/**
	 * Function to save grant allocation split.
	 */
	public GrantAllocationSplit saveGrantAllocationSplit(def grantAllocationSplitInstance,Integer projectId){
		def grantAllocationService = new GrantAllocationService()
		def projects=Projects.get(projectId);
		
		grantAllocationSplitInstance.projects=projects
		if(grantAllocationSplitInstance.save())
			grantAllocationSplitInstance.isSaved = true
		else
			grantAllocationSplitInstance.isSaved = false
			
		return grantAllocationSplitInstance
	}
	public List validateGrantAllocationSplit(def grantAllocationSplitInstance,def projectId)
	{
       def grantAllocationSplitInstanceCheck = GrantAllocationSplit.findAll("from GrantAllocationSplit GAS where GAS.accountHead.id='"+grantAllocationSplitInstance.accountHead.id+"' and GAS.grantPeriod.id= '"+grantAllocationSplitInstance.grantPeriod.id+"' and GAS.projects.id="+projectId)
       return grantAllocationSplitInstanceCheck
	}
//	Function to get Fund allocated account head with grant period
	 public GrantAllocationSplit[] getAccountHeadByProject(def projectId)
	 {
	        def accountHeadList=GrantAllocationSplit.findAll("from GrantAllocationSplit GS where GS.projects.id="+projectId+"  order by GS.accountHead.code asc")
	        for(int i=0;i<accountHeadList.size();i++ )
	        {
	        	accountHeadList[i].accHeadPeriod=accountHeadList[i].accountHead.name.toUpperCase()+" ("+accountHeadList[i].grantPeriod.name+")"
	        }
	        return accountHeadList
	 }

	 public List getAccountHeadByNameandCode(def projectId)
	 {
		 def accountHeadList=AccountHeads.findAll("from AccountHeads AH where AH.parent.id is NULL and AH.activeYesNo='Y' order by AH.name")
		 for(int i=0;i<accountHeadList.size();i++ )
	        {
			 println"drtfgretr"
	        	accountHeadList[i].accHeadCode=accountHeadList[i].code+" -"+accountHeadList[i].name
	        }
		 println"accountHeadList"+accountHeadList
	        return accountHeadList
	 }
	 
	 public List getSubAccountHeadByNameandCode(def params)
	 {
		 def subAccountHead=AccountHeads.findAll("from AccountHeads AH where AH.activeYesNo='Y' and AH.parent.id="+params.accountHead)
		 for(int i=0;i<subAccountHead.size();i++ )
	        {
			 println"drtfgretr"
			 subAccountHead[i].accHeadCode=subAccountHead[i].code+" -"+subAccountHead[i].name
	        }
		 println"subAccountHead"+subAccountHead
	        return subAccountHead
	 }
public List getAccountHeadOfProject(def projectId)
{
	
	def accountHeadList=GrantAllocationSplit.executeQuery("select GS.accountHead from GrantAllocationSplit GS where GS.projects.id="+projectId+"and GS.grantPeriod.defaultYesNo='Y'order by GS.accountHead.name asc")
	def grantPeriodList=GrantAllocationSplit.executeQuery("select GS.grantPeriod from GrantAllocationSplit GS where GS.projects.id="+projectId+"and GS.grantPeriod.defaultYesNo='Y'")
println"....accountHeadList..."+accountHeadList
	 for(int i=0;i<accountHeadList.size();i++ )
	      {
		
		
	      accountHeadList[i].accHeadPeriod=accountHeadList[i].name.toUpperCase()+"("+grantPeriodList[i].name+")"
	      println"accountHeadList[i].accHeadPeriod"+accountHeadList[i].accHeadPeriod
	      }
	
	return accountHeadList
}
	/**
	 * Get the list of grant allocation split based on grant period
	 */
	public GrantAllocationSplit[] getGrantAllocationSplitBasedOnGrantPeriod(def grantPeriodParams)
	{
		def grantAllocationSplitInstanceList=GrantAllocationSplit.findAll("FROM GrantAllocationSplit GAS WHERE GAS.grantPeriod="+grantPeriodParams.id)
		return grantAllocationSplitInstanceList
	}
	
	/**
	 * Get the list of grant allocation split based on account head
	 */
	public GrantAllocationSplit[] getGrantAllocationSplitBasedOnAccountHead(def accountHeadsParams)
	{
		def grantAllocationSplitInstanceList=GrantAllocationSplit.findAll("from GrantAllocationSplit GAS where GAS.accountHead = "+accountHeadsParams.id)
		return grantAllocationSplitInstanceList
	}
}
	
