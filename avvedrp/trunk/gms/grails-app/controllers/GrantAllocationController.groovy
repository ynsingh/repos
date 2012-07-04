	import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
	import java.util.Iterator;
	import java.text.*;
	import java.util.*;
	import java.sql.*;
	import java.io.*
	import net.sf.jasperreports.engine.JRException;
	import net.sf.jasperreports.engine.JasperExportManager;
	import net.sf.jasperreports.engine.JasperFillManager;
	import net.sf.jasperreports.engine.JasperPrint;
	import net.sf.jasperreports.engine.JasperRunManager;
	import javax.servlet.ServletOutputStream;
	import grails.util.GrailsUtil
	
	import static org.springframework.security.acls.domain.BasePermission.ADMINISTRATION
	import static org.springframework.security.acls.domain.BasePermission.DELETE
	import static org.springframework.security.acls.domain.BasePermission.READ
	import org.springframework.security.acls.domain.PrincipalSid
	import org.springframework.security.acls.model.Permission
	import org.springframework.security.acls.model.Sid
	import org.springframework.dao.DataAccessException

	import grails.plugins.springsecurity.Secured
	
	import org.codehaus.groovy.grails.commons.ApplicationHolder as AH
	import static org.springframework.security.acls.domain.BasePermission.ADMINISTRATION
	import static org.springframework.security.acls.domain.BasePermission.DELETE
	import static org.springframework.security.acls.domain.BasePermission.READ
	import static org.springframework.security.acls.domain.BasePermission.WRITE
	import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
	import org.springframework.security.core.authority.AuthorityUtils
	import org.springframework.security.core.context.SecurityContextHolder as SCH
	
	
    class GrantAllocationController extends GmsController {
		
	private static final Permission[] HAS_DELETE = [DELETE, ADMINISTRATION]
	private static final Permission[] HAS_ADMIN = [ADMINISTRATION]
	
	def aclPermissionFactory
	def aclUtilService
	def grantAllocationService
	def projectsService
	def springSecurityService
    def grantAllocationSplitService
    def grantExpenseService
    def index = { 
    		
    		redirect(action:list,params:params) 
    		}

	// the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST',funtSave:'POST']

    def list = {
    	GrailsHttpSession gh=getSession()
    	gh.putValue("Help","Fund_Allocation.htm")
        if(!params.max) params.max = 10
        
    	def dataSecurityService = new DataSecurityService()
        def grantAllocationInstanceList=dataSecurityService.getGrantAllocationsForLoginUser(gh.getValue("ProjectID"),gh.getValue("PartyID"))
        		
        [ grantAllocationInstanceList: grantAllocationInstanceList ]
    }
    
    def delete = {
		def grantAllocationService = new GrantAllocationService()
        def dataSecurityService = new DataSecurityService()
		def grantAllocationInstance=grantAllocationService.getGrantAllocationById(new Integer(params.id))
		def grantAllocationSplitInstance = grantAllocationService.getGrantAllocationSplit(grantAllocationInstance);
		def grantExpenseInstance = grantAllocationService.getExpenseForGrantAllocation(grantAllocationInstance);
		def grantReceiptInstance = grantAllocationService.getGrantReceiptForGrantAllocation(grantAllocationInstance);
		def fundTransferInstance= grantAllocationService.getFundTransferForGrantAllocation(grantAllocationInstance);
		if(grantAllocationSplitInstance)
		{
			if(grantAllocationInstance.projects.parent.id == null)
				
			{
				
			redirect(action:'fundAllot') 
			}
			else
			{
				
				redirect(action:'subGrantAllot',id:grantAllocationInstance.projects.parent.id) 
			}
			flash.message = "${message(code: 'default.Cannotdeletedoneheadwiseallocation.label')}" 
		}
		else if(grantExpenseInstance)
		{
           if(grantAllocationInstance.projects.parent.id == null)
				
			{
				
			redirect(action:'fundAllot') 
			}
			else
			{
				
				redirect(action:'subGrantAllot',id:grantAllocationInstance.projects.parent.id) 
			}
			flash.message = "${message(code: 'default.CannotdeleteEnteredGrantExpense.label')}" 

		}
		else if(grantReceiptInstance)
		{
            if(grantAllocationInstance.projects.parent.id == null)
				
			{
				
			redirect(action:'fundAllot') 
			}
			else
			{
				
				redirect(action:'subGrantAllot',id:grantAllocationInstance.projects.parent.id) 
			}
			flash.message = "${message(code: 'default.CannotdeleteReceivedGrant.label')}"

		}
		else if(fundTransferInstance)
		{
			flash.message = "${message(code: 'default.CannotdeletedoneFundTransfer.label')}"
			redirect(action:'subGrantAllot',id:grantAllocationInstance.projects.parent.id)
		}
		else
		{
			def grantAllocationId = grantAllocationService.deleteGrantAllocation(new Integer(params.id))
			if(grantAllocationId != null){
				if(grantAllocationId > 0){
					GrailsHttpSession gh=getSession()
		            if(gh.getValue("fromUrL")=="fundAllot") {
		            	redirect(action:'fundAllot')
		                flash.message = "Fund Allocation deleted" 
		                	
	                }
	                else {
	                	if(gh.getValue("fromUrL")=="subGrantAllotExt")
	                	redirect(action:'subGrantAllotExt',id:gh.getValue("fromID"))
	                	else
	                		
	                		redirect(action:'subGrantAllot',id:gh.getValue("fromID"))	
	                	flash.message = "${message(code: 'default.deleted.label')}"
	                }
				}
				else {
					
		            flash.message = "${message(code: 'default.notfond.label')}"
		            redirect(action:list)
		        }
			}
		}
		
		
		
    }

    def edit = {
		def grantAllocationService = new GrantAllocationService()
        def grantAllocationInstance=grantAllocationService.getGrantAllocationById(new Integer(params.id))
        def dataSecurityService = new DataSecurityService()
		//checking  whether the user has access to the given projects
		def projectsInstance = projectsService.getProjectById(grantAllocationInstance.projects.id)
		if(dataSecurityService.checkForAuthorisedAcsessInProjects(grantAllocationInstance.projects.id,new Integer(getUserPartyID()))==0)
		{
			
					
					 redirect uri:'/invalidAccess.gsp'

		}
		else
		{
        if(!grantAllocationInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
        else {
        	NumberFormat formatter = new DecimalFormat("#0.00");
            
             grantAllocationInstance.amountAllocated=  new Double(formatter.format(grantAllocationInstance.amountAllocated))
        	return [projectsInstance:projectsInstance,grantAllocationInstance : grantAllocationInstance,amount:formatter.format(grantAllocationInstance.amountAllocated) ]
        }
		}
    }
    

    def update = 
    {
    	def grantAllocationService = new GrantAllocationService()
    	def grantAllocation = grantAllocationService.checkGrantAllotted(params)
    	
    	GrailsHttpSession gh=getSession()
    	if(grantAllocation)
    	{
	    	flash.message = "${message(code: 'default.Amountcannotchangedallocatedtosubprojects.label')}"
	    	redirect(action:edit,id:params.id)
    	}
    	else
    	{
	    	def grantAllocationInstance = grantAllocationService.updateGrantAllocation(params)
			if(grantAllocationInstance)
			{
				if(grantAllocationInstance.isSaved)
				{
					if(gh.getValue("fromUrL")=="fundAllot")
					{
						redirect(action:'fundAllot',model:[grantAllocationInstance:grantAllocationInstance],params:[id:params.projects.id])
		                flash.message = "${message(code: 'default.updated.label')}"
					}
					else
					{
						redirect(action:'create',model:[grantAllocationInstance:grantAllocationInstance],params:[id:params.projects.id])
						flash.message = "${message(code: 'default.updated.label')}"
					}
				}
				else
					render(view:'edit',model:[grantAllocationInstance:grantAllocationInstance])
			}
			else 
			{
				flash.message = "${message(code: 'default.notfond.label')}"
				redirect(action:edit,id:params.id)
			}
    	}
    }
    
    def fundList = {
		GrailsHttpSession gh=getSession()
        def grantAllocationInstance = new GrantAllocation()
        grantAllocationInstance.properties = params
        def dataSecurityService = new DataSecurityService()
        def grantAllocationInstanceList=dataSecurityService.getGrantAllocationsForLoginUser(
        		"Fund",gh.getValue("ProjectID"),gh.getValue("PartyID"))
        return ['grantAllocationInstance':grantAllocationInstance,
                'grantAllocationInstanceList':grantAllocationInstanceList]
    }
    
    def fundAllot = 
    {
			GrailsHttpSession gh=getSession()
    		gh.removeValue("Help")
    		//putting help pages in session
    		gh.putValue("Help","Fund_Allocation.htm")
        	gh.putValue("fromUrL", "fundAllot");
        	gh.putValue("fromID", params.id);
        	
        	def grantAllocationInstance = new GrantAllocation()
        	grantAllocationInstance.properties = params
      	    def dataSecurityService = new DataSecurityService()
    		String subQuery="";
            if(params.sort != null && !params.sort.equals(""))
            	subQuery=" order by GA."+params.sort
            if(params.order != null && !params.order.equals(""))
            	subQuery =subQuery+" "+params.order
           
            def grantAllocationInstanceList
            def projectIdStart="("
            def projectsService = new ProjectsService()		
            def projectsInstance = projectsService.getProjectById(params.id)	
            //def projectsPIMapInstance = dataSecurityService.getProjectsPIMap(projectsInstance.id);
            def projectsPIMapInstance = projectsService.checkPIofProject(projectsInstance.id)
            def grantAllocationInstanceListproj
            grantAllocationInstanceListproj = grantAllocationService.getGrantAllocationsForAssignedProject(projectsInstance.id)
            def partyinstance
            def partInstance
            if(projectsInstance.parent!=null)
            {
	            //def grantInstance = GrantAllocation.findAll("from GrantAllocation GA where GA.projects="+projectsInstance.parentId)
	            def grantInstance = grantAllocationService.getGrantAllocationInstanceForParentProject(projectsInstance)
	            partyinstance=GrantAllocation.executeQuery("select GA.party from GrantAllocation GA where GA.id="+grantInstance[0].id)
            }
            else
            {
            	partInstance = Party.get(gh.getValue("Party"))
            }
            
        	
    	     
        	ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
        	NumberFormat formatter = new DecimalFormat("#0.00");
        	return ['grantAllocationInstance':grantAllocationInstance,
        	        'grantAllocationInstanceList':grantAllocationInstanceListproj,
                'partyinstance':partyinstance,'partInstance':partInstance,
                'currencyFormat':currencyFormatter,
                'projectsPIMapInstance':projectsPIMapInstance,
                'projectsInstance':projectsInstance,
                'amount':formatter.format(grantAllocationInstance.amountAllocated)]
    }
        
    def funtSave = 
    {
    		GrailsHttpSession gh=getSession()
    		def grantAllocationInstance = new GrantAllocation(params)
    		def projectsInstance = projectsService.getProjectById(new Integer(params.id))	
    		//def projectsInstance = Projects.get(params.id)
    		grantAllocationInstance.projects = projectsInstance
    		grantAllocationInstance.createdBy="admin"
    		grantAllocationInstance.modifiedBy="admin"
    		// grantAllocationInstance.DateOfAllocation=new Date();
    	    grantAllocationInstance.allocationType="Fund"
    	    grantAllocationInstance.sanctionOrderNo="";
    	    grantAllocationInstance.code=""
    	    def partInstance
    	    // def granterInstance=Party.get(new Integer(params.grantor))
    	    if(projectsInstance.parent!=null)
    	    {
    	    def grantInstance = grantAllocationService.getGrantAllocationInstanceForParentProject(projectsInstance)
            def partyinstance=GrantAllocation.executeQuery("select GA.party from GrantAllocation GA where GA.id="+grantInstance[0].id)
    				       
    		grantAllocationInstance.party=partyinstance[0]
    	    }
    	    
    	    else
    	    {
    	    	partInstance = Party.get(gh.getValue("Party"))
    	    grantAllocationInstance.party = partInstance
    	    }
    	   	def grantAllocationService = new GrantAllocationService()
    	    
		    def fundAllotId = grantAllocationService.saveFundAllocation(grantAllocationInstance)
		    if(fundAllotId != null)
		    {
		    	flash.message = "${message(code: 'default.created.label')}"
	    		redirect(action:'fundAllot',params:[id:params.id])
		    }
		    else
		    {
		    	flash.message = "${message(code: 'default.FundAllocationnotcreated.label')}"
	    		redirect(action:'fundAllot',params:[id:params.id])
	    	}
    	    
        
    }
    
    def subGrantAllot = 
    {
  		GrailsHttpSession gh=getSession()
        def grantAllocationInstance = new GrantAllocation()	
    	gh.removeValue("Help")
	    //putting help pages in session
	    gh.putValue("Help","SubProject_Allocation.htm")
    	gh.putValue("fromID", params.id);
    	String subQuery="";
        if(params.sort != null && !params.sort.equals(""))
       	subQuery=" order by GA."+params.sort
       	if(params.order != null && !params.order.equals(""))
       	subQuery =subQuery+" "+params.order
    	def grantAllocationService = new GrantAllocationService()
    	def grantAllocation=grantAllocationService.getGrantAllocationById(new Integer(params.id))
    	def investigatorService=new InvestigatorService()
		def investigatorInstanceList = investigatorService.getAllInvestigators()
    	def dataSecurityService = new DataSecurityService()
    	//checking  whether the user has access to the given projects
    	if(dataSecurityService.checkForAuthorisedAcsessInProjects(new Integer(params.id),new Integer(getUserPartyID()))==0)
    	{
    		 redirect uri:'/invalidAccess.gsp'
		}
		else
		{
            grantAllocationInstance.properties = params
	        def projectInstance = new Projects()
	        projectInstance.parent = Projects.get( params.id )
	        projectInstance.parent.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProject(projectInstance.parent.id,getUserPartyID())
	        
	        def subProjectsList = dataSecurityService.getSubProjectsForProject(projectInstance)
	        
	        def partyInstance=Party.get(gh.getValue("Party"))
	        grantAllocationInstance.granter=partyInstance;
	        def grantAllocationInstanceList = grantAllocationService.getSubGrantAllocationsChild(new Integer(params.id),getUserPartyID(),subQuery)
	        def headAllocatedAmount = grantAllocationSplitService.getAllocatedAmountByProjectId(params.id)//sum of headwise allocated amount
	        double sumAmountAllocated = 0.00
	        def projectsPIMapInstanceList=[]
	        def sum
	        for(int i=0;i<grantAllocationInstanceList.size();i++)
	        {
	        	sumAmountAllocated = sumAmountAllocated + grantAllocationInstanceList[i].amountAllocated
	        	
	        	//get PI of projects
	        	def projectsPIMapInstance = dataSecurityService.getProjectsPIMap(grantAllocationInstanceList[i].projects.id);
    	        projectsPIMapInstanceList.add(projectsPIMapInstance)
	        }
            
	        grantAllocationInstance.totAllAmount = sumAmountAllocated
	        if(headAllocatedAmount[0])
	         {
	           sum = sumAmountAllocated + headAllocatedAmount[0] // sum of grantamount and expenseamount
	           grantAllocationInstance.balanceAmount=projectInstance.parent.totAllAmount - sum
	         }
	        else
	        	grantAllocationInstance.balanceAmount=projectInstance.parent.totAllAmount - sumAmountAllocated
	        
	        ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
	        NumberFormat formatter = new DecimalFormat("#0.00");
	        return ['grantAllocationInstance':grantAllocationInstance,
	                'projectInstance':projectInstance,
	                'grantAllocationInstanceList':grantAllocationInstanceList,
	                'partyInstance':partyInstance,'grantAllocation':grantAllocation,
	                'projectsPIMapInstanceList':projectsPIMapInstanceList,
	                'currencyFormat':currencyFormatter,'subProjectsList':subProjectsList,
	                'amount':formatter.format(grantAllocationInstance.amountAllocated),
	                'investigatorInstanceList':investigatorInstanceList]
    	}
 			def projectInstance = Projects.get( params.id )
	        projectInstance.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProject(projectInstance.id,getUserPartyID())
	       
        
      
        	def partyInstance=Party.get(gh.getValue("Party"))
        	grantAllocationInstance.granter=partyInstance;
        def grantAllocationInstanceList = grantAllocationService.getSubGrantAllocationInSort(new Integer(params.id),subQuery)
        
        return ['grantAllocationInstance':grantAllocationInstance,
                'projectInstance':projectInstance,
                'grantAllocationInstanceList':grantAllocationInstanceList,
                'partyInstance':partyInstance,'grantAllocation':grantAllocation ]
		
    }


	
	def updatePi = {
		def investigatorService=new InvestigatorService()
		
		if(params.recipient)
    	{
			def investigatorInstanceList = investigatorService.getInvestigatorsWithParty(params.recipient)
	    	
	    	render (template:"listPi", model : ['investigatorInstanceList' : investigatorInstanceList])
	    	
    	}
		
	}
    
    def subGrantAllotExt = {
    	GrailsHttpSession gh=getSession()
    	gh.putValue("fromUrL", "subGrantAllotExt");
    	gh.putValue("fromID", params.id);
    	gh.removeValue("Help");
    	gh.putValue("Help","Grant_Alot_Ext_Agency.htm");
    	
        def grantAllocationInstance = new GrantAllocation()
        grantAllocationInstance.properties = params
        
        def grantAllocationService = new GrantAllocationService()
        def dataSecurityService = new DataSecurityService()
        
//      checking  whether the user has access to the given projects
		if(dataSecurityService.checkForAuthorisedAcsessInProjects(new Integer(params.id),new Integer(getUserPartyID()))==0)
		{	
			redirect uri:'/invalidAccess.gsp'
		}
		else
		{ 
			def projectInstance = Projects.get( params.id )
	        projectInstance.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProject(projectInstance.id,getUserPartyID())
	       
        	def partyInstance=Party.get(new Long(gh.getValue("Party")))
        	grantAllocationInstance.party=partyInstance;
			grantAllocationInstance.projects = projectInstance
			String subQuery="";
	        if(params.sort != null && !params.sort.equals(""))
	       	subQuery=" order by GA."+params.sort
	       if(params.order != null && !params.order.equals(""))
	       	subQuery =subQuery+" "+params.order
			
			def grantAllocationInstanceList = grantAllocationService.getValidGrantAllocationsForProjectAndParty("("+projectInstance.id.toString()+")","("+partyInstance.id.toString()+")",subQuery)
			 def projectsPIMapInstanceList=[]
            //get PI of projects
            for(int i=0;i<grantAllocationInstanceList.size();i++)
            {
            	def projectsPIMapInstance = dataSecurityService.getProjectsPIMap(grantAllocationInstanceList[i].projects.id);
            	projectsPIMapInstanceList.add(projectsPIMapInstance)
            }
	        ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
			return ['grantAllocationInstance':grantAllocationInstance,
			        'projectInstance':projectInstance,
			        'grantAllocationInstanceList':grantAllocationInstanceList,
			        'partyInstance':partyInstance,
			        'projectsPIMapInstanceList':projectsPIMapInstanceList,
			        'currencyFormat':currencyFormatter]
		}
    }
	/*1. Get the total Amount allocated during the sub allotment and the new amount Allocated that entered 
	 * by the user.
	 * 2. Calculate the sum of these amounts 
	 * 3. Compare this total amount with the amount allocated for the entire Project
	 * 4. Only if the total amount is not exceeding the allocated amount for the
	 *    project,the user can give the suballocation
	 * */
    def subGrantSave = 
    {
		def investigatorService = new InvestigatorService()
		params.createdDate = new Date();
		params.modifiedDate = new Date();
		double sumAmount = 0.0
    	double totAllAmount = ((params.totAllAmount).toDouble()).doubleValue()// Fund for the parent project
    	double amountAllocated = ((params.amountAllocated).toDouble()).doubleValue() // new amount
    	double newAmount = ((params.amount).toDouble()).doubleValue() // Total amount used for sub allocation
    	double balanceAmount= ((params.balance).toDouble()).doubleValue()// totAllAmount - newAmount
    	sumAmount = amountAllocated + newAmount
      	def investigatorList = params.investigator.id
		def granterInstance
    	def grantAllocationInstance
    	def projectsInstance = new Projects(params)
		projectsInstance.code=params.code
		projectsInstance.name=params.name
		projectsInstance.createdBy="user";
		projectsInstance.modifiedBy = "user";
		projectsInstance.activeYesNo='Y'
		//save projects
		def parentProjectsInstance = projectsService.getProjectById(new Long( params.parent.id ))
    	projectsInstance.parent=parentProjectsInstance
    	projectsInstance.projectType=parentProjectsInstance.projectType
    	def investigatorInstance = investigatorService.getInvestigatorById(new Integer(investigatorList[0])) 
		projectsInstance.investigator = investigatorInstance
		def investigatorcoPiInstance = investigatorService.getInvestigatorById(new Integer(investigatorList[1]))
		projectsInstance.copi = investigatorcoPiInstance
    	GrailsHttpSession gh=getSession()
    	granterInstance=Party.get(new Integer(params.grantor))
		grantAllocationInstance = new GrantAllocation(params)
        grantAllocationInstance.projects=projectsInstance
		grantAllocationInstance.granter=granterInstance
    	grantAllocationInstance.createdBy="admin"
		grantAllocationInstance.modifiedBy="admin"
		grantAllocationInstance.allocationType="grand"
		grantAllocationInstance.code=""
		def grantAllocationInstanceCheck = grantAllocationService.getGrantAllocationsByProjectIdAndPartyId(grantAllocationInstance.projects.id,grantAllocationInstance.party.id)
		def partyService = new PartyService();
    	def grantAllocationService = new GrantAllocationService()
    
		if(Double.compare(totAllAmount,sumAmount) >=0 )
		{
			Integer duplicateCheck = grantAllocationService.checkDuplicateFundAllot(grantAllocationInstance);
			def grantAllocationList = grantAllocationService.getGrantAllocationsByProjectCode(grantAllocationInstance.projects.id)
			def GrantAllocation 
			def grantAllocationInstanceForAccess
			def accessInstance
			def GrantAllocationSave
			
			if(grantAllocationList)
			{
					
					if(grantAllocationList[0].party.id == grantAllocationInstance.party.id || grantAllocationList==null)
					{
						GrantAllocation = grantAllocationService.checkGrantAllocationSplitByProjectId(grantAllocationInstance)
						if(GrantAllocation == null)
						{
						projectsInstance = projectsService.saveProjects(projectsInstance,gh.getValue("UserId"))
							if(projectsInstance.saveMode != null)
							{
								if(projectsInstance.saveMode.equals("Saved"))
					    		{
									GrantAllocationSave = grantAllocationService.saveSubGrantAllocation(grantAllocationInstance)
									if(!grantAllocationInstanceCheck)
									{
										grantAllocationInstanceForAccess = GrantAllocationSave
										accessInstance = projectsService.saveProjectAccessPermission(grantAllocationInstanceForAccess)
										def userInstanse = Person.get(gh.getValue("UserId"))
										/*calling a method for save project access permission for pi*/
								    	projectsService.saveAccessPermissionForprojects(grantAllocationInstance,projectsInstance,investigatorInstance.email)
							    	
									}
									flash.message = "${message(code: 'default.Newallocationcreated.label')}"
				    			}
				    			else if(projectsInstance.saveMode.equals("Duplicate"))
				    			{
				    				flash.message = "${message(code: 'default.AlreadyExists.label')}"
				    			
				    			}
				    		}
							else
				    			flash.message = "${message(code: 'default.AlreadyExists.label')}"
							
							
						}
						else
						{
							
							if(amountAllocated > balanceAmount)
							{
								flash.message= "${message(code: 'default.couldnotallocateBalanceAmount.label')}"
							}
							else
							{
							projectsInstance = projectsService.saveProjects(projectsInstance,gh.getValue("UserId"))
							if(projectsInstance.saveMode != null)
							{
								if(projectsInstance.saveMode.equals("Saved"))
					    		{
									GrantAllocationSave = grantAllocationService.saveSubGrantAllocation(grantAllocationInstance)
									if(!grantAllocationInstanceCheck)
									{
										grantAllocationInstanceForAccess = GrantAllocation.get(GrantAllocation.id)
										accessInstance = projectsService.saveProjectAccessPermission(grantAllocationInstanceForAccess)
									}
									flash.message = "${message(code: 'default.Newallocationcreated.label')}"
					    		}
					    		else if(projectsInstance.saveMode.equals("Duplicate"))
					    		{
					    			flash.message = "${message(code: 'default.AlreadyExists.label')}"
					    			
					    		}
					    	}
					    	else
					    		flash.message = "${message(code: 'default.AlreadyExists.label')}"
				    	}
					}
				}
				else
				{
					flash.message = "${message(code: 'default.ProjectAlreadyAllotted.label')}" + grantAllocationList[0].party.code
				}
			}
			
			
			
			else
			{
				GrantAllocation = grantAllocationService.checkGrantAllocationSplitByProjectId(grantAllocationInstance)
					if(GrantAllocation == null)
						{
						if(investigatorInstance == investigatorcoPiInstance)
						{
							flash.message = "${message(code: 'default.alreadyAssignedPI.label')}"
						}
						else
						{
							projectsInstance = projectsService.saveProjects(projectsInstance,gh.getValue("UserId"))
							if(projectsInstance.saveMode != null)
							{
								if(projectsInstance.saveMode.equals("Saved"))
					    		{
									GrantAllocationSave = grantAllocationService.saveSubGrantAllocation(grantAllocationInstance)
									if(!grantAllocationInstanceCheck)
									{
										grantAllocationInstanceForAccess = GrantAllocationSave
										accessInstance = projectsService.saveProjectAccessPermission(grantAllocationInstanceForAccess)
										def userInstanse = Person.get(gh.getValue("UserId"))
										/*calling a method for save project access permission for pi*/
								    	projectsService.saveAccessPermissionForprojects(grantAllocationInstance,projectsInstance,investigatorInstance.email)
								    	/*calling a method for save project access permission for co-pi*/
								    	projectsService.saveAccessPermissionForprojects(grantAllocationInstance,projectsInstance,investigatorcoPiInstance.email)
							    	
									}
							 	flash.message = "${message(code: 'default.Newallocationcreated.label')}"
				    			}
				    			else if(projectsInstance.saveMode.equals("Duplicate"))
				    			{
				    				flash.message = "${message(code: 'default.AlreadyExists.label')}"
				    			
				    			}
				    		}
							else
				    			flash.message = "${message(code: 'default.AlreadyExists.label')}"
						    }
					    }
					else
						{
							if(amountAllocated > balanceAmount)
								{
									
								flash.message="${message(code: 'default.couldnotallocateBalanceAmount.label')}"
								}
						
							else
								{
									projectsInstance = projectsService.saveProjects(projectsInstance,gh.getValue("UserId"))
									if(projectsInstance.saveMode != null)
									{
										if(projectsInstance.saveMode.equals("Saved"))
							    		{
											GrantAllocationSave = grantAllocationService.saveSubGrantAllocation(grantAllocationInstance)
											if(!grantAllocationInstanceCheck)
											{
												grantAllocationInstanceForAccess = GrantAllocation.get(GrantAllocation.id)
												accessInstance = projectsService.saveProjectAccessPermission(grantAllocationInstanceForAccess)
												def userInstanse = Person.get(gh.getValue("UserId"))
												/*calling a method for save project access permission for pi*/
										    	projectsService.saveAccessPermissionForprojects(grantAllocationInstance,projectsInstance,investigatorInstance.email)
										    	/*calling a method for save project access permission for co-pi*/
										    	projectsService.saveAccessPermissionForprojects(grantAllocationInstance,projectsInstance,investigatorcoPiInstance.email)
									    	
											}
											flash.message = "${message(code: 'default.Newallocationcreated.label')}"
						    			}
						    			else if(projectsInstance.saveMode.equals("Duplicate"))
						    			{
						    				flash.message = "${message(code: 'default.AlreadyExists.label')}"
						    			
						    			}
						    		}
									else
						    			flash.message = "${message(code: 'default.AlreadyExists.label')}"
							    }
					   }
			}
		}
		else
		{
			flash.message = "${message(code: 'default.AmountshouldnotexceedAllocatedAmount.label')}" 
		}
		redirect(action:'subGrantAllot',id:parentProjectsInstance.id)
		//end save projects
	}


    def subGrantSaveExt = {
    	
        def grantAllocationInstance = new GrantAllocation(params)
        def grantAllocationInstanceNew = new GrantAllocation()
		grantAllocationInstanceNew.createdBy="admin"
        	grantAllocationInstanceNew.modifiedBy="admin"
		
        		grantAllocationInstanceNew.allocationType="grand"
        			grantAllocationInstanceNew.code="default "
        				grantAllocationInstanceNew.projects= grantAllocationInstance.projects
	        			grantAllocationInstanceNew.amountAllocated= grantAllocationInstance.amountAllocated
	        			grantAllocationInstanceNew.DateOfAllocation= grantAllocationInstance.DateOfAllocation
	        			grantAllocationInstanceNew.remarks= grantAllocationInstance.remarks
	        			grantAllocationInstanceNew.sanctionOrderNo= grantAllocationInstance.sanctionOrderNo
	        			
		def granterInstance = Party.get(grantAllocationInstance.granter.id)
		grantAllocationInstanceNew.granter = granterInstance
		def partyInstance = Party.get(grantAllocationInstance.party.id)
		grantAllocationInstanceNew.party = partyInstance
		def GrantAllocation = grantAllocationService.subGrantSaveExt(grantAllocationInstanceNew)	
        flash.message = "${message(code: 'default.Newallocationcreated.label')}"
        redirect(action:'subGrantAllotExt',id:grantAllocationInstance.projects.id)
       
    }

    def mainDash = {
    	GrailsHttpSession gh=getSession()
    	
    	//get data from grant_allocation
    	
    	
    	
	    List<GrantAllocation> grantAllocationInstanceList 	
    
    	def dataSecurityService = new DataSecurityService();
    	try{
          grantAllocationInstanceList = grantAllocationService.getAll()
	
    	}
    	catch(Exception e)
    	{
    		
    	}
		
    	/*if(gh.getValue("Role")=="ROLE_PI")
    	{
    		println "Piiii"
    		grantAllocationInstanceList=dataSecurityService.getProjectsWithGrantAllocationForLoginPi(gh.getValue("Pi"))
    	}
    	else{
    		
    		grantAllocationInstanceList=dataSecurityService.getProjectsFromGrantAllocationForLoginUser(gh.getValue("PartyID"));
    	}	    	
    	*/
    	
         
        [ grantAllocationInstanceList: grantAllocationInstanceList ]
    	
    }

    def show = {
		def grantAllocationService = new GrantAllocationService()
        def grantAllocationInstance = grantAllocationService.getGrantAllocationById(new Integer(params.id))
        	//GrantAllocation.get( params.id )

        if(!grantAllocationInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
        else { return [ grantAllocationInstance : grantAllocationInstance ] }
    }

    
    
    def editProAllot = {
		GrailsHttpSession gh=getSession()
		def partyService = new PartyService()
		def grantAllocationService = new GrantAllocationService()
		def dataSecurityService = new DataSecurityService()
		def investigatorService=new InvestigatorService()
		
		def grantAllocationInstance = grantAllocationService.getGrantAllocationById(new Integer(params.id))
		def investigatorInstanceList = investigatorService.getInvestigatorsWithParty(grantAllocationInstance.party.id)
		def projectsInstance = Projects.get(grantAllocationInstance.projects.id)
        if(projectsInstance)
		{
			def projectsPIMapInstance = projectsService.checkPIofProject(projectsInstance.id)
			def projectsCOPIMapInstance = projectsService.checkCOPIofProject(projectsInstance.id)
			if(projectsPIMapInstance)
			projectsInstance.investigator = projectsPIMapInstance.investigator
			if(projectsCOPIMapInstance)
			projectsInstance.copi = projectsCOPIMapInstance.investigator
		}
		def partyInstance=partyService.getPartyById(gh.getValue("Party"))
		  def projectInstance 

        if(grantAllocationInstance.projects.parent!=null) 
         projectInstance = Projects.get(grantAllocationInstance.projects.parent.id )
        else
         	projectInstance = Projects.get(grantAllocationInstance.projects.id )
        projectInstance.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProject(projectInstance.id,getUserPartyID())
          def grantAllocationInstanceList = grantAllocationService.getSubGrantAllocations(new Integer((projectInstance.id).intValue()))
	        def headAllocatedAmount = grantAllocationSplitService.getAllocatedAmountByProjectId(projectInstance.id)//sum of headwise allocated amount

	        double sumAmountAllocated = 0.00
	        def projectsPIMapInstanceList=[]
	        def sum
	        for(int i=0;i<grantAllocationInstanceList.size();i++)
	        {
	        	sumAmountAllocated = sumAmountAllocated + grantAllocationInstanceList[i].amountAllocated
	        	
	        	//get PI of projects
	        	def projectsPIMapInstance = dataSecurityService.getProjectsPIMap(grantAllocationInstanceList[i].projects.id);
    	        projectsPIMapInstanceList.add(projectsPIMapInstance)
	        }
            
	        grantAllocationInstance.totAllAmount = sumAmountAllocated
	        if(headAllocatedAmount[0])
	         {
	           sum = sumAmountAllocated + headAllocatedAmount[0] // sum of grantamount and expenseamount
	           grantAllocationInstance.balanceAmount=projectInstance.totAllAmount - sum
	         }
	        else
	        	grantAllocationInstance.balanceAmount=projectInstance.totAllAmount - sumAmountAllocated
        
       
        //checking  whether the user has access to the given projects
		if(dataSecurityService.checkForAuthorisedAcsessInProjects(projectInstance.id,new Integer(getUserPartyID()))==0)
		{
			
					
					 redirect uri:'/invalidAccess.gsp'

		}
		else
		{ 
	        if(!grantAllocationInstance) {
	            flash.message = "${message(code: 'default.notfond.label')}"
	            redirect(action:list)
	        }
	        else {
	        	ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
	        	NumberFormat formatter = new DecimalFormat("#0.00");
	        	return [ grantAllocationInstance : grantAllocationInstance ,
	        	         'projectInstance':projectInstance,
	        	         'partyInstance':partyInstance,
	        	         'currencyFormat':currencyFormatter,
	        	         'amount':formatter.format(grantAllocationInstance.amountAllocated),
	        	         'projectsInstance':projectsInstance,'investigatorInstanceList':investigatorInstanceList]
	        }
        
		}
    }
    def updateProAllot = 
    {
    	 double sumAmount = 0.0
    	 double projectAmount = 0.0
    	 double amountToBeUpdated = 0.0
    	 double amountAllocated= ((params.amountAllocated).toDouble()).doubleValue()
    	 double balanceAmount= ((params.balance).toDouble()).doubleValue()
    	 projectAmount = ((params.totAllAmount).toDouble()).doubleValue()	 
     	 sumAmount = ((params.amountAllocated).toDouble()).doubleValue() + ((params.amount).toDouble()).doubleValue()
    	 GrailsHttpSession gh=getSession()
		 def grantAllocationService = new GrantAllocationService()
		 def investigatorService = new InvestigatorService()
		 def pastGrantAllocation = grantAllocationService.getGrantAllocationById(new Integer(params.id))
		 amountToBeUpdated = (sumAmount - (pastGrantAllocation.amountAllocated).doubleValue())
		 double exactBalance = (balanceAmount+(pastGrantAllocation.amountAllocated).doubleValue())
		 
	     def grantAllocationUpdateInstance = grantAllocationService.getGrantAllocationById(new Integer(params.id))
	        
		 def grantAllocationInstance 
		 def grantAllocationSplitInstance = grantAllocationService.getGrantAllocationSplit(grantAllocationUpdateInstance);
		 def grantExpenseInstance = grantAllocationService.getExpenseForGrantAllocation(grantAllocationUpdateInstance);
		 def grantReceiptInstance = grantAllocationService.getGrantReceiptForGrantAllocation(grantAllocationUpdateInstance);
		 def fundTransferInstance= grantAllocationService.getFundTransferForGrantAllocation(grantAllocationUpdateInstance);
		 def grantAllocationTackingInstance = GrantAllocationTracking.find("from GrantAllocationTracking GT where GT.grantAllocation="+pastGrantAllocation.id)
		 if(grantAllocationTackingInstance)
         {
          if(grantAllocationTackingInstance.grantAllocationStatus=='Closed')
	        {
	        	 flash.message = "${message(code: 'default.Cantupdatethisprojectasitisalreadyclosed.label')}"
	    		 redirect(action:'editProAllot',id:pastGrantAllocation.id)
	        }
	     }
         else
         {
			if(amountAllocated > exactBalance)
			{
				flash.message= "${message(code: 'default.couldnotallocateBalanceAmount.label')}"
					redirect(action:'editProAllot',id:pastGrantAllocation.id)
			}
			else
			{
			if(grantAllocationSplitInstance)
			{
				
					flash.message = "${message(code: 'default.Cannotupdatedoneheadwiseallocation.label')}"
					redirect(action:'subGrantAllot',id:grantAllocationUpdateInstance.projects.parent.id) 
				
				 
			}
			else if(grantExpenseInstance)
			{
				flash.message = "${message(code: 'default.CannotUpdateEnteredGrantExpense.label')}"
					redirect(action:'subGrantAllot',id:grantAllocationUpdateInstance.projects.parent.id) 
				 

			}
			else if(grantReceiptInstance)
			{
	            
				flash.message = "${message(code: 'default.CannotUpdateReceivedGrant.label')}"
					redirect(action:'subGrantAllot',id:grantAllocationUpdateInstance.projects.parent.id) 
				

			}
			else if(fundTransferInstance)
			{
				flash.message = "${message(code: 'default.CannotUpdatedoneFundTransfer.label')}"
				redirect(action:'subGrantAllot',id:grantAllocationUpdateInstance.projects.parent.id)
			}
			else
			{
		 if(grantAllocationUpdateInstance.projects.parent!=null)
		 {
		 if(Double.compare(projectAmount,amountToBeUpdated) >=0)
		 {

			
			/* project save start */
			def projectsInstance = new Projects()
			 projectsInstance.properties=params
			 projectsInstance.id=new Long(params.projects.id)
			def investigatorInstance=investigatorService.getInvestigatorById(params.investigator.id)
			def copiInstance=investigatorService.getInvestigatorById(params.copi.id)
			projectsInstance.investigator = investigatorInstance
			projectsInstance.copi = copiInstance
			if(investigatorInstance == copiInstance)
			{
				flash.message = "${message(code: 'default.alreadyAssignedPI.label')}"
				redirect(action:'editProAllot',id:pastGrantAllocation.id)
			}
			else
			{
				def projectsInstanceSave = projectsService.updateSubProject(projectsInstance)
				 
				 if(projectsInstanceSave)
				 {
					 if(projectsInstanceSave.saveMode != null)
					{
						if(projectsInstanceSave.saveMode.equals("Updated"))
						{
							grantAllocationInstance = grantAllocationService.updateGrantAllocation(params)
							flash.message = "${message(code: 'default.updated.label')}"
													
						}
						else if(projectsInstanceSave.saveMode.equals("Duplicate"))
						{
							flash.message = "${message(code: 'default.AlreadyExists.label')}"
							redirect(action:'editProAllot',id:pastGrantAllocation.id)
						}
					}
					else 
					{
						redirect(action:'editProAllot',id:pastGrantAllocation.id)
			    	}
				}
				 else 
				{
					 flash.message = "${message(code: 'default.notfond.label')}"
					redirect(action:'editProAllot',id:pastGrantAllocation.id)
				}
				}
			
			/* project save end */
		 }
		 else
		 {
			 flash.message = "${message(code: 'default.AmountshouldnotexceedAllocatedAmount.label')}"
			 redirect(action:'editProAllot',id:pastGrantAllocation.id)
		 }
		 }
		 else
		 {
			 grantAllocationInstance = grantAllocationService.updateGrantAllocation(params)
		 }
		 if(grantAllocationInstance)
		 {
			 if(grantAllocationInstance.isSaved)
			 {
				 if(grantAllocationUpdateInstance.projects.parent==null)
				 {
					 flash.message = "${message(code: 'default.updated.label')}"
				 }
				 else
				 {
				 flash.message = "${message(code: 'default.updated.label')}"
				 }
				 if(gh.getValue("fromUrL")=="subGrantAllotExt")
					 redirect(action:'subGrantAllotExt',id:grantAllocationInstance.projects.id)
				 else
				 {
					 def grantAllocation = grantAllocationService.getGrantAllocationAfterSubProjectAllocation(grantAllocationInstance)
					 redirect(action:'subGrantAllot',id:grantAllocation.projects.id)
				 }
			 }
			 else 
			 {
				 render(view:'edit',model:[grantAllocationInstance:grantAllocationInstance])
			 }
		 }
/*		 else 
		 {
			 flash.message = "GrantAllocation not found with id ${params.id}"
			 redirect(action:edit,id:params.id)
		 }*/
    }
    }
    }
    }
    
    def headMsg = {
		redirect(action:assignHeads,id:params.grantAllotId)
            
    }
    
    def reports = {
		println params
		GrailsHttpSession gh=getSession()
		def dataSecurityService = new DataSecurityService();
		def grantAllocationInstanceList
		   
        try{
        	grantAllocationInstanceList = grantAllocationService.getAll()

	}
	  catch(Exception e)
	{
		
	}
        
        def projectsList=[]
        def projectsPIMapInstanceList=[]
        //get PI of projects
        for(int i=0;i<grantAllocationInstanceList.size();i++)
        {
        
        	projectsList.add(grantAllocationInstanceList[i].projects)
        }
		
		
		
		
	
	
        return[projectInstanceList:projectsList]
    }
        
    
    
    

    def create = {
    		
    		GrailsHttpSession gh=getSession()
    		gh.putValue("fromUrL", "Create");
    		gh.putValue("fromID", params.id);
        def grantAllocationInstance = new GrantAllocation()

        grantAllocationInstance.properties = params
        def grantAllocation=GrantAllocation.find("from GrantAllocation  GA where  GA.id="+params.id);
        println "params"+params.id
        grantAllocationInstance.projects=grantAllocation.projects
            def grantAllocationInstanceList=GrantAllocation.findAll("from GrantAllocation  GA where  GA.allocationType<>'Fund' and GA.projects= "+grantAllocation.projects.id);
         
   
        	 def allAmt=GrantAllocation.executeQuery("select sum(GA.amountAllocated) as total from GrantAllocation  GA where  GA.allocationType<>'Fund' and GA.projects= "+grantAllocation.projects.id+" group by GA.projects");
         println "allAmt"+allAmt;
        	 if(allAmt[0]!=null)
        	 grantAllocation.totAllAmount=new Double(allAmt[0]).doubleValue()
        return ['grantAllocationInstance':grantAllocationInstance,
                'projectInstance':grantAllocation,
                'grantAllocationInstanceList':grantAllocationInstanceList]
        
    }

    def save = {
        def grantAllocationInstance = new GrantAllocation(params)
        if(!grantAllocationInstance.hasErrors() ) {
        	    grantAllocationInstance.createdBy="admin"
        		grantAllocationInstance.modifiedBy="admin"
        		def projects=Projects.find("from Projects  PA where  PA.id="+params.project);
        	    println projects
        		grantAllocationInstance.projects=projects;
        	    grantAllocationInstance.allocationType="grand"
        	    grantAllocationInstance.code=""
        	    def grantAllocation = GrantAllocation.find("from GrantAllocation GA where GA.id = '"+params.grantAllot+"'");
        	    grantAllocationInstance.granter = grantAllocation.party
        	    grantAllocationInstance.save();
            	println params
            flash.message = "${message(code: 'default.ProjectAllotted.label')}"
            redirect(action:'create',id:params.grantAllot)
        }
        else {
            render(view:'create',model:[grantAllocationInstance:grantAllocationInstance])
        }
    }
    
    def listReport = {
    		println params
        	
    		return['reportListInstance':params]
    		
    }
    
  
    
def projectDash = 
{
			def totmonths=new HashSet()
    		def expense=[]
    		def recive=[]
    		def months=[]
    		def monthName=""
    		def totalexpense=0;
    		def totalRecieve=0;
    		def balance=[];
    		def range=[];
    		def rangelimit=[];
    		def monthname=["Jan","Feb","Mar","Apr","May","Jun","July","Aug","Sep","Oct","Nov","Dec"]
    		def monthnameView=[]
    		def grantAllocationService = new GrantAllocationService()
    		def projectTrackingInstanceList = []
    		Integer ProjectId=new Integer(params.id);
            // def grantAllocationInstanceId = projectsService.getProjectById(new Long(params.id))
            def projectInstance
            
            try{
             projectInstance = projectsService.getProjectById(new Long(params.id))
             def projectInstanceList = Projects.findAll("from Projects  P where  P.parent.id="+projectInstance.id)
        	 def parentProjectInstance
        	 String subProjectString="";
             
        	 if(projectInstanceList)
        	 {
        		 for(int i=0;i<projectInstanceList.size();i++)
        		 {
        			 if(subProjectString =="")
        			 {
        				 subProjectString = subProjectString + projectInstanceList[i].code
        			 }
        			 else
        			 {
        				 subProjectString = subProjectString +", "+ projectInstanceList[i].code
        			 }
        		 }
        	 }
             if(projectInstance.parent)
             {
            	 parentProjectInstance = Projects.get(projectInstance.parent.id)
             }
             
            projectInstance.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProject(projectInstance.id,getUserPartyID())
           	def sumAmount = GrantExpense.executeQuery("select sum(GE.expenseAmount) as SumAmt from GrantExpense GE where GE.projects.id ="+ projectInstance.id) 
           	def sumTransferInstance = FundTransfer.executeQuery("select sum(FU.amount) as SumAmt from FundTransfer FU where FU.grantAllocation.projects.parent.id ="+ projectInstance.id)
            println "sumTransferInstance"+ sumTransferInstance
           	double fundTransferInstance=0;
                              
                              if(sumTransferInstance[0]==null)
                            	  fundTransferInstance=0
                            	  else
                            		 fundTransferInstance=sumTransferInstance[0]
                              
                         println "fundTransferInstance"+ fundTransferInstance
           	def sumGrantRecieve = GrantExpense.executeQuery("select sum(GR.amount) as SumAmt from GrantReceipt GR where GR.projects.id ="+ projectInstance.id)
           	
           	def grantAllocationSplit = GrantAllocationSplit.findAll("from GrantAllocationSplit  GAS where  GAS.projects="+projectInstance.id);
            def grantAllocationInstance = GrantAllocation.find("from GrantAllocation  GA where  GA.projects="+projectInstance.id);
            grantAllocationSplit = GrantAllocationSplit.executeQuery("select sum(GAS.amount) as SumAmt,GAS.accountHead.code from GrantAllocationSplit GAS where GAS.projects.id ="+ projectInstance.id+"and  GAS.grantAllocation.party.id ="+ getUserPartyID()+" group by GAS.accountHead.id")
                              
                              
             def monthlyExpenseAndRecipts = GrantReceipt.executeQuery("SELECT MONTH(dateOfExpense) as Month ,"+ 
									    " sum(expenseAmount) as  expAmt ,	(SELECT  sum(amount) as expAmt FROM GrantReceipt GR where GR.projects.id="+ projectInstance.id+"  	and  MONTHNAME(dateOfReceipt)=MONTHNAME(GA.dateOfExpense) "+
							"	group by MONTHNAME(dateOfReceipt)) as reciept "+
							"	 FROM GrantExpense GA where  GA.projects.id="+ projectInstance.id+"  group by MONTHNAME(GA.dateOfExpense) order by GA.dateOfExpense   asc")

							 println  "monthlyExpenseAndRecipts "+monthlyExpenseAndRecipts
							
							  
							 
							 
							 GrailsHttpSession gh=getSession()
							gh.removeValue("Help")
							gh.putValue("Help","Project_Dash.htm")//putting help pages in session
						        gh.putValue("ProjectID",projectInstance.id.toString());	
    		  					
							 def totalExpense=0;
                              def totalReciept=0;
                             
                              
							 
                              monthnameView.add("0")
							   balance.add(0)
							 for(int i=0;i<monthlyExpenseAndRecipts.size();i++)
					            {
								monthnameView.add(monthname[new Integer(monthlyExpenseAndRecipts[i][0]).intValue()-1])					            
									double expenseMonth=0
								if(monthlyExpenseAndRecipts[i][1]!=null)
									expenseMonth=new Double(monthlyExpenseAndRecipts[i][1]).doubleValue()
								        totalExpense=totalExpense+ expenseMonth;
								double Reciept=0
								if(monthlyExpenseAndRecipts[i][2]!=null)
									Reciept=new Double(monthlyExpenseAndRecipts[i][2]).doubleValue()
								totalReciept=totalReciept+ Reciept;
								double balanceInmonth=0;
								if(totalReciept>0)
									balanceInmonth=(totalReciept-totalExpense)/totalReciept*4096
									if(balanceInmonth<0)
										balanceInmonth=0;
									balance.add(balanceInmonth)
                                } 
							 
							  println "aa"+ monthnameView
							  println "balance"+ balance
							  println "sumAmount"+ sumAmount
							  if(sumAmount[0]==null)
								  sumAmount[0]=0
								  if(sumGrantRecieve[0]==null)
									  sumGrantRecieve[0]=0
									
									  									  
								//drawing the line chart

									  def chart1 = new GoogleChartBuilder()
									def resultLinechart = chart1.lineChart
									{
										 size(w:250, h:180)  
										 title(color:'808080', size:13)
										 {
										  row('Fund Balance') 
										 } 
										 data(encoding:'extended')
										 { 
											 dataSet(balance)  
										 } 
										 colors
										 {   
											 color('66CC00') 
											 color('3399ff')  
										 } 
										 lineStyle(line1:[1,6,3]) 
										 legend
										 {
											 label('% of balance') 
										 }  
										 axis(left:[0,20,40,60,80,100], bottom:monthnameView)
										 backgrounds
										 {
											 background
											 {
												 solid(color:'cbe2f0')
											 }  
		
											 area
											 {
											       gradient(angle:45, start:'FFFFFF', end:'cbe2f0')
											 } 
										 } 
										 markers
										 {     
											 rangeMarker(type:'horizontal', color:'FF0000', start:50, end:52)
										 }
	
										  grid(y:50, x:100/1, dash:3, space:1)
										  
										  
									 }

									  
									  //drawing the  pie chart
									  
									    def chart = new GoogleChartBuilder()
				      def textList =[]

				      def total=0
				      def i=0
				      grantAllocationSplit.each {
                
										  
										  
							textList.add (grantAllocationSplit[i][0]*100/projectInstance.totAllAmount)
							total=total+grantAllocationSplit[i][0]
							i++
						}
							
									  
									  i=0
						if(total<projectInstance.totAllAmount)
						textList.add((projectInstance.totAllAmount-total)*100/projectInstance.totAllAmount)
				      def resultPiechart = chart.pie3DChart{
				      size(w:250, h:95)
				      title(color:'808080', size:13)
				      {
				         row('Head Wise Allocation') 
				      } 
				      data(encoding:'text')
				      {
				         dataSet(textList)
				      }
					  colors
					  {
						color('e25d3a')
						color('fbc363')
					   }
					    
					     

				       labels
				       { 
					 grantAllocationSplit.each {

					 label(grantAllocationSplit[i][1]) 
					 i++
					 }
						if(total<projectInstance.totAllAmount)
						label(" Un Allocated")
					}
				       backgrounds{
				 background{
				  solid(color:'cbe2f0')
				    }  

				 area{
					gradient(angle:45, start:'cbe2f0', end:'cbe2f0')
				 } 
				 } 

				       }
								  
									  			         
            if(!projectInstance) {
                flash.message = "${message(code: 'default.notfond.label')}"
                redirect(action:list)
            }
          
            else 
            {
				if(params.projectStatus!='Closed')
				{
				projectInstance.status = "Active"
				println"8888888"
				
				}
				else
				{
            	projectInstance.status = params.projectStatus  
				println"999999"
				}           	  	
            	ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
            	return [ projectInstance : projectInstance,sumAmount:sumAmount,
            	         grantAllocationSplit:grantAllocationSplit,
            	         resultPiechart:resultPiechart,resultLinechart:resultLinechart,
            	         rangelimit:rangelimit,sumGrantRecieve:sumGrantRecieve,
            	         'currencyFormat':currencyFormatter,
            	         'grantAllocationInstance':grantAllocationInstance,
            	         'fundTransferInstance':fundTransferInstance,
            	         'subProjectString':subProjectString,
            	         'parentProjectInstance':parentProjectInstance]
            	}
            }
            catch(Exception e)
            {
            	redirect uri:'/invalidAccess.gsp'
            }
            }
        
        
    def grantReports = {
    		println params
    		GrailsHttpSession gh=getSession()
    	
    		//def dataSecurityService = new DataSecurityService();
    		List<GrantAllocation> grantAllocationInstance 	
		
	    	try{
	    		grantAllocationInstance = grantAllocationService.getAll()
		
	    	}
	    	catch(Exception e)
	    	{
	    		
	    	}
	    	def projectInstanceList=[]
			for(int i=0;i<grantAllocationInstance.size();i++)
			{
				projectInstanceList.add(grantAllocationInstance[i].projects)
				println projectInstanceList
			}
    		//def projectInstanceList = dataSecurityService.getProjectsForLoginUser(gh.getValue("PartyID"))
    		println "projectInstanceList"+projectInstanceList
    		return[projectInstanceList:projectInstanceList]
        }
		
		def auditLoggingReport = {
    		println params
    		GrailsHttpSession gh=getSession()
    	
    		//def dataSecurityService = new DataSecurityService();
    		List<GrantAllocation> grantAllocationInstance 	
		
	    	try{
	    		grantAllocationInstance = grantAllocationService.getAll()
		
	    	}
	    	catch(Exception e)
	    	{
	    		
	    	}
	    	def projectInstanceList=[]
			for(int i=0;i<grantAllocationInstance.size();i++)
			{
				projectInstanceList.add(grantAllocationInstance[i].projects)
				println projectInstanceList
			}
    		//def projectInstanceList = dataSecurityService.getProjectsForLoginUser(gh.getValue("PartyID"))
    		println "projectInstanceList"+projectInstanceList
    		return[projectInstanceList:projectInstanceList]
        }
//  For State of Accounts Report
    def reportView = {}
    /* Get sql connection */
    private Connection getSqlConnection(){
        /*getting the datasource instance from the GmsController
         *as this class extends it*/
		 Connection con = dataSource.getConnection()
    	return con
    }
	
	
	
	def showReports=
	{
		File reportFile
		def webRootDir = servletContext.getRealPath("/")
		def utilizationInstance = Utilization.get(params.id)
		reportFile = new File(webRootDir+ "/reports/StatementOFAccounts.jasper")
		Map parameters = new HashMap();
								
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		def ProjectID
		if(utilizationInstance)
		{
			parameters.put("reportDateTo",utilizationInstance.endDate)
			parameters.put("reportDate",utilizationInstance.startDate)
			
			
			ProjectID =utilizationInstance.projects.id
		}
		else
		{
			Date startDate  = df.parse(params.reportDate_value)
			Date endDate = df.parse(params.reportDateTo_value)
			println "endDate" +endDate 
			println "startDate" + startDate
			parameters.put("reportDateTo",endDate)
			parameters.put("reportDate",startDate)
			ProjectID =params.projects
		}
			
		GrailsHttpSession gh=getSession()
		println "priject id from session" +gh.getValue("ProjectID")
		println "test =="	
		println "inner =="	
		
		
		// parameters.put("grantPeriod",params.grantPeriod)
		parameters.put("projectID",ProjectID.toString())
		parameters.put("Path",webRootDir+"/reports/")
		
		def sql = getSqlConnection()
		
		byte[] bytes = 
			JasperRunManager.runReportToPdf(
					reportFile.getPath(), 
				parameters, 
				sql
				);

		response.setContentType("application/pdf");
		response.setContentLength(bytes.length);
		ServletOutputStream ouputStream = response.getOutputStream();
		ouputStream.write(bytes, 0, bytes.length);
		sql.close()
		ouputStream.flush();
		ouputStream.close();
				
	}
	def utilizationCertificate=
	{
		File reportFile
		def webRootDir = servletContext.getRealPath("/")
		
		def utilizationInstance = Utilization.get(params.id)
		
		reportFile = new File(webRootDir+ "/reports/UtilizationCertificate.jasper")
		Map parameters = new HashMap();
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		def ProjectID
		if(utilizationInstance)
		{
			println("utilizationInstance --->"+utilizationInstance)
			println("utilizationInstance.startdate --->"+utilizationInstance.startDate)
			println("utilizationInstance.end date --->"+utilizationInstance.endDate)

			parameters.put("reportDateTo",utilizationInstance.endDate)
			parameters.put("reportDate",utilizationInstance.startDate)
			
			println "utilizationInstance.projects.id"+utilizationInstance.projects.id
			ProjectID =utilizationInstance.projects.id
		}
		else
		{
			Date startDate  = df.parse(params.reportDate_value)
			Date endDate = df.parse(params.reportDateTo_value)
			println "endDate" +endDate 
			println "startDate" + startDate
			parameters.put("reportDateTo",endDate)
			parameters.put("reportDate",startDate)
			
			ProjectID =params.projects
		}

		GrailsHttpSession gh=getSession()
		println "priject id from session" +gh.getValue("ProjectID")
		println "test =="	
		println "inner =="	
		
		
		// parameters.put("grantPeriod",params.grantPeriod)
		parameters.put("projectID",ProjectID.toString())
		parameters.put("Path",webRootDir+"/reports/")
		def sql = getSqlConnection()
		
		byte[] bytes = 
			JasperRunManager.runReportToPdf(
					reportFile.getPath(), 
				parameters, 
				sql
				);
		println "parameters =="	+ parameters
		response.setContentType("application/pdf");
		response.setContentLength(bytes.length);
		ServletOutputStream ouputStream = response.getOutputStream();
		ouputStream.write(bytes, 0, bytes.length);
		sql.close()
		ouputStream.flush();
		ouputStream.close();
			
}

def menu = {
			def userService = new UserService()
			def partyService = new PartyService()
			GrailsHttpSession gh=getSession()
			def parentList = []
			def menuRoleMapParentList = [] as Set
			def roleLst= new ArrayList()
			def allChildList = []
	    	def sizeList =[]
	    	int s=0;
			def userRoleList = userService.getUserRoleByUserId(gh.getValue("UserId"))
			for(int i=0;i<userRoleList.size();i++)
			{
				roleLst << userRoleList[i]
			}
			String roleIds="(";
			for(int i=0;i<roleLst.size();i++)
			{
				roleIds=roleIds+roleLst[i].id+","
			}
			roleIds=roleIds.substring(0,roleIds.length()-1)+")"
			menuRoleMapParentList = MenuRoleMap.findAll("from MenuRoleMap MRM where MRM.role.id in "+roleIds+ "and MRM.menu.parentId=-1 and MRM.activeYesNo='Y' and MRM.menu.menuAlignment='V' group by MRM.menu.id order by MRM.menu.menuOrder asc")
			for(int k=0;k<menuRoleMapParentList.size();k++){
				def childList=MenuRoleMap.findAll("from MenuRoleMap MRM where MRM.role.id in "+roleIds+" and MRM.menu.parentId="+menuRoleMapParentList[k].menu.id+" and MRM.activeYesNo='Y' and MRM.menu.menuAlignment='V' group by MRM.menu.id order by MRM.menu.menuOrder asc")
				if (!childList){
					parentList.add(menuRoleMapParentList[k])
    			}
			}
			menuRoleMapParentList.removeAll(parentList)
			render(view:'menu',model:[menuRoleMapParentList:menuRoleMapParentList,roleIds:roleIds])
	}
def top = {}

def gmsFrame = {
			def userService = new UserService()
			def partyService = new PartyService()
			GrailsHttpSession gh=getSession()
			def partyInstance = Party.get(gh.getValue("Party"))
			def menuRoleMapParentList = [] as Set
			def userRoleList = userService.getUserRoleByUserId(gh.getValue("UserId"))
			def roleLst= new ArrayList()
			String path
			def menuRoleMapChildInstance = []
			for(int i=0;i<userRoleList.size();i++)
			{
				roleLst << userRoleList[i]
			}
			String roleIds="(";
			for(int i=0;i<roleLst.size();i++)
			{
				roleIds=roleIds+roleLst[i].id+","
			}
			roleIds=roleIds.substring(0,roleIds.length()-1)+")"
			menuRoleMapParentList = MenuRoleMap.findAll("from MenuRoleMap MRM where MRM.role.id in "+roleIds+ "and MRM.menu.parentId=-1 and MRM.activeYesNo='Y' and MRM.menu.menuAlignment='V' group by MRM.menu.id order by MRM.menu.menuOrder asc")
			for(int j=0;j<menuRoleMapParentList.size();j++)
			{
				menuRoleMapChildInstance = MenuRoleMap.findAll("from MenuRoleMap MRM where MRM.role.id in "+roleIds+" and MRM.menu.parentId="+menuRoleMapParentList[j].menu.id+" and MRM.activeYesNo='Y' and MRM.menu.menuAlignment='V' group by MRM.menu.id order by MRM.menu.menuOrder asc")
				if(menuRoleMapChildInstance){
					break;
				}
			}
			if(menuRoleMapChildInstance){
			
				path = "../"+menuRoleMapChildInstance[0].menu.menuPath
				
			}
			else{
				path = "../user/changePassword"
			}
				
			[path:path]
	}
	
   def projectsSummary = {
			
	GrailsHttpSession gh=getSession()
	def partyInstance=Party.get(gh.getValue("Party"))
	return['partyInstance':partyInstance]

	}
	
  def grantedProjects = {
			
			GrailsHttpSession gh=getSession()
			def partyInstance=Party.get(gh.getValue("Party"))
			def partyInstanceList = Party.executeQuery("select GA.party.id from GrantAllocation GA where GA.granter.id = "+partyInstance.id+" group by GA.party.id")
            def partyNewInstanceList=[]
            def partyIdInstance
            for(int i=0;i<partyInstanceList.size();i++)
              {
            	partyIdInstance = Party.find("from Party P where P.id = "+partyInstanceList[i])
         	    partyNewInstanceList.add(partyIdInstance)
              }
			return['partyInstance':partyInstance,'partyNewInstanceList':partyNewInstanceList]	
			
	}
}
