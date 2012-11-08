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
import com.myapp.struts.CirDAO.CirculationDAO;
import java.util.List;

/**
 *
 * @author edrp02
 */
public class CirViewAllAccountAction1 extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String library_id,sublibrary_id;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CirculationDAO cirdao=new CirculationDAO();
        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
        sublibrary_id=(String)session.getAttribute("sublibrary_id");
        List cirmemacclist=cirdao.searchCirMemAccount2(library_id,sublibrary_id);

        session.removeAttribute("cirmemacclist");
        session.removeAttribute("cirmemacclist1");
       
          session.setAttribute("cirmemacclist", cirmemacclist);
         


         List cirmemacclist1=cirdao.searchCirMemCancel(library_id,sublibrary_id);
       
          session.setAttribute("cirmemacclist1", cirmemacclist1);



          return mapping.findForward("success");
        
       

    }
}
