/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmeict.smvdu.Beans.SpringClassFile;

import java.util.List;
import org.nmeict.smvdu.Beans.OrgLogoDetails;

/**
 *
 * @author KESU
 */
public class OrgLogoProfileService implements IOrgLogoProfile{

    private OrgLogoProfileDAO orgLogoProfileDAO = new OrgLogoProfileDAO();

    public OrgLogoProfileDAO getOrgLogoProfileDAO() {
        return orgLogoProfileDAO;
    }

    public void setOrgLogoProfileDAO(OrgLogoProfileDAO orgLogoProfileDAO) {
        this.orgLogoProfileDAO = orgLogoProfileDAO;
    }
    
    
    
    
    @Override
    public List<OrgLogoDetails> AllCollegeLogoDetails(String query) {
       return orgLogoProfileDAO.AllCollegeLogoDetails(query);
    }
    
}
