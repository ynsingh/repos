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
public class AcqInitiateApproval2Action extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    AcquisitionDao acqdao=new AcquisitionDao();
    AcqBibliographyDetails acqb=new AcqBibliographyDetails();
    AcqApprovalHeader acqaph=new AcqApprovalHeader();
    AcqApprovalHeaderId  acqaphid=new AcqApprovalHeaderId();

    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
         HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");

        List<ApprovalList> aa=acqdao.searchOnApprovalList(library_id, sub_library_id);

       
        System.out.println(aa.size()+"..........................");
        session.setAttribute("onApprovalList", aa);
        return mapping.findForward("listApproval");
        
        
        
    }
}
