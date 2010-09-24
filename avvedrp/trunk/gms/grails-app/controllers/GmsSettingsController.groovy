class GmsSettingsController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [gmsSettingsInstanceList: GmsSettings.list(params), gmsSettingsInstanceTotal: GmsSettings.count()]
    }

    def create = {
        def gmsSettingsInstance = new GmsSettings()
        gmsSettingsInstance.properties = params
        return [gmsSettingsInstance: gmsSettingsInstance]
    }

    def save = {
        def gmsSettingsInstance = new GmsSettings(params)
        if (gmsSettingsInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'gmsSettings.label', default: 'GmsSettings'), gmsSettingsInstance.id])}"
            redirect(action: "show", id: gmsSettingsInstance.id)
        }
        else {
            render(view: "create", model: [gmsSettingsInstance: gmsSettingsInstance])
        }
    }

    def show = {
        def gmsSettingsInstance = GmsSettings.get(params.id)
        if (!gmsSettingsInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gmsSettings.label', default: 'GmsSettings'), params.id])}"
            redirect(action: "list")
        }
        else {
            [gmsSettingsInstance: gmsSettingsInstance]
        }
    }

    def edit = {
        def gmsSettingsInstance = GmsSettings.get(params.id)
        if (!gmsSettingsInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gmsSettings.label', default: 'GmsSettings'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [gmsSettingsInstance: gmsSettingsInstance]
        }
    }

    def update = {
        def gmsSettingsInstance = GmsSettings.get(params.id)
        if (gmsSettingsInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (gmsSettingsInstance.version > version) {
                    
                    gmsSettingsInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'gmsSettings.label', default: 'GmsSettings')] as Object[], "Another user has updated this GmsSettings while you were editing")
                    render(view: "edit", model: [gmsSettingsInstance: gmsSettingsInstance])
                    return
                }
            }
            gmsSettingsInstance.properties = params
            if (!gmsSettingsInstance.hasErrors() && gmsSettingsInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'gmsSettings.label', default: 'GmsSettings'), gmsSettingsInstance.id])}"
                redirect(action: "show", id: gmsSettingsInstance.id)
            }
            else {
                render(view: "edit", model: [gmsSettingsInstance: gmsSettingsInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gmsSettings.label', default: 'GmsSettings'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def gmsSettingsInstance = GmsSettings.get(params.id)
        if (gmsSettingsInstance) {
            try {
                gmsSettingsInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'gmsSettings.label', default: 'GmsSettings'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'gmsSettings.label', default: 'GmsSettings'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gmsSettings.label', default: 'GmsSettings'), params.id])}"
            redirect(action: "list")
        }
    }
}
