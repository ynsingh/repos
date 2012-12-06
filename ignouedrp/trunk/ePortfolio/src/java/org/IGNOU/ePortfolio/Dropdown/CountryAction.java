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
import org.IGNOU.ePortfolio.Model.Country;

/**
 *
 * @author Amit
 */
public class CountryAction extends ActionSupport {
   
    private static final long serialVersionUID = -2223948287805083119L;
    private Map<String, String> countryMap=null;
    private CountryCityDao coDao=new CountryCityDao();
    private List<Country> countryList;
    
     
    @Override
    public String execute() {
       
        countryList=coDao.CountryList();
         countryMap = new HashMap<String, String>();
         for(int i=0; i<countryList.size();i++)
        {
           countryMap.put(countryList.get(i).getCode(),countryList.get(i).getName());
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
     * @return the countryList
     */
    public List<Country> getCountryList() {
        return countryList;
    }

    /**
     * @return the countryMap
     */
    public Map<String, String> getCountryMap() {
        return countryMap;
    }

}
