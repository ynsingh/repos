import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import java.text.SimpleDateFormat
import java.text.*;
import java.util.*;
import org.codehaus.groovy.grails.commons.ApplicationHolder as AH
import grails.util.GrailsUtil
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.codehaus.groovy.grails.commons.ControllerArtefactHandler
import org.springframework.beans.BeanWrapper
import org.springframework.beans.PropertyAccessorFactory
import grails.converters.JSON
class ProjectsController extends GmsController
{
	def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']
	def partyDepartmentService
	def partyService
    def grantAllocationService
    def projectsService
    def fundTransferService
    def grantReceiptService
    def investigatorService 
    def list = 
    {
    	GrailsHttpSession gh=getSession()
		
		def grantAllocationWithprojectsInstanceList
		def grandAllocationList
		
		gh.removeValue("Help")
		gh.putValue("Help","Project_List.htm")//putting help pages in session
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		if(!params.max) params.max = 10
			String subQuery="";
		if(params.sort != null && !params.sort.equals(""))
			subQuery=" order by GA."+params.sort
		println "+params.sort"+params.sort
		if(params.order != null && !params.order.equals(""))
			subQuery =subQuery+" "+params.order
			params.offset = (params.offset ? params.int('offset') : 0)
		grantAllocationWithprojectsInstanceList = grantAllocationService
													.getGrantAllocationGroupByProjects(gh.getValue("Party"))
		//------
		 // For Pagination
			def total=grantAllocationWithprojectsInstanceList.size()
			if(total)
			{
			def max = projectsService.findUpperIndex(params.offset,params.max,total) 
			
			 grantAllocationWithprojectsInstanceList = grantAllocationWithprojectsInstanceList.getAt(params.offset..max)											
			}
			 def  pIMapList =[]
		def pIMapInstance
		def projectInstanceList = []
		for(int i=0;i<grantAllocationWithprojectsInstanceList.size();i++)
		{
			pIMapInstance=projectsService.checkPIofProject(grantAllocationWithprojectsInstanceList[i].projects.id)
			
			pIMapList.add(pIMapInstance)
			/* Get Closed project details */
			def projectTrackingInstanceCheck = grantAllocationService.getClosedProject(grantAllocationWithprojectsInstanceList[i].projects.id)
			if(projectTrackingInstanceCheck)
				grantAllocationWithprojectsInstanceList[i].projects.status = "Closed"
			
		}
		
		[ 'grantAllocationWithprojectsInstanceList': grantAllocationWithprojectsInstanceList,'pIMapList':pIMapList,'grantAllocationWithprojectsTotal':total,offset:params.offset]
    }
	
	def inactiveProjectsList = 
	{
		GrailsHttpSession gh=getSession()
		
		def grantAllocationWithprojectsInstanceList
		def grandAllocationList
		
		gh.removeValue("Help")   		
		gh.putValue("Help","Project_List.htm")//putting help pages in session
		
		if(!params.max) params.max = 10
			String subQuery="";
		if(params.sort != null && !params.sort.equals(""))
			subQuery=" order by GA."+params.sort
		println "+params.sort"+params.sort
		if(params.order != null && !params.order.equals(""))
			subQuery =subQuery+" "+params.order
		
		List<GrantAllocation> grantAllocationInstanceList = grantAllocationService.getAll()
		[ grantAllocationWithprojectsInstanceList: grantAllocationInstanceList ]
	}
    def show = 
    {
		def projectsService = new ProjectsService()
		def projectsInstance = projectsService.getProjectById(new Integer( params.id ))
		def dataSecurityService = new DataSecurityService()
		//checking  whether the user has access to the given projects
		if(dataSecurityService.checkForAuthorisedAcsessInProjects(new Integer( params.id ),new Integer(getUserPartyID()))==0)
		{
			redirect uri:'/invalidAccess.gsp'
		}
		else
		{
			if(!projectsInstance) 
			{
				flash.message = "${message(code: 'default.notfond.label')}"
				redirect(action:list)
			}
			else 
			{ 
				return [ projectsInstance : projectsInstance ] 
			}
		}
    }

	def delete =
	{
		def projectsService = new ProjectsService()
		def dataSecurityService = new DataSecurityService()
		
		
		//checking  whether the user has access to the given projects
		def projectsInstance = new Projects() 
		projectsInstance.properties = params
		println"params.id"+params.id
		Integer projectId = projectsService.deleteProject(params)
		
		if(projectId != null)
		{
			if(projectId > 0)
			{
				 flash.message = "${message(code: 'default.deleted.label')}"
			}
			else
			{
				flash.message = "${message(code: 'default.cannotDeleteProject.label')}"
			}
			if(projectsInstance.parent !=null)
			{
				redirect(action:showSubProjects,id:projectsInstance.parent.id)
			}
			else
			{
				redirect(action:list,id:projectsInstance.id)
			}
		}
		else 
		{
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
		}
    }
    
    
    def edit = 
    {
    	def projectsService = new ProjectsService()
		def dataSecurityService = new DataSecurityService()
		
		GrailsHttpSession gh=getSession()
		gh.putValue("Help","Edit_Projects.htm")//putting help pages in session
					
		def projectsInstance = projectsService.getProjectById(new Integer( params.id ))
		if(projectsInstance)
		{
			def projectsPIMapInstance = projectsService.checkPIofProject(projectsInstance.id)
			if(projectsPIMapInstance)
			{	projectsInstance.investigator = projectsPIMapInstance.investigator
				
				}
	    }
		
		def projectid=projectsInstance.id
		//checking  whether the user has access to the given projects
		if(dataSecurityService.checkForAuthorisedAcsessInProjects(projectid,new Integer(getUserPartyID()))==0)
		{
			redirect uri:'/invalidAccess.gsp'
		}
		else
		{
			if(!projectsInstance) 
			{
				flash.message = "${message(code: 'default.notfond.label')}"
				redirect(action:list)
			}
			else 
			{
				
				def investigatorService = new InvestigatorService()
                def investigatorList=[]
    	        investigatorList=investigatorService.getInvestigatorsWithParty(gh.getValue("PartyID"))
    	        def projectsPiMapInstance=investigatorService.getProjectsPIMapByProjectsId(projectsInstance.id)
    	        return [ 'projectsInstance' : projectsInstance , 'investigatorList':investigatorList]
		
			}
		}
    
	   
  }
   
 	def editsub =
	{
		def projectsService = new ProjectsService()
		def dataSecurityService = new DataSecurityService()
		
		GrailsHttpSession gh=getSession()
		gh.putValue("Help","New_Projects.htm")//putting help pages in session
		
		def projectsInstance = projectsService.getProjectById(new Integer( params.id ))
		def projectid=projectsInstance.id
		println"params"+params
		println"projectid"+projectid
		if(!projectsInstance)
		{
			flash.message = "${message(code: 'default.notfond.label')}"
        	redirect(action:showSubProjects,id:params.parent.id)
		}
		else 
		{
			return [ projectsInstance : projectsInstance ]
		}
    }

	
	def subedit = 
	{
		def projectsService = new ProjectsService()
		def dataSecurityService = new DataSecurityService()
		GrailsHttpSession gh=getSession()
		def projectsInstance = projectsService.getProjectById(new Integer( params.id ))
		
		def projectid
       
		if(params.flag=='Y')
		{
			projectid=projectsInstance.parent.id
		}
		else
		{
			projectid=projectsInstance.id
		}
		    		
		//checking  whether the user has access to the given projects
		if(dataSecurityService.checkForAuthorisedAcsessInProjects(projectsInstance.parent.id,
																	new Integer(getUserPartyID()))==0)
		{
			 redirect uri:'/invalidAccess.gsp'
		}
		else
		{
			if(!projectsInstance)
			{
                flash.message = "${message(code: 'default.notfond.label')}"
                redirect(action:list)
			}
			else 
			{
				return [ projectsInstance : projectsInstance ]
			}
		}
	}
    

    def update = 
    {
		
		GrailsHttpSession gh=getSession()
		
		def projectsInstance = projectsService.updateProject(params)	
		
		def investigatorInstance=investigatorService.getInvestigatorById(params.investigator.id)
		
		if(projectsInstance)
		{
			if(projectsInstance.saveMode != null)
			{
				if(projectsInstance.saveMode.equals("Updated"))
				{
					flash.message ="${message(code: 'default.updated.label')}" 
					redirect(action:edit,id:projectsInstance.id)
				}
				else if(projectsInstance.saveMode.equals("Duplicate"))
				{
					def investigatorList=investigatorService.getInvestigatorsWithParty(gh.getValue("PartyID"))
					gh.putValue("ProjectId",projectsInstance.id)
		    		//validation error msg start
        			// The following helps with field highlighting in your view
        			def projectsNewInstance = new Projects()
					projectsNewInstance.properties=params
					projectsNewInstance.id=projectsInstance.id
					projectsNewInstance.errors.rejectValue('code','default.AlreadyExists.label')
        			//end
        			render(view:'edit',model:['projectsInstance':projectsNewInstance,'investigatorList':investigatorList])
					}
				else if(projectsInstance.saveMode.equals("NotUpdated"))
				{
					flash.message = "${message(code: 'default.cannotupdate.label')}"+investigatorInstance?.email+ "${message(code: 'default.alreadyassigned.label')}"
						
						render(view:'edit',model:[projectsInstance:projectsInstance])
				}
			}
			else 
			{
				render(view:'edit',model:[projectsInstance:projectsInstance])
			}
		}
		else 
		{
			flash.message = "${message(code: 'default.notfond.label')}"
			redirect(action:edit,id:params.id)
		}
	}
    
	def subupdate =
	{
		def projectsService = new ProjectsService()
		def projectsInstance = projectsService.updateSubProject(params)	
		println"projectsInstance"+projectsInstance.saveMode
		println"params.parent.id"+params.parent.id
		if(projectsInstance)
		{
			if(projectsInstance.saveMode != null)
			{
				if(projectsInstance.saveMode.equals("Updated"))
				{
					flash.message = "${message(code: 'default.updated.label')}"
					println"projectsInstance after"+projectsInstance
					redirect(action:showSubProjects,id:projectsInstance.parent.id)
				}
				else if(projectsInstance.saveMode.equals("Duplicate"))
				{
					flash.message = "${message(code: 'default.AlreadyExists.label')}"
						gh.putValue("ProjectId",projectsInstance.id)
		    			redirect(action:edit,id:projectsInstance.id)
				}
			}
			else 
			{
		        render(view:'editsub',model:[projectsInstance:projectsInstance])
		    }
		}
		else 
		{
		    flash.message = "${message(code: 'default.notfond.label')}"
		    redirect(action:editsub,id:params.parent.id)
		}
	}

    def create = 
    {
			def projectsInstance = new Projects()
		projectsInstance.properties = params['Projects']
		def projectsService = new ProjectsService()
		GrailsHttpSession gh=getSession()
		gh.putValue("Help","New_Projects.htm")//putting help pages in session
		def investigatorService = new InvestigatorService()
        def investigatorList=[]
    	investigatorList=investigatorService.getInvestigatorsWithParty(gh.getValue("PartyID"))
    	def grantAllocationWithprojectsInstanceList
		if(params.id)
		{
	    	projectsInstance = projectsService.getProjectById(new Integer( params.id ))
		}
		return ['projectsInstance':projectsInstance,'investigatorList':investigatorList]
		
				
	}
	
	/*
	 * @@ Save project details @@ 
	 **** 1. Save the project details
	 **** 2. Grant will be allocated to the project with amount 0.00
	 **** 3. PI will be mapped to the project
	 */
    def save = 
    {
		params.createdBy = "user";
		params.createdDate = new Date();
		params.modifiedBy = "user";
		params.modifiedDate = new Date();
    	def projectsInstance = new Projects(params)
    	println params
    	def projectsService = new ProjectsService()
    	
    	GrailsHttpSession gh=getSession()
    	   	
    	projectsInstance = projectsService.saveProjectsWithAmountAllocated(projectsInstance,gh.getValue("UserId"),params)

    	if(projectsInstance.saveMode != null)
    	{
    		if(projectsInstance.saveMode.equals("Saved"))
    		{
    			flash.message = "${message(code: 'default.createdProject.message')}"
    			gh.putValue("ProjectId",projectsInstance.id)
			redirect(action:'projectDash',controller:'grantAllocation',id:projectsInstance.id)
			}
    		else if(projectsInstance.saveMode.equals("Duplicate"))
    		{
    			def investigatorList=investigatorService.getInvestigatorsWithParty(gh.getValue("PartyID"))
    				gh.putValue("ProjectId",projectsInstance.id)
        			//validation error msg start
        			 // The following helps with field highlighting in your view
                    projectsInstance.errors.rejectValue('code','default.AlreadyExists.label')
        			//end
        			render(view:'create',model:['projectsInstance':projectsInstance,'investigatorList':investigatorList])
        			
    		}
    	}
    	else
    	{
    		render(view:'create',model:[projectsInstance:projectsInstance])
    	}
    }
    def saveSub = 
    {
	    println "save sub "+params.parent.id
		params.createdBy = "user";
		params.createdDate = new Date();
		params.modifiedBy = "user";
		params.modifiedDate = new Date();
    	def projectsInstance = new Projects(params)
    	def projectsService = new ProjectsService()
    	println params.parent
    	def parentProjectsInstance = projectsService.getProjectById(new Integer( params.parent.id ))
    	
    	projectsInstance.projectType=parentProjectsInstance.projectType
    	GrailsHttpSession gh=getSession()
    	projectsInstance = projectsService.saveProjects(projectsInstance,gh.getValue("UserId"))
    	
    	if(projectsInstance.saveMode != null)
    	{
    		if(projectsInstance.saveMode.equals("Saved"))
    		{
    			flash.message = "${message(code: 'default.created.label')}"
    			redirect(action:showSubProjects,id:projectsInstance.parent.id)
    		}
    		else if(projectsInstance.saveMode.equals("Duplicate"))
    		{
    			flash.message = "${message(code: 'default.AlreadyExists.label')}"
    			render(view:'showSubProjects',model:[projectsInstance:projectsInstance])
    		}
    	}
    	else
    	{
    		flash.message = "${message(code: 'default.AlreadyExists.label')}"
    		render(view:'showSubProjects',model:[projectsInstance:projectsInstance])
    	}
    }
        		 		
    def proList = 
    {
		GrailsHttpSession gh=getSession()
		gh.putValue("Help","Project_List.htm")//putting help pages in session
        
		if(!params.max) params.max = 10
        
        def dataSecurityService = new DataSecurityService()
        def projectsInstanceList=dataSecurityService.getProjectsOfLoginUser(gh.getValue("ProjectID"))
        
        [ projectsInstanceList: projectsInstanceList ]
    }
    
    def showSubProjects =
    {
		println "params"+params.id
		def dataSecurityService = new DataSecurityService()
		
		//checking  whether the user has access to the given projects
		if(dataSecurityService.checkForAuthorisedAcsessInProjects(new Integer( params.id ),new Integer(getUserPartyID()))==0)
		{
			 redirect uri:'/invalidAccess.gsp'
		}
		else
		{
	    	GrailsHttpSession gh=getSession()
	    	gh.putValue("Help","sub_Projects.htm")//putting help pages in session
	       
	    	def projectsInstance = new Projects()
			def projectsInstanceList= new Projects();
			
			def projectsService = new ProjectsService()
			def projectsList=projectsService.getProjectById(new Integer(params.id))
			projectsInstance.parent=projectsList
	        projectsInstance.properties = params
	        String subQuery ="";
	        
	        if(params.sort != null && !params.sort.equals(""))
	        	subQuery=" order by P."+params.sort
	        if(params.order != null && !params.order.equals(""))
	        	subQuery =subQuery+" "+params.order
	        
	        projectsInstanceList=projectsService.getActiveSubProjects(new Integer(params.id),subQuery)
	        def grantAllocationInstance = GrantAllocation.find("from GrantAllocation  GA where  GA.projects="+params.id);
	        return ['projectsInstance':projectsInstance,
	                'projectsInstanceList':projectsInstanceList,
	                'grantAllocationInstance':grantAllocationInstance]
		}
    }
    
    def checkforAuthrizedUserInProjectDomain =
    {
		println "checkforAuthrizedUserInProjectDomain :"+params.id
		GrailsHttpSession gh=getSession()
		def grantAllocationWithprojectsInstanceList
		def dataSecurityService = new DataSecurityService()
		def projectsService = new ProjectsService()
		if(params.id!=null)
		{           
			grantAllocationWithprojectsInstanceList=dataSecurityService.getProjectsFromGrantAllocationForLoginUser(gh.getValue("PartyID"),params.id)
			println "grantAllocationWithprojectsInstanceList :"+grantAllocationWithprojectsInstanceList
			if(grantAllocationWithprojectsInstanceList.size()==0)
			{
				flash.message = "${message(code: 'default.notAuthorizedAccess.label')}"
				return -1;
			}
			else
			{
				return 1;
			}
		}           
    }
    def search = 
    {
	GrailsHttpSession gh=getSession()
	gh.removeValue("Help")
	gh.putValue("Help","Search_Projects.htm")//putting help pages in session
        def projectsInstance = new Projects()
        //projectsInstance.properties = params
        return ['projectsInstance':projectsInstance]        	       	
    }
    def searchProjects = 
    {
		def projectsInstance = new Projects(params)
    	
    	   	
    	GrailsHttpSession gh=getSession()
    	def grantAllocationInstanceList = projectsService.searchProjects(projectsInstance,gh.getValue("Party"),params);
		def  pIMapList =[]
		def pIMapInstance
		def projectInstanceList = []
		for(int i=0;i<grantAllocationInstanceList.size();i++)
		{
			pIMapInstance=projectsService.checkPIofProject(grantAllocationInstanceList[i].projects.id)
			
			pIMapList.add(pIMapInstance)
			/* Get Closed project details */
			def projectTrackingInstanceCheck = grantAllocationService.getClosedProject(grantAllocationInstanceList[i].projects.id)
			if(projectTrackingInstanceCheck)
				grantAllocationInstanceList[i].projects.status = "Closed"
			
		}
		
		render(template:'simpleSearch',model:['grantAllocationInstanceList':grantAllocationInstanceList])  
    }
	def advancedSearchProjects = 
    {
			def investigatorService=new InvestigatorService()
			def investigatorInstanceList = investigatorService.getAllInvestigators()
			render(view:'advancedSearch',model:['investigatorInstanceList':investigatorInstanceList])  
			
    }
    def grantSearch = 
    {
			GrailsHttpSession gh=getSession()
			def partyDepartmentInstance = partyService.getPartyDepartment(gh.getValue("Party"))
			def partyInstance = partyService.getAllActiveParties()
			[partyDepartmentInstance:partyDepartmentInstance,partyInstance:partyInstance]
    }
	def grantSearchResult =
	{
			println "grant search result"
			GrailsHttpSession gh=getSession()
			def grantAllocationInstanceList = projectsService.getGrantSearchResult(params,gh.getValue("Party"))
			def  pIMapList =[]
		def pIMapInstance
		def projectInstanceList = []
		for(int i=0;i<grantAllocationInstanceList.size();i++)
		{
			pIMapInstance=projectsService.checkPIofProject(grantAllocationInstanceList[i].projects.id)
			
			pIMapList.add(pIMapInstance)
			/* Get Closed project details */
			def projectTrackingInstanceCheck = grantAllocationService.getClosedProject(grantAllocationInstanceList[i].projects.id)
			if(projectTrackingInstanceCheck)
				grantAllocationInstanceList[i].projects.status = "Closed"
			
		}
		
			render(template:'grantSearchResult',model:['grantAllocationInstanceList':grantAllocationInstanceList])  
	}
	def projectsDashBoard =
	{
		GrailsHttpSession gh = getSession()	
		gh.removeValue("Help")
		gh.putValue("Help","Project_DashBoard.htm")//putting help pages in session
	}

 	def getalert=
    {
        GrailsHttpSession gh = getSession()
        def fundTransferInstanceList=[]
        def receivedInstanceList=[]
        def fundTransferInstance
        def receivedInstance
        def fundTansInstance
        def transferInstance
        def partyInstance = Party.get(gh.getValue("Party"))
        def loginInstance =gh.getValue("LoggedIn")
        def grantAllocationInstanceList = grantAllocationService.getGrantAllocationByPartyId(partyInstance)
        for(int i=0;i<grantAllocationInstanceList.size();i++)
        {
            fundTransferInstance=fundTransferService.getFundTransferByGrantAllotId(grantAllocationInstanceList[i].id)
            for(int j=0;j<fundTransferInstance.size();j++)
            {
                transferInstance = fundTransferInstance[j]
                fundTransferInstanceList.add(transferInstance)
            }
        }
        if(fundTransferInstanceList)
        {
            for(int k=0;k<fundTransferInstanceList.size();k++)
            {
                receivedInstance = grantReceiptService.getGrantReceiptByFundTransfrId(fundTransferInstanceList[k].id)
                if(!receivedInstance)
                 {
                    receivedInstanceList.add(fundTransferInstanceList[k].grantAllocation.projects.name)
                 }
            }
        }
       if(receivedInstanceList)
       {
            if(loginInstance=='login')
			{
	             gh.putValue("LoggedIn",params.controller)
	             render receivedInstanceList as JSON
	        }
       }
   	}
   	
   	def deactivate = 
   	{
   		GrailsHttpSession gh=getSession()
		gh.removeValue("Help")
		gh.putValue("Help","Create_Role_Privileges.htm")//putting help pages in session
		def projectsInstance = Projects.findAll("from Projects P ")
		println"------projectsInstance-----------"+projectsInstance
		return[projectsInstance:projectsInstance]
   	
   	}  
   	
   	def savedeactivate =
	{
	    def projectInstance = Projects.get(params.projects.id)
	    if(projectInstance)
	    {
		   	if(projectInstance.activeYesNo == 'N' && params.activeYesNo == 'N')
		   	{
		   	   	flash.message = "${message(code: 'default.ThisProjectAlreadyDeactivated.label')}"
			   	redirect(action:deactivate)
		   	}
		   	else
		   	{
		   	   	projectInstance.createdDate =  new Date();
				projectInstance.modifiedBy = "user";
				projectInstance.createdBy = "user";
				projectInstance.projectType = projectInstance.projectType
			    projectInstance.modifiedDate =  new Date();
			    projectInstance.name = projectInstance.name
			    projectInstance.code = projectInstance.code
			   	projectInstance.activeYesNo=params.activeYesNo
			    if(projectInstance.save())
		   		{
			   		if(projectInstance.activeYesNo == 'Y')
			   		{
				   		flash.message = "${message(code: 'default.Projectactivated.label')}"
				   		redirect(action:deactivate)
			   		}
			   		else
			   		{
				   		flash.message = "${message(code: 'default.ProjectDeactivated.label')}"
				   		redirect(action:deactivate)
			   		}
		   		}
		   	}
	   	}
   	}
	
	def messageAttach = {
		
		def messageAttachmentsInstance = new MessageAttachments()
		def attachmentsService = new AttachmentsService()
        GrailsHttpSession gh=getSession()
		def projectsInstance = Projects.get(gh.getValue("ProjectID"))
        def messageAttachmentsInstanceList = []
		messageAttachmentsInstanceList =attachmentsService.getMessageAttachmentListbyProject(projectsInstance)
		
        return [messageAttachmentsInstance: messageAttachmentsInstance,
                messageAttachmentsInstanceList:messageAttachmentsInstanceList,
                projectsInstance:projectsInstance]
		
	} 
	
	def saveMessage = {
		def messageAttachmentsInstance = new MessageAttachments()
		def attachmentsName='Attachments'
		def gmsSettingsService = new GmsSettingsService()
		GrailsHttpSession gh=getSession()
		def projectsInstance = Projects.get(gh.getValue("ProjectID"))
		def userInstanse = Person.get(gh.getValue("UserId"))
		def gmsSettingsInstance = gmsSettingsService.getGmsSettings(attachmentsName)
		def webRootDir
		if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_PRODUCTION))
		{
			webRootDir = gmsSettingsInstance.value
		}
		if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_DEVELOPMENT))
		{
			webRootDir = gmsSettingsInstance.value
		}
			def downloadedfile = request.getFile("attachmentPath");
		if(!downloadedfile.empty) {
			String fileName=downloadedfile.getOriginalFilename()
			if((fileName.lastIndexOf(".EXE")==-1)&&(fileName.lastIndexOf(".exe")==-1))
			{
				downloadedfile.transferTo(new File(webRootDir+fileName))
				
				messageAttachmentsInstance.attachmentPath=fileName
				messageAttachmentsInstance.projects = projectsInstance
				messageAttachmentsInstance.viewedYesNo = 'N'
				messageAttachmentsInstance.createdBy = userInstanse.username
				messageAttachmentsInstance.createdDate = new Date()
				if (messageAttachmentsInstance.save(flush: true)) {
					flash.message = "${message(code: 'default.Fileuploaded.label')}"
				}
				else {
					println "error ="
					//redirect(action: "create", id: attachmentsInstance.domainId)//, model: [attachmentsInstance: attachmentsInstance])
				}
			}
			else
			{
				flash.message = "${message(code: 'default.ExeFile.label')}"
			}
		}
		else {
			flash.message = "${message(code: 'default.fileEmpty.label')}"
		 }
		redirect(action: "messageAttach")
	}
	
	def downloadAttachments = {
		def partyService = new PartyService()
		GrailsHttpSession gh=getSession()
		def messageAttachmentsInstance  = MessageAttachments.get( params.id )
		String fileName
		def file
		if(messageAttachmentsInstance)
		{
		def gmsSettingsService = new GmsSettingsService()
		 def attachmentsName='Attachments'
		 def gmsSettingsInstance = gmsSettingsService.getGmsSettings(attachmentsName)
		 def webRootDir
		 fileName = messageAttachmentsInstance.attachmentPath
		 def partyInstance = Party.get(gh.getValue("Party"))
		 def projectsInstance = Projects.get(messageAttachmentsInstance.projects.id)
		 def granterPartyInstance = partyService.getGranterByProjects(projectsInstance)
		  if(partyInstance == granterPartyInstance){
			 if(messageAttachmentsInstance.viewedYesNo == 'N'){
				 messageAttachmentsInstance.viewedYesNo = 'Y'
				 messageAttachmentsInstance.save()
			 }
		 }
		  if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_PRODUCTION))
		{
			 webRootDir = gmsSettingsInstance.value
		}
		if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_DEVELOPMENT))
		{
			webRootDir = gmsSettingsInstance.value
		}
		 file = new File(webRootDir+fileName)
		 def fname=file.getName()
		 
		 if (fname.indexOf(".gif")>-1) {
			 response.setContentType("image/gif");
		  } else if (fname.indexOf(".pdf")>-1) {
			 response.setContentType("application/pdf");
		  } else if (fname.indexOf(".doc")>-1) {
			 response.setContentType("application/msword");
		  }else if (fname.indexOf(".xls")>-1){
			 response.setContentType("application/vnd.ms-excel");
		  }else if (fname.indexOf(".xlsx")>-1){
			 response.setContentType("application/vnd.ms-excel");
		  }else if(fname.indexOf(".docx")>-1) {
			 response.setContentType("application/msword");
		  }else if(fname.indexOf(".ppt")>-1) {
			 response.setContentType("application/ppt");
		  }else if(fname.indexOf(".pptx")>-1) {
			 response.setContentType("application/ppt");
		  }
		 
		 response.setHeader("Content-disposition", "attachment;fileName=${file.getName()}")
		  
		 response.outputStream << file.newInputStream() // Performing a binary stream copy
		 
	}
	
	else
	{
		file=new File("nofile");
		response.setHeader("Content-disposition", "attachment;fileName=${file.getName()}")
		 
		 response.outputStream << file.newInputStream() // Performing a binary stream copy
		 
	}
	}
	
	def deleteMessageAttach = {
		def messageAttachmentsInstance  = MessageAttachments.get( params.id )
		if (messageAttachmentsInstance) {
			try {
				messageAttachmentsInstance.delete(flush: true)
				flash.message = "${message(code: 'default.deleted.label')}"
				redirect(action: "messageAttach")
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "${message(code: 'default.Fileinuse.label')}"
				redirect(action: "messageAttach")
			}
		}
		else {
			flash.message = "${message(code: 'default.FilenotFound.label')}"
			redirect(action: "messageAttach")
		}
	}
	
	def messagesList = {
		def grantAllocationService = new GrantAllocationService()
		def attachmentsService = new AttachmentsService()
		def messageAttachmentsInstanceList = []
		GrailsHttpSession gh=getSession()
		def partyInstance = Party.get(gh.getValue("Party"))
		def grantAllocationInstanceList = grantAllocationService.getGrantAllocationByGranterIdGroupByProjects(partyInstance.id)
		if(grantAllocationInstanceList){
			messageAttachmentsInstanceList = attachmentsService.getMessageAttachmentListbyGrantAllocation(grantAllocationInstanceList)
		}
		return ['messageAttachmentsInstanceList':messageAttachmentsInstanceList]
	}
	
	
}