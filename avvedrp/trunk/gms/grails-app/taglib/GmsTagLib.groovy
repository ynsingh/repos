
class GmsTagLib {

	
  def grantAllocationService
  

 
         def menuList = {attrs ->
             out << g.render(template: '/menuList', model:
       [grantAllocation:  grantAllocationService.getAll()])
         }
  
  def subMenuList = {attrs ->
		  out << g.render(template: '/subMenuList')
         }
         
         def subMenuProjects = {attrs ->
		  out << g.render(template: '/subMenuProjects')
         }
  			
		 def subMenuNotification = {attrs ->
		  out << g.render(template: '/subMenuNotification')
		 }
  
    }