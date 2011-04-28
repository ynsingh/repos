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
import com.myapp.struts.systemsetupDAO.SubMemberDAO;
import javax.servlet.http.HttpSession;
import com.myapp.struts.utility.*;

/**
 *
 * @author edrp02
 */
public class CirCreateAccount2Action extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String mem_id,library_id,sublibrary_id;
    String password;
    private String MEMCAT;
    private String MEMSUBCAT;
    private String TXTDESG1;
    private String TXTFACULTY;
    private String TXTDEPT;
    private String TXTSEM;
    private String library;
    private String TXTREG_DATE;
    private String TXTEXP_DATE;
   
    private String button;
    String card_id;
    String status="Active";
    boolean result;

    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CirCreateAccount2ActionForm ccaaf=(CirCreateAccount2ActionForm)form;
        mem_id=ccaaf.getMem_id();
        password=ccaaf.getPassword();
        password = PasswordEncruptionUtility.password_encrupt(password);
        card_id=ccaaf.getCard_id();
        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
        sublibrary_id=(String)session.getAttribute("sublibrary_id");
        MEMCAT = ccaaf.getMEMCAT();
        MEMSUBCAT = ccaaf.getMEMSUBCAT();
        TXTDEPT = ccaaf.getTXTDEPT();
        TXTDESG1 = ccaaf.getTXTDESG1();
        TXTEXP_DATE = ccaaf.getTXTEXP_DATE();
        TXTFACULTY = ccaaf.getTXTFACULTY();
        TXTREG_DATE = ccaaf.getTXTREG_DATE();
        TXTSEM = ccaaf.getTXTSEM();
        library = ccaaf.getLibrary();
        button = request.getParameter("button");
       if(button!=null)
           if(button.equals("Delete"))
           {
               CirMemberAccount cirmemac=cirDAO.getAccount(library_id, sublibrary_id, mem_id);
              boolean check = cirDAO.delete(cirmemac);
              if(check){
                  request.setAttribute("msg","Account Successfully Removed");
                  return mapping.findForward("fail");
              }else
              {
                  request.setAttribute("msg","Account Not Removed");
                  return mapping.findForward("fail");
              }
           }
        CirMemberAccount cirmemac=cirDAO.getAccount(library_id, library, mem_id);
        if(cirmemac==null)cirmemac = new CirMemberAccount();
        if(cirmemac.getId()==null){
        CirMemberAccountId cirmemId = new CirMemberAccountId(library_id, library, mem_id);
            cirmemac.setId(cirmemId);
        }
        cirmemac.setCardId(card_id);
        cirmemac.setPassword(password);
        cirmemac.setStatus(status);
        cirmemac.setMemType(MEMCAT);
        cirmemac.setSubMemberType(MEMSUBCAT);
        cirmemac.setDeptId(TXTDEPT);
        cirmemac.setDesg(TXTDESG1);
        cirmemac.setExpiryDate(TXTEXP_DATE);
        cirmemac.setFacultyId(TXTFACULTY);
        cirmemac.setReqDate(TXTREG_DATE);
        cirmemac.setSemester(TXTSEM);
        String no_of_issueable="";
        SubEmployeeType book=(SubEmployeeType)SubMemberDAO.searchIssueLimit(library_id,MEMCAT,MEMSUBCAT);
        if(book!=null)
        {
         no_of_issueable=String.valueOf(book.getNoOfIssueableBook());

        }

              cirmemac.setFine("0");
              cirmemac.setLastchkoutdate("");
              cirmemac.setNoOfChkout("0");
              cirmemac.setTotalIssuedBook("0");
              cirmemac.setNoOfIssueableBook(no_of_issueable);
              cirmemac.setCurrentIssuedBook("0");
              cirmemac.setReservationMade("0");
        result=cirDAO.insert(cirmemac);
        if(result==true)
        {
          request.setAttribute("msg", "Account For Member Id:"+mem_id+" Created and confirmation mail sent successfully.");
          return mapping.findForward("success");  
        }    
        else
        {
          request.setAttribute("msg", "Account Not Created");
          return mapping.findForward("fail");
         
        }

        
    }
}
