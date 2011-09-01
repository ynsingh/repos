/**
 *
 * @author sknaqvi
 */

package pojo.hibernate;

import utils.BaseDAO;
import java.util.List;

public class InstitutionroleprivilegesDAO  extends BaseDAO {

    public void save(Institutionroleprivileges irp) {
        try {
            beginTransaction();
            getSession().save(irp);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void update(Institutionroleprivileges irp) {
        try {
            beginTransaction();
            getSession().update(irp);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void delete(Institutionroleprivileges irp) {
        try {
            beginTransaction();
            getSession().delete(irp);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public List<Institutionroleprivileges> findAll() {
        beginTransaction();
        List<Institutionroleprivileges> list = getSession().createQuery("select u from Institutionroleprivileges u").list();
        commitTransaction();
        return list;
    }


    //The following function retrieves the Institution Role Privilege record by iupID (The primary key)
    public Institutionroleprivileges findByIupId(Short iupId) {
        beginTransaction();
        List<Institutionroleprivileges> list = getSession().createQuery("select u from Institutionroleprivileges u where u.iupId = :iupId")
                                                            .setParameter("iupId", iupId)
                                                            .list();
        commitTransaction();
        return list.get(0);
    }

    //The following function prepares a list of Institution Role Privileges for the given Institutional Role
    public List<Institutionroleprivileges> findByiurId(Integer iurId) {
        beginTransaction();
        List<Institutionroleprivileges> list = getSession().createQuery("select u from Institutionroleprivileges u where u.institutionuserroles.iurId = :iurId")
                                                            .setParameter("iurId", iurId)
                                                            .list();
        commitTransaction();
        return list;
    }

    //The following function returns the instittution role Id contained in IUP_ID
    public Integer findInstitutionRoleForUserProfile(Short iupId) {
        beginTransaction();
        List<Institutionroleprivileges> list  = getSession().createQuery("select u.institutionuserroles.iurId from Institutionroleprivileges u where u.iupId = :iupId)")
                                                            .setParameter("iupId", iupId).list();
        commitTransaction();
        return list.get(0).getInstitutionuserroles().getIurId();
    }

    public Integer CountRecordsForInstitutionRole(Integer iurId) {
        beginTransaction();
        String list = getSession().createQuery("select count(u) from Institutionroleprivileges u where u.institutionuserroles.iurId = :iurId")
                                                            .setParameter("iurId", iurId)
                                                            .uniqueResult().toString();
        commitTransaction();
        return Integer.parseInt(list);
    }
}