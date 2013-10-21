/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Administration;
import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;

import pojo.hibernate.Institutionuserroles;
import pojo.hibernate.InstitutionuserroleDAO;

import pojo.hibernate.Genericuserroles;
import pojo.hibernate.GenericuserrolesDAO;

import pojo.hibernate.Institutionroleprivileges;
import pojo.hibernate.InstitutionroleprivilegesDAO;

import pojo.hibernate.Erpmmodule;
import pojo.hibernate.ErpmmoduleDAO;

import pojo.hibernate.Erpmprogram;
import pojo.hibernate.ErpmprogramDAO;

import utils.DevelopmentSupport;

import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.interceptor.validation.SkipValidation;


//This class provides features to manages the following activities:
//1.    To let user create Institutional Roles for the selected Institution
//2.    To let user edit Institutional Roles for the selected Institution
//3.    To let user delete Institutional Roles for the selected Institution
//4.    To let user initialize the Institutional roles for the selected Institution with Generic system Roles
//5.    To let user add additional programs to the selected Institutional role
//6.    To let user delete(revoke) a programs to the selected Institutional role
//7.    To let user customize the access privilegs for the programs in the selected Institutional role

/**
 *
 * @author erp05
 */
public class ManageInstitutionUserRole extends DevelopmentSupport {
    private List<Institutionmaster> imList = new ArrayList<Institutionmaster>();
    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();
    private Institutionuserroles iur, iur2;
    private InstitutionuserroleDAO iurDao = new InstitutionuserroleDAO();
    private List<Institutionuserroles> iurList = new ArrayList<Institutionuserroles>();
    private List<Genericuserroles> gurList = new ArrayList<Genericuserroles>();

    private Institutionroleprivileges irp;
    private List<Institutionroleprivileges> irpList = new ArrayList<Institutionroleprivileges>();
    private InstitutionroleprivilegesDAO irpDao = new InstitutionroleprivilegesDAO();

    private ErpmmoduleDAO erpmmDao = new  ErpmmoduleDAO();
    private List<Erpmmodule> erpmmList = new ArrayList<Erpmmodule>();

    private ErpmprogramDAO erpmpDao = new  ErpmprogramDAO();
    private List<Erpmprogram> erpmpList = new ArrayList<Erpmprogram>();


    private String message, message2;
    Integer IurId, InstitutionRole;
    Byte gurId;
    Short IupId, ImId;

    public void setIur(Institutionuserroles iur) {
        this.iur = iur;
    }

    public Institutionuserroles getIur() {
        return this.iur;
    }

    public void setInstitutionRole(Integer InstitutionRole) {
        this.InstitutionRole = InstitutionRole;
    }

    public Integer getInstitutionRole() {
        return this.InstitutionRole;
    }

    public void setimList(List<Institutionmaster> imList) {
        this.imList = imList;
    }

    public List<Institutionmaster>  getimList() {
        return this.imList;
    }

    public void setIurId(Integer IurId) {
        this.IurId = IurId;
    }

    public Integer getIurId() {
        return this.IurId;
    }

    public void setImId(Short ImId) {
        this.ImId = ImId;
    }

    public Short getImId() {
        return this.ImId;
    }

    public void setIupId(Short IupId) {
        this.IupId = IupId;
    }

    public Short getIupId() {
        return this.IupId;
    }

    public void setgurList(List<Genericuserroles> gurList) {
        this.gurList = gurList;
    }

    public List<Genericuserroles>  getgurList() {
        return this.gurList;
    }

    public void setirpList(List<Institutionroleprivileges> irpList) {
        this.irpList = irpList;
    }

    public List<Institutionroleprivileges>  getirpList() {
        return this.irpList;
    }

     public void setIrp(Institutionroleprivileges irp) {
        this.irp = irp;
    }

    public Institutionroleprivileges getIrp() {
        return this.irp;
    }

  public void seterpmmList(List<Erpmmodule> erpmmList) {
        this.erpmmList = erpmmList;
    }

    public List<Erpmmodule>  geterpmmList() {
        return this.erpmmList;
    }

  public void seterpmpList(List<Erpmprogram> erpmpList) {
        this.erpmpList = erpmpList;
    }

    public List<Erpmprogram>  geterpmpList() {
        return this.erpmpList;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage2(String message2) {
        this.message2 = message2;
    }

    public String getMessage2() {
        return this.message2;
    }

    public void setgurId(Byte gurId) {
        this.gurId = gurId;
    }

    public Byte getgurId() {
        return this.gurId;
    }

    public void setiurList(List<Institutionuserroles> iurList) {
        this.iurList = iurList;
    }

    public List<Institutionuserroles> getiurList() {
        return this.iurList;
    }

    @Override
    public String execute() throws Exception {
        try {
            //Prepare a list of institutions under perview of the current user
            imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in -> ManageInstitutionRoleAxn "  + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR; 
        }
    }



    //This method saves the Institution Role while adding a new role and also while editing the existing role

    public String Save() throws Exception {
        try {
            //Check to find if the record is being edited or a new record is being created
            if(iur.getIurId() == null) {
                //New Institutional Record is being created; Save it
                iurDao.save(iur);
            } else {
                //The Institutional Role record contained in iur has been modified
                //Update the Institutional Role record
                Institutionuserroles iur2 = iurDao.findByIURId(iur.getIurId());
                iur2 = iur;
                iurDao.update(iur2);

                //Prepare list for showing Institution User Role(s) for the selected institution
                iurList = iurDao.findByInstitutionId(iur.getInstitutionmaster().getImId());
          }

            //Prepare a list of institutions under perview of the current user
           imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
           iur = null;

           return SUCCESS;

        }

        catch (Exception e) {
             if (e.getCause().toString().contains("Duplicate entry"))
                message = "Institution with the name '" + iur.getIurName() + "' already exists";
            else if(e.getCause().toString().contains("UNIQUE_IUR_Name_IUR_IM_ID"))
                message = "The role you are trying to create already exists";
            else
                message = "Exception in Save method -> ManageInstitutionRoleAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
            return ERROR;
        }
    }



    //This method retrieves the existing Institutional User Roles for the selected Institution
    public String FetchIUR() throws Exception {
        try {
            //Prepare list for showing Institution User Role(s) for the selected institution
            iurList = iurDao.findByInstitutionId(iur.getInstitutionmaster().getImId());

            //Prepare a list of institutions under perview of the current user
            imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));

            return SUCCESS;

        } catch (Exception e) {
            message = "Exception in FetchIUR method -> ManageInstitutionRoleAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }


    //This method deletes the desired Institutional User Role for the selected Institution
    public String DeleteIUR() throws Exception {
        try {

            //Find the Institutional user Role record to be deleted
           iur = iurDao.findByIURId(getIurId());

           //Prepare a list of institutions under perview of the current user
           imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));

           //Delete the Institutional user Role record
           iurDao.delete(iur);

           //Recreate the  list for showing Institution User Role(s) for the selected institution
           iurList = iurDao.findByInstitutionId(iur.getInstitutionmaster().getImId());
           iur=null;

           return SUCCESS;

        } catch (Exception e) {
            if (e.getCause().toString().contains("FK_ERPMUR_IUR_ID"))
                message = "This role is already assigned to users. Cannot delete";
            else
                message = "Exception in DeleteIUR method -> ManageInstitutionRoleAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    //This method sets up editing for the desired Institutional User Role for the selected Institution
    public String EditIUR() throws Exception {
        try {

            //Retreive the Institutional user Role record to be edited
            iur = iurDao.findByIURId(getIurId());

            //Prepare a list of institutions under perview of the current user
            imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));

            //Call JSP for letting user make changes
            return SUCCESS;

        } catch (Exception e) {
                message = "Exception in EditIUR method -> ManageInstitutionRoleAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }



    //This method sets up process for initializing the user role from generic system roles
    //For example: A role "Jamia Purchase Manager" created by the JMI's administrator may
    //be initialized with the privileges as defined in the generic system role of "Purchase Manager"

    public String InitializeIURP() throws Exception {
        try {

            //Retrieve the Institution User Role record which is to be setup e.g "Clerk"
            iur = iurDao.findByIURId(getIurId());

            //Create Generic Role List from the generic system roles
            GenericuserrolesDAO gurDao = new GenericuserrolesDAO();
            gurList = gurDao.findAll();

            return SUCCESS;

        } catch (Exception e) {
                message = "Exception in InitializeIURP method -> ManageInstitutionRoleAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }



    //This method sets up process for letting user edit access rights of asigned programs and adding
    //additional programs to the selected Institution User Role

    public String AddEditIURP() throws Exception {
        try {

            //Retrieve the Institution User Role recod which is to be setup e.g "Clerk"
            iur = iurDao.findByIURId(getIurId());

            //Prepare List of privileges assigned to the selected role
            irpList = irpDao.findByiurId(iur.getIurId());

            //Prepare message for the screen telling user the current status
            message2 = "You are now viewing privileges for the role '" + iurDao.findByIURId(iur.getIurId()).getIurName() +
                        "' in '" + iurDao.findByIURId(iur.getIurId()).getInstitutionmaster().getImName() + "'" ;

            return SUCCESS;

        } catch (Exception e) {
                message = "Exception in AddEditIURP method -> ManageInstitutionRoleAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    //This method initialize the Institution role privileges using a genric role selected by the user

    public String CreateIURP() throws Exception {
        try {

            //Count the number of existing privileges for the Institution Role being Initialized
            Integer InitialCount = irpDao.CountRecordsForInstitutionRole(iur.getIurId());

            //Copy Generic Role Privileges to Institution Role privileges for the selected Role; TryCopy holds 0 when privileges could not be copied perhaps due to duplicability

            Integer TryCopy = iurDao.copyGenericRolePrivileges(Byte.parseByte(getgurId().toString()), iur.getIurId(), getImId());

            //Count the number of privileges after adding privileges based on the selected generic role
            Integer FinalCount = irpDao.CountRecordsForInstitutionRole(iur.getIurId());

            //Decide to throw suitable message updating the action taken to the end user
            if ((FinalCount-InitialCount) == 0 || TryCopy == 0)
               message = "No generic role privileges found for addition";
            else
               message = (FinalCount-InitialCount) + " Privileges added successfully";

            //Show the resultant records of the selected Institution User Role
            BrowseIURP();

        return SUCCESS;

        } catch (Exception e) {
            if (e.getCause().toString().contains("UNIQUE_MODULE_PROGRAM_ROLE")) {
                message = "Privileges already exists with the user role";
                BrowseIURP();
                return SUCCESS;
            }
            else
                message = "Exception in CreateIURP method -> ManageInstitutionRoleAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    //This method shows Institutional Role Privileges for a Role
    public String BrowseIURP() throws Exception {
        try {
            if(iur != null) {
                irpList = irpDao.findByiurId(iur.getIurId());
            }
            else {
                  irpList = irpDao.findByiurId(getIurId());
                  iur = iurDao.findByIURId(getIurId());
            }

            message2 = "You are now viewing privileges for the role '" +
                        iur.getIurName() +
                        "' in '" + iur.getInstitutionmaster().getImName() + "'" ;
            return SUCCESS;

        } catch (Exception e) {
            message = "Exception in BrowseIURP method -> ManageInstitutionRoleAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }


    public String DeleteIURP() throws Exception {
        try {


          irp = irpDao.findByIupId(getIupId());
          //Retrieve IUR Record
          iur = iurDao.findByIURId(irp.getInstitutionuserroles().getIurId());

          irpDao.delete(irp);

          irpList = irpDao.findByiurId(irp.getInstitutionuserroles().getIurId());

          message = "Record successfully deleted";

          message2 = "You are now viewing privileges for  the role '" + iur.getIurName() + "' in '" + iur.getInstitutionmaster().getImName() + "'";


          return SUCCESS;

        } catch (Exception e) {
                message = "Exception in DeleteIURP method -> ManageInstitutionRoleAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    //This method setups editing of a particular privilege given to a role in an institution
    //For Example: Purchase Manager may have privilege - add, delete, and view on a given progrom.
    //The method would enable the Administrator to revoke permission to delete or add permission to update
    public String EditIURP() throws Exception {
        try {

           irp = irpDao.findByIupId(getIupId());
           //Retrieve IUR Record
           iur = iurDao.findByIURId(irp.getInstitutionuserroles().getIurId());

           message = "You are now editing privileges for the role '" + iur.getIurName() + "' in '" + iur.getInstitutionmaster().getImName() + "'" ;
           return SUCCESS;

        } catch (Exception e) {
                message = "Exception in EditIURP method -> ManageInstitutionRoleAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    //This method updates the Institution Role privilege after editing by the user
    public String SaveIURP() throws Exception {
        try {

           if (irp.getIupId() != null) {
           Institutionroleprivileges irp2 = irpDao.findByIupId(irp.getIupId());
           irp2 = irp;
           irpDao.update(irp2);
           //Retrieve IUR Record
           iur = iurDao.findByIURId(irp2.getInstitutionuserroles().getIurId());

           }
         else {
                     irp.setInstitutionuserroles(iur);
                     irpDao.save(irp);
                     message2 = "Record Saved successfully";
                     iur = iurDao.findByIURId(irp.getInstitutionuserroles().getIurId());
         }

           InstitutionRole  = iur.getIurId();

           message = "You are now editing privileges for the role '" + iur.getIurName() + "' in '" + iur.getInstitutionmaster().getImName() + "'" ;

           irpList = irpDao.findByiurId(iur.getIurId());
        return SUCCESS;

        } catch (Exception e) {
            if (e.getCause().toString().contains("UNIQUE_MODULE_PROGRAM_ROLE")) {
                message = "Privileges already exists with the user role";
                iur = iurDao.findByIURId(irp.getInstitutionuserroles().getIurId());
                InstitutionRole  = iur.getIurId();
                message = "You are now editing privileges for the role '" + iur.getIurName() + "' in '" + iur.getInstitutionmaster().getImName() + "'" ;
                irpList = irpDao.findByiurId(iur.getIurId());
            }
            else
                message = "Exception in SaveIURP method -> ManageInstitutionRoleAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }


    //This method adds a privilege to a role in an institution
    public String AddIURP() throws Exception {
        try {

           erpmmList = erpmmDao.findAll();
           erpmpList = erpmpDao.findByErpmmId(Integer.parseInt("1"));
           //Retrieve IUR Record
           iur = iurDao.findByIURId(iur.getIurId());
           message = "You are now adding privilege for the role '" + iur.getIurName() + "' in '" + iur.getInstitutionmaster().getImName() + "'" ;
           return SUCCESS;

        } catch (Exception e) {
                message = "Exception in EditIURP method -> ManageInstitutionRoleAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    //Add this method By Sajid Aziz.
      public String Clear() throws Exception {
        try {
            //Clear form field
            iur = null;
            imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in -> DepartmentalBudgetAllocationAxn"  + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

}



