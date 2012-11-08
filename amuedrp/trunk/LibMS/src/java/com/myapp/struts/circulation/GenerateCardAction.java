/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import com.myapp.struts.CirDAO.CirculationDAO;
import com.myapp.struts.hbm.CirMemberAccount;
import com.myapp.struts.hbm.CirMemberDetail;
import com.myapp.struts.hbm.Courses;
import com.myapp.struts.hbm.Department;
import com.myapp.struts.hbm.EmployeeType;
import com.myapp.struts.hbm.Faculty;
import com.myapp.struts.systemsetupDAO.CourseDAO;
import com.myapp.struts.systemsetupDAO.DeptDAO;
import com.myapp.struts.systemsetupDAO.FacultyDAO;
import com.myapp.struts.systemsetupDAO.MemberCategoryDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class GenerateCardAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String memid,library_id;
    Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";
    private String sublibrary_id;
    private String button;
   CirculationDAO cirdao=new CirculationDAO();

MemberCategoryDAO memdao=new MemberCategoryDAO();
DeptDAO deptdao=new DeptDAO();
FacultyDAO facdao=new FacultyDAO();
CourseDAO coursedao=new CourseDAO();

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
session.removeAttribute("cirmemaccountdetail");
session.removeAttribute("cirmemdetail");


        GenerateCardActionForm myform=(GenerateCardActionForm)form;
         CirculationMemberActionForm cmdf=new CirculationMemberActionForm();
        memid=myform.getTXTMEMID();
        button=myform.getButton();


        System.out.println(button+"........................");
       
        library_id=(String)session.getAttribute("library_id");
         sublibrary_id=(String)session.getAttribute("sublibrary_id");
        CirMemberDetail memobj=cirdao.searchCirMemDetails(library_id, memid);
if(memobj!=null){
       CirMemberAccount cirmemberaccount=cirdao.getAccount2(library_id, sublibrary_id, memid);
                   if(cirmemberaccount!=null)
                   {

                       if(!cirmemberaccount.getStatus().equals("Active"))
                       {

                         //request.setAttribute("msg1","Member Not Active");
                         request.setAttribute("msg1",resource.getString("circulation.cirmemberaccountaction.membernotact"));
                         return mapping.findForward("fail");



                        }else{

                          //for Card Generated Button

                        if(button.equalsIgnoreCase("Generate Card")){

                        String status=cirmemberaccount.getCardStatus();
                                             System.out.println("ddd"+status);
if(status!=null && status.equalsIgnoreCase("Issued")){

 //request.setAttribute("msg1","Card Is Already Issued to Member on "+cirmemberaccount.getCardIssueDate();
                         request.setAttribute("msg1","Card Is Already Issued to Member "+cirmemberaccount.getId().getMemid()+" on "+cirmemberaccount.getCardIssueDate());
                         return mapping.findForward("fail");

}
else if(status!=null && status.equalsIgnoreCase("Losts")){

 //request.setAttribute("msg1","Card reported to be lost by  Member ";
                         request.setAttribute("msg1","Card reported to be lost by  Member "+cirmemberaccount.getId().getMemid() +" on "+cirmemberaccount.getLostDate());
                         return mapping.findForward("fail");

}
else{
 
session.setAttribute("cirmemaccountdetail", cirmemberaccount);
session.setAttribute("cirmemdetail", memobj);


  List<EmployeeType> emp=(List<EmployeeType>)memdao.searchEmpType(library_id);

        List<Department> dep=(List<Department>)deptdao.getDeptLibrary("library_id");
                List<Faculty> fac =(List<Faculty>)facdao.searchFaculty(library_id);
                List<Courses> course =(List<Courses>)coursedao.getCourse(library_id);

session.setAttribute("list",emp);
session.setAttribute("list2",fac);

             session.setAttribute("departmentname",dep);
             session.setAttribute("facultyname",fac);
             session.setAttribute("coursename",course);


           
            myform.setTXTMEMID(memobj.getId().getMemId());
            myform.setTXTFNAME(memobj.getFname());
            myform.setTXTMNAME(memobj.getMname());
            myform.setTXTLNAME(memobj.getLname());
            myform.setTXTEMAILID(memobj.getEmail());
            myform.setTXTADD1(memobj.getAddress1());
            myform.setTXTADD2(memobj.getAddress2());
            myform.setTXTCITY1(memobj.getCity1());
            myform.setTXTSTATE1(memobj.getState1());
            myform.setTXTCOUNTRY1(memobj.getCountry1());
            myform.setTXTCITY2(memobj.getCity2());
            myform.setTXTSTATE2(memobj.getState2());
            myform.setTXTCOUNTRY2(memobj.getCountry2());
            myform.setTXTPH1(memobj.getPhone1());
            myform.setTXTPH2(memobj.getPhone2());
            myform.setTXTPIN1(memobj.getPin1());
            myform.setTXTPIN2(memobj.getPin2());
            myform.setTXTFAX(memobj.getFax());
            myform.setMEMCAT( cirmemberaccount.getMemType());
            myform.setMEMSUBCAT( cirmemberaccount.getSubMemberType());
            myform.setTXTOFFICE( cirmemberaccount.getOffice());
            myform.setTXTDESG1( cirmemberaccount.getDesg());
            myform.setTXTFACULTY( cirmemberaccount.getFacultyId());
            myform.setTXTDEPT( cirmemberaccount.getDeptId());
            myform.setTXTCOURSE( cirmemberaccount.getCourseId());
            myform.setTXTSEM( cirmemberaccount.getSemester());
            myform.setTXTREG_DATE( cirmemberaccount.getReqDate());
            myform.setTXTEXP_DATE( cirmemberaccount.getExpiryDate());
 request.setAttribute("button", "Generate Card");

        return mapping.findForward(SUCCESS);
}
                        
                        }
                        else if(button.equalsIgnoreCase("Lost Card")){

  String status=cirmemberaccount.getCardStatus();
        if(status!=null && status.equalsIgnoreCase("Losts")){

 //request.setAttribute("msg1","Card reported to be lost by  Member ";
                         request.setAttribute("msg1","Card reported to be lost by  Member "+cirmemberaccount.getId().getMemid() +" on "+cirmemberaccount.getLostDate());
                         return mapping.findForward("fail");

}
else{



session.setAttribute("cirmemaccountdetail", cirmemberaccount);
session.setAttribute("cirmemdetail", memobj);




        List<Department> dep=(List<Department>)deptdao.getDeptLibrary("library_id");
                List<Faculty> fac =(List<Faculty>)facdao.searchFaculty(library_id);
                List<Courses> course =(List<Courses>)coursedao.getCourse(library_id);


             session.setAttribute("departmentname",dep);
             session.setAttribute("facultyname",fac);
             session.setAttribute("coursename",course);


          
            cmdf.setTXTMEMID(memobj.getId().getMemId());
            cmdf.setTXTFNAME(memobj.getFname());
            cmdf.setTXTMNAME(memobj.getMname());
            cmdf.setTXTLNAME(memobj.getLname());
            cmdf.setTXTEMAILID(memobj.getEmail());
            cmdf.setTXTADD1(memobj.getAddress1());
            cmdf.setTXTADD2(memobj.getAddress2());
            cmdf.setTXTCITY1(memobj.getCity1());
            cmdf.setTXTSTATE1(memobj.getState1());
            cmdf.setTXTCOUNTRY1(memobj.getCountry1());
            cmdf.setTXTCITY2(memobj.getCity2());
            cmdf.setTXTSTATE2(memobj.getState2());
            cmdf.setTXTCOUNTRY2(memobj.getCountry2());
            cmdf.setTXTPH1(memobj.getPhone1());
            cmdf.setTXTPH2(memobj.getPhone2());
            cmdf.setTXTPIN1(memobj.getPin1());
            cmdf.setTXTPIN2(memobj.getPin2());
            cmdf.setTXTFAX(memobj.getFax());
            cmdf.setMEMCAT( cirmemberaccount.getMemType());
            cmdf.setMEMSUBCAT( cirmemberaccount.getSubMemberType());
            cmdf.setTXTOFFICE( cirmemberaccount.getOffice());
            cmdf.setTXTDESG1( cirmemberaccount.getDesg());
            cmdf.setTXTFACULTY( cirmemberaccount.getFacultyId());
            cmdf.setTXTDEPT( cirmemberaccount.getDeptId());
            cmdf.setTXTCOURSE( cirmemberaccount.getCourseId());
            cmdf.setTXTSEM( cirmemberaccount.getSemester());
            cmdf.setTXTREG_DATE( cirmemberaccount.getReqDate());
            cmdf.setTXTEXP_DATE( cirmemberaccount.getExpiryDate());
            request.setAttribute("button", "Lost Card");


        return mapping.findForward(SUCCESS);
}
                        }
                          else if(button.equalsIgnoreCase("Duplicate Card")){
 String status=cirmemberaccount.getCardStatus();
        if(status!=null && !status.equalsIgnoreCase("Losts")){

 //request.setAttribute("msg1","Card reported to be lost by  Member ";
                         request.setAttribute("msg1","Card not reported to be lost by  Member "+cirmemberaccount.getId().getMemid() +", Cannot Generate Duplicate");
                         return mapping.findForward("fail");

}
else{



session.setAttribute("cirmemaccountdetail", cirmemberaccount);
session.setAttribute("cirmemdetail", memobj);




        List<Department> dep=(List<Department>)deptdao.getDeptLibrary("library_id");
                List<Faculty> fac =(List<Faculty>)facdao.searchFaculty(library_id);
                List<Courses> course =(List<Courses>)coursedao.getCourse(library_id);


             session.setAttribute("departmentname",dep);
             session.setAttribute("facultyname",fac);
             session.setAttribute("coursename",course);



            cmdf.setTXTMEMID(memobj.getId().getMemId());
            cmdf.setTXTFNAME(memobj.getFname());
            cmdf.setTXTMNAME(memobj.getMname());
            cmdf.setTXTLNAME(memobj.getLname());
            cmdf.setTXTEMAILID(memobj.getEmail());
            cmdf.setTXTADD1(memobj.getAddress1());
            cmdf.setTXTADD2(memobj.getAddress2());
            cmdf.setTXTCITY1(memobj.getCity1());
            cmdf.setTXTSTATE1(memobj.getState1());
            cmdf.setTXTCOUNTRY1(memobj.getCountry1());
            cmdf.setTXTCITY2(memobj.getCity2());
            cmdf.setTXTSTATE2(memobj.getState2());
            cmdf.setTXTCOUNTRY2(memobj.getCountry2());
            cmdf.setTXTPH1(memobj.getPhone1());
            cmdf.setTXTPH2(memobj.getPhone2());
            cmdf.setTXTPIN1(memobj.getPin1());
            cmdf.setTXTPIN2(memobj.getPin2());
            cmdf.setTXTFAX(memobj.getFax());
            cmdf.setMEMCAT( cirmemberaccount.getMemType());
            cmdf.setMEMSUBCAT( cirmemberaccount.getSubMemberType());
            cmdf.setTXTOFFICE( cirmemberaccount.getOffice());
            cmdf.setTXTDESG1( cirmemberaccount.getDesg());
            cmdf.setTXTFACULTY( cirmemberaccount.getFacultyId());
            cmdf.setTXTDEPT( cirmemberaccount.getDeptId());
            cmdf.setTXTCOURSE( cirmemberaccount.getCourseId());
            cmdf.setTXTSEM( cirmemberaccount.getSemester());
            cmdf.setTXTREG_DATE( cirmemberaccount.getReqDate());
            cmdf.setTXTEXP_DATE( cirmemberaccount.getExpiryDate());
            request.setAttribute("button", "Duplicate Card");
            


        return mapping.findForward(SUCCESS);
}


                          }






                        }
                }else{
                    //request.setAttribute("msg1","Member Not Active");
                         request.setAttribute("msg1",resource.getString("circulation.cirmemberaccountaction.membernotact"));
                         return mapping.findForward("fail");

                   }
}
        else{
//request.setAttribute("msg1","Member Not Registered");
             request.setAttribute("msg1",resource.getString("circulation.cirmemberaccountaction.membernotreg"));
             return mapping.findForward("fail");

       
        }
        return null;
    }
}
