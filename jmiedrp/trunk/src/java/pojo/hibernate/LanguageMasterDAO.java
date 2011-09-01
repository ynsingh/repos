/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author afreen
 */

package pojo.hibernate;
import utils.BaseDAO;
import java.util.List;
import java.util.Date;



public class LanguageMasterDAO  extends BaseDAO {


    public List<LanguageMaster> findAll() {
        beginTransaction();
        List<LanguageMaster> list = getSession().createQuery("select u from LanguageMaster u").list();
        commitTransaction();
        return list;
    }



}
