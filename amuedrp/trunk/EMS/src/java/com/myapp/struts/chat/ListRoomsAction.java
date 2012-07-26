/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.chat;

import chat.ChatRoom;
import chat.ChatRoomList;
import com.myapp.struts.utility.AppPath;
import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Properties;
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
public class ListRoomsAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    ChatRoomList rooms ;
    Properties pro = new Properties();
    String name,home;

    String pos;
  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session=request.getSession();

        // HttpSession session=request.getSession();
//   String name =(String)session.getAttribute("user_id");
   String name =(String)session.getAttribute("loginname");
   String pos=(String)session.getAttribute("login_role");
  ListRoomsActionForm  lraf =(ListRoomsActionForm)form;

 

 String t=(String)session.getAttribute("x");
 String path = AppPath.getPropertiesFilePath();
// servlet.getServletContext().getRealPath("/");
home=path;








 


if(name!=null && t==null){
  session.setAttribute("Name", name);
  session.setAttribute("Position", pos);
  
  System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%");
 FileInputStream in = new FileInputStream(home+"chat.properties");
  				pro = new Properties();
                                pro.load(in);

                                 rooms=new ChatRoomList();
				Enumeration keys = pro.keys();
                                int i=0;
				while (keys.hasMoreElements())
				{
                                        String roomName = (String)keys.nextElement();
					String roomDescr = (String)pro.getProperty(roomName);
                                        addNewRoom(rooms, roomName, roomDescr);
                                         i++;
				}
				in.close();
                                if(i>0)
                                {session.setAttribute("chatroomlist", rooms);
                                }else
                                    session.removeAttribute("chatroomlist");
                              return mapping.findForward(SUCCESS);


}
         return null;
    }
public void addNewRoom(ChatRoomList list, String roomName, String roomDescr)
	{
		String s = getServlet().getInitParameter("maxNoOfMessages");
		int maxMessages = 25;
		if (s != null)
		{
			try
			{
				maxMessages = Integer.parseInt(s);
			}
			catch (NumberFormatException nfe)
			{

			}
		}
              
		ChatRoom room = new ChatRoom(roomName, roomDescr);
		room.setMaximumNoOfMessages(maxMessages);
		rooms.addRoom(room);
	}

}
