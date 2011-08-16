/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.*;
import com.myapp.struts.AcquisitionDao.AcquisitionDao;
import javax.servlet.http.HttpSession;


/**
 *
 * @author maqbool
 */
public class AcqMergeAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

    

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AcqBiblioActionForm acqbibfrm= (AcqBiblioActionForm)form;
         HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String title=acqbibfrm.getTitle();
        String doc_type=acqbibfrm.getDocument_type();
        String isbn=acqbibfrm.getIsbn();
        String button=acqbibfrm.getButton();
        String author=acqbibfrm.getAuthor();
        if(button.equals("Add Title")){
            acqbibfrm.setTitle(title);
            acqbibfrm.setAuthor(author);
            acqbibfrm.setLibrary_id(library_id);
            acqbibfrm.setSub_library_id(sub_library_id);
            acqbibfrm.setIsbn(isbn);
            acqbibfrm.setDocument_type(doc_type);
            
            return mapping.findForward("merge");

        }
        
        return mapping.findForward(SUCCESS);
    }
}
