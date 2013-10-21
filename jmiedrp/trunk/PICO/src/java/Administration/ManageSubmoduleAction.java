/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Administration;

import java.util.ArrayList;
import java.util.List;
import pojo.hibernate.Erpmsubmodule;
import pojo.hibernate.ErpmsubmoduleDAO;
import utils.DevelopmentSupport;
import pojo.hibernate.Erpmmodule;
import pojo.hibernate.ErpmmoduleDAO;

/**
 *
 * @author mkhan
 */
public class ManageSubmoduleAction extends DevelopmentSupport{
    
    private String message;
    private Integer erpmSubModuleid;
    private List<Erpmsubmodule> erpmsmList = new ArrayList<Erpmsubmodule>();
    private List<Erpmmodule> erpmmList = new ArrayList<Erpmmodule>();
    private Erpmsubmodule erpmsm = new Erpmsubmodule();
    private Erpmmodule erpmm=new Erpmmodule();
    private ErpmsubmoduleDAO erpmsmDao = new ErpmsubmoduleDAO();
    private ErpmmoduleDAO erpmmDao = new ErpmmoduleDAO();
    
    
    public Erpmmodule getErpmm() {
        return erpmm;
    }

    public void setErpmm(Erpmmodule erpmm) {
        this.erpmm = erpmm;
    }

    public List<Erpmmodule> getErpmmList() {
        return erpmmList;
    }

    public void setErpmmList(List<Erpmmodule> erpmmList) {
        this.erpmmList = erpmmList;
    }

    public List<Erpmsubmodule> getErpmsmList() {
        return erpmsmList;
    }

    public void setErpmsmList(List<Erpmsubmodule> erpmsmList) {
        this.erpmsmList = erpmsmList;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public Integer getErpmSubModuleid() {
        return erpmSubModuleid;
    }

    public void setErpmSubModuleid(Integer erpmSubModuleId) {
        this.erpmSubModuleid = erpmSubModuleId;
    }
    
    public Erpmsubmodule getErpmsm() {
        return erpmsm;
    }

    public void setErpmsm(Erpmsubmodule erpmsm) {
        this.erpmsm = erpmsm;
    }
    
    @Override
    public String execute() throws Exception {        
       try {         
             erpmsmList = erpmsmDao.findAll();
             erpmmList=erpmmDao.findAll();
             return SUCCESS;
           }
           
        catch (Exception e) {
            message = "Exception in Execute method -> ManageSubmoduleAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
      }

 public String Browse() throws Exception {
        try {
            erpmsmList = erpmsmDao.findAll();
            
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Browse method -> ManageSubmoduleAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Save() throws Exception {
          
        try {
            //If part saves record for the first time; else parts is for record update
            if(erpmsm.getErpmSubModuleId()==null){
                erpmsmDao.save(erpmsm);
                erpmsm = new Erpmsubmodule();
                ErpmsubmoduleDAO sm2DAO=new  ErpmsubmoduleDAO();
                erpmsmList=sm2DAO.findAll();
                message = "Program record saved successfully. ";  
             }
             else {
                   erpmsmDao.update(erpmsm);
                   message="Record Updated";
                   erpmsmDao=null;
                   ErpmmoduleDAO erpmmDAO=new  ErpmmoduleDAO();
                   erpmmList=erpmmDAO.findAll();                        
             }
             
             return SUCCESS;
             } catch (Exception e) {
                message = "Exception in Save method -> ManageSubmoduleAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
                return ERROR;
        }
    }
 
  public String Clear() throws Exception {
        try {
            erpmsm=new Erpmsubmodule();
            ErpmmoduleDAO mDAO=new  ErpmmoduleDAO();
            erpmmList=mDAO.findAll();
          
            ErpmsubmoduleDAO smDAO=new  ErpmsubmoduleDAO();
            erpmsmList=smDAO.findAll();
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Clear method -> ManageSubmoduleAxn "  + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }
 
 public String Edit() throws Exception {
        try {
            
            erpmsm=erpmsmDao.findBySubmoduleId(getErpmSubModuleid());
            erpmmList = erpmmDao.findAll();

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Edit method -> ManageSubmodule Axn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }
 
 public String Delete() throws Exception {
        try {
             erpmsmList = erpmsmDao.findAll();
             erpmsm=erpmsmDao.findBySubmoduleId(getErpmSubModuleid());
             erpmsmDao.delete(erpmsm);
             message = "Record deleted successfull";
             erpmsmList = erpmsmDao.findAll();
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Delete method -> ManageSubmoduleAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }
 
}
