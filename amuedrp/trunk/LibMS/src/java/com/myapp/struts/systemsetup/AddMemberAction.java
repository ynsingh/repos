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
import java.util.Locale;
import java.util.ResourceBundle;

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
   Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
          HttpSession session=request.getSession();
MemberDAO memdao=new MemberDAO();
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
        AddMemberActionForm amaf=(AddMemberActionForm)form;
        emptype_id=amaf.getEmptype_id();
        emptype_full_name=amaf.getEmptype_full_name();
      
        library_id=(String)session.getAttribute("library_id");
        EmployeeType echeck=memdao.getEmployeeByName(library_id, emptype_full_name);
        if(echeck==null){
        etid.setEmptypeId(emptype_id);
        etid.setLibraryId(library_id);
        et.setId(etid);
        et.setEmptypeFullName(emptype_full_name);
        result=memdao.insert(et);
        if(result==true)
        {
           // request.setAttribute("msg", "Record Inserted Successfully");
            request.setAttribute("msg", resource.getString("circulation.circulationnewmemberregAction.recinsesucc"));
            return mapping.findForward("success");

        }
        else
        {
            //request.setAttribute("msg", "Record Not Inserted");
            request.setAttribute("msg", resource.getString("circulation.circulationnewmemberregAction.recnotinsesucc"));
            return mapping.findForward("success");
        }
        }else{
            //request.setAttribute("msg1", "Duplicate Member Type Name");
            request.setAttribute("msg1", resource.getString("systemsetup.addmemberAction.duplicatememtypename"));
            return mapping.findForward("success");
        }
        
    }
}
