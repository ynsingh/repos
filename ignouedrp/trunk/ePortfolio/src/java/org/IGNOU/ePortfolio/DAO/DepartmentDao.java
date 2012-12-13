/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.Date;
import org.IGNOU.ePortfolio.Model.Department;
import org.IGNOU.ePortfolio.Model.Institute;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author IGNOU Team
 */
public class DepartmentDao {

    private SessionFactory sessionFactory;

    public Department RegDept(Integer instituteId, String departmentName, String departmentCode, String introduction, String postalAddress, Integer phoneCode, Integer phoneNo, long mobileNo, Integer fax, String deptEmailId) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = null;
        Institute inst = new Institute();
        try {
            t = s.beginTransaction();
            inst.setInstituteId(instituteId);
            Department DepModel = new Department();
            DepModel.setInstitute(inst);
            DepModel.setDateRegister(new Date());
            DepModel.setDepartmentCode(departmentCode);
            DepModel.setDepartmentName(departmentName);
            DepModel.setDeptEmailId(deptEmailId);
            DepModel.setFax(fax);
            DepModel.setIntroduction(introduction);
            DepModel.setMobileNo(mobileNo);
            DepModel.setPhoneCode(phoneCode);
            DepModel.setPhoneNo(phoneNo);
            DepModel.setPostalAddress(postalAddress);
            s.save(DepModel);
            t.commit();
            return DepModel;
        } catch (Throwable ex) {
            //Log the Exception
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sessionFactory.close();
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}