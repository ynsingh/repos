/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Candidate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author akhtar
 */
public class CandidateImageUploadAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
          HttpSession session = request.getSession();

             String page = (String)session.getAttribute("page");
        String page1 = (String)session.getAttribute("page1");
       CandidateRegActionForm form1 = (CandidateRegActionForm)form;
        String enrollment=form1.getEnrollment();

        String instituteid=form1.getInstitute_id();
        System.out.println("inst"+instituteid);
        String dep=form1.getDepartment();
        String cour=form1.getCourse();
        String dur=form1.getDuration();
        String sess=form1.getSession();
        String vname=form1.getV_name();
        String gen=form1.getGender();
        String bdate=form1.getB_date();
        String f_name=form1.getF_name();
        String m_name=form1.getM_name();
        String jdate =form1.getJ_date();
        String year=form1.getYear();
        String mnumb=form1.getM_number();
        String email=form1.getEmail();
        String city=form1.getCity();
        String city1=form1.getCity1();
        String state=form1.getState();
        String state1=form1.getState1();
        String zcode=form1.getZipcode();
        String zcode1=form1.getZipcode1();
        String country=form1.getCountry();
        String country1=form1.getCountry1();
        String cadd=form1.getC_add();
        String padd=form1.getP_add();
        String button=form1.getButton();
        String election=form1.getElections();
        String position=form1.getPosition();

  FormFile filename = form1.getImg();
     byte[]   img = form1.getImg().getFileData();
        if (img!=null)
        System.out.println("ImageUploadAction:"+img.length);
        session.setAttribute("image", img);
        session.setAttribute("filename", filename);
        request.setAttribute("imagechange", 1);

        request.setAttribute("enrollment", enrollment);
        request.setAttribute("instituteid", instituteid);
        request.setAttribute("dep", dep);
        request.setAttribute("cour", cour);
        request.setAttribute("dur", dur);
        request.setAttribute("sess", sess);
        request.setAttribute("jdate", jdate);
        request.setAttribute("vname", vname);
        request.setAttribute("gen", gen);
        request.setAttribute("country",country);
        request.setAttribute("country1",country1);
        request.setAttribute("bdate", bdate);
        request.setAttribute("f_name", f_name);
        request.setAttribute("m_nmae", m_name);
        request.setAttribute("year", year);
        request.setAttribute("elections", election);
        request.setAttribute("position", position);

        request.setAttribute("mnumb",mnumb);
        request.setAttribute("email", email);
        request.setAttribute("cadd", cadd);
        request.setAttribute("padd",padd);
        request.setAttribute("city",city);
        request.setAttribute("city1", city);
        request.setAttribute("state", state);
        request.setAttribute("state1", state1);
        request.setAttribute("country", country);
        request.setAttribute("country", country);
        request.setAttribute("button", button);
        request.setAttribute("Status", "U");
 if (page==null){
        return mapping.findForward(SUCCESS);
        }else{
            if (page1!=null && page1.equalsIgnoreCase("Submit")){
                return mapping.findForward(SUCCESS);
        }
            return mapping.findForward("failure");
        }
    }
}

