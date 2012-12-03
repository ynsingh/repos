import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import java.text.*;
import java.util.*;
import grails.converters.deep.JSON
class AssetDepreciateController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [assetDepreciateInstanceList: AssetDepreciate.list(params), assetDepreciateInstanceTotal: AssetDepreciate.count()]
    }

    def create = {
        def assetDepreciateInstance = new AssetDepreciate()
        def assetInstance = Asset.get(params.id)
        println"-------assetInstance-------"+assetInstance
        assetDepreciateInstance.properties = params
        def assetDepreciateInstanceList = AssetDepreciate.findAll("from AssetDepreciate AD where AD.asset.id ="+assetInstance.id)
        println"-------assetDepreciateInstanceList-------"+assetDepreciateInstanceList
        ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
    	NumberFormat formatter = new DecimalFormat("#0.00");
        return [assetDepreciateInstance: assetDepreciateInstance,assetInstance:assetInstance,assetDepreciateInstanceList:assetDepreciateInstanceList]
    }

    def save = {
    println"---------params-----------"+params
        def assetDepreciateInstance = new AssetDepreciate(params)
        def assetInstance = Asset.get(params.id)
        assetDepreciateInstance.asset = assetInstance
        if (assetDepreciateInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.label')}"
            redirect(action: "create", id: assetInstance.id)
        }
        else {
            render(view: "create", model: [assetDepreciateInstance: assetDepreciateInstance])
        }
    }

    def show = {
        def assetDepreciateInstance = AssetDepreciate.get(params.id)
        if (!assetDepreciateInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'assetDepreciate.label', default: 'AssetDepreciate'), params.id])}"
            redirect(action: "list")
        }
        else {
            [assetDepreciateInstance: assetDepreciateInstance]
        }
    }

    def edit = {
        def assetDepreciateInstance = AssetDepreciate.get(params.id)
        ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
    	NumberFormat formatter = new DecimalFormat("#0.00");
        if (!assetDepreciateInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'assetDepreciate.label', default: 'AssetDepreciate'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [assetDepreciateInstance: assetDepreciateInstance]
        }
    }

    def update = {
     println"---------params-----------"+params
        def assetDepreciateInstance = AssetDepreciate.get(params.id)
        if (assetDepreciateInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (assetDepreciateInstance.version > version) {
                    
                    assetDepreciateInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'assetDepreciate.label', default: 'AssetDepreciate')] as Object[], "Another user has updated this AssetDepreciate while you were editing")
                    render(view: "edit", model: [assetDepreciateInstance: assetDepreciateInstance])
                    return
                }
            }
            assetDepreciateInstance.properties = params
            if (!assetDepreciateInstance.hasErrors() && assetDepreciateInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.label')}"
                redirect(action: "create", id: assetDepreciateInstance.asset.id)
            }
            else {
                render(view: "edit", model: [assetDepreciateInstance: assetDepreciateInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'assetDepreciate.label', default: 'AssetDepreciate'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def assetDepreciateInstance = AssetDepreciate.get(params.id)
        if (assetDepreciateInstance) {
            try {
                assetDepreciateInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'assetDepreciate.label', default: 'AssetDepreciate'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
               flash.message = "${message(code: 'default.deleted.label')}"
                 redirect(action: "create", id: assetDepreciateInstance.asset.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'assetDepreciate.label', default: 'AssetDepreciate'), params.id])}"
            redirect(action: "list")
        }
    }
}
