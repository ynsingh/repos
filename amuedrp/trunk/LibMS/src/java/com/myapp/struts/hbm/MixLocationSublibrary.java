/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.hbm;

/**
 *
 * @author EdRP-05
 */
public class MixLocationSublibrary implements java.io.Serializable{
private String locationId;
private String locationName;
private String sublibraryId;
private String sublibraryName;

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getSublibraryId() {
        return sublibraryId;
    }

    public void setSublibraryId(String sublibraryId) {
        this.sublibraryId = sublibraryId;
    }

    public String getSublibraryName() {
        return sublibraryName;
    }

    public void setSublibraryName(String sublibraryName) {
        this.sublibraryName = sublibraryName;
    }
}
