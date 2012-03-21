import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import java.text.*;
import java.util.*;
import org.apache.commons.validator.EmailValidator
import grails.converters.*

class FundTransferController {
	def notificationsEmailsService
	def gmsSettingsService
	
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
    	
    	def grantAllocationInstance = GrantAllocation.get(params.id)
        def fundTransferInstance = new FundTransfer()
        def fundTransferService=new FundTransferService();
        fundTransferInstance.properties = params
        def fundTransferInstanceList= fundTransferService.getFundTransferDetailsByProject(grantAllocationInstance.projects.id)
        
        return [fundTransferInstance: fundTransferInstance,
        grantAllocationInstance:grantAllocationInstance,
        fundTransferInstanceList: fundTransferInstanceList,
        currencyFormat:currencyFormatter]
    }

    def save = 
    {
    	def fundTransferInstance = new FundTransfer(params.id)
    	def userService = new UserService()
        def grantAllocationInstance = GrantAllocation.get(params.grantAllocationId)
        def fundTransferService=new FundTransferService();
        def fundTransferInstanceList= fundTransferService.getFundTransferDetailsByProject(grantAllocationInstance.projects.id)
        fundTransferInstance.grantAllocation = grantAllocationInstance
        fundTransferInstance.createdBy="admin"
    	fundTransferInstance.modifiedBy="admin"
    	
    	def grantReceiptService=new GrantReceiptService()
        def grantAllocationParentInstance=fundTransferService.getGrantAllocationByProjects(fundTransferInstance)
        def chkgrantReceiptInstance=grantReceiptService.chkgrantReceiptInstanceForParent( fundTransferInstance)
        if(grantAllocationParentInstance[0].granter && !chkgrantReceiptInstance[0] )
        {
        	flash.message = "Fund can be transfered only if the grant has been received"
    		redirect(action: "create",id:grantAllocationInstance.id , params:[subMenu:params.subMenu])
    		
    	 }
       
         else
          {
        	  def chkFundTransferAmnt=fundTransferService.chkAmntValidation(fundTransferInstance)
        	  def totFundTransfered = FundTransfer.executeQuery("select sum(FT.amount) from FundTransfer FT where FT.grantAllocation.id="+fundTransferInstance.grantAllocationId)
       
        	  double totalFund
        	  if(totFundTransfered[0]!=null)
        	  {
        		  totalFund = new Double(totFundTransfered[0]).doubleValue() + fundTransferInstance.amount
        		 
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
        	   def grantAllocationTackingInstance = GrantAllocationTracking.find("from GrantAllocationTracking GT where GT.grantAllocation="+grantAllocationInstance.id)
        	   if(grantAllocationTackingInstance)
        	   {
	        	   if(grantAllocationTackingInstance.grantAllocationStatus=='Closed')
	        	   {
	        	   		flash.message = "${message(code: 'default.Canttranferfundfromthisprojectasitisalreadyclosed.label')}"
	    		  		redirect(action: "create",id:grantAllocationInstance.id , params:[subMenu:params.subMenu])
	        	   }
	           }
        	   else
        	   {
        		  if (fundTransferInstance.save(flush: true)) 
        		  {
        			  def numformatter = new DecimalFormat("#0.00");
        			  ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
      				  def authorityInstance = Authority.find("from Authority A where A.authority = 'ROLE_SITEADMIN'")
        			  def userMapList = userService.getAllUsersByPartyID(params.Recepient)
        			  def userInstance = userService.getSiteAdminOfReceiver(userMapList,authorityInstance)
        			  EmailValidator emailValidator = EmailValidator.getInstance()
        			  if (emailValidator.isValid(userInstance.email))
        			  {
        				  def mailSubject="GMS Fund Transfer Information Against Project "+params.project;
        				  def mailFooter=gmsSettingsService.getGmsSettingsValue("MailFooter")
        				  String mailMessage="";
        				  mailMessage="Dear "+userInstance[0].userRealName+" "+userInstance[0].userSurName+", \n";
        				  mailMessage+="\nAn amount of Rs."+currencyFormatter.ConvertToIndainRS(fundTransferInstance.amount)+" /-";
          			      mailMessage+=" has been transferred from "+params.grantor;
          			      mailMessage+=" for the project entitled "+params.project;
        				  mailMessage+=" on "+params.dateOfTransfer_value+".";
        				  mailMessage+=" \nKindly acknowledge receipt of the fund in GMS. ";
        			      mailMessage=mailMessage+" \n\n\n" 
        			      mailMessage+=mailFooter
        			      notificationsEmailsService.sendConfirmation(userInstance[0].email,mailSubject,mailMessage,"text/plain")
        				}
        			  
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
    	 def fundTransferInstance = FundTransfer.get(params.id)
       def grantAllocationInstance = GrantAllocation.get(fundTransferInstance.grantAllocationId)
       
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
    	def userService = new UserService()
        def fundTransfer = FundTransfer.get(params.id)
        def grantAllocationInstance = GrantAllocation.get(params.grantAllocationId)
        def fundTransferService=new FundTransferService();
        if (fundTransfer) 
        {
        	def fundTransferInstance=new FundTransfer()
        	def actualAmt=fundTransfer.amount
            fundTransferInstance.properties = params
            
            def chkFundTransferAmnt=fundTransferService.chkAmntValidation(fundTransfer)
            def totFundTransfered = FundTransfer.executeQuery("select sum(FT.amount) from FundTransfer FT where FT.grantAllocation.id="+fundTransfer.grantAllocationId)
           
            double totalFund
            if(totFundTransfered[0]!=null)
            {
            totalFund = new Double(totFundTransfered[0]).doubleValue() + fundTransferInstance.amount-actualAmt
            }
            else
            {
            	totalFund = fundTransferInstance.amount 
            }
            
            def grantReceiptService = new GrantReceiptService()
            def chkReceiveFund=grantReceiptService.chkReceiveFundTransfer(fundTransfer)
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
        	   fundTransfer.amount=fundTransferInstance.amount 
        	   fundTransfer.dateOfTransfer=fundTransferInstance.dateOfTransfer 
            if (fundTransfer.save()) {
            	ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();	
            	def authorityInstance = Authority.find("from Authority A where A.authority = 'ROLE_SITEADMIN'")
  			  def userMapList = userService.getAllUsersByPartyID(params.Recepient)
  			  def userInstance = userService.getSiteAdminOfReceiver(userMapList,authorityInstance)
  			  EmailValidator emailValidator = EmailValidator.getInstance()
  			  if (emailValidator.isValid(userInstance.email))
  			  {
  				  def mailSubject="GMS Fund Transfer Information Against Project "+params.project;
  				  def mailFooter=gmsSettingsService.getGmsSettingsValue("MailFooter")
  				  String mailMessage="";
  				  mailMessage="Dear "+userInstance[0].userRealName+" "+userInstance[0].userSurName+", \n\n ";
  			      mailMessage+="\n\nAn amount of Rs."+currencyFormatter.ConvertToIndainRS(fundTransferInstance.amount)+" /-";
  			      mailMessage+="has been transferred from "+params.grantor;
				  mailMessage+=" for the project entitled "+params.project;	
				  mailMessage+=" on "+params.dateOfTransfer_value+".";
				  mailMessage+=" \nKindly acknowledge receipt of the fund in GMS. ";	
  			      mailMessage=mailMessage+" \n\n" 
			     mailMessage+=mailFooter
  			     notificationsEmailsService.sendConfirmation(userInstance[0].email,mailSubject,mailMessage,"text/plain")
  				}
                flash.message = "${message(code: 'default.updated.label')}"
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
