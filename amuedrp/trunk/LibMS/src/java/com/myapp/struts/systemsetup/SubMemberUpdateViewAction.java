/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import com.myapp.struts.hbm.*;
import com.myapp.struts.systemsetupDAO.*;
import com.myapp.struts.CirculationDAO.CirculationDAO;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author edrp02
 */
public class SubMemberUpdateViewAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String button;
    String sub_emptype_full_name;
    String emptype_full_name;
    String sub_emptype_id;
    String no_of_issueable_book;
    String library_id;
    String emptype_id;
    boolean result;
    private String sublibrary_id;
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
        SubMemberUpdateViewActionForm smuvaf=(SubMemberUpdateViewActionForm)form;
        button=smuvaf.getButton();
        sub_emptype_full_name=smuvaf.getSub_emptype_full_name();
        emptype_full_name=smuvaf.getEmptype_full_name();
        sub_emptype_id=smuvaf.getSub_emptype_id();
        emptype_id=smuvaf.getEmptype_id();

        no_of_issueable_book=smuvaf.getNo_of_issueable_book();
        
        library_id=(String)session.getAttribute("library_id");
        sublibrary_id=(String)session.getAttribute("sublibrary_id");
        SubEmployeeType subemptype=SubMemberDAO.getSubEployeeName(library_id,emptype_id,sub_emptype_id);
         if(button.equals("Update"))
        {
             List<CirMemberAccount> cirobj=null;
            if(!subemptype.getNoOfIssueableBook().equals(no_of_issueable_book))
            {
              cirobj=(List<CirMemberAccount>)CirculationDAO.searchCirMemAccountDetailsBySubMember(library_id,emptype_id,sub_emptype_id);
             if(cirobj!=null && !cirobj.isEmpty())
{
                 Iterator it = cirobj.iterator();
                 Integer i=0;
                 while(it.hasNext())
                 {
                     
                     CirMemberAccount cirmem = (CirMemberAccount)cirobj.get(i);
                     cirmem.setNoOfIssueableBook(no_of_issueable_book);
                     cirobj.set(i, cirmem);
                     it.next();
                     i++;
                     
}
             }
            }
             subemptype.setSubEmptypeFullName(sub_emptype_full_name);
             subemptype.setNoOfIssueableBook( Integer.parseInt(no_of_issueable_book));






             result=SubMemberDAO.Update(subemptype,cirobj);

             if(result==true)
             {
                 //request.setAttribute("msg", "Record Update Successfully");
                 request.setAttribute("msg", resource.getString("circulation.circulationnewmemberregAction.recupdatesucc"));
              return mapping.findForward("success");
             }
             else
             {
                //request.setAttribute("msg1", "Record Not Update");
                 request.setAttribute("msg1", resource.getString("circulation.circulationnewmemberregAction.recupdatenotsecc"));
              return mapping.findForward("success");
             }

        }
        if(button.equals("Delete"))
        {
            List<CirMemberAccount> cir=   (List<CirMemberAccount>)MemberDAO.searchAccount(library_id,emptype_id,sub_emptype_id);
           if(!cir.isEmpty()){

           //   request.setAttribute("msg1", "Account Created With SubMember,Cannot Deleted");
            request.setAttribute("msg1", resource.getString("systemsetup.submemupdateviewaction.acccreatedwithsubmemcanotdel"));
              return mapping.findForward("success");
           }


            result=SubMemberDAO.Delete(subemptype);
             if(result==true)
             {
               // request.setAttribute("msg", "Record Deleted Successfully");
              request.setAttribute("msg", resource.getString("circulation.circulationnewmemberregAction.recdelsucc"));
              return mapping.findForward("success");
             }
             else
             {
                List<BookCategory> book=(List<BookCategory>)BookCategoryDAO.searchBookCategoryBySubMemberId(library_id,emptype_id,sub_emptype_id);
                if(!book.isEmpty())
                {
                 // request.setAttribute("msg", "Please Remove Related Data from Document Category");

              request.setAttribute("msg", resource.getString("systemsetup.submemupdateviewaction.plzremoverelateddatafromdocdetail"));
              return mapping.findForward("success");
                }else{
                  //  request.setAttribute("msg1", "Record not Deleted");
              request.setAttribute("msg1", resource.getString("circulation.circulationnewmemberregAction.memnotdelsucc"));
              return mapping.findForward("success");

                }
             }


        }


        
        return mapping.findForward(SUCCESS);
    }
}
