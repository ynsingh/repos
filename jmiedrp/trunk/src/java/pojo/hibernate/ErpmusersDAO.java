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
import java.util.Date;



public class ErpmusersDAO  extends BaseDAO {
    
    public void save(Erpmusers erpmuser) {
        try {
            beginTransaction();
            getSession().save(erpmuser);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void update(Erpmusers erpmuser) {
        try {
            beginTransaction();
            getSession().update(erpmuser);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void delete(Erpmusers erpmuser) {
        try {
            beginTransaction();
            getSession().delete(erpmuser);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public List<Erpmusers> findAll() {
        beginTransaction();
        List<Erpmusers> list = getSession().createQuery("select u from Erpmusers u").list();
        commitTransaction();
        return list;
    }

    public Erpmusers findByUserName(String ERPMU_Name) {
        beginTransaction();
        List<Erpmusers> erpmuserList  =  getSession().createQuery("select u from Erpmusers u where u.erpmuName=:username ").
                                                      setParameter("username",ERPMU_Name).list();
        commitTransaction();
        Erpmusers erpmuser = erpmuserList.get(0);
        return erpmuser;
    }


     public Erpmusers findByUserId(Integer erpmuId) {
        beginTransaction();
        List<Erpmusers> erpmuserList  =  getSession().createQuery("select u from Erpmusers u where u.erpmuId=:erpmuId ").
                                                      setParameter("erpmuId",erpmuId).list();
        commitTransaction();
        Erpmusers erpmuser = erpmuserList.get(0);
        return erpmuser;
    }
    public List<Erpmusers> RetrieveUser(String ERPMU_Name, String ERPMU_Password)
    {
        beginTransaction();
        List<Erpmusers> list  = getSession().createQuery("select u from Erpmusers u, Erpmuserrole r where u.erpmuId = r.erpmusers.erpmuId and r.erpmurActive = 'Y' and u.erpmuName=:username and u.erpmuPassword=:password ").
                                setParameter("username",ERPMU_Name).setParameter("password",ERPMU_Password).list();

        commitTransaction();

        return list;
    }



    public String RetrieveSecretQuestion(String ERPMU_Name, String ERPMU_DOB)
    {
        beginTransaction();
        List<Erpmusers> list  = getSession().createQuery("select u from Erpmusers u where u.erpmuName=:username and u.erpmuDob =:userdob").
                                setString("username",ERPMU_Name).setString("userdob", ERPMU_DOB).list();
        commitTransaction();
        return list.get(0).getErpmuSecretQuestion();
    }

    public Erpmusers FindByUserNameSecretAnswer(String ERPMU_Name, String Secret_Answer)
    {
        beginTransaction();
        List<Erpmusers> list  = getSession().createQuery("select u from Erpmusers u where u.erpmuName=:username and u.erpmuSecretAnswer =:useranswer").
                                setString("username",ERPMU_Name).setString("useranswer", Secret_Answer).list();
        commitTransaction();
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }
  public Erpmusers findByUserFullNames(String ERPMU_FullName) {
        beginTransaction();
        List <Erpmusers> erpmuserList  =  getSession().createQuery("select u from Erpmusers u where u.erpmuFullName=:ERPMU_FullName ").
                                                      setParameter("ERPMU_FullName",ERPMU_FullName).list();
        commitTransaction();
        //Erpmusers erpmuser = erpmuserList.get(0);
        return erpmuserList.get(0);
    }

  

}