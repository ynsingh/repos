/*
      Design by Iqubal Ahmad
      Modified on 2011-02-02
     This Action Class is meant for Checking Duplicasy first During member Registeration process.
     And if it is not Duplicate Cir_view_member.jsp will appear.
     This Action Class also send faculty_resultset Object For Calling Ajax in Fac,Dept, & Course.
 */

package com.myapp.struts.circulation;
import com.myapp.struts.AdminDAO.SubLibraryDAO;
import com.myapp.struts.systemsetupDAO.*;

import com.myapp.struts.hbm.*;
import com.myapp.struts.systemsetupDAO.MemberCategoryDAO;
import com.myapp.struts.CirculationDAO.CirculationDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Iqubal Ahmad
 */
public class CirculationMemberAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
   String mem_id;
   String button;
   String library_id;
   String sublibrary_id;
   String sql;
   List list1;
   List list2;
   List list;
  // List list4;
//   List list5;

    /**
      Design by Iqubal Ahmad
      Modified on 2011-02-02
     This Action Class is meant for Checking Duplicasy first During member Registeration process.
     And if it is not Duplicate Cir_view_member.jsp will appear.
     This Action Class also send faculty_resultset Object For Calling Ajax in Fac,Dept, & Course.
   
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CirculationMemberRegActionForm acqRegisterActionForm =(CirculationMemberRegActionForm)form;
        
        mem_id=acqRegisterActionForm.getTXTMEMID();


        button=acqRegisterActionForm.getButton();
    
        HttpSession session=request.getSession();
        session.setAttribute("page1", button);
        library_id=(String)session.getAttribute("library_id");
        sublibrary_id=(String)session.getAttribute("sublibrary_id");


        
        
          list1=(List)MemberCategoryDAO.searchEmpType(library_id);
          System.out.println(list1.size());
          list2=(List)FacultyDAO.searchFaculty(library_id);

          list = (List)SubLibraryDAO.searchAccessibleSubLib(library_id,sublibrary_id);

            session.setAttribute("list",list);
            session.setAttribute("list1",list1);
            session.setAttribute("list2",list2);
 
             
              


            if(list.isEmpty()|| list1.isEmpty())
            {
             request.setAttribute("msg1","You Need to Set Member and SubMember Type");
            return mapping.findForward("failure");

            }

              
         
        if(button.equals("Register"))
        {
         CirMemberDetail memobj=(CirMemberDetail)CirculationDAO.searchCirMemDetails(library_id, mem_id);
        // System.out.println("Check1"+memobj);
         if(memobj!=null)
         {
            request.setAttribute("msg1", "member Id : "+mem_id+" already Registered");
            return mapping.findForward("failure");
         }
         else
         {
            session.removeAttribute("cirmemaccountdetail");
session.removeAttribute("cirmemdetail");
//session.removeAttribute("CirculationMemberRegActionForm");
session.removeAttribute("button");
session.removeAttribute("filename");
             session.setAttribute("memid", mem_id);
             return mapping.findForward("success");
         }
        }
        if(button.equals("Update")||button.equals("View")||button.equals("Delete"))
        {

            session.removeAttribute("cirmemaccountdetail");
            session.removeAttribute("cirmemdetail");
            //session.removeAttribute("CirculationMemberRegActionForm");
          //  session.removeAttribute("CirculationMemberActionForm");
           // session.removeAttribute("button");
           // session.removeAttribute("filename");


             CirMemberDetail memobj=CirculationDAO.searchCirMemDetails(library_id, mem_id);
      
                if(memobj==null)
                {
                    request.setAttribute("msg1", "member Id : "+mem_id+" Does Not Exists");
                    return mapping.findForward("failure");
                }
                else
                {
                       











CirMemberAccount cirmem=(CirMemberAccount)CirculationDAO.searchCirMemAccountDetails(library_id, sublibrary_id, mem_id);
CirMemberDetail cirmemobj=(CirMemberDetail)CirculationDAO.searchCirMemDetails(library_id, mem_id);


System.out.println(cirmemobj+" " +cirmem+ ".........."+button);
if(cirmemobj!=null)
{
    if(cirmem!=null){
request.setAttribute("cirmemaccountdetail", cirmem);
request.setAttribute("cirmemdetail", cirmemobj);
//session.setAttribute("cirmemaccountdetail", cirmem);
//session.setAttribute("cirmemdetail", cirmemobj);
    }
    else
    {
        request.setAttribute("cirmemdetail", cirmemobj);
        //session.setAttribute("cirmemdetail", cirmemobj);
        List<Department> dep=(List<Department>)DeptDAO.getDeptLibrary("library_id");
                List<Faculty> fac =(List<Faculty>)FacultyDAO.searchFaculty(library_id);
                List<Courses> course =(List<Courses>)CourseDAO.getCourse(library_id);

             request.setAttribute("button", button);
             session.setAttribute("departmentname",dep);
             session.setAttribute("facultyname",fac);
             session.setAttribute("coursename",course);
             return mapping.findForward("single");
    }
    request.setAttribute("button", button);


                        

                          
                
                             return mapping.findForward("view/update/delete");
}else
{
    request.setAttribute("msg1", "member Id : "+mem_id+" Does Not Exists");
                    return mapping.findForward("failure");
}
            
                    }


             }



        
       
        return null;
    }
}
