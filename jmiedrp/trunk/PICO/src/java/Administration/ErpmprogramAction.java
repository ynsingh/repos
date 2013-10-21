/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Administration;

/**
 *
 * @author mkhan
 */

import pojo.hibernate.Erpmsubmodule;
import pojo.hibernate.ErpmsubmoduleDAO;
import pojo.hibernate.Erpmprogram;
import pojo.hibernate.ErpmprogramDAO;
import pojo.hibernate.Genericroleprivileges;
import pojo.hibernate.GenericroleprivilegesDAO;
import pojo.hibernate.Genericuserroles;
import pojo.hibernate.GenericuserrolesDAO;
import pojo.hibernate.ErpmusersDAO;

import utils.DevelopmentSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.struts2.interceptor.validation.SkipValidation;


public class ErpmprogramAction extends DevelopmentSupport {
  
    private String message;
    private Short erpmId;
    private Erpmprogram erpmprogram = new Erpmprogram();
    private ErpmprogramDAO erpmpDao = new ErpmprogramDAO();
    private List<Erpmprogram> erpmList = new ArrayList<Erpmprogram>();
    private ErpmsubmoduleDAO erpmsmDao = new ErpmsubmoduleDAO();
    private Erpmsubmodule erpmsm = new Erpmsubmodule();
    private List<Erpmsubmodule> erpmsmList = new ArrayList<Erpmsubmodule>();
    private Genericroleprivileges genericRolePrivilges = new Genericroleprivileges();
    private GenericroleprivilegesDAO genericRolePrivilgesDao = new GenericroleprivilegesDAO();
    private Genericuserroles genericUserRoles = new Genericuserroles();
    private GenericuserrolesDAO genericUserRolesDao = new GenericuserrolesDAO();
    private ErpmusersDAO erpmusersDao = new ErpmusersDAO();
    private String institutionAdministrator;
    private String purchaseManager;
    private String purchaseStaff;
    
    public void setinstitutionAdministrator(String institutionAdministrator) {
            this.institutionAdministrator = institutionAdministrator;
    }

    public String getinstitutionAdministrator() {
        return institutionAdministrator;
    }
    public void setpurchaseManager(String purchaseManager) {
            this.purchaseManager = purchaseManager;
    }

    public String getpurchaseManager() {
        return purchaseManager;
    }
    
    public void setpurchaseStaff(String purchaseStaff) {
            this.purchaseStaff = purchaseStaff;
    }

    public String getpurchaseStaff() {
        return purchaseStaff;
    }
        
    public void setMesssge(String message) {
            this.message = message;
    }

    public String getMessage() {
        return message;
    }
     
    public void seterpmprogram(Erpmprogram Erpmprogram) {
         this.erpmprogram=erpmprogram;
    }

    public Erpmprogram geterpmprogram() {
         return erpmprogram;
    }

    public void seterpmsm(Erpmsubmodule Erpmsm) {
         this.erpmsm=erpmsm;      
     }

    public Erpmsubmodule geterpmsm() {
         return erpmsm;
    }

    public List<Erpmsubmodule> geterpmsmList() {
        return this.erpmsmList;
    }
     
    public void seterpmsmList(List<Erpmsubmodule> erpmsmList) {
         this.erpmsmList=erpmsmList;
    }

    public List<Erpmprogram> geterpmList() {
        return this.erpmList;
    }
     
    public void seterpmList(List<Erpmprogram> erpmList ) {
         this.erpmList=erpmList;
    }

    public Short getErpmId() {
        return erpmId;
    }

    public void setErpmId(Short erpmId) {
        this.erpmId = erpmId;
    }

    @Override
    public String execute() throws Exception {        
       try {
             erpmsmList = erpmsmDao.findAll();
             erpmList = erpmpDao.findByErpmmId(1);
             return SUCCESS;
        } catch (Exception e) {
                message = "Exception in Excute method -> ErpmProgramAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
                return ERROR;
        }
      }
      
    @SkipValidation  
       public String Browse() throws Exception {
        try {
            erpmList = erpmpDao.findAll();
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Browse method -> ErpmProgramAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }
       
        public int checkOrderAvailability()
        {
           int result=1;
            //short programId;
            message = message + " Now in checkOrderAvailability-1";
            Short s = erpmprogram.getErpmprogram().getErpmpId();
            ErpmprogramDAO erpmprogramDAO = new  ErpmprogramDAO();
            Integer erpmSubmoduleId = erpmprogram.getErpmsubmodule().getErpmSubModuleId();
            message = message + " Now in checkOrderAvailability-2";

            erpmList=erpmprogramDAO.findSecondLevelItemsBySubModuleId(erpmSubmoduleId, s, Integer.parseInt(getSession().getAttribute("userid").toString()));         
            Iterator iterator=erpmList.iterator();
            message = message + " Now in checkOrderAvailability-3";

            while(iterator.hasNext()) {
                int existingOrder,enteredOrder,existingProgramId;
              
                Erpmprogram erpmNext=(Erpmprogram)iterator.next();        
                existingOrder=erpmNext.getErpmpOrder();
                enteredOrder=erpmprogram.getErpmpOrder();

                if(existingOrder==enteredOrder) {
                    result=0;
                    message=message+"Order Matched";
              }
          }
          message=message+"Entered order"+erpmprogram.getErpmpOrder().toString()+"result"+Integer.valueOf(result).toString();          
          return result;
        }
            
       public String Save() throws Exception {         
        try {
            //If part saves record for the first time; else parts is for record update
            if(erpmprogram.getErpmpId()==null) {
                message = message + " Now in save";
                   //if(checkOrderAvailability()==1) {
                        erpmpDao.save(erpmprogram);
                        insertGenericPrivileges();
                        erpmprogram = new Erpmprogram();
                        ErpmsubmoduleDAO sm2DAO=new  ErpmsubmoduleDAO();
                        erpmsmList=sm2DAO.findAll();
                        ErpmprogramDAO sp2DAO=new  ErpmprogramDAO();
                        erpmList=sp2DAO.findAll();
                        
                        message = "Program record saved successfully. ";  
                   //}
                   //else {
                   //    message += "Order already exist.Please Enter another order. ";
                   //}
                   
               }
               else {
                message = message + " Now in update";
                        Erpmprogram erpmTemp = new Erpmprogram();
                        erpmTemp = erpmpDao.findByErpmId(erpmprogram.getErpmpId());
                        erpmTemp = erpmprogram;
                        if (erpmprogram.getErpmprogram().getErpmpId()==0)
                            erpmTemp.setErpmprogram(null);
                        erpmpDao.update(erpmTemp);
                        
                        message = "Record updated successfully.";                        
                    }
               return SUCCESS;
               } catch (Exception e) {
                    message =message +   "Exception in Save method -> ErpmProgramAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
                    return ERROR;
                }
    }

    //This method stores Generic Privileges for the Program created by the User   
    public void insertGenericPrivileges() {
        
        //Check, if Institution Administrator privilege has been set
        if(institutionAdministrator.compareTo("true") == 0) {
            //Set Privileges for "Generic Administrator
            genericUserRoles = genericUserRolesDao.findByName("Administrator");
            //Check, if Instititution Administrator is not already added in the Generic Privileges for the program
            if (!genericRolePrivilgesDao.CheckGenericPrivileges(erpmprogram.getErpmpId(),genericUserRoles.getGurId()))
                setGenericPrivileges();
        }
        
        //Check, if Purchase Manager privilege has been set
        if(purchaseManager.compareTo("true") == 0) {
            //Set Privileges for "Generic Purchase Manager
            genericUserRoles = genericUserRolesDao.findByName("Purchase Manager");
            setGenericPrivileges();
        }

        //Check, if Purchase Staff privilege has been set              
        if(purchaseStaff.compareTo("true") == 0) {
            //Set Privileges for "Generic Purchase Staff
            genericUserRoles = genericUserRolesDao.findByName("Purchase Staff");
            setGenericPrivileges();
        }
        return ;
    }
    
    private void setGenericPrivileges() {
            erpmprogram = erpmpDao.findByErpmId(erpmprogram.getErpmpId());
            genericRolePrivilges.setErpmprogram(erpmprogram);
            genericRolePrivilges.setErpmmodule(erpmprogram.getErpmsubmodule().getErpmmodule());
            genericRolePrivilges.setErpmsubmodule(erpmprogram.getErpmsubmodule());
            genericRolePrivilges.setGenericuserroles(genericUserRoles);
            genericRolePrivilges.setGupCanAdd("1");
            genericRolePrivilges.setGupCanDelete("1");
            genericRolePrivilges.setGupCanView("1");
            genericRolePrivilges.setGupCanEdit("1");            
            genericRolePrivilgesDao.save(genericRolePrivilges);            
    }


    public void validate() {
        try {
                if(erpmprogram.getErpmpId() == null) {
                    if(erpmprogram.getErpmpDisplayName().isEmpty())
                        addFieldError("erpmprogram.erpmpDisplayName" ,"Please give Program's name");
                    if(erpmprogram.getErpmsubmodule().getErpmSubModuleId() == null)
                    {
                        erpmsmList = new ArrayList<Erpmsubmodule>();
                        ErpmsubmoduleDAO sm3DAO=new  ErpmsubmoduleDAO();
                        erpmsmList=sm3DAO.findAll();
                        addFieldError("erpmprogram.erpmsubmodule.erpmSubModuleId" ,"Please set Submodule's type");
                    }
                    if(erpmprogram.getErpmpHref().isEmpty())
                        addFieldError("erpmprogram.erpmpHref" ,"Please give Program's Action Name");
                    if(erpmprogram.getErpmpOrder()==null)
                        addFieldError("erpmprogram.erpmpOrder" ,"Please give Order Number");
                    message = "Here-1";
                    if(erpmprogram.getErpmprogram().getErpmpId() == 0)
                    {
                       /* erpmList = new ArrayList<Erpmprogram>();
                        ErpmprogramDAO sp3DAO=new  ErpmprogramDAO();
                        erpmList=sp3DAO.findAll();
                        addFieldError("erpmprogram.erpmprogram.erpmpId" ,"Please select Sub Program");
                        */
                        message = message + "Here-2";
                        erpmprogram.setErpmprogram(null);
                        message = message + "Here-3";
                    }
                    
                    //Prepare LOVs
                    prepare_lovs();
            }

           }
               catch (NullPointerException e) {}
    }
    
    private void prepare_lovs() {

            //Prepare Program List
            erpmList=erpmpDao.findAll();
            //Prepare Submodule List
            erpmsmList = erpmsmDao.findAll();
    }

    public String Delete() throws Exception {
        try {
            
            erpmList = erpmpDao.findAll();
            erpmsmList = erpmsmDao.findAll();
            erpmprogram=erpmpDao.findByErpmId(getErpmId());
            erpmpDao.delete(erpmprogram);
            message = "Record deleted successfull";
            erpmList = erpmpDao.findAll();
            erpmsmList = erpmsmDao.findAll();
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Delete method -> ErpmProgramAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }
    
    public String Edit() throws Exception {
        try {
                erpmprogram=erpmpDao.findByErpmId(getErpmId());
                erpmList = erpmpDao.findAll();
                erpmsmList = erpmsmDao.findAll();
                           
                return SUCCESS;
        } catch (Exception e) {
                message = "Exception in Edit method -> ErpmProgramAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
                return ERROR;
        }
    }

    @SkipValidation
    public String Clear() throws Exception {
        try {
            erpmprogram=new Erpmprogram();
            ErpmsubmoduleDAO sm2DAO=new  ErpmsubmoduleDAO();
            erpmsmList=sm2DAO.findAll();
            ErpmprogramDAO sp2DAO=new  ErpmprogramDAO();
            erpmList=sp2DAO.findAll();
            return SUCCESS;
        } catch (Exception e) {
                message = "Exception in Clear Method -> ErpmProgramAxn "  + e.getMessage() + " Reported Cause is: " + e.getCause();
                return ERROR;
        }
    }
}