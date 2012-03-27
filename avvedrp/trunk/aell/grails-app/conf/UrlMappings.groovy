class UrlMappings {
    static mappings = {
      "/login/auth" {
         controller = 'login'
         action = 'auth'
      }
	  "/openId/auth" {
		  controller = 'openId'
		  action = 'auth'
	   }
      

      "/$controller/$action?/$id?"{
	      constraints {
			 // apply constraints here
		  }
	  }
      "/"(view:"/index")
	  "500"(view:'/error')
	}
}
