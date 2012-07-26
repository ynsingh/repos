/*
*
 SET MAIL BODY ACTION IN ELECTION MANAGER INTERFACE
*/
package com.myapp.struts.election_manager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.utility.LoggerUtils;
import com.myapp.struts.utility.UserLog;
import java.util.HashMap;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
public class EmailBodyAction extends org.apache.struts.action.Action
{

    /* forward name="success" path="" */
    HashMap obj=new HashMap();
    private static Logger log4j =LoggerUtils.getLogger();
    String role=null;
    private static final String SUCCESS = "success";
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
try
{
       MailBodyFormBean  mbf =(MailBodyFormBean)form;
       HttpSession session=request.getSession();
       String candidateid =mbf.getCandidateid();
       String voterid=mbf.getVoterid();
       String electionmail =mbf.getElectionmail();
       String changepass = mbf.getChangepass();
       String onetimekey = mbf.getOnetime();
       String resetlogin   = mbf.getResetlogin();
       String resetpasswithonetimekey =mbf.getResetpassonetime();
       String resetpasswithonetimekeyandlink=mbf.getResetpassonetimewithlink();
        String key = "";
        String key1 = "";
        String key2 = "";
        String key3 = "";
        String key4 = "";
        String key5 = "";
        String val = "";

        role=(String)session.getAttribute("login_role");
        if(role.startsWith("insti"))
        {
            if(electionmail.isEmpty())
            {
                 request.setAttribute("msgmail","Sorry Cannot Accept Blank Data");
                 return mapping.findForward("fail");
            }
             key= (String)session.getAttribute("user_id")+"em";
             val=electionmail;
             obj.put(key, val);
            
        }
        else
        {
             if(voterid.isEmpty() && candidateid.isEmpty() && changepass.isEmpty() && onetimekey.isEmpty()&& resetlogin.isEmpty()&& resetpasswithonetimekey.isEmpty()&& resetpasswithonetimekeyandlink.isEmpty())
            {
                request.setAttribute("msgmail","Sorry Cannot Accept Blank Data");
                return mapping.findForward("fail");
            }
            if(voterid!=null && voterid.isEmpty()==false  || changepass.isEmpty()==false && changepass!=null || onetimekey.isEmpty()==false && onetimekey!=null || resetlogin.isEmpty()==false && resetlogin!=null || resetpasswithonetimekey.isEmpty()==false && resetpasswithonetimekey!=null || resetpasswithonetimekeyandlink.isEmpty()==false && resetpasswithonetimekeyandlink!=null)
            {
                key= (String)session.getAttribute("user_id")+"vm";
                key1= (String)session.getAttribute("user_id")+"voterchpass";
                key2= (String)session.getAttribute("user_id")+"voteronetime";
                key3= (String)session.getAttribute("user_id")+"voterresetlogin";
                key4= (String)session.getAttribute("user_id")+"voterresetpasswithkey";
                key5= (String)session.getAttribute("user_id")+"voterresetpasswithkeyandlink";
                obj.put(key, voterid);
                obj.put(key1, changepass);
                obj.put(key2, onetimekey);
                obj.put(key3, resetlogin);
                obj.put(key4, resetpasswithonetimekey);
                obj.put(key5, resetpasswithonetimekeyandlink);



             
            }
            if(candidateid!=null && candidateid.isEmpty()==false)
            {
                key= (String)session.getAttribute("user_id")+"candi";
                val=candidateid;
                obj.put(key, val);
                
            }

              if(voterid!=null && voterid.isEmpty()==false)
            {
                  System.out.println("Voterrrrrrrrrrrrrrrrrr");
                key= (String)session.getAttribute("user_id")+"vm";
                val=voterid;
                obj.put(key, val);

            }
        }
        UserLog.writeProperty("mail.properties", obj);
}
catch(Exception e)
{
log4j.error(e);
}
request.setAttribute("msgmail","Mail Setting Successfully uploaded");
if(role.startsWith("insti"))
{
 return mapping.findForward("insti-admin");
}
else
{
  return mapping.findForward(SUCCESS);
}
}
}

