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
public interface IOrgLogoProfile {
    public List<OrgLogoDetails> AllCollegeLogoDetails(String query);
}
