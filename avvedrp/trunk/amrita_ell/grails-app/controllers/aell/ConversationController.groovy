package aell

class ConversationController {
    def a=true
       
    def index = {
      System.out.println("In index-----"+a)
        
      if(a==true){
          render(view : "sideMenu")
             a=false;
            System.out.println("In index-----"+a)
      } else{
          System.out.println("in else")
        //redirect(uri:"../conversation/sideMenu")
        //render(view : "sideMenu")
      //  redirect(controller:"conversation",action:"sidemenu")
        return
      }

    }
  def sideMenu = {
     System.out.println("in side menu")
    return
   }
}
