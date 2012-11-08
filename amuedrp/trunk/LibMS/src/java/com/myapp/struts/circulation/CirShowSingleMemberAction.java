package com.myapp.struts.circulation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.*;
import com.myapp.struts.CirDAO.CirculationDAO;

/**
 *
 * @author edrp02
 */
public class CirShowSingleMemberAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String library_id,sublibrary_id,mem_id;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CirculationDAO cirdao=new CirculationDAO();
        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
        sublibrary_id=(String)session.getAttribute("sublibrary_id");
        mem_id=(String)request.getParameter("id");
        
        CirMemberDetail cmemdetail=cirdao.searchCirMemDetails(library_id, mem_id);
          System.out.println("@@@@@@@@@@"+cmemdetail.getFname());

        if(cmemdetail!=null)
        {
           request.setAttribute("mem_id",mem_id);
           request.setAttribute("cmemdetail",cmemdetail);
           return mapping.findForward("success");
        }



        return mapping.findForward("success");
    }
}
