package in.ac.dei.edrp.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.ibatis.sqlmap.client.SqlMapClient;

import in.ac.dei.edrp.client.CM_boardNormalizationGetter;
import in.ac.dei.edrp.client.CM_entityInfoGetter;
import in.ac.dei.edrp.client.CM_progMasterInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.CM_boardConnect;
import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;

import java.util.List;


@SuppressWarnings("serial")
public class CM_boardImpl extends RemoteServiceServlet
    implements CM_boardConnect {
    SqlMapClient client = SqlMapManager.getSqlMapClient();
    Log4JInitServlet logObj = new Log4JInitServlet();

    /*
     * Method for getting list of entities offering particular program for setting board normalization factor
     */
    //Update by devendra june 8 
    public CM_entityInfoGetter[] methodGetProgOfferingEntityList() throws Exception {
        try {
            List<?> list = client.queryForList("progOfferingEntity");
            CM_entityInfoGetter[] result = (CM_entityInfoGetter[]) list.toArray(new CM_entityInfoGetter[list.size()]);
            return result;
        } catch (Exception e) {
            logObj.logger.error("Exception in methodgetEntityList" + e);
            throw new Exception(e);
        }
    }

    /*
     * Method for getting program list for setting board normalization factor
     */
  //Update by devendra june 8 
    public CM_progMasterInfoGetter[] methodprogList(String user_id,String entityId) {
        try {
            String university_id = user_id.substring(1, 5);
            CM_progMasterInfoGetter CMPMIG = new CM_progMasterInfoGetter();
            CMPMIG.setProgram_id(university_id + "%");
            CMPMIG.setBranchname("Y");
            CMPMIG.setEntity_id(entityId);
            List<?> list = client.queryForList("progListforBoard", CMPMIG);
            return list.toArray(new CM_progMasterInfoGetter[list.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in methodprogList " + e);
        }
        return null;
    }

    /*
     * Method for getting branch list for setting board normalization factor
     */
    public CM_progMasterInfoGetter[] methodbranchList(String program_id,
        String entity_id) {
        try {
            CM_progMasterInfoGetter CMPMIG = new CM_progMasterInfoGetter();
            CMPMIG.setProgram_id(program_id);
            CMPMIG.setCreator_id(entity_id);
            CMPMIG.setCategory_code(entity_id.substring(0, 4));

            List<?> list = client.queryForList("branchListforBoard", CMPMIG);

            return list.toArray(new CM_progMasterInfoGetter[list.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in methodbranchList " + e);

            //			throw new Exception(e);
        }

        return null;
    }

    /*
     * Method for getting component list for setting board normalization factor
     */
    public CM_boardNormalizationGetter[] methodComponentList(
        String program_id, String entity_id) {
        try {
            CM_progMasterInfoGetter CMPMIG = new CM_progMasterInfoGetter();
            CMPMIG.setProgram_id(program_id);
            CMPMIG.setCreator_id(entity_id);

            List<?> list = client.queryForList("compListforBoard", CMPMIG);

            return list.toArray(new CM_boardNormalizationGetter[list.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in methodComponentList " + e);

            //			throw new Exception(e);
        }

        return null;
    }

    /*
     * Method for getting list of boards for setting board normalization factor
     */
    public CM_boardNormalizationGetter[] methodBoardList() {
        try {
            List<?> list = client.queryForList("boardList", null);

            return list.toArray(new CM_boardNormalizationGetter[list.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in methodBoardList " + e);

            //			throw new Exception(e);
        }

        return null;
    }

    /*
     * Method for setting board normalization factor
     */
    public void methodAddBoardNormalizationFactor(
        CM_boardNormalizationGetter object) throws Exception {
        try {
            CM_boardNormalizationGetter[] duplicacy = methodboardListForManage(object);

            if (duplicacy.length > 0) {
                throw new Exception("This Record already exists");
            } else {
                client.insert("addBoardNormalizationFactor", object);
            }
        } catch (Exception e) {
            logObj.logger.error(
                "exception in methodAddBoardNormalizationFactor " + e);
            throw e;
        }
    }

    /*
     * Method for getting program list for setting board normalization factor for managing details
     */
    //Update by Devendra June 8
    public CM_progMasterInfoGetter[] methodprogListForManage(String user_id,String entityId) {
        try {
            String university_id = user_id.substring(1, 5);
            CM_progMasterInfoGetter CMPMIG = new CM_progMasterInfoGetter();
            CMPMIG.setProgram_id(university_id + "%");
            CMPMIG.setEntity_id(entityId);
            List<?> list = client.queryForList("progListforBoardforManage",CMPMIG);
            return list.toArray(new CM_progMasterInfoGetter[list.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in methodprogListForManage " + e);
        }
        return null;
    }

    /*
     * Method for getting board normalization factor list for managing details
     */
    public CM_boardNormalizationGetter[] methodboardListForManage(
        CM_boardNormalizationGetter object) {
        try {
            object.setUniversity_id(object.getProgram_id().substring(0, 4));

            List<?> list = client.queryForList("factorDetailList", object);
            return list.toArray(new CM_boardNormalizationGetter[list.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in methodboardListForManage " + e);

            //			throw new Exception(e);
        }

        return null;
    }

    /*
     * Method to update board normalization factor
     */
    public void methodNormalizationFactorUpdate(
        CM_boardNormalizationGetter object) {
        try {        	
            client.update("updateBoardFactor", object);
        } catch (Exception e) {
            logObj.logger.error("exception in methodNormalizationFactorUpdate " +
                e);

            //			throw new Exception(e);
        }
    }

    /*
     * Method to update board normalization factor
     */
    public void methodNormalizationFactorDelete(
        CM_boardNormalizationGetter object) {
        try {        	
            client.delete("deleteBoardFactor", object);
        } catch (Exception e) {
            logObj.logger.error("exception in methodNormalizationFactorDelete " +
                e);

            //			throw new Exception(e);
        }
    }

    @Override
    public CM_progMasterInfoGetter[] methodspecializationList(
        String program_id, String entity_id, String branch_id) {
        try {
            CM_progMasterInfoGetter CMPMIG = new CM_progMasterInfoGetter();
            CMPMIG.setProgram_id(program_id);
            CMPMIG.setCreator_id(entity_id);
            CMPMIG.setCategory_code(entity_id.substring(0, 4));
            CMPMIG.setBranchcode(branch_id);

            List<?> list = client.queryForList("specializationListforBoard",
                    CMPMIG);

            return list.toArray(new CM_progMasterInfoGetter[list.size()]);
        } catch (Exception e) {
            logObj.logger.error("exception in methodbranchList " + e);

            //			throw new Exception(e);
        }

        return null;
    }
}
