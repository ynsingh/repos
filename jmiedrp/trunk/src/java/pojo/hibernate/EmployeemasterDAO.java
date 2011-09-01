/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo.hibernate;




import utils.BaseDAO;
import java.util.List;


/**
 *
 * @author afreen
 */
public class EmployeemasterDAO  extends BaseDAO {
 public void save(Employeemaster  emp) {
        try {
            beginTransaction();
            getSession().save(emp);
            commitTransaction();
            }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;

        }
    }

    public void update(Employeemaster emp) {
        try {
            beginTransaction();
            getSession().update(emp);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void delete(Employeemaster emp) {
        try {
            beginTransaction();
            getSession().delete(emp);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public List<Employeemaster> findAll() {
        beginTransaction();
        List<Employeemaster> list = getSession().createQuery("select u from Employeemaster  u").list();
        commitTransaction();
        return list;
    }

    public Employeemaster findByempId(short empId)
    {
        beginTransaction();
        Employeemaster  emp  = (Employeemaster) getSession().load(Employeemaster.class , empId);
        commitTransaction();
        return emp;
    }
 public List<Employeemaster> findByImId(Short imId) {
        beginTransaction();
        List<Employeemaster> empList  =  getSession().createQuery("Select u from Employeemaster u where u.institutionmaster.imId = :imId").setParameter("imId", imId).list();
        commitTransaction();
        return empList;
   }
public List<Employeemaster> findBySImId(int simId) {
        beginTransaction();
        List<Employeemaster> empList  =  getSession().createQuery("Select u from Employeemaster u where u.subinstitutionmaster.simId = :simId").setParameter("simId", simId).list();
        commitTransaction();
        return empList;
   }

public List<Employeemaster> findByDmId(int dmId) {
        beginTransaction();
        List<Employeemaster> empList  =  getSession().createQuery("Select u from Employeemaster u where u.departmentmaster.dmId = :dmId").setParameter("dmId", dmId).list();
        commitTransaction();
        return empList;
   }




}
