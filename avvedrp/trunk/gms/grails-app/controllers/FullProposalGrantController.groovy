class FullProposalGrantController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [fullProposalGrantInstanceList: FullProposalGrant.list(params), fullProposalGrantInstanceTotal: FullProposalGrant.count()]
    }

    def create = {
        def fullProposalGrantInstance = new FullProposalGrant()
        fullProposalGrantInstance.properties = params
        return [fullProposalGrantInstance: fullProposalGrantInstance]
    }

    def save = {
        def fullProposalGrantInstance = new FullProposalGrant(params)
        if (fullProposalGrantInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'fullProposalGrant.label', default: 'FullProposalGrant'), fullProposalGrantInstance.id])}"
            redirect(action: "show", id: fullProposalGrantInstance.id)
        }
        else {
            render(view: "create", model: [fullProposalGrantInstance: fullProposalGrantInstance])
        }
    }

    def show = {
        def fullProposalGrantInstance = FullProposalGrant.get(params.id)
        if (!fullProposalGrantInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'fullProposalGrant.label', default: 'FullProposalGrant'), params.id])}"
            redirect(action: "list")
        }
        else {
            [fullProposalGrantInstance: fullProposalGrantInstance]
        }
    }

    def edit = {
        def fullProposalGrantInstance = FullProposalGrant.get(params.id)
        if (!fullProposalGrantInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'fullProposalGrant.label', default: 'FullProposalGrant'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [fullProposalGrantInstance: fullProposalGrantInstance]
        }
    }

    def update = {
        def fullProposalGrantInstance = FullProposalGrant.get(params.id)
        if (fullProposalGrantInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (fullProposalGrantInstance.version > version) {
                    
                    fullProposalGrantInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'fullProposalGrant.label', default: 'FullProposalGrant')] as Object[], "Another user has updated this FullProposalGrant while you were editing")
                    render(view: "edit", model: [fullProposalGrantInstance: fullProposalGrantInstance])
                    return
                }
            }
            fullProposalGrantInstance.properties = params
            if (!fullProposalGrantInstance.hasErrors() && fullProposalGrantInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'fullProposalGrant.label', default: 'FullProposalGrant'), fullProposalGrantInstance.id])}"
                redirect(action: "show", id: fullProposalGrantInstance.id)
            }
            else {
                render(view: "edit", model: [fullProposalGrantInstance: fullProposalGrantInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'fullProposalGrant.label', default: 'FullProposalGrant'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def fullProposalGrantInstance = FullProposalGrant.get(params.id)
        if (fullProposalGrantInstance) {
            try {
                fullProposalGrantInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'fullProposalGrant.label', default: 'FullProposalGrant'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'fullProposalGrant.label', default: 'FullProposalGrant'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'fullProposalGrant.label', default: 'FullProposalGrant'), params.id])}"
            redirect(action: "list")
        }
    }
}
