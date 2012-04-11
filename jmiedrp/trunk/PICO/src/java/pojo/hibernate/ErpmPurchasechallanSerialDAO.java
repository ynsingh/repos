/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo.hibernate;
import utils.BaseDAO;
import java.util.List;
import java.util.*;


/**
 *
 * @author erp05
 */
public class ErpmPurchasechallanSerialDAO extends BaseDAO {

    public void save(ErpmPurchasechallanSerial PCSerial) {
        try {
            beginTransaction();
            getSession().save(PCSerial);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;    }
    }
public void delete(ErpmPurchasechallanSerial PCSerial) {
        try {
            beginTransaction();
            getSession().delete(PCSerial);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }
 public void update(ErpmPurchasechallanSerial PCSerial) {
        try {
            beginTransaction();
            getSession().update(PCSerial);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }


}
