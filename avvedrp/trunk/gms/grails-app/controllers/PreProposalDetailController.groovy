class PreProposalDetailController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "create", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [preProposalDetailInstanceList: PreProposalDetail.list(params), preProposalDetailInstanceTotal: PreProposalDetail.count()]
    }

    def create = {
        def preProposalDetailInstance = new PreProposalDetail()
        preProposalDetailInstance.properties = params
        return [preProposalDetailInstance: preProposalDetailInstance]
    }

    def save = {
    	def preProposalDetailService = new PreProposalDetailService()
        def preProposalDetailInstance = new PreProposalDetail(params)
    	preProposalDetailInstance = preProposalDetailService.savePreProposalDetail(params)
        if (preProposalDetailInstance) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'preProposalDetail.label', default: 'PreProposalDetail'), preProposalDetailInstance.id])}"
            redirect(action: "create", id: preProposalDetailInstance.id)
        }
        else {
            render(view: "create", model: [preProposalDetailInstance: preProposalDetailInstance])
        }
    }

    def show = {
        def preProposalDetailInstance = PreProposalDetail.get(params.id)
        if (!preProposalDetailInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'preProposalDetail.label', default: 'PreProposalDetail'), params.id])}"
            redirect(action: "list")
        }
        else {
            [preProposalDetailInstance: preProposalDetailInstance]
        }
    }

    def edit = {
    	def preProposalDetailService = new PreProposalDetailService()
    	def preProposalDetailInstance  = preProposalDetailService.getPreProposalDetailById(new Integer( params.id ))
       
        if (!preProposalDetailInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'preProposalDetail.label', default: 'PreProposalDetail'), params.id])}"
            redirect(action: "create")
        }
        else {
            return [preProposalDetailInstance: preProposalDetailInstance]
        }
    }

    def update = {
    	def preProposalDetailService = new PreProposalDetailService()
    	def preProposalDetailInstance  = preProposalDetailService.getPreProposalDetailById(new Integer( params.id ))
       
        if (preProposalDetailInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (preProposalDetailInstance.version > version) {
                    
                    preProposalDetailInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'preProposalDetail.label', default: 'PreProposalDetail')] as Object[], "Another user has updated this PreProposalDetail while you were editing")
                    render(view: "edit", model: [preProposalDetailInstance: preProposalDetailInstance])
                    return
                }
            }
            preProposalDetailInstance = preProposalDetailService.updatePreProposalDetail(params, preProposalDetailInstance)
            if( preProposalDetailInstance.saveMode.equals( "Updated")) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'preProposalDetail.label', default: 'PreProposalDetail'), preProposalDetailInstance.id])}"
                redirect(action: "create", id: preProposalDetailInstance.id)
            }
            else {
                render(view: "edit", model: [preProposalDetailInstance: preProposalDetailInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'preProposalDetail.label', default: 'PreProposalDetail'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
    	def preProposalDetailService = new PreProposalDetailService()
    	def preProposalDetailInstance  = preProposalDetailService.getPreProposalDetailById(new Integer( params.id ))
       
        if (preProposalDetailInstance) {
            try {
                preProposalDetailInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'preProposalDetail.label', default: 'PreProposalDetail'), params.id])}"
                redirect(action: "create")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'preProposalDetail.label', default: 'PreProposalDetail'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'preProposalDetail.label', default: 'PreProposalDetail'), params.id])}"
            redirect(action: "list")
        }
    }
}
