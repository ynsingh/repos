/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.marc;

import com.myapp.struts.hbm.Biblio;
import com.myapp.struts.hbm.BiblioId;
import java.util.HashMap;
import java.util.List;
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
public class UCatAction5 extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    HashMap hm1=new HashMap();
    private MarcHibDAO marchib=new MarcHibDAO();



    private Biblio biblio=new Biblio();
    private BiblioId biblioid= new BiblioId();

    private Biblio biblio1=new Biblio();
    private BiblioId biblioid1= new BiblioId();

    private Biblio biblio2=new Biblio();
    private BiblioId biblioid2= new BiblioId();

    private Biblio biblio3=new Biblio();
    private BiblioId biblioid3= new BiblioId();

    private Biblio biblio4=new Biblio();
    private BiblioId biblioid4= new BiblioId();


    private Biblio biblio5=new Biblio();
    private BiblioId biblioid5= new BiblioId();
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

        System.out.println("inside Ucataction5 !");
        CatActionForm5 caf5=(CatActionForm5)form;

        HttpSession session = request.getSession();
        int  bibid = Integer.parseInt((String)session.getAttribute("biblio_id"));
        System.out.println("************************************************  "+bibid);
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");



        String t=caf5.getZclick();             // t is click value on jsp

        String z500a,z5003, z502a,z502b,z502c,z502d,z502g,z502o, z504a,z504b, z505a,z505g,z505r,z505t,z505u, z520a,z520b,z520c,z520u,z5202,z5203, z546a,z546b,z5463;
        Character in5001,in5002,in5021,in5022,in5041,in5042,in5051,in5052,in5201,in5202,in5461,in5462;

        // getting values of indicator fields from CatActionForm5
            in5001=caf5.getIn5001();
            in5002=caf5.getIn5002();
            in5021=caf5.getIn5021();
            in5022=caf5.getIn5022();
            in5041=caf5.getIn5041();
            in5042=caf5.getIn5042();
            in5051=caf5.getIn5051();
            in5052=caf5.getIn5052();
            in5201=caf5.getIn5201();
            in5202=caf5.getIn5202();
            in5461=caf5.getIn5461();
            in5462=caf5.getIn5462();

        //getting values of subfields from CatActionForm5
            z500a=caf5.getZ500a();
            z5003=caf5.getZ5003();

            z502a=caf5.getZ502a();
            z502b=caf5.getZ502b();
            z502c=caf5.getZ502c();
            z502d=caf5.getZ502d();
            z502g=caf5.getZ502g();
            z502o=caf5.getZ502o();

            z504a=caf5.getZ504a();
            z504b=caf5.getZ504b();

            z505a=caf5.getZ505a();
            z505g=caf5.getZ505g();
            z505r=caf5.getZ505r();
            z505t=caf5.getZ505t();
            z505u=caf5.getZ505u();

            z520a=caf5.getZ520a();
            z520b=caf5.getZ520b();
            z520c=caf5.getZ520c();
            z520u=caf5.getZ520u();
            z5202=caf5.getZ5202();
            z5203=caf5.getZ5203();

            z546a=caf5.getZ546a();
            z546b=caf5.getZ546b();
            z5463=caf5.getZ5463();

            //filling object with data for MARC Tag 500
    biblioid.setLibraryId(library_id);
    biblio.setSublibraryId(sub_library_id);
    biblioid.setMarctag("500");
    biblio.setIndicator1(in5001);
    biblio.setIndicator2(in5002);
        biblio.set$3(z5003);
        biblio.set$a(z500a);



          biblioid.setBibId(bibid);
           biblio.setId(biblioid);
//           marchib.insert(biblio);
hm1=(HashMap)session.getAttribute("hsmp");
if(hm1.containsKey("20")){
            hm1.remove("20");
        }
 hm1.put("20", biblio);


         //filling object with data for MARC Tag 502
    biblioid1.setLibraryId(library_id);
    biblio1.setSublibraryId(sub_library_id);
    biblioid1.setMarctag("502");
    biblio1.setIndicator1(in5021);
    biblio1.setIndicator2(in5022);
        biblio1.set$a(z502a);
        biblio1.set$b(z502b);
        biblio1.set$c(z502c);
        biblio1.set$d(z502d);
        biblio1.set$g(z502g);
        biblio1.set$g(z502o);

        biblioid1.setBibId(bibid);
           biblio1.setId(biblioid1);
          //           marchib.insert(biblio1);
if(hm1.containsKey("21")){
            hm1.remove("21");
        }
 hm1.put("21", biblio1);




        //filling object with data for MARC Tag 504
    biblioid2.setLibraryId(library_id);
    biblio2.setSublibraryId(sub_library_id);
    biblioid2.setMarctag("504");
    biblio2.setIndicator1(in5041);
    biblio2.setIndicator2(in5042);
        biblio2.set$a(z504a);
        biblio2.set$b(z504b);

       biblioid2.setBibId(bibid);
           biblio2.setId(biblioid2);
//           marchib.insert(biblio2);
if(hm1.containsKey("22")){
            hm1.remove("22");
        }
 hm1.put("22", biblio2);


    //filling object with data for MARC Tag 505
    biblioid3.setLibraryId(library_id);
    biblio3.setSublibraryId(sub_library_id);
    biblioid3.setMarctag("505");
    biblio3.setIndicator1(in5051);
    biblio3.setIndicator2(in5052);
        biblio3.set$a(z505a);
        biblio3.set$g(z505g);
        biblio3.set$r(z505r);
        biblio3.set$t(z505t);
        biblio3.set$u(z505u);

         biblioid3.setBibId(bibid);
           biblio3.setId(biblioid3);
//           marchib.insert(biblio3);
if(hm1.containsKey("23")){
            hm1.remove("23");
        }
 hm1.put("23", biblio);



        //filling object with data for MARC Tag 520
    biblioid4.setLibraryId(library_id);
    biblio4.setSublibraryId(sub_library_id);
    biblioid4.setMarctag("520");
    biblio4.setIndicator1(in5201);
    biblio4.setIndicator2(in5202);
        biblio4.set$a(z520a);
        biblio4.set$b(z520b);
        biblio4.set$c(z520c);
        biblio4.set$u(z520u);
        biblio4.set$2(z5202);
        biblio4.set$3(z5203);

       biblioid4.setBibId(bibid);
           biblio4.setId(biblioid4);
//           marchib.insert(biblio4);
if(hm1.containsKey("24")){
            hm1.remove("24");
        }
 hm1.put("24", biblio4);


         //filling object with data for MARC Tag 546
    biblioid5.setLibraryId(library_id);
    biblio5.setSublibraryId(sub_library_id);
    biblioid5.setMarctag("546");
    biblio5.setIndicator1(in5461);
    biblio5.setIndicator2(in5462);
        biblio5.set$a(z546a);

        biblio5.set$b(z546b);
        biblio5.set$3(z5463);

        biblioid5.setBibId(bibid);
           biblio5.setId(biblioid5);
//           marchib.insert(biblio5);
if(hm1.containsKey("25")){
            hm1.remove("25");
        }
 hm1.put("25", biblio5);

        System.out.println("All six objects saved now NAvigating to page "+t);
        String bib_id=(String)session.getAttribute("biblio_id") ;
 //code for mapping forwards......
         if(t.equals("0"))
        {
              List<Biblio> biblist= marchib.getdataforupdate1(bib_id,"082");
System.out.println("BBBBGGGGGGGGGGGGGGGGGGGGGGGGGGGG"+biblist.size());

            for(int i=0;biblist.size()>i;i++)
            {
                   if(biblist.get(i).getId().getMarctag().equals("020")){

                     request.setAttribute("020", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("022")){

                     request.setAttribute("022", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("041")){
                      // System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("041", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("043")){
                        //System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("043", biblist.get(i));
                    }

                   if(biblist.get(i).getId().getMarctag().equals("082")){
    //                    System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("082", biblist.get(i));
                    }

            }
            return mapping.findForward("forward0");
        }
        else if(t.equals("1"))
        {
             List<Biblio> biblist= marchib.getdataforupdate2(bib_id);
System.out.println("1111111111111111111111   "+biblist.size());

            for(int i=0;biblist.size()>i;i++)
            {
                   if(biblist.get(i).getId().getMarctag().equals("100")){

                     request.setAttribute("100", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("110")){

                     request.setAttribute("110", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("130")){
                      // System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("130", biblist.get(i));
                    }
            }
                return mapping.findForward("forward1");
        }

        else if(t.equals("2"))
        {
            List<Biblio> biblist = marchib.getdataforupdate3(bib_id);
                System.out.println("22222222222222    "+biblist.size());
                for(int i=0;biblist.size()>i;i++)
            {
                   if(biblist.get(i).getId().getMarctag().equals("210")){

                     request.setAttribute("210", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("245")){

                     request.setAttribute("245", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("250")){
                      // System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("250", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("256")){

                     request.setAttribute("256", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("260")){

                     request.setAttribute("260", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("263")){
                      // System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("263", biblist.get(i));
                    }
            }
                return mapping.findForward("forward2");
        }

        else if(t.equals("3"))
        {
                List<Biblio> biblist = marchib.getdataforupdate4(bib_id);
                System.out.println("3333333333333    "+biblist.size());
                for(int i=0;biblist.size()>i;i++)
            {
                   if(biblist.get(i).getId().getMarctag().equals("300")){

                     request.setAttribute("300", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("306")){

                     request.setAttribute("306", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("336")){
                      // System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("336", biblist.get(i));
                    }

            }
                return mapping.findForward("forward3");
        }
        else if(t.equals("4"))
        {
                 List<Biblio> biblist = marchib.getdataforupdate44(bib_id);
                System.out.println("44444444444    "+biblist.size());
                for(int i=0;biblist.size()>i;i++)
            {
                   if(biblist.get(i).getId().getMarctag().equals("490")){

                     request.setAttribute("490", biblist.get(i));
                    }

            }
                return mapping.findForward("forward4");
        }
        
        else if(t.equals("6"))
        {
                 List<Biblio> biblist = marchib.getdataforupdate6(bib_id);
                System.out.println("666666666666    "+biblist.size());
                for(int i=0;biblist.size()>i;i++)
                {
                   if(biblist.get(i).getId().getMarctag().equals("600")){

                     request.setAttribute("600", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("650")){

                     request.setAttribute("650", biblist.get(i));
                    }

                }
             return mapping.findForward("forward6");
        }

        else if(t.equals("7"))
        {
                List<Biblio> biblist = marchib.getdataforupdate7(bib_id);
                        System.out.println("77777777777    "+biblist.size());
                        for(int i=0;biblist.size()>i;i++)
                    {
                           if(biblist.get(i).getId().getMarctag().equals("700")){

                             request.setAttribute("700", biblist.get(i));
                            }
                           if(biblist.get(i).getId().getMarctag().equals("740")){

                             request.setAttribute("740", biblist.get(i));
                            }
                    }
                return mapping.findForward("forward7");
        }
        else if(t.equals("8"))
        {
                List<Biblio> biblist = marchib.getdataforupdate8(bib_id);
                        System.out.println("888888888888    "+biblist.size());
                        for(int i=0;biblist.size()>i;i++)
                         {
                           if(biblist.get(i).getId().getMarctag().equals("800"))
                           {

                             request.setAttribute("800", biblist.get(i));
                            }
                           if(biblist.get(i).getId().getMarctag().equals("830"))
                           {

                             request.setAttribute("830", biblist.get(i));
                            }
                           if(biblist.get(i).getId().getMarctag().equals("850"))
                           {
                              // System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                             request.setAttribute("850", biblist.get(i));
                            }
                           if(biblist.get(i).getId().getMarctag().equals("852"))
                           {

                             request.setAttribute("852", biblist.get(i));
                            }
                           if(biblist.get(i).getId().getMarctag().equals("856"))
                           {

                             request.setAttribute("856", biblist.get(i));
                            }

                       }
                return mapping.findForward("forward8");
        }
        else if(t.equals("9"))
        {
                return mapping.findForward("forward9");
        }


                List<Biblio> biblist = marchib.getdataforupdate5(bib_id);
                System.out.println("555555555555    "+biblist.size());
                for(int i=0;biblist.size()>i;i++)
                 {
                   if(biblist.get(i).getId().getMarctag().equals("500")){

                     request.setAttribute("500", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("502")){

                     request.setAttribute("502", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("504")){
                      // System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("504", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("505")){

                     request.setAttribute("505", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("520")){

                     request.setAttribute("520", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("546")){
                      // System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("546", biblist.get(i));
                    }
                   }
        return mapping.findForward("forward5");
    }
        
     
}
