/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import com.myapp.struts.circulationDAO.cirDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.*;
import javax.servlet.http.HttpSession;
import com.myapp.struts.utility.DateCalculation;


public class CirculationCheckoutAction extends org.apache.struts.action.Action {
    
   
    private static final String SUCCESS = "success";
    String memid;
    String library_id;
    String sublibrary_id;


   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CirculationCheckoutActionForm ccaf=(CirculationCheckoutActionForm)form;
        memid=ccaf.getMemid();
        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
        sublibrary_id=(String)session.getAttribute("sublibrary_id");

        CirMemberDetail  cirmemberdetail=cirDAO.getMemid(library_id,memid);
        
        if(cirmemberdetail!=null)
        {
          CirMemberAccount cma=cirDAO.getAccount(library_id,sublibrary_id, memid);
          if(cma!=null)
          {

              if(cma.getStatus().equals("Active")){

          String date_reg=cma.getReqDate();
          String date_ex=cma.getExpiryDate();
          String system_date=DateCalculation.now();
          long days1=DateCalculation.getDifference(date_ex, system_date);
          if(days1<0){
          request.setAttribute("msg1", "Member's account is expired");
          }
          long days=DateCalculation.getDifference( date_ex,system_date);
          System.out.println("GHHHHHHHHHHHHHHHHHHH"+days);
          if(days<30){
          String msg="Reminder:After "+days+" days the member's account will be expired.";
          request.setAttribute("msg2", msg);
          }
          session.setAttribute("cma",cma);
          session.setAttribute("cirmemberdetail",cirmemberdetail);
          session.setAttribute("memid",memid);
          return mapping.findForward("success");
              }
              else{
              request.setAttribute("msg1", "Member Account is not Active");
             return mapping.findForward("failure");


              }


          }else{
          request.setAttribute("msg1", "Member account does not exists");
          return mapping.findForward("failure");
          }
        }
        else
        {
          request.setAttribute("msg1", "Member account does not exists");
          return mapping.findForward("failure");
        }
    }
}
