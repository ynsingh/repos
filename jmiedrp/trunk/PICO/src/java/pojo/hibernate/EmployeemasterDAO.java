/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo.hibernate;

import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import java.util.List;


public class EmployeemasterDAO  {
 public void save(Employeemaster  emp) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(emp);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(emp != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public void update(Employeemaster emp) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(emp);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(emp != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public void delete(Employeemaster emp) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(emp);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(emp != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public List<Employeemaster> findAll() {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Employeemaster> list = session.createQuery("select u from Employeemaster u").list();
            return list;
        }
        finally {
            session.close();
            }
    }

    public Employeemaster findByempId(int empId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Employeemaster  emp  = (Employeemaster) session.load(Employeemaster.class , empId);
            Hibernate.initialize(emp.getInstitutionmaster());
            Hibernate.initialize(emp.getSubinstitutionmaster());
            return emp;
        }
        finally {
            session.close();
            }
    }

    public Employeemaster findByEmp_Id(int empId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Employeemaster>  emp  = session.createQuery("Select u from Employeemaster u where u.empId = :empId").setParameter("empId", empId).list();
            return emp.get(0);
        }
        finally {
            session.close();
            }
    }

    public List<Employeemaster> findByImId(Short imId) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<Employeemaster> empList  =  session.createQuery("Select u from Employeemaster u where u.institutionmaster.imId = :imId").setParameter("imId", imId).list();
            for (index=0; index < empList.size(); ++index) {
                Hibernate.initialize(empList.get(index).getInstitutionmaster());
                Hibernate.initialize(empList.get(index).getSubinstitutionmaster());
                Hibernate.initialize(empList.get(index).getDepartmentmaster());
                Hibernate.initialize(empList.get(index).getErpmGenMaster());
            }
            
            return empList;
        }
        finally {
            session.close();
            }
       }

    public List<Employeemaster> findBySImId(int simId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Employeemaster> empList  =  session.createQuery("Select u from Employeemaster u where u.subinstitutionmaster.simId = :simId").setParameter("simId", simId).list();
            return empList;
        }
        finally {
            session.close();
            }
   }

    public List<Employeemaster> findByDmId(int dmId) {

        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Employeemaster> empList  =  session.createQuery("Select u from Employeemaster u where u.departmentmaster.dmId = :dmId").setParameter("dmId", dmId).list();
            return empList;
        }
        finally {
            session.close();
            }
    }

    public List<Employeemaster> findAllEmployeesForUserInstitutions(Integer user) {

        String SQL = "Select u from Employeemaster u where u.institutionmaster.imId in ( " +
                       "Select v.institutionmaster.imId from Erpmuserrole v where v.erpmusers.erpmuId = :user)";
                
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Employeemaster> empList  = session.createQuery(SQL).setParameter("user", user).list();
            return empList;
        }
        finally {
            session.close();
            }
        }
        

public Employeemaster findForDepartmentBySimId(int empId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
	        List<Employeemaster> dba  = session.createQuery("Select u from Employeemaster u where u.empId = :empId").setParameter("empId", empId).list();
	        return dba.get(0);
        }
        finally {
            session.close();
            }
        }

public List<Employeemaster> findEmployeeForUser(Integer erpmuId,Integer dmId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
        List<Employeemaster> dimList = session.createQuery("select distinct(u) from Employeemaster u, Erpmuserrole r where r.erpmusers.erpmuId = :erpmuId and r.employeemaster.dmId = u.dmId and u.departmentmaster.dmId =:dmId").setParameter("erpmuId", erpmuId).setParameter("dmId",dmId).list();
        return dimList;
        }
        finally {
            session.close();
            }
    }

}
//
//
///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package pojo.hibernate;
//
//
//
//
//import utils.BaseDAO;
//import java.util.List;
//
//
///**
// *
// * @author afreen
// */
//public class EmployeemasterDAO  extends BaseDAO {
// public void save(Employeemaster  emp) {
//        try {
//            beginTransaction();
//            getSession().save(emp);
//            commitTransaction();
//            }
//        catch (RuntimeException re) {
//            re.printStackTrace();
//            throw re;
//
//        }
//
// }
//
//    public void update(Employeemaster emp) {
//        try {
//            beginTransaction();
//            getSession().update(emp);
//            commitTransaction();
//        }
//        catch (RuntimeException re) {
//            re.printStackTrace();
//            throw re;
//        }
//    }
//
//    public void delete(Employeemaster emp) {
//        try {
//            beginTransaction();
//            getSession().delete(emp);
//            commitTransaction();
//        }
//        catch (RuntimeException re) {
//            re.printStackTrace();
//            throw re;
//        }
//    }
//
//    public List<Employeemaster> findAll() {
//        beginTransaction();
//        List<Employeemaster> list = getSession().createQuery("select u from Employeemaster u").list();
//        commitTransaction();
//        return list;
//    }
//
//    public Employeemaster findByempId(int empId)
//    {
//        beginTransaction();
//        Employeemaster  emp  = (Employeemaster) getSession().load(Employeemaster.class , empId);
//        commitTransaction();
//        return emp;
//    }
// public List<Employeemaster> findByImId(Short imId) {
//        beginTransaction();
//        List<Employeemaster> empList  =  getSession().createQuery("Select u from Employeemaster u where u.institutionmaster.imId = :imId").setParameter("imId", imId).list();
//        commitTransaction();
//        return empList;
//   }
//public List<Employeemaster> findBySImId(int simId) {
//        beginTransaction();
//        List<Employeemaster> empList  =  getSession().createQuery("Select u from Employeemaster u where u.subinstitutionmaster.simId = :simId").setParameter("simId", simId).list();
//        commitTransaction();
//        return empList;
//   }
//
//public List<Employeemaster> findByDmId(int dmId) {
//        beginTransaction();
//        List<Employeemaster> empList  =  getSession().createQuery("Select u from Employeemaster u where u.departmentmaster.dmId = :dmId").setParameter("dmId", dmId).list();
//        commitTransaction();
//        return empList;
//   }
//
//public List<Employeemaster> findAllEmployeesForUserInstitutions(Integer user) {
//
//    String SQL = "Select u from Employeemaster u where u.institutionmaster.imId in ( " +
//                        "Select v.institutionmaster.imId from Erpmuserrole v where v.erpmusers.erpmuId = :user)";
//        beginTransaction();
//        List<Employeemaster> empList  = getSession().createQuery(SQL).setParameter("user", user).list();
//        commitTransaction();
//
//        return empList;
//
//        }
//
//
//
//
//
//}
