import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import java.text.*;
import java.util.*;
import org.apache.commons.validator.EmailValidator
import grails.converters.*
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
class ExternalFundAllocationController {
	
	def notificationsEmailsService
	def gmsSettingsService
	def grantAllocationService
	def fundTransferService
	ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [externalFundAllocationInstanceList: ExternalFundAllocation.list(params), externalFundAllocationInstanceTotal: ExternalFundAllocation.count()]
    }

    def create = 
    {
    	GrailsHttpSession gh=getSession()
	gh.removeValue("Help")
	gh.putValue("Help","External_Fund.htm")//putting help pages in session
    	def granterInstance = Party.get(gh.getValue("Party"))
    	def grantAllocationList
    	def allocationInstanceList=[]
    	def externalFundAllocationInstanceList = []
    	def allocation
    	def externalFundAllocationInstance = new ExternalFundAllocation()
    	externalFundAllocationInstance.grantAllocation = new GrantAllocation()
    	externalFundAllocationInstance.properties = params
    	NumberFormat formatter = new DecimalFormat("#0.00");
    	def externalFundAllocationInst = grantAllocationService.getexternalFundAllocationList()
    	for(int i=0;i<externalFundAllocationInst.size();i++ )
 	    {
	    	grantAllocationList = grantAllocationService.getgrantAllocationListByGranterId(granterInstance,externalFundAllocationInst[i].grantAllocation.id)
	    	for(int j=0;j<grantAllocationList.size();j++)
			{
			    allocation = grantAllocationList[j]
	    		allocationInstanceList.add(allocation)
	    		def extFundAllnInst = grantAllocationService.getExternalFundAllocationByGrantAllocation(grantAllocationList[j].id)
	    		externalFundAllocationInstanceList.add(extFundAllnInst)
			}
	    }
    	return [currencyFormat:currencyFormatter,externalFundAllocationInstance: externalFundAllocationInstance,
    	        allocationInstanceList:allocationInstanceList,externalFundAllocationInst:externalFundAllocationInst,
    	        'allocatedAmount':formatter.format(externalFundAllocationInstance.grantAllocation.amountAllocated),
    	        externalFundAllocationInstanceList:externalFundAllocationInstanceList,granterInstance:granterInstance]
    }

    def save =
    {
    	GrailsHttpSession gh=getSession()
    	def userService = new UserService()
        def externalFundAllocationInstance = new ExternalFundAllocation(params)
    	def userInstance = Person.get(gh.getValue("UserId"))
    	def projectsInstance = Projects.get(params.projects)
    	def grantorInstance = Party.get(gh.getValue("Party"))
    	def partyInstance = Party.get(params.recipient)
    	def grantAllocationInstance = new GrantAllocation()
    	grantAllocationInstance.projects = projectsInstance 
    	grantAllocationInstance.party =  partyInstance
    	grantAllocationInstance.granter = grantorInstance
    	grantAllocationInstance.allocationType="Grant"
    	grantAllocationInstance.createdBy=userInstance.username
        grantAllocationInstance.modifiedBy="admin"
        grantAllocationInstance.createdDate=new Date()
        grantAllocationInstance.modifiedDate=new Date()
        grantAllocationInstance.code="default"
        grantAllocationInstance.amountAllocated= Double.parseDouble(params.amountAllocated)
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    	String dateOfGranting = params.dateOfGranting_day + "/" + params.dateOfGranting_month + "/" + params.dateOfGranting_year;
    	Date grantingDate = dateFormat.parse(dateOfGranting);
        grantAllocationInstance.DateOfAllocation = grantingDate
    	grantAllocationInstance.remarks= params.remarks
    	grantAllocationInstance.sanctionOrderNo= params.sanctionOrderNo
    	if(grantAllocationInstance.save())
    	{
    		externalFundAllocationInstance.properties = params
    		externalFundAllocationInstance.grantAllocation =grantAllocationInstance
    		externalFundAllocationInstance.createdBy=userInstance.username
	    	 if (externalFundAllocationInstance.save(flush: true))
	    	 {
	    		 flash.message = "${message(code: 'default.created.label')}"
	    		 redirect(action: "create", id: externalFundAllocationInstance.id)
	         }
	        else 
	        {
	            render(view: "create", model: [externalFundAllocationInstance: externalFundAllocationInstance])
	        }
    	}
    }

    def show = {
        def externalFundAllocationInstance = ExternalFundAllocation.get(params.id)
        if (!externalFundAllocationInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'externalFundAllocation.label', default: 'ExternalFundAllocation'), params.id])}"
            redirect(action: "list")
        }
        else {
            [externalFundAllocationInstance: externalFundAllocationInstance]
        }
    }

    def edit = {
    	GrailsHttpSession gh=getSession()
    	def  NumberFormat formatter = new DecimalFormat("#0.00");
    	def grantAllocationInstance = GrantAllocation.get(params.id)
    	def projectList =  grantAllocationService.getgrantAllocationListByPartyId(grantAllocationInstance)
    	def externalFundAllocationInst = grantAllocationService.getexternalFundAllocationByGrantAllocationId(grantAllocationInstance)
        if (!externalFundAllocationInst) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'externalFundAllocation.label', default: 'ExternalFundAllocation'), params.id])}"
            redirect(action: "create")
        }
        else {
            return ['amount':formatter.format(externalFundAllocationInst.grantAllocation.amountAllocated),projectList:projectList,grantAllocationInstance:grantAllocationInstance,externalFundAllocationInst:externalFundAllocationInst]
        }
    }

    def update = {
    	 GrailsHttpSession gh=getSession()
    	 def externalFundAllocationInstance = ExternalFundAllocation.get(params.id)
         def userService = new UserService()
         if (externalFundAllocationInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (externalFundAllocationInstance.version > version) {
                    
                    externalFundAllocationInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'externalFundAllocation.label', default: 'ExternalFundAllocation')] as Object[], "Another user has updated this ExternalFundAllocation while you were editing")
                    render(view: "create", model: [externalFundAllocationInstance: externalFundAllocationInstance])
                    return
                }
            }
            def externalFundAllocationStatusInstance = ExternalFundAllocation.executeQuery("select status from ExternalFundAllocation EF where EF.id="+externalFundAllocationInstance.id)
        	if(externalFundAllocationStatusInstance[0] == 'Closed')
        	{
        		    flash.error = "${message(code: 'default.RefundingStatusisClosedByGranteeSoCantEdit.label')}"
        		    redirect(action: "create", id: externalFundAllocationInstance.id)
    	    }
        	else
        	{
	            def grantAllocationInstance = grantAllocationService.getgrantAllocationByGrantAllocationId(externalFundAllocationInstance)
	            def userInstance = Person.get(gh.getValue("UserId"))
	            def projectsInstance = Projects.get(params.projects)
	            grantAllocationInstance.projects = projectsInstance
	        	grantAllocationInstance.allocationType="Grant"
	        	grantAllocationInstance.modifiedBy=userInstance.username
	            grantAllocationInstance.modifiedDate=new Date()
	            grantAllocationInstance.code="default"
	            def fundTransferInstance = FundTransfer.find("from FundTransfer FT where FT.grantAllocation="+grantAllocationInstance.id)
	            if(fundTransferInstance)
	        	{
	        		flash.error = "${message(code: 'default.AsFundisTransfferedCantUpdate.label')}"
			    	redirect(action: "create", id: externalFundAllocationInstance.id)
	        	}
	        	else
	        	{
	        	    grantAllocationInstance.amountAllocated= Double.parseDouble(params.amountAllocated)
		        	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		            String dateOfGranting = params.dateOfGranting_day + "/" + params.dateOfGranting_month + "/" + params.dateOfGranting_year;
		            Date grantingDate = dateFormat.parse(dateOfGranting);
		            grantAllocationInstance.DateOfAllocation = grantingDate
		            grantAllocationInstance.remarks= params.remarks
		        	grantAllocationInstance.sanctionOrderNo= params.sanctionOrderNo
		        	if(grantAllocationInstance.save())
		        	{
			        	externalFundAllocationInstance.properties = params
			        	externalFundAllocationInstance.modifiedBy=userInstance.username
			            if (!externalFundAllocationInstance.hasErrors() && externalFundAllocationInstance.save(flush: true))
			    	    {	 
			    	    		flash.message = "${message(code: 'default.updated.label')}"
				                redirect(action: "create", id: externalFundAllocationInstance.id)
				         }
				         else 
				         {
				             render(view: "create", model: [externalFundAllocationInstance: externalFundAllocationInstance])
				         }
		        	}
	        	}
        	}
         }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'externalFundAllocation.label', default: 'ExternalFundAllocation'), params.id])}"
            redirect(action: "create")
        }
    }


    def delete = {
        def externalFundAllocationInstance = ExternalFundAllocation.get(params.id)
        if (externalFundAllocationInstance) {
            try {
                externalFundAllocationInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'externalFundAllocation.label', default: 'ExternalFundAllocation'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'externalFundAllocation.label', default: 'ExternalFundAllocation'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'externalFundAllocation.label', default: 'ExternalFundAllocation'), params.id])}"
            redirect(action: "list")
        }
    }
    
    def getRecipientProjects =
    {
    	def projectList=[]
        def grantAllocationInstanceList = grantAllocationService.getgrantAllocationPartyId(params)
        for(int i=0;i<grantAllocationInstanceList.size();i++)
	    {
	    	def projectsInstance = grantAllocationService.getprojectsByGrantAllocationInstanceList(grantAllocationInstanceList[i].projects.id)
	    	if(projectsInstance.activeYesNo == 'Y'){
				projectList.add(projectsInstance)
	    	}
	    }
	    render (template:"projectSelect", model : ['projectList' : projectList])
	 }
    
    
    def tranferFund = 
    {
    	GrailsHttpSession gh=getSession()
	gh.removeValue("Help")
	gh.putValue("Help","Fund_Transfer.htm")//putting help pages in session
    	NumberFormat formatter = new DecimalFormat("#0.00");
    	def grantAllocationInstance = GrantAllocation.get(params.id)
    	def fundTransferInstance = new FundTransfer()
        fundTransferInstance.properties = params
        def fundTransferInstanceList= grantAllocationService.getfundTransferListByGrantAllocationId(grantAllocationInstance)
        return ['amount':formatter.format(fundTransferInstance.amount),currencyFormat:currencyFormatter,fundTransferInstance:fundTransferInstance,fundTransferInstanceList:fundTransferInstanceList,grantAllocationInstance:grantAllocationInstance]
    }
    
    def saveTranferFund =
    {
    	GrailsHttpSession gh=getSession()
    	def userService = new UserService()
    	def grantAllocationInstance = GrantAllocation.get(params.grantAllotId)
    	def  amount = params.amountAllocated 
    	double balanceamount
    	def externalFundAllocationInstance = grantAllocationService.getexternalFundAllocationByGrantAllotId(params)
    	def externalFundAllocationStatusInstance = ExternalFundAllocation.executeQuery("select status from ExternalFundAllocation EF where EF.id="+externalFundAllocationInstance.id)
    	def transferInstance = grantAllocationService.getsumAmountofFundTransferByGrantAllotId(params)
    	def totalAmountInstance = grantAllocationService.getsumAllocatedAmountByGrantAllotId(grantAllocationInstance)
    	if(transferInstance)
        {
        	balanceamount = Double.valueOf(totalAmountInstance[0]) - Double.valueOf(transferInstance[0])
        }
    	def projectInstance = grantAllocationService.getprojectsByParamsValue(params)
    	def partyInstance = grantAllocationService.getpartyByParamsValue(params)
    	def fundTransferInstance = new FundTransfer()
		def userInstance = Person.get(gh.getValue("UserId"))
		fundTransferInstance.createdBy = userInstance.username
		fundTransferInstance.grantAllocation = grantAllocationInstance
		fundTransferInstance.amount =Double.parseDouble(params.amount)
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    	String dateOfGranting = params.dateOfTransfer_day + "/" + params.dateOfTransfer_month + "/" + params.dateOfTransfer_year;
    	Date grantingDate = dateFormat.parse(dateOfGranting);
        fundTransferInstance.dateOfTransfer = grantingDate
    	fundTransferInstance.createdDate = new Date()
    	fundTransferInstance.modifiedBy = "admin"
    	fundTransferInstance.modifiedDate = new Date()
    	if(externalFundAllocationStatusInstance[0] == 'Closed')
    	{
    		    flash.error = "${message(code: 'default.RefundingStatusisClosedByGranteeSoCantTransferAnyAmount.label')}"
		        redirect(action: "tranferFund", id: grantAllocationInstance.id,params:[fund:params.fund,currencyFormat:currencyFormatter])
	    }
    	else
    	{
	    	if(fundTransferInstance.amount > totalAmountInstance[0])
	    	{
	    		flash.error ="${message(code: 'default.TransferAmountLessThanTotalAmount.label')}"
	   			redirect(action: "tranferFund", id: grantAllocationInstance.id,params:[fund:params.fund,currencyFormat:currencyFormatter])
	    	}
	    	else
	    	{
	    		if(balanceamount!=null)
	       		{
	    			if(fundTransferInstance.amount > balanceamount)
	       			{
	    				flash.error ="${message(code: 'default.TransferAmountLessThanTotalAllocatedAmount.label')}"
	       				redirect(action: "tranferFund", id: grantAllocationInstance.id,params:[fund:params.fund,currencyFormat:currencyFormatter])
	       			}
	       			else
	       			{
	       					if (fundTransferInstance.save(flush: true)) 
							{
	       						  externalFundAllocationInstance.status = "Transferred"
								  def numformatter = new DecimalFormat("#0.00");
								  ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
								  def authorityInstance = Authority.find("from Authority A where A.authority = 'ROLE_SITEADMIN'")
								  def userMapList = userService.getAllUsersByPartyID(params.Recepient)
								  userInstance = userService.getSiteAdminOfReceiver(userMapList,authorityInstance)
								  EmailValidator emailValidator = EmailValidator.getInstance()
								  if (emailValidator.isValid(userInstance.email))
								  {
									  def mailSubject="GMS Fund Transfer Information Against Project "+projectInstance.code;
									  def mailFooter=gmsSettingsService.getGmsSettingsValue("MailFooter")
									  String mailMessage="";
									  mailMessage="Dear "+userInstance[0].userRealName+" "+userInstance[0].userSurName+", \n";
									  mailMessage+="\nAn amount of Rs."+currencyFormatter.ConvertToIndainRS(fundTransferInstance.amount)+" /-";
					 			      mailMessage+=" has been transferred from "+partyInstance.code;
					 			      mailMessage+=" for the project entitled "+projectInstance.code;
									  mailMessage+=" on "+params.dateOfTransfer_value+".";
									  mailMessage+=" \nKindly acknowledge receipt of the fund in GMS. ";
								      mailMessage=mailMessage+" \n\n\n" 
								      mailMessage+=mailFooter
								      notificationsEmailsService.sendConfirmation(userInstance[0].email,mailSubject,mailMessage,"text/plain")
									}
								  flash.message = "${message(code: 'default.created.label')}"
								  redirect(action: "tranferFund", id: grantAllocationInstance.id,params:[fund:params.fund,currencyFormat:currencyFormatter])
							  }
	       			}
	       		  }
				  else 
				  {
					  if (fundTransferInstance.save(flush: true)) 
						{
						  externalFundAllocationInstance.status = "Transferred"
							  def numformatter = new DecimalFormat("#0.00");
							  ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
							  def authorityInstance = Authority.find("from Authority A where A.authority = 'ROLE_SITEADMIN'")
							  def userMapList = userService.getAllUsersByPartyID(params.Recepient)
							  userInstance = userService.getSiteAdminOfReceiver(userMapList,authorityInstance)
							  EmailValidator emailValidator = EmailValidator.getInstance()
							  if (emailValidator.isValid(userInstance.email))
							  {
								  def mailSubject="GMS Fund Transfer Information Against Project "+projectInstance.code;
								  def mailFooter=gmsSettingsService.getGmsSettingsValue("MailFooter")
								  String mailMessage="";
								  mailMessage="Dear "+userInstance[0].userRealName+" "+userInstance[0].userSurName+", \n";
								  mailMessage+="\nAn amount of Rs."+currencyFormatter.ConvertToIndainRS(fundTransferInstance.amount)+" /-";
				 			      mailMessage+=" has been transferred from "+partyInstance.code;
				 			      mailMessage+=" for the project entitled "+projectInstance.code;
								  mailMessage+=" on "+params.dateOfTransfer_value+".";
								  mailMessage+=" \nKindly acknowledge receipt of the fund in GMS. ";
							      mailMessage=mailMessage+" \n\n\n" 
							      mailMessage+=mailFooter
							      notificationsEmailsService.sendConfirmation(userInstance[0].email,mailSubject,mailMessage,"text/plain")
								}
							  flash.message = "${message(code: 'default.created.label')}"
							  redirect(action: "tranferFund", id: grantAllocationInstance.id,params:[fund:params.fund,currencyFormat:currencyFormatter])
						  }
				  }
	    	}
    	}
    }
     
    def editTranferFund = 
    {
    	 def fundTransferInstance = FundTransfer.get(params.id)
         def grantAllocationInstance = GrantAllocation.get(fundTransferInstance.grantAllocationId)
         ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
    	  if (!fundTransferInstance) {
              flash.message = "${message(code: 'default.notfond.label')}"
              redirect(action: "tranferFund",params:[currencyFormat:currencyFormatter])
          }
          else {
          	NumberFormat formatter = new DecimalFormat("#0.00");
            return [fundTransferInstance: fundTransferInstance,
                      grantAllocationInstance: grantAllocationInstance,
                      currencyFormat:currencyFormatter,
                      amount:formatter.format(fundTransferInstance.amount)]
          }
    }
    
    def updateTranferFund = 
    {
    	 GrailsHttpSession gh=getSession()
    	 def userService = new UserService()
         def fundTransfer = FundTransfer.get(params.id)
         def grantAllocationInstance = GrantAllocation.get(params.grantAllocationId)
         def projectInstance = grantAllocationService.getprojectsByParamsValue(params)
    	 def partyInstance = grantAllocationService.getpartyByParamsValue(params)
    	 def fundTransferService=new FundTransferService();
    	 def userNameInstance = Person.get(gh.getValue("UserId"))
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
	        	   redirect(action: "tranferFund", id: grantAllocationInstance.id, params:[fund:params.fund,fundTransferInstance: fundTransfer,
	        	                       	                                                grantAllocationInstance: grantAllocationInstance,currencyFormat:currencyFormatter,fund:'ExternalAgency'])
	           }
	           else
	           {
	        	   fundTransfer.amount=fundTransferInstance.amount 
	        	   fundTransfer.dateOfTransfer=fundTransferInstance.dateOfTransfer 
	        	   fundTransfer.modifiedBy=userNameInstance.username
	        	   if (fundTransfer.save()) {
	               	ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();	
	               	def authorityInstance = Authority.find("from Authority A where A.authority = 'ROLE_SITEADMIN'")
	     			  def userMapList = userService.getAllUsersByPartyID(params.Recepient)
	     			  def userInstance = userService.getSiteAdminOfReceiver(userMapList,authorityInstance)
	     			  EmailValidator emailValidator = EmailValidator.getInstance()
	     			  if (emailValidator.isValid(userInstance.email))
	     			  {
	     				  def mailSubject="GMS Fund Transfer Information Against Project "+projectInstance.code;
	     				  def mailFooter=gmsSettingsService.getGmsSettingsValue("MailFooter")
	     				  String mailMessage="";
	     				  mailMessage="Dear "+userInstance[0].userRealName+" "+userInstance[0].userSurName+", \n\n ";
	     			      mailMessage+="\n\nAn amount of Rs."+currencyFormatter.ConvertToIndainRS(fundTransferInstance.amount)+" /-";
	     			      mailMessage+="has been transferred from "+partyInstance.code;
	     			      mailMessage+=" for the project entitled "+projectInstance.code;	
	     			      mailMessage+=" on "+params.dateOfTransfer_value+".";
	     			      mailMessage+=" \nKindly acknowledge receipt of the fund in GMS. ";	
	     			      mailMessage=mailMessage+" \n\n" 
	   			     mailMessage+=mailFooter
	     			     notificationsEmailsService.sendConfirmation(userInstance[0].email,mailSubject,mailMessage,"text/plain")
	     				}
	                   flash.message = "${message(code: 'default.updated.label')}"
	                   redirect(action: "tranferFund", id: grantAllocationInstance.id, params:[fund:params.fund])

	               }
		            else 
		            {
		            	 render(view: "tranferFund", model: [fundTransferInstance: fundTransfer,
		                                             grantAllocationInstance: grantAllocationInstance,
		                                             currencyFormat:currencyFormatter],
														params:[fund:params.fund])
		            }
	           }
            }
            else
	        {
	        	 flash.message = "${message(code: 'default.GrantRecievedFund.label')}"
	        	 render(view: "tranferFund", model: [fundTransferInstance: fundTransfer,
	                                              grantAllocationInstance: grantAllocationInstance,currencyFormat:currencyFormatter],
	 												params:[fund:params.fund])
	        }
        }
        else 
        {
        	flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "tranferFund",params:[subMenu:params.subMenu,currencyFormat:currencyFormatter])
        }
    }
    
    def externalFundList =
    {
    	GrailsHttpSession gh=getSession()
    	NumberFormat formatter = new DecimalFormat("#0.00");
    	def grantorInstance = Party.get(gh.getValue("Party"))
    	def allocationInstance
    	def grantAllocationInstanceList = []
    	def grantAllocationInstance = grantAllocationService.getallGrantAllocationByProjectIdThatsInExternalFundAllocation(params)
    	return [currencyFormat:currencyFormatter,grantAllocationInstance: grantAllocationInstance]
    }
    
    def receiveExternalFund = 
    {
    	def grantReceiptService = new GrantReceiptService();
    	def transferInstance
    	def grantReceiptInstance = new GrantReceipt()
    	grantReceiptInstance.properties = params
    	def fundTransferInstanceList = grantAllocationService.getfundTransferByGrantAllocation(params)
    	if(fundTransferInstanceList)
		{
	    	def  formatter = new SimpleDateFormat("dd/MM/yy");
			for(int i=0;i<fundTransferInstanceList.size();i++)
	    	{
				String transferDate = formatter.format(fundTransferInstanceList[i].dateOfTransfer);
				def numformatter = new DecimalFormat("#0.00");
				println numformatter.format(fundTransferInstanceList[i].amount) 
				fundTransferInstanceList[i].amountCode=transferDate + "-" + numformatter.format(fundTransferInstanceList[i].amount)
	    	}
		}
    	def accountHeadList=grantAllocationService.getgrantAllocationSplitByprojectId(params)
    	for(int i=0;i<accountHeadList.size();i++ )
	    {
	        	accountHeadList[i].accHeadPeriod=accountHeadList[i].accountHead.name.toUpperCase()+" ("+accountHeadList[i].grantPeriod.name+")"
	    }
    	 def subQry = "";
    	 def grantAllocationInstanceList = grantAllocationService.getgrantAllocationByIdInExternalFundAllocation(params)
    	 def  formatter = new SimpleDateFormat("dd/MM/yy");
		 for(int i=0;i<grantAllocationInstanceList.size();i++)
		 {
			 String s = formatter.format(grantAllocationInstanceList[i].DateOfAllocation);
			 def numformatter = new DecimalFormat("#0.00");
			 println numformatter.format(grantAllocationInstanceList[i].amountAllocated)
			 grantAllocationInstanceList[i].grantCode=s+"-"+numformatter.format(grantAllocationInstanceList[i].amountAllocated)
		 }
		 def grantAllocationInstance = GrantAllocation.get(params.id)
		 def grantReceiptInstanceList = grantAllocationService.getgrantReceiptByGrantAllocationIdAndProjectId(params)
		 def totalAmountReceived = grantReceiptService.getSumOfGrantReceviedByProjects(params.projectId)
		 return [grantReceiptInstance:grantReceiptInstance,fundTransferInstanceList:fundTransferInstanceList,
		         grantAllocationInstanceList:grantAllocationInstanceList,accountHeadList:accountHeadList,
		         grantReceiptInstanceList:grantReceiptInstanceList,grantAllocationInstance:grantAllocationInstance]
    }
    
    def saveReceiveExternalFund = 
    {
    	def grantReceiptService = new GrantReceiptService();
    	def grantAllocationService = new GrantAllocationService()
        def grantReceiptInstance = new GrantReceipt(params)
        def fundTransferInstance = new FundTransfer()
    	def grantReceiptInstanceCheck 
    	if(params.fundTransfer)
        {
        	
        	if(params.fundTransfer.id != "null")
        	{
        		fundTransferInstance = FundTransfer.get(new Integer(params.fundTransfer.id))
        		grantReceiptInstanceCheck = grantAllocationService.getgrantReceiptByFundTransferId(params)
        	}
        }
        if(!grantReceiptInstance.hasErrors() ) 
        {
        	grantReceiptInstance.createdBy="admin"
    		grantReceiptInstance.modifiedBy="admin"
        	grantReceiptInstance.createdDate=new Date()
    	    if((fundTransferInstance) && (fundTransferInstance.dateOfTransfer > grantReceiptInstance.dateOfReceipt))
    	    {
    	    	grantReceiptInstance.fundTransfer = fundTransferInstance
    	    	flash.message="${message(code: 'default.ReceiptDateValidationAgainstFundtransfer.label')}"
    	    	redirect(action:receiveExternalFund,id:params.projectId)
    	    }
    	    else
    	    {
    	    	if(fundTransferInstance)
    	    	{
    	    		grantReceiptInstance.fundTransfer = fundTransferInstance
    	    	    grantReceiptInstance.amount=fundTransferInstance.amount
    	        }
	    	    def grantAllocationInstanceList
	    		def fundTransferService=new FundTransferService();
	    	    def fundTransferInstanceList = grantAllocationService.getfundTransferGrantAllocationId(params)
	    	    if(fundTransferInstanceList)
	    		{
	    	    	def  formatter = new SimpleDateFormat("dd/MM/yy");
	    			for(int i=0;i<fundTransferInstanceList.size();i++)
	    	    	{
	    				String transferDate = formatter.format(fundTransferInstanceList[i].dateOfTransfer);
	    				def numformatter = new DecimalFormat("#0.00");
	    				println numformatter.format(fundTransferInstanceList[i].amount) 
	    				fundTransferInstanceList[i].amountCode=transferDate + "-" + numformatter.format(fundTransferInstanceList[i].amount)
	    	    	}
	    		}
	    	    if(!grantReceiptInstanceCheck)
	    	    {
		    	    def grantAllocationInstance = GrantAllocation.get(params.grantAllocation.id)
		    	    grantReceiptInstance = grantReceiptService.saveGrantReceipt(grantReceiptInstance,new Integer(params.projectId))
		        	flash.message = "${message(code: 'default.Grantreceived.label')}"
		        	redirect(action:receiveExternalFund,
		        			params:[id:grantAllocationInstance.id,grantReceiptInstanceId:grantReceiptInstance.id,projectId:grantAllocationInstance.projects.id],
		        			        fundTransferInstanceList:fundTransferInstanceList,grantAllocationInstance:grantAllocationInstance)
	    	    }
	    	    else
	    	    {
	    	    	flash.message = "${message(code: 'default.Amountalreadyreceived.label')}"
	    	    	redirect(action:receiveExternalFund,id:params.projectId)
	    	    }
    	  }
        }
        else {
            render(view:'receiveExternalFund',model:[grantReceiptInstance:grantReceiptInstance])
        }
    }
    
    def editReceiveExternalFund = 
    {
    	def grantReceiptService = new GrantReceiptService();
		def grantAllocationService = new GrantAllocationService()
        def grantReceiptInstance = grantReceiptService.getGrantReceiptById(new Integer(params.id))
        def dataSecurityService = new DataSecurityService()
		def grantAllocationSplitService=new GrantAllocationSplitService()
		def accountHeadList=grantAllocationService.getgrantAllocationSplitByprojectId(params)
		def fundTransferService=new FundTransferService();
		def fundTransferInstanceList = grantAllocationService.getfundTransferGrantAllocation(params)
		def grantAllocationInstanceList = grantAllocationService.getgrantAllocationIdInExtrnalFundAllocation(params)
		def  formatter = new SimpleDateFormat("dd/MM/yy");
		for(int i=0;i<grantAllocationInstanceList.size();i++)
		 {
			 String s = formatter.format(grantAllocationInstanceList[i].DateOfAllocation);
			 def numformatter = new DecimalFormat("#0.00");
			 println numformatter.format(grantAllocationInstanceList[i].amountAllocated)
			 grantAllocationInstanceList[i].grantCode=s+"-"+numformatter.format(grantAllocationInstanceList[i].amountAllocated)
		 }
		if(fundTransferInstanceList)
		{
			formatter = new SimpleDateFormat("dd/MM/yy");
			for(int i=0;i<fundTransferInstanceList.size();i++)
	    	{
				String transferDate = formatter.format(fundTransferInstanceList[i].dateOfTransfer);
				def numformatter = new DecimalFormat("#0.00");
				fundTransferInstanceList[i].amountCode=transferDate + "-" + numformatter.format(fundTransferInstanceList[i].amount)
	    	}
		}
        if(!grantReceiptInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:editReceiveExternalFund)
        }
        else {
        	return [ grantReceiptInstance : grantReceiptInstance,accountHeadList:accountHeadList,
                     grantAllocationInstanceList:grantAllocationInstanceList,
                     fundTransferInstanceList:fundTransferInstanceList]
        }
		
    }
    

	def updateReceiveExternalFund = 
		{
    		def grantReceiptService = new GrantReceiptService();
			def fundTransferInstance = new FundTransfer()
			def grantAllocationInstance = GrantAllocation.get(params.grantAllocation.id)
			if(params.fundTransfer)
		    {
		    	
		    	if(params.fundTransfer.id != "null")
		    	{
		    		fundTransferInstance = FundTransfer.get(new Integer(params.fundTransfer.id))
		    	}
		    }
			def grantReceiptInstance = grantReceiptService.getGrantReceiptById(new Integer(params.id))
			//create SimpleDateFormat object with desired date format
		    SimpleDateFormat sdfDestination = new SimpleDateFormat("dd/MM/yyyy");
		    //parse the date into another format
		    String strDate = sdfDestination.format(fundTransferInstance.dateOfTransfer);
		    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		    Date strtingDate = df.parse(strDate)
		    Date receiptDate = df.parse(params.dateOfReceipt_value)
		    if((fundTransferInstance) && (strtingDate > receiptDate))
			 {
				grantReceiptInstance.fundTransfer = fundTransferInstance
				flash.message="${message(code: 'default.ReceiptDateValidationAgainstFundtransfer.label')}"
				redirect(action:editReceiveExternalFund,id:params.id)
			}
			else
			{
			    grantReceiptInstance = grantReceiptService.updateGrantReceipt(params)
			    if(grantReceiptInstance) 
			    {
		         	if(grantReceiptInstance.isSaved)
		    		{
			    		flash.message = "${message(code: 'default.updated.label')}"
						redirect(action:receiveExternalFund,
								params:[id:grantAllocationInstance.id,grantReceiptInstanceId:grantReceiptInstance.id,projectId:grantAllocationInstance.projects.id],
	        			        grantAllocationInstance:grantAllocationInstance)
		    		}
		    		else 
		    		{
		    			render(view:'editReceiveExternalFund',model:[grantReceiptInstance:grantReceiptInstance])
		    		}
			    }
			    else {
			        flash.message = "${message(code: 'default.notfond.label')}"
			        redirect(action:editReceiveExternalFund,id:params.id)
			    }
			}
		}
    
    def viewExpenses =
    {
    	def grantExpenseInstanceList = grantAllocationService.getgrantExpenseByGrantAllocationId(params)
    	return [grantExpenseInstanceList:grantExpenseInstanceList]
    }
    def agencyCreate = 
    {
    	GrailsHttpSession gh=getSession()
		gh.removeValue("Help")
		gh.putValue("Help","ExternalFund_Agency.htm")//putting help pages in session
    	def granterInstance =  Party.get(gh.getValue("Party"))
    	def agentInstance = Party.get(params.agentInstance)
    	def projectInstance= Projects.get(params.id)
    	def grantAllocationList
    	def allocationInstanceList=[]
    	def externalFundAllocationInstanceList = []
    	def allocation
    	def externalFundAllocationInstance = new ExternalFundAllocation()
    	externalFundAllocationInstance.grantAllocation = new GrantAllocation()
    	externalFundAllocationInstance.properties = params
    	NumberFormat formatter = new DecimalFormat("#0.00");
    	def externalFundAllocationInst = grantAllocationService.getexternalFundAllocationList()
    	
    	def grantAllocationInstList
    	for(int i=0;i<externalFundAllocationInst.size();i++ )
 	    {
    		grantAllocationInstList =grantAllocationService.getgrantAllocationListByPartyTypeGA(projectInstance,granterInstance,externalFundAllocationInst[i].grantAllocation.id)
    		for(int j=0;j<grantAllocationInstList.size();j++)
			{
			    allocation = grantAllocationInstList[j]
	    		allocationInstanceList.add(allocation)
	    		def extFundAllnInst = grantAllocationService.getExternalFundAllocationByGrantAllocation(grantAllocationInstList[j].id)
	    		externalFundAllocationInstanceList.add(extFundAllnInst)
			}
	    }
    	return [currencyFormat:currencyFormatter,externalFundAllocationInstance: externalFundAllocationInstance,
    	        allocationInstanceList:allocationInstanceList,externalFundAllocationInst:externalFundAllocationInst,
    	        'allocatedAmount':formatter.format(externalFundAllocationInstance.grantAllocation.amountAllocated),
    	        externalFundAllocationInstanceList:externalFundAllocationInstanceList,granterInstance:granterInstance]
    }

    def agencySave =
    {
    	GrailsHttpSession gh=getSession()
    	def userService = new UserService()
        def externalFundAllocationInstance = new ExternalFundAllocation(params)
    	def userInstance = Person.get(gh.getValue("UserId"))
    	def projectsInstance = Projects.get(params.projects)
    	def grantorInstance = Party.get(params.recipient)
    	def partyInstance =  Party.get(gh.getValue("Party"))
    	def grantAllocationInstance = new GrantAllocation()
    	grantAllocationInstance.projects = projectsInstance 
    	grantAllocationInstance.party =  partyInstance
    	grantAllocationInstance.granter = grantorInstance
    	grantAllocationInstance.allocationType="Grant"
    	grantAllocationInstance.createdBy=userInstance.username
        grantAllocationInstance.modifiedBy="admin"
        grantAllocationInstance.createdDate=new Date()
        grantAllocationInstance.modifiedDate=new Date()
        grantAllocationInstance.code="default"
        grantAllocationInstance.amountAllocated= new Double(params.amountAllocated)
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    	String dateOfGranting = params.dateOfGranting_day + "/" + params.dateOfGranting_month + "/" + params.dateOfGranting_year;
    	Date grantingDate = dateFormat.parse(dateOfGranting);
        grantAllocationInstance.DateOfAllocation = grantingDate
    	grantAllocationInstance.remarks= params.remarks
    	grantAllocationInstance.sanctionOrderNo= params.sanctionOrderNo
    	if(grantAllocationInstance.save())
    	{
    		externalFundAllocationInstance.properties = params
    		externalFundAllocationInstance.grantAllocation =grantAllocationInstance
    		externalFundAllocationInstance.createdBy=userInstance.username
	    	 if (externalFundAllocationInstance.save(flush: true))
	    	 {
	    		 flash.message = "${message(code: 'default.created.label')}"
	    		 redirect(action: "agencyCreate",params:[agentInstance:grantorInstance.id,id:projectsInstance.id])
	         }
	        else 
	        {
	            render(view: "create", model: [externalFundAllocationInstance: externalFundAllocationInstance])
	        }
    	}
    }
   def agencyEdit = {
    	GrailsHttpSession gh=getSession()
    	def  NumberFormat formatter = new DecimalFormat("#0.00");
    	def grantAllocationInstance = GrantAllocation.get(params.id)
    	def projectList =  grantAllocationService.getgrantAllocationListByPartyId(grantAllocationInstance)
    	def externalFundAllocationInst = grantAllocationService.getexternalFundAllocationByGrantAllocationId(grantAllocationInstance)
        if (!externalFundAllocationInst) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'externalFundAllocation.label', default: 'ExternalFundAllocation'), params.id])}"
            redirect(action: "agencyCreate")
        }
        else {
            return ['amount':formatter.format(externalFundAllocationInst.grantAllocation.amountAllocated),projectList:projectList,grantAllocationInstance:grantAllocationInstance,externalFundAllocationInst:externalFundAllocationInst]
        }
    }

    def agencyUpdate = {
    	 GrailsHttpSession gh=getSession()
    	 def externalFundAllocationInstance = ExternalFundAllocation.get(params.id)
         def userService = new UserService()
         if (externalFundAllocationInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (externalFundAllocationInstance.version > version) {
                    
                    externalFundAllocationInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'externalFundAllocation.label', default: 'ExternalFundAllocation')] as Object[], "Another user has updated this ExternalFundAllocation while you were editing")
                    render(view: "create", model: [externalFundAllocationInstance: externalFundAllocationInstance])
                    return
                }
            }
           
	            	def grantAllocationInstance = grantAllocationService.getgrantAllocationByGrantAllocationId(externalFundAllocationInstance)
	           	   def userInstance = Person.get(gh.getValue("UserId"))
		            def projectsInstance = Projects.get(params.projects)
		            grantAllocationInstance.projects = projectsInstance
		        	grantAllocationInstance.allocationType="Grant"
		        	grantAllocationInstance.modifiedBy=userInstance.username
		            grantAllocationInstance.modifiedDate=new Date()
		            grantAllocationInstance.code="default"
		            def fundTransferInstance = FundTransfer.find("from FundTransfer FT where FT.grantAllocation="+grantAllocationInstance.id)
        			if(fundTransferInstance)
	        		{
	        			flash.error = "${message(code: 'default.AsFundisTransfferedCantUpdate.label')}"
			    		redirect(action: "agencyCreate", id: projectsInstance.id)
	        		}
	        		else
	        		{
			            grantAllocationInstance.amountAllocated= Double.parseDouble(params.amountAllocated)
			        	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			            String dateOfGranting = params.dateOfGranting_day + "/" + params.dateOfGranting_month + "/" + params.dateOfGranting_year;
			            Date grantingDate = dateFormat.parse(dateOfGranting);
			            grantAllocationInstance.DateOfAllocation = grantingDate
			        	grantAllocationInstance.remarks= params.remarks
			        	grantAllocationInstance.sanctionOrderNo= params.sanctionOrderNo
			        	
			        	if(grantAllocationInstance.save())
			        	{
			        	
					        	externalFundAllocationInstance.properties = params
					        	externalFundAllocationInstance.modifiedBy=userInstance.username
					            if (!externalFundAllocationInstance.hasErrors() && externalFundAllocationInstance.save(flush: true))
					    	    {	 
					            		flash.message = "${message(code: 'default.updated.label')}"
						                redirect(action: "agencyCreate", id: projectsInstance.id)
						         }
						         else 
						         {
						             render(view: "create", model: [externalFundAllocationInstance: externalFundAllocationInstance])
						         }
						}
        			}
       		 }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'externalFundAllocation.label', default: 'ExternalFundAllocation'), params.id])}"
            redirect(action: "create")
        }
    }

  	
}
