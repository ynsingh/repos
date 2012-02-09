/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.election_manager;

import com.myapp.struts.hbm.ElectionDAO;
import com.myapp.struts.hbm.Position1;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Edrp-04
 */
public class FindPosition extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        StringBuffer emp_ids = new StringBuffer();
       
        ElectionDAO electiondao=new ElectionDAO();
HttpSession session=request.getSession();
        String searchText = request.getParameter("getSubLibrary_Id");
        String instituteId=request.getParameter("instituteid");
        List position1 =electiondao.Position1(instituteId, searchText);

        Iterator it = position1.iterator();
        int tcount=0;
        emp_ids.append("<sublibrary_ids>");
        while (it.hasNext())
        {

//construct the xml string.
            //SubLibrary sublibpojo = (SubLibrary)sublibrary.get(tcount);
Position1 position =(Position1)position1.get(tcount);

                emp_ids.append("<position_id>"+position.getId().getPositionId()+"</position_id>");
                emp_ids.append("<position_name>"+position.getPositionName()+"</position_name>");
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
