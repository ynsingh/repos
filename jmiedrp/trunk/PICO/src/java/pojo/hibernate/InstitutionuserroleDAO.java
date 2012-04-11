/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo.hibernate;

/**
 *
 * @author kazim
 */
import utils.BaseDAO;
import java.util.List;


public class InstitutionuserroleDAO  extends BaseDAO {

    public void save(Institutionuserroles iur) {
        try {
            beginTransaction();
            getSession().save(iur);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void update(Institutionuserroles iur) {
        try {
            beginTransaction();
            getSession().update(iur);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void delete(Institutionuserroles iur) {
        try {
            beginTransaction();
            getSession().delete(iur);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public List<Institutionuserroles> findAll() {
        beginTransaction();
        List<Institutionuserroles> list = getSession().createQuery("select u from Institutionuserroles u").list();
        commitTransaction();
        return list;
    }

    public Institutionuserroles findByIURId(Integer IUR_ID) {
        beginTransaction();
        Institutionuserroles iur  =  (Institutionuserroles) getSession().load(Institutionuserroles.class , IUR_ID);
        commitTransaction();
        return iur;
    }

    public List<Institutionuserroles> findByInstitutionId(Short IUR_IM_ID) {
        beginTransaction();
        List<Institutionuserroles> iurList  =  getSession().createQuery("select u from Institutionuserroles u where u.institutionmaster.imId = :iurimid ").
                                                setParameter("iurimid",IUR_IM_ID).list();
        commitTransaction();
        return iurList;
    }

    public Integer  copyGenericRolePrivileges(Byte gurId, Integer iurId, Short imId) {
        beginTransaction();
        Integer i = getSession().createSQLQuery("call copy_genericroleprivileges(:gurId,:iurId, :imId)")
                                .setParameter("gurId", gurId)
                                .setParameter("iurId", iurId)
                                .setParameter("imId", imId)
                                .executeUpdate();
        commitTransaction();
        return i;
    }

public Institutionuserroles findInstitutionAdministrator(Short imId) {
        beginTransaction();
        List<Institutionuserroles> iur  =  getSession().createQuery("select u from Institutionuserroles u where u.institutionmaster.imId = :imId and upper(u.iurName) = 'INSTITUTION ADMINISTRATOR'").
                                                setParameter("imId",imId).list();
        commitTransaction();
        return iur.get(0);
}
}

