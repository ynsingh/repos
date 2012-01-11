/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import com.myapp.struts.CirDAO.CirculationDAO;
import com.myapp.struts.hbm.CirMemberAccount;
import com.myapp.struts.hbm.CirMemberDetail;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.myapp.struts.utility.*;
import com.myapp.struts.utility.Email;
/**
 *
 * @author edrp01
 */
public class CirDelinquentMemberAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    boolean result;
    private String password;

      private final ExecutorService executor=Executors.newFixedThreadPool(1);
  Email obj;
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
         HttpSession session=request.getSession();
        String library_id =(String)session.getAttribute("library_id");
        String sublibrary_id=(String)session.getAttribute("sublibrary_id");
             CirDelinquentMemberActionForm   cdma =(CirDelinquentMemberActionForm)form;
  
        String changestatus = cdma.getChangestatus();

     String memid = cdma.getMemid();
   String button = cdma.getButton();
  String reason =cdma.getReason();
  String status=cdma.getChangestatus();
  String sem=cdma.getTXTSEM();
  String reg_date=cdma.getTXTREG_DATE();
  String exp_date=cdma.getTXTEXP_DATE();


  CirMemberDetail cirmem=CirculationDAO.getMemid(library_id,memid);


  CirMemberAccount cirmemac =  CirculationDAO.getAccount(library_id, sublibrary_id, memid);
  
  

 if(changestatus.equalsIgnoreCase("Renewal"))
 {
     cirmemac.setRemark(reason);
    cirmemac.setStatus("Active");
    cirmemac.setSemester(sem);
    cirmemac.setReqDate(reg_date);
    cirmemac.setExpiryDate(exp_date);

    
/*Password Generate and Reset It*/
                 password= RandomPassword.getRandomString(10);

        String password1 = PasswordEncruptionUtility.password_encrupt(password);

        cirmemac.setPassword(password1);



    



 result = CirculationDAO.updateAccount(cirmemac);



  if(result==true)
  {

       
                  String path = servlet.getServletContext().getRealPath("/");
              obj=new Email(path,cirmem.getEmail(),password,"Renewal of Library Member Registration","Your Library MemberShip is Successfully Renewed With \n UserID: "+cirmemac.getId().getMemid()+"\nPassword: "+password,"\n\nDear "+cirmem.getFname()+" "+cirmem.getLname()+",\n","With Regards\nWebAdmin\nLibMS");

            executor.submit(new Runnable() {

                public void run() {
                    obj.send();
                }
            });






      request.setAttribute("msg","Member Record Sucessfully  "+cirmemac.getStatus()+" for Member id " +memid +" Mail Sent Successfully");

         List cirmemacclist=CirculationDAO.searchCirMemAccount2(library_id,sublibrary_id);
         session.removeAttribute("cirmemacclist");
        if(!cirmemacclist.isEmpty())
        {
          session.setAttribute("cirmemacclist", cirmemacclist);
         }

       return mapping.findForward(SUCCESS);
  }
  else
  {
     request.setAttribute("msg1","Record not Updated Due to Some Internal Reasons");
       return mapping.findForward(SUCCESS);
  }









 }else{


  if(!changestatus.equalsIgnoreCase("active"))
  cirmemac.setRemark(reason);
  cirmemac.setStatus(changestatus);




 result = CirculationDAO.updateAccount(cirmemac);



  if(result==true)
  {
      request.setAttribute("msg","Member Record Sucessfully  "+cirmemac.getStatus()+" for Member id " +memid);

         List cirmemacclist=CirculationDAO.searchCirMemAccount2(library_id,sublibrary_id);
         session.removeAttribute("cirmemacclist");
        if(!cirmemacclist.isEmpty())
        {
          session.setAttribute("cirmemacclist", cirmemacclist);
         }

       return mapping.findForward(SUCCESS);
  }
  else
  {
     request.setAttribute("msg1","Record not Updated Due to Some Internal Reasons");
       return mapping.findForward(SUCCESS);
  }

 }

       // return mapping.findForward(SUCCESS);
    }
}
