import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import org.codehaus.groovy.grails.commons.GrailsApplication
import grails.util.GrailsUtil
import java.text.SimpleDateFormat
import java.text.*;
import java.util.*;

class NotificationController {
	def grantAllocationService
	def notificationService
	def projectsService
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

  
    def list = {
    println "---------------chk--------------"
			params.max = Math.min(params.max ? params.int('max') : 10, 100)
            if(!params.max) params.max = 10
            String subQuery ="";
           GrailsHttpSession gh=getSession()
           gh.removeValue("Help")
       		//putting help pages in session
       		gh.putValue("Help","Notification_List.htm")
            if(params.sort != null && !params.sort.equals(""))
            	subQuery=" order by N."+params.sort
            if(params.order != null && !params.order.equals(""))
            	subQuery =subQuery+" "+params.order
            
        	def notificationInstanceList
        	
        	//def dataSecurityService = new DataSecurityService()
            def notificationService = new NotificationService()
        	params.offset = (params.offset ? params.int('offset') : 0)
        
        	notificationInstanceList=notificationService.getAllNotifications(subQuery,gh.getValue("Party"))
        	 // For Pagination
        	def total=notificationInstanceList.size()
        	if(total)
			{
        	def max = projectsService.findUpperIndex(params.offset,params.max,total)
        	notificationInstanceList = notificationInstanceList.getAt(params.offset..max)
			}
        	def notificationsEmailsInstanceList = []
        	for(int i=0;i<notificationInstanceList.size();i++)
	        {
        		def notificationsEmailInstance = NotificationsEmails.findAll("from NotificationsEmails NE where NE.notification.id="+notificationInstanceList[i].id)
        		notificationsEmailsInstanceList.add(notificationsEmailInstance)
        		
	        }
        	
    		
            [ notificationInstanceList: notificationInstanceList,notificationsEmailsInstanceList:notificationsEmailsInstanceList,'notificationInstanceListTotal': total,offset:params.offset]
        }
    
    def show = {
        def notificationInstance = Notification.get( params.id )

        if(!notificationInstance) {
            flash.message = "${message(code: 'default.FilenotFound.label')}"
            redirect(action:list)
        }
        else { return [ notificationInstance : notificationInstance ] }
    }

    def delete = {
        def notificationInstance = Notification.get( params.id )
        if(notificationInstance) {
           if(notificationInstance.delete())
           {
            flash.message = "${message(code: 'default.deleted.label')}"
            redirect(action:list)
           }
           else{flash.message = "${message(code: 'default.Fileinuse.label')}"
               redirect(action:list)}
        }
        else {
            flash.message = "${message(code: 'default.FilenotFound.label')}"
            redirect(action:list)
        }
    }

    def edit = {
        def notificationInstance = Notification.get( params.id )

        if(!notificationInstance) {
            flash.message = "${message(code: 'default.FilenotFound.label')}"
            redirect(action:list)
        }
        else {
        	NumberFormat formatter = new DecimalFormat("#0.00");
            return [ 'notificationInstance' : notificationInstance,'notificationAmount':formatter.format(notificationInstance.amount) ]
        }
    }

    def update = {
		 def notificationInstance = Notification.get( params.id )
        if(notificationInstance) {
        	
        	notificationInstance.properties = params
          
            /*uploading application form*/
         /* if(request.getFile('myFile'))
            {
            def downloadedfile = request.getFile('myFile')
    		def attachmentsName='ApplicationForm'
			 def gmsSettingsService = new GmsSettingsService()
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
   	        	/*if(!downloadedfile.empty)
   	        	{
   	        		String fileName=downloadedfile.getOriginalFilename().toString().substring(0,downloadedfile.getOriginalFilename().toString().indexOf("."))
   	        		//String fileName=downloadedfile.getOriginalFilename()
   	        		notificationInstance.applicationForm=fileName+".gsp"
	    		
   	        		new File( webRootDir ).mkdirs()
   	        		downloadedfile.transferTo( new File( webRootDir + File.separatorChar + fileName+".gsp") )
   	        	}
            }*/
            if(!notificationInstance.hasErrors() && notificationInstance.save()) {
                flash.message = flash.message = "${message(code: 'default.updated.label')}"
                redirect(action:list,id:notificationInstance.id)
            }
        
            else {
                render(view:'edit',model:[notificationInstance:notificationInstance])
            }
        }
       
        else {
            flash.message = "${message(code: 'default.FilenotFound.label')}"
            redirect(action:edit,id:params.id)
        }
    }

    def upload = {
        	
        		 def notificationInstance = Notification.get( params.id )
        		   return ['notificationInstance':notificationInstance]
        		
        }
        def savefile = {
        		
        		def notificationInstance = new Notification(params)
        		def downloadedfile = request.getFile('file');
        		//String filename=downloadedfile.getOriginalFilename().toString().substring(0,downloadedfile.getOriginalFilename().toString().lastIndexOf("."))
        		String filename=downloadedfile.getOriginalFilename()
        	     downloadedfile.transferTo(new File('c:/somefolder/'+filename))
               
        		
                   redirect(action:list,id:notificationInstance.id)
        		
        }
    def create = {
        def notificationInstance = new Notification()
        GrailsHttpSession gh=getSession()
        gh.removeValue("Help")
    		//putting help pages in session
    	gh.putValue("Help","Create_Notification.htm")
        notificationInstance.properties = params
 		   //def grantAllocationWithprojectsInstanceList
		def dataSecurityService = new DataSecurityService()
        List<GrantAllocation> grantAllocationWithprojectsInstanceList 	
		try{
			grantAllocationWithprojectsInstanceList = grantAllocationService.getGrantAllocationByActiveProjects()
		   }
    	catch(Exception e)
    	{
    	}	
    	def grantAllocatedprojectsList =[]
        def closedProjectList = [] 
    	def closedProjectInstanceList = []
        def closedProjectInstance
        def projectsService = new ProjectsService()
       for(int i=0;i<grantAllocationWithprojectsInstanceList.projects.size();i++)
            {
    	      closedProjectInstance =projectsService.getClosedProjects(grantAllocationWithprojectsInstanceList[i].projects.id )
 	          if(closedProjectInstance) 
    	    	   closedProjectList.add(closedProjectInstance)
            }
       for(int i=0;i<grantAllocationWithprojectsInstanceList.projects.size();i++)
       {
      	grantAllocatedprojectsList.add(grantAllocationWithprojectsInstanceList[i].projects )

       }
       for(int i=0;i<closedProjectList.projects.size();i++)
       {
             
        	closedProjectInstanceList.add(closedProjectList[i].projects)
       }
     grantAllocatedprojectsList.removeAll(closedProjectInstanceList)
         return ['notificationInstance':notificationInstance,
                'grantAllocationWithprojectsInstanceList':grantAllocatedprojectsList]
    }

    def save={	
       def notificationInstance = new Notification(params)
      
       def notificationService = new NotificationService()
       def approvalAuthorityService = new ApprovalAuthorityService()
       def proposalApprovalAuthorityMapService = new ProposalApprovalAuthorityMapService()
       //GrailsHttpSession gh=getSession()
       
       //println"notificationInstanceList.notificationCode"+notificationInstanceList.notificationCode
      def chkUniNotCodeInstance = notificationService.getNotificationCode(notificationInstance)
        if(chkUniNotCodeInstance)
       {
    	   flash.message = "${message(code: 'default.NotDuplicate.message')}"
    	   redirect(action:create,id:notificationInstance.id)
       }
       else
       {
    	   def dataSecurityService = new DataSecurityService()
       		List<GrantAllocation> grantAllocationWithprojectsInstanceList 	
       		try{
			grantAllocationWithprojectsInstanceList = grantAllocationService.getGrantAllocationByActiveProjects()
       		}
   			catch(Exception e)
   			{
   		
   			}
       
   			/*def downloadedfile = request.getFile('myFile')
    		def attachmentsName='ApplicationForm'
			 def gmsSettingsService = new GmsSettingsService()
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
    		if(!downloadedfile.empty)
    		{
	    		String fileName=downloadedfile.getOriginalFilename().toString().substring(0,downloadedfile.getOriginalFilename().toString().indexOf("."))
	    		//String fileName=downloadedfile.getOriginalFilename()
	    		notificationInstance.applicationForm=fileName+".gsp"
	    		
	    		new File( webRootDir+"proposalApplication/" ).mkdirs()
	    		downloadedfile.transferTo( new File( webRootDir + File.separatorChar + fileName+".gsp") )
    		}*/
    		if(!notificationInstance.hasErrors() && notificationInstance.save()) 
    		{
    			GrailsHttpSession gh=getSession()
    			def chkDefaultAuthorityInstance = approvalAuthorityService.chkDefaultAuthority(gh.getValue("PartyID"))
    			
    			if(chkDefaultAuthorityInstance)
    			{
    				
    				def approvalAuthorityInstance = approvalAuthorityService.getApprovalAuthorityById((Integer)chkDefaultAuthorityInstance[0].id)
    				def proposalApprovalAuthorityMapInstance = proposalApprovalAuthorityMapService.saveProposalApprovalAuthorityMapForProposalApplication(approvalAuthorityInstance,notificationInstance.id,"Notification")
    				
    			}
            flash.message = "${message(code: 'default.created.label')}"
            	
            redirect(action:list,id:notificationInstance.id)
    		}
    		else
    		{
            render(view:'create',model:[notificationInstance:notificationInstance,grantAllocationWithprojectsInstanceList:grantAllocationWithprojectsInstanceList])
    		}
       }
    }


    def download = 
       {
    		def notificationInstance = Notification.get( params.id )
    		String fileName = notificationInstance.eligibilitydocument
    		def file = new File('c:/somefolder/'+fileName)     
    		response.setContentType("application/octet-stream") 
    		response.setHeader("Content-disposition", "attachment;fileName=${file.getName()}") 
    		 
    		response.outputStream << file.newInputStream() // Performing a binary stream copy 

    		
    }
	def downloadApplicationForm = 
    {
 		def notificationInstance = Notification.get( params.id )
 		
 		String fileName = notificationInstance.applicationForm
 		def htmlFile = fileName.toString().substring(0,fileName.toString().lastIndexOf('.'))+".htm"
 		def attachmentsName='ApplicationForm'
		def gmsSettingsService = new GmsSettingsService()
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
        def srcFile = new File(webRootDir+fileName)
        def file = new File(webRootDir+htmlFile)   
 		if(srcFile.exists())
 		{
 			boolean exists = file.exists();
 			if(exists==false)
 			{
 				org.apache.commons.io.FileUtils.copyFile(srcFile, file)
 			}
 			//response.setContentType("application/octet-stream") 
 			response.setContentType("text/htm");
 			response.setHeader("Content-disposition", "attachment;fileName=${file.getName()}") 
 		 
 			response.outputStream << file.newInputStream() // Performing a binary stream copy 
 		}
 		else
 		{
 			flash.message ="File does not exist"
			redirect(controller:'notification',action:'list')
 		}
 		
 }
    def publishNotification =
    {
    		def notificationsInstance = Notification.get(params.id)
    		def notificationsAttachmentsInstance = NotificationsAttachments.findAll("from NotificationsAttachments NA where NA.notification.id="+params.id)
    		def notificationsEmailsInstance = NotificationsEmails.findAll("from NotificationsEmails NE where NE.notification.id="+params.id)
    		[notificationsInstance:notificationsInstance,
    		 notificationsAttachmentsInstance:notificationsAttachmentsInstance,
    		 notificationsEmailsInstance:notificationsEmailsInstance]
    		
    }
	def listReport = {
    		
    		return['reportListInstance':params]
    		
    }
	
	def granteeReports=
	{
			GrailsHttpSession gh=getSession()
			def partyInstance=Party.get(gh.getValue("Party"))
			return['partyInstance':partyInstance]
	}
	def publish = {
    		
    		def notificationInstance = Notification.get(params.id)
    		def proposalApprovalAuthorityMapService = new ProposalApprovalAuthorityMapService()
    		def proposalApprovalAuthorityMapInstance = proposalApprovalAuthorityMapService.getProposalApprovalAuthorityMapByProposalIdAndType(params.id,"Notification")
    		if(proposalApprovalAuthorityMapInstance != null)
    		{
    		notificationInstance.publishYesNo="Y"
    		def notificationInstanceSaveStatus=notificationService.updateNotification(notificationInstance)
    		if(notificationInstanceSaveStatus)
    		{
    			flash.message="${message(code: 'default.NotificationPublished.label')}"
    	    	redirect(controller:"notification",action:"list")
    		}
    		else
    		{	
    			redirect(controller:"notification",action:"list")
    		}
    		}
    		else
    		{
    			flash.message ="${message(code: 'default.NotificationNotPublished.label')}"
    			redirect(controller:"notification",action:"list")
    		}
    }
	
		def proposalFilter = {
			def gmsSettingsService = new GmsSettingsService()
			def days = gmsSettingsService.getGmsSettingsValue("limit_days")
			println "days"+days
			return [ days : days]
		}

		def proposalFilterSave = {
			def gmsSettingsInstance = GmsSettings.find( "from GmsSettings GS where 	GS.name='limit_days'");
			gmsSettingsInstance.value=params.proposalFilter
			if(gmsSettingsInstance.save()){
				 flash.message="${message(code: 'default.Modified.label')}"
				 
			}
			else{
				flash.message="${message(code: 'Failed!')}"
			}
			redirect(controller:"notification",action:"proposalFilter")
		}

		def notificationAuthorityMap = {	
			def proposalApprovalAuthorityMapService = new ProposalApprovalAuthorityMapService()
			def approvalAuthorityService = new ApprovalAuthorityService()
			GrailsHttpSession gh=getSession()
			def partyId = gh.getValue("Party")
			def approvalAuthorityInstance = approvalAuthorityService.getApprovalAuthorityByParty(partyId)
			def currentApprovalAuthority = proposalApprovalAuthorityMapService.getProposalApprovalAuthorityMapByProposalIdAndType(params.id,"Notification")
			['approvalAuthorityInstance': approvalAuthorityInstance,'proposalId':params.id,'currentApprovalAuthority':currentApprovalAuthority]
	}
	def saveNotificationAuthorityMap = {
			def proposalApprovalAuthorityMapService = new ProposalApprovalAuthorityMapService()
			def approvalAuthorityService = new ApprovalAuthorityService()
			if(params.approvalAuthorityId != 'null')
			{
			def approvalAuthorityInstance = approvalAuthorityService.getApprovalAuthorityById(Integer.parseInt(params.approvalAuthorityId))
			def proposalApprovalAuthorityMapInstance = proposalApprovalAuthorityMapService.saveProposalApprovalAuthorityMapForProposalApplication(approvalAuthorityInstance,Integer.parseInt(params.proposalId),"Notification")
			flash.message="${message(code: 'default.NotificationApprovalAuthorityMapCreated.label')}"
			redirect(controller:"notification",action:"list")
			}
			else
			{
			redirect(controller:"notification",action:"notificationAuthorityMap")	
			}
	}
}

