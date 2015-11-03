/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Administration;
/**
 *
 * @author kazim
 * @author <a href="mailto:jaivirpal@gmail.com">Jaivir Singh</a>2015
 */
import com.opensymphony.xwork2.ActionContext;
import pojo.hibernate.Erpmusers;
import pojo.hibernate.Erpmuserrole;
import pojo.hibernate.ErpmusersDAO;
import pojo.hibernate.ErpmuserroleDAO;
import pojo.hibernate.ErpmuserprofileDAO;
import pojo.hibernate.Erpmsubmodule;
import pojo.hibernate.ErpmsubmoduleDAO;
import pojo.hibernate.Erpmprogram;
import pojo.hibernate.ErpmprogramDAO;
import pojo.hibernate.LanguageMaster;
import pojo.hibernate.LanguageMasterDAO;
import pojo.hibernate.UserMessageDAO;

import org.apache.struts2.ServletActionContext;
import javax.servlet.http.*;

import javax.servlet.http.*;
import java.io.IOException;

import utils.DevelopmentSupport;
import java.util.ArrayList;

import org.iitk.brihaspati.modules.utils.security.EncrptDecrpt;
import org.iitk.brihaspati.modules.utils.security.ReadNWriteInTxt;
import org.iitk.brihaspati.modules.utils.security.RemoteAuth;


import java.util.List;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.iitk.brihaspati.modules.utils.security.RemoteAuth;
import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;

import pojo.hibernate.Edrpusers;
import pojo.hibernate.EdrpusersDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Locale;
import java.util.ResourceBundle;


public class ErpmusersAction extends DevelopmentSupport {

    private Erpmusers erpmuser;
    private String message;
    private Integer jobs;
    private List<Erpmusers> erpmusersList = new ArrayList<Erpmusers>();
    private ErpmusersDAO erpmusersDao = new ErpmusersDAO();
    private ErpmprogramDAO erpmpDao = new ErpmprogramDAO();
    private List<Erpmprogram> erpmpFirstLevelList = new ArrayList<Erpmprogram>();
    private List<Erpmprogram> erpmpSecondLevelList = new ArrayList<Erpmprogram>();
    private ErpmsubmoduleDAO erpmsmDao = new ErpmsubmoduleDAO();    
    private List<Erpmsubmodule> erpmsmList = new ArrayList<Erpmsubmodule>();
    private ErpmuserprofileDAO erpmuserprofileDao = new ErpmuserprofileDAO();
    private ErpmuserroleDAO erpmuserroleDAO = new ErpmuserroleDAO();
    private String  erpmu_Name;
    private LanguageMasterDAO LangDao = new LanguageMasterDAO();
    private List<LanguageMaster> LangList = new ArrayList<LanguageMaster>();
    private List<Institutionmaster> imIdList = new ArrayList<Institutionmaster>();
    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();

private Edrpusers edrpuser;
    private List<Edrpusers> edrpusersList = new ArrayList<Edrpusers>();
    private EdrpusersDAO edrpusersDao = new EdrpusersDAO();

    public void setMesssge(String message) {
            this.message = message;
    }

     public String getMessage() {
        return message;
    }

    public void setjobs(Integer jobs) {
            this.jobs = jobs;
    }

     public Integer getjobs() {
        return jobs;
    }


     public void setLangList( List<LanguageMaster> LangList) {
            this.LangList = LangList;
    }

     public  List<LanguageMaster> getLangList() {
        return LangList;
    }
    public Erpmusers getErpmuser() {
        return erpmuser;
    }
    public void setErpmuser(Erpmusers erpmuser) {
        this.erpmuser = erpmuser;
    }

    public String getErpmu_Name() {
        return erpmu_Name;
    }
    public void setErpmu_Name(String erpmu_Name) {
        this.erpmu_Name = erpmu_Name;
    }

    public List<Erpmusers> geterpmusersList() {
        return erpmusersList;
    }

    public void seterpmusersList(List<Erpmusers> erpmusersList) {
        this.erpmusersList = erpmusersList;
    }
   public void setimIdList(List<Institutionmaster> imIdList) {
        this.imIdList = imIdList;
    }

    public List<Institutionmaster> getimIdList() {
        return this.imIdList;
    }

public Edrpusers getEdrpuser() {
        return edrpuser;
    }
    public void setEdrpuser(Edrpusers edrpuser) {
        this.edrpuser = edrpuser;
    }

     @Override


    public String execute() throws Exception {        
       try {
            List<Erpmusers> list = erpmusersDao.RetrieveUser(erpmuser.getErpmuName(), erpmuser.getErpmuPassword()); //; //erpmuName);
		List<Edrpusers> listedrp = edrpusersDao.RetrieveUser(erpmuser.getErpmuName(),erpmuser.getErpmuPassword());
		//message="testing in erpmuseraction"+"list--"+list.size()+"--listedrp--"+listedrp.size();	
            
            LangList=LangDao.findAll();
            if ((list.size() == 0)||(listedrp.size()== 0))
            {
                message = "Either your account is not yet activated or password is incorrect. Pl. contact your administrator";
                list.clear();
                return ERROR;
           }
            else
            {
		message=message+"else partlisterpmuser=="+list.size();	
                
                Erpmuserrole userDefaultRole = erpmuserroleDAO.findDefaultUserRole(list.get(0).getErpmuId());                
                getSession().setAttribute("isAdministrator", erpmuserroleDAO.isUserAdministrator(list.get(0).getErpmuId()));

                getSession().setAttribute("userid", list.get(0).getErpmuId());
                getSession().setAttribute("username", list.get(0).getErpmuName());
                getSession().setAttribute("userfullname", list.get(0).getErpmuFullName());
                getSession().setAttribute("imId", userDefaultRole.getInstitutionmaster().getImId());
                getSession().setAttribute("simId", userDefaultRole.getSubinstitutionmaster().getSimId());
                getSession().setAttribute("dmId", userDefaultRole.getDepartmentmaster().getDmId());

                //Prepare List of Top Menu Items
                erpmsmList = erpmsmDao.findByModuleId(Byte.parseByte("1"));

  
                int i;
                String menuName, menuHref;
                
                // message = message + " Module id = " + erpmsmList.get(1).getErpmSubModuleId() + " User Id = " + list.get(0).getErpmuId() + "i = " + erpmsmList.size();
                for(i=0; i < erpmsmList.size(); ++i) {
                    erpmpFirstLevelList = erpmpDao.findFirstLevelItemsBySubModuleId(erpmsmList.get(i).getErpmSubModuleId(),list.get(0).getErpmuId());
                    //message = message + " i= " + i + "Value returned = " + erpmpFirstLevelList.size();
                    if (erpmpFirstLevelList.size() == 0) {
                        menuName = "Menu[" + i + "][0][0]";
                        menuHref = "MenuHref["+ i + "][0][0]";
                     if("hi".equals(ActionContext.getContext().getLocale().toString()))
                         getSession().setAttribute(menuName,erpmsmList.get(i).getEsmNameHindi());
                     else
                        getSession().setAttribute(menuName,erpmsmList.get(i).getEsmName());
                        getSession().setAttribute(menuHref,erpmsmList.get(i).getEsmHref());
                     
                    // message = message + menuName + erpmsmList.get(i).getEsmName();

                    }
                else {
                        menuName = "Menu[" + i + "][0][0]";
                        menuHref = "MenuHref["+ i + "][0][0]";
                        if("hi".equals(ActionContext.getContext().getLocale().toString()))
                         getSession().setAttribute(menuName,erpmsmList.get(i).getEsmNameHindi());
                     else
                        getSession().setAttribute(menuName,erpmsmList.get(i).getEsmName());
                        getSession().setAttribute(menuHref,erpmsmList.get(i).getEsmHref());
                        //message = message + menuName + erpmsmList.get(i).getEsmName();
                    
                        for (int j = 0; j < erpmpFirstLevelList.size(); ++j) {
                        erpmpSecondLevelList = erpmpDao.findSecondLevelItemsBySubModuleId(erpmsmList.get(i).getErpmSubModuleId(), erpmpFirstLevelList.get(j).getErpmpId(), list.get(0).getErpmuId());
                        //message = message + "SUB MODULE ID = " + erpmsmList.get(i).getErpmSubModuleId() + "PROGRAM ID = " + erpmpFirstLevelList.get(i).getErpmpId() + "II LEVEL LIST SiZE " + erpmpSecondLevelList.size();
                        int jIncremeneted = j + 1;
                        if (erpmpSecondLevelList.size() == 0) {
                            menuName = "Menu[" + i + "][" + jIncremeneted + "][0]";
                            menuHref = "MenuHref["+ i + "][" + jIncremeneted + "][0]";
                          if("hi".equals(ActionContext.getContext().getLocale().toString()))
                              getSession().setAttribute(menuName,erpmpFirstLevelList.get(j).getErpmpDisplayNameHindi());
                          else
                            getSession().setAttribute(menuName,erpmpFirstLevelList.get(j).getErpmpDisplayName());
                            getSession().setAttribute(menuHref,erpmpFirstLevelList.get(j).getErpmpHref());
                           // message = message +  " ++ " + menuName + erpmpFirstLevelList.get(j).getErpmpDisplayName() + " ++ " ;
                        }
                        else {
                              menuName = "Menu[" + i + "][" + jIncremeneted + "][0]";
                              menuHref = "MenuHref["+ i + "][" + jIncremeneted + "][0]";
                              if("hi".equals(ActionContext.getContext().getLocale().toString()))
                                  getSession().setAttribute(menuName,erpmpFirstLevelList.get(j).getErpmpDisplayNameHindi());
                              else
                              getSession().setAttribute(menuName,erpmpFirstLevelList.get(j).getErpmpDisplayName());
                              getSession().setAttribute(menuHref,erpmpFirstLevelList.get(j).getErpmpHref());
//                              message = message + menuName + erpmpFirstLevelList.get(j).getErpmpDisplayName();

                            for (int k = 0; k < erpmpSecondLevelList.size(); ++k) {
                            int kIncremeneted = k +1 ;
                            menuName = "Menu[" + i +"][" + jIncremeneted + "][" + kIncremeneted + "]";
                            menuHref = "MenuHref["+ i + "][" + jIncremeneted + "][" + kIncremeneted + "]";
                            if("hi".equals(ActionContext.getContext().getLocale().toString()))
                            getSession().setAttribute(menuName,erpmpSecondLevelList.get(k).getErpmpDisplayNameHindi());
                            else
                            getSession().setAttribute(menuName,erpmpSecondLevelList.get(k).getErpmpDisplayName());
                            getSession().setAttribute(menuHref,erpmpSecondLevelList.get(k).getErpmpHref());
                           //message = message +  " == " + menuName + "Link = " + erpmpSecondLevelList.get(k).getErpmpHref() + "Link Finishes " + erpmpSecondLevelList.get(k).getErpmpDisplayName() + " == ";

                           } //End For k Loop
                        } //End Else
			imIdList = imDao.findAll();
                    	}   //End For j Loop
                        
                } //End Else
                } //End for i loop
            jobs = addUserJobs(); 
           } //End Else 
	
            list.clear();
           return SUCCESS;
        }
        catch (Exception e) {
            message = message + e.getCause() + "  " + e.getMessage() + Integer.parseInt(getSession().getAttribute("userid").toString());
	return ERROR;
        }
    }
public Integer addUserJobs() {
    
    UserMessageDAO userMessageDao = new UserMessageDAO();

    return userMessageDao.countUserMessages(Integer.parseInt(getSession().getAttribute("userid").toString()));
}

@SkipValidation
public String brihaspatiLogin() {
   
    HttpServletRequest request =  ServletActionContext.getRequest();

    String email = erpmuser.getErpmuName();
    String returnurl = request.getRequestURL().toString(); //"http://14.139.62.116/pico/Administration/Login.action";
    returnurl = returnurl.substring(0, returnurl.indexOf("Login.action")) + "verifyBrihaspatiLogin";
    String sourceid = "jmi_pico";

    String resp = RemoteAuth.AuthR(email, returnurl, sourceid);
    HttpServletResponse response = ServletActionContext.getResponse();

    try{
        response.sendRedirect(resp);
    }
    catch (Exception e) {
        message = "Brihaspati Server seems to be unreachable or there is some other problem. Actual exception is: " + e.getMessage();
        return ERROR;
    }

    message = email + resp + "===" + returnurl;

    return SUCCESS;
}

@SkipValidation
public String verifyBrihaspatiLogin() {

    String homeDir = System.getProperty("user.home");

    String path = homeDir +"\\remote_auth\\brihaspati3-remote-access.properties";
    String line=ReadNWriteInTxt.readLin(path,"jmi_pico");
    Integer startIndex=line.indexOf(";");
    Integer endIndex=line.lastIndexOf(";");
    String jmiKey = line.substring(startIndex + 1, endIndex);


    HttpServletRequest request = ServletActionContext.getRequest();
    String rand=request.getParameter("rand");
    String hash=request.getParameter("hash");
    String encd=request.getParameter("encd");

    String enUrl1=EncrptDecrpt.decrypt(encd,"jmi_pico");
    System.out.println("message in Success screen aa =="+enUrl1);

    //Extract the Email embedded in the return URL
    Integer startEMail=enUrl1.indexOf("email=") + 6;
    Integer endMail = enUrl1.indexOf("&sess=");
    String email1 = enUrl1.substring(startEMail, endMail);

    //Extract the Sessionid embedded in the return URL
    String sessno = enUrl1.substring(endMail+6);


    String hashcode=EncrptDecrpt.keyedHash(email1, rand, jmiKey);
    boolean matched=hashcode.equals(hash);

    String filepath=homeDir+"/remote_auth/remote-user.txt";

    HttpServletResponse response = ServletActionContext.getResponse();

    if(matched)    {
        try{
            response.sendRedirect("http://google.com");
        }
        catch (Exception e) {
        message = "Brihaspati Server seems to be unreachable or there is some other problem. Actual exception is: " + e.getMessage();
        return ERROR;
        }
    }
    else {
        try{
                response.sendRedirect("http://gmail.com");
        }
        catch (Exception e) {
        message = "Brihaspati Server seems to be unreachable or there is some other problem. Actual exception is: " + e.getMessage();
        return ERROR;
        }

    }

    message = "Home Directory " + homeDir + "Path is :" + path + " Line : " + line + " JMI Key: " + jmiKey + "======="
           + " Rand = " + rand + " hash = " + hash + " encd " + encd + " enURL = " +
            enUrl1 + "EMail = " + email1 + " sessno " + sessno + " matched = "  + matched;
    return ERROR;
    
}
public String Connectivity() throws Exception {
                try{
                        Locale locale = ActionContext.getContext().getLocale();
                        ResourceBundle bundle = ResourceBundle.getBundle("dao", locale);
                        String  dburl=bundle.getString("pico.jdbc.url");
                        String  mysqluname=bundle.getString("pico.jdbc.username");
                        String  mysqlupsswd=bundle.getString("pico.jdbc.password");
                        Connection conn=DriverManager.getConnection(dburl, mysqluname, mysqlupsswd);
                        Statement stmt=conn.createStatement();
                        ResultSet rs=stmt.executeQuery("select * from erpmusers");
                        while(rs.next())
                        message=rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3);
                        conn.close();
                        return SUCCESS;
                }
                catch(Exception e){
                        message="error in connectivity method";
                        return ERROR;
                }
    }


}
