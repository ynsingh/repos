/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;


import com.myapp.struts.AcquisitionDao.AcquisitionDao;
import com.myapp.struts.hbm.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.util.*;


/**
 *
 * @author maqbool
 */
public class AcqRecieveOrderProcessAction extends org.apache.struts.action.Action {
 AcquisitionDao acqdao=new AcquisitionDao();
    
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
           AcqOrderManagementActionForm acqorder=(AcqOrderManagementActionForm)form;
 

          HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String vendor=acqorder.getVendor();

        List<String> acqvendor=acqdao.searchDoc6(library_id, sub_library_id);

ArrayList list1=new ArrayList();
for(int i=0;i<acqvendor.size();i++){
String v=acqvendor.get(i);
AcqBibliographyDetails acqv=new AcqBibliographyDetails();
acqv.setVendor(v);
list1.add(acqv);
}

System.out.println("#################"+list1.size());
         session.setAttribute("vendors", list1);

        return mapping.findForward("order");
    }
}

       



    
