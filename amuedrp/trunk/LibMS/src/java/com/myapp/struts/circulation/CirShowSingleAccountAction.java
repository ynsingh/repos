/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class CirShowSingleAccountAction extends org.apache.struts.action.Action {
    CirculationDAO cirdao=new CirculationDAO();
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String library_id,sublibrary_id,mem_id;
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
        sublibrary_id=(String)session.getAttribute("sublibrary_id");
        mem_id=(String)request.getParameter("id");
        CirMemberAccount cmaccount=cirdao.searchCirMemAccountDetails(library_id, sublibrary_id, mem_id);
        CirMemberDetail cmemdetail=cirdao.searchCirMemDetails(library_id, mem_id);
        System.out.println("#######"+cmaccount+"%%%%%%%%%%%%%%%"+cmemdetail);
        if(cmaccount!=null)
        {
           request.setAttribute("fname",cmemdetail.getFname());
           request.setAttribute("cmaccount",cmaccount);
           System.out.println("#######"+"%%%%%%%%%%%%%%%");

           return mapping.findForward("success");
        }



        return mapping.findForward("");
    }
}
