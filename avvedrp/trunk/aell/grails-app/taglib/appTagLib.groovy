class appTagLib {
    def redirectMainPage = {
	def login_flag = session.login_flag
	if(login_flag=='openId')
		{
			
			response.sendRedirect("${request.contextPath}/openId/auth")
		}
	else if(login_flag=='openIdCreate')
		{
			
			response.sendRedirect("${request.contextPath}/openId/createAccount")
		}
	else
		{
			
			response.sendRedirect("${request.contextPath}/login/auth")
		}
      
    }
     def includeJs = { attrs ->
		 out << "<script src='js/${attrs['script']}.js' />"
	 	}
 def displayFile = {attrs, body->
		def filename = attrs["filename"]

		if(filename){
		  def extension = filename.split("\\.")[-1]
		  def userDir = "images"
		
		  switch(extension.toUpperCase()){
			case ["JPG", "PNG", "GIF", "BMP"]:
				def html = """
				<p>
				   <img  class="viewImage" height="100em" width="90.0em" src="${createLinkTo(dir:''+userDir,
											file:''+filename)}"
						alt="${filename}"
						title="${filename}" />
				</p>
				"""
				out << html
			break
		
			case "HTML":
				out << "p>html</p>"
			break
			default:
				out << "<p>file</p>"
			break
		  }
		}else{
		  out << "<!-- no file -->"
		}
	}
	
	def displayMchoiceText = {attrs, body ->
		def iterList = attrs["qnContentList"]
		
		def html="""

	"""
		out << html
	}
	
  }
  