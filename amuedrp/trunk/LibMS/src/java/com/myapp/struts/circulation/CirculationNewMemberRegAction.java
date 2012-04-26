/*
      Design by Iqubal Ahmad
      Modified on 2011-02-02
     This Action Class is meant for Submit,Update,Deleate Record During process of member Registration.
     This Action Class is meant for Submit values into Cir_member_Detail table.
 */

package com.myapp.struts.circulation;


import com.myapp.struts.systemsetupDAO.*;
import com.myapp.struts.CirDAO.CirculationDAO;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.*;
import com.myapp.struts.utility.AppPath;
import com.myapp.struts.utility.UserLog;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.struts.upload.FormFile;



/**
 *
 * @author Iqubal Ahmad
 */
public class CirculationNewMemberRegAction extends org.apache.struts.action.Action {


    private boolean result;
  

    private CirMemberDetail cmd = new CirMemberDetail();
    private CirMemberDetailId cmdi = new CirMemberDetailId();

    private CirMemberAccount cma = new CirMemberAccount();
    private CirMemberAccountId cmai = new CirMemberAccountId();

    private String no_of_issueable;
    String library_id;
    String sublibrary_id;
    private String lib;
    Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";




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
         HttpSession session1 =request.getSession();
         try{

        locale1=(String)session1.getAttribute("locale");
    if(session1.getAttribute("locale")!=null)
    {
        locale1 = (String)session1.getAttribute("locale");
        System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align = "left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
        CirculationMemberActionForm cmdf =(CirculationMemberActionForm)form;
       
      String  member_id = cmdf.getTXTMEMID();
      String last_name=cmdf.getTXTLNAME();
      String mail_id=cmdf.getTXTEMAILID();
       String button=cmdf.getButton();
       String mem_name=cmdf.getTXTFNAME();
       FormFile v=(FormFile)session1.getAttribute("filename");
       byte[] iii=null;
       if(v!=null)iii=v.getFileData();


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
            System.out.println(cmdf.getTempreg()+".........................");
            if(cmdf.getTempreg()!=null)
            {
            cmd.setCollege(cmdf.getCollege());
            cmd.setCollAddress(cmdf.getColladd());
            cmd.setTempStatus("Y");
            }

         System.out.println(cmdf.getImg()+v.getFileName());
            if(v.getFileName()!=null)
            {
                iii=v.getFileData();
                String ext=v.getFileName().substring(v.getFileName().indexOf(".")+1,v.getFileName().length());
                UserLog.writeImage(member_id+"."+ext, iii);
                cmd.setImage(member_id+"."+ext);
            }
           

            

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

           // String msg="Record Inserted successfully";
             String msg=resource.getString("circulation.circulationnewmemberregAction.recinsesucc");
            request.setAttribute("msg",msg);
            request.setAttribute("mem_id",member_id);
             request.setAttribute("mem_name",mem_name);
             request.setAttribute("last_name",last_name);
             request.setAttribute("mail_id",mail_id);
             request.setAttribute("button",button);
             session1.removeAttribute("image");
            return mapping.findForward("Submit");
            }
            session1.removeAttribute("filename");
            session1.removeAttribute("image");

        request.removeAttribute("imagechange");
            return mapping.findForward("failure");
          }

         if(button.equals("Update"))
            {



               sublibrary_id=(String)session1.getAttribute("sublibrary_id") ;



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


         
            if(cmdf.getImg()!=null)
            {
                iii=cmdf.getImg().getFileData();
                String ext=v.getFileName().substring(v.getFileName().indexOf(".")+1,v.getFileName().length());
                UserLog.writeImage(member_id+"."+ext, iii);
                cmd.setImage(member_id+"."+ext);
            }
           
            
            result=CirculationDAO.update(cmd1);


            //Account Updation


             CirMemberAccount cma = (CirMemberAccount)CirculationDAO.searchCirMemAccountDetails(library_id, sublibrary_id, member_id);

if(cma!=null)
{



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





              cma.setNoOfIssueableBook(no_of_issueable);









            cma.setSemester(cmdf.getTXTSEM());

            result=CirculationDAO.updateAccount(cma);



}



            if(result==true)

            {

               // String msg1="Record updated successfully";
                 String msg1=resource.getString("circulation.circulationnewmemberregAction.recupdatesucc");
                request.setAttribute("msg1",msg1);
                request.setAttribute("mem_id",member_id);
                request.setAttribute("mem_name",mem_name);
                request.setAttribute("button",button);
                System.out.println("Update Transaction kay Baad");
                return mapping.findForward("Update");
            }
            else
            {
                //String msg1="Record updated Not successfully";
                String msg1=resource.getString("circulation.circulationnewmemberregAction.recupdatenotsecc");
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
              // String msg1="Member Account is not Existing your SubLibrary ";
              String msg1=resource.getString("circulation.circulationnewmemberregAction.memaccnotexistsublib");
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
             // String msg1="Member Checkout are there ,So Cannot Be Deleted";
              String msg1=resource.getString("circulation.circulationnewmemberregAction.memchkcannotdel");
              request.setAttribute("err",msg1);
              request.setAttribute("mem_id",member_id);
              request.setAttribute("mem_name",mem_name);
              request.setAttribute("button",button);
              return mapping.findForward("Delete");
        }
          result=CirculationDAO.deleteAccount(library_id, sublibrary_id, member_id);







            if(result==true)

            {
              //String msg1="Member Record Deleted Successfully from This SubLibrary ";
              String msg1=resource.getString("circulation.circulationnewmemberregAction.memrecdelsuccfromsublib");
              request.setAttribute("msg1",msg1);
              request.setAttribute("lib",lib);
              request.setAttribute("mem_id",member_id);
              request.setAttribute("mem_name",mem_name);
              request.setAttribute("button",button);
              return mapping.findForward("Delete");
            }else{

             // String msg2="Record Not Deleted Successfully";
              String msg2=resource.getString("circulation.circulationnewmemberregAction.memnotdelsucc");
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
 
