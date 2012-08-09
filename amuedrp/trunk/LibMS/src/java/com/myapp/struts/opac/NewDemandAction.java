/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;
import  com.myapp.struts.opacDAO.NewDemandDAO;
import com.myapp.struts.hbm.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class NewDemandAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    NewDemandDAO newdmnddao= new NewDemandDAO();
    String library_id,memid,title,author,publ,publ_yr,edition;
    String req_date,isbn,copy,vol,remark,category,issn,lang,mem_name;
    String date;
    String sublibrary_id;
    int demand_id;
    String mem_type;
    String sub_member_type,sub_author,publication_place,lcc_no;
    Demandlist dl=new Demandlist();
    DemandlistId dlid=new DemandlistId();
    boolean result;

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {


   HttpSession session=request.getSession();

   NewDemandActionForm myform = (NewDemandActionForm)form;

   Calendar cal = new GregorianCalendar();
    int month = cal.get(Calendar.MONTH);
    int year = cal.get(Calendar.YEAR);
    int day = cal.get(Calendar.DAY_OF_MONTH);
    //date=day+"/"+(month+1)+"/"+year;
    date=year+"-"+(month+1)+"-"+day;


        library_id=myform.getCMBLib();
        sublibrary_id=myform.getCmdSubLibary();

        memid=(String)session.getAttribute("mem_id");
     mem_name =(String)session.getAttribute("mem_name");
        title=myform.getTXTTITLE();


        Demandlist demandobj=(Demandlist)NewDemandDAO.getDemandList(library_id,sublibrary_id,memid,title,"pending");
        if(demandobj!=null){

            request.setAttribute("msg", "Demand List For Given Title Already Send and Is Under Processing");
            return mapping.findForward("error");



        }



mem_type=myform.getMem_type();
sub_member_type=myform.getSub_member_type();

category=myform.getCMBCAT();
author=myform.getTXTAUTHOR();
publ=myform.getTXTPUB();
publ_yr=myform.getTXTPUBYR();
isbn=myform.getTXTISBN();
//copy=myform.getTXTCOPY();
vol=myform.getTXTVOL();
edition=myform.getTXTEDITION();


remark=myform.getTXTREMARK();
req_date=date;
  issn=myform.getIssn();
  sub_author=myform.getSub_author();
  publication_place=myform.getPublication_place();
  lcc_no=myform.getLcc_no();
  lang=myform.getLang();
  int demandId=newdmnddao.returnMaxDemandId(library_id, sublibrary_id);
  dlid.setLibraryId(library_id);
  dlid.setSublibraryId(sublibrary_id);
  dlid.setMemId(memid);
  dlid.setTitle(title);
  dl.setId(dlid);
  System.out.println(dlid.getLibraryId()+"-"+dlid.getMemId()+"-"+dlid.getSublibraryId()+"-"+dlid.getTitle());

  dl.setAuthor(author);
  dl.setCategory(category);
  dl.setDemandDate(req_date);
  dl.setIsbn(isbn);
  dl.setIssn(issn);
  dl.setLanguage(lang);
  //dl.setNoOfCopy(copy);
  dl.setPublishYr(publ_yr);
  dl.setPublisher(publ);
  dl.setRemark(remark);
  dl.setRemark(remark);
  dl.setEdition(edition);
  dl.setMemName(mem_name);
  dl.setSubAuthor(sub_author);
  dl.setPublicationPlace(publication_place);
  dl.setLccNo(lcc_no);
  dl.setMemberType(mem_type);
  dl.setSubMemberType(sub_member_type);
  dl.getId().setDemandId(demandId);
  dl.setStatus("pending");


  result=newdmnddao.insert(dl);
  System.out.println(result);
        if(result==true)
        {


            request.setAttribute("msg", "Record Inserted Successfully");
            return mapping.findForward("success");

        }
        else
        {
            request.setAttribute("msg", "Record Not Inserted");
            return mapping.findForward("error");
        }



    }
}
