import java.text.*;
import java.util.*;

import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class GrantReceiptController extends GmsController {
	def projectsService
    def index = { redirect(action:list,params:params) }
      
    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
    
        
        if(!params.max) params.max = 10
        println"params"+params
        
        def grantReceiptService = new GrantReceiptService();
        def grantReceiptInstanceList = grantReceiptService.getGrantReceiptByParams(params)
        println"params"+params
        [ grantReceiptInstanceList: grantReceiptInstanceList ]
    }

    def show = {
		def grantReceiptService = new GrantReceiptService();
        def grantReceiptInstance = grantReceiptService.getGrantReceiptById(new Integer(params.id))

        if(!grantReceiptInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
        else { return [ grantReceiptInstance : grantReceiptInstance ] }
    }

    def delete = {
		def grantReceiptService = new GrantReceiptService();
		Integer projectid = grantReceiptService.deleteGrantReceipt(new Integer(params.id))
		
		if(projectid != null){
			if(projectid > 0){
				flash.message = "${message(code: 'default.deleted.label')}"
					redirect(action:create,id:projectid)
			}
		}
		else {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
		}
    }

    def edit = {
		def grantReceiptService = new GrantReceiptService();
		def grantAllocationService = new GrantAllocationService()
        def grantReceiptInstance = grantReceiptService.getGrantReceiptById(new Integer(params.id))
         def dataSecurityService = new DataSecurityService()
		def grantAllocationSplitService=new GrantAllocationSplitService()
       def accountHeadList=grantAllocationSplitService.getAccountHeadByProject(grantReceiptInstance.projects.id)
       
       def fundTransferService=new FundTransferService();
		def grantAllocationInstanceList
        def fundTransferInstanceList
        
		if(grantReceiptInstance)
        {
			if(grantReceiptInstance.projects.id)
	        {
				grantAllocationInstanceList=grantAllocationService.getGrantAllocationsByProject(grantReceiptInstance.projects.id)
	        	fundTransferInstanceList= fundTransferService.getFundTransferDetailsByProject(grantReceiptInstance.projects.id)
	        }
        }
        
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
       //checking  whether the user has access to the given projects
		if(dataSecurityService.checkForAuthorisedAcsessInProjects(grantReceiptInstance.projects.id,new Integer(getUserPartyID()))==0)
		{
			
					
					 redirect uri:'/invalidAccess.gsp'

		}
		else
		{
        if(!grantReceiptInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
        else {
        	NumberFormat formatter = new DecimalFormat("#0.00");
        	def totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProject(grantReceiptInstance.projects.id,getUserPartyID())
        	def totalAmountReceived = grantReceiptService.getSumOfGrantReceviedByProjects(grantReceiptInstance.projects.id)
            grantReceiptInstance.balanceAmt = totAllAmount - totalAmountReceived + grantReceiptInstance.amount
            return [ grantReceiptInstance : grantReceiptInstance,accountHeadList:accountHeadList,
                     grantAllocationInstanceList:grantAllocationInstanceList,
                     fundTransferInstanceList:fundTransferInstanceList,amount:formatter.format(grantReceiptInstance.amount)]
        }
		}
    }

    def update = 
    {
		def grantReceiptService = new GrantReceiptService();
		def fundTransferInstance = new FundTransfer()
    	if(params.fundTransfer)
        {
        	
        	if(params.fundTransfer.id != "null")
        	{
        		fundTransferInstance = FundTransfer.get(new Integer(params.fundTransfer.id))
        	}
        }
		def grantReceiptInstance = grantReceiptService.getGrantReceiptById(new Integer(params.id))
		println"grantReceiptInstance"+grantReceiptInstance
		println"params"+params
		println"grantReceiptInstance.dateOfReceipt"+grantReceiptInstance.dateOfReceipt
    	println"grantReceiptInstance.dateOfReceipt"+params.dateOfReceipt_value
    	println"fundTransferInstance.dateOfTransfer"+fundTransferInstance.dateOfTransfer
    	
          //create SimpleDateFormat object with desired date format
          SimpleDateFormat sdfDestination = new SimpleDateFormat("dd/MM/yyyy");
     
          //parse the date into another format
          String strDate = sdfDestination.format(fundTransferInstance.dateOfTransfer);
          println"strDate"+strDate
          DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
          Date strtingDate = df.parse(strDate)
          println"strtingDate"+strtingDate
          
          Date receiptDate = df.parse(params.dateOfReceipt_value)
          println"receiptDate"+receiptDate
          println"datediff" +(strtingDate > receiptDate)
          
    		if((fundTransferInstance) && (strtingDate > receiptDate))
    	 {
    		println"____testing____"
    		grantReceiptInstance.fundTransfer = fundTransferInstance
    		flash.message="${message(code: 'default.ReceiptDateValidationAgainstFundtransfer.label')}"
    			redirect(action:edit,id:params.id)
    	}
    	else
    	{
         grantReceiptInstance = grantReceiptService.updateGrantReceipt(params)
        if(grantReceiptInstance) 
        {
            
	    		if(grantReceiptInstance.isSaved)
	    		{
        		flash.message = "${message(code: 'default.updated.label')}"
    			redirect(action:create,id:grantReceiptInstance.projects.id)
	    		}
	    		else 
	    		{
                render(view:'edit',model:[grantReceiptInstance:grantReceiptInstance])
	    		}
	    	}
       
        else {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:edit,id:params.id)
        }
    	}
    }

    def create = {
			println "params="+params
		def grantReceiptService = new GrantReceiptService();
		def grantAllocationService = new GrantAllocationService()
    	def grantAllocationSplitService=new GrantAllocationSplitService()	
        def grantReceiptInstance = new GrantReceipt()
        GrailsHttpSession gh=getSession()
        gh.removeValue("Help")
       	//putting help pages in session
       	gh.putValue("Help","Grant_Receipt.htm")
		if(params.grantReceiptInstanceId)
		{
			grantReceiptInstance = grantReceiptService.getGrantReceiptById(new Integer(params.grantReceiptInstanceId))
		}
		else
		{
			grantReceiptInstance.properties = params
		}
        def projectsInstance 
        if(params.id ==null)
        {
        	projectsInstance = projectsService.getProjectById(new Long(params.projectId))
        	//projectsInstance = Projects.get(new Integer(params.projectId))
        	
        }
        else
        {
        	projectsInstance = projectsService.getProjectById(new Long(params.id))
        	// projectsInstance = Projects.get(new Integer(params.id))
        }
          def dataSecurityService = new DataSecurityService()
		//checking  whether the user has access to the given projects
		if(dataSecurityService.checkForAuthorisedAcsessInProjects(projectsInstance.id,new Integer(getUserPartyID()))==0)
		{
			
					
					 redirect uri:'/invalidAccess.gsp'

		}
		else
		{
        projectsInstance.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProject(projectsInstance.id,getUserPartyID())
		def grantAllocationInstanceList
		def fundTransferService=new FundTransferService();
        
        def fundTransferInstanceList
        
		if(params.id ==null)
        {
        	grantAllocationInstanceList=grantAllocationService.getGrantAllocationsByProject(params.projectId)
        	fundTransferInstanceList= fundTransferService.getFundTransferDetailsByProject(params.projectId)
        	
        }
        else
        {
        	grantAllocationInstanceList=grantAllocationService.getGrantAllocationsByProject(params.id)
        	fundTransferInstanceList= fundTransferService.getFundTransferDetailsByProject(params.id)
        }
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
		String subQuery="";
        if(params.sort != null && !params.sort.equals(""))
        	subQuery=" order by GA."+params.sort
       	if(params.order != null && !params.order.equals(""))
       		subQuery =subQuery+" "+params.order
   
        grantReceiptInstance.projects=projectsInstance
        def accountHeadList=grantAllocationSplitService.getAccountHeadByProject(grantReceiptInstance.projects.id)
        def grantReceiptList
        def totalAmountReceived
        def summaryList
        if(params.id ==null)
        {
        	grantReceiptList=grantReceiptService.getGrantReceiptByProjects(params.projectId,subQuery)
        	totalAmountReceived = grantReceiptService.getSumOfGrantReceviedByProjects(params.projectId)
        	summaryList =  grantReceiptService.getGrantReceiptSummary(params.projectId)
        }
        else
        {
        	grantReceiptList=grantReceiptService.getGrantReceiptByProjects(params.id,subQuery)
        	totalAmountReceived = grantReceiptService.getSumOfGrantReceviedByProjects(params.id)
        	summaryList =  grantReceiptService.getGrantReceiptSummary(params.id)
        }
        //def grantRecieptInstanceList=grantReceiptService.getGrantReceiptBySort(params.id,subQuery)
        NumberFormat formatter = new DecimalFormat("#0.00") 
        grantReceiptInstance.balanceAmt = projectsInstance.totAllAmount - totalAmountReceived
        println "grantAllocationInstanceList"+grantAllocationInstanceList.size()
        grantReceiptInstance.grantAllocation = grantAllocationInstanceList[0]
        
        
        ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
        return ['grantReceiptInstance':grantReceiptInstance,
                'grantReceiptInstanceList':grantReceiptList,
                'summaryList':summaryList,
                'grantAllocationInstanceList':grantAllocationInstanceList,
                'accountHeadList':accountHeadList,
                'currencyFormat':currencyFormatter,
                'fundTransferInstanceList':fundTransferInstanceList,
                ,'amount':formatter.format(grantReceiptInstance.amount)]
		}
    }

    def save = {
    	println params
		def grantReceiptService = new GrantReceiptService();
    	def grantAllocationService = new GrantAllocationService()
        def grantReceiptInstance = new GrantReceipt(params)
        def fundTransferInstance = new FundTransfer()
    	def grantReceiptInstanceCheck 
    	println "grantReceiptInstanceCheck ="+grantReceiptInstanceCheck
    	if(params.fundTransfer)
        {
        	
        	if(params.fundTransfer.id != "null")
        	{
        		fundTransferInstance = FundTransfer.get(new Integer(params.fundTransfer.id))
        		grantReceiptInstanceCheck = GrantReceipt.find("from GrantReceipt where fundTransfer.id="+params.fundTransfer.id)
        	}
        }
        if(!grantReceiptInstance.hasErrors() ) 
        {
        	
    	    grantReceiptInstance.createdBy="admin"
    		grantReceiptInstance.modifiedBy="admin"
        	grantReceiptInstance.modifiedBy="admin"
    		grantReceiptInstance.createdDate=new Date()
    	    println "grantReceiptInstance" + grantReceiptInstance.grantAllocation
    	    
    	    	
    	    	println"grantReceiptInstance.dateOfReceipt"+grantReceiptInstance.dateOfReceipt
    	    	println"fundTransferInstance.dateOfTransfer"+fundTransferInstance.dateOfTransfer
    	    	if((fundTransferInstance) && (fundTransferInstance.dateOfTransfer > grantReceiptInstance.dateOfReceipt))
    	    	 {
    	    		println"____testing____"
    	    		grantReceiptInstance.fundTransfer = fundTransferInstance
    	    		flash.message="${message(code: 'default.ReceiptDateValidationAgainstFundtransfer.label')}"
    	    			redirect(action:create,id:params.projectId)
    	    	}
    	    	else
    	    	{
    	    		if(fundTransferInstance)
    	    	    {
    	    		println"........testing outside....."
    	    		grantReceiptInstance.fundTransfer = fundTransferInstance
    	    	    grantReceiptInstance.amount=fundTransferInstance.amount
    	            }
    	    println "grantReceiptInstance*** in controller" +grantReceiptInstance.fundTransfer
    	    
    	    def grantAllocationInstanceList
    		def fundTransferService=new FundTransferService();
    	    def fundTransferInstanceList
            
    		if(params.id ==null)
            {
            	grantAllocationInstanceList=grantAllocationService.getGrantAllocationsByProject(params.projectId)
            	fundTransferInstanceList= fundTransferService.getFundTransferDetailsByProject(params.projectId)
            	
            }
            else
            {
            	grantAllocationInstanceList=grantAllocationService.getGrantAllocationsByProject(params.id)
            	fundTransferInstanceList= fundTransferService.getFundTransferDetailsByProject(params.id)
            }
    	    if(fundTransferInstanceList)
    		{
    	    	println"amma"
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
    	    grantReceiptInstance = grantReceiptService.saveGrantReceipt(grantReceiptInstance,new Integer(params.projectId))
        	flash.message = "${message(code: 'default.Grantreceived.label')}"
        	redirect(action:create,id:grantReceiptInstance.projects.id,
        			params:[grantReceiptInstanceId:grantReceiptInstance.id],
        			        'fundTransferInstanceList':fundTransferInstanceList,
        			        'grantAllocationInstanceList':grantAllocationInstanceList)
    	    }
    	    else
    	    {
    	    	flash.message = "${message(code: 'default.Amountalreadyreceived.label')}"
    	    	redirect(action:create,id:params.projectId)
    	    }
    	  }
        }
        else {
            render(view:'create',model:[grantReceiptInstance:grantReceiptInstance])
        }
      
    }
    def clear = {
    		def grantReceiptService = new GrantReceiptService();
    		def grantAllocationService = new GrantAllocationService()
        	def grantAllocationSplitService=new GrantAllocationSplitService()	
            def grantReceiptInstance = new GrantReceipt()
    		
    	    grantReceiptInstance.properties = params
    		
            println"++++++++++++++++++params+++++++"+params
            
            def projectsInstance = Projects.get(new Integer(params.projectId))
              def dataSecurityService = new DataSecurityService()
    		//checking  whether the user has access to the given projects
    		if(dataSecurityService.checkForAuthorisedAcsessInProjects(projectsInstance.id,new Integer(getUserPartyID()))==0)
    		{
    			
    					
    					 redirect uri:'/invalidAccess.gsp'

    		}
    		else
    		{
            projectsInstance.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProject(projectsInstance.id,getUserPartyID())
    		def grantAllocationInstanceList=grantAllocationService.getGrantAllocationsByProject(params.projectId)
    		String subQuery="";
            if(params.sort != null && !params.sort.equals(""))
           	subQuery=" order by GA."+params.sort
           if(params.order != null && !params.order.equals(""))
           	subQuery =subQuery+" "+params.order
       
            grantReceiptInstance.projects=projectsInstance
            def accountHeadList=grantAllocationSplitService.getAccountHeadByProject(grantReceiptInstance.projects.id)
            def grantReceiptList=grantReceiptService.getGrantReceiptByProjects(params.projectId,subQuery)
            
            //def grantRecieptInstanceList=grantReceiptService.getGrantReceiptBySort(params.id,subQuery)
            
            def totalAmountReceived = grantReceiptService.getSumOfGrantReceviedByProjects(params.projectId)
            
            grantReceiptInstance.balanceAmt = projectsInstance.totAllAmount - totalAmountReceived
            
            	
            def summaryList =  grantReceiptService.getGrantReceiptSummary(params.projectId)
            grantReceiptInstance.grantAllocation = grantAllocationInstanceList[0]
            ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
            NumberFormat formatter = new DecimalFormat("#0.00")
            redirect(action:create,id:params.projectId,
            		'grantReceiptInstance':grantReceiptInstance,
            		'grantReceiptInstanceList':grantReceiptList,
            		'summaryList':summaryList,
            		'grantAllocationInstanceList':grantAllocationInstanceList,
            		'accountHeadList':accountHeadList,'currencyFormat':currencyFormatter,
            		'amount':formatter.format(grantReceiptInstance.amount)
            		)
    		}
        }
	def selectAmount = {
			println params
			def fundTransferInstance = new FundTransfer()
			def grantReceiptInstanceCheck = GrantReceipt.findAll("from GrantReceipt where fundTransfer.id="+params.id)
			  
			if(params.id != "null")
			{	
				if(grantReceiptInstanceCheck)
				{
					println "grant receipt against fund transfer"
					def numformatter = new DecimalFormat("#0.00");
                    render(text:"<input type='text' id='amount' name='amount' value='0.00' style='text-align: right' readOnly=true /><font face='verdana' color='#C00000'>Transferred fund already received</font>", contentType:'text/html')


				
				}
				else
				{
					fundTransferInstance = FundTransfer.get(params.id)
					println "no --- grant receipt against fund transfer"
					def numformatter = new DecimalFormat("#0.00");
				    println numformatter.format(fundTransferInstance.amount) 
						render(text:"<input type='text' id='amount' name='amount' value='${numformatter.format(fundTransferInstance.amount)}' style='text-align: right' readOnly=true />", contentType:'text/html')

				
				}
			}
			else
			{
				def grantReceiptInstance=new GrantReceipt()
				def numformatter = new DecimalFormat("#0.00");
				println numformatter.format(grantReceiptInstance.amount.amount) 
				render(text:"<input type='text' id='amount' name='amount' value='${numformatter.format(grantReceiptInstance.amount)}' style='text-align: right' readOnly=true />", contentType:'text/html')
			}
			
	}
}
