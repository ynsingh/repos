package org.nmeict.smvdu.Beans.SpringClassFile;

import java.util.List;

import org.nmeict.smvdu.Beans.OtherFeeHeadMaster;

/*
 *@author SHAISTA BANO
 */

public interface IOrgOtherFeeHeadMasterService {
	/**
	  * Add New OtherFeeHeadMaster
	  *
	  * @param  OtherFeeHeadMaster otherFeeHeadCode
	  */
 public void addNewFeeHeadCode(OtherFeeHeadMaster otherFeeHeadCode);
 
 
 /**
	  * Update OtherFeeHeadMaster
	  *
	  * @param  OtherFeeHeadMaster otherFeeHeadMaster
	  */
 public void updateOtherFeeHeadCode(List<OtherFeeHeadMaster> otherFeeHeadCode);
 
 
 
 /**
	  * 
	  *
	  * Load All OtherFeeHeadMaster For A Organisation
	  */
 
 
 
 public List<OtherFeeHeadMaster> loadOtherFeeHeadCode();
 
 
    /**
	  * Search OtherFeeHeadMaster
	  *
	  * @param  String otherFeeHeadCode
	  */

  public List<OtherFeeHeadMaster> loadFeeHeadCode(int departmentCode, int degreeCode, int branchCode, int semCode);
 
 
    /**
	  * Search OtherFeeHeadMaster
	  *
	  * @param  String otherFeeHeadCode
	  */
 
 public OtherFeeHeadMaster searchOtherFeeHeadCode(String orgEntity);
 
 
    /**
        *Delete OtherFeeHeadMaster
        *
        * @param  OtherFeeHeadMaster otherFeeHeadMaster
        */
    public Exception deleteFeeHeadCode(OtherFeeHeadMaster ofhm);

}
