//SUPERADMIN CHNAGE PASSWORD & CHANGE ANY USER PASSWORD
package com.myapp.struts;
import com.myapp.struts.AdminDAO.LoginDAO;
import com.myapp.struts.AdminDAO.StaffDetailDAO;
import com.myapp.struts.hbm.*;
import com.myapp.struts.utility.LoggerUtils;
import com.myapp.struts.utility.PasswordEncruptionUtility;
import java.util.List;
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
import org.apache.log4j.Logger;
public class SuperAdminAction extends org.apache.struts.action.Action
{
   private final ExecutorService executor=Executors.newFixedThreadPool(1);
  Email obj;
  String user_id1;
    String user_id2;
    String password1;
    String password4;
    String role;
    String staff_id;
    String redirection;
    boolean result;
     private static Logger log4j =LoggerUtils.getLogger();
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        try
        {
            SuperAdminActionForm admin=(SuperAdminActionForm)form;
            user_id1=admin.getUser_id1().trim();
            password1=admin.getPassword1().trim();
            System.out.println(user_id1+"  "+password1);
                LoginDAO logindao= new LoginDAO();
                String password2 = PasswordEncruptionUtility.password_encrupt(password1);
                
                List rs1 = (List)logindao.getUser(user_id1);
               
                if(rs1!=null)
                {
                    if(rs1.size()>0)
                    {
                        Login r=(Login)rs1.get(0);

                        r.setPassword(password2);
                        result=logindao.updateadmin(r);
                        if(result==true)
                        {
                            request.setAttribute("msg","Record Successfully Updated");
                            HttpSession session = request.getSession();
                            session.removeAttribute("SuperAdminActionForm");
                             obj=new Email(r.getStaffDetail().getEmailId(),password1,"Your Password on LibMS Updated Successfully","Dear "+r.getStaffDetail().getFirstName()+" "+r.getStaffDetail().getLastName()+",\nYour Password  of LibMS has been updated Successfully for LibMS.\nUser Id="+r.getLoginId()+"\nPassword:"+password1+"\nUser Role:"+r.getRole()+"Thanks,"+"\nWeb Admin, LibMS");

            executor.submit(new Runnable() {

                public void run() {
                    obj.send();
                }
            });


                            return    mapping.findForward("success");
                        }
                    }
                }
                else
                {
                    request.setAttribute("msg","Password Incorrect!");
                    return mapping.findForward("fail");
                }




        }
        catch(Exception e)
        {
            log4j.error(e);
            return mapping.findForward("fail");
        }
    return    mapping.findForward("success");
    }
}
