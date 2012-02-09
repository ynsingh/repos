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
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session= request.getSession();
        String Name =(String)session.getAttribute("Name");
              SendMessageActionForm sma =(SendMessageActionForm)form;
            String sendmsg  =sma.getSendmsg();
            String submit =sma.getSubmit();
          
     Calendar currentDate = Calendar.getInstance();
  SimpleDateFormat formatter=   new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
  String date = formatter.format(currentDate.getTime());
           
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
