package Administration;

import java.io.*;

import java.io.InputStream;
import java.util.*;
import pojo.hibernate.GfrMaster;
import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;

import pojo.hibernate.GfrMasterDAO;
import net.sf.jasperreports.engine.*;
import org.apache.struts2.interceptor.validation.SkipValidation;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import utils.DevelopmentSupport;

public class ManageGFRAction extends DevelopmentSupport {

    private String message;
    private InputStream inputStream;
    // this list gfrMasterList for  accessing data of GFRMaster from database 
    private List<GfrMaster> gfrMasterList = new ArrayList<GfrMaster>();
    private GfrMasterDAO gfrMasterDao = new GfrMasterDAO();
    private InstitutionmasterDAO imDao =new InstitutionmasterDAO() ;

// gfrMasterId use for to get gfrGfrId from jsp to this action class for browse and edit
    private GfrMaster grfMaster;
    private int gfrMasterId;
    private ManageGFRAction grfAction;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setgrfMaster(GfrMaster grfMaster) {
        this.grfMaster = grfMaster;
    }

    public GfrMaster getgrfMaster() {
        return this.grfMaster;
    }

    public void setgrfAction(ManageGFRAction grfAction) {
        this.grfAction = grfAction;
    }

    public ManageGFRAction getgrfAction() {
        return this.grfAction;
    }

    public List<GfrMaster> getgfrMasterList() {
        return gfrMasterList;
    }

    public void setgfrMasterList(List<GfrMaster> gfrMasterList) {
        this.gfrMasterList = gfrMasterList;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setgfrMasterId(int gfrMasterId) {
        this.gfrMasterId = gfrMasterId;
    }

    public int getgfrMasterId() {
        return this.gfrMasterId;
    }

    @Override
    public String execute() throws Exception {
        try {

            return "input";
        } catch (Exception e) {
            // message = "Exception in -> EmployeeAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }
//save data of GFRMaster in  data base

    public String Save() throws Exception {

        try {

            //If part saves record for the first time; else parts is for record update
            if (grfMaster.getGfrGfrId() == null) {//data submitteting by form then getGfrGfrId() will be null
                if(grfMaster.getGfrorInstituteRule()=='I'){
                   Institutionmaster im = imDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
                   grfMaster.setInstitutionmaster(im);
                }
                gfrMasterDao.save(grfMaster);
                //  gfrMasterId= grfMaster.getGfrGfrId();
                message = "Employee record saved successfully. GfrMaster Id is " + grfMaster.getGfrGfrId();

                //InitializeLOVs();
                grfMaster = null;
            } else {
                //if edit data is updated  then get GfrMaster object according selected  GfrGfrId
                GfrMaster grfMaster2 = gfrMasterDao.findBygrfMasterId(grfMaster.getGfrGfrId().shortValue());
                grfMaster2 = grfMaster;
                //updating data
                gfrMasterDao.update(grfMaster2);
                message = "Employee record updated successfully";
                grfMaster = null;
                // InitializeLOVs();
            }

            return "input";
        } catch (Exception e) {
            message = "Exception in Save method ->GfrMaster-- " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;

        }
    }

    @Override
    public void validate() {
        try {
            if (grfMaster.getGfrGfrId() == null) {

                if (grfMaster.getGfrChapterNo() == 0) {
                    addFieldError("grfMaster.gfrChapterNo", "Please set Chapter No");
                }
                if (grfMaster.getGfrRuleNo() == null) {
                    addFieldError("grfMaster.gfrRuleNo", "Please give GFRRule No");
                }
                if (grfMaster.getGfrChapterName().isEmpty()) {
                    addFieldError("grfMaster.gfrChapterName", "Please give Chapter Name");
                }

                if (grfMaster.getGfrSection() == null) {
                    addFieldError("grfMaster.gfrDescription", "Please select Section");
                }


            }

        } catch (NullPointerException e) {
        }
    }

    public String Clear() throws Exception {
        try {
            //for clearing form null object grfMaster
            grfMaster = null;


            return "input";
        } catch (Exception e) {
            message = "Exception in Clear method -> ManageGFRActionAxn" + e.getMessage();
            return ERROR;
        }
    }
    // this method use brouse of whole data from data base 

    @SkipValidation
    public String BrowseManageGFR() throws Exception {
        try {
//          get whole data from GFRMaster in form list to show in Browse page
            gfrMasterList = gfrMasterDao.findListOfgfrMaster();
            return "input";
        } catch (Exception e) {
            message = "Exception in Browse method -> BrowseManageGFRAxn" + e.getMessage();
            return "ERROR";
        }
    }
//in this edit method  we show selected data of browse page in main jsp by clicking in edit link 

    public String Edit() throws Exception {
        try {
            // getting data in gfrMaster object from from GFRMaster database on base of gfrMsterId which is getting from browse page by getgfrMasterId()
            grfMaster = gfrMasterDao.findBygrfMasterId(getgfrMasterId());

            return "input";

        } catch (Exception e) {
            message = "Exception in Edit method -> InstitutionAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return "input";
        }
    }
    //deleting record of grfMaster

    public String Delete() throws Exception {
        try {
            //getting GFRMaster object according gfrMasterId when we click in edit  link in browse page by getgfrMasterId() method

            grfMaster = gfrMasterDao.findBygrfMasterId(getgfrMasterId());
            //deleting that GFRMaster object
            gfrMasterDao.delete(grfMaster);
            //now getting remaining GFRMaster list to show in brouse page
            gfrMasterList = gfrMasterDao.findListOfgfrMaster();
            message = "Date is deleted with newsid" + getgfrMasterId();

            return "input";
        } catch (Exception e) {
            if (e.getCause().toString().contains("java.sql.BatchUpdateException: Cannot delete or update a parent row")) {
                message = "Cannot delete record as related record(s) exist(s). Reported cause is         :" + e.getCause();
            } else {
                message = "Exception in Delete method -> GFRMastersAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            }
            return "ERROR";
        }
    }
}
