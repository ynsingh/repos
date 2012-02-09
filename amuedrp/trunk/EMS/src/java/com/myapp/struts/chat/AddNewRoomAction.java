/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.chat;

import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import chat.*;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author edrp01
 */
public class AddNewRoomAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    ChatRoomList rooms = new ChatRoomList();
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

   AddNewRoomActionForm  anr =(AddNewRoomActionForm)form;
   HttpSession session=request.getSession();
   String roomName =anr.getRn();
   String roomDescr=anr.getRd();
    Properties props = new Properties();
    String path = servlet.getServletContext().getRealPath("/");

  String home=path;
  System.out.println(home);

//  FileInputStream in = new FileInputStream(home+"/Chatroom.properties");
//
//
//				props = new Properties();
//				props.load(in);
//				Enumeration keys = props.keys();
//				while (keys.hasMoreElements())
//				{
//					String roomName1 = (String)keys.nextElement().toString();
//
//					addNewRoom(rooms, roomName1);
//				}
//				in.close();
//				session.setAttribute("chatroomlist", rooms);
//				System.err.println("Room List Created");

  getServlet().getInitParameter(home);

		if (roomName == null || (roomName = roomName.trim()).length() == 0 || roomDescr == null || (roomDescr = roomDescr.trim()).length() == 0)
		{
			request.setAttribute("error", "Please specify the room name and room description");
			//getServletContext().getRequestDispatcher("/addRoom.jsp").forward(request, response);
			//return; back page

		}
		else if (roomName != null && roomName.indexOf(" ") != -1)
		{
			request.setAttribute("error", "Room Name cannot contain spaces");
			//getServletContext().getRequestDispatcher("/addRoom.jsp").forward(request, response);
			//return back page;
		}
		try
		{
			if (rooms != null)
			{
				addNewRoom(rooms, roomName);
			}


			//String s = getServletContext().getInitParameter("saveRooms");
			boolean save = false;
		//	if (s != null && "true".equals(s) )
			{
			//	Boolean b = Boolean.valueOf(s);
			//	save = b.booleanValue();
			}
			if (save)
			{
				if (props != null)
				{
					props.put(roomName, roomDescr);
			//		String path = "/WEB-INF/"+getServletContext().getInitParameter("chatpropertyfile");
			//		String realPath = getServletContext().getRealPath(path);
			//		OutputStream fos = new FileOutputStream(realPath);
			//		props.store(fos, "List of Rooms");
			//		fos.close();
				}
				else
				{
					response.setContentType("text/html");
					//PrintWriter out = response.getWriter();
					//out.println("Properties are null");
				}
			}
			response.sendRedirect(request.getContextPath() + "/listrooms.jsp");
		}
		catch (Exception e)
		{
				System.err.println("Exception: " + e.getMessage());
		}


        
        return mapping.findForward(SUCCESS);
    }

    public void addNewRoom(ChatRoomList list, String roomName)
	{
		//String s = getServletContext().getInitParameter("maxNoOfMessages");
		int maxMessages = 25;
	//	if (s != null)
		{
	//		try
	//		{
	//			maxMessages = Integer.parseInt(s);
	//		}
	//		catch (NumberFormatException nfe)
	//		{
//
	//		}
		}
		ChatRoom room = new ChatRoom(roomName);
		room.setMaximumNoOfMessages(maxMessages);
		rooms.addRoom(room);
	}
}
