/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import java.util.ArrayList;

/**
 *
 * @author Algox
 */
public class ThemeBean {

    private String theme = "classic";

    private ArrayList<String> themes = new ArrayList<String>();

    public ArrayList<String> getThemes() {
        if(themes.isEmpty())
        {
            themes.add("classic");
            themes.add("DEFAULT");
            themes.add("deepMarine");
            themes.add("ruby");
            themes.add("japanCherry");
            themes.add("wine");
            themes.add("blueSky");
            themes.add("emeraldTown");
            themes.add("plain");
        }
        return themes;
    }

    public void setThemes(ArrayList<String> themes) {
        this.themes = themes;
    }

    

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
    

}
