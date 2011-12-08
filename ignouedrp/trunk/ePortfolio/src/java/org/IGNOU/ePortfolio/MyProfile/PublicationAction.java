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


package org.IGNOU.ePortfolio.MyProfile;

import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.MyProfile.Dao.MyProfileDAO;
import org.IGNOU.ePortfolio.MyProfile.Model.ProfilePublication;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created on 14-Sep-2011.
 * @author Vinay
 */
public class PublicationAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private String user_id = new UserSession().getUserInSession();
    private MyProfileDAO dao = new MyProfileDAO();
    private ProfilePublication Pub;
    private List<ProfilePublication> PubListList;
    private long publicationId;
    private String pubTitle;
    private String publisher;
    private String pubDate;
    private String pubUrl;
    private String auther1;
    private String auther2;
    private String auther3;
    private String auther4;
    private String summary;
    

    public PublicationAction() {
    }

    public String ShowPubInfo() throws Exception {
        PubListList = dao.PubList(user_id);    
          if (PubListList.isEmpty()) {
           // return SUCCESS;
            return INPUT;
        } else {
            //return ERROR;
//              auther1=PubListList.iterator().next().getAuther1();
//              auther2=PubListList.iterator().next().getAuther2();
//              auther3=PubListList.iterator().next().getAuther3();
//              auther4=PubListList.iterator().next().getAuther4();
//             StringTokenizer st =new StringTokenizer(auther1);
//             String st1=st.nextToken();
//             auther1=auther1+" "+st1;
                    
            return SUCCESS;
        }
    }

    public String EditPubInfo() throws Exception {
        PubListList = dao.EditPub(publicationId);
        return SUCCESS;
    }

    public String UpdatePubInfo() throws Exception {
        dao.UpdatePub(publicationId, user_id, pubTitle, publisher, pubDate, pubUrl, auther1, auther2, auther3, auther4, summary);
        return SUCCESS;
    }

    public String DeletePublicationInfo() throws Exception {
        dao.DeletePubInfo(publicationId);
        return SUCCESS;
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
     * @return the dao
     */
    public MyProfileDAO getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(MyProfileDAO dao) {
        this.dao = dao;
    }

    /**
     * @return the Pub
     */
    public ProfilePublication getPub() {
        return Pub;
    }

    /**
     * @param Pub the Pub to set
     */
    public void setPub(ProfilePublication Pub) {
        this.Pub = Pub;
    }

    /**
     * @return the PubListList
     */
    public List<ProfilePublication> getPubListList() {
        return PubListList;
    }

    /**
     * @param PubListList the PubListList to set
     */
    public void setPubListList(List<ProfilePublication> PubListList) {
        this.PubListList = PubListList;
    }

    /**
     * @return the publicationId
     */
    public long getPublicationId() {
        return publicationId;
    }

    /**
     * @param publicationId the publicationId to set
     */
    public void setPublicationId(long publicationId) {
        this.publicationId = publicationId;
    }

    /**
     * @return the pubTitle
     */
    public String getPubTitle() {
        return pubTitle;
    }

    /**
     * @param pubTitle the pubTitle to set
     */
    public void setPubTitle(String pubTitle) {
        this.pubTitle = pubTitle;
    }

    /**
     * @return the publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * @param publisher the publisher to set
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * @return the pubDate
     */
    public String getPubDate() {
        return pubDate;
    }

    /**
     * @param pubDate the pubDate to set
     */
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    /**
     * @return the pubUrl
     */
    public String getPubUrl() {
        return pubUrl;
    }

    /**
     * @param pubUrl the pubUrl to set
     */
    public void setPubUrl(String pubUrl) {
        this.pubUrl = pubUrl;
    }

    /**
     * @return the auther1
     */
    public String getAuther1() {
        return auther1;
    }

    /**
     * @param auther1 the auther1 to set
     */
    public void setAuther1(String auther1) {
        this.auther1 = auther1;
    }

    /**
     * @return the auther2
     */
    public String getAuther2() {
        return auther2;
    }

    /**
     * @param auther2 the auther2 to set
     */
    public void setAuther2(String auther2) {
        this.auther2 = auther2;
    }

    /**
     * @return the auther3
     */
    public String getAuther3() {
        return auther3;
    }

    /**
     * @param auther3 the auther3 to set
     */
    public void setAuther3(String auther3) {
        this.auther3 = auther3;
    }

    /**
     * @return the auther4
     */
    public String getAuther4() {
        return auther4;
    }

    /**
     * @param auther4 the auther4 to set
     */
    public void setAuther4(String auther4) {
        this.auther4 = auther4;
    }

    /**
     * @return the summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary the summary to set
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

}
