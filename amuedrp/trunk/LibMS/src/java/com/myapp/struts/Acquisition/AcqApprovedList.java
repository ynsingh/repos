/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.*;
import com.myapp.struts.AcquisitionDao.AcquisitionDao;
import java.util.List;

/**
 *
 * @author maqbool
 */
public class AcqApprovedList extends org.apache.struts.action.Action {
    
  
 
    AcquisitionDao acqdao=new AcquisitionDao();
    AcqBibliographyDetails acqb=new AcqBibliographyDetails();
    AcqApprovalHeader acqaph=new AcqApprovalHeader();
    AcqApprovalHeaderId  acqaphid=new AcqApprovalHeaderId();

    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AcquisitionDao dao=new AcquisitionDao();
         HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
       



         


   List<approval_1> bb=dao.searchDoc5( library_id, sub_library_id);
        session.setAttribute("opacListApproved1", bb);

 return mapping.findForward("success");



         }
        
        
    }
