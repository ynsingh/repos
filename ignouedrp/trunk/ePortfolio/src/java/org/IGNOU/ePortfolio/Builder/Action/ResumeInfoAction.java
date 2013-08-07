/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Builder.Action;

import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.ResumeDao;
import org.IGNOU.ePortfolio.Model.Resume;

/**
 *
 * @author IGNOU Team
 */
public class ResumeInfoAction extends ActionSupport{
    private ResumeDao rsdao=new ResumeDao();
    private String user_id=new UserSession().getUserInSession();
    private List<Resume> resumelist;
    private Resume rslist;
    private long idResume;
    private String resumeName,resumeType;
    private byte[] resume;
     private InputStream fis;
    private FileOutputStream fos;
    
    public String ListResume() 
    {
        setResumelist(getRsdao().ResumeByUserId(getUser_id()));
        return "success";

    }
     
    public String ResumeDelete()
    {
    rsdao.ResumeDelete(getIdResume());
    return SUCCESS;
    }
    
    
    public String DownloadResume() throws FileNotFoundException, IOException
    {
     resumelist = rsdao.ResumeDetailByIdResume(idResume);
     resumeName=resumelist.iterator().next().getResumeName();
     resumeType=resumelist.iterator().next().getResumeType();
     resume= resumelist.iterator().next().getResume();
     fis= new ByteArrayInputStream(resume); // download file directly
     return SUCCESS;
    }
    
    /**
     * @return the rsdao
     */
    public ResumeDao getRsdao() {
        return rsdao;
    }

    /**
     * @param rsdao the rsdao to set
     */
    public void setRsdao(ResumeDao rsdao) {
        this.rsdao = rsdao;
    }

    /**
     * @return the user_id
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /**
     * @return the resumelist
     */
    public List<Resume> getResumelist() {
        return resumelist;
    }

    /**
     * @param resumelist the resumelist to set
     */
    public void setResumelist(List<Resume> resumelist) {
        this.resumelist = resumelist;
    }

    /**
     * @return the rslist
     */
    public Resume getRslist() {
        return rslist;
    }

    /**
     * @param rslist the rslist to set
     */
    public void setRslist(Resume rslist) {
        this.rslist = rslist;
    }

    /**
     * @return the idResume
     */
    public long getIdResume() {
        return idResume;
    }

    /**
     * @param idResume the idResume to set
     */
    public void setIdResume(long idResume) {
        this.idResume = idResume;
    }

    /**
     * @return the resumeName
     */
    public String getResumeName() {
        return resumeName;
    }

    /**
     * @param resumeName the resumeName to set
     */
    public void setResumeName(String resumeName) {
        this.resumeName = resumeName;
    }

    /**
     * @return the resumeType
     */
    public String getResumeType() {
        return resumeType;
    }

    /**
     * @param resumeType the resumeType to set
     */
    public void setResumeType(String resumeType) {
        this.resumeType = resumeType;
    }

    /**
     * @return the resume
     */
    public byte[] getResume() {
        return resume;
    }

    /**
     * @param resume the resume to set
     */
    public void setResume(byte[] resume) {
        this.resume = resume;
    }

    /**
     * @return the fis
     */
    public InputStream getFis() {
        return fis;
    }

    /**
     * @param fis the fis to set
     */
    public void setFis(InputStream fis) {
        this.fis = fis;
    }

   
}
 