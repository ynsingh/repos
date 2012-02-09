/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.institute_admin;

import com.myapp.struts.hbm.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Edrp-04
 */
public class View_Manager_DetailAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    //private String manager_id;
    //private String first_name;
    //private String last_name;
    //private String staff_id;
    List<Election_Manager_StaffDetail> rst;

    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
            HttpSession session = request.getSession();
        String  ElectionManagerId="id";
       ElectionManagerId=request.getParameter(ElectionManagerId);

       if(ElectionManagerId==null)
           ElectionManagerId=(String)session.getAttribute("manager_id");

       String instituteId = (String)session.getAttribute("institute_id");

     ElectionManager electionmanager=new ElectionManager();
     ElectionManagerDAO electionmanagerdao=new ElectionManagerDAO();

//HttpSession session = request.getSession();
      try{
        //con=MyConnection.getMyConnection();
        //PreparedStatement stmt=con.prepareStatement("select * from admin_registration where  status is null");
        //rst=stmt.executeQuery();
        rst = (List<Election_Manager_StaffDetail>) electionmanagerdao.GetManagerDetails(ElectionManagerId,instituteId);
            request.setAttribute("resultset", rst);
        if(session.getAttribute("manager_id")!=null)
        return mapping.findForward("success1");
else
        return mapping.findForward("success");
        }
        catch(Exception e)
        {
        e.getMessage();
        }
return null;

    }
}
