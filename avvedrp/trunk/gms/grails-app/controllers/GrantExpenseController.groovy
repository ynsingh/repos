import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import java.text.SimpleDateFormat

import java.text.*;
import java.util.*;
import ConvertToIndainRS
class GrantExpenseController extends GmsController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
		GrailsHttpSession gh=getSession()
        if(!params.max) params.max = 10
        
        /* List all grant allocations with account head */
//       [ grantAllocationInstanceList: GrantAllocation.findAll("from GrantAllocation GA where GA.accountHeads IS NOT NULL and GA.allocationType not in ('Fund') and  GA.projects in "+gh.getValue("ProjectID")+" and   GA.party in "+gh.getValue("PartyID")) ]
 
		def dataSecurityService = new DataSecurityService()
		def grantAllocationInstanceList = dataSecurityService.getGrantAllocationsForLoginUser(null,gh.getValue("ProjectID"),gh.getValue("PartyID"))
        [ grantAllocationInstanceList: grantAllocationInstanceList]	
    }

    def show = {
		System.out.println("Params "+params)
    		
		def grantExpenseService = new GrantExpenseService()
        def grantExpenseInstance =  grantExpenseService.getGrantExpenseById(new Integer(params.id))
        
        if(!grantExpenseInstance) {
            flash.message = "Grant Expense not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ grantExpenseInstance : grantExpenseInstance ] }
    }

    def delete = {
		def grantExpenseService = new GrantExpenseService()
		 def grantExpenseInstance =  grantExpenseService.getGrantExpenseById(new Integer(params.id))
		Integer grantAllocationId = grantExpenseService.deleteGrantExpense(new Integer(params.id))
		
		if(grantAllocationId != null){
			if(grantAllocationId > 0){
				flash.message = "Grant Expense ${params.id} deleted"
				redirect(action:create,id:grantExpenseInstance.projects.id)
			}
		}
		else{
			flash.message = "Grant Expense not found with id ${params.id}"
            redirect(action:list)
		}
    }

    def edit = {
		def grantExpenseService = new GrantExpenseService()
		def grantAllocationService = new GrantAllocationService()
		def grantAllocationSplitService=new GrantAllocationSplitService()
	
        def grantExpenseInstance = grantExpenseService.getGrantExpenseById(new Integer(params.id))
         def dataSecurityService = new DataSecurityService()
		//checking  whether the user has access to the given projects
		if(dataSecurityService.checkForAuthorisedAcsessInProjects(grantExpenseInstance.projects.id,new Integer(getUserPartyID()))==0)
		{
			
					
					 redirect uri:'/invalidAccess.gsp'

		}
		else
		{
        
        /* Get grant allocation details. */
        Integer projectId = grantExpenseInstance.projects.id
        def projectsInstance = Projects.get(projectId)
  	     projectsInstance.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProject(projectsInstance.id,getUserPartyID())
		def accountHeadList=grantAllocationSplitService.getAccountHeadByProject(projectsInstance.id)

    	
    	/* Get already allocated expenses */
    	def sdf = new SimpleDateFormat('dd/MM/yyyy') 
        def dateFrom = new Date()
    	def dateTo = new Date()
       
    		
    	if(params.dateFrom_value)
    		dateFrom = sdf.parse(params.dateFrom_value)
    		
		if(params.dateTo_value)
    		dateTo = sdf.parse(params.dateTo_value)
    		
    	def grantExpenseInstanceList = grantExpenseService.getGrantExpenseByProjectsAndExpenseDateRange(params.grantExpenseId,projectsInstance,dateFrom,dateTo)
    	
    	/* Get summary of expenses */
    	def grantExpenseSummaryList = grantExpenseService.getGrantExpenseTotalForAProject(projectsInstance)

        if(!grantExpenseInstance) {
            flash.message = "Grant Expense not found with id ${params.id}"
            redirect(action:list)
        }
        else {
        	grantExpenseInstance.dateFrom = dateFrom
        	grantExpenseInstance.dateTo = dateTo
            return [ projectsInstance:projectsInstance,grantExpenseInstance : grantExpenseInstance,grantExpenseInstanceList:grantExpenseInstanceList,grantExpenseSummaryList:grantExpenseSummaryList,accountHeadList:accountHeadList ]
        }
		}
    }

    def update = {
		def grantExpenseService = new GrantExpenseService()
		def grantExpenseInstance = grantExpenseService.updateGrantExpense(params)
        if(grantExpenseInstance) {
            if(grantExpenseInstance.isSaved){	
                flash.message = " Expense Entry updated"
                redirect(action:create,id:grantExpenseInstance.projects.id)
            }
            else {
                render(view:'edit',model:[grantExpenseInstance:grantExpenseInstance])
            }
        }
        else {
            flash.message = "Grant Expense not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
		def grantExpenseService = new GrantExpenseService()
		def grantAllocationService = new GrantAllocationService()
		def grantAllocationSplitService=new GrantAllocationSplitService()
        GrailsHttpSession gh=getSession()
        gh.removeValue("Help")
       		//putting help pages in session
       	gh.putValue("Help","Grant_Expense.htm")
    	/* Get grant allocation details. */
    	println "*********************************************create params.id "+params.id
    	def projectsInstance
    	if(params.id){
    		projectsInstance = Projects.get(new Integer(params.id))
    	//account head listing based on default grant period
    	def accountHeadList=grantAllocationSplitService.getAccountHeadByProject(projectsInstance.id)

    	  def dataSecurityService = new DataSecurityService()
		//checking  whether the user has access to the given projects
		if(dataSecurityService.checkForAuthorisedAcsessInProjects(projectsInstance.id,new Integer(getUserPartyID()))==0)
		{
			
					
					 redirect uri:'/invalidAccess.gsp'

		}
		else
		{
    	 
   	     projectsInstance.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProject(projectsInstance.id,getUserPartyID())
		def grantAllocationInstanceList=grantAllocationService.getGrantAllocationsByProject(params.id)
    	 
    	/* Get already allocated expenses */
    	def sdf = new SimpleDateFormat('dd/MM/yyyy')
    	def sdf1 = new SimpleDateFormat('yyyy/MM/dd')
        def dateFrom = new Date()
    	def dateTo = new Date()
   	 
		
   	     println"++++++++++++params++++++++++"+params
    	println "new datefrom "	+params.dateFrom+ "new dateTo" +params.dateTo
    	if(params.dateFrom_value){
    		dateFrom = sdf.parse(params.dateFrom_value)
    	}
    		
		if(params.dateTo_value)
    		dateTo = sdf.parse(params.dateTo_value)
    		    	
    	  println "datefrom  "+dateFrom+"  dateTo "+dateTo+"  params.grantExpenseId "+params.grantExpenseId
    	def grantExpenseInstanceList = grantExpenseService.getGrantExpenseByProjectsAndExpenseDateRange(params.grantExpenseId,projectsInstance,dateFrom,dateTo)
    	
    	/* Get summary of expenses */
    	def grantExpenseSummaryList = grantExpenseService.getGrantExpenseTotalForAProject(projectsInstance)
    		
        def grantExpenseInstance = new GrantExpense()
    	grantExpenseInstance.projects = projectsInstance
    	grantExpenseInstance.dateFrom = dateFrom
    	grantExpenseInstance.dateTo = dateTo
        grantExpenseInstance.properties = params
        ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
         return ['projectsInstance':projectsInstance,'grantExpenseInstance':grantExpenseInstance,'grantExpenseInstanceList':grantExpenseInstanceList,'grantExpenseSummaryList':grantExpenseSummaryList,'grantAllocationInstanceList':grantAllocationInstanceList,'currencyFormat':currencyFormatter,'accountHeadList':accountHeadList]
		}
    	}
    	else {    			
    			redirect uri:'/invalidAccess.gsp'}
    }

    def save = {
		def grantExpenseService = new GrantExpenseService()
		params.createdBy="admin"
		params.createdDate = new Date()
        def grantExpenseInstance = new GrantExpense(params)
		
		grantExpenseInstance = grantExpenseService.saveGrantExpense(grantExpenseInstance) 
		if(grantExpenseInstance.isSaved){
            flash.message = "Expense details Created"
            redirect(action:create,id:grantExpenseInstance.projects.id,params:[grantExpenseId:grantExpenseInstance.id])
        }
        else {
            render(view:'create',model:[grantExpenseInstance:grantExpenseInstance])
        }
    }
    
    def listExpenses = {
		def grantExpenseService = new GrantExpenseService()
		
		def grantAllocationService = new GrantAllocationService()
		GrailsHttpSession gh=getSession()
    	 gh.putValue("projDashLink", "projectDash/"+params.id);
		
        gh.removeValue("Help")
       		//putting help pages in session
       	gh.putValue("Help","List_Grant_Expense.htm")
		
    	def projectId
    	/* Get grant allocation details. */
    	if(params.id){
    		projectId = params.id}
    	else if(params.projects){
    		projectId = params.projects.id}
    		else{
    		redirect uri:'/invalidAccess.gsp'}
    	println"%%%params.id%%%"+params.id
    		 def projectsInstance = Projects.get(new Integer(projectId))
    		  
    		 
    		  def dataSecurityService = new DataSecurityService()
		//checking  whether the user has access to the given projects
		if(dataSecurityService.checkForAuthorisedAcsessInProjects(projectsInstance.id,new Integer(getUserPartyID()))==0)
		{
			
					
					 redirect uri:'/invalidAccess.gsp'

		}
		else
		{
   	         projectsInstance.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProject(projectsInstance.id,getUserPartyID())
    	/* Get already allocated expenses */
    	def sdf = new SimpleDateFormat('dd/MM/yyyy')
		def sdf1 = new SimpleDateFormat('yyyy/MM/dd')
        def dateFrom = new Date()
    	def dateTo = new Date()
   	      
   	      
    	if(params.dateOfExpenseFrom)
    		dateFrom = sdf.parse(params.dateOfExpenseFrom_value)
    		
		if(params.dateOfExpenseTo)
    		dateTo = sdf.parse(params.dateOfExpenseTo_value)
    		println "datefromformat  "+sdf1.format(dateFrom)+"  dateTo "+sdf1.format(dateTo)+"  params.grantExpenseId "+params.grantExpenseId	
    		println "datefrom  "+dateFrom+"  dateTo "+dateTo+"  params.grantExpenseId "+projectsInstance	
    	
    	def grantExpenseInstanceList = grantExpenseService.getGrantExpenseByProjectsAndExpenseDateRange(params.grantExpenseId,projectsInstance,dateFrom,dateTo)
    		println "datefrom"+grantExpenseInstanceList
    		 def grantExpenseInstance = new GrantExpense()
   	      println"params********"+params.dateOfExpenseFrom
    	grantExpenseInstance.projects = projectsInstance
    	grantExpenseInstance.dateFrom=dateFrom
    	grantExpenseInstance.dateTo=dateTo
        grantExpenseInstance.properties = params
       
 	        print"grantExpenseInstance.id"+grantExpenseInstance.id
 	        print"--------test-----"+new Integer(params.id)
        ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
 	      
        return ['projectsInstance':projectsInstance,'grantExpenseInstance':grantExpenseInstance,'grantExpenseInstanceList':grantExpenseInstanceList,'currencyFormat':currencyFormatter]
		}
    }
    
    def listSummaryExpenses = {
		def grantExpenseService = new GrantExpenseService()
		def grantAllocationService = new GrantAllocationService()
		GrailsHttpSession gh=getSession()
    
		
        gh.removeValue("Help")
       		//putting help pages in session
       	gh.putValue("Help","Grant_Expense_Summary.htm")
    	/* Get grant allocation details. */
    	def projectId
    	if(params.id)
    		projectId = params.id
    	else
    		projectId = params.projects.id
    		println "projectId"+projectId
    		 def projectsInstance = Projects.get(new Integer(projectId))
    		 
    		  def dataSecurityService = new DataSecurityService()
		//checking  whether the user has access to the given projects
		if(dataSecurityService.checkForAuthorisedAcsessInProjects(projectsInstance.id,new Integer(getUserPartyID()))==0)
		{
			
					
					 redirect uri:'/invalidAccess.gsp'

		}
		else
		{
    	       projectsInstance.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProject(projectsInstance.id,getUserPartyID())
		

    	/* Get summary of expenses */
    	def grantExpenseSummaryList = grantExpenseService.getGrantExpenseSummaryForAProject(projectsInstance)
    	ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
        return ['projectsInstance':projectsInstance,'grantExpenseSummaryList':grantExpenseSummaryList,'currencyFormat':currencyFormatter]
    }
    }
    
   
    
   
   
    
}
