/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Administration;

/**
 *
 * @author Faraz Ahmad & Ehtesham
 *
 * Updated By :
 */
import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;
import pojo.hibernate.ErpmItemCategoryMaster;
import pojo.hibernate.ErpmItemCategoryMasterDao;
import pojo.hibernate.ErpmusersDAO;
import java.io.*;
import java.util.*;
import org.apache.struts2.interceptor.validation.SkipValidation;
import utils.DevelopmentSupport;

public class ItemCategoryAction extends DevelopmentSupport {

    private Character depvalue;
    private String message;
    private ErpmItemCategoryMaster erpmItemCategoryMaster = new ErpmItemCategoryMaster();
    private List<ErpmItemCategoryMaster> erpmItemCategoryMasterList = new ArrayList<ErpmItemCategoryMaster>();
    private ErpmItemCategoryMasterDao erpmItemCategoryMasterDao = new ErpmItemCategoryMasterDao();
    private Integer STUDID;
    private List<Institutionmaster> imList = new ArrayList<Institutionmaster>();
    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();
    private List<Institutionmaster> tosImIdList = new ArrayList<Institutionmaster>();
    private ErpmusersDAO erpmusersDao = new ErpmusersDAO();
    private List<ErpmItemCategoryMaster> erpmIcmList1 = new ArrayList<ErpmItemCategoryMaster>();
    private List<ErpmItemCategoryMaster> erpmIcmList2 = new ArrayList<ErpmItemCategoryMaster>();
    private List<ErpmItemCategoryMaster> erpmIcmList3 = new ArrayList<ErpmItemCategoryMaster>();
    private Integer TosId;
    // private Short i = 1;
    private static Short j = 1;
    private static Short k = 2;
    private Short l = 18;
    private InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public List<Institutionmaster> getImList() {
        return imList;
    }

    public void setImList(List<Institutionmaster> imList) {
        this.imList = imList;
    }

    public List<Institutionmaster> getTosImIdList() {
        return tosImIdList;
    }

    public void setTosImIdList(List<Institutionmaster> tosImIdList) {
        this.tosImIdList = tosImIdList;
    }

    public List<ErpmItemCategoryMaster> getErpmItemCategoryMasterList() {
        return erpmItemCategoryMasterList;
    }

    public void setErpmItemCategoryMasterList(List<ErpmItemCategoryMaster> erpmItemCategoryMasterList) {
        this.erpmItemCategoryMasterList = erpmItemCategoryMasterList;
    }

    public List<ErpmItemCategoryMaster> getErpmIcmList1() {
        return erpmIcmList1;
    }

    /**
     * @param erpmIcmList1 the erpmIcmList1 to set
     */
    public void setErpmIcmList1(List<ErpmItemCategoryMaster> erpmIcmList1) {
        this.erpmIcmList1 = erpmIcmList1;
    }

    /**
     * @return the erpmIcmList2
     */
    public List<ErpmItemCategoryMaster> getErpmIcmList2() {
        return erpmIcmList2;
    }

    /**
     * @param erpmIcmList2 the erpmIcmList2 to set
     */
    public void setErpmIcmList2(List<ErpmItemCategoryMaster> erpmIcmList2) {
        this.erpmIcmList2 = erpmIcmList2;
    }

    /**
     * @return the erpmIcmList3
     */
    public List<ErpmItemCategoryMaster> getErpmIcmList3() {
        return erpmIcmList3;
    }

    /**
     * @param erpmIcmList3 the erpmIcmList3 to set
     */
    public void setErpmIcmList3(List<ErpmItemCategoryMaster> erpmIcmList3) {
        this.erpmIcmList3 = erpmIcmList3;
    }

    public Character getDepvalue() {
        return depvalue;
    }

    public void setDepvalue(Character depvalue) {
        this.depvalue = depvalue;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the erpmItemCategoryMaster
     */
    public ErpmItemCategoryMaster getErpmItemCategoryMaster() {
        return erpmItemCategoryMaster;
    }

    /**
     * @param erpmItemCategoryMaster the erpmItemCategoryMaster to set
     */
    public void setErpmItemCategoryMaster(ErpmItemCategoryMaster erpmItemCategoryMaster) {
        this.erpmItemCategoryMaster = erpmItemCategoryMaster;
    }

    /**
     * @return the TosId
     */
    public Integer getTosId() {
        return TosId;
    }

    /**
     * @param TosId the TosId to set
     */
    public void setTosId(Integer TosId) {
        this.TosId = TosId;
    }

    public Integer getSTUDID() {
        return STUDID;
    }

    public void setSTUDID(Integer STUDID) {
        this.STUDID = STUDID;
    }

    @SkipValidation
    public String execute() throws Exception {
        try {
            InitializeLOVs();
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in -> ManageOpeningStockAction" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public void InitializeLOVs() {
        tosImIdList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
        erpmIcmList2 = erpmItemCategoryMasterDao.findByErpmicmItemLevel(j);
        erpmIcmList3 = erpmItemCategoryMasterDao.findByErpmicmItemLevel(k);
        return;
    }

    @SkipValidation
    public String BrowseItemCategory() throws Exception {
        try {
            tosImIdList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
            erpmItemCategoryMasterList = erpmItemCategoryMasterDao.findByImId(Short.parseShort(getSession().getAttribute("imId").toString()));
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Browse method -> BrowseItemCategoryAxn" + e.getMessage();
            return ERROR;
        }

    }

    public String SaveItemCategory() throws Exception {

        try {

            if (erpmItemCategoryMaster.getErpmicmItemId() == null) {

                if (erpmItemCategoryMaster.getErpmItemCategoryMaster().getErpmicmItemId() == null) {
                    erpmItemCategoryMaster.setErpmItemCategoryMaster(null);
                }
                erpmItemCategoryMaster.getErpmicmItemId();
                erpmItemCategoryMasterDao.save(erpmItemCategoryMaster);

                message = "Category record saved successfully. Category Id is " + erpmItemCategoryMaster.getErpmicmItemId();


            } else {
                if (erpmItemCategoryMaster.getErpmItemCategoryMaster().getErpmicmItemId() == null || erpmItemCategoryMaster.getErpmItemCategoryMaster().getErpmicmItemId() == 0) {

                    erpmItemCategoryMaster.setErpmItemCategoryMaster(null);
                }
                ErpmItemCategoryMaster em2 = erpmItemCategoryMasterDao.findByErpmicmItemId(erpmItemCategoryMaster.getErpmicmItemId());
                em2 = erpmItemCategoryMaster;
                erpmItemCategoryMasterDao.update(em2);
                message = "Record updated successfully.";

            }
            InitializeLOVs();
            erpmItemCategoryMaster = null;

            return SUCCESS;
        } catch (Exception e) {
            message = erpmItemCategoryMaster.getErpmItemCategoryMaster().getErpmicmItemId() + " " + "Exception in Save method -> EmployeeAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;

        }
    }

    @SkipValidation
    public String Edit() throws Exception {
        try {
            erpmItemCategoryMaster = erpmItemCategoryMasterDao.findByErpmId(getSTUDID());
            erpmIcmList1 = erpmItemCategoryMasterDao.findParentCategoryMaster(erpmItemCategoryMaster.getErpmicmItemLevel());
            InitializeLOVs();
            setDepvalue(erpmItemCategoryMaster.getErpmicmDepreciationMethod());
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Edit method -> ManageOpeningStockAction" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String Clear() throws Exception {
        try {
            tosImIdList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
            erpmItemCategoryMaster = null;
            InitializeLOVs();
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Clear method -> ItemCategoryMasterClearAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }

    }

    @Override
    public void validate() {
        try {
            if (erpmItemCategoryMaster.getInstitutionmaster().getImId() == 0) {
                addFieldError("erpmItemCategoryMaster.institutionmaster.imId", "Please Select Institution Name.");

            }

            if (erpmItemCategoryMaster.getErpmicmCatDesc().length() == 0) {
                addFieldError("erpmItemCategoryMaster.erpmicmCatDesc", "Please enter Item Category.");
            }

            if (erpmItemCategoryMaster.getErpmicmItemLevel() == 0) {
                addFieldError("erpmItemCategoryMaster.erpmicmItemLevel", "Please select Category level.");
            }


            if (erpmItemCategoryMaster.getErpmicmDepreciationMethod() == 0) {
                addFieldError("erpmItemCategoryMaster.erpmicmDepreciationMethod", "Please Select Depeciation method");
            }

            if (erpmItemCategoryMaster.getErpmicmDepreciationPercentage() == -1) {
                addFieldError("erpmItemCategoryMaster.erpmicmDepreciationPercentage", "Please Enter Depeciation rate");
            }
        } catch (NullPointerException npe) {
        }
    }

    @SkipValidation
    public String Delete() throws Exception {
        try {
            //Retrieve the record to be deleted
            erpmItemCategoryMaster = erpmItemCategoryMasterDao.findByErpmicmItemId(getSTUDID());
            erpmItemCategoryMasterDao.delete(erpmItemCategoryMaster);

            //Retrieve issue items list
            erpmItemCategoryMasterList = erpmItemCategoryMasterDao.findByImId(Short.parseShort(getSession().getAttribute("imId").toString()));
            message = "Item Record deleted successfully.";

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Delete method -> IssueItemstAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }




    }
}
