package org.IGNOU.ePortfolio.Model;
// Generated Feb 24, 2012 11:44:15 AM by Hibernate Tools 3.2.1.GA

/**
 * BookChapterAuthor generated by hbm2java
 * @author IGNOU Team
 * @version 1
 * @since 23-02-2012 Last Modified on 24-02-2012
 */
public class BookChapterAuthor implements java.io.Serializable {

    private Long authorId;
    private BookChapter bookChapter;
    private String fname;
    private String lname;

    public BookChapterAuthor() {
    }

    public BookChapterAuthor(BookChapter bookChapter, String fname, String lname) {
        this.bookChapter = bookChapter;
        this.fname = fname;
        this.lname = lname;
    }

    public Long getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public BookChapter getBookChapter() {
        return this.bookChapter;
    }

    public void setBookChapter(BookChapter bookChapter) {
        this.bookChapter = bookChapter;
    }

    public String getFname() {
        return this.fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return this.lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
}
