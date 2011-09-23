/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import  com.myapp.struts.hbm.Privilege;
import  com.myapp.struts.hbm.PrivilegeId;
import  com.myapp.struts.hbm.SerPrivilege;
import  com.myapp.struts.hbm.CirPrivilegeId;
import  com.myapp.struts.hbm.CirPrivilege;
import  com.myapp.struts.hbm.CatPrivilege;
import  com.myapp.struts.hbm.CatPrivilegeId;
import  com.myapp.struts.hbm.AcqPrivilege;
import  com.myapp.struts.hbm.AcqPrivilegeId;
import  com.myapp.struts.hbm.Login;
import  com.myapp.struts.hbm.LoginId;
import  com.myapp.struts.hbm.StaffDetail;
import  com.myapp.struts.hbm.StaffDetailId;
import  com.myapp.struts.hbm.SubLibrary;
import  com.myapp.struts.hbm.SubLibraryId;
import  com.myapp.struts.hbm.Library;
import  com.myapp.struts.hbm.AdminRegistration;
import  com.myapp.struts.hbm.SerPrivilegeId;

import  com.myapp.struts.AdminDAO.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpSession;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.myapp.struts.utility.*;
import com.myapp.struts.utility.Email;

/**
 * Developed By : Kedar Kumar
 * Modified By  : 19-Feb-2011
 * Use to Approved the Institute Request from SuperAdmin
 */
public class AdminViewAction1 extends org.apache.struts.action.Action {
    
      private final ExecutorService executor=Executors.newFixedThreadPool(1);
  Email obj;
    boolean result;
   
    private  String user_name;
    private  String password;
    private String staff_id;

    private String sublibrary_id;
    private String sublibrary_name;
    
    
    private int registration_request_id;
    private  String institute_name;
  
    
    private String login_id;
    
    private String admin_password;
    private  String library_id;
    private  String library_name;
  
    private String button;

    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    String align="left";


    
    
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    throws Exception
    {
         HttpSession session=request.getSession();
         try{
              locale1=(String)session.getAttribute("locale");

              if(session.getAttribute("locale")!=null)
              {
                locale1 = (String)session.getAttribute("locale");
       // System.out.println("locale="+locale1);
              }
              else locale1="en";
            }catch(Exception e){locale1="en";}
             locale = new Locale(locale1);
             if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;align="left";}
             else{ rtl="RTL";page=false;align="right";}
              ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);



        AdminViewActionForm admin=(AdminViewActionForm)form;


        registration_request_id=admin.getRegistration_request_id();
        institute_name=admin.getInstitute_name();
        
        login_id=admin.getLogin_id();
        admin_password=admin.getAdmin_password();
        library_id=admin.getLibrary_id();
        library_name=admin.getLibrary_name();
        button=admin.getButton();
        staff_id="admin."+library_id;
       
        
        user_name =admin.getAdmin_fname()+" "+admin.getAdmin_lname();
        
        System.out.println(admin_password);
        

        sublibrary_id=library_id;
        sublibrary_name="Main Library";
        
    
     if(button.equalsIgnoreCase("accept"))  {

/*Use to Check whether the Entered Library Exist or not */
        Library tempobj=(Library)LibraryDAO.searchLibraryID(library_id);
        

            if(tempobj!=null)
            {
               if(locale.equals("en")||locale.equals("ar")||locale.equals("ur"))
                 request.setAttribute("msg",resource.getString("admin.acceptmesg.msg")+library_id);
               else
                 request.setAttribute("msg",library_id+" "+resource.getString("admin.acceptmesg.msg"));
             return mapping.findForward("failure");
           }


/* Use to Insert New Libray Entry in Library Table */
            Library libobj=new Library();
            libobj.setLibraryId(library_id);
            libobj.setLibraryName(library_name);
            libobj.setWorkingStatus("OK");
            libobj.setStaffId(staff_id);
            libobj.setRegistrationId(registration_request_id);



             result=LibraryDAO.insert1(libobj);
                if(result==false)
                {
                    String msg=resource.getString("admin.acceptmesg.msg2");
                    request.setAttribute("msg", msg);
                    return mapping.findForward("failure");

                }

    /* Use to Insert New SubLibray Entry related to Library Table */
            SubLibraryId sublibid=new SubLibraryId(library_id, sublibrary_id);
            SubLibrary subobj=new SubLibrary(sublibid, libobj, sublibrary_name);
            subobj.setId(sublibid);
           

           
        

              
              
                result=SubLibraryDAO.insert(subobj);
                if(result==false)
                {
                    String msg=resource.getString("admin.acceptmesg.msg2");
                    request.setAttribute("msg", msg);
                    return mapping.findForward("failure");

                }





         
  /* Use to Insert New Staff Entry related to Library Table & SubLibrary Table */
            StaffDetailId staffidobj=new StaffDetailId(staff_id,library_id);
            StaffDetail staffobj=new StaffDetail(staffidobj, libobj, sublibrary_id) ;
           
           
            staffobj.setTitle(admin.getCourtesy());
            
            staffobj.setFirstName(admin.getAdmin_fname());
            staffobj.setLastName(admin.getAdmin_lname());
            staffobj.setEmailId(admin.getAdmin_email());
            staffobj.setDateJoining(null);
            staffobj.setDateReleaving(null);
            staffobj.setDateOfBirth(null);
            staffobj.setGender(admin.getGender());
            staffobj.setMobileNo(admin.getMobile_no());
            staffobj.setContactNo(admin.getLand_line_no());
            staffobj.setCity1(admin.getCity());
            staffobj.setState1(admin.getState());
            staffobj.setZip1(admin.getPin());
            staffobj.setCountry1(admin.getCountry());


             result=StaffDetailDAO.insert1(staffobj);
                if(result==false)
                {
                    String msg=resource.getString("admin.acceptmesg.msg2");
                    request.setAttribute("msg", msg);
                    return mapping.findForward("failure");

                }

              /*Password Generate and Reset It*/
                 password= RandomPassword.getRandomString(10);
                 System.out.println(password);
        String password1 = PasswordEncruptionUtility.password_encrupt(password);



              
      /* Use to Insert New Login Entry related to Library Table & SubLibrary Table and Staff Table */
                LoginId loginIdobj=new LoginId(staff_id, library_id);
                Login logobj=new Login(loginIdobj,staffobj,login_id) ;
                logobj.setPassword(password1);
                logobj.setRole("insti-admin");
                logobj.setSublibraryId(sublibrary_id);
                logobj.setUserName(user_name);
                logobj.setQuestion("@");
                
                System.out.println(staff_id+library_id+admin_password+sublibrary_id+user_name+login_id);
                

                 result=LoginDAO.insert1(logobj);
                if(result==false)
                {
                    String msg=resource.getString("admin.acceptmesg.msg2");
                    request.setAttribute("msg", msg);
                    return mapping.findForward("failure");

                }

 /* Use to Insert New Entry in Privilege related to Staff Detail Table */
           PrivilegeId privid=new PrivilegeId(staff_id, library_id);
           Privilege priv=new Privilege(privid, staffobj);
           priv.setSublibraryId(sublibrary_id);
           priv.setAcquisition("false");
           priv.setAdministrator("false");
           priv.setCataloguing("false");
           priv.setOpac("false");
           priv.setSerial("false");
           priv.setSystemSetup("false");
           priv.setUtilities("false");
           priv.setCirculation("false");

           result=PrivilegeDAO.insert(priv);

            if(result==false)
                {
                    String msg=resource.getString("admin.acceptmesg.msg2");
                    request.setAttribute("msg", msg);
                    return mapping.findForward("failure");

                }
/* Use to Insert New Entry in AcqPrivilege related to Staff Detail Table */
                AcqPrivilegeId acqid=new AcqPrivilegeId(staff_id, library_id);
                AcqPrivilege acq=new AcqPrivilege(acqid, staffobj, sublibrary_id);

                acq.setAcq101("false");
                acq.setAcq102("false");
                acq.setAcq103("false");
                acq.setAcq104("false");
                acq.setAcq105("false");
                acq.setAcq106("false");
                acq.setAcq107("false");
                acq.setAcq108("false");
                acq.setAcq109("false");
                acq.setAcq110("false");
                acq.setAcq111("false");
                acq.setAcq112("false");
                acq.setAcq113("false");
                acq.setAcq114("false");
                acq.setAcq115("false");
                acq.setAcq116("false");
                acq.setAcq117("false");
                acq.setAcq118("false");
                acq.setAcq119("false");
                acq.setAcq120("false");
                acq.setAcq121("false");
                acq.setAcq122("false");
                acq.setAcq123("false");
                acq.setAcq124("false");
                acq.setAcq125("false");
                acq.setAcq126("false");
                acq.setAcq127("false");
                acq.setAcq128("false");
                acq.setAcq129("false");
                acq.setAcq130("false");
                acq.setAcq131("false");
                acq.setAcq132("false");
                acq.setAcq133("false");
                acq.setAcq134("false");
                acq.setAcq135("false");
                acq.setAcq136("false");
                acq.setAcq137("false");
                acq.setAcq138("false");
                acq.setAcq139("false");
                acq.setAcq140("false");
                acq.setAcq141("false");
                acq.setAcq142("false");
                acq.setAcq143("false");
                acq.setAcq144("false");
                acq.setAcq145("false");
                acq.setAcq146("false");
                acq.setAcq147("false");
                acq.setAcq148("false");
                acq.setAcq149("false");
                acq.setAcq150("false");
                acq.setAcq151("false");
                acq.setAcq152("false");
                acq.setAcq153("false");
                acq.setAcq154("false");
                acq.setAcq155("false");
                acq.setAcq156("false");
                acq.setAcq157("false");
                acq.setAcq158("false");
                acq.setAcq159("false");
                acq.setAcq160("false");
                acq.setAcq161("false");
                acq.setAcq162("false");
                acq.setAcq163("false");
                acq.setAcq164("false");
                acq.setAcq165("false");
                acq.setAcq166("false");
                acq.setAcq167("false");
                acq.setAcq168("false");
                acq.setAcq169("false");
                acq.setAcq170("false");
                acq.setAcq171("false");
                acq.setAcq172("false");
                acq.setAcq173("false");
                acq.setAcq174("false");
                acq.setAcq175("false");
                acq.setAcq176("false");
                acq.setAcq177("false");
                acq.setAcq178("false");
                acq.setAcq179("false");
                acq.setAcq180("false");
                acq.setAcq181("false");
                acq.setAcq182("false");
                acq.setAcq183("false");
                acq.setAcq184("false");
                acq.setAcq185("false");
                acq.setAcq186("false");
                acq.setAcq187("false");
                acq.setAcq188("false");
                acq.setAcq189("false");
                acq.setAcq190("false");
                acq.setAcq191("false");
                acq.setAcq192("false");
                acq.setAcq193("false");
                acq.setAcq194("false");
                acq.setAcq195("false");
                acq.setAcq196("false");
                acq.setAcq197("false");
                acq.setAcq198("false");
                acq.setAcq199("false");

                result=AcqPrivilegeDAO.insert(acq);

            if(result==false)
                {
                    String msg=resource.getString("admin.acceptmesg.msg2");
                    request.setAttribute("msg", msg);
                    return mapping.findForward("failure");

                }
/* Use to Insert New Entry in CatPrivilege related to Staff Detail Table */

             CatPrivilegeId catpriId=new CatPrivilegeId(staff_id, library_id);
             CatPrivilege Cat=new CatPrivilege(catpriId, staffobj, sublibrary_id);

            Cat.setCat201("false");
            Cat.setCat202("false");
            Cat.setCat203("false");
            Cat.setCat204("false");
            Cat.setCat205("false");
            Cat.setCat206("false");
            Cat.setCat207("false");
            Cat.setCat208("false");
            Cat.setCat209("false");
            Cat.setCat210("false");
            Cat.setCat211("false");
            Cat.setCat212("false");
            Cat.setCat213("false");
            Cat.setCat214("false");
            Cat.setCat215("false");
            Cat.setCat216("false");
            Cat.setCat217("false");
            Cat.setCat218("false");
            Cat.setCat219("false");
            Cat.setCat220("false");
            Cat.setCat221("false");
            Cat.setCat222("false");
            Cat.setCat223("false");
            Cat.setCat224("false");
            Cat.setCat225("false");
            Cat.setCat226("false");
            Cat.setCat227("false");
            Cat.setCat228("false");
            Cat.setCat229("false");
            Cat.setCat230("false");
            Cat.setCat231("false");
            Cat.setCat232("false");
            Cat.setCat233("false");
            Cat.setCat234("false");
            Cat.setCat235("false");
            Cat.setCat236("false");
            Cat.setCat237("false");
            Cat.setCat238("false");
            Cat.setCat239("false");
            Cat.setCat240("false");
            Cat.setCat241("false");
            Cat.setCat242("false");
            Cat.setCat243("false");
            Cat.setCat244("false");
            Cat.setCat245("false");
            Cat.setCat246("false");
            Cat.setCat247("false");
            Cat.setCat248("false");
            Cat.setCat249("false");
            Cat.setCat250("false");
            Cat.setCat251("false");
            Cat.setCat252("false");
            Cat.setCat253("false");
            Cat.setCat254("false");
            Cat.setCat255("false");
            Cat.setCat256("false");
            Cat.setCat257("false");
            Cat.setCat258("false");
            Cat.setCat259("false");
            Cat.setCat260("false");
            Cat.setCat261("false");
            Cat.setCat262("false");
            Cat.setCat263("false");
            Cat.setCat264("false");
            Cat.setCat265("false");
            Cat.setCat266("false");
            Cat.setCat267("false");
            Cat.setCat268("false");
            Cat.setCat269("false");
            Cat.setCat270("false");
            Cat.setCat271("false");
            Cat.setCat272("false");
            Cat.setCat273("false");
            Cat.setCat274("false");
            Cat.setCat275("false");
            Cat.setCat276("false");
            Cat.setCat277("false");
            Cat.setCat278("false");
            Cat.setCat279("false");
            Cat.setCat280("false");
            Cat.setCat281("false");
            Cat.setCat282("false");
            Cat.setCat283("false");
            Cat.setCat284("false");
            Cat.setCat285("false");
            Cat.setCat286("false");
            Cat.setCat287("false");
            Cat.setCat288("false");
            Cat.setCat289("false");
            Cat.setCat290("false");
            Cat.setCat291("false");
            Cat.setCat292("false");
            Cat.setCat293("false");
            Cat.setCat294("false");
            Cat.setCat295("false");
            Cat.setCat296("false");
            Cat.setCat297("false");
            Cat.setCat298("false");
            Cat.setCat299("false");


            result=CatPrivilegeDAO.insert(Cat);

            if(result==false)
                {
                    String msg=resource.getString("admin.acceptmesg.msg2");
                    request.setAttribute("msg", msg);
                    return mapping.findForward("failure");

                }


/* Use to Insert New Entry in SerPrivilege related to Staff Detail Table */

        SerPrivilegeId serid=new SerPrivilegeId(staff_id, library_id);
        SerPrivilege Ser=new SerPrivilege(serid, staffobj, sublibrary_id);
        Ser.setSer401("false");
        Ser.setSer402("false");
        Ser.setSer403("false");
        Ser.setSer404("false");
        Ser.setSer405("false");
        Ser.setSer406("false");
        Ser.setSer407("false");
        Ser.setSer408("false");
        Ser.setSer409("false");
        Ser.setSer410("false");
        Ser.setSer411("false");
        Ser.setSer412("false");
        Ser.setSer413("false");
        Ser.setSer414("false");
        Ser.setSer415("false");
        Ser.setSer416("false");
        Ser.setSer417("false");
        Ser.setSer418("false");
        Ser.setSer419("false");
        Ser.setSer420("false");
        Ser.setSer421("false");
        Ser.setSer422("false");
        Ser.setSer423("false");
        Ser.setSer424("false");
        Ser.setSer425("false");
        Ser.setSer426("false");
        Ser.setSer427("false");
        Ser.setSer428("false");
        Ser.setSer429("false");
        Ser.setSer430("false");
        Ser.setSer431("false");
        Ser.setSer432("false");
        Ser.setSer433("false");
        Ser.setSer434("false");
        Ser.setSer435("false");
        Ser.setSer436("false");
        Ser.setSer437("false");
        Ser.setSer438("false");
        Ser.setSer439("false");
        Ser.setSer440("false");
        Ser.setSer441("false");
        Ser.setSer442("false");
        Ser.setSer443("false");
        Ser.setSer444("false");
        Ser.setSer445("false");
        Ser.setSer446("false");
        Ser.setSer447("false");
        Ser.setSer448("false");
        Ser.setSer449("false");
        Ser.setSer450("false");
        Ser.setSer451("false");
        Ser.setSer452("false");
        Ser.setSer453("false");
        Ser.setSer454("false");
        Ser.setSer455("false");
        Ser.setSer456("false");
        Ser.setSer457("false");
        Ser.setSer458("false");
        Ser.setSer459("false");
        Ser.setSer460("false");
        Ser.setSer461("false");
        Ser.setSer462("false");
        Ser.setSer463("false");
        Ser.setSer464("false");
        Ser.setSer465("false");
        Ser.setSer466("false");
        Ser.setSer467("false");
        Ser.setSer468("false");
        Ser.setSer469("false");
        Ser.setSer470("false");
        Ser.setSer471("false");
        Ser.setSer472("false");
        Ser.setSer473("false");
        Ser.setSer474("false");
        Ser.setSer475("false");
        Ser.setSer476("false");
        Ser.setSer477("false");
        Ser.setSer478("false");
        Ser.setSer479("false");
        Ser.setSer480("false");
        Ser.setSer481("false");
        Ser.setSer482("false");
        Ser.setSer483("false");
        Ser.setSer484("false");
        Ser.setSer485("false");
        Ser.setSer486("false");
        Ser.setSer487("false");
        Ser.setSer488("false");
        Ser.setSer489("false");
        Ser.setSer490("false");
        Ser.setSer491("false");
        Ser.setSer492("false");
        Ser.setSer493("false");
        Ser.setSer494("false");
        Ser.setSer495("false");
        Ser.setSer496("false");
        Ser.setSer497("false");
        Ser.setSer498("false");
        Ser.setSer499("false");


            result=SerPrivilegeDAO.insert(Ser);

            if(result==false)
                {
                    String msg=resource.getString("admin.acceptmesg.msg2");
                    request.setAttribute("msg", msg);
                    return mapping.findForward("failure");

                }
/* Use to Insert New Entry in CirPrivilege related to Staff Detail Table */

        CirPrivilegeId cirprivid=new CirPrivilegeId(staff_id, library_id);
        CirPrivilege Cir=new CirPrivilege(cirprivid, staffobj, sublibrary_id);
        Cir.setCir301("false");
        Cir.setCir302("false");
        Cir.setCir303("false");
        Cir.setCir304("false");
        Cir.setCir305("false");
        Cir.setCir306("false");
        Cir.setCir307("false");
        Cir.setCir308("false");
        Cir.setCir309("false");
        Cir.setCir310("false");
        Cir.setCir311("false");
        Cir.setCir312("false");
        Cir.setCir313("false");
        Cir.setCir314("false");
        Cir.setCir315("false");
        Cir.setCir316("false");
        Cir.setCir317("false");
        Cir.setCir318("false");
        Cir.setCir319("false");
        Cir.setCir320("false");
        Cir.setCir321("false");
        Cir.setCir322("false");
        Cir.setCir323("false");
        Cir.setCir324("false");
        Cir.setCir325("false");
        Cir.setCir326("false");
        Cir.setCir327("false");
        Cir.setCir328("false");
        Cir.setCir329("false");
        Cir.setCir330("false");
        Cir.setCir331("false");
        Cir.setCir332("false");
        Cir.setCir333("false");
        Cir.setCir334("false");
        Cir.setCir335("false");
        Cir.setCir336("false");
        Cir.setCir337("false");
        Cir.setCir338("false");
        Cir.setCir339("false");
        Cir.setCir340("false");
        Cir.setCir341("false");
        Cir.setCir342("false");
        Cir.setCir343("false");
        Cir.setCir344("false");
        Cir.setCir345("false");
        Cir.setCir346("false");
        Cir.setCir347("false");
        Cir.setCir348("false");
        Cir.setCir349("false");
        Cir.setCir350("false");
        Cir.setCir351("false");
        Cir.setCir352("false");
        Cir.setCir353("false");
        Cir.setCir354("false");
        Cir.setCir355("false");
        Cir.setCir356("false");
        Cir.setCir357("false");
        Cir.setCir358("false");
        Cir.setCir359("false");
        Cir.setCir360("false");
        Cir.setCir361("false");
        Cir.setCir362("false");
        Cir.setCir363("false");
        Cir.setCir364("false");
        Cir.setCir365("false");
        Cir.setCir366("false");
        Cir.setCir367("false");
        Cir.setCir368("false");
        Cir.setCir369("false");
        Cir.setCir370("false");
        Cir.setCir371("false");
        Cir.setCir372("false");
        Cir.setCir373("false");
        Cir.setCir374("false");
        Cir.setCir375("false");
        Cir.setCir376("false");
        Cir.setCir377("false");
        Cir.setCir378("false");
        Cir.setCir379("false");
        Cir.setCir380("false");
        Cir.setCir381("false");
        Cir.setCir382("false");
        Cir.setCir383("false");
        Cir.setCir384("false");
        Cir.setCir385("false");
        Cir.setCir386("false");
        Cir.setCir387("false");
        Cir.setCir388("false");
        Cir.setCir389("false");
        Cir.setCir390("false");
        Cir.setCir391("false");
        Cir.setCir392("false");
        Cir.setCir393("false");
        Cir.setCir394("false");
        Cir.setCir395("false");
        Cir.setCir396("false");
        Cir.setCir397("false");
        Cir.setCir398("false");
        Cir.setCir399("false");


            result=CirPrivilegeDAO.insert(Cir);

            if(result==false)
                {
                    String msg=resource.getString("admin.acceptmesg.msg2");
                    request.setAttribute("msg", msg);
                    return mapping.findForward("failure");

                }



    /* Use to Update AdminRegistration Table if status is approved */

            AdminRegistration adminobj=(AdminRegistration)AdminRegistrationDAO.searchInstituteAdmin(login_id);
            adminobj.setStatus("Registered");
            adminobj.setLibraryId(library_id);
           adminobj.setStaffId(staff_id);
       

             result= AdminRegistrationDAO.update1(adminobj);

            if(result==false)
                {
                    String msg=resource.getString("admin.acceptmesg.msg2");
                    request.setAttribute("msg", msg);
                    return mapping.findForward("failure");

                }
            else{
                 
                    
                        request.setAttribute("accept_msg1", library_id);
                        request.setAttribute("accept_msg2",library_name );
                        request.setAttribute("accept_msg3",institute_name );
                        if(locale.equals("en")||locale.equals("ar")||locale.equals("ur"))
                           request.setAttribute("msg",resource.getString("admin.acceptmesg.msg1")+" "+library_id);
                        else
                          request.setAttribute("msg",resource.getString("admin.acceptmesg.msg1")+" "+library_id);
                        

                        
                  String path = servlet.getServletContext().getRealPath("/");
              obj=new Email((String)session.getAttribute("webmail"),(String)session.getAttribute("webpass"),path,staffobj.getEmailId(),password,"Approval of request for library Registration","Your request for Library registration has been Successfully Approved .\n User ID :"+login_id+"\n Password :"+password+"\n","\n\nDear "+logobj.getUserName()+",\n","With Regards\nWebAdmin\nLibMS");
 
            executor.submit(new Runnable() {

                public void run() {
                    obj.send();
                }
            });

                       
                       
                       
             
            }
              return mapping.findForward("success");

     }

       if(button.equalsIgnoreCase("Reject"))
       {
                     /* Use to Update AdminRegistration Table if status is rejected */

            AdminRegistration      adminobj=(AdminRegistration)AdminRegistrationDAO.searchInstituteAdmin(login_id);

            adminobj.setStatus("rejected");





             result= AdminRegistrationDAO.update1(adminobj);

            if(result==false)
                {
                    String msg=resource.getString("admin.acceptmesg.msg2");
                    request.setAttribute("msg", msg);
                    return mapping.findForward("failure");

                }
            else{


                        request.setAttribute("accept_msg1", "");
                        request.setAttribute("accept_msg2","" );
                        request.setAttribute("accept_msg3","");
                       
                           request.setAttribute("msg","Request for Library Registration is rejected");
                     



   String     path = servlet.getServletContext().getRealPath("/");
      obj=new Email((String)session.getAttribute("webmail"),(String)session.getAttribute("webpass"),path,adminobj.getAdminEmail(),"","Approval of request for library Registration","Sorry, Your request for Library registration had not been Approved .\n" ,"\n\nDear "+adminobj.getAdminFname()+" "+adminobj.getAdminLname()+",\n","With Regards\nWebAdmin\nLibMS");

 
            executor.submit(new Runnable() {

                public void run() {
                    obj.send();
                }
            });




                        }
                return mapping.findForward("success1");
            
       }
         return null;
    }

}
