/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmeict.smvdu.Beans.SpringClassFile;

import java.util.List;
import org.nmeict.smvdu.Beans.OrgProfile;

/**
 *
 * OrgProfileService Class is created by implementing IOrgProfileService Interface.
 */

public class OrgProfileService implements IOrgProfileService{


    private OrgProfileDAO orgProfileDAO = new OrgProfileDAO();

    public OrgProfileDAO getOrgProfileDAO() {
        return orgProfileDAO;
    }

    public void setOrgProfileDAO(OrgProfileDAO orgProfileDAO) {
        this.orgProfileDAO = orgProfileDAO;
    }
    


    @Override
	public void addNewOrgProfile(OrgProfile orgProfile) {
        try
        {
            //System.out.println("...."+orgProfile.getOrgName()+"..."+orgProfile.getOrgId());
            getOrgProfileDAO().addNewOrgProfile(orgProfile); 
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    

    @Override
	public void updateOrgProfile(OrgProfile orgProfile) {
        getOrgProfileDAO().updateOrgProfile(orgProfile);
    }


    @Override
	public List<OrgProfile> loadOrgProfile() {
        //throw new UnsupportedOperationException("Not supported yet.");
	return getOrgProfileDAO().loadOrgProfile();
    }


    @Override
	public OrgProfile searchOrgProfile(String orgEntity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
