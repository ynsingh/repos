/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import java.io.Serializable;

/**
 *
 * @author edrp02
 */
public class AcqAccession implements Serializable{

    String prn,invoice_no,recieving_no,recieved_copies,title,doc_type,publisher_name,author,lcc_no,sub_author,publishing_yr,publishing_place,edition,isbn,volume_no,sub_author0,sub_author1,sub_author2;
    int title_id,control_no;

    public int getControl_no() {
        return control_no;
    }

    public void setControl_no(int control_no) {
        this.control_no = control_no;
    }

    public String getInvoice_no() {
        return invoice_no;
    }

    public void setInvoice_no(String invoice_no) {
        this.invoice_no = invoice_no;
    }

    public String getPrn() {
        return prn;
    }

    public void setPrn(String prn) {
        this.prn = prn;
    }

    public String getRecieving_no() {
        return recieving_no;
    }

    public void setRecieving_no(String recieving_no) {
        this.recieving_no = recieving_no;
    }

    
    public int getTitle_id() {
        return title_id;
    }

    public void setTitle_id(int title_id) {
        this.title_id = title_id;
    }

    
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDoc_type() {
        return doc_type;
    }

    public void setDoc_type(String doc_type) {
        this.doc_type = doc_type;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getLcc_no() {
        return lcc_no;
    }

    public void setLcc_no(String lcc_no) {
        this.lcc_no = lcc_no;
    }

    public String getPublisher_name() {
        return publisher_name;
    }

    public void setPublisher_name(String publisher_name) {
        this.publisher_name = publisher_name;
    }

    public String getPublishing_place() {
        return publishing_place;
    }

    public void setPublishing_place(String publishing_place) {
        this.publishing_place = publishing_place;
    }

    public String getPublishing_yr() {
        return publishing_yr;
    }

    public void setPublishing_yr(String publishing_yr) {
        this.publishing_yr = publishing_yr;
    }

    public String getRecieved_copies() {
        return recieved_copies;
    }

    public void setRecieved_copies(String recieved_copies) {
        this.recieved_copies = recieved_copies;
    }

    public String getSub_author() {
        return sub_author;
    }

    public void setSub_author(String sub_author) {
        this.sub_author = sub_author;
    }

    public String getSub_author0() {
        return sub_author0;
    }

    public void setSub_author0(String sub_author0) {
        this.sub_author0 = sub_author0;
    }

    public String getSub_author1() {
        return sub_author1;
    }

    public void setSub_author1(String sub_author1) {
        this.sub_author1 = sub_author1;
    }

    public String getSub_author2() {
        return sub_author2;
    }

    public void setSub_author2(String sub_author2) {
        this.sub_author2 = sub_author2;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVolume_no() {
        return volume_no;
    }

    public void setVolume_no(String volume_no) {
        this.volume_no = volume_no;
    }

    
}
