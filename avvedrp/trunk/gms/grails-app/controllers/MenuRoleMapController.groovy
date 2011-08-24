import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class MenuRoleMapController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def create = {
    	def partyService = new PartyService()
    	def userService = new UserService()
    	def menuRoleMapInstance = new MenuRoleMap()
    	GrailsHttpSession gh=getSession()
        menuRoleMapInstance.properties = params
        def menuInstanceList = partyService.getAllMenu() //Get All Menu Names.
        def authorityList = userService.getAuthorityListByRole(gh.getValue("UserId"))
        return [menuRoleMapInstance: menuRoleMapInstance,menuInstanceList:menuInstanceList,authorityList:authorityList]
    }

    def listMenu = {
    	def partyService = new PartyService()
    	def newMenuList =[] as Set
    	def prntList = [] as Set
    	def allChildList = []
    	def sizeList =[]
    	int s=0;
    	if(params.roleId!=null)
    	{
    		def menuInstanceList = partyService.getAllMenu()
    		def menuRoleMapInstanceList=partyService.getMapByPartyAndRole(params.roleId)
    		def parentList=partyService.getParentListByMenuOrder(params.roleId)
    		for(PL in parentList)
    		{
    			def childList=partyService.getChildListByMenuOrder(params.roleId,PL.menu.id)
    			if (childList){
    				sizeList[s]=childList.size()
    				s++
    				for(int k=0;k<childList.size();k++){
    					allChildList.add(childList[k])
    				}
    			}else{
    				def parntList = MenuRoleMap.get(PL.id)
    				prntList.add(parntList)
    			}
    		}
    		parentList.removeAll(prntList)
    		for(ML in menuRoleMapInstanceList)
			{
    			newMenuList = partyService.getMenuFromMap(ML.menu.id)
    			if(newMenuList[0].parentId != -1){
    				menuInstanceList.removeAll(newMenuList)
    			}
    			
    		} 
    		render (template:"listMenu", model : ['menuInstanceList' : menuInstanceList,parentList:parentList,sizeList:sizeList,
    		                                      allChildList:allChildList,'menuRoleMapInstanceList':menuRoleMapInstanceList])
    	}
    }
    def save = {
    	def partyService = new PartyService()
    	def childMapList = []
    	def menuList = params.menuSel.toString()
    	def menuMapList=menuList.split(',')
    	if (menuMapList.length ==1)
    	{
			def menuRoleList = []
			menuRoleList.add(params.menuSel.toString())
			params.menuSel =  menuRoleList
		}
    	for(int i=0;i<params.menuSel.size();i++){
	        def menuRoleMapInstance = new MenuRoleMap(params)
	        menuRoleMapInstance.createdBy="SuperAdmin"
	        menuRoleMapInstance.createdDate=new Date()
	        menuRoleMapInstance.modifiedBy="SuperAdmin"
	        menuRoleMapInstance.modifiedDate=new Date()
	        menuRoleMapInstance.activeYesNo='Y'
	        def newMenuList = partyService.getMenuFromMap(params.menuSel[i])
	        menuRoleMapInstance.menu = newMenuList[0]
        	menuRoleMapInstance.save()
        }
    	def menuRoleMapParentList = partyService.getParentListByMenuOrder(params.role.id)
    	def childList = partyService.getChildList(params.role.id)
		def parentList = []
    	for(int k=0;k<menuRoleMapParentList.size();k++){
			def menuRoleMapChildInstance = partyService.getChildListByMenuOrder(params.role.id,menuRoleMapParentList[k].menu.id)
			for(int l=0;l<menuRoleMapChildInstance.size();l++){
				childMapList.add(menuRoleMapChildInstance[l])
			}
		}
    	def commons = childList.intersect(childMapList)
    	def difference = childList.plus(childMapList)
    	difference.removeAll(commons)
    	if(difference.size()>0){
			for(int j=0;j<difference.size();j++){
				def menuInstance = partyService.getMenuFromMap(difference[j].menu.parentId)
				if(parentList != null){
					 if (parentList.contains(menuInstance[0])) {
					}else{
						 parentList.add(menuInstance[0])
					 }
				}
				else{
					parentList.add(menuInstance[0])
				}
			}
			partyService.saveMapInstance(parentList,params.role.id)
		}
		redirect(action: "listMenu",params:[roleId:params.role.id])
    }

    def delete = {
    	def partyService = new PartyService()
    	def menuList = params.menuSelt.toString()
    	def menuMapList=menuList.split(',')
    	def parentList = []
    	def selectMenuList = []
    	int c=0		
    	if (menuMapList.length ==1)
    	{
			def menuRoleList = []
			menuRoleList.add(params.menuSelt.toString())
			params.menuSelt =  menuRoleList
		}
    	
    	for(int i=0;i<params.menuSelt.size();i++){
    		def menuRoleMapInstance = MenuRoleMap.get(params.menuSelt[i])
    		selectMenuList.add(menuRoleMapInstance)
    		if (menuRoleMapInstance) {
	        	if(menuRoleMapInstance.menu.parentId == -1){
	        		parentList.add(menuRoleMapInstance)
	        	}
        		menuRoleMapInstance.activeYesNo='N'
                menuRoleMapInstance.save()
	        }
    	}
    	for(int j=0;j<parentList.size();j++){
			def menuRoleMapInstance = MenuRoleMap.get(parentList[j].id)
			def childLst=partyService.getChildListByMenuOrder(params.role.id,menuRoleMapInstance.menu.id)
			def commons = childLst.intersect(selectMenuList)
			if(commons){
				childLst.removeAll(commons)
			}
			if(childLst){
				menuRoleMapInstance.activeYesNo='Y'
	            menuRoleMapInstance.save()
	            c++
			}
		}
		if(c>0){
			flash.error = "${message(code: 'default.submenuexists.label')}"
		}
		redirect(action: "listMenu",params:[roleId:params.role.id])
	}
}
