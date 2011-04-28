/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import com.myapp.struts.hbm.*;
import com.myapp.struts.systemsetupDAO.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class AddSubMemberAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String emptype_id;
    String sub_emptype_id;
    String sub_emptype_full_name;
    String emptype_full_name;
    String library_id;
    String no_of_issueable_book;
    boolean result;
    SubEmployeeType sat=new SubEmployeeType();
    SubEmployeeTypeId satid=new SubEmployeeTypeId();
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AddSubMemberActionForm asmaf=(AddSubMemberActionForm)form;
        sub_emptype_id=asmaf.getSub_emptype_id();
        sub_emptype_full_name=asmaf.getSub_emptype_full_name();
        emptype_full_name=asmaf.getEmptype_full_name();
        no_of_issueable_book=asmaf.getNo_of_issueable_book();
        emptype_id=asmaf.getEmptype_id();
        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");

        SubEmployeeType emptype1=MemberDAO.getSubEmployeeByName(library_id,emptype_id, sub_emptype_full_name);
        System.out.println("SubMemberType="+emptype1);
        if(emptype1==null){
        satid.setLibraryId(library_id);
        satid.setSubEmptypeId(sub_emptype_id);
        satid.setEmptypeId(emptype_id);
        sat.setId(satid);
        sat.setSubEmptypeFullName(sub_emptype_full_name);
        sat.setNoOfIssueableBook( Integer.parseInt(no_of_issueable_book));
        result=SubMemberDAO.insert(sat);
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
        }
        else
        {
            request.setAttribute("msg1", "Duplicate Sub Member Type Name");
            return mapping.findForward("success");
        }
        
    }
}
