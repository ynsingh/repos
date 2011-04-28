/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import com.myapp.struts.CirculationDAO.CirculationDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.circulationDAO.cirDAO;
import com.myapp.struts.hbm.*;
import com.myapp.struts.systemsetupDAO.FacultyDAO;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author edrp02
 */
public class CirMemberAccountAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String mem_id,button,library_id,sublibrary_id;
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CirMemberAccountActionForm cmaaf=(CirMemberAccountActionForm)form;
        mem_id=cmaaf.getMem_id();
        button=cmaaf.getButton();
        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
        sublibrary_id=(String)session.getAttribute("sublibrary_id");


                 String sublibraryId = "";






        if(button.equals("Create"))
        {
           CirMemberDetail cirmemberdetail=cirDAO.getMemberDetail(library_id, mem_id);
           if(cirmemberdetail!=null)
           {

           
                                  
                  request.setAttribute("mem_id",mem_id);
                  System.out.println(mem_id);
                 List<EmployeeType> list1 = (List<EmployeeType>)CirculationDAO.getAllEmployeeTypes(library_id);
                  List<Faculty> list3 = (List<Faculty>)CirculationDAO.getAllFaculty(library_id);
                  List<SubEmployeeType> list2 = (List<SubEmployeeType>)CirculationDAO.getAllSubEmployeeTypes(library_id);
                  List<SubLibrary> libRs=null;
                  if(sublibrary_id.equals(library_id))
                    libRs = (List<SubLibrary>)CirculationDAO.getAllSubLibrary(library_id,sublibraryId,mem_id);
                  else
                      libRs = (List<SubLibrary>)CirculationDAO.getAllSubLibrary1(library_id,sublibrary_id,mem_id);
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
                      request.setAttribute("msg1","Member Account Already Active");
                       return mapping.findForward("fail");
                  }
              


           }
           else
           {
             request.setAttribute("msg1","Member Not Registered");
             return mapping.findForward("fail");

           }

       }


       if(button.equals("Update")||button.equals("View")||button.equals("Delete"))
       {
                  session.removeAttribute("list3");
                  session.removeAttribute("list1");
                  session.removeAttribute("list2");
                  session.removeAttribute("list");
             CirMemberDetail cirmemberdetail=cirDAO.getMemberDetail(library_id, mem_id);
             if(cirmemberdetail!=null)
             {

                   CirMemberAccount cirmemberaccount=cirDAO.getAccount2(library_id, sublibrary_id, mem_id);
                   if(cirmemberaccount!=null)
                   {

                       if(cirmemberaccount.getStatus().equals("Active"))
                       {
                           
                               List<EmployeeType> list1 = (List<EmployeeType>)CirculationDAO.getAllEmployeeTypes(library_id);
                                  List<Faculty> list3 = (List<Faculty>)CirculationDAO.getAllFaculty(library_id);
                                  List<SubEmployeeType> list2 = (List<SubEmployeeType>)CirculationDAO.getAllSubEmployeeTypes(library_id);
                                  List<SubLibrary> libRs=null;
                                  
                                      libRs = (List<SubLibrary>)CirculationDAO.getAllSubLibrary2(library_id,sublibrary_id);
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
                         request.setAttribute("msg1","Member Not Active");
                         return mapping.findForward("fail");

                       }

                   }
                   else
                   {

                      request.setAttribute("msg1","Member is Not Active");
                      return mapping.findForward("fail");

                   }





             }
             else
             {
               request.setAttribute("msg1","Member Not Registered");
               return mapping.findForward("fail");

             }
             
       }

      return mapping.findForward("fail");

    }
}
