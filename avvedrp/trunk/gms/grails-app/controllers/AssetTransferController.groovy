class AssetTransferController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [assetTransferInstanceList: AssetTransfer.list(params), assetTransferInstanceTotal: AssetTransfer.count()]
    }

    def create = {
    	println"-------params-------"+params
        def assetTransferInstance = new AssetTransfer()
        def assetInstance = Asset.get(params.id)
        println"-------assetInstance-------"+assetInstance
        assetTransferInstance.properties = params
        def assetTransferInstanceList = AssetTransfer.findAll("from AssetTransfer AT where AT.asset.id ="+assetInstance.id)
        println"-------assetTransferInstanceList-------"+assetTransferInstanceList
        return [assetTransferInstance: assetTransferInstance,assetInstance:assetInstance,assetTransferInstanceList:assetTransferInstanceList]
    }

    def save = {
    	println"-------params-------"+params
        def assetTransferInstance = new AssetTransfer(params)
        def assetInstance = Asset.get(params.id)
        assetTransferInstance.asset = assetInstance
        if (assetTransferInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.label')}"
            redirect(action: "create", id: assetInstance.id)
        }
        else {
            render(view: "create", model: [assetTransferInstance: assetTransferInstance])
        }
    }

    def show = {
        def assetTransferInstance = AssetTransfer.get(params.id)
        if (!assetTransferInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'assetTransfer.label', default: 'AssetTransfer'), params.id])}"
            redirect(action: "list")
        }
        else {
            [assetTransferInstance: assetTransferInstance]
        }
    }

    def edit = {
        def assetTransferInstance = AssetTransfer.get(params.id)
        if (!assetTransferInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'assetTransfer.label', default: 'AssetTransfer'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [assetTransferInstance: assetTransferInstance]
        }
    }

    def update = {
    println"-------params-------"+params
        def assetTransferInstance = AssetTransfer.get(params.id)
         if (assetTransferInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (assetTransferInstance.version > version) {
                    
                    assetTransferInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'assetTransfer.label', default: 'AssetTransfer')] as Object[], "Another user has updated this AssetTransfer while you were editing")
                    render(view: "edit", model: [assetTransferInstance: assetTransferInstance])
                    return
                }
            }
            assetTransferInstance.properties = params
            if (!assetTransferInstance.hasErrors() && assetTransferInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.label')}"
                redirect(action: "create", id: assetTransferInstance.asset.id)
            }
            else {
                render(view: "edit", model: [assetTransferInstance: assetTransferInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'assetTransfer.label', default: 'AssetTransfer'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
    println"---------------"+params
        def assetTransferInstance = AssetTransfer.get(params.id)
        if (assetTransferInstance) {
            try {
                assetTransferInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.label')}"
               	redirect(action: "create", id: assetTransferInstance.asset.id)
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'assetTransfer.label', default: 'AssetTransfer'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'assetTransfer.label', default: 'AssetTransfer'), params.id])}"
            redirect(action: "list")
        }
    }
}
