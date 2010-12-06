import grails.converters.*
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import org.codehaus.groovy.grails.commons.GrailsApplication
import grails.util.GrailsUtil
import org.codehaus.groovy.grails.commons.ControllerArtefactHandler
import org.springframework.beans.BeanWrapper
import org.springframework.beans.PropertyAccessorFactory

class RolePrivilegesController {
    def rolePrivilegesService
    def userService
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
    		
        if(!params.max) params.max = 10
        [ rolePrivilegesInstanceList: RolePrivileges.list( params ) ]
    }

    def show = {
        def rolePrivilegesInstance = RolePrivileges.get( params.id )

        if(!rolePrivilegesInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
        else { return [ rolePrivilegesInstance : rolePrivilegesInstance ] }
    }

    def delete = {
    		println "params delete==="+params
    		GrailsHttpSession gh=getSession()
    		def actionInstanceList = params.deleteAction
			def actionList=params.deleteAction.toString();
    		println "actionList"+actionList
    		def actionSplit=actionList.split(',')
    		if(actionSplit.length==1)
    		{
    			def actionArray=[]
    			actionArray.add(params.deleteAction)
    			actionInstanceList=actionArray
    			println "projectInstance"+actionInstanceList
    		}
    		for(actionName in actionInstanceList)
    		{
    		def rolePrivilegesSelectedInstance=RolePrivileges.find("from RolePrivileges RP where RP.controllerName='"+params.controllerName+"' and RP.actionName='"+actionName+"' and RP.role="+params.role.id+" and RP.party="+gh.getValue("Party"))
    		
    		//def rolePrivilegesInstance = RolePrivileges.get( rolePrivilegesSelectedInstance.id )
    		//if(rolePrivilegesInstance) 
    			
    			rolePrivilegesSelectedInstance.delete()
    			
    			
          
    	}
    		def rolePrivilegesInstance=RolePrivileges.find("from RolePrivileges RP where RP.controllerName='"+params.controllerName+"' and RP.role="+params.role.id+" and RP.party="+gh.getValue("Party"))

    		 redirect(action:getActionName,params:[filename:rolePrivilegesInstance.controllerName,role:rolePrivilegesInstance.role.id])
    }

    def edit = {
        def rolePrivilegesInstance = RolePrivileges.get( params.id )

        if(!rolePrivilegesInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
        else {
            return [ rolePrivilegesInstance : rolePrivilegesInstance ]
        }
    }

    def update = {
        def rolePrivilegesInstance = RolePrivileges.get( params.id )
        if(rolePrivilegesInstance) {
            rolePrivilegesInstance.properties = params
            if(!rolePrivilegesInstance.hasErrors() && rolePrivilegesInstance.save()) {
                flash.message = "${message(code: 'default.updated.label')}"
                redirect(action:show,id:rolePrivilegesInstance.id)
            }
            else {
                render(view:'edit',model:[rolePrivilegesInstance:rolePrivilegesInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def rolePrivilegesInstance = new RolePrivileges()
        rolePrivilegesInstance.properties = params
        List filename = new ArrayList() 
       //for reading controller name
        filename=grailsApplication.controllerClasses.logicalPropertyName
        
        def authorityInstanceList = userService.getAllRolls()   
        return ['rolePrivilegesInstance':rolePrivilegesInstance,'filename':filename,'authorityInstanceList':authorityInstanceList]
    }
    
    //for listing action name assigned to each role 
    def getActionName = {
    		println "---------------on call--------------------" +params
    		GrailsHttpSession gh=getSession()
    		if(params.filename){
    		def rolePrivilegesInstance = RolePrivileges.findAll("from RolePrivileges RP where RP.controllerName='"+params.filename+"'"+" and RP.role="+params.role+" and RP.party.id ="+gh.getValue("Party"))
    		
    		
    		//
    		def controllerNameNew = grailsApplication.controllerClasses
    	
    		
    		List actionFileName = new ArrayList()
    		def webRootDir
    		def actionPath
    	    File folder
    	        
    	        //for reading the action name from each controller 
    		if(params.filename!="null")
    		{

        		def controllerClassname =grailsApplication?.getArtefactByLogicalPropertyName("Controller", params.filename)
        		
        		def data = []
        		List actions = []
        		def controllerInfo = [:]
        		controllerInfo.controller = controllerClassname.logicalPropertyName
        		BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(controllerClassname.newInstance())
     		    for (pd in beanWrapper.propertyDescriptors) {
     		        String closureClassName = controllerClassname.getPropertyOrStaticPropertyOrFieldValue(pd.name, Closure)?.class?.name
     		        if (closureClassName){
     		        	actions << pd.name
     		        	
     		        }
     		    }
        		 controllerInfo.actions = actions.sort()
     		    data << controllerInfo
        		
    			
    		actionFileName=actions
    		def actionList=[]
    		def actionSet=[] as Set
    		def actionCollection=[]
    		if(rolePrivilegesInstance)
    		{
    		
    			
    			for(RP in rolePrivilegesInstance.actionName)
    			{
    				actionList.add(RP)
    					
       			}
    			//differnce between two list
    			actionCollection = new ArrayList(actionFileName)
    			actionCollection.removeAll(actionList)
    			
    		}
    		else
    		{
    			actionCollection=actionFileName
       		}
    	
    		
    			
    		render (template:"actionNameSelect", model : ['actionNameList' : actionCollection,
    		                                              'rolePrivilegesInstance':rolePrivilegesInstance])
    		}
    		else
    		{
    			render (template:"noActionSelect")
    			
    		}
    		}
    		else{
    			redirect(action:create)
    		}
    		
    }
    def getSaveActions = {
    		println "getSaveActions"
    		def rolePrivilegesInstance = RolePrivileges.findAll("from RolePrivileges RP where RP.controllerName='"+params.filename+"'")
    }

    def save = {
    		println "save params=================="+params
    		GrailsHttpSession gh=getSession()
    		def rolePrivilegesInstance = new RolePrivileges(params)
    		def actionInstanceList = params.actionName
    		def actionList = params.actionName.toString()
    		def webRootDir = servletContext.getRealPath("/")
    		def actionListSplit=actionList.split(',')
    		if (actionListSplit.length ==1)
    		{
    		def actionDet=[]
    		actionDet.add(params.actionName)
    		actionInstanceList=actionDet
    		}
    		
    		def rolePrivilegesService = new RolePrivilegesService()
    		def rolePrivilegesSaveStatus = rolePrivilegesService.saveRolePrivileges(params,actionInstanceList,gh.getValue("Party"))
        if(rolePrivilegesSaveStatus) {
            flash.message = "${message(code: 'default.created.label')}"
           //redirect(action:create,id:rolePrivilegesInstance.id)
            redirect(action:getActionName,params:[filename:rolePrivilegesInstance.controllerName,role:rolePrivilegesInstance.role.id])
        }
        else {
            render(view:'create',model:[rolePrivilegesInstance:rolePrivilegesInstance])
        }
    }
    def newRolePrivileges = 
    {
    		def authorityInstanceList = userService.getAllRolls()   		
    		return[authorityInstanceList:authorityInstanceList]
    }
    def saveNewRolePriviliges = {
    		println "saveNewRolePriviliges+++++++++++++++"+params.authority
    		GrailsHttpSession gh = getSession()
    		def rolePrivilegesService = new RolePrivilegesService()
    		def webRootDir
			
	       	def party = Party.get(gh.getValue("Party"))
	       	def roleInstance = Authority.get(params.authority) 
	       	def rolePrivilegesInstanceStatus=RolePrivileges.find("from RolePrivileges RP where RP.role="+params.authority+" and RP.party="+gh.getValue("Party"))
	       	
	       		def data = []
	       		for (controller in grailsApplication.controllerClasses) {
	       			def controllerInfo = [:]
	       			controllerInfo.controller = controller.logicalPropertyName
	       			controllerInfo.controllerName = controller.fullName
	       			List actions = []
	       			BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(controller.newInstance())
	       			for (pd in beanWrapper.propertyDescriptors) {
	       				String closureClassName = controller.getPropertyOrStaticPropertyOrFieldValue(pd.name, Closure)?.class?.name
	       						if (closureClassName) 
	       							{
	       							actions << pd.name
	       							
	       							def rolePrivilegesInstanceCheck=rolePrivilegesService.getRolePrivileges(controller.logicalPropertyName,pd.name,params.authority,gh.getValue("Party"))
	    							
	       							if(!rolePrivilegesInstanceCheck)
	       							{
	       								def rolePrivilegesInstance=new RolePrivileges()
	       								rolePrivilegesInstance.controllerName=controller.logicalPropertyName
	       								rolePrivilegesInstance.actionName=pd.name
	       								rolePrivilegesInstance.role=roleInstance
	       								rolePrivilegesInstance.party=party
	       								rolePrivilegesInstance.save()
	       							}
	       							}
	       			}
	       			controllerInfo.actions = actions.sort()
	       			data << controllerInfo
	       		}
	       		flash.message = "${message(code: 'default.created.label')}"
	       		redirect(action:newRolePrivileges)
	       	
    }
}
