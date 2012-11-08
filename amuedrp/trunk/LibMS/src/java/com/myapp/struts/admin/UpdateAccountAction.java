//Update Account Details
package com.myapp.struts.admin;
import  com.myapp.struts.hbm.Privilege;
import  com.myapp.struts.hbm.SerPrivilege;
import  com.myapp.struts.hbm.CirPrivilege;
import  com.myapp.struts.hbm.CatPrivilege;
import  com.myapp.struts.hbm.AcqPrivilege;
import  com.myapp.struts.hbm.AcqPrivilegeId;
import  com.myapp.struts.hbm.Login;
import  com.myapp.struts.hbm.StaffDetail;
import  com.myapp.struts.hbm.SerPrivilegeId;
import  com.myapp.struts.AdminDAO.*;
import com.myapp.struts.hbm.AdminRegistration;
import com.myapp.struts.hbm.Department;
import com.myapp.struts.hbm.SubLibrary;
import com.myapp.struts.systemsetupDAO.DeptDAO;
import com.myapp.struts.utility.AppPath;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.myapp.struts.utility.Email;
import com.myapp.struts.utility.PasswordEncruptionUtility;
import com.myapp.struts.utility.RandomPassword;
import java.util.Locale;
import java.util.ResourceBundle;
public class UpdateAccountAction extends org.apache.struts.action.Action {
    
   /* forward name="success" path="" */
    private final ExecutorService executor=Executors.newFixedThreadPool(1);
    Email obj;
    private String user_name;
    private String staff_id;
    private String password;
    private String library_id;
    private String login_id;
    private String button;
    private String role;
    private String sublibrary_id;
    private String question;
    private String ans;
    private boolean result;
    private String login_role;
    int i;
    LoginDAO logindao;
    private String password1;
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
         HttpSession session=request.getSession();

         try
         {
                locale1=(String)session.getAttribute("locale");
                if(session.getAttribute("locale")!=null)
                {
                    locale1 = (String)session.getAttribute("locale");
                }
                else locale1="en";
         }
         catch(Exception e)
         {
             locale1="en";
         }
        locale = new Locale(locale1);
        if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align = "left";}
        else{ rtl="RTL";align="right";}
        ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

        DeptDAO deptdao=new DeptDAO();
        SubLibraryDAO sublibdao=new SubLibraryDAO();
        PrivilegeDAO privdao=new PrivilegeDAO();
        AcqPrivilegeDAO acqprivdao=new AcqPrivilegeDAO();
        SerPrivilegeDAO serprivdao=new SerPrivilegeDAO();
        CirPrivilegeDAO cirprivdao=new CirPrivilegeDAO();
        CatPrivilegeDAO catprivdao=new CatPrivilegeDAO();
        StaffDetailDAO staffdao=new StaffDetailDAO();
        CreatePrivilege cirprivobj=new CreatePrivilege();
        AdminRegistrationDAO admindao=new AdminRegistrationDAO();

        CreateAccountActionForm caaction=(CreateAccountActionForm)form;
        login_id=caaction.getLogin_id();
        user_name=caaction.getUser_name();
        /*Password Generate and Reset It*/
        password= RandomPassword.getRandomString(10);
        password1=PasswordEncruptionUtility.password_encrupt(password);
        staff_id=caaction.getStaff_id();
        button=caaction.getButton();
        role=caaction.getRole();
        question=caaction.getQuestion();
        ans=caaction.getAns();
        logindao=new LoginDAO();
        library_id=(String)session.getAttribute("library_id");
        request.setAttribute("btn",button);
        Login logobj=logindao.searchRole(staff_id, library_id);

      
        sublibrary_id=caaction.getSublibrary_id();
        Department dept=deptdao.getDeptName(library_id, sublibrary_id);
        String sublibname=null;
        SubLibrary sublib=null;
        if(dept==null)
        {
                sublib=sublibdao.getLibName(library_id, sublibrary_id);
                sublibname=sublib.getSublibName();
                if(sublibname.equalsIgnoreCase("Main Library"))
                {
                AdminRegistration admin=admindao.searchInstitute(library_id);
                sublibname=admin.getLibraryName();
                }

        }
        else
           sublibname=dept.getDeptName();

    if(button.equals("Update Account"))
    {

        //Cannot Update Institute Admin
         String id="admin."+library_id;   
         if(staff_id.equals(id))
         {
                request.setAttribute("user_id", login_id);
                request.setAttribute("user_name", user_name);
                request.setAttribute("library_id", library_id);
                request.setAttribute("staff_id", staff_id);
                request.setAttribute("role", role);
               // request.setAttribute("msg1", "You Cannot Update Institute Admin");
                 request.setAttribute("msg1",resource.getString("admin.UpdateStaffAction.error1"));
                return mapping.findForward("success");
         }
         //Cannot delete Your Own account
            id=(String)session.getAttribute("staff_id");
            if(staff_id.equalsIgnoreCase(id) )
            {


                request.setAttribute("user_id", login_id);
                request.setAttribute("user_name", user_name);
                request.setAttribute("library_id", library_id);
                request.setAttribute("staff_id", staff_id);
                request.setAttribute("role", role);
                //request.setAttribute("msg1", "You Cannot Update Your Own Account");
                request.setAttribute("msg1",resource.getString("admin.UpdateAccountAction.error1"));
                return mapping.findForward("success");

            }
        //Cannot Create Same Level Account
         login_role=(String)session.getAttribute("login_role");
            if(logobj!=null)
            {
                if(logobj.getRole().equalsIgnoreCase(login_role))
                {
                   request.setAttribute("user_id", login_id);
                   request.setAttribute("user_name", user_name);
                   request.setAttribute("library_id", library_id);
                   request.setAttribute("staff_id", staff_id);
                   request.setAttribute("role",logobj.getRole());
                   //request.setAttribute("msg1", "You Cannot Update Admin ");
                   request.setAttribute("msg1",resource.getString("admin.UpdateAccountAction.error2"));
                   return mapping.findForward("success");
                }
            }


    //CASE 1:
    //if existing role is updated with same one of any user like staff role updated  with staff... and
    //sublibrary is also same then
    //in this case no privilege are to be reassigned  only password reset.
    

    String user_role="";
    logobj=logindao.searchRole(staff_id, library_id);
    user_role=logobj.getRole();
    if(user_role.equals(role) && logobj.getSublibraryId().equalsIgnoreCase(sublibrary_id))
    {
        logobj=logindao.searchStaffId(staff_id, library_id);
        logobj.setPassword(password1);
        logobj.setSublibraryId(sublibrary_id);
        result=logindao.update1(logobj);
                if(result==true)
                {
                    request.setAttribute("user_id", login_id);
                    request.setAttribute("user_name", user_name);
                    request.setAttribute("library_id", library_id);
                    request.setAttribute("staff_id", staff_id);
                    request.setAttribute("role", role);
                    /*mail to user for updatation*/
                    StaffDetail staffobj=staffdao.searchStaffId(staff_id, library_id);
                    mailSend(staffobj.getEmailId(),password,"Your Role on LibMS Account Updated","Dear "+user_name+",<br>Your LibMS Account Role has been Changed Successfully with Following Deatils.<br><hr><b> user_name</b> "+login_id+"<br><b> password </b> "+password+"<br><b>LibMS Account Role</b>  "+role+"<br><b> Dept/SubLibrary</b> "+sublibname+"<br>Thanks,<br>"+session.getAttribute("username")+",<br>"+session.getAttribute("login_role")+"<br>"+session.getAttribute("sublibrary_name")+"<br>"+session.getAttribute("library_name"));
                    // request.setAttribute("msg", "Record Updated Successfully");
                   request.setAttribute("msg", resource.getString("admin.UpdateAccountAction.error3"));
                   return mapping.findForward("success");

                }
                else
                {
                         //request.setAttribute("msg", "Record Cannot Updated");
                         request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error4"));
                         return mapping.findForward("error");
                }
}

  //CASE 2.if existing role is updated with same one of any user like staff role updated  with staff... but
  //sublibrary is changed  then
  //in this case no privilege are to be reassigned  can be modified but sublibrary changed.

    logobj=logindao.searchRole(staff_id, library_id);
    user_role=logobj.getRole();
    if(user_role.equals(role) && logobj.getSublibraryId().equalsIgnoreCase(sublibrary_id)==false)
    {
        logobj=logindao.searchStaffLogin(staff_id, library_id);
        logobj.setPassword(password1);
        logobj.setSublibraryId(sublibrary_id);
        result=logindao.update1(logobj);
                if(result==true)
                {
                      //update the sublibrary is all places
                    StaffDetail temp=staffdao.searchStaffId(staff_id, library_id);
                    temp.setSublibraryId(sublibrary_id);
                    result=staffdao.update1(temp);
                     if(result==true)
                    {
                    result=cirprivobj.updateSublibraryId(staff_id,library_id,sublibrary_id);
                    request.setAttribute("user_id", login_id);
                    request.setAttribute("user_name", user_name);
                    request.setAttribute("library_id", library_id);
                    request.setAttribute("staff_id", staff_id);
                    request.setAttribute("role", role);

                    /*mail to user for updatation*/
                    StaffDetail staffobj=staffdao.searchStaffId(staff_id, library_id);
                    mailSend(staffobj.getEmailId(),password,"Your Role on LibMS Account Updated","Dear "+user_name+",<br>Your LibMS Account Role has been Changed Successfully with Following Deatils.<br><hr><b> user_name</b> "+login_id+"<br><b> password </b> "+password+"<br><b>LibMS Account Role</b>  "+role+"<br><b> Dept/SubLibrary</b> "+sublibname+"<br>Thanks,<br>"+session.getAttribute("username")+",<br>"+session.getAttribute("login_role")+"<br>"+session.getAttribute("sublibrary_name")+"<br>"+session.getAttribute("library_name"));
                   // request.setAttribute("msg", "Record Updated Successfully");
                    request.setAttribute("msg", resource.getString("admin.UpdateAccountAction.error3"));
                    return mapping.findForward("success");
                    }
                     else
                     {
                    // request.setAttribute("msg", "Record Cannot Updated");
                       request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error4"));
                     return mapping.findForward("error");
                     }
                }
                else
                {
                   // request.setAttribute("msg", "Record Cannot Updated");
                   request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error4"));
                   return mapping.findForward("error");
                }
}

//CASE 3: if admin role decrease to staff then previous privilege will have to restored and only admin/systemsetup and utitlty should
//be removed



if(user_role.equals("admin") && role.equals("staff"))
{
        logobj=logindao.searchStaffLogin(staff_id, library_id);
        logobj.setRole(role);
        logobj.setPassword(password1);
        logobj.setSublibraryId(sublibrary_id);
        logobj.setQuestion(question);
        logobj.setAns(ans);
        result=logindao.update1(logobj);
                if(result==true)

                {
                    //update the sublibrary is all places
                StaffDetail temp=staffdao.searchStaffId(staff_id, library_id);
                temp.setSublibraryId(sublibrary_id);
                result=staffdao.update1(temp);
                if(result==true)
                {
                 
                    Privilege priv=privdao.searchStaffLogin(staff_id, library_id);
                    priv.setSublibraryId(sublibrary_id);
                    priv.setAdministrator("true");
                    priv.setSystemSetup("true");
                    priv.setUtilities("true");
                    AcqPrivilege acqpriv=acqprivdao.searchStaffLogin(staff_id, library_id);
                    acqpriv.setSublibraryId(sublibrary_id);
                    CatPrivilege catpriv=catprivdao.searchStaffLogin(staff_id, library_id);
                    catpriv.setSublibraryId(sublibrary_id);
                    SerPrivilege serpriv=serprivdao.searchStaffLogin(staff_id, library_id);
                    serpriv.setSublibraryId(sublibrary_id);
                    CirPrivilege cirpriv=cirprivdao.searchStaffLogin(staff_id, library_id);
                    cirpriv.setSublibraryId(sublibrary_id);
                    result=privdao.update(priv);
                    result=acqprivdao.update(acqpriv);
                    result=cirprivdao.update(cirpriv);
                    result=catprivdao.update(catpriv);
                    result=serprivdao.update(serpriv);

                        if(result==true)
                        {

                  //  cirpriv.assignStaffPrivilege(staff_id, library_id, sublibrary_id);


                      System.out.println("..............kkkkkkkk");
                    request.setAttribute("user_id", login_id);
                    request.setAttribute("user_name", user_name);
                    request.setAttribute("library_id", library_id);
                    request.setAttribute("staff_id", staff_id);
                    request.setAttribute("role", role);

                /*mail to user for updatation*/
                StaffDetail staffobj=staffdao.searchStaffId(staff_id, library_id);
           
           mailSend(staffobj.getEmailId(),password,"Your Role on LibMS Account Updated","Dear "+user_name+",<br>Your LibMS Account Role has been Changed Successfully with Following Deatils.<br><hr><b> user_name</b> "+login_id+"<br><b> password </b> "+password+"<br><b>LibMS Account Role</b>  "+role+"<br><b> Dept/SubLibrary</b> "+sublibname+"<br>Thanks,<br>"+session.getAttribute("username")+",<br>"+session.getAttribute("login_role")+"<br>"+session.getAttribute("sublibrary_name")+"<br>"+session.getAttribute("library_name"));
                    //request.setAttribute("msg", "Record Updated Successfully");
             request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error3"));



                    return mapping.findForward("success");
                }else{
                //request.setAttribute("msg", "Record Cannot Updated");
                           request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error4"));
                      return mapping.findForward("error");
                }}else{
                 //request.setAttribute("msg", "Record Cannot Updated");
                       request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error4"));
                      return mapping.findForward("error");

                }
                  }



}

//if admin role decrease to dept-staff then previous privilege will have to restored and only admin/systemsetup and utitlty should
//be removed and sublibrary is also updated
if(user_role.equals("admin") && role.equals("dept-staff"))
{
   

    logobj=logindao.searchStaffLogin(staff_id, library_id);
    logobj.setRole(role);
      logobj.setSublibraryId(sublibrary_id);
      logobj.setQuestion(question);
      logobj.setPassword(password1);
      logobj.setAns(ans);
    result=logindao.update1(logobj);



                if(result==true)

                {
                    //update the sublibrary is all places
                StaffDetail temp=staffdao.searchStaffId(staff_id, library_id);
                temp.setSublibraryId(sublibrary_id);
                result=staffdao.update1(temp);

                result=acqprivdao.DeleteStaff(staff_id, library_id);
                result=serprivdao.DeleteStaff(staff_id, library_id);

                if(result==true)
                {

                      Privilege priv=privdao.searchStaffLogin(staff_id, library_id);
                    priv.setSublibraryId(sublibrary_id);
                    priv.setAdministrator("true");
                    priv.setSystemSetup("true");
                    priv.setUtilities("true");
                    priv.setAcquisition("true");
                    priv.setSerial("true");

                    AcqPrivilegeId acqid=new AcqPrivilegeId(staff_id, library_id);
                    AcqPrivilege acq=new AcqPrivilege(acqid, temp, sublibrary_id);
                      acq.setAcq101("true");
                acq.setAcq102("true");
                acq.setAcq103("true");
                acq.setAcq104("true");
                acq.setAcq105("true");
                acq.setAcq106("true");
                acq.setAcq107("true");
                acq.setAcq108("true");
                acq.setAcq109("true");
                acq.setAcq110("true");
                acq.setAcq111("true");
                acq.setAcq112("true");
                acq.setAcq113("true");
                acq.setAcq114("true");
                acq.setAcq115("true");
                acq.setAcq116("true");
                acq.setAcq117("true");
                acq.setAcq118("true");
                acq.setAcq119("true");
                acq.setAcq120("true");
                acq.setAcq121("true");
                acq.setAcq122("true");
                acq.setAcq123("true");
                acq.setAcq124("true");
                acq.setAcq125("true");
                acq.setAcq126("true");
                acq.setAcq127("true");
                acq.setAcq128("true");
                acq.setAcq129("true");
                acq.setAcq130("true");
                acq.setAcq131("true");
                acq.setAcq132("true");
                acq.setAcq133("true");
                acq.setAcq134("true");
                acq.setAcq135("true");
                acq.setAcq136("true");
                acq.setAcq137("true");
                acq.setAcq138("true");
                acq.setAcq139("true");
                acq.setAcq140("true");
                acq.setAcq141("true");
                acq.setAcq142("true");
                acq.setAcq143("true");
                acq.setAcq144("true");
                acq.setAcq145("true");
                acq.setAcq146("true");
                acq.setAcq147("true");
                acq.setAcq148("true");
                acq.setAcq149("true");
                acq.setAcq150("true");
                acq.setAcq151("true");
                acq.setAcq152("true");
                acq.setAcq153("true");
                acq.setAcq154("true");
                acq.setAcq155("true");
                acq.setAcq156("true");
                acq.setAcq157("true");
                acq.setAcq158("true");
                acq.setAcq159("true");
                acq.setAcq160("true");
                acq.setAcq161("true");
                acq.setAcq162("true");
                acq.setAcq163("true");
                acq.setAcq164("true");
                acq.setAcq165("true");
                acq.setAcq166("true");
                acq.setAcq167("true");
                acq.setAcq168("true");
                acq.setAcq169("true");
                acq.setAcq170("true");
                acq.setAcq171("true");
                acq.setAcq172("true");
                acq.setAcq173("true");
                acq.setAcq174("true");
                acq.setAcq175("true");
                acq.setAcq176("true");
                acq.setAcq177("true");
                acq.setAcq178("true");
                acq.setAcq179("true");
                acq.setAcq180("true");
                acq.setAcq181("true");
                acq.setAcq182("true");
                acq.setAcq183("true");
                acq.setAcq184("true");
                acq.setAcq185("true");
                acq.setAcq186("true");
                acq.setAcq187("true");
                acq.setAcq188("true");
                acq.setAcq189("true");
                acq.setAcq190("true");
                acq.setAcq191("true");
                acq.setAcq192("true");
                acq.setAcq193("true");
                acq.setAcq194("true");
                acq.setAcq195("true");
                acq.setAcq196("true");
                acq.setAcq197("true");
                acq.setAcq198("true");
                acq.setAcq199("true");

                    result=acqprivdao.insert(acq);

                    SerPrivilegeId serid=new SerPrivilegeId(staff_id, library_id);
                    SerPrivilege Ser=new SerPrivilege(serid, temp, sublibrary_id);
                     Ser.setSer401("true");
        Ser.setSer402("true");
        Ser.setSer403("true");
        Ser.setSer404("true");
        Ser.setSer405("true");
        Ser.setSer406("true");
        Ser.setSer407("true");
        Ser.setSer408("true");
        Ser.setSer409("true");
        Ser.setSer410("true");
        Ser.setSer411("true");
        Ser.setSer412("true");
        Ser.setSer413("true");
        Ser.setSer414("true");
        Ser.setSer415("true");
        Ser.setSer416("true");
        Ser.setSer417("true");
        Ser.setSer418("true");
        Ser.setSer419("true");
        Ser.setSer420("true");
        Ser.setSer421("true");
        Ser.setSer422("true");
        Ser.setSer423("true");
        Ser.setSer424("true");
        Ser.setSer425("true");
        Ser.setSer426("true");
        Ser.setSer427("true");
        Ser.setSer428("true");
        Ser.setSer429("true");
        Ser.setSer430("true");
        Ser.setSer431("true");
        Ser.setSer432("true");
        Ser.setSer433("true");
        Ser.setSer434("true");
        Ser.setSer435("true");
        Ser.setSer436("true");
        Ser.setSer437("true");
        Ser.setSer438("true");
        Ser.setSer439("true");
        Ser.setSer440("true");
        Ser.setSer441("true");
        Ser.setSer442("true");
        Ser.setSer443("true");
        Ser.setSer444("true");
        Ser.setSer445("true");
        Ser.setSer446("true");
        Ser.setSer447("true");
        Ser.setSer448("true");
        Ser.setSer449("true");
        Ser.setSer450("true");
        Ser.setSer451("true");
        Ser.setSer452("true");
        Ser.setSer453("true");
        Ser.setSer454("true");
        Ser.setSer455("true");
        Ser.setSer456("true");
        Ser.setSer457("true");
        Ser.setSer458("true");
        Ser.setSer459("true");
        Ser.setSer460("true");
        Ser.setSer461("true");
        Ser.setSer462("true");
        Ser.setSer463("true");
        Ser.setSer464("true");
        Ser.setSer465("true");
        Ser.setSer466("true");
        Ser.setSer467("true");
        Ser.setSer468("true");
        Ser.setSer469("true");
        Ser.setSer470("true");
        Ser.setSer471("true");
        Ser.setSer472("true");
        Ser.setSer473("true");
        Ser.setSer474("true");
        Ser.setSer475("true");
        Ser.setSer476("true");
        Ser.setSer477("true");
        Ser.setSer478("true");
        Ser.setSer479("true");
        Ser.setSer480("true");
        Ser.setSer481("true");
        Ser.setSer482("true");
        Ser.setSer483("true");
        Ser.setSer484("true");
        Ser.setSer485("true");
        Ser.setSer486("true");
        Ser.setSer487("true");
        Ser.setSer488("true");
        Ser.setSer489("true");
        Ser.setSer490("true");
        Ser.setSer491("true");
        Ser.setSer492("true");
        Ser.setSer493("true");
        Ser.setSer494("true");
        Ser.setSer495("true");
        Ser.setSer496("true");
        Ser.setSer497("true");
        Ser.setSer498("true");
        Ser.setSer499("true");


                    result=serprivdao.insert(Ser);


                    CatPrivilege catpriv=catprivdao.searchStaffLogin(staff_id, library_id);
                    catpriv.setSublibraryId(sublibrary_id);
                    CirPrivilege cirpriv=cirprivdao.searchStaffLogin(staff_id, library_id);
                    cirpriv.setSublibraryId(sublibrary_id);

                    result=privdao.update(priv);

                    result=cirprivdao.update(cirpriv);
                    result=catprivdao.update(catpriv);





                  

                        if(result==true)
                        {

                 

                      
                    request.setAttribute("user_id", login_id);
                    request.setAttribute("user_name", user_name);
                    request.setAttribute("library_id", library_id);
                    request.setAttribute("staff_id", staff_id);

                /*mail to user for updatation*/
                StaffDetail staffobj=staffdao.searchStaffId(staff_id, library_id);
           mailSend(staffobj.getEmailId(),password,"Your Role on LibMS Account Updated","Dear "+user_name+",<br>Your LibMS Account Role has been Changed Successfully with Following Deatils.<br><hr><b> user_name</b> "+login_id+"<br><b> password </b> "+password+"<br><b>LibMS Account Role</b>  "+role+"<br><b> Dept/SubLibrary</b> "+sublibname+"<br>Thanks,<br>"+session.getAttribute("username")+",<br>"+session.getAttribute("login_role")+"<br>"+session.getAttribute("sublibrary_name")+"<br>"+session.getAttribute("library_name"));
                    request.setAttribute("role", role);
                   // request.setAttribute("msg", "Record Updated Successfully");
                     request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error3"));
                    return mapping.findForward("success");
                }else{
                //request.setAttribute("msg", "Record Cannot Updated");
                         request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error4"));
                      return mapping.findForward("error");
                }}else{
                 //request.setAttribute("msg", "Record Cannot Updated");
                     request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error4"));
                      return mapping.findForward("error");

                }
                  }



}


if(user_role.equals("admin") && role.equals("dept-admin"))
{
    logobj=logindao.searchStaffLogin(staff_id, library_id);
    logobj.setRole(role);
      logobj.setSublibraryId(sublibrary_id);
      logobj.setQuestion(question);
      logobj.setAns(ans);
      logobj.setPassword(password1);
    result=logindao.update1(logobj);



                if(result==true)

                {
                    //update the sublibrary is all places
                StaffDetail temp=staffdao.searchStaffId(staff_id, library_id);
                temp.setSublibraryId(sublibrary_id);
                result=staffdao.update1(temp);

                result=acqprivdao.DeleteStaff(staff_id, library_id);
                result=serprivdao.DeleteStaff(staff_id, library_id);

                if(result==true)
                {

                      Privilege priv=privdao.searchStaffLogin(staff_id, library_id);
                    priv.setSublibraryId(sublibrary_id);
                    priv.setAdministrator("false");
                    priv.setSystemSetup("false");
                    priv.setUtilities("false");
                    priv.setAcquisition("true");
                    priv.setSerial("true");

                    AcqPrivilegeId acqid=new AcqPrivilegeId(staff_id, library_id);
                    AcqPrivilege acq=new AcqPrivilege(acqid, temp, sublibrary_id);
                      acq.setAcq101("true");
                acq.setAcq102("true");
                acq.setAcq103("true");
                acq.setAcq104("true");
                acq.setAcq105("true");
                acq.setAcq106("true");
                acq.setAcq107("true");
                acq.setAcq108("true");
                acq.setAcq109("true");
                acq.setAcq110("true");
                acq.setAcq111("true");
                acq.setAcq112("true");
                acq.setAcq113("true");
                acq.setAcq114("true");
                acq.setAcq115("true");
                acq.setAcq116("true");
                acq.setAcq117("true");
                acq.setAcq118("true");
                acq.setAcq119("true");
                acq.setAcq120("true");
                acq.setAcq121("true");
                acq.setAcq122("true");
                acq.setAcq123("true");
                acq.setAcq124("true");
                acq.setAcq125("true");
                acq.setAcq126("true");
                acq.setAcq127("true");
                acq.setAcq128("true");
                acq.setAcq129("true");
                acq.setAcq130("true");
                acq.setAcq131("true");
                acq.setAcq132("true");
                acq.setAcq133("true");
                acq.setAcq134("true");
                acq.setAcq135("true");
                acq.setAcq136("true");
                acq.setAcq137("true");
                acq.setAcq138("true");
                acq.setAcq139("true");
                acq.setAcq140("true");
                acq.setAcq141("true");
                acq.setAcq142("true");
                acq.setAcq143("true");
                acq.setAcq144("true");
                acq.setAcq145("true");
                acq.setAcq146("true");
                acq.setAcq147("true");
                acq.setAcq148("true");
                acq.setAcq149("true");
                acq.setAcq150("true");
                acq.setAcq151("true");
                acq.setAcq152("true");
                acq.setAcq153("true");
                acq.setAcq154("true");
                acq.setAcq155("true");
                acq.setAcq156("true");
                acq.setAcq157("true");
                acq.setAcq158("true");
                acq.setAcq159("true");
                acq.setAcq160("true");
                acq.setAcq161("true");
                acq.setAcq162("true");
                acq.setAcq163("true");
                acq.setAcq164("true");
                acq.setAcq165("true");
                acq.setAcq166("true");
                acq.setAcq167("true");
                acq.setAcq168("true");
                acq.setAcq169("true");
                acq.setAcq170("true");
                acq.setAcq171("true");
                acq.setAcq172("true");
                acq.setAcq173("true");
                acq.setAcq174("true");
                acq.setAcq175("true");
                acq.setAcq176("true");
                acq.setAcq177("true");
                acq.setAcq178("true");
                acq.setAcq179("true");
                acq.setAcq180("true");
                acq.setAcq181("true");
                acq.setAcq182("true");
                acq.setAcq183("true");
                acq.setAcq184("true");
                acq.setAcq185("true");
                acq.setAcq186("true");
                acq.setAcq187("true");
                acq.setAcq188("true");
                acq.setAcq189("true");
                acq.setAcq190("true");
                acq.setAcq191("true");
                acq.setAcq192("true");
                acq.setAcq193("true");
                acq.setAcq194("true");
                acq.setAcq195("true");
                acq.setAcq196("true");
                acq.setAcq197("true");
                acq.setAcq198("true");
                acq.setAcq199("true");

                    result=acqprivdao.insert(acq);

                    SerPrivilegeId serid=new SerPrivilegeId(staff_id, library_id);
                    SerPrivilege Ser=new SerPrivilege(serid, temp, sublibrary_id);
                     Ser.setSer401("true");
        Ser.setSer402("true");
        Ser.setSer403("true");
        Ser.setSer404("true");
        Ser.setSer405("true");
        Ser.setSer406("true");
        Ser.setSer407("true");
        Ser.setSer408("true");
        Ser.setSer409("true");
        Ser.setSer410("true");
        Ser.setSer411("true");
        Ser.setSer412("true");
        Ser.setSer413("true");
        Ser.setSer414("true");
        Ser.setSer415("true");
        Ser.setSer416("true");
        Ser.setSer417("true");
        Ser.setSer418("true");
        Ser.setSer419("true");
        Ser.setSer420("true");
        Ser.setSer421("true");
        Ser.setSer422("true");
        Ser.setSer423("true");
        Ser.setSer424("true");
        Ser.setSer425("true");
        Ser.setSer426("true");
        Ser.setSer427("true");
        Ser.setSer428("true");
        Ser.setSer429("true");
        Ser.setSer430("true");
        Ser.setSer431("true");
        Ser.setSer432("true");
        Ser.setSer433("true");
        Ser.setSer434("true");
        Ser.setSer435("true");
        Ser.setSer436("true");
        Ser.setSer437("true");
        Ser.setSer438("true");
        Ser.setSer439("true");
        Ser.setSer440("true");
        Ser.setSer441("true");
        Ser.setSer442("true");
        Ser.setSer443("true");
        Ser.setSer444("true");
        Ser.setSer445("true");
        Ser.setSer446("true");
        Ser.setSer447("true");
        Ser.setSer448("true");
        Ser.setSer449("true");
        Ser.setSer450("true");
        Ser.setSer451("true");
        Ser.setSer452("true");
        Ser.setSer453("true");
        Ser.setSer454("true");
        Ser.setSer455("true");
        Ser.setSer456("true");
        Ser.setSer457("true");
        Ser.setSer458("true");
        Ser.setSer459("true");
        Ser.setSer460("true");
        Ser.setSer461("true");
        Ser.setSer462("true");
        Ser.setSer463("true");
        Ser.setSer464("true");
        Ser.setSer465("true");
        Ser.setSer466("true");
        Ser.setSer467("true");
        Ser.setSer468("true");
        Ser.setSer469("true");
        Ser.setSer470("true");
        Ser.setSer471("true");
        Ser.setSer472("true");
        Ser.setSer473("true");
        Ser.setSer474("true");
        Ser.setSer475("true");
        Ser.setSer476("true");
        Ser.setSer477("true");
        Ser.setSer478("true");
        Ser.setSer479("true");
        Ser.setSer480("true");
        Ser.setSer481("true");
        Ser.setSer482("true");
        Ser.setSer483("true");
        Ser.setSer484("true");
        Ser.setSer485("true");
        Ser.setSer486("true");
        Ser.setSer487("true");
        Ser.setSer488("true");
        Ser.setSer489("true");
        Ser.setSer490("true");
        Ser.setSer491("true");
        Ser.setSer492("true");
        Ser.setSer493("true");
        Ser.setSer494("true");
        Ser.setSer495("true");
        Ser.setSer496("true");
        Ser.setSer497("true");
        Ser.setSer498("true");
        Ser.setSer499("true");


                    result=serprivdao.insert(Ser);


                    CatPrivilege catpriv=catprivdao.searchStaffLogin(staff_id, library_id);
                    catpriv.setSublibraryId(sublibrary_id);
                    CirPrivilege cirpriv=cirprivdao.searchStaffLogin(staff_id, library_id);
                    cirpriv.setSublibraryId(sublibrary_id);

                    result=privdao.update(priv);

                    result=cirprivdao.update(cirpriv);
                    result=catprivdao.update(catpriv);







                        if(result==true)
                        {




                      System.out.println("..............kkkkkkkk");
                    request.setAttribute("user_id", login_id);
                    request.setAttribute("user_name", user_name);
                    request.setAttribute("library_id", library_id);
                    request.setAttribute("staff_id", staff_id);
                    request.setAttribute("role", role);

                /*mail to user for updatation*/
                StaffDetail staffobj=staffdao.searchStaffId(staff_id, library_id);
           mailSend(staffobj.getEmailId(),password,"Your Role on LibMS Account Updated","Dear "+user_name+",<br>Your LibMS Account Role has been Changed Successfully with Following Deatils.<br><hr><b> user_name</b> "+login_id+"<br><b> password </b> "+password+"<br><b>LibMS Account Role</b>  "+role+"<br><b> Dept/SubLibrary</b> "+sublibname+"<br>Thanks,<br>"+session.getAttribute("username")+",<br>"+session.getAttribute("login_role")+"<br>"+session.getAttribute("sublibrary_name")+"<br>"+session.getAttribute("library_name"));
                    //request.setAttribute("msg", "Record Updated Successfully");
             request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error3"));
                    return mapping.findForward("success");
                }else{
                //request.setAttribute("msg", "Record Cannot Updated");
                         request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error4"));
                      return mapping.findForward("error");
                }}else{
                 //request.setAttribute("msg", "Record Cannot Updated");
                     request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error4"));
                      return mapping.findForward("error");

                }
                  }


  }


if(user_role.equals("dept-admin") && role.equals("admin"))
{
    //////////////////////need to change
 logobj=logindao.searchStaffLogin(staff_id, library_id);
    logobj.setRole(role);
      logobj.setSublibraryId(sublibrary_id);
      logobj.setQuestion(question);
      logobj.setPassword(password1);
      logobj.setAns(ans);
    result=logindao.update1(logobj);



                if(result==true)

                {
                    //update the sublibrary is all places
                StaffDetail temp=staffdao.searchStaffId(staff_id, library_id);
                temp.setSublibraryId(sublibrary_id);
                result=staffdao.update1(temp);

                if(result==true)
                {
                   result =privdao.DeleteStaff(staff_id,library_id);
                   result=acqprivdao.DeleteStaff(staff_id, library_id);
                   result=catprivdao.DeleteStaff(staff_id, library_id);
                   result=cirprivdao.DeleteStaff(staff_id, library_id);
                   result=serprivdao.DeleteStaff(staff_id, library_id);

                        if(result==true)
                        {

                    cirprivobj.assignAdminPrivilege(staff_id, library_id, sublibrary_id);


                      System.out.println("..............kkkkkkkk");
                    request.setAttribute("user_id", login_id);
                    request.setAttribute("user_name", user_name);
                    request.setAttribute("library_id", library_id);
                    request.setAttribute("staff_id", staff_id);
                    request.setAttribute("role", role);

                /*mail to user for updatation*/
                StaffDetail staffobj=staffdao.searchStaffId(staff_id, library_id);
           mailSend(staffobj.getEmailId(),password,"Your Role on LibMS Account Updated","Dear "+user_name+",<br>Your LibMS Account Role has been Changed Successfully with Following Deatils.<br><hr><b> user_name</b> "+login_id+"<br><b> password </b> "+password+"<br><b>LibMS Account Role</b>  "+role+"<br><b> Dept/SubLibrary</b> "+sublibname+"<br>Thanks,<br>"+session.getAttribute("username")+",<br>"+session.getAttribute("login_role")+"<br>"+session.getAttribute("sublibrary_name")+"<br>"+session.getAttribute("library_name"));
                    //request.setAttribute("msg", "Record Updated Successfully");
             request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error3"));
                    return mapping.findForward("success");
                }else{
                //request.setAttribute("msg", "Record Cannot Updated");
                        request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error4"));
                      return mapping.findForward("error");
                }}else{
                 //request.setAttribute("msg", "Record Cannot Updated");
                     request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error4"));
                      return mapping.findForward("error");

                }
                  }
  }

if(user_role.equals("dept-admin") && role.equals("staff"))
{
    //////////////////////need to change
 logobj=logindao.searchStaffLogin(staff_id, library_id);
    logobj.setRole(role);
    logobj.setQuestion(question);
      logobj.setAns(ans);
      logobj.setPassword(password1);
      logobj.setSublibraryId(sublibrary_id);
    result=logindao.update1(logobj);



                if(result==true)

                {
                    //update the sublibrary is all places
                StaffDetail temp=staffdao.searchStaffId(staff_id, library_id);
                temp.setSublibraryId(sublibrary_id);
                result=staffdao.update1(temp);

                if(result==true)
                {
                result=acqprivdao.DeleteStaff(staff_id, library_id);
                result=serprivdao.DeleteStaff(staff_id, library_id);

                if(result==true)
                {

                      Privilege priv=privdao.searchStaffLogin(staff_id, library_id);
                    priv.setSublibraryId(sublibrary_id);
                    priv.setAdministrator("true");
                    priv.setSystemSetup("true");
                    priv.setUtilities("true");
                    priv.setAcquisition("true");
                    priv.setSerial("true");

                    AcqPrivilegeId acqid=new AcqPrivilegeId(staff_id, library_id);
                    AcqPrivilege acq=new AcqPrivilege(acqid, temp, sublibrary_id);
                      acq.setAcq101("true");
                acq.setAcq102("true");
                acq.setAcq103("true");
                acq.setAcq104("true");
                acq.setAcq105("true");
                acq.setAcq106("true");
                acq.setAcq107("true");
                acq.setAcq108("true");
                acq.setAcq109("true");
                acq.setAcq110("true");
                acq.setAcq111("true");
                acq.setAcq112("true");
                acq.setAcq113("true");
                acq.setAcq114("true");
                acq.setAcq115("true");
                acq.setAcq116("true");
                acq.setAcq117("true");
                acq.setAcq118("true");
                acq.setAcq119("true");
                acq.setAcq120("true");
                acq.setAcq121("true");
                acq.setAcq122("true");
                acq.setAcq123("true");
                acq.setAcq124("true");
                acq.setAcq125("true");
                acq.setAcq126("true");
                acq.setAcq127("true");
                acq.setAcq128("true");
                acq.setAcq129("true");
                acq.setAcq130("true");
                acq.setAcq131("true");
                acq.setAcq132("true");
                acq.setAcq133("true");
                acq.setAcq134("true");
                acq.setAcq135("true");
                acq.setAcq136("true");
                acq.setAcq137("true");
                acq.setAcq138("true");
                acq.setAcq139("true");
                acq.setAcq140("true");
                acq.setAcq141("true");
                acq.setAcq142("true");
                acq.setAcq143("true");
                acq.setAcq144("true");
                acq.setAcq145("true");
                acq.setAcq146("true");
                acq.setAcq147("true");
                acq.setAcq148("true");
                acq.setAcq149("true");
                acq.setAcq150("true");
                acq.setAcq151("true");
                acq.setAcq152("true");
                acq.setAcq153("true");
                acq.setAcq154("true");
                acq.setAcq155("true");
                acq.setAcq156("true");
                acq.setAcq157("true");
                acq.setAcq158("true");
                acq.setAcq159("true");
                acq.setAcq160("true");
                acq.setAcq161("true");
                acq.setAcq162("true");
                acq.setAcq163("true");
                acq.setAcq164("true");
                acq.setAcq165("true");
                acq.setAcq166("true");
                acq.setAcq167("true");
                acq.setAcq168("true");
                acq.setAcq169("true");
                acq.setAcq170("true");
                acq.setAcq171("true");
                acq.setAcq172("true");
                acq.setAcq173("true");
                acq.setAcq174("true");
                acq.setAcq175("true");
                acq.setAcq176("true");
                acq.setAcq177("true");
                acq.setAcq178("true");
                acq.setAcq179("true");
                acq.setAcq180("true");
                acq.setAcq181("true");
                acq.setAcq182("true");
                acq.setAcq183("true");
                acq.setAcq184("true");
                acq.setAcq185("true");
                acq.setAcq186("true");
                acq.setAcq187("true");
                acq.setAcq188("true");
                acq.setAcq189("true");
                acq.setAcq190("true");
                acq.setAcq191("true");
                acq.setAcq192("true");
                acq.setAcq193("true");
                acq.setAcq194("true");
                acq.setAcq195("true");
                acq.setAcq196("true");
                acq.setAcq197("true");
                acq.setAcq198("true");
                acq.setAcq199("true");

                    result=acqprivdao.insert(acq);

                    SerPrivilegeId serid=new SerPrivilegeId(staff_id, library_id);
                    SerPrivilege Ser=new SerPrivilege(serid, temp, sublibrary_id);
                     Ser.setSer401("true");
        Ser.setSer402("true");
        Ser.setSer403("true");
        Ser.setSer404("true");
        Ser.setSer405("true");
        Ser.setSer406("true");
        Ser.setSer407("true");
        Ser.setSer408("true");
        Ser.setSer409("true");
        Ser.setSer410("true");
        Ser.setSer411("true");
        Ser.setSer412("true");
        Ser.setSer413("true");
        Ser.setSer414("true");
        Ser.setSer415("true");
        Ser.setSer416("true");
        Ser.setSer417("true");
        Ser.setSer418("true");
        Ser.setSer419("true");
        Ser.setSer420("true");
        Ser.setSer421("true");
        Ser.setSer422("true");
        Ser.setSer423("true");
        Ser.setSer424("true");
        Ser.setSer425("true");
        Ser.setSer426("true");
        Ser.setSer427("true");
        Ser.setSer428("true");
        Ser.setSer429("true");
        Ser.setSer430("true");
        Ser.setSer431("true");
        Ser.setSer432("true");
        Ser.setSer433("true");
        Ser.setSer434("true");
        Ser.setSer435("true");
        Ser.setSer436("true");
        Ser.setSer437("true");
        Ser.setSer438("true");
        Ser.setSer439("true");
        Ser.setSer440("true");
        Ser.setSer441("true");
        Ser.setSer442("true");
        Ser.setSer443("true");
        Ser.setSer444("true");
        Ser.setSer445("true");
        Ser.setSer446("true");
        Ser.setSer447("true");
        Ser.setSer448("true");
        Ser.setSer449("true");
        Ser.setSer450("true");
        Ser.setSer451("true");
        Ser.setSer452("true");
        Ser.setSer453("true");
        Ser.setSer454("true");
        Ser.setSer455("true");
        Ser.setSer456("true");
        Ser.setSer457("true");
        Ser.setSer458("true");
        Ser.setSer459("true");
        Ser.setSer460("true");
        Ser.setSer461("true");
        Ser.setSer462("true");
        Ser.setSer463("true");
        Ser.setSer464("true");
        Ser.setSer465("true");
        Ser.setSer466("true");
        Ser.setSer467("true");
        Ser.setSer468("true");
        Ser.setSer469("true");
        Ser.setSer470("true");
        Ser.setSer471("true");
        Ser.setSer472("true");
        Ser.setSer473("true");
        Ser.setSer474("true");
        Ser.setSer475("true");
        Ser.setSer476("true");
        Ser.setSer477("true");
        Ser.setSer478("true");
        Ser.setSer479("true");
        Ser.setSer480("true");
        Ser.setSer481("true");
        Ser.setSer482("true");
        Ser.setSer483("true");
        Ser.setSer484("true");
        Ser.setSer485("true");
        Ser.setSer486("true");
        Ser.setSer487("true");
        Ser.setSer488("true");
        Ser.setSer489("true");
        Ser.setSer490("true");
        Ser.setSer491("true");
        Ser.setSer492("true");
        Ser.setSer493("true");
        Ser.setSer494("true");
        Ser.setSer495("true");
        Ser.setSer496("true");
        Ser.setSer497("true");
        Ser.setSer498("true");
        Ser.setSer499("true");


                    result=serprivdao.insert(Ser);


                    CatPrivilege catpriv=catprivdao.searchStaffLogin(staff_id, library_id);
                    catpriv.setSublibraryId(sublibrary_id);
                    CirPrivilege cirpriv=cirprivdao.searchStaffLogin(staff_id, library_id);
                    cirpriv.setSublibraryId(sublibrary_id);

                    result=privdao.update(priv);

                    result=cirprivdao.update(cirpriv);
                    result=catprivdao.update(catpriv);







                        if(result==true)
                        {



                      
                    request.setAttribute("user_id", login_id);
                    request.setAttribute("user_name", user_name);
                    request.setAttribute("library_id", library_id);
                    request.setAttribute("staff_id", staff_id);
                    request.setAttribute("role", role);

                /*mail to user for updatation*/
                StaffDetail staffobj=staffdao.searchStaffId(staff_id, library_id);
           mailSend(staffobj.getEmailId(),password,"Your Role on LibMS Account Updated","Dear "+user_name+",<br>Your LibMS Account Role has been Changed Successfully with Following Deatils.<br><hr><b> user_name</b> "+login_id+"<br><b> password </b> "+password+"<br><b>LibMS Account Role</b>  "+role+"<br><b> Dept/SubLibrary</b> "+sublibname+"<br>Thanks,<br>"+session.getAttribute("username")+",<br>"+session.getAttribute("login_role")+"<br>"+session.getAttribute("sublibrary_name")+"<br>"+session.getAttribute("library_name"));

                   // request.setAttribute("msg", "Record Updated Successfully");
             request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error3"));
                    return mapping.findForward("success");
                }else{
                //request.setAttribute("msg", "Record Cannot Updated");
                         request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error4"));

                      return mapping.findForward("error");
                }
                }else{
                 //request.setAttribute("msg", "Record Cannot Updated");
                     request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error4"));
                      return mapping.findForward("error");

                }
                }}
                 
  }

if(user_role.equals("dept-admin") && role.equals("dept-staff"))
{
    //////////////////////need to change
 logobj=logindao.searchStaffLogin(staff_id, library_id);

    logobj.setRole(role);
    logobj.setQuestion(question);
      logobj.setAns(ans);
      logobj.setPassword(password1);
      logobj.setSublibraryId(sublibrary_id);
    result=logindao.update1(logobj);



               if(result==true)

                {
                    //update the sublibrary is all places
                StaffDetail temp=staffdao.searchStaffId(staff_id, library_id);
                temp.setSublibraryId(sublibrary_id);
                result=staffdao.update1(temp);

                if(result==true)
                {
                result=acqprivdao.DeleteStaff(staff_id, library_id);
                result=serprivdao.DeleteStaff(staff_id, library_id);

                if(result==true)
                {

                      Privilege priv=privdao.searchStaffLogin(staff_id, library_id);
                    priv.setSublibraryId(sublibrary_id);
                    priv.setAdministrator("true");
                    priv.setSystemSetup("true");
                    priv.setUtilities("true");
                    priv.setAcquisition("true");
                    priv.setSerial("true");

                    AcqPrivilegeId acqid=new AcqPrivilegeId(staff_id, library_id);
                    AcqPrivilege acq=new AcqPrivilege(acqid, temp, sublibrary_id);
                      acq.setAcq101("true");
                acq.setAcq102("true");
                acq.setAcq103("true");
                acq.setAcq104("true");
                acq.setAcq105("true");
                acq.setAcq106("true");
                acq.setAcq107("true");
                acq.setAcq108("true");
                acq.setAcq109("true");
                acq.setAcq110("true");
                acq.setAcq111("true");
                acq.setAcq112("true");
                acq.setAcq113("true");
                acq.setAcq114("true");
                acq.setAcq115("true");
                acq.setAcq116("true");
                acq.setAcq117("true");
                acq.setAcq118("true");
                acq.setAcq119("true");
                acq.setAcq120("true");
                acq.setAcq121("true");
                acq.setAcq122("true");
                acq.setAcq123("true");
                acq.setAcq124("true");
                acq.setAcq125("true");
                acq.setAcq126("true");
                acq.setAcq127("true");
                acq.setAcq128("true");
                acq.setAcq129("true");
                acq.setAcq130("true");
                acq.setAcq131("true");
                acq.setAcq132("true");
                acq.setAcq133("true");
                acq.setAcq134("true");
                acq.setAcq135("true");
                acq.setAcq136("true");
                acq.setAcq137("true");
                acq.setAcq138("true");
                acq.setAcq139("true");
                acq.setAcq140("true");
                acq.setAcq141("true");
                acq.setAcq142("true");
                acq.setAcq143("true");
                acq.setAcq144("true");
                acq.setAcq145("true");
                acq.setAcq146("true");
                acq.setAcq147("true");
                acq.setAcq148("true");
                acq.setAcq149("true");
                acq.setAcq150("true");
                acq.setAcq151("true");
                acq.setAcq152("true");
                acq.setAcq153("true");
                acq.setAcq154("true");
                acq.setAcq155("true");
                acq.setAcq156("true");
                acq.setAcq157("true");
                acq.setAcq158("true");
                acq.setAcq159("true");
                acq.setAcq160("true");
                acq.setAcq161("true");
                acq.setAcq162("true");
                acq.setAcq163("true");
                acq.setAcq164("true");
                acq.setAcq165("true");
                acq.setAcq166("true");
                acq.setAcq167("true");
                acq.setAcq168("true");
                acq.setAcq169("true");
                acq.setAcq170("true");
                acq.setAcq171("true");
                acq.setAcq172("true");
                acq.setAcq173("true");
                acq.setAcq174("true");
                acq.setAcq175("true");
                acq.setAcq176("true");
                acq.setAcq177("true");
                acq.setAcq178("true");
                acq.setAcq179("true");
                acq.setAcq180("true");
                acq.setAcq181("true");
                acq.setAcq182("true");
                acq.setAcq183("true");
                acq.setAcq184("true");
                acq.setAcq185("true");
                acq.setAcq186("true");
                acq.setAcq187("true");
                acq.setAcq188("true");
                acq.setAcq189("true");
                acq.setAcq190("true");
                acq.setAcq191("true");
                acq.setAcq192("true");
                acq.setAcq193("true");
                acq.setAcq194("true");
                acq.setAcq195("true");
                acq.setAcq196("true");
                acq.setAcq197("true");
                acq.setAcq198("true");
                acq.setAcq199("true");

                    result=acqprivdao.insert(acq);

                    SerPrivilegeId serid=new SerPrivilegeId(staff_id, library_id);
                    SerPrivilege Ser=new SerPrivilege(serid, temp, sublibrary_id);
                     Ser.setSer401("true");
        Ser.setSer402("true");
        Ser.setSer403("true");
        Ser.setSer404("true");
        Ser.setSer405("true");
        Ser.setSer406("true");
        Ser.setSer407("true");
        Ser.setSer408("true");
        Ser.setSer409("true");
        Ser.setSer410("true");
        Ser.setSer411("true");
        Ser.setSer412("true");
        Ser.setSer413("true");
        Ser.setSer414("true");
        Ser.setSer415("true");
        Ser.setSer416("true");
        Ser.setSer417("true");
        Ser.setSer418("true");
        Ser.setSer419("true");
        Ser.setSer420("true");
        Ser.setSer421("true");
        Ser.setSer422("true");
        Ser.setSer423("true");
        Ser.setSer424("true");
        Ser.setSer425("true");
        Ser.setSer426("true");
        Ser.setSer427("true");
        Ser.setSer428("true");
        Ser.setSer429("true");
        Ser.setSer430("true");
        Ser.setSer431("true");
        Ser.setSer432("true");
        Ser.setSer433("true");
        Ser.setSer434("true");
        Ser.setSer435("true");
        Ser.setSer436("true");
        Ser.setSer437("true");
        Ser.setSer438("true");
        Ser.setSer439("true");
        Ser.setSer440("true");
        Ser.setSer441("true");
        Ser.setSer442("true");
        Ser.setSer443("true");
        Ser.setSer444("true");
        Ser.setSer445("true");
        Ser.setSer446("true");
        Ser.setSer447("true");
        Ser.setSer448("true");
        Ser.setSer449("true");
        Ser.setSer450("true");
        Ser.setSer451("true");
        Ser.setSer452("true");
        Ser.setSer453("true");
        Ser.setSer454("true");
        Ser.setSer455("true");
        Ser.setSer456("true");
        Ser.setSer457("true");
        Ser.setSer458("true");
        Ser.setSer459("true");
        Ser.setSer460("true");
        Ser.setSer461("true");
        Ser.setSer462("true");
        Ser.setSer463("true");
        Ser.setSer464("true");
        Ser.setSer465("true");
        Ser.setSer466("true");
        Ser.setSer467("true");
        Ser.setSer468("true");
        Ser.setSer469("true");
        Ser.setSer470("true");
        Ser.setSer471("true");
        Ser.setSer472("true");
        Ser.setSer473("true");
        Ser.setSer474("true");
        Ser.setSer475("true");
        Ser.setSer476("true");
        Ser.setSer477("true");
        Ser.setSer478("true");
        Ser.setSer479("true");
        Ser.setSer480("true");
        Ser.setSer481("true");
        Ser.setSer482("true");
        Ser.setSer483("true");
        Ser.setSer484("true");
        Ser.setSer485("true");
        Ser.setSer486("true");
        Ser.setSer487("true");
        Ser.setSer488("true");
        Ser.setSer489("true");
        Ser.setSer490("true");
        Ser.setSer491("true");
        Ser.setSer492("true");
        Ser.setSer493("true");
        Ser.setSer494("true");
        Ser.setSer495("true");
        Ser.setSer496("true");
        Ser.setSer497("true");
        Ser.setSer498("true");
        Ser.setSer499("true");


                    result=serprivdao.insert(Ser);


                    CatPrivilege catpriv=catprivdao.searchStaffLogin(staff_id, library_id);
                    catpriv.setSublibraryId(sublibrary_id);
                    CirPrivilege cirpriv=cirprivdao.searchStaffLogin(staff_id, library_id);
                    cirpriv.setSublibraryId(sublibrary_id);

                    result=privdao.update(priv);

                    result=cirprivdao.update(cirpriv);
                    result=catprivdao.update(catpriv);







                        if(result==true)
                        {





                    request.setAttribute("user_id", login_id);
                    request.setAttribute("user_name", user_name);
                    request.setAttribute("library_id", library_id);
                    request.setAttribute("staff_id", staff_id);
                    request.setAttribute("role", role);

                /*mail to user for updatation*/
                StaffDetail staffobj=staffdao.searchStaffId(staff_id, library_id);
           mailSend(staffobj.getEmailId(),password,"Your Role on LibMS Account Updated","Dear "+user_name+",<br>Your LibMS Account Role has been Changed Successfully with Following Deatils.<br><hr><b> user_name</b> "+login_id+"<br><b> password </b> "+password+"<br><b>LibMS Account Role</b>  "+role+"<br><b> Dept/SubLibrary</b> "+sublibname+"<br>Thanks,<br>"+session.getAttribute("username")+",<br>"+session.getAttribute("login_role")+"<br>"+session.getAttribute("sublibrary_name")+"<br>"+session.getAttribute("library_name"));
//                    request.setAttribute("msg", "Record Updated Successfully");
             request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error3"));
                    return mapping.findForward("success");
                }else{
                //request.setAttribute("msg", "Record Cannot Updated");
                         request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error4"));
                      return mapping.findForward("error");
                }}else{
                 //request.setAttribute("msg", "Record Cannot Updated");
                     request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error4"));
                      return mapping.findForward("error");

                }
                  }}
  }


if(user_role.equals("staff") && role.equals("admin"))
{
    //////////////////////need to change
 logobj=logindao.searchStaffLogin(staff_id, library_id);
    logobj.setRole(role);
     logobj.setQuestion(question);
      logobj.setAns(ans);
      logobj.setPassword(password1);
      logobj.setSublibraryId(sublibrary_id);
    result=logindao.update1(logobj);



                if(result==true)

                {
                    //update the sublibrary is all places
                StaffDetail temp=staffdao.searchStaffId(staff_id, library_id);
                temp.setSublibraryId(sublibrary_id);
                result=staffdao.update1(temp);

                if(result==true)
                {
                   result =privdao.DeleteStaff(staff_id,library_id);
                   result=acqprivdao.DeleteStaff(staff_id, library_id);
                   result=catprivdao.DeleteStaff(staff_id, library_id);
                   result=cirprivdao.DeleteStaff(staff_id, library_id);
                   result=serprivdao.DeleteStaff(staff_id, library_id);

                        if(result==true)
                        {

                    cirprivobj.assignAdminPrivilege(staff_id, library_id, sublibrary_id);


                      
                    request.setAttribute("user_id", login_id);
                    request.setAttribute("user_name", user_name);
                    request.setAttribute("library_id", library_id);
                    request.setAttribute("staff_id", staff_id);
                    request.setAttribute("role", role);

                /*mail to user for updatation*/
                StaffDetail staffobj=staffdao.searchStaffId(staff_id, library_id);
                 String path = servlet.getServletContext().getRealPath("/");
                                            mailSend(staffobj.getEmailId(),password,"Your Role on LibMS Account Updated","Dear "+user_name+",<br>Your LibMS Account Role has been Changed Successfully with Following Deatils.<br><hr><b> user_name</b> "+login_id+"<br><b> password </b> "+password+"<br><b>LibMS Account Role</b>  "+role+"<br><b> Dept/SubLibrary</b> "+sublibname+"<br>Thanks,<br>"+session.getAttribute("username")+",<br>"+session.getAttribute("login_role")+"<br>"+session.getAttribute("sublibrary_name")+"<br>"+session.getAttribute("library_name"));


               System.out.println("Mailing");
            executor.submit(new Runnable() {

                public void run() {

                    obj.send();
                }
            });
                    //request.setAttribute("msg", "Record Updated Successfully");
             request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error3"));
                    return mapping.findForward("success");
                }else{
                //request.setAttribute("msg", "Record Cannot Updated");
                        request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error4"));
                      return mapping.findForward("error");
                }}else{
                 //request.setAttribute("msg", "Record Cannot Updated");
                     request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error4"));
                      return mapping.findForward("error");

                }
                  }
  }


if(user_role.equals("staff") && role.equals("dept-admin"))
{
   //////////////////////need to change
 logobj=logindao.searchStaffLogin(staff_id, library_id);
    logobj.setRole(role);
     logobj.setQuestion(question);
      logobj.setAns(ans);
      logobj.setPassword(password1);
      logobj.setSublibraryId(sublibrary_id);
    result=logindao.update1(logobj);



           if(result==true)

                {
                    //update the sublibrary is all places
                StaffDetail temp=staffdao.searchStaffId(staff_id, library_id);
                temp.setSublibraryId(sublibrary_id);
                result=staffdao.update1(temp);

                if(result==true)
                {
                result=acqprivdao.DeleteStaff(staff_id, library_id);
                result=serprivdao.DeleteStaff(staff_id, library_id);

                if(result==true)
                {

                      Privilege priv=privdao.searchStaffLogin(staff_id, library_id);
                    priv.setSublibraryId(sublibrary_id);
                    priv.setAdministrator("false");
                    priv.setSystemSetup("false");
                    priv.setUtilities("false");
                    priv.setAcquisition("true");
                    priv.setSerial("true");

                    AcqPrivilegeId acqid=new AcqPrivilegeId(staff_id, library_id);
                    AcqPrivilege acq=new AcqPrivilege(acqid, temp, sublibrary_id);
                      acq.setAcq101("true");
                acq.setAcq102("true");
                acq.setAcq103("true");
                acq.setAcq104("true");
                acq.setAcq105("true");
                acq.setAcq106("true");
                acq.setAcq107("true");
                acq.setAcq108("true");
                acq.setAcq109("true");
                acq.setAcq110("true");
                acq.setAcq111("true");
                acq.setAcq112("true");
                acq.setAcq113("true");
                acq.setAcq114("true");
                acq.setAcq115("true");
                acq.setAcq116("true");
                acq.setAcq117("true");
                acq.setAcq118("true");
                acq.setAcq119("true");
                acq.setAcq120("true");
                acq.setAcq121("true");
                acq.setAcq122("true");
                acq.setAcq123("true");
                acq.setAcq124("true");
                acq.setAcq125("true");
                acq.setAcq126("true");
                acq.setAcq127("true");
                acq.setAcq128("true");
                acq.setAcq129("true");
                acq.setAcq130("true");
                acq.setAcq131("true");
                acq.setAcq132("true");
                acq.setAcq133("true");
                acq.setAcq134("true");
                acq.setAcq135("true");
                acq.setAcq136("true");
                acq.setAcq137("true");
                acq.setAcq138("true");
                acq.setAcq139("true");
                acq.setAcq140("true");
                acq.setAcq141("true");
                acq.setAcq142("true");
                acq.setAcq143("true");
                acq.setAcq144("true");
                acq.setAcq145("true");
                acq.setAcq146("true");
                acq.setAcq147("true");
                acq.setAcq148("true");
                acq.setAcq149("true");
                acq.setAcq150("true");
                acq.setAcq151("true");
                acq.setAcq152("true");
                acq.setAcq153("true");
                acq.setAcq154("true");
                acq.setAcq155("true");
                acq.setAcq156("true");
                acq.setAcq157("true");
                acq.setAcq158("true");
                acq.setAcq159("true");
                acq.setAcq160("true");
                acq.setAcq161("true");
                acq.setAcq162("true");
                acq.setAcq163("true");
                acq.setAcq164("true");
                acq.setAcq165("true");
                acq.setAcq166("true");
                acq.setAcq167("true");
                acq.setAcq168("true");
                acq.setAcq169("true");
                acq.setAcq170("true");
                acq.setAcq171("true");
                acq.setAcq172("true");
                acq.setAcq173("true");
                acq.setAcq174("true");
                acq.setAcq175("true");
                acq.setAcq176("true");
                acq.setAcq177("true");
                acq.setAcq178("true");
                acq.setAcq179("true");
                acq.setAcq180("true");
                acq.setAcq181("true");
                acq.setAcq182("true");
                acq.setAcq183("true");
                acq.setAcq184("true");
                acq.setAcq185("true");
                acq.setAcq186("true");
                acq.setAcq187("true");
                acq.setAcq188("true");
                acq.setAcq189("true");
                acq.setAcq190("true");
                acq.setAcq191("true");
                acq.setAcq192("true");
                acq.setAcq193("true");
                acq.setAcq194("true");
                acq.setAcq195("true");
                acq.setAcq196("true");
                acq.setAcq197("true");
                acq.setAcq198("true");
                acq.setAcq199("true");

                    result=acqprivdao.insert(acq);

                    SerPrivilegeId serid=new SerPrivilegeId(staff_id, library_id);
                    SerPrivilege Ser=new SerPrivilege(serid, temp, sublibrary_id);
                     Ser.setSer401("true");
        Ser.setSer402("true");
        Ser.setSer403("true");
        Ser.setSer404("true");
        Ser.setSer405("true");
        Ser.setSer406("true");
        Ser.setSer407("true");
        Ser.setSer408("true");
        Ser.setSer409("true");
        Ser.setSer410("true");
        Ser.setSer411("true");
        Ser.setSer412("true");
        Ser.setSer413("true");
        Ser.setSer414("true");
        Ser.setSer415("true");
        Ser.setSer416("true");
        Ser.setSer417("true");
        Ser.setSer418("true");
        Ser.setSer419("true");
        Ser.setSer420("true");
        Ser.setSer421("true");
        Ser.setSer422("true");
        Ser.setSer423("true");
        Ser.setSer424("true");
        Ser.setSer425("true");
        Ser.setSer426("true");
        Ser.setSer427("true");
        Ser.setSer428("true");
        Ser.setSer429("true");
        Ser.setSer430("true");
        Ser.setSer431("true");
        Ser.setSer432("true");
        Ser.setSer433("true");
        Ser.setSer434("true");
        Ser.setSer435("true");
        Ser.setSer436("true");
        Ser.setSer437("true");
        Ser.setSer438("true");
        Ser.setSer439("true");
        Ser.setSer440("true");
        Ser.setSer441("true");
        Ser.setSer442("true");
        Ser.setSer443("true");
        Ser.setSer444("true");
        Ser.setSer445("true");
        Ser.setSer446("true");
        Ser.setSer447("true");
        Ser.setSer448("true");
        Ser.setSer449("true");
        Ser.setSer450("true");
        Ser.setSer451("true");
        Ser.setSer452("true");
        Ser.setSer453("true");
        Ser.setSer454("true");
        Ser.setSer455("true");
        Ser.setSer456("true");
        Ser.setSer457("true");
        Ser.setSer458("true");
        Ser.setSer459("true");
        Ser.setSer460("true");
        Ser.setSer461("true");
        Ser.setSer462("true");
        Ser.setSer463("true");
        Ser.setSer464("true");
        Ser.setSer465("true");
        Ser.setSer466("true");
        Ser.setSer467("true");
        Ser.setSer468("true");
        Ser.setSer469("true");
        Ser.setSer470("true");
        Ser.setSer471("true");
        Ser.setSer472("true");
        Ser.setSer473("true");
        Ser.setSer474("true");
        Ser.setSer475("true");
        Ser.setSer476("true");
        Ser.setSer477("true");
        Ser.setSer478("true");
        Ser.setSer479("true");
        Ser.setSer480("true");
        Ser.setSer481("true");
        Ser.setSer482("true");
        Ser.setSer483("true");
        Ser.setSer484("true");
        Ser.setSer485("true");
        Ser.setSer486("true");
        Ser.setSer487("true");
        Ser.setSer488("true");
        Ser.setSer489("true");
        Ser.setSer490("true");
        Ser.setSer491("true");
        Ser.setSer492("true");
        Ser.setSer493("true");
        Ser.setSer494("true");
        Ser.setSer495("true");
        Ser.setSer496("true");
        Ser.setSer497("true");
        Ser.setSer498("true");
        Ser.setSer499("true");


                    result=serprivdao.insert(Ser);


                    CatPrivilege catpriv=catprivdao.searchStaffLogin(staff_id, library_id);
                    catpriv.setSublibraryId(sublibrary_id);
                    CirPrivilege cirpriv=cirprivdao.searchStaffLogin(staff_id, library_id);
                    cirpriv.setSublibraryId(sublibrary_id);

                    result=privdao.update(priv);

                    result=cirprivdao.update(cirpriv);
                    result=catprivdao.update(catpriv);







                        if(result==true)
                        {




                      System.out.println("..............kkkkkkkk");
                    request.setAttribute("user_id", login_id);
                    request.setAttribute("user_name", user_name);
                    request.setAttribute("library_id", library_id);
                    request.setAttribute("staff_id", staff_id);
                    request.setAttribute("role", role);

                /*mail to user for updatation*/
                StaffDetail staffobj=staffdao.searchStaffId(staff_id, library_id);

            mailSend(staffobj.getEmailId(),password,"Your Role on LibMS Account Updated","Dear "+user_name+",<br>Your LibMS Account Role has been Changed Successfully with Following Deatils.<br><hr><b> user_name</b> "+login_id+"<br><b> password </b> "+password+"<br><b>LibMS Account Role</b>  "+role+"<br><b> Dept/SubLibrary</b> "+sublibname+"<br>Thanks,<br>"+session.getAttribute("username")+",<br>"+session.getAttribute("login_role")+"<br>"+session.getAttribute("sublibrary_name")+"<br>"+session.getAttribute("library_name"));


               System.out.println("Mailing");
            executor.submit(new Runnable() {

                public void run() {

                    obj.send();
                }
            });
                    //request.setAttribute("msg", "Record Updated Successfully");
             request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error3"));
                    return mapping.findForward("success");
                }else{
                //request.setAttribute("msg", "Record Cannot Updated");
                         request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error4"));
                      return mapping.findForward("error");
                }}else{
                 //request.setAttribute("msg", "Record Cannot Updated");
                     request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error4"));
                      return mapping.findForward("error");

                }
                  }}
  }


if(user_role.equals("staff") && role.equals("dept-staff"))
{
      //////////////////////need to change
 logobj=logindao.searchStaffLogin(staff_id, library_id);
    logobj.setRole(role);
     logobj.setQuestion(question);
      logobj.setAns(ans);
      logobj.setPassword(password1);
      logobj.setSublibraryId(sublibrary_id);
    result=logindao.update1(logobj);



             if(result==true)

                {
                    //update the sublibrary is all places
                StaffDetail temp=staffdao.searchStaffId(staff_id, library_id);
                temp.setSublibraryId(sublibrary_id);
                result=staffdao.update1(temp);

                if(result==true)
                {
                result=acqprivdao.DeleteStaff(staff_id, library_id);
                result=serprivdao.DeleteStaff(staff_id, library_id);

                if(result==true)
                {

                      Privilege priv=privdao.searchStaffLogin(staff_id, library_id);
                    priv.setSublibraryId(sublibrary_id);
                    priv.setAdministrator("true");
                    priv.setSystemSetup("true");
                    priv.setUtilities("true");
                    priv.setAcquisition("true");
                    priv.setSerial("true");

                    AcqPrivilegeId acqid=new AcqPrivilegeId(staff_id, library_id);
                    AcqPrivilege acq=new AcqPrivilege(acqid, temp, sublibrary_id);
                      acq.setAcq101("true");
                acq.setAcq102("true");
                acq.setAcq103("true");
                acq.setAcq104("true");
                acq.setAcq105("true");
                acq.setAcq106("true");
                acq.setAcq107("true");
                acq.setAcq108("true");
                acq.setAcq109("true");
                acq.setAcq110("true");
                acq.setAcq111("true");
                acq.setAcq112("true");
                acq.setAcq113("true");
                acq.setAcq114("true");
                acq.setAcq115("true");
                acq.setAcq116("true");
                acq.setAcq117("true");
                acq.setAcq118("true");
                acq.setAcq119("true");
                acq.setAcq120("true");
                acq.setAcq121("true");
                acq.setAcq122("true");
                acq.setAcq123("true");
                acq.setAcq124("true");
                acq.setAcq125("true");
                acq.setAcq126("true");
                acq.setAcq127("true");
                acq.setAcq128("true");
                acq.setAcq129("true");
                acq.setAcq130("true");
                acq.setAcq131("true");
                acq.setAcq132("true");
                acq.setAcq133("true");
                acq.setAcq134("true");
                acq.setAcq135("true");
                acq.setAcq136("true");
                acq.setAcq137("true");
                acq.setAcq138("true");
                acq.setAcq139("true");
                acq.setAcq140("true");
                acq.setAcq141("true");
                acq.setAcq142("true");
                acq.setAcq143("true");
                acq.setAcq144("true");
                acq.setAcq145("true");
                acq.setAcq146("true");
                acq.setAcq147("true");
                acq.setAcq148("true");
                acq.setAcq149("true");
                acq.setAcq150("true");
                acq.setAcq151("true");
                acq.setAcq152("true");
                acq.setAcq153("true");
                acq.setAcq154("true");
                acq.setAcq155("true");
                acq.setAcq156("true");
                acq.setAcq157("true");
                acq.setAcq158("true");
                acq.setAcq159("true");
                acq.setAcq160("true");
                acq.setAcq161("true");
                acq.setAcq162("true");
                acq.setAcq163("true");
                acq.setAcq164("true");
                acq.setAcq165("true");
                acq.setAcq166("true");
                acq.setAcq167("true");
                acq.setAcq168("true");
                acq.setAcq169("true");
                acq.setAcq170("true");
                acq.setAcq171("true");
                acq.setAcq172("true");
                acq.setAcq173("true");
                acq.setAcq174("true");
                acq.setAcq175("true");
                acq.setAcq176("true");
                acq.setAcq177("true");
                acq.setAcq178("true");
                acq.setAcq179("true");
                acq.setAcq180("true");
                acq.setAcq181("true");
                acq.setAcq182("true");
                acq.setAcq183("true");
                acq.setAcq184("true");
                acq.setAcq185("true");
                acq.setAcq186("true");
                acq.setAcq187("true");
                acq.setAcq188("true");
                acq.setAcq189("true");
                acq.setAcq190("true");
                acq.setAcq191("true");
                acq.setAcq192("true");
                acq.setAcq193("true");
                acq.setAcq194("true");
                acq.setAcq195("true");
                acq.setAcq196("true");
                acq.setAcq197("true");
                acq.setAcq198("true");
                acq.setAcq199("true");

                    result=acqprivdao.insert(acq);

                    SerPrivilegeId serid=new SerPrivilegeId(staff_id, library_id);
                    SerPrivilege Ser=new SerPrivilege(serid, temp, sublibrary_id);
                     Ser.setSer401("true");
        Ser.setSer402("true");
        Ser.setSer403("true");
        Ser.setSer404("true");
        Ser.setSer405("true");
        Ser.setSer406("true");
        Ser.setSer407("true");
        Ser.setSer408("true");
        Ser.setSer409("true");
        Ser.setSer410("true");
        Ser.setSer411("true");
        Ser.setSer412("true");
        Ser.setSer413("true");
        Ser.setSer414("true");
        Ser.setSer415("true");
        Ser.setSer416("true");
        Ser.setSer417("true");
        Ser.setSer418("true");
        Ser.setSer419("true");
        Ser.setSer420("true");
        Ser.setSer421("true");
        Ser.setSer422("true");
        Ser.setSer423("true");
        Ser.setSer424("true");
        Ser.setSer425("true");
        Ser.setSer426("true");
        Ser.setSer427("true");
        Ser.setSer428("true");
        Ser.setSer429("true");
        Ser.setSer430("true");
        Ser.setSer431("true");
        Ser.setSer432("true");
        Ser.setSer433("true");
        Ser.setSer434("true");
        Ser.setSer435("true");
        Ser.setSer436("true");
        Ser.setSer437("true");
        Ser.setSer438("true");
        Ser.setSer439("true");
        Ser.setSer440("true");
        Ser.setSer441("true");
        Ser.setSer442("true");
        Ser.setSer443("true");
        Ser.setSer444("true");
        Ser.setSer445("true");
        Ser.setSer446("true");
        Ser.setSer447("true");
        Ser.setSer448("true");
        Ser.setSer449("true");
        Ser.setSer450("true");
        Ser.setSer451("true");
        Ser.setSer452("true");
        Ser.setSer453("true");
        Ser.setSer454("true");
        Ser.setSer455("true");
        Ser.setSer456("true");
        Ser.setSer457("true");
        Ser.setSer458("true");
        Ser.setSer459("true");
        Ser.setSer460("true");
        Ser.setSer461("true");
        Ser.setSer462("true");
        Ser.setSer463("true");
        Ser.setSer464("true");
        Ser.setSer465("true");
        Ser.setSer466("true");
        Ser.setSer467("true");
        Ser.setSer468("true");
        Ser.setSer469("true");
        Ser.setSer470("true");
        Ser.setSer471("true");
        Ser.setSer472("true");
        Ser.setSer473("true");
        Ser.setSer474("true");
        Ser.setSer475("true");
        Ser.setSer476("true");
        Ser.setSer477("true");
        Ser.setSer478("true");
        Ser.setSer479("true");
        Ser.setSer480("true");
        Ser.setSer481("true");
        Ser.setSer482("true");
        Ser.setSer483("true");
        Ser.setSer484("true");
        Ser.setSer485("true");
        Ser.setSer486("true");
        Ser.setSer487("true");
        Ser.setSer488("true");
        Ser.setSer489("true");
        Ser.setSer490("true");
        Ser.setSer491("true");
        Ser.setSer492("true");
        Ser.setSer493("true");
        Ser.setSer494("true");
        Ser.setSer495("true");
        Ser.setSer496("true");
        Ser.setSer497("true");
        Ser.setSer498("true");
        Ser.setSer499("true");


                    result=serprivdao.insert(Ser);


                    CatPrivilege catpriv=catprivdao.searchStaffLogin(staff_id, library_id);
                    catpriv.setSublibraryId(sublibrary_id);
                    CirPrivilege cirpriv=cirprivdao.searchStaffLogin(staff_id, library_id);
                    cirpriv.setSublibraryId(sublibrary_id);

                    result=privdao.update(priv);

                    result=cirprivdao.update(cirpriv);
                    result=catprivdao.update(catpriv);







                        if(result==true)
                        {




                      System.out.println("..............kkkkkkkk");
                    request.setAttribute("user_id", login_id);
                    request.setAttribute("user_name", user_name);
                    request.setAttribute("library_id", library_id);
                    request.setAttribute("staff_id", staff_id);
                    request.setAttribute("role", role);

                /*mail to user for updatation*/
                StaffDetail staffobj=staffdao.searchStaffId(staff_id, library_id);
                 String path = servlet.getServletContext().getRealPath("/");
            mailSend(staffobj.getEmailId(),password,"Your Role on LibMS Account Updated","Dear "+user_name+",<br>Your LibMS Account Role has been Changed Successfully with Following Deatils.<br><hr><b> user_name</b> "+login_id+"<br><b> password </b> "+password+"<br><b>LibMS Account Role</b>  "+role+"<br><b> Dept/SubLibrary</b> "+sublibname+"<br>Thanks,<br>"+session.getAttribute("username")+",<br>"+session.getAttribute("login_role")+"<br>"+session.getAttribute("sublibrary_name")+"<br>"+session.getAttribute("library_name"));


               System.out.println("Mailing");
            executor.submit(new Runnable() {

                public void run() {

                    obj.send();
                }
            });
                    //request.setAttribute("msg", "Record Updated Successfully");
             request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error3"));
                    return mapping.findForward("success");
                }else{
                //request.setAttribute("msg", "Record Cannot Updated");
                         request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error4"));
                      return mapping.findForward("error");
                }}else{
                 //request.setAttribute("msg", "Record Cannot Updated");
                     request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error4"));
                      return mapping.findForward("error");

                }
                  }}

   }


if(user_role.equals("dept-staff") && role.equals("admin"))
{
  logobj=logindao.searchStaffLogin(staff_id, library_id);
    logobj.setRole(role);
      logobj.setSublibraryId(sublibrary_id);
      logobj.setQuestion(question);
      logobj.setAns(ans);
      logobj.setPassword(password1);
    result=logindao.update1(logobj);



                if(result==true)

                {
                    //update the sublibrary is all places
                StaffDetail temp=staffdao.searchStaffId(staff_id, library_id);
                temp.setSublibraryId(sublibrary_id);
                result=staffdao.update1(temp);

                if(result==true)
                {
                   result =privdao.DeleteStaff(staff_id,library_id);
                   result=acqprivdao.DeleteStaff(staff_id, library_id);
                   result=catprivdao.DeleteStaff(staff_id, library_id);
                   result=cirprivdao.DeleteStaff(staff_id, library_id);
                   result=serprivdao.DeleteStaff(staff_id, library_id);

                        if(result==true)
                        {

                    cirprivobj.assignAdminPrivilege(staff_id, library_id, sublibrary_id);


                      System.out.println("..............kkkkkkkk");
                    request.setAttribute("user_id", login_id);
                    request.setAttribute("user_name", user_name);
                    request.setAttribute("library_id", library_id);
                    request.setAttribute("staff_id", staff_id);
                    request.setAttribute("role", role);

                /*mail to user for updatation*/
                StaffDetail staffobj=staffdao.searchStaffId(staff_id, library_id);
            
            mailSend(staffobj.getEmailId(),password,"Your Role on LibMS Account Updated","Dear "+user_name+",<br>Your LibMS Account Role has been Changed Successfully with Following Deatils.<br><hr><b> user_name</b> "+login_id+"<br><b> password </b> "+password+"<br><b>LibMS Account Role</b>  "+role+"<br><b> Dept/SubLibrary</b> "+sublibname+"<br>Thanks,<br>"+session.getAttribute("username")+",<br>"+session.getAttribute("login_role")+"<br>"+session.getAttribute("sublibrary_name")+"<br>"+session.getAttribute("library_name"));


               System.out.println("Mailing");
            executor.submit(new Runnable() {

                public void run() {

                    obj.send();
                }
            });
                    //request.setAttribute("msg", "Record Updated Successfully");
             request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error3"));
                    return mapping.findForward("success");
                }else{
                //request.setAttribute("msg", "Record Cannot Updated");
                        request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error4"));
                      return mapping.findForward("error");
                }}else{
                 //request.setAttribute("msg", "Record Cannot Updated");
                     request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error4"));
                      return mapping.findForward("error");

                }
                  }
 }

if(user_role.equals("dept-staff") && role.equals("dept-admin"))
{
    login_role=(String)session.getAttribute("login_role");
    if(login_role.equalsIgnoreCase("dept-admin"))
    {
                    request.setAttribute("user_id", login_id);
                   request.setAttribute("user_name", user_name);
                   request.setAttribute("library_id", library_id);
                   request.setAttribute("staff_id", staff_id);
                   request.setAttribute("role",logobj.getRole());
                  // request.setAttribute("msg1", "You Cannot Create Departmental Admin ");
                    request.setAttribute("msg1", resource.getString("admin.UpdateAccountAction.error5"));
                   return mapping.findForward("success");

    }


logobj=logindao.searchStaffLogin(staff_id, library_id);
    logobj.setRole(role);
    logobj.setPassword(password1);
      logobj.setSublibraryId(sublibrary_id);
      logobj.setQuestion(question);
      logobj.setAns(ans);
    result=logindao.update1(logobj);



                if(result==true)

                {
                    //update the sublibrary is all places
                StaffDetail temp=staffdao.searchStaffId(staff_id, library_id);
                temp.setSublibraryId(sublibrary_id);
                result=staffdao.update1(temp);

 if(result==true)
                {
                result=acqprivdao.DeleteStaff(staff_id, library_id);
                result=serprivdao.DeleteStaff(staff_id, library_id);

                if(result==true)
                {

                      Privilege priv=privdao.searchStaffLogin(staff_id, library_id);
                    priv.setSublibraryId(sublibrary_id);
                    priv.setAdministrator("false");
                    priv.setSystemSetup("false");
                    priv.setUtilities("false");
                    priv.setAcquisition("true");
                    priv.setSerial("true");

                    AcqPrivilegeId acqid=new AcqPrivilegeId(staff_id, library_id);
                    AcqPrivilege acq=new AcqPrivilege(acqid, temp, sublibrary_id);
                      acq.setAcq101("true");
                acq.setAcq102("true");
                acq.setAcq103("true");
                acq.setAcq104("true");
                acq.setAcq105("true");
                acq.setAcq106("true");
                acq.setAcq107("true");
                acq.setAcq108("true");
                acq.setAcq109("true");
                acq.setAcq110("true");
                acq.setAcq111("true");
                acq.setAcq112("true");
                acq.setAcq113("true");
                acq.setAcq114("true");
                acq.setAcq115("true");
                acq.setAcq116("true");
                acq.setAcq117("true");
                acq.setAcq118("true");
                acq.setAcq119("true");
                acq.setAcq120("true");
                acq.setAcq121("true");
                acq.setAcq122("true");
                acq.setAcq123("true");
                acq.setAcq124("true");
                acq.setAcq125("true");
                acq.setAcq126("true");
                acq.setAcq127("true");
                acq.setAcq128("true");
                acq.setAcq129("true");
                acq.setAcq130("true");
                acq.setAcq131("true");
                acq.setAcq132("true");
                acq.setAcq133("true");
                acq.setAcq134("true");
                acq.setAcq135("true");
                acq.setAcq136("true");
                acq.setAcq137("true");
                acq.setAcq138("true");
                acq.setAcq139("true");
                acq.setAcq140("true");
                acq.setAcq141("true");
                acq.setAcq142("true");
                acq.setAcq143("true");
                acq.setAcq144("true");
                acq.setAcq145("true");
                acq.setAcq146("true");
                acq.setAcq147("true");
                acq.setAcq148("true");
                acq.setAcq149("true");
                acq.setAcq150("true");
                acq.setAcq151("true");
                acq.setAcq152("true");
                acq.setAcq153("true");
                acq.setAcq154("true");
                acq.setAcq155("true");
                acq.setAcq156("true");
                acq.setAcq157("true");
                acq.setAcq158("true");
                acq.setAcq159("true");
                acq.setAcq160("true");
                acq.setAcq161("true");
                acq.setAcq162("true");
                acq.setAcq163("true");
                acq.setAcq164("true");
                acq.setAcq165("true");
                acq.setAcq166("true");
                acq.setAcq167("true");
                acq.setAcq168("true");
                acq.setAcq169("true");
                acq.setAcq170("true");
                acq.setAcq171("true");
                acq.setAcq172("true");
                acq.setAcq173("true");
                acq.setAcq174("true");
                acq.setAcq175("true");
                acq.setAcq176("true");
                acq.setAcq177("true");
                acq.setAcq178("true");
                acq.setAcq179("true");
                acq.setAcq180("true");
                acq.setAcq181("true");
                acq.setAcq182("true");
                acq.setAcq183("true");
                acq.setAcq184("true");
                acq.setAcq185("true");
                acq.setAcq186("true");
                acq.setAcq187("true");
                acq.setAcq188("true");
                acq.setAcq189("true");
                acq.setAcq190("true");
                acq.setAcq191("true");
                acq.setAcq192("true");
                acq.setAcq193("true");
                acq.setAcq194("true");
                acq.setAcq195("true");
                acq.setAcq196("true");
                acq.setAcq197("true");
                acq.setAcq198("true");
                acq.setAcq199("true");

                    result=acqprivdao.insert(acq);

                    SerPrivilegeId serid=new SerPrivilegeId(staff_id, library_id);
                    SerPrivilege Ser=new SerPrivilege(serid, temp, sublibrary_id);
                     Ser.setSer401("true");
        Ser.setSer402("true");
        Ser.setSer403("true");
        Ser.setSer404("true");
        Ser.setSer405("true");
        Ser.setSer406("true");
        Ser.setSer407("true");
        Ser.setSer408("true");
        Ser.setSer409("true");
        Ser.setSer410("true");
        Ser.setSer411("true");
        Ser.setSer412("true");
        Ser.setSer413("true");
        Ser.setSer414("true");
        Ser.setSer415("true");
        Ser.setSer416("true");
        Ser.setSer417("true");
        Ser.setSer418("true");
        Ser.setSer419("true");
        Ser.setSer420("true");
        Ser.setSer421("true");
        Ser.setSer422("true");
        Ser.setSer423("true");
        Ser.setSer424("true");
        Ser.setSer425("true");
        Ser.setSer426("true");
        Ser.setSer427("true");
        Ser.setSer428("true");
        Ser.setSer429("true");
        Ser.setSer430("true");
        Ser.setSer431("true");
        Ser.setSer432("true");
        Ser.setSer433("true");
        Ser.setSer434("true");
        Ser.setSer435("true");
        Ser.setSer436("true");
        Ser.setSer437("true");
        Ser.setSer438("true");
        Ser.setSer439("true");
        Ser.setSer440("true");
        Ser.setSer441("true");
        Ser.setSer442("true");
        Ser.setSer443("true");
        Ser.setSer444("true");
        Ser.setSer445("true");
        Ser.setSer446("true");
        Ser.setSer447("true");
        Ser.setSer448("true");
        Ser.setSer449("true");
        Ser.setSer450("true");
        Ser.setSer451("true");
        Ser.setSer452("true");
        Ser.setSer453("true");
        Ser.setSer454("true");
        Ser.setSer455("true");
        Ser.setSer456("true");
        Ser.setSer457("true");
        Ser.setSer458("true");
        Ser.setSer459("true");
        Ser.setSer460("true");
        Ser.setSer461("true");
        Ser.setSer462("true");
        Ser.setSer463("true");
        Ser.setSer464("true");
        Ser.setSer465("true");
        Ser.setSer466("true");
        Ser.setSer467("true");
        Ser.setSer468("true");
        Ser.setSer469("true");
        Ser.setSer470("true");
        Ser.setSer471("true");
        Ser.setSer472("true");
        Ser.setSer473("true");
        Ser.setSer474("true");
        Ser.setSer475("true");
        Ser.setSer476("true");
        Ser.setSer477("true");
        Ser.setSer478("true");
        Ser.setSer479("true");
        Ser.setSer480("true");
        Ser.setSer481("true");
        Ser.setSer482("true");
        Ser.setSer483("true");
        Ser.setSer484("true");
        Ser.setSer485("true");
        Ser.setSer486("true");
        Ser.setSer487("true");
        Ser.setSer488("true");
        Ser.setSer489("true");
        Ser.setSer490("true");
        Ser.setSer491("true");
        Ser.setSer492("true");
        Ser.setSer493("true");
        Ser.setSer494("true");
        Ser.setSer495("true");
        Ser.setSer496("true");
        Ser.setSer497("true");
        Ser.setSer498("true");
        Ser.setSer499("true");


                    result=serprivdao.insert(Ser);


                    CatPrivilege catpriv=catprivdao.searchStaffLogin(staff_id, library_id);
                    catpriv.setSublibraryId(sublibrary_id);
                    CirPrivilege cirpriv=cirprivdao.searchStaffLogin(staff_id, library_id);
                    cirpriv.setSublibraryId(sublibrary_id);

                    result=privdao.update(priv);

                    result=cirprivdao.update(cirpriv);
                    result=catprivdao.update(catpriv);







                        if(result==true)
                        {




                      System.out.println("..............kkkkkkkk");
                    request.setAttribute("user_id", login_id);
                    request.setAttribute("user_name", user_name);
                    request.setAttribute("library_id", library_id);
                    request.setAttribute("staff_id", staff_id);
                    request.setAttribute("role", role);
                   // request.setAttribute("msg", "Record Updated Successfully");
                    request.setAttribute("msg", resource.getString("admin.UpdateAccountAction.error3"));

                /*mail to user for updatation*/
                StaffDetail staffobj=staffdao.searchStaffId(staff_id, library_id);
            
            mailSend(staffobj.getEmailId(),password,"Your Role on LibMS Account Updated","Dear "+user_name+",<br>Your LibMS Account Role has been Changed Successfully with Following Deatils.<br><hr><b> user_name</b> "+login_id+"<br><b> password </b> "+password+"<br><b>LibMS Account Role</b>  "+role+"<br><b> Dept/SubLibrary</b> "+sublibname+"<br>Thanks,<br>"+session.getAttribute("username")+",<br>"+session.getAttribute("login_role")+"<br>"+session.getAttribute("sublibrary_name")+"<br>"+session.getAttribute("library_name"));


               System.out.println("Mailing");
            executor.submit(new Runnable() {

                public void run() {

                    obj.send();
                }
            });

                    return mapping.findForward("success");
                }else{
                //request.setAttribute("msg", "Record Cannot Updated");
                            request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error4"));
                      return mapping.findForward("error");
                }}else{
                 //request.setAttribute("msg", "Record Cannot Updated");
                        request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error4"));
                      return mapping.findForward("error");

                }
                  }}
   }
if(user_role.equals("dept-staff") && role.equals("staff"))
{
 logobj=logindao.searchStaffLogin(staff_id, library_id);
    logobj.setRole(role);
      logobj.setSublibraryId(sublibrary_id);
      logobj.setQuestion(question);
      logobj.setPassword(password1);
      logobj.setAns(ans);
    result=logindao.update1(logobj);



                if(result==true)

                {
                    //update the sublibrary is all places
                StaffDetail temp=staffdao.searchStaffId(staff_id, library_id);
                temp.setSublibraryId(sublibrary_id);
                result=staffdao.update1(temp);

               if(result==true)
                {
                result=acqprivdao.DeleteStaff(staff_id, library_id);
                result=serprivdao.DeleteStaff(staff_id, library_id);

                if(result==true)
                {

                      Privilege priv=privdao.searchStaffLogin(staff_id, library_id);
                    priv.setSublibraryId(sublibrary_id);
                    priv.setAdministrator("true");
                    priv.setSystemSetup("true");
                    priv.setUtilities("true");
                    priv.setAcquisition("true");
                    priv.setSerial("true");

                    AcqPrivilegeId acqid=new AcqPrivilegeId(staff_id, library_id);
                    AcqPrivilege acq=new AcqPrivilege(acqid, temp, sublibrary_id);
                      acq.setAcq101("true");
                acq.setAcq102("true");
                acq.setAcq103("true");
                acq.setAcq104("true");
                acq.setAcq105("true");
                acq.setAcq106("true");
                acq.setAcq107("true");
                acq.setAcq108("true");
                acq.setAcq109("true");
                acq.setAcq110("true");
                acq.setAcq111("true");
                acq.setAcq112("true");
                acq.setAcq113("true");
                acq.setAcq114("true");
                acq.setAcq115("true");
                acq.setAcq116("true");
                acq.setAcq117("true");
                acq.setAcq118("true");
                acq.setAcq119("true");
                acq.setAcq120("true");
                acq.setAcq121("true");
                acq.setAcq122("true");
                acq.setAcq123("true");
                acq.setAcq124("true");
                acq.setAcq125("true");
                acq.setAcq126("true");
                acq.setAcq127("true");
                acq.setAcq128("true");
                acq.setAcq129("true");
                acq.setAcq130("true");
                acq.setAcq131("true");
                acq.setAcq132("true");
                acq.setAcq133("true");
                acq.setAcq134("true");
                acq.setAcq135("true");
                acq.setAcq136("true");
                acq.setAcq137("true");
                acq.setAcq138("true");
                acq.setAcq139("true");
                acq.setAcq140("true");
                acq.setAcq141("true");
                acq.setAcq142("true");
                acq.setAcq143("true");
                acq.setAcq144("true");
                acq.setAcq145("true");
                acq.setAcq146("true");
                acq.setAcq147("true");
                acq.setAcq148("true");
                acq.setAcq149("true");
                acq.setAcq150("true");
                acq.setAcq151("true");
                acq.setAcq152("true");
                acq.setAcq153("true");
                acq.setAcq154("true");
                acq.setAcq155("true");
                acq.setAcq156("true");
                acq.setAcq157("true");
                acq.setAcq158("true");
                acq.setAcq159("true");
                acq.setAcq160("true");
                acq.setAcq161("true");
                acq.setAcq162("true");
                acq.setAcq163("true");
                acq.setAcq164("true");
                acq.setAcq165("true");
                acq.setAcq166("true");
                acq.setAcq167("true");
                acq.setAcq168("true");
                acq.setAcq169("true");
                acq.setAcq170("true");
                acq.setAcq171("true");
                acq.setAcq172("true");
                acq.setAcq173("true");
                acq.setAcq174("true");
                acq.setAcq175("true");
                acq.setAcq176("true");
                acq.setAcq177("true");
                acq.setAcq178("true");
                acq.setAcq179("true");
                acq.setAcq180("true");
                acq.setAcq181("true");
                acq.setAcq182("true");
                acq.setAcq183("true");
                acq.setAcq184("true");
                acq.setAcq185("true");
                acq.setAcq186("true");
                acq.setAcq187("true");
                acq.setAcq188("true");
                acq.setAcq189("true");
                acq.setAcq190("true");
                acq.setAcq191("true");
                acq.setAcq192("true");
                acq.setAcq193("true");
                acq.setAcq194("true");
                acq.setAcq195("true");
                acq.setAcq196("true");
                acq.setAcq197("true");
                acq.setAcq198("true");
                acq.setAcq199("true");

                    result=acqprivdao.insert(acq);

                    SerPrivilegeId serid=new SerPrivilegeId(staff_id, library_id);
                    SerPrivilege Ser=new SerPrivilege(serid, temp, sublibrary_id);
                     Ser.setSer401("true");
        Ser.setSer402("true");
        Ser.setSer403("true");
        Ser.setSer404("true");
        Ser.setSer405("true");
        Ser.setSer406("true");
        Ser.setSer407("true");
        Ser.setSer408("true");
        Ser.setSer409("true");
        Ser.setSer410("true");
        Ser.setSer411("true");
        Ser.setSer412("true");
        Ser.setSer413("true");
        Ser.setSer414("true");
        Ser.setSer415("true");
        Ser.setSer416("true");
        Ser.setSer417("true");
        Ser.setSer418("true");
        Ser.setSer419("true");
        Ser.setSer420("true");
        Ser.setSer421("true");
        Ser.setSer422("true");
        Ser.setSer423("true");
        Ser.setSer424("true");
        Ser.setSer425("true");
        Ser.setSer426("true");
        Ser.setSer427("true");
        Ser.setSer428("true");
        Ser.setSer429("true");
        Ser.setSer430("true");
        Ser.setSer431("true");
        Ser.setSer432("true");
        Ser.setSer433("true");
        Ser.setSer434("true");
        Ser.setSer435("true");
        Ser.setSer436("true");
        Ser.setSer437("true");
        Ser.setSer438("true");
        Ser.setSer439("true");
        Ser.setSer440("true");
        Ser.setSer441("true");
        Ser.setSer442("true");
        Ser.setSer443("true");
        Ser.setSer444("true");
        Ser.setSer445("true");
        Ser.setSer446("true");
        Ser.setSer447("true");
        Ser.setSer448("true");
        Ser.setSer449("true");
        Ser.setSer450("true");
        Ser.setSer451("true");
        Ser.setSer452("true");
        Ser.setSer453("true");
        Ser.setSer454("true");
        Ser.setSer455("true");
        Ser.setSer456("true");
        Ser.setSer457("true");
        Ser.setSer458("true");
        Ser.setSer459("true");
        Ser.setSer460("true");
        Ser.setSer461("true");
        Ser.setSer462("true");
        Ser.setSer463("true");
        Ser.setSer464("true");
        Ser.setSer465("true");
        Ser.setSer466("true");
        Ser.setSer467("true");
        Ser.setSer468("true");
        Ser.setSer469("true");
        Ser.setSer470("true");
        Ser.setSer471("true");
        Ser.setSer472("true");
        Ser.setSer473("true");
        Ser.setSer474("true");
        Ser.setSer475("true");
        Ser.setSer476("true");
        Ser.setSer477("true");
        Ser.setSer478("true");
        Ser.setSer479("true");
        Ser.setSer480("true");
        Ser.setSer481("true");
        Ser.setSer482("true");
        Ser.setSer483("true");
        Ser.setSer484("true");
        Ser.setSer485("true");
        Ser.setSer486("true");
        Ser.setSer487("true");
        Ser.setSer488("true");
        Ser.setSer489("true");
        Ser.setSer490("true");
        Ser.setSer491("true");
        Ser.setSer492("true");
        Ser.setSer493("true");
        Ser.setSer494("true");
        Ser.setSer495("true");
        Ser.setSer496("true");
        Ser.setSer497("true");
        Ser.setSer498("true");
        Ser.setSer499("true");


                    result=serprivdao.insert(Ser);


                    CatPrivilege catpriv=catprivdao.searchStaffLogin(staff_id, library_id);
                    catpriv.setSublibraryId(sublibrary_id);
                    CirPrivilege cirpriv=cirprivdao.searchStaffLogin(staff_id, library_id);
                    cirpriv.setSublibraryId(sublibrary_id);

                    result=privdao.update(priv);

                    result=cirprivdao.update(cirpriv);
                    result=catprivdao.update(catpriv);







                        if(result==true)
                        {




                      System.out.println("..............kkkkkkkk");
                    request.setAttribute("user_id", login_id);
                    request.setAttribute("user_name", user_name);
                    request.setAttribute("library_id", library_id);
                    request.setAttribute("staff_id", staff_id);
                    request.setAttribute("role", role);
                    // request.setAttribute("msg", "Record Updated Successfully");
                        request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error3"));

                /*mail to user for updatation*/
                StaffDetail staffobj=staffdao.searchStaffId(staff_id, library_id);
            
            mailSend(staffobj.getEmailId(),password,"Your Role on LibMS Account Updated","Dear "+user_name+",<br>Your LibMS Account Role has been Changed Successfully with Following Deatils.<br><hr><b> user_name</b> "+login_id+"<br><b> password </b> "+password+"<br><b>LibMS Account Role</b>  "+role+"<br><b> Dept/SubLibrary</b> "+sublibname+"<br>Thanks,<br>"+session.getAttribute("username")+",<br>"+session.getAttribute("login_role")+"<br>"+session.getAttribute("sublibrary_name")+"<br>"+session.getAttribute("library_name"));


               System.out.println("Mailing");
            executor.submit(new Runnable() {

                public void run() {
                 
                    obj.send();
                }
            });
                   
                    return mapping.findForward("success");
                }else{
               // request.setAttribute("msg", "Record Cannot Updated");
                            request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error4"));
                      return mapping.findForward("error");
                }}else{
                // request.setAttribute("msg", "Record Cannot Updated");
                        request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error4"));
                      return mapping.findForward("error");

                }
                  }}

  }






          




    }
 
   
    if(button.equals("Confirm"))
    {
      String id="admin."+library_id;   //get Institute admin ID
   if(staff_id.equals(id))
         {
              request.setAttribute("user_id", login_id);
                request.setAttribute("user_name", user_name);
                request.setAttribute("library_id", library_id);
                request.setAttribute("staff_id", staff_id);
                 request.setAttribute("role", role);
              //  request.setAttribute("msg1", "You Cannot Delete Institute Admin");
                   request.setAttribute("msg1",resource.getString("admin.UpdateAccountAction.error6"));
                  return mapping.findForward("success");
         }

        id=(String)session.getAttribute("staff_id");  //cannot delete own account
            if(staff_id.equals(id))
            {


                   request.setAttribute("user_id", login_id);
                request.setAttribute("user_name", user_name);
                request.setAttribute("library_id", library_id);
                request.setAttribute("staff_id", staff_id);
                 request.setAttribute("role", role);
                // request.setAttribute("msg1", "You Cannot Delete Your Own Account");
                request.setAttribute("msg1", resource.getString("admin.UpdateAccountAction.error7"));
                  return mapping.findForward("success");

            }



       login_role=(String)session.getAttribute("login_role");    //cannot delete Same Level Account

    Login    log=logindao.searchRole(staff_id, library_id);

            if(log!=null)
            {
                if(log.getRole().equalsIgnoreCase(login_role))
                {
                   request.setAttribute("user_id", login_id);
                request.setAttribute("user_name", user_name);
                request.setAttribute("library_id", library_id);
                request.setAttribute("staff_id", staff_id);
                 request.setAttribute("role", role);
              //  request.setAttribute("msg1", "You Cannot Delete Admin");
                   request.setAttribute("msg1",resource.getString("admin.UpdateAccountAction.error8"));
                  return mapping.findForward("success");
                }
            }

    result=logindao.DeleteLogin(staff_id, library_id,sublibrary_id);
    result=privdao.DeleteStaff(staff_id, library_id, sublibrary_id);
    result=acqprivdao.DeleteLogin(staff_id, library_id,sublibrary_id);
    result=catprivdao.DeleteLogin(staff_id, library_id,sublibrary_id);
    result=cirprivdao.DeleteLogin(staff_id, library_id, sublibrary_id);
    result=serprivdao.DeleteLogin(staff_id, library_id, sublibrary_id);
       

        if(result==true)

      {
            request.setAttribute("user_id", login_id);
      request.setAttribute("user_name", user_name);
      request.setAttribute("library_id", library_id);
      request.setAttribute("staff_id", staff_id);
     request.setAttribute("role", role);


          //  request.setAttribute("msg", "Record Deleted Successfully  ");
       request.setAttribute("msg",resource.getString("admin.UpdateAccountAction.error9"));
            /*code for Java Mailing*/
            /*mail to user for deletion of account message*/
                StaffDetail staffobj=staffdao.searchStaffId(staff_id, library_id);
        mailSend(staffobj.getEmailId(),password,"Your Account on LibMS Account Updated","Dear "+user_name+",\nYour LibMS Account Role is removed <br>Thanks,<br>"+session.getAttribute("username")+",<br>"+session.getAttribute("login_role")+"<br>"+session.getAttribute("sublibrary_name")+"<br>"+session.getAttribute("library_name"));


                 
            executor.submit(new Runnable() {

                public void run() {
                    obj.send();
                }
            });



         return mapping.findForward("success");





     }
        else{
           request.setAttribute("user_id", login_id);
      request.setAttribute("user_name", user_name);
      request.setAttribute("library_id", library_id);
      request.setAttribute("staff_id", staff_id);
     request.setAttribute("role", role);


         //   request.setAttribute("msg", "Some Error Encountered ");
        request.setAttribute("msg",resource.getString("admin.error.error"));
         return mapping.findForward("success");

        }
    }
      return mapping.findForward("success");
       
      }
    public void mailSend(String to,String admin_password,String subject,String body){
            AppPath.sendmail(to,admin_password,subject,body);
    }
    }




    
