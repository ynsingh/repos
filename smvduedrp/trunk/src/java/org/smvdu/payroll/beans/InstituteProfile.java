/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import java.io.File;
import org.smvdu.payroll.beans.db.OrgProfileDB;

/**
 *
 * @author Algox
 */
public class InstituteProfile {
    
    private String name;
    private String error;
    private String website;

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
    

    public InstituteProfile()
    {
       name =  new OrgProfileDB().getProfile();
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    private File theFile;

    public File getTheFile() {
        return theFile;
    }

    public void setTheFile(File theFile) {
        this.theFile = theFile;
    }
    private String tagLine;

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }
    

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @param string
     */
    public void setName(String string) {
        name = string;
    }

   
   
}
