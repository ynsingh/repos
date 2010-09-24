import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import org.codehaus.groovy.grails.commons.GrailsApplication
import grails.util.GrailsUtil

class NotificationController {
	def grantAllocationService
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

  
    def list = {
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
        	println"++++++notparams++++++"+params
        	notificationInstanceList=notificationService.getAllNotifications(subQuery,gh.getValue("Party"))
        	
            [ notificationInstanceList: notificationInstanceList ]
        }
    
    def show = {
        def notificationInstance = Notification.get( params.id )

        if(!notificationInstance) {
            flash.message = "Notification not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ notificationInstance : notificationInstance ] }
    }

    def delete = {
        def notificationInstance = Notification.get( params.id )
        if(notificationInstance) {
           if(notificationInstance.delete())
           {
            flash.message = "Notification deleted"
            redirect(action:list)
           }
           else{flash.message = "Notification could not delete"
               redirect(action:list)}
        }
        else {
            flash.message = "Notification not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def notificationInstance = Notification.get( params.id )

        if(!notificationInstance) {
            flash.message = "Notification not found"
            redirect(action:list)
        }
        else {
            return [ notificationInstance : notificationInstance ]
        }
    }

    def update = {
			println "--------------"+params
        def notificationInstance = Notification.get( params.id )
        if(notificationInstance) {
        	def notificationEmailInstance = NotificationsEmails.find("from NotificationsEmails where notification.id="+params.id)
           if(!notificationEmailInstance)
           {
        	notificationInstance.properties = params
          
            /*uploading application form*/
            if(request.getFile('myFile'))
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
   	        	if(!downloadedfile.empty)
   	        	{
   	        		String fileName=downloadedfile.getOriginalFilename().toString().substring(0,downloadedfile.getOriginalFilename().toString().indexOf("."))
   	        		//String fileName=downloadedfile.getOriginalFilename()
   	        		notificationInstance.applicationForm=fileName+".gsp"
	    		
   	        		new File( webRootDir ).mkdirs()
   	        		downloadedfile.transferTo( new File( webRootDir + File.separatorChar + fileName+".gsp") )
   	        	}
            }
            if(!notificationInstance.hasErrors() && notificationInstance.save()) {
                flash.message = "Notification updated"
                redirect(action:list,id:notificationInstance.id)
            }
            else {
                render(view:'edit',model:[notificationInstance:notificationInstance])
            }
           } else {
               flash.message = "Notification already published so can not update"
                   redirect(action:edit,id:params.id)
               }
        	}
        else {
            flash.message = "Notification not found"
            redirect(action:edit,id:params.id)
        }
    }

    def upload = {
        	
        		 def notificationInstance = Notification.get( params.id )
        		 print"+++++++++++++++++++id+++++++++++++"
                return ['notificationInstance':notificationInstance]
        		
        }
        def savefile = {
        		
        		print "+++++++++++++++save++++++++++++++++++++"
        		def notificationInstance = new Notification(params)
        		println "Notification+"+notificationInstance
        		
        		def downloadedfile = request.getFile('file');
        		//String filename=downloadedfile.getOriginalFilename().toString().substring(0,downloadedfile.getOriginalFilename().toString().lastIndexOf("."))
        		String filename=downloadedfile.getOriginalFilename()
        		println "File Name--"+filename
        		
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
			grantAllocationWithprojectsInstanceList = grantAllocationService.getAll()
		}
    	catch(Exception e)
    	{
    		
    	}
    	println "grantAllocationWithprojectsInstanceList "+grantAllocationWithprojectsInstanceList
        //grantAllocationWithprojectsInstanceList=dataSecurityService.getProjectsFromGrantAllocationForLoginUser(gh.getValue("PartyID"))
      
        return ['notificationInstance':notificationInstance,'grantAllocationWithprojectsInstanceList':grantAllocationWithprojectsInstanceList]
    }

    def save={	
       print "+++++++++++++++save++++++++++++++++++++"
    		def notificationInstance = new Notification(params)
    		
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
    		if(!downloadedfile.empty)
    		{
	    		String fileName=downloadedfile.getOriginalFilename().toString().substring(0,downloadedfile.getOriginalFilename().toString().indexOf("."))
	    		//String fileName=downloadedfile.getOriginalFilename()
	    		notificationInstance.applicationForm=fileName+".gsp"
	    		
	    		new File( webRootDir+"proposalApplication/" ).mkdirs()
	    		downloadedfile.transferTo( new File( webRootDir + File.separatorChar + fileName+".gsp") )
    		}
        if(!notificationInstance.hasErrors() && notificationInstance.save()) {
            flash.message = "Notification created"
            	
          redirect(action:list,id:notificationInstance.id)
        }
        else {
            render(view:'create',model:[notificationInstance:notificationInstance])
        }
    }


    def download = 
       {
    		def notificationInstance = Notification.get( params.id )
    		println"++++++++++++++id+++++++++++"+params
    		String fileName = notificationInstance.eligibilitydocument
    		println"++++++++++++++++++filename+++++++++"+fileName
    		def file = new File('c:/somefolder/'+fileName)     
    		response.setContentType("application/octet-stream") 
    		response.setHeader("Content-disposition", "attachment;fileName=${file.getName()}") 
    		 
    		response.outputStream << file.newInputStream() // Performing a binary stream copy 

    		
    }
	def downloadApplicationForm = 
    {
 		def notificationInstance = Notification.get( params.id )
 		
 		String fileName = notificationInstance.applicationForm
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
 		def file = new File(webRootDir+fileName)    
 		response.setContentType("application/octet-stream") 
 		response.setHeader("Content-disposition", "attachment;fileName=${file.getName()}") 
 		 
 		response.outputStream << file.newInputStream() // Performing a binary stream copy 

 		
 }
    def publishNotification =
    {
    		println "publish-=-=-=-=-"+params
    		def notificationsInstance = Notification.get(params.id)
    		def notificationsAttachmentsInstance = NotificationsAttachments.findAll("from NotificationsAttachments NA where NA.notification.id="+params.id)
    		def notificationsEmailsInstance = NotificationsEmails.findAll("from NotificationsEmails NE where NE.notification.id="+params.id)
    		[notificationsInstance:notificationsInstance,notificationsAttachmentsInstance:notificationsAttachmentsInstance,notificationsEmailsInstance:notificationsEmailsInstance]
    		
    }
	def listReport = {
    		println "+++++++++++++++++"+params
        	
    		return['reportListInstance':params]
    		
    }
	
	def granteeReports=
	{
			println"params"+params
			GrailsHttpSession gh=getSession()
			def partyInstance=Party.get(gh.getValue("Party"))
			println"partyInstance"+partyInstance
			return['partyInstance':partyInstance]
	}
}

