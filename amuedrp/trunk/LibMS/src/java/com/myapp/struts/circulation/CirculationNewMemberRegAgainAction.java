/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import com.myapp.struts.CirculationDAO.CirculationDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.*;
import com.myapp.struts.systemsetupDAO.SubMemberDAO;
import javax.servlet.http.HttpSession;

/**
 *
 * @author edrp02
 */
public class CirculationNewMemberRegAgainAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String status="Active";
    String  library_id,sublibrary_id,mem_id ,MEMCAT, MEMSUBCAT,TXTDESG1,TXTOFFICE,TXTFACULTY,TXTDEPT,TXTCOURSE,TXTSEM,TXTREG_DATE,TXTEXP_DATE,card_id,password;
    String no_of_issueable;
    boolean result;
    CirMemberAccount cirmemberac=new CirMemberAccount();
    CirMemberAccountId cirmemberacid=new CirMemberAccountId();



    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CirculationNewMemberRegAgainActionForm  cnmraf=(CirculationNewMemberRegAgainActionForm)form;
        MEMCAT=cnmraf.getMEMCAT();
        MEMSUBCAT=cnmraf.getMEMSUBCAT();
        TXTDESG1=cnmraf.getTXTDESG1();
        TXTOFFICE=cnmraf.getTXTOFFICE();
        TXTFACULTY=cnmraf.getTXTFACULTY();
        TXTDEPT=cnmraf.getTXTDEPT();
        TXTCOURSE=cnmraf.getTXTCOURSE();
        TXTSEM=cnmraf.getTXTSEM();
        TXTREG_DATE=cnmraf.getTXTREG_DATE();
        TXTEXP_DATE=cnmraf.getTXTEXP_DATE();
        password=cnmraf.getPassword();
        card_id=cnmraf.getCard_id();
        mem_id=cnmraf.getMemid();

        System.out.println("MemberId"+mem_id+"LibraryId"+library_id+"SublibraryId"+sublibrary_id);

        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
        sublibrary_id=(String)session.getAttribute("sublibrary_id");

        cirmemberacid.setLibraryId(library_id);
        cirmemberacid.setSublibraryId(sublibrary_id);
        cirmemberacid.setMemid(mem_id);
        cirmemberac.setId(cirmemberacid);
        cirmemberac.setCardId(card_id);
        cirmemberac.setCourseId(TXTCOURSE);
        cirmemberac.setDeptId(TXTDEPT);
        cirmemberac.setOffice(TXTOFFICE);
        cirmemberac.setDesg(TXTDESG1);
        cirmemberac.setSemester(TXTSEM);
        cirmemberac.setReqDate(TXTREG_DATE);
        cirmemberac.setExpiryDate(TXTEXP_DATE);
        cirmemberac.setPassword(password);
        cirmemberac.setMemType(MEMCAT);
        cirmemberac.setSubMemberType(MEMSUBCAT);
        cirmemberac.setStatus(status);
        cirmemberac.setFacultyId(TXTFACULTY);

        SubEmployeeType book=(SubEmployeeType)SubMemberDAO.searchIssueLimit(library_id,MEMCAT,MEMSUBCAT);
        if(book!=null)
        {
         no_of_issueable=String.valueOf(book.getNoOfIssueableBook());

        }

              cirmemberac.setFine("0");
              cirmemberac.setLastchkoutdate("");
              cirmemberac.setNoOfChkout("0");
              cirmemberac.setTotalIssuedBook("0");
              cirmemberac.setNoOfIssueableBook(no_of_issueable);
              cirmemberac.setCurrentIssuedBook("0");
              cirmemberac.setReservationMade("0");

              result=CirculationDAO.insert(cirmemberac);
              if(result==true)
              {
                String msg="Record Inserted successfully";
                request.setAttribute("msg",msg);
                return mapping.findForward("success");
              }
              else
              {
                String msg="Record not Inserted successfully";
                request.setAttribute("msg",msg);
                return mapping.findForward("fail");

              }


        
    }
}
