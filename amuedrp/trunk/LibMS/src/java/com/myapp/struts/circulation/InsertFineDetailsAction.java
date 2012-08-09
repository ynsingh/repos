/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.CirDAO.CirculationDAO;
import com.myapp.struts.circulation.CirCheckInReportActionForm;
import com.myapp.struts.hbm.FineDetails;
import com.myapp.struts.hbm.FineDetailsId;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 *
 * @author edrp-03
 */
public class InsertFineDetailsAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
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



        HttpSession session=request.getSession();
        CirCheckInReportActionForm crv=(CirCheckInReportActionForm)form;
        CirculationDAO cirdao =new CirculationDAO();

       
        String  library_id=(String)session.getAttribute("library_id");
            String  sub_lib=(String)session.getAttribute("sublibrary_id");
            String memid=(String)session.getAttribute("mem");

        System.out.println(library_id+sub_lib+memid);
        Calendar currentDate = Calendar.getInstance();
  SimpleDateFormat formatter=
  new SimpleDateFormat("yyyy-MM-dd ");
  String dateNow = formatter.format(currentDate.getTime());
  System.out.println("Now the date is :=>  " + dateNow);
       
        FineDetails fd=new FineDetails();
        FineDetailsId fdi=new FineDetailsId();

        
     
        fdi.setLibraryId(library_id);
        fdi.setSublibraryId(library_id);
        fdi.setMemid(memid);
        fdi.setSlipno(crv.getSlipno());
        fd.setTfine(crv.getTotalfine());
//        fd.setPaid1(crv.getPaid1());
//        fd.setPaid2(crv.getPaid2());
//        fd.setPaid3(crv.getPaid3());
//        fd.setPaid4(crv.getPaid4());
        fd.setPaid(crv.getPaid());
        fd.setRemaining(crv.getRemain());
        fd.setPaymod(crv.getPaymod());
        fd.setChequeDdNo(crv.getChequeno());
        fd.setBankname(crv.getBankname());

        fd.setPaydate(dateNow);
        fd.setIssuedate(crv.getDate());
       
        fd.setId(fdi);
        cirdao.insert(fd);
request.setAttribute("msg", "Payment Successfull");
        

        
        return mapping.findForward(SUCCESS);
    }
}
