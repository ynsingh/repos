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
public class UCatAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
        HashMap hm1=new HashMap();
    private MarcHibDAO marchib=new MarcHibDAO();

    MarcHibDAO dao=new MarcHibDAO();

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

        System.out.println("inside UCatAction");
        BiblioActionForm caf=(BiblioActionForm)form;

        HttpSession session = request.getSession();


        int  bibid = Integer.parseInt((String)session.getAttribute("biblio_id"));
        System.out.println("************************************************  "+bibid);
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");


        String t=caf.getZclick();                       // t is the click value on jsp


        Character in0201,in0221,in0411,in0431,in0821,in0202,in0222,in0412,in0432,in0822;
      String z020,z020c,z020z,z022,z022y,z022z,z041,z041b,z041d,z043,z082,z082b,z0822;

      // getting values of isbn and indicator fields from biblioactionform

      in0201 = caf.getIn0201();
      in0202 = caf.getIn0202();
      in0221= caf.getIn0221();
      in0222 = caf.getIn0222();
      in0411 = caf.getIn0411();
      in0412 = caf.getIn0412();
      in0431 = caf.getIn0431();
      in0432 = caf.getIn0432();
      in0821 = caf.getIn0821();
      in0822 = caf.getIn0822();

      // getting values of subfields from biblioactionform
      z020 = caf.getZ020();
      z020c = caf.getZ020c();
      z020z = caf.getZ020z();
      z022 = caf.getZ022();
      z022y = caf.getZ022y();
      z022z = caf.getZ022z();
      z041  = caf.getZ041();
      z041b = caf.getZ041b();
      z041d = caf.getZ041d();
      z043 = caf.getZ043();
      z082 = caf.getZ082();
      z082b = caf.getZ082b();
      z0822 = caf.getZ0822();

//filling object with data for MARC Tag 020
    biblioid.setLibraryId(library_id);
    biblio.setSublibraryId(sub_library_id);
    biblioid.setMarctag("020");
    biblio.setIndicator1(in0201);
    biblio.setIndicator2(in0202);
    biblio.set$a(z020);
    biblio.set$c(z020c);
    biblio.set$z(z020z);


          biblioid.setBibId(bibid);
           biblio.setId(biblioid);
//           marchib.insert(biblio);
 hm1 = (HashMap)session.getAttribute("hsmp");
          //insert
        if(hm1.containsKey("1")){
            hm1.remove("1");
        }
    hm1.put("1", biblio);
          //insert


    System.out.println("1st object saved");

//filling object with data for Marc tag 022
    biblioid1.setLibraryId(library_id);
    biblio1.setSublibraryId(sub_library_id);
    biblioid1.setMarctag("022");
    biblio1.setId(biblioid1);
    biblio1.setIndicator1(in0221);
    biblio1.setIndicator2(in0222);
    biblio1.set$a(z022);
    biblio1.set$y(z022y);
    biblio1.set$z(z022z);

          biblioid1.setBibId(bibid);
           biblio1.setId(biblioid1);
//           marchib.insert(biblio1);
  if(hm1.containsKey("2")){
            hm1.remove("2");
        }
hm1.put("2", biblio1);
          //insert

    System.out.println("2nd object saved");

//filling object with data for Marc tag 041

    biblioid2.setLibraryId(library_id);
    biblio2.setSublibraryId(sub_library_id);
    biblioid2.setMarctag("041");
    biblio2.setId(biblioid2);
    biblio2.setIndicator1(in0411);
    biblio2.setIndicator2(in0412);
    biblio2.set$a(z041);
    biblio2.set$y(z041b);
    biblio2.set$z(z041d);

         biblioid2.setBibId(bibid);
           biblio2.setId(biblioid2);
//           marchib.insert(biblio2);
 if(hm1.containsKey("3")){
            hm1.remove("3");
        }
hm1.put("3", biblio2);
          //insert

    System.out.println("3rd object saved");

////filling object with data for Marc tag 043
    biblioid3.setLibraryId(library_id);
    biblio3.setSublibraryId(sub_library_id);
    biblioid3.setMarctag("043");
    biblio3.setId(biblioid3);
    biblio3.setIndicator1(in0431);
    biblio3.setIndicator2(in0432);
    biblio3.set$a(z043);

    biblioid3.setBibId(bibid);
           biblio3.setId(biblioid3);
//           marchib.insert(biblio3);
if(hm1.containsKey("4")){
            hm1.remove("4");
        }
hm1.put("4", biblio3);
          //insert
          //insert

    System.out.println("4th object saved");

//filling object with data for Marc tag 082
   biblioid4.setLibraryId(library_id);
    biblio4.setSublibraryId(sub_library_id);
    biblioid4.setMarctag("082");
    biblio4.setId(biblioid1);
    biblio4.setIndicator1(in0821);
    biblio4.setIndicator2(in0822);
    biblio4.set$a(z082);
    biblio4.set$y(z082b);
    biblio4.set$z(z0822);

         biblioid4.setBibId(bibid);
           biblio4.setId(biblioid4);
//           marchib.insert(biblio4);
 if(hm1.containsKey("5")){
            hm1.remove("5");
        }
hm1.put("5", biblio4);
          //insert

    System.out.println("5th object saved");

String bib_id=(String)session.getAttribute("biblio_id") ;
      System.out.println("t value is = "+t+"  and bib_id is = "+bib_id);
        //code for mapping forwards......
         if(t.equals("1"))
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

        else if(t.equals("2")){
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
      
        else if(t.equals("3")){

             List<Biblio> biblist = marchib.getdataforupdate4(bib_id);
                System.out.println("22222222222222    "+biblist.size());
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
        else if(t.equals("5"))
        {
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
        else if(t.equals("6"))
        {
                 List<Biblio> biblist = marchib.getdataforupdate6(bib_id);
                System.out.println("666666666    "+biblist.size());
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
        
        
}
