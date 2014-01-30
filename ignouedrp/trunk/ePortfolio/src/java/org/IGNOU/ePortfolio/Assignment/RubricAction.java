/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Assignment;

import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author Vinay
 */
public class RubricAction extends ActionSupport {

    private String category[], level[], point[];

    public RubricAction() {
    }

    @Override
    public String execute() throws Exception {
        System.out.println("Cat" + getCategory().length + ", level" + getLevel().length + ", point" + getPoint().length);
        for (int i = 0; i < category.length; i++) {
            if (category[i].equals("") || category[i] == null) {
            } else {
                System.out.println("Category " + category[i]);
            }
            int count = 0;
            for (int j = 0; j < level.length; j++) {
                if (level[count + j].equals("") || level[count + j] == null) {
                } else {
                    System.out.println("Level " + level[count + j]);
                    System.out.println("Point " + point[count + j]);
                }
            }
            // count = count + j;
        }
        return SUCCESS;
    }

    /**
     * @return the category
     */
    public String[] getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String[] category) {
        this.category = category;
    }

    /**
     * @return the level
     */
    public String[] getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(String[] level) {
        this.level = level;
    }

    /**
     * @return the point
     */
    public String[] getPoint() {
        return point;
    }

    /**
     * @param point the point to set
     */
    public void setPoint(String[] point) {
        this.point = point;
    }
}
