package org.IGNOU.ePortfolio.DAO;

/**
 *
 * Copyright (c) 2011 eGyankosh, IGNOU, New Delhi. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproducuce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL eGyankosh,
 * IGNOU OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL,SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 * Contributors: Members of eGyankosh, IGNOU, New Delhi.
 *
 */
import java.util.List;
import org.IGNOU.ePortfolio.Model.ProfileAcademic;
import org.IGNOU.ePortfolio.Model.ProfileCertification;
import org.IGNOU.ePortfolio.Model.ProfileEmployment;
import org.IGNOU.ePortfolio.Model.ProfileReferences;
import org.IGNOU.ePortfolio.Model.ProfileSkill;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @version 12
 * @author IGNOU Team
 */
public class MyProfileDAO {

    private SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;

    @SuppressWarnings("unchecked")
    public List<ProfileAcademic> ProfileAcademicListByUserId(String user_id) {
        s = sf.openSession();
        Transaction t = s.beginTransaction();

        List<ProfileAcademic> academiclist = null;
        try {
            academiclist = s.createQuery("from ProfileAcademic where user_id='" + user_id + "' order by pyear DESC").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sf.close();
        return academiclist;
    }

    @SuppressWarnings("unchecked")
    public List<ProfileEmployment> ProfileEmployementListByUserId(String user_id) {
        s = sf.openSession();
        Transaction t = s.beginTransaction();

        List<ProfileEmployment> employmentlist = null;
        try {
            employmentlist = s.createQuery("from ProfileEmployment where user_id='" + user_id + "'  order by jDate desc").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sf.close();
        return employmentlist;
    }
    /*04-04-2012 by IGNOU Team resolved on 05-04-2011*/

    @SuppressWarnings("unchecked")
    public ProfileAcademic ProfileAcademicUpdate(List<Long> academicInfoId, List<String> user_id, List<String> degree, List<String> university, List<String> location, List<String> fstudy, List<String> pyear, List<Integer> percent, List<String> division) {
        s = sf.openSession();
        Transaction t = s.beginTransaction();
        ProfileAcademic pa = new ProfileAcademic();
        try {
            for (int i = 0; i < degree.size(); i++) {
                ProfileAcademic UpdateAcademiclList = (ProfileAcademic) s.load(ProfileAcademic.class, academicInfoId.get(i));
                UpdateAcademiclList.setDegree(degree.get(i));
                UpdateAcademiclList.setDivision(division.get(i));
                UpdateAcademiclList.setFstudy(fstudy.get(i));
                UpdateAcademiclList.setLocation(location.get(i));
                UpdateAcademiclList.setPercent(percent.get(i));
                UpdateAcademiclList.setPyear(pyear.get(i));
                UpdateAcademiclList.setUniversity(university.get(i));
                if (null != UpdateAcademiclList) {
                    s.update(UpdateAcademiclList);
                }
                s.flush();
                s.clear();
            }
            t.commit();
            return pa;
        } catch (Throwable ex) {
            //Log the Exception
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sf.close();
        }
    }

    /*Writen by IGNOU Team on 04-Sep-2011*/
    @SuppressWarnings("unchecked")
    public List<ProfileAcademic> ProfileAcademicEdit(String user_id) {
        s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();

            List<ProfileAcademic> editacademiclist = null;
            try {
                editacademiclist = s.createQuery("from ProfileAcademic where user_id='" + user_id + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return editacademiclist;
        } catch (Throwable ex) {
            //Log the Exception
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sf.close();
        }
    }

    /*Writen by IGNOU Team on 12-Sep-2011*/
    @SuppressWarnings("unchecked")
    public ProfileAcademic ProfileAcademicDelete(long academicInfoId) {
        s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();

            ProfileAcademic deleteInfo = (ProfileAcademic) s.load(ProfileAcademic.class, academicInfoId);
            if (deleteInfo != null) {
                s.delete(deleteInfo);
            }
            t.commit();
            return deleteInfo;
        } catch (Throwable ex) {
            //Log the Exception
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sf.close();
        }
    }
    /*Writen by IGNOU Team on 13-Sep-2011*/

    @SuppressWarnings({"unchecked", "unchecked", "unchecked"})
    public List<ProfileEmployment> ProfileEmploymentlListByEmplymentId(long employmentInfoId) {
        s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();

            List<ProfileEmployment> EmployeeList = null;
            try {
                EmployeeList = s.createQuery("from ProfileEmployment where employmentInfoId='" + employmentInfoId + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return EmployeeList;
        } catch (Throwable ex) {
            //Log the Exception
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sf.close();
        }
    }

    @SuppressWarnings("unchecked")
    public ProfileEmployment ProfileEmploymentUpdate(long employmentInfo_id, String user_id, String jTitle, String orgName, String oAddress, String oCity, String oState, String oCountry, String jDate, String lDate, String description) {
        s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            ProfileEmployment UpdateEmpInfo = (ProfileEmployment) s.load(ProfileEmployment.class, employmentInfo_id);
            UpdateEmpInfo.setDescription(description);
            UpdateEmpInfo.setJdate(jDate);
            UpdateEmpInfo.setJtitle(jTitle);
            UpdateEmpInfo.setLdate(lDate);
            UpdateEmpInfo.setOaddress(oAddress);
            UpdateEmpInfo.setOcity(oCity);
            UpdateEmpInfo.setOcountry(oCountry);
            UpdateEmpInfo.setOrgName(orgName);
            UpdateEmpInfo.setOstate(oState);
            if (null != UpdateEmpInfo) {
                s.update(UpdateEmpInfo);
            }
            t.commit();
            return UpdateEmpInfo;
        } catch (Throwable ex) {
            //Log the Exception
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sf.close();
        }
    }

    @SuppressWarnings("unchecked")
    public ProfileEmployment ProfileEmploymentDelete(long employmentInfoId) {
        s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            ProfileEmployment DeleteInfo = (ProfileEmployment) s.load(ProfileEmployment.class, employmentInfoId);
            if (DeleteInfo != null) {
                s.delete(DeleteInfo);
            }
            t.commit();
            return DeleteInfo;
        } catch (Throwable ex) {
            //Log the Exception
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sf.close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<ProfileSkill> ProfileSkillListByUserIdType(String user_id, String type) {
        s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();

            List<ProfileSkill> SkillList = null;
            try {
                SkillList = s.createQuery("from ProfileSkill where user_id='" + user_id + "' and type='" + type + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return SkillList;
        } catch (Throwable ex) {
            //Log the Exception
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sf.close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<ProfileSkill> ProfileSkillByUserId(String user_id) {
        s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();

            List<ProfileSkill> SkillList = null;
            try {
                SkillList = s.createQuery("from ProfileSkill where user_id='" + user_id + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return SkillList;
        } catch (Throwable ex) {
            //Log the Exception
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sf.close();
        }
    }

    /**
     * Added on 15-Sep-2011
     *
     * @param skillId
     * @return boolean
     * @author IGNOU Team
     *
     */
    @SuppressWarnings("unchecked")
    public ProfileSkill ProfileSkillDelete(long skillId) {
        s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            ProfileSkill DeleteInfo = (ProfileSkill) s.load(ProfileSkill.class, skillId);
            if (DeleteInfo != null) {
                s.delete(DeleteInfo);
            }
            t.commit();
            return DeleteInfo;
        } catch (Throwable ex) {
            //Log the Exception
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sf.close();
        }
    }

    /**
     * Added on 05-Oct-2011
     *
     * @param user_id
     * @return CertificateInfoList
     * @author IGNOU Team
     *
     */
    @SuppressWarnings({"unchecked", "unchecked"})
    public List<ProfileCertification> ProfileCertificationListByUserId(String user_id) {
        s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();

            List<ProfileCertification> CertificateInfoList = null;
            try {
                CertificateInfoList = s.createQuery("from ProfileCertification where user_id='" + user_id + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return CertificateInfoList;
        } catch (Throwable ex) {
            //Log the Exception
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sf.close();
        }
    }

    /**
     * Added on 07-Oct-2011
     *
     * @param certificationId
     * @return CertificateInfoList
     * @author IGNOU Team
     *
     */
    @SuppressWarnings("unchecked")
    public List<ProfileCertification> ProfileCertificationByCertificationId(long certificationId) {
        s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();

            List<ProfileCertification> CertificateInfoList = null;
            try {
                CertificateInfoList = s.createQuery("from ProfileCertification where certification_id='" + certificationId + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return CertificateInfoList;
        } catch (Throwable ex) {
            //Log the Exception
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sf.close();
        }
    }

    @SuppressWarnings("unchecked")
    public ProfileCertification ProfileCertificationUpdate(long certification_id, String user_id, String certificationName, String certificationAuthority, String license, String certificationDate, String validDate) {
        s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();

            ProfileCertification UpdateCertificateInfo = (ProfileCertification) s.load(ProfileCertification.class, certification_id);
            UpdateCertificateInfo.setCertificationAuthority(certificationAuthority);
            UpdateCertificateInfo.setCertificationDate(certificationDate);
            // UpdateCertificateInfo.setCertificationId(certification_id);
            UpdateCertificateInfo.setCertificationName(certificationName);
            UpdateCertificateInfo.setLicense(license);
            UpdateCertificateInfo.setValidDate(validDate);
            if (null != UpdateCertificateInfo) {
                s.update(UpdateCertificateInfo);
            }
            t.commit();
            return UpdateCertificateInfo;
        } catch (Throwable ex) {
            //Log the Exception
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sf.close();
        }
    }

    @SuppressWarnings("unchecked")
    public ProfileCertification ProfileCertificationDelete(long certificationId) {
        s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            ProfileCertification DeleteInfo = (ProfileCertification) s.load(ProfileCertification.class, certificationId);
            if (DeleteInfo != null) {
                s.delete(DeleteInfo);
            }
            t.commit();
            return DeleteInfo;
        } catch (Throwable ex) {
            //Log the Exception
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sf.close();
        }
    }

    /**
     * Added on 11-Oct-2011
     *
     * @param userId
     * @return ReferenceInfoList
     * @author IGNOU Team
     *
     */
    @SuppressWarnings("unchecked")
    public List<ProfileReferences> ProfileReferencesListByUserId(String userId) {
        s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<ProfileReferences> ReferenceInfoList = null;
            try {
                ReferenceInfoList = s.createQuery("from ProfileReferences where userId='" + userId + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return ReferenceInfoList;
        } catch (Throwable ex) {
            //Log the Exception
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sf.close();
        }
    }

    /**
     * Added on 12-Oct-2011
     *
     * @param referencesId
     * @return ReferenceInfoList
     * @author IGNOU Team
     *
     */
    @SuppressWarnings("unchecked")
    public List<ProfileReferences> ProfileReferencesEdit(long referencesId) {
        s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<ProfileReferences> ReferenceInfoList = null;
            try {
                ReferenceInfoList = s.createQuery("from ProfileReferences where references_id='" + referencesId + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return ReferenceInfoList;
        } catch (Throwable ex) {
            //Log the Exception
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sf.close();
        }
    }

    public ProfileReferences ProfileReferencesUpdate(long references_id, String user_id, String name, String designation, String org_univ, String place, String city, String state, String country, Long phoneno, Long mobileno, String email_id, String website) {
        s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            ProfileReferences UpdateInfo = (ProfileReferences) s.load(ProfileReferences.class, references_id);
            UpdateInfo.setCity(city);
            UpdateInfo.setCountry(country);
            UpdateInfo.setDesignation(designation);
            UpdateInfo.setEmailId(email_id);
            UpdateInfo.setMobileno(mobileno);
            UpdateInfo.setName(name);
            UpdateInfo.setOrgUniv(org_univ);
            UpdateInfo.setPhoneno(phoneno);
            UpdateInfo.setPlace(place);
            UpdateInfo.setState(state);
            UpdateInfo.setWebsite(website);
            if (null != UpdateInfo) {
                s.update(UpdateInfo);
            }
            t.commit();
            return UpdateInfo;
        } catch (Throwable ex) {
            //Log the Exception
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sf.close();
        }
    }

    public ProfileReferences ProfileReferencesDelete(long referencesId) {
        s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            ProfileReferences DeleteInfo = (ProfileReferences) s.load(ProfileReferences.class, referencesId);
            if (DeleteInfo != null) {
                s.delete(DeleteInfo);
            }
            t.commit();
            return DeleteInfo;
        } catch (Throwable ex) {
            //Log the Exception
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sf.close();
        }
    }
}
