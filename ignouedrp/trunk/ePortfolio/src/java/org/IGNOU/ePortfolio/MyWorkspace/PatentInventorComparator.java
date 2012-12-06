/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyWorkspace;

import java.util.Comparator;
import org.IGNOU.ePortfolio.Model.Inventor;

/**
 * @author IGNOU Team
 * @version 1
 * @since 11-07-2012
 */
public class PatentInventorComparator implements Comparator<Inventor> {

    @Override
    public int compare(Inventor PI1, Inventor PI2) {
        String I1 = PI1.getIdInventor().toString();
        String I2 = PI2.getIdInventor().toString();
        return I1.compareTo(I2);
    }
}
