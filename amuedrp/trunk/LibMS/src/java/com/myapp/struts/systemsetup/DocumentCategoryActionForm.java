/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;


/**
 *
 * @author EdRP-05
 */
public class DocumentCategoryActionForm extends org.apache.struts.action.ActionForm {
private String document_category_id;
private String document_category_name;
private String issue_check;
private String library_id;
private String sub_library_id;
private String button;

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getLibrary_id() {
        return library_id;
    }

    public void setLibrary_id(String library_id) {
        this.library_id = library_id;
    }

    public String getSub_library_id() {
        return sub_library_id;
    }

    public void setSub_library_id(String sub_library_id) {
        this.sub_library_id = sub_library_id;
    }

    public String getDocument_category_id() {
        return document_category_id;
    }

    public void setDocument_category_id(String document_category_id) {
        this.document_category_id = document_category_id;
    }

    public String getDocument_category_name() {
        return document_category_name;
    }

    public void setDocument_category_name(String document_category_name) {
        this.document_category_name = document_category_name;
    }

    public String getIssue_check() {
        return issue_check;
    }

    public void setIssue_check(String issue_check) {
        this.issue_check = issue_check;
    }
}
