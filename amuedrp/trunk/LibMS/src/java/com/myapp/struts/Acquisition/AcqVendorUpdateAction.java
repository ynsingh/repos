/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.*;
import com.myapp.struts.AcquisitionDao.AcquisitionDao;
import com.myapp.struts.AcquisitionDao.VendorDAO;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author maqbool
 */
public class AcqVendorUpdateAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    AcquisitionDao ado=new AcquisitionDao();
    VendorDAO vndrdao=new VendorDAO();
    private boolean result;
   // AcqVendor acqvndr=new AcqVendor();
    //AcqVendorId acqvndrid=new AcqVendorId();

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

        AcqVendorActionForm acqvndrfrm= (AcqVendorActionForm)form;
         HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String button= acqvndrfrm.getButton();

        String vendor_id=acqvndrfrm.getVendor_id();
        System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGG"+vendor_id);
 // AcqVendor  acqvndr=ado.searchDoc5(library_id, sub_library_id, vendor_id);
  AcqVendor acqvndr=new AcqVendor();
AcqVendorId acqvid=new AcqVendorId();
    
        if(button.equals("Update")){
   // acqvndr=ado.searchDoc5(library_id, sub_library_id, vendor_id);
acqvid.setLibraryId(library_id);
acqvid.setSubLibraryId(sub_library_id);
acqvid.setVendorId(vendor_id);
acqvndr.setId(acqvid);
//acqvndr.setVendorId(vendor_id);
        acqvndr.setVendor(acqvndrfrm.getVendor());
        acqvndr.setState(acqvndrfrm.getState());
        acqvndr.setPin(acqvndrfrm.getPin());
        acqvndr.setPhone(acqvndrfrm.getPhone());
        acqvndr.setFax(acqvndrfrm.getFax());
        acqvndr.setEntryDate(acqvndrfrm.getEntry_date());
        acqvndr.setEmail(acqvndrfrm.getEmail());
        acqvndr.setCountry(acqvndrfrm.getCountry());
        acqvndr.setContactPerson(acqvndrfrm.getContact_person());
        acqvndr.setVendorCurrency(acqvndrfrm.getVendor_currency());
        acqvndr.setCity(acqvndrfrm.getCity());
        acqvndr.setAddress(acqvndrfrm.getAddress());
        ado.update2(acqvndr);
       // result=ado.update2(acqvndr);
      //  if(result==true){
        String msg="Record Updated Successfull";
        request.setAttribute("msg", msg);
        return mapping.findForward("update");
        }
      //  else{
    //        String msg="Record Not Updated";
    //    request.setAttribute("msg1", msg);
     //   return mapping.findForward("update");

    //    }
    //    }

        if(button.equals("Delete"))
        {
            vndrdao.delete2(vendor_id, library_id, sub_library_id);
           String msg3="Record is deleted Successfully";
           request.setAttribute("msg",msg3);

 return mapping.findForward("delete");

       }
        if(button.equals("View"))
        {
        vndrdao.search2VendorId(vendor_id, library_id, sub_library_id);

        return mapping.findForward("view");

        }



        return mapping.findForward(SUCCESS);
    }
}
