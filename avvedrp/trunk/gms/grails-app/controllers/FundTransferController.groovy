import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import java.text.*;
import java.util.*;

import grails.converters.*
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

    def save = 
    {
    	println "params" +params
        def fundTransferInstance = new FundTransfer(params.id)
    	
        def grantAllocationInstance = GrantAllocation.get(params.grantAllocationId)
        def fundTransferService=new FundTransferService();
        def fundTransferInstanceList= fundTransferService.getFundTransferDetailsByProject(grantAllocationInstance.projects.id)
        fundTransferInstance.grantAllocation = grantAllocationInstance
        fundTransferInstance.createdBy="admin"
    	fundTransferInstance.modifiedBy="admin"
    	
    	def grantReceiptService=new GrantReceiptService()
        def grantAllocationParentInstance=fundTransferService.getGrantAllocationByProjects(fundTransferInstance)
        println"grantAllocationParentInstance"+grantAllocationParentInstance[0].granter
        def chkgrantReceiptInstance=grantReceiptService.chkgrantReceiptInstanceForParent( fundTransferInstance)
        if(grantAllocationParentInstance[0].granter && !chkgrantReceiptInstance[0] )
        {
          print"testing......"
    		flash.message = "Fund can be transfered only if the grant has been received"
    		redirect(action: "create",id:grantAllocationInstance.id , params:[subMenu:params.subMenu])
    		
    	 }
       
         else
          {
        	  println"fundTransferInstance.amount"+fundTransferInstance.amount
        	  def chkFundTransferAmnt=fundTransferService.chkAmntValidation(fundTransferInstance)
        	  println"chkFundTransferAmnt"+chkFundTransferAmnt[0].amountAllocated
        
        	  def totFundTransfered = FundTransfer.executeQuery("select sum(FT.amount) from FundTransfer FT where FT.grantAllocation.id="+fundTransferInstance.grantAllocationId)
       
        	  double totalFund
        	  if(totFundTransfered[0]!=null)
        	  {
        		  totalFund = new Double(totFundTransfered[0]).doubleValue() + fundTransferInstance.amount
        		  println"totalFundtransfer"+totalFund
        	  }
        	  else
        	  {
        		  totalFund = fundTransferInstance.amount 
        	  }
        
        	  if(chkFundTransferAmnt[0].amountAllocated < totalFund)
        	  {
        		  flash.message = "${message(code: 'default.FundTransferAmount.label')}"
        		  redirect(action: "create",id:grantAllocationInstance.id ,params:[subMenu:params.subMenu])
        	  }
        	  else
        	  {
        		  if (fundTransferInstance.save(flush: true)) 
        		  {
        			  flash.message = "${message(code: 'default.created.label')}"
        			  redirect(action: "create", id: grantAllocationInstance.id,params:[subMenu:params.subMenu,currencyFormat:currencyFormatter])
        		  }
        		  else 
        		  {
        			  render(view: "create", model: [fundTransferInstance: fundTransferInstance,
                                           grantAllocationInstance:grantAllocationInstance,
                                           currencyFormat:currencyFormatter],
                                           params:[subMenu:params.subMenu])
        		  }
        	  }
          }
        
    }

    def show = {
        def fundTransferInstance = FundTransfer.get(params.id)
        if (!fundTransferInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "list")
        }
        else {
            [fundTransferInstance: fundTransferInstance]
        }
    }

    def edit = {
    	 println"params"+params
        def fundTransferInstance = FundTransfer.get(params.id)
        println"fundTransferInstance"+fundTransferInstance
        //def grantAllocationInstance = GrantAllocation.get(params.grantAllocationId)
        def grantAllocationInstance = GrantAllocation.get(fundTransferInstance.grantAllocationId)
       
        //println"grantAllocationInstance"+grantAllocationInstance
        println"fundTransferInstance.grantAllocation"+fundTransferInstance.grantAllocation.id
        if (!fundTransferInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "create",params:[subMenu:params.subMenu,currencyFormat:currencyFormatter])
        }
        else {
        	NumberFormat formatter = new DecimalFormat("#0.00");
            return [fundTransferInstance: fundTransferInstance,
                    grantAllocationInstance: grantAllocationInstance,
                    currencyFormat:currencyFormatter,params:[subMenu:params.subMenu],
                    amount:formatter.format(fundTransferInstance.amount)]
        }
    }

    def update = {
        def fundTransfer = FundTransfer.get(params.id)
        println " params.grantAllocationId " + params.grantAllocationId
        def grantAllocationInstance = GrantAllocation.get(params.grantAllocationId)
        def fundTransferService=new FundTransferService();
        if (fundTransfer) 
        {
        	def fundTransferInstance=new FundTransfer()
        	def actualAmt=fundTransfer.amount
            fundTransferInstance.properties = params
            
            def chkFundTransferAmnt=fundTransferService.chkAmntValidation(fundTransfer)
            println"chkFundTransferAmnt"+chkFundTransferAmnt[0].amountAllocated
            
            def totFundTransfered = FundTransfer.executeQuery("select sum(FT.amount) from FundTransfer FT where FT.grantAllocation.id="+fundTransfer.grantAllocationId)
           
            double totalFund
            if(totFundTransfered[0]!=null)
            {
            totalFund = new Double(totFundTransfered[0]).doubleValue() + fundTransferInstance.amount-actualAmt
            println"totalFundtransfer*******************"+totalFund
            }
            else
            {
            	totalFund = fundTransferInstance.amount 
            }
            
            println"chkFundTransferAmnt[0].amountAllocated"+chkFundTransferAmnt[0].amountAllocated
            println "totalFund[0].amountAllocated"+totalFund
            def grantReceiptService = new GrantReceiptService()
            println"fundTransferInstance.id"+fundTransfer
            def chkReceiveFund=grantReceiptService.chkReceiveFundTransfer(fundTransfer)
            println"chkReceiveFund"+chkReceiveFund
            if(!chkReceiveFund)
            {
           if(chkFundTransferAmnt[0].amountAllocated < totalFund)
           {
        	   flash.message = "${message(code: 'default.FundTransferAmount.label')}"
        		   render(view: "edit", model: [fundTransferInstance: fundTransfer,
                                                grantAllocationInstance: grantAllocationInstance,currencyFormat:currencyFormatter],
   												params:[subMenu:params.subMenu])
           }
           else
           {
        	   println "fundTransferInstance"+fundTransferInstance.dateOfTransfer
        	   
        	   fundTransfer.amount=fundTransferInstance.amount 
        	   fundTransfer.dateOfTransfer=fundTransferInstance.dateOfTransfer 
            if (fundTransfer.save()) {
                flash.message = "${message(code: 'default.updated.label')}"
                	  println "Updated Succesfully"
                redirect(action: "create", id: grantAllocationInstance.id, params:[subMenu:params.subMenu])

            }
            else {
                render(view: "edit", model: [fundTransferInstance: fundTransfer,
                                             grantAllocationInstance: grantAllocationInstance,
                                             currencyFormat:currencyFormatter],
												params:[subMenu:params.subMenu])
            }
           }
        }
        
        else
        {
        	 flash.message = "${message(code: 'default.GrantRecievedFund.label')}"

        		 render(view: "edit", model: [fundTransferInstance: fundTransfer,
                                              grantAllocationInstance: grantAllocationInstance,currencyFormat:currencyFormatter],
 												params:[subMenu:params.subMenu])
        }
        }
        else {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "create",params:[subMenu:params.subMenu,currencyFormat:currencyFormatter])
        }
    }

    def delete = {
        def fundTransferInstance = FundTransfer.get(params.id)
        if (fundTransferInstance) {
            try {
                fundTransferInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.label')}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.inuse.label')}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "create")
        }
    }
}
