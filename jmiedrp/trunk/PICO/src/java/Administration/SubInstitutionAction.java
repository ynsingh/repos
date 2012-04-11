/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Administration;

/**
 *
 * @author kazim
 */

import pojo.hibernate.Subinstitutionmaster;
import pojo.hibernate.SubinstitutionmasterDAO;
import pojo.hibernate.Departmentmaster;
import pojo.hibernate.DepartmentmasterDAO;

import pojo.hibernate.Statemaster;
import pojo.hibernate.StatemasterDAO;

import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;



import pojo.hibernate.ErpmGenMaster;
import pojo.hibernate.ErpmGenMasterDao;
import pojo.hibernate.Countrymaster;
import pojo.hibernate.CountrymasterDAO;
import pojo.hibernate.Employeemaster;
import pojo.hibernate.EmployeemasterDAO;
import utils.DevelopmentSupport;

import pojo.hibernate.Committeemaster;
import pojo.hibernate.CommitteemasterDAO;



import java.util.ArrayList;
import java.util.List;

public class SubInstitutionAction extends DevelopmentSupport{
    private Institutionmaster im;
  //  private Subinstitutionmaster sim;
    private Departmentmaster dm;
    private Committeemaster cm;
    private ErpmGenMaster egm;

private Subinstitutionmaster sim;// = new Subinstitutionmaster();
    
private List<Subinstitutionmaster> simList = new ArrayList<Subinstitutionmaster>();
private SubinstitutionmasterDAO simDao = new SubinstitutionmasterDAO ();
private List<Institutionmaster> simImIdList = new ArrayList<Institutionmaster>();
private List<ErpmGenMaster> simTypeList = new ArrayList<ErpmGenMaster>();
private List<Statemaster> statemasterList = new ArrayList<Statemaster>();
private InstitutionmasterDAO imDao= new InstitutionmasterDAO();
private ErpmGenMasterDao simTypeDao = new ErpmGenMasterDao();
private StatemasterDAO stDao=new StatemasterDAO();
private List<Countrymaster> ctList = new ArrayList<Countrymaster>();
private CountrymasterDAO cmDao = new CountrymasterDAO();
private EmployeemasterDAO empDao = new EmployeemasterDAO();
private List<Employeemaster> empList = new ArrayList<Employeemaster>();
private CommitteemasterDAO comDAO = new CommitteemasterDAO();
private ErpmGenMasterDao              GMDao     =           new ErpmGenMasterDao();
private String message;
private Integer SIMID;

    public void setegm(ErpmGenMaster egm) {
                 this.egm = egm;
    }
    public ErpmGenMaster getegm() {
                 return this.egm;
    }

    public void setdm(Departmentmaster dm) {
                this.dm = dm;
    }

    public Departmentmaster getdm() {
                return this.dm;
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

public String getMessage() {
        return message;
    }

     public void setMessage(String message) {
        this.message = message;
    }

    public Subinstitutionmaster getsim() {
        return sim;
    }

     public void setsim(Subinstitutionmaster sim) {
        this.sim = sim;
    }

    public List<Institutionmaster> getsimImIdList() {
        return simImIdList;
    }
    public void setsimImIdList(List<Institutionmaster> simImIdList) {
        this.simImIdList = simImIdList;
    }

    public List<ErpmGenMaster> getsimTypeList() {
        return simTypeList;
    }
    public void setsimTypeList(List<ErpmGenMaster> simTypeList) {
        this.simTypeList = simTypeList;
    }

    public List<Statemaster> getStatemasterList() {
        return statemasterList;
    }
    public void setStatemasterList(List<Statemaster> statemasterList) {
        this.statemasterList = statemasterList;
    }

    public List<Subinstitutionmaster> getsimList() {
        return simList;
    }

    public void setsimList(List<Subinstitutionmaster> simList) {
        this.simList = simList;
    }

    public Integer getSIMID() {
        return SIMID;
    }

    public void setSIMID(Integer SIMID) {
        this.SIMID = SIMID;
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

    @Override


    public String execute() throws Exception {
        
        try {
            simImIdList=imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
            simTypeList =simTypeDao.findByErpmGmType(Short.parseShort("5"));
            statemasterList=stDao.findAll();
            ctList=cmDao.findAll();
            String SIM_SN = getSession().getAttribute("simshortname").toString();
            Subinstitutionmaster sim1= simDao.findInstBySIMShortName(SIM_SN);
            empList=empDao.findByImId(sim1.getInstitutionmaster().getImId());
            return SUCCESS;
            } catch (Exception e) {
                message = "Exception in Execute method -> SubInstitutionAxn"  + e.getMessage() + e.getCause();
                return ERROR;
       }

    }

 public String Save() throws Exception {
        try {
            //If part saves record for the first time; else parts is for record update            
            if (sim.getSimId() == null) {

                   
                simDao.save(sim);

                   egm=GMDao.findByErpmGmId(Short.parseShort("64"));
                   cm.setDepartmentmaster(null);
                   cm.setInstitutionmaster(sim.getInstitutionmaster());
                   cm.setSubinstitutionmaster(sim);
                   cm.setErpmGenMaster(egm);
                   String stremail=sim.getSimEmailId().toString();
                   cm.setCommitteeConvener(stremail);
                   String str=sim.getSimName() +", "  +sim.getSimHeadDesignation();
                   cm.setCommitteeName(str);
                   cm.setCommitteeActive('t');
                   cm.setCommitteeLevel('I');
                   String strCommPurpose="Head of The SubInstitution";
                   cm.setCommmitteePurpose(strCommPurpose);

                   comDAO.save(cm);

                message = "Sub Institution record saved successfully. Sub-Institution Id is " + sim.getSimId();
            } else {
                Subinstitutionmaster sim1 =  simDao.findBySimId(sim.getSimId());
                sim1 = sim;
                simDao.update(sim1);
                message = "Sub Institution record saved successfully.";
            }           
            return SUCCESS;
        } catch (Exception e) {
            if (e.getCause().toString().contains("UNIQUE_SIM_IM_ID_SIM_Name")) 
                message = "A subinstiutution record with the name '" + sim.getSimName() + "' already exists for the institution.";
            else
                message = "Exception in Save method -> ManageItemsAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

public String Browse() throws Exception {
    try {
        simList = simDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Short.valueOf(getSession().getAttribute("imId").toString()));
        return SUCCESS;
        } catch (Exception e) {
             message = "Exception in Browse method -> SubInstitutionAxn" + e.getMessage();
             return ERROR;
            }
        }

public String Edit() throws Exception {
        try {

            sim = simDao.findBySimId(getSIMID());

            //Prepare List of Employees workinf for the Institution 
            empList=empDao.findByImId(sim.getInstitutionmaster().getImId());

            //Prepate List of Institutions for the User
            simImIdList=imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));

            //Prepate List of Sub-Institutions Type
            simTypeList =simTypeDao.findByErpmGmType(Short.parseShort("5"));

            //Prepate Country List
            ctList=cmDao.findAll();

            //Prepate State List
            statemasterList=stDao.findAll();

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Edit method -> SubInstitutionAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }


public String Delete() {
        sim = simDao.findBySimId(getSIMID());
        simDao.delete(sim);
        simList = simDao.findAll();
        return SUCCESS;
        }

public String Clear() throws Exception {
        try {
            sim = null;
            simImIdList=imDao.findAll();
            simTypeList =simTypeDao.findByErpmGmType(Short.parseShort("5"));
            statemasterList=stDao.findAll();
            return SUCCESS;
            } catch (Exception e) {
                message = "Exception in Clear method -> SubInstitutionAxn" + e.getMessage() + e.getCause();
                return ERROR;
       }

    }

}
