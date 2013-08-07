/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Administrator.Action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import org.IGNOU.ePortfolio.DAO.ContactUsDao;
import org.IGNOU.ePortfolio.Model.ContactUs;

/**
 *
 * @author Amit
 */
public class ContactUsAction extends ActionSupport {

    private List<ContactUs> contactList;
    private ContactUsDao cuDao = new ContactUsDao();
    private int contactId;
    private String contactName;
    private String contactAddress;
    private Long contactOff;
    private Long contactMob;
    private String contactEmail;
    private String aboutUs;


    public String contactAdd() {
        cuDao.ContactUsSave(contactName, contactAddress, contactOff, contactMob, contactEmail,aboutUs);
        return SUCCESS;
    }
    public String ContactEdit(){
       contactList=(cuDao.ContactUsEditByContactId(contactId));
       if(contactList.isEmpty())
       {
           return ERROR;
       }
       else{
        return SUCCESS;
       }
    }
    
    public String ContactUpdate(){
       cuDao.ContactUsUpdate(contactId, contactName, contactAddress, contactOff, contactMob, contactEmail,aboutUs);
        return SUCCESS;
     }
    
    
    @Override
    public String execute() throws Exception {
        contactList=(cuDao.ContactUsList());
        if (contactList.isEmpty()) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }

    /**
     * @return the contactList
     */
    public List<ContactUs> getContactList() {
        return contactList;
    }

  

    /**
     * @return the contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * @param contactName the contactName to set
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * @return the contactAddress
     */
    public String getContactAddress() {
        return contactAddress;
    }

    /**
     * @param contactAddress the contactAddress to set
     */
    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    /**
     * @return the contactOff
     */
    public Long getContactOff() {
        return contactOff;
    }

    /**
     * @param contactOff the contactOff to set
     */
    public void setContactOff(Long contactOff) {
        this.contactOff = contactOff;
    }

    /**
     * @return the contactMob
     */
    public Long getContactMob() {
        return contactMob;
    }

    /**
     * @param contactMob the contactMob to set
     */
    public void setContactMob(Long contactMob) {
        this.contactMob = contactMob;
    }

    /**
     * @return the contactEmail
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * @param contactEmail the contactEmail to set
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /**
     * @return the contactId
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * @param contactId the contactId to set
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * @return the aboutUs
     */
    public String getAboutUs() {
        return aboutUs;
    }

    /**
     * @param aboutUs the aboutUs to set
     */
    public void setAboutUs(String aboutUs) {
        this.aboutUs = aboutUs;
    }

}
