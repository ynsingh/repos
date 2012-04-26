/*
 * SET MAIL SETTING FOR SMTP SERVER
 */

package com.myapp.struts.admin;
import com.myapp.struts.utility.AppPath;
import com.myapp.struts.utility.LoggerUtils;
import com.myapp.struts.utility.UserLog;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
public class MailSettingAction extends org.apache.struts.action.Action {
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static Logger log4j =LoggerUtils.getLogger();
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
     try
     {
        HttpSession session=request.getSession();
        MailSettingActionForm msf   =(MailSettingActionForm)form;
        String button =msf.getButton();
        String faddress =msf.getFaddress();
        String host=msf.getHost();
        String password =msf.getPassword();
        String port=msf.getPort();
        String sname=msf.getSname();
        HashMap obj=new HashMap();
        String key1 = "faddress";
        String key2 = "webpass";
        String key3 = "host";
        String key4 = "port";
        String key5 = "webadmin";
        obj.put(key1, faddress);
        obj.put(key2, password);
        obj.put(key3, host);
        obj.put(key4, port);
        obj.put(key5, sname);
        UserLog.writeProperty("libms.properties",obj);
        request.setAttribute("msg", "Record Added Successfully");
        
      }
      catch(Exception e)
     {
        request.setAttribute("msg", "Insertion has error encourted"+e.getMessage());
        log4j.error(e.toString());
     }
      return mapping.findForward(SUCCESS);
    }
}
