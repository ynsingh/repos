
package pojo.hibernate;

import utils.BaseDAO;
import java.util.List;

public class ErpmprogramDAO extends BaseDAO {
    public void save(Erpmprogram erpmp) {
        try {
            beginTransaction();
            getSession().save(erpmp);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

     public void update(Erpmprogram erpmp) {
        try {
            beginTransaction();
            getSession().update(erpmp);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void delete(Erpmprogram erpmp) {
        try {
            beginTransaction();
            getSession().delete(erpmp);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }


    public List<Erpmprogram> findByErpmmId(Byte erpmmId) {
        beginTransaction();
        List<Erpmprogram> list = getSession().createQuery("select u from Erpmprogram u where u.erpmmodule.erpmmId = :erpmmId").setParameter("erpmmId", erpmmId).list();
        commitTransaction();
        return list;
    }

    //The following method prepares a list of programs which have not yet been assigned to a given Instititution Role
    public List<Erpmprogram> findRemainingProgramsForInstitute(Byte erpmmId, Integer InstitutionRole) {
        beginTransaction();
        List<Erpmprogram> list = getSession().createQuery("select u from Erpmprogram u where u.erpmmodule.erpmmId = :erpmmId "
                                                    +     "and u.erpmpId not in (select v.erpmprogram.erpmpId from Institutionroleprivileges v "
                                                    +     "where v.institutionuserroles.iurId = :InstitutionRole)")
                                                    .setParameter("erpmmId", erpmmId)
                                                    .setParameter("InstitutionRole", InstitutionRole)
                                                    .list();
        commitTransaction();
        return list;
    }


}
