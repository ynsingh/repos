///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
package com.myapp.struts.election_manager;
//
import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import chat.*;
import java.io.File;
import java.io.FileOutputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
//
///**
// *
// * @author edrp01
// */
public class EmailBodyAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    ChatRoomList rooms = new ChatRoomList();
   // String success;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

   MailBodyFormBean  mbf =(MailBodyFormBean)form;
   HttpSession session=request.getSession();
  String candidateid =mbf.getCandidateid();
  String voterid=mbf.getVoterid();
   String electionmail =mbf.getElectionmail();
   
    Properties pro = new Properties();
   // String path = servlet.getServletContext().getRealPath("/");



 String path = (String)session.getAttribute("apppath");
 path=path.substring(0,path.lastIndexOf("/"));
path=path.substring(0,path.lastIndexOf("/"));
path=path.substring(0,path.lastIndexOf("/"));
System.out.println(path);
   String home=path+"/web/";

File f = new File(home + "mail.properties");
//
FileInputStream in = new FileInputStream(f);
  pro.load(in);
 System.out.print("Enter Key : "+f+in+home);
String key = "";
String val = "";

String role=(String)session.getAttribute("login_role");
if(role.startsWith("insti"))
{
    if(electionmail.isEmpty()){

     request.setAttribute("msgmail","Sorry Cannot Accept Blank Data");
    return mapping.findForward("fail");


    }
   key= (String)session.getAttribute("user_id")+"em";
    val=electionmail;
    pro.setProperty(key, val);
    pro.store(new FileOutputStream(home + "mail.properties"),null);
    request.setAttribute("msgmail","Mail Setting Successfully uploaded");
    return mapping.findForward("insti-admin");

}else{

     if(voterid.isEmpty() && candidateid.isEmpty()){

     request.setAttribute("msgmail","Sorry Cannot Accept Blank Data");
    return mapping.findForward("fail");


    }

if(voterid!=null && voterid.isEmpty()==false)
{key= (String)session.getAttribute("user_id")+"voter";
    val=voterid;
    pro.setProperty(key, val);
}
 if(candidateid!=null && candidateid.isEmpty()==false)
     {key= (String)session.getAttribute("user_id")+"candi";
    val=candidateid;
  pro.setProperty(key, val);
 }

//val+="candi"+candidateid;
}


pro.store(new FileOutputStream(home + "mail.properties"),null);

request.setAttribute("msgmail","Mail Setting Successfully uploaded");
System.out.println("msgmail---------------------------------");
  return mapping.findForward(SUCCESS);

    }

}

