/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.institute_admin;

import com.myapp.struts.hbm.*;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Edrp-04
 */
public class View_ElectionsAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
   // private String institute_id;
     List rst;
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Election_Manager_RegistrationActionForm electionManagerForm=(Election_Manager_RegistrationActionForm)form;

      //  institute_id=electionManagerForm.getInstitute_id();

        ElectionManager electionmanager=new ElectionManager();
     ElectionDAO electionmanagerdao=new ElectionDAO();

     HttpSession session = request.getSession();
    

     String instituteId = (String)session.getAttribute("institute_id");

      System.out.println("instituteId");
        //con=MyConnection.getMyConnection();
        //PreparedStatement stmt=con.prepareStatement("select * from admin_registration where  status is null");
        //rst=stmt.executeQuery();
      String searchby = request.getParameter("search_by");
      String searchkeyword = request.getParameter("search_keyword");
      String sortby = request.getParameter("sort_by");
     System.out.println(sortby+".....................");

        rst =electionmanagerdao.GetElectionDetailsbyinstituteId(instituteId,searchby,searchkeyword,sortby);
            session.setAttribute("resultset", rst);
         System.out.println("resultset"+ rst);
            System.out.println("rst+"+rst.size());

        return mapping.findForward("success");
        


       
    }
}
