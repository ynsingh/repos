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
import pojo.hibernate.Erpmsubmodule;
import pojo.hibernate.ErpmsubmoduleDAO;
import pojo.hibernate.Erpmprogram;
import pojo.hibernate.ErpmprogramDAO;
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

            message = "Welcome to Purchase & Inventory Control System";

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
            //Prepare List of Top Menu Items
            erpmsmList = erpmsmDao.findByModuleId(Byte.parseByte("1"));
            int i;
            String menuName, menuHref;
           // message = message + " Module id = " + erpmsmList.get(1).getErpmSubModuleId() + " User Id = " + list.get(0).getErpmuId();
            for(i=0; i < erpmsmList.size(); ++i) {
                erpmpFirstLevelList = erpmpDao.findFirstLevelItemsBySubModuleId(erpmsmList.get(i).getErpmSubModuleId(),list.get(0).getErpmuId());
                //message = message + " i= " + i + "Value returned = " + erpmpFirstLevelList.size();
                if (erpmpFirstLevelList.size() == 0) {
                     menuName = "Menu[" + i + "][0][0]";
                     menuHref = "MenuHref["+ i + "][0][0]";
                     
                     getSession().setAttribute(menuName,erpmsmList.get(i).getEsmName());
                     getSession().setAttribute(menuHref,erpmsmList.get(i).getEsmHref());
                     
                     //message = message + menuName + erpmsmList.get(i).getEsmName();

                    }
                else {
                        menuName = "Menu[" + i + "][0][0]";
                        menuHref = "MenuHref["+ i + "][0][0]";
                        getSession().setAttribute(menuName,erpmsmList.get(i).getEsmName());
                        getSession().setAttribute(menuHref,erpmsmList.get(i).getEsmHref());
                        //message = message + menuName + erpmsmList.get(i).getEsmName();
                    
                        for (int j = 0; j < erpmpFirstLevelList.size(); ++j) {
                        erpmpSecondLevelList = erpmpDao.findSecondLevelItemsBySubModuleId(erpmsmList.get(i).getErpmSubModuleId(), erpmpFirstLevelList.get(j).getErpmpId());
                       // message = message + "SUB MODULE ID = " + erpmsmList.get(i).getErpmSubModuleId() + "PROGRAM ID = " + erpmpFirstLevelList.get(i).getErpmpId() + "II LEVEL LIST SiZE " + erpmpSecondLevelList.size();
                        int jIncremeneted = j + 1;
                        if (erpmpSecondLevelList.size() == 0) {
                            menuName = "Menu[" + i + "][" + jIncremeneted + "][0]";
                            menuHref = "MenuHref["+ i + "][" + jIncremeneted + "][0]";
                            getSession().setAttribute(menuName,erpmpFirstLevelList.get(j).getErpmpDisplayName());
                            getSession().setAttribute(menuHref,erpmpFirstLevelList.get(j).getErpmpHref());
                         //   message = message +  " ++ " + menuName + erpmpFirstLevelList.get(j).getErpmpDisplayName() + " ++ " ;
                        }
                        else {
                              menuName = "Menu[" + i + "][" + jIncremeneted + "][0]";
                              menuHref = "MenuHref["+ i + "][" + jIncremeneted + "][0]";
                              getSession().setAttribute(menuName,erpmpFirstLevelList.get(j).getErpmpDisplayName());
                              getSession().setAttribute(menuHref,erpmpFirstLevelList.get(j).getErpmpHref());
                           //   message = message + menuName + erpmpFirstLevelList.get(j).getErpmpDisplayName();

                            for (int k = 0; k < erpmpSecondLevelList.size(); ++k) {
                            int kIncremeneted = k +1 ;
                            menuName = "Menu[" + i +"][" + jIncremeneted + "][" + kIncremeneted + "]";
                            menuHref = "MenuHref["+ i + "][" + jIncremeneted + "][" + kIncremeneted + "]";
                            getSession().setAttribute(menuName,erpmpSecondLevelList.get(k).getErpmpDisplayName());
                            getSession().setAttribute(menuHref,erpmpSecondLevelList.get(k).getErpmpHref());
                          // message = message +  " == " + menuName + "Link = " + erpmpSecondLevelList.get(k).getErpmpHref() + "Link Finishes " + erpmpSecondLevelList.get(k).getErpmpDisplayName() + " == ";

                        } //End For k Loop
                        } //End Else
                    }   //End For j Loop
                        
                } //End Else
                } //End for i loop
           } //End Else
            list.clear();
           return SUCCESS;
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