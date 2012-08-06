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

public class InstitutionmasterDAO extends BaseDAO {

    public void save(Institutionmaster im) {
        try {
            beginTransaction();
            getSession().save(im);
            commitTransaction();            
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;        
        }
    }

    public void update(Institutionmaster im) {
        try {
            beginTransaction();
            getSession().update(im);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void delete(Institutionmaster im) {
        try {
            beginTransaction();
            getSession().delete(im);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public List<Institutionmaster> findAll() {
        beginTransaction();
        List<Institutionmaster> list = getSession().createQuery("select u from Institutionmaster u").list();
        commitTransaction();
        return list;
    }

    public Institutionmaster findByImId(Short imId)
    {
        beginTransaction();
        Institutionmaster im  = new Institutionmaster ();
        im  = (Institutionmaster) getSession().load(Institutionmaster.class , imId);
        commitTransaction();
        return im;
    }
    
    public List<Institutionmaster> findInstForUser(Integer erpmuId) {
        beginTransaction();
        List<Institutionmaster> imList = getSession().createQuery("select distinct(u) from Institutionmaster u, Erpmuserrole r where r.erpmusers.erpmuId = :erpmuId and r.institutionmaster.imId = u.imId").setParameter("erpmuId", erpmuId).list();
        commitTransaction();
        return imList;
    }
public List<Institutionmaster> findForUser(Integer erpmuId)
    {
        beginTransaction();
        List<Institutionmaster> imList = getSession().createQuery("select distinct(u) from Institutionmaster u, Erpmuserrole r where r.institutionmaster.imId = u.imId and r.erpmusers.erpmuId = :erpmuId").setParameter("erpmuId",erpmuId).list();
        commitTransaction();
        return imList;
    }
public Institutionmaster findInstByIMShortName(String imShortName) {
        beginTransaction();
 List<Institutionmaster> imList = getSession().createQuery("select distinct(u) from Institutionmaster u where u.imShortName = :imShortName").setParameter("imShortName", imShortName).list();
        commitTransaction();
        return imList.get(0);
    }

public Institutionmaster findInstByIMFullName(String imName) {
        beginTransaction();
 List<Institutionmaster> imList = getSession().createQuery("select distinct(u) from Institutionmaster u where u.imName = :imName").setParameter("imName",imName).list();
        commitTransaction();
        return imList.get(0);
    }

public List<Institutionmaster> findInstByIMName(String imName)
    {
        beginTransaction();
        List<Institutionmaster> imList = getSession().createQuery("select distinct(u) from Institutionmaster u where u.imName = :imName").setParameter("imName",imName).list();
        commitTransaction();
        return imList;
    }

public List<Institutionmaster> findInstByShortName(String imShortName) {
        beginTransaction();
        List<Institutionmaster> imList = getSession().createQuery("select distinct(u) from Institutionmaster u where u.imShortName = :imShortName").setParameter("imShortName", imShortName).list();
        commitTransaction();
        return imList;
    }

public String findDefaultInstByID(Short imId) {
        beginTransaction();
        List<Institutionmaster> imList = getSession().createQuery("select u from Institutionmaster u where u.imId = :imId").setParameter("imId",imId).list();
        commitTransaction();
        return imList.get(0).getImName();
    }



  
}