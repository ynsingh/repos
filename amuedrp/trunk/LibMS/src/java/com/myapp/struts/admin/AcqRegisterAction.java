/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
import  com.myapp.struts.hbm.*;
import  com.myapp.struts.AdminDAO.*;

import java.sql.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.util.ResourceBundle;
/**
 *
 * @author Dushyant
 */
public class AcqRegisterAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private String staff_id;
    private String button;
    private String library_id;
    private String sublibrary_id;
    
    PreparedStatement stmt;
    String sql;
    Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";
    StaffDetailDAO staffdao=new StaffDetailDAO();
    SubLibraryDAO sublibdao=new SubLibraryDAO();
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

        AcqRegisterActionForm acqRegisterActionForm =(AcqRegisterActionForm)form;
        staff_id=acqRegisterActionForm.getStaff_id();
        button=acqRegisterActionForm.getButton();
       
        
        library_id=(String)session.getAttribute("library_id");
        sublibrary_id=(String)session.getAttribute("sublibrary_id");
        String mainlib=(String)session.getAttribute("mainsublibrary");
       System.out.println(button);
        if(button.equals("Register"))
        {
         
         StaffDetail staffobj=(StaffDetail)staffdao.searchStaffId(staff_id,library_id);
           

         if(staffobj!=null)
         {
           // request.setAttribute("msg1", "Staff Id : "+staff_id+" already exists");
              request.setAttribute("msg1",resource.getString("admin.acq_register.staffId")+ " : "+staff_id+" "+resource.getString("admin.acq_register.exists"));
            return mapping.findForward("duplicate");
         }
         else
         {
             List<SubLibrary>  sublib=sublibdao.searchSubLib(library_id);
             if(!sublib.isEmpty())
             {
                 session.setAttribute("sublib",sublib);
               session.setAttribute("new_staff_id",staff_id);
             return mapping.findForward("register");
             }
             else
             {
                request.setAttribute("msg1", resource.getString("admin.acq_register.error"));
                return mapping.findForward("duplicate");


             }
         }
        }
















StaffDetail staffobj;
        if(button.equals("Update")||button.equals("View")||button.equals("Delete"))
        {

             if(sublibrary_id.equalsIgnoreCase(mainlib))
             {


                         staffobj=(StaffDetail)staffdao.searchStaffId(staff_id,library_id);
                        if(staffobj==null)
                        {
                        //request.setAttribute("msg1", "Staff Id: "+staff_id+" doesn't exists");
                        request.setAttribute("msg1",resource.getString("admin.acq_register.staffId")+ " : "+staff_id+" "+resource.getString("admin.acq_register.notexists"));
                        return mapping.findForward("duplicate");
                        }
             }
             else
             {
                        staffobj=(StaffDetail)staffdao.searchStaffId(staff_id,library_id,sublibrary_id);
                        if(staffobj==null)
                        {

                       // request.setAttribute("msg1", "Staff Id: "+staff_id+" doesn't exists");
                       request.setAttribute("msg1",resource.getString("admin.acq_register.staffId")+ " : "+staff_id+" "+resource.getString("admin.acq_register.notexists"));
                        return mapping.findForward("duplicate");
                        }


             }

         if(staffobj!=null)
         {
           
           
            
            StaffDetailActionForm staffform=new StaffDetailActionForm();
            staffform.setLibrary_id(staffobj.getId().getLibraryId());
            staffform.setStaff_id(staffobj.getId().getStaffId());
            staffform.setSublibrary_id(staffobj.getSublibraryId());
         
            if(staffobj.getTitle()!=null) 
                 staffform.setCourtesy(staffobj.getTitle());

            staffform.setFirst_name(staffobj.getFirstName());
            staffform.setLast_name(staffobj.getLastName());

            if(staffobj.getContactNo()!=null)
            staffform.setContact_no(staffobj.getContactNo());

            staffform.setMobile_no(staffobj.getMobileNo());

            staffform.setEmail_id(staffobj.getEmailId());
            if(staffobj.getDateJoining()!=null)
            {
                staffform.setDo_joining(staffobj.getDateJoining().toString());

            }

            if(staffobj.getDateReleaving()!=null)
            {
                staffform.setDo_releaving(staffobj.getDateReleaving().toString());
            }
            staffform.setFather_name(staffobj.getFatherName());
             if(staffobj.getDateOfBirth()!=null)
            {
            staffform.setDate_of_birth(staffobj.getDateOfBirth().toString());
             }

            staffform.setGender(staffobj.getGender());

            staffform.setAddress1(staffobj.getAddress1());
            staffform.setCity1(staffobj.getCity1());
            staffform.setState1(staffobj.getState1());
            staffform.setCountry1(staffobj.getCountry1());
            if(staffobj.getZip1()!=null)

            staffform.setZip1(staffobj.getZip1());

            if(staffobj.getAddress2()!=null)
            staffform.setAddress2(staffobj.getAddress2());
            if(staffobj.getCity2()!=null)
            staffform.setCity2(staffobj.getCity2());
            if(staffobj.getState2()!=null)
            staffform.setState2(staffobj.getState2());

            if(staffobj.getCountry2()!=null)
            staffform.setCountry2(staffobj.getCountry2());
            if(staffobj.getZip2()!=null)
            staffform.setZip2(staffobj.getZip2());
            
            session.setAttribute("updateresultset",staffobj);

            request.setAttribute("button", button);
             List<SubLibrary>  sublib=sublibdao.searchSubLib(library_id);
             if(!sublib.isEmpty())
             {
                 request.setAttribute("sublib",sublib);
                
             }
            request.setAttribute("change", "1");
             return mapping.findForward("update/view/delete");
         }
        

       
    }
 return null;
}
}
