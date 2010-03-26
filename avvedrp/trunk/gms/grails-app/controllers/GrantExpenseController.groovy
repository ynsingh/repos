import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import java.text.SimpleDateFormat
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
            return [ projectsInstance:projectsInstance,grantExpenseInstance : grantExpenseInstance,grantExpenseInstanceList:grantExpenseInstanceList,grantExpenseSummaryList:grantExpenseSummaryList ]
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
		
    	/* Get grant allocation details. */
    	println "*********************************************create params.id "+params.id
    	 def projectsInstance = Projects.get(new Integer(params.id))
    	 
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
        return ['projectsInstance':projectsInstance,'grantExpenseInstance':grantExpenseInstance,'grantExpenseInstanceList':grantExpenseInstanceList,'grantExpenseSummaryList':grantExpenseSummaryList,'grantAllocationInstanceList':grantAllocationInstanceList]
		}
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
    	/* Get already allocated expenses */
    	def sdf = new SimpleDateFormat('dd/MM/yyyy')
		def sdf1 = new SimpleDateFormat('yyyy/MM/dd')
        def dateFrom = new Date()
    	def dateTo = new Date()
    		
    	if(params.dateOfExpenseFrom)
    		dateFrom = sdf.parse(params.dateOfExpenseFrom_value)
    		
		if(params.dateOfExpenseTo)
    		dateTo = sdf.parse(params.dateOfExpenseTo_value)
    		println "datefrom  "+sdf1.format(dateFrom)+"  dateTo "+sdf1.format(dateTo)+"  params.grantExpenseId "+params.grantExpenseId	
    		println "datefrom  "+dateFrom+"  dateTo "+dateTo+"  params.grantExpenseId "+projectsInstance	
    	
    	def grantExpenseInstanceList = grantExpenseService.getGrantExpenseByProjectsAndExpenseDateRange(params.grantExpenseId,projectsInstance,dateFrom,dateTo)
    		println "datefrom"+grantExpenseInstanceList
        def grantExpenseInstance = new GrantExpense()
    	grantExpenseInstance.projects = projectsInstance
        grantExpenseInstance.properties = params
        return ['projectsInstance':projectsInstance,'grantExpenseInstance':grantExpenseInstance,'grantExpenseInstanceList':grantExpenseInstanceList]
		}
    }
    
    def listSummaryExpenses = {
		def grantExpenseService = new GrantExpenseService()
		def grantAllocationService = new GrantAllocationService()
		
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
    	
        return ['projectsInstance':projectsInstance,'grantExpenseSummaryList':grantExpenseSummaryList]
    }
    }
    
}
