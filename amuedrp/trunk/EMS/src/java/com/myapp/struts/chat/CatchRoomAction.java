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
public class CatchRoomAction extends org.apache.struts.action.Action {
    
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
   // String Name =(String)session.getAttribute("Name");
   // String position=(String)session.getAttribute("Position");
    CatchRoomActionForm craf =(CatchRoomActionForm)form;
    Calendar currentDate = Calendar.getInstance();
  SimpleDateFormat formatter=   new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
  String date = formatter.format(currentDate.getTime());

            String sendmsg  =request.getParameter("msg");
          
       

            System.out.println(sendmsg+"....................");


if(sendmsg!=null){


         home=System.getProperty("user.home");
                       FileInputStream in1 = new FileInputStream(home+"/chat.properties");

               
                 pro = new Properties();
				pro.load(in1);

              in1.close();

System.out.println(session.getAttribute("voterchatmsg")+".lllllllllllllllll");


pro.setProperty((String)session.getAttribute("voterchatmsg"),sendmsg);

        FileOutputStream propertiesOutput = new FileOutputStream(home+"/chat.properties");
       
        pro.store(propertiesOutput,"new changes");


//        HashMap msg=(HashMap)session.getAttribute("chatlog");
//   if(msg!=null)
//        msg.put(String.valueOf(c++),Name+">>"+sendmsg);
//   else
//   {msg=new HashMap();
//        msg.put(String.valueOf(c++),Name+">>"+sendmsg);
//        session.setAttribute("chatlog", msg);
//   }

        
        
//        //temp chat log
//
             home=System.getProperty("user.home");
            in1 = new FileInputStream(home+"/chatlog.properties");


                 pro = new Properties();
        	pro.load(in1);
//
              in1.close();
//
//
sendmsg=sendmsg.replace("'","");
//
pro.put(institute_id+"&"+session.getAttribute("chatter")+"&"+Name+"&"+String.valueOf(c++),Name+" : "+sendmsg);

        propertiesOutput = new FileOutputStream(home+"/chatlog.properties");

        pro.store(propertiesOutput,"new changes");



        return mapping.findForward(SUCCESS);
}
else
{


                // Check User Login after Room Selection
                
  String button = craf.getButton();
  String radio =craf.getRn();
  
  System.out.println("radio:"+radio + "button:"+button+Name+candidate);
  home=System.getProperty("user.home");



   FileInputStream in = new FileInputStream(home+"/chat.properties");
  				pro = new Properties();
                                 pro.load(in);


				Enumeration keys = pro.keys();
                                int i=0;
				while (keys.hasMoreElements())
				{
                                       String key=(String)keys.nextElement();

                                       //if voter already login and break...............
                                       if(key.equalsIgnoreCase(institute_id+"&"+position+"&"+Name+"&"+radio.substring(radio.lastIndexOf("&")+1,radio.length())))
                                       {
System.out.println("kkkkkkkkkkkkkkkk");
                                           session.setAttribute("msg1","You are Already login in Chat");
                                                   return mapping.findForward("success1");
                                       }

                                       pro1.put(key, pro.get(key));

                                   i++;
				}
				in.close();

                                 pro1.put(institute_id+"&"+position+"&"+Name+"&"+radio.substring(radio.lastIndexOf("&")+1,radio.length()),"Chat Start");
                                OutputStream fos = new FileOutputStream(home+"/chat.properties");


session.setAttribute("chatter",radio.substring(radio.lastIndexOf("&")+1,radio.length()));
 session.setAttribute("voterchatmsg",institute_id+"&"+position+"&"+Name+"&"+radio.substring(radio.lastIndexOf("&")+1,radio.length()));
  



					fos = new FileOutputStream(home+"/chat.properties");
					pro1.store(fos, "Current Chat Status");
					fos.close();


//String chatroom = (String)session.getAttribute("chatroom");
//  if(candidate>1 && radio==chatroom){
//      String msg="one candidate is already logged in this room so try other room";
//      session.setAttribute("msg", msg);
//    session.setAttribute("count", candidate);
//     // System.out.println("Message:-----------------"+msg);
//      return mapping.findForward("success1");
//  }else if(position!=null){

//  if(radio==null)
//   radio=(String)session.getAttribute("chatroom");
//
//  session.setAttribute("chatroom",radio);
// Chatter chatter = new Chatter(Name, "Male", 0,position,radio);
//
//
// if(session.getAttribute("ChatterList")!=null){
//
//   HashMap hm =(HashMap) session.getAttribute("ChatterList");
//                       System.out.println("HmMMMMMMMMMMM"+hm.values());
//
// if(hm!=null){
//Set set = hm.entrySet();
//// Get an iterator
//Iterator it = set.iterator();
//// Display elements
//while(it.hasNext()) {
//Map.Entry me = (Map.Entry)it.next();
//Chatter demo=(Chatter)me.getValue();
//System.out.println("Demoooooooooooo"+demo.getRoom()+demo.getPosition()+demo.getName());
//
//if(radio.equalsIgnoreCase(demo.getRoom()) && demo.getPosition().equalsIgnoreCase("voter")&& demo.getName().equalsIgnoreCase(Name))
//{
//   request.setAttribute("msg","Voter Already Login/ Please Use Another Room");
//    System.out.println("Voter Already Login/ Please Use Another Room");
//
//     return mapping.findForward("success1");
//
//
//}
//
//
//}
//
//}
// //check for role validation
//
//System.out.println(candidate+"........................");
////if(candidate>1 ){
//
////}
//
// }
//
// HashMap hm =(HashMap) session.getAttribute("ChatterList");
//                        System.out.println("HmMMMMMMMMMMM"+hm);
//
//                        if(hm!=null){
//Set set = hm.entrySet();
//// Get an iterator
//Iterator it = set.iterator();
//// Display elements
//while(it.hasNext()) {
//Map.Entry me = (Map.Entry)it.next();
//Chatter demo=(Chatter)me.getValue();
//
//
//if(demo.getName().equalsIgnoreCase(Name)){
//
//return mapping.findForward(SUCCESS);
//
//
//}
//
//}
// }  else{
//                        i++;
//                        String key=String.valueOf(i);
//
//                        loginuser.put(key, chatter);
////}
//
//        session.setAttribute("ChatterList", loginuser);
 return mapping.findForward(SUCCESS);




}



        
    }
}
