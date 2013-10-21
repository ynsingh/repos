package pojo.hibernate;

import utils.HibernateUtil;
import org.hibernate.Session;
import java.util.List;

public class LanguageMasterDAO {


    public List<LanguageMaster> findAll() {

        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<LanguageMaster> list = session.createQuery("select u from LanguageMaster u").list();
            return list;
        }
        finally {
            session.close();
            }
        }
    }


//
///**
// *
// * @author afreen
// */
//
//package pojo.hibernate;
//import utils.BaseDAO;
//import java.util.List;
//import java.util.Date;
//
//
//
//public class LanguageMasterDAO  extends BaseDAO {
//
//
//    public List<LanguageMaster> findAll() {
//        beginTransaction();
//        List<LanguageMaster> list = getSession().createQuery("select u from LanguageMaster u").list();
//        commitTransaction();
//        return list;
//    }
//
//
//
//}
