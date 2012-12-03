import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import grails.converters.JSON
import java.text.*;
import java.util.*;
class ProjectTrackingController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']
	def expenseRequestService
	def projectsService
	def grantAllocationService
	def grantReceiptService
	def checkListService
    def list = {
        if(!params.max) params.max = 10
        
        def projectsService = new ProjectsService()
        def projectTrackingInstanceList = projectsService.getAllProjecTracking(params)
        [ projectTrackingInstanceList: projectTrackingInstanceList ]
    }

    def show = {
		def projectsService = new ProjectsService()
		def projectTrackingInstance = projectsService.getProjectTrackingById(new Integer( params.id ))

        if(!projectTrackingInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
        else { return [ projectTrackingInstance : projectTrackingInstance ] }
    }

    def delete = {
        def projectsService = new ProjectsService()
        Integer projectId = projectsService.deleteProjectTracking(new Integer(params.id))
        if(projectId != null){
            flash.message = "${message(code: 'default.deleted.label')}"
        	redirect(action:create,id:projectId)
        }
        else {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
    }

    def edit = {
		def projectsService = new ProjectsService()
		def projectTrackingInstance = projectsService.getProjectTrackingById(new Integer( params.id ))

        if(!projectTrackingInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
        else {
        	/* Get project details */
        	Integer projectId = projectTrackingInstance.projects.id
    		def projectsInstance = projectsService.getProjectById(projectId)
    		/* Get project tracking details if any */
    		def projectTrackingInstanceList = projectsService.getProjectTrackingByProject(projectsInstance) 
    		for(projectStatus in projectTrackingInstanceList.projectStatus)
    		{
    			if(projectStatus=='Closed')
    			{
    				projectsInstance.status='Closed'
    			}
    		}
            
    		return [ projectsInstance:projectsInstance, projectTrackingInstance : projectTrackingInstance, projectTrackingInstanceList:projectTrackingInstanceList ]
        }
    }

    def update = {
  
    		def projectsService = new ProjectsService()
    		def subProjectInstanceList = []
    		def subProjectTrackingList = []

        /* Get project details */
			def projectsInstance = projectsService.getProjectById(new Integer(params.projects.id))
		
	if(params.projectStatus =='Closed')
	 {
		    
		/* Get sub project details */
		 subProjectInstanceList = projectsService.getSubProjectsForClosingMainProject(params.projects.id)
		 println"subProjectInstanceList"+subProjectInstanceList	
		 def subProjectTrackingInstance 

		 if(subProjectInstanceList)
		    	{
		    		for(int i=0;i<subProjectInstanceList.size();i++)
		    			{
		    		 
		    		         println"subProjectInstanceList[i]"+subProjectInstanceList[i].id
		    		         subProjectTrackingInstance = new ProjectTracking()
		    		         subProjectTrackingInstance =  ProjectTracking.find("from ProjectTracking PT where PT.projects.id = "+subProjectInstanceList[i].id)	
		    		         println"subProjectTrackingInstance"+subProjectTrackingInstance
		    		         if(subProjectTrackingInstance)
		    		        	 subProjectTrackingList.add(subProjectTrackingInstance)
		    			}
		   
		    		if(subProjectTrackingList.size()== subProjectInstanceList.size())
		    		{
		    		  def projectTrackingInstance = projectsService.updateProjectTracking(params)
		    		  		
		    	  			if(projectTrackingInstance){
		    	  				if(projectTrackingInstance.saveMode != null){
		    	  						flash.message = "${message(code: 'default.updated.label')}"
		    	  						redirect(action:create,id:projectTrackingInstance.projects.id)
		    	  					}
		    	  				else {
		    	  						render(view:'create',id:projectTrackingInstance.projects.id)
		    	  					}
		    	  					}
		    	  		else {
		    	              flash.message = "${message(code: 'default.notfond.label')}"
		    	              redirect(action:create,id:params.projects.id)
		    	          }
		    	   
		        
		    		}
		    		 else
					    {
				            flash.message = "${message(code: 'default.notAllowed.label')}"
					        redirect(action:create,id:params.projects.id)
					    }
		     }
		    	else
		    	{
		    		def projectTrackingInstance = projectsService.updateProjectTracking(params)
    		  		
    	  			if(projectTrackingInstance){
    	  					if(projectTrackingInstance.saveMode != null){
    	  						flash.message = "${message(code: 'default.updated.label')}"
    	  						redirect(action:create,id:projectTrackingInstance.projects.id)
    	  						}
    	  					else {
    	  						render(view:'create',id:projectTrackingInstance.projects.id)
    	  						}
    	  						}
    	  		  else {
    	              flash.message = "${message(code: 'default.notfond.label')}"
    	              redirect(action:create,id:params.projects.id)
    	  		  		}
		        
		    	}
      }
    
	else
		{
			def projectTrackingInstance = projectsService.updateProjectTracking(params)
  		
  				if(projectTrackingInstance){
  					if(projectTrackingInstance.saveMode != null){
  						flash.message = "${message(code: 'default.updated.label')}"
  						redirect(action:create,id:projectTrackingInstance.projects.id)
  						}
  				   else {
  						render(view:'create',id:projectTrackingInstance.projects.id)
  					    }
  				   }
  				else {
              flash.message = "${message(code: 'default.notfond.label')}"
              redirect(action:create,id:params.projects.id)
  					}
    }
}

    def create = 
    {
   		GrailsHttpSession gh=getSession()
   		def projectsService = new ProjectsService()
		def projectsInstance = projectsService.getProjectById(new Integer(params.id))
		gh.putValue("ProjectId",projectsInstance.id)
		return ['projectsInstance':projectsInstance]
		
    }

        def save = 
        {
    		params.createdBy = "admin"
        	params.createdDate = new Date()
    		params.modifiedBy = "admin"
        	params.modifiedDate = new Date()
    		GrailsHttpSession gh=getSession()
    		def projectsService = new ProjectsService()
		     /* Get project details */
			def projectsInstance = projectsService.getProjectById(new Integer(params.projects.id))
		    def projectTrackingInstance = new ProjectTracking(params)
    	    gh.putValue("TrackingStatus",projectTrackingInstance.projectStatus)
    	    gh.putValue("Trackingremarks",projectTrackingInstance.remarks)
    	    gh.putValue("TrackingDate",projectTrackingInstance.dateOfTracking)
    	    gh.putValue("TrackingpercOfCompletion",projectTrackingInstance.percOfCompletion)
            redirect(action:statusDetails,params:[id:projectTrackingInstance.projects.id,projectStatus:projectTrackingInstance.projectStatus,remarks:projectTrackingInstance.remarks,dateOfTracking:projectTrackingInstance.dateOfTracking,percOfCompletion:projectTrackingInstance.percOfCompletion])
       }
	def closureDetails = 
	{
		GrailsHttpSession gh=getSession()
		gh.removeValue("Help")
		gh.putValue("Help","Project_Tracking.htm")//putting help pages in session
    	def projectsService = new ProjectsService()
    	def projectsInstance = projectsService.getProjectById(new Integer(params.id))
		gh.putValue("ProjectId",projectsInstance.id)
    	/* Get project details */
    	/* Get project tracking details if any */
    	def projectTrackingInstance = new ProjectTracking()	
    	projectTrackingInstance.projects = projectsInstance
        projectTrackingInstance.properties = params
        return ['projectsInstance':projectsInstance, 
                    'projectTrackingInstance':projectTrackingInstance]
	}
	def statusDetails =
	{
		GrailsHttpSession gh=getSession()
		def projectsInstance =Projects.get(gh.getValue("ProjectId"))
		def checklistmap = ChecklistMap.findAll("from ChecklistMap CM where CM.projects.id="+projectsInstance.id+" and CM.activeYesNo='Y'")
		 return ['projectsInstance':projectsInstance,checklistmap:checklistmap]
		
	}
	
	def subPrjtDetails = 
	{
		GrailsHttpSession gh=getSession()
		def projectTrackingInstanceCheck
		def statusInstance
		def projectTrackingInstanceList =[]
		def grantAllocationService = new GrantAllocationService()
		def projectsInstance =Projects.get(gh.getValue("ProjectId"))
		def subProjectList = Projects.findAll("from Projects P where P.parent.id="+projectsInstance.id)
		for(int i=0;i<subProjectList.size();i++)
		{
			/* Get Closed project details*/
				projectTrackingInstanceCheck = grantAllocationService.getStatusofdProject(subProjectList[i].id) 
				for(int j=0;j<projectTrackingInstanceCheck.size();j++)
				{
					statusInstance = projectTrackingInstanceCheck[j]
		    		projectTrackingInstanceList.add(statusInstance)
				}
		}
		return ['projectsInstance':projectsInstance,'subProjectList':subProjectList,'projectTrackingInstanceList':projectTrackingInstanceList]
	}
	
	  def cancel = 
      {
    	  GrailsHttpSession gh=getSession()
		  def projectsInstance =Projects.get(gh.getValue("ProjectId"))
    	  redirect(action:"statusDetails", id:projectsInstance.id)
      } 
      
      def trackingdetails=
      {
      	  GrailsHttpSession gh=getSession()
      	  def grantAllocationService = new GrantAllocationService()
      	  def projectTrackingInstanceCheck
		  def statusInstance
		  def projectTrackingInstanceList =[]
		  def projectsInstance =Projects.get(gh.getValue("ProjectId"))
      	  def subProjectList = Projects.findAll("from Projects P where P.parent.id="+projectsInstance.id)
		  for(int i=0;i<subProjectList.size();i++)
		  {
			/* Get Closed project details*/
				projectTrackingInstanceCheck = Projects.findAll("from Projects P where P.id="+subProjectList[i].id+" and P.id NOT IN (SELECT PT.projects.id from ProjectTracking PT)")
				for(int j=0;j<projectTrackingInstanceCheck.size();j++)
				{
					statusInstance = projectTrackingInstanceCheck[j]
		    		projectTrackingInstanceList.add(statusInstance.name)
				}
		  }
		  if(projectTrackingInstanceList)
		  render projectTrackingInstanceList as JSON
      }
      
      def fundAdvnceDetails =
      {
      	  GrailsHttpSession gh=getSession()
          def advanceFundList
          def advanceInstance
          def advanceInstanceList=[]
		  def projectsInstance =Projects.get(gh.getValue("ProjectId"))
		  def grantAllocationService = new GrantAllocationService()
      	  def grantAllocationInstance = grantAllocationService.getAllGrantAllocationOfProject(projectsInstance.id) 
      	  for(int i=0;i<grantAllocationInstance.size();i++)
		  {
		  	advanceFundList = grantAllocationService.getAllFundAdvanceByGrantAllocation(grantAllocationInstance[i].id) 
		  	for(int j=0;j<advanceFundList.size();j++)
			{
					advanceInstance = advanceFundList[j]
		    		advanceInstanceList.add(advanceInstance)
			} 
		  }
		  return['advanceInstanceList':advanceInstanceList]
      }
	def advanceDetails=
	{
		  GrailsHttpSession gh=getSession()
		  def advanceInstance
		  def advancedetails
		  def advanceInstanceList=[]
          def projectsInstance =Projects.get(gh.getValue("ProjectId"))
          def grantAllocationService = new GrantAllocationService()
      	  def grantAllocationInstance = grantAllocationService.getAllGrantAllocationOfProject(projectsInstance.id) 
      	  for(int i=0;i<grantAllocationInstance.size();i++)
		  {
		  	advanceInstance = grantAllocationService.getAllClosedFundAdvanceByGrantAllocation(grantAllocationInstance[i].id)
		  	for(int j=0;j<advanceInstance.size();j++)
			{
					advancedetails = advanceInstance[j]
		    		advanceInstanceList.add(advancedetails.id)
			}  
		  }
		  if(advanceInstanceList)
		  render advanceInstanceList as JSON
	}
	
	def expenseRequestDetails=
	{
		GrailsHttpSession gh=getSession()
		def projectsInstance =Projects.get(gh.getValue("ProjectId"))
		def expenseRequestEntryInstance = expenseRequestService.getExpenseRequestEntryByProjectId(projectsInstance.id) //get the Expense request list based on project id
    	return['expenseRequestEntryInstance':expenseRequestEntryInstance]	
	}
	
	def expenseDetails=
	{
		GrailsHttpSession gh=getSession()
		def projectsInstance =Projects.get(gh.getValue("ProjectId"))
		def expenseRequestEntryInstance = expenseRequestService.getPendingRequestEntryByProjectId(projectsInstance.id)
		if(expenseRequestEntryInstance)
		  render expenseRequestEntryInstance as JSON
	}
	 
	 def checkFundDetails=
	 {
		GrailsHttpSession gh=getSession()
		def projectsInstance =Projects.get(gh.getValue("ProjectId"))
		def projectStatus =(gh.getValue("TrackingStatus"))
		if(projectStatus == 'Surrender')
		{
			def mapList = params.chkselt.toString()
			def mapPrjtList = []
	    	def chkMapList=mapList.split(',')
	    	if (chkMapList.length ==1) 
	    	{
	    		mapPrjtList.add(params.chkselt.toString())
				params.chkselt =  mapPrjtList
			}
			def checkcompulsoryList = checkListService.getCheckListBySatifiedInstnce(projectsInstance.id)
			def chkList
			def instance
			def compulsoryList = []
			for(int i=0;i<params.chkselt.size();i++)
			{
				chkList = checkListService.getAllCheckListByParamsId(params.chkselt[i])
				for(int j=0;j<chkList.size();j++ )
    	 	    {
					instance = chkList[j]
                    compulsoryList.add(instance)
    	 	    }
	 	    }
	 	    def commons = compulsoryList.intersect(checkcompulsoryList)
			if(commons.size()<checkcompulsoryList.size)
			{
				flash.error ="${message(code: 'default.Mustselectcomplsoryfields.label')}"
				redirect(action:statusDetails,params:[id:projectsInstance.id,projectStatus:'Surrender'])
			}
			else
			{
				if(params.chkselt[0]!= 'null')
				{
				 	for(int i=0;i<params.chkselt.size();i++)
					{
						def checklistMapInstance = new ChecklistMap()
						checklistMapInstance = ChecklistMap.get(params.chkselt[i])
						checklistMapInstance.satisfied = 'Y'
						checklistMapInstance.save()
					}
					if(params.refund != 'NotRefund')
					{
						redirect(action:fundDetails,id:projectsInstance.id)
					}
					else
					{
						redirect(action:fundDetails,params:[id:projectsInstance.id,refund:'NotRefund'])
					}
				}
				else
				{
					flash.error ="${message(code: 'default.PleaseselectanychecklistOtherwiseucantgoforfurtherchecking.label')}"
					redirect(action:statusDetails,params:[id:projectsInstance.id,projectStatus:'Surrender'])
				}
			}
		}
		else
		{
			if(params.refund != 'NotRefund')
			{
				redirect(action:fundDetails,id:projectsInstance.id)
			}
			else
			{
				redirect(action:fundDetails,params:[id:projectsInstance.id,refund:'NotRefund'])
			}
		}
	 }
	 
	 def fundDetails= 
	 {
	    GrailsHttpSession gh=getSession()
	    def projectRefundInstance = new ProjectRefund()
		def projectsInstance =Projects.get(gh.getValue("ProjectId"))
		def sumAmount = GrantExpense.executeQuery("select sum(GE.expenseAmount) as SumAmt from GrantExpense GE where GE.projects.id ="+ projectsInstance.id)
		def sumTransferInstance = FundTransfer.executeQuery("select sum(FU.amount) as SumAmt from FundTransfer FU where FU.grantAllocation.projects.parent.id ="+ projectsInstance.id)
        def sumGrantRecieve = grantReceiptService.getSumOfAmountReceivedForProject(projectsInstance.id)
        projectsInstance.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProject(projectsInstance.id,gh.getValue("PartyID"))
        
        if(sumAmount[0]==null)
	     sumAmount[0]=0
	    if(sumGrantRecieve==null)
		  sumGrantRecieve=0
		if(sumTransferInstance[0]==null)
		  sumTransferInstance[0]=0
		  def balance = sumGrantRecieve - sumAmount[0]
        if(balance==null)
		  balance=0
		  NumberFormat formatter = new DecimalFormat("#0.00");
        ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
        return['projectsInstance':projectsInstance,'currencyFormat':currencyFormatter,sumAmount:sumAmount,
        sumTransferInstance:sumTransferInstance,sumGrantRecieve:sumGrantRecieve,balance:balance,'refundAmount':formatter.format(projectRefundInstance.refundAmount)]
	 }
	 
	 def closeProject=
	 {
	 	def projectTrackingInstance = new ProjectTracking()
	 	GrailsHttpSession gh=getSession()
		def projectsInstance =Projects.get(gh.getValue("ProjectId"))
		projectTrackingInstance.projects = projectsInstance
		projectTrackingInstance.projectStatus =(gh.getValue("TrackingStatus"))
		projectTrackingInstance.remarks =(gh.getValue("Trackingremarks"))
		projectTrackingInstance.dateOfTracking =(gh.getValue("TrackingDate"))
		projectTrackingInstance.percOfCompletion =(gh.getValue("TrackingpercOfCompletion"))
		projectTrackingInstance.createdBy = "admin"
    	projectTrackingInstance.createdDate = new Date()
		projectTrackingInstance.modifiedBy = "admin"
    	projectTrackingInstance.modifiedDate = new Date()
		projectTrackingInstance.save()
		flash.message = projectsInstance.name +" "+"${message(code: 'default.closed.label')}"
		redirect(controller: "projects", action: "list")
	 }
	 
	 def refundAmount=
	 {
	 println"---------------params---------"+params
	 	 def projectRefundInstance = new ProjectRefund()
		 GrailsHttpSession gh=getSession()
		 def projectsInstance =Projects.get(gh.getValue("ProjectId"))
		 double refundAmount= ((params.refundAmount).toDouble()).doubleValue()
		 double balance= ((params.balnc).toDouble()).doubleValue()
		 if(refundAmount > balance)
		 {
		 	flash.error ="${message(code: 'default.RefundAmountMustbelessThanBalanceAmount.label')}"
			redirect(action:fundDetails,params:[id:projectsInstance.id])
		 }
		 else
		 {
			 projectRefundInstance.properties = params
			 projectRefundInstance.projects = projectsInstance
			 projectRefundInstance.createdBy = "admin"
	    	 projectRefundInstance.createdDate = new Date()
			 projectRefundInstance.modifiedBy = "admin"
	    	 projectRefundInstance.modifiedDate = new Date()
			 projectRefundInstance.save()
			 redirect(action:"projectClosure", id:projectsInstance.id)
		 }
	 	
	 }
	 
	 def projectClosure=
	 {
	 	GrailsHttpSession gh=getSession()
		def projectsInstance =Projects.get(gh.getValue("ProjectId"))
		def sumAmount = GrantExpense.executeQuery("select sum(GE.expenseAmount) as SumAmt from GrantExpense GE where GE.projects.id ="+ projectsInstance.id)
		def sumTransferInstance = FundTransfer.executeQuery("select sum(FU.amount) as SumAmt from FundTransfer FU where FU.grantAllocation.projects.parent.id ="+ projectsInstance.id)
        def sumGrantRecieve = grantReceiptService.getSumOfAmountReceivedForProject(projectsInstance.id)		
        projectsInstance.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProject(projectsInstance.id,gh.getValue("PartyID"))
        def amtRefund = ProjectRefund.executeQuery("select sum(PR.refundAmount) as SumAmt from ProjectRefund PR where PR.projects.id ="+ projectsInstance.id)
         if(sumAmount[0]==null)
	     sumAmount[0]=0
	    if(sumGrantRecieve==null)
		  sumGrantRecieve=0
		if(sumTransferInstance[0]==null)
		  sumTransferInstance[0]=0
		if(amtRefund[0]==null)
	     amtRefund[0]=0 
        ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
        return['projectsInstance':projectsInstance,'currencyFormat':currencyFormatter,sumAmount:sumAmount,
        sumTransferInstance:sumTransferInstance,sumGrantRecieve:sumGrantRecieve,amtRefund:amtRefund]
	 }
	 def utilizationDetails=
	 {
	 	GrailsHttpSession gh=getSession()
		def projectsInstance =Projects.get(gh.getValue("ProjectId"))
		def utilizationInstanceCheck = Utilization.findAll("from Utilization U where U.projects.id="+projectsInstance.id)
		def utilizationCertificateList
		utilizationCertificateList = Utilization.findAll("from Utilization U where U.projects.id ="+projectsInstance.id+" and U.grantee='"+gh.getValue("Party")+"')")
		[projectsInstance:projectsInstance,utilizationInstanceCheck:utilizationInstanceCheck,utilizationCertificateList:utilizationCertificateList]
     }
	 
	 
	 def utilizedDetails=
	 {
	 
	 	GrailsHttpSession gh=getSession()
		def projectsInstance =Projects.get(gh.getValue("ProjectId"))
		def utilizationInstanceCheck = Utilization.findAll("from Utilization U where U.projects.id="+projectsInstance.id)
		if(!utilizationInstanceCheck)
		  render utilizationInstanceCheck as JSON
	 }
	 
	 def listReport = {
    		
    		return['reportListInstance':params]
    		
    }
	
	def projectClosureReport=
	{
			GrailsHttpSession gh=getSession()
			def partyInstance=Party.get(gh.getValue("Party"))
			return['partyInstance':partyInstance]
	}
}
