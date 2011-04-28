/*
      Design by Iqubal Ahmad
      Modified on 2011-02-02
     This Action Class is meant for Submit,Update,Deleate Record During process of member Registration.
     This Action Class is meant for Submit values into Cir_member_Detail table.
 */

package com.myapp.struts.circulation;


import com.myapp.struts.systemsetupDAO.*;
import com.myapp.struts.CirculationDAO.CirculationDAO;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.*;
import java.util.List;
import org.apache.struts.upload.FormFile;



/**
 *
 * @author Iqubal Ahmad
 */
public class CirculationNewMemberRegAction extends org.apache.struts.action.Action {


    private boolean result;
    private static String SUCCESS = "success";

    private CirMemberDetail cmd = new CirMemberDetail();
    private CirMemberDetailId cmdi = new CirMemberDetailId();

    private CirMemberAccount cma = new CirMemberAccount();
    private CirMemberAccountId cmai = new CirMemberAccountId();

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
private String mem_id;
private String faculty_id;
    private String no_of_issueable;
    String library_id;
    String sublibrary_id;
    private String lib;



    /**
      Design by Iqubal Ahmad
      Modified on 2011-02-02
     This Action Class is meant for Submit,Update,Deleate Record During process of member Registration.
     This Action Class is meant for Submit values into Cir_member_Detail table.
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        CirculationMemberActionForm cmdf =(CirculationMemberActionForm)form;
        HttpSession session1 =request.getSession();
      // ImageUploadActionForm form1 = (ImageUploadActionForm)session1.getAttribute("CirImageUploadActionForm");
      String  member_id = cmdf.getTXTMEMID();
      String last_name=cmdf.getTXTLNAME();
      String mail_id=cmdf.getTXTEMAILID();
       String button=cmdf.getButton();
       String mem_name=cmdf.getTXTFNAME();
       FormFile v=(FormFile)session1.getAttribute("filename");
       byte[] iii=null;
       if(v!=null)iii=v.getFileData();


    // byte[] g=(byte[])session1.getAttribute("image");
 //    System.out.println("IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII"+iii.length);
       library_id=(String)session1.getAttribute("library_id");
       sublibrary_id=null;


            if(button!=null)
            if(button.equals("Submit"))
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
            cmd.setCreatedBy((String)session1.getAttribute("sublibrary_id"));


         if (cmdf.getImg()!=null)
            cmd.setImage(cmdf.getImg().getFileData());
         else
               if(iii!=null){cmd.setImage(iii);}


            CirculationDAO.insert(cmd);

             session1.removeAttribute("filename");

            String[] sublib;
            if( cmdf.getLibrary()==null)
            {
                sublib=new String[1];
                sublib[0]=(String)session1.getAttribute("sublibrary_id");

            }
            else
                sublib=cmdf.getLibrary();

            System.out.println(sublib.length);
           for(int i=0;i<sublib.length;i++)
            {
              CirMemberAccountId  cmai = new CirMemberAccountId();
              CirMemberAccount cma = new CirMemberAccount();
            cmai.setLibraryId(library_id);
            System.out.println("sublibrary_id = "+sublib[i]);
            cmai.setSublibraryId(sublib[i]);
            cmai.setMemid(member_id);
            session1.setAttribute("noofaccount",sublib );

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

    SubEmployeeType book=(SubEmployeeType)SubMemberDAO.searchIssueLimit(library_id,cmdf.getMEMCAT(),cmdf.getMEMSUBCAT());

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

            result=CirculationDAO.insert(cma);
            }
            if(result==true)
            {
            String msg="Record Inserted successfully";
            request.setAttribute("msg",msg);
            request.setAttribute("mem_id",member_id);
             request.setAttribute("mem_name",mem_name);
             request.setAttribute("last_name",last_name);
             request.setAttribute("mail_id",mail_id);
             request.setAttribute("button",button);
             session1.removeAttribute("image");
            return mapping.findForward("Submit");
            }
            return mapping.findForward("failure");
          }

         if(button.equals("Update"))
            {



               sublibrary_id=(String)session1.getAttribute("sublibrary_id") ;



                    faculty_id = cmdf.getTXTFACULTY();





                        mem_id =cmdf.getMEMCAT();



                    CirMemberDetail cmd1=(CirMemberDetail)CirculationDAO.searchCirMemDetails(library_id,  member_id);

            cmd1.setFname(cmdf.getTXTFNAME());
            cmd1.setMname(cmdf.getTXTMNAME());
            cmd1.setLname(cmdf.getTXTLNAME());
            cmd1.setEmail(cmdf.getTXTEMAILID());
            cmd1.setAddress1(cmdf.getTXTADD1());
            cmd1.setAddress2(cmdf.getTXTADD2());
            cmd1.setCity1(cmdf.getTXTCITY1());
            cmd1.setState1(cmdf.getTXTSTATE1());
            cmd1.setCountry1(cmdf.getTXTCOUNTRY1());
            cmd1.setCity2(cmdf.getTXTCITY2());
            cmd1.setState2(cmdf.getTXTSTATE2());
            cmd1.setCountry2(cmdf.getTXTCOUNTRY2());
            cmd1.setPhone1(cmdf.getTXTPH1());
            cmd1.setPhone2(cmdf.getTXTPH2());
            cmd1.setPin1(cmdf.getTXTPIN1());
            cmd1.setPin2(cmdf.getTXTPIN2());
            cmd1.setFax(cmdf.getTXTFAX());
           /* if (cmdf.getImg()!=null)
            cmd1.setImage(cmdf.getImg().getFileData());
             else
                cmd.setImage(cmdf.getUploadedFile());*/
            result=CirculationDAO.update(cmd1);


            //Account Updation


             CirMemberAccount cma = (CirMemberAccount)CirculationDAO.searchCirMemAccountDetails(library_id, sublibrary_id, member_id);

if(cma!=null)
{


//if(cmdf.getMEMCAT()!=null || cmdf.getMEMSUBCAT()!=null || cmdf.getTXTDESG1()!=null || cmdf.getTXTOFFICE()!=null ||cmdf.getTXTFACULTY()!=null || cmdf.getTXTCOURSE()!=null || cmdf.getTXTDEPT()!=null ||cmdf.getTXTREG_DATE()!=null || cmdf.getTXTEXP_DATE()!=null)

            cma.setMemType(cmdf.getMEMCAT());
            cma.setSubMemberType(cmdf.getMEMSUBCAT());
            cma.setDesg(cmdf.getTXTDESG1());
            cma.setOffice(cmdf.getTXTOFFICE());
            cma.setFacultyId(cmdf.getTXTFACULTY());
            cma.setDeptId(cmdf.getTXTDEPT());
            cma.setCourseId(cmdf.getTXTCOURSE());
            cma.setReqDate(cmdf.getTXTREG_DATE());
            cma.setExpiryDate(cmdf.getTXTEXP_DATE());


    SubEmployeeType book=(SubEmployeeType)SubMemberDAO.searchIssueLimit(library_id,cmdf.getMEMCAT(),cmdf.getMEMSUBCAT());

    if(book!=null)
    {
    no_of_issueable=String.valueOf(book.getNoOfIssueableBook());

    }

System.out.println(no_of_issueable+"...................");





              cma.setNoOfIssueableBook(no_of_issueable);









            cma.setSemester(cmdf.getTXTSEM());

            result=CirculationDAO.updateAccount(cma);



}



            if(result==true)

            {
                String msg1="Record updated successfully";
                request.setAttribute("msg1",msg1);
                request.setAttribute("mem_id",member_id);
                request.setAttribute("mem_name",mem_name);
                request.setAttribute("button",button);
                System.out.println("Update Transaction kay Baad");
                return mapping.findForward("Update");
            }
            else
            {
                String msg1="Record updated Not successfully";
                request.setAttribute("err",msg1);
                request.setAttribute("mem_id",member_id);
                request.setAttribute("mem_name",mem_name);
                request.setAttribute("button",button);
                System.out.println("Update Transaction kay Baad");
                return mapping.findForward("Update");

            }

          }


        if(button.equals("Delete"))
          {


       sublibrary_id=(String)session1.getAttribute("sublibrary_id");
        System.out.println("Delete Button recieve"+library_id+sublibrary_id+member_id);
        List<CirMemberAccount> cirmemobj=(List<CirMemberAccount>)CirculationDAO.searchCirMemAccountDetails(library_id,member_id);

        System.out.println(cirmemobj.size()+"Account of Member");
        if(cirmemobj.size()>1)
        {
        lib="Member Account Exist in More Than One Library ";

        }
        if(cirmemobj.isEmpty())
        {
                String msg1="Member Account is not Existing your Library ";
              request.setAttribute("err",msg1);
              request.setAttribute("lib",lib);
              request.setAttribute("mem_id",member_id);
              request.setAttribute("mem_name",mem_name);
              request.setAttribute("button",button);
              return mapping.findForward("Delete");
        }
System.out.println(library_id+sublibrary_id+member_id+"........................");
        List<CirCheckout> chkobj=(List<CirCheckout>)CirculationDAO.searchCheckoutMemDetails(library_id,sublibrary_id, member_id);

        System.out.println(chkobj+"........................");

        if(chkobj!=null && !chkobj.isEmpty())
        {
            String msg1="Member Checkout are there ,So Cannot Be Deleted";
              request.setAttribute("err",msg1);
              request.setAttribute("mem_id",member_id);
              request.setAttribute("mem_name",mem_name);
              request.setAttribute("button",button);
              return mapping.findForward("Delete");
        }
          result=CirculationDAO.deleteAccount(library_id, sublibrary_id, member_id);







            if(result==true)

            {
              String msg1="Member Record Deleted Successfully from This SubLibrary ";
              request.setAttribute("msg1",msg1);
              request.setAttribute("lib",lib);
              request.setAttribute("mem_id",member_id);
              request.setAttribute("mem_name",mem_name);
              request.setAttribute("button",button);
              return mapping.findForward("Delete");
            }else{

              String msg2="Record Not Deleted Successfully";
              request.setAttribute("err",msg2);
              request.setAttribute("mem_id",member_id);
              request.setAttribute("mem_name",mem_name);
              request.setAttribute("button",button);
              return mapping.findForward("Delete");
            }


          }



return null;
    }

}
 
