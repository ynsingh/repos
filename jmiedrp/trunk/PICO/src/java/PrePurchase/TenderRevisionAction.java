/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PrePurchase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.struts2.interceptor.validation.SkipValidation;
import utils.DevelopmentSupport;
import pojo.hibernate.Departmentmaster;
import pojo.hibernate.DepartmentmasterDAO;
import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;
import pojo.hibernate.Subinstitutionmaster;
import pojo.hibernate.SubinstitutionmasterDAO;
import pojo.hibernate.ErpmTenderRevisions;
import pojo.hibernate.ErpmTenderMaster;
import pojo.hibernate.ErpmTenderMasterDAO;
import pojo.hibernate.ErpmGenMaster;
import pojo.hibernate.ErpmGenMasterDao;
import pojo.hibernate.ErpmTenderRevisionsDAO;
import utils.DateUtilities;

/**
 *
 * @author manauwar
 */
public class TenderRevisionAction extends DevelopmentSupport {

    private ErpmTenderRevisions etr;
    private ErpmTenderRevisionsDAO etrDao = new ErpmTenderRevisionsDAO();
    private List<Institutionmaster> imList = new ArrayList<Institutionmaster>();
    private List<Subinstitutionmaster> simList = new ArrayList<Subinstitutionmaster>();
    private List<Departmentmaster> dmList = new ArrayList<Departmentmaster>();
    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();
    private SubinstitutionmasterDAO simDao = new SubinstitutionmasterDAO();
    private List<ErpmTenderMaster> tmList = new ArrayList<ErpmTenderMaster>();
    private DepartmentmasterDAO dmDao = new DepartmentmasterDAO();
    private ErpmTenderMasterDAO tmDao = new ErpmTenderMasterDAO();
    private List<ErpmGenMaster> revTypeList = new ArrayList<ErpmGenMaster>();
    private List<ErpmTenderRevisions> etrList = new ArrayList<ErpmTenderRevisions>();
    private ErpmGenMasterDao revTypeDao = new ErpmGenMasterDao();
    private String message;
    private String TenderName;
    private String revisionDate;
    private Integer ETRID;

    public List<Departmentmaster> getDmList() {
        return dmList;
    }

    public void setDmList(List<Departmentmaster> dmList) {
        this.dmList = dmList;
    }

    public Integer getETRID() {
        return ETRID;
    }

    public void setETRID(Integer ETRID) {
        this.ETRID = ETRID;
    }

   

    public List<ErpmTenderRevisions> getEtrList() {
        return etrList;
    }

    public void setEtrList(List<ErpmTenderRevisions> etrList) {
        this.etrList = etrList;
    }

    public String getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(String revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getTenderName() {
        return TenderName;
    }

    public void setTenderName(String TenderName) {
        this.TenderName = TenderName;
    }

    public List<ErpmTenderMaster> getTmList() {
        return tmList;
    }

    public void setTmList(List<ErpmTenderMaster> tmList) {
        this.tmList = tmList;
    }

    public ErpmTenderRevisions getEtr() {
        return etr;
    }

    public void setEtr(ErpmTenderRevisions etr) {
        this.etr = etr;
    }

    public List<Institutionmaster> getImList() {
        return imList;
    }

    public void setImList(List<Institutionmaster> imList) {
        this.imList = imList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Subinstitutionmaster> getSimList() {
        return simList;
    }

    public void setSimList(List<Subinstitutionmaster> simList) {
        this.simList = simList;
    }

    public List<ErpmGenMaster> getRevTypeList() {
        return revTypeList;
    }

    public void setRevTypeList(List<ErpmGenMaster> revTypeList) {
        this.revTypeList = revTypeList;
    }

@SkipValidation
    public String execute() throws Exception {
        try {
            InitializeLOVs();

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in -> ManageTenderRevisionAction" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

@SkipValidation
    public void InitializeLOVs() {

        imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
        simList = simDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Short.valueOf(getSession().getAttribute("imId").toString()));
        dmList = dmDao.findAllDepartmentsForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
        tmList = tmDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
        revTypeList = revTypeDao.findByErpmGmType(Short.parseShort("23"));


    }

    public String SaveTenderRevision() {
        try {
            if (etr.getTrTrId() == null) {
                DateUtilities dt = new DateUtilities();
                etr.setTrRevisionDate(dt.convertStringToDate(getRevisionDate()));
                Boolean var = etrDao.findByRevisionNoAndTenderNo(etr.getErpmTenderMaster().getTmTmId(),etr.getTrRevisionNo());
                if(var == false)
                {
                    message = "This revision no already exist for this Tender No, Please enter another Revision no";
                    return INPUT;
                }
                if(var == true)
                {
                etrDao.save(etr);
                }
                etrList = etrDao.findAll();
                message = "Record saved successfully";

            } else {
                ErpmTenderRevisions etr1 = new ErpmTenderRevisions();
               etr1 = etrDao.findByTRId(etr.getTrTrId());
               DateUtilities dt = new DateUtilities();
                etr.setTrRevisionDate(dt.convertStringToDate(getRevisionDate()));
                etr1 = etr;
                etrDao.update(etr1);
                etrList = etrDao.findAll();
                message = "Record updated successfully";
            }

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in SaveTenderRevision method -> TenderRevisionAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String clearTenderRevision() {
        try {
            etr = null;
            InitializeLOVs();
            setRevisionDate("");
            message = "Form cleared successfully";
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in clearTenderRevision method -> TenderRevisionAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String BrowseTenderRevision() {
        try {

            etrList = etrDao.findAll();
            message = "You can now EDIT and DELETE the records";
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in BrowseTenderRevision method -> TenderRevisionAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    
@SkipValidation
    public String EditTenderRevision() {
        try {

            DateUtilities dt = new DateUtilities();
            etr = etrDao.findByTRId(getETRID());
            InitializeLOVs();
            setTenderName(etr.getErpmGenMaster().getErpmgmEgmDesc());
            setRevisionDate(dt.convertDateToString(etr.getTrRevisionDate(), "dd-MM-yyyy"));

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in EditTenderRevision method -> TenderRevisionAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }


@SkipValidation
    public String DeleteTenderRevision() {
        try {

            etr = etrDao.findByTRId(getETRID());
            etrDao.delete(etr);
            etrList = etrDao.findAll();
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in :DeleteTenderRevision method -> TenderRevisionAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;

        }
    }

@Override
 public void validate() {
        try {
            
            if (etr.getErpmGenMaster().getErpmgmEgmId() == null) {
                    addFieldError("etr.erpmGenMaster.erpmgmEgmId", "Please select Revision Type");
                }

                if (etr.getInstitutionmaster().getImId() == null) {
                    addFieldError("etr.institutionmaster.imId", "Please select Institution");
                }
                if (etr.getSubinstitutionmaster().getSimId() == null) {
                    addFieldError("etr.subinstitutionmaster.simId","Please select SubInstitution");
                }
               if (etr.getDepartmentmaster().getDmId() == null) {
                    addFieldError("etr.departmentmaster.dmId", "Please select Department");
                }

                 if (etr.getErpmTenderMaster().getTmTmId() == null) {
                    addFieldError("etr.erpmTenderMaster.tmTmId", "Please select Tender No");
                }

            DateUtilities dt = new DateUtilities();

            if(dt.isValidDate(getRevisionDate())== false)
            {
                addFieldError("revisionDate", "Please give valid Revision Date [dd-mm-yyyy]");
            }
             
                  InitializeLOVs();
        } catch (Exception e) {
            message = "Encountered an Exception during Validation" + e.getCause();
        }
    }
}
