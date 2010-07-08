import java.text.*;
import java.util.*;
import ConvertToIndainRS
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class GrantReceiptController extends GmsController {
    
    def index = { redirect(action:list,params:params) }
      
    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        println"params"+params
        
        def grantReceiptService = new GrantReceiptService();
        def grantReceiptInstanceList = grantReceiptService.getGrantReceiptByParams(params)
        println"params"+params
        [ grantReceiptInstanceList: grantReceiptInstanceList ]
    }

    def show = {
		def grantReceiptService = new GrantReceiptService();
        def grantReceiptInstance = grantReceiptService.getGrantReceiptById(new Integer(params.id))

        if(!grantReceiptInstance) {
            flash.message = "GrantReceipt not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ grantReceiptInstance : grantReceiptInstance ] }
    }

    def delete = {
		def grantReceiptService = new GrantReceiptService();
		Integer projectid = grantReceiptService.deleteGrantReceipt(new Integer(params.id))
		
		if(projectid != null){
			if(projectid > 0){
				flash.message = "GrantReceived deleted"
					redirect(action:create,id:projectid)
			}
		}
		else {
            flash.message = "GrantReceipt not found with id ${params.id}"
            redirect(action:list)
		}
    }

    def edit = {
		def grantReceiptService = new GrantReceiptService();
		def grantAllocationService = new GrantAllocationService()
        def grantReceiptInstance = grantReceiptService.getGrantReceiptById(new Integer(params.id))
         def dataSecurityService = new DataSecurityService()
		def grantAllocationSplitService=new GrantAllocationSplitService()
       def accountHeadList=grantAllocationSplitService.getAccountHeadByProject(grantReceiptInstance.projects.id)

       //checking  whether the user has access to the given projects
		if(dataSecurityService.checkForAuthorisedAcsessInProjects(grantReceiptInstance.projects.id,new Integer(getUserPartyID()))==0)
		{
			
					
					 redirect uri:'/invalidAccess.gsp'

		}
		else
		{
        if(!grantReceiptInstance) {
            flash.message = "GrantReceipt not found with id ${params.id}"
            redirect(action:list)
        }
        else {
        	def totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProject(grantReceiptInstance.projects.id,getUserPartyID())
        	def totalAmountReceived = grantReceiptService.getSumOfGrantReceviedByProjects(grantReceiptInstance.projects.id)
            grantReceiptInstance.balanceAmt = totAllAmount - totalAmountReceived - grantReceiptInstance.amount
            return [ grantReceiptInstance : grantReceiptInstance,accountHeadList:accountHeadList]
        }
		}
    }

    def update = {
		def grantReceiptService = new GrantReceiptService();
        def grantReceiptInstance = grantReceiptService.updateGrantReceipt(params)
        if(grantReceiptInstance) {
        	if(grantReceiptInstance.isSaved){
        		flash.message = "Grant Recieved updated"
    			redirect(action:create,id:grantReceiptInstance.projects.id)
        	}
        	else {
                render(view:'edit',model:[grantReceiptInstance:grantReceiptInstance])
            }
        }
        else {
            flash.message = "GrantReceipt not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
		def grantReceiptService = new GrantReceiptService();
		def grantAllocationService = new GrantAllocationService()
    	def grantAllocationSplitService=new GrantAllocationSplitService()	
        def grantReceiptInstance = new GrantReceipt()
        GrailsHttpSession gh=getSession()
        gh.removeValue("Help")
       		//putting help pages in session
       	gh.putValue("Help","Grant_Receipt.htm")
        grantReceiptInstance.properties = params
        println"++++++++++++++++++params+++++++"+params
        
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
		String subQuery="";
        if(params.sort != null && !params.sort.equals(""))
       	subQuery=" order by GA."+params.sort
       if(params.order != null && !params.order.equals(""))
       	subQuery =subQuery+" "+params.order
   
        grantReceiptInstance.projects=projectsInstance
        def accountHeadList=grantAllocationSplitService.getAccountHeadByProject(grantReceiptInstance.projects.id)
        def grantReceiptList=grantReceiptService.getGrantReceiptByProjects(params.id,subQuery)
        
        //def grantRecieptInstanceList=grantReceiptService.getGrantReceiptBySort(params.id,subQuery)
        
        def totalAmountReceived = grantReceiptService.getSumOfGrantReceviedByProjects(params.id)
        
        grantReceiptInstance.balanceAmt = projectsInstance.totAllAmount - totalAmountReceived
        
        	
        def summaryList =  grantReceiptService.getGrantReceiptSummary(params.id)
        grantReceiptInstance.grantAllocation = grantAllocationInstanceList[0]
        ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
        return ['grantReceiptInstance':grantReceiptInstance,'grantReceiptInstanceList':grantReceiptList,'summaryList':summaryList,'grantAllocationInstanceList':grantAllocationInstanceList,'accountHeadList':accountHeadList,'currencyFormat':currencyFormatter]
		}
    }

    def save = {
    		println params
		def grantReceiptService = new GrantReceiptService();
        def grantReceiptInstance = new GrantReceipt(params)
        if(!grantReceiptInstance.hasErrors() ) {
        	
    	    grantReceiptInstance.createdBy="admin"
    		grantReceiptInstance.modifiedBy="admin"
        	grantReceiptInstance.modifiedBy="admin"
    		grantReceiptInstance.createdDate=new Date()
        	grantReceiptInstance = grantReceiptService.saveGrantReceipt(grantReceiptInstance,new Integer(params.projectId))
        	flash.message = "Grant Recieved"
        	redirect(action:create,id:grantReceiptInstance.projects.id)
        }
        else {
            render(view:'create',model:[grantReceiptInstance:grantReceiptInstance])
        }
    }
}
