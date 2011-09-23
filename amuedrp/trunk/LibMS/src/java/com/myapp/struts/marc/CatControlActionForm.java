/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.marc;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author EdRP-05
 */
public class CatControlActionForm extends org.apache.struts.action.ActionForm {

    private String fix_data=null;
private String phy_desc=null;
private String d_t_l_t=null;
private String control_no_id=null;
private String control_no=null;
private String leader=null;
private String zclick=null;

    public String getZclick() {
        return zclick;
    }

    public void setZclick(String zclick) {
        this.zclick = zclick;
    }
    public String getControl_no() {
        return control_no;
    }

    public void setControl_no(String control_no) {
        this.control_no = control_no;
    }

    public String getControl_no_id() {
        return control_no_id;
    }

    public void setControl_no_id(String control_no_id) {
        this.control_no_id = control_no_id;
    }

    public String getD_t_l_t() {
        return d_t_l_t;
    }

    public void setD_t_l_t(String d_t_l_t) {
        this.d_t_l_t = d_t_l_t;
    }

    public String getFix_data() {
        return fix_data;
    }

    public void setFix_data(String fix_data) {
        this.fix_data = fix_data;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getPhy_desc() {
        return phy_desc;
    }

    public void setPhy_desc(String phy_desc) {
        this.phy_desc = phy_desc;
    }
    @Override
  public void reset(ActionMapping mapping, HttpServletRequest request) {
  this.control_no=null;
  this.control_no_id=null;
  this.d_t_l_t=null;
  this.fix_data=null;
  this.leader=null;
  this.phy_desc=null;
  this.zclick=null;
  }

}
