/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmeict.smvdu.Beans.RemoteAuthentication;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.iitk.brihaspati.modules.utils.security.EncrptDecrpt;
import org.iitk.brihaspati.modules.utils.security.ReadNWriteInTxt;
import org.iitk.brihaspati.modules.utils.security.RemoteAuth;
import org.nmeict.smvdu.Beans.OrgLoginDetails;
import org.nmeict.smvdu.Beans.OrgProfile;
import org.nmeict.smvdu.Beans.db.UserDB;

@ManagedBean (name="verifiedLoginPageBean")
@SessionScoped

/**
 *
 * @author Shaista Bano
 */
public class VerifiedLoginPage {
    public void VerifiedLoginPage(){
       /* String message;
    String homeDir = System.getProperty("user.home");

    String path = homeDir +"/remote_auth/brihaspati3-remote-access.properties";
    String line=ReadNWriteInTxt.readLin(path,"student_fees");
    
    String instSecretKey=StringUtils.substringBetween(line,";",";");
    System.out.println("instSecretKey="+instSecretKey);
    
    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    String rand=request.getParameter("rand");
    String hash=request.getParameter("hash");
    String encd=request.getParameter("encd");

    String enUrl1=EncrptDecrpt.decrypt(encd,"student_fees");
    System.out.println("message in Success screen aa =="+enUrl1);
    //Extract the Email embedded in the return URL
    String email1=StringUtils.substringBetween(enUrl1,"email=","&sess=");
    System.out.println("email="+email1);
    //Extract the Sessionid embedded in the return URL
     String sessno=StringUtils.substringAfter(enUrl1,"&sess=");
     System.out.println("sessno"+sessno);
    String hashcode=EncrptDecrpt.keyedHash(email1, rand, instSecretKey);
    boolean matched=hashcode.equals(hash);

    String filepath=homeDir+"/remote_auth/remote-user.txt";

    HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
    
            //String action = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("action");
    if(matched)    {
        try{ 
            String path1=homeDir+"/remote_auth/remote-user.txt";
            boolean flag=ReadNWriteInTxt.readF(path1,email1+";"+sessno);
            if(flag){

                new OrgLoginDetails().setAdminId(email1);
                 int x = new UserDB().validate(email1);
                  ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                 if(x == 2){
                     
                    context.redirect("AdminLogin.xhtml?faces-redirect=true");
               }
                 if(x == 3){}
            //response.sendRedirect("http://google.com");
               
                context.redirect("MainPage.xhtml?faces-redirect=true");
            }
            else{
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "User does not exist in this application. So Please register first", ""));
                return;
            }
            
        }
        catch (Exception e) {
        message = "Brihaspati Server seems to be unreachable or there is some other problem. Actual exception is: " + e.getMessage();
        //return "ERROR";
         }

    }

    message = "Home Directory " + homeDir + "Path is :" + path + " Line : " + line + " inst Secret Key: " + instSecretKey + "======="
           + " Rand = " + rand + " hash = " + hash + " encd " + encd + " enURL = " +
            enUrl1 + "EMail = " + email1 + " sessno " + sessno + " matched = "  + matched;
    System.out.print("\n\n in LoginFromStudentFees Class message="+message);
    */
    }
    

//public String studentFeeLogin() {
public void studentFeeLogin() {
    String message;
    String homeDir = System.getProperty("user.home");

    String path = homeDir +"/remote_auth/brihaspati3-remote-access.properties";
    String line=ReadNWriteInTxt.readLin(path,"student_fees");
    
    String instSecretKey=StringUtils.substringBetween(line,";",";");
    System.out.println("instSecretKey="+instSecretKey);
    
    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    String rand=request.getParameter("rand");
    String hash=request.getParameter("hash");
    String encd=request.getParameter("encd");
    System.out.print("rand="+rand);
    String enUrl1=EncrptDecrpt.decrypt(encd,"student_fees");
    System.out.println("message in Success screen aa =="+enUrl1);
    //Extract the Email embedded in the return URL
    String email1=StringUtils.substringBetween(enUrl1,"email=","&sess=");
    System.out.println("email="+email1);
    //Extract the Sessionid embedded in the return URL
     String sessno=StringUtils.substringAfter(enUrl1,"&sess=");
     System.out.println("sessno"+sessno);
    String hashcode=EncrptDecrpt.keyedHash(email1, rand, instSecretKey);
    boolean matched=hashcode.equals(hash);

    String filepath=homeDir+"/remote_auth/remote-user.txt";

    HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
    
            //String action = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("action");
    if(matched)    {
        try{ 
            String path1=homeDir+"/remote_auth/remote-user.txt";
            boolean flag=ReadNWriteInTxt.readF(path1,email1+";"+sessno);
            if(flag){

               
                synchronized (this) 
        	{ 
                    new UserDB().validate(email1);
                    
                    //ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                    //context.redirect("../MainPage.xhtml?faces-redirect=true");
                }
            }else{
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "User does not exist in this application. So Please register first", ""));
                //return null;
            }
            
        }
        catch (Exception e) {
        message = "Brihaspati Server seems to be unreachable or there is some other problem. Actual exception is: " + e.getMessage();
        //return "ERROR";
         }

    }
/*
    message = "Home Directory " + homeDir + "Path is :" + path + " Line : " + line + " inst Secret Key: " + instSecretKey + "======="
           + " Rand = " + rand + " hash = " + hash + " encd " + encd + " enURL = " +
            enUrl1 + "EMail = " + email1 + " sessno " + sessno + " matched = "  + matched;
    System.out.print("\n\n in LoginFromStudentFees Class message="+message);
    return "SUCCESS";
    */

}
//public OrgLoginDetails validate(ResultSet rst, int tempInt) {
    public void validateFromdatabase(OrgLoginDetails orgLoginDetail, int tempInt) {
        
        try{
            
            OrgProfile op = new OrgProfile();
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext(); 
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
            System.out.println("path in verification=="+path);
            synchronized (this) {
                
                System.out.println("tempInt=="+tempInt);
                if(tempInt == 2){
                    context.redirect("../AdminLogin.xhtml?faces-redirect=true");
                }
                if(tempInt == 1){
                    String orgId = orgLoginDetail.getOrgId();
                    System.out.println("OrgId="+orgId);
                    File file = new File(path+File.separator+"img"+File.separator+orgId);
                    if(!file.exists()){
                        BufferedImage image = null;
                        image = ImageIO.read(new File(path+File.separator+"img"+File.separator+"Other298.jpg"));
                        file.mkdir();
                        ImageIO.write(image, "jpg",new File(path+File.separator+"img"+File.separator+orgId+File.separator+"out.png"));
                    }
                    
                    op = orgLoginDetail.getiOrgLoginProfile().loadAllDetails(orgId);
                    orgLoginDetail.setOrgProfile(op);
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("org",orgLoginDetail);
                
                    context.redirect("../MainPage.xhtml?faces-redirect=true");
                }
            
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "User does not exist in this application. So Please register first", ""));
            }
        }catch(Exception e){
             //return null;
        }
        //return o;

            
     }
}
