/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Dropdown;

import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.IGNOU.ePortfolio.DAO.CountryCityDao;
import org.IGNOU.ePortfolio.Model.City;

/**
 *
 * @author Amit
 */
public class CityAction extends ActionSupport {

    private static final long serialVersionUID = -2223948287805083119L;
    private Map<String, String> cityMap = null;
    private CountryCityDao coDao = new CountryCityDao();
    private String CountryCode;
    private List<City> cityList;

    @Override
    public String execute() {
        cityList = coDao.CityList(CountryCode);
        cityMap = new HashMap<String, String>();

        for (int i = 0; i < cityList.size(); i++) {
            cityMap.put(cityList.get(i).getName(), cityList.get(i).getName());
        }

        return SUCCESS;
    }

    public String getJSON() {
        return execute();
    }

    /**
     * @return the coDao
     */
    public CountryCityDao getCoDao() {
        return coDao;
    }

    /**
     * @param coDao the coDao to set
     */
    public void setCoDao(CountryCityDao coDao) {
        this.coDao = coDao;
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
     * @return the cityList
     */
    public List<City> getCityList() {
        return cityList;
    }

    /**
     * @return the cityMap
     */
    public Map<String, String> getCityMap() {
        return cityMap;
    }
}
