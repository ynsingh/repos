class UrlMappings {
    static mappings = {
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
