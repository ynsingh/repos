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
            flash.message = "${message(code: 'default.FilenotFound.label')}"
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
            flash.message = "${message(code: 'default.deleted.label')}"
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
            flash.message = "${message(code: 'default.inuse.label')}"
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
            flash.message = "${message(code: 'default.FilenotFound.label')}"
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
                flash.message = "${message(code: 'default.updated.label')}"
                redirect(action:show,id:notificationsAttachmentsInstance.id)
            }
            else 
            {
                render(view:'edit',model:[notificationsAttachmentsInstance:notificationsAttachmentsInstance])
            }
        }
        else 
        {
            flash.message = "${message(code: 'default.FilenotFound.label')}"
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
        println "++++++++++Active Yes No++++++"+ params.activeYesNo
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

        return ['notificationsAttachmentsInstance':notificationsAttachmentsInstance,
                'notificationsAttachmentsInstanceList':notificationsAttachmentsList]
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
			            if((fileName.lastIndexOf(".EXE")==-1)&&(fileName.lastIndexOf(".exe")==-1))
			            
			            {
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
			            }
			    		new File( webRootDir).mkdirs()
		            	downloadedfile.transferTo( new File( webRootDir + File.separatorChar + fileName) )
		            	//notificationsAttachmentsInstance.attachmentType=attachmentTypeInstance
		            	//notificationsAttachmentsInstance.attachmentType=attachmentTypeInstance		                 
		            	notificationsAttachmentsInstance.save()
			            flash.message = "${message(code: 'default.Fileuploaded.label')}"
			            	 
			          
			            	 params.clear()
			            // MultipartRequestHolder.setMultipartRequest(null)
			            
			            if(documentTypefromParam == 'Proposal')
			            {
			            	//def notid = Proposal.find("from Proposal P where P.")
			            	redirect(controller:"notificationsEmails",action:"partyNotificationsList")
			            }
			            else
			            {
			            	redirect(controller:"notification",action:"list",id:notificationsAttachmentsInstance.notification.id)
			            	//redirect(action:create,id:notificationsAttachmentsInstance.notification.id,params:[documentType:documentTypefromParam])
			            }

			            }
			            else 
			            {
			            	flash.message = "${message(code: 'default.ExeFile.label')}"
				            redirect(action:create,id:notificationsAttachmentsInstance.notification.id,params:[documentType:documentTypefromParam])
			            }
		            }
		            else
		            {
		            	params.clear()
		            	//MultipartRequestHolder.setMultipartRequest(null)
		            	flash.message = "${message(code: 'default.SelectFile.label')}"	
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
}       
           
