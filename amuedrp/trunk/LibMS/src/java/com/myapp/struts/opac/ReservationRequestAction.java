/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;

import com.myapp.struts.hbm.*;
import com.myapp.struts.CirculationDAO.CirculationDAO;

import com.myapp.struts.opacDAO.*;
import java.util.*;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import org.apache.catalina.Session;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Faraz
 */
public class ReservationRequestAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    int reservation_id;
    boolean result;
    List control_list;
    int max_id;
    Reservationlist rl=new Reservationlist();
    ReservationlistId rlid=new ReservationlistId();
   
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
            HttpSession session = request.getSession();
            ResultSet rs;
            ReservationRequetActionForm myForm = (ReservationRequetActionForm)form;
            
            String library_id,memid,accessionno,card_id,title,author,publ,edition;
            String req_date,callno,isbn,vol,remark,category,issn,lang,pub_year,sublibrary_id;
            String memsublib=(String)session.getAttribute("memsublib");
            library_id=myForm.getCMBLib();
            sublibrary_id=myForm.getcmdSubLibary();
            memid=(String)session.getAttribute("id");
            accessionno=myForm.getAccessionno();


            Reservationlist resobj=(Reservationlist)ReservationListDAO.getRequestDetail(library_id,sublibrary_id,memid,accessionno,"pending");

            if(resobj!=null){
             request.setAttribute("msg", "Reservation request Already Given");
            return mapping.findForward("success");

            }
            
            Calendar cal = new GregorianCalendar();
            int month = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            req_date= year+"-"+(month+1)+"-"+day;

            
            card_id=(String)session.getAttribute("card_id");
            title=myForm.getTXTTITLE();
            category=myForm.getCMBCAT();
            author=myForm.getTXTAUTHOR();
            isbn=myForm.getTXTISBN();
            callno=myForm.getTXTCALLNO();
            edition=myForm.getTXTEDITION();
            vol=myForm.getTXTVOL();
            publ=myForm.getTXTPUBL();
            remark=myForm.getTXTREMARKS();
            issn=myForm.getIssn();
            lang=myForm.getLang();
            pub_year=myForm.getPub_year();


            control_list=NewDemandDAO.getMaxReservationId(library_id,sublibrary_id,memid);
               if(control_list.get(0)!=null)
               {
                max_id=Integer.parseInt((String)control_list.get(0)) ;
                System.out.println("maxId="+max_id);
                max_id++;
               }
               else
               {
                max_id=1;
               }
            System.out.println("maxId="+max_id);
            reservation_id=max_id;

            rlid.setLibraryId(library_id);
            rlid.setRequestId(String.valueOf(reservation_id));
            rlid.setSublibraryId(sublibrary_id);
            rl.setId(rlid);
            rl.getId().setMemid(memid);
            rl.setAccessionno(accessionno);
            rl.setCardId(card_id);
            rl.setTitle(title);
            rl.setCategory(category);
            rl.setAuthor(author);
            rl.setIsbn(isbn);
            rl.setCallno(callno);
            rl.setEdition(edition);
            rl.setVolume(vol);
            rl.setPublication(publ);
            rl.setRemark(remark);
            rl.setRequestDate(req_date);
            rl.setIssn(issn);
            rl.setLanguage(lang);
            rl.setPubYear(pub_year);
            rl.setStatus("pending");
            result=NewDemandDAO.insert1(rl);
             // System.out.println(String.valueOf(CirculationDAO.getMaxReservationId(library_id, memid)));
        if(result==true)
        {
         CirMemberAccount cirobj=(CirMemberAccount)CirculationDAO.searchCirMemAccountDetails(library_id,memsublib, memid);
            if(cirobj!=null)
            {
                Integer researvation_made = Integer.parseInt(cirobj.getReservationMade());
                researvation_made++;
              System.out.println();

            cirobj.setReservationMade(researvation_made.toString());
            result=CirculationDAO.updateAccount(cirobj);

           // if(result==true)
          //  {
               request.setAttribute("msg", "Record Inserted Successfully");
                return mapping.findForward("success");

           // }
           //  else
           //  {
            //request.setAttribute("msg", "Record Not Inserted");
           // return mapping.findForward("error");
           //  }


         

             }
        }
        else
        {
            request.setAttribute("msg", "Record Not Inserted");
            return mapping.findForward("error");
        }


           request.setAttribute("msg", "Record Not Inserted");
            return mapping.findForward("error");
        }

    
}
