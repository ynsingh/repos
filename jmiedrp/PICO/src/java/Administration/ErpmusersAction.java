/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Administration;
/**
 *
 * @author kazim
 */

import pojo.hibernate.Erpmusers;
import pojo.hibernate.Erpmuserrole;
import pojo.hibernate.ErpmusersDAO;
import pojo.hibernate.ErpmuserroleDAO;
import pojo.hibernate.ErpmuserprofileDAO;
import pojo.hibernate.LanguageMaster;
import pojo.hibernate.LanguageMasterDAO;
import utils.DevelopmentSupport;
import java.util.ArrayList;

import java.util.List;


public class ErpmusersAction extends DevelopmentSupport {

    private Erpmusers erpmuser;
    private String message;
    private List<Erpmusers> erpmusersList = new ArrayList<Erpmusers>();
    private ErpmusersDAO erpmusersDao = new ErpmusersDAO();
    private ErpmuserprofileDAO erpmuserprofileDao = new ErpmuserprofileDAO();
    private ErpmuserroleDAO erpmuserroleDAO = new ErpmuserroleDAO();
    private String  erpmu_Name;
   private LanguageMasterDAO LangDao = new LanguageMasterDAO();
private List<LanguageMaster> LangList = new ArrayList<LanguageMaster>();
    public void setMesssge(String message) {
            this.message = message;
    }

     public String getMessage() {
        return message;
    }
 public void setLangList( List<LanguageMaster> LangList) {
            this.LangList = LangList;
    }

     public  List<LanguageMaster> getLangList() {
        return LangList;
    }
    @Override
    public String execute() throws Exception {        
       try {

        List<Erpmusers> list = erpmusersDao.RetrieveUser(erpmuser.getErpmuName(), erpmuser.getErpmuPassword()); //; //erpmuName);
       LangList=LangDao.findAll();
       message="hello"+LangList.get(0).getLangId();
       if (list.size() == 0)
        {
            message = "Either your account is not yet activated or password is incorrect. Pl. contact your administrator";
            list.clear();            
            return ERROR;
           }
        else
        {            
            getSession().setAttribute("userid", list.get(0).getErpmuId());
            getSession().setAttribute("username", list.get(0).getErpmuName());
            getSession().setAttribute("userfullname", list.get(0).getErpmuFullName());
            Erpmuserrole userDefaultRole = erpmuserroleDAO.findDefaultUserRole(list.get(0).getErpmuId());
            userDefaultRole.getInstitutionmaster().getImShortName();
            getSession().setAttribute("imId", userDefaultRole.getInstitutionmaster().getImId());
            getSession().setAttribute("simId", userDefaultRole.getSubinstitutionmaster().getSimId());
            getSession().setAttribute("imshortname", userDefaultRole.getInstitutionmaster().getImShortName());
            getSession().setAttribute("simshortname",userDefaultRole.getSubinstitutionmaster().getSimShortName());
            getSession().setAttribute("dmshortname", userDefaultRole.getDepartmentmaster().getDmShortName());
            getSession().setAttribute("dmId", userDefaultRole.getDepartmentmaster().getDmId());
                        
           
            list.clear();            
           return SUCCESS;
        }

        }
   catch (Exception e)
   {
   message = message + e.getCause() + "  " + e.getMessage();
   return ERROR;
   }
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
}