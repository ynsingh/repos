import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class FinancialYearController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }
   
    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [financialYearInstanceList: FinancialYear.list(params), financialYearInstanceTotal: FinancialYear.count()]
    }

    def create = {
		GrailsHttpSession gh=getSession()
		gh.removeValue("Help")
		gh.putValue("Help","Financial_Year.htm")//putting help pages in session
        def financialYearInstance = new FinancialYear()
        def budgetMasterService = new BudgetMasterService()  
        financialYearInstance.properties = params
        /*Get all active Financial Year*/
        def financialYearInstanceList=budgetMasterService.getfinancialYearList()        
        //def financialYearInstanceList = FinancialYear.findAll("from FinancialYear FY where FY.activeYesNo = 'Y'")
        return [financialYearInstance: financialYearInstance,financialYearInstanceList:financialYearInstanceList]
    }

    def save = {
        def financialYearInstance = new FinancialYear(params)
    	def budgetMasterService = new BudgetMasterService()
        /* Method to check duplicate financial Year*/
    	def chkFinancialYearInstanceList = budgetMasterService.checkDuplicateFinancialYear(params)
	    //def financialYearInstanceList= FinancialYear.findAll("from FinancialYear FY where FY.activeYesNo='Y' and FY.financialPeriod='" + financialYearInstance.financialPeriod+"'")
        if(chkFinancialYearInstanceList)
        {

           flash.message ="${message(code: 'default.AlreadyExists.label')}"
	       redirect(action: "create", id: financialYearInstance.id)
        }
         else
         {
         financialYearInstance.activeYesNo="Y" 
         if (financialYearInstance.save()) 
        	 {
            flash.message = "${message(code: 'default.Financialyearcreated.message', args: [message(code: 'financialYear.label', default: 'FinancialYear'), financialYearInstance.id])}"
            redirect(action: "create", id: financialYearInstance.id)
        }
         }
      
    }

    def show = {
        def financialYearInstance = FinancialYear.get(params.id)
        if (!financialYearInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'financialYear.label', default: 'FinancialYear'), params.id])}"
            redirect(action: "list")
        }
        else {
            [financialYearInstance: financialYearInstance]
        }
    }

    def edit = {
        def financialYearInstance = FinancialYear.get(params.id)
        if (!financialYearInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'financialYear.label', default: 'FinancialYear'), params.id])}"
            redirect(action: "create")
        }
        else {
            return [financialYearInstance: financialYearInstance]
        }
    }

    def update = {
    	   def financialYearInstance = FinancialYear.get(params.id)
           def budgetMasterService = new BudgetMasterService()
           if (financialYearInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (financialYearInstance.version > version) {
                    
                    financialYearInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'financialYear.label', default: 'FinancialYear')] as Object[], "Another user has updated this FinancialYear while you were editing")
                    render(view: "edit", model: [financialYearInstance: financialYearInstance])
                    return
                }
            }
            /* Method to check duplicate financial Year*/
            def chkFinancialYearInstanceList = budgetMasterService.checkDuplicateFinancialYear(params)
            if(chkFinancialYearInstanceList && (chkFinancialYearInstanceList[0].id!= Long.parseLong(params.id)))
            {
            	flash.message ="${message(code: 'default.AlreadyExists.label')}"
            	redirect(action: "create", id: financialYearInstance.id)
            }
         else
         {
        	 financialYearInstance.properties = params
           if (!financialYearInstance.hasErrors() && financialYearInstance.save(flush: true))
           {
              flash.message = "${message(code: 'default.Financialyearupdated.message', args: [message(code: 'financialYear.label', default: 'FinancialYear'), financialYearInstance.id])}"
              redirect(action: "create", id: financialYearInstance.id)
           }
          else {
              render(view: "edit", model: [financialYearInstance: financialYearInstance])
          }
         }
      }
  }
       
          
   
    def delete = {
        def financialYearInstance = FinancialYear.get(params.id)
        if (financialYearInstance) {
            try {
                financialYearInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'financialYear.label', default: 'FinancialYear'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'financialYear.label', default: 'FinancialYear'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'financialYear.label', default: 'FinancialYear'), params.id])}"
            redirect(action: "list")
        }
    }
}
