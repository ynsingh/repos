import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession

class FundTransferController {
	ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [fundTransferInstanceList: fundTransferInstanceList, fundTransferInstanceTotal: FundTransfer.count()]
    }

    def create = {
    	println "params.id" +params.id
    	def grantAllocationInstance = GrantAllocation.get(params.id)
        def fundTransferInstance = new FundTransfer()
        def fundTransferService=new FundTransferService();
        fundTransferInstance.properties = params
        println "params" +params
        def fundTransferInstanceList= fundTransferService.getFundTransferDetailsByProject(grantAllocationInstance.projects.id)
        
        return [fundTransferInstance: fundTransferInstance,
        grantAllocationInstance:grantAllocationInstance,
        fundTransferInstanceList: fundTransferInstanceList,
        currencyFormat:currencyFormatter]
    }

    def save = {
    	println "params" +params
        def fundTransferInstance = new FundTransfer(params.id)
        def grantAllocationInstance = GrantAllocation.get(params.grantAllocationId)
        def fundTransferService=new FundTransferService();
        def fundTransferInstanceList= fundTransferService.getFundTransferDetailsByProject(grantAllocationInstance.projects.id)
        fundTransferInstance.grantAllocation = grantAllocationInstance
        fundTransferInstance.createdBy="admin"
		fundTransferInstance.modifiedBy="admin"
        if (fundTransferInstance.save(flush: true)) {
            flash.message = "Created Succesfully"
             redirect(action: "create", id: grantAllocationInstance.id,params:[subMenu:params.subMenu,currencyFormat:currencyFormatter])
        }
        else {
            render(view: "create", model: [fundTransferInstance: fundTransferInstance,grantAllocationInstance:grantAllocationInstance,currencyFormat:currencyFormatter],params:[subMenu:params.subMenu])
        }
    }

    def show = {
        def fundTransferInstance = FundTransfer.get(params.id)
        if (!fundTransferInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'fundTransfer.label', default: 'FundTransfer'), params.id])}"
            redirect(action: "list")
        }
        else {
            [fundTransferInstance: fundTransferInstance]
        }
    }

    def edit = {
        def fundTransferInstance = FundTransfer.get(params.id)
        def grantAllocationInstance = GrantAllocation.get(params.grantAllocationId)
        if (!fundTransferInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'fundTransfer.label', default: 'FundTransfer'), params.id])}"
            redirect(action: "create",params:[subMenu:params.subMenu,currencyFormat:currencyFormatter])
        }
        else {
            return [fundTransferInstance: fundTransferInstance,grantAllocationInstance: grantAllocationInstance,currencyFormat:currencyFormatter,params:[subMenu:params.subMenu]]
        }
    }

    def update = {
        def fundTransferInstance = FundTransfer.get(params.id)
        println " params.grantAllocationId " + params.grantAllocationId
        def grantAllocationInstance = GrantAllocation.get(params.grantAllocationId)
        
        if (fundTransferInstance) 
        {
            fundTransferInstance.properties = params
            if (!fundTransferInstance.hasErrors() && fundTransferInstance.save(flush: true)) {
                flash.message = "Updated Succesfully"
                redirect(action: "create", id: grantAllocationInstance.id, params:[subMenu:params.subMenu])

            }
            else {
                render(view: "edit", model: [fundTransferInstance: fundTransferInstance,
                                             grantAllocationInstance: grantAllocationInstance,currencyFormat:currencyFormatter],
												params:[subMenu:params.subMenu])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'fundTransfer.label', default: 'FundTransfer'), params.id])}"
            redirect(action: "create",params:[subMenu:params.subMenu,currencyFormat:currencyFormatter])
        }
    }

    def delete = {
        def fundTransferInstance = FundTransfer.get(params.id)
        if (fundTransferInstance) {
            try {
                fundTransferInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'fundTransfer.label', default: 'FundTransfer'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'fundTransfer.label', default: 'FundTransfer'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'fundTransfer.label', default: 'FundTransfer'), params.id])}"
            redirect(action: "create")
        }
    }
}
