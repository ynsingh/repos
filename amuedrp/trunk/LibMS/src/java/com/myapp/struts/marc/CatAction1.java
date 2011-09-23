/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.marc;

import com.myapp.struts.hbm.Biblio;
import com.myapp.struts.hbm.BiblioId;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author zeeshan
 */
public class CatAction1 extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
      private MarcHibDAO marchib=new MarcHibDAO();
    
    MarcHibDAO dao=new MarcHibDAO();
HashMap hm1 = new HashMap();
private Biblio bib=new Biblio();
    private Biblio biblio=new Biblio();
    private BiblioId biblioid= new BiblioId();

    private Biblio biblio1=new Biblio();
    private BiblioId biblioid1= new BiblioId();

    private Biblio biblio2=new Biblio();
    private BiblioId biblioid2= new BiblioId();
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("I'm now in CatACtion 1 !");
        CatActionForm1 caf1=(CatActionForm1) form;

         HttpSession session = request.getSession();
        int bibid = (Integer)session.getAttribute("biblio_id");
        System.out.println("************************************************  "+bibid);
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");


        String t=caf1.getZclick();                  // t is click value on jsp

        Character in1001,in1002,in1101,in1102,in1301,in1302;
        String z100,z100c,z100d,z100q,z100j,z100u,z1000,z110,z110b,z110c,z110d,z110k,z110n,z110p,z1100,z110t,z110u,z1104,
                z130,z130d,z130f,z130h,z130k,z130m,z130n,z130p,z130l,z130r,z130s,zclick;

        // getting values of indicator fields from CatActionForm1
        in1001=caf1.getIn1001();
        in1002=caf1.getIn1002();
        in1101=caf1.getIn1101();
        in1102=caf1.getIn1102();
        in1301=caf1.getIn1301();
        in1302=caf1.getIn1302();

        //getting values of subfields from CatActionForm1
        z100=caf1.getZ100();
                z100c=caf1.getZ100c();
                z100d=caf1.getZ100d();
                z100q=caf1.getZ100q();
                z100j=caf1.getZ100j();
                z100u=caf1.getZ100u();
                z1000=caf1.getZ1000();

                z110=caf1.getZ110();
                z110b=caf1.getZ110b();
                z110c=caf1.getZ110c();
                z110d=caf1.getZ110d();
                z110k=caf1.getZ110k();
                z110n=caf1.getZ110n();
                z110p=caf1.getZ110p();
                z1100=caf1.getZ1100();
                z110t=caf1.getZ110t();
                z110u=caf1.getZ110u();
                z1104=caf1.getZ1104();

                z130=caf1.getZ130();
                z130d=caf1.getZ130d();
                z130f=caf1.getZ130f();
                z130h=caf1.getZ130h();
                z130k=caf1.getZ130k();
                z130m=caf1.getZ130m();
                z130n=caf1.getZ130n();
                z130p=caf1.getZ130p();
                z130l=caf1.getZ130l();
                z130r=caf1.getZ130r();
                z130s=caf1.getZ130s();

//filling object with data for MARC Tag 100
    biblioid.setLibraryId(library_id);
    biblio.setSublibraryId(sub_library_id);
    biblioid.setMarctag("100");
    biblio.setIndicator1(in1001);
    biblio.setIndicator2(in1002);
        biblio.set$a(z100);
        biblio.set$c(z100c);
        biblio.set$d(z100d);
        biblio.set$q(z100q);
        biblio.set$j(z100j);
        biblio.set$u(z100u);
        biblio.set$0(z1000);

        biblioid.setBibId(bibid);
           biblio.setId(biblioid);
//           marchib.insert(biblio);
 hm1 = (HashMap)session.getAttribute("hsmp");

  if(hm1.containsKey("6")){
            hm1.remove("6");
        }
 hm1.put("6", biblio);
     

//filling object with data for MARC Tag 110
    biblioid1.setLibraryId(library_id);
    biblio1.setSublibraryId(sub_library_id);
    biblioid1.setMarctag("110");
    biblio1.setId(biblioid1);
    biblio1.setIndicator1(in1101);
    biblio1.setIndicator2(in1102);
        biblio1.set$a(z110);
        biblio1.set$b(z110b);
        biblio1.set$c(z110c);
        biblio1.set$d(z110d);
        biblio1.set$k(z110k);
        biblio1.set$n(z110n);
        biblio1.set$p(z110p);
        biblio1.set$0(z1100);
        biblio1.set$t(z110t);
        biblio1.set$u(z110u);
        biblio1.set$4(z1104);

        biblioid1.setBibId(bibid);
           biblio1.setId(biblioid1);
//           marchib.insert(biblio1);
            if(hm1.containsKey("7")){
            hm1.remove("7");
        }
 hm1.put("7", biblio1);
// bib=(Biblio)hm1.get("2");
// System.out.println("%%%%%%%%%%%% "+bib.get$a());
          //insert
     

    //filling object with data for MARC Tag 130
    biblioid2.setLibraryId(library_id);
    biblio2.setSublibraryId(sub_library_id);
    biblioid2.setMarctag("130");
    biblio2.setIndicator1(in1301);
    biblio2.setIndicator2(in1302);
        biblio2.set$a(z130);
        biblio2.set$d(z130d);
        biblio2.set$f(z130f);
        biblio2.set$h(z130h);
        biblio2.set$k(z130k);
        biblio2.set$m(z130m);
        biblio2.set$n(z130n);
        biblio2.set$p(z130p);
        biblio2.set$l(z130l);
        biblio2.set$r(z130r);
        biblio2.set$s(z130s);

        biblioid2.setBibId(bibid);
           biblio2.setId(biblioid2);
//           marchib.insert(biblio2);
            if(hm1.containsKey("8")){
            hm1.remove("8");
        }
hm1.put("8", biblio2);
          //insert
     
System.out.println(hm1.size());
session.setAttribute("hsmp", hm1);
        //code for mapping forwards......
         if(t.equals("0"))
        {
            return mapping.findForward("forward0");
        }
        else if(t.equals("2")){
                return mapping.findForward("forward2");
        }
        else if(t.equals("3")){
                return mapping.findForward("forward3");
        }
        else if(t.equals("4")){
                return mapping.findForward("forward4");
        }
        else if(t.equals("5")){
                return mapping.findForward("forward5");
        }
        else if(t.equals("6")){
                return mapping.findForward("forward6");
        }
        else if(t.equals("7")){
                return mapping.findForward("forward7");
        }
        else if(t.equals("8")){
                return mapping.findForward("forward8");
        }
        else if(t.equals("9"))
        {
                return mapping.findForward("forward9");
        }
        else if(t.equals("10"))
        {
        return mapping.findForward("forward10");
        }

        return mapping.findForward("forward1");
    }
}
