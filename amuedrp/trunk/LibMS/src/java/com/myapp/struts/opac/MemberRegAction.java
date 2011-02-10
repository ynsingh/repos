/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;
import  com.myapp.struts.*;
import java.util.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;

/**
 *
 * @author System Administrator
 */
public class MemberRegAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String faculty,dept,course;
    ResultSet rst,rst1,rst2;


    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ArrayList arr=new ArrayList();
       HttpSession session = request.getSession();
        String library_id=(String)session.getAttribute("library_id");
            if(library_id==null)
                return mapping.findForward("fail");
            else{
        rst=MyQueryResult.getMyExecuteQuery("select faculty_id,faculty_name from faculty where library_id='"+library_id+"'");
        if(rst.next())
        {     session.setAttribute("faculty_resultset",rst);
             
        }

      //   rst1=MyQueryResult.getMyExecuteQuery("select a.dept_id,a.dept_name from department a inner join faculty b on  a.faculty_id=b.faculty_id and a.library_id=b.library_id");
      //  if(rst1.next())
     //   {   request.setAttribute("dept_resultset",rst1);
      //   rst2=MyQueryResult.getMyExecuteQuery("select a.course_id,a.course_name from courses a inner join department b on a.dept_id=b.dept_id and a.faculty_id=b.faculty_id and a.library_id=b.library_id;");
     //    if(rst2.next())
    //         request.setAttribute("course_resultset",rst2);
    //    }
       // }

   
    return mapping.findForward("success");}
    }
}
