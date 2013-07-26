/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyWorkspace;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.MyProfileDAO;
import org.IGNOU.ePortfolio.DAO.BookChapterDao;
import org.IGNOU.ePortfolio.Model.BookChapter;
import org.IGNOU.ePortfolio.Model.BookChapterAuthor;

/**
 *
 * @author IGNOU Team
 * @version 1
 * @since 23-Feb-2012
 */
public class BookChapterInfoAction extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private BookChapter bc = new BookChapter();
    private BookChapterDao dao = new BookChapterDao();
    private Long bookChapterId;
    private String userId;
    private String bcType;
    private String role;
    private String title;
    private Integer noCoauthor;
    private String publisher;
    private String isbn;
    private String publishedOn;
    private Integer PFrom;
    private Integer PTo;
    private String language;
    private String affiliation;
    private String url;
    private String summary;
    private Set<BookChapterAuthor> bookChapterAuthors = new HashSet<BookChapterAuthor>(0);
    private ArrayList<String> fname;
    private ArrayList<String> lname;
    private List<BookChapter> BCListList;
    private String msg;
    private String infoDeleted = getText("msg.infoDeleted");
    private String infoUpdated = getText("msg.infoUpdated");
    private MyProfileDAO bdao = new MyProfileDAO();

    public BookChapterInfoAction() {
    }

    public String ShowBCInfo() throws Exception {
        BCListList = dao.BookChapterListByUserId(user_id);
        if (BCListList.isEmpty()) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }

    public String EditBCInfo() throws Exception {
        setBCListList(getDao().BookChapterListByBookChapterId(getBookChapterId()));
        return SUCCESS;
    }

    public String UpdateBCInfo() throws Exception {
        getDao().BookChapterUpdate(getBookChapterId(), getUserId(), getBcType(), getRole(), getTitle(), getNoCoauthor(), getPublisher(), getIsbn(), getPublishedOn(), getPFrom(), getPTo(), getLanguage(), getAffiliation(), getUrl(), getSummary(), getBookChapterAuthors(), getFname(), getLname());
        msg = infoUpdated;
        return SUCCESS;
    }

    public String DeleteBCInfo() throws Exception {
        getDao().BookChapterDeleteByBookChapterId(getBookChapterId());
        msg = infoDeleted;
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
     * @return the bc
     */
    public BookChapter getBc() {
        return bc;
    }

    /**
     * @param bc the bc to set
     */
    public void setBc(BookChapter bc) {
        this.bc = bc;
    }

    /**
     * @return the dao
     */
    public BookChapterDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(BookChapterDao dao) {
        this.dao = dao;
    }

    /**
     * @return the bookChapterId
     */
    public Long getBookChapterId() {
        return bookChapterId;
    }

    /**
     * @param bookChapterId the bookChapterId to set
     */
    public void setBookChapterId(Long bookChapterId) {
        this.bookChapterId = bookChapterId;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the bcType
     */
    public String getBcType() {
        return bcType;
    }

    /**
     * @param bcType the bcType to set
     */
    public void setBcType(String bcType) {
        this.bcType = bcType;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the noCoauthor
     */
    public Integer getNoCoauthor() {
        return noCoauthor;
    }

    /**
     * @param noCoauthor the noCoauthor to set
     */
    public void setNoCoauthor(Integer noCoauthor) {
        this.noCoauthor = noCoauthor;
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
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the publishedOn
     */
    public String getPublishedOn() {
        return publishedOn;
    }

    /**
     * @param publishedOn the publishedOn to set
     */
    public void setPublishedOn(String publishedOn) {
        this.publishedOn = publishedOn;
    }

    /**
     * @return the PFrom
     */
    public Integer getPFrom() {
        return PFrom;
    }

    /**
     * @param PFrom the PFrom to set
     */
    public void setPFrom(Integer PFrom) {
        this.PFrom = PFrom;
    }

    /**
     * @return the PTo
     */
    public Integer getPTo() {
        return PTo;
    }

    /**
     * @param PTo the PTo to set
     */
    public void setPTo(Integer PTo) {
        this.PTo = PTo;
    }

    /**
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return the affiliation
     */
    public String getAffiliation() {
        return affiliation;
    }

    /**
     * @param affiliation the affiliation to set
     */
    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
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

    /**
     * @return the bookChapterAuthors
     */
    public Set<BookChapterAuthor> getBookChapterAuthors() {
        return bookChapterAuthors;
    }

    /**
     * @param bookChapterAuthors the bookChapterAuthors to set
     */
    public void setBookChapterAuthors(Set<BookChapterAuthor> bookChapterAuthors) {
        this.bookChapterAuthors = bookChapterAuthors;
    }

    /**
     * @return the fname
     */
    public ArrayList<String> getFname() {
        return fname;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(ArrayList<String> fname) {
        this.fname = fname;
    }

    /**
     * @return the lname
     */
    public ArrayList<String> getLname() {
        return lname;
    }

    /**
     * @param lname the lname to set
     */
    public void setLname(ArrayList<String> lname) {
        this.lname = lname;
    }

    /**
     * @return the BCListList
     */
    public List<BookChapter> getBCListList() {
        return BCListList;
    }

    /**
     * @param BCListList the BCListList to set
     */
    public void setBCListList(List<BookChapter> BCListList) {
        this.BCListList = BCListList;
    }

    /**
     * @return the bdao
     */
    public MyProfileDAO getBdao() {
        return bdao;
    }

    /**
     * @param bdao the bdao to set
     */
    public void setBdao(MyProfileDAO bdao) {
        this.bdao = bdao;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the infoDeleted
     */
    public String getInfoDeleted() {
        return infoDeleted;
    }

    /**
     * @param infoDeleted the infoDeleted to set
     */
    public void setInfoDeleted(String infoDeleted) {
        this.infoDeleted = infoDeleted;
    }

    /**
     * @return the infoUpdated
     */
    public String getInfoUpdated() {
        return infoUpdated;
    }

    /**
     * @param infoUpdated the infoUpdated to set
     */
    public void setInfoUpdated(String infoUpdated) {
        this.infoUpdated = infoUpdated;
    }
}
