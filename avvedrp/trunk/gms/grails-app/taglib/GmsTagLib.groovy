
class GmsTagLib {

	
  def grantAllocationService
  def proposalService
  

 
         def menuList = {attrs ->
             out << g.render(template: '/menuList', model:
       [grantAllocation:  grantAllocationService.getAll()])
         }
  
  def subMenuList = {attrs ->
		  out << g.render(template: '/subMenuList',model:
		       [projectsInstance:  Projects.get(session.ProjectID)])
         }
         
         def subMenuProjects = {attrs ->
		  out << g.render(template: '/subMenuProjects')
         }
  			
		 def subMenuNotification = {attrs ->
		  out << g.render(template: '/subMenuNotification')
		 }
  def pageNavigation = {attrs ->
  def proposalApplicationInstance = proposalService.getProposalApplicationById(attrs.proposalApplicationInstance)
  def proposalApplicationExtInstance = proposalService.getMaxPageOfProposalApplicationExt(proposalApplicationInstance?.id)
  out << g.render(template: '/pageNavigation',model:['page':new Integer(attrs.page),'proposalApplicationInstance':proposalApplicationInstance,'lastPage':proposalApplicationExtInstance[0]])
 }
  
  
    }