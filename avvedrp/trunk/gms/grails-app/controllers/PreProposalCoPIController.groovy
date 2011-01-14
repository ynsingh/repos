class PreProposalCoPIController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "create", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [preProposalCoPIInstanceList: PreProposalCoPI.list(params), preProposalCoPIInstanceTotal: PreProposalCoPI.count()]
    }

    def create = {
        def preProposalCoPIInstance = new PreProposalCoPI()
        preProposalCoPIInstance.properties = params
        return [preProposalCoPIInstance: preProposalCoPIInstance]
    }

    def save = {
    	def preProposalCoPIService = new PreProposalCoPIService()
        def preProposalCoPIInstance = new PreProposalCoPI(params)
    	preProposalCoPIInstance  = preProposalCoPIService.savePreProposalCoPI(params)
        if (preProposalCoPIInstance) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'preProposalCoPI.label', default: 'PreProposalCoPI'), preProposalCoPIInstance.id])}"
            redirect(action: "create", id: preProposalCoPIInstance.id)
        }
        else {
            render(view: "create", model: [preProposalCoPIInstance: preProposalCoPIInstance])
        }
    }

    def show = {
        def preProposalCoPIInstance = PreProposalCoPI.get(params.id)
        if (!preProposalCoPIInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'preProposalCoPI.label', default: 'PreProposalCoPI'), params.id])}"
            redirect(action: "list")
        }
        else {
            [preProposalCoPIInstance: preProposalCoPIInstance]
        }
    }

    def edit = {
    	def preProposalCoPIService = new PreProposalCoPIService()
    	def  preProposalCoPIInstance =  preProposalCoPIService.getPreProposalCoPIById(new Integer( params.id ))
       
        if (!preProposalCoPIInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'preProposalCoPI.label', default: 'PreProposalCoPI'), params.id])}"
            redirect(action: "create")
        }
        else {
            return [preProposalCoPIInstance: preProposalCoPIInstance]
        }
    }

    def update = {
    	def preProposalCoPIService = new PreProposalCoPIService()
    	def  preProposalCoPIInstance =  preProposalCoPIService.getPreProposalCoPIById(new Integer( params.id ))
        if (preProposalCoPIInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (preProposalCoPIInstance.version > version) {
                    
                    preProposalCoPIInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'preProposalCoPI.label', default: 'PreProposalCoPI')] as Object[], "Another user has updated this PreProposalCoPI while you were editing")
                    render(view: "edit", model: [preProposalCoPIInstance: preProposalCoPIInstance])
                    return
                }
            }
            preProposalCoPIInstance =  preProposalCoPIService.updatePreProposalCoPI(params,preProposalCoPIInstance)
            if( preProposalCoPIInstance.saveMode.equals( "Updated")) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'preProposalCoPI.label', default: 'PreProposalCoPI'), preProposalCoPIInstance.id])}"
                redirect(action: "create", id: preProposalCoPIInstance.id)
            }
            else {
                render(view: "edit", model: [preProposalCoPIInstance: preProposalCoPIInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'preProposalCoPI.label', default: 'PreProposalCoPI'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
    	def preProposalCoPIService = new PreProposalCoPIService()
    	def  preProposalCoPIInstance =  preProposalCoPIService.getPreProposalCoPIById(new Integer( params.id ))
        if (preProposalCoPIInstance) {
            try {
                preProposalCoPIInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'preProposalCoPI.label', default: 'PreProposalCoPI'), params.id])}"
                redirect(action: "create")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'preProposalCoPI.label', default: 'PreProposalCoPI'), params.id])}"
                redirect(action: "create", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'preProposalCoPI.label', default: 'PreProposalCoPI'), params.id])}"
            redirect(action: "list")
        }
    }
}
