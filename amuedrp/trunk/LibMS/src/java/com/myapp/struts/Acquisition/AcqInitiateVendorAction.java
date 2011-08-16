/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.*;
import com.myapp.struts.AcquisitionDao.VendorDAO;
import com.myapp.struts.AcquisitionDao.AcquisitionDao;

/**
 *
 * @author maqbool
 */
public class AcqInitiateVendorAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    AcquisitionDao ado=new AcquisitionDao();
    VendorDAO vndrdao=new VendorDAO();
    AcqVendor avndr=new AcqVendor();
    AcqVendorId avndrid=new AcqVendorId();

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
         AcqVendorActionForm acqvndr=(AcqVendorActionForm)form;

          HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String vendor_id=acqvndr.getVendor_id();
        
        String button=acqvndr.getButton();
        if(vendor_id!=null){
       

        if(button.equals("New")){
        avndr=vndrdao.search1VendorNo(vendor_id, library_id, sub_library_id);
     if(avndr !=null)
     {
         String msg1="This Vendor Id is Already Exist";
         request.setAttribute("msg1", msg1);
         return mapping.findForward("duplicatevendorid");
     }

        return mapping.findForward("new");
        }
         AcqVendor  acqvndr1=ado.searchDoc5(library_id, sub_library_id, vendor_id);
        System.out.println("Vendor"+acqvndr1);
  if(acqvndr1==null){
  String msg="Record Not Found";
        request.setAttribute("msg1", msg);
        return mapping.findForward("failure");


  }else{

        if(button.equals("Update")){
            if(vendor_id!=null){
         avndr=ado.getBiblio(library_id, sub_library_id, vendor_id);
         if(avndr!=null){
         acqvndr.setAddress(avndr.getAddress());
         acqvndr.setCity(avndr.getCity());
         acqvndr.setCountry(avndr.getCountry());
         acqvndr.setEmail(avndr.getEmail());
         acqvndr.setEntry_date(avndr.getEntryDate());
         acqvndr.setFax(avndr.getFax());
         acqvndr.setLibrary_id(avndr.getId().getLibraryId());
         acqvndr.setSub_library_id(avndr.getId().getSubLibraryId());
         acqvndr.setVendor_id(avndr.getId().getVendorId());
         acqvndr.setPhone(avndr.getPhone());
         acqvndr.setPin(avndr.getPin());
         acqvndr.setContact_person(avndr.getContactPerson());
         acqvndr.setVendor_currency(avndr.getVendorCurrency());
         acqvndr.setVendor(avndr.getVendor());
         acqvndr.setState(avndr.getState());

          return mapping.findForward("update1");

         }


            }

        return mapping.findForward("update");
        }

       if(button.equals("Delete"))
       {
           if(vendor_id!=null){
         avndr=ado.getBiblio(library_id, sub_library_id, vendor_id);
         if(avndr!=null){
         acqvndr.setAddress(avndr.getAddress());
         acqvndr.setCity(avndr.getCity());
         acqvndr.setCountry(avndr.getCountry());
         acqvndr.setEmail(avndr.getEmail());
         acqvndr.setEntry_date(avndr.getEntryDate());
         acqvndr.setFax(avndr.getFax());
         acqvndr.setLibrary_id(avndr.getId().getLibraryId());
         acqvndr.setSub_library_id(avndr.getId().getSubLibraryId());
         acqvndr.setVendor_id(acqvndr.getVendor_id());
         acqvndr.setPhone(avndr.getPhone());
         acqvndr.setContact_person(avndr.getContactPerson());
         acqvndr.setVendor_currency(avndr.getVendorCurrency());
         acqvndr.setPin(avndr.getPin());
         acqvndr.setVendor(avndr.getVendor());
         acqvndr.setState(avndr.getState());

          return mapping.findForward("delete");

         }


            }


return mapping.findForward("delete1");
       }
        if(button.equals("View"))
        {
        if(vendor_id!=null)
        {
              avndr=ado.getBiblio(library_id, sub_library_id, vendor_id);
         if(avndr!=null){
         acqvndr.setAddress(avndr.getAddress());
         acqvndr.setCity(avndr.getCity());
         acqvndr.setCountry(avndr.getCountry());
         acqvndr.setEmail(avndr.getEmail());
         acqvndr.setEntry_date(avndr.getEntryDate());
         acqvndr.setFax(avndr.getFax());
         acqvndr.setLibrary_id(avndr.getId().getLibraryId());
         acqvndr.setSub_library_id(avndr.getId().getSubLibraryId());
         acqvndr.setVendor_id(acqvndr.getVendor_id());
         acqvndr.setPhone(avndr.getPhone());
         acqvndr.setPin(avndr.getPin());
          acqvndr.setContact_person(avndr.getContactPerson());
         acqvndr.setVendor_currency(avndr.getVendorCurrency());
         acqvndr.setVendor(avndr.getVendor());
         acqvndr.setState(avndr.getState());

          return mapping.findForward("view");

         }

        }

        }
        }}
return mapping.findForward("failure");
}
}
