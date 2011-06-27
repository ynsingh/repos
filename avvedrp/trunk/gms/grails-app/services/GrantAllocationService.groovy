import java.text.SimpleDateFormat;
import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import org.springframework.security.acls.model.Permission
import org.springframework.transaction.annotation.Transactional
import java.util.Date;
import java.text.DecimalFormat;
import java.text.NumberFormat;



class GrantAllocationService {
	
	static transactional = false
	
	
	def aclService
	def aclUtilService
	def springSecurityService
	def projectsService

	
	
	
	
	@PostFilter("hasPermission(filterObject, 'read') or hasPermission(filterObject, admin)")
	List<GrantAllocation> getAll() {
		println "Returning all GrantAllocation"
		log.debug 'Returning all GrantAllocation'
		GrantAllocation.list()
	}


	@PreAuthorize("hasPermission(#id, 'GrantAllocation', read) or hasPermission(#id, 'GrantAllocation', admin)")
	GrantAllocation getById(Long id) {
		log.debug "Returning GrantAllocation with id: $id"
		GrantAllocation.get id
	}
	
	
	/**
	 * Get all grant allocation for projects and parties
	 */
	public GrantAllocation[] getGrantAllocationsForProjectAndParty(String projectId,String partyId,String subQuery){
		 def grantAllocationInstanceList=GrantAllocation.findAll("from GrantAllocation GA where GA.projects in "+projectId+" and GA.party in "+partyId+subQuery);
		 return grantAllocationInstanceList
	}
	
	/**
	 * Get all grant allocation by using projectsId and partiesId
	 */
	public GrantAllocation[] getGrantAllocationsByProjectIdAndPartyId(def projectId,def partyId){
		 def grantAllocationInstanceList=GrantAllocation.findAll("from GrantAllocation GA where GA.projects = "+projectId+" and GA.party = "+partyId);
		 return grantAllocationInstanceList
	}
	
	/**
	 * Get all grant allocation (where allocation type not fund) for projects 
	 */
	public GrantAllocation[] getGrantAllocationsForProject(def projectId){
		 def sumAmt = GrantAllocation.executeQuery("select sum(GA.amountAllocated) as total from GrantAllocation  GA where   GA.projects= "+projectId+" group by GA.projects");
		 return grantAllocationInstanceList
	}
	
	
	
	/**
	 * Get all grant allocation (where allocation type not fund) for projects 
	 */
	public GrantAllocation[] getGrantAllocationsForAssignedProject(def projectId){
		 def grantAllocationInstanceList = GrantAllocation.executeQuery(" from GrantAllocation GA where GA.projects IN ("+projectId +")");
		 return grantAllocationInstanceList
	}
	
	/**
	 * Get all grant allocations with no status or status open for projects and parties
	 */
	public GrantAllocation[] getValidGrantAllocationsForProjectAndParty(String projectId,String partyId,String subQuery){
		 //def subQuery="";
		
		 def grantAllocationInstanceInitialList=getGrantAllocationsForProjectAndParty(projectId,partyId,subQuery)
		 def grantAllocationInstanceList = []
		
		 /* Check the grant allocation status in grant allocation tracking */
		 for(grantAllocationInstance in grantAllocationInstanceInitialList){
			 def grantAllocationTrackingList = GrantAllocationTracking.findAllByGrantAllocationAndGrantAllocationStatusNotEqual(grantAllocationInstance,"Open")
			 if(!grantAllocationTrackingList)
				 grantAllocationInstanceList.add(grantAllocationInstance)
		 }
		 return grantAllocationInstanceList
	}
	
	/**
	 * Get grant allocation by ID
	 */
	public GrantAllocation getGrantAllocationById(Integer grantAllocationId){
		 def grantAllocationInstance = GrantAllocation.get( grantAllocationId )
		 return grantAllocationInstance
	}
	
	
	/**
	 * Get total Amount allocated for the Project
	 */
	public double getTotalAmountAllocatedfortheProject(Integer ptojectID){
		 def grantAllocationInstance = GrantAllocation.get( grantAllocationId )
		 return grantAllocationInstance
	}
	
	/**
	 * Delete grant allocation
	 */
	public Integer deleteGrantAllocation(Integer grantAllocationId){
		 Integer grantAllocationDeletedId = null
		 def projectsService = new ProjectsService()
		 def grantAllocationInstance = getGrantAllocationById( grantAllocationId )
		 if(grantAllocationInstance) {
			
			 def accessInstance = projectsService.deleteProjectAccessPermission(grantAllocationId)
	            
			 def projectInstance=Projects.get(grantAllocationInstance.projects.id)
			 println "grantAllocationId  "+grantAllocationId
			 grantAllocationInstance.delete()
           if(getGrantAllocationsByProject(projectInstance.id).size()==0)
        	  projectInstance.delete();
            grantAllocationDeletedId = grantAllocationInstance.id
		 }
		 else{
			 grantAllocationDeletedId = -1
		 }
		 return grantAllocationDeletedId
	}
	
	/**
	 * save grant allocation
	 */
	public GrantAllocation saveGrantAllocation(def grantAllocationInstance,def projectId){
		def projects=Projects.find("from Projects  PA where  PA.id="+projectId)
		grantAllocationInstance.projects=projects;
		grantAllocationInstance.save();
		grantAllocationInstance.isSaved = true
        
		return grantAllocationInstance
            
	}
	
	/**
	 * Update grant allocation
	 */
	public GrantAllocation updateGrantAllocation(def grantAllocationParams){
		def grantAllocationInstance = GrantAllocation.get( grantAllocationParams.id )
        if(grantAllocationInstance) {
        	grantAllocationInstance.properties = grantAllocationParams
            if(!grantAllocationInstance.hasErrors() && grantAllocationInstance.save()) {
            	grantAllocationInstance.isSaved = true
            }
            else
            	grantAllocationInstance.isSaved = false
        }
		return grantAllocationInstance
            
	}
	
	/**
	 * Get grant allocation after sub project allocation.
	 */
	public GrantAllocation getGrantAllocationAfterSubProjectAllocation(GrantAllocation grantAllocationInstance){
		def grantAllocation
		
			grantAllocation=GrantAllocation.find("from GrantAllocation  GA where  GA.projects= "+grantAllocationInstance.projects.parent.id);
		
		return grantAllocation
	}
	
	/**
	 * Get sum of amount allocated for a project 
	 */
	public double getSumOfAmountAllocatedForProject(def projectId,def partyId){
		 double totalAmt = 0.0
		 def sumAmt = GrantAllocation.executeQuery("select sum(GA.amountAllocated) as total from GrantAllocation  GA where   GA.projects= "+projectId+" and GA.party= "+partyId+" group by GA.projects");
		 if(sumAmt[0]!=null)
			 totalAmt = new Double(sumAmt[0]).doubleValue()
			 
		 return totalAmt
	}
	
	/**
	 * Function to get sub grant allocations
	 */
	public GrantAllocation[] getSubGrantAllocationInSort(Integer projectId,String subQuery){
		 
		def grantAllocation=getGrantAllocationsByProject(projectId)
		
		 def grantAllocationInstanceList = []
		 def grantAllocationInstanceInitialList=GrantAllocation.findAll("from GrantAllocation  GA where  GA.projects.parent= "+projectId+""+subQuery);
		 println grantAllocationInstanceInitialList
		/* Check the grant allocation status in grant allocation tracking */
		for(grantAllocationInitialInstance in grantAllocationInstanceInitialList){
			def grantAllocationTrackingList = GrantAllocationTracking.findAllByGrantAllocationAndGrantAllocationStatusNotEqual(grantAllocationInitialInstance,"Open")
			if(!grantAllocationTrackingList)
				grantAllocationInstanceList.add(grantAllocationInitialInstance)
		}
		 
		 return grantAllocationInstanceList
	}
	
	public GrantAllocation[] getSubGrantAllocations(Integer projectId){
		 
		def grantAllocation=getGrantAllocationsByProject(projectId)
		
		 def grantAllocationInstanceList = []
		 def grantAllocationInstanceInitialList=GrantAllocation.findAll("from GrantAllocation  GA where  GA.projects.parent= "+projectId+"");
		 println grantAllocationInstanceInitialList
		/* Check the grant allocation status in grant allocation tracking */
		for(grantAllocationInitialInstance in grantAllocationInstanceInitialList){
			def grantAllocationTrackingList = GrantAllocationTracking.findAllByGrantAllocationAndGrantAllocationStatusNotEqual(grantAllocationInitialInstance,"Open")
			if(!grantAllocationTrackingList)
				grantAllocationInstanceList.add(grantAllocationInitialInstance)
		}
		 
		 return grantAllocationInstanceList
	}
	
	/**
	 * Function to get sub grant allocations
	 */
	public GrantAllocation[] getSubGrantAllocationsChild(Integer projectId,def grantorID,String subQuery){
		 
		def grantAllocation=getGrantAllocationsByProject(projectId)
		
		 def grantAllocationInstanceList = []
		 def grantAllocationInstanceInitialList=GrantAllocation.findAll("from GrantAllocation  GA where  GA.granter= "+grantorID+" and GA.projects.parent="+projectId+""+subQuery);
		 println grantAllocationInstanceInitialList
		/* Check the grant allocation status in grant allocation tracking */
		for(grantAllocationInitialInstance in grantAllocationInstanceInitialList){
			def grantAllocationTrackingList = GrantAllocationTracking.findAllByGrantAllocationAndGrantAllocationStatusNotEqual(grantAllocationInitialInstance,"Open")
			if(!grantAllocationTrackingList)
				grantAllocationInstanceList.add(grantAllocationInitialInstance)
		}
		 
		 return grantAllocationInstanceList
	}
	
	/**
	 * Function to get all grant allocations by allocation type.
	 */
	 public GrantAllocation[] getGrantAllocations(String partyID,String subQuery){
		 
		 def subQry = "";
		 def grantAllocationInstanceList=GrantAllocation.findAll("from GrantAllocation  GA where  GA.granter is null and GA.party in "+partyID+""+subQuery);
		 
		 return grantAllocationInstanceList
	}
	
	 /**
		 * Function to get all grant allocations by in a project.
		 */
		 public GrantAllocation[] getGrantAllocationsByProject(def projectId){
			 def subQry = "";
			
			 def grantAllocationInstanceList=GrantAllocation.findAll("from GrantAllocation  GA where  GA.projects = "+projectId);
			 
			     def  formatter = new SimpleDateFormat("dd/MM/yy");
			   

			 for(int i=0;i<grantAllocationInstanceList.size();i++)
			 {
				 String s = formatter.format(grantAllocationInstanceList[i].DateOfAllocation);
				
				 
				  def numformatter = new DecimalFormat("#0.00");
				  println numformatter.format(grantAllocationInstanceList[i].amountAllocated)
    
				 grantAllocationInstanceList[i].grantCode=s+"-"+numformatter.format(grantAllocationInstanceList[i].amountAllocated)
			 }
			 return grantAllocationInstanceList
		}
	 
	 public GrantAllocation[] getGrantAllocationsByProjectCode(def projectId)
	 {
		 def subQry = "";
		 
			
		 def grantAllocationInstanceList=GrantAllocation.findAll("from GrantAllocation  GA where  GA.projects = "+projectId);
		 for(int i=0;i<grantAllocationInstanceList.size();i++)
		 {
			 String s=grantAllocationInstanceList[i].projects.code;
			 println"***********s**********"+s
			 def numformatter = new DecimalFormat("#0.00");
			  println numformatter.format(grantAllocationInstanceList[i].amountAllocated)

			 grantAllocationInstanceList[i].grantCode=s+"-"+numformatter.format(grantAllocationInstanceList[i].amountAllocated)
		 }
		 return grantAllocationInstanceList
		
	 }
	
	/**
	 * Function to check whether fund is allocated for same project and party.
	 */
	public Integer checkDuplicateFundAllot(def grantAllocationInstance){
		def fundAllotId = 0
    	System.out.println("DuplicateFundAllot__Projid  "+grantAllocationInstance.projects.id+"  partyid "+grantAllocationInstance.party.id)
    	def chkFundAllotInstance = GrantAllocation.find("from GrantAllocation GA where GA.projects= "+grantAllocationInstance.projects.id)
    	if(chkFundAllotInstance)
    		fundAllotId = chkFundAllotInstance.id
    		
    	return fundAllotId
    }
	
	/**
	 * Function to save fund allocation.
	 */
	public Integer saveFundAllocation(def grantAllocationInstance){
		Integer fundAllotId = null
		println grantAllocationInstance
        	if(grantAllocationInstance.save())
        		fundAllotId = grantAllocationInstance.id
	   
        		println fundAllotId
    	return fundAllotId
	}
	
	/**
	 * Function to save sub grant allocation.
	 */
	public GrantAllocation saveSubGrantAllocation(def grantAllocationInstance){
		
		def chkhdallocinstance=GrantAllocationSplit.findAll("from GrantAllocationSplit GS where GS.projects="+grantAllocationInstance.projects.parent.id)
		println"*********chkhdallocinstance***********"+chkhdallocinstance[0]
		if(chkhdallocinstance[0])
		{
		
		grantAllocationInstance.save()
	    return grantAllocationInstance    
		
	}
	}
	public GrantAllocation subGrantSaveExt(def grantAllocationInstance){
		Integer fundAllotId = null
		println grantAllocationInstance
    	if(grantAllocationInstance.save())
    		fundAllotId = grantAllocationInstance.id
   
    		println fundAllotId
	}
	
	/**
	 * Function to get grant allocation tracking by grant allocation
	 */
	public GrantAllocationTracking getGrantAllocationTrackingByGrantAllocation(def grantAllocationInstance){
		def grantAllocationTrackingInstance = GrantAllocationTracking.findByGrantAllocation(grantAllocationInstance)
		return grantAllocationTrackingInstance
	}
	
	/**
	 * Function to get grant allocation tracking by Id.
	 */
	public GrantAllocationTracking getGrantAllocationTrackingById(Integer grantAllocationTrackingId){
		def grantAllocationTrackingInstance = GrantAllocationTracking.get( grantAllocationTrackingId )
		return grantAllocationTrackingInstance
	}
	
	
	
	/**
	 * Function to get month wise Expense and Reciept.
	 */
	public ArrayList getMonthWiseExpenseAndReciept(Integer grantAllocationId){
		def grantAllocationTrackingInstance = GrantAllocationTracking.get( grantAllocationTrackingId )
		return grantAllocationTrackingInstance
	}
	
	/**
	 * Function to save or update grant allocation tracking
	 */
	public GrantAllocationTracking saveOrUpdateGrantAllocationTracking(def grantAllocationTrackingParams){
		def grantAllocationTrackingInstance
		def saveMode
		
		/* Check whether save or update */
    	if(grantAllocationTrackingParams.id && grantAllocationTrackingParams.id != null){
    		saveMode = "Updated"
    		grantAllocationTrackingInstance = getGrantAllocationTrackingById(new Integer( grantAllocationTrackingParams.id ))
    		grantAllocationTrackingInstance.properties = grantAllocationTrackingParams
    	}
    	else{
    		saveMode = "Created"
			grantAllocationTrackingParams.createdBy = "admin"
			grantAllocationTrackingParams.createdDate = new Date()
    		grantAllocationTrackingInstance = new GrantAllocationTracking(grantAllocationTrackingParams)
    	}
		
		println "*grantallocationtrackinginstance "+grantAllocationTrackingInstance
		if(!grantAllocationTrackingInstance.hasErrors() && grantAllocationTrackingInstance.save()) {
			grantAllocationTrackingInstance.saveMode = saveMode
		}
		return grantAllocationTrackingInstance
	}
	/**
	 * Function to check whether grant allotted for particular project
	 */
	public ArrayList checkGrantAllotted(def params)
	{
		println "+++++++++++++++++++++++inside checkGrantAllotted++++++++++++++++++++++++"
		def grantAllocation = GrantAllocation.find("from GrantAllocation GA,Projects P where P.parent.id = "+params.get("projects.id") + " and P.id = GA.projects.id");
		println "+++++++++++++++++++++++ grantAllocation++++++++++++++++++++++++" + grantAllocation
		return grantAllocation;
	}
	/**
	 * Function to check whether the headwise allocation is done.
	 */
	public GrantAllocationSplit getGrantAllocationSplit(def grantAllocationInstance){
		
		def grantAllocationSplitInstance=GrantAllocationSplit.find("from GrantAllocationSplit GS where GS.grantAllocation="+grantAllocationInstance.id)
		println"*********grantAllocationSplitInstance***********"+grantAllocationSplitInstance
		
	    return grantAllocationSplitInstance    
		
	}
	/**
	 * Function to check whether expense is entered against grant allocation.
	 */
	public GrantExpense getExpenseForGrantAllocation(def grantAllocationInstance){
		
		def grantExpenseInstance=GrantExpense.find("from GrantExpense GE where GE.grantAllocation="+grantAllocationInstance.id)
		println"*********grantAllocationSplitInstance***********"+grantExpenseInstance
		
	    return grantExpenseInstance    
		
	}
	/**
	 * Function to check whether the funnd received against the fund allocation.
	 */
	public GrantReceipt getGrantReceiptForGrantAllocation(def grantAllocationInstance){
		
		def grantReceiptInstance=GrantReceipt.find("from GrantReceipt GR where GR.grantAllocation="+grantAllocationInstance.id)
		println"*********grantAllocationSplitInstance***********"+grantReceiptInstance
		
	    return grantReceiptInstance    
		
	}
	
	public FundTransfer getFundTransferForGrantAllocation(def grantAllocationInstance)
	{
		def fundTransferInstance=FundTransfer.find("from FundTransfer FT where FT.grantAllocation="+grantAllocationInstance.id)
		return fundTransferInstance
	}
	/**
	 * Get all grant allocation for logged in user groupBy Projects 
	 */
	@PostFilter("hasPermission(filterObject, 'read') or hasPermission(filterObject, admin)")
	public GrantAllocation[] getGrantAllocationGroupByProjects(def partyID){
		 def grantAllocationInstanceList=GrantAllocation.findAll("from GrantAllocation GA where GA.projects.activeYesNo='Y' GROUP BY GA.projects");
		 return grantAllocationInstanceList
	}
	/**
	 * Get all grant allocation with active projects for logged in user groupBy Projects 
	 */
	@PostFilter("hasPermission(filterObject, 'read') or hasPermission(filterObject, admin)")
	public GrantAllocation[] getGrantAllocationByActiveProjects(){
		 def grantAllocationInstanceList=GrantAllocation.findAll("from GrantAllocation GA where GA.projects.activeYesNo='Y' GROUP BY GA.projects");
		 return grantAllocationInstanceList
	}
	 public GrantAllocation getGrantAllocationByProjects(def projectID){
		 def grantAllocationInstance=GrantAllocation.find("from GrantAllocation GA where GA.projects.id = "+projectID +" GROUP BY GA.projects");
		 return grantAllocationInstance
	}
	 
	 /*
	  * Function to check grantAllocation is there in GrantAllocationSplit
	  */
	 public GrantAllocation checkGrantAllocationSplitByProjectId(def grantAllocationInstance){
			
			def chkhdallocinstance=GrantAllocationSplit.findAll("from GrantAllocationSplit GS where GS.projects="+grantAllocationInstance.projects.parent.id)
			println"*********chkhdallocinstance***********"+chkhdallocinstance[0]
			if(chkhdallocinstance[0])
			{
			
			
		    return grantAllocationInstance    
			
			}
	 }
			
	public List getGrantAllocationInstanceForParentProject(def projectsInstance)
	{
	def grantInstance = GrantAllocation.findAll("from GrantAllocation GA where GA.projects="+projectsInstance.parentId)
	return grantInstance
	}
	/*
	 *Method to get all closed projects list 
	 */
	  public getClosedProject(def projectId)
	 {
		  def projectTrackingInstanceCheck=ProjectTracking.find("from ProjectTracking PT where PT.projectStatus='Closed'and PT.projects.id="+projectId)
		  return projectTrackingInstanceCheck
	 }
	/*
	 * Getting grantAllocation list using Party Id.
	 */
	public getGrantAllocationByPartyIdGroupByProjects(def PartyID)
	{
		def grantAllocationInstanceList = GrantAllocation.findAll("from GrantAllocation GA where GA.party.id="+PartyID+"GROUP BY GA.projects")	
		return grantAllocationInstanceList
	}
	/*
	 * save new grantAllocation
	 */
	 public saveNewGrantAllocation(def grantAllocationInstance)
	{
		grantAllocationInstance.code="default"
  		grantAllocationInstance.allocationType=""
  		grantAllocationInstance.createdBy=""
  		grantAllocationInstance.modifiedBy=""
		
		if(grantAllocationInstance.save())
		{
			
		}
		else
		{
			
		}
		return grantAllocationInstance
	}
	 /**
	  * Get sum of grant allocation for projects 
	  */
		public def getGrantAllocationsSumForProject(def projectId){
			 def grantAllocationInstance=GrantAllocation.executeQuery("select SUM(GA.amountAllocated) from GrantAllocation GA where GA.projects.id="+projectId+" group by GA.projects.id");
			 return grantAllocationInstance
		}
	
	
	
	
	
	
}