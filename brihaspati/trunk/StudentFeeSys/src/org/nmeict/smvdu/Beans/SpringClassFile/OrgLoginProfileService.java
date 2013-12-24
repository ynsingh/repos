/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmeict.smvdu.Beans.SpringClassFile;

import org.nmeict.smvdu.Beans.OrgLoginDetails;
import org.nmeict.smvdu.Beans.OrgProfile;


public class OrgLoginProfileService implements IOrgLoginProfile{

    private OrgLoginProfileDAO orgLoginProfileDAO = new OrgLoginProfileDAO();

    public OrgLoginProfileDAO getOrgLoginProfileDAO() {
        return orgLoginProfileDAO;
    }

    public void setOrgLoginProfileDAO(OrgLoginProfileDAO orgLoginProfileDAO) {
        this.orgLoginProfileDAO = orgLoginProfileDAO;
    }
    
    
    @Override
    public boolean validateOrgUser(OrgLoginDetails orgLoginDetails) {
        return orgLoginProfileDAO.validateOrgUser(orgLoginDetails);
    }

	@Override
	public OrgProfile loadAllDetails(String orgId) {
		return orgLoginProfileDAO.loadAllDetails(orgId);
	}
    
}
