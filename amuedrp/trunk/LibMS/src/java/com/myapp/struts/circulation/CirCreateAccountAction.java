/*
Design by Iqubal Ahmad
Modified on 2011-02-02
This Action Class is meant for Submitting password value into Cir_member_Account table.
 */
package com.myapp.struts.circulation;

import com.myapp.struts.CirDAO.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.*;
import java.util.Iterator;
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
 * @author Iqubal Ahmad
 */
public class CirCreateAccountAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    // private static final String SUCCESS = "success";
    //  private CirMemberAccount cma = new CirMemberAccount();
    //    private CirMemberAccountId cmd = new CirMemberAccountId();
    String no_of_issueable = "";
    String memtype_id = "";
    String submemtype_id = "";
    private final ExecutorService executor = Executors.newFixedThreadPool(1);
    private boolean result;
    private String password;
    private String password1;
    Email obj;
     Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";

    /**
    Design by Iqubal Ahmad
    Modified on 2011-02-02
    This Action Class is meant for Submitting password value into Cir_member_Account table.

     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
             HttpSession session1 = request.getSession();
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
        CirCreateAccountActionForm cca = (CirCreateAccountActionForm) form;
        String button = cca.getButton();
       
        String library_id = (String) session1.getAttribute("library_id");
        String sublibrary_id = (String) session1.getAttribute("sublibrary_id");
        String card_type = cca.getCard_type();


        if (button.equals("Submit")) {
            CirMemberAccount cma = (CirMemberAccount) CirculationDAO.searchCirMemAccountDetails(library_id, sublibrary_id, cca.getMem_id());



            if (card_type.equalsIgnoreCase("gc")) {


                cma.setCardId(cca.getCard_id());



                cma.setStatus("Active");
                /*Password Generate and Reset It*/
                password = RandomPassword.getRandomString(10);
                System.out.println(password);

                password1 = PasswordEncruptionUtility.password_encrupt(password);

                cma.setPassword(password1);

                result = CirculationDAO.updateAccount(cma);

                if (result == true) {
                    CirMemberDetail cma1 = (CirMemberDetail) CirculationDAO.searchCirMemDetails(library_id, cca.getMem_id());
                    //"Member Account Created Successfully for LibMS OPAC Login", "Login Id=" + cca.getMem_id() + " Your Password for LibMS OPAC Login is=" + password
                      String path = servlet.getServletContext().getRealPath("/");
                      obj=new Email(path,cma1.getEmail(),password,"Congruation,Your are Registered as Library Member","You Have been registered as a valid Library member for Library Name"+session1.getAttribute("library_name").toString()+"\nYour Member Account as Follows \nUser Id:"+cca.getMem_id()+"\nPassword:"+password+".\n","Dear "+cca.getMem_name()+",\n","Thanks,\n"+session1.getAttribute("username")+",\n"+"Institute Admin");
                    //obj = new Email(path,cma1.getEmail(), password, resource.getString("circulation.circreateaccaction.memacccreatforopac"), resource.getString("circulation.circreateaccaction.loginid") + cca.getMem_id() + resource.getString("circulation.circreateaccaction.yourpassforlogin") + password);
                    executor.submit(new Runnable() {

                        public void run() {
                            obj.send();
                        }
                    });

                    List<CirMemberAccount> lst = (List<CirMemberAccount>) CirculationDAO.searchCirMemAccountDetailsLst(library_id, sublibrary_id, cca.getMem_id());
                    Iterator it = lst.iterator();
                    while (it.hasNext()) {
                        CirMemberAccount cirmemac = (CirMemberAccount) it.next();
                        cirmemac.setReqDate("");
                        cirmemac.setExpiryDate("");
                        boolean result1 = CirculationDAO.updateAccount(cirmemac);
                    }

                    //Account For Member Id:" + cma.getId().getMemid() + " Created and confirmation mail sent successfully
                    String msg = resource.getString("circulation.circreateaccaction.accformemid") + cma.getId().getMemid() + resource.getString("circulation.circreateaccaction.confirmailsend");
                    request.setAttribute("memid", cca.getMem_id());
                    request.setAttribute("msg", msg);
                    return mapping.findForward("Submit");
                }
            } else {
                
                String[] lm = (String[]) session1.getAttribute("noofaccount");

                for (int i = 0; i < lm.length; i++) {
                    CirMemberAccount cma1 = (CirMemberAccount) CirculationDAO.searchCirMemAccountDetails(library_id, lm[i], cca.getMem_id());
                    cma1.setCardId(cca.getCard_id());
                    cma1.setStatus("Active");

                    /*Password Generate and Reset It*/
                    password = RandomPassword.getRandomString(10);
                    System.out.println(password);

                    password1 = PasswordEncruptionUtility.password_encrupt(password);



                    cma1.setPassword(password1);
                    result = CirculationDAO.updateAccount(cma1);

                }
                if (result == true) {
                    session1.removeAttribute("noofaccount");
                    CirMemberDetail cma1 = (CirMemberDetail) CirculationDAO.searchCirMemDetails(library_id, cca.getMem_id());
               //Member Account Created Successfully for LibMS OPAC Login", "Login Id=" + cca.getMem_id() + " Your Password for LibMS OPAC Login is=" + password
                       String path = servlet.getServletContext().getRealPath("/");
                   obj=new Email(path,cma1.getEmail(),password,"Congruation,Your are Registered as Library Member","You Have been registered as a valid Library member for Library Name"+session1.getAttribute("library_name").toString()+"\nYour Member Account as Follows \nUser Id:"+cca.getMem_id()+"\nPassword:"+password+".\n","Dear "+cca.getMem_name()+",\n","Thanks,\n"+session1.getAttribute("username")+",\n"+"Institute Admin");
                       
                    executor.submit(new Runnable() {

                        public void run() {
                            obj.send();
                        }
                    });
                    //Account For Member Id:" + cma.getId().getMemid() + " Created and confirmation mail sent successfully
                    String msg = resource.getString("circulation.circreateaccaction.accformemid") + cma.getId().getMemid() + resource.getString("circulation.circreateaccaction.confirmailsend");
                    request.setAttribute("memid", cca.getMem_id());
                    request.setAttribute("msg", msg);
                    return mapping.findForward("Submit");
                }





            }


        }
        return null;


    }
}
