package org.nmeict.smvdu.Beans.SpringClassFile;

import java.util.List;

import org.nmeict.smvdu.Beans.BranchMaster;

public class OrgBranchService implements IOrgBranchService{

	
	private OrgBranchDAO orgBranchDAO = new OrgBranchDAO();
	
	
	public OrgBranchDAO getOrgBranchDAO() {
		return orgBranchDAO;
	}

	public void setOrgBranchDAO(OrgBranchDAO orgBranchDAO) {
		this.orgBranchDAO = orgBranchDAO;
	}

	@Override
	public void addOrgBranch(BranchMaster branchMaster) {
		getOrgBranchDAO().addOrgBranch(branchMaster);
		
	}

	@Override
	public void update(List<BranchMaster> branchMasterList) {
			getOrgBranchDAO().update(branchMasterList);
		
	} 

	@Override
	public List<BranchMaster> loadAllBranchList(int bCode) {
		return getOrgBranchDAO().loadAllBranchList(bCode); 
	}
    
        @Override
        public void deleteOrgBranch(BranchMaster branchMaster) {
            getOrgBranchDAO().deleteOrgBranch(branchMaster);
        }
            
        
}
