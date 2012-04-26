/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
//import com.myapp.struts.AdminDAO.LogsDAO;
import com.myapp.struts.hbm.Logsetting;

/**
 *
 * @author imran
 */
public class LogAction extends org.apache.struts.action.Action {
   

    private static final String SUCCESS = "success";
    private String login_id;
    private String date;
    private String time;
    private String class_name;
    private String url;
    private String activity;
    private String action_result;
    

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

                  LogActionForm la = (LogActionForm)form;
                  login_id = la.getLogin_id();
                  date =la.getDate();
                  time = la.getTime();
                  class_name = la.getClass_name();
                  url = la.getUrl();
                  activity = la.getActivity();
                  action_result = la.getAction_result();
                Logsetting obj=new Logsetting();


                    String pattern ="";

                    if(login_id!=null)
                    obj.setP1("LoginID");//,Date in ISO8601 format: %d{ISO8601} %n";


                    if(date!=null)
                    obj.setP2("DateofLogin");//Classname of caller: %C %n";


                    if(time!=null)
                    obj.setP3("LoginTime");//Logger name: %c{2} %n";


                    if(class_name!=null)
                    obj.setP4("ClassName");// Location of log event: %l %n";


                    if(url!=null)
                    obj.setP5("Url");//Message: %m %n %n";


                    if(activity!=null)
                    obj.setP6("ActivityPerformed");//Thread Name: %t %n";


                    if(action_result!=null)
                    obj.setP7("ActionResult");//Line Number: %L %n";

        //Line Number: %L %n";
//        boolean result = LogsDAO.DeleteSetting();


  //               result=LogsDAO.insertSetting(obj);
           
    //                request.setAttribute("msg", "File Logged Successfully!");
                    return mapping.findForward(SUCCESS);

    }
}
