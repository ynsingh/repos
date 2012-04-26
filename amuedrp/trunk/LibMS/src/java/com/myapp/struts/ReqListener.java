package com.myapp.struts;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

public class ReqListener implements ServletRequestListener {

  private static long reqCount;

  public void requestInitialized(ServletRequestEvent sre) {

   // ServletContext context = sre.getServletContext();
    //ServletRequest request = sre.getServletRequest();

  //  synchronized (context) {
//      context
//          .log("Request for "
//              + (request instanceof HttpServletRequest ? ((HttpServletRequest) request)
//                  .getRequestURI()
//                  : "Unknown") + "; Count=" + ++reqCount);
        System.gc();
       // System.out.println("Error");
    //}

  }

  public void requestDestroyed(ServletRequestEvent sre) {

  }

}

           
