/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.cataloguing;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author Client
 */
public class BibliographicDetailEntryActionForm extends org.apache.struts.validator.ValidatorForm {
    
    private int biblio_id;
    private String library_id;
    private String document_type;
    private String accession_type;//For retrosepctive document it is 'old' and for new document it is 'new'
    private String title;
    private String subtitle;
    private String author_main;
    private String author_sub;
    private String publisher_name;
    private String publication_place;
    private int publishing_year;
    private String control_no;
    private String call_no;
    private String isbn10;
    private String isbn13;
    private String price;
    private String edition;
    private String index_no;
    private String no_of_pages;
    private String physical_width;
    private String bind_type;    
    private String button;
    private String no_of_copies;
 
    

    public int getBiblio_id() {
        return biblio_id;
    }

    public void setBiblio_id(int biblio_id) {
        this.biblio_id = biblio_id;
    }

    public String getNo_of_copies() {
        return no_of_copies;
    }

    public void setNo_of_copies(String no_of_copies) {
        this.no_of_copies = no_of_copies;
    }

    public String getAccession_type() {
        return accession_type;
    }

    public void setAccession_type(String accession_type) {
        this.accession_type = accession_type;
    }

    public String getCall_no() {
        return call_no;
    }

    public void setCall_no(String call_no) {
        this.call_no = call_no;
    }

    public String getAuthor_main() {
        return author_main;
    }

    public void setAuthor_main(String author_main) {
        this.author_main = author_main;
    }

    public String getAuthor_sub() {
        return author_sub;
    }

    public void setAuthor_sub(String author_sub) {
        this.author_sub = author_sub;
    }



    public String getBind_type() {
        return bind_type;
    }

    public void setBind_type(String bind_type) {
        this.bind_type = bind_type;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getControl_no() {
        return control_no;
    }

    public void setControl_no(String control_no) {
        this.control_no = control_no;
    }

    public String getDocument_type() {
        return document_type;
    }

    public void setDocument_type(String document_type) {
        this.document_type = document_type;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getIndex_no() {
        return index_no;
    }

    public void setIndex_no(String index_no) {
        this.index_no = index_no;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getLibrary_id() {
        return library_id;
    }

    public void setLibrary_id(String library_id) {
        this.library_id = library_id;
    }

    public String getNo_of_pages() {
        return no_of_pages;
    }

    public void setNo_of_pages(String no_of_pages) {
        this.no_of_pages = no_of_pages;
    }

    public String getPhysical_width() {
        return physical_width;
    }

    public void setPhysical_width(String physical_width) {
        this.physical_width = physical_width;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
    }

    public int getPublishing_year() {
        return publishing_year;
    }

    public void setPublishing_year(int publishing_year) {
        this.publishing_year = publishing_year;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
