package in.ac.dei.edrp.server;

import in.ac.dei.edrp.client.CMaddMarksInfoGetter;
import in.ac.dei.edrp.server.SharedFiles.Log4JInitServlet;
import in.ac.dei.edrp.server.SharedFiles.SqlMapManager;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;


/**
 * file updated
 * @author Ashish
 *
 */
public class Idgetter {
    SqlMapClient client = SqlMapManager.getSqlMapClient();
    Log4JInitServlet logObj = new Log4JInitServlet();

    /**
     * method updated by devendra May 3rd
     */
    public boolean validateRegistrationNumber(String entity_id,
        String program_id, String regnumber) {
        boolean b = true;

        try {
            CMaddMarksInfoGetter[] getDataObject = null;
            CMaddMarksInfoGetter addMarksObject = new CMaddMarksInfoGetter();
            addMarksObject.setProgram_id(program_id);
//            addMarksObject.setBranch_id(branch_code);
            addMarksObject.setEntity_id(entity_id);
            addMarksObject.setRegistration_number(regnumber);
//            addMarksObject.setSpecializationCode(specialization_code);

            /**
             * query updated
             */
            List<?> regNoCount = client.queryForList("getRegCount",
                    addMarksObject);

            getDataObject = regNoCount.toArray(new CMaddMarksInfoGetter[regNoCount.size()]);

            for (int i = 0; i < getDataObject.length; i++) {
                if (getDataObject[i].getTotal_marks() >= 1) {
                    b = false;
                }
            }
        } catch (Exception e) {
            System.out.println("Error message in getValidation " +
                e.getMessage());
            logObj.logger.error("Error message in getValidation " +
                e.getMessage());
        }

        return b;
    }

    /**
     * method updated by Devendra May 3rd
     */
    public String[] getTestCosForReg(String entity_id, String program_id,String regnumber) {
        String[] val = new String[2];

        try {
            CMaddMarksInfoGetter[] getDataObject = null;
            CMaddMarksInfoGetter addMarksObject = new CMaddMarksInfoGetter();
            addMarksObject.setProgram_id(program_id);
//            addMarksObject.setBranch_id(branch_code);
            addMarksObject.setEntity_id(entity_id);
            addMarksObject.setRegistration_number(regnumber);
//            addMarksObject.setSpecializationCode(specialization_code);

            /**
             * query updated
             */
            List<?> getCos = client.queryForList("getCosForTestNo",
                    addMarksObject);
            getDataObject = getCos.toArray(new CMaddMarksInfoGetter[getCos.size()]);
            val[0] = getDataObject[0].getTest_number();
            val[1] = getDataObject[0].getCos_value();
        } catch (Exception e) {
            System.out.println("Error message in getTestCosForReg " +
                e.getMessage());
            logObj.logger.error("Error message in getTestCosForReg " +
                e.getMessage());
        }

        return val;
    }
}
