/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Administration;

/**
 *
 * @author kazim
 */



import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;
import pojo.hibernate.ErpmGenMaster;
import pojo.hibernate.ErpmGenMasterDao;
import pojo.hibernate.Statemaster;
import pojo.hibernate.StatemasterDAO;
import pojo.hibernate.Countrymaster;
import utils.DevelopmentSupport;
import pojo.hibernate.CountrymasterDAO;
import pojo.hibernate.Employeemaster;
import pojo.hibernate.EmployeemasterDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
public class InstitutionAction extends DevelopmentSupport{

    private Institutionmaster im, im2;
    private Short ImId;
    private List<Institutionmaster> imList = new ArrayList<Institutionmaster>();
    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();

     private EmployeemasterDAO empDao = new EmployeemasterDAO();
            private List<Statemaster> statemasterList = new ArrayList<Statemaster>();
    private List<ErpmGenMaster> institutiontypeList = new ArrayList<ErpmGenMaster>();
    private List<Countrymaster> ctList = new ArrayList<Countrymaster>();
    private CountrymasterDAO cmDao = new CountrymasterDAO();
    private List<Employeemaster> empList = new ArrayList<Employeemaster>();
            private String message;

    public String getMessage() {
        return message;
    }

    public Short getImId() {
        return ImId;
    }
    
    public void setImId(Short ImId) {
        this.ImId = ImId;
    }

    public Institutionmaster getim() {
        return im;
    }
    public void setim(Institutionmaster im) {
        this.im = im;
    }

    public List<Institutionmaster> getimList() {
        return imList;
    }

    public void setbtmList(List<Institutionmaster> imList) {
        this.imList = imList;
    }

   public List<ErpmGenMaster> getInstitutiontypeList() {
        return institutiontypeList;
    }
    public void setInstitutiontypeList(List<ErpmGenMaster> institutiontypeList) {
        this.institutiontypeList = institutiontypeList;
    }

    public List<Statemaster> getStatemasterList() {
        return statemasterList;
    }
    public void setStatemasterList(List<Statemaster> statemasterList) {
        this.statemasterList = statemasterList;
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
            //Prepate State List
            StatemasterDAO stateDao = new StatemasterDAO();
          ctList=cmDao.findAll();
            statemasterList = stateDao.findAll();

            //Prepate Institution Type List
            ErpmGenMasterDao erpmgmDao = new ErpmGenMasterDao();
            institutiontypeList = erpmgmDao.findByErpmGmType(Short.parseShort("4"));
 String IM_SN = getSession().getAttribute("imshortname").toString();
          // message="HELLO"+ IM_SN ;
 Institutionmaster im3=imDao.findInstByIMShortName(IM_SN);
          //message="HELLO"+ im3.getImId();
 empList=empDao.findByImId(im3.getImId());
            return SUCCESS;
            }
       catch (Exception e)
       {
           message = "Exception in Browse method -> InstitutionAxn" + e.getMessage();
           return ERROR;
       }
       }

public String Browse() throws Exception {
    try {
        imList = imDao.findAll();  
        return SUCCESS;
     }
        catch (Exception e)
            {
             message = "Exception in Browse method -> InstitutionAxn" + e.getMessage();
             return ERROR;
            }
        }

public String Edit() throws Exception {
        try {
            //Prepate Institution Type List
            ErpmGenMasterDao erpmgmDao = new ErpmGenMasterDao();
            institutiontypeList = erpmgmDao.findByErpmGmType(Short.parseShort("4"));

            //Prepate State List
            StatemasterDAO statemasterDao = new StatemasterDAO();
            statemasterList = statemasterDao.findAll();

            im = imDao.findByImId(getImId());
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Edit method -> InstitutionAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

public String Delete() {    
    im = imDao.findByImId(getImId());
    imDao.delete(im);
    imList = imDao.findAll();    
    return SUCCESS;
    }


public String Save() throws Exception {
        try {
            //If part saves record for the first time; else parts is for record update
            if (im.getImId() == null) {
                imDao.save(im);
                message = "Institution record saved successfully. Institution Id is " + im.getImId();
            } else {
                Institutionmaster im2 = imDao.findByImId(im.getImId());
                im2 = im;
                imDao.update(im2);
                message = "Record saved successfully.";
            }
            imList = imDao.findAll();
            return SUCCESS;
        } catch (Exception e) {

            if (e.getCause().toString().contains("Unique_IM_Name"))
                message = "Institution with the name '" + im.getImName() + "' already exists";
            else if (e.getCause().toString().contains("Unique_IM_Short_Name"))
                message = "Institution with the short name '" + im.getImShortName() + "' already exists";
            else
                message = "Exception in Save method -> InstitutionAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;

        }
    }


      public String Clear() throws Exception {
        try {
            im = null;
            //Prepate State List
            StatemasterDAO stateDao = new StatemasterDAO();
            statemasterList = stateDao.findAll();

            //Prepate Institution Type List
            ErpmGenMasterDao erpmgmDao = new ErpmGenMasterDao();
            institutiontypeList = erpmgmDao.findByErpmGmType(Short.parseShort("4"));

            return SUCCESS;
            }
       catch (Exception e)
       {
           message = "Exception in Clear method -> InstitutionAxn" + e.getMessage();
           return ERROR;
       }
       }


}
