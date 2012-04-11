/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Administration;

import pojo.hibernate.Departmentmaster;
import pojo.hibernate.DepartmentmasterDAO;
import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;
import pojo.hibernate.Subinstitutionmaster;
import pojo.hibernate.SubinstitutionmasterDAO;
import pojo.hibernate.Statemaster;
import pojo.hibernate.StatemasterDAO;
import pojo.hibernate.Countrymaster;
import pojo.hibernate.CountrymasterDAO;
import pojo.hibernate.Employeemaster;
import pojo.hibernate.EmployeemasterDAO;
import utils.DevelopmentSupport;

import pojo.hibernate.Committeemaster;
import pojo.hibernate.CommitteemasterDAO;

import pojo.hibernate.ErpmGenMaster;
import pojo.hibernate.ErpmGenMasterDao;

import java.util.ArrayList;
import java.util.List;
import java.util.*;
/**
 *
 * @author kazim
 */
public class DepartmentAction extends DevelopmentSupport {

    private Institutionmaster im;
    private Subinstitutionmaster sim;
    private Departmentmaster dm;
    private Committeemaster cm;
    private ErpmGenMaster egm;


    private List<Departmentmaster> dmList = new ArrayList<Departmentmaster>();
    private List<Institutionmaster> imIdList = new ArrayList<Institutionmaster>();
    private List<Subinstitutionmaster> simImIdList = new ArrayList<Subinstitutionmaster>();
    private List<Statemaster> statemasterList = new ArrayList<Statemaster>();
    private DepartmentmasterDAO dmDao = new DepartmentmasterDAO();
    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();
    private SubinstitutionmasterDAO simDao = new SubinstitutionmasterDAO();
    private StatemasterDAO statemasterDao = new StatemasterDAO();
    private List<Countrymaster> ctList = new ArrayList<Countrymaster>();
    private CountrymasterDAO cmDao = new CountrymasterDAO();
    private EmployeemasterDAO empDao = new EmployeemasterDAO();
    private List<Employeemaster> empList = new ArrayList<Employeemaster>();
    private String message;
    private Integer dmId;
    private CommitteemasterDAO comDAO = new CommitteemasterDAO();
    private ErpmGenMasterDao              GMDao     =           new ErpmGenMasterDao();


    public void setegm(ErpmGenMaster egm) {
                 this.egm = egm;
    }
    public ErpmGenMaster getegm() {
                 return this.egm;
    }

    public void setcm(Committeemaster cm) {
                this.cm = cm;
    }

    public Committeemaster cm() {
                return this.cm;
    }

    public void setim(Institutionmaster im) {
                    this.im = im;
    }
    public Institutionmaster getim() {
                return this.im;
    }
    
    public void setsim(Subinstitutionmaster sim) {
                this.sim = sim;
    }

    public Subinstitutionmaster getsim() {
                return this.sim;
    }

    public void setdm(Departmentmaster dm) {
                this.dm = dm;
    }

    public Departmentmaster getdm() {
                return this.dm;
    }

    public void setdmId(Integer dmId) {
        this.dmId = dmId;
    }

    public Integer getdmId() {
        return this.dmId;
    }

    

    public void setimIdList(List<Institutionmaster> imIdList) {
        this.imIdList = imIdList;
    }

    public List<Institutionmaster> getimIdList() {
        return this.imIdList;
    }

    public void setsimImIdList(List<Subinstitutionmaster> simImIdList) {
        this.simImIdList = simImIdList;
    }

    public List<Subinstitutionmaster> getsimImIdList() {
        return this.simImIdList;
    }

    public void setstatemasterList(List<Statemaster> statemasterList) {
        this.statemasterList = statemasterList;
    }

    public List<Statemaster> getstatemasterList() {
        return this.statemasterList;
    }

     public void setdmList(List<Departmentmaster> dmList) {
        this.dmList = dmList;
    }

    public List<Departmentmaster> getdmList() {
        return this.dmList;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setctList(List<Countrymaster> ctList) {
        this.ctList = ctList;
    }

    public List<Countrymaster> getctList() {
        return this.ctList;
    }



    public void setempList(List<Employeemaster> empList) {
        this.empList = empList;
    }

    public List<Employeemaster> getempList() {
        return this.empList;
    }

    public void InitializeLOVs() {
      //Prepare LOV containing User Institutions
      imIdList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));

      //Prepare LOV containing the SubInstitutions forthe selected Institution
      simImIdList = simDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Short.valueOf(getSession().getAttribute("imId").toString()));
      
      //Prepare LOVS for Country and State
      statemasterList = statemasterDao.findAll();
      ctList=cmDao.findAll();

      return;      
    }

 public void InitializeLOVsForEdit() {
                 //Prepare Institution LOV
              imIdList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));

             //Prepare Sub-Institution LOV
             simImIdList = simDao.findBysimImId(dm.getInstitutionmaster().getImId());

             //Prepare Employees LOV
             empList=empDao.findByImId(dm.getInstitutionmaster().getImId());

            //Prepare LOVS for Country and State
            statemasterList = statemasterDao.findAll();
            ctList=cmDao.findAll();
            return;
}
     @Override
    public String execute() throws Exception {
        try {
           InitializeLOVs();
           String DM_SN = getSession().getAttribute("dmshortname").toString();
          //message="hello"+DM_SN;
           Departmentmaster dm= dmDao.findDeptByDMShortName(DM_SN );
         // message="hello"+DM_SN+dm.getDmId();
           empList=empDao.findByDmId(dm.getDmId());
          return SUCCESS;
        } catch (Exception e) {
            message = "Exception in -> DepartmentAxn"  + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Browse() throws Exception {
        try {
            dmList = dmDao.findAll();
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Browse method -> DepartmentAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Save() throws Exception {
        try {
        

                   //If part saves record for the first time; else parts is for record update
               if (dm.getDmId() == null) {
               if(dm.getInstitutionmaster().getImId()==null)
                    message = "Please select institution";
                else if(dm.getSubinstitutionmaster().getSimId()==null)
                    message = "Please select College/Faculty/School ";
               else if(dm.getDmName().length()==0)
                    message = "Please enter Department ";
               else if(dm.getDmShortName().length()==0)
                    message = "Please enter Department Short Name ";
               else if(dm.getDmAddressLine1().length()==0)
                    message = "Please enter Address ";
               else if(dm.getCountrymaster().getCountryId()==null)
                    message = "Please select Country ";
               else if(dm.getStatemaster().getStateId()==null)
                    message = "Please select State ";
                else if(dm.getDmDistrict().length()==0)
                    message = "Please enter District ";
               else if(dm.getDmPinNo().length()==0)
                    message = "Please enter Pin ";
               else if(dm.getDmEmailId().length()==0)
                    message = "Please enter Email ";
               else{
                   dmDao.save(dm);

                   egm=GMDao.findByErpmGmId(Short.parseShort("64"));
                   cm.setDepartmentmaster(dm);
                   cm.setInstitutionmaster(dm.getInstitutionmaster());
                   cm.setSubinstitutionmaster(dm.getSubinstitutionmaster());
                   cm.setErpmGenMaster(egm);
                   String stremail=dm.getDmEmailId().toString();
                   cm.setCommitteeConvener(stremail);
                   String str=dm.getDmName() +", "  +dm.getDmHeadDesignation();
                   cm.setCommitteeName(str);
                   cm.setCommitteeActive('t');
                   cm.setCommitteeLevel('D');
                   String strCommPurpose="Head of The Department";
                   cm.setCommmitteePurpose(strCommPurpose);

                   comDAO.save(cm);
                        message = "Department record saved successfully. Departmenyt Id is " + dm.getDmId();
                    }
                  InitializeLOVs();
                  dm=null;
               } else {
                    Departmentmaster dm2 = dmDao.findByDmId(dm.getDmId());
                    dm2 = dm;
                    dmDao.update(dm2);
                    message = "Department record updated successfully.";
                    InitializeLOVsForEdit();
               }
            dmList = dmDao.findAll();
    

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Save method -> DepartmentAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;

        }
    }

    public String Edit() throws Exception {
        try {

             dm = dmDao.findByDmId(getdmId());
             InitializeLOVsForEdit();
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Edit method -> DepartmentAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Delete() throws Exception {
        try {
            dm = dmDao.findByDmId(getdmId());
            dmDao.delete(dm);
            dmList = dmDao.findAll();
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Delete method -> DepartmentAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Clear() throws Exception {
        try {

            dm = null;

            imIdList = imDao.findAll();
            simImIdList = simDao.findAll();
            statemasterList = statemasterDao.findAll();

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in -> DepartmentAxn"  + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }


}
