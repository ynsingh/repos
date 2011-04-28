/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;

import com.myapp.struts.hbm.SubLibrary;
import com.myapp.struts.opacDAO.OpacSearchDAO;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author faraz
 */
public class OpacSubLibrarySearchAction extends org.apache.struts.action.Action {
    
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
        StringBuffer emp_ids = new StringBuffer();
        OpacSearchDAO opacDAO = new OpacSearchDAO();

        String searchText = request.getParameter("getSubLibrary_Id");
        List sublibrary = opacDAO.subLibrarySearch(searchText);

        Iterator it = sublibrary.iterator();
        int tcount=0;
        emp_ids.append("<sublibrary_ids>");
        while (it.hasNext())
        {

//construct the xml string.
            SubLibrary sublibpojo = (SubLibrary)sublibrary.get(tcount);


                emp_ids.append("<sublibrary_id>"+sublibpojo.getId().getSublibraryId()+"</sublibrary_id>");
                emp_ids.append("<sublibrary_name>"+sublibpojo.getSublibName()+"</sublibrary_name>");
                tcount++;
                it.next();
        }
        emp_ids.append("</sublibrary_ids>");


        if(emp_ids!=null)
        {response.setContentType("application/xml");
        response.getWriter().write(emp_ids.toString());
        }
        return null;
    }
}






