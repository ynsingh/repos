import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class GmsTagLib {

	
  def grantAllocationService
  def proposalService
  
  def menuList = {attrs ->
             out << g.render(template: '/menuList', model:
				 [grantAllocation:  grantAllocationService.getAll()])
  }
  
  def subMenuList = {attrs ->
	  
	  def userService = new UserService()
	  GrailsHttpSession gh=getSession()
	  def menuRoleMapParentList = [] as Set
	  def userRoleList = userService.getUserRoleByUserId(gh.getValue("UserId"))
	  String roleIds=userService.getAllRolesAsString(userRoleList)
	  menuRoleMapParentList = userService.getAllParentListMappedToRolesForHMenu(roleIds)
	  
	  out << g.render(template: '/subMenuList',model:
		       [projectsInstance:  Projects.get(gh.getValue("ProjectID")),menuRoleMapParentList:menuRoleMapParentList,roleIds:roleIds])
  }
         
 def subMenuProjects = {attrs ->
  out << g.render(template: '/subMenuProjects')
 }
  			
 def subMenuNotification = {attrs ->
	 
	 def userService = new UserService()
	 GrailsHttpSession gh=getSession()
	 def menuRoleMapParentList = [] as Set
	 def userRoleList = userService.getUserRoleByUserId(gh.getValue("UserId"))
	 String roleIds=userService.getAllRolesAsString(userRoleList)
	 menuRoleMapParentList = userService.getAllParentListMappedToRoles(roleIds)
	 
  out << g.render(template: '/subMenuNotification',model:
		       [menuRoleMapParentList:menuRoleMapParentList,roleIds:roleIds])
 }
 
  def subMenuLogin = {attrs ->
  	out << g.render(template: '/subMenuLogin')
  }
  def pageNavigation = {attrs ->
  def proposalApplicationInstance = proposalService.getProposalApplicationById(attrs.proposalApplicationInstance)
  def proposalApplicationExtInstance = proposalService.getMaxPageOfProposalApplicationExt(proposalApplicationInstance?.id)
  out << g.render(template: '/pageNavigation',model:['page':new Integer(attrs.page),'proposalApplicationInstance':proposalApplicationInstance,'lastPage':proposalApplicationExtInstance[0],'status':attrs.status])
 }
  
}