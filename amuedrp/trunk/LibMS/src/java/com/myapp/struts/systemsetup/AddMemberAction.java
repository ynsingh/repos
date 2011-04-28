/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.*;
import com.myapp.struts.systemsetupDAO.MemberDAO;

/**
 *
 * @author edrp02
 */
public class AddMemberAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String emptype_id;
    String emptype_full_name;
    String library_id;
    boolean result;
    EmployeeType et=new EmployeeType();
    EmployeeTypeId etid=new EmployeeTypeId();

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
        AddMemberActionForm amaf=(AddMemberActionForm)form;
        emptype_id=amaf.getEmptype_id();
        emptype_full_name=amaf.getEmptype_full_name();
        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
        EmployeeType echeck=MemberDAO.getEmployeeByName(library_id, emptype_full_name);
        if(echeck==null){
        etid.setEmptypeId(emptype_id);
        etid.setLibraryId(library_id);
        et.setId(etid);
        et.setEmptypeFullName(emptype_full_name);
        result=MemberDAO.insert(et);
        if(result==true)
        {
            request.setAttribute("msg", "Record Inserted Successfully");
            return mapping.findForward("success");

        }
        else
        {
            request.setAttribute("msg", "Record Not Inserted");
            return mapping.findForward("success");
        }
        }else{
            request.setAttribute("msg1", "Duplicate Member Type Name");
            return mapping.findForward("success");
        }
        
    }
}
