/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Administration;

import java.util.*;
import pojo.hibernate.*;
import utils.DevelopmentSupport;

/**
 *
 * @author Saeed
 */
public class ManageGFRProgramMappingAction extends DevelopmentSupport {

    private String message;
    private Integer gfrMasterId;
    private Integer gfrMappingId;
    private static Short varerpmprgmId;
    private static Integer varerpmSubModuleId;
    private GfrProgramMapping gfrprgrmMap = new GfrProgramMapping();
    private List<Erpmsubmodule> erpmsmList = new ArrayList<Erpmsubmodule>();
    private ErpmsubmoduleDAO erpmsmDao = new ErpmsubmoduleDAO();
    private Erpmprogram erpmprogram = new Erpmprogram();
    private List<Erpmprogram> erpmprgmList = new ArrayList<Erpmprogram>();
    private ErpmprogramDAO erpmprgmDao = new ErpmprogramDAO();
    private GfrMaster gfrMaster = new GfrMaster();
    private List<GfrMaster> gfrMasterList = new ArrayList<GfrMaster>();
    private GfrMasterDAO gfrMasterDao = new GfrMasterDAO();
    private List<GfrProgramMapping> GfrProgramMappingList = new ArrayList<GfrProgramMapping>();
    private GfrProgramMappingDAO GfrProgramMappingDao = new GfrProgramMappingDAO();

    public GfrProgramMapping getGfrprgrmMap() {
        return gfrprgrmMap;
    }

    public void setGfrprgrmMap(GfrProgramMapping gfrprgrmMap) {
        this.gfrprgrmMap = gfrprgrmMap;
    }

    public List<Erpmsubmodule> getErpmsmList() {
        return erpmsmList;
    }

    public void setErpmsmList(List<Erpmsubmodule> erpmsmList) {
        this.erpmsmList = erpmsmList;
    }

    public List<Erpmprogram> getErpmprgmList() {
        return erpmprgmList;
    }

    public void setErpmprgmList(List<Erpmprogram> erpmprgmList) {
        this.erpmprgmList = erpmprgmList;
    }

    public List<GfrMaster> getGfrMasterList() {
        return gfrMasterList;
    }

    public void setGfrMasterList(List<GfrMaster> gfrMasterList) {
        this.gfrMasterList = gfrMasterList;
    }

    public List<GfrProgramMapping> getGfrProgramMappingList() {
        return GfrProgramMappingList;
    }

    public void setGfrProgramMappingList(List<GfrProgramMapping> GfrProgramMappingList) {
        this.GfrProgramMappingList = GfrProgramMappingList;
    }

    public GfrMaster getGfrMaster() {
        return gfrMaster;
    }

    public void setGfrMaster(GfrMaster gfrMaster) {
        this.gfrMaster = gfrMaster;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getGfrMappingId() {
        return gfrMappingId;
    }

    public void setGfrMappingId(Integer gfrMappingId) {
        this.gfrMappingId = gfrMappingId;
    }

    public Integer getGfrMasterId() {
        return gfrMasterId;
    }

    public void setGfrMasterId(Integer gfrMasterId) {
        this.gfrMasterId = gfrMasterId;
    }

    public static Short getVarerpmprgmId() {
        return varerpmprgmId;
    }

    public static void setVarerpmprgmId(Short varerpmprgmId) {
        ManageGFRProgramMappingAction.varerpmprgmId = varerpmprgmId;
    }

    public static Integer getVarerpmSubModuleId() {
        return varerpmSubModuleId;
    }

    public static void setVarerpmSubModuleId(Integer varerpmSubModuleId) {
        ManageGFRProgramMappingAction.varerpmSubModuleId = varerpmSubModuleId;
    }

    public String execute() throws Exception {
        try {
            erpmsmList = erpmsmDao.findAllParentMenu();
            erpmprgmList = erpmprgmDao.findAll();

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Initialising ManageGFRProgramMappingAction -> ManageGFRProgramMappingActionAxn" + e.getMessage();
            return ERROR;
        }
    }

    public String Done() throws Exception {
        try {
            if(gfrprgrmMap.getErpmprogram().getErpmsubmodule().getErpmSubModuleId()==null){
                 
            
                 message = "Please select Sub Module";
                  erpmsmList = erpmsmDao.findAllParentMenu();
            erpmprgmList = erpmprgmDao.findAll();

                             return SUCCESS;

             }
            if(gfrprgrmMap.getErpmprogram().getErpmpId()==null){
                 message = "Please select Program";
                  erpmsmList = erpmsmDao.findAllParentMenu();
            erpmprgmList = erpmprgmDao.findAll();

                             return SUCCESS;

            }
               
            setVarerpmprgmId(gfrprgrmMap.getErpmprogram().getErpmpId());
            setVarerpmSubModuleId(gfrprgrmMap.getErpmprogram().getErpmsubmodule().getErpmSubModuleId());

            GfrProgramMappingList = GfrProgramMappingDao.findByProgramId(getVarerpmprgmId());
            gfrMasterList = gfrMasterDao.findListOfgfrMasterForGfrMapping(getVarerpmprgmId());

            erpmsmList = erpmsmDao.findAllParentMenu();
            erpmprgmList = erpmprgmDao.findItemsBySubModuleId(getVarerpmSubModuleId());


            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Done method" + e.getMessage();
            return ERROR;
        }
    }

    public String Exclude() throws Exception {
        try {

            GfrProgramMapping gfrprgrmMap1 = new GfrProgramMapping();
            gfrprgrmMap = GfrProgramMappingDao.findBygfrProgramMappingId(getGfrMappingId());
            GfrProgramMappingDao.delete(gfrprgrmMap);

            GfrProgramMappingList = GfrProgramMappingDao.findByProgramId(getVarerpmprgmId());
            gfrMasterList = gfrMasterDao.findListOfgfrMasterForGfrMapping(getVarerpmprgmId());

            erpmsmList = erpmsmDao.findAllParentMenu();
            erpmprgmList = erpmprgmDao.findItemsBySubModuleId(getVarerpmSubModuleId());

//            gfrprgrmMap.getErpmprogram().getErpmsubmodule().setErpmSubModuleId(getVarerpmSubModuleId());
//            gfrprgrmMap.getErpmprogram().setErpmpId(getVarerpmprgmId());

            message = " Excluded successfully.";

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Exclude method" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Include() throws Exception {
        try {


            gfrMaster = gfrMasterDao.findBygrfMasterId(getGfrMasterId());
            gfrprgrmMap.setGfrMaster(gfrMaster);
            erpmprogram = erpmprgmDao.findByErpmId(getVarerpmprgmId());
            gfrprgrmMap.setErpmprogram(erpmprogram);
            GfrProgramMappingDao.save(gfrprgrmMap);

            GfrProgramMappingList = GfrProgramMappingDao.findByProgramId(gfrprgrmMap.getErpmprogram().getErpmpId());
            gfrMasterList = gfrMasterDao.findListOfgfrMasterForGfrMapping(gfrprgrmMap.getErpmprogram().getErpmpId());


            erpmsmList = erpmsmDao.findAllParentMenu();
            erpmprgmList = erpmprgmDao.findItemsBySubModuleId(getVarerpmSubModuleId());

//            gfrprgrmMap.getErpmprogram().setErpmpId(getVarerpmprgmId());
//            gfrprgrmMap.getErpmprogram().getErpmsubmodule().setErpmSubModuleId(getVarerpmSubModuleId());

            message = "Record saved successfully.";

            return SUCCESS;
        } catch (Exception e) {

            message = "Exception in Save Method " + e.getMessage() + e.getCause();
            return ERROR;
        }
    }
}
