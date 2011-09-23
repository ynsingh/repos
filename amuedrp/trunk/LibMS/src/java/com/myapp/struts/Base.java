/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;
import java.io.IOException;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Base extends HttpServlet {
  private String webmail = "";

  private String webpass = "";

  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    ServletContext context = getServletContext();
    webmail = context.getInitParameter("webmail");
    webpass = context.getInitParameter("webpass");

  }

  public void doGet(HttpServletRequest req, HttpServletResponse res)
      throws IOException,ServletException {
  HttpSession session=req.getSession();
  session.setAttribute("webmail", webmail);
  session.setAttribute("webpass", webpass);
  
              String url = "/login.jsp";
 RequestDispatcher dispatcher =
 getServletContext().getRequestDispatcher(url);
 dispatcher.forward(req, res);
  }
}