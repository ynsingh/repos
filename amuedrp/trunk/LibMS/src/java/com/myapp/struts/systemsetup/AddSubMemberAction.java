/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import com.myapp.struts.hbm.*;
import com.myapp.struts.systemsetupDAO.*;
import java.util.Locale;
import java.util.ResourceBundle;
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
     Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session=request.getSession();
         try{

        locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
        System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align = "left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
        AddSubMemberActionForm asmaf=(AddSubMemberActionForm)form;
        sub_emptype_id=asmaf.getSub_emptype_id();
        sub_emptype_full_name=asmaf.getSub_emptype_full_name();
        emptype_full_name=asmaf.getEmptype_full_name();
        no_of_issueable_book=asmaf.getNo_of_issueable_book();
        emptype_id=asmaf.getEmptype_id();
        
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
           //request.setAttribute("msg", "Record Inserted Successfully");
            request.setAttribute("msg", resource.getString("circulation.circulationnewmemberregAction.recinsesucc"));
            return mapping.findForward("success");

        }
        else
        {
            // request.setAttribute("msg", "Record Not Inserted");
            request.setAttribute("msg", resource.getString("circulation.circulationnewmemberregAction.recnotinsesucc"));
            return mapping.findForward("success");
        }
        }
        else
        {
            //request.setAttribute("msg1", "Duplicate Sub Member Type Name");
            request.setAttribute("msg1", resource.getString("systemsetup.addmemaction.duplisubmemtypename"));
            return mapping.findForward("success");
        }
        
    }
}
