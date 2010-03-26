
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
	
}