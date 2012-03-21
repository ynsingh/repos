import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import java.text.*;
import java.util.*;

class ExternalFundRefundController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [externalFundRefundInstanceList: ExternalFundRefund.list(params), externalFundRefundInstanceTotal: ExternalFundRefund.count()]
    }

    def create = {
    	def grantAllocationService = new GrantAllocationService()
    	def grantReceiptService = new GrantReceiptService()
    	def grantExpenseService = new GrantExpenseService()
    	def RefundList = []
        def externalFundRefundInstance = new ExternalFundRefund()
        externalFundRefundInstance.properties = params
        def grantAllocationInstance = GrantAllocation.get(params.id)
        def externalFundAllocationInstance = new ExternalFundAllocation()
        double totalAmtReceived = grantReceiptService.getSumOfAmountReceivedForGrantAllocation(grantAllocationInstance.id)
        def extrnlFndAllocatnInstance = grantAllocationService.getExternalFundAllocationByGrantAllocation(grantAllocationInstance.id)
        RefundList = grantAllocationService.getRefundListBygrantAllocationInstance(grantAllocationInstance)
        ConvertToIndainRS currencyFormatter=new ConvertToIndainRS()
        NumberFormat formatter = new DecimalFormat("#0.00")
        return [externalFundRefundInstance: externalFundRefundInstance,'totalAmtReceived':totalAmtReceived,
                'refundAmount':formatter.format(externalFundRefundInstance.refundAmount),
                grantAllocationInstance:grantAllocationInstance,RefundList:RefundList,'currencyFormat':currencyFormatter,
                externalFundAllocationInstance:externalFundAllocationInstance,extrnlFndAllocatnInstance:extrnlFndAllocatnInstance]
    }

    def save = {
    	GrailsHttpSession gh=getSession()
    	def grantReceiptService = new GrantReceiptService()
    	def externalFundRefundInstance = new ExternalFundRefund(params)
    	def userService = new UserService()
    	double balanceamount
    	def userInstance = userService.getUserById(new Integer(gh.getValue("UserId").intValue()))
    	def grantAllocationService = new GrantAllocationService()
    	def grantAllocationInstance = GrantAllocation.get(params.grantAllocationId)
    	def grantReceiptList = grantReceiptService.getGrantReceiptByGrantAllocation(grantAllocationInstance.id)
    	def externalFundAllocationInstance = grantAllocationService.getExternalFundAllocationByGrantAllocation(params.grantAllocationId)
    	def externalFundAllocationStatusInstance = ExternalFundAllocation.executeQuery("select status from ExternalFundAllocation EF where EF.id="+externalFundAllocationInstance.id)
    	double totalAmtReceived = grantReceiptService.getSumOfAmountReceivedForGrantAllocation(grantAllocationInstance.id)
    	def reFundInstance = ExternalFundRefund.executeQuery("select SUM(EFR.refundAmount) from ExternalFundRefund EFR where EFR.externalFundAllocation.id="+externalFundAllocationInstance.id+" group by EFR.externalFundAllocation.id")
    	if(reFundInstance)
        {
        	balanceamount = Double.valueOf(totalAmtReceived) - Double.valueOf(reFundInstance[0])
        }
    	
    	
    	externalFundRefundInstance.externalFundAllocation = externalFundAllocationInstance
        externalFundRefundInstance.createdBy = userInstance.username
        externalFundRefundInstance.createdDate = new Date()
    	if(externalFundAllocationInstance.refundable == 'N')
    	{
    		    flash.error = "${message(code: 'default.NonRefundable.label')}"
		        redirect(action: "create", id: params.grantAllocationId)
	    }
    	else
    	{
	    	if(externalFundAllocationStatusInstance[0] == 'Closed')
	    	{
	    		    flash.error = "${message(code: 'default.RefundingStatusisAlreadyClosedSoCantRefundAnyAmount.label')}"
			        redirect(action: "create", id: params.grantAllocationId)
		    }
	    	else
	    	{
	    		if(grantReceiptList[0] == null)
		    	{
		    		flash.error = "${message(code: 'default.NoGrantFundisReceivedsocantRefund.label')}"
			        redirect(action: "create", id: params.grantAllocationId)
		    	}
		    	else
		    	{
		    		if(externalFundRefundInstance.refundAmount > totalAmtReceived)
	    			{
			    		flash.error = "${message(code: 'default.RefundAmountShouldBeLessThanReceivedAmount.label')}"
			   			 redirect(action: "create", id: params.grantAllocationId)
	    			}
	    			else
	    			{
			    		if(balanceamount!=null)
			       		{
			    			if(externalFundRefundInstance.refundAmount > balanceamount)
			       			{
			    				flash.error ="${message(code: 'default.RefundAmountShouldBeLessThanTotalRefundedAmount.label')}"
			       				 redirect(action: "create", id: params.grantAllocationId)
			       			}
			       			else
			       			{
					    		if (externalFundRefundInstance.save(flush: true)) {
						        	externalFundAllocationInstance.status = params.refundingStatus
						        	if (externalFundAllocationInstance.save(flush: true)) {
							            flash.message = "${message(code: 'default.created.label')}"
							            redirect(action: "create", id: params.grantAllocationId)
							        }
						        }
						      }
						 }
						 else
						 {
						 	if (externalFundRefundInstance.save(flush: true)) {
						        	externalFundAllocationInstance.status = params.refundingStatus
						        	if (externalFundAllocationInstance.save(flush: true)) {
							            flash.message = "${message(code: 'default.created.label')}"
							            redirect(action: "create", id: params.grantAllocationId)
							        }
						        }
						  }
					}
		    	}
	    	}
    	}
    }

    def edit = {
    	def grantReceiptService = new GrantReceiptService()
    	def grantAllocationService = new GrantAllocationService()
        def externalFundRefundInstance = ExternalFundRefund.get(params.id)
        def grantAllocationInstance = GrantAllocation.get(externalFundRefundInstance.externalFundAllocation.grantAllocation.id)
        double totalAmtReceived = grantReceiptService.getSumOfAmountReceivedForGrantAllocation(grantAllocationInstance.id)
        double totalAmtRefunded = grantAllocationService.getSumOfTotalRefund(externalFundRefundInstance.externalFundAllocation.id)
        double balance = totalAmtReceived-totalAmtRefunded
        ConvertToIndainRS currencyFormatter=new ConvertToIndainRS()
        NumberFormat formatter = new DecimalFormat("#0.00")
        if (!externalFundRefundInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'externalFundRefund.label', default: 'ExternalFundRefund'), params.id])}"
            redirect(action: "create", id: grantAllocationInstance.id)
        }
        else {
            return [externalFundRefundInstance: externalFundRefundInstance,grantAllocationInstance:grantAllocationInstance,
                    'totalAmtReceived':totalAmtReceived,'balance':balance,'currencyFormat':currencyFormatter,amount:formatter.format(externalFundRefundInstance.refundAmount)]
        }
    }

    def update = {
     	GrailsHttpSession gh=getSession()
    	def userService = new UserService()
    	def grantReceiptService = new GrantReceiptService()
    	def grantAllocationService = new GrantAllocationService()
    	def userInstance = userService.getUserById(new Integer(gh.getValue("UserId").intValue()))
    	def externalFundRefundInstance = ExternalFundRefund.get(params.id)
    	def grantAllocationInstance = GrantAllocation.get(externalFundRefundInstance.externalFundAllocation.grantAllocation.id)
        if (externalFundRefundInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (externalFundRefundInstance.version > version) {
                    
                    externalFundRefundInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'externalFundRefund.label', default: 'ExternalFundRefund')] as Object[], "Another user has updated this ExternalFundRefund while you were editing")
                    render(view: "edit", model: [externalFundRefundInstance: externalFundRefundInstance])
                    return
                }
            }
            double totalAmtReceived = grantReceiptService.getSumOfAmountReceivedForGrantAllocation(grantAllocationInstance.id)
            double totalAmtRefunded = grantAllocationService.getSumOfTotalRefund(externalFundRefundInstance.externalFundAllocation.id)
            double balance = totalAmtReceived-totalAmtRefunded
            double allocatedAmount
            allocatedAmount =Double.valueOf(params.refundAmount)
            if(externalFundRefundInstance.externalFundAllocation.status == 'Closed')
            {
            	flash.error = "${message(code: 'default.RefundingStatusisClosedByGranteeSoCantEdit.label')}"
    			redirect(action: "edit", id: externalFundRefundInstance.externalFundAllocation.id)
    		}
    		else
    		{
	            if(allocatedAmount > totalAmtReceived)
	    		{
	    			flash.error = "${message(code: 'default.RefundAmountShouldBeLessThanReceivedAmount.label')}"
	    			redirect(action: "edit", id: externalFundRefundInstance.externalFundAllocation.id)
	    		}
	            else
	            {
        			externalFundRefundInstance.properties = params
		            externalFundRefundInstance.modifiedBy = userInstance.username
		            externalFundRefundInstance.modifiedDate = new Date()
		            if (!externalFundRefundInstance.hasErrors() && externalFundRefundInstance.save(flush: true)) 
			        {
		    			def externalFundAllocationInstance = ExternalFundAllocation.get(externalFundRefundInstance.externalFundAllocation.id)
		            	externalFundAllocationInstance.status = params.refundingStatus
		            	if (externalFundAllocationInstance.save(flush: true)) 
		            	{
			                flash.message = "${message(code: 'default.updated.label')}"
			                redirect(action: "create", id: grantAllocationInstance.id)
		            	}
			        }
				 }
		     }
		    }
    }

    def delete = {
        def externalFundRefundInstance = ExternalFundRefund.get(params.id)
        if (externalFundRefundInstance) {
            try {
                externalFundRefundInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'externalFundRefund.label', default: 'ExternalFundRefund'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'externalFundRefund.label', default: 'ExternalFundRefund'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'externalFundRefund.label', default: 'ExternalFundRefund'), params.id])}"
            redirect(action: "list")
        }
    }
    
    def refundList = {
    	def grantAllocationService = new GrantAllocationService()
    	def grantAllocationInstance = GrantAllocation.get(params.id)
    	def RefundList = []
        RefundList = grantAllocationService.getRefundListBygrantAllocationInstance(grantAllocationInstance)
    	def externalFundAllocationInstance = grantAllocationService.getExternalFundAllocationByGrantAllocation(params.id)
    	
        return [grantAllocationInstance:grantAllocationInstance,RefundList:RefundList,
                externalFundAllocationInstance:externalFundAllocationInstance]
    }
}
