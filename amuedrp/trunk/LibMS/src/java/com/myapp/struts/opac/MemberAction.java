/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;
import com.myapp.struts.CirculationDAO.CirculationDAO;
import com.myapp.struts.opacDAO.*;
import com.myapp.struts.hbm.*;
import java.util.List;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.utility.*;

/**
 *
 * @author Faraz
 */
public class MemberAction extends org.apache.struts.action.Action {
    
     String ID,password,lib_id,sublibrary_id,libname;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
       
       
        HttpSession session = request.getSession();
        MemberActionForm myForm =(MemberActionForm)form; 
        
        ID = myForm.getTXTMEMID();
        password=myForm.getTXTPASS();
        password = PasswordEncruptionUtility.password_encrupt(password);
        lib_id=myForm.getCMBLib();
        sublibrary_id=myForm.getCmdSubLibary();

       if(ID==null)ID=(String)session.getAttribute("id");
       if(password==null)password=(String)session.getAttribute("password");
       if(lib_id==null)lib_id=(String)session.getAttribute("memlib");
       if(sublibrary_id==null)sublibrary_id=(String)session.getAttribute("memsublib");

        session.setAttribute("id", ID);
       session.setAttribute("password",password);
        session.setAttribute("memlib",lib_id);
        session.setAttribute("memsublib",sublibrary_id);



       
       
        CirMemberAccount cirmem=(CirMemberAccount)CirculationDAO.searchCirMemAccountDetails(lib_id,sublibrary_id, ID,password);


       if(cirmem!=null)
       {
           CirMemberAccount cirmem2=(CirMemberAccount)CirculationDAO.searchCirMemAccountDetails1(lib_id,sublibrary_id, ID,password);

           if(cirmem2==null){
               String msg = "Your Membership is expired!" +
                           " please contact to your Library..";
                request.setAttribute("msg", msg);
                return mapping.findForward("notfound");
           }

            String  status=cirmem.getStatus();

            if(!status.equals("Active"))
            {
                String msg = "Sorry, your Membership is cancelled" +
                           " for somehow reason, please contact to your Library..";
                request.setAttribute("msg", msg);
                return mapping.findForward("notfound");
            }


     
        session.setAttribute("card_id",cirmem.getCardId());
        session.setAttribute("mem_id",cirmem.getId().getMemid());
        session.setAttribute("mem_type", cirmem.getMemType());
        



        CirMemberDetail cirmem1=(CirMemberDetail)CirculationDAO.searchCirMemDetails(lib_id, ID);
        if(cirmem1!=null)
        {
         session.setAttribute("mem_name",cirmem1.getFname()+" "+cirmem1.getMname()+" "+cirmem1.getLname());
        }
        else
        {
            String msg="Invalid Member";
            request.setAttribute("msg", msg);
            return mapping.findForward("notfound");
        }

         List<MemberFinewithDocument> cirtrans=(List<MemberFinewithDocument>)CirTransactionHistoryDAO.getMemberFineWithDocumentDetail(lib_id, sublibrary_id, ID);
        if(cirtrans!=null)
        {session.setAttribute("fine_details",cirtrans);
        }
      
       List<Reservationlist> cirreservation=(List<Reservationlist>)ReservationListDAO.getMemberDetail(lib_id, sublibrary_id, ID);
          
       if(cirreservation!=null)
        {
           session.setAttribute("reservationlist", cirreservation);
        }
       String type = (String)session.getAttribute("type");
       if(type!=null)
       {
           String memId = (String)session.getAttribute("mem_id");
           String libId = (String)session.getAttribute("checkoutRequestLibId");
           String sublibId = (String)session.getAttribute("checkoutRequestSubLibId");
            String docId = (String)session.getAttribute("checkoutRequestDocId" );

            CirMemberAccount memSubLib = (CirMemberAccount)CirculationDAO.searchCirMemAccountDetails1(libId, sublibId, memId,password);
            System.out.println(memSubLib);
            if(memSubLib==null)
            {
                request.setAttribute("msg", "You do not have an account in library "+ libId +"-"+sublibId);
                //return mapping.findForward("account");
            }
            else
            {

            List<CirOpacRequest> check1 = (List<CirOpacRequest>)CirRequestfromOpacDAO.checkDuplicateRequest(libId, sublibId, memId, docId);
            System.out.println("check1 = "+ check1.isEmpty());
            if(check1.isEmpty()){
            boolean flag = CirRequestfromOpacDAO.SendCheckOutRequest(memId, libId, sublibId, docId);
            if(flag)
            {
                session.removeAttribute("checkoutRequestMemId");
                session.removeAttribute("checkoutRequestLibId");
                session.removeAttribute("checkoutRequestSubLibId");
                session.removeAttribute("checkoutRequestDocId");
               // session.removeAttribute("type");
               // session.removeAttribute("memId");
                request.setAttribute("confirm", "continue");
                request.setAttribute("msg", "Your request successfully Send");
            }
            else
            {
                session.removeAttribute("checkoutRequestMemId");
                session.removeAttribute("checkoutRequestLibId");
                session.removeAttribute("checkoutRequestSubLibId");
                session.removeAttribute("checkoutRequestDocId");
                //session.removeAttribute("type");
                request.setAttribute("confirm", "continue");
            }
               }
               else request.setAttribute("msg", "Your request already Pending");
            }
       }

        session.setAttribute("memberaccount",cirmem);
            return mapping.findForward("account");
        }
        else
        {
            String msg="Invalid Member";
            request.setAttribute("msg", msg);
            return mapping.findForward("notfound");
        }
      
    }
}
