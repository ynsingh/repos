class appTagLib {
    def redirectMainPage = {
      response.sendRedirect("${request.contextPath}/index/index")
    }
  }