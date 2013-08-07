/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Registration;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.IGNOU.ePortfolio.DAO.InstituteDAO;
import org.IGNOU.ePortfolio.Model.Institute;

/**
 *
 * @author IGNOU Team
 * @version 1
 * @since 21-04-2012
 */
public class InstituteRegAction extends ActionSupport implements ModelDriven<Object> {

    Institute ins = new Institute();
    InstituteDAO dao = new InstituteDAO();
    private String msg, RegSuccess = getText("msg.infoSaved");
    private String CountryCode, city;

    public InstituteRegAction() {
    }

    @Override
    public String execute() throws Exception {
        ins.setCountry(CountryCode);
        ins.setCityPlace(city);
        dao.InstituteSave(ins);
        msg = RegSuccess;
        return SUCCESS;
    }

    @Override
    public Object getModel() {
        return ins;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the RegSuccess
     */
    public String getRegSuccess() {
        return RegSuccess;
    }

    /**
     * @param RegSuccess the RegSuccess to set
     */
    public void setRegSuccess(String RegSuccess) {
        this.RegSuccess = RegSuccess;
    }

    /**
     * @return the CountryCode
     */
    public String getCountryCode() {
        return CountryCode;
    }

    /**
     * @param CountryCode the CountryCode to set
     */
    public void setCountryCode(String CountryCode) {
        this.CountryCode = CountryCode;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }
}
