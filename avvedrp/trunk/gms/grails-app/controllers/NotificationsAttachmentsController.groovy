import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import org.codehaus.groovy.grails.commons.GrailsApplication
import grails.util.GrailsUtil
class NotificationsAttachmentsController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list =
    {
        if(!params.max) params.max = 10
        [ notificationsAttachmentsInstanceList: NotificationsAttachments.list( params ) ]
    }

    def show = 
    {
        def notificationsAttachmentsInstance = NotificationsAttachments.get( params.id )

        if(!notificationsAttachmentsInstance) 
        {
            flash.message = "NotificationsAttachments not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ notificationsAttachmentsInstance : notificationsAttachmentsInstance ] }
    }

    def delete = 
    {
        def notificationsAttachmentsInstance = NotificationsAttachments.get( params.id )
        println "+++++++++++++++++++params+++++++++++++++++++" +params
        if(notificationsAttachmentsInstance)
        {
            notificationsAttachmentsInstance.delete()
            flash.message = "The Record is deleted Succesfully"
                println "+++++++++++++++++++params+++++++++++++++++++" +params
                if(params.documentType == 'Proposal')
                {
                	redirect(action:create,id:notificationsAttachmentsInstance.proposal.id,params:[documentType:params.documentType])
                }
                else
                {
                	redirect(action:create,id:notificationsAttachmentsInstance.notification.id,params:[documentType:params.documentType])

                }
        }
        else 
        {
            flash.message = "The Record could not deleted"
            if(params.documentType == 'Proposal')
            {
            redirect(action:create,id:notificationsAttachmentsInstance.proposal.id,params:[documentType:params.documentType])
        
            }
            else
            {
                redirect(action:create,id:notificationsAttachmentsInstance.notification.id,params:[documentType:params.documentType])

            }
        }
    }

    def edit = 
    {
        def notificationsAttachmentsInstance = NotificationsAttachments.get( params.id )

        if(!notificationsAttachmentsInstance) 
        {
            flash.message = "NotificationsAttachments not found with id ${params.id}"
            redirect(action:list)
        }
        else 
        {
            return [ notificationsAttachmentsInstance : notificationsAttachmentsInstance ]
        }
    }

    def update = 
    {
        def notificationsAttachmentsInstance = NotificationsAttachments.get( params.id )
        if(notificationsAttachmentsInstance) 
        {
            notificationsAttachmentsInstance.properties = params
            if(!notificationsAttachmentsInstance.hasErrors() && notificationsAttachmentsInstance.save())
            {
                flash.message = "NotificationsAttachments ${params.id} updated"
                redirect(action:show,id:notificationsAttachmentsInstance.id)
            }
            else 
            {
                render(view:'edit',model:[notificationsAttachmentsInstance:notificationsAttachmentsInstance])
            }
        }
        else 
        {
            flash.message = "NotificationsAttachments not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = 
    {
    		println "noti create"
    		//MultipartRequestHolder.setMultipartRequest(null)
        def notificationsAttachmentsService = new NotificationsAttachmentsService();	
        def notificationsAttachmentsInstance = new NotificationsAttachments()
        notificationsAttachmentsInstance.properties = params
        println "++++++++++documentType++++++"
        println "++++++++++documentType++++++"+ params.documentType
        if(params.documentType == 'Proposal')
        {
        	notificationsAttachmentsInstance.proposal=Proposal.get(params.id)
        	notificationsAttachmentsInstance.notification=notificationsAttachmentsInstance.proposal.notification
        	println "noitficatonid="+notificationsAttachmentsInstance.notification
        	
        }
        else
        {
        notificationsAttachmentsInstance.notification=Notification.get(params.id)
        
        }
        println "create Notid="+notificationsAttachmentsInstance.notification+"=paramsid="+params.id
        println "+++++++++++notificationsAttachmentsInstance.attachmentType+++++++++++"+ notificationsAttachmentsInstance.attachmentType
        println "proposal Id "+params.id
        def notificationsAttachmentsList=notificationsAttachmentsService.getNotificationsAttachmentsByNotification(params)
        println "+++++++++++notificationsAttachmentsList+++++++++++"+ notificationsAttachmentsList

        return ['notificationsAttachmentsInstance':notificationsAttachmentsInstance,'notificationsAttachmentsInstanceList':notificationsAttachmentsList]
    }

    def save = 
    {
    		// def attachmentTypeInstance = AttachmentType.get(params.attachmentType.id)	
        def notificationsAttachmentsInstance = new NotificationsAttachments(params)
        def notificationsAttachmentsService = new NotificationsAttachmentsService();
       
        //println "attachment="+attachmentTypeInstance
        
        
	        if(!notificationsAttachmentsInstance.hasErrors() )
	        {
	        	println "params"+params
	        	println "notiiii"+notificationsAttachmentsInstance.notification;
	        	
	        	notificationsAttachmentsInstance.notification=Notification.get(params.notificationId)
	        	println "notificationbbb=="+notificationsAttachmentsInstance.notification;
	        	if(params.documentType == 'Proposal')
	        	{
	        		notificationsAttachmentsInstance.proposal=Proposal.get(params.proposalId)
	        	}
	        	else
	        	{
	        		notificationsAttachmentsInstance.proposal=null
	        	}
 	        	//notificationsAttachmentsInstance.attachmentType=AttachmentType.get(params.attachmentType.id)
			
				//println "notificationisd===="+notificationsAttachmentsInstance.proposal.notification.id
				//notificationsAttachmentsInstance.notification=notificationsAttachmentsInstance.proposal.notification
				def downloadedfile = request.getFile('myfile');
		        Integer notificationId =notificationsAttachmentsService
											.getNotificationsAttachmentsInstance(downloadedfile,
													params)
					def documentTypefromParam=params.documentType								
												
			    if(notificationId)
		        {
			    	
	            	 
		        	flash.message = "File already uploaded"	
		        		//MultipartRequestHolder.setMultipartRequest(null)
		        		if(documentTypefromParam == 'Proposal')
		        		{
		        			params.clear()
		        			redirect(action:create,id:notificationsAttachmentsInstance.proposal.id,params:[documentType:documentTypefromParam])  
		        		}
		        		else
		        		{
		        			params.clear()
		        			redirect(action:create,id:notificationsAttachmentsInstance.notification.id,params:[documentType:documentTypefromParam])  

		        		}
		        }
		        else
		        {
		        		           
		            if(!downloadedfile.empty)
		            {
            	
		            	
			            String fileName=downloadedfile.getOriginalFilename()
			    		println "File Name--"+fileName+"  attachmenttype "+notificationsAttachmentsInstance.attachmentType
			    		notificationsAttachmentsInstance.attachmentPath=fileName
			    		def attachmentsName='Attachments'
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
			            	println "gmsSettingsInstance.value"+gmsSettingsInstance.value
			            }
			    		println "System.getProperty"+System.getProperty("user.home")
			            new File( webRootDir).mkdirs()
		            	downloadedfile.transferTo( new File( webRootDir + File.separatorChar + fileName) )
		            	//notificationsAttachmentsInstance.attachmentType=attachmentTypeInstance
		            	println "notificationsAttachmentsInstance" +notificationsAttachmentsInstance.attachmentPath
		            	println "File Name--"+notificationsAttachmentsInstance.attachmentPath
		            	println "notificationsAttachmentsInstance********" +notificationsAttachmentsInstance
		            	//notificationsAttachmentsInstance.attachmentType=attachmentTypeInstance		                 
		            	notificationsAttachmentsInstance.save()
			            flash.message = "File Uploaded Successfully"
			            	 println "notificationsAttachmentsInstance44" +notificationsAttachmentsInstance
			          
			            	 params.clear()
			            println "documentTypefromParam"+documentTypefromParam
			            println "documentTypefromParam"+params
			           // MultipartRequestHolder.setMultipartRequest(null)
			            
			            if(documentTypefromParam == 'Proposal')
			            {
			            	//def notid = Proposal.find("from Proposal P where P.")
			            	println "proposalId="+notificationsAttachmentsInstance.notification.id
			            	redirect(controller:"proposal",action:"create",id:notificationsAttachmentsInstance.proposal.notification.id)
			            }
			            else
			            {
			            	redirect(controller:"notification",action:"list",id:notificationsAttachmentsInstance.notification.id)
			            	//redirect(action:create,id:notificationsAttachmentsInstance.notification.id,params:[documentType:documentTypefromParam])
			            }

		           
		            }
		            else
		            {
		            	params.clear()
		            	//MultipartRequestHolder.setMultipartRequest(null)
		            	flash.message = "please select file to be uploaded"	
		            	redirect(action:create,id:notificationsAttachmentsInstance.notification.id,params:[documentType:documentTypefromParam])
		            }
		        }
	        
	        }
	        
	        else
	        {
	        	render(view:'create',model:[notificationsAttachmentsInstance:notificationsAttachmentsInstance])
	        }
     }
    
    def download = 
    {
 		def notificationsAttachmentsInstance = NotificationsAttachments.get( params.id )
 		
 		String fileName = notificationsAttachmentsInstance.attachmentPath
 		
 		def attachmentsName='Attachments'
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
}       
           
