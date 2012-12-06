/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Model;

/**
 *
 * @author IGNOU Team
 * @version 1
 * @since 22-12-2011
 */
public class RoleModel {

    private String rolename;
    private String roleId;

    public RoleModel(String rolename, String roleId) {
        this.rolename = rolename;
        this.roleId = roleId;
    }

    public RoleModel() {
    }

    /**
     * @return the rolename
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * @param rolename the rolename to set
     */
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    /**
     * @return the roleId
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
