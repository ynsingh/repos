/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;

import com.myapp.struts.hbm.Location;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.systemsetupDAO.LocationDAO;
import java.util.List;

/**
 *
 * @author edrp02
 */
public class LocationViewAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String library_id,sublibrary_id;
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LocationViewActionForm lvaf=(LocationViewActionForm)form;
        library_id=lvaf.getCMBLib();
        sublibrary_id=lvaf.getCMBSUBLib();

        List<Location>  loc=(List<Location>)LocationDAO.getLocation(library_id, sublibrary_id);
        if(!loc.isEmpty())
        {
           request.setAttribute("loc", loc);
           return mapping.findForward("view");
        }
        
        return mapping.findForward(null);
    }
}
