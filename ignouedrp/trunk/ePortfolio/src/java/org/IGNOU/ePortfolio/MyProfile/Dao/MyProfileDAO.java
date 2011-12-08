

/*
 * 
 *  Copyright (c) 2011 eGyankosh, IGNOU, New Delhi.
 *  All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or
 *  without modification, are permitted provided that the following
 *  conditions are met:
 *
 *  Redistributions of source code must retain the above copyright
 *  notice, this  list of conditions and the following disclaimer.
 *
 *  Redistribution in binary form must reproducuce the above copyright
 *  notice, this list of conditions and the following disclaimer in
 *  the documentation and/or other materials provided with the
 *  distribution.
 *
 *
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED.  IN NO EVENT SHALL eGyankosh, IGNOU OR ITS CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *  Contributors: Members of eGyankosh, IGNOU, New Delhi.
 *
 */
package org.IGNOU.ePortfolio.MyProfile.Dao;

import org.IGNOU.ePortfolio.MyProfile.Model.ProfileAcademic;
import org.IGNOU.ePortfolio.MyProfile.Model.ProfileBasic;
import org.IGNOU.ePortfolio.MyProfile.Model.ProfileContact;
import org.IGNOU.ePortfolio.MyProfile.Model.ProfileEmployment;
import org.IGNOU.ePortfolio.MyProfile.Model.ProfilePersonal;
import org.IGNOU.ePortfolio.MyProfile.Model.ProfilePublication;
import org.IGNOU.ePortfolio.MyProfile.Model.ProfileSkill;
import java.util.List;
import org.IGNOU.ePortfolio.MyProfile.Model.ProfileCertification;
import org.IGNOU.ePortfolio.MyProfile.Model.ProfileReferences;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 * @version 12
 * @author Vinay
 */
public class MyProfileDAO {

    private SessionFactory sf;

    // Create the SessionFactory from standard (hibernate.cfg.xml) 
    // config file.
    @SuppressWarnings("unchecked")
    public List<ProfileBasic> BasicList(String user_id) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();

        List<ProfileBasic> basiclist = null;
        try {
            basiclist = s.createQuery("from ProfileBasic where user_id='" + user_id + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sf.close();
        return basiclist;

    }

    @SuppressWarnings("unchecked")
    public List<ProfileContact> ContactList(String user_id) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();

        List<ProfileContact> contactlist = null;
        try {
            contactlist = s.createQuery("from ProfileContact where user_id='" + user_id + "'").list();
            t.commit();
            return contactlist;
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
    public List<ProfileAcademic> AcademicList(String user_id) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();

        List<ProfileAcademic> academiclist = null;
        try {
            academiclist = s.createQuery("from ProfileAcademic where user_id='" + user_id + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sf.close();
        return academiclist;
    }

    @SuppressWarnings("unchecked")
    public List<ProfileEmployment> EmploymentList(String user_id) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();

        List<ProfileEmployment> employmentlist = null;
        try {
            employmentlist = s.createQuery("from ProfileEmployment where user_id='" + user_id + "'  order by jDate").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sf.close();
        return employmentlist;
    }

    @SuppressWarnings("unchecked")
    public List<ProfilePersonal> PersonalList(String user_id) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();

        List<ProfilePersonal> personallist = null;
        try {
            personallist = s.createQuery("from ProfilePersonal where user_id='" + user_id + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sf.close();
        return personallist;
    }

    @SuppressWarnings("unchecked")
    public ProfileBasic UpdateBasic(long basicInfo_ID, String user_id, String fName, String mName, String lName, String gender, String date_of_birth, String pBirth, String mStatus, String aboutMe) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            ProfileBasic UpdateBasicInfoList = (ProfileBasic) s.load(ProfileBasic.class, basicInfo_ID);
            UpdateBasicInfoList.setFname(fName);
            UpdateBasicInfoList.setMname(mName);
            UpdateBasicInfoList.setLname(lName);
            UpdateBasicInfoList.setGender(gender);
            UpdateBasicInfoList.setPbirth(pBirth);
            UpdateBasicInfoList.setDateOfBirth(date_of_birth);
            UpdateBasicInfoList.setAboutMe(aboutMe);
            UpdateBasicInfoList.setMstatus(mStatus);
            if (null != UpdateBasicInfoList) {
                s.update(UpdateBasicInfoList);
            }
            t.commit();
            return UpdateBasicInfoList;
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
    public ProfileContact UpdateContact(long contactInfo_id, String user_id, String address1, String address2, String city, String state, String country, Integer pin, Long hTelephone, Long oTelephone, Long mobile, Long fax, String email1, String email2, String email3, String oWebsite, String pWebsite) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            ProfileContact UpdateContactList = (ProfileContact) s.load(ProfileContact.class, contactInfo_id);
            UpdateContactList.setAddress1(address1);
            UpdateContactList.setAddress2(address2);
            UpdateContactList.setCity(city);
            UpdateContactList.setCountry(country);
            UpdateContactList.setEmail1(email1);
            UpdateContactList.setEmail2(email2);
            UpdateContactList.setEmail3(email3);
            UpdateContactList.setFax(fax);
            UpdateContactList.setHtelephone(hTelephone);
            UpdateContactList.setMobile(mobile);
            UpdateContactList.setOtelephone(oTelephone);
            UpdateContactList.setOwebsite(oWebsite);
            UpdateContactList.setPin(pin);
            UpdateContactList.setPwebsite(pWebsite);
            UpdateContactList.setState(state);
            if (null != UpdateContactList) {
                s.update(UpdateContactList);
            }
            t.commit();
            return UpdateContactList;
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
    public ProfilePersonal UpdatePersonal(Long personalInfo, String user_id, String fBook, String fTVShow, String fMovie, String fQuote, String oInfo) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            ProfilePersonal UpdatePersonalList = (ProfilePersonal) s.load(ProfilePersonal.class, personalInfo);
            UpdatePersonalList.setFbook(fBook);
            UpdatePersonalList.setFmovie(fMovie);
            UpdatePersonalList.setFquote(fQuote);
            UpdatePersonalList.setFtvshow(fTVShow);
            UpdatePersonalList.setOinfo(oInfo);
            if (null != UpdatePersonalList) {
                s.update(UpdatePersonalList);
            }
            t.commit();
            return UpdatePersonalList;
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
    /*Writen by Vinay on 04-Sep-2011.
     * Edited by Vinay on 12-Sep-2011.
     */

    @SuppressWarnings("unchecked")
    public ProfileAcademic UpdateAcademic(long academicInfo_id, String user_id, String degree, String university, String location, String fStudy, String pYear, int percentage, String division) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            ProfileAcademic UpdateAcademiclList = (ProfileAcademic) s.load(ProfileAcademic.class, academicInfo_id);
            UpdateAcademiclList.setDegree(degree);
            UpdateAcademiclList.setDivision(division);
            UpdateAcademiclList.setFstudy(fStudy);
            UpdateAcademiclList.setLocation(location);
            UpdateAcademiclList.setPercentage(percentage);
            UpdateAcademiclList.setPyear(pYear);
            UpdateAcademiclList.setUniversity(university);
            if (null != UpdateAcademiclList) {
                s.update(UpdateAcademiclList);
            }
            t.commit();
            return UpdateAcademiclList;
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

    /*Writen by Vinay on 04-Sep-2011*/
    @SuppressWarnings("unchecked")
    public List<ProfileAcademic> EditAcademic(long academicInfoId) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();

            List<ProfileAcademic> editacademiclist = null;
            try {
                editacademiclist = s.createQuery("from ProfileAcademic where academicInfo_id='" + academicInfoId + "'").list();
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

    /*Writen by Vinay on 12-Sep-2011*/
    @SuppressWarnings("unchecked")
    public ProfileAcademic DeleteAcademicInformation(long academicInfoId) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
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
    /*Writen by Vinay on 13-Sep-2011*/

    @SuppressWarnings({"unchecked", "unchecked", "unchecked"})
    public List<ProfileEmployment> empList(long employmentInfoId) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
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
    public ProfileEmployment UpdateEmp(long employmentInfo_id, String user_id, String jTitle, String orgName, String oAddress, String oCity, String oState, String oCountry, String jDate, String lDate, String description) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
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
    public ProfileEmployment DeleteEmpInfo(long employmentInfoId) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
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
    /*Writen by Vinay on 14-Sep-2011*/

    @SuppressWarnings({"unchecked"})
    public List<ProfilePublication> PubList(String user_id) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();

            List<ProfilePublication> PublicationList = null;
            try {
                PublicationList = s.createQuery("from ProfilePublication where user_id='" + user_id + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return PublicationList;
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
    public ProfilePublication DeletePubInfo(long publication_id) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            ProfilePublication DeleteInfo = (ProfilePublication) s.load(ProfilePublication.class, publication_id);
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

    @SuppressWarnings({"unchecked"})
    public List<ProfilePublication> EditPub(long publication_id) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();

            List<ProfilePublication> PublicationList = null;
            try {
                PublicationList = s.createQuery("from ProfilePublication where publication_id='" + publication_id + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return PublicationList;
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
    public ProfilePublication UpdatePub(long publication_id, String user_id, String pubTitle, String publisher, String pubDate, String pubUrl, String auther1, String auther2, String auther3, String auther4, String summary) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            ProfilePublication UpdatePublicationInfo = (ProfilePublication) s.load(ProfilePublication.class, publication_id);
            UpdatePublicationInfo.setAuther1(auther1);
            UpdatePublicationInfo.setAuther2(auther2);
            UpdatePublicationInfo.setAuther3(auther3);
            UpdatePublicationInfo.setAuther4(auther4);
            UpdatePublicationInfo.setPubDate(pubDate);
            UpdatePublicationInfo.setPubTitle(pubTitle);
            UpdatePublicationInfo.setPubUrl(pubUrl);
            // UpdatePublicationInfo.setPublicationId(publication_id);
            UpdatePublicationInfo.setPublisher(publisher);
            UpdatePublicationInfo.setSummary(summary);
            // UpdatePublicationInfo.setUserId(user_id);
            if (null != UpdatePublicationInfo) {
                s.update(UpdatePublicationInfo);
            }
            t.commit();
            return UpdatePublicationInfo;
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
    public List<ProfileSkill> ShowSkill(String user_id, String type) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();

            List<ProfileSkill> SkillList = null;
            try {
                SkillList = s.createQuery("from ProfileSkill where user_id='" + user_id + "' and type='"+type+"'").list();
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
     *@param skillId 
     * @return boolean
     * @author Vinay
     **/
    @SuppressWarnings("unchecked")
    public ProfileSkill DeleteSkilInfo(long skillId) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
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
     * @param user_id 
     * @return CertificateInfoList
     * @author Vinay
     **/
    @SuppressWarnings({"unchecked", "unchecked"})
    public List<ProfileCertification> ShowCertificateInfo(String user_id) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
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
     *  @param certificationId 
     * @return  CertificateInfoList
     * @author Vinay
     **/
    @SuppressWarnings("unchecked")
    public List<ProfileCertification> ShowCertificateEditInfo(long certificationId) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
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
    public ProfileCertification UpdateCertificate(long certification_id, String user_id, String certificationName, String certificationAuthority, String license, String certificationDate, String validDate) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
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
    public ProfileCertification DeleteCertificate(long certificationId) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
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
     * @param userId 
     * @return ReferenceInfoList
     * @author Vinay
     **/
    @SuppressWarnings("unchecked")
    public List<ProfileReferences> ShowReferenceInfo(String userId) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
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
     * @param referencesId 
     * @return ReferenceInfoList
     * @author Vinay
     **/
    @SuppressWarnings("unchecked")
    public List<ProfileReferences> EditReferenceInfo(long referencesId) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
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

    public ProfileReferences UpdateReferenceInfo(long references_id, String user_id, String name, String designation, String org_univ, String place, String city, String state, String country, Long phoneno, Long mobileno, String email_id, String website) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
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

    public ProfileReferences DeleteReference(long referencesId) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
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

    /**
     * @return the sf
     */
    public SessionFactory getSf() {
        return sf;
    }
}
