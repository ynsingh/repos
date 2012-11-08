/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import com.myapp.struts.CirDAO.CirculationDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.*;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author edrp02
 */
public class CirMemberAccountAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String mem_id,button,library_id,sublibrary_id;
     Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CirculationDAO cirdao=new CirculationDAO();
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
        CirMemberAccountActionForm cmaaf=(CirMemberAccountActionForm)form;
        mem_id=cmaaf.getMem_id();
        button=cmaaf.getButton();
       
        library_id=(String)session.getAttribute("library_id");
        sublibrary_id=(String)session.getAttribute("sublibrary_id");


                 String sublibraryId = "";






        if(button.equals("Create"))
        {
           CirMemberDetail cirmemberdetail=cirdao.getMemberDetail(library_id, mem_id);
           if(cirmemberdetail!=null)
           {

           
                                  
                  request.setAttribute("mem_id",mem_id);
                  System.out.println(mem_id);
                 List<EmployeeType> list1 = (List<EmployeeType>)cirdao.getAllEmployeeTypes(library_id);
                  List<Faculty> list3 = (List<Faculty>)cirdao.getAllFaculty(library_id);
                  List<SubEmployeeType> list2 = (List<SubEmployeeType>)cirdao.getAllSubEmployeeTypes(library_id);
                  List<SubLibrary> libRs=null;
                  if(sublibrary_id.equals(library_id))
                    libRs = (List<SubLibrary>)cirdao.getAllSubLibrary(library_id,sublibraryId,mem_id);
                  else
                      libRs = (List<SubLibrary>)cirdao.getAllSubLibrary1(library_id,sublibrary_id,mem_id);
                  if(libRs!=null && !libRs.isEmpty()){
                  session.setAttribute("list3",list3);
                  session.setAttribute("list1",list1);
                  session.setAttribute("list2",list2);
                  session.setAttribute("cirmemberdetail",cirmemberdetail);
                  session.setAttribute("list",libRs);
                  return mapping.findForward("success");
                  }
                  else
                  {
                     // request.setAttribute("msg1","Member Account Already Active");
                      request.setAttribute("msg1",resource.getString("circulation.cirmemberaccountaction.memalreadyact"));
                       return mapping.findForward("fail");
                  }
              


           }
           else
           {
             //request.setAttribute("msg1","Member Not Registered");
             request.setAttribute("msg1",resource.getString("circulation.cirmemberaccountaction.membernotreg"));
             return mapping.findForward("fail");

           }

       }


       if(button.equals("Update")||button.equals("View")||button.equals("Delete"))
       {
                  session.removeAttribute("list3");
                  session.removeAttribute("list1");
                  session.removeAttribute("list2");
                  session.removeAttribute("list");
             CirMemberDetail cirmemberdetail=cirdao.getMemberDetail(library_id, mem_id);
             if(cirmemberdetail!=null)
             {

                   CirMemberAccount cirmemberaccount=cirdao.getAccount2(library_id, sublibrary_id, mem_id);
                   if(cirmemberaccount!=null)
                   {

                       if(cirmemberaccount.getStatus().equals("Active"))
                       {
                           
                               List<EmployeeType> list1 = (List<EmployeeType>)cirdao.getAllEmployeeTypes(library_id);
                                  List<Faculty> list3 = (List<Faculty>)cirdao.getAllFaculty(library_id);
                                  List<SubEmployeeType> list2 = (List<SubEmployeeType>)cirdao.getAllSubEmployeeTypes(library_id);
                                  List<SubLibrary> libRs=null;
                                  
                                      libRs = (List<SubLibrary>)cirdao.getAllSubLibrary2(library_id,sublibrary_id);
                  if(libRs!=null && !libRs.isEmpty()){
                  
                  System.out.println("button="+button);
                  session.setAttribute("list",libRs);
                  
                           }
                                session.setAttribute("list3",list3);
                                  session.setAttribute("list1",list1);
                                  session.setAttribute("list2",list2);
                                  System.out.println("button="+button);
                                  session.setAttribute("page", button);
                                  session.setAttribute("cirmemberaccount",cirmemberaccount);
                                  session.setAttribute("cirmemberdetail",cirmemberdetail);
                                  return mapping.findForward("view");
                          
                       }
                       else
                       {
                         //request.setAttribute("msg1","Member Not Active");
                         request.setAttribute("msg1",resource.getString("circulation.cirmemberaccountaction.membernotact"));
                         return mapping.findForward("fail");

                       }

                   }
                   else
                   {

                     // request.setAttribute("msg1","Member is Not Active");
                      request.setAttribute("msg1",resource.getString("circulation.cirmemberaccountaction.membernotact"));
                      return mapping.findForward("fail");

                   }





             }
             else
             {
              // request.setAttribute("msg1","Member Not Registered");
               request.setAttribute("msg1",resource.getString("circulation.cirmemberaccountaction.membernotreg"));
               return mapping.findForward("fail");

             }
             
       }

      return mapping.findForward("fail");

    }
}
