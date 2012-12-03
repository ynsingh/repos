import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import java.text.*;
import org.apache.commons.validator.EmailValidator
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


class ExpenseRequestEntryController {

	def notificationsEmailsService
	def gmsSettingsService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    
    def index = {
        redirect(action: "list", params: params)
    }
    def grantAllocationService
    def projectsService
    def userService = new UserService()
	def investigatorService = new InvestigatorService()
	def expenseRequestService = new ExpenseRequestService()
    def proposalApprovalAuthorityMapService = new ProposalApprovalAuthorityMapService()
    def approvalAuthorityService = new ApprovalAuthorityService()
    def proposalApprovalService = new ProposalApprovalService()
	def approvalAuthorityDetailService = new ApprovalAuthorityDetailService()
    def proposalApprovalDetailService = new ProposalApprovalDetailService()
	
    
    
    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [expenseRequestEntryInstanceList: ExpenseRequestEntry.list(params), expenseRequestEntryInstanceTotal: ExpenseRequestEntry.count()]
    }
/*
 * Function to enter Expense Request.
 */
 def create = {
	    	def dataSecurityService = new DataSecurityService()
	    	def grantExpenseService = new GrantExpenseService()
	    	GrailsHttpSession gh=getSession()
			gh.removeValue("Help")
			gh.putValue("Help","Create_ExpenseRequest.htm")//putting help pages in session
	    	def projectsList = []
	    	def userInstance = userService.getUserById(new Integer(gh.getValue("UserId").intValue()))
	    	def investigatorInstance = investigatorService.getInvestigatorByemail(userInstance.email)
	    	if(investigatorInstance)
	    	{
	    	def expenseRequestEntryInstanceList = expenseRequestService
					.getExpenseRequestEntryByInvestigator(investigatorInstance.id)//get past requested list of login PI
	    	def expenseRequestEntryInstance = new ExpenseRequestEntry()
	        expenseRequestEntryInstance.properties = params
	        def grantAllocationWithprojectsInstanceList = grantAllocationService
					.getGrantAllocationGroupByProjects(gh.getValue("Party"))//get the grant allocation of projects have access permission for login PI 
					
			for(int i=0;i<grantAllocationWithprojectsInstanceList.size();i++)
	        {
				def projectInstance = projectsService
								.getActiveProjectById(grantAllocationWithprojectsInstanceList[i].projects.id)
				projectsList.add(projectInstance)
	        }
	    	for(int i=0;i<expenseRequestEntryInstanceList.size();i++)
	    	{
	    def grantExpenseAmount = grantExpenseService.getGrantExpenseAmountByRequestCode(expenseRequestEntryInstanceList[i].expenseRequestCode)
	   
	    if(grantExpenseAmount[0])
	    {
	    if(expenseRequestEntryInstanceList[i].expenseAmount == grantExpenseAmount[0])
	    {
	    	expenseRequestEntryInstanceList[i].paymentStatus = "Paid in full"
	    }
	    	else
	    	{
	    		
	    		if(expenseRequestEntryInstanceList[i].expenseAmount > grantExpenseAmount[0])
	    			expenseRequestEntryInstanceList[i].paymentStatus = "Partial Payment"
	    	}
	    }
	    else
	    {
	    	expenseRequestEntryInstanceList[i].paymentStatus = "On hold"
	    }
	    	}
	    	ConvertToIndainRS currencyFormatter=new ConvertToIndainRS()
	    	NumberFormat formatter = new DecimalFormat("#0.00")
	        return [expenseRequestEntryInstance: expenseRequestEntryInstance,
	                expenseRequestEntryInstanceList: expenseRequestEntryInstanceList,
	                projectsList:projectsList,'currencyFormat':currencyFormatter,
	                'amount':formatter.format(expenseRequestEntryInstance.expenseAmount),
	                'invoiceAmount':formatter.format(expenseRequestEntryInstance.invoiceAmount)]
	    }
	    	else
	    	{
	    		redirect uri:'/invalidPIAccess.gsp'
	    	}
    }
/*
 * Function to save Expense Request.
 */
    def save = {
    	GrailsHttpSession gh=getSession()
    	def dataSecurityService = new DataSecurityService()
    	def projectsList = dataSecurityService.getProjectsForLoginUser(gh.getValue("PartyID"))
    	def userInstance = userService.getUserById(new Integer(gh.getValue("UserId").intValue()))
    	params.createdBy = userInstance.username
		params.createdDate = new Date()
		params.status = 'Pending'
    	params.level = 0
    	def investigatorInstance = investigatorService.getInvestigatorByemail(userInstance.email)// get the PI details
    	params.investigator = investigatorInstance
    	if(params.invoiceAmount == "")
    	{
    		params.invoiceAmount = 0
    	}
		def expenseRequestEntryInstance = new ExpenseRequestEntry(params)
		
    	def cal = Calendar.instance				
    	def year = cal.get(Calendar.YEAR)
    	def month = cal.get(Calendar.MONTH)+ 1
    	def date = cal.get(Calendar.DATE)
    	def dat =year.toString() + month.toString() + date.toString() +
    	cal.get(Calendar.HOUR_OF_DAY)+cal.get(Calendar.MINUTE)+cal.get(Calendar.SECOND)
    	//cal.time = new Date(time * 1000)
    	expenseRequestEntryInstance.expenseRequestCode = expenseRequestEntryInstance.projects.code + dat + investigatorInstance.id  // generate Expense request code
    	
    	
    	if (expenseRequestEntryInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.label')}"
            redirect(action: "create", id: expenseRequestEntryInstance.id)
        }
        else {
            render(view: "create", model: [expenseRequestEntryInstance: expenseRequestEntryInstance])
        }
    }

    def show = {
        def expenseRequestEntryInstance = ExpenseRequestEntry.get(params.id)
        if (!expenseRequestEntryInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "list")
        }
        else {
            [expenseRequestEntryInstance: expenseRequestEntryInstance]
        }
    }
/*
 * Function to edit Expense Request.
 */
    def edit = {
    	GrailsHttpSession gh=getSession()
    	def dataSecurityService = new DataSecurityService()
    	
    	def expenseRequestEntryInstance = ExpenseRequestEntry.get(params.id)
    	def proposalApprovalAuthorityMapInstanceList = proposalApprovalAuthorityMapService
    			.getProposalApprovalAuthorityMapInstanceListByProposalId(expenseRequestEntryInstance.id) // get proposal Approval Authority Map By Request Entry.
        def projectsList = []
    	def grantAllocationWithprojectsInstanceList = grantAllocationService
			.getGrantAllocationGroupByProjects(gh.getValue("Party")) //get the grant allocation of projects have access permission for login PI 
		for(int i=0;i<grantAllocationWithprojectsInstanceList.size();i++)
        {
			def projectInstance = projectsService
					.getActiveProjectById(grantAllocationWithprojectsInstanceList[i].projects.id) // get the project list by grant Allocation.
			projectsList.add(projectInstance) 
        }
        ConvertToIndainRS currencyFormatter=new ConvertToIndainRS()
        NumberFormat formatter = new DecimalFormat("#0.00")
        if (!expenseRequestEntryInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "create")
        }
        else {
            return [expenseRequestEntryInstance: expenseRequestEntryInstance,projectsList:projectsList,
                    proposalApprovalAuthorityMapInstanceList:proposalApprovalAuthorityMapInstanceList,
                    'currencyFormat':currencyFormatter,
                    'expenseAmount':formatter.format(expenseRequestEntryInstance.expenseAmount),
                    'invoiceAmount':formatter.format(expenseRequestEntryInstance.invoiceAmount)]
        }
    }
/*
 * Function to update Expense Request.
 */
    def update = {
    	GrailsHttpSession gh=getSession()
    	def userInstance = userService.getUserById(new Integer(gh.getValue("UserId").intValue()))
    	params.modifiedBy = userInstance.username
		params.modifiedDate = new Date()
        def expenseRequestEntryInstance = ExpenseRequestEntry.get(params.id)
        if (expenseRequestEntryInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (expenseRequestEntryInstance.version > version) {
                    
                    expenseRequestEntryInstance.errors.rejectValue("Another user has updated this ExpenseRequestEntry while you were editing")
                    render(view: "edit", model: [expenseRequestEntryInstance: expenseRequestEntryInstance])
                    return
                }
            }
            if(params.invoiceAmount == "")
        	{
        		params.invoiceAmount = 0
        	}
            expenseRequestEntryInstance.properties = params
            if (!expenseRequestEntryInstance.hasErrors() && expenseRequestEntryInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.label')}"
                redirect(action: "create", id: expenseRequestEntryInstance.id)
            }
            else {
                render(view: "edit", model: [expenseRequestEntryInstance: expenseRequestEntryInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "edit")
        }
    }
/*
 * Function to delete Expense Request.
 */
    def delete = {
        def expenseRequestEntryInstance = ExpenseRequestEntry.get(params.id)
        if (expenseRequestEntryInstance) {
            try {
                expenseRequestEntryInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.label')}"		
                redirect(action: "create")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.inuse.label')}"
                redirect(action: "create", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "create")
        }
    }
/*
 * Function to get Expense Request in finance login.
 */    
    def financeLogin = {
    	GrailsHttpSession gh=getSession()
		gh.removeValue("Help")
		gh.putValue("Help","ExpenseRequest_List.htm")//putting help pages in session
    	def grantAllocationInstanceList = grantAllocationService
    			.getGrantAllocationByPartyIdGroupByProjects(gh.getValue("PartyID"))//get the grant allocation of projects have access permission for login finance person
    	def expenseRequestEntryInstanceList = []
    	def proposalApprovalAuthorityMapInstanceList = []
    	for(int i=0;i<grantAllocationInstanceList.size();i++)
		{
    		def expenseRequestEntryInstance = expenseRequestService
    				.getExpenseRequestEntryByProjectId(grantAllocationInstanceList[i].projects.id) //get the Expense request list based on project access permission
    		for(int j=0;j<expenseRequestEntryInstance.size();j++)
    		{
    			def expenseRequestMaxOrder = proposalApprovalAuthorityMapService
    					.getPreProposalApprovalMaxOrder('ExpenseRequest',expenseRequestEntryInstance[j].id)// get the heighest approve order of an expense request. 
    	    	def proposalApprovalAuthorityMapInstance = proposalApprovalAuthorityMapService
    	    		.getProposalApprovalAuthorityMapByApproveOrder(expenseRequestEntryInstance[j],expenseRequestMaxOrder[0]) // get the proposalApprovalAuthorityMapInstance have heighest approve order.
    	    	proposalApprovalAuthorityMapInstanceList.add(proposalApprovalAuthorityMapInstance)
    	    	if(expenseRequestEntryInstance)
	    		{
    	    		expenseRequestEntryInstanceList.add(expenseRequestEntryInstance[j])
	    		}
    		}
		}
    	ConvertToIndainRS currencyFormatter=new ConvertToIndainRS()
    	[expenseRequestEntryInstanceList:expenseRequestEntryInstanceList,'currencyFormat':currencyFormatter,
    	 proposalApprovalAuthorityMapInstanceList:proposalApprovalAuthorityMapInstanceList]
    }
/*
 * Function to edit invoice details and to get Approval authority list of the institution
 */     
    def expenseRequestDetails = {
    	GrailsHttpSession gh=getSession()
    	def attachmentsService = new AttachmentsService()
    	def grantReceiptService = new GrantReceiptService()
		def grantExpenseService = new GrantExpenseService()
		def fundTransferService = new FundTransferService()
		
    	def expenseRequestEntryInstance = expenseRequestService.getExpenseRequestEntryById(params.id)
		def userMapList = userService.getAllUsersByPartyID(gh.getValue("PartyID"))
    	def userList = []
    	for(int i=0;i<userMapList.size();i++)
		{
    		def userInstance = userService.getUserById((userMapList[i].user.id).intValue())
    		userList.add(userInstance)
		}
    	def approvalAuthorityInstance = approvalAuthorityService.getApprovalAuthorityByParty(gh.getValue("Party"))
		
		expenseRequestEntryInstance.projects.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProject(expenseRequestEntryInstance.projects.id,gh.getValue("PartyID"))
    	double receivedAmount = grantReceiptService.getSumOfAmountReceivedForProject(expenseRequestEntryInstance.projects.id)		
		def totExpense = grantExpenseService.getTotalExpenseByProject(expenseRequestEntryInstance.projects.id)
		def sumTransferInstance = fundTransferService.sumTransferInstanceByProject(expenseRequestEntryInstance.projects.id)
		def sumSubGrantAllot = grantAllocationService.getTotalSubdrantAllot(expenseRequestEntryInstance.projects.id)
		
	  	ConvertToIndainRS currencyFormatter=new ConvertToIndainRS()
    	NumberFormat formatter = new DecimalFormat("#0.00")
    	[expenseRequestEntryInstance:expenseRequestEntryInstance,
    	 userList:userList,approvalAuthorityInstance:approvalAuthorityInstance,
    	 'currencyFormat':currencyFormatter,'sumTransferInstance':sumTransferInstance,
    	 'expenseAmount':formatter.format(expenseRequestEntryInstance.expenseAmount),
         'invoiceAmount':formatter.format(expenseRequestEntryInstance.invoiceAmount),
		 'totAllAmount':expenseRequestEntryInstance.projects.totAllAmount,
		 'receivedAmount':receivedAmount,'totExpense':totExpense,'sumSubGrantAllot':sumSubGrantAllot]
		  	
    	
    }
    
/*
 * Function to save invoice details and sent evaluation request to selected Approval authority.
 */     
    
    def submit = {
    	GrailsHttpSession gh=getSession()
    	if(params.invoiceAmount == "")
    	{
    		params.invoiceAmount = 0
    	}
    	def approvalAuthorityDetailsInstanceList = []
    	def expenseRequestEntryInstance = expenseRequestService
    			.getExpenseRequestEntryById(params.expenseRequestEntry)
    	expenseRequestEntryInstance.invoiceNo = params.invoiceNo
    	DateFormat df = new SimpleDateFormat("dd/MM/yyyy")
    	expenseRequestEntryInstance.invoiceDate  = df.parse(params.invoiceDate_value)
    	expenseRequestEntryInstance.invoiceAmount = new Double(params.invoiceAmount)
    	expenseRequestEntryInstance.save() // save the new/updated invoice details.
    	def proposalApprovalAuthorityMapInstanceList = proposalApprovalAuthorityMapService
    		.getProposalApprovalAuthorityMapInstanceListByProposalId(expenseRequestEntryInstance.id)
    	
    	def approvalAuthorityInstance =ApprovalAuthority.get(new Integer(params.approvalAuthority.id).intValue())
    	def userInstance = Person.get(gh.getValue("UserId"))
    	def userMapInstance = UserMap.find("from UserMap  UM where UM.user.id= "+userInstance.id)
    	def proposalApprovalAuthorityMapInstance
    	def approveOrder = 0
    	
    	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    def expenseDate = dateFormat.format(expenseRequestEntryInstance.dateOfExpense)
	    Date date = new Date();
	    def requestDate = dateFormat.format(date) 
	    
    	if(expenseRequestEntryInstance.status == 'Pending')
    	{
	    	if(proposalApprovalAuthorityMapInstanceList)
	    	{
		    	for(int i=0;i<proposalApprovalAuthorityMapInstanceList.size();i++)
				{
		    		if(approveOrder < (proposalApprovalAuthorityMapInstanceList[i].approveOrder))
		    		{
		    			approveOrder = (proposalApprovalAuthorityMapInstanceList[i].approveOrder)
		    			proposalApprovalAuthorityMapInstance =  proposalApprovalAuthorityMapInstanceList[i]// get proposal Approval Authority Map having heigh approve order.
		    		}
				}
		    	if(proposalApprovalAuthorityMapInstance.approvalAuthority.approveAll =='Y')
		    	{
		    		
			    	def approvalAuthorityDetailInstanceList = approvalAuthorityDetailService
			    		.getApprovalAuthorityDetailByApprovalAuthority(proposalApprovalAuthorityMapInstance.approvalAuthority.id)// get the list of persons in current authority level.
			    	def proposalApprovalInstanceList = proposalApprovalService
			    		.getProposalApprovalInstanceListByProposalApprovalAuthorityMapInstance(approvalAuthorityDetailInstanceList,proposalApprovalAuthorityMapInstance) // get the list of persons reviewed in current authority level.
			    	if(approvalAuthorityDetailInstanceList.size() == proposalApprovalInstanceList.size())
					{
			    		def proposalApprovalAuthorityMapDuplicateInstance= proposalApprovalAuthorityMapService.getProposalApprovalAuthorityMapInstanceListByProposalIdAndType(expenseRequestEntryInstance.id,params)
			        	if(proposalApprovalAuthorityMapDuplicateInstance)
			        	{
			        		flash.error = "${message(code: 'default.CannotSend.message')}"
			        		redirect(action: "financeLogin")
					    		
			        			
			        	}
			        	else
			        	{
						proposalApprovalAuthorityMapInstance = proposalApprovalAuthorityMapService
							.saveProposalApprovalAuthorityMap(proposalApprovalAuthorityMapInstanceList,approvalAuthorityInstance,userInstance,expenseRequestEntryInstance) //create new approval authority map by selected approval authority.
		    			flash.message = "${message(code: 'default.SendSuccessfully.message')}"
		    			redirect(action: "financeLogin")
		    		}
					}
		    		else{
		    			flash.error = "${message(code: 'default.RequestPending.message')}"
		    			redirect(action: "financeLogin")
		    		}
		    	}
		    	else 
		    	{
		    		def approvalAuthorityDetailInstanceList = approvalAuthorityDetailService
		    		.getApprovalAuthorityDetailByApprovalAuthority(proposalApprovalAuthorityMapInstance.approvalAuthority.id)// get the list of persons in current authority level.
		    	   def proposalApprovalInstanceList = proposalApprovalService
		    		.getProposalApprovalInstanceListByProposalApprovalAuthorityMapInstance(approvalAuthorityDetailInstanceList,proposalApprovalAuthorityMapInstance) // get the list of persons reviewed in current authority level.
		    	    if(proposalApprovalInstanceList)
		    	    {
		    	    	
		    	    
		    		def proposalApprovalAuthorityMapDuplicateInstance= proposalApprovalAuthorityMapService.getProposalApprovalAuthorityMapInstanceListByProposalIdAndType(expenseRequestEntryInstance.id,params)
		        	if(proposalApprovalAuthorityMapDuplicateInstance)
		        	{
		        		flash.error = "${message(code: 'default.CannotSend.message')}"
		        		redirect(action: "financeLogin")
				  	}
		        	else
		        	{
		    		proposalApprovalAuthorityMapInstance = proposalApprovalAuthorityMapService
		    			.saveProposalApprovalAuthorityMap(proposalApprovalAuthorityMapInstanceList,approvalAuthorityInstance,userInstance,expenseRequestEntryInstance) //create new approval authority map by selected approval authority.
	    			
	    			
	    			     if (proposalApprovalAuthorityMapInstance) 
					    	{
								approvalAuthorityDetailsInstanceList = ApprovalAuthorityDetail.findAll("from ApprovalAuthorityDetail AAD where AAD.approvalAuthority.id="+approvalAuthorityInstance.id+" and AAD.activeYesNo='Y'")
									if(approvalAuthorityDetailsInstanceList)
										{
													
											for(int k=0;k<approvalAuthorityDetailsInstanceList.size();k++)
											    {
													
													EmailValidator emailValidator = EmailValidator.getInstance()
													  if (emailValidator.isValid(approvalAuthorityDetailsInstanceList[k].person.email))
													  {
														  def mailSubject="GMS Expense request Information Against a Project ";
														  def mailFooter=gmsSettingsService.getGmsSettingsValue("MailFooter")
														  String mailMessage="";
														  mailMessage="Dear "+approvalAuthorityDetailsInstanceList[k].person.userRealName+" "+approvalAuthorityDetailsInstanceList[k].person.userSurName+", \n";
														  mailMessage+=" An expense request has been sent to you on GMS by "+userInstance.userRealName+" "+userInstance.userSurName+" for the project "+expenseRequestEntryInstance.projects.code+" and is pending your approval. ";
														  mailMessage+=" \n\nDetails are as given below:";
														  
													       mailMessage+=" \n\nDate of Request:"+requestDate;
													       mailMessage+=" \nExpense Description:"+expenseRequestEntryInstance.expenseDescription;
													       mailMessage+=" \nExpense Amount:"+expenseRequestEntryInstance.expenseAmount;
													       mailMessage+=" \nDate of Expense:"+expenseDate;
													     
													      mailMessage=mailMessage+" \n" 
													      mailMessage+=" \nRegards," 
													      mailMessage+=" \nGrant Management System (GMS) at "+userMapInstance.party.code;
													      mailMessage+=" \n[This is an auto-generated e-mail. Please do not reply] ";
													      notificationsEmailsService.sendConfirmation(approvalAuthorityDetailsInstanceList[k].person.email,mailSubject,mailMessage,"text/plain")
											   
											         }
											    
											    }
									  }			
				           }  	    			
	    			
	    			
	    			
	    			flash.message = "${message(code: 'default.SendSuccessfully.message')}"
	    			redirect(action: "financeLogin")
		        	}
		    	    }
		    	    else
		    	    {
		    	    	flash.error = "${message(code: 'default.RequestPending.message')}"
			    		redirect(action: "financeLogin")
		    	    }
		    		
		    	}
		 	}
	    	
	    	else{
	    		
				  proposalApprovalAuthorityMapInstance = proposalApprovalAuthorityMapService
	    			.saveProposalApprovalAuthorityMap(proposalApprovalAuthorityMapInstanceList,approvalAuthorityInstance,userInstance,expenseRequestEntryInstance)
					    	
					    	if (proposalApprovalAuthorityMapInstance) 
					    	{
								approvalAuthorityDetailsInstanceList = ApprovalAuthorityDetail.findAll("from ApprovalAuthorityDetail AAD where AAD.approvalAuthority.id="+approvalAuthorityInstance.id+" and AAD.activeYesNo='Y'")
									if(approvalAuthorityDetailsInstanceList)
										{
													
											for(int k=0;k<approvalAuthorityDetailsInstanceList.size();k++)
											    {
													
													EmailValidator emailValidator = EmailValidator.getInstance()
													  if (emailValidator.isValid(approvalAuthorityDetailsInstanceList[k].person.email))
													  {
														  def mailSubject="GMS Expense request Information Against a Project ";
														  def mailFooter=gmsSettingsService.getGmsSettingsValue("MailFooter")
														  String mailMessage="";
														  mailMessage="Dear "+approvalAuthorityDetailsInstanceList[k].person.userRealName+" "+approvalAuthorityDetailsInstanceList[k].person.userSurName+", \n";
														  mailMessage+=" An expense request has been sent to you on GMS by "+userInstance.userRealName+" "+userInstance.userSurName+" for the project "+expenseRequestEntryInstance.projects.code+" and is pending your approval. ";
														  mailMessage+=" \n\nDetails are as given below:";
														  
													       mailMessage+=" \n\nDate of Request:"+requestDate;
													       mailMessage+=" \nExpense Description:"+expenseRequestEntryInstance.expenseDescription;
													       mailMessage+=" \nExpense Amount:"+expenseRequestEntryInstance.expenseAmount;
													       mailMessage+=" \nDate of Expense:"+expenseDate;
													     
													      mailMessage=mailMessage+" \n" 
													      mailMessage+=" \nRegards," 
													      mailMessage+=" \nGrant Management System (GMS) at "+userMapInstance.party.code;
													      mailMessage+=" \n[This is an auto-generated e-mail. Please do not reply] ";
													      notificationsEmailsService.sendConfirmation(approvalAuthorityDetailsInstanceList[k].person.email,mailSubject,mailMessage,"text/plain")
											   
											         }
											    
											    }
									  }			
				           }  	
					    	
						flash.message = "${message(code: 'default.submittedsuccessfully.message')}"
						redirect(action: "financeLogin")
	    	   }
    	   
    	}
    	else
    	{
    		flash.error = "${message(code: 'default.RequestCannotBeProceeded.message')}"
			redirect(action: "financeLogin")
    	}
    	
    }
/*
 * Function to list the status details of each member in Approval authority.
 */     
 def approvalStatus = {

 	if(params.financeSide == 'financeSide')   
	    
	    {
	    	def approvalAuthorityInstanceList = []
	    	def proposalApprovalInstanceList = []
	    	int s=0
	    	def sizeList = []
	    	def approvalAuthorityDetailList = []
	    	def proposalApprovalDetailInstanceList = []
	    	def proposalApprovalAuthorityMapInstanceList = []
	    	def expenseRequestEntryInstance = expenseRequestService.getExpenseRequestEntryById(params.id)
	    	
	    	proposalApprovalAuthorityMapInstanceList = proposalApprovalAuthorityMapService
	    			.getProposalApprovalAuthorityMapInstanceListByProposalId(params.id) // get all proposal Approval Authority Map.
	    	
	    	for(int i=0;i<proposalApprovalAuthorityMapInstanceList.size();i++)
			{
	    		def approvalAuthorityInstance = ApprovalAuthority
	    			.get( proposalApprovalAuthorityMapInstanceList[i].approvalAuthority.id) 
	    		approvalAuthorityInstanceList.add(approvalAuthorityInstance)// get all approval authority to which request had send.
			}
	    		
	    		
	    	if(expenseRequestEntryInstance.status == 'Pending')
	    	{
	    		def expenseRequestMaxOrder = proposalApprovalAuthorityMapService
	    			.getPreProposalApprovalMaxOrder('ExpenseRequest',expenseRequestEntryInstance.id) // get the heighest approve order of an expense request.
		    	def newProposalApprovalAuthorityMapInstance = proposalApprovalAuthorityMapService
		    		.getProposalApprovalAuthorityMapByApproveOrder(expenseRequestEntryInstance,expenseRequestMaxOrder[0]) // get the proposalApprovalAuthorityMapInstance have heighest approve order.
		    	for(int j=0;j<proposalApprovalAuthorityMapInstanceList.size();j++)
		    	{
		    		if(proposalApprovalAuthorityMapInstanceList[j].approveOrder == newProposalApprovalAuthorityMapInstance.approveOrder)
			    	{
			    		
		    			def approvalAuthorityDetailInstanceList = approvalAuthorityDetailService
		    				.getApprovalAuthorityDetailByApprovalAuthority(proposalApprovalAuthorityMapInstanceList[j].approvalAuthority.id)//get members in each authority.
			        	sizeList[s] = approvalAuthorityDetailInstanceList.size()
			        	s++
			        	for(int k=0;k<approvalAuthorityDetailInstanceList.size();k++)
			        	{
			        		def proposalApprovalInstance = proposalApprovalService
			        			.getProposalApprovalByProposalApprovalAuthorityMapAndUser(proposalApprovalAuthorityMapInstanceList[j].id,approvalAuthorityDetailInstanceList[k].id) //get the list of persons reviewed in current authority level.
			        		approvalAuthorityDetailList.add(approvalAuthorityDetailInstanceList[k])
			        		if(proposalApprovalInstance)
			        		{
			        			def proposalApprovalDetailInstance = proposalApprovalDetailService
			        				.proposalApprovalDetailByProposalApproval(proposalApprovalInstance.id)
			            		proposalApprovalDetailInstanceList.add(proposalApprovalDetailInstance) // get the status of each reviewed member in the authority.
			            		proposalApprovalInstanceList.add(proposalApprovalInstance)
			            		
			        		}
			        		else
			        		{
			        			proposalApprovalInstanceList.add(null)
			        			
			        		}
			        		
			        	}
		    		
					}
			    	else
			    	{
			    		def approvalAuthorityDetailInstanceList = approvalAuthorityDetailService
	    				.getApprovalAuthorityDetailByApprovalAuthority(proposalApprovalAuthorityMapInstanceList[j].approvalAuthority.id)//get members in each authority.
			    		
		    			proposalApprovalInstanceList = proposalApprovalService
		    				.getProposalApprovalByProposalApprovalAuthorityMap(proposalApprovalAuthorityMapInstanceList[j].id) 
		    			sizeList[s] = proposalApprovalInstanceList.size()
			        	s++
			        	for(int l=0;l<proposalApprovalInstanceList.size();l++)
			        	{
			        		def proposalApprovalDetailInstance = proposalApprovalDetailService
			        			.proposalApprovalDetailByProposalApproval(proposalApprovalInstanceList[l].id)
		            		proposalApprovalDetailInstanceList.add(proposalApprovalDetailInstance) // get the status of each reviewed member in the authority.
			        	
		            		approvalAuthorityDetailList.add(approvalAuthorityDetailInstanceList[l])
			        	}
					}
		    	}
	    	}
	    	else
	    	{
	    		for(int i=0;i<proposalApprovalAuthorityMapInstanceList.size();i++)
				{
	    			def approvalAuthorityDetailInstanceList = approvalAuthorityDetailService
					.getApprovalAuthorityDetailByApprovalAuthority(proposalApprovalAuthorityMapInstanceList[i].approvalAuthority.id)//get members in each authority.
	    			
	    			proposalApprovalInstanceList = proposalApprovalService
	    				.getProposalApprovalByProposalApprovalAuthorityMap(proposalApprovalAuthorityMapInstanceList[i].id) // get reviewed list of each authority.
	    			sizeList[s] = proposalApprovalInstanceList.size()
		        	s++
		        	for(int j=0;j<proposalApprovalInstanceList.size();j++)
		        	{
		        		def proposalApprovalDetailInstance = proposalApprovalDetailService
		        			.proposalApprovalDetailByProposalApproval(proposalApprovalInstanceList[j].id)
	            		proposalApprovalDetailInstanceList.add(proposalApprovalDetailInstance) // get the status of each reviewed member in the authority.
	            		approvalAuthorityDetailList.add(approvalAuthorityDetailInstanceList[j])
		        	}
				}
	    		
	    	}
	    	[proposalApprovalAuthorityMapInstanceList:proposalApprovalAuthorityMapInstanceList,
	    	 approvalAuthorityInstanceList:approvalAuthorityInstanceList,
	    	 expenseRequestEntryInstance:expenseRequestEntryInstance,sizeList:sizeList,
	    	 proposalApprovalDetailInstanceList:proposalApprovalDetailInstanceList,
	    	 approvalAuthorityDetailList:approvalAuthorityDetailList]
    
		 }
		
		 else
		  {
 	    	def approvalAuthorityInstanceList = []
	    	def proposalApprovalInstanceList = []
	    	int s=0
	    	def sizeList = []
	    	def approvalAuthorityDetailList = []
	    	def proposalApprovalDetailInstanceList = []
	    	def proposalApprovalAuthorityMapInstanceList = []
	    	def proposalApprovalAuthorityMapInstance = ProposalApprovalAuthorityMap.find("from ProposalApprovalAuthorityMap PPM where PPM.id="+params.id)
	    	def expenseRequestEntryInstance = expenseRequestService.getExpenseRequestEntryById(proposalApprovalAuthorityMapInstance.proposalId)
	    	proposalApprovalAuthorityMapInstanceList = ProposalApprovalAuthorityMap.findAll("from ProposalApprovalAuthorityMap PPM where PPM.proposalId="+proposalApprovalAuthorityMapInstance?.proposalId+" and  PPM.id != "+proposalApprovalAuthorityMapInstance.id+" and PPM.proposalType = 'ExpenseRequest'")
	    	
	    	for(int i=0;i<proposalApprovalAuthorityMapInstanceList.size();i++)
			{
	    		def approvalAuthorityInstance = ApprovalAuthority
	    			.get( proposalApprovalAuthorityMapInstanceList[i].approvalAuthority.id) 
	    		approvalAuthorityInstanceList.add(approvalAuthorityInstance)// get all approval authority to which request had send.
			}
	    		
	    		
	    	if(expenseRequestEntryInstance.status == 'Pending')
	    	{
	    		def expenseRequestMaxOrder = proposalApprovalAuthorityMapService
	    			.getPreProposalApprovalMaxOrder('ExpenseRequest',expenseRequestEntryInstance.id) // get the heighest approve order of an expense request.
		    	def newProposalApprovalAuthorityMapInstance = proposalApprovalAuthorityMapService
		    		.getProposalApprovalAuthorityMapByApproveOrder(expenseRequestEntryInstance,expenseRequestMaxOrder[0]) // get the proposalApprovalAuthorityMapInstance have heighest approve order.
		    	for(int j=0;j<proposalApprovalAuthorityMapInstanceList.size();j++)
		    	{
		    		if(proposalApprovalAuthorityMapInstanceList[j].approveOrder == newProposalApprovalAuthorityMapInstance.approveOrder)
			    	{
			    		
		    			def approvalAuthorityDetailInstanceList = approvalAuthorityDetailService
		    				.getApprovalAuthorityDetailByApprovalAuthority(proposalApprovalAuthorityMapInstanceList[j].approvalAuthority.id)//get members in each authority.
			        	sizeList[s] = approvalAuthorityDetailInstanceList.size()
			        	s++
			        	for(int k=0;k<approvalAuthorityDetailInstanceList.size();k++)
			        	{
			        		def proposalApprovalInstance = proposalApprovalService
			        			.getProposalApprovalByProposalApprovalAuthorityMapAndUser(proposalApprovalAuthorityMapInstanceList[j].id,approvalAuthorityDetailInstanceList[k].id) //get the list of persons reviewed in current authority level.
			        		approvalAuthorityDetailList.add(approvalAuthorityDetailInstanceList[k])
			        		if(proposalApprovalInstance)
			        		{
			        			def proposalApprovalDetailInstance = proposalApprovalDetailService
			        				.proposalApprovalDetailByProposalApproval(proposalApprovalInstance.id)
			            		proposalApprovalDetailInstanceList.add(proposalApprovalDetailInstance) // get the status of each reviewed member in the authority.
			            		proposalApprovalInstanceList.add(proposalApprovalInstance)
			            		
			        		}
			        		else
			        		{
			        			proposalApprovalInstanceList.add(null)
			        			
			        		}
			        		
			        	}
		    		
					}
			    	else
			    	{
			    		def approvalAuthorityDetailInstanceList = approvalAuthorityDetailService
	    				.getApprovalAuthorityDetailByApprovalAuthority(proposalApprovalAuthorityMapInstanceList[j].approvalAuthority.id)//get members in each authority.
			    		
		    			proposalApprovalInstanceList = proposalApprovalService
		    				.getProposalApprovalByProposalApprovalAuthorityMap(proposalApprovalAuthorityMapInstanceList[j].id) 
		    			sizeList[s] = proposalApprovalInstanceList.size()
			        	s++
			        	for(int l=0;l<proposalApprovalInstanceList.size();l++)
			        	{
			        		def proposalApprovalDetailInstance = proposalApprovalDetailService
			        			.proposalApprovalDetailByProposalApproval(proposalApprovalInstanceList[l].id)
		            		proposalApprovalDetailInstanceList.add(proposalApprovalDetailInstance) // get the status of each reviewed member in the authority.
			        	
		            		approvalAuthorityDetailList.add(approvalAuthorityDetailInstanceList[l])
			        	}
					}
		    	}
	    	}
	    	else
	    	{
	    		for(int i=0;i<proposalApprovalAuthorityMapInstanceList.size();i++)
				{
	    			def approvalAuthorityDetailInstanceList = approvalAuthorityDetailService
					.getApprovalAuthorityDetailByApprovalAuthority(proposalApprovalAuthorityMapInstanceList[i].approvalAuthority.id)//get members in each authority.
	    			
	    			proposalApprovalInstanceList = proposalApprovalService
	    				.getProposalApprovalByProposalApprovalAuthorityMap(proposalApprovalAuthorityMapInstanceList[i].id) // get reviewed list of each authority.
	    			sizeList[s] = proposalApprovalInstanceList.size()
		        	s++
		        	for(int j=0;j<proposalApprovalInstanceList.size();j++)
		        	{
		        		def proposalApprovalDetailInstance = proposalApprovalDetailService
		        			.proposalApprovalDetailByProposalApproval(proposalApprovalInstanceList[j].id)
	            		proposalApprovalDetailInstanceList.add(proposalApprovalDetailInstance) // get the status of each reviewed member in the authority.
	            		approvalAuthorityDetailList.add(approvalAuthorityDetailInstanceList[j])
		        	}
				}
	    		
	    	}
	    	[proposalApprovalAuthorityMapInstanceList:proposalApprovalAuthorityMapInstanceList,
	    	 approvalAuthorityInstanceList:approvalAuthorityInstanceList,
	    	 expenseRequestEntryInstance:expenseRequestEntryInstance,sizeList:sizeList,
	    	 proposalApprovalDetailInstanceList:proposalApprovalDetailInstanceList,
	    	 approvalAuthorityDetailList:approvalAuthorityDetailList]
  
  
  
  }


}
	    
    
/*
 * Function to list the requests in Approval authority members login.
 */     
    def expenseApprovalRequest = {
    	GrailsHttpSession gh=getSession()
		gh.removeValue("Help")
		gh.putValue("Help","Evaluate_Expense_Request.htm")//putting help pages in session
    	def approvalAuthorityDetailInstanceList = approvalAuthorityDetailService
    			.getApprovalAuthorityDetailInstanceListByPerson(gh.getValue("UserId")) // get the authority list where login user assigned as a member.
    	def expenseRequestEntryInstanceList = []
    	def proposalApprovalAuthorityMapInstanceList = []
    	def proposalApprovalDetailInstanceList = []
    	for(int i=0;i<approvalAuthorityDetailInstanceList.size();i++)
		{	
    		def proposalApprovalAuthorityMapInstance = proposalApprovalAuthorityMapService
    			.getProposalApprovalAuthorityMapByApprovalauthority(approvalAuthorityDetailInstanceList[i].approvalAuthority.id)//get proposal Approval authority map based on authority.
    		if(proposalApprovalAuthorityMapInstance)
    		{
	    		for(int j=0;j<proposalApprovalAuthorityMapInstance.size();j++)
				{
	    			
	    			def expenseRequestEntryInstance = expenseRequestService
	    				.getExpenseRequestEntryById(proposalApprovalAuthorityMapInstance[j].proposalId) // get all expense request of the proposal.
	    			if(expenseRequestEntryInstance!=null)
					{
						if(expenseRequestEntryInstance.status == 'Pending')
	    			    {
	    				def expenseRequestMaxOrder = proposalApprovalAuthorityMapService
	    					.getPreProposalApprovalMaxOrder('ExpenseRequest',expenseRequestEntryInstance.id) // get the heighest approve order of an expense request.
	        	    	def newProposalApprovalAuthorityMapInstance = proposalApprovalAuthorityMapService
	        	    		.getProposalApprovalAuthorityMapByApproveOrder(expenseRequestEntryInstance,expenseRequestMaxOrder[0]) // get the proposalApprovalAuthorityMapInstance have heighest approve order.
	        	    	if(proposalApprovalAuthorityMapInstance[j].approveOrder >= newProposalApprovalAuthorityMapInstance.approveOrder)
	        	    	{
			    			proposalApprovalAuthorityMapInstanceList.add(proposalApprovalAuthorityMapInstance[j])
			    			expenseRequestEntryInstanceList.add(expenseRequestEntryInstance)
			    			def proposalApprovalInstance = proposalApprovalService
			    				.getProposalApprovalByProposalApprovalAuthorityMapAndUser(proposalApprovalAuthorityMapInstance[j].id,approvalAuthorityDetailInstanceList[i].id) // get the reviewed list of login member.
			    			if(proposalApprovalInstance)
			    			{
				    			def proposalApprovalDetailInstance = proposalApprovalDetailService
				    				.proposalApprovalDetailByProposalApproval(proposalApprovalInstance.id)
				    			proposalApprovalDetailInstanceList.add(proposalApprovalDetailInstance) //get the review status of request by the login user.
				    		}
	        	    	}
	    			    
					}
				}
    		}
		}
	}	
		
		[approvalAuthorityDetailInstanceList:approvalAuthorityDetailInstanceList,expenseRequestEntryInstanceList:expenseRequestEntryInstanceList,
    	 proposalApprovalAuthorityMapInstanceList:proposalApprovalAuthorityMapInstanceList,proposalApprovalDetailInstanceList:proposalApprovalDetailInstanceList]
    }
/*
 * Function to evaluate the Expense Request.
 */     
    def approveReject = {
    	GrailsHttpSession gh=getSession()
		def grantExpenseService = new GrantExpenseService()
		def fundTransferService = new FundTransferService()
		def grantReceiptService = new GrantReceiptService()
		
    	def proposalApprovalDetailInstance = new ProposalApprovalDetail()
    	def proposalApprovalAuthorityMapInstance = proposalApprovalAuthorityMapService.getProposalAuthorityMapById(params.id)
    	def expenseRequestEntryInstance = expenseRequestService.getExpenseRequestEntryById(proposalApprovalAuthorityMapInstance.proposalId)
    	def approvalAuthorityInstance = approvalAuthorityService.getApprovalAuthorityByParty(gh.getValue("Party"))
    	def approvalAuthorityDetailInstance = approvalAuthorityDetailService
    		.getApprovalAuthorityDetailByApprovalAuthorityUser(proposalApprovalAuthorityMapInstance.approvalAuthority.id,gh.getValue("UserId")) // get the authority list where login user assigned as a member.
    	def proposalApprovalInstance = proposalApprovalService
		.getProposalApprovalByProposalApprovalAuthorityMapAndUser(proposalApprovalAuthorityMapInstance.id,approvalAuthorityDetailInstance.id) // get the reviewed status of login member.
		if(proposalApprovalInstance)
		{
			proposalApprovalDetailInstance = proposalApprovalDetailService
			.proposalApprovalDetailByProposalApproval(proposalApprovalInstance.id)
		}
		
		expenseRequestEntryInstance.projects.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProject(expenseRequestEntryInstance.projects.id,gh.getValue("PartyID")) //For
		double receivedAmount = grantReceiptService.getSumOfAmountReceivedForProject(expenseRequestEntryInstance.projects.id) //getting Account
		def totExpense = grantExpenseService.getTotalExpenseByProject(expenseRequestEntryInstance.projects.id)                //details of a project
		def sumTransferInstance = fundTransferService.sumTransferInstanceByProject(expenseRequestEntryInstance.projects.id)   
		def sumSubGrantAllot = grantAllocationService.getTotalSubdrantAllot(expenseRequestEntryInstance.projects.id)           
		
    	ConvertToIndainRS currencyFormatter=new ConvertToIndainRS()
		[expenseRequestEntryInstance:expenseRequestEntryInstance,'currencyFormat':currencyFormatter,approvalAuthorityInstance:approvalAuthorityInstance,
    	 proposalApprovalAuthorityMapInstance:proposalApprovalAuthorityMapInstance,proposalApprovalDetailInstance:proposalApprovalDetailInstance,
		 'receivedAmount':receivedAmount,'totExpense':totExpense,'sumTransferInstance':sumTransferInstance,'sumSubGrantAllot':sumSubGrantAllot,
		 'totAllAmount':expenseRequestEntryInstance.projects.totAllAmount]
    }
/*
 * Function to enter the Expense after approval.
 */     
    def expenseEntry = {
	GrailsHttpSession gh=getSession()
    	def grantAllocationSplitService=new GrantAllocationSplitService()
    	def grantExpenseService = new GrantExpenseService()
    	def grantExpenseInstance = new GrantExpense()
		def grantReceiptService = new GrantReceiptService()
		def fundTransferService = new FundTransferService()
	
    	def expenseRequestEntryInstance = expenseRequestService.getExpenseRequestEntryById(new Integer(params.id))
    	def accountHeadList=grantAllocationSplitService.getAccountHeadByProject(expenseRequestEntryInstance.projects.id)
    	def grantAllocationInstanceList=grantAllocationService.getGrantAllocationsByProject(expenseRequestEntryInstance.projects.id)
    	def grantExpenseInstanceList = grantExpenseService.getGrantExpenseByRequestCode(expenseRequestEntryInstance.expenseRequestCode)// get expense request based on request code.

		expenseRequestEntryInstance.projects.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProject(expenseRequestEntryInstance.projects.id,gh.getValue("PartyID"))
    	double receivedAmount = grantReceiptService.getSumOfAmountReceivedForProject(expenseRequestEntryInstance.projects.id)		
		def totExpense = grantExpenseService.getTotalExpenseByProject(expenseRequestEntryInstance.projects.id)
		def sumTransferInstance = fundTransferService.sumTransferInstanceByProject(expenseRequestEntryInstance.projects.id)
		def sumSubGrantAllot = grantAllocationService.getTotalSubdrantAllot(expenseRequestEntryInstance.projects.id)
		
	    ConvertToIndainRS currencyFormatter=new ConvertToIndainRS()
    	NumberFormat formatter = new DecimalFormat("#0.00")
    	[expenseRequestEntryInstance:expenseRequestEntryInstance,accountHeadList:accountHeadList,
    	 grantAllocationInstanceList:grantAllocationInstanceList,grantExpenseInstanceList:grantExpenseInstanceList,
    	 'amount':formatter.format(grantExpenseInstance.expenseAmount),grantExpenseInstance:grantExpenseInstance,'currencyFormat':currencyFormatter,
         'sumTransferInstance':sumTransferInstance,'totAllAmount':expenseRequestEntryInstance.projects.totAllAmount,
		 'receivedAmount':receivedAmount,'totExpense':totExpense,'sumSubGrantAllot':sumSubGrantAllot]
    }
/*
 * Function to save evaluation result of Expense Request.
 */     
    def submitApproval = {
    	
    	GrailsHttpSession gh=getSession()
    	def proposalApprovalAuthorityMapInstance = proposalApprovalAuthorityMapService.getProposalAuthorityMapById(params.proposalApprovalAuthorityMapId)
    	def approvalAuthorityDetailInstance = approvalAuthorityDetailService
    		.getApprovalAuthorityDetailByApprovalAuthorityUser(proposalApprovalAuthorityMapInstance.approvalAuthority.id,gh.getValue("UserId")) // get the authority list where login user assigned as a member.
    	def userInstance = userService.getUserById(new Integer(gh.getValue("UserId").intValue()))
    	params.proposalApprovalAuthorityMap = proposalApprovalAuthorityMapInstance
    	params.approvalAuthorityDetail = approvalAuthorityDetailInstance
    	params.createdBy = userInstance.username
		params.createdDate = new Date()
    	def proposalApprovalInstance = new ProposalApproval(params)
    	if (proposalApprovalInstance.save(flush: true)) 
    	{
		
	    	params.proposalApproval = proposalApprovalInstance
	    	params.proposalStatus = params.status
	    	params.remarks = params.description
	    	params.activeYesNo = 'Y'
	    	params.approvalDate = new Date()
	    	def proposalApprovalDetailInstance = new ProposalApprovalDetail(params)
	    	if (proposalApprovalDetailInstance.save(flush: true)) //Save the review status.
	    	{
	    		flash.message = "${message(code: 'default.created.label')}"
    			redirect(action: "approveReject",params:[id:proposalApprovalAuthorityMapInstance.id])
	    	}
	    	
    	}
    }
/*
 * Function to route Expense Request to other approval authority from evaluated members login.
 */     
    def sendRequest = {
    	GrailsHttpSession gh=getSession()
    	def proposalApprovalAuthorityMapInstance = proposalApprovalAuthorityMapService
    		.getProposalAuthorityMapById(params.proposalApprovalAuthorityMapId)
    	def approvalAuthorityInstance =ApprovalAuthority.get(params.approvalAuthority.id)
    	def userInstance = userService.getUserById(new Integer(gh.getValue("UserId").intValue()))
    	def expenseRequestEntryInstance = expenseRequestService
    		.getExpenseRequestEntryById(proposalApprovalAuthorityMapInstance.proposalId)
    	def proposalApprovalAuthorityMapInstanceList = proposalApprovalAuthorityMapService
		.getProposalApprovalAuthorityMapInstanceListByProposalId(expenseRequestEntryInstance.id)
    	def order = proposalApprovalAuthorityMapService.checkApproveOrder(proposalApprovalAuthorityMapInstance,proposalApprovalAuthorityMapInstanceList)
    
       
    	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    def expenseDate = dateFormat.format(expenseRequestEntryInstance.dateOfExpense)
	    Date date = new Date();
	    def requestDate = dateFormat.format(date)
    
    	def userMapInstance = UserMap.find("from UserMap  UM where UM.user.id= "+userInstance.id)
     
    	if(order > (proposalApprovalAuthorityMapInstance.approveOrder))
    	{
    		flash.error = "${message(code: 'default.requestalreadysend.message')}"
			redirect(action: "expenseApprovalRequest")
    	}
    	else
    	{
	    	if(proposalApprovalAuthorityMapInstance.approvalAuthority.approveAll =='Y')
	    	{
	    		def approvalAuthorityDetailInstanceList =approvalAuthorityDetailService
	    			.getApprovalAuthorityDetailByApprovalAuthority(proposalApprovalAuthorityMapInstance.approvalAuthority.id)
	        	def proposalApprovalInstanceList = proposalApprovalService
	        		.getProposalApprovalInstanceListByProposalApprovalAuthorityMapInstance(approvalAuthorityDetailInstanceList,proposalApprovalAuthorityMapInstance)
	    		if(approvalAuthorityDetailInstanceList.size() == proposalApprovalInstanceList.size())
	    		{
	    			def proposalApprovalAuthorityMapDuplicateInstance= proposalApprovalAuthorityMapService.getProposalApprovalAuthorityMapInstanceListByProposalIdAndType(expenseRequestEntryInstance.id,params)
		        	if(proposalApprovalAuthorityMapDuplicateInstance)
		        	{
		        		flash.error = "${message(code: 'default.CannotSend.message')}"
		        		redirect(action: "expenseApprovalRequest")
				 	}
		        	else
		        	{
	    			proposalApprovalAuthorityMapInstance = proposalApprovalAuthorityMapService
	    				.saveProposalApprovalAuthorityMap(proposalApprovalAuthorityMapInstanceList,approvalAuthorityInstance,userInstance,expenseRequestEntryInstance)
	    			
	    			
	    			 if(proposalApprovalAuthorityMapInstance)
    			   {
    			   
    			      def approvalAuthorityDetailsInstanceList = ApprovalAuthorityDetail.findAll("from ApprovalAuthorityDetail AAD where AAD.approvalAuthority.id="+approvalAuthorityInstance.id+" and AAD.activeYesNo='Y'")
						if(approvalAuthorityDetailsInstanceList)
							{
										
								for(int k=0;k<approvalAuthorityDetailsInstanceList.size();k++)
								    {
										
										EmailValidator emailValidator = EmailValidator.getInstance()
										  if (emailValidator.isValid(approvalAuthorityDetailsInstanceList[k].person.email))
										  {
											  def mailSubject="GMS Expense request Information Against a Project ";
											  def mailFooter=gmsSettingsService.getGmsSettingsValue("MailFooter")
											  String mailMessage="";
											  mailMessage="Dear "+approvalAuthorityDetailsInstanceList[k].person.userRealName+" "+approvalAuthorityDetailsInstanceList[k].person.userSurName+", \n";
											  mailMessage+=" An expense request has been sent to you on GMS by "+userInstance.userRealName+" "+userInstance.userSurName+" for the project "+expenseRequestEntryInstance.projects.code+" and is pending at your approval. ";
											  mailMessage+=" \n\nDetails are as given below:";
											  
										       mailMessage+=" \n\nDate of Request:"+requestDate;
										       mailMessage+=" \nExpense Description:"+expenseRequestEntryInstance.expenseDescription;
										       mailMessage+=" \nExpense Amount:"+expenseRequestEntryInstance.expenseAmount;
										       mailMessage+=" \nDate of Expense:"+expenseDate;
										     
										      mailMessage=mailMessage+" \n" 
										      mailMessage+=" \nRegards," 
										      mailMessage+=" \nGrant Management System (GMS) at "+userMapInstance.party.code;
										      mailMessage+=" \n[This is an auto-generated e-mail. Please do not reply] ";
										      notificationsEmailsService.sendConfirmation(approvalAuthorityDetailsInstanceList[k].person.email,mailSubject,mailMessage,"text/plain")
								   
								         }
								    
								    }			
					          }
    			   
    			   }   			
	    			
	    			
	    			flash.message = "${message(code: 'default.SendSuccessfully.message')}"
	    			redirect(action: "expenseApprovalRequest")
	    		}
	    		}
	    		else{
	    			flash.error = "${message(code: 'default.RequestPending.message')}"
	    			redirect(action: "expenseApprovalRequest")
	    		}
	    	}
	    	else 
	    	{
	    		def approvalAuthorityDetailInstanceList = approvalAuthorityDetailService
	    		.getApprovalAuthorityDetailByApprovalAuthority(proposalApprovalAuthorityMapInstance.approvalAuthority.id)// get the list of persons in current authority level.
	    	    def proposalApprovalInstanceList = proposalApprovalService
	    		.getProposalApprovalInstanceListByProposalApprovalAuthorityMapInstance(approvalAuthorityDetailInstanceList,proposalApprovalAuthorityMapInstance) // get the list of persons reviewed in current authority level.
	    	    if(proposalApprovalInstanceList)
	    	    {
	    	    	
	    	    
	    		def proposalApprovalAuthorityMapDuplicateInstance= proposalApprovalAuthorityMapService.getProposalApprovalAuthorityMapInstanceListByProposalIdAndType(expenseRequestEntryInstance.id,params)
	        	if(proposalApprovalAuthorityMapDuplicateInstance)
	        	{
	        		flash.error = "${message(code: 'default.CannotSend.message')}"
	        		redirect(action: "expenseApprovalRequest")
			  	}
	        	
	        	else
	        	{
	    		proposalApprovalAuthorityMapInstance = proposalApprovalAuthorityMapService
	    			.saveProposalApprovalAuthorityMap(proposalApprovalAuthorityMapInstanceList,approvalAuthorityInstance,userInstance,expenseRequestEntryInstance) //create new approval authority map by selected approval authority.
    			
    			  if(proposalApprovalAuthorityMapInstance)
    			   {
    			   
    			      def approvalAuthorityDetailsInstanceList = ApprovalAuthorityDetail.findAll("from ApprovalAuthorityDetail AAD where AAD.approvalAuthority.id="+approvalAuthorityInstance.id+" and AAD.activeYesNo='Y'")
						if(approvalAuthorityDetailsInstanceList)
							{
										
								for(int k=0;k<approvalAuthorityDetailsInstanceList.size();k++)
								    {
										
										EmailValidator emailValidator = EmailValidator.getInstance()
										  if (emailValidator.isValid(approvalAuthorityDetailsInstanceList[k].person.email))
										  {
											  def mailSubject="GMS Expense request Information Against a Project ";
											  def mailFooter=gmsSettingsService.getGmsSettingsValue("MailFooter")
											  String mailMessage="";
											  mailMessage="Dear "+approvalAuthorityDetailsInstanceList[k].person.userRealName+" "+approvalAuthorityDetailsInstanceList[k].person.userSurName+", \n";
											  mailMessage+=" An expense request has been sent to you on GMS by "+userInstance.userRealName+" "+userInstance.userSurName+" for the project "+expenseRequestEntryInstance.projects.code+" and is pending at your approval. ";
											  mailMessage+=" \n\nDetails are as given below:";
											  
										       mailMessage+=" \n\nDate of Request:"+requestDate;
										       mailMessage+=" \nExpense Description:"+expenseRequestEntryInstance.expenseDescription;
										       mailMessage+=" \nExpense Amount:"+expenseRequestEntryInstance.expenseAmount;
										       mailMessage+=" \nDate of Expense:"+expenseDate;
										     
										      mailMessage=mailMessage+" \n" 
										      mailMessage+=" \nRegards," 
										      mailMessage+=" \nGrant Management System (GMS) at "+userMapInstance.party.code;
										      mailMessage+=" \n[This is an auto-generated e-mail. Please do not reply] ";
										      notificationsEmailsService.sendConfirmation(approvalAuthorityDetailsInstanceList[k].person.email,mailSubject,mailMessage,"text/plain")
								   
								         }
								    
								    }			
					          }
    			   
    			   }
    			
    	   			
	    			flash.message = "${message(code: 'default.SendSuccessfully.message')}"
	    			redirect(action: "expenseApprovalRequest")
	        	}
	    	   
    	    }
    	    else
    	    {
    	    	flash.error = "${message(code: 'default.RequestPending.message')}"
	    		redirect(action: "expenseApprovalRequest")
    	    }
	    		
	    }
	}
    }
/*
 * Function to terminate the routing process.
 */
    def processComplete = {
    	
    	GrailsHttpSession gh=getSession()
    	def proposalApprovalAuthorityMapInstance = proposalApprovalAuthorityMapService.getProposalAuthorityMapById(params.proposalApprovalAuthorityMapId)
    	def expenseRequestEntryInstance = ExpenseRequestEntry.get(proposalApprovalAuthorityMapInstance.proposalId)
    	def expenseRequestMaxOrder = proposalApprovalAuthorityMapService.getExpenseRequestMaxOrder(expenseRequestEntryInstance)
    	proposalApprovalAuthorityMapInstance = proposalApprovalAuthorityMapService
    		.getProposalApprovalAuthorityMapByApproveOrder(expenseRequestEntryInstance,expenseRequestMaxOrder[0])
    	
    	def approvalAuthorityDetailInstanceList = approvalAuthorityDetailService
			.getApprovalAuthorityDetailByApprovalAuthority(proposalApprovalAuthorityMapInstance.approvalAuthority.id)
    	def proposalApprovalInstanceList = proposalApprovalService
    		.getProposalApprovalInstanceListByProposalApprovalAuthorityMapInstance(approvalAuthorityDetailInstanceList,proposalApprovalAuthorityMapInstance)
    	def level = 0
    	if(expenseRequestEntryInstance.status != 'Pending')
    	{
    		flash.error = "${message(code: 'default.ProcessCompleted.message')}"
    		redirect(action: "expenseApprovalRequest")
    	}
    	else {
    		if(proposalApprovalAuthorityMapInstance.approvalAuthority.approveAll == 'Y')
    		{
    			if(approvalAuthorityDetailInstanceList.size() == proposalApprovalInstanceList.size())
	    		{
	    			for(int i=0;i<proposalApprovalInstanceList.size();i++)
	    			{
	    				def proposalApprovalDetailInstance = proposalApprovalDetailService
	    					.proposalApprovalDetailByProposalApproval(proposalApprovalInstanceList[i].id)
	    				if(proposalApprovalDetailInstance.proposalStatus != 'Approved')
	    				{
	    					expenseRequestEntryInstance.status = 'Rejected'
    						flash.message = "${message(code: 'default.ProcessCompletedRejected.message')}"
				    		
    						break 
	    				}
	    				else{
	    					level = level+1
	    				}
	    					
	    			}
	    			if(level == proposalApprovalInstanceList.size())
	    			{
		    			expenseRequestEntryInstance.status = 'Approved'
						flash.message = "${message(code: 'default.ProcessCompletedApproved.message')}"
			    		
	    			}
	    			
	    		}
	    		else{
	    		
	    			flash.error = "${message(code: 'default.ProcessCannotCompleted.message')}"
	    			
	    		}
    		
    			redirect(action: "expenseApprovalRequest")
    		}
    		else{
    			def approvalAuthorityDetailInstance = approvalAuthorityDetailService
    				.getApprovalAuthorityDetailByApprovalAuthorityUser(proposalApprovalAuthorityMapInstance.approvalAuthority.id,gh.getValue("UserId"))
    			def proposalApprovalInstance = proposalApprovalService.getProposalApprovalByProposalApprovalAuthorityMap(proposalApprovalAuthorityMapInstance.id)
    			def proposalApprovalDetailInstance = proposalApprovalDetailService.proposalApprovalDetailByProposalApproval(proposalApprovalInstance[0].id)
    			if(proposalApprovalInstance)
    			{
    				if(proposalApprovalDetailInstance.proposalStatus == 'Approved')
    				{
    					expenseRequestEntryInstance.status = 'Approved'
						flash.message = "${message(code: 'default.ProcessCompletedApproved.message')}"
			    		redirect(action: "expenseApprovalRequest")
    					
    				}
    				else{
    					expenseRequestEntryInstance.status = 'Rejected'
						flash.message = "${message(code: 'default.ProcessCompletedRejected.message')}"
			    		redirect(action: "expenseApprovalRequest")
    					
    				}
    			}
    			else{
    				flash.error = "${message(code: 'default.PleaseReviw.message')}"
	    			redirect(action: "expenseApprovalRequest")
    				
    			}
    		}
		}
    	
    	
    }
/*
 * Function to save the payment details 
 */
    def saveExpense = {
    	GrailsHttpSession gh=getSession()
		def grantExpenseService = new GrantExpenseService()
		def grantAllocationSplitService = new GrantAllocationSplitService()
		def userInstance = Person.get(gh.getValue("UserId"))
		params.createdBy=userInstance.username
		params.createdDate = new Date()
        def grantExpenseInstance = new GrantExpense(params)
		grantExpenseInstance.utilizationSubmitted = 'N'
		def grantAllocationInstance = GrantAllocation.get( grantExpenseInstance.grantAllocation.id )
		def allocatedAmount=grantAllocationService.getSumOfAmountAllocatedForProject(grantAllocationInstance.projects.id,gh.getValue("PartyID"))
		def expenseTotal=GrantExpense.executeQuery("select sum(GE.expenseAmount) from GrantExpense GE where GE.projects="+grantExpenseInstance.projects.id)
		double balanceAmnt
		def totalpaid = grantExpenseInstance. expenseAmount
		def expenseRequestEntryInstance = expenseRequestService.getExpenseRequestEntryByRequestCode(params.expenseRequestCode)
		def grantExpenseInstanceList = grantExpenseService.getGrantExpenseByRequestCode(params.expenseRequestCode)
		if(grantExpenseInstanceList)
		{
			for(int i=0;i<grantExpenseInstanceList.size();i++)
			{
				totalpaid = totalpaid + grantExpenseInstanceList[i].expenseAmount
			}
		}
    	
    	
		if(expenseTotal[0])
		{
			balanceAmnt= allocatedAmount- expenseTotal[0]
		}
		else
		{
			balanceAmnt= allocatedAmount
			
		}
		
		if(grantExpenseInstance.expenseAmount > balanceAmnt)
	    {
			flash.error = "${message(code: 'default.ExpenseAmountValidationAgainstAllocatedAmount.label')}"
    		redirect(action:expenseEntry,id:expenseRequestEntryInstance.id,params:[expenseAmount:grantExpenseInstance.expenseAmount,
	    		                                     			                   grantAllocationId:grantExpenseInstance.grantAllocation.id,
	    		                                     			                   accountHeadId:grantExpenseInstance.grantAllocationSplit.id,
	    		                                     			                   modeOfPayment:grantExpenseInstance.modeOfPayment,ddNo:grantExpenseInstance.ddNo,
	    		                                     			                   bankName:grantExpenseInstance.bankName,ddBranch:grantExpenseInstance.ddBranch,
	    		                                     			                   description:grantExpenseInstance.description])
	    }
	    else
	    {
			def headAllcnAmnt=grantExpenseService.getAllocatedAmntHeadwise(grantExpenseInstance)
			def headExpAmnt = grantExpenseService.getExpAmntHeadwise(grantExpenseInstance)
			def headSplitAmtChilds = grantAllocationSplitService.getTotalSubGrantSplit(grantExpenseInstance.projects.id,grantExpenseInstance.grantAllocationSplit.accountHead.id, grantExpenseInstance.grantAllocationSplit.grantPeriod.id)
			double headBalance
			if (headExpAmnt[0])
			{
			  headBalance = headAllcnAmnt[0]-headExpAmnt[0]
			}
			else
			{
			  headBalance = headAllcnAmnt[0]
			}
			if(headSplitAmtChilds){
				headBalance = headBalance-headSplitAmtChilds
			}
		   if(grantExpenseInstance.expenseAmount > headBalance)
		   {
			   flash.error = "${message(code: 'default.ExpenseAmountValidationAgaistHeadwiseAllcn.label')}"
			   redirect(action:expenseEntry,id:expenseRequestEntryInstance.id,params:[expenseAmount:grantExpenseInstance.expenseAmount,
			                   grantAllocationId:grantExpenseInstance.grantAllocation.id,accountHeadId:grantExpenseInstance.grantAllocationSplit.id,
			                   modeOfPayment:grantExpenseInstance.modeOfPayment,ddNo:grantExpenseInstance.ddNo,
			                   bankName:grantExpenseInstance.bankName,ddBranch:grantExpenseInstance.ddBranch,
			                   description:grantExpenseInstance.description])
		   }
		   else
		   {
	    	if(grantExpenseInstance.dateOfExpense < grantExpenseInstance.grantAllocation.DateOfAllocation)
	    	{
	    		flash.error="${message(code: 'default.DateValidationAgainstAllocationdate.label')}"
	    			 redirect(action:expenseEntry,id:expenseRequestEntryInstance.id,params:[expenseAmount:grantExpenseInstance.expenseAmount,
	    			                                     			                   grantAllocationId:grantExpenseInstance.grantAllocation.id,
	    			                                     			                   accountHeadId:grantExpenseInstance.grantAllocationSplit.id,
	    			                                     			                   modeOfPayment:grantExpenseInstance.modeOfPayment,ddNo:grantExpenseInstance.ddNo,
	    			                                     			                   bankName:grantExpenseInstance.bankName,ddBranch:grantExpenseInstance.ddBranch,
	    			                                     			                   description:grantExpenseInstance.description])
	    	}
	    	else
	    	{
	    		if(totalpaid > expenseRequestEntryInstance.expenseAmount)
	    		{
	    			flash.error = "${message(code: 'default.PayableAmountShouldNotExceed.message')}"
    	    		redirect(action:expenseEntry,id:expenseRequestEntryInstance.id,params:[expenseAmount:grantExpenseInstance.expenseAmount,
	    		    		                                     			                   grantAllocationId:grantExpenseInstance.grantAllocation.id,
	    		    		                                     			                   accountHeadId:grantExpenseInstance.grantAllocationSplit.id,
	    		    		                                     			                   modeOfPayment:grantExpenseInstance.modeOfPayment,ddNo:grantExpenseInstance.ddNo,
	    		    		                                     			                   bankName:grantExpenseInstance.bankName,ddBranch:grantExpenseInstance.ddBranch,
	    		    		                                     			                   description:grantExpenseInstance.description])
	    		}
	    		else
	    		{
		    		grantExpenseInstance = grantExpenseService.saveGrantExpense(grantExpenseInstance)
		    		if(grantExpenseInstance.isSaved)
		    		{
		    			flash.message = "${message(code: 'default.created.label')}"
		    			redirect(action:expenseEntry,id:expenseRequestEntryInstance.id,params:[grantExpenseId:grantExpenseInstance.id])
		    		}
		    		else 
		    		{
		    			render(view:'expenseEntry',model:[grantExpenseInstance:grantExpenseInstance])
		    		}
	    		}
	    	}   
		   }
	    }
    }
/*
 * Function to edit the payment details 
 */
    def editexpense = {
    	def grantExpenseService = new GrantExpenseService()
		def grantAllocationSplitService=new GrantAllocationSplitService()
    	def grantExpenseInstance = grantExpenseService.getGrantExpenseById(new Integer(params.id).intValue())
		def accountHeadList=grantAllocationSplitService.getAccountHeadByProject(grantExpenseInstance.projects.id)
    	def grantAllocationInstanceList=grantAllocationService
    		.getGrantAllocationsByProject(grantExpenseInstance.projects.id)
    	[grantExpenseInstance:grantExpenseInstance,accountHeadList:accountHeadList,
    	 grantAllocationInstanceList:grantAllocationInstanceList]
    
    }
/*
 * Function to update the payment details 
 */
   def updateExpense = {
    	GrailsHttpSession gh=getSession() 
    	def grantExpenseService = new GrantExpenseService()
    	def grantExpenseOrginalInstance = grantExpenseService.getGrantExpenseById(new Integer(params.id))
    	def grantExpenseInstance = new GrantExpense(params)
		grantExpenseInstance.utilizationSubmitted = 'N'
		def grantExpenseWithOutSave = grantExpenseInstance
		def grantAllocationInstance = GrantAllocation.get( grantExpenseInstance.grantAllocation.id )
		def allocatedAmount=grantAllocationService.getSumOfAmountAllocatedForProject(grantAllocationInstance.projects.id,gh.getValue("PartyID"))		
		def expenseTotal=GrantExpense.executeQuery("select sum(GE.expenseAmount) from GrantExpense GE where GE.projects="+grantExpenseInstance.projects.id)
    	double balanceAmnt
    	def totalpaid = grantExpenseInstance. expenseAmount
		def expenseRequestEntryInstance = expenseRequestService.getExpenseRequestEntryByRequestCode(params.expenseRequestCode)
		def grantExpenseInstanceList = grantExpenseService.getGrantExpenseByRequestCode(params.expenseRequestCode)
		if(grantExpenseInstanceList)
		{
			for(int i=0;i<grantExpenseInstanceList.size();i++)
			{
				totalpaid = totalpaid + grantExpenseInstanceList[i].expenseAmount
			}
			totalpaid = totalpaid - grantExpenseOrginalInstance.expenseAmount
			
		}
		if(expenseTotal[0])
		{
		    balanceAmnt= (allocatedAmount+grantExpenseOrginalInstance.expenseAmount)- expenseTotal[0]
		}
		else
		{
			 balanceAmnt= allocatedAmount
		}
		
		if(grantExpenseInstance.expenseAmount > balanceAmnt)
	    {
			flash.error = "${message(code: 'default.ExpenseAmountValidationAgainstAllocatedAmount.label')}"
	    	redirect(action:editexpense,id:params.id)
	    }
	    else
	    {
		   	def headAllcnAmnt=grantExpenseService.getAllocatedAmntHeadwise(grantExpenseInstance)
			def headExpAmnt = grantExpenseService.getExpAmntHeadwise(grantExpenseInstance)
			double headBalance
			if (headExpAmnt[0])
			{
				if(grantExpenseOrginalInstance.grantAllocationSplit.id == (new Integer(params.grantAllocationSplit.id)))
				{
					headBalance = headAllcnAmnt[0]-(headExpAmnt[0] - grantExpenseOrginalInstance.expenseAmount)
						//(headAllcnAmnt[0]+grantExpenseOrginalInstance.expenseAmount)-headExpAmnt[0]
				}
				else
				 headBalance = headAllcnAmnt[0]-headExpAmnt[0]
			}
		   else
			{
			  headBalance = headAllcnAmnt[0]
			}
		   if(grantExpenseInstance.expenseAmount > headBalance)
		   {
			   flash.error = "${message(code: 'default.ExpenseAmountValidationAgaistHeadwiseAllcn.label')}"
			   redirect(action:editexpense,id:params.id)
		   }
		   else
		   {
				if(grantExpenseInstance) 
				{
		        	
		    		if(grantExpenseInstance.dateOfExpense < grantExpenseInstance.grantAllocation.DateOfAllocation)
		    		{
		    			flash.error ="${message(code: 'default.DateValidationAgainstAllocationdate.label')}"
		    			grantExpenseInstance=grantExpenseWithOutSave
		    			redirect(action:editexpense,id:params.id)
		    		}
		    		else
		    		{
		    			if(totalpaid > expenseRequestEntryInstance.expenseAmount)
			    		{
		    				flash.error = "${message(code: 'default.PayableAmountShouldNotExceed.message')}"
		    	    		redirect(action:expenseEntry,id:expenseRequestEntryInstance.id,params:[expenseAmount:grantExpenseInstance.expenseAmount,
			    		    		                                     			                   grantAllocationId:grantExpenseInstance.grantAllocation.id,
			    		    		                                     			                   accountHeadId:grantExpenseInstance.grantAllocationSplit.id,
			    		    		                                     			                   modeOfPayment:grantExpenseInstance.modeOfPayment,ddNo:grantExpenseInstance.ddNo,
			    		    		                                     			                   bankName:grantExpenseInstance.bankName,ddBranch:grantExpenseInstance.ddBranch,
			    		    		                                     			                   description:grantExpenseInstance.description])
			    		}
			    		else
			    		{
			    			grantExpenseInstance = grantExpenseService.updateGrantExpense(params)
			    			if(grantExpenseInstance.isSaved)
			    			{	
			    				flash.message = "${message(code: 'default.updated.label')}"
			    				redirect(action:expenseEntry,id:expenseRequestEntryInstance.id)
			    			}
			    		
			    			else 
			    			{
			                render(view:'editexpense',model:[grantExpenseInstance:grantExpenseInstance])
			            	}
			    		}
		    		}
				}
	        	
		        else 
		        {
		        	grantExpenseInstance=grantExpenseWithOutSave
		        	flash.error = "${message(code: 'default.notfond.label')}"
		            redirect(action:editexpense,id:params.id)
		        }
		   }
	    }
    }
/*
 * Function to delete the payment details 
 */   
    def deleteExpense = {
    	def grantExpenseService = new GrantExpenseService()
		def grantExpenseInstance =  grantExpenseService.getGrantExpenseById(new Integer(params.id))
		Integer grantAllocationId = grantExpenseService.deleteGrantExpense(new Integer(params.id))
		def expenseRequestEntryInstance = expenseRequestService
			.getExpenseRequestEntryByRequestCode(grantExpenseInstance.expenseRequestCode)
		if(grantAllocationId != null){
			if(grantAllocationId > 0){
				flash.message = "${message(code: 'default.deleted.label')}"
				redirect(action:expenseEntry,params:[id:expenseRequestEntryInstance.id])
			}
		}
		else{
			flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list,id:expenseRequestEntryInstance.id)
		}
    }
    
    def revieweStatus = 
    {
    	GrailsHttpSession gh=getSession()
		gh.removeValue("Help")
		gh.putValue("Help","Expense_Request_Reviewer_Status_List.htm")//putting help pages in session
    	def proposalApprovalAuthorityMapInstance
    	def expenseRequestEntryList=[]
    	 def expenseRequestEntryInstance
    	 def proposalApprovalInstance
    	 def proposalApprovalDetailInstance
    	 def proposalStatusList=[]
    	def approvalAuthorityInstance = approvalAuthorityDetailService
		.getApprovalAuthorityDetailInstanceListByPerson(gh.getValue("UserId")) // get the authority list where login user assigned as a member.
       
    	if(approvalAuthorityInstance)
    	{
    		for(int k=0;k<approvalAuthorityInstance.size();k++)
    		{
    	proposalApprovalAuthorityMapInstance = proposalApprovalAuthorityMapService
		.getProposalApprovalAuthorityMapByApprovalauthority(approvalAuthorityInstance[k].approvalAuthority.id)//get proposal Approval authority map based on authority.
		
        if(proposalApprovalAuthorityMapInstance)
        {
        	for(int i=0;i<proposalApprovalAuthorityMapInstance.size();i++)
        	{
        		 expenseRequestEntryInstance = expenseRequestService
 				.getExpenseRequestEntryById(proposalApprovalAuthorityMapInstance[i].proposalId) // get all expense request of the proposal.
 				expenseRequestEntryList.add(expenseRequestEntryInstance)
 				proposalApprovalDetailInstance = ProposalApprovalDetail.find("from ProposalApprovalDetail PAD where PAD.proposalApproval.proposalApprovalAuthorityMap.proposalId="+expenseRequestEntryInstance.id+
        		   "and PAD.proposalApproval.approvalAuthorityDetail.id="+approvalAuthorityInstance[k].id)
        		  proposalStatusList.add(proposalApprovalDetailInstance)
        	
        		   }
        		  }
        		}
        	
        }
 
    	ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
	 [expenseRequestEntryList:expenseRequestEntryList,'currencyFormat':currencyFormatter,proposalStatusList:proposalStatusList]
       
    }
	
	def invoiceAttachList = {
		def attachmentsService = new AttachmentsService()
		def attachmentsInstance = new Attachments()
		
		def attachmentsInstanceList = []
		def attachmentTypeList = []
		
		attachmentTypeList = attachmentsService.getattachmentTypesByDocType('Invoice')
		attachmentsInstanceList =attachmentsService.getAttachmentListbyDomainIdAndDomain(params.id,'expenseRequestEntry')
	[attachmentTypeList:attachmentTypeList,attachmentsInstanceList:attachmentsInstanceList,
		attachmentsInstance:attachmentsInstance]
	}
	
	
	 def approvalAuthoritySelect=
    {
    		if(params.approvalAuthority.id)
    		{
    			render (template:"apprvlAuthrtySelt")
    		}
    		
    }
}
