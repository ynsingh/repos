/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.cataloguing;

import java.io.Serializable;
import java.util.StringTokenizer;
public class catalogue  implements Serializable{
    private String  statement_responsibility,subject,location,main_entry,accession_no,isbn10,no_of_pages,index_no,call_no,location_name,shelving_location;
private String title;
private String subtitle;
private String publisher_name;
private String publication_place;
private String publishing_year;
private String added_entry;
private String added_entry1;
private String added_entry2;
private String main_entry1,main_entry2,added_entry11,added_entry22,pub_name_year;

    public String getPub_name_year() {
        return pub_name_year;
    }

    public void setPub_name_year(String pub_name_year) {
        this.pub_name_year = pub_name_year;
    }


    public String getAdded_entry11() {
        return added_entry11;
    }

    public void setAdded_entry11(String added_entry11) {
        this.added_entry11 = added_entry11;
    }

    public String getAdded_entry22() {
        return added_entry22;
    }

    public void setAdded_entry22(String added_entry22) {
        this.added_entry22 = added_entry22;
    }



    public String getMain_entry1() {
        return main_entry1;
    }

    public void setMain_entry1(String main_entry1) {
        this.main_entry1 = main_entry1;
    }

    public String getMain_entry2() {
        return main_entry2;
    }

    public void setMain_entry2(String main_entry2) {
        this.main_entry2 = main_entry2;
    }



    public String getAdded_entry() {
        return added_entry;
    }

    String delim="[ .]+";
    public void setAdded_entry(String added_entry) {
        this.added_entry = added_entry;
        if(added_entry.isEmpty()==false){
        String[] token=added_entry.split(delim);
        if(token!=null){
		if(token.length>0)
	            this.added_entry11=token[1]+", "+token[0]+". , ";
		
		}
        }
    }

    public String getAdded_entry1() {
        return added_entry1;

    }

    public void setAdded_entry1(String added_entry1) {
        this.added_entry1 = added_entry1;
        
        if(added_entry1.isEmpty()==false)
        {
            String[] token1=added_entry1.split(delim);
		 if(token1!=null)
		{     if(token1.length>0)  this.added_entry22=token1[1]+", "+token1[0]+". , ";}
        }
    }

    public String getAdded_entry2() {
        return added_entry2;
    }

    public void setAdded_entry2(String added_entry2) {
        this.added_entry2 = added_entry2;
    }


    public String getPublication_place() {
        return publication_place;
    }

    public void setPublication_place(String publication_place) {
        this.publication_place = publication_place;
    }

    public String getPublisher_name() {
        return publisher_name;
    }

    public void setPublisher_name(String publisher_name) {
        this.publisher_name = publisher_name;
        if(this.getPub_name_year()==null)
            this.pub_name_year=publisher_name;
        else
            this.publisher_name=publisher_name+","+this.getPub_name_year();
    }

    public String getPublishing_year() {
        return publishing_year;

    }

    public void setPublishing_year(String publishing_year) {
        this.publishing_year = publishing_year;
        if(this.getPub_name_year()!=null)
                this.pub_name_year=this.getPub_name_year()+",   "+"["+publishing_year+"].";
    }

    public String getShelving_location() {
        return shelving_location;
    }

    public void setShelving_location(String shelving_location) {
        this.shelving_location = shelving_location;
    }


    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

   
    /**
     * @return the statement_responsibility
     */
    public String getStatement_responsibility() {
        return statement_responsibility;
    }

    /**
     * @param statement_responsibility the statement_responsibility to set
     */
    public void setStatement_responsibility(String statement_responsibility) {
        this.statement_responsibility = statement_responsibility;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the main_entry
     */
    public String getMain_entry() {
        return main_entry;
    }

    /**
     * @param main_entry the main_entry to set
     */
    public void setMain_entry(String main_entry) {
        this.main_entry = main_entry;
        StringTokenizer token=new StringTokenizer(main_entry);
        String  me1=token.nextToken();
        String me2=token.nextToken();
        this.main_entry1=me1;
        this.main_entry2=me2;
        
    }

    /**
     * @return the accession_no
     */
    public String getAccession_no() {
        return accession_no;
    }

    /**
     * @param accession_no the accession_no to set
     */
    public void setAccession_no(String accession_no) {
        this.accession_no = accession_no;
    }

    /**
     * @return the isbn10
     */
    public String getIsbn10() {
        return isbn10;
    }

    /**
     * @param isbn10 the isbn10 to set
     */
    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    /**
     * @return the no_of_pages
     */
    public String getNo_of_pages() {
        return no_of_pages;
    }

    /**
     * @param no_of_pages the no_of_pages to set
     */
    public void setNo_of_pages(String no_of_pages) {
        this.no_of_pages = no_of_pages;
    }

    /**
     * @return the index_no
     */
    public String getIndex_no() {
        return index_no;
    }

    /**
     * @param index_no the index_no to set
     */
    public void setIndex_no(String index_no) {
        this.index_no = index_no;
    }

    /**
     * @return the call_no
     */
    public String getCall_no() {
        return call_no;
    }

    /**
     * @param call_no the call_no to set
     */
    public void setCall_no(String call_no) {
        this.call_no = call_no;
    }

    /**
     * @return the location_name
     */
    public String getLocation_name() {
        return location_name;
    }

    /**
     * @param location_name the location_name to set
     */
    public void setLocation_name(String location_name) {
        this.location_name = location_name;
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

}
