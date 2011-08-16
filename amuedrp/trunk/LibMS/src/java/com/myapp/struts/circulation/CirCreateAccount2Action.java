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
import com.myapp.struts.utility.*;
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
public class CirCreateAccount2Action extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String mem_id,library_id,sublibrary_id;
    private final ExecutorService executor=Executors.newFixedThreadPool(1);
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
private String password1;
 Email obj;
  Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
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
        CirCreateAccount2ActionForm ccaaf=(CirCreateAccount2ActionForm)form;
        mem_id=ccaaf.getMem_id();
        password=ccaaf.getPassword();
       /*Password Generate and Reset It*/
                 password= RandomPassword.getRandomString(10);
                 System.out.println(password);
                  password1=PasswordEncruptionUtility.password_encrupt(password);
        card_id=ccaaf.getCard_id();
       
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
      
        CirMemberAccount cirmemac=CirculationDAO.getAccount(library_id, library, mem_id);
        if(cirmemac==null)cirmemac = new CirMemberAccount();
        if(cirmemac.getId()==null){
        CirMemberAccountId cirmemId = new CirMemberAccountId(library_id, library, mem_id);
            cirmemac.setId(cirmemId);
        }
        cirmemac.setCardId(card_id);
        cirmemac.setPassword(password1);
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
                CirMemberDetail cirobj=CirculationDAO.searchCirMemDetails(library_id, mem_id);
                //Create Member Account  Successfully from LibMS OPAC Login","User Id="+mem_id+" Your Password for LibMS OPAC Login is="
                  String path = servlet.getServletContext().getRealPath("/");
            obj=new Email(path,cirobj.getEmail(),password,"Congruation,Your are Registered as Library Member","You Have been registered as a valid Library member for Library Name"+session.getAttribute("library_name").toString()+"\nYour Member Account as Follows \nUser Id:"+mem_id+"\nPassword:"+password+".\n","Dear "+cirobj.getMname()+" "+cirobj.getMname()+" "+cirobj.getLname()+",\n","Thanks,\n"+session.getAttribute("username")+",\n"+"Institute Admin");
            
            executor.submit(new Runnable() {

                public void run() {
                    obj.send();
                }
            });
        result=CirculationDAO.insert1(cirmemac);
        if(result==true)
        {
           
         //Account For Member Id:"+mem_id+" Created and confirmation mail sent successfully.
          request.setAttribute("msg", resource.getString("circulation.circreateaccaction.accformemid")+mem_id+resource.getString("circulation.circreateaccaction.confirmailsend"));
          return mapping.findForward("success");  
        }    
        else
        {
            //Account Not Created
            request.setAttribute("msg", resource.getString("circulation.circreateaccaction.accnotcreated"));
          return mapping.findForward("fail");
         
        }

        
    }
}
