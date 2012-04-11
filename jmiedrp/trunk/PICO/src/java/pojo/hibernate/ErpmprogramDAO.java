
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


    public List<Erpmprogram> findByErpmmId(Integer erpmSubModuleId) {
        beginTransaction();
        List<Erpmprogram> list = getSession().createQuery("select u from Erpmprogram u where u.erpmsubmodule.erpmSubModuleId = :erpmSubModuleId").setParameter("erpmSubModuleId", erpmSubModuleId).list();
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

    public List<Erpmprogram> findFirstLevelItemsBySubModuleId(Integer erpmSubModuleId, Integer erpmuId) {

    String SQL =    "Select a from Erpmprogram a, Institutionroleprivileges b, Institutionuserroles c, Erpmuserrole d "
                        + "where  b.erpmprogram.erpmpId = a.erpmpId and "
                        + "b.institutionuserroles.iurId = c.iurId and "
                        + "c.iurId = d.institutionuserroles.iurId and "
                        + "a.erpmsubmodule.erpmSubModuleId = :erpmSubModuleId and "
                        + "d.erpmusers.erpmuId = :erpmuId and "
                        + "d.erpmurDefault = 't' and "
                        + "a.erpmprogram.erpmpId is null order by a.erpmpOrder";


        beginTransaction();
        List<Erpmprogram> list = getSession().createQuery(SQL).
                                 setParameter("erpmSubModuleId", erpmSubModuleId).
                                 setParameter("erpmuId",erpmuId).list();
        commitTransaction();
        return list;
    }

    public List<Erpmprogram> findSecondLevelItemsBySubModuleId(Integer erpmSubModuleId, Short erpmpId) {
        beginTransaction();
        List<Erpmprogram> list = getSession().createQuery("select u from Erpmprogram u where u.erpmsubmodule.erpmSubModuleId = :erpmSubModuleId and u.erpmprogram.erpmpId = :erpmpId order by erpmpOrder").
                                 setParameter("erpmSubModuleId", erpmSubModuleId).setParameter("erpmpId", erpmpId).list();
        commitTransaction();
        return list;
    }


}
