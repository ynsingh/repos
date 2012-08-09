/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.marc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DeleteFixedMarcAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

    private MarcHibDAO mhd=new MarcHibDAO();
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
System.out.println("Call Delete");
            HttpSession session = request.getSession();
                String bib_id=(String)session.getAttribute("biblio_id") ;
                      String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
                boolean m=mhd.deleteMarcBiblio(bib_id,library_id,sub_library_id);
        System.out.println("s###################### "+m);

        request.setAttribute("del", "the record is deleted!");
        return mapping.findForward(SUCCESS);
    }
}
