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
public class CirShowSingleAccountAction1 extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String library_id,sublibrary_id,mem_id,status;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CirculationDAO cirdao=new CirculationDAO();
        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
        sublibrary_id=(String)session.getAttribute("sublibrary_id");
        mem_id=(String)request.getParameter("id");
        status=(String)request.getParameter("status");
        CirMemberAccount cmaccount;
        CirMemberDetail cmemdetail=cirdao.searchCirMemDetails(library_id, mem_id);
        

//System.out.println(status+" ............ "+mem_id);
if(status==null)
{
            cmaccount=cirdao.searchCirMemAccountDetails(library_id, sublibrary_id, mem_id);
            request.setAttribute("fname",cmemdetail.getFname());
          request.setAttribute("status", status);
            request.setAttribute("cmaccount",cmaccount);
           return mapping.findForward("success");
}
if(status.equalsIgnoreCase("Renewal")){

        cmaccount=cirdao.searchCirMemAccountDetails2(library_id, sublibrary_id, mem_id,"Cancel");
        }
else if(status.equalsIgnoreCase("Blocked")){

        cmaccount=cirdao.searchCirMemAccountDetails2(library_id, sublibrary_id, mem_id,"Active");
        }
       

else if(status.equalsIgnoreCase("Cancel")){

        cmaccount=cirdao.searchCirMemAccountDetails2(library_id, sublibrary_id, mem_id,"Active");
        }
       
 else{
        cmaccount=cirdao.searchCirMemAccountDetails2(library_id, sublibrary_id, mem_id,"Blocked");
        }



        if(cmaccount!=null)
        {
          request.setAttribute("fname",cmemdetail.getFname());
          request.setAttribute("status", status);
            request.setAttribute("cmaccount",cmaccount);
           return mapping.findForward("success");
        }
        else{

        request.setAttribute("msg1","Member with Id "+mem_id+" Already Cancel/Blocked");
           return mapping.findForward("fail");
        }


        
    }
}
