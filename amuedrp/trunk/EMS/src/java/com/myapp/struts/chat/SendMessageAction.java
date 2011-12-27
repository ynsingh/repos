/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.chat;

import chat.Message;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author edrp01
 */
public class SendMessageAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    HashMap<String,Message> loginuser= new HashMap<String,Message >();
    int i=0;
    // public static final String DATE_FORMAT_NOW = "yyyy-MM-dd";
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
        HttpSession session= request.getSession();
        String Name =(String)session.getAttribute("Name");
              SendMessageActionForm sma =(SendMessageActionForm)form;
            String sendmsg  =sma.getSendmsg();
            String submit =sma.getSubmit();
           // DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	   //get current date time with Date()
	  // Date date = new Date();
	  // System.out.println(dateFormat.format(date));

	   //get current date time with Calendar()
	   //Calendar cal = Calendar.getInstance();
	   //System.out.println(dateFormat.format(cal.getTime()));

    //SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
   // sdf.format(cal.getTime());
    //String date=String.valueOf(sdf.format(cal.getTime()));
   // Calendar currentDate = Calendar.getInstance();
 // SimpleDateFormat formatter=
 // new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
 // String date = formatter.format(currentDate.getTime());
     //System.out.println(date+"@@@@@@@@@@@@3333333333333");
     Calendar currentDate = Calendar.getInstance();
  SimpleDateFormat formatter=   new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
  String date = formatter.format(currentDate.getTime());
            System.out.println(sendmsg+"....................");
           if(sendmsg!=null){
            Message obj=new Message(Name, sendmsg,date ,(String)session.getAttribute("chatroom"));
            i++;
                        String key=String.valueOf(i);

                        loginuser.put(key, obj);
//}

                      // }
        session.setAttribute("MessageList", loginuser);
}
        
        return null;
    }
}
