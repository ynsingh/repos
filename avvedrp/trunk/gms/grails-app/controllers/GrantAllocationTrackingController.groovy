import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession

class GrantAllocationTrackingController {
	def grantAllocationService
    def checkListService
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ grantAllocationTrackingInstanceList: GrantAllocationTracking.list( params ) ]
    }

    def show = {
        def grantAllocationTrackingInstance = GrantAllocationTracking.get( params.id )

        if(!grantAllocationTrackingInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
        else { return [ grantAllocationTrackingInstance : grantAllocationTrackingInstance ] }
    }

    def delete = {
        def grantAllocationTrackingInstance = GrantAllocationTracking.get( params.id )
        if(grantAllocationTrackingInstance) {
            grantAllocationTrackingInstance.delete()
            flash.message = "${message(code: 'default.deleted.label')}"
            redirect(action:list)
        }
        else {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
    }

    def edit = {
        def grantAllocationTrackingInstance = GrantAllocationTracking.get( params.id )

        if(!grantAllocationTrackingInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
        else {
            return [ grantAllocationTrackingInstance : grantAllocationTrackingInstance ]
        }
    }

    def update = {
        def grantAllocationTrackingInstance = GrantAllocationTracking.get( params.id )
        if(grantAllocationTrackingInstance) {
            grantAllocationTrackingInstance.properties = params
            if(!grantAllocationTrackingInstance.hasErrors() && grantAllocationTrackingInstance.save()) {
                flash.message = "${message(code: 'default.updated.label')}"
                redirect(action:create,id:grantAllocationTrackingInstance.grantAllocation.id)
            }
            else {
               // render(view:'create',model:[grantAllocationTrackingInstance:grantAllocationTrackingInstance])
               redirect(action:create,id:grantAllocationTrackingInstance.grantAllocation.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.notfond.label')}"
        	redirect(action:create,id:params.grantAllocation.id)
        }
    }

    def create = {
   		GrailsHttpSession gh=getSession()
		gh.removeValue("Help")
		gh.putValue("Help","GrantWithdrawl_Closure.htm")//putting help pages in session
		def grantAllocationService = new GrantAllocationService()
	
		/* Get grant allocation details. */
    	def grantAllocationInstance = grantAllocationService.getGrantAllocationById(new Integer(params.id)) 
    	/* Get grant allocation tracking details if any for the grant allocation */
    	def grantAllocationTrackingInstance = grantAllocationService.getGrantAllocationTrackingByGrantAllocation(grantAllocationInstance)
    	
    	if(!grantAllocationTrackingInstance){
    		grantAllocationTrackingInstance = new GrantAllocationTracking()
        	grantAllocationTrackingInstance.grantAllocation = grantAllocationInstance
            	grantAllocationTrackingInstance.properties = params
    	}
    	grantAllocationTrackingInstance.trackType = params.trackType
    	ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
        return ['grantAllocationInstance':grantAllocationInstance,
                'currencyFormat':currencyFormatter,
                'grantAllocationTrackingInstance':grantAllocationTrackingInstance]
    }

    def save = 
    {
   		GrailsHttpSession gh=getSession()
		def grantAllocationService = new GrantAllocationService()	
		params.modifiedBy = "admin"
    	params.modifiedDate = new Date()
    	def grantAllocationInstance = GrantAllocation.get(params.grantAllocation.id)
    	def projectTrackingCheck=ProjectTracking.findAll("from ProjectTracking P where P.projects.id= '"+grantAllocationInstance.projects.id+"'")
    	if(projectTrackingCheck)
    	{
    		flash.message ="${message(code: 'default.projectisAlready.label')}" +" "+projectTrackingCheck.projectStatus 
  			redirect(action:create,id:grantAllocationInstance.id)
    	}
    	else
    	{
    		def grantAllocationTrackingInstance = new GrantAllocationTracking(params)
			gh.putValue("TrackingStatus",grantAllocationTrackingInstance.grantAllocationStatus)
	    	gh.putValue("Trackingremarks",grantAllocationTrackingInstance.remarks)
	    	gh.putValue("TrackingDate",grantAllocationTrackingInstance.dateOfTracking)
    		redirect(action:statusDetails,params:[id:grantAllocationTrackingInstance.grantAllocation.id,projectStatus:grantAllocationTrackingInstance.grantAllocationStatus,remarks:grantAllocationTrackingInstance.remarks,dateOfTracking:grantAllocationTrackingInstance.dateOfTracking])
       }
	}
    
    def grantAllocationTrackingReports = {
		def dataSecurityService = new DataSecurityService()
		GrailsHttpSession gh=getSession()
		def grantAllocationInstanceList=[]
		try{
        	grantAllocationInstanceList = grantAllocationService.getAll()
        }
		  catch(Exception e)
		{
			
		}
		def projectsList=[]
        
        for(int i=0;i<grantAllocationInstanceList.size();i++)
        {
			if(grantAllocationInstanceList[i].projects.activeYesNo == 'Y'){
        	projectsList.add(grantAllocationInstanceList[i].projects)
			}
        }
		return[projectInstanceList:projectsList]
    }
    
    def showReport = {
    		println "showReport params "+params
    		return['reportListInstance':params]
    }
    
    def statusDetails =
	{
		GrailsHttpSession gh=getSession()
		def grantAllocationInstance = grantAllocationService.getGrantAllocationById(new Integer(params.id)) 
		def checklistmap = ChecklistMap.findAll("from ChecklistMap CM where CM.projects.id="+grantAllocationInstance.projects.id+" and CM.activeYesNo='Y'")
		return ['grantAllocationInstance':grantAllocationInstance,checklistmap:checklistmap]
		
	}
	
	def withdrawProject =
	{
	 	GrailsHttpSession gh=getSession()
		def projectsInstance =Projects.get(params.prjctId)
		def grantAllocationInstance =GrantAllocation.get(params.grantAllotId)
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
			redirect(action:statusDetails,params:[id:grantAllocationInstance.id])
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
					def grantAllocationTrackingInstance = new GrantAllocationTracking()
					grantAllocationTrackingInstance.grantAllocation = grantAllocationInstance
					grantAllocationTrackingInstance.grantAllocationStatus =(gh.getValue("TrackingStatus"))
					grantAllocationTrackingInstance.remarks =(gh.getValue("Trackingremarks"))
					grantAllocationTrackingInstance.dateOfTracking =(gh.getValue("TrackingDate"))
					grantAllocationTrackingInstance.createdBy = "admin"
			    	grantAllocationTrackingInstance.createdDate = new Date()
					grantAllocationTrackingInstance.modifiedBy = "admin"
			    	grantAllocationTrackingInstance.modifiedDate = new Date()
					grantAllocationTrackingInstance.save()
					flash.message = projectsInstance.name +" "+"${message(code: 'default.withdrawalsuccesfully.label')}"
					redirect(controller: "grantAllocation", action: "subGrantAllot",params:[id:projectsInstance.parent.id])
					
			}
			else
			{
				flash.error ="${message(code: 'default.PleaseselectanychecklistOtherwiseucantgoforfurtherchecking.label')}"
				redirect(action:statusDetails,params:[id:grantAllocationInstance.id])
			}
		}
	}
}
