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
import com.myapp.struts.CirDAO.CirculationDAO;
import com.myapp.struts.hbm.*;
import com.myapp.struts.systemsetupDAO.SubMemberDAO;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.myapp.struts.utility.Email;
import com.myapp.struts.utility.PasswordEncruptionUtility;
import com.myapp.struts.utility.RandomPassword;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author edrp02
 */
public class CirViewUpdateAccountAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
private final ExecutorService executor=Executors.newFixedThreadPool(1);
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
    private String password1;
    Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";
    Email obj;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        CirculationDAO cirdao=new CirculationDAO();
        SubMemberDAO submemdao=new SubMemberDAO();
        HttpSession session=request.getSession();
         try{

        locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
        System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align = "left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
        CirViewUpdateAccountActionForm cvuaf=(CirViewUpdateAccountActionForm)form;
        mem_id=cvuaf.getMem_id();
        password=cvuaf.getPassword();
       /*Password Generate and Reset It*/
                 password= RandomPassword.getRandomString(10);
                 System.out.println(password);
                  password1=PasswordEncruptionUtility.password_encrupt(password);
        card_id=cvuaf.getCard_id();
        button=cvuaf.getButton();
       
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




        CirMemberAccount cirmemberaccount=cirdao.searchCirMemAccountDetails(library_id, sublibrary_id, mem_id);
       System.out.println("Updated.....................");

        if(button.equals("Update"))
        {
          cirmemberaccount.setPassword(password1);
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

 SubEmployeeType book=(SubEmployeeType)submemdao.searchIssueLimit(library_id,MEMCAT,MEMSUBCAT);

    if(book!=null)
    {
    no_of_issueable=String.valueOf(book.getNoOfIssueableBook());

    }

System.out.println(no_of_issueable+"...................");





              cirmemberaccount.setNoOfIssueableBook(no_of_issueable);














 CirMemberDetail cirobj=cirdao.searchCirMemDetails(library_id, mem_id);
    //"Update Circulation Member Account :Password Reset Successfully from LibMS","User Id="+mem_id+" Your Password for LibMS OPAC Login is="+password
    String path = servlet.getServletContext().getRealPath("/");
 obj=new Email(cirobj.getEmail(),password,"Update Circulation Member Account as Library Member","Dear "+cirobj.getFname()+" "+cirobj.getMname()+" "+cirobj.getLname()+"Your Account is Updated for Library member for Library Name "+session.getAttribute("library_name").toString()+"\nYour Member Account as Follows \nUser Id:"+mem_id+"\nPassword:"+password+"\nThanks,\n"+session.getAttribute("username")+",\n"+"Institute Admin");
  
            executor.submit(new Runnable() {

                public void run() {
                    obj.send();
                }
            });

          result=cirdao.updateAccount(cirmemberaccount);
          if(result==true)
          {
             


            //  Record Updated Successfully
            request.setAttribute("msg",resource.getString("circulation.circulationnewmemberregAction.recupdatesucc"));
            return mapping.findForward("success");
          }
          else
          {
            //Record Not Updated
              request.setAttribute("msg1",resource.getString("circulation.circulationnewmemberregAction.recupdatenotsecc"));
            return mapping.findForward("fail");

          }

        }

        if(button.equals("Delete"))
        {
System.out.println("hggggggggggggggg");
         List<CirCheckout> chkobj=(List<CirCheckout>)cirdao.searchCheckoutMemDetails(library_id,sublibrary_id, mem_id);

       

        if(!chkobj.isEmpty())
        {
            //Member Checkout are there ,So Cannot Be Deleted
            String msg1=resource.getString("circulation.circulationnewmemberregAction.memchkcannotdel");
             request.setAttribute("msg1",msg1);
            return mapping.findForward("fail");

        }

            CirMemberDetail cirobj=cirdao.searchCirMemDetails(library_id, mem_id);
            //Delete Circulation Member Account Successfully from LibMS","Your Account is Successfully deleted from LibMS OPAC Login"
           
           result=cirdao.deleteAccount(library_id, sublibrary_id, mem_id);

           if(result==true)
           {
                  String path = servlet.getServletContext().getRealPath("/");
        
          obj=new Email(cirobj.getEmail(),"","Update LibMS Member Account","Dear "+cirobj.getFname()+" "+cirobj.getMname()+" "+cirobj.getLname()+",\nSorry Your Member Account is Deleted for Library Name "+session.getAttribute("library_name").toString()+"\nThanks,\n"+session.getAttribute("username")+",\n"+"Institute Admin");
            executor.submit(new Runnable() {

                public void run() {
                    obj.send();
                }
            });
            //Record Deleted Successfully
               request.setAttribute("msg",resource.getString("circulation.circulationnewmemberregAction.recdelsucc"));
            return mapping.findForward("success");
           }
           else
           {
            //Record Not Deleted
               request.setAttribute("msg1",resource.getString("circulation.circulationnewmemberregAction.memnotdelsucc"));
            return mapping.findForward("fail");

           }



        }



        return mapping.findForward("");
    }
}
