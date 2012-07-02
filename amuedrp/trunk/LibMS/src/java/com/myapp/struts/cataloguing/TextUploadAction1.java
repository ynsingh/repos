/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.cataloguing;
import java.io.*;

import javax.servlet.http.*;
import javax.servlet.*;

public class TextUploadAction1 extends HttpServlet {
  public void doPost (HttpServletRequest req,
                     HttpServletResponse res)
    throws ServletException, IOException
  {
        getServletContext().getRequestDispatcher("/cataloguing/uploadtxt.jsp").forward(req, res);

  }
}
