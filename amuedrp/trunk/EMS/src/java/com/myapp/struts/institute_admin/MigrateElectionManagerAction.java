/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.institute_admin;

import com.myapp.struts.AdminDAO.LoginDAO;
import com.myapp.struts.SuperAdminActionForm;
import com.myapp.struts.hbm.AdminRegistrationDAO;
import com.myapp.struts.hbm.Election;
import com.myapp.struts.hbm.ElectionDAO;
import com.myapp.struts.hbm.ElectionManagerMigrate;
import com.myapp.struts.hbm.ElectionManagerMigrateId;
import com.myapp.struts.hbm.Login;
import com.myapp.struts.hbm.StaffDetail;
import com.myapp.struts.hbm.StaffDetailDAO;
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
public class MigrateElectionManagerAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private int registration_id ;

    List rst;

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
    
        AdminRegistrationDAO admindao = new AdminRegistrationDAO();
        SuperAdminActionForm admin=(SuperAdminActionForm)form;
        HttpSession session = request.getSession();
        String instituteId = (String)session.getAttribute("institute_id");
String oldem=admin.getUser_id1();
String newem=admin.getUser_id2();
String election=admin.getElection();

  ElectionDAO electiondao=new ElectionDAO();
  Election e=electiondao.Electionname(instituteId, election);

  Login obj=LoginDAO.searchLoginID(newem);
  if(obj==null)
  {
  request.setAttribute("msg1", "ELection Manager Id is not correct");
  return mapping.findForward("success");

  }
    if( e.getCreatedBy().equalsIgnoreCase(oldem)){
Login staff=LoginDAO.searchLoginID(newem);

if(staff.getRole().startsWith("Election Manager")){
ElectionManagerMigrateId e1=new ElectionManagerMigrateId(election, instituteId);
ElectionManagerMigrate e2=new ElectionManagerMigrate(e1);
e2.setOldEm(oldem);
e2.setNewEm(newem);
admindao.migrate_election_manager(e2);


e.setCreatedBy(newem);
electiondao.update(e);

 request.setAttribute("msg", "ELection Manager Migrated to:-"+newem);
         


    }
}
else
{request.setAttribute("msg1", "ELection Manager Id is not correct");
 
}
   return mapping.findForward("success");
    }
}
