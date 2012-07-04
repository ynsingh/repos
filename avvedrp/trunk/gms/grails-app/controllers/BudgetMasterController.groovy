import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import java.text.*;
import java.util.*;
import grails.converters.deep.JSON
class BudgetMasterController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [budgetMasterInstanceList: BudgetMaster.list(params), budgetMasterInstanceTotal: BudgetMaster.count()]
    }

    def create = {
    	GrailsHttpSession gh=getSession()
		gh.removeValue("Help")
		gh.putValue("Help","Budget_Master.htm")//putting help pages in session
        def budgetMasterInstance = new BudgetMaster()
    	def budgetMasterService = new BudgetMasterService()
    	budgetMasterInstance.properties = params
    	def budgetMasterInstanceList
    	def partyInstance = Party.get(gh.getValue("Party"))
    	/* Method to get All Active Budget Master*/
        budgetMasterInstanceList = budgetMasterService.getBudgetMasterList(partyInstance)
        ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
    	NumberFormat formatter = new DecimalFormat("#0.00");
   
        [budgetMasterInstance:budgetMasterInstance,budgetMasterInstanceList: budgetMasterInstanceList,partyInstance:partyInstance,
         'currencyFormat':currencyFormatter,'amount':formatter.format(budgetMasterInstance.totalBudgetAmount)]
    }

    def save = {
    	GrailsHttpSession gh=getSession()
        def budgetMasterInstance = new BudgetMaster(params)
    	def budgetMasterService = new BudgetMasterService()
    	def partyInstance = Party.get(params.party.id)
    	def budgetMasterInstanceList = budgetMasterService.getParty(partyInstance)
        //def budgetMasterInstanceList = BudgetMaster.findAll("from BudgetMaster BM where BM.activeYesNo = 'Y' and BM.party="+partyInstance.id)
        /* Method to check Duplicate BdgetMaster*/
        def chkbudgetMasterInstanceList = budgetMasterService.checkDuplicateBudgetMaster(params)
        if(chkbudgetMasterInstanceList)
        {

           flash.message ="${message(code: 'default.AlreadyExists.label')}"
	       redirect(action: "create", id: budgetMasterInstance.id)
        }
        else
        {
        	budgetMasterInstance.activeYesNo="Y" 
          if(budgetMasterInstance.save())
        {
            flash.message = "${message(code: 'default.Budgetmastercreated.message', args: [message(code: 'budgetMaster.label', default: 'BudgetMaster'), budgetMasterInstance.id])}"
            redirect(action: "create")
       
        }
        }
       
    }

    def show = {
        def budgetMasterInstance = BudgetMaster.get(params.id)
        if (!budgetMasterInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'budgetMaster.label', default: 'BudgetMaster'), params.id])}"
            redirect(action: "list")
        }
        else {
            [budgetMasterInstance: budgetMasterInstance]
        }
    }

    def edit = {
    	GrailsHttpSession gh=getSession()
    	ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
    	NumberFormat formatter = new DecimalFormat("#0.00");
        def budgetMasterInstance = BudgetMaster.get(params.id)
        def partyInstance = Party.get(gh.getValue("Party"))
        if (!budgetMasterInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'budgetMaster.label', default: 'BudgetMaster'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [budgetMasterInstance: budgetMasterInstance,partyInstance:partyInstance,
                    'currencyFormat':currencyFormatter,'amount':formatter.format(budgetMasterInstance.totalBudgetAmount)]
        }
    }

    def update = {
    	def budgetMasterInstance = BudgetMaster.get(params.id)
        def budgetMasterService = new BudgetMasterService()
        if (budgetMasterInstance) 
        {
	            if (params.version) 
	            {
	                def version = params.version.toLong()
	                if (budgetMasterInstance.version > version)
	                {
	                    
	                    budgetMasterInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'budgetMaster.label', default: 'BudgetMaster')] as Object[], "Another user has updated this BudgetMaster while you were editing")
	                    render(view: "create", model: [budgetMasterInstance: budgetMasterInstance])
	                    return
	                }
	            }
	        /* Method to check Duplicate BdgetMaster*/
            def chkbudgetMasterInstanceList = budgetMasterService.checkDuplicateBudgetMaster(params)
            //def budgetMasterInstanceList= BudgetMaster.find("from BudgetMaster BM where BM.activeYesNo='Y' and BM.title='"+params.title+"'")
            if(chkbudgetMasterInstanceList && (chkbudgetMasterInstanceList.id!= Long.parseLong(params.id)))
              {
        	   flash.message ="${message(code: 'default.AlreadyExists.label')}"
        	   redirect(action: "create", id: budgetMasterInstance.id)
              }
            else
               {        
        		   budgetMasterInstance.properties = params
		             if (!budgetMasterInstance.hasErrors() && budgetMasterInstance.save(flush: true))
		             {
		                flash.message = "${message(code: 'default.Budgetmasterupdated.message', args: [message(code: 'budgetMaster.label', default: 'BudgetMaster'), budgetMasterInstance.id])}"
		                redirect(action: "create", id: budgetMasterInstance.id)
		             }
    	  
		             else
		             {
    	 
	            	     budgetMasterInstance.properties = params
			             if (!budgetMasterInstance.hasErrors() && budgetMasterInstance.save(flush: true))
			             {
			                flash.message = "${message(code: 'default.Budgetmasterupdated.message', args: [message(code: 'budgetMaster.label', default: 'BudgetMaster'), budgetMasterInstance.id])}"
			                redirect(action: "create", id: budgetMasterInstance.id)
			             }
            
			            else 
			            {
			                render(view: "edit", model: [budgetMasterInstance: budgetMasterInstance])
			            }
	                 }
			    }
		          
       }
    }
   

    def delete = {
        def budgetMasterInstance = BudgetMaster.get(params.id)
        if (budgetMasterInstance) {
            try {
                budgetMasterInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'budgetMaster.label', default: 'BudgetMaster'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'budgetMaster.label', default: 'BudgetMaster'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'budgetMaster.label', default: 'BudgetMaster'), params.id])}"
            redirect(action: "list")
        }
    }
    def confirmAssignedBudget =
    {
    	def budgetMasterService = new BudgetMasterService()
    	def getModuleMapInstance = budgetMasterService.getModuleMapByBudgetMasterId(params)
    	if(getModuleMapInstance)
    	       render getModuleMapInstance as JSON
    	
    }
}
