/*
      Design by Iqubal Ahmad
      Modified on 2011-02-02
     This Action Class is meant for Submitting password value into Cir_member_Account table.
 */

package com.myapp.struts.circulation;
import com.myapp.struts.CirculationDAO.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.*;
import com.myapp.struts.utility.PasswordEncruptionUtility;
import java.util.Iterator;
import java.util.List;


/**
 *
 * @author Iqubal Ahmad
 */
public class CirCreateAccountAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
   // private static final String SUCCESS = "success";
  //  private CirMemberAccount cma = new CirMemberAccount();
 //    private CirMemberAccountId cmd = new CirMemberAccountId();
    String no_of_issueable="";
    String memtype_id="";
    String submemtype_id="";
    
    private boolean result;
    
    
    
    /**
      Design by Iqubal Ahmad
      Modified on 2011-02-02
     This Action Class is meant for Submitting password value into Cir_member_Account table.
     
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CirCreateAccountActionForm cca = (CirCreateAccountActionForm)form;
        String button=cca.getButton();
        HttpSession session1 =request.getSession();
         String   library_id=(String)session1.getAttribute("library_id");
         String   sublibrary_id=(String)session1.getAttribute("sublibrary_id");
         String card_type=cca.getCard_type();

   /* CirMemberAccount member=(CirMemberAccount)CirculationDAO.searchCirMemAccountDetails(library_id,cca.getMem_id());
    if(member!=null)
    {
   // memtype_id=member.getMemType();
   // submemtype_id=member.getSubMemberType();


    }

    SubEmployeeType book=(SubEmployeeType)SubMemberDAO.searchIssueLimit(library_id,memtype_id,submemtype_id);

    if(book!=null)
    {
    no_of_issueable=String.valueOf(book.getNoOfIssueableBook());

    }

*/

       // Session session=HibernateUtil.getSessionFactory().getCurrentSession();
       //  Transaction tx=null;
    //  System.out.println(cca.getMail_id()+""+cca.getMem_name()+""+cca.getPassword()+library_id+cca.getMem_id()+button);

          if(button.equals("Submit"))
            {
           //    try
           //   {
           //   tx=session.beginTransaction();
           //   cmd.setLibraryId(library_id);
            //  cmd.setMemid(cca.getMem_id());
              
             // cma.setId(cmd);


            //  cma.setFine("0");
            //  cma.setLastchkoutdate("");
             // cma.setNoOfChkout("0");
             // cma.setTotalIssuedBook("0");
             // cma.setNoOfIssueableBook(no_of_issueable);
             // cma.setCurrentIssuedBook("0");
           //   cma.setReservationMade("0");
              CirMemberAccount cma=(CirMemberAccount)CirculationDAO.searchCirMemAccountDetails(library_id,sublibrary_id, cca.getMem_id());

             

              if(card_type.equalsIgnoreCase("gc")){

              
              cma.setCardId(cca.getCard_id());



              cma.setStatus("Active");
              String password = cca.getPassword();
              password=PasswordEncruptionUtility.password_encrupt(password);
              //cma.setMemname(cca.getMem_name());
              cma.setPassword(password);
          //   session.save(cma);
         //   tx.commit();

              result=CirculationDAO.updateAccount(cma);
              if(result==true){
                  List<CirMemberAccount> lst =(List<CirMemberAccount>)CirculationDAO.searchCirMemAccountDetailsLst(library_id, sublibrary_id,cca.getMem_id());
                  Iterator it = lst.iterator();
                  while(it.hasNext()){
                      CirMemberAccount cirmemac = (CirMemberAccount)it.next();
                      cirmemac.setReqDate("");
                      cirmemac.setExpiryDate("");
                      boolean result1=CirculationDAO.updateAccount(cirmemac);
                  }
               
            String msg="Account For Member Id:"+cma.getId().getMemid()+" Created and confirmation mail sent successfully.";
              request.setAttribute("memid",cca.getMem_id());
            request.setAttribute("msg",msg);
             return mapping.findForward("Submit");
              }
              }else{

              String []lm=(String[])session1.getAttribute("noofaccount");
              for(int i=0;i<lm.length;i++)
              {
               CirMemberAccount cma1=(CirMemberAccount)CirculationDAO.searchCirMemAccountDetails(library_id,lm[i], cca.getMem_id());
                cma1.setCardId(cca.getCard_id());
                cma1.setStatus("Active");

                String password = cca.getPassword();
              password=PasswordEncruptionUtility.password_encrupt(password);
              //cma.setMemname(cca.getMem_name());
              cma1.setPassword(password);
                 result=CirculationDAO.updateAccount(cma1);
                     
                      }
 if(result==true){
                    String msg="Account For Member Id:"+cma.getId().getMemid()+" Created and confirmation mail sent successfully.";
                    request.setAttribute("memid",cca.getMem_id());
                 request.setAttribute("msg",msg);
                     return mapping.findForward("Submit");
              }





              }
           
          
          }
  return null;
        
       
    }
}
