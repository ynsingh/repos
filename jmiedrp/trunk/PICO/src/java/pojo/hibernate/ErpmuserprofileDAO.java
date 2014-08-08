/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo.hibernate;

/**
 *
 * @author kazim
 */
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ErpmuserprofileDAO {

    public List<Erpmuserprofile> RetrieveUserProfile(String ERPMU_Name, String ERPMU_Password)
    {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
                    List<Erpmuserprofile> list  = session.createQuery("Select u from Erpmuserprofile u").list();
            return list;
        }
        catch (RuntimeException re) {
                throw re;
        }
        finally {
            session.close();
            }
        }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//package pojo.hibernate;
//
///**
// *
// * @author kazim
// */
//import utils.BaseDAO;
//import java.util.List;
//
//public class ErpmuserprofileDAO extends BaseDAO {
//
//    public List<Erpmuserprofile> RetrieveUserProfile(String ERPMU_Name, String ERPMU_Password)
//    {
//        beginTransaction();
//        List<Erpmuserprofile> list  = getSession().createQuery("Select u from Erpmuserprofile u").list();
//        commitTransaction();
//        return list;
//    }
//
//}
