import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import java.text.*;
import java.util.*;
import grails.converters.deep.JSON
class AssetController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	def itemPurchaseService
    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [assetInstanceList: Asset.list(params), assetInstanceTotal: Asset.count()]
    }

    def create = {
     	println"---------------------"+params
        def assetInstance = new Asset()
        def projectInstance = Projects.get(params.id)
        println"-projectInstance-"+projectInstance
        assetInstance.properties = params
        def assetInstanceList = itemPurchaseService.getAssetList(projectInstance)
        println"-----------assetInstanceList----------"+assetInstanceList
        ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
    	NumberFormat formatter = new DecimalFormat("#0.00");
        return [assetInstance: assetInstance,projectInstance:projectInstance,assetInstanceList:assetInstanceList,'currencyFormat':currencyFormatter,'amount':formatter.format(assetInstance.cost)]
    }

    def save = {
    	println"---------------------"+params
        def assetInstance = new Asset(params)
        def projectInstance = Projects.get(params.projectId)
	    assetInstance.projects=projectInstance
	    assetInstance.activeYesNo='Y'
	    def assetInstanceList = itemPurchaseService.getAssetList(projectInstance)
        if (assetInstance.save(flush: true)) {
           flash.message = "${message(code: 'default.created.label')}"
           redirect(action: "create", id:projectInstance.id)
        }
        else {
            render(view: "create", model: [assetInstance: assetInstance,assetInstanceList:assetInstanceList])
        }
    }

    def show = {
        def assetInstance = Asset.get(params.id)
        if (!assetInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'asset.label', default: 'Asset'), params.id])}"
            redirect(action: "list")
        }
        else {
            [assetInstance: assetInstance]
        }
    }

    def edit = {
     	println"---------------------"+params
        def assetInstance = Asset.get(params.id)
        ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
    	NumberFormat formatter = new DecimalFormat("#0.00");
        def projectInstance = Projects.find("from Projects P where P.id ="+ assetInstance.projects.id)
        if (!assetInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'asset.label', default: 'Asset'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [assetInstance: assetInstance,projectInstance:projectInstance,'currencyFormat':currencyFormatter,'amount':formatter.format(assetInstance.cost)]
        }
    }

    def update = {
     	println"---------------------"+params
        def assetInstance = Asset.get(params.id)
        def projectInstance = Projects.get(params.projectId)
        if (assetInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (assetInstance.version > version) {
                    
                    assetInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'asset.label', default: 'Asset')] as Object[], "Another user has updated this Asset while you were editing")
                    render(view: "edit", model: [assetInstance: assetInstance])
                    return
                }
            }
            assetInstance.properties = params
            if (!assetInstance.hasErrors() && assetInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.label')}"
                redirect(action: "create", id: projectInstance.id)
            }
            else {
                render(view: "edit", model: [assetInstance: assetInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'asset.label', default: 'Asset'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def assetInstance = Asset.get(params.id)
        if (assetInstance) {
            try {
                assetInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'asset.label', default: 'Asset'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'asset.label', default: 'Asset'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'asset.label', default: 'Asset'), params.id])}"
            redirect(action: "list")
        }
    }
}
