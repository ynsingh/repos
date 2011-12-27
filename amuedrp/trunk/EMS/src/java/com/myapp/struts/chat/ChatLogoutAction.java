/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.chat;

import chat.ChatRoom;
import chat.ChatRoomList;
import chat.Chatter;
import chat.Message;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
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
public class ChatLogoutAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
HashMap<String,Chatter> loginuser= new HashMap<String,Chatter>();
HashMap<String,Message> loginuser1= new HashMap<String,Message>();
static HashMap msg=new HashMap();
int candidate=0;
static int c=0;
  int i=0,j=0;
    private static final String SUCCESS = "success";
     public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
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
        HttpSession session=request.getSession();
    String Name =(String)session.getAttribute("user_id");
    String institute_id =(String)session.getAttribute("institute_id");
    String position=(String)session.getAttribute("login_role");
     Properties pro = new Properties();
     Properties pro1 = new Properties();
    String home;



                // Check User Login after Room Selection




  home=System.getProperty("user.home");



   FileInputStream in;
//
//				Enumeration keys = pro.keys();
//                                int i=0;
//				while (keys.hasMoreElements())
//				{
//                                       String key=(String)keys.nextElement();
//
//                                       pro1.put(key, pro.get(key));
//
//                                   i++;
//				}
//				in.close();
//
//                                 pro1.put(institute_id+"&"+position+"&"+Name+"&h",(String)session.getAttribute("electionname")+"&"+(String)session.getAttribute("positionname"));
//                                 pro1.put(institute_id+"&"+position+"&"+Name,(String)session.getAttribute("electionname")+"&"+(String)session.getAttribute("positionname"));
//

                                 if(position.equalsIgnoreCase("candidate")){

//remove from chat Room List
                                in= new FileInputStream(home+"/chat.properties");
  				pro = new Properties();
                                 pro.load(in);
//
                                     OutputStream fos = new FileOutputStream(home+"/chat.properties");
					fos = new FileOutputStream(home+"/chat.properties");

                                        pro.remove((String)session.getAttribute("voterchatmsg"));
  pro.remove((String)session.getAttribute("voterchatmsg")+"&h");

  Enumeration keys = pro.keys();
                                int i=0;
				while (keys.hasMoreElements())
				{
                                       String key=(String)keys.nextElement();
if(key.endsWith(Name)){
                pro.remove(key);
}

                                   i++;
				}
				in.close();


					pro.store(fos, "Current Chat Status");
					fos.close();
//remove from chatlog List

                                        in= new FileInputStream(home+"/chatlog.properties");
  				pro = new Properties();
                                 pro.load(in);

                                     fos = new FileOutputStream(home+"/chatlog.properties");
					


                                         keys = pro.keys();
                       i=0;
				while (keys.hasMoreElements())
				{
                                       String key=(String)keys.nextElement();
                                    if(key.startsWith((String)(session.getAttribute("institute_id")+"&"+session.getAttribute("chatter")+"&"+session.getAttribute("user_id"))))
                                       pro.remove(key);

                                   i++;
				}
				in.close();

					pro.store(fos, "Current Chat Status");
					fos.close();


 return mapping.findForward(SUCCESS);
                                 }
                                 else if(position.equalsIgnoreCase("voter")){
                                in= new FileInputStream(home+"/chat.properties");
  				pro = new Properties();
                                 pro.load(in);
//
                                     OutputStream fos = new FileOutputStream(home+"/chat.properties");
					fos = new FileOutputStream(home+"/chat.properties");

                                        pro.remove((String)session.getAttribute("voterchatmsg"));
					pro.store(fos, "Current Chat Status");
					fos.close();
//remove from chatlog List

                                  /*      in= new FileInputStream(home+"/chatlog.properties");
  				pro = new Properties();
                                 pro.load(in);

                                     fos = new FileOutputStream(home+"/chatlog.properties");



                                        Enumeration keys = pro.keys();
                                int i=0;
				while (keys.hasMoreElements())
				{
                                       String key=(String)keys.nextElement();
                                    if(key.startsWith((String)(session.getAttribute("institute_id")+"&"+session.getAttribute("chatter")+"&"+session.getAttribute("user_id"))))
                                       pro.remove(key);

                                   i++;
				}
				in.close();

					pro.store(fos, "Current Chat Status");
					fos.close();

*/
 return mapping.findForward("success1");



                                 }


return mapping.findForward(SUCCESS);

}




    }
