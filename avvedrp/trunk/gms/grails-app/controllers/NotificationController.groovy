import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession

class NotificationController {
    
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
            flash.message = "Notification ${notificationInstance.notificationCode} deleted"
            redirect(action:list)
           }
           else{flash.message = "Notification ${notificationInstance.notificationCode} could not delete"
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
            flash.message = "Notification not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ notificationInstance : notificationInstance ]
        }
    }

    def update = {
        def notificationInstance = Notification.get( params.id )
        if(notificationInstance) {
            notificationInstance.properties = params
            if(!notificationInstance.hasErrors() && notificationInstance.save()) {
                flash.message = "Notification ${notificationInstance.notificationCode} updated"
                redirect(action:list,id:notificationInstance.id)
            }
            else {
                render(view:'edit',model:[notificationInstance:notificationInstance])
            }
        }
        else {
            flash.message = "Notification not found with id ${params.id}"
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
       
		def grantAllocationWithprojectsInstanceList
		def dataSecurityService = new DataSecurityService()
        grantAllocationWithprojectsInstanceList=dataSecurityService.getProjectsFromGrantAllocationForLoginUser(gh.getValue("PartyID"))
      
        return ['notificationInstance':notificationInstance,'grantAllocationWithprojectsInstanceList':grantAllocationWithprojectsInstanceList]
    }

    def save={	
       print "+++++++++++++++save++++++++++++++++++++"
    		def notificationInstance = new Notification(params)
    		println "Notification+"+notificationInstance
    		def downloadedfile = request.getFile('myFile')
    		println "downloadedfile = +"+downloadedfile
    		if(!downloadedfile.empty)
    		{
    		println "downloadedfile="+downloadedfile
    		String fileName=downloadedfile.getOriginalFilename().toString().substring(0,downloadedfile.getOriginalFilename().toString().indexOf("."))
    		//String fileName=downloadedfile.getOriginalFilename()
    		notificationInstance.applicationForm=fileName+".gsp"
    		println "fileName"+fileName
    		new File( "grails-app/views/applicationDocs/" ).mkdirs()
    		downloadedfile.transferTo( new File( "grails-app/views/applicationDocs/" + File.separatorChar + fileName+".gsp") )
        	}
        if(!notificationInstance.hasErrors() && notificationInstance.save()) {
            flash.message = "Notification ${notificationInstance.notificationCode} created"
            	
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
}

