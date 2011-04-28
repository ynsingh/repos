/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
import  com.myapp.struts.hbm.*;
import  com.myapp.struts.AdminDAO.*;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 *
 * @author Dushyant
 */
public class StaffDetailAction extends org.apache.struts.action.Action {

    
    private String library_id;
    private String sublibrary_id;
    private String library_name;
    private String staff_id;
    private String first_name;
    private String last_name;
    private String contact_no;
    private String mobile_no;
    private String email_id;
    private String do_joining;
    private String do_releaving;
    private String date_of_birth;
    private String address1;
    private String city1;
    private String state1;
    private String country1;
    private String zip1;
    private String address2;
    private String city2;
    private String state2;
    private String country2;
    private String zip2;
    private String father_name;
    private String gender;
    private String courtesy;
    private String role;
    public boolean result;
     private byte[] imagefile;

    Connection con;
    int i=0;

   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
HttpSession session1 =request.getSession();
ImageUploadActionForm form1 = (ImageUploadActionForm)session1.getAttribute("ImageUploadActionForm");

        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
        library_name=(String)session.getAttribute("library_name");
String mainlib=(String)session.getAttribute("mainsublibrary");
        StaffDetailActionForm staff=(StaffDetailActionForm)form;

sublibrary_id=(String)session.getAttribute("sublibrary_id");
if(sublibrary_id.equalsIgnoreCase(mainlib)==true){
/*Combox box of sublibrary gives name of sublibrary and to get sublibrary_id we neeed to use it when insti-admin is login*/

 
    sublibrary_id=staff.getSublibrary_id();
}
else{
    sublibrary_id=staff.getSublibrary_id();
}

        staff_id=staff.getEmployee_id();
        first_name=staff.getFirst_name();
        last_name=staff.getLast_name();
        contact_no=staff.getContact_no();
        mobile_no=staff.getMobile_no();
        email_id=staff.getEmail_id();
        do_joining=staff.getDo_joining();
        date_of_birth=staff.getDate_of_birth();
        do_releaving=staff.getDo_releaving();
        address1=staff.getAddress1();
        city1=staff.getCity1();
        state1=staff.getState1();
        country1=staff.getCountry1();
        zip1=staff.getZip1();
        address2=staff.getAddress2();
        city2=staff.getCity2();
        state2=staff.getState2();
        country2=staff.getCountry2();
        zip2=staff.getZip2();
        father_name=staff.getFather_name();
        gender=staff.getGender();
        courtesy=staff.getCourtesy();

       System.out.println(do_joining+do_releaving+date_of_birth);
         
     
         Library libobj=new Library();
            libobj.setLibraryId(library_id);
            libobj.setLibraryName(library_name);
            libobj.setWorkingStatus("OK");
            libobj.setStaffId(staff_id);


        /* Use to Insert New Staff Entry related to Library Table & SubLibrary Table */
            StaffDetailId staffidobj=new StaffDetailId(staff_id,library_id);
            StaffDetail staffobj=new StaffDetail(staffidobj, libobj, sublibrary_id) ;


             if(staff.getCourtesy().equals("Select")==false)
               staffobj.setTitle(staff.getCourtesy());

            staffobj.setFirstName(first_name);
            staffobj.setLastName(last_name);
            staffobj.setEmailId(email_id);

            if(contact_no!=null)
           staffobj.setContactNo(contact_no);

            if(mobile_no!=null)
            staffobj.setMobileNo(mobile_no);


            if(do_joining.equals("")==false)
            {
               staffobj.setDateJoining(Date.valueOf(do_joining));

            }

            if(do_releaving.equals("")==false)
            {
                staffobj.setDateReleaving(Date.valueOf(do_releaving));
            }
            if(father_name!=null)
             staffobj.setFatherName(father_name);

            if(date_of_birth.equals("")==false)
            {
             staffobj.setDateOfBirth(Date.valueOf(date_of_birth));
             }

            if(gender.equals("Select")==false)
             staffobj.setGender(gender);

            staffobj.setAddress1(address1);
            staffobj.setCity1(city1);
            staffobj.setState1(state1);
            staffobj.setCountry1(country1);

            if(zip1!=null)

             staffobj.setZip1(zip1);

            if(address2!=null)
            staffobj.setAddress2(address2);
            if(city2!=null)
            staffobj.setCity2(city2);
            if(state2!=null)
            staffobj.setState2(state2);

            if(country2!=null)
            staffobj.setCountry2(country2);
            
            if(zip2!=null)
            staffobj.setZip2(zip2);

                if (form1!=null)
                 staffobj.setStaffImage(form1.getImg().getFileData());




             result=StaffDetailDAO.insert1(staffobj);
                if(result==false)
                {
                    String msg="Request for registration failure due to some error";
                    request.setAttribute("msg", msg);
                    return mapping.findForward("errorp");

                }
                else{




        
       
        request.setAttribute("staff_id",staff_id );
        request.setAttribute("library_id",library_id );
        request.setAttribute("sublibrary_id",sublibrary_id );
        request.setAttribute("first_name",first_name );
        request.setAttribute("last_name",last_name );
        request.setAttribute("email_id",email_id );
        return mapping.findForward("success");

        }
       
       

      
    }
}


