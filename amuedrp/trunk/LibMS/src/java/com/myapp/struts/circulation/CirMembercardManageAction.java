/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import com.myapp.struts.CirDAO.CirculationDAO;
import com.myapp.struts.hbm.CirMemberDetail;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author edrp02
 */
public class CirMembercardManageAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String library_id,sublibrary_id,reg_date,expiry_date,memid;
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        CirMembercardManageActionForm myform=(CirMembercardManageActionForm)form;
        memid=myform.getMemid();
        reg_date=myform.getReg_date();
        expiry_date=myform.getExpiry_date();

        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
        sublibrary_id=(String)session.getAttribute("sublibrary_id");
        List<MemberList> memberlist=(List<MemberList>)CirculationDAO.Memberlist(library_id, memid, reg_date, expiry_date);
        session.setAttribute("memberlist", memberlist);

        return mapping.findForward(SUCCESS);
    }
}
