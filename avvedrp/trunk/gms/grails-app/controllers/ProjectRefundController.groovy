class ProjectRefundController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [projectRefundInstanceList: ProjectRefund.list(params), projectRefundInstanceTotal: ProjectRefund.count()]
    }

    def create = {
        def projectRefundInstance = new ProjectRefund()
        projectRefundInstance.properties = params
        return [projectRefundInstance: projectRefundInstance]
    }

    def save = {
        def projectRefundInstance = new ProjectRefund(params)
        if (projectRefundInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'projectRefund.label', default: 'ProjectRefund'), projectRefundInstance.id])}"
            redirect(action: "show", id: projectRefundInstance.id)
        }
        else {
            render(view: "create", model: [projectRefundInstance: projectRefundInstance])
        }
    }

    def show = {
        def projectRefundInstance = ProjectRefund.get(params.id)
        if (!projectRefundInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'projectRefund.label', default: 'ProjectRefund'), params.id])}"
            redirect(action: "list")
        }
        else {
            [projectRefundInstance: projectRefundInstance]
        }
    }

    def edit = {
        def projectRefundInstance = ProjectRefund.get(params.id)
        if (!projectRefundInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'projectRefund.label', default: 'ProjectRefund'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [projectRefundInstance: projectRefundInstance]
        }
    }

    def update = {
        def projectRefundInstance = ProjectRefund.get(params.id)
        if (projectRefundInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (projectRefundInstance.version > version) {
                    
                    projectRefundInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'projectRefund.label', default: 'ProjectRefund')] as Object[], "Another user has updated this ProjectRefund while you were editing")
                    render(view: "edit", model: [projectRefundInstance: projectRefundInstance])
                    return
                }
            }
            projectRefundInstance.properties = params
            if (!projectRefundInstance.hasErrors() && projectRefundInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'projectRefund.label', default: 'ProjectRefund'), projectRefundInstance.id])}"
                redirect(action: "show", id: projectRefundInstance.id)
            }
            else {
                render(view: "edit", model: [projectRefundInstance: projectRefundInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'projectRefund.label', default: 'ProjectRefund'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def projectRefundInstance = ProjectRefund.get(params.id)
        if (projectRefundInstance) {
            try {
                projectRefundInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'projectRefund.label', default: 'ProjectRefund'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'projectRefund.label', default: 'ProjectRefund'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'projectRefund.label', default: 'ProjectRefund'), params.id])}"
            redirect(action: "list")
        }
    }
}
