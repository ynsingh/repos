/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.opacDAO.*;

/**
 *
 * @author Faraz
 */
public class LocationsAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    OpacSearchDAO opacsearch=new OpacSearchDAO();
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

            String lib_id;
            HttpSession session = request.getSession();
            lib_id = (String)session.getAttribute("library_id");
            List lib=opacsearch.LibrarySearch(lib_id);
            List sublib=opacsearch.subLibrarySearch(lib_id);


            if(!lib.isEmpty()&&!sublib.isEmpty())
            {
            session.setAttribute("lib", lib);
            session.setAttribute("sublib", sublib);
            return mapping.findForward(SUCCESS);
            }else{
                request.setAttribute("msg", "No Record Found");
            return mapping.findForward(SUCCESS);

            }
        
       
    }
}
