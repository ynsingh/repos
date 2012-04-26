/*
      Design by Iqubal Ahmad
      Modified on 2011-02-02
      This Action Class is meant for Submitting values From OPAC Member registration page to Cir_requestfrom_opac table.
 */

package com.myapp.struts.opac;
import com.myapp.struts.opacDAO.*;
import com.myapp.struts.hbm.*;
import com.myapp.struts.utility.UserLog;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author edrp01
 */
public class OpacNewMemberAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */

    private static String SUCCESS = "success";
    private CirRequestfromOpac cro = new CirRequestfromOpac();

    private String TXTMEMID;
    private String TXTFNAME;
    private String TXTLNAME;
    private String TXTMNAME;
    private String TXTEMAILID;
    private String TXTPASS;
    private String TXTADD1;
    private String TXTCITY1;
    private String TXTSTATE1;
    private String TXTCOUNTRY1;
    private String TXTPH1;
    private String TXTPH2;
    private String TXTFAX;
    private String TXTADD2;
    private String TXTCITY2;
    private String TXTPIN1;
    private String TXTPIN2;
    private String TXTSTATE2;
    private String TXTCOUNTRY2;
    private String MEMCAT;
    private String MEMSUBCAT;
    private String TXTFACULTY;
    private String TXTDEPT;
    private String TXTCOURSE;
    private String TXTSEM;
    private byte[] imagefile;
    private String button;
    private String TXTDESG1;
    private String TXTOFFICE;
    private String date;
     Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";

    
    /**
      Design by Iqubal Ahmad
      Modified on 2011-02-02
      This Action Class is meant for Submitting values From OPAC Member registration page to Cir_requestfrom_opac table.
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {

         OpacNewMemberActionForm cmdf =(OpacNewMemberActionForm)form;
        HttpSession session1 =request.getSession();
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
         FormFile v=(FormFile)session1.getAttribute("filename");

       byte[] iii=null;
       if(v!=null)iii=v.getFileData();
       
         String CMBLib=cmdf.getCMBLib();
   Calendar cal = new GregorianCalendar();
    int month = cal.get(Calendar.MONTH);
    int year = cal.get(Calendar.YEAR);
    int day = cal.get(Calendar.DAY_OF_MONTH);
    //date=day+"/"+(month+1)+"/"+year;
    date=year+"-"+(month+1)+"-"+day;

      //  OpacImageUploadActionForm form1 = (OpacImageUploadActionForm)session1.getAttribute("OpacImageUploadActionForm");
       


    //Session session=HibernateUtil.getSessionFactory().getCurrentSession();
      //      Transaction tx=null;


              CirRequestfromOpac test=(CirRequestfromOpac)CirRequestfromOpacDAO.getMemberDetail(cmdf.getCMBLib(),cmdf.getCmdSubLibrary(),cmdf.getTXTMEMID());
         
         if(test!=null)
         {
            //  request.setAttribute("msg", "Request with Member Id : "+cmdf.getTXTMEMID()+" already submitted");
             request.setAttribute("msg", resource.getString("circulation.opacnewmem.reqwithmemid")+cmdf.getTXTMEMID()+ resource.getString("circulation.opacnewmem.alredysubmit"));
            return mapping.findForward("failure");
         }

          CirMemberDetail   test1=(CirMemberDetail)CirRequestfromOpacDAO.getMemberId(cmdf.getCMBLib(),cmdf.getTXTMEMID());


         if(test1!=null)
         {
              // request.setAttribute("msg", "Request with Member Id : "+cmdf.getTXTMEMID()+" already submitted");
             request.setAttribute("msg", resource.getString("circulation.opacnewmem.reqwithmemid")+cmdf.getTXTMEMID()+ resource.getString("circulation.opacnewmem.alredysubmit"));
            return mapping.findForward("failure");
         }




           
             
             
            cro.setLibraryId(CMBLib);
            
            cro.setMemId(cmdf.getTXTMEMID());
            cro.setFname(cmdf.getTXTFNAME());
            cro.setMname(cmdf.getTXTMNAME());
            cro.setLname(cmdf.getTXTLNAME());
            cro.setEmail(cmdf.getTXTEMAILID());
            cro.setAddress1(cmdf.getTXTADD1());
            cro.setAddress2(cmdf.getTXTADD2());
            cro.setCity1(cmdf.getTXTCITY1());
            cro.setState1(cmdf.getTXTSTATE1());
            cro.setCountry1(cmdf.getTXTCOUNTRY1());
            cro.setCity2(cmdf.getTXTCITY2());
            cro.setState2(cmdf.getTXTSTATE2());
            cro.setCountry2(cmdf.getTXTCOUNTRY2());

            cro.setMemType(cmdf.getMEMCAT());
            cro.setSubMemberType(cmdf.getMEMSUBCAT());
            cro.setDesg(cmdf.getTXTDESG1());
            cro.setOffice(cmdf.getTXTOFFICE());
            cro.setFacultyId(cmdf.getTXTFACULTY());
            cro.setDeptId(cmdf.getTXTDEPT());
            cro.setCourse(cmdf.getTXTCOURSE());
        
            cro.setRequestdate(cmdf.getTXTREQ_DATE());
            cro.setPhone1(cmdf.getTXTPH1());
            cro.setPhone2(cmdf.getTXTPH2());
            cro.setPin1(cmdf.getTXTPIN1());
            cro.setPin2(cmdf.getTXTPIN2());
            cro.setFax(cmdf.getTXTFAX());
            cro.setStatus("UnApproved");
            cro.setSemester(cmdf.getTXTSEM());
            cro.setRequestdate(date);
            cro.setSublibraryId(cmdf.getCmdSubLibrary());

            System.out.println(cmdf.getImg()+v.getFileName());
            if(v.getFileName()!=null)
            {
                iii=v.getFileData();
                String ext=v.getFileName().substring(v.getFileName().indexOf(".")+1,v.getFileName().length());
                UserLog.writeImage(cmdf.getTXTMEMID()+"."+ext, iii);
                cro.setImage(cmdf.getTXTMEMID()+"."+ext);
            }


          boolean result= CirRequestfromOpacDAO.insert(cro);
          if(result==true){
           //String msg="Request for Member Registration sent successfully to Circulation Division";
            String msg=resource.getString("circulation.opacnewmem.reqsendtocirdiv");
            request.setAttribute("msg",msg);
            session1.removeAttribute("image");
            return mapping.findForward("Submit");
          }
          else
            
            {
                System.out.println("Exception Generated:");
               return mapping.findForward("failure");
              

            }
    }
}
    
     
    

