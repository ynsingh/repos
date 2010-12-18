class EligibilityCriteriaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [eligibilityCriteriaInstanceList: EligibilityCriteria.list(params), eligibilityCriteriaInstanceTotal: EligibilityCriteria.count()]
    }

    def create = {
        def eligibilityCriteriaInstance = new EligibilityCriteria()
        eligibilityCriteriaInstance.properties = params
        def eligibilityCriteriaInstanceList=EligibilityCriteria.findAll("from EligibilityCriteria EC")
        return [eligibilityCriteriaInstance: eligibilityCriteriaInstance,eligibilityCriteriaInstanceList: eligibilityCriteriaInstanceList]
    }

    def save = {
    	def salaryComponentService = new SalaryComponentService()
    	def eligibilityCriteriaInstance = new EligibilityCriteria(params)
        def chkEligibilityCriteriaInstance = EligibilityCriteria.find("from EligibilityCriteria EC where EC.eligibilityCriteria= '"+params.eligibilityCriteria+"'")
        if(chkEligibilityCriteriaInstance)
        {
        	
        	flash.message ="${message(code: 'default.AlreadyExists.label')}"
    	    redirect(action: "create", id: eligibilityCriteriaInstance.id)
        }
        else
        {
        if (eligibilityCriteriaInstance.save(flush: true)) {
        	flash.message ="${message(code: 'default.created.label')}"
            redirect(action: "create", id: eligibilityCriteriaInstance.id)
        }
        else 
            render(view: "create", model: [eligibilityCriteriaInstance: eligibilityCriteriaInstance])
        }
    }

    def show = {
        def eligibilityCriteriaInstance = EligibilityCriteria.get(params.id)
        if (!eligibilityCriteriaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'eligibilityCriteria.label', default: 'EligibilityCriteria'), params.id])}"
            redirect(action: "list")
        }
        else {
            [eligibilityCriteriaInstance: eligibilityCriteriaInstance]
        }
    }

    def edit = {
        def eligibilityCriteriaInstance = EligibilityCriteria.get(params.id)
        if (!eligibilityCriteriaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'eligibilityCriteria.label', default: 'EligibilityCriteria'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [eligibilityCriteriaInstance: eligibilityCriteriaInstance]
        }
    }

    def update = {
        def eligibilityCriteriaInstance = EligibilityCriteria.get(params.id)
        if (eligibilityCriteriaInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (eligibilityCriteriaInstance.version > version) {
                    
                    eligibilityCriteriaInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'eligibilityCriteria.label', default: 'EligibilityCriteria')] as Object[], "Another user has updated this EligibilityCriteria while you were editing")
                    render(view: "edit", model: [eligibilityCriteriaInstance: eligibilityCriteriaInstance])
                    return
                }
            }
            eligibilityCriteriaInstance.properties = params
            if (!eligibilityCriteriaInstance.hasErrors() && eligibilityCriteriaInstance.save(flush: true)) {
            	flash.message = "${message(code: 'default.updated.label')}"
                redirect(action: "create", id: eligibilityCriteriaInstance.id)
            }
            else {
                render(view: "edit", model: [eligibilityCriteriaInstance: eligibilityCriteriaInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'eligibilityCriteria.label', default: 'EligibilityCriteria'), params.id])}"
            redirect(action: "create")
        }
    }

    def delete = {
        def eligibilityCriteriaInstance = EligibilityCriteria.get(params.id)
        if (eligibilityCriteriaInstance) {
            try {
                eligibilityCriteriaInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.label')}"
                redirect(action: "create")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
            	flash.message = "${message(code: 'default.inuse.label')}"
                redirect(action: "create", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'eligibilityCriteria.label', default: 'EligibilityCriteria'), params.id])}"
            redirect(action: "create")
        }
    }
    }
