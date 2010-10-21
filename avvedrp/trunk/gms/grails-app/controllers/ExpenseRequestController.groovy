import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession

class ExpenseRequestController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        println"***params***"+params
        GrailsHttpSession gh=getSession()
        def expenseRequestService = new ExpenseRequestService();
        def expenseRequestInstanceList = expenseRequestService.getExpenseRequestByProjects(gh.getValue("ProjectID"))
      println"expenseRequestInstanceList"+expenseRequestInstanceList
      println"expenseRequestInstanceList[0].accountHead.id"+expenseRequestInstanceList[0].accountHead.id
        [ expenseRequestInstanceList: expenseRequestInstanceList ]
       
    }

    def show = {
        def expenseRequestInstance = ExpenseRequest.get( params.id )

        if(!expenseRequestInstance) {
            flash.message = "${message(code: 'default.ExpenseRequestnotFound.label')}"
            redirect(action:list)
        }
        else { return [ expenseRequestInstance : expenseRequestInstance ] }
    }

    def delete = {
        def expenseRequestInstance = ExpenseRequest.get( params.id )
        if(expenseRequestInstance) {
            expenseRequestInstance.delete()
            flash.message = "${message(code: 'default.deleted.label')}"
            redirect(action:list)
        }
        else {
            flash.message = "${message(code: 'default.ExpenseRequestnotFound.label')}"
            redirect(action:list)
        }
    }

    def edit = {
        def expenseRequestInstance = ExpenseRequest.get( params.id )
        def grantAllocationService=new GrantAllocationService()
        GrailsHttpSession gh=getSession()
      
        println"***********projectID*********"+gh.getValue("ProjectID")
        
        def projectsInstance = Projects.get(new Integer(gh.getValue("ProjectID")))
		println"***********project code*********"+projectsInstance.code
       
        
		def grantAllocationInstanceList=grantAllocationService.getGrantAllocationsByProjectCode(gh.getValue("ProjectID"))

        if(!expenseRequestInstance) {
            flash.message = "${message(code: 'default.ExpenseRequestnotFound.label')}"
            redirect(action:list)
        }
        else {
            return [ expenseRequestInstance : expenseRequestInstance,
                     grantAllocationInstanceList:grantAllocationInstanceList ]
        }
    }

    def update = {
        def expenseRequestInstance = ExpenseRequest.get( params.id )
        if(expenseRequestInstance) {
            expenseRequestInstance.properties = params
            println"+params.id+++"+params.id
            if(!expenseRequestInstance.hasErrors() && expenseRequestInstance.save()) {
                flash.message = "${message(code: 'default.updated.label')}"
                redirect(action:list,id:expenseRequestInstance.id)
            }
            else {
                render(view:'edit',model:[expenseRequestInstance:expenseRequestInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.ExpenseRequestnotFound.label')}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def expenseRequestInstance = new ExpenseRequest()
        def grantAllocationService = new GrantAllocationService()
        def grantAllocationSplitService=new GrantAllocationSplitService()
        GrailsHttpSession gh=getSession()
      
        println"***********projectID*********"+gh.getValue("ProjectID")
        
        def projectsInstance = Projects.get(new Integer(gh.getValue("ProjectID")))
		println"***********project code*********"+projectsInstance.code
        expenseRequestInstance.properties = params
        
		def grantAllocationInstanceList=grantAllocationService.getGrantAllocationsByProjectCode(gh.getValue("ProjectID"))
		println"&&&&&&&&&&&&grantAllocationId&&&&&&&&&&&&&&&"+grantAllocationInstanceList[0].id
		def accountHeadList=grantAllocationSplitService.getAccountHeadOfProject(gh.getValue("ProjectID"))
        println"%%%%accountHeadList%%%%"+accountHeadList
        return ['expenseRequestInstance':expenseRequestInstance,
                'projectsInstance':projectsInstance,
                'grantAllocationInstanceList':grantAllocationInstanceList,
                'accountHeadList':accountHeadList]
    }

    def save = {
    		println "Save-------"
    		
        def expenseRequestInstance = new ExpenseRequest(params)
    		
        def expenseRequestService = new ExpenseRequestService()
    		def expAmt
    		
        if(!expenseRequestInstance.hasErrors() && expenseRequestInstance.save()) {
        	
        	println"*****expenseRequestInstance.accountHead.id****"+expenseRequestInstance.accountHead.id
         def chkGrntRcvdInstance=GrantReceipt.findAll("from GrantReceipt GR,GrantAllocationSplit GS where GR.projects.id="+expenseRequestInstance.grantAllocation.projects.id+"and GR.grantAllocation.id="+expenseRequestInstance.grantAllocation.id+"and GR.grantAllocationSplit.id=GS.id and GS.accountHead.id="+expenseRequestInstance.accountHead.id)
        	if(chkGrntRcvdInstance[0])
        	{
        		def recAmtList=expenseRequestService.chkFundAvailableByAcctHead(expenseRequestInstance)
        	//def chkExpInstance=GrantExpense.findAll("from GrantExpense GE,GrantAllocationSplit GS,GrantAllocation GA where GE.projects.id="+expenseRequestInstance.grantAllocation.projects.id+"and GS.grantAllocation.id=GA.id and GE.grantAllocation.id="+expenseRequestInstance.grantAllocation.id+"and GE.grantAllocationSplit.id=GS.id and GS.accountHead.id="+expenseRequestInstance.accountHead.id)
        	
        	//if(chkExpInstance[0])
        	// {
        	expAmt=GrantExpense.executeQuery("select sum(GE.expenseAmount) from GrantExpense GE,GrantAllocation GA,GrantAllocationSplit GS where  GS.grantAllocation.id=GA.id and GE.grantAllocation.id="+expenseRequestInstance.grantAllocation.id+" and GE.grantAllocationSplit.id=GS.id AND GS.accountHead.id="+expenseRequestInstance.accountHead.id)        
        	println"++++expAmt++++"+expAmt[0]
        	if(expAmt[0]==null)
        	{
        	expAmt[0]=0
        	}
        	 double balanceAmt;
        	balanceAmt=recAmtList[0]-expAmt[0];
        	
        	if(balanceAmt >= expenseRequestInstance.requestedAmount)
        	{
           flash.message = "${message(code: 'default.FundAvailable.label')}"
          expenseRequestInstance.fundAvailableYesNo='Y'
            redirect(action:create,id:expenseRequestInstance.id)
        	}
        	else
        	 {
        		flash.message = "${message(code: 'default.FundnotAvailable.label')}"
        		expenseRequestInstance.fundAvailableYesNo='N'
        	   redirect(action:create,id:expenseRequestInstance.id)
        }
        		
        	}
        	
        	
        else
        {
        	expenseRequestInstance.fundAvailableYesNo='N'
        	flash.message ="${message(code: 'default.FundnotAvailable.label')}"
        	redirect(action:create,id:expenseRequestInstance.id)
        }
        }
        else {
        	
            render(view:'create',model:[expenseRequestInstance:expenseRequestInstance])
        }
    }
}
