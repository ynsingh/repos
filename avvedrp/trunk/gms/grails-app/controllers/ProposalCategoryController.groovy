class ProposalCategoryController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "create", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [proposalCategoryInstanceList: ProposalCategory.list(params), proposalCategoryInstanceTotal: ProposalCategory.count()]
    }

    def create = {
        def proposalCategoryInstance = new ProposalCategory()
        proposalCategoryInstance.properties = params
        return [proposalCategoryInstance: proposalCategoryInstance]
    }

    def save = {
    	def proposalCategoryService = new ProposalCategoryService()
        def proposalCategoryInstance = new ProposalCategory(params)
    	proposalCategoryInstance =  proposalCategoryService.saveProposalCategory(params)
        if (proposalCategoryInstance) {
            flash.message = "${message(code: 'default.ProposalCategoryCreated.label')}"
            redirect(action: "create", id: proposalCategoryInstance.id)
        }
        else {
            render(view: "create", model: [proposalCategoryInstance: proposalCategoryInstance])
        }
    }

    def show = {
        def proposalCategoryInstance = ProposalCategory.get(params.id)
        if (!proposalCategoryInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'proposalCategory.label', default: 'ProposalCategory'), params.id])}"
            redirect(action: "list")
        }
        else {
            [proposalCategoryInstance: proposalCategoryInstance]
        }
    }

    def edit = {
    	def proposalCategoryService = new ProposalCategoryService()
    	def proposalCategoryInstance = proposalCategoryService.getProposalCategoryById(new Integer( params.id ))
       
        if (!proposalCategoryInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'proposalCategory.label', default: 'ProposalCategory'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [proposalCategoryInstance: proposalCategoryInstance]
        }
    }

    def update = {
    	def proposalCategoryService = new ProposalCategoryService()
    	def proposalCategoryInstance = proposalCategoryService.getProposalCategoryById(new Integer( params.id ))
        if (proposalCategoryInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (proposalCategoryInstance.version > version) {
                    
                    proposalCategoryInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'proposalCategory.label', default: 'ProposalCategory')] as Object[], "Another user has updated this ProposalCategory while you were editing")
                    render(view: "edit", model: [proposalCategoryInstance: proposalCategoryInstance])
                    return
                }
            }
            
            proposalCategoryInstance = proposalCategoryService.updateProposalCategory( params,proposalCategoryInstance)
            if(proposalCategoryInstance.saveMode.equals( "Updated"))
            {
                flash.message = "${message(code: 'default.ProposalCategoryUpdated.label')}"
                redirect(action: "create", id: proposalCategoryInstance.id)
            }
            else {
                render(view: "edit", model: [proposalCategoryInstance: proposalCategoryInstance])
            }
        }
        else {
            flash.message ="${message(code: 'default.ProposalCategoryNotFound.label')}"
            redirect(action: "list")
        }
    }

    def delete = {
    	def proposalCategoryService = new ProposalCategoryService()
    	def proposalCategoryInstance = proposalCategoryService.getProposalCategoryById(new Integer( params.id ))
        if (proposalCategoryInstance) {
            try {
                proposalCategoryInstance.delete(flush: true)
                flash.message = "${message(code: 'default.ProposalCategoryDeleted.label')}"
                redirect(action: "create")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'proposalCategory.label', default: 'ProposalCategory'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.ProposalCategoryNotFound.label')}"
            redirect(action: "list")
        }
    }
}
