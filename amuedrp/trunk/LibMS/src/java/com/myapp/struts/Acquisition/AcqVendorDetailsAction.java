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
import javax.servlet.http.HttpSession;

/**
 *
 * @author maqbool
 */
public class AcqVendorDetailsAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    AcquisitionDao ado=new AcquisitionDao();
    AcqVendor acqvndr=new AcqVendor();
    AcqVendorId acqvndrid=new AcqVendorId();


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
         AcqVendorActionForm acqvndrfrm=( AcqVendorActionForm)form;

          HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String vendor_id=acqvndrfrm.getVendor_id();
        String vendor=acqvndrfrm.getVendor();
        String address=acqvndrfrm.getAddress();
        String city=acqvndrfrm.getCity();
        String state=acqvndrfrm.getState();
        String country=acqvndrfrm.getCountry();
        String pin=acqvndrfrm.getPin();
        String fax=acqvndrfrm.getFax();
        String contact_person=acqvndrfrm.getContact_person();
        String vendor_currency=acqvndrfrm.getVendor_currency();
        String email=acqvndrfrm.getEmail();
        String entry_date=acqvndrfrm.getEntry_date();
        String phone=acqvndrfrm.getPhone();
        acqvndr.setVendor(vendor);
        acqvndr.setState(state);
        acqvndr.setPin(pin);
        acqvndr.setPhone(phone);
        acqvndr.setId(acqvndrid);
        acqvndr.setFax(fax);
        acqvndr.setEntryDate(entry_date);
        acqvndr.setEmail(email);
        acqvndr.setCity(city);
        acqvndr.setCountry(country);
        acqvndr.setContactPerson(contact_person);
        acqvndr.setVendorCurrency(vendor_currency);
        acqvndr.setAddress(address);
        acqvndrid.setLibraryId(library_id);
        acqvndrid.setSubLibraryId(sub_library_id);
        acqvndrid.setVendorId(vendor_id);
        ado.insert4(acqvndr);
        String msg5="Record is inserted successfully";
        request.setAttribute("msg", msg5);

        return mapping.findForward(SUCCESS);
    }
}
