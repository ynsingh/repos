/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import com.myapp.struts.CirculationDAO.CirculationDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.*;

/**
 *
 * @author faraz
 */
public class CirculationShowMemberAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
String library_id,sublibrary_id;
String mem_id;
String button;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
CirculationShowMemberActionForm cir=(CirculationShowMemberActionForm)form;

sublibrary_id=cir.getLibrary();
mem_id=cir.getMemId();
HttpSession session=request.getSession();

button=cir.getButton();


library_id=(String)session.getAttribute("library_id");
System.out.println(library_id+" "+sublibrary_id+ " "+mem_id);

CirMemberAccount cirmem=(CirMemberAccount)CirculationDAO.searchCirMemAccountDetails(library_id, sublibrary_id, mem_id);
CirMemberDetail cirmemobj=(CirMemberDetail)CirculationDAO.searchCirMemDetails(library_id, mem_id);
System.out.println(cirmemobj+" " +cirmem+ ".........."+button);
request.setAttribute("cirmemaccountdetail", cirmem);
request.setAttribute("cirmemdetail", cirmemobj);
    request.setAttribute("button", button);

        return mapping.findForward("success");
    }
}
