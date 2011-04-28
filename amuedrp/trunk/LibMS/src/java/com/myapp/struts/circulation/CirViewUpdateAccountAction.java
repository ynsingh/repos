/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.CirculationDAO.CirculationDAO;
import com.myapp.struts.hbm.*;
import com.myapp.struts.systemsetupDAO.SubMemberDAO;
import java.util.List;
import com.myapp.struts.utility.*;

/**
 *
 * @author edrp02
 */
public class CirViewUpdateAccountAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

    String mem_id,password,card_id,library_id,sublibrary_id,button;
     private String MEMCAT;
    private String MEMSUBCAT;
    private String TXTDESG1;
    private String TXTFACULTY;
    private String TXTDEPT;
    private String TXTSEM;
    private String library;
    private String TXTREG_DATE;
    private String TXTEXP_DATE;
    private String TXTCOURSE;
    private String TXTOFFICE;

    boolean result;
    private String no_of_issueable;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CirViewUpdateAccountActionForm cvuaf=(CirViewUpdateAccountActionForm)form;
        mem_id=cvuaf.getMem_id();
        password=cvuaf.getPassword();
        password=PasswordEncruptionUtility.password_encrupt(password);
        card_id=cvuaf.getCard_id();
        button=cvuaf.getButton();
        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
        sublibrary_id=(String)session.getAttribute("sublibrary_id");


        MEMCAT = cvuaf.getMEMCAT();
        MEMSUBCAT = cvuaf.getMEMSUBCAT();
        TXTDEPT = cvuaf.getTXTDEPT();
        TXTDESG1 = cvuaf.getTXTDESG1();
        TXTEXP_DATE = cvuaf.getTXTEXP_DATE();
        TXTFACULTY = cvuaf.getTXTFACULTY();
        TXTCOURSE=cvuaf.getTXTCOURSE();
        TXTREG_DATE = cvuaf.getTXTREG_DATE();
        TXTSEM = cvuaf.getTXTSEM();
        TXTOFFICE=cvuaf.getTXTOFFICE();




        CirMemberAccount cirmemberaccount=CirculationDAO.searchCirMemAccountDetails(library_id, sublibrary_id, mem_id);
       System.out.println("Updated.....................");

        if(button.equals("Update"))
        {
          cirmemberaccount.setPassword(password);
          cirmemberaccount.setCardId(card_id);

          cirmemberaccount.setSemester(TXTSEM);
          cirmemberaccount.setOffice(TXTOFFICE);
          cirmemberaccount.setDesg(TXTDESG1);
          cirmemberaccount.setFacultyId(TXTFACULTY);
          cirmemberaccount.setCourseId(TXTCOURSE);
          cirmemberaccount.setDeptId(TXTDEPT);
          cirmemberaccount.setMemType(MEMCAT);
          cirmemberaccount.setSubMemberType(MEMSUBCAT);
          cirmemberaccount.setReqDate(TXTREG_DATE);
          cirmemberaccount.setExpiryDate(TXTEXP_DATE);

 SubEmployeeType book=(SubEmployeeType)SubMemberDAO.searchIssueLimit(library_id,MEMCAT,MEMSUBCAT);

    if(book!=null)
    {
    no_of_issueable=String.valueOf(book.getNoOfIssueableBook());

    }

System.out.println(no_of_issueable+"...................");





              cirmemberaccount.setNoOfIssueableBook(no_of_issueable);
















          result=CirculationDAO.updateAccount(cirmemberaccount);
          if(result==true)
          {
            request.setAttribute("msg","Record Updated Successfully");
            return mapping.findForward("success");
          }
          else
          {
            request.setAttribute("msg1","Record Not Updated");
            return mapping.findForward("fail");

          }

        }

        if(button.equals("Delete"))
        {

         List<CirCheckout> chkobj=(List<CirCheckout>)CirculationDAO.searchCheckoutMemDetails(library_id,sublibrary_id, mem_id);

        System.out.println(chkobj+"........................");

        if(!chkobj.isEmpty())
        {
            String msg1="Member Checkout are there ,So Cannot Be Deleted";
             request.setAttribute("msg1",msg1);
            return mapping.findForward("fail");

        }


           result=CirculationDAO.deleteAccount(library_id, sublibrary_id, mem_id);
           if(result==true)
           {
            request.setAttribute("msg","Record Deleted Successfully");
            return mapping.findForward("success");
           }
           else
           {
            request.setAttribute("msg1","Record Not Deleted");
            return mapping.findForward("fail");

           }



        }



        return mapping.findForward("");
    }
}
