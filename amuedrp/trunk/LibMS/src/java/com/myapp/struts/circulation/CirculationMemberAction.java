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
import java.util.ResourceBundle;
import com.myapp.struts.hbm.*;
import com.myapp.struts.systemsetupDAO.MemberCategoryDAO;
import com.myapp.struts.CirculationDAO.CirculationDAO;
import java.util.List;
import java.util.Locale;
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
   Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";

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
        CirculationMemberRegActionForm acqRegisterActionForm =(CirculationMemberRegActionForm)form;
        
        mem_id=acqRegisterActionForm.getTXTMEMID();


        button=acqRegisterActionForm.getButton();
    
        
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
            // request.setAttribute("msg1","You Need to Set Member and SubMember Type");
             request.setAttribute("msg1",resource.getString("circulation.circulationmemberaction.errmess2"));
            return mapping.findForward("failure");

            }

              
         
        if(button.equals("Register"))
        {
         CirMemberDetail memobj=(CirMemberDetail)CirculationDAO.searchCirMemDetails(library_id, mem_id);
        
         if(memobj!=null)
         {
             //request.setAttribute("msg1",member Id +mem_id+ " "+ Already Registered )
            request.setAttribute("msg1", resource.getString("circulation.circulationmemberaction.memid")+mem_id+ " "+ resource.getString("circulation.circulationmemberaction.errmess"));
            return mapping.findForward("failure");
         }
         else
         {

             session.setAttribute("memid", mem_id);
             return mapping.findForward("success");
         }
        }
        if(button.equals("Update")||button.equals("View")||button.equals("Delete"))
        {

           
           

             CirMemberDetail memobj=CirculationDAO.searchCirMemDetails(library_id, mem_id);
      
                if(memobj==null)
                {
                   // request.setAttribute("msg1", "member Id : "+mem_id+" Does Not Exists");
                    request.setAttribute("msg1", resource.getString("circulation.circulationmemberaction.memid")+mem_id+" "+resource.getString("circulation.circulationmemberaction.errmess1"));
                    return mapping.findForward("failure");
                }
                else
                {
                       











CirMemberAccount cirmem=(CirMemberAccount)CirculationDAO.searchCirMemAccountDetails(library_id, sublibrary_id, mem_id);
CirMemberDetail cirmemobj=(CirMemberDetail)CirculationDAO.searchCirMemDetails(library_id, mem_id);


System.out.println(cirmemobj+" " +cirmem+ ".........."+button);
session.setAttribute("cirmemaccountdetail", cirmem);
session.setAttribute("cirmemdetail", cirmemobj);
 
if(cirmemobj!=null)
{
    if(cirmem!=null){
session.setAttribute("cirmemaccountdetail", cirmem);
session.setAttribute("cirmemdetail", cirmemobj);

    }
    else
    {
      //  request.setAttribute("cirmemdetail", cirmemobj);
        session.setAttribute("cirmemdetail", cirmemobj);
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

            CirculationMemberActionForm cmdf=new CirculationMemberActionForm();
            cmdf.setTXTMEMID(cirmemobj.getId().getMemId());
            cmdf.setTXTFNAME(cirmemobj.getFname());
            cmdf.setTXTMNAME(cirmemobj.getMname());
            cmdf.setTXTLNAME(cirmemobj.getLname());
            cmdf.setTXTEMAILID(cirmemobj.getEmail());
            cmdf.setTXTADD1(cirmemobj.getAddress1());
            cmdf.setTXTADD2(cirmemobj.getAddress2());
            cmdf.setTXTCITY1(cirmemobj.getCity1());
            cmdf.setTXTSTATE1(cirmemobj.getState1());
            cmdf.setTXTCOUNTRY1(cirmemobj.getCountry1());
            cmdf.setTXTCITY2(cirmemobj.getCity2());
            cmdf.setTXTSTATE2(cirmemobj.getState2());
            cmdf.setTXTCOUNTRY2(cirmemobj.getCountry2());
            cmdf.setTXTPH1(cirmemobj.getPhone1());
            cmdf.setTXTPH2(cirmemobj.getPhone2());
            cmdf.setTXTPIN1(cirmemobj.getPin1());
            cmdf.setTXTPIN2(cirmemobj.getPin2());
            cmdf.setTXTFAX(cirmemobj.getFax());
            cmdf.setMEMCAT(cirmem.getMemType());
            cmdf.setMEMSUBCAT(cirmem.getSubMemberType());
            cmdf.setTXTOFFICE(cirmem.getOffice());
            cmdf.setTXTDESG1(cirmem.getDesg());
            cmdf.setTXTFACULTY(cirmem.getFacultyId());
            cmdf.setTXTDEPT(cirmem.getDeptId());
            cmdf.setTXTCOURSE(cirmem.getCourseId());
            cmdf.setTXTSEM(cirmem.getSemester());
            cmdf.setTXTREG_DATE(cirmem.getReqDate());
            cmdf.setTXTEXP_DATE(cirmem.getExpiryDate());

                             return mapping.findForward("view/update/delete");
}else
{
    //request.setAttribute("msg1", "member Id : "+mem_id+" Does Not Exists");
    request.setAttribute("msg1", resource.getString("circulation.circulationmemberaction.memid")+mem_id+" "+resource.getString("circulation.circulationmemberaction.errmess1") );
                    return mapping.findForward("failure");
}
            
                    }


             }



        
       
        return null;
    }
}
