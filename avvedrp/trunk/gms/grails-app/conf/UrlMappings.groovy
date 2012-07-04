class UrlMappings {
    static mappings = {
		"/login/auth" {
			controller = 'openId'
			action = 'auth'
		 }
		"/login/openIdCreateAccount" {
			controller = 'openId'
			action = 'createAccount'
		 }
      "/$controller/$action?/$id?"
    	 {
    		 System.out.println("testing UrlMappings ")
	      constraints {
			 // apply constraints here
		  }
	  }
	  "500"(view:'/error')
	}
}
