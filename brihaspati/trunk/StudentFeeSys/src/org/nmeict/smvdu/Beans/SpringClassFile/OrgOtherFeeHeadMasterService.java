package org.nmeict.smvdu.Beans.SpringClassFile;

import java.util.List;

import org.nmeict.smvdu.Beans.OtherFeeHeadMaster;

/*
 * @author Shaista Bano
 */

public class OrgOtherFeeHeadMasterService implements IOrgOtherFeeHeadMasterService{

	private OrgOtherFeeHeadMasterDAO orgOtherFeeHeadMasterDAO = new OrgOtherFeeHeadMasterDAO();
	
	
	
	public OrgOtherFeeHeadMasterDAO getOrgOtherFeeHeadMasterDAO() {
		return orgOtherFeeHeadMasterDAO;
	}

	public void setOrgOtherFeeHeadMasterDAO(
			OrgOtherFeeHeadMasterDAO orgOtherFeeHeadMasterDAO) {
		this.orgOtherFeeHeadMasterDAO = orgOtherFeeHeadMasterDAO;
	}

	@Override
	public void addNewFeeHeadCode(OtherFeeHeadMaster otherFeeHeadCode) {
		
		getOrgOtherFeeHeadMasterDAO().addNewFeeHeadCode(otherFeeHeadCode);
		
	}

	

	@Override
	public List<OtherFeeHeadMaster> loadOtherFeeHeadCode() {
		// TODO Auto-generated method stub
		return getOrgOtherFeeHeadMasterDAO().loadOtherFeeHeadCode(); 
	}
        
        @Override
	public List<OtherFeeHeadMaster> loadFeeHeadCode(int departmentCode, int degreeCode, int branchCode, int semCode) {
		// TODO Auto-generated method stub
		return getOrgOtherFeeHeadMasterDAO().loadFeeHeadCode(departmentCode, degreeCode, branchCode, semCode); 
	}
        
	@Override
	public OtherFeeHeadMaster searchOtherFeeHeadCode(String orgEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateOtherFeeHeadCode(List<OtherFeeHeadMaster> otherFeeHeadCode) {
		getOrgOtherFeeHeadMasterDAO().updateOtherFeeHeadCode(otherFeeHeadCode);
		
	}
       
	public Exception deleteFeeHeadCode(OtherFeeHeadMaster otherFeeHeadCode) {
		
		return getOrgOtherFeeHeadMasterDAO().deleteFeeHeadCode(otherFeeHeadCode);
		
	}

}
