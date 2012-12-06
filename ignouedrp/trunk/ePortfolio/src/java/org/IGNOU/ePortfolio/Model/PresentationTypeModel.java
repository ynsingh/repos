/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Model;

/**
 *
 * @author IGNOU Team
 * @version 1
 * @since 22-12-2011
 */
public class PresentationTypeModel {

    private String typeId;
    private String typeName;

    public PresentationTypeModel(String typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public PresentationTypeModel() {
    }

    /**
     * @return the typeId
     */
    public String getTypeId() {
        return typeId;
    }

    /**
     * @param typeId the typeId to set
     */
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    /**
     * @return the typeName
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * @param typeName the typeName to set
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
