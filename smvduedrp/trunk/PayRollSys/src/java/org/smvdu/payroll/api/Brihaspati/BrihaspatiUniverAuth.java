/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.api.Brihaspati;

import java.io.File;
import java.util.ArrayList;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.iitk.brihaspati.modules.utils.security.ReadNWriteInTxt;
import org.iitk.brihaspati.modules.utils.security.RemoteAuth;

/**
 *
 * @author smvdu
 */
public class BrihaspatiUniverAuth {

    /**
     * Creates a new instance of BrihaspatiUniverAuth
     */
    public BrihaspatiUniverAuth() {
    }

    public void brihaspatiAuth(BrihaspatiUniverAuthBean bua) {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
           // String path1 = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
            //System.out.println(path1);
            System.out.println("KLOP : "+request.getContextPath()+"?email="+bua.getEmailId()+"&context="+request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath());
            String paySourceId = "smvdu_payroll";
             //System.out.println("Hello World  : "+skey+"     :     "+serverurl);*/
             String resp = RemoteAuth.AuthR(bua.getEmailId(),request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/PayrollSys/adminLogin/brihaspatiMainLogin.jsf","smvdu_payroll");
             NavigationHandler navh = facesContext.getApplication().getNavigationHandler();
            navh.handleNavigation(facesContext, null, resp);
            System.out.println("Resp : "+resp);
            /*String path = System.getProperty("user.home") + File.separator + "remote_auth" + File.separator + "brihaspati3-remote-access.properties";
            String line = ReadNWriteInTxt.readLin(path, paySourceId);
            String skey = StringUtils.substringBetween(line, ";", ";");
            String serverurl = StringUtils.substringAfterLast(line, ";");
            
            /*String skey=new BrihaspatiProFile().propertieDataSeqKey();
             String serverurl=new BrihaspatiProFile().propertieDataUrl();*/
            
           
            //String login =
            /*System.out.println("Request Url : :  " + request.getRequestURI());
            System.out.println("Context : "+request.getParameter("context")); 
            String rand = request.getParameter("rand");
            String hash = request.getParameter("hash");
            String encd = request.getParameter("encd");
            System.out.println(rand+" : : "+hash+" : : "+encd);
            System.out.println("Resp : "+resp);*/
            //return resp;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
