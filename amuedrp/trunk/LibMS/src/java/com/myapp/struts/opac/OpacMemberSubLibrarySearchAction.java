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
public class OpacMemberSubLibrarySearchAction extends org.apache.struts.action.Action {
    
   
 
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        StringBuffer emp_ids = new StringBuffer();
        OpacSearchDAO opacDAO = new OpacSearchDAO();

        String searchText = request.getParameter("getSubLibrary_Id");
        String memberId=request.getParameter("getMember_Id");
        System.out.println(searchText+" "+memberId);
        List<MemberSubLibrary> sublibrary = (List<MemberSubLibrary>)opacDAO.MembersubLibrarySearch(searchText,memberId);
System.out.println(sublibrary.size());
        Iterator it = sublibrary.iterator();
        int tcount=0;
        emp_ids.append("<sublibrary_ids>");
        while (it.hasNext())
        {

//construct the xml string.
           MemberSubLibrary sublibpojo = (MemberSubLibrary)sublibrary.get(tcount);


                emp_ids.append("<sublibrary_id>"+sublibpojo.getSubLibrary().getId().getSublibraryId()+"</sublibrary_id>");
                emp_ids.append("<sublibrary_name>"+sublibpojo.getSubLibrary().getSublibName()+"</sublibrary_name>");
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






