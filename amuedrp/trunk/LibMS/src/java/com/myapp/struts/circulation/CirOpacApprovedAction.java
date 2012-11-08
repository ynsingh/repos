/*
     Design by Iqubal Ahmad
     Modified on 2011-02-02
     This Action Class is meant for Submitting Approved  value after grid from opac request into Cir_member_detail table.
     This Action Class also Check Duplication of Already Approved Record.
 */

package com.myapp.struts.circulation;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.*;
import com.myapp.struts.opacDAO.*;
import javax.servlet.http.*;
//import com.mysql.jdbc.ResultSet;
import com.myapp.struts.CirDAO.CirculationDAO;
import com.myapp.struts.systemsetupDAO.SubMemberDAO;
import com.myapp.struts.utility.AppPath;
import com.myapp.struts.utility.UserLog;

/**
 *
 * @author Iqubal Ahmad
 */
public class CirOpacApprovedAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private CirMemberDetail cmd = new CirMemberDetail();
    private CirMemberDetailId cmdi = new CirMemberDetailId();
     CirRequestfromOpac cro = new CirRequestfromOpac();
    private String TXTMEMID;
    private String TXTFNAME;
    private String TXTLNAME;
    private String TXTMNAME;
    private String TXTEMAILID;
    private String TXTPASS;
    private String TXTADD1;
    private String TXTCITY1;
    private String TXTSTATE1;
    private String TXTCOUNTRY1;
    private String TXTPH1;
    private String TXTPH2;
    private String TXTFAX;
    private String TXTADD2;
    private String TXTCITY2;
    private String TXTPIN1;
    private String TXTPIN2;
    private String TXTSTATE2;
    private String TXTCOUNTRY2;
    private String MEMCAT;
    private String MEMSUBCAT;
    private String TXTFACULTY;
    private String TXTDEPT;
    private String TXTCOURSE;
    private String TXTREG_DATE;
    private String TXTEXP_DATE;
    private String TXTSEM;
    private byte[] imagefile;
    private String button;
    private String TXTDESG1;
    private String TXTOFFICE;
    String library_id;
    String sublibrary_id;
    private String no_of_issueable;
    private boolean result;
    
    /**
      Design by Iqubal Ahmad
      Modified on 2011-02-02
     This Action Class is meant for Submitting Approved  value after grid from opac request into Cir_member_detail table.
     This Action Class also Check Duplication of Already Approved Record.
     *
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
CirculationDAO cirdao=new CirculationDAO();
SubMemberDAO submemdao=new SubMemberDAO();
CirRequestfromOpacDAO crodao = new CirRequestfromOpacDAO();
           CirOpacApprovedActionForm cmdf =(CirOpacApprovedActionForm)form;
        HttpSession session1 =request.getSession();
        //ImageUploadActionForm form1 = (ImageUploadActionForm)session1.getAttribute("ImageUploadActionForm");
        CirRequestfromOpac form1 = (CirRequestfromOpac)session1.getAttribute("opacimage");

 library_id=(String)session1.getAttribute("library_id");
       sublibrary_id=(String)session1.getAttribute("sublibrary_id");



        String  member_id = cmdf.getTXTMEMID();
        CirRequestfromOpac obj=cirdao.searchMemberfromOPAC(library_id, sublibrary_id,member_id);
        System.out.println(obj+".................");
      String last_name=cmdf.getTXTLNAME();
      String mail_id=cmdf.getTXTEMAILID();
       String button=cmdf.getButton();
       String mem_name=cmdf.getTXTFNAME();

        
            if(button!=null)
            if(button.equals("Approved"))
            {







            cmdi.setMemId(member_id);

            cmdi.setLibraryId(library_id);

            cmd.setId(cmdi);
            cmd.setFname(cmdf.getTXTFNAME());
            cmd.setMname(cmdf.getTXTMNAME());
            cmd.setLname(cmdf.getTXTLNAME());
            cmd.setEmail(cmdf.getTXTEMAILID());
            cmd.setAddress1(cmdf.getTXTADD1());
            cmd.setAddress2(cmdf.getTXTADD2());
            cmd.setCity1(cmdf.getTXTCITY1());
            cmd.setState1(cmdf.getTXTSTATE1());
            cmd.setCountry1(cmdf.getTXTCOUNTRY1());
            cmd.setCity2(cmdf.getTXTCITY2());
            cmd.setState2(cmdf.getTXTSTATE2());
            cmd.setCountry2(cmdf.getTXTCOUNTRY2());
            cmd.setPhone1(cmdf.getTXTPH1());
            cmd.setPhone2(cmdf.getTXTPH2());
            cmd.setPin1(cmdf.getTXTPIN1());
            cmd.setPin2(cmdf.getTXTPIN2());
            cmd.setFax(cmdf.getTXTFAX());
           
            byte[]  iii=UserLog.getBytesFromFile(AppPath.getProjectPropertiesImagePath()+obj.getImage());

            System.out.println("##############"+iii+"##################");

            cmd.setImage(iii);

            cirdao.insert(cmd);
            CirMemberAccountId  cmai = new CirMemberAccountId();
            CirMemberAccount cma = new CirMemberAccount();
            cmai.setLibraryId(library_id);
          
            cmai.setSublibraryId(sublibrary_id);
            cmai.setMemid(member_id);


            cma.setId(cmai);
            cma.setMemType(cmdf.getMEMCAT());
            cma.setSubMemberType(cmdf.getMEMSUBCAT());
            cma.setDesg(cmdf.getTXTDESG1());
            cma.setOffice(cmdf.getTXTOFFICE());
            cma.setFacultyId(cmdf.getTXTFACULTY());
            cma.setDeptId(cmdf.getTXTDEPT());
            cma.setCourseId(cmdf.getTXTCOURSE());
            cma.setReqDate(cmdf.getTXTREG_DATE());
            cma.setExpiryDate(cmdf.getTXTEXP_DATE());
            cma.setStatus("registered");

    SubEmployeeType book=(SubEmployeeType)submemdao.searchIssueLimit(library_id,cmdf.getMEMCAT(),cmdf.getMEMSUBCAT());

    if(book!=null)
    {
    no_of_issueable=String.valueOf(book.getNoOfIssueableBook());

    }






              cma.setFine("0");
              cma.setLastchkoutdate("");
              cma.setNoOfChkout("0");
              cma.setTotalIssuedBook("0");
              cma.setNoOfIssueableBook(no_of_issueable);
              cma.setCurrentIssuedBook("0");
              cma.setReservationMade("0");
              cma.setLastchkoutdate("");




              cma.setStatus("Registered");



            cma.setSemester(cmdf.getTXTSEM());

            result=cirdao.insert(cma);
            }
            if(result==true)
            {

                CirRequestfromOpac requestobj=(CirRequestfromOpac)crodao.getMemberDetail(library_id, sublibrary_id,cmdf.getTXTMEMID());
                if(requestobj!=null)
                {
                requestobj.setStatus("Approved");
                result=crodao.update(requestobj);
                }


            String msg="Record Inserted successfully";
            request.setAttribute("msg",msg);
            request.setAttribute("mem_id",member_id);
             request.setAttribute("mem_name",mem_name);
             request.setAttribute("last_name",last_name);
             request.setAttribute("mail_id",mail_id);
             request.setAttribute("button",button);
             session1.removeAttribute("image");
            return mapping.findForward("Approved");
            }
            return mapping.findForward("failure");
          




      
     // CirRequestOpacActionForm   form2=(CirRequestOpacActionForm)form;
     //  HttpSession session1 =request.getSession();
        //ImageUploadActionForm form1 = (ImageUploadActionForm)session1.getAttribute("ImageUploadActionForm");
      //  CirRequestfromOpac form1 = (CirRequestfromOpac)session1.getAttribute("opacimage");
    // int registrationId  =form1.getRegistrationId();
    //  String  registration_Id = String.valueOf(registrationId) ;
    //    String  member_id = cmdf.getTXTMEMID();
    //  String last_name=cmdf.getTXTLNAME();
     // String mail_id=cmdf.getTXTEMAILID();
    //   String button=cmdf.getButton();
    //   String mem_name=cmdf.getTXTFNAME();
     //   String   library_id=(String)session1.getAttribute("library_id");
         //cmd=(CirMemberDetail)session1.getAttribute("cirmemberdetail");
         //session1.removeAttribute("cirmemberdetail");
      //    Session session=HibernateUtil.getSessionFactory().getCurrentSession();
     //       Transaction tx=null;

      //      if(button.equals("Approved"))
       //     {
        /*       boolean test=DuplicateEntry.checkDuplicateEntry("cir_member_detail","memid" , member_id,library_id);
               if(test)
                {
                    request.setAttribute("msg1", "member Id : "+member_id+" already Approved");
                   return mapping.findForward("failure");
                }

         else{
               try
              {
            cmdi.setMemId(member_id);
            cmdi.setLibraryId(library_id);
            cmd.setId(cmdi);
            cmd.setFname(cmdf.getTXTFNAME());
            cmd.setMname(cmdf.getTXTMNAME());
            cmd.setLname(cmdf.getTXTLNAME());
            cmd.setEmail(cmdf.getTXTEMAILID());
            cmd.setAddress1(cmdf.getTXTADD1());
            cmd.setAddress2(cmdf.getTXTADD2());
            cmd.setCity1(cmdf.getTXTCITY1());
            cmd.setState1(cmdf.getTXTSTATE1());
            cmd.setCountry1(cmdf.getTXTCOUNTRY1());
            cmd.setCity2(cmdf.getTXTCITY2());
            cmd.setState2(cmdf.getTXTSTATE2());
            cmd.setCountry2(cmdf.getTXTCOUNTRY2());

            cmd.setMemType(cmdf.getMEMCAT());
            cmd.setSubMemberType(cmdf.getMEMSUBCAT());
            cmd.setDesg(cmdf.getTXTDESG1());
            cmd.setOffice(cmdf.getTXTOFFICE());
            cmd.setFacultyId(cmdf.getTXTFACULTY());
            cmd.setDeptId(cmdf.getTXTDEPT());
            cmd.setCourse(cmdf.getTXTCOURSE());
            cmd.setRegDate(cmdf.getTXTREG_DATE());
            cmd.setExpDate(cmdf.getTXTEXP_DATE());
            cmd.setRequestdate(cmdf.getTXTREQ_DATE());
            cmd.setStatus("Approved");
            cmd.setPhone1(cmdf.getTXTPH1());
            cmd.setPhone2(cmdf.getTXTPH2());
            cmd.setPin1(cmdf.getTXTPIN1());
            cmd.setPin2(cmdf.getTXTPIN2());
            cmd.setFax(cmdf.getTXTFAX());
            cmd.setSemester(cmdf.getTXTSEM());
            if (form1!=null)
            cmd.setImage(form1.getImage());
             else cmd.setImage(cmdf.getUploadedFile());
            System.out.println("Approved button mila");
            tx=session.beginTransaction();
            session.save(cmd);
            //tx.commit();
            String msg="Request Approved successfully";
            request.setAttribute("msg",msg);
            request.setAttribute("mem_id",member_id);
             request.setAttribute("mem_name",mem_name);
             request.setAttribute("last_name",last_name);
             request.setAttribute("mail_id",mail_id);
             request.setAttribute("button",button);
             session1.removeAttribute("image");
             // To change unapproved to approved in Grid
             //tx=session.beginTransaction();
             Query query = session.createQuery("FROM CirRequestfromOpac WHERE id.registrationId = :registrationId and libraryId = :libraryId");
                        query.setString("registrationId",registration_Id);
                        query.setString("libraryId", library_id);
                        CirRequestfromOpac cirreqopac= (CirRequestfromOpac)query.uniqueResult();
                         if (cirreqopac!=null)
                        {
                             cirreqopac.setStatus("Approved");
                             cirreqopac.setRegDate(cmdf.getTXTREG_DATE());
                             cirreqopac.setExpDate(cmdf.getTXTEXP_DATE());
                             session.update(cirreqopac);                             
                             tx.commit();
                         }
                       
            return mapping.findForward("Approved");



            }catch(RuntimeException e)
            {
                System.out.println("Exception Generated:"+ e);
                tx.rollback();
               // return mapping.findForward("failure");
                //throw e;

            }
          }
            }

            
        
        return mapping.findForward(SUCCESS);*/
    }
}
